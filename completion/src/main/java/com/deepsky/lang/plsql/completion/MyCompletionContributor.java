/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.completion;

import com.deepsky.lang.plsql.completion.logic.ASTTreeAdapter;
import com.deepsky.lang.plsql.completion.processors.C_Context;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.CompletionProcessor2;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generator.CallMetaInfo;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generator.TreePathContext;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.intellij.codeInsight.completion.*;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MyCompletionContributor extends CompletionContributor {

    public void beforeCompletion(@NotNull final CompletionInitializationContext context) {
        context.setFileCopyPatcher(Constants.IDENT_PATCHER);
//        context.setDummyIdentifier(Constants.COMPL_IDENTIFIER);
    }

//    void print(ASTNode parent, int indent) {
//        System.out.println(putIndent(indent) + parent.getElementType());
//        ASTNode cur = parent.getFirstChildNode();
//        while (cur != null) {
//            if (cur.getElementType() != PlSqlTokenTypes.WS) {
//                print(cur, indent + 1);
//            }
//            cur = cur.getTreeNext();
//        }
//    }
//
//
//    String putIndent(int indent) {
//        StringBuilder b = new StringBuilder(indent);
//        for (int i = 0; i < indent * 2; i++) {
//            b.append(' ');
//        }
//        return b.toString();
//    }


    @Override
    public void fillCompletionVariants(CompletionParameters parameters, final CompletionResultSet result) {
        if (parameters.getCompletionType() != CompletionType.BASIC) {
            return;
        }

        final ASTNode nodeToComplete = parameters.getPosition().getNode();
//        print(root, 0);

        TreePath path = ASTTreeAdapter.recovery2(nodeToComplete);
        System.out.println(path.printPath());
        System.out.flush();

        CompletionProcessor2 proc = new CompletionProcessor2(path);
        if (proc.process()) {
            // Great! Path found
            TreePathContext context = proc.getContext();
            CallMetaInfo metaInfo = context.getMeta();
            System.out.println("Path: " + context.getTreePath());

            CompletionCaller caller = buildInvoker(metaInfo);
            C_Context cContext = new C_Context() {
                @Override
                public String getLookup() {
                    return "";
                }

                @Override
                public CompletionResultSet getResultSet() {
                    return result;
                }
            };

//            Object[] args = new Object[1+context.getHandlerParameters().length];
//            System.arraycopy(context.getHandlerParameters(), 0, args, 1, context.getHandlerParameters().length);
//            args[0] = cContext;
//            caller.call(args);

            super.fillCompletionVariants(parameters, result);
        }

    }


    private CompletionCaller buildInvoker(CallMetaInfo metaInfo) {
        String className = metaInfo.getClassName();
        String methodName = metaInfo.getMethodName();
        String[] argTypes = metaInfo.getArgTypes();

        // TODO convert arg type to classes

        try {
            Class clazz = Class.forName(className);

            Method m = clazz.getDeclaredMethod(methodName, new Class[0] /* todo */);
            Object targetHandler = clazz.newInstance();

            return new CompletionCaller(targetHandler, m);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // TODO - implement me
        return null;
    }


    class CompletionCaller {

        Object targetObject;
        Method handler;

        public CompletionCaller(Object targetObject, Method handler){
            this.targetObject = targetObject;
            this.handler = handler;
        }

        boolean call(Object... args) {
            try {
                handler.invoke(targetObject, args);
                return true;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
