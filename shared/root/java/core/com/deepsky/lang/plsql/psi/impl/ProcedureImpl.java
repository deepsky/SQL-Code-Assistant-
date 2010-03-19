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

import com.deepsky.database.ora.desc.ProcedureDescriptorImpl;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.resolver.ResolveUtils;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.parser.ContextPath;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProcedureImpl extends PlSqlElementBase implements Procedure {

    public ProcedureImpl(ASTNode astNode) {
        super(astNode);
    }

    public ObjectName getObjectName(){
        ASTNode child = getNode().findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
        if(child == null){
            throw new SyntaxTreeCorruptedException();
        }

        return (ObjectName) child.getPsi();
    }
    
    public String getEName() {
        ASTNode child = getNode().findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
        return child.getText();
    }

    public void setEName(String name) {
        //To change body of implemented methods use File | Settings | File Templates.
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
        if(alist != null){
            for(Argument arg: alist.getArguments()){
                if(arg.getArgumentName().equalsIgnoreCase(parameterName)){
                    return arg;
                }
            }
        }
        return null;
    }

    @NotNull
    public Declaration[] getDeclarationList() {
        PlSqlBlock block = (PlSqlBlock)this.findChildByType(PLSqlTypesAdopted.PLSQL_BLOCK);
        if(block != null){
            return block.getDeclarations();
        }
//        DeclarationList alist = (DeclarationList) this.findChildByType(PLSqlTypesAdopted.DECLARE_LIST);
//        if (alist != null) {
//            return alist.getDeclList();
//        }
        return new Declaration[0];
    }

    public PlSqlBlock getBlock() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean createOrReplace() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ExecutableDescriptor describe() {

        PackageBody body = getParent() instanceof PackageBody? (PackageBody) getParent() : null;
        PackageDescriptor pdesc = null;

        String name = getEName();
        ProcedureDescriptorImpl fdesc;
        if (body != null) {
            pdesc = body.describe();
            fdesc = new ProcedureDescriptorImpl(pdesc, name);
        } else {
            fdesc = new ProcedureDescriptorImpl((SqlScriptLocator)null, name);
        }
        
        Argument[] args = getArguments();
        for (Argument a : args) {
            Type t = a.getType();
            if(t.typeId() == Type.USER_DEFINED){
                UserDefinedType udt = (UserDefinedType) t;
                if(udt.getDefinitionPackage() == null && pdesc != null){
                    // complete the FQN of the UDT
                    udt.setDefinitionPackage(pdesc.getName());
                }
            }

            fdesc.addParameter(t, a.getArgumentName(), a.getDefaultExpr() != null);
        }

        return fdesc;
    }


    public String getPackageName(){
        PackageBody pkg = this.getParent() instanceof PackageBody? (PackageBody) this.getParent() : null;
        return pkg!=null? pkg.getPackageName(): null;
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
    public String getQuickNavigateInfo() {
        StringBuilder out = new StringBuilder();
        for (Argument a : getArguments()) {
            if (out.length() > 0) {
                out.append(", ");
            }
            out.append(a.getPresentableForm());
        }

        return "[Procedure] "
                + getEName().toLowerCase()
                + ((out.length() > 0) ? " (" + out.toString().toLowerCase() + ") " : " ");
    }

    // [Contex Management Stuff] Start -------------------------------
    CtxPath cachedCtxPath = null;
    public CtxPath getCtxPath() {
        if(cachedCtxPath != null){
            return cachedCtxPath;
        } else {
            CtxPath parent = super.getCtxPath();
            cachedCtxPath = new CtxPathImpl(
                    parent.getPath() + ResolveUtils.encodeCtx(ContextPath.PROCEDURE_BODY, parent.getSeqNEXT() + "$"
                    + this.getEName().toLowerCase()));
        }
        return cachedCtxPath;
    }
    // [Contex Management Stuff] End ---------------------------------

}
