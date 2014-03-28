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

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.common.PluginKeys2;
import com.deepsky.lang.plsql.completion.logic.ASTTreeAdapter;
import com.deepsky.lang.plsql.completion.lookups.SelectFieldLookupElement;
import com.deepsky.lang.plsql.completion.processors.C_Context;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.CompletionProcessor2;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generator.CallMetaInfo;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generator.TreePathContext;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.utils.StringUtils;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;


public class MyCompletionContributor extends GenericCompletionContributor {

    /**
     * Called when the completion is finished quickly, lookup hasn't been shown and gives possibility to autoinsert some item (typically - the only one).
     */
    @Nullable
    public AutoCompletionDecision handleAutoCompletionPossibility(AutoCompletionContext context) {
        if (ApplicationManager.getApplication().isUnitTestMode()) {
            return AutoCompletionDecision.SHOW_LOOKUP;
        }
        return null;
    }


    /**
     * The main contributor method that is supposed to provide completion variants to result, basing on completion parameters.
     * The default implementation looks for {@link com.intellij.codeInsight.completion.CompletionProvider}s you could register by
     * invoking {@link #extend(CompletionType, com.intellij.patterns.ElementPattern, CompletionProvider)} from you contributor constructor,
     * matches the desired completion type and {@link com.intellij.patterns.ElementPattern} with actual ones, and, depending on it, invokes those
     * completion providers.<p>
     * <p/>
     * If you want to implement this functionality directly by overriding this method, the following is for you.
     * Always check that parameters match your situation, and that completion type ({@link CompletionParameters#getCompletionType()}
     * is of your favourite kind. This method is run outside of read action, so you have to manage this manually
     * ({@link com.intellij.openapi.application.Application#runReadAction(Runnable)}). Don't take read actions for too long.<p>
     *
     * @param parameters
     * @param result
     */
    @Override
    public void fillCompletionVariants(CompletionParameters parameters, final CompletionResultSet result) {
        if (parameters.getCompletionType() != CompletionType.BASIC) {
            return;
        }

        final ASTNode nodeToComplete = parameters.getPosition().getNode();

        TreePath path = ASTTreeAdapter.recovery2(nodeToComplete);
        System.out.println(path.printPath());
        System.out.flush();

        CompletionProcessor2 proc = new CompletionProcessor2(path);
        if (proc.process()) {
            // Great! Path found
            TreePathContext context = proc.getContext();

            final Set<LookupElement> lookupElementList = new HashSet<LookupElement>();
            final String lookupStr = stripText(StringUtils.discloseDoubleQuotes(nodeToComplete.getText()));
            final VariantsProvider provider = chooseSearchDomain((PlSqlFile) nodeToComplete.getPsi().getContainingFile());

            for (int i = 0; i < context.options(); i++) {
                CallMetaInfo metaInfo = context.getDesc(i).getMeta();
                System.out.println("Path: " + context.getDesc(i).getTreePath());
                System.out.println("Method: " + metaInfo.getMethodName() + " Class: " + metaInfo.getClassName());

                CompletionCaller caller = null;
                try {
                    caller = buildInvoker(metaInfo);
                    C_Context cContext = new C_Context() {
                        @Override
                        public String getLookup() {
                            return lookupStr;
                        }

                        @Override
                        public VariantsProvider getProvider() {
                            return provider;
                        }

                        @Override
                        public void addElement(@NotNull LookupElement element) {
                            lookupElementList.add(element);
                        }
                    };
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

                    Object[] args = new Object[1 + context.getDesc(i).getHandlerParameters().length];
                    System.arraycopy(context.getDesc(i).getHandlerParameters(), 0, args, 1, context.getDesc(i).getHandlerParameters().length);
                    args[0] = cContext;
                    caller.call(args);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InstantiationException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

            // Finalize SelectFieldLookupElement elements
            LookupElement[] elements = lookupElementList.toArray(new LookupElement[lookupElementList.size()]);
            for (int i2 = 0; i2 < elements.length; i2++) {
                SelectFieldLookupElement selectField = null;
                if (elements[i2] instanceof SelectFieldLookupElement) {
                    selectField = (SelectFieldLookupElement) elements[i2];
                }
                for (int j = i2 + 1; j < elements.length; j++) {
                    if (selectField != null && elements[j] instanceof SelectFieldLookupElement) {
                        String m = selectField.getSuggestedName();
                        String n2 = ((SelectFieldLookupElement) elements[j]).getSuggestedName();
                        if (m.equalsIgnoreCase(n2)) {
                            selectField.getIt().setFullQualifiedColumn(true);
                            ((SelectFieldLookupElement) elements[j]).getIt().setFullQualifiedColumn(true);
                        }
                    }
                }

                result.withPrefixMatcher(lookupStr).addElement(elements[i2]);
            }

            // Feed parent with lookup elements
            super.fillCompletionVariants(parameters, result);
        }
    }


    private CompletionCaller buildInvoker(CallMetaInfo metaInfo) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        String className = metaInfo.getClassName();
        String methodName = metaInfo.getMethodName();
        Class[] callArgTypes = metaInfo.getArgTypes();

        Class clazz = Class.forName(className);

        Method m = clazz.getDeclaredMethod(methodName, callArgTypes);
        Object targetHandler = clazz.newInstance();

        return new CompletionCaller(targetHandler, m);
    }


    private class CompletionCaller {

        Object targetObject;
        Method handler;

        public CompletionCaller(Object targetObject, Method handler) {
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
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private VariantsProvider chooseSearchDomain(PlSqlFile plsqlFile) {
        DbUrl dbUrl = plsqlFile.getDbUrl();
        AbstractSchema i = PluginKeys2.SQL_INDEX_MAN.getData(plsqlFile.getProject()).getIndex(dbUrl, 0);
        return (i != null) ? i.getVariantsProvider() : null;
    }


    public static String stripText(String text) {
        if (text == null) {
            return null;
        }
        int idx = text.indexOf(Constants.COMPL_IDENTIFIER); //IntellijIdeaRulezzz);
        if (idx >= 0) {
            if (idx == 0) {
                return "";
            } else {
                return text.substring(0, idx);
            }

        } else {
            return text;
        }
    }

    void print(ASTNode parent, int indent) {
        System.out.println(putIndent(indent) + parent.getElementType());
        ASTNode cur = parent.getFirstChildNode();
        while (cur != null) {
            if (cur.getElementType() != PlSqlTokenTypes.WS) {
                print(cur, indent + 1);
            }
            cur = cur.getTreeNext();
        }
    }

    String putIndent(int indent) {
        StringBuilder b = new StringBuilder(indent);
        for (int i = 0; i < indent * 2; i++) {
            b.append(' ');
        }
        return b.toString();
    }


}
