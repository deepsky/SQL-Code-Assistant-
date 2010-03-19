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

package com.deepsky.lang.plsql.psi.impl.ddl;

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.impl.PlSqlElementBase;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class CreateViewImpl extends PlSqlElementBase implements CreateView {

    public CreateViewImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public String getViewName() {
        return this.findChildByType(PLSqlTypesAdopted.VIEW_NAME_DDL).getText();
    }

    @NotNull
    public VColumnDefinition[] getColumnDefs() {
        ASTNode[] columns = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.V_COLUMN_DEF));
        VColumnDefinition[] out = new VColumnDefinition[columns==null? 0: columns.length];
        for(int i=0; i<out.length; i++){
            out[i] = (VColumnDefinition) columns[i].getPsi();
        }
        return out;

    }

/*
    @Nullable
    public String getQuickNavigateInfo() {
        TableDefinition t = findParent(TableDefinition.class);
        if (t != null) {
            String tableName = t.getTableName();
//            TableDescriptor desc = t.describe();
//            if(desc != null){
//                desc.
//            }
            return "[Table] " + tableName.toLowerCase()
                    + "\n [Column] " + getColumnName().toLowerCase() + " "
                    + getType().toString().toUpperCase();
        }
        return null;
    }
*/

    // todo -- needs to review
    public VColumnDefinition getColumnByName(String name) {
        for (VColumnDefinition c : getColumnDefs()) {
            if (c.getColumnName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public int getColumnPos(String columnName) {
        ASTNode[] columns = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.V_COLUMN_DEF));

        if(columns != null && columns.length > 0){
            int pos = 0;
            for(ASTNode node: columns){
                if(columnName.equalsIgnoreCase(node.getText())){
                    return pos;
                } else {
                    pos++;
                }
            }
        }
        return -1;
    }

    @NotNull
    public SelectStatement getSelectExpr() {
        ASTNode select = getNode().findChildByType(
                TokenSet.create(PLSqlTypesAdopted.SELECT_EXPRESSION, PLSqlTypesAdopted.SELECT_EXPRESSION_UNION)
            );
        if(select != null){
            return (SelectStatement) select.getPsi();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }


    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitCreateView(this);
        } else {
            super.accept(visitor);
        }
    }
}
