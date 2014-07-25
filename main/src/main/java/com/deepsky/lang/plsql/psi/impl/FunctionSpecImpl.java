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
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.names.ObjectName;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class FunctionSpecImpl extends PlSqlElementBase implements FunctionSpec {

    public FunctionSpecImpl(ASTNode astNode) {
        super(astNode);
    }

    public ArgumentList getArgumentList() {
        return (ArgumentList) this.findChildByType(PLSqlTypesAdopted.ARGUMENT_LIST);
    }

    @NotNull
    public Argument[] getArguments() {
        ArgumentList al = (ArgumentList) this.findChildByType(PLSqlTypesAdopted.ARGUMENT_LIST);
        return (al != null) ? al.getArguments() : new Argument[0];
    }

    public String getEName() {
        ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
        return node.getText();
    }


    public ObjectName getEObjectName() {
        ASTNode child = getNode().findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
        if (child == null) {
            throw new SyntaxTreeCorruptedException();
        }

        return (ObjectName) child.getPsi();
    }

    public Type getReturnType() {
        PsiElement type = this.findChildByType(PLSqlTypesAdopted.RETURN_TYPE);
        if (type != null) {
            return TypeFactory.createTypeByName(type.getText());
        } else {
            return TypeFactory.createTypeById(Type.UNKNOWN);
        }
    }

    @Override
    public PsiElement getReturnTypeElement() {
        PsiElement type = this.findChildByType(PLSqlTypesAdopted.RETURN_TYPE);
        if (type == null) {
            throw new SyntaxTreeCorruptedException();
        }
        return type;
    }

    public String getPackageName() {
        PsiElement parent = this.getParent();
        if (parent instanceof PackageBody) {
            return ((PackageBody) parent).getPackageName();
        } else if (parent instanceof PackageSpec) {
            return ((PackageSpec) parent).getPackageName();
        }
        return null;
    }

/*
    @Nullable
    public String getQuickNavigateInfo() {
        ArgumentList alist = getArgumentList();
        StringBuilder out = new StringBuilder();
        if (alist != null) {
            for (Argument a : alist.getArguments()) {
                if (out.length() > 0) {
                    out.append(", ");
                }
                out.append(a.getPresentableForm());
            }
        }
        return "[Function] "
                + getEName().toLowerCase()
                + ((out.length() > 0) ? " (" + out.toString().toLowerCase() + ") " : " ")
                + getReturnType().typeName();
    }
*/


    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitFunctionSpec(this);
        } else {
            super.accept(visitor);
        }
    }

    @Nullable
    public ItemPresentation getPresentation() {
        try {
            return new FunctionSpecPresentation();
        } catch(SyntaxTreeCorruptedException e){
            return null;
        }
    }

    private class FunctionSpecPresentation implements ItemPresentation {
        public String getPresentableText() {
            String ctxPath = FunctionSpecImpl.this.getCtxPath1().getPath();
            ContextPathUtil.CtxPathParser ctxParser = new ContextPathUtil.CtxPathParser(ctxPath);
            String funcName = ctxParser.lastCtxName();
            ContextPathUtil.CtxPathParser parentCtxParser = ctxParser.getParentCtxParser();
            if(parentCtxParser != null){
                switch(parentCtxParser.extractLastCtxType()){
                    case ContextPath.PACKAGE_BODY:
                    case ContextPath.PACKAGE_SPEC:
                        funcName = parentCtxParser.lastCtxName() + "." + funcName;
                        break;
                }
            }

            ArgumentList alist = getArgumentList();
            StringBuilder out = new StringBuilder();
            if (alist != null) {
                for (Argument a : alist.getArguments()) {
                    if (out.length() > 0) {
                        out.append(", ");
                    }
                    out.append(a.getPresentableForm());
                }
            }
            // todo -- truncate long string
            return "[Function] "
                    + funcName
                    + ((out.length() > 0) ? " (" + out.toString().toLowerCase() + ") " : " ")
                    + getReturnType().typeName();
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
