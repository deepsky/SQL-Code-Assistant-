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

import com.deepsky.lang.plsql.psi.FunctionCall;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.validation.ValidationException;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class FunctionCallImpl extends CallableImpl implements FunctionCall {

    public FunctionCallImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getFragmentText() {
        return StringUtils.discloseDoubleQuotes(getText());
    }
    
    public void accept(@NotNull PsiElementVisitor visitor) {
      if (visitor instanceof PlSqlElementVisitor) {
        ((PlSqlElementVisitor)visitor).visitFunctionCall(this);
      }
      else {
        super.accept(visitor);
      }
    }


    @NotNull
    public Type getExpressionType() {

//        String fname = getFunctionName();
        try {
//            FunctionDescriptor fdesc = (FunctionDescriptor) ResolveHelper3.resolveCallable(this);

//            Type type = fdesc.getReturnType();
//            // todo - workaround for any types
//            if(type.typeId() == Type.ANY){
//                // attempt to resolve type using the real argument
//                CallArgument[] args = this.getCallArgumentList();
//                if(args.length > 0){
//                    Type t = args[0].getExpression().getExpressionType();
//                    return t;
//                }
//            }
//            return type;

            ResolveContext777 ctx = getCompositeName().resolve(null); //getResolveContext();
            return ctx.getType();
        } catch (NameNotResolvedException e1) {
//            try {
//                ExecutableDescriptor edesc = ResolveHelper3.resolveCallable(this);
//
//                int hh =0;
//            } catch (NameNotResolvedException e2) {
//                int gg =0;
//            }

            throw new ValidationException("Function '" + getFunctionName() + "' not defined", getNode());
        }
    }

}
