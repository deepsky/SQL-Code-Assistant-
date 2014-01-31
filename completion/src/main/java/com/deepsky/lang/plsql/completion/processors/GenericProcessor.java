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
import com.deepsky.lang.plsql.psi.ColumnSpec;
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.TableAlias;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;

import java.util.ArrayList;
import java.util.List;

@SyntaxTreePath("/..#ERROR_TOKEN_A")
public class GenericProcessor {

    @SyntaxTreePath("/1#C_MARKER")
    public void process$Start(C_Context context, ASTNode node) {
        // TODO - implement me
    }


    @SyntaxTreePath("/..#SEMI #C_MARKER")
    public void process$Start3(C_Context context, ASTNode node) {
        // TODO - implement me
    }

    @SyntaxTreePath("/..#ALTER_TABLE #C_MARKER")
    public void process$Start4(C_Context context, ASTNode node) {
        // TODO - implement me
    }

    @SyntaxTreePath("/#DELETE #FROM 1#TABLE_ALIAS ..#WHERE_CONDITION #C_MARKER")
    public void process$Start5(C_Context context, ASTNode node) {
        // TODO - implement me
    }

    @SyntaxTreePath("/#CREATE #SEQUENCE 1#SEQUENCE_NAME .. #C_MARKER")
    public void process$Start6(C_Context context, ASTNode node) {
        // TODO - implement me
    }

    // INSERT related
    @SyntaxTreePath("/#INSERT #C_MARKER")
    public void process$InsertInto(C_Context ctx) {
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("into"));
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("values"));
    }

    // INSERT related
    @SyntaxTreePath("/#INSERT #INTO #TABLE_ALIAS/#TABLE_REF/#C_MARKER")
    public void process$InsertIntoTab(C_Context ctx) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }

    @SyntaxTreePath("/#INSERT #INTO 1$TableAlias #COLUMN_SPEC_LIST/..2$ColumnSpec/..#NAME_FRAGMENT/#C_MARKER")
    public void process$InsertColumnName(C_Context ctx, TableAlias t, ColumnSpec nameRef) {
        VariantsProvider provider = ctx.getProvider();
        provider.collectColumnNames(t, ctx.getLookup(), false);

        // TODO - filter out columns already existing in column list
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        for (LookupElement elem : variants) {
            ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(elem);
        }
    }

    @SyntaxTreePath("/#INSERT #INTO ..#COLUMN_SPEC_LIST #C_MARKER")
    public void process$InsertValues(C_Context ctx) {
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("select"));
        ctx.getResultSet().withPrefixMatcher(ctx.getLookup()).addElement(KeywordLookupElement.create("values"));
    }

    @SyntaxTreePath("/#SELECT #ASTERISK 1#C_MARKER")
    public void process$SelectAsterisk() {
        // TODO - implement me
    }


    @SyntaxTreePath("/#SELECT #C_MARKER")
    public void process$Select() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT #IDENTIFIER #C_MARKER")
    public void process$SelectIdent() {
        // TODO - implement me
    }

    // select (select 1-2 from dual) asd, (select <caret>) t
    @SyntaxTreePath("/#SELECT ..#EXPR_COLUMN #COMMA #ERROR_TOKEN_A/..#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER")
    public void process$SelectExpr() {
        // TODO - implement me
    }

    // select (select 1-2 from dual) asd, (select <caret>) t
    @SyntaxTreePath("/#SELECT ..#COMMA #EXPR_COLUMN/..#SUBQUERY_EXPR//#OPEN_PAREN 2$SelectStatement/..$FromClause/..#TABLE_ALIAS/..#TABLE_REF/#C_MARKER")
    public void process$SelectSubqueryExpr() {
        // TODO - implement me
    }


    @SyntaxTreePath("//..$FromClause ..#ERROR_TOKEN_A/#ORDER #C_MARKER")
    public void process$SelectOrder() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT .. 1#EXPR_COLUMN #COMMA #ERROR_TOKEN_A/2#C_MARKER")
    public void process$SelectColumnCommaMarker() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT .. 1#EXPR_COLUMN 2#C_MARKER")
    public void process$SelectColumnMarker() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT .. 1#EXPR_COLUMN #AS 2#C_MARKER")
    public void process$SelectColumnAsMarker() {
        // TODO - implement me
    }

    // select asd, t from a order by c1 <caret>
    // select pop from tab1 group by pop <caret>
    @SyntaxTreePath("/1$SelectStatement #C_MARKER")
    public void process$SelectAppender(C_Context ctx, SelectStatement select) {
        // TODO - implement me
    }

}

