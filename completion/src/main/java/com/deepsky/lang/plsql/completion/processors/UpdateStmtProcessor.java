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
import com.deepsky.lang.plsql.completion.lookups.dml.UpdateLookupElement;
import com.deepsky.lang.plsql.psi.ColumnSpecList;
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.TableAlias;
import com.intellij.codeInsight.lookup.LookupElement;

import java.util.ArrayList;
import java.util.List;

@SyntaxTreePath("//..#UPDATE_COMMAND")
public class UpdateStmtProcessor extends CompletionBase {

    @SyntaxTreePath("/#ERROR_TOKEN_A/#UPDATE #TABLE_ALIAS/#TABLE_REF #ALIAS_NAME//#C_MARKER")
    public void process$UpdateTabAlias(C_Context ctx) {
        ctx.addElement(KeywordLookupElement.create("set"));
    }

    @SyntaxTreePath("/#SIMPLE_UPDATE_COMMAND/#UPDATE 1$TableAlias #SET #ERROR_TOKEN_A/#COLUMN_SPEC ..#COMMA #C_MARKER")
    public void process$UpdateTabRef(C_Context ctx, TableAlias t) {
        VariantsProvider provider = ctx.getProvider();
        provider.collectColumnNames(t, ctx.getLookup(), false);

        // TODO - filter out columns already existing in column list
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }


