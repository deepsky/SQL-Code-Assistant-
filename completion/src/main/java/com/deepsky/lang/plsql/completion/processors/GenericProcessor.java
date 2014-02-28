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
import com.deepsky.lang.plsql.completion.lookups.GroupByLookupElement;
import com.deepsky.lang.plsql.completion.lookups.KeywordLookupElement;
import com.deepsky.lang.plsql.completion.lookups.OrderByLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.SelectLookupElement;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;

import java.util.ArrayList;
import java.util.List;


public class GenericProcessor extends CompletionBase {

    @SyntaxTreePath("/..1ANY//..#EXISTS_EXPR/#EXISTS #ERROR_TOKEN_A/2#C_MARKER")
    public void process$anyExists(C_Context ctx, ASTNode parent, ASTNode marker) {
        ctx.addElement(SelectLookupElement.createSubquery(is2ndLatest(parent, marker)));
    }

    @SyntaxTreePath("/..1ANY//..#TABLE_REFERENCE_LIST_FROM/..#TABLE_ALIAS/#TABLE_REF/2#C_MARKER")
    public void process$TableViewNames(C_Context ctx, ASTNode parent, ASTNode marker) {
        collectTableViewNames(ctx);
        ctx.addElement(SelectLookupElement.createSubquery(is2ndLatest(parent, marker)));
    }

    @SyntaxTreePath("/..ANY//..#SUBQUERY/..#SELECT_EXPRESSION/..#TABLE_REFERENCE_LIST_FROM/..#TABLE_ALIAS/..#ALIAS_NAME//#C_MARKER")
    public void process$TableAlias(C_Context ctx) {
        ctx.addElement(KeywordLookupElement.create("where"));
    }

    @SyntaxTreePath("/..ANY//#SELECT ..#TABLE_REFERENCE_LIST_FROM ..#ERROR_TOKEN_A/#GROUP #C_MARKER")
    public void process$GroupBy(C_Context ctx) {
        ctx.addElement(GroupByLookupElement.createBy());
    }


    @SyntaxTreePath("/..ANY//#SELECT ..#TABLE_REFERENCE_LIST_FROM ..#ERROR_TOKEN_A/#ORDER #C_MARKER")
    public void process$OrderBy(C_Context ctx) {
        ctx.addElement(OrderByLookupElement.createBy());
    }


    @SyntaxTreePath("/..ANY//#SELECT ..1#GROUP_CLAUSE/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$GroupByVar(C_Context ctx, ASTNode groupClause, NameFragmentRef ref) {
        SelectStatement select = (SelectStatement) groupClause.getTreeParent().getPsi();
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = ref.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        if (prev == null && ctx.getLookup().length() > 0) {
            variants.addAll(provider.collectSystemFuncCall(ctx.getLookup()));
        }

        provider.collectColumnVariants(select, prevText);
        variants.addAll(provider.takeCollectedLookups());
        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }


    @SyntaxTreePath("/..ANY//#SELECT ..1#GROUP_CLAUSE/..#FUNCTION_CALL//..#CALL_ARGUMENT/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$GroupByFuncCall(C_Context ctx, ASTNode groupClause, NameFragmentRef ref) {
        SelectStatement select = (SelectStatement) groupClause.getTreeParent().getPsi();
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = ref.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        final List<LookupElement> variants = new ArrayList<LookupElement>();
//        if (prev == null && ctx.getLookup().length() > 0) {
        variants.addAll(provider.collectSystemFuncCall(ctx.getLookup()));
//        }

        provider.collectColumnVariants( select, prevText);
        variants.addAll(provider.takeCollectedLookups());
        for (LookupElement elem : variants) {
            ctx.addElement(elem);
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

    @SyntaxTreePath("/..ANY//#SELECT ..1#ORDER_CLAUSE/..#SORTED_DEF/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$SelectOrderBy2(C_Context ctx, ASTNode orderClause, NameFragmentRef nameRef) {
        _processSelectOrderBy2(ctx, orderClause, nameRef);
    }


    @SyntaxTreePath("/..ANY//#SELECT ..1#ORDER_CLAUSE/..#SORTED_DEF/..2$FunctionCall//..#CALL_ARGUMENT/..#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectOrderByFuncCall(C_Context ctx, ASTNode orderClause, FunctionCall call, NameFragmentRef nameRef) {
        _processSelectOrderBy2(ctx, orderClause, nameRef);

/*
TODO
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = ref.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        final List<LookupElement> variants = new ArrayList<LookupElement>();
//        if (prev == null && ctx.getLookup().length() > 0) {
        variants.addAll(provider.collectSystemFuncCall(ctx.getLookup()));
//        }

        provider.collectColumnVariants( select, prevText);
        variants.addAll(provider.takeCollectedLookups());
        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
*/

    }


    @SyntaxTreePath("/..ANY//#SELECT ..1#EXPR_COLUMN/#LEAD_FUNCTION/..#SPEC_CALL_ARGUMENT_LIST/..#ORDER_CLAUSE/..#SORTED_DEF/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$LeadFuncOrder(C_Context ctx, ASTNode expr, NameFragmentRef nameRef) {
        SelectStatement select = (SelectStatement) expr.getTreeParent().getPsi();
        collectColumns(ctx, select, nameRef);
    }


    @SyntaxTreePath("/..ANY//..#TABLE_REFERENCE_LIST_FROM/..#FROM_SUBQUERY/..#SUBQUERY/..1$SelectStatement/..#EXPR_COLUMN//..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$SelectFromSubquery4(C_Context ctx, SelectStatement select, NameFragmentRef ref) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = ref.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        provider.collectColumnVariants(select, prevText);

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        // Collect Sequence
        variants.addAll(provider.collectSequenceVariants(prevText, ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    @SyntaxTreePath("/..ANY//..1#TABLE_REFERENCE_LIST_FROM/..#FROM_SUBQUERY/..#SUBQUERY/..2$SelectStatement/..#WHERE_CONDITION//..#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$FromWhere(C_Context ctx, ASTNode from, SelectStatement select, NameFragmentRef ref) {
        collectColumns(ctx, select, ref);
        FromClause fromClause = (FromClause) from.getPsi();
        if(fromClause.getParent() instanceof SelectStatement){
            collectColumns(ctx, select, null);
        }
    }


    private void _processSelectOrderBy2(C_Context ctx, ASTNode orderClause, NameFragmentRef nameRef) {
        SelectStatement select = (SelectStatement) orderClause.getTreeParent().getPsi();
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        provider.collectColumnVariants(select, prevText);
        variants.addAll(provider.collectSystemFuncCall(ctx.getLookup()));
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
            ctx.addElement(elem);
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

}


