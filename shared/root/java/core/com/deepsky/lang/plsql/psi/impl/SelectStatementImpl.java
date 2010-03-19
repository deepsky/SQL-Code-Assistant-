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

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.resolver.ResolveUtils;
import com.deepsky.lang.plsql.struct.parser.ContextPath;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SelectStatementImpl extends PlSqlElementBase implements SelectStatement {

    public SelectStatementImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public SelectFieldCommon[] getSelectFieldList() {

        final ASTNode[] nodes = getNode().getChildren(PlSqlElementTypes.DISPLAYED_COLUMN);

        SelectFieldCommon[] columns = new SelectFieldCommon[nodes.length];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = (SelectFieldCommon) nodes[i].getPsi();
        }
        return columns;
    }

    public SelectFieldExpr findSelectFieldByName(String alias) {
        final ASTNode[] nodes = getNode().getChildren(PlSqlElementTypes.DISPLAYED_COLUMN);

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].getPsi() instanceof SelectFieldExpr) {
                SelectFieldExpr field = (SelectFieldExpr) nodes[i].getPsi();
                String alias1 = field.getAlias();
                if (alias1 != null) {
                    if (alias1.equalsIgnoreCase(alias)) {
                        return field;
                    }
                } else {
                    Expression fexp = field.getExpression();
                    // first check whethere the name is a composite 
                    if (fexp instanceof CompositeName) {
                        NameFragmentRef[] names = ((CompositeName) fexp).getNamePieces();
                        if (alias.equalsIgnoreCase(names[names.length - 1].getText())) {
                            return field;
                        }
                    } else if (fexp.getText().equalsIgnoreCase(alias)) {
                        return field;
                    }
                }
            }
        }
        return null;
    }

    public SelectFieldExpr findSelectFieldByAlias(String alias) {
        final ASTNode[] nodes = getNode().getChildren(PlSqlElementTypes.DISPLAYED_COLUMN);

        for (ASTNode node : nodes) {
            if (node.getPsi() instanceof SelectFieldExpr) {
                SelectFieldExpr field = (SelectFieldExpr) node.getPsi();
                String alias1 = field.getAlias();
                if (alias1 != null) {
                    if (alias1.equalsIgnoreCase(alias)) {
                        return field;
                    }
                }
            }
        }
        return null;
    }

    @NotNull
    public FromClause getFromClause() {
        final ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.TABLE_REFERENCE_LIST_FROM);
        if (node != null) {
            return (FromClause) node.getPsi();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    public WhereCondition getWhereCondition() {
        final ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.WHERE_CONDITION);
        if (node != null) {
            return (WhereCondition) node.getPsi();
        } else {
            return null;
        }
    }

    @Nullable
    public OrderByClause getOrderByClause() {
        ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.ORDER_CLAUSE);
        return (node != null) ? (OrderByClause) node.getPsi() : null;
    }

    @Nullable
    public GroupByClause getGroupByClause() {
        ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.GROUP_CLAUSE);
        return (node != null) ? (GroupByClause) node.getPsi() : null;
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
            ((PlSqlElementVisitor) visitor).visitSelectStatement(this);
        } else {
            super.accept(visitor);
        }
    }


    // [Contex Management Stuff] Start -------------------------------
    CtxPath cachedCtxPath = null;
    public CtxPath getCtxPath() {
        if(cachedCtxPath != null){
            return cachedCtxPath;
        } else {
            CtxPath parent = super.getCtxPath();
            cachedCtxPath = new CtxPathImpl(
                    parent.getPath()
                            + ResolveUtils.encodeCtx(ContextPath.SELECT_EXPR,parent.getSeqNEXT() + "$"));
        }
        return cachedCtxPath;
    }
    // [Contex Management Stuff] End ---------------------------------

}
