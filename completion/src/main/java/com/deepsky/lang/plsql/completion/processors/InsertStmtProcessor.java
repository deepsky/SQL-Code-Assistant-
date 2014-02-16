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
import com.deepsky.lang.plsql.completion.lookups.GroupByLookupElement;
import com.deepsky.lang.plsql.completion.lookups.KeywordLookupElement;
import com.deepsky.lang.plsql.completion.lookups.OrderByLookupElement;
import com.deepsky.lang.plsql.psi.ColumnSpec;
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.TableAlias;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;

import java.util.ArrayList;
import java.util.List;

@SyntaxTreePath("/..1#INSERT_COMMAND")
public class InsertStmtProcessor extends CompletionBase {

    @SyntaxTreePath("/#INSERT #INTO 1$TableAlias #COLUMN_SPEC_LIST/..2$ColumnSpec/..#NAME_FRAGMENT/#C_MARKER")
    public void insertColumnName(C_Context ctx, ASTNode root, TableAlias t, ColumnSpec nameRef) {
        collectColumns(ctx, t, false);
    }

    @SyntaxTreePath("/..#INTO 1$TableAlias #COLUMN_SPEC_LIST/..2$ColumnSpec/..#NAME_FRAGMENT/#C_MARKER")
    public void insertInto(C_Context ctx, ASTNode root, TableAlias t, ColumnSpec nameRef) {
        collectColumns(ctx, t, false);
    }

//    @SyntaxTreePath("/..#INTO #TABLE_ALIAS ..$SelectStatement/..#TABLE_REFERENCE_LIST_FROM/..#TABLE_ALIAS/#TABLE_REF/#C_MARKER")
//    public void insertIntoFromSelect(C_Context ctx, ASTNode root) {
//        collectTableViewNames(ctx);
//    }

    @SyntaxTreePath("/..#INTO #TABLE_ALIAS ..2$SelectStatement/..#TABLE_REFERENCE_LIST_FROM/..#TABLE_ALIAS/..#ALIAS_NAME//3#C_MARKER")
    public void insertIntoFromSelect2(C_Context ctx, ASTNode root, SelectStatement select, ASTNode marker) {
        if(is2ndLatest(root, marker)){
            // Marker is the last element of the statement
            if(select.getWhereCondition() == null)
                ctx.addElement(KeywordLookupElement.create("where"));
            if (select.getGroupByClause() == null)
                ctx.addElement(GroupByLookupElement.create());
            if (select.getOrderByClause() == null)
                ctx.addElement(OrderByLookupElement.create());

            completeStart(ctx);
        } else {
            // Marker is in the middle of the statement
            // Check whether WHERE clause exists
            if(select.getWhereCondition() == null)
                ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("where"));
            if (select.getGroupByClause() == null)
                ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(GroupByLookupElement.create());
            if (select.getOrderByClause() == null)
                ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(OrderByLookupElement.create());
        }
    }

    @SyntaxTreePath("/..#INTO #TABLE_ALIAS ..2$SelectStatement/..#WHERE_CONDITION//..#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void insertIntoFromSelectVarRef(C_Context ctx, ASTNode root, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

    @SyntaxTreePath("/..#INTO #TABLE_ALIAS #COLUMN_SPEC_LIST 2$SelectStatement/#SELECT ..#EXPR_COLUMN//..#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void insertIntoFromSelectExpr(C_Context ctx, ASTNode root, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }
}
