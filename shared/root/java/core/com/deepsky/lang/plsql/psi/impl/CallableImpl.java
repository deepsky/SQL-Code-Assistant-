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

import com.deepsky.lang.plsql.psi.Callable;
import com.deepsky.lang.plsql.psi.CallArgument;
import com.deepsky.lang.plsql.psi.CallableCompositeName;
import com.deepsky.lang.plsql.psi.CallArgumentList;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class CallableImpl extends PlSqlElementBase implements Callable {
    public CallableImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public String getFunctionName() {
        CallableCompositeName ccname = getCompositeName();
        return ccname.getCName();
//        ASTNode node = getNode().findChildByType(//TokenSet.create(
//               PlSqlElementTypes.CALLABLE_NAME_REF //PlSqlElementTypes.FUNC_NAME_REF, PlSqlElementTypes.PROC_NAME_REF)
//        );
//
////        ASTNode exec_name = node.findChildByType(PlSqlElementTypes.EXEC_NAME_REF);
//        ASTNode exec_name = node.findChildByType(PlSqlElementTypes.NAME_FRAGMENT);
//        return exec_name.getText();
    }

    @NotNull
    public CallableCompositeName getCompositeName() {
        return (CallableCompositeName) getNode().findChildByType(
                PlSqlElementTypes.CALLABLE_NAME_REF //TokenSet.create(PlSqlElementTypes.FUNC_NAME_REF, PlSqlElementTypes.PROC_NAME_REF)
        ).getPsi();
    }

    @NotNull
    public CallArgument[] getCallArgumentList() {
        ASTNode callList = getNode().findChildByType(PlSqlElementTypes.CALL_ARGUMENT_LIST);
        if (callList != null) {
            ASTNode[] args = callList.getChildren(TokenSet.create(PlSqlElementTypes.CALL_ARGUMENT));
            if (args != null && args.length > 0) {
                CallArgument[] out = new CallArgument[args.length];
                for (int i=0; i<out.length; i++) {
                    out[i] = (CallArgument) args[i].getPsi();
                }

                return out;
            }
        }
        return new CallArgument[0];
    }

    @Nullable
    public CallArgumentList getCallArgumentListNode() {
        ASTNode callList = getNode().findChildByType(PlSqlElementTypes.CALL_ARGUMENT_LIST);
        if(callList != null){
            return (CallArgumentList) callList.getPsi();
        } else {
            return null;
        }
    }


    // todo -----------------------------------
    @NotNull
    public ResolveContext777 resolveContext() throws NameNotResolvedException {
        throw new NameNotResolvedException("Not supported"); 
    }

}
