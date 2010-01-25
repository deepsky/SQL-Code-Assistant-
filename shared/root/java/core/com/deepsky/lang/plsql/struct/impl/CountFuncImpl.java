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

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.utils.StringUtils;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class CountFuncImpl extends PsiElementDumb implements CountFunction {

    Expression expr;
    public CountFuncImpl(Expression expr){
        this.expr = expr;
    }

    public CountFuncImpl(Expression expr, boolean distinct) {
        // todo --
        this.expr = expr;
    }

    String asterisk;
    public CountFuncImpl(String asterisk){
        this.asterisk = asterisk;
    }

    @NotNull
    public Type getExpressionType() {
        return null;
    }//    String getPackageName();

    @NotNull
    public String getFunctionName() {
        return null;
    }

    @NotNull
    public CallableCompositeName getCompositeName() {
        return null;
    }

    @NotNull
    public CallArgument[] getCallArgumentList() {
        return new CallArgument[]{ new CallArgumentImpl(null, expr)};
    }

    @Nullable
    public CallArgumentList getCallArgumentListNode() {
        // todo -
        return null;
    }

    public String getAsterisk(){
        return asterisk;
    }

    public void process(Visitor visitor){
        visitor.accept(this);
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
