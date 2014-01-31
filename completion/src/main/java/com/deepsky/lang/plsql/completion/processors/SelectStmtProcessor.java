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
import com.deepsky.lang.plsql.completion.lookups.GenericLookupElement;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

import java.util.*;

@SyntaxTreePath("/..1$SelectStatement")
public class SelectStmtProcessor extends CompletionBase {

    // Set of aggregate functions
    private final static Set<String> AGGR_FUNC_NAMES = new HashSet<String>(
            Arrays.asList(new String[]{
                    // TODO -- add more AGGR functions
                    "MAX", "MIN", "COUNT", "SUM", "AVG", "COLLECT", "FIRST", "LAST", "GROUP_ID", "MEDIAN"})
    );



    @SyntaxTreePath("/ ..#TABLE_REFERENCE_LIST_FROM/ ..#TABLE_ALIAS/#TABLE_REF/#C_MARKER")
    public void process$SelectFromTab(C_Context ctx, SelectStatement select) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup()));
        variants.addAll(provider.collectViewNameVariants(ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }

    @SyntaxTreePath("/#SELECT ..2#EXPR_COLUMN/#PARENTHESIZED_EXPR/ ..#VAR_REF//#C_MARKER")
    public void process$SelectParenExpr() {
        // TODO - implement me
    }

    // select * from (select a, <caret>)
    @SyntaxTreePath("/..#TABLE_REFERENCE_LIST_FROM/..#FROM_SUBQUERY/ ..#ERROR_TOKEN_A/#SELECT ..#EXPR_COLUMN #COMMA #ERROR_TOKEN_A/#C_MARKER")
    public void process$SelectFromSubqueryError() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT ..2#EXPR_COLUMN/#VAR_REF/..3$NameFragmentRef/#C_MARKER")
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


    @SyntaxTreePath("/..1#EXPR_COLUMN/#SUBQUERY_EXPR//..2$SelectStatement/..#EXPR_COLUMN/#FUNCTION_CALL//..#CALL_ARGUMENT/#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectSubqueryExpr(C_Context ctx, SelectStatement select0, ASTNode expr, SelectStatement select, NameFragmentRef nameRef) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;
        provider.collectColumnVariants(select, prevText);

        PsiElement parent = expr.getTreeParent().getPsi();
        if(parent instanceof SelectStatement){
            provider.collectColumnVariants((SelectStatement) parent, prevText);
        }

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        // Collect Sequence
        variants.addAll(provider.collectSequenceVariants(prevText, ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }

    @SyntaxTreePath("//..#FROM_SUBQUERY//..#TABLE_REFERENCE_LIST_FROM/ ..#TABLE_ALIAS/#TABLE_REF/#C_MARKER")
    public void process$SelectFromTabAlias(C_Context ctx, SelectStatement select) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup()));
        variants.addAll(provider.collectViewNameVariants(ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
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

    @SyntaxTreePath("/..#TABLE_REFERENCE_LIST_FROM/..#TABLE_ALIAS/..#ALIAS_NAME/#ALIAS_IDENT/#C_MARKER")
    public void process$SelectTabAliasName() {
        // TODO - implement me
    }

    @SyntaxTreePath("/ ..#TABLE_REFERENCE_LIST_FROM/..#FROM_SUBQUERY/#SUBQUERY/#OPEN_PAREN #ERROR_TOKEN_A/2#C_MARKER")
    public void process$SelectFromSubquery2() {
        // TODO - implement me
    }


    @SyntaxTreePath("/ ..#EXPR_COLUMN/#SUBQUERY_EXPR// ..1$SelectStatement/ ..#TABLE_REFERENCE_LIST_FROM/ ..2#TABLE_ALIAS/#TABLE_REF/#C_MARKER")
    public void process$SelectFromSubquery3(C_Context ctx, SelectStatement select0, SelectStatement select, ASTNode tabAlias) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup()));
        variants.addAll(provider.collectViewNameVariants(ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }


    @SyntaxTreePath("/..1$FromClause//..#FROM_SUBQUERY/..#SUBQUERY/..2$SelectStatement/..#EXPR_COLUMN/#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectFromSubquery4(C_Context ctx, SelectStatement select0, FromClause from, SelectStatement select, NameFragmentRef ref) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = ref.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        provider.collectColumnVariants(select, prevText);

        final List<LookupElement> variants = new ArrayList<LookupElement>();

        // Collect columns in super query
        PsiElement parent = from.getParent();
        if(parent instanceof SelectStatement){
            SelectStatement parentSelect = (SelectStatement) parent;
            provider.collectColumnVariants(parentSelect, prevText);
        }

        variants.addAll(provider.takeCollectedLookups());

        // Collect Sequence
        variants.addAll(provider.collectSequenceVariants(prevText, ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }

    @SyntaxTreePath("/ ..2#TABLE_REFERENCE_LIST_FROM ..3#ERROR_TOKEN_A/#ORDER #C_MARKER")
    public void process$SelectOrderBy() {
        // TODO - implement me
    }


    /**
     * Is the specified expression an aggregate function
     *
     * @param expression expr to test
     * @return true if it is an aggregate like MIN, MAX, etc
     */
    private boolean isExprAggrFunction(Expression expression) {
        if(expression instanceof Callable){
            final String name = ((Callable)expression).getFunctionName();
            return AGGR_FUNC_NAMES.contains(name.toUpperCase());
        }
        return false;
    }

    private void addIfMatch(String reference, List<LookupElement> source, List<LookupElement> out) {
        for (LookupElement e : source) {
            if (e.getLookupString().equalsIgnoreCase(reference.replaceAll(" ", ""))) {
                out.add(e);
                break;
            }
        }
    }

    /**
     * Trivial compare expressions
     *
     * @param expr
     * @param expressions
     * @return
     */
    private boolean checkExpr(Expression expr, Expression[] expressions) {
        final String exprTest = expr.getText().replaceAll("[\n\t ]", "");
        for(Expression e: expressions){
            return exprTest.equalsIgnoreCase(e.getText().replaceAll("[\n\t ]", ""));
        }

        return false;
    }


    @SyntaxTreePath("/..#ORDER_CLAUSE/..#SORTED_DEF/#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$SelectOrderBy2(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        if (prev == null && ctx.getLookup().length() > 0) {
            variants.addAll(provider.collectSystemFuncCall(ctx.getLookup()));
        }

        provider.collectColumnVariants(select, prevText);
        variants.addAll(provider.takeCollectedLookups());

        final GroupByClause groupBy = select.getGroupByClause();
        if (groupBy != null) {
            List<LookupElement> out1 = new ArrayList<LookupElement>();
            // Filter out all names that don't match names in groupBy clause
            for (Expression expr : groupBy.getExprList()) {
                if (expr instanceof ObjectReference) {
                    addIfMatch(expr.getText(), variants, out1);
                }
            }

            variants.clear();
            variants.addAll(out1);
        }

        // Search for aggregate functions among select fields, if they have alias, use them
        for (SelectFieldCommon f : select.getSelectFieldList()) {
            if (f instanceof SelectFieldExpr) {
                final String alias = ((SelectFieldExpr) f).getAlias();
                if (alias != null) {
                    try {
                        final Expression expr = ((SelectFieldExpr) f).getExpression();
                        Type t = expr.getExpressionType();
                        if (isExprAggrFunction(expr)) {
                            // Case: count(*) cnt
                            variants.add(GenericLookupElement.create(alias, t.typeName(), null, null));
                        } else {
                            if (groupBy != null) {
                                // Case: to_char(date1, 'YYYY') if GROUP_BY has the same
                                if (checkExpr(expr, groupBy.getExprList())) {
                                    variants.add(GenericLookupElement.create(alias, t.typeName(), null, null));
                                }
                            } else {
                                // Add just alias name of select field expr
                                variants.add(GenericLookupElement.create(alias, t.typeName(), null, null));
                            }
                        }
                    } catch (ValidationException e) {
                        // Ignore single validation error
                    }
                }
            }
        }

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }


        // select field expression
//        if (prev == null && lookUpStr.length() > 0) {
//            __collect__(COLLECT_SYSTEM_FUNC_CALLS);
//        }
//        __collect__(COLLECT_COLUMN);
//
//        final PsiElement parent = l.getParent();
//        if (parent instanceof SelectStatement) {
//            final GroupByClause groupBy = ((SelectStatement) parent).getGroupByClause();
//            if (groupBy != null) {
//                List<LookupElement> out1 = new ArrayList<LookupElement>();
//                // Filter out all names that don't match names in groupBy clause
//                for (Expression expr : groupBy.getExprList()) {
//                    if (expr instanceof ObjectReference) {
//                        addIfMatch(expr.getText(), out, out1);
//                    }
//                }
//
//                out.clear();
//                out.addAll(out1);
//            } else {
//                __collect__(COLLECT_SEQUENCE_REF);
//            }
//        }

    }

    @SyntaxTreePath("/..#GROUP_CLAUSE/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$SelectGroupBy(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        if (prev == null && ctx.getLookup().length() > 0) {
            variants.addAll(provider.collectSystemFuncCall(ctx.getLookup()));
        }

        provider.collectColumnVariants(select, prevText);
        variants.addAll(provider.takeCollectedLookups());
        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }


    @SyntaxTreePath("/..#GROUP_CLAUSE/..#FUNCTION_CALL//..#CALL_ARGUMENT/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$SelectGroupByFunc(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        if (prev == null && ctx.getLookup().length() > 0) {
            variants.addAll(provider.collectSystemFuncCall(ctx.getLookup()));
        }

        provider.collectColumnVariants(select, prevText);
        variants.addAll(provider.takeCollectedLookups());
        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }

