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

import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.deepsky.lang.plsql.completion.lookups.LookupUtils;
import com.deepsky.lang.plsql.completion.lookups.SelectFieldLookupElement;
import com.deepsky.lang.plsql.completion.lookups.TableLookupElement;
import com.deepsky.lang.plsql.psi.ColumnSpec;
import com.deepsky.lang.plsql.psi.ColumnSpecList;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.codeStyle.CodeStyleManager;

import java.util.ArrayList;
import java.util.List;


public class InsertLookupElement<T extends LookupElement> extends LookupElementDecorator<T> {

    protected InsertLookupElement(T delegate) {
        super(delegate);
    }

    public static InsertLookupElement create() {//} String table_alias, final VariantsProviderImpl.ColumnElement it, boolean forceUsingTableAlias) {
        LookupElement e = LookupElementBuilder.create("insert")//it.getName())
//                .withTailText(it.getTail(), true)
//                .withTypeText(it.getType())
//                .withIcon(it.getIcon())
                .withPresentableText("insert into <table> (column1, ..) values (..)")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "insert into "; //it.getQualifyName(); //forceUsingTableAlias);
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
//        emulateInsertion(TableLookupElement.create("", Icons.TABLE), context.getTailOffset(), context);

//                        LookupElement item = TableLookupElement.create("", Icons.TABLE);
                        final Document document = editor.getDocument();

//                        final String lookupString = item.getLookupString();
//                        document.insertString(context.getTailOffset(), lookupString);
                        editor.getCaretModel().moveToOffset(context.getTailOffset());
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
                .withStrikeoutness(false); //it.isStrikeout());

        return new InsertLookupElement<LookupElement>(e);
    }


    public void ____handleInsert(InsertionContext context) {
        final Editor editor = context.getEditor();
        String prefix = "insert into "; //it.getQualifyName(); //forceUsingTableAlias);
        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
//        emulateInsertion(TableLookupElement.create("", Icons.TABLE), context.getTailOffset(), context);

        LookupElement item = TableLookupElement.create("", Icons.TABLE);
        final Document document = editor.getDocument();
        final String lookupString = item.getLookupString();

        document.insertString(context.getTailOffset(), lookupString);
        editor.getCaretModel().moveToOffset(context.getTailOffset());
//        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);


//                setOffsets(context, offset, offset);

        //final Editor editor = context.getEditor();
//        int offset = editor.getCaretModel().getOffset() + prefix.length() + 1;
//        final Document document = editor.getDocument();
//        LookupElement item = TableLookupElement.create("", Icons.TABLE);
//        final String lookupString = item.getLookupString();
//
//        document.insertString(offset, lookupString);
//        editor.getCaretModel().moveToOffset(context.getTailOffset());

/*
There is no fixed set of events that can trigger parsing. The parsing will occur every time someone
has called PsiDocumentManager.commitDocument(). Parsing for autopopup code completion is performed
in a background thread and should not affect editor responsiveness.
*/
        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

/*
        int startOffset = context.getStartOffset();
        CodeStyleManager.getInstance(context.getProject()).reformatText(context.getFile(),
                startOffset,
                startOffset + prefix.length() + 1);
*/

        LookupUtils.scheduleAutoPopup(editor, context);
        //item.handleInsert(context);

    }


/*
    public void renderElement(LookupElementPresentation presentation) {
        getDelegate().renderElement(presentation);
//        final String castType = getItemText(presentation, getCastItem());
//        presentation.setItemText("(" + castType + ")" + presentation.getItemText());
        presentation.setTypeText("select ... from <table>");
    }
*/


    public static LookupElement createInsertIntoTab(VariantsProvider provider, final String tableName) {
        LookupElement e = LookupElementBuilder.create("")
                .withPresentableText("insert into " + tableName + " values ()")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = " values ()";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

                        int startOffset = context.getStartOffset();
                        CodeStyleManager.getInstance(context.getProject()).reformatText(context.getFile(),
                                startOffset,
                                startOffset + prefix.length() + 1);

                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new InsertLookupElement<LookupElement>(e);
    }


    public static LookupElement createInsertIntoTabColumnList(VariantsProvider provider, String tableName) {
        String columnList = buildColumnSpecList(provider.collectColumnNames(tableName, ""), 40);
        LookupElement e = LookupElementBuilder.create("")
                .withPresentableText("insert into " + tableName + " (" + columnList + ") ..")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = " () ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();
                        editor.getCaretModel().moveToOffset(context.getTailOffset()-2);
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new InsertLookupElement<LookupElement>(e);
    }


    public static LookupElement createInsertFromSelect(String tableName, ColumnSpecList list) {
        String columnList = buildColumnSpecList(list, 40);
        LookupElement e = LookupElementBuilder.create("select")
                .withPresentableText("insert into " + tableName + " (" + columnList + ") select .. from <table>")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = " select * from ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new InsertLookupElement<LookupElement>(e);
    }


    public static void emulateInsertion(String lookupString, int offset, InsertionContext context) {
        final Editor editor = context.getEditor();
        final Document document = editor.getDocument();

//        document.insertString(offset, lookupString);
        document.replaceString(context.getStartOffset(), context.getTailOffset(), lookupString);
        editor.getCaretModel().moveToOffset(context.getTailOffset() + 1);
        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
    }

    static String buildColumnSpecList(ColumnSpecList list, int maxSize){
        List<String> columns = new ArrayList<String>();
        for(ColumnSpec column: list.getColumns()){
            columns.add(column.getText());
        }
        String columnList = Formatter.formatColumnSpecList(columns, maxSize);
        return columnList.length() == 0? "..": columnList;
    }

    static String buildColumnSpecList(List<LookupElement> list, int maxSize){
        List<String> columns = new ArrayList<String>();
        for(LookupElement e: list){
            columns.add(((SelectFieldLookupElement)e).getSuggestedName());
        }
        String columnList = Formatter.formatColumnSpecList(columns, maxSize);
        return columnList.length() == 0? "..": columnList;
    }
}
