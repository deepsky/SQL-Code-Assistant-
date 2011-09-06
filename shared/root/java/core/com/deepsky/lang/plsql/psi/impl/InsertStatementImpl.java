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
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class InsertStatementImpl extends PlSqlElementBase implements InsertStatement {

    public InsertStatementImpl(ASTNode astNode) {
        super(astNode);
    }

    public TableAlias getIntoTable() {
        final ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.TABLE_ALIAS);

        if (node != null) {
            return (TableAlias) node.getPsi();
        } else {
            return null;
        }
    }

    @NotNull
    public ColumnSpec[] getColumnList() {
        final ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.COLUMN_SPEC_LIST);
        if (node == null) {
            throw new SyntaxTreeCorruptedException();
        }

        ColumnSpecList columnList = (ColumnSpecList) node.getPsi();
        return columnList.getColumns();
    }

    public Expression[] getValues() {
        ASTNode values = getNode().findChildByType(PLSqlTypesAdopted.EXPR_LIST);
        if (values != null) {
            ASTNode[] exprList = values.getChildren(PlSqlElementTypes.EXPR_TYPES);
            Expression[] out = new Expression[exprList.length];
            int i = 0;
            for (ASTNode node : exprList) {
                out[i++] = (Expression) node.getPsi();
            }

            return out;
        }
        return null;
    }

    public SelectStatement getSubquery() {
        // TODO
        return null;
    }


    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitInsertStatement(this);
        } else {
            super.accept(visitor);
        }
    }

}
