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

package com.deepsky.lang.plsql.psi.impl.ref;

import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.impl.PlSqlReferenceBase;
import com.deepsky.lang.plsql.psi.ref.TableRefWithLink;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class TableRefWithLinkImpl extends PlSqlReferenceBase implements TableRefWithLink {

    public TableRefWithLinkImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getLinkName() {
        String full = StringUtils.discloseDoubleQuotes(getText());
        return full.substring(full.indexOf('@'), full.length());
    }

    public String getTableName() {
        String full = StringUtils.discloseDoubleQuotes(getText());
        return full.substring(0, full.indexOf('@'));
    }

    @Override
    protected PsiElement resolveInternal() {
        // todo -- implement me
        return null;
    }

    @NotNull
    @Override
    public Object[] getVariants(String text) {
        // todo -- implement me
        return new Object[0];
    }


    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitTableRefWithLink(this);
        } else {
            super.accept(visitor);
        }
    }

    public TextRange getTableNameRange() {
        String text = getText();
        if (text.charAt(0) == '"' && text.charAt(text.length() - 1) == '"') {
            return new TextRange(getTextRange().getStartOffset() + 1, getTextRange().getEndOffset() - 1);
        } else {
            return getTextRange();
        }
    }

    public ResolveDescriptor resolveLight() {
        return getResolveFacade().getLLResolver().resolveTableRef(getTableName());
    }

}
