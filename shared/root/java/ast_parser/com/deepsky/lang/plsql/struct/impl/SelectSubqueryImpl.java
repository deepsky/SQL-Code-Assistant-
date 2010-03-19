/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.struct.impl;

import com.deepsky.lang.plsql.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SelectSubqueryImpl extends PsiElementDumb implements SelectStatement {

    SelectStatement select;
    public SelectSubqueryImpl(SelectStatement select){
        this.select = select;        
    }

    @NotNull
    public SelectFieldCommon[] getSelectFieldList() {
        return select.getSelectFieldList();
    }

    public SelectFieldExpr findSelectFieldByName(String alias) {
        return select.findSelectFieldByName(alias);
    }

    @Nullable
    public SelectFieldExpr findSelectFieldByAlias(String alias) {
        return select.findSelectFieldByAlias(alias);
    }

    @NotNull
    public FromClause getFromClause() {
        return select.getFromClause();
    }

    public WhereCondition getWhereCondition() {
        return select.getWhereCondition();
    }

    @Nullable
    public OrderByClause getOrderByClause() {
        return select.getOrderByClause();
    }

    public GroupByClause getGroupByClause() {
        return select.getGroupByClause();
    }

    @Nullable
    public SelectStatement getFollowingSelectStatement() {
        return select.getFollowingSelectStatement();
    }// NONE type means - there is no any following statement

    public int getFollowingSelectStatementType() {
        return select.getFollowingSelectStatementType();
    }

    public void process(Visitor visitor){
        visitor.accept(this);
    }

}


