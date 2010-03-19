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

package com.deepsky.lang.plsql.psi.resolve;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public abstract class LoopHandler implements ASTNodeHandler {

    final TokenSet loop = TokenSet.create(PlSqlElementTypes.LOOP_STATEMENT);

    private PsiElement elem;

    public LoopHandler(PsiElement element) {
        this.elem = element;
    }

    @NotNull
    public TokenSet getTokenSet() {
        return loop;
    }

    public boolean handleNode(@NotNull ASTNode node) {
        try {
            ASTNode loopSpec = node.findChildByType(PlSqlElementTypes.LOOP_SPEC_TYPES);
            if (loopSpec != null) {
                ASTNode lindex = loopSpec.findChildByType(PlSqlElementTypes.LOOP_INDEX);
                if (lindex != null && lindex.getText().equalsIgnoreCase(elem.getText())) {
                    if (loopSpec.getElementType() == PlSqlElementTypes.NUMERIC_LOOP_SPEC) {
                        // --
                        handleNumericLoopSpec(loopSpec);
                    } else if (loopSpec.getElementType() == PlSqlElementTypes.CURSOR_REF_LOOP_SPEC) {
                        ASTNode cursor = loopSpec.findChildByType(PlSqlElementTypes.CURSOR_NAME);
                        handleCursorRefLoopSpec(cursor);
                    } else if (loopSpec.getElementType() == PlSqlElementTypes.CURSOR_IMPL_LOOP_SPEC) {
                        ASTNode select = loopSpec.findChildByType(PlSqlElementTypes.SUBQUERY_SELECTS);
                        handleCursorFromSelectLoopSpec((SelectStatement)select.getPsi());
                    } else if (loopSpec.getElementType() == PlSqlElementTypes.FORALL_LOOP_SPEC) {
                        handleForALLLoopSpec(loopSpec);
                    }
                }
            } else {
                // todo -- fix me
            }

        } catch (NullPointerException e) {
        } catch (SyntaxTreeCorruptedException e) {
        }
        
        return false;
    }

    protected abstract void handleForALLLoopSpec(ASTNode loopSpec);

    public abstract void handleNumericLoopSpec(ASTNode node);

    public abstract void handleCursorRefLoopSpec(ASTNode cursorRef);

    public abstract void handleCursorFromSelectLoopSpec(SelectStatement select);
}
