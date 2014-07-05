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

import com.deepsky.lang.plsql.completion.lookups.LookupUtils;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;

public class PlSqlPackageLookupElement<T extends LookupElement> extends LookupElementDecorator<T> {

    protected PlSqlPackageLookupElement(T delegate) {
        super(delegate);
    }

    public static PlSqlPackageLookupElement createPackage() {
        LookupElement e = LookupElementBuilder.create("create package")
//                .withTailText(it.getTail(), true)
//                .withTypeText(it.getType())
//                .withIcon(it.getIcon())
                .withPresentableText("create package <package name> as ... end;")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "create package  as\nend;";
                        prefix = adoptPrefix(item.getLookupString(), editor.getDocument().getText(), context.getStartOffset(), prefix);
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset() - 8);
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

/*
                        int startOffset = context.getStartOffset();
                        CodeStyleManager.getInstance(context.getProject()).reformatText(context.getFile(),
                                startOffset,
                                startOffset + prefix.length() + 1);
*/

                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new PlSqlPackageLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }

    private static String adoptPrefix(String lookupString, String text, int endOffset, String prefix) {

        int i = endOffset;
        for (int cnt = lookupString.length() + 20; i > 0 && cnt > 0; cnt--, i--) ;

        String _prefix = LookupUtils.calcLookupPrefix(lookupString, text.substring(i, endOffset));
        if (prefix.startsWith(_prefix)) {
            return prefix.substring(_prefix.length());
        }
        return prefix;
    }


    public static PlSqlPackageLookupElement createPackageBody() {
        LookupElement e = LookupElementBuilder.create("create package body")
//                .withTailText(it.getTail(), true)
//                .withTypeText(it.getType())
//                .withIcon(it.getIcon())
                .withPresentableText("create package body <package name> as ... end;")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "create package body  as\nend;";
                        prefix = adoptPrefix(item.getLookupString(), editor.getDocument().getText(), context.getStartOffset(), prefix);
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset() - 8);
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new PlSqlPackageLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }
}
