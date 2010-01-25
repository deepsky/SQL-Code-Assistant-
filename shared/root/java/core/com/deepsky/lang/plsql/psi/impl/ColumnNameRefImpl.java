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

package com.deepsky.lang.plsql.psi.impl;

import com.deepsky.lang.plsql.psi.ColumnNameRef;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.TableScope;
import com.deepsky.lang.plsql.psi.resolve.impl.PlainTableColumnContext;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;


public class ColumnNameRefImpl extends PlSqlReferenceBase implements ColumnNameRef {

    public ColumnNameRefImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Object[] getVariants(String text) {
        if (getParent() instanceof TableScope) {
            TableScope tscope = (TableScope) getParent();

            TableDescriptor tdesc = ResolveHelper.describeTable(tscope.getTableName());
            if (tdesc != null) {
                try {
                    return new PlainTableColumnContext(getProject(), tdesc, getText()).getVariants(text);
                } catch (NameNotResolvedException e1) {
                    // todo --
                }
            }
        }

        // todo --
        return new Object[0];
    }

    public PsiElement resolve() {
        if (getParent() instanceof TableScope) {
            TableScope tscope = (TableScope) getParent();

            TableDescriptor tdesc = ResolveHelper.describeTable(tscope.getTableName());
            if (tdesc != null) {
                try {
                    return new PlainTableColumnContext(getProject(), tdesc, getText()).getDeclaration();
                } catch (NameNotResolvedException e1) {
                    // todo --
                }
            }
        }

        // todo --
        return null;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitColumnNameRef(this);
        } else {
            super.accept(visitor);
        }
    }

    public boolean isReferenceTo(PsiElement psiElement) {
        PsiElement resolved = resolve();
        return psiElement == resolved;
    }


}
