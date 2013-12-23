/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.lang.plsql.formatter;

import com.deepsky.lang.common.PlSqlFileType;
import com.deepsky.lang.common.PlSqlLanguage;
import com.deepsky.lang.plsql.formatter.settings.PlSqlCodeStyleSettings;
import com.intellij.lang.Language;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.intellij.util.IncorrectOperationException;
import junit.framework.Assert;

public abstract class PlSqlFormatterTestCase extends LightCodeInsightFixtureTestCase {
    private static final Logger LOG = Logger.getInstance("#PlSqlFormatterTestCase");
    protected CodeStyleSettings myTempSettings;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setSettings(getProject());
    }

    @Override
    protected void tearDown() throws Exception {
        setSettingsBack();
        super.tearDown();
    }

    protected CommonCodeStyleSettings getCommonSettings() {
        return myTempSettings.getCommonSettings(Language.findLanguageByID(PlSqlLanguage.ID));
    }

    protected PlSqlCodeStyleSettings getCustomSettings(){
        return CodeStyleSettingsManager.getSettings(getProject()).getCustomSettings(PlSqlCodeStyleSettings.class);
    }


    protected void setSettings(Project project) {
        Assert.assertNull(myTempSettings);
        CodeStyleSettings settings = CodeStyleSettingsManager.getSettings(project);
        myTempSettings = settings.clone();

//        CommonCodeStyleSettings.IndentOptions gr = myTempSettings.getIndentOptions(PlSqlFileType.FILE_TYPE);
        CodeStyleSettings.IndentOptions gr = myTempSettings.getIndentOptions(PlSqlFileType.FILE_TYPE);
        Assert.assertNotSame(gr, settings.OTHER_INDENT_OPTIONS);
        gr.INDENT_SIZE = 4;
        gr.CONTINUATION_INDENT_SIZE = 8;
        gr.TAB_SIZE = 4;

        CodeStyleSettingsManager.getInstance(project).setTemporarySettings(myTempSettings);
    }

    protected void setSettingsBack() {
        final CodeStyleSettingsManager manager = CodeStyleSettingsManager.getInstance(getProject());
        myTempSettings.getIndentOptions(PlSqlFileType.FILE_TYPE).INDENT_SIZE = 200;
        myTempSettings.getIndentOptions(PlSqlFileType.FILE_TYPE).CONTINUATION_INDENT_SIZE = 200;
        myTempSettings.getIndentOptions(PlSqlFileType.FILE_TYPE).TAB_SIZE = 200;

        manager.dropTemporarySettings();
        myTempSettings = null;
    }

    protected void checkFormatting(String fileText, String expected) {
        myFixture.configureByText(PlSqlFileType.FILE_TYPE, fileText);
        checkFormatting(expected);
    }

    protected void doFormat(final PsiFile file) {
        CommandProcessor.getInstance().executeCommand(getProject(), new Runnable() {
            public void run() {
                ApplicationManager.getApplication().runWriteAction(new Runnable() {
                    public void run() {
                        try {
                            TextRange myTextRange = file.getTextRange();
//                            CodeStyleManager.getInstance(file.getProject()).reformatText(file, myTextRange.getStartOffset(), myTextRange.getEndOffset());
                            CodeStyleManager.getInstance(getProject()).reformatText(file, myTextRange.getStartOffset(), myTextRange.getEndOffset());
                        } catch (IncorrectOperationException e) {
                            LOG.error(e);
                        }
                    }
                });
            }
        }, null, null);
    }

    protected void checkFormatting(String expected) {
        doFormat(myFixture.getFile());
        myFixture.checkResult(expected, true);
    }
}

