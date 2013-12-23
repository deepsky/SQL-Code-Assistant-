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

package com.deepsky.lang.plsql.sqlIndex;

import com.deepsky.lang.plsql.psi.CallArgument;
import com.deepsky.lang.plsql.psi.Callable;
import com.deepsky.lang.plsql.resolver.RefHolder;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.helpers.PackageResolveHelper;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.types.TableColumnRefType;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * Implementation class is supposed to be a stateless component
 */
public interface ResolveHelper {

    @Nullable
    Type resolveType(TableColumnRefType type);

    @Nullable
    UserDefinedType resolveUDT(String usageContext, String typeName);

    ResolveDescriptor[] resolveReferencePoly(RefHolder ref);
    ResolveDescriptor[] resolveFrontPoly(RefHolder ref);

    ResolveDescriptor resolveGenericReference(String name, String ctxPath);
    ResolveDescriptor resolvePlSqlVariable(String name, String ctxPath);
    ResolveDescriptor resolveReference(RefHolder ref);
    ResolveDescriptor resolveFront(RefHolder ref);

    /**
     * Resolve names
     * @param rholder - name holder
     * @param position - position of the name to resolve
     * @return resolve result
     */
    ResolveDescriptor[] resolveNames(RefHolder rholder, int position);

    // statistics
    long getSpentTime();
    void clearStatistics();


/*
    @Nullable
    ResolveDescriptor resolve(int refType, String path, String name, CallArgument[] args);
*/

    @Nullable
    ResolveDescriptor resolveCallable(RefHolder rholder, CallArgument[] args, int position);

    @Nullable
    ResolveDescriptor resolveCallable(@Nullable ResolveDescriptor ctx, int funcCall, String path, String text, CallArgument[] args);

    @Nullable
    ResolveDescriptor resolveCallable(int callableType, String path, String callableName, CallArgument[] args);

    /**
     * Returns all overloaded function/procedures descriptors
     * 
     * @param callableType
     * @param path
     * @param callableName
     * @return
     */
    @NotNull
    ResolveDescriptor[] resolveCallable(int callableType, String path, String callableName);

    @Nullable
    ResolveDescriptor resolveCallable(Callable callable);

    @Nullable
    ResolveDescriptor resolveReference(ResolveDescriptor ctx, String nameToResolve);

    @Nullable
    ResolveDescriptor resolveTableRef(String tableName);

    @Nullable
    ResolveDescriptor resolveSequenceRef(String text);

    @Nullable
    PackageResolveHelper resolvePackage(String pkgName);

}
