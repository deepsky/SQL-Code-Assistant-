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
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class SelectStmtProcessor {


    @SyntaxTreePath("/#ERROR_TOKEN_A/#SELECT #ASTERISK 1#C_MARKER")
    public void process$SelectAsterisk() {
        // TODO - implement me
    }


    @SyntaxTreePath("/#ERROR_TOKEN_A/#SELECT #C_MARKER")
    public void process$Select() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#ERROR_TOKEN_A/#SELECT #IDENTIFIER #C_MARKER")
    public void process$SelectIdent() {
        // TODO - implement me
    }

    @SyntaxTreePath("// ..#ERROR_TOKEN_A/#SELECT ..#EXPR_COLUMN #COMMA #ERROR_TOKEN_A/#C_MARKER")
    public void process$SelectExpr() {
        // TODO - implement me
    }

    @SyntaxTreePath("//#SELECT .. 1#EXPR_COLUMN 2#C_MARKER")
    public void process$SelectColumnMarker() {
        // TODO - implement me
    }

    @SyntaxTreePath("//#SELECT .. 1#EXPR_COLUMN #AS 2#C_MARKER")
    public void process$SelectColumnAsMarker() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..SelectStatement/ ..#TABLE_REFERENCE_LIST_FROM/ ..1#TABLE_ALIAS/#TABLE_REF/#C_MARKER")
    public void process$SelectFromTab(C_Context ctx, ASTNode tabAlias) {

        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup()));
        variants.addAll(provider.collectViewNameVariants(ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }

    @SyntaxTreePath("//..1$SelectStatement/#SELECT ..2#EXPR_COLUMN/#PARENTHESIZED_EXPR/ ..#VAR_REF//#C_MARKER")
    public void process$SelectParenExpr() {
        // TODO - implement me
    }


    @SyntaxTreePath("/..1$SelectStatement/#SELECT ..2#EXPR_COLUMN/#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectVarRef(C_Context ctx, SelectStatement select, ASTNode expr, NameFragmentRef nameRef) {

        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        provider.collectColumnVariants(select, prevText);

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        // Collect Sequence
        variants.addAll(provider.collectSequenceVariants(prevText, ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }

    @SyntaxTreePath("//..1$SelectStatement/ ..#TABLE_REFERENCE_LIST_FROM/ ..#TABLE_ALIAS/2#TABLE_REF #ALIAS_NAME//3#C_MARKER")
    public void process$SelectFromTabAlias() {
        // TODO - implement me
    }

    @SyntaxTreePath("//SelectStatement/#SELECT ..1#ERROR_TOKEN_A/#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER")
    public void process$SelectSubquery() {
        // TODO - implement me
    }

    @SyntaxTreePath("//#SELECT ..1#ERROR_TOKEN_A/#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER")
    public void process$SelectSubquery2() {
        // TODO - implement me
    }

    @SyntaxTreePath("//SelectStatement/#SELECT ..1#ERROR_TOKEN_A/#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER")
    public void process$SelectFromSubquery() {
        // TODO - implement me
    }

    @SyntaxTreePath("//SelectStatement/ ..#EXPR_COLUMN/ ..#ALIAS_NAME/#ALIAS_IDENT/1#C_MARKER")
    public void process$SelectColumnAlias() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..SelectStatement/ ..#TABLE_REFERENCE_LIST_FROM/..#FROM_SUBQUERY/#SUBQUERY/#OPEN_PAREN #ERROR_TOKEN_A/1#C_MARKER")
    public void process$SelectFromSubquery2() {
        // TODO - implement me
    }


    @SyntaxTreePath("// ..#EXPR_COLUMN//#SUBQUERY / ..1$SelectStatement/ ..#TABLE_REFERENCE_LIST_FROM/ ..2#TABLE_ALIAS/#TABLE_REF/#C_MARKER")
    public void process$SelectFromSubquery3(C_Context ctx, SelectStatement select, ASTNode tabAlias) {
        // TODO - implement me
    }

    @SyntaxTreePath("//..SelectStatement/ ..1#TABLE_REFERENCE_LIST_FROM ..2#ERROR_TOKEN_A/#ORDER #C_MARKER")
    public void process$SelectOrderBy() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..1$SelectStatement/..#TABLE_REFERENCE_LIST_FROM ..2#ORDER_CLAUSE/..#SORTED_DEF/#VAR_REF//#C_MARKER")
    public void process$SelectOrderBy2(C_Context ctx, SelectStatement select, ASTNode orderBy) {
        // TODO - implement me
    }

    @SyntaxTreePath("//..1$SelectStatement/..#TABLE_REFERENCE_LIST_FROM ..2#GROUP_CLAUSE/..#VAR_REF//#C_MARKER")
    public void process$SelectGroupBy(C_Context ctx, SelectStatement select, ASTNode groupBy) {
        // TODO - implement me
    }

    @SyntaxTreePath("//..1$SelectStatement/..1#TABLE_REFERENCE_LIST_FROM ..#ERROR_TOKEN_A/#GROUP #C_MARKER")
    public void process$SelectGroupBy2(C_Context ctx, SelectStatement select) {
        // TODO - implement me
    }

    @SyntaxTreePath("//..SelectStatement/..1#WHERE_CONDITION ..2#ERROR_TOKEN_A/#GROUP #C_MARKER")
    public void process$SelectWhere() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..1$SelectStatement/..#WHERE_CONDITION//..2#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectWhere1(C_Context ctx, SelectStatement select, ASTNode expr, NameFragmentRef nameRef) {
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

    @SyntaxTreePath("//..1$SelectStatement/..#WHERE_CONDITION//..#RELATION_CONDITION/..2#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectWhere2(C_Context ctx, SelectStatement select, ASTNode expr, NameFragmentRef nameRef) {
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

    @SyntaxTreePath("//..1$SelectStatement/..#WHERE_CONDITION//..#LIKE_CONDITION/..2#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectWhere3(C_Context ctx, SelectStatement select, ASTNode expr, NameFragmentRef nameRef) {
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

    @SyntaxTreePath("//..1$SelectStatement/ ..2#TABLE_REFERENCE_LIST_FROM #WHERE_CONDITION/..#EXISTS_EXPR/..#SUBQUERY/..3$SelectStatement/#SELECT ..#EXPR_COLUMN/#VAR_REF/..4$NameFragmentRef/#C_MARKER")
    public void process$SelectExistsExpr(C_Context ctx, SelectStatement select, ASTNode expr, SelectStatement subquery, NameFragmentRef nameRef) {
        // TODO - implement me
        int h =1;
    }

    @SyntaxTreePath("//..1$SelectStatement/ ..2#TABLE_REFERENCE_LIST_FROM #WHERE_CONDITION/..#EXISTS_EXPR/..#SUBQUERY/..3$SelectStatement/#SELECT ..#WHERE_CONDITION// ..#VAR_REF/..4$NameFragmentRef/#C_MARKER")
    public void process$SelectWhereSubquery(C_Context ctx, SelectStatement select, ASTNode expr, SelectStatement subquery, NameFragmentRef nameRef) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        provider.collectColumnVariants(select, prevText);
        provider.collectColumnVariants(subquery, prevText);

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }

//    @SyntaxTreePath("//..1$SelectStatement/ ..2#TABLE_REFERENCE_LIST_FROM #WHERE_CONDITION/..#EXISTS_EXPR/..#SUBQUERY/..3$SelectStatement/#SELECT ..#WHERE_CONDITION// ..#VAR_REF/..4$NameFragmentRef/#C_MARKER")
//    public void process$SelectWhereSubquery2() {
//        // TODO - implement me
//    }

    @SyntaxTreePath("//1$SelectStatement 2#C_MARKER")
    public void process$SelectAppender(C_Context ctx, SelectStatement select) {
        // TODO - implement me
    }

    @SyntaxTreePath("//..1$SelectStatement/..#EXPR_COLUMN/#LAG_FUNCTION/..#SPEC_CALL_ARGUMENT_LIST/..#QUERY_PARTITION_CLAUSE/..2#VAR_REF//#C_MARKER")
    public void process$LagFunc(C_Context ctx, SelectStatement select, ASTNode var) {
        // TODO - implement me
        int kk=0;
    }

    @SyntaxTreePath("//..1$SelectStatement/..#EXPR_COLUMN/#LAG_FUNCTION/..#SPEC_CALL_ARGUMENT_LIST/..#CALL_ARGUMENT/2#VAR_REF//#C_MARKER")
    public void process$LagFuncArg(C_Context ctx, SelectStatement select, ASTNode var) {
        // TODO - implement me
        int kk=0;
    }

    @SyntaxTreePath("//..1$SelectStatement/..#EXPR_COLUMN/#LEAD_FUNCTION/..#SPEC_CALL_ARGUMENT_LIST/..#QUERY_PARTITION_CLAUSE/..2#VAR_REF//#C_MARKER")
    public void process$LeadFunc(C_Context ctx, SelectStatement select, ASTNode var) {
        // TODO - implement me
        int kk=0;
    }

    @SyntaxTreePath("//..1$SelectStatement/..#EXPR_COLUMN/#LEAD_FUNCTION/..#SPEC_CALL_ARGUMENT_LIST/..#ORDER_CLAUSE/..#SORTED_DEF/2#VAR_REF//#C_MARKER")
    public void process$LeadFuncOrder(C_Context ctx, SelectStatement select, ASTNode var) {
        // TODO - implement me
        int kk=0;
    }
}
