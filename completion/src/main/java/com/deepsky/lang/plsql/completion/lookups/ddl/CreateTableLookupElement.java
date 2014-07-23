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

package com.deepsky.lang.plsql.completion.lookups.ddl;

import com.deepsky.lang.plsql.completion.lookups.DDLLookupElementBase;
import com.deepsky.lang.plsql.completion.lookups.LookupUtils;
import com.deepsky.lang.plsql.completion.lookups.TableLookupElement;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;

public class CreateTableLookupElement<T extends LookupElement> extends DDLLookupElementBase<T> {

    protected CreateTableLookupElement(T delegate) {
        super(delegate);
    }

    public static CreateTableLookupElement createRegular() {
        LookupElement e = LookupElementBuilder.create("create table")
                .withTypeText("Create regular table", true)
                .withIcon(Icons.TABLE)
                .withPresentableText("create table <table name> (..)")
                .withCaseSensitivity(false)
                .withStrikeoutness(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "create table (\n);";
                        prefix = adoptPrefix(item.getLookupString(), editor.getDocument().getText(), context.getStartOffset(), prefix);
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);

                        final Document document = editor.getDocument();
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

                        editor.getCaretModel().moveToOffset(context.getTailOffset()-3);

                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                });

        return new CreateTableLookupElement<LookupElement>(e);
    }


    public static CreateTableLookupElement createTemporary() {
        LookupElement e = LookupElementBuilder.create("create temporary table")
                .withTypeText("Create temporary table", true)
                .withIcon(Icons.TEMP_TABLE)
                .withPresentableText("create temporary table <table name> (..) on commit ..")
                .withCaseSensitivity(false)
                .withStrikeoutness(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "create temporary table (\n) on commit preserve rows;";
                        prefix = adoptPrefix(
                                item.getLookupString(),
                                editor.getDocument().getText(),
                                context.getStartOffset(), prefix);

                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);

                        final Document document = editor.getDocument();
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

                        editor.getCaretModel().moveToOffset(context.getTailOffset()-4);

                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                });

        return new CreateTableLookupElement<LookupElement>(e);
    }


/*
    public void renderElement(LookupElementPresentation presentation) {
        getDelegate().renderElement(presentation);
//        final String castType = getItemText(presentation, getCastItem());
//        presentation.setItemText("(" + castType + ")" + presentation.getItemText());
        presentation.setTypeText("select ... from <table>");
    }
*/


}

