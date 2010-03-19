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

import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameFragmentRefImpl extends PlSqlReferenceBase implements NameFragmentRef { //}, PsiNamedElement {

    static final LoggerProxy log = LoggerProxy.getInstance("#NameFragmentRefImpl");

    // todo - dirty hack which should be fixed asap
    static public Map<String, RefElement> resolveCache = new HashMap<String, RefElement>();

    public NameFragmentRefImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getName() {
        String name = super.getName();
//        log.info("getName() = " + name);
        return name;
    }

    public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException {
        return this;
    }

    static class RefElement {
        public PsiElement elem;

        public RefElement(PsiElement psi) {
            this.elem = psi;
        }
    }

    public PsiElement resolve() {

        RefElement elem = resolveCache.get(this.toString());
        if (elem != null) {
//            log.info("resolve() = " + elem.elem + " this: " + this);
            return elem.elem;
        }
//        log.info("resolve() [" + this + "]");

        PsiElement psi = null;
        try {
            ResolveContext777 ctx = resolveInner(this);
            psi = ctx.getDeclaration();
        } catch (NameNotResolvedException e1) {
        } catch (SyntaxTreeCorruptedException e1) {
        }

        resolveCache.put(this.toString(), new RefElement(psi));
//        log.info("resolve() = " + psi + " this: " + this);
        return psi;
    }


    private ResolveContext777 resolveInner(PlSqlElement leafElem) throws NameNotResolvedException {

        PsiElement parent = leafElem.getParent();

        if (parent instanceof CompositeName) {
            // ObjectReference
            // TypeNameReference
            // CallableCompositeName
            // SequenceExpr
            // ColumnSpec
            // VColumnDefinition
            // TriggerColumnNameRef
            // ParameterReference

            CompositeName _parent = (CompositeName) parent;
            return _parent.resolve((NameFragmentRef) leafElem);
/*
            NameFragmentRef[] pieces = _parent.getNamePieces();
            int pos = _parent.getFragmentIndex((NameFragmentRef) leafElem);

            if (pos == -1) {
                throw new NameNotResolvedException("[2] Could not resolve name: " + leafElem.getText());
            }

            ResolveContext777 ctx = _parent.getResolveContext();
            for (int i = 1; --pos >= 0; i++) {
                ctx = ctx.resolve(pieces[i]);
            }
            return ctx;
*/

        } else {
            throw new NameNotResolvedException("[3] Could not resolve name: " + leafElem.getText());
        }
    }

    @NotNull
    private VariantsProcessor777 createVariantsProcessor(PlSqlElement leafElem) throws NameNotResolvedException {

        PsiElement parent = leafElem.getParent();

        if (parent instanceof CompositeName) {
            // ...
            CompositeName _parent = (CompositeName) parent;
            return _parent.createVariantsProcessor(leafElem);
        } else {
            throw new NameNotResolvedException("[3] Could not resolve name: " + leafElem.getText());
        }
    }


    @NotNull
    public Object[] getVariants(String text) {

        try {
            VariantsProcessor777 proc = createVariantsProcessor(this);
            return adopt(text, proc.getVariants(text));
        } catch (NameNotResolvedException e1) {
            // todo --
            return new Object[0];
        }
    }


    String[] adopt(String template, String[] variants) {
        List<String> cc = new ArrayList<String>();

        boolean toLower = false;
        if (template.length() > 0) {
            for (String v : variants) {
                StringBuffer _v = new StringBuffer();
                for (int i = 0; i < template.length(); i++) {
                    char c = template.charAt(i);
                    if (Character.isLetter(c)) {
                        if (Character.isLowerCase(c)) {
                            toLower = true;
                        } else {
                            toLower = false;
                        }
                    }
                    _v.append(c);
                }

                if (v.length() > _v.length()) {
                    String s;
                    if (toLower) {
                        s = v.toLowerCase();
                        _v.append(s.substring(_v.length(), v.length()));
                    } else {
                        s = v.toUpperCase();
                        _v.append(s.substring(_v.length(), v.length()));
                    }
                }
                cc.add(_v.toString());
            }
            return cc.toArray(new String[cc.size()]);
        } else {
            return variants;
        }
    }

    @NotNull
    public ResolveContext777 resolveContext() throws NameNotResolvedException {
        return resolveInner(this);
    }

    public String getFragmentText() {
        return StringUtils.discloseDoubleQuotes(getText());
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitNameFragmentRef(this);
        } else {
            super.accept(visitor);
        }
    }

    public boolean isReferenceTo(PsiElement psiElement) {

        if (psiElement instanceof ColumnDefinition) {
            return ((ColumnDefinition) psiElement).getColumnName().equalsIgnoreCase(getText()) && psiElement == resolve();
        } else if (psiElement instanceof VColumnDefinition) {
            return ((VColumnDefinition) psiElement).getColumnName().equalsIgnoreCase(getText()) && psiElement == resolve();
        } else if (psiElement instanceof VariableDecl) {
            VariableDecl var = (VariableDecl) psiElement;
            if(var.getDeclName().equalsIgnoreCase(getText())){
                PsiElement psi = resolve();
                return var == psi;
            } else {
                return false;
            }
        } else if (psiElement instanceof Argument) {
            Argument arg = (Argument) psiElement;
            if(arg.getArgumentName().equalsIgnoreCase(getText())){
                PsiElement psi = resolve();
                return arg == psi;
            } else {
                return false;
            }
        } else {
            // todo -- default comparison needs in optimization
            PsiElement psi = resolve();
            return psi == psiElement;
        }
    }

}
