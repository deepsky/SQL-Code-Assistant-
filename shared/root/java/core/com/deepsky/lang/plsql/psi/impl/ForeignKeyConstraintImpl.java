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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.ColumnDefinition;
import com.deepsky.lang.plsql.psi.ColumnNameDDL;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ref.DDLTable;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.ASTNode;
import com.deepsky.lang.plsql.psi.ForeignKeyConstraint;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import org.jetbrains.annotations.NotNull;

public class ForeignKeyConstraintImpl extends PlSqlElementBase implements ForeignKeyConstraint {

    public ForeignKeyConstraintImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getReferencedTable() {
        return getNode().findChildByType(PLSqlTypesAdopted.TABLE_NAME).getText();
    }

    public String[] getReferencedColumns() {
        ASTNode[] columnLists = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.COLUMN_NAME_LIST));
        ASTNode[] columns= columnLists[1].getChildren(TokenSet.create(PLSqlTypesAdopted.COLUMN_NAME_DDL));
        String[] out = new String[columns==null? 0: columns.length];
        for(int i=0; i<out.length; i++){
            out[i] = columns[i].getText();
        }

        return out;
    }

    public String[] getOwnColumns() {
        ASTNode[] columnLists = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.COLUMN_NAME_LIST));
        ASTNode[] columns= columnLists[0].getChildren(TokenSet.create(PLSqlTypesAdopted.COLUMN_NAME_DDL));
        String[] out = new String[columns==null? 0: columns.length];
        for(int i=0; i<out.length; i++){
            out[i] = columns[i].getText();
        }

        return out;
    }

    @NotNull
    public DDLTable getReferencedTable2() {
        ASTNode node = getLevelBelow().findChildByType(PLSqlTypesAdopted.TABLE_NAME_DDL);
        __ASSERT_NOT_NULL__(node);

        return (DDLTable) node.getPsi();
    }


    @NotNull
    public ColumnNameDDL[] getReferencedColumns2(){
        ASTNode[] nodes = getLevelBelow().getChildren(TokenSet.create(PLSqlTypesAdopted.COLUMN_NAME_LIST));
        if(nodes == null || nodes.length != 2){
            throw new SyntaxTreeCorruptedException();
        }

        ASTNode[] columns= nodes[1].getChildren(TokenSet.create(PLSqlTypesAdopted.COLUMN_NAME_DDL));
        ColumnNameDDL[] out = new ColumnNameDDL[columns==null? 0: columns.length];
        for(int i=0; i<out.length; i++){
            out[i] = (ColumnNameDDL) columns[i].getPsi();
        }

        return out;
    }

    @NotNull
    private ASTNode getLevelBelow(){
        ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.FK_SPEC);
        __ASSERT_NOT_NULL__(node);
        return node;
    }

    public String getConstraintName() {
        return getNode().findChildByType(PLSqlTypesAdopted.CONSTRAINT_NAME).getText();
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
      if (visitor instanceof PlSqlElementVisitor) {
        ((PlSqlElementVisitor)visitor).visitForeignKeyConstraint(this);
      }
      else {
        super.accept(visitor);
      }
    }

}
