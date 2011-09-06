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
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NameFragmentRefImpl extends PlSqlReferenceBase implements NameFragmentRef { //}, PsiNamedElement {

    static final LoggerProxy log = LoggerProxy.getInstance("#NameFragmentRefImpl");

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

    protected PsiElement resolveInternal() {
        return facade.resolveFragment(this);
    }


    @NotNull
    public Object[] getVariants(String text) {

        return new Object[0];
/*
todo -- resolve stuff refactoring
        try {
            VariantsProcessor777 proc = createVariantsProcessor(this);
            return adopt(text, proc.getVariants(text));
        } catch (NameNotResolvedException e1) {
            // todo --
            return new Object[0];
        }
*/
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

    public String getNameInScope(){
        CompositeName cname = (CompositeName) getParent();
        NameFragmentRef[] pieces = cname.getNamePieces();
        StringBuilder b = new StringBuilder();
        for(NameFragmentRef r: pieces){
            if(b.length() > 0){
                b.append(".");
            }
            b.append(r.getFragmentText());
            if(r == this){
                break;
            }
        }

        return b.toString();
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

    public ResolveDescriptor resolveLight() {

        return getResolveFacade().getLLResolver().resolveGenericReference(
                getNameInScope(), getCtxPath1().getPath()
        );
    }
}
