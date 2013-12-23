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

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SelectStatementUnionImpl extends PlSqlElementBase implements SelectStatementUnion {

    private final static TokenSet SELECT_OP = TokenSet.create(
            PlSqlTokenTypes.KEYWORD_UNIQUE,
            PlSqlTokenTypes.KEYWORD_DISTINCT
    );

    static final private TokenSet selects = TokenSet.create(
            PlSqlElementTypes.SELECT_EXPRESSION,
            PlSqlElementTypes.SUBQUERY
    );


    public SelectStatementUnionImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public SelectFieldCommon[] getSelectFieldList() {
        return getSelectStatements()[0].getSelectFieldList();
    }

    public boolean isDistinctOrUniqueSpecified() {
        SelectStatement selectStmt =  get_1stSelectStatement();
        final ASTNode[] nodes = selectStmt.getNode().getChildren(SELECT_OP);
        return nodes != null && nodes.length != 0;
    }

    public SelectFieldExpr findSelectFieldByName(String alias) {
        return getSelectStatements()[0].findSelectFieldByName(alias);
    }

    public SelectFieldExpr findSelectFieldByAlias(String alias) {
        return getSelectStatements()[0].findSelectFieldByAlias(alias);
    }

    @NotNull
    public FromClause getFromClause() {
        return getSelectStatements()[0].getFromClause();
    }

    public WhereCondition getWhereCondition() {
        return getSelectStatements()[0].getWhereCondition();
    }

    @Nullable
    public OrderByClause getOrderByClause() {
        SelectStatement[] selects =  getSelectStatements();
        return selects[selects.length-1].getOrderByClause();
    }

    @Nullable
    public GroupByClause getGroupByClause() {
        // todo -- does groupby make sense for select union?
        ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.GROUP_CLAUSE);
        return (node != null) ? (GroupByClause) node.getPsi() : null;
    }

    @Nullable
    public ForUpdateClause getForUpdateClause(){
        ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.FOR_UPDATE_CLAUSE);
        return (node != null) ? (ForUpdateClause) node.getPsi() : null;
    }

    public PsiElement getIntoClause() {
        return getSelectStatements()[0].getIntoClause();
    }


    @NotNull
    private SelectStatement get_1stSelectStatement() {
        ASTNode[] nodes = getNode().getChildren(selects);
        if (nodes == null || nodes.length == 0) {
            throw new SyntaxTreeCorruptedException();
        }

        PsiElement psi = nodes[0].getPsi();
        if(psi instanceof SelectStatement){
            return (SelectStatement) psi;
        } else if(psi instanceof Subquery){
            return ((Subquery) psi).getSelectStatement();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    @NotNull
    public SelectStatement[] getSelectStatements() {
        ASTNode[] nodes = getNode().getChildren(selects);
        SelectStatement[] selects = new SelectStatement[nodes==null? 0: nodes.length];
        for(int i =0; i< selects.length; i++){
            PsiElement psi = nodes[i].getPsi();
            if(psi instanceof SelectStatement){
                selects[i] = (SelectStatement) psi;
            } else if(psi instanceof Subquery){
                selects[i] = ((Subquery) psi).getSelectStatement();
            }
        }


        //SelectStatement[] selects = this.findChildrenByClass(SelectStatement.class);
        if (selects.length == 0) {
            throw new SyntaxTreeCorruptedException();
        }
        return selects;
    }

    public int[] getSelectStatementTypes() {
        throw new NotSupportedException();
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitSelectStatementUnion(this);
        } else {
            super.accept(visitor);
        }
    }
}
