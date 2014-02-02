package com.deepsky.lang.plsql.completion;

import com.deepsky.lang.plsql.completion.logic.ASTTreeAdapter;
import com.deepsky.lang.plsql.completion.processors.C_Context;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.CallMetaInfo;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.CompletionProcessor2;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.TreePathContext;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.lang.ASTNode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MyCompletionContributor extends CompletionContributor {

    @Override
    public void fillCompletionVariants(CompletionParameters parameters, final CompletionResultSet result) {
        if (parameters.getCompletionType() != CompletionType.BASIC) {
            return;
        }

        final ASTNode nodeToComplete = parameters.getPosition().getNode();

        TreePath path = ASTTreeAdapter.recovery2(nodeToComplete);
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        if (proc.process()) {
            // Great! Path found
            TreePathContext context = proc.getContext();
            CallMetaInfo metaInfo = context.getMeta();

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
