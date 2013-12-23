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
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.helpers.SysFuncResolveHelper;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class TrimFunction extends SpecFunctionCallBaseImpl {

    final private static Type VARCHAR2 = TypeFactory.createTypeByName("VARCHAR2");

    public TrimFunction(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Type getExpressionType() {
        return VARCHAR2;
    }

    @NotNull
    public String getFunctionName() {
        return "TRIM";
    }

    @NotNull
    public PsiElement getPsiFunctionName() {
        return getNode().findChildByType(PlSqlTokenTypes.KEYWORD_TRIM).getPsi();
    }

    final static TokenSet tokenSet = TokenSet.create(
            PlSqlTokenTypes.KEYWORD_LEADING,
            PlSqlTokenTypes.KEYWORD_TRAILING,
            PlSqlTokenTypes.KEYWORD_BOTH
    );


    @NotNull
    public String formatFunctionSignature() {
        NameFragmentRef[] ref = getCompositeName().getNamePieces();
        ResolveDescriptor rDesc = ref[ref.length-1].resolveLight();
        if(rDesc instanceof SysFuncResolveHelper){
            StringBuilder sb = new StringBuilder("trim");
            final ASTNode callList = getNode().findChildByType(PlSqlElementTypes.SPEC_CALL_ARGUMENT_LIST);
            if (callList != null) {
                final ASTNode node = callList.findChildByType(tokenSet);
                if(node != null){
                    sb.append("(").append(node.getText().toUpperCase()).append(" expression");
                } else {
                    sb.append("(expression");
                }
                final ASTNode node2 = callList.findChildByType(PlSqlTokenTypes.KEYWORD_FROM);
                if(node2 != null){
                    sb.append(" FROM expression)");
                } else {
                    sb.append(")");
                }
                return sb.append(": ").append(VARCHAR2.typeName()).toString();
            }
        }
        // todo -- handle error case
        return "";
    }

}
