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

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.CallArgument;
import com.deepsky.lang.plsql.psi.CallableCompositeName;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.SSystemFunctionCall;
import com.deepsky.lang.plsql.psi.impl.PlSqlElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public abstract class SpecFunctionCallBaseImpl extends PlSqlElementBase implements SSystemFunctionCall {

    public SpecFunctionCallBaseImpl(ASTNode astNode) {
        super(astNode);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
//            ((PlSqlElementVisitor) visitor).visitSpecFunctionCall(this);
            ((PlSqlElementVisitor) visitor).visitFunctionCall(this);
        } else {
            super.accept(visitor);
        }
    }

    public boolean isAggregate() {
        return false;
    }


    @NotNull
    public CallableCompositeName getCompositeName() {
        final ASTNode node = getNode().findChildByType(PlSqlElementTypes.CALLABLE_NAME_REF);

        if (node != null) {
            final PsiElement psi = node.getPsi();
            if (psi != null) {
                return (CallableCompositeName) psi;
            }
        }

        throw new SyntaxTreeCorruptedException();
    }

    @NotNull
    public CallArgument[] getCallArguments() {
        ASTNode callList = getNode().findChildByType(PlSqlElementTypes.SPEC_CALL_ARGUMENT_LIST);
        if (callList != null) {
            ASTNode[] args = callList.getChildren(TokenSet.create(PlSqlElementTypes.CALL_ARGUMENT));
            if (args != null && args.length > 0) {
                CallArgument[] out = new CallArgument[args.length];
                for (int i = 0; i < out.length; i++) {
                    out[i] = (CallArgument) args[i].getPsi();
                }

                return out;
            }
        }
        return new CallArgument[0];
    }


    @NotNull
    public String formatFunctionSignature() {
        return "";
    }
/*
    public FunctionParamInfo[] getParamInfo() {
        return new FunctionParamInfo[0];
    }
*/

    // presentation stuff
/*
    @Nullable
    public ItemPresentation getPresentation() {
        return new SysFunctionPresentation();
    }

    public FileStatus getFileStatus() {
        return null;
    }

    public String getName() {
        return getFunctionName();
    }


    private class SysFunctionPresentation implements ItemPresentation {
        public String getPresentableText() {
            FunctionParamInfo[] paramInfos = getParamInfo();
            if(paramInfos.length > 0){
                return paramInfos[0].formatArgumentList();
            }
            return null;
        }

        @Nullable
        public String getLocationString() {
            return null;///"(temporary table)";
        }

        @Nullable
        public Icon getIcon(boolean open) {
            return null;//chooseIcon();
        }

        @Nullable
        public TextAttributesKey getTextAttributesKey() {
            return TextAttributesKey.find("SQL.TABLE");
        }
    }
*/

}
