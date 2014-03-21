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

    private String leId; // Lookup Element ID
    private String tableAlias;
    private VariantsProviderImpl.ColumnElement it;

    protected SelectFieldLookupElement(T delegate, String leId, String tableAlais, VariantsProviderImpl.ColumnElement it) {
        super(delegate);
        this.leId = leId;
        this.tableAlias = tableAlais;
        this.it = it;
    }

    public static SelectFieldLookupElement create(String table_alias, final VariantsProviderImpl.ColumnElement it) {
        LookupElement e = LookupElementBuilder.create(it.getName())
                .withTailText(it.getTail(), true)
                .withTypeText(it.getType())
                .withIcon(it.getIcon())
                .withCaseSensitivity(false)
                .withStrikeoutness(it.isStrikeout());

        String _leId = it.getName() + (it.getTail() != null ? it.getTail() : "")
                + (it.tableAlias!=null? it.tableAlias: "");
        return new SelectFieldLookupElement<LookupElement>(e, _leId, table_alias, it);
    }

    public static LookupElement create( VariantsProviderImpl.ColumnElement it) {
        return create(null, it);
    }

    public void handleInsert(InsertionContext context) {

        if (tableAlias == null) {
            final Editor editor = context.getEditor();
            String prefix = it.getQualifyName();
            if(prefix != null){
                editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix + ".");
                emulateInsertion(getDelegate(), context.getTailOffset(), context);
            }
        }
    }

    public static void emulateInsertion(LookupElement item, int offset, InsertionContext context) {
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
        return leId.equals(that.leId);
    }


    public int hashCode() {
        return leId.hashCode();
    }

    public String getSuggestedQualifyName(){
        return it.getQualifyName();
    }

    public String getSuggestedName(){
        return it.getName();
    }

    public VariantsProviderImpl.ColumnElement getIt(){
        return it;
    }

}
