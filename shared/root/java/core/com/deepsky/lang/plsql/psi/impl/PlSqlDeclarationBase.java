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

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.Declaration;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandler;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public abstract class PlSqlDeclarationBase extends PlSqlElementBase implements Declaration {

    public PlSqlDeclarationBase(ASTNode astNode) {
        super(astNode);
    }


    private static TokenSet defCtx =
            TokenSet.create(
                    PlSqlElementTypes.PACKAGE_BODY,
                    PlSqlElementTypes.PACKAGE_SPEC,
                    PlSqlElementTypes.FUNCTION_BODY,
                    PlSqlElementTypes.PROCEDURE_BODY,
                    PlSqlElementTypes.CREATE_TRIGGER
            );

    public PlSqlElement getDefinitionContext(){
        ASTTreeProcessor treeProcessor = new ASTTreeProcessor();
        final PsiElement[] ctx = new PsiElement[1];
        treeProcessor.add(new ASTNodeHandler() {
            @NotNull
            public TokenSet getTokenSet() {
                return defCtx;
            }

            public boolean handleNode(@NotNull ASTNode node) {
                ctx[0] = node.getPsi();
                return true;
            }
        });

        treeProcessor.process(getNode(), true /* break on first hit*/);
        return (PlSqlElement) ctx[0];
    }
}
