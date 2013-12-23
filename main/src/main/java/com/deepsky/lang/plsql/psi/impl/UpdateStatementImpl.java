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
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.TableAlias;
import com.deepsky.lang.plsql.psi.UpdateStatement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class UpdateStatementImpl extends PlSqlElementBase implements UpdateStatement {

    public UpdateStatementImpl(ASTNode astNode) {
        super(astNode);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitUpdateStatement(this);
        } else {
            super.accept(visitor);
        }
    }

    @NotNull
    public TableAlias getTargetTable() {

        TableAlias tab;
        ASTNode simple_u = getNode().findChildByType(PLSqlTypesAdopted.SIMPLE_UPDATE_COMMAND);
        if (simple_u != null) {
            tab = getTableAlias(simple_u);
        } else {
            ASTNode subquery_u = getNode().findChildByType(PLSqlTypesAdopted.SUBQUERY_UPDATE_COMMAND);
            if (subquery_u != null) {
                tab = getTableAlias(subquery_u);
            } else {
                // Syntax looks not quite correct, but try to find TABLE_ALIAS anyway
                return getTableAlias(getNode());
            }
        }
        return tab;
    }

    private TableAlias getTableAlias(ASTNode ast) {
        if (ast != null) {
            ASTNode alias = ast.findChildByType(PLSqlTypesAdopted.TABLE_ALIAS);
            if (alias != null) {
                PsiElement psi = alias.getPsi();
                if (psi instanceof TableAlias) {
                    return (TableAlias) psi;
                }
            }
        }

        throw new SyntaxTreeCorruptedException();
    }

}
