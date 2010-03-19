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

import com.deepsky.database.SqlScriptManager;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.Callable;
import com.deepsky.lang.plsql.psi.Executable;
import com.deepsky.lang.plsql.psi.ParameterReference;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.resolve.*;
import com.deepsky.lang.plsql.struct.ExecutableDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class ParameterReferenceImpl extends PlSqlCompositeNameBase implements ParameterReference {

    public ParameterReferenceImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Type getExpressionType() {
        try {
            Callable callable = getCallable();
            ExecutableDescriptor edesc = ResolveHelper3.resolveCallable(callable);
            Type type = edesc.getArgumentType(getText());
            if (type != null) {
                return type;
            } else {
                throw new ValidationException("Cannot resolve type", this);
            }
        } catch (NameNotResolvedException e) {
            throw new ValidationException("Cannot resolve type", e.getCauseElement());
        }
    }

    @NotNull
    public ResolveContext777 getResolveContext() throws NameNotResolvedException {
        Callable callable = getCallable();
        ExecutableDescriptor edesc = ResolveHelper3.resolveCallable(callable);
        Type type = edesc.getArgumentType(getText());
        if (type != null) {
            return new ParameterResolveContext777(getText(), edesc, type);
        } else {
            throw new NameNotResolvedException("Cannot resolve parameter with name " + getText());
        }
    }


    private Callable getCallable() throws NameNotResolvedException {
        ASTTreeProcessor runner = new ASTTreeProcessor();
        final Callable[] out = {null};
        runner.add(new ASTNodeHandler() {
            @NotNull
            public TokenSet getTokenSet() {
                return PlSqlElementTypes.CALLABLE_CONTEXT;
            }

            public boolean handleNode(@NotNull ASTNode node) {
                if (node.getPsi() instanceof Callable) {
                    out[0] = (Callable) node.getPsi();
                }
                return false;
            }
        });

        runner.process(getNode().getTreeParent());
        if(out[0] == null){
            throw new NameNotResolvedException("Cannot resolve parameter " + getText());
        }

        return out[0];
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitParameterReference(this);
        } else {
            super.accept(visitor);
        }
    }


    class ParameterResolveContext777 implements ResolveContext777 {

        Type type;
        ExecutableDescriptor edesc;
        String parameterName;

        public ParameterResolveContext777(String parameterName, ExecutableDescriptor edesc, Type type) {
            this.parameterName = parameterName;
            this.edesc = edesc;
            this.type = type;
        }

        @NotNull
        public VariantsProcessor777 create(int narrow_type) throws NameNotResolvedException {
            // todo
            throw new NameNotResolvedException("");
        }

        public PsiElement getDeclaration() {
            PsiElement psi = SqlScriptManager.mapToPsiTree(getProject(), edesc);
            if(psi instanceof Executable){
                Executable exec = (Executable) psi;
                return exec.getArgumentByName(parameterName);
            }
            return null;
        }

        public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {
            throw new NameNotResolvedException("");
        }

        public Type getType() throws NameNotResolvedException {
            return type;
        }
    }

    
}
