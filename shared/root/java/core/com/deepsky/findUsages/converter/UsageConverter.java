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

package com.deepsky.findUsages.converter;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.intellij.openapi.actionSystem.TypeSafeDataProvider;
import com.intellij.psi.PsiElement;
import com.intellij.usages.RenameableUsage;
import com.intellij.usages.Usage;
import com.intellij.usages.UsageInfo2UsageAdapter;
import com.intellij.usages.rules.MergeableUsage;
import com.intellij.usages.rules.PsiElementUsage;
import com.intellij.usages.rules.UsageInLibrary;
import com.intellij.usages.rules.UsageInModule;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class UsageConverter implements com.intellij.usages.UsageConvertor {
    @NotNull
    public Usage convert(@NotNull Usage usage) {
/*
        if (usage instanceof UsageInfo2UsageAdapter) {
            PsiElement elem = ((UsageInfo2UsageAdapter) usage).getElement();
            PlSqlFile file = (PlSqlFile) elem.getContainingFile();
            int plsqlElemCount = 0;
            for (PsiElement e : file.getChildren()) {
                if (e instanceof PlSqlElement) {
                    plsqlElemCount++;
                }
            }

            if (plsqlElemCount == 1) {
                // convert usage
                return transform((UsageInfo2UsageAdapter) usage);
            }
        }
*/
        // todo -- implement me
        return usage;
    }

    @NotNull
    public String getComponentName() {
        return "com.deepsky.findUsages.converter.UsageConverter";
    }

    public void initComponent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void disposeComponent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Cut off UsageInFile interface from implementation list
     *
     * @param usage - candidate for lobotomy
     * @return - result of conversion
     */
    // UsageInModule, UsageInLibrary, UsageInFile, PsiElementUsage,
    // MergeableUsage, Comparable<UsageInfo2UsageAdapter>, RenameableUsage,
    // TypeSafeDataProvider
    private Usage transform(UsageInfo2UsageAdapter usage) {
        InvocationHandler usageHandler = new UsageAdapter(usage);
        Usage u0 = (Usage) Proxy.newProxyInstance(
                PlSqlElement.class.getClassLoader(),
                new Class[]{UsageInModule.class, UsageInLibrary.class, PsiElementUsage.class,
                        MergeableUsage.class, Comparable.class, RenameableUsage.class,
                        TypeSafeDataProvider.class},
                usageHandler);

        return u0;
    }


    private class UsageAdapter implements InvocationHandler {
        UsageInfo2UsageAdapter usageAdapter;

        public UsageAdapter(UsageInfo2UsageAdapter usageAdapter) {
            this.usageAdapter = usageAdapter;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("compareTo") && args.length == 1) {
                if(args[0] instanceof Proxy){
                    UsageAdapter adapter = (UsageAdapter) Proxy.getInvocationHandler((Proxy) args[0]);
                    return usageAdapter.compareTo(adapter.usageAdapter);
                } else if(args[0] instanceof UsageInfo2UsageAdapter){
                    return usageAdapter.compareTo((UsageInfo2UsageAdapter) args[0]);
                } else {
                    return 0;
                }
//                UsageAdapter adapter = (UsageAdapter) Proxy.getInvocationHandler((Proxy) args[0]);
//                return usageAdapter.compareTo(adapter.usageAdapter);
            } else {
                return method.invoke(usageAdapter, args);
            }
        }
    }


}
