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

package com.deepsky.lang.plsql.resolver;

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.names.ColumnNameRef;
import com.deepsky.lang.plsql.psi.ref.SequenceRef;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.lang.plsql.sqlIndex.ResolveHelper;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.types.TableColumnRefType;
import com.intellij.psi.PsiElement;

public interface ResolveFacade {

    AbstractSchema getSimpleIndex();

    Type resolveType(Expression expression);

    /**
     * Calculate real type of the Column Type i.e "table1.name%type"
     *
     * @param t - subject to resolve
     * @return resolved type
     */
    Type resolveType(TableColumnRefType t);

    PsiElement resolveCallable(Callable callable);

    PsiElement[] resolveCallableWithOverloads(Callable callable);

    PsiElement resolveTable(TableRef table);

    PsiElement resolveSequence(SequenceRef ref);

    PsiElement resolveObjectReference(ObjectReference ref);

    PsiElement resolveColumnNameRef(ColumnNameRef ref);

    PsiElement resolveCRecordItemRef(CRecordItemRef ref);

    PsiElement resolveFragment(NameFragmentRef fragment);

    PackageSpec findPackageSpecification(PackageBody body);

    ExecutableSpec findSpecificationFor(Executable procedure);

    ResolveHelper getLLResolver();

    /**
     * Check whether call arguments match to formal argument list
     *
     * @param callArgs - call arguments
     * @param argList  - formal argument list
     * @return - matches
     */
    boolean callArgumentListMatchesArgumentList(CallArgumentList callArgs, ArgumentList argList);
}
