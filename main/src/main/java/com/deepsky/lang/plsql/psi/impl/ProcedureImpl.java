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

import com.deepsky.lang.common.ResolveProvider;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.names.ObjectName;
import com.deepsky.lang.plsql.psi.utils.PlSqlUtil;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.ExecutableDescriptor;
import com.deepsky.lang.plsql.struct.ProcedureDescriptor;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ProcedureImpl extends PlSqlElementBase implements Procedure {

    public ProcedureImpl(ASTNode astNode) {
        super(astNode);
    }

    public ObjectName getEObjectName() {
        ASTNode child = getNode().findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
        if (child == null) {
            throw new SyntaxTreeCorruptedException();
        }

        return (ObjectName) child.getPsi();
    }

    public String getEName() {
        ASTNode child = getNode().findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
        if (child == null) {
            throw new SyntaxTreeCorruptedException();
        }
        return child.getText();
    }

    public void setEName(String name) {
        // TODO - implement me
    }

    @NotNull
    public Argument[] getArguments() {
        ArgumentList alist = (ArgumentList) this.findChildByType(PLSqlTypesAdopted.ARGUMENT_LIST);
        if (alist != null) {
            return alist.getArguments();
        } else {
            return new Argument[0];
        }
    }

    public ArgumentList getArgumentList() {
        return (ArgumentList) this.findChildByType(PLSqlTypesAdopted.ARGUMENT_LIST);
    }

    public Argument getArgumentByName(String parameterName) {
        ArgumentList alist = (ArgumentList) this.findChildByType(PLSqlTypesAdopted.ARGUMENT_LIST);
        if (alist != null) {
            for (Argument arg : alist.getArguments()) {
                if (arg.getArgumentName().equalsIgnoreCase(parameterName)) {
                    return arg;
                }
            }
        }
        return null;
    }

    @NotNull
    public String getObjectType() {
        return "PROCEDURE";
    }

    @NotNull
    public String getObjectName() {
        ASTNode child = getNode().findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
        if(child == null){
            throw new SyntaxTreeCorruptedException();
        }
        return child.getText().toUpperCase();
    }

    @NotNull
    public String getCreateQuery() {
        return PlSqlUtil.completeCreateScript(this);
    }

    @NotNull
    public Declaration[] getDeclarationList() {
        PlSqlBlock block = (PlSqlBlock) this.findChildByType(PLSqlTypesAdopted.PLSQL_BLOCK);
        if (block != null) {
            return block.getDeclarations();
        }
        return new Declaration[0];
    }

    @NotNull
    public PlSqlBlock getBlock() {
        ASTNode blk = this.getNode().findChildByType(PlSqlElementTypes.PLSQL_BLOCK);
        if(blk == null){
            throw new SyntaxTreeCorruptedException();
        }
        return (PlSqlBlock) blk.getPsi();
    }

    public String getPackageName() {
        PackageBody pkg = this.getParent() instanceof PackageBody ? (PackageBody) this.getParent() : null;
        return pkg != null ? pkg.getPackageName() : null;
    }

    public ExecutableSpec getSpecification() {
        ResolveFacade facade = ((ResolveProvider) getContainingFile()).getResolver();
        return facade.findSpecificationFor(this);
    }

    public boolean equals(ExecutableDescriptor edesc) {
        if (!(edesc instanceof ProcedureDescriptor)) {
            return false;
        }

        if (edesc.getName().equalsIgnoreCase(getEName())) {
            String[] names = edesc.getArgumentNames();

            // check out arguments
            Argument[] args = getArguments();
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

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitProcedure(this);
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
            String ctxPath = ProcedureImpl.this.getCtxPath1().getPath();
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
