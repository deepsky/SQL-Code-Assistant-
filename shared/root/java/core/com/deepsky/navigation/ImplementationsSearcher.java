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

package com.deepsky.navigation;

import com.deepsky.database.SqlScriptManager;
import com.deepsky.database.fs.CachedVirtualFileSystem;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.struct.*;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.util.Processor;
import com.intellij.util.QueryExecutor;

public class ImplementationsSearcher implements QueryExecutor<PsiElement, PsiElement> {

    // todo -- at the moment it is supported Go To Impl for objects in the Database scope only!
    public boolean execute(final PsiElement sourceElement, final Processor<PsiElement> consumer) {
        if (sourceElement instanceof ObjectName) {
            if(sourceElement.getParent() instanceof ExecutableSpec && dbScopeOriginated(sourceElement)){
                ExecutableSpec spec = (ExecutableSpec) sourceElement.getParent();
                String packageName = spec.getPackageName();
                if(packageName != null){
                    PackageBodyDescriptor desc = ResolveHelper.resolve_PackageBody(sourceElement.getProject(), packageName);
                    if(desc != null){
                        DbObject[] dbos = desc.findObjectByName(spec.getEName());
                        for(DbObject dbo: dbos){
                            if(dbo instanceof FunctionDescriptor && spec instanceof FunctionSpec){
                                ExecutableDescriptor edesc = (ExecutableDescriptor) dbo;
                                ExecutableDescriptor edesc1 = spec.describe();
                                if (edesc.signatureEquals(edesc1)) {
                                    PlSqlElement e = SqlScriptManager.mapToPsiTree(sourceElement.getProject(), edesc);
                                    if(e instanceof ObjectName){ //Function){
//                                        consumer.process(((Function) e).getObjectName());
                                        consumer.process(e);
                                    }
//                                    consumer.process(sourceElement);
//                                    consumer.process(spec);
                                }
                            } else if(dbo instanceof ProcedureDescriptor && spec instanceof ProcedureSpec){
                                ExecutableDescriptor edesc = (ExecutableDescriptor) dbo;
                                ExecutableDescriptor edesc1 = spec.describe();
                                if (edesc.signatureEquals(edesc1)) {
                                    PlSqlElement e = SqlScriptManager.mapToPsiTree(sourceElement.getProject(), edesc);
                                    if(e instanceof ObjectName){ //Procedure){
//                                        consumer.process(((Procedure) e).getObjectName());
                                        consumer.process(e);
                                    }
//                                    consumer.process(sourceElement);
//                                    consumer.process(spec);
                                }
                            }
                        }
                    }
                }
            }
            return true;
//        } else if (sourceElement instanceof ExecutableSpec) {
//            ExecutableSpec espec = (ExecutableSpec) sourceElement;
//            String packageName = espec.getPackageName();
//            if(packageName != null){
//                PackageBodyDescriptor desc = ResolveHelper.resolve_PackageBody(packageName);
//                if(desc != null){
//                    DbObject[] dbos = desc.findObjectByName(espec.getEName());
//                    for(DbObject dbo: dbos){
//                        if(dbo instanceof FunctionDescriptor && espec instanceof FunctionSpec){
//                            ExecutableDescriptor edesc = (ExecutableDescriptor) dbo;
//                            ExecutableDescriptor edesc1 = espec.describe();
//                            if (edesc.signatureEquals(edesc1)) {
//                                PlSqlElement e = SqlScriptManager.mapToPsiTree(sourceElement.getProject(), edesc);
//                                if(e instanceof ObjectName){ //Function){
//                                    consumer.process(e);
////                                    consumer.process(((Function) e).getObjectName());
//                                }
////                                    consumer.process(spec);
//                            }
//                        } else if(dbo instanceof ProcedureDescriptor && espec instanceof ProcedureSpec){
//                            ExecutableDescriptor edesc = (ExecutableDescriptor) dbo;
//                            ExecutableDescriptor edesc1 = espec.describe();
//                            if (edesc.signatureEquals(edesc1)) {
//                                PlSqlElement e = SqlScriptManager.mapToPsiTree(sourceElement.getProject(), edesc);
//                                if(e instanceof ObjectName){ //Procedure){
//                                    consumer.process(e);
////                                    consumer.process(((Procedure) e).getObjectName());
//                                }
////                                consumer.process(sourceElement);
//                            }
//                        }
//                    }
//                }
//            }
//            return true;
        }
        return false;
    }

    private boolean dbScopeOriginated(PsiElement elem) {
        VirtualFile vf = elem.getContainingFile().getVirtualFile();
        if(vf != null){
            if(vf.getFileSystem().getProtocol().equals(CachedVirtualFileSystem.PROTOCOL)){
                return true;
            }
        }
        return false;
    }


}
