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

package test.deepsky.lang.plsql.completion2;

import com.intellij.codeInsight.lookup.Lookup;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.codeInsight.lookup.impl.LookupImpl;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.statistics.StatisticsManager;
import com.intellij.psi.statistics.impl.StatisticsManagerImpl;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.jetbrains.annotations.NotNull;

public abstract class LightFixtureCompletionTestCase extends LightCodeInsightFixtureTestCase {
    protected LookupElement[] myItems;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //((StatisticsManagerImpl) StatisticsManager.getInstance()).clearStatistics();
    }

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return JAVA_1_6;
    }

    @Override
    protected void tearDown() throws Exception {
        myItems = null;
        super.tearDown();
    }

    protected void configureByFile(String path) {
        myFixture.configureFromExistingVirtualFile(myFixture.copyFileToProject(path, com.intellij.openapi.util.text.StringUtil.getShortName(path, '/')));
        complete();
    }

    protected void complete() {
        myItems = myFixture.completeBasic();
    }

    protected void selectItem(LookupElement item) {
        selectItem(item, (char)0);
    }

    protected void checkResultByFile(String path) {
        myFixture.checkResultByFile(path);
    }

    protected void selectItem(LookupElement item, final char completionChar) {
        final LookupImpl lookup = getLookup();
        lookup.setCurrentItem(item);
        if (completionChar == 0 || completionChar == '\n' || completionChar == '\t' || completionChar == Lookup.COMPLETE_STATEMENT_SELECT_CHAR) {
            new WriteCommandAction.Simple(getProject()) {
                @Override
                protected void run() throws Throwable {
                    lookup.finishLookup(completionChar);
                }
            }.execute().throwException();
        } else {
            type(completionChar);
        }
    }

    protected LookupImpl getLookup() {
        return (LookupImpl) LookupManager.getInstance(getProject()).getActiveLookup();
    }

    protected void assertStringItems(String... items) {
        assertOrderedEquals(myFixture.getLookupElementStrings(), items);
    }

    protected void type(String s) {
        myFixture.type(s);
    }
    protected void type(char c) {
        myFixture.type(c);
    }
}
