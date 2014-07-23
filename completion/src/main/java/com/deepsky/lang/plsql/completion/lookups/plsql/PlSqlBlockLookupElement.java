/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.completion.lookups.plsql;

import com.deepsky.lang.common.PlSqlFileType;
import com.deepsky.lang.common.PlSqlLanguage;
import com.deepsky.lang.plsql.completion.lookups.LookupUtils;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;

public class PlSqlBlockLookupElement<T extends LookupElement> extends LookupElementDecorator<T> {

    protected PlSqlBlockLookupElement(T delegate) {
        super(delegate);
    }

    public static PlSqlBlockLookupElement create(final boolean doFinalize) {
        LookupElement e = LookupElementBuilder.create("begin")
                .withPresentableText("begin .. end")
                .withTypeText("Create PL/SQL block")
                .withCaseSensitivity(false)
                .withStrikeoutness(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();

                        String prefix = "begin\n\t\nend;" + (doFinalize? "\n/": "\n");
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);

                        final Document document = editor.getDocument();

                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

                        int startOffset = context.getStartOffset();
                        CodeStyleManager.getInstance(context.getProject()).reformatText(context.getFile(),
                                startOffset,
                                startOffset + prefix.length() + 1);

                        final int line = editor.getCaretModel().getLogicalPosition().line;
                        final int column = editor.getCaretModel().getLogicalPosition().column;
                        final CodeStyleSettings styleSettings = CodeStyleSettingsManager.getSettings(context.getProject());
                        final int indentSize = styleSettings.getIndentSize(PlSqlFileType.FILE_TYPE);
                        final LogicalPosition pos = new LogicalPosition(line+1, column-5+indentSize);

                        editor.getCaretModel().moveToLogicalPosition(pos);

                        LookupUtils.scheduleAutoPopup(editor, context);
                        editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
                    }
                });

        return new PlSqlBlockLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 3)
        );
    }

    public static PlSqlBlockLookupElement createDeclare() {
        LookupElement e = LookupElementBuilder.create("declare")
                .withPresentableText("declare .. begin .. end")
                .withTypeText("Create anonymous PL/SQL block")
                .withCaseSensitivity(false)
                .withStrikeoutness(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();

                        final int line = editor.getCaretModel().getLogicalPosition().line;
                        final int column = editor.getCaretModel().getLogicalPosition().column;
                        final CodeStyleSettings styleSettings = CodeStyleSettingsManager.getSettings(context.getProject());
                        final int indentSize = styleSettings.getIndentSize(PlSqlFileType.FILE_TYPE);
                        final LogicalPosition pos = new LogicalPosition(line+1, column-7+indentSize);

                        String prefix = "declare\n\t\nbegin\n\t\nend;\n/\n";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);

                        final Document document = editor.getDocument();
                        editor.getCaretModel().moveToOffset(context.getTailOffset()-5);
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

                        editor.getCaretModel().moveToLogicalPosition(pos);

                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                });

        return new PlSqlBlockLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 3)
        );
    }
}
