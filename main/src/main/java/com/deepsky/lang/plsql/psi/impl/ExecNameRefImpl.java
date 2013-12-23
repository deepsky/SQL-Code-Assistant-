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

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.names.CompositeName;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class ExecNameRefImpl extends PlSqlReferenceBase implements ExecNameRef {

    public ExecNameRefImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getFragmentText() {
        return getText();
    }

    protected PsiElement resolveInternal() {
        return facade.resolveFragment(this);
    }

    @NotNull
    @Override
    public Object[] getVariants(String text) {
        // todo -- implement me
        return new Object[0];
    }

    public CallArgument[] getCallArgumentList() {
        return ((Callable)getParent().getParent()).getCallArguments();
    }

    public String getNameInScope(){
        CompositeName cname = (CompositeName) getParent();
        NameFragmentRef[] pieces = cname.getNamePieces();
        StringBuilder b = new StringBuilder();
        for(NameFragmentRef r: pieces){
            if(b.length() > 0){
                b.append(".");
            }
            b.append(getFragmentText());
            if(r == this){
                break;
            }
        }

        return b.toString();
    }

    public NameFragmentRef getPrevFragment() {
        NameFragmentRef[] pieces = ((CompositeName) getParent()).getNamePieces();
        NameFragmentRef prev = null;
        for(NameFragmentRef r: pieces){
            if( r == this){
                return prev;
            }
            prev = r;
        }

        return null;
    }

    public ResolveDescriptor resolveLight() {
        PsiElement parent = getParent().getParent();
        if(parent != null){
            final PsiElement granpa = parent.getParent();
            if(granpa instanceof CollectionMethodCall || granpa instanceof CollectionMethodCall2){
                // Access to Collection detected
                return getResolveFacade().getLLResolver().resolveGenericReference(
                        getNameInScope(), getCtxPath1().getPath()
                );
            }
        }
        final CallableCompositeName cname = (CallableCompositeName)getParent();
        int type = cname.getCallable() instanceof FunctionCall? ContextPath.FUNC_CALL: ContextPath.PROC_CALL;
        // int callableType, String path, String callableName, CallArgument[] args        
        return getResolveFacade().getLLResolver().resolveCallable(
                type, getCtxPath1().getPath(), cname.getText(),  getCallArgumentList()
        );
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitExecNameRef(this);
        } else {
            super.accept(visitor);
        }
    }

}
