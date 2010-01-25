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

package com.deepsky.lang.plsql.psi.resolve;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public abstract class ContextEnumerator implements ASTNodeHandler {

    final static TokenSet tokenSet = TokenSet.create(
            PlSqlElementTypes.PACKAGE_BODY,
            PlSqlElementTypes.PACKAGE_SPEC,
            PlSqlElementTypes.PROCEDURE_BODY,
            PlSqlElementTypes.PROCEDURE_SPEC,
            PlSqlElementTypes.FUNCTION_BODY,
            PlSqlElementTypes.FUNCTION_SPEC,
            PlSqlElementTypes.CREATE_TRIGGER
//            PlSqlElementTypes.SELECT_EXPRESSION,
//            PlSqlElementTypes.SELECT_EXPRESSION_UNION,
//            PlSqlElementTypes.INSERT_COMMAND,
//            PlSqlElementTypes.UPDATE_COMMAND,
//            PlSqlElementTypes.DELETE_COMMAND
    );

    public ContextEnumerator(){
    }

    TokenSet extSet = null;
    public ContextEnumerator(TokenSet extSet){
        this.extSet = extSet;
    }

    @NotNull
    public TokenSet getTokenSet() {
        return extSet == null? tokenSet : extSet;
    }

    public boolean handleNode(@NotNull ASTNode node) {
        handleASTNode(node);
        return false;
    }

    public abstract void handleASTNode(@NotNull ASTNode node);
}
