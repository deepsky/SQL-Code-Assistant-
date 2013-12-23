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

package com.deepsky.lang.plsql.paraminfo;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.CallArgumentList;
import com.deepsky.lang.plsql.psi.Callable;
import com.deepsky.lang.plsql.psi.FunctionCall;
import com.deepsky.lang.plsql.psi.names.CompositeName;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.resolver.helpers.ExecutableResolveHelper;
import com.deepsky.lang.plsql.resolver.utils.CallArgumentResolver;
import com.deepsky.lang.PsiUtil;
import com.deepsky.lang.plsql.sqlIndex.ResolveHelper;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.parameterInfo.*;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FunctionParameterInfoHandler implements ParameterInfoHandler {
    public boolean couldShowInLookup() {
        return true;
    }

    public Object[] getParametersForLookup(LookupElement item, ParameterInfoContext context) {
        return new Object[0];
    }

    public Object[] getParametersForDocumentation(Object p, ParameterInfoContext context) {
        return new Object[0];
    }

    private PsiElement paramInfoElement;
    private ResolveFacade facade;


    public Object findElementForParameterInfo(CreateParameterInfoContext context) {

        PsiElement el = context.getFile().findElementAt(context.getOffset());
        if (el == null) {
            return null;
        }

        PsiElement callArgumentsList = PsiUtil.findAncestor(el, PlSqlElementTypes.CALL_ARGUMENT_LIST);
        if (callArgumentsList != null) {
            PsiElement _callable = callArgumentsList.getParent();
            if (_callable instanceof Callable) {
                Callable callable = (Callable) _callable;
                facade = ((PlSqlFile) callable.getContainingFile()).getResolver();

                ResolveDescriptor[] methods = resolveCallWithOverloads(facade, callable);
                if (methods.length > 0) {
                    context.setItemsToShow(createMethods(methods));
                    paramInfoElement = callArgumentsList;
                    return callArgumentsList;
                }
            }
        } else {
            // Syntax tree might be NOT corrupted
            // Normalize element at caret
            if (PsiUtil.isWS(el)) {
                el = PsiUtil.nextNonWSLeaf(el);
            }

            Callable callable = null;
            // Check on  PlSqlElementTypes.CALL_ARGUMENT first
            PsiElement element = PsiUtil.findAncestor(el, PlSqlElementTypes.CALL_ARGUMENT);
            if (element != null) {
                PsiElement _callable = element.getParent();
                if (_callable instanceof Callable) {
                    callable = (Callable) _callable;
                } else {
                    return null;
                }
            } else {
                // Check on COMMA on CLOSE_PAREN
                if (el.getNode().getElementType() == PlSqlTokenTypes.COMMA) {
                    ASTNode node = PsiUtil.prevVisibleSibling(el.getNode());
                    if (node == null || node.getElementType() != PlSqlElementTypes.CALL_ARGUMENT) {
                        return null;
                    }
                } else if (el.getNode().getElementType() != PlSqlTokenTypes.CLOSE_PAREN) {
                    return null;
                }
                // Find supplementary leading parenthesis
                final PsiElement[] e1 = {null};
                PsiUtil.iterateBackOverNonWSLeafs(el, new PsiUtil.PsiElementHandler() {
                    int close_paren = 1;

                    public boolean handle(PsiElement e) {
                        if (e.getNode().getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
                            close_paren++;
                        } else if (e.getNode().getElementType() == PlSqlTokenTypes.OPEN_PAREN) {
                            if (--close_paren == 0) {
                                // Leading parenthesis found,
                                // take previous toke which is expected to be func/proc name
                                e1[0] = e.getPrevSibling();
                                return false;
                            }
                        }
                        return true;
                    }
                });
                if (!(e1[0] instanceof CompositeName) || !(e1[0].getParent() instanceof Callable)) {
                    return null;
                }
                callable = (Callable) e1[0].getParent();
            }

            facade = ((PlSqlFile) callable.getContainingFile()).getResolver();
            ResolveDescriptor[] methods = resolveCallWithOverloads(facade, callable);
            if (methods.length > 0) {
                context.setItemsToShow(createMethods(methods));
                paramInfoElement = callable;
                return callable;
            }
        }

        return null;
    }

    private ResolveDescriptor[] resolveCallWithOverloads(ResolveFacade facade, Callable callable) {
        ResolveHelper rhelper = facade.getLLResolver();
        int ctype = callable instanceof FunctionCall ? ContextPath.FUNC_CALL : ContextPath.PROC_CALL;
        String ctxPath = callable.getCtxPath1().getPath();
        return rhelper.resolveCallable(
                ctype, ctxPath, callable.getFunctionName()
        );
    }

    public void showParameterInfo(@NotNull Object element, CreateParameterInfoContext context) {
        context.showHint((PsiElement) element, 1, this);
    }

    private boolean cursorInsideParentasis(PsiElement _callable, int offset) {
        int offsetInParent = offset - _callable.getTextOffset();
        if (offsetInParent > 0) {
            String text = _callable.getText();
            if (text.indexOf('(') < offsetInParent && text.lastIndexOf(')') >= offsetInParent) {
                return true;
            }
        }
        return false;
    }

    public Object findElementForUpdatingParameterInfo(UpdateParameterInfoContext context) {
        PsiElement el = context.getFile().findElementAt(context.getOffset());

        PsiElement target = PsiUtil.findAncestor(el,
                PlSqlElementTypes.CALL_ARGUMENT_LIST,
                PlSqlElementTypes.FUNCTION_CALL,
                PlSqlElementTypes.PROCEDURE_CALL);

        if (target != null && target == paramInfoElement) {
            return target;
        }

        return null;
    }

    public void updateParameterInfo(@NotNull Object o, UpdateParameterInfoContext context) {
        // Highlight entered parameter list
        Object[] candidates = context.getObjectsToView();
        if (candidates.length > 1) {
            for (Object c : candidates) {
                ExecutableResolveHelper cEx = (ExecutableResolveHelper) c;
                if (o instanceof CallArgumentList) {
                    CallArgumentList callArgs = (CallArgumentList) o;
                    if (callArgumentListMatchesArgumentList(cEx, callArgs)) {
                        context.setHighlightedParameter(c);
                    }
                } else {
                    // Case:    PlSqlElementTypes.CALL_ARGUMENT
                    //          PlSqlElementTypes.FUNCTION_CALL,
                    //          PlSqlElementTypes.PROCEDURE_CALL
                    // TODO - highlight parameter list
                }
            }
        }
    }


    public boolean callArgumentListMatchesArgumentList(ExecutableResolveHelper ehlp, CallArgumentList callArgs) { //}, ArgumentList argList) {
        List<String> errors = new ArrayList<String>();
        new CallArgumentResolver(facade.getLLResolver()).validate(ehlp, callArgs.getArguments(), errors);
        return errors.size() == 0;
    }

    public String getParameterCloseChars() {
        return ")";
    }

    public boolean tracksParameterIndex() {
        return false;
    }

    public void updateUI(Object p, ParameterInfoUIContext context) {
//        String text = ((ExecutableResolveHelper) p).getParamInfo();
        String text = Formatter.formatArgList(((ExecutableResolveHelper) p).getArgumentSpecification(), false);
        StringBuffer buffer = new StringBuffer(text);
        context.setupUIComponentPresentation(
                buffer.toString(),
                -1, //0,
                -1, //buffer.length(),
                !context.isUIComponentEnabled(),
                false, //method.isDeprecated(),
                false,
                context.getDefaultParameterColor()
        );
    }

    private ExecutableResolveHelper[] createMethods(ResolveDescriptor[] el) {

        List<ExecutableResolveHelper> out = new ArrayList<ExecutableResolveHelper>();
        for (ResolveDescriptor rDesc : el) {
            if (rDesc instanceof ExecutableResolveHelper) {
                out.add((ExecutableResolveHelper) rDesc);
            }
        }
        return out.toArray(new ExecutableResolveHelper[out.size()]);
    }

}