//        if (isPLSQLContext(l) && !isSqlContext(l)) {
//            if (prev == null) {
//                // 1. might be a package name
//                __collect__(COLLECT_PACKAGE_VARS);
//                // 2. system functions
//                __collect__(COLLECT_SYSTEM_FUNC_CALLS);
//                // 3. variables
//                __collect__(COLLECT_VAR_VARS);
//            } else {
//                // 1. variables in the package scope
//                __collect__(COLLECT_PACKAGE_VAR_VARS);
//                // 2. user defined functions
//                __collect__(COLLECT_USER_FUNC_CALLS);
//            }
//        }
//        if (isSqlContext(l)) {
//            // select field expression
//            __collect__(COLLECT_COLUMN);
//        }

    }

    @SyntaxTreePath("/..1#TABLE_REFERENCE_LIST_FROM ..#ERROR_TOKEN_A/#GROUP #C_MARKER")
    public void process$SelectGroupBy2(C_Context ctx, SelectStatement select) {
        // TODO - implement me
    }

    @SyntaxTreePath("/..2#WHERE_CONDITION ..3#ERROR_TOKEN_A/#GROUP #C_MARKER")
    public void process$SelectWhere() {
        // TODO - implement me
    }

    @SyntaxTreePath("/..#WHERE_CONDITION//..2#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectWhere1(C_Context ctx, SelectStatement select, ASTNode expr, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

    @SyntaxTreePath("/..#WHERE_CONDITION//..#RELATION_CONDITION/..2#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectWhere2(C_Context ctx, SelectStatement select, ASTNode expr, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

    @SyntaxTreePath("/..#WHERE_CONDITION//..#LIKE_CONDITION/..2#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectWhere3(C_Context ctx, SelectStatement select, ASTNode expr, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);

    }


    @SyntaxTreePath("/..#WHERE_CONDITION/..#EXISTS_EXPR//..$SelectStatement/#SELECT ..#TABLE_REFERENCE_LIST_FROM/..#TABLE_ALIAS/..#TABLE_REF/#C_MARKER")
    public void process$SelectExistsTab(C_Context ctx, SelectStatement select) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup()));
        variants.addAll(provider.collectViewNameVariants(ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }

    @SyntaxTreePath("/..#WHERE_CONDITION/..#EXISTS_EXPR//..2$SelectStatement/..#WHERE_CONDITION/..#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectExistsExpr(C_Context ctx, SelectStatement select, SelectStatement subquery, NameFragmentRef nameRef) {
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

/*
    @SyntaxTreePath("/..#WHERE_CONDITION/..#EXISTS_EXPR/..#SUBQUERY/..2$SelectStatement/..#WHERE_CONDITION// ..#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectWhereSubquery(C_Context ctx, SelectStatement select, SelectStatement subquery, NameFragmentRef nameRef) {
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
*/

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

    @SyntaxTreePath("/..#EXPR_COLUMN/#LEAD_FUNCTION/..#SPEC_CALL_ARGUMENT_LIST/..#ORDER_CLAUSE/..#SORTED_DEF/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$LeadFuncOrder(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }


    // CASE WHEN EXPR
    @SyntaxTreePath("/ ..#EXPR_COLUMN/#CASE_EXPRESSION_SRCH/..#RELATION_CONDITION/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$CaseSearchExpr(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

    @SyntaxTreePath("/ ..#EXPR_COLUMN/#CASE_EXPRESSION_SRCH/..#RELATION_CONDITION//..#FUNCTION_CALL/..#CALL_ARGUMENT_LIST/..#CALL_ARGUMENT/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$CaseSearchExprFuncCall(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

    // ANSI FROM STATEMENT
    @SyntaxTreePath("/ ..#TABLE_REFERENCE_LIST_FROM/..#ANSI_JOIN_TAB_SPEC/..#ANSI_JOIN_TAB_CONDITION//..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$AnsiFrom(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

}
