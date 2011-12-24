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

package com.deepsky.lang.plsql.psi.impl.spec_func_call;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class CastExpression extends SpecFunctionCallBaseImpl {

    public CastExpression(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Type getExpressionType() {
        final ASTNode callList = getNode().findChildByType(PlSqlElementTypes.SPEC_CALL_ARGUMENT_LIST);
        if (callList != null) {
            TypeSpec type = this.findChildByClass(TypeSpec.class);
            if (type != null) {
                return type.getType();
            }
        }

        throw new SyntaxTreeCorruptedException();
    }

    @NotNull
    public String getFunctionName() {
        return "CAST";
    }

    @NotNull
    public PsiElement getPsiFunctionName() {
        return getNode().findChildByType(PlSqlTokenTypes.KEYWORD_CAST).getPsi();
    }


    final static TokenSet tokenSet = TokenSet.create(
            PlSqlElementTypes.DATATYPE,
            PlSqlElementTypes.TYPE_NAME_REF
    );

    @NotNull
    public String formatFunctionSignature() {
        StringBuilder sb = new StringBuilder("cast");

        final ASTNode callList = getNode().findChildByType(PlSqlElementTypes.SPEC_CALL_ARGUMENT_LIST);
        if (callList != null) {
            final ASTNode node = callList.findChildByType(tokenSet);
            if (node != null) {
                String type = node.getText();
                if (node.getElementType() == PlSqlElementTypes.DATATYPE) {
                    type = type.toUpperCase();
                }
                return sb.append("(expression as ").append(type).append("): ").append(type).toString();
            }
        }

        // todo -- handle error case
        return "";
    }

}
