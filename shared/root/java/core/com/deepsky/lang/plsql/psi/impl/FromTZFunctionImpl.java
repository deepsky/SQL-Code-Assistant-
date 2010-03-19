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

import com.deepsky.lang.plsql.psi.FunctionCall;
import com.deepsky.lang.plsql.psi.CallArgument;
import com.deepsky.lang.plsql.psi.CallableCompositeName;
import com.deepsky.lang.plsql.psi.CallArgumentList;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class FromTZFunctionImpl extends PlSqlElementBase implements FunctionCall {
    public FromTZFunctionImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public String getFunctionName() {
        return "FROM_TZ";
    }

    @NotNull
    public CallableCompositeName getCompositeName() {
        return (CallableCompositeName) getNode().findChildByType(PlSqlElementTypes.CALLABLE_NAME_REF).getPsi();
    }

    @NotNull
    public CallArgument[] getCallArgumentList() {
        // todo - not supported at the moment
        return new CallArgument[0];
    }

    @NotNull
    public Type getExpressionType() {
        return TypeFactory.createTypeByName("TIMESTAMP");
    }

    // todo -----------------------------------
    @NotNull
    public ResolveContext777 resolveContext() throws NameNotResolvedException {
        throw new NameNotResolvedException("Not supported");
    }

    public String getFragmentText() {
        return StringUtils.discloseDoubleQuotes(getText());
    }

    // todo -- [start] PsiReference specific
    public PsiElement getElement() {
        // todo --
        return null;
    }

    public TextRange getRangeInElement() {
        // todo --
        return null;
    }

    public PsiElement resolve() {
        // todo --
        return null;
    }

    public String getCanonicalText() {
        // todo --
        return null;
    }

    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        // todo --
        return null;
    }

    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        // todo --
        return null;
    }

    public boolean isReferenceTo(PsiElement element) {
        // todo --
        return false;
    }

    @NotNull
    public Object[] getVariants() {
        // todo --
        return new Object[0];
    }

    public boolean isSoft() {
        // todo --
        return false;
    }
    // todo -- [end] PsiReference specific

}
