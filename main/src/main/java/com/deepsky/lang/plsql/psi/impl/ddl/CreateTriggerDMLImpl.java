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

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.Declaration;
import com.deepsky.lang.plsql.psi.DeclarationList;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ddl.CreateTriggerDML;
import com.deepsky.lang.plsql.psi.impl.PlSqlElementBase;
import com.deepsky.lang.plsql.psi.utils.PlSqlUtil;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.view.Icons;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CreateTriggerDMLImpl extends PlSqlElementBase implements CreateTriggerDML {

    public CreateTriggerDMLImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public String getTriggerName() {
        try {
            return getNode().findChildByType(PlSqlElementTypes.TRIGGER_NAME).getText();
        } catch (NullPointerException e) {
            throw new SyntaxTreeCorruptedException();
        }
    }

    @NotNull
    public String getTableName() {
        try {
            ASTNode node = getNode().findChildByType(PlSqlElementTypes.DML_TRIGGER_CLAUSE);
            return node.findChildByType(PlSqlElementTypes.TABLE_REF).getText();
        } catch (NullPointerException e) {
            throw new SyntaxTreeCorruptedException();
        }
    }

    @NotNull
    public Declaration[] getDeclarationList() {
        ASTNode block = getNode().findChildByType(PlSqlElementTypes.PLSQL_BLOCK);
        if (block != null) {
            ASTNode decl = block.findChildByType(PlSqlElementTypes.DECLARE_LIST);
            if (decl != null) {
                DeclarationList dlist = (DeclarationList) decl.getPsi();
                return dlist.getDeclList();
            }
        }
        return new Declaration[0];
    }

    static final TokenSet beforeAfter = TokenSet.create(
        PlSqlTokenTypes.KEYWORD_BEFORE, PlSqlTokenTypes.KEYWORD_AFTER
    );

    static final TokenSet insertUpdateDelete = TokenSet.create(
        PlSqlTokenTypes.KEYWORD_INSERT, PlSqlTokenTypes.KEYWORD_UPDATE, PlSqlTokenTypes.KEYWORD_DELETE
    );

    public String getConditionClause() {
        ASTNode[] after_before = getNode().getChildren(beforeAfter);

        ASTNode clause = getNode().findChildByType(PlSqlElementTypes.DML_TRIGGER_CLAUSE);
        if(clause != null){
            ASTNode[] iud = clause.getChildren(insertUpdateDelete);
            if(iud != null && iud.length > 0){
                StringBuilder sb = new StringBuilder(after_before[0].getText());
                sb.append(" ");
                for(int i=0; i<iud.length; i++){
                    if(i>0){
                        sb.append(" or ");
                    }
                    sb.append(iud[i].getText());
                }
                return sb.toString();
            }
        }
        return null;
    }

    @NotNull
    public String getObjectType() {
        return "TRIGGER";
    }

    @NotNull
    public String getObjectName() {
        return getTriggerName();
    }
    
    @NotNull
    public String getCreateQuery() {
        return PlSqlUtil.completeCreateScript(this);
    }


    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitCreateTriggerDML(this);
        } else {
            super.accept(visitor);
        }
    }

    // presentation stuff
    public Icon getIcon(int flags) {
        return Icons.TRIGGER;
    }

    @Nullable
    public ItemPresentation getPresentation() {
        return new TablePresentation();
    }

    public FileStatus getFileStatus() {
        return null;
    }

    public String getName() {
        return getTriggerName();
    }


    class TablePresentation implements ItemPresentation {
        public String getPresentableText() {
            return getTriggerName().toLowerCase();
        }

        @Nullable
        public String getLocationString() {
            return "(DML Trigger)";
        }

        @Nullable
        public Icon getIcon(boolean open) {
            return Icons.TRIGGER;
        }

        @Nullable
        public TextAttributesKey getTextAttributesKey() {
            return null;
        }
    }

 
}
