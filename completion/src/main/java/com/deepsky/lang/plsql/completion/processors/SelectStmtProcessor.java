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

import com.deepsky.lang.PsiUtil;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.completion.SyntaxTreePath;
import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.deepsky.lang.plsql.completion.lookups.CaseExpressionLookupElement;
import com.deepsky.lang.plsql.completion.lookups.KeywordLookupElement;
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

import java.util.ArrayList;
import java.util.List;


@SyntaxTreePath("//..1$SelectStatement")
public class SelectStmtProcessor extends CompletionBase {


    @SyntaxTreePath("/#SELECT ..2#EXPR_COLUMN/#PARENTHESIZED_EXPR/ ..#VAR_REF//#C_MARKER")
    public void process$SelectParenExpr() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT ..#ERROR_TOKEN_A/#ERROR_TOKEN_A/#CASE #VAR_REF/#NAME_FRAGMENT/#C_MARKER")
    public void process$SelectCaseExpr(C_Context ctx, SelectStatement select) {
        ctx.addElement(CaseExpressionLookupElement.createCaseWhen());
    }

    // select * from (select a from tab1) <caret>
    @SyntaxTreePath("/..#TABLE_REFERENCE_LIST_FROM/..#FROM_SUBQUERY/#SUBQUERY #ALIAS_NAME/#ALIAS_IDENT/2#C_MARKER")
    public void process$SelectFromSubqueryAlias(C_Context ctx, SelectStatement select, ASTNode marker) {
        ASTNode next = PsiUtil.nextVisibleSibling(marker);
        final boolean isSemiLast = next != null && next.getElementType() == PlSqlTokenTypes.SEMI;
        if (!isSemiLast) {
            completeStart(ctx);
        }
    }

    // select * from (select a, <caret>)
    @SyntaxTreePath("/..#TABLE_REFERENCE_LIST_FROM/..#FROM_SUBQUERY// ..#ERROR_TOKEN_A/#SELECT ..#EXPR_COLUMN #COMMA #ERROR_TOKEN_A/#C_MARKER")
    public void process$SelectFromSubqueryError(C_Context ctx) {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT ..#EXPR_COLUMN/2#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectVarRef(C_Context ctx, SelectStatement select, ASTNode varRef, NameFragmentRef nameRef) {
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        collectColumns(ctx, select, nameRef);
        // Collect Sequence
        VariantsProvider provider = ctx.getProvider();
        for (LookupElement elem : provider.collectSequenceVariants(prevText, ctx.getLookup())) {
            ctx.addElement(elem);
        }

        if (varRef.getChildren(null).length == 1) {
            ctx.addElement(CaseExpressionLookupElement.createCase());
        }

        collectPlSqlVariables(ctx, select, nameRef);
    }


    @SyntaxTreePath("/#SELECT ..#EXPR_COLUMN/#CASE_EXPRESSION_SRCH/#CASE #WHEN 2#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectCaseCondition(C_Context ctx, SelectStatement select, ASTNode varRef, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);

        if (varRef.getChildren(null).length == 1) {
            collectSystemFunctions(ctx);
            ctx.addElement(KeywordLookupElement.create("systimestamp"));
            ctx.addElement(KeywordLookupElement.create("sysdate"));
            ctx.addElement(KeywordLookupElement.create("sessiontimezone"));
            ctx.addElement(KeywordLookupElement.create("dbtimezone"));
        }
    }

    @SyntaxTreePath("/..1#EXPR_COLUMN/#SUBQUERY_EXPR//..2$SelectStatement/..#EXPR_COLUMN/#FUNCTION_CALL//..#CALL_ARGUMENT/..#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectSubqueryExpr(C_Context ctx, SelectStatement select0, ASTNode expr, SelectStatement select, NameFragmentRef nameRef) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;
        provider.collectColumnVariants(select, prevText);

        PsiElement parent = expr.getTreeParent().getPsi();
        if (parent instanceof SelectStatement) {
            provider.collectColumnVariants((SelectStatement) parent, prevText);
        }

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        // Collect Sequence
        variants.addAll(provider.collectSequenceVariants(prevText, ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    @SyntaxTreePath("/#SELECT ..2#ERROR_TOKEN_A/#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER")
    public void process$SelectSubquery() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT ..2#ERROR_TOKEN_A/#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER")
    public void process$SelectSubquery2() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT ..2#ERROR_TOKEN_A/#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER")
    public void process$SelectFromSubquery() {
        // TODO - implement me
    }

    @SyntaxTreePath("/ ..#EXPR_COLUMN/ ..#ALIAS_NAME/#ALIAS_IDENT/2#C_MARKER")
    public void process$SelectColumnAlias() {
        // TODO - implement me
    }

    @SyntaxTreePath("/ ..#TABLE_REFERENCE_LIST_FROM/..#FROM_SUBQUERY/#SUBQUERY/#OPEN_PAREN #ERROR_TOKEN_A/2#C_MARKER")
    public void process$SelectFromSubquery2() {
        // TODO - implement me
    }

    @SyntaxTreePath("/..#EXPR_COLUMN/#LAG_FUNCTION/..#SPEC_CALL_ARGUMENT_LIST/..#QUERY_PARTITION_CLAUSE/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$LagFunc(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

    @SyntaxTreePath("/..#EXPR_COLUMN/#LAG_FUNCTION/..#SPEC_CALL_ARGUMENT_LIST/..#CALL_ARGUMENT/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$LagFuncArg(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

    @SyntaxTreePath("/..#EXPR_COLUMN/#LEAD_FUNCTION/..#SPEC_CALL_ARGUMENT_LIST/..#QUERY_PARTITION_CLAUSE/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$LeadFunc(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

    // ANSI FROM STATEMENT
    @SyntaxTreePath("/ ..#TABLE_REFERENCE_LIST_FROM/..#ANSI_JOIN_TAB_SPEC/..#ANSI_JOIN_TAB_CONDITION//..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$AnsiFrom(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

}
