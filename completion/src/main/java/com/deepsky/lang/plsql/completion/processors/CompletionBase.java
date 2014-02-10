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

package com.deepsky.lang.plsql.completion.processors;

import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.deepsky.lang.plsql.completion.lookups.KeywordLookupElement;
import com.deepsky.lang.plsql.completion.lookups.LookupUtils;
import com.deepsky.lang.plsql.completion.lookups.dml.DeleteLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.InsertLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.SelectLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.UpdateLookupElement;
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.TableAlias;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class CompletionBase {

    protected boolean is2ndLatest(@NotNull ASTNode root, @NotNull ASTNode child){
        return child.getTextRange().getEndOffset() == root.getTextRange().getEndOffset();
    }


    protected void collectColumns(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        provider.collectColumnVariants(select, prevText);

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }


    protected void collectColumns(C_Context ctx, TableAlias tableName, boolean forceUsingTableAlias) {
        VariantsProvider provider = ctx.getProvider();
        provider.collectColumnNames(tableName, ctx.getLookup(), forceUsingTableAlias);

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }


    protected  void collectTableNames(C_Context ctx){
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }

    protected  void collectTableNamesFinalize(C_Context ctx){
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup(), new InsertHandler<LookupElement>() {
            @Override
            public void handleInsert(InsertionContext context, LookupElement item) {
                final Editor editor = context.getEditor();
                String prefix = item.getLookupString() + ";";
                editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                editor.getCaretModel().moveToOffset(context.getTailOffset());
                final Document document = editor.getDocument();
                PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
            }
        }));

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }

    protected  void collectTableViewNames(C_Context ctx){
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup()));
        variants.addAll(provider.collectViewNameVariants(ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }

    protected void completeStart(C_Context ctx) {
//        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("select"));
//        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("insert"));
//        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("update"));
//        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("delete"));
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(SelectLookupElement.create());
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(SelectLookupElement.createSelectFromSelect());
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(InsertLookupElement.create());
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(UpdateLookupElement.create());
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(DeleteLookupElement.create());
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("create"));
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("drop"));
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("alter"));
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("comment"));
    }

}
