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

package com.deepsky.lang.plsql.psi.resolve.impl;

import com.deepsky.database.SqlScriptManager;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper3;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.struct.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PackageContext implements ResolveContext777 {
    PackageDescriptor pdesc;
    Project project;

    public PackageContext(PackageDescriptor pdesc, Project project) {
        this.pdesc = pdesc;
        this.project = project;
    }

    @NotNull
    public VariantsProcessor777 create(int narrow_type) {
        return new PackageItemVariantsProcessor777(narrow_type);
    }

    public PsiElement getDeclaration() {
        return SqlScriptManager.mapToPsiTree(project, pdesc);
    }

    public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {
        String text = elem.getText();

        DbObject[] objects = pdesc.findObjectByName(text);

        List<ResolveContext777> out = new ArrayList<ResolveContext777>();
        for (DbObject dbo : objects) {
            if(dbo instanceof UserDefinedTypeDescriptor){
                out.add(
                    UserDefinedTypeHelper.createResolveContext(project, (UserDefinedTypeDescriptor) dbo)
                );
//            }
//            if (dbo instanceof RecordTypeDescriptor) {
//                out.add(new RecordTypeContext(project, (RecordTypeDescriptor) dbo));
//            } else if (dbo instanceof TableCollectionDescriptor) {
//                out.add(new TableCollectionTypeContext(project, (TableCollectionDescriptor) dbo));
            } else if (dbo instanceof VariableDescriptor) {
                VariableDescriptor vdesc = (VariableDescriptor) dbo;
                out.add(new VariableContext(vdesc, project));
            } else if (dbo instanceof ExecutableDescriptor) {
                // validate argument list
                if (elem.getContext() instanceof CallableCompositeName) {
                    // executable with arguments
                    Callable exec = (Callable) ((CallableCompositeName) elem.getContext()).getContext();
                    List<String> errors = new ArrayList<String>();
                    if (dbo instanceof ProcedureDescriptor && exec instanceof ProcedureCall) {
                        CallArgument[] callArgs = exec.getCallArgumentList();
                        if (ResolveHelper3.validateCallArgumentList((ExecutableDescriptor) dbo, callArgs, errors)) {
                            out.add(new ExecutableContext((ExecutableDescriptor) dbo, project));
                        }
                    } else if (dbo instanceof FunctionDescriptor && exec instanceof FunctionCall) {
                        CallArgument[] callArgs = exec.getCallArgumentList();
                        if (ResolveHelper3.validateCallArgumentList((ExecutableDescriptor) dbo, callArgs, errors)) {
                            out.add(new ExecutableContext((ExecutableDescriptor) dbo, project));
                        }
                    }
                } else if (elem.getContext() instanceof ObjectReference) {
                    // executable without arguments
                    out.add(new ExecutableContext((ExecutableDescriptor) dbo, project));
                } else {
                    throw new NameNotResolvedException("Type " + dbo.getClass() + " not supported");
                }
            }
        }
        
        if (out.size() == 1) {
            return out.get(0);
        } else {
            throw new NameNotResolvedException((PlSqlElement) elem, "Package has no name: " + elem.getText());
        }
    }

    public Type getType() throws NameNotResolvedException {
        throw new NameNotResolvedException("");
    }

    class PackageItemVariantsProcessor777 implements VariantsProcessor777 {

        int narrow_type;
        public PackageItemVariantsProcessor777(int narrow_type){
            this.narrow_type = narrow_type;
        }

        public String[] getVariants(String prefix) {

            Set<String> out = new HashSet<String>();
            BitMask mask = new BitMask(narrow_type);
            for(DbObject dbo: pdesc.getObjects()){
                if(//mask.member(DbObjectHelper.dbo2typecode(dbo)) &&
                        (prefix.length() == 0 || dbo.getName().toUpperCase().startsWith(prefix.toUpperCase()))){
                    // todo - consider to return PsiElement instead of an object's name 
                    out.add(dbo.getName());
                }
            }

            return out.toArray(new String[out.size()]);
        }
    }

    class BitMask {
        int mask;
        public BitMask(int mask){
            this.mask = mask;
        }
        public boolean member(int _mask){
            return (mask & _mask) != 0;
        }
    }

    
}
