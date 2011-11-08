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

import com.deepsky.lang.common.PlSqlFileType;
import com.deepsky.lang.common.ResolveProvider;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.Visitor;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.resolver.factory.ContextPathManager;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.command.impl.DummyProject;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class PlSqlElementBase extends ASTWrapperPsiElement implements PlSqlElement {

    protected ResolveFacade facade;

    public PlSqlElementBase(ASTNode astNode) {
        super(astNode);
    }

    final protected void __ensure_resolver_available__() {
        if (facade == null) {
            facade = ((ResolveProvider) getContainingFile()).getResolver();
        }
    }

    final protected ResolveFacade getResolveFacade() {
        __ensure_resolver_available__();
        return facade;
    }


    @NotNull
    public Project getProject() {
        try {
            return super.getProject();
        } catch (PsiInvalidElementAccessException e) {
            // helpful in standalone runnings
            return DummyProject.getInstance();
        }
    }

    @NotNull
    public PsiElement[] getChildren() {
        PsiElement psiChild = getFirstChild();
        if (psiChild == null) return EMPTY_ARRAY;

        List<PsiElement> result = new ArrayList<PsiElement>();
        while (psiChild != null) {
//        if (psiChild.getNode() instanceof CompositeElement) {
            result.add(psiChild);
//        }
            psiChild = psiChild.getNextSibling();
        }
        return result.toArray(new PsiElement[result.size()]);
    }

    @NotNull
    public Language getLanguage() {
        return PlSqlFileType.FILE_TYPE.getLanguage();
    }

    @Nullable
    public String getQuickNavigateInfo() {
        return null;
    }

    public String getStrippedText() {
        // todo -- resolve stuff refactoring
        throw new NotSupportedException();
    }

    public void process(Visitor proc) {
        // todo -
    }

    public <T> T findParent(Class clazz) {
        ASTNode n = getNode();
        while (n != null) {

            if (n.getPsi().getClass() == clazz) {
                return (T) n.getPsi();
            } else {
                java.lang.reflect.Type[] interfaces = n.getPsi().getClass().getGenericInterfaces();
                for (java.lang.reflect.Type t : interfaces) {
                    if (t == clazz) {
                        return (T) n.getPsi();
                    }
                }

                if (n.getPsi().getClass().getGenericSuperclass() == clazz) {
                    return (T) n.getPsi();
                }
            }
            n = n.getTreeParent();
        }

        return null;
    }

    final protected void __ASSERT_NOT_NULL__(Object o) {
        if (o == null) {
            throw new SyntaxTreeCorruptedException();
        }
    }

    final public CtxPath getCtxPath1() {
        //return ((CompositeElementExt) getNode()).getCtxPath1();
        return ContextPathManager.getCtxPath(getNode());
    }

}
