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

import com.deepsky.lang.plsql.psi.TableDefinition;
import com.deepsky.lang.plsql.psi.ColumnDefinition;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.GenericConstraint;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.database.ObjectCacheFactory;
import com.deepsky.database.ObjectCache;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TableDefinitionImpl extends PlSqlElementBase implements TableDefinition {

    public TableDefinitionImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getTableName() {
        return this.findChildByType(PLSqlTypesAdopted.TABLE_NAME_DDL).getText();
    }

    @NotNull
    public ColumnDefinition[] getColumnDefs() {
        return this.findChildrenByClass(ColumnDefinition.class);
    }

    public ColumnDefinition getColumnByName(String name) {
        for( ColumnDefinition c: getColumnDefs()){
            if(c.getColumnName().equalsIgnoreCase(name)){
                return c;
            }
        }
        return null;
    }

    @NotNull
    public GenericConstraint[] getConstraints() {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.CONSTRAINT));
        if(nodes != null){
            List<GenericConstraint> out = new ArrayList<GenericConstraint>();
            for(ASTNode node: nodes){
                if(node.getPsi() instanceof GenericConstraint){
                    out.add((GenericConstraint) node.getPsi());
                }
            }
            return out.toArray(new GenericConstraint[out.size()]);
        }
        return new GenericConstraint[0];
    }

//    public TableDescriptor describe() {
//        DbObject[] objs = ObjectCacheFactory.getObjectCache().findByNameForType(ObjectCache.TABLE, getTableName());
//        return (TableDescriptor) (objs.length>0? objs[0]: null);
//    }

    public void accept(@NotNull PsiElementVisitor visitor) {
      if (visitor instanceof PlSqlElementVisitor) {
        ((PlSqlElementVisitor)visitor).visitTableDefinition(this);
      }
      else {
        super.accept(visitor);
      }
    }

}
