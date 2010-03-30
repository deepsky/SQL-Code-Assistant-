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

import com.deepsky.database.ora.desc.FunctionDescriptorImpl;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.view.Icons;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class FunctionImpl extends PlSqlElementBase implements Function {

    static final LoggerProxy log = LoggerProxy.getInstance("#FunctionImpl");

    public FunctionImpl(ASTNode astNode) {
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
        if(alist != null){
            return alist.getArguments();
        } else {
            return new Argument[0];
        }
    }

    @Nullable
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

    public Type getReturnType() {
        PsiElement type = this.findChildByType(PLSqlTypesAdopted.RETURN_TYPE);
        if(type != null){
            return TypeFactory.createTypeByName(type.getText());
        } else {
            throw new SyntaxTreeCorruptedException();
//            return TypeFactory.createTypeById(Type.UNKNOWN);
        }
    }

    @NotNull
    public Declaration[] getDeclarationList() {
        PlSqlBlock block = (PlSqlBlock)this.findChildByType(PLSqlTypesAdopted.PLSQL_BLOCK);
        if(block != null){
            return block.getDeclarations();
        }
//        DeclarationList alist = (DeclarationList) this.findChildByType(PLSqlTypesAdopted.DECLARE_LIST);
//        if(alist != null){
//            return alist.getDeclList();
//        }
        return new Declaration[0];
    }

    public PlSqlBlock getBlock() {
        return null;
    }

    public boolean createOrReplace() {
        return false;
    }

    public ExecutableDescriptor describe() {

        PackageBody body = getParent() instanceof PackageBody? (PackageBody) getParent() : null;
        PackageDescriptor pdesc = null;
        FunctionDescriptorImpl fd = null;
        if(body != null){
            pdesc = body.describe();
            fd = new FunctionDescriptorImpl(pdesc, getEName(), getReturnType(), false);
        } else {
            fd = new FunctionDescriptorImpl((SqlScriptLocator)null, getEName(), getReturnType(), false);
        }


        for (Argument a : getArguments()) {
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
    }


    public String getPackageName(){
        PackageBody pkg = this.getParent() instanceof PackageBody? (PackageBody) this.getParent() : null;
        return pkg!=null? pkg.getPackageName(): null;
    }

    public boolean equals(ExecutableDescriptor edesc) {
        if(!(edesc instanceof FunctionDescriptor)){
            return false;
        }

        FunctionDescriptor fdesc = (FunctionDescriptor) edesc;
        if(edesc.getName().equalsIgnoreCase(getEName())){
            String[] names = edesc.getArgumentNames();

            // check out arguments
            Argument[] args = getArguments();
            if(args.length == names.length){
                for(int i=0; i<names.length; i++){
                   if(!args[i].getArgumentName().equalsIgnoreCase(names[i]) ||
                       args[i].getType().typeId() != edesc.getArgumentType(names[i]).typeId()){
                       return false;
                   }
                }
            } else {
                return false;
            }

            // check return type
            if( getReturnType().typeId() != fdesc.getReturnType().typeId()){
                return false;
            }
            return true;
        }
        return false;
    }

    public void subtreeChanged(){
        log.info("[subtreeChanged] FunctionBody: " + getEName() );
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
      if (visitor instanceof PlSqlElementVisitor) {
        ((PlSqlElementVisitor)visitor).visitFunction(this);
      }
      else {
        super.accept(visitor);
      }
    }

    @Nullable
    public String getQuickNavigateInfo(){
        StringBuilder out = new StringBuilder();
        for(Argument a: getArguments()){
            if(out.length() > 0){
                out.append(", ");
            }
            out.append(a.getPresentableForm());
        }
        return "[Function] "
                + getEName().toLowerCase()
                + ((out.length()>0)? " (" + out.toString().toLowerCase() + ") ": " ")
                + getReturnType().typeName();
    }

}