    @SyntaxTreePath("/#SIMPLE_UPDATE_COMMAND/#UPDATE 1$TableAlias #SET #COLUMN_SPEC/#NAME_FRAGMENT/#C_MARKER")
    public void process$UpdateColumn(C_Context ctx, TableAlias t) {
        VariantsProvider provider = ctx.getProvider();
        provider.collectColumnNames(t, ctx.getLookup(), false);

        // TODO - filter out columns already existing in column list
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    @SyntaxTreePath("/#ERROR_TOKEN_A/#UPDATE #TABLE_ALIAS/#TABLE_REF/#C_MARKER")
    public void process$UpdateColumnName(C_Context ctx) {
        collectTableNames(ctx);
    }

    @SyntaxTreePath("/#SIMPLE_UPDATE_COMMAND/#UPDATE 1$TableAlias/#TABLE_REF/#C_MARKER")
    public void process$UpdateTable(C_Context ctx, TableAlias t) {
        collectTableNames(ctx);
    }

    @SyntaxTreePath("/#SIMPLE_UPDATE_COMMAND/#UPDATE 1$TableAlias #SET #ERROR_TOKEN_A/#C_MARKER")
    public void process$UpdateColumnVar(C_Context ctx, TableAlias t) {
        collectColumns(ctx, t, false);
        ctx.addElement(UpdateLookupElement.createSubqueryParen(t.getTableName(), null));
    }

    @SyntaxTreePath("/#SIMPLE_UPDATE_COMMAND/#UPDATE 1$TableAlias #SET ..#COLUMN_SPEC #EQ #VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$UpdateColumnVar2(C_Context ctx, TableAlias t, NameFragmentRef nameRef) {
        collectColumns(ctx, t, false);
        if (nameRef.getPrevFragment() == null) {
            ctx.addElement(KeywordLookupElement.create("sysdate"));
            ctx.addElement(KeywordLookupElement.create("systimestamp"));
            ctx.addElement(KeywordLookupElement.create("dbtimezone"));
            ctx.addElement(KeywordLookupElement.create("current_timestamp"));
        }

    }

    @SyntaxTreePath("/#SIMPLE_UPDATE_COMMAND/#UPDATE 1$TableAlias #SET ..#COLUMN_SPEC #EQ #ARITHMETIC_EXPR//..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$UpdateColumnVar3(C_Context ctx, TableAlias t, NameFragmentRef nameRef) {
        collectColumns(ctx, t, false);
        if (nameRef.getPrevFragment() == null) {
            ctx.addElement(KeywordLookupElement.create("sysdate"));
            ctx.addElement(KeywordLookupElement.create("systimestamp"));
            ctx.addElement(KeywordLookupElement.create("dbtimezone"));
            ctx.addElement(KeywordLookupElement.create("current_timestamp"));
        }

    }

    @SyntaxTreePath("/#SIMPLE_UPDATE_COMMAND/#UPDATE 1$TableAlias #SET ..#WHERE_CONDITION/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$UpdateWhereVar(C_Context ctx, TableAlias t, NameFragmentRef nameRef) {
        collectColumns(ctx, t, false);
        ctx.addElement(KeywordLookupElement.create("exists"));
    }


//    @SyntaxTreePath("//#UPDATE 1$TableAlias #SET ..#COLUMN_SPEC #EQ #ARITHMETIC_EXPR//..$SelectStatement/..#TABLE_REFERENCE_LIST_FROM/..#TABLE_ALIAS/#TABLE_REF/#C_MARKER")
//    public void process$UpdateAssignment1(C_Context ctx, TableAlias t) {
//        collectTableNames(ctx);
//    }

    @SyntaxTreePath("//#UPDATE 1$TableAlias #SET ..#COLUMN_SPEC #EQ #ARITHMETIC_EXPR//..2$SelectStatement/..#WHERE_CONDITION/..#RELATION_CONDITION/..#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$UpdateAssignment2(C_Context ctx, TableAlias t, SelectStatement select, NameFragmentRef ref) {
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


    @SyntaxTreePath("//#UPDATE 1$TableAlias #SET ..#COLUMN_SPEC #EQ #SUBQUERY_EXPR//..2$SelectStatement/..#EXPR_COLUMN/..#FUNCTION_CALL/..#CALL_ARGUMENT_LIST/..#CALL_ARGUMENT/..#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$UpdateAssignment3(C_Context ctx, TableAlias t, SelectStatement select, NameFragmentRef ref) {
        collectColumns(ctx, select, ref);
        collectColumns(ctx, t, false);
    }

    @SyntaxTreePath("//#UPDATE 1$TableAlias #SET #ERROR_TOKEN_A/2$ColumnSpecList #C_MARKER")
    public void process$UpdateSubquery(C_Context ctx, TableAlias t, ColumnSpecList list) {
        ctx.addElement(UpdateLookupElement.createSubqueryEq(t.getTableName(), list.getColumns()));
    }

    @SyntaxTreePath("/#SUBQUERY_UPDATE_COMMAND/#UPDATE 1$TableAlias #SET #ERROR_TOKEN_A/2$ColumnSpecList/..#COLUMN_SPEC/#NAME_FRAGMENT/#C_MARKER")
    public void process$SubqueryUpdate2(C_Context ctx, TableAlias t, ColumnSpecList list) {
        collectColumns(ctx, t, false);
        // TODO do column filtering
    }

    @SyntaxTreePath("/#SUBQUERY_UPDATE_COMMAND/#UPDATE 1$TableAlias #SET 2$ColumnSpecList/..#COLUMN_SPEC/#NAME_FRAGMENT/#C_MARKER")
    public void process$SubqueryUpdate3(C_Context ctx, TableAlias t, ColumnSpecList list) {
        collectColumns(ctx, t, false);
        // TODO do column filtering
    }

    @SyntaxTreePath("/#SUBQUERY_UPDATE_COMMAND/#UPDATE 1$TableAlias #SET 2$ColumnSpecList #EQ #SUBQUERY/#OPEN_PAREN #ERROR_TOKEN_A/#C_MARKER")
    public void process$SubqueryUpdate4(C_Context ctx, TableAlias t, ColumnSpecList list) {
        ctx.addElement(SelectLookupElement.create());
    }

    @SyntaxTreePath("/#SUBQUERY_UPDATE_COMMAND/#UPDATE 1$TableAlias #SET 2$ColumnSpecList #EQ #ERROR_TOKEN_A/#C_MARKER")
    public void process$SubqueryUpdate5(C_Context ctx, TableAlias t, ColumnSpecList list) {
        ctx.addElement(SelectLookupElement.createSubquery(true));
    }

    @SyntaxTreePath("/#SUBQUERY_UPDATE_COMMAND/#UPDATE $TableAlias #SET $ColumnSpecList #EQ #SUBQUERY/..1$SelectStatement/#SELECT ..#EXPR_COLUMN/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$SubqueryUpdate6(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }
}
