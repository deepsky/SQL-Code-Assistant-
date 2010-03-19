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

package com.deepsky.lang.plsql.psi.impl;

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.NotSupportedException;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SelectStatementUnionImpl extends PlSqlElementBase implements SelectStatementUnion {

    public SelectStatementUnionImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public SelectFieldCommon[] getSelectFieldList() {
        SelectStatement[] selects = this.findChildrenByClass(SelectStatement.class);
        if(selects.length == 0){
            throw new SyntaxTreeCorruptedException();
        }
        return selects[0].getSelectFieldList();
    }

    public SelectFieldExpr findSelectFieldByName(String alias) {
        SelectStatement[] selects = this.findChildrenByClass(SelectStatement.class);
        if(selects.length == 0){
            throw new SyntaxTreeCorruptedException();
        }
        return selects[0].findSelectFieldByName(alias);
    }

    public SelectFieldExpr findSelectFieldByAlias(String alias) {
        SelectStatement[] selects = this.findChildrenByClass(SelectStatement.class);
        if(selects.length == 0){
            throw new SyntaxTreeCorruptedException();
        }
        return selects[0].findSelectFieldByAlias(alias);
    }

    @NotNull
    public FromClause getFromClause() {
        throw new NotSupportedException();
    }

    public WhereCondition getWhereCondition() {
        throw new NotSupportedException();
    }

    @Nullable
    public OrderByClause getOrderByClause() {
        SelectStatement[] selects = this.findChildrenByClass(SelectStatement.class);
        if(selects.length == 0){
            throw new SyntaxTreeCorruptedException();
        }
        return selects[selects.length].getOrderByClause();
    }

    @Nullable
    public GroupByClause getGroupByClause() {
        throw new NotSupportedException();
    }

    @Nullable
    public SelectStatement getFollowingSelectStatement() {
        throw new NotSupportedException();
    }// NONE type means - there is no any following statement

    public int getFollowingSelectStatementType() {
        throw new NotSupportedException();
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
      if (visitor instanceof PlSqlElementVisitor) {
        ((PlSqlElementVisitor)visitor).visitSelectStatementUnion(this);
      }
      else {
        super.accept(visitor);
      }
    }
}
