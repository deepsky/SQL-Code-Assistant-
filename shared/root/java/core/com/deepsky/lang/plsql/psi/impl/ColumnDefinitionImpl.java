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

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.ColumnDefinition;
import com.deepsky.lang.plsql.psi.TableDefinition;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.parser.ASTParseHelper;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ColumnDefinitionImpl extends PlSqlElementBase implements ColumnDefinition {//}, PsiNamedElement {

    static final LoggerProxy log = LoggerProxy.getInstance("#ColumnDefinitionImpl");

    public ColumnDefinitionImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getName(){
//        return super.getName();
        String name =  this.findChildByType(PLSqlTypesAdopted.COLUMN_NAME_DDL).getText();
        log.info("getName() = " + name + " this: " + this);
        return name;
    }
    
    public String getColumnName() {
        return this.findChildByType(PLSqlTypesAdopted.COLUMN_NAME_DDL).getText();
    }

    public PsiElement getPsiColumnName(){
        return this.findChildByType(PLSqlTypesAdopted.COLUMN_NAME_DDL);
    }

    public int getTextOffset() {
        // todo !!!
        return findChildByType(PLSqlTypesAdopted.COLUMN_NAME_DDL).getTextOffset();
    }

    public Type getType() {
        PsiElement s = findChildByType(PLSqlTypesAdopted.DATATYPE);
        if (s == null) {
            // todo
            return null;
        }
        return ASTParseHelper.parseDatatype(s.getText());
    }

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


    public boolean isNotNull() {
        ASTNode not_null = getNode().findChildByType(PlSqlTokenTypes.KEYWORD_NOT);
        return not_null != null;
    }

    public boolean isPrimaryKey() {
        ASTNode[] pk = getNode().getChildren(TokenSet.create(
                PlSqlTokenTypes.KEYWORD_PRIMARY, PlSqlTokenTypes.KEYWORD_KEY
                )
        );

        if(pk != null && pk.length == 2){
            return true;
        } else {
            // todo -- request table for PK
            return false;
        }
    }

    public ForeignKeySpec getForeignKeySpec() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public TableDefinition getTableDefinition() {
        ASTNode parent = getNode().getTreeParent();
        if(parent != null && parent.getElementType() == PLSqlTypesAdopted.TABLE_DEF){
            return (TableDefinition) parent.getPsi();
        }
        throw new SyntaxTreeCorruptedException();
    }


    public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException {
        return null;
    }
}
