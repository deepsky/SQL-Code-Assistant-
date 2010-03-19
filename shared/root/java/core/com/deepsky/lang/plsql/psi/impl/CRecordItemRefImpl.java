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

import com.deepsky.lang.plsql.psi.CRecordItemRef;
import com.deepsky.lang.plsql.psi.CollectionMethodCall;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;


public class CRecordItemRefImpl extends PlSqlReferenceBase implements CRecordItemRef {
    public CRecordItemRefImpl(ASTNode astNode) {
        super(astNode);
    }


    public PsiElement resolve() {
        if (getParent() instanceof CollectionMethodCall) {
            CollectionMethodCall cmethod = (CollectionMethodCall) getParent();
            try {
                ResolveContext777 ctx = cmethod.getResolveContext();
                return ctx.resolve(this).getDeclaration();
            } catch (NameNotResolvedException e) {
                //
            }
        }

        return null;
    }


    public boolean isReferenceTo(PsiElement psiElement) {
        // todo -- implement me
        return false;
    }

    @NotNull
    public Object[] getVariants(String text) {
        if (getParent() instanceof CollectionMethodCall) {
            CollectionMethodCall cmethod = (CollectionMethodCall) getParent();
            try {
                ResolveContext777 ctx = cmethod.getResolveContext();
                return ctx.create(0).getVariants(text);
            } catch (NameNotResolvedException e) {
                //
            }
        }

        // todo -- implement me
        return new Object[0];
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitCRecordItemRef(this);
        } else {
            super.accept(visitor);
        }
    }

}
