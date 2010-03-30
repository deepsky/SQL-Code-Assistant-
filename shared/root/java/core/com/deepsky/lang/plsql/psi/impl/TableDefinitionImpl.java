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
import com.deepsky.lang.plsql.psi.ColumnDefinition;
import com.deepsky.lang.plsql.psi.GenericConstraint;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.view.Icons;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TableDefinitionImpl extends PlSqlElementBase implements TableDefinition {

    IElementType organization_type = null;

    public TableDefinitionImpl(ASTNode astNode) {
        super(astNode);

        ASTNode organization_node = astNode.findChildByType(TokenSet.create(
                PLSqlTypesAdopted.IOT_TYPE,
                PLSqlTypesAdopted.HEAP_ORGINIZED,
                PLSqlTypesAdopted.EXTERNAL_TYPE
            )
        );

        if(organization_node != null){
            organization_type = organization_node.getElementType();
        }
    }

    public String getTableName() {
        PsiElement psi = this.findChildByType(PLSqlTypesAdopted.TABLE_NAME_DDL);
        if (psi != null) {
            return psi.getText();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    @NotNull
    public ColumnDefinition[] getColumnDefs() {
        return this.findChildrenByClass(ColumnDefinition.class);
    }

    public ColumnDefinition getColumnByName(String name) {
        for (ColumnDefinition c : getColumnDefs()) {
            if (c.getColumnName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    @NotNull
    public GenericConstraint[] getConstraints() {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.CONSTRAINT));
        if (nodes != null) {
            List<GenericConstraint> out = new ArrayList<GenericConstraint>();
            for (ASTNode node : nodes) {
                if (node.getPsi() instanceof GenericConstraint) {
                    out.add((GenericConstraint) node.getPsi());
                }
            }
            return out.toArray(new GenericConstraint[out.size()]);
        }
        return new GenericConstraint[0];
    }


    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitTableDefinition(this);
        } else {
            super.accept(visitor);
        }
    }

    // presentation stuff
    public Icon getIcon(int flags){
        if(organization_type != null){
            if(organization_type == PLSqlTypesAdopted.IOT_TYPE){
                return Icons.TABLE;
            } else if(organization_type == PLSqlTypesAdopted.HEAP_ORGINIZED){
                return Icons.TABLE;
            } else if(organization_type == PLSqlTypesAdopted.EXTERNAL_TYPE){
                return Icons.EXT_TABLE;
            }
        }

        return Icons.TABLE;
    }

    @Nullable
    public ItemPresentation getPresentation() {
        return new TablePresentation();
    }

    public FileStatus getFileStatus() {
        return null;
    }

    public String getName() {
        return getTableName();
    }


    class TablePresentation implements ItemPresentation {
        public String getPresentableText(){
            return getTableName().toLowerCase();
        }

        @Nullable
        public String getLocationString(){
            if(organization_type != null){
                if(organization_type == PLSqlTypesAdopted.IOT_TYPE){
                    return "(index-organized table)";
                } else if(organization_type == PLSqlTypesAdopted.HEAP_ORGINIZED){
                    return "(heap-organized table)";
                } else if(organization_type == PLSqlTypesAdopted.EXTERNAL_TYPE){
                    return "(external table)";
                }
            }

            return "(regular table)";
        }

        @Nullable
        public Icon getIcon(boolean open){
            return Icons.TABLE;
        }

        @Nullable
        public TextAttributesKey getTextAttributesKey(){
            return TextAttributesKey.find("SQL.TABLE");
        }
    }

}
