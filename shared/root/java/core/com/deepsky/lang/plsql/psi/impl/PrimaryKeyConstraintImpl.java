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
import com.deepsky.lang.plsql.psi.ColumnNameRef;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.PrimaryKeyConstraint;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class PrimaryKeyConstraintImpl extends PlSqlElementBase implements PrimaryKeyConstraint {

    public PrimaryKeyConstraintImpl(ASTNode astNode) {
        super(astNode);
    }

    public String[] getPrimaryKeys() {
        ASTNode[] columnLists = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.OWNER_COLUMN_NAME_LIST));
        ASTNode[] columns = columnLists[0].getChildren(TokenSet.create(PLSqlTypesAdopted.COLUMN_NAME_REF));
        String[] out = new String[columns == null ? 0 : columns.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = StringUtils.discloseDoubleQuotes(columns[i].getText());
        }

        return out;
    }

    @NotNull
    public ColumnNameRef[] getPKColumns() {
        ASTNode columnList = getNode().findChildByType(PLSqlTypesAdopted.OWNER_COLUMN_NAME_LIST);
        ASTNode[] columns = columnList.getChildren(TokenSet.create(PLSqlTypesAdopted.COLUMN_NAME_REF));
        ColumnNameRef[] out = new ColumnNameRef[columns == null ? 0 : columns.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = (ColumnNameRef) columns[i].getPsi();
        }
        return out;
    }

    public String getConstraintName() {
        ASTNode constraintName = getNode().findChildByType(PLSqlTypesAdopted.CONSTRAINT_NAME);
        if(constraintName != null){
            return StringUtils.discloseDoubleQuotes(constraintName.getText());
        } else {
            return "";
        }
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitPrimaryKeyConstraint(this);
        } else {
            super.accept(visitor);
        }
    }

}
