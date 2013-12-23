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
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.VariableDecl;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class VariableDeclImpl extends PlSqlDeclarationBase implements VariableDecl {

    public VariableDeclImpl(ASTNode astNode) {
        super(astNode);
    }

    public PsiElement getVariableName() {
        return this.findChildByType(PLSqlTypesAdopted.VARIABLE_NAME);
    }

    public Type getType() {
        return getTypeSpec().getType();
    }

    public TypeSpec getTypeSpec() {
        ASTNode type = getNode().findChildByType(PlSqlElementTypes.TYPES);
        if (type != null) {
            return (TypeSpec) type.getPsi();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    public PsiElement getConstant(){
        ASTNode _const = getNode().findChildByType(PlSqlTokenTypes.KEYWORD_CONSTANT);
        return _const!=null? _const.getPsi(): null;
    }

    public boolean isNotNull() {
        // todo --
        return false;
    }

    public boolean isBeingAssigned() {
        // todo --
        return false;
    }

    public boolean isDefault() {
        // todo --
        return false;
    }

    public Expression getDefaultExpr() {
        // todo --
        return null;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitVariableDecl(this);
        } else {
            super.accept(visitor);
        }
    }

    public String getDeclName() {
        PsiElement e = this.findChildByType(PLSqlTypesAdopted.VARIABLE_NAME);
        if (e != null) {
            return e.getText();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    @Nullable
    public ItemPresentation getPresentation() {
        try {
            return new VarPresentation();
        } catch(SyntaxTreeCorruptedException e){
            return null;
        }
    }

    private class VarPresentation implements ItemPresentation {
        public String getPresentableText() {
            String ctxPath = VariableDeclImpl.this.getCtxPath1().getPath();
            String varName = getDeclName();
            ContextPathUtil.CtxPathParser ctxParser = new ContextPathUtil.CtxPathParser(ctxPath);
            ContextPathUtil.CtxPathParser parentCtxParser = ctxParser.getParentCtxParser();
            if(parentCtxParser != null){
                switch(parentCtxParser.extractLastCtxType()){
                    case ContextPath.PACKAGE_BODY:
                    case ContextPath.PACKAGE_SPEC:
                        varName = parentCtxParser.lastCtxName() + "." + varName;
                        break;
                }
            }
            String text = varName + " " + getTypeSpec().getText();
            return "[Variable] " + text;
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
            return TextAttributesKey.find("PLSQL.VAR");
        }
    }
}
