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

package com.deepsky.lang.plsql.completion.lookups;

import com.deepsky.lang.plsql.completion.VariantsProviderImpl;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;


public class SelectFieldLookupElement<T extends LookupElement> extends LookupElementDecorator<T> {

    private String sign;
    private String tableAlias;
    VariantsProviderImpl.ColumnElement it;

    // configurable parameter
    boolean forceUsingTableAlias = false;

    protected SelectFieldLookupElement(T delegate, String sign, String tableAlais, VariantsProviderImpl.ColumnElement it) {
        super(delegate);
        this.sign = sign;
        this.tableAlias = tableAlais;
        this.it = it;
    }

    public static LookupElement create(String table_alias, VariantsProviderImpl.ColumnElement it) {
        LookupElement e = LookupElementBuilder.create(it.getName())
                .setTailText(it.getTail(), true)
                .setTypeText(it.getType())
                .setIcon(it.getIcon())
                .setCaseSensitive(false)
                .setStrikeout(it.isStrikeout());

        String _sign = it.getName() + (it.getTail() != null ? it.getTail() : "");
        return new SelectFieldLookupElement<LookupElement>(e, _sign, table_alias, it);
    }

    public void handleInsert(InsertionContext context) {

        if (tableAlias == null) {
            if (it.isFullQualifiedColumn() && it.getQualifyName() != null) {
                final Editor editor = context.getEditor();
                String prefix = it.getQualifyName();
                editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix + ".");
                emulateInsertion(getDelegate(), context.getTailOffset(), context);

            }
            if (!it.isFullQualifiedColumn() && forceUsingTableAlias && it.getQualifyName() != null) {
                final Editor editor = context.getEditor();
                String prefix = it.getQualifyName();
                editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix + ".");
                emulateInsertion(getDelegate(), context.getTailOffset(), context);

            }
        }

//        if(it.tableAlias.length() > 0 && tableAlias == null){
//            final Editor editor = context.getEditor();
//            editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), it.tableAlias + "." );
//            emulateInsertion(getDelegate(), context.getTailOffset(), context);
//
//        } else if(it.tableAlias.length() == 0 && tableAlias == null && it.isFullQualifiedColumn()){
//            final Editor editor = context.getEditor();
//            editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), it.tableName + "." );
//            emulateInsertion(getDelegate(), context.getTailOffset(), context);
//
//        }
    }


    public static void emulateInsertion(LookupElement item, int offset, InsertionContext context) {
        //setOffsets(context, offset, offset);

        final Editor editor = context.getEditor();
        final Document document = editor.getDocument();
        final String lookupString = item.getLookupString();

        document.insertString(offset, lookupString);
        editor.getCaretModel().moveToOffset(context.getTailOffset());
        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectFieldLookupElement that = (SelectFieldLookupElement) o;
        return sign.equals(that.sign);
    }


    public int hashCode() {
        return sign.hashCode();
    }

}
