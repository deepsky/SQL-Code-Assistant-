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
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.ExecutableDescriptor;
import com.deepsky.lang.plsql.struct.ProcedureDescriptor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

/*
    public boolean equals2(ExecutableDescriptor edesc) {
        if (!(edesc instanceof ProcedureDescriptor)) {
            return false;
        }

        if (edesc.getName().equalsIgnoreCase(getEName())) {
            String[] names = edesc.getArgumentNames();

            // check out arguments
            ArgumentList lst = getArgumentList();
            Argument[] args = lst != null ? lst.getArguments() : new Argument[0]; //getArgumentList();
            if (args.length == names.length) {
                for (int i = 0; i < names.length; i++) {
                    if (!args[i].getArgumentName().equalsIgnoreCase(names[i]) ||
                            args[i].getType().typeId() != edesc.getArgumentType(names[i]).typeId()) {
                        return false;
                    }
                }
            } else {
                return false;
            }

            return true;
        }
        return false;
    }
*/

    public ExecutableDescriptor describe() {
        // todo -- resolve stuff refactoring
        throw new NotSupportedException();

/*
        PlSqlElement context = getUsageContext(TokenSet.create(
                PlSqlElementTypes.PACKAGE_BODY, PlSqlElementTypes.PACKAGE_SPEC)
        );

        PackageDescriptor pdesc = null;
        ProcedureDescriptorImpl fd;
        if (context instanceof PackageBody) {
            pdesc = ((PackageBody)context).describe();
            fd = new ProcedureDescriptorImpl(pdesc, getEName().toUpperCase());
        } else if (context instanceof PackageSpec) {
            pdesc = ((PackageSpec)context).describe();
            fd = new ProcedureDescriptorImpl(pdesc, getEName().toUpperCase());
        } else {
            String url = getContainingFile().getVirtualFile().getUrl();
            fd = new ProcedureDescriptorImpl(
                    new FileBasedContextUrl(url),
                    getEName().toUpperCase());
        }

        for (Argument a : getArgumentList().getArguments()) {
            Type t = a.getType();
            if(t.typeId() == Type.USER_DEFINED){
                UserDefinedType udt = (UserDefinedType) t;
                if(udt.getDefinitionPackage() == null && pdesc != null){
                    // complete the FQN of the UDT
                    udt.setDefinitionPackage(pdesc.getName());
                }
            }
            fd.addParameter(
                    t,
                    a.getArgumentName(),
                    a.getDefaultExpr() != null
            );
        }

        return fd;
*/
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

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitProcedureSpec(this);
        } else {
            super.accept(visitor);
        }
    }

}
