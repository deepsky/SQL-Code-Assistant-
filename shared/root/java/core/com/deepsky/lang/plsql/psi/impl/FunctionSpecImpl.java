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
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.view.Icons;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
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
        return (al != null)? al.getArguments(): new Argument[0];
    }

    public String getEName() {
        ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
        return node.getText();
    }


    public ObjectName getObjectName() {
        ASTNode child = getNode().findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
        if(child == null){
            throw new SyntaxTreeCorruptedException();
        }

        return (ObjectName) child.getPsi();
    }

    public Type getReturnType() {
        PsiElement type = this.findChildByType(PLSqlTypesAdopted.RETURN_TYPE);
        if(type != null){
            return TypeFactory.createTypeByName(type.getText());
        } else {
            return TypeFactory.createTypeById(Type.UNKNOWN);
        }
    }

    public boolean equals2(ExecutableDescriptor edesc) {
        if(!(edesc instanceof FunctionDescriptor)){
            return false;
        }

        FunctionDescriptor fdesc = (FunctionDescriptor) edesc;
        if(edesc.getName().equalsIgnoreCase(getEName())){
            String[] names = edesc.getArgumentNames();

            // check out arguments
            ArgumentList lst = getArgumentList();
            Argument[] args = (lst == null)? new Argument[0]: lst.getArguments();
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

    public ExecutableDescriptor describe() {
        PlSqlElement context = getUsageContext(TokenSet.create(
                PlSqlElementTypes.PACKAGE_BODY, PlSqlElementTypes.PACKAGE_SPEC)
        );

        PackageDescriptor pdesc = null;
        FunctionDescriptorImpl fd;
        if (context instanceof PackageBody) {
            pdesc = ((PackageBody)context).describe();
            fd = new FunctionDescriptorImpl(pdesc, getEName().toUpperCase(), getReturnType(), false);
        } else if (context instanceof PackageSpec) {
            pdesc = ((PackageSpec)context).describe();
            fd = new FunctionDescriptorImpl(pdesc, getEName().toUpperCase(), getReturnType(), false);
        } else {
            String url = getContainingFile().getVirtualFile().getUrl();
            fd = new FunctionDescriptorImpl(
                    new FileBasedContextUrl(url),
                    getEName().toUpperCase(),
                    getReturnType(), false
            );
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
    }


    public String getPackageName(){
        PsiElement parent = this.getParent();
        if(parent instanceof PackageBody){
            return ((PackageBody)parent).getPackageName();
        } else if(parent instanceof PackageSpec){
            return ((PackageSpec)parent).getPackageName();
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
        return "[Function] "
                + getEName().toLowerCase()
                + ((out.length()>0)? " (" + out.toString().toLowerCase() + ") ": " ")
                + getReturnType().typeName();
    }


    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitFunctionSpec(this);
        } else {
            super.accept(visitor);
        }
    }

}
