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

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.Callable;
import com.deepsky.lang.plsql.psi.CollectionMethodCall;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper3;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class CollectionMethodCallImpl extends PlSqlCompositeNameBase implements CollectionMethodCall {

    public CollectionMethodCallImpl(ASTNode astNode) {
        super(astNode);
    }

    // todo - dirty hack which should be fixed asap
    static public Map<String, RefElement> resolveCache = new HashMap<String, RefElement>();

    static class RefElement {
        public ResolveContext777 ctx;

        public RefElement(ResolveContext777 ctx) {
            this.ctx = ctx;
        }
    }

    @NotNull
    public ResolveContext777 getResolveContext() throws NameNotResolvedException {

        RefElement elem = resolveCache.get(this.toString());
        if (elem != null) {
            if(elem.ctx == null){
                throw new NameNotResolvedException("Name not resolved: " + getText());
            }
            return elem.ctx;
        }

        ASTNode node = getNode().findChildByType(PlSqlElementTypes.FUNCTION_CALL);
        if (node == null) {
            throw new SyntaxTreeCorruptedException();
        }
        ResolveContext777[] ctx = ResolveHelper3.resolveCollectionCall((Callable) node.getPsi());
        if(ctx.length == 1){
            resolveCache.put(this.toString(), new RefElement(ctx[0]));
        } else {
            resolveCache.put(this.toString(), new RefElement(null));
            throw new NameNotResolvedException("Name not resolved: " + getText());
        }

        return ctx[0];
    }

/*
    public static ResolveContext777 resolveCollectionCall(Callable callable)  {
//        ASTNode node = getNode().findChildByType(PlSqlElementTypes.FUNCTION_CALL);
//        if (node == null) {
//            throw new SyntaxTreeCorruptedException();
//        }
//
//        FunctionCall fcall = (FunctionCall) node.getPsi();
        final String fname = callable.getFunctionName();
        final TableCollectionTypeContext[] ctx = {null};

        PackageBody body =
                (PackageBody) PsiTreeHelpers
                        .findParentNode(
                                callable,
                                new PlSqlElementType[]{
                                        PlSqlElementTypes.PACKAGE_BODY}
                        );

        final UsageContext uctx = new UsageContext((body != null)? body.getPackageName(): null);

        // 2. the name of variable or argument (usage: function/procedure definition)
        ASTTreeProcessor runner = new ASTTreeProcessor();
        runner.add(new ExecCtxDeclarationEnumerator() {
            public void handleArgument(Argument arg) {
                if (arg.getArgumentName().equalsIgnoreCase(fname)) {
                    Type t = arg.getType();
                    if (t instanceof UserDefinedType) {
                        UserDefinedTypeDescriptor tdesc = ResolveHelper.resolve_Type((UserDefinedType) t, uctx);
                        if (tdesc instanceof TableCollectionDescriptor) {
                            TableCollectionDescriptor cdesc = (TableCollectionDescriptor) tdesc;
                            ctx[0] = new TableCollectionTypeContext(cdesc);
                        }
                    }
                }
            }

            public void handleDecl(Declaration decl) {
                if (decl instanceof VariableDecl) {
                    VariableDecl var = (VariableDecl) decl;
                    if (var.getVariableName().equalsIgnoreCase(fname)) {
                        Type t = var.getType();
                        if (t instanceof UserDefinedType) {
                            UserDefinedTypeDescriptor tdesc = ResolveHelper.resolve_Type((UserDefinedType) t, uctx);
                            if (tdesc instanceof TableCollectionDescriptor) {
                                TableCollectionDescriptor cdesc = (TableCollectionDescriptor) tdesc;
                                ctx[0] = new TableCollectionTypeContext(cdesc);
                            }
                        }
                    }
                }
            }
        });

        runner.process(callable.getNode());
        return ctx[0];
    }
*/


    @NotNull
    public Type getExpressionType() {
        try {
            PsiElement psi = this.findChildByType(PLSqlTypesAdopted.C_RECORD_ITEM_REF);
            ResolveContext777 ctx = getResolveContext();
            ResolveContext777 ctx2 = ctx.resolve(psi);
            return ctx2.getType();
        } catch (NameNotResolvedException e) {
            throw new ValidationException(e.getMessage());
        } catch (Throwable e) {
            throw new ValidationException(e.getMessage());
        }
    }
}
