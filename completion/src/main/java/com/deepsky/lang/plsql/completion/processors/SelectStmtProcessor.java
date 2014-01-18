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
    public void process$SelectFromTab() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..1$SelectStatement/#SELECT ..2#EXPR_COLUMN/#PARENTHESIZED_EXPR/ ..#VAR_REF//#C_MARKER")
    public void process$SelectParenExpr() {
        // TODO - implement me
    }


    @SyntaxTreePath("/..1$SelectStatement/#SELECT ..2#EXPR_COLUMN/#VAR_REF/#NAME_FRAGMENT/#C_MARKER")
    public void process$SelectVarRef() {
        // TODO - implement me
    }

    @SyntaxTreePath("/..1$SelectStatement/#SELECT ..#EXPR_COLUMN/#VAR_REF/2#NAME_FRAGMENT #DOT 3#NAME_FRAGMENT/#C_MARKER")
    public void process$SelectVarRef2() {
        // TODO - implement me
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


    @SyntaxTreePath("// .. #EXPR_COLUMN // #SUBQUERY / .. SelectStatement / .. #TABLE_REFERENCE_LIST_FROM / .. 1#TABLE_ALIAS / #TABLE_REF / #C_MARKER")
    public void process$SelectFromSubquery3() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..SelectStatement/ ..1#TABLE_REFERENCE_LIST_FROM ..2#ERROR_TOKEN_A/#ORDER #C_MARKER")
    public void process$SelectOrderBy() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..SelectStatement/..1#TABLE_REFERENCE_LIST_FROM ..2#ORDER_CLAUSE/..#SORTED_DEF/#VAR_REF//#C_MARKER")
    public void process$SelectOrderBy2() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..SelectStatement/..1#TABLE_REFERENCE_LIST_FROM ..2#GROUP_CLAUSE/..#VAR_REF//#C_MARKER")
    public void process$SelectGroupBy() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..SelectStatement/..1#TABLE_REFERENCE_LIST_FROM ..2#ERROR_TOKEN_A/#GROUP #C_MARKER")
    public void process$SelectGroupBy2() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..SelectStatement/..1#WHERE_CONDITION ..2#ERROR_TOKEN_A/#GROUP #C_MARKER")
    public void process$SelectWhere() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..1$SelectStatement/ ..2#TABLE_REFERENCE_LIST_FROM #WHERE_CONDITION/..#EXISTS_EXPR/..#SUBQUERY/..3$SelectStatement/#SELECT ..#EXPR_COLUMN/#VAR_REF/4#NAME_FRAGMENT #DOT #NAME_FRAGMENT/#C_MARKER")
    public void process$SelectExistsExpr() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..1$SelectStatement/ ..2#TABLE_REFERENCE_LIST_FROM #WHERE_CONDITION/..#EXISTS_EXPR/..#SUBQUERY/..3$SelectStatement/#SELECT ..#WHERE_CONDITION// ..#VAR_REF/4#NAME_FRAGMENT #DOT #NAME_FRAGMENT/#C_MARKER")
    public void process$SelectWhereSubquery() {
        // TODO - implement me
    }

    @SyntaxTreePath("//..1$SelectStatement/ ..2#TABLE_REFERENCE_LIST_FROM #WHERE_CONDITION/..#EXISTS_EXPR/..#SUBQUERY/..3$SelectStatement/#SELECT ..#WHERE_CONDITION// ..#VAR_REF/#NAME_FRAGMENT/#C_MARKER")
    public void process$SelectWhereSubquery2() {
        // TODO - implement me
    }

    @SyntaxTreePath("//1$SelectStatement 2#C_MARKER")
    public void process$SelectAppender() {
        // TODO - implement me
    }
}
