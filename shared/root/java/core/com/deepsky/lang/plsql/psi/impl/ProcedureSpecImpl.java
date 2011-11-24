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
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ProcedureSpecImpl extends PlSqlElementBase implements ProcedureSpec {
    public ProcedureSpecImpl(ASTNode astNode) {
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
        return "[Procedure] "
                + getEName().toLowerCase()
                + ((out.length() > 0) ? " (" + out.toString().toLowerCase() + ") " : " ");
    }
*/

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitProcedureSpec(this);
        } else {
            super.accept(visitor);
        }
    }

    @Nullable
    public ItemPresentation getPresentation() {
        try {
            return new ProcSpecPresentation();
        } catch(SyntaxTreeCorruptedException e){
            return null;
        }
    }

    private class ProcSpecPresentation implements ItemPresentation {
        public String getPresentableText() {
            String ctxPath = ProcedureSpecImpl.this.getCtxPath1().getPath();
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
            return "[Procedure] "
                    + funcName
                    + ((out.length() > 0) ? " (" + out.toString().toLowerCase() + ") " : " ");
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
