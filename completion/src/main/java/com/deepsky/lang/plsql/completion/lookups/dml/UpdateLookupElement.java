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

package com.deepsky.lang.plsql.completion.lookups.dml;


import com.deepsky.lang.plsql.completion.lookups.LookupUtils;
import com.deepsky.lang.plsql.psi.ColumnSpec;
import com.deepsky.lang.plsql.psi.ColumnSpecList;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class UpdateLookupElement<T extends LookupElement> extends LookupElementDecorator<T> {
    protected UpdateLookupElement(T delegate) {
        super(delegate);
    }

    public static UpdateLookupElement create() {
        LookupElement e = LookupElementBuilder.create("update")
                .withPresentableText("update <table> set ...")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "update  set";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

//                        final String lookupString = item.getLookupString();
//                        document.insertString(context.getTailOffset(), lookupString);
                        editor.getCaretModel().moveToOffset(context.getTailOffset() - 4);
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

        return new UpdateLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 1)
        );
    }

    public static LookupElement createSubqueryEq(@NotNull String table, @Nullable ColumnSpec[] cs) {
        String columns = "..."; // we don't know column name by default
        if(cs != null){
            StringBuilder b = new StringBuilder();
            for(int i = 0 ; i<cs.length; i++){
                b.append(cs[i].getColumnNameRef());
                if(i != cs.length -1){
                    b.append(",");
                }
            }
            columns = b.toString();
        }
        LookupElement e = LookupElementBuilder.create("=")
                .withPresentableText("update "+table+" set ("+ columns + ") = (select ... from <table>)")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "= (select * from );";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset() - 2);
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new UpdateLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 1));
    }

    public static LookupElement createSubqueryParen(@NotNull String table, @Nullable ColumnSpec[] cs) {
        String columns = "..."; // we don't know column name by default
        if(cs != null){
            StringBuilder b = new StringBuilder();
            for(int i = 0 ; i<cs.length; i++){
                b.append(cs[i].getColumnNameRef());
                if(i != cs.length -1){
                    b.append(",");
                }
            }
            columns = b.toString();
        }
        LookupElement e = LookupElementBuilder.create("(")
                .withPresentableText("update "+table+" set ("+ columns + ") = (select ... from <table>)")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "() =";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset() - 3);
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new UpdateLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 1));
    }

    public static String buildColumnSpecList(ColumnSpecList list, int maxSize){
        List<String> columns = new ArrayList<String>();
        for(ColumnSpec column: list.getColumns()){
            columns.add(column.getText());
        }
        String columnList = Formatter.formatColumnSpecList(columns, maxSize);
        return columnList.length() == 0? "..": columnList;
    }

}

