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
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.resolver.helpers.ExecutableResolveHelper;
import com.deepsky.lang.plsql.resolver.utils.ArgumentSpec;
import com.deepsky.lang.plsql.resolver.utils.CallArgumentResolver;
import com.deepsky.lang.plsql.sqlIndex.ResolveHelper;
import com.deepsky.lang.plsql.utils.SqlTreeUtil;
import com.intellij.codeInsight.lookup.LookupElement;
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
        PsiElement callArgumentsList = SqlTreeUtil.findAncestor(el, PlSqlElementTypes.CALL_ARGUMENT_LIST);
        if (callArgumentsList != null) {
            PsiElement _callable = callArgumentsList.getParent();
            if (_callable instanceof Callable) {
                Callable callable = (Callable) _callable;
                facade = ((PlSqlFile) callable.getContainingFile()).getResolver();

                ResolveDescriptor[] methods = resolveCallWithOverloads(facade, callable);
                if(methods.length > 0){
                    context.setItemsToShow(createMethods(methods));
                    paramInfoElement = callArgumentsList;
                    return callArgumentsList;
                }
            }
        } else {
            // syntax tree might be corrupted, so check on  PlSqlElementTypes.CALL_ARGUMENT
            PsiElement element = SqlTreeUtil.findAncestor(el, PlSqlElementTypes.CALL_ARGUMENT);
            if(element != null){
                PsiElement _callable = element.getParent();
                if (_callable instanceof Callable) {
                    Callable callable = (Callable) _callable;
                    facade = ((PlSqlFile) callable.getContainingFile()).getResolver();

                    ResolveDescriptor[] methods = resolveCallWithOverloads(facade, callable);
                    if(methods.length > 0){
                        context.setItemsToShow(createMethods(methods));
                        paramInfoElement = element;
                        return element;
                    }
                }
            }
        }
        return null;
    }

    private ResolveDescriptor[] resolveCallWithOverloads(ResolveFacade facade, Callable callable){
        ResolveHelper rhelper = facade.getLLResolver();
        int ctype = callable instanceof FunctionCall? ContextPath.FUNC_CALL: ContextPath.PROC_CALL;
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
        PsiElement callArgumentsList = SqlTreeUtil.findAncestor(el, PlSqlElementTypes.CALL_ARGUMENT_LIST);
        if (callArgumentsList != null) {
            if (callArgumentsList == paramInfoElement){ //this.callArgumentsList) {
                return callArgumentsList;
            }
/*
            PsiElement _callable = callArgumentsList.getParent();
            int offset = context.getEditor().getCaretModel().getOffset();
            if (cursorInsideParentasis(_callable, offset) && callArgumentsList == this.callArgumentsList) {
                return callArgumentsList;
            }
*/
        } else {
            PsiElement callArgument = SqlTreeUtil.findAncestor(el, PlSqlElementTypes.CALL_ARGUMENT);
            if (callArgument != null && callArgument == paramInfoElement) {
                return callArgument;
            }
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
                    if(callArgumentListMatchesArgumentList(cEx, callArgs)){
                        context.setHighlightedParameter(c);
                    }
                }
            }
        }
    }


    public boolean callArgumentListMatchesArgumentList(ExecutableResolveHelper ehlp, CallArgumentList callArgs){ //}, ArgumentList argList) {
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

    private ExecutableResolveHelper[] createMethods(ResolveDescriptor[] el){

        List<ExecutableResolveHelper> out = new ArrayList<ExecutableResolveHelper>();
        for (ResolveDescriptor rDesc : el) {
            if (rDesc instanceof ExecutableResolveHelper) {
                out.add((ExecutableResolveHelper) rDesc);
            }
        }
        return out.toArray(new ExecutableResolveHelper[out.size()]);
    }

}
