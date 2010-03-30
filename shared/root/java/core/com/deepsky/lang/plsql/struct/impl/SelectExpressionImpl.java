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

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SelectExpressionImpl extends PsiElementDumb implements SelectStatement {

    WhereCondition where;
    FromClause fromClause;
    SelectFieldCommon[] fields;
    SelectStatement following;

    int followingType = SelectStatement.NONE;

    public SelectExpressionImpl() {
        super(PLSqlTypesAdopted.SELECT_EXPRESSION);
    }


    @NotNull
    public SelectFieldCommon[] getSelectFieldList() {
        return fields;
    }

    public void setSelectFieldList(SelectFieldCommon[] fields) {
        this.fields = fields;
    }

    public SelectFieldExpr findSelectFieldByName(String alias) {
        // todo --
        return null;
    }

    @Nullable
    public SelectFieldExpr findSelectFieldByAlias(String alias) {
        // todo --
        return null;
    }

    @NotNull
    public FromClause getFromClause() {
        return fromClause;
    }

    public OrderByClause getOrderByClause() {
        // todo --
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public GroupByClause getGroupByClause() {
        // todo --
        return null;
    }

    @Nullable
    public SelectStatement getFollowingSelectStatement() {
        return following;
    }// NONE type means - there is no any following statement

    public int getFollowingSelectStatementType() {
        return followingType;
    }

    public void setWhereCondition(WhereCondition where) {
        this.where = where;
    }

    public WhereCondition getWhereCondition() {
        return where;
    }

    public void process(Visitor proc) {
        proc.accept(this);
    }

    public void setFromClause(FromClause fromClause) {
        this.fromClause = fromClause;
    }

    public void setFollowingSelectStatement(SelectStatement select1, int type) {
        this.following = select1;
        this.followingType = type;
    }

}
