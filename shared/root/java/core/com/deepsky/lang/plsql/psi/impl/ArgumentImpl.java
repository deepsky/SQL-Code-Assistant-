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
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.Argument;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.deepsky.lang.plsql.psi.utils.ASTNodeIterator;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class ArgumentImpl extends PlSqlElementBase implements Argument {

    public ArgumentImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getArgumentName() {
        PsiElement e = findChildByType(PLSqlTypesAdopted.PARAMETER_NAME);
        if (e != null) {
            return e.getText();
        } else {
            throw new SyntaxTreeCorruptedException();
//            return "";
        }
    }

    @NotNull
    public Type getType() {
        ASTNode type = getNode().findChildByType(PlSqlElementTypes.TYPES);
        if (type != null) {
            return ((TypeSpec) type.getPsi()).getType();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    public TypeSpec getTypeSpec() {
        ASTNode type = getNode().findChildByType(PlSqlElementTypes.TYPES);
        if (type != null) {
            return (TypeSpec) type.getPsi();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

/*
    public String getQuickNavigateInfo() {
        return getPresentableForm();
    }
*/

    public String getPresentableForm() {
        StringBuilder out = new StringBuilder();
        ASTNode child = getNode().getFirstChildNode();
        ASTNodeIterator iterator = new ASTNodeIterator(child);
        while (iterator.hasNext() && !iterator.peek().getText().equals(";")) {
            if (out.length() > 0) {
                out.append(" ");
            }
            out.append(iterator.next().getText());
        }

        return out.toString();
    }

    public Expression getDefaultExpr() {
        return (Expression) findChildByFilter(PlSqlElementTypes.EXPR_TYPES);
    }

    public boolean isIn() {
        // todo -- implement me
        return false;
    }

    public boolean isOut() {
        // todo -- implement me
        return false;
    }

    public boolean isNocopy() {
        // todo -- implement me
        return false;
    }

    public boolean isAssigned() {
        // todo
        return false;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitArgument(this);
        } else {
            super.accept(visitor);
        }
    }

    @Nullable
    public ItemPresentation getPresentation() {
        try {
            return new ArgumentPresentation();
        } catch(SyntaxTreeCorruptedException e){
            return null;
        }
    }

    private class ArgumentPresentation implements ItemPresentation {
        public String getPresentableText() {
            String text = getArgumentName() + " " + getTypeSpec().getText();
            return "[Argument] " + text;
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
