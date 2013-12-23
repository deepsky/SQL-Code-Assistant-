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

package com.deepsky.lang.plsql.psi.impl.names;

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.impl.PlSqlElementBase;
import com.deepsky.lang.plsql.psi.names.ObjectName;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.navigation.PlSqlPackageUtil;
import com.deepsky.view.Icons;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ObjectNameImpl extends PlSqlElementBase implements ObjectName {

    public ObjectNameImpl(ASTNode astNode) {
        super(astNode);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitObjectName(this);
        } else {
            super.accept(visitor);
        }
    }

    public String getObjectName() {
        return this.getText();
    }

    public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
        return this;
    }

    public String getName() {
        return getText();
    }

/*
    @Nullable
    public String getQuickNavigateInfo() {
        PsiElement parent = getParent();
        if (parent instanceof ExecutableSpec) {
            return ((ExecutableSpec) parent).getQuickNavigateInfo();
        } else if (parent instanceof Executable) {
            return ((Executable) parent).getQuickNavigateInfo();
        }
        return null;
    }
*/


    // presentation stuff
    public Icon getIcon(int flags) {
        PsiElement parent = getParent();
        if (parent instanceof FunctionSpec) {
            return Icons.FUNCTION_SPEC;
        } else if (parent instanceof Function) {
            return Icons.FUNCTION_BODY;
        } else if (parent instanceof ProcedureSpec) {
            return Icons.PROCEDURE_SPEC;
        } else if (parent instanceof Procedure) {
            return Icons.PROCEDURE_BODY;
        }
        return null;
    }

    @Nullable
    public ItemPresentation getPresentation() {
        return new TablePresentation();
    }

    public FileStatus getFileStatus() {
        return null;
    }

    class TablePresentation implements ItemPresentation {
        public String getPresentableText() {
            PsiElement parent = getParent();
            String text = getText();
            if (parent instanceof FunctionSpec) {
                text = Formatter.formatSignature((FunctionSpec) parent, 40);
            } else if (parent instanceof Function) {
                text = Formatter.formatSignature((Function) parent, 40);
            } else if (parent instanceof ProcedureSpec) {
                text = Formatter.formatSignature((ProcedureSpec) parent, 40);
            } else if (parent instanceof Procedure) {
                text = Formatter.formatSignature((Procedure) parent, 40);
            }

            return text;
        }

        @Nullable
        public String getLocationString() {
            PsiElement parent = getParent();
            String pkgName = PlSqlPackageUtil.findPackageNameForElement(parent);
            if (parent instanceof FunctionSpec) {
                return pkgName != null ? ("in " + pkgName + " (Function Spec)") : "(Function Spec)";
            } else if (parent instanceof Function) {
                return pkgName != null ? ("in " + pkgName + " (Function Body)") : "(Function Body)";
            } else if (parent instanceof ProcedureSpec) {
                return pkgName != null ? ("in " + pkgName + " (Procedure Spec)") : "(Procedure Spec)";
            } else if (parent instanceof Procedure) {
                return pkgName != null ? ("in " + pkgName + " (Procedure Body)") : "(Procedure Body)";
            }
            return null;
        }

        @Nullable
        public Icon getIcon(boolean open) {
            PsiElement parent = getParent();
            if (parent instanceof FunctionSpec) {
                return Icons.FUNCTION_SPEC;
            } else if (parent instanceof Function) {
                return Icons.FUNCTION_BODY;
            } else if (parent instanceof ProcedureSpec) {
                return Icons.PROCEDURE_SPEC;
            } else if (parent instanceof Procedure) {
                return Icons.PROCEDURE_BODY;
            }
            return null;
        }

        @Nullable
        public TextAttributesKey getTextAttributesKey() {
            return null;
        }
    }
}
