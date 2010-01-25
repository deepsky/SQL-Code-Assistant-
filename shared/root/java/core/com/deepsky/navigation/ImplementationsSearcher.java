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

package com.deepsky.navigation;

import com.deepsky.lang.plsql.psi.ExecutableSpec;
import com.deepsky.lang.plsql.psi.FunctionSpec;
import com.deepsky.lang.plsql.psi.ObjectName;
import com.deepsky.lang.plsql.psi.ProcedureSpec;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.struct.*;
import com.intellij.psi.PsiElement;
import com.intellij.util.Processor;
import com.intellij.util.QueryExecutor;

public class ImplementationsSearcher implements QueryExecutor<PsiElement, PsiElement> {

    public boolean execute(final PsiElement sourceElement, final Processor<PsiElement> consumer) {
        if (sourceElement instanceof ObjectName) {
            if(sourceElement.getParent() instanceof ExecutableSpec){
                ExecutableSpec spec = (ExecutableSpec) sourceElement.getParent();
                String packageName = spec.getPackageName();
                if(packageName != null){
                    PackageBodyDescriptor desc = ResolveHelper.resolve_PackageBody(packageName);
                    if(desc != null){
                        DbObject[] dbos = desc.findObjectByName(spec.getEName());
                        for(DbObject dbo: dbos){
                            if(dbo instanceof FunctionDescriptor && spec instanceof FunctionSpec){
                                ExecutableDescriptor edesc = (ExecutableDescriptor) dbo;
                                ExecutableDescriptor edesc1 = spec.describe();
                                if (edesc.signatureEquals(edesc1)) {
                                    consumer.process(sourceElement);
//                                    consumer.process(spec);
                                }
                            } else if(dbo instanceof ProcedureDescriptor && spec instanceof ProcedureSpec){
                                ExecutableDescriptor edesc = (ExecutableDescriptor) dbo;
                                ExecutableDescriptor edesc1 = spec.describe();
                                if (edesc.signatureEquals(edesc1)) {
                                    consumer.process(sourceElement);
//                                    consumer.process(spec);
                                }
                            }
                        }
                    }
                }
            }
            return true;
        } else if (sourceElement instanceof ExecutableSpec) {
            ExecutableSpec espec = (ExecutableSpec) sourceElement;
            int hh = 0;
            return false;
        }
        return true;
    }


//    private void processSpec(ExecutableSpec spec, final Processor<PsiElement> consumer){
//        String packageName = spec.getPackageName();
//        if(packageName != null){
//            PackageBodyDescriptor desc = ResolveHelper.resolve_PackageBody(packageName);
//            if(desc != null){
//                DbObject[] dbos = desc.findObjectByName(spec.getEName());
//                for(DbObject dbo: dbos){
//                    if(dbo instanceof FunctionDescriptor && spec instanceof FunctionSpec){
//                        ExecutableDescriptor edesc = (ExecutableDescriptor) dbo;
//                        ExecutableDescriptor edesc1 = spec.describe();
//                        if (edesc.signatureEquals(edesc1)) {
//                            consumer.process(sourceElement);
//                        }
//                    } else if(dbo instanceof ProcedureDescriptor && spec instanceof ProcedureSpec){
//                        ExecutableDescriptor edesc = (ExecutableDescriptor) dbo;
//                        ExecutableDescriptor edesc1 = spec.describe();
//                        if (edesc.signatureEquals(edesc1)) {
//                            consumer.process(sourceElement);
//                        }
//                    }
//                }
//            }
//        }
//    }
}
