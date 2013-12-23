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
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.TableAlias;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.struct.TableDescriptorLegacy;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class TableAliasImpl extends GenericTableBase implements TableAlias {

    static final Logger log = Logger.getInstance("#PlainTableImpl");

    public TableAliasImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public String getTableName() {
        TableRef elem = (TableRef) getTableNameElement();
        return elem.getTableName();
    }

    // table_spec ( alias )?
    // table_spec:  ( schema_name DOT )? table_ref ( AT_SIGN link_name )?
    // alias:       ( "as" )?  identifier
    @NotNull
    public TableRef getTableNameElement() {
        ASTNode node = getNode().findChildByType(TokenSet.create(PLSqlTypesAdopted.TABLE_REF, PLSqlTypesAdopted.TABLE_REF_WITH_LINK));
        if (node != null && node.getPsi() instanceof TableRef) {
            return (TableRef) node.getPsi();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

/*
    public String getQuickNavigateInfo() {
        return "[Table] " + getTableName().toLowerCase();
    }
*/


    public String getSchemaName() {
        ASTNode schema = getNode().findChildByType(PLSqlTypesAdopted.SCHEMA_NAME);
        return (schema != null) ? StringUtils.discloseDoubleQuotes(schema.getText()) : "";
    }

    public String getAlias() {
        ASTNode alias = getNode().findChildByType(PLSqlTypesAdopted.ALIAS_NAME);
        if (alias != null && alias.getText().trim().length() > 0) {
            String[] splitted = alias.getText().split(" ");
            if (splitted.length > 1) {
                return splitted[1];
            } else {
                return splitted[0];
            }
        }
        return null;
    }


    // TODO -- subject to remove
    protected TableDescriptorLegacy describeInternal() {
        // todo -- resolve stuff refactoring
        throw new NotSupportedException();
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitPlainTable(this);
        } else {
            super.accept(visitor);
        }
    }
}
