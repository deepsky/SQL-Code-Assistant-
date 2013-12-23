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

package com.deepsky.lang.plsql.completion;

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.names.ColumnNameRef;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.intellij.codeInsight.lookup.LookupElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;


public interface VariantsProvider {

    int FILTER_NONE = 0;
    int FILTER_EXPR = 1;
    int FILTER_TYPES = 2;

    List<LookupElement> collectTypeNameVariants(String ctxPath, String lookupString);

    List<LookupElement> collectTableNameVariants(String lookupString);
    List<LookupElement> collectViewNameVariants(String lookupString);

    List<LookupElement> collectNamesInContext(ResolveDescriptor desc, String lookUpStr);

    List<LookupElement> collectNamesInContext(NameFragmentRef prev, String lookUpStr, int filter);

    List<LookupElement> collectColumnNameRef(final ColumnNameRef columnNameRef, String lookUpStr);

    List<LookupElement> collectDataTypeVariants(String ctxPath, String lookUpStr);

    /**
     * Collect variants for variable
     * @param usageCtx
     * @param lookUpStr
     * @return
     */
    List<LookupElement> collectVarVariants(String usageCtx, String lookUpStr);

    List<LookupElement> collectFuncCall(String usageCtx, String alias, String lookUpStr);

    List<LookupElement> collectProcCall(String usageCtx, String alias, String lookUpStr);

    List<LookupElement> collectSystemFuncCall(String lookUpStr);

    List<LookupElement> collectPackageVariants(String lookUpStr);

    List<LookupElement> collectVarVariantsInPackage(String pkgName, String lookUpStr);

    Collection<LookupElement> collectParametersNames(ParameterReference parent, String lookUpStr);

    List<LookupElement> collectColumnNames(@NotNull String tableName, String lookupStr);

    Collection<? extends LookupElement> collectSequenceVariants(String prevText, String lookUpStr);


    /**
     * Collect variables in context
     * @param context context where to search for variables
     * @param prefix prefix of the
     * @return
     */
    List<LookupElement> collectVariableVariants(PlSqlBlock context, String prefix);

    // New approach
    void collectColumnVariants(SelectStatement select, final String alias);
    void collectColumnNames(TableAlias tableName, String lookUpStr, boolean forceUsingTableAlias);

    List<? extends LookupElement> takeCollectedLookups();

}
