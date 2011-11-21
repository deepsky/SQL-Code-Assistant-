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
import com.deepsky.lang.plsql.psi.ddl.PartitionSpecification;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.utils.StringUtils;
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

    public TableDefinitionImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getTableName() {
        PsiElement psi = this.findChildByType(PLSqlTypesAdopted.TABLE_NAME_DDL);
        if (psi != null) {
            return StringUtils.discloseDoubleQuotes(psi.getText());
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

    public String getQuickNavigateInfo() {
        return "[Table] " + getTableName().toLowerCase();
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

    public int getTableType() {
        ASTNode node = getNode().findChildByType(TokenSet.create(
                PLSqlTypesAdopted.IOT_TYPE,
                PLSqlTypesAdopted.HEAP_ORGINIZED,
                PLSqlTypesAdopted.EXTERNAL_TYPE
        ));

        if (node != null) {
            if (node.getElementType() == PLSqlTypesAdopted.IOT_TYPE) {
                return TableDefinition.IOT_ORGANIZED;
            } else if (node.getElementType() == PLSqlTypesAdopted.HEAP_ORGINIZED) {
                return TableDefinition.HEAP_ORGANIZED;
            } else if (node.getElementType() == PLSqlTypesAdopted.EXTERNAL_TYPE) {
                return TableDefinition.EXTERNAL_ORGANIZED;
            }
        }
        return TableDefinition.REGULAR;
    }

    public int getPartitionType() {
        ASTNode partition = getNode().findChildByType(PLSqlTypesAdopted.PARTITION_SPEC);
        if (partition != null) {
            if (partition.findChildByType(PLSqlTypesAdopted.RANGE_PARTITION) != null) {
                return TableDefinition.PARTITION_BY_RANGE;
            } else if (partition.findChildByType(PLSqlTypesAdopted.HASH_PARTITION) != null) {
                return TableDefinition.PARTITION_BY_HASH;
            }
        }

        return TableDefinition.PARTITION_NONE;
    }

    public PartitionSpecification getPartitionSpec() {
        ASTNode partition = getNode().findChildByType(PLSqlTypesAdopted.PARTITION_SPEC);
        if (partition != null) {
            ASTNode spec =  partition.findChildByType(
                    TokenSet.create(
                            PLSqlTypesAdopted.RANGE_PARTITION,
                            PLSqlTypesAdopted.HASH_PARTITION
                    )
            );

            return spec != null? (PartitionSpecification) spec.getPsi() : null;
        }
        return null;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitTableDefinition(this);
        } else {
            super.accept(visitor);
        }
    }


    public static Icon chooseIcon(int tableType, int partitionType){
        switch(tableType){
            case TableDefinition.IOT_ORGANIZED:
                return Icons.IOT_TABLE;
            case TableDefinition.HEAP_ORGANIZED:
                return Icons.HEAP_TABLE;
            case TableDefinition.EXTERNAL_ORGANIZED:
                return Icons.EXT_TABLE;
            case TableDefinition.TEMPORARY:
                return Icons.TEMP_TABLE;
            default:
                return (partitionType != -1)? Icons.PARTI_TABLE: Icons.TABLE;
        }
    }

    private Icon chooseIcon() {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(
                PLSqlTypesAdopted.IOT_TYPE,
                PLSqlTypesAdopted.HEAP_ORGINIZED,
                PLSqlTypesAdopted.EXTERNAL_TYPE,
                PLSqlTypesAdopted.PARTITION_SPEC
        ));
        switch (nodes.length) {
            case 0:
                return Icons.TABLE;
            case 1: {
                IElementType t = nodes[0].getElementType();
                if (t == PLSqlTypesAdopted.IOT_TYPE) {
                    return Icons.IOT_TABLE;
                } else if (t == PLSqlTypesAdopted.HEAP_ORGINIZED) {
                    return Icons.HEAP_TABLE;
                } else if (t == PLSqlTypesAdopted.EXTERNAL_TYPE) {
                    return Icons.EXT_TABLE;
                } else if (t == PLSqlTypesAdopted.PARTITION_SPEC) {
                    return Icons.PARTI_TABLE;
                } else {
                    return Icons.TABLE;
                }
            }
            default: {
                IElementType t = nodes[0].getElementType();
                if (t == PLSqlTypesAdopted.IOT_TYPE) {
                    return Icons.IOT_TABLE;
                } else if (t == PLSqlTypesAdopted.HEAP_ORGINIZED) {
                    return Icons.HEAP_TABLE;
                } else if (t == PLSqlTypesAdopted.EXTERNAL_TYPE) {
                    return Icons.EXT_TABLE;
                } else {
                    // table partitioned and IOT or HEAP organized
                    t = nodes[1].getElementType();
                    if (t == PLSqlTypesAdopted.IOT_TYPE) {
                        return Icons.IOT_TABLE;
                    } else if (t == PLSqlTypesAdopted.HEAP_ORGINIZED) {
                        return Icons.HEAP_TABLE;
                    } else if (t == PLSqlTypesAdopted.EXTERNAL_TYPE) {
                        return Icons.EXT_TABLE;
                    } else if (t == PLSqlTypesAdopted.PARTITION_SPEC) {
                        return Icons.PARTI_TABLE;
                    } else {
                        return Icons.TABLE;
                    }
                }
            }
        }
    }


    public Icon getIcon(int flags) {
        return chooseIcon(); //Icons.TEMP_TABLE;
    }

    // presentation stuff
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
        public String getPresentableText() {
            return getTableName().toLowerCase();
        }

        @Nullable
        public String getLocationString() {
            return null;///"(temporary table)";
        }

        @Nullable
        public Icon getIcon(boolean open) {
            return chooseIcon();//Icons.TEMP_TABLE;
        }

        @Nullable
        public TextAttributesKey getTextAttributesKey() {
            return TextAttributesKey.find("SQL.TABLE");
        }
    }

}
