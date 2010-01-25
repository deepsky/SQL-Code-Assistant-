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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.lang.plsql.struct.impl;

import com.deepsky.database.ora.desc.*;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PackageBodyImpl extends PsiElementDumb implements PackageBody {

    static LoggerProxy log = LoggerProxy.getInstance("#PackageBodyImpl");

    String packageName;
    List<PlSqlElement> specs = new ArrayList<PlSqlElement>();

    public String getPackageName() {
        int idx = packageName.lastIndexOf(".");
        if(idx == -1){
            return packageName;
        } else {
            return packageName.substring(idx+1, packageName.length());
        }
    }

    public Executable[] findExecutableByName(String name) {
        // todo
        return new Executable[0];
    }

    public ExecutableSpec[] findExecutableSpecByName(String name) {
        // todo
        return new ExecutableSpec[0];
    }

    @NotNull
    public PackageBodyDescriptor describe() {
        PackageBodyDescriptorImpl pkgDesc = new PackageBodyDescriptorImpl(getPackageName());

        // todo -- lastDDL should be set on the merge stage
        //pkgDesc.setLastDDLTime(lastDDL);

        for (PlSqlElement e : specs) {
            if (e instanceof FunctionSpec) {
                // todo -- FunctionSpec.describe()
                FunctionSpec f = (FunctionSpec) e;
                FunctionDescriptorImpl fd = new FunctionDescriptorImpl(pkgDesc, f.getEName(), f.getReturnType(), false);
                for (Argument a : f.getArgumentList().getArguments()) {
                    Type t = a.getType();
                    if(t.typeId() == Type.USER_DEFINED){
                        UserDefinedType udt = (UserDefinedType) t;
                        if(udt.getDefinitionPackage() == null){
                            // complete the FQN of the UDT
                            udt.setDefinitionPackage(packageName);
                        }
                    }
                    fd.addParameter(
                            t,
                            a.getArgumentName(),
                            a.getDefaultExpr() != null
                    );
                }

                pkgDesc.add(fd);
            } else if (e instanceof Function) {
                // todo -- Function.describe()
                Function f = (Function) e;
                FunctionDescriptorImpl fd = new FunctionDescriptorImpl(pkgDesc, f.getEName(), f.getReturnType(), false);
                for (Argument a : f.getArguments()) {
                    Type t = a.getType();
                    if(t.typeId() == Type.USER_DEFINED){
                        UserDefinedType udt = (UserDefinedType) t;
                        if(udt.getDefinitionPackage() == null){
                            // complete the FQN of the UDT
                            udt.setDefinitionPackage(packageName);
                        }
                    }

                    fd.addParameter(
                            t,
                            a.getArgumentName(),
                            a.getDefaultExpr() != null
                    );
                }

                pkgDesc.add(fd);
            } else if (e instanceof ProcedureSpec) {
                // todo -- ProcedureSpec.describe()
                ProcedureSpec p = (ProcedureSpec) e;
                ProcedureDescriptorImpl fd = new ProcedureDescriptorImpl(pkgDesc, p.getEName());
                for (Argument a : p.getArgumentList().getArguments()) {
                    Type t = a.getType();
                    if(t.typeId() == Type.USER_DEFINED){
                        UserDefinedType udt = (UserDefinedType) t;
                        if(udt.getDefinitionPackage() == null){
                            // complete the FQN of the UDT
                            udt.setDefinitionPackage(packageName);
                        }
                    }
                    fd.addParameter(
                            t,
                            a.getArgumentName(),
                            a.getDefaultExpr() != null
                    );
                }

                pkgDesc.add(fd);
            } else if (e instanceof Procedure) {
                Procedure p = (Procedure) e;
                ProcedureDescriptorImpl fd = new ProcedureDescriptorImpl(pkgDesc, p.getEName());
                for (Argument a : p.getArguments()) {
                    Type t = a.getType();
                    if(t.typeId() == Type.USER_DEFINED){
                        UserDefinedType udt = (UserDefinedType) t;
                        if(udt.getDefinitionPackage() == null){
                            // complete the FQN of the UDT
                            udt.setDefinitionPackage(packageName);
                        }
                    }
                    fd.addParameter(
                            t,
                            a.getArgumentName(),
                            a.getDefaultExpr() != null
                    );
                }
                pkgDesc.add(fd);
            } else if (e instanceof VariableDecl) {
                VariableDecl var = (VariableDecl) e;
                VariableDescriptorImpl vardesc = new VariableDescriptorImpl(
                        pkgDesc,
                        var.getDeclName(),
                        var.getType(),
                        var.isConstant()
                );
                pkgDesc.add(vardesc);
            } else if (e instanceof RecordTypeDecl) {
                RecordTypeDecl rtypedecl = (RecordTypeDecl) e;
                RecordTypeDescriptorImpl rtype = new RecordTypeDescriptorImpl(pkgDesc, rtypedecl.getDeclName());
                for(RecordTypeItem item: rtypedecl.getItems()){
                    rtype.addRecordItem(item.getRecordItemName(), item.getType());
                    // TODO - requires resolving of the real type in case of %TYPE and %ROWTYPE
                    // ...
                }

                pkgDesc.add(rtype);
            } else if (e instanceof TableCollectionDecl) {
                TableCollectionDecl coll = (TableCollectionDecl) e;
                TableCollectionDescriptor tdesc = new TableCollectionDescriptorImpl(pkgDesc, coll.getDeclName(), coll.getBaseType());

                pkgDesc.add(tdesc);
            } else if (e instanceof VarrayCollectionDecl){
                VarrayCollectionDecl tdecl = (VarrayCollectionDecl)e;
                VarrayCollectionDescriptor tcoll = new VarrayCollectionDescriptorImpl(
                        pkgDesc, tdecl.getDeclName().toUpperCase(), tdecl.getBaseType());

                pkgDesc.add(tcoll);
            } else if (e instanceof RefCursorDecl) {
                RefCursorDecl cursor = (RefCursorDecl) e;
                RefCursorTypeDescriptorImpl refcur = new RefCursorTypeDescriptorImpl(
                        pkgDesc,
                        cursor.getDeclName()
                );
                pkgDesc.add(refcur);
            } else {
                // todo
                log.warn("Type " +e.getClass().getSimpleName() + " will not be loaded");
            }
        }

        return pkgDesc;
    }

    @NotNull
    public PlSqlElement[] findObjectByName(String name) {
        // todo ---
        return new PlSqlElement[0];
    }

    @NotNull
    public PlSqlElement[] getObjects() {
        return specs.toArray(new PlSqlElement[0]);
    }

    public PackageSpec getPackageSpecification() {
        // todo ---
        return null;
    }

    public Executable findExecutableByDecl(ExecutableSpec spec) {
        // todo ---
        return null;
    }

    public void setName(String name) {
        this.packageName = name;
    }

    public void setObjectSpec(List<PlSqlElement> specs) {
        this.specs = specs;
    }
}
