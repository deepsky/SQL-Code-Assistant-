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

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.ColumnDefinition;
import com.deepsky.lang.plsql.psi.ColumnFKSpec;
import com.deepsky.lang.plsql.psi.ColumnPKSpec;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ColumnDefinitionImpl extends PlSqlElementBase implements ColumnDefinition {//}, PsiNamedElement {

    static final LoggerProxy log = LoggerProxy.getInstance("#ColumnDefinitionImpl");

    public ColumnDefinitionImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getName() {
        return getColumnName();
    }

    public String getColumnName() {
        return StringUtils.discloseDoubleQuotes(
                this.findChildByType(PLSqlTypesAdopted.COLUMN_NAME_DDL).getText()
                );
    }

    public PsiElement getPsiColumnName() {
        return this.findChildByType(PLSqlTypesAdopted.COLUMN_NAME_DDL);
    }

    public int getTextOffset() {
        // todo !!!
        return findChildByType(PLSqlTypesAdopted.COLUMN_NAME_DDL).getTextOffset();
    }


    public Type getType() {
        TypeSpec type = this.findChildByClass(TypeSpec.class);
        if (type != null) {
            return type.getType();
        }
        throw new SyntaxTreeCorruptedException();
    }

    public TypeSpec getTypeSpec() {
        TypeSpec type = this.findChildByClass(TypeSpec.class);
        if (type != null) {
            return type;
        }
        throw new SyntaxTreeCorruptedException();
    }

/*
    @Nullable
    public String getQuickNavigateInfo() {
        TableDefinition t = findParent(TableDefinition.class);
        if (t != null) {
            String completeName = t.getTableName() + "." + getColumnName();
            return  "[Column] " + completeName.toLowerCase() + " "
                    + getType().toString().toUpperCase();
        }
        return null;
    }
*/

    public boolean isNotNull() {
        ASTNode not_null = getNode().findChildByType(PLSqlTypesAdopted.NOT_NULL_STMT);
        return not_null != null && not_null.findChildByType(PlSqlTokenTypes.KEYWORD_NOT) != null;
    }

    public boolean isPrimaryKey() {
        ASTNode pkSpec = getNode().findChildByType(PLSqlTypesAdopted.COLUMN_PK_SPEC);
        return pkSpec != null;
    }

    public boolean hasCheckConstraint() {
        return getNode().findChildByType(PLSqlTypesAdopted.COLUMN_CHECK_CONSTRAINT) != null;
    }

    public ColumnPKSpec getPrimaryKeySpec() {
        ASTNode pkSpec = getNode().findChildByType(PLSqlTypesAdopted.COLUMN_PK_SPEC);
        if (pkSpec != null) {
            // ("constraint" constraint_name)?  "primary" "key" ("disable"|"enable")?
            return (ColumnPKSpec) pkSpec.getPsi();
        }
        return null;
    }

    public ColumnFKSpec getForeignKeySpec() {
        ASTNode fkSpec = getNode().findChildByType(PLSqlTypesAdopted.COLUMN_FK_SPEC);
        if (fkSpec != null) {
            // ("constraint" constraint_name)? "references"! (schema_name DOT)? table_name_ddl OPEN_PAREN! column_name_ddl CLOSE_PAREN! ("rely")? ("disable"|"enable")?
            return (ColumnFKSpec) fkSpec.getPsi();
        }
        return null;
    }

    public TableDefinition getTableDefinition() {
        ASTNode parent = getNode().getTreeParent();
        if (parent != null && parent.getElementType() == PLSqlTypesAdopted.TABLE_DEF) {
            return (TableDefinition) parent.getPsi();
        }
        throw new SyntaxTreeCorruptedException();
    }


    public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException {
        return null;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitColumnDefinition(this);
        } else {
            super.accept(visitor);
        }
    }

    @Nullable
    public ItemPresentation getPresentation() {
        try {
            return new ColumnDefPresentation();
        } catch(SyntaxTreeCorruptedException e){
            return null;
        }
    }

    private class ColumnDefPresentation implements ItemPresentation {
        public String getPresentableText() {
            TableDefinition t = getTableDefinition();
            if (t != null) {
                String completeName = t.getTableName() + "." + getColumnName();
                return  "[Column] " + completeName.toLowerCase() + " "
                        + getTypeSpec().getText();
            }
            return null;
        }

        @Nullable
        public String getLocationString() {
            return null;
        }

        @Nullable
        public Icon getIcon(boolean open) {
            return null;
        }

        @Nullable
        public TextAttributesKey getTextAttributesKey() {
            return null;
        }
    }

}
