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

import com.deepsky.lang.plsql.completion.SyntaxTreePath;
import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.deepsky.lang.plsql.completion.lookups.KeywordLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.SelectLookupElement;
import com.deepsky.lang.plsql.psi.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SyntaxTreePath("/..1#DELETE_COMMAND")
public class DeleteStmtProcessor extends CompletionBase {

    @SyntaxTreePath("/#DELETE #FROM $TableAlias/#TABLE_REF #ALIAS_NAME//2#C_MARKER")
    public void process$DeleteAliasName(C_Context ctx, ASTNode d, ASTNode marker) {
        if(is2ndLatest(d, marker)){
            // Marker is the last element of the statement
            ctx.addElement(KeywordLookupElement.create("where"));
            completeStart(ctx);
        } else {
            // Marker is in the middle of the statement
            if(((DeleteStatement)d.getPsi()).getWhereCondition() == null)
                ctx.addElement(KeywordLookupElement.create("where"));
        }
    }


    @SyntaxTreePath("/#DELETE #FROM $TableAlias/#TABLE_REF/2#C_MARKER")
    public void process$DeleteTabRef(C_Context ctx, ASTNode d, ASTNode marker) {
        if(is2ndLatest(d, marker)){
            collectTableNamesFinalize(ctx);
        } else {
            collectTableNames(ctx);
        }
    }

//    @SyntaxTreePath("(//..#WHERE_CONDITION)[0]//..#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    @SyntaxTreePath("/#DELETE #FROM 2$TableAlias #WHERE_CONDITION/..3#VAR_REF/..4$NameFragmentRef/#C_MARKER")
    public void process$DeleteWhere(C_Context ctx, ASTNode del, TableAlias t, ASTNode expr, NameFragmentRef nameRef) {
        collectColumns(ctx, t, false);
        if(expr.getChildren(null).length == 1){
            ctx.addElement(KeywordLookupElement.create("exists"));
        }
    }

    @SyntaxTreePath("/#DELETE #FROM 2$TableAlias #WHERE_CONDITION/..$Condition/..3#VAR_REF/..4$NameFragmentRef/#C_MARKER")
    public void process$DeleteWhere2(C_Context ctx, ASTNode del, TableAlias t, ASTNode expr, NameFragmentRef nameRef) {
        collectColumns(ctx, t, false);
        if(expr.getChildren(null).length == 1 && expr.getTreeParent().getPsi() instanceof LogicalExpression){
            ctx.addElement(KeywordLookupElement.create("exists"));
        }
    }

    @SyntaxTreePath("/#DELETE #FROM 2$TableAlias #WHERE_CONDITION/..$Condition/..$Condition/..3#VAR_REF/..4$NameFragmentRef/#C_MARKER")
    public void process$DeleteWhere3(C_Context ctx, ASTNode del, TableAlias t, ASTNode expr, NameFragmentRef nameRef) {
        collectColumns(ctx, t, false);
        if(expr.getChildren(null).length == 1 && expr.getTreeParent().getPsi() instanceof LogicalExpression){
            ctx.addElement(KeywordLookupElement.create("exists"));
        }
    }

    @SyntaxTreePath("/#DELETE #FROM 2$TableAlias #WHERE_CONDITION//..#EXISTS_EXPR//..3$SelectStatement/..#WHERE_CONDITION//..#VAR_REF/..4$NameFragmentRef/#C_MARKER")
    public void process$DeleteWithSubquey(C_Context ctx, ASTNode del, TableAlias t, SelectStatement select, NameFragmentRef ref) {
        collectColumns(ctx, select, ref);
        VariantsProvider provider = ctx.getProvider();
        provider.collectColumnNames(t, ctx.getLookup(), false);

        // TODO - filter out columns already existing in column list
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    @SyntaxTreePath("//..#EXISTS_EXPR/#EXISTS #ERROR_TOKEN_A/#C_MARKER")
    public void process$ExistsExpr(C_Context ctx, ASTNode d) {
        ctx.addElement(SelectLookupElement.createSubquery());
    }

    @SyntaxTreePath("//.. 2$TableAlias #WHERE_CONDITION//..#RELATION_CONDITION 3#C_MARKER")
    public void process$RelConditionAppendix(C_Context ctx, ASTNode d, TableAlias tab, ASTNode marker) {
        if(is2ndLatest(d, marker)){
            completeStart(ctx);
        } else {
            ctx.addElement(KeywordLookupElement.create("exists"));
            ctx.addElement(KeywordLookupElement.create("or"));
            ctx.addElement(KeywordLookupElement.create("and"));
        }
    }
}
