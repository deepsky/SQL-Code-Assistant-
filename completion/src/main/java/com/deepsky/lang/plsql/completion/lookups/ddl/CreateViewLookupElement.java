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
import com.deepsky.lang.plsql.completion.lookups.TableLookupElement;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;

public class CreateViewLookupElement<T extends LookupElement> extends DDLLookupElementBase<T> {

    protected CreateViewLookupElement(T delegate) {
        super(delegate);
    }

    public static CreateViewLookupElement create() {//} String table_alias, final VariantsProviderImpl.ColumnElement it, boolean forceUsingTableAlias) {
        LookupElement e = LookupElementBuilder.create("select")//it.getName())
//                .withTailText(it.getTail(), true)
//                .withTypeText(it.getType())
//                .withIcon(it.getIcon())
                .withPresentableText("create view .. as select .. from <table>")
                .withCaseSensitivity(false)
                .withStrikeoutness(false); //it.isStrikeout());

//        String _leId = it.getName() + (it.getTail() != null ? it.getTail() : "")
//                + (it.tableAlias!=null? it.tableAlias: "");
        return new CreateViewLookupElement<LookupElement>(e);
    }


    public void handleInsert(InsertionContext context) {

        final Editor editor = context.getEditor();
        String prefix = "create view view1 as select * from dual;"; //it.getQualifyName(); //forceUsingTableAlias);
        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
        emulateInsertion(TableLookupElement.create("", Icons.TABLE), context.getTailOffset(), context);

//                setOffsets(context, offset, offset);

        //final Editor editor = context.getEditor();
        int offset = editor.getCaretModel().getOffset() + prefix.length() + 1;
        final Document document = editor.getDocument();
        LookupElement item = TableLookupElement.create("", Icons.TABLE);
        final String lookupString = item.getLookupString();

        document.insertString(offset, lookupString);
        editor.getCaretModel().moveToOffset(context.getTailOffset());
        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
        item.handleInsert(context);
    }

}
