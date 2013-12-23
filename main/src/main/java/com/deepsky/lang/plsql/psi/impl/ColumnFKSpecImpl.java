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
import com.deepsky.lang.plsql.psi.ColumnFKSpec;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class ColumnFKSpecImpl extends PlSqlElementBase implements ColumnFKSpec {

    public ColumnFKSpecImpl(ASTNode astNode) {
        super(astNode);
    }


    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitColumnFKSpec(this);
        } else {
            super.accept(visitor);
        }
    }


    public String getReferencedTable() {
        ASTNode tableRef = getNode().findChildByType(PLSqlTypesAdopted.TABLE_REF);
        if(tableRef == null){
            throw new SyntaxTreeCorruptedException();
        }
        return StringUtils.discloseDoubleQuotes(tableRef.getText());
    }

    public String getReferencedColumn() {
        ASTNode columnRef = getNode().findChildByType(PLSqlTypesAdopted.COLUMN_NAME_REF);
        if(columnRef == null){
            throw new SyntaxTreeCorruptedException();
        }
        return StringUtils.discloseDoubleQuotes(columnRef.getText());
    }

    public String getConstraintName() {
        ASTNode columnRef = getNode().findChildByType(PLSqlTypesAdopted.CONSTRAINT_NAME);
        return columnRef!=null? columnRef.getText(): null;
    }

    @NotNull
    public TableRef getTableRef() {
        ASTNode tableRef = getNode().findChildByType(PLSqlTypesAdopted.TABLE_REF);
        if(tableRef == null){
            throw new SyntaxTreeCorruptedException();
        }
        return (TableRef) tableRef.getPsi();
    }


}
