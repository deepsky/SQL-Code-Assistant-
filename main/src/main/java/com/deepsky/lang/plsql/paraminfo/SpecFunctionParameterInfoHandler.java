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

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.CallArgumentList;
import com.deepsky.lang.plsql.psi.SpecFunctionCall;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.helpers.ExecutableResolveHelper;
import com.deepsky.lang.plsql.resolver.utils.ArgumentSpec;
import com.deepsky.lang.PsiUtil;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.*;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SpecFunctionParameterInfoHandler implements ParameterInfoHandler {
    public boolean couldShowInLookup() {
        return true;
    }

    public Object[] getParametersForLookup(LookupElement item, ParameterInfoContext context) {
        return new Object[0];
    }

    public Object[] getParametersForDocumentation(Object p, ParameterInfoContext context) {
        return new Object[0];
    }

    //    CallArgumentList callArgumentsList;
//    SpecFunctionCall callable;
    PsiElement callArgumentsList;
//    ResolveFacade facade;

    public Object findElementForParameterInfo(CreateParameterInfoContext context) {

        PsiElement el = context.getFile().findElementAt(context.getOffset());
        callArgumentsList = PsiUtil.findAncestor(el, PlSqlElementTypes.SPEC_CALL_ARGUMENT_LIST);
        if (callArgumentsList != null) {
            PsiElement _callable = callArgumentsList.getParent();
            if (_callable instanceof SpecFunctionCall) {
                SpecFunctionCall callable = (SpecFunctionCall) _callable;
//                facade = ((PlSqlFile) callable.getContainingFile()).getResolver();

                ResolveDescriptor[] methods = null; // todo -- fix me resolveCallWithOverloads(facade, callable);
                if (methods.length > 0) {
                    context.setItemsToShow(createMethods(methods));
                    return callArgumentsList;
                }
            }
        }
        return null;
    }


    public void showParameterInfo(@NotNull Object element, CreateParameterInfoContext context) {
        context.showHint((PsiElement) element, 1, this);
    }

    public Object findElementForUpdatingParameterInfo(UpdateParameterInfoContext context) {
        PsiElement el = context.getFile().findElementAt(context.getOffset());
        PsiElement callArgumentsList = PsiUtil.findAncestor(el, PlSqlElementTypes.SPEC_CALL_ARGUMENT_LIST);
        if (callArgumentsList != this.callArgumentsList) {
//            if (callArgumentsList.getParent() == this.callable) {
//                return this.callable;
//            }
            return this.callArgumentsList;
        }
        return null;
    }

    public void updateParameterInfo(@NotNull Object o, UpdateParameterInfoContext context) {

        Object[] candidates = context.getObjectsToView();
        if (candidates.length > 1 && o instanceof PsiElement &&
                ((PsiElement) o).getNode().getElementType() == PlSqlElementTypes.SPEC_CALL_ARGUMENT_LIST) {
            for (Object c : candidates) {
                ExecutableResolveHelper cEx = (ExecutableResolveHelper) c;
//                if (o instanceof CallArgumentList) {
                CallArgumentList callArgs = (CallArgumentList) o;
                ArgumentSpec[] args = cEx.getArgumentSpecification();
                if (callArgumentListMatchesArgumentList(cEx, callArgs)) {
                    context.setHighlightedParameter(c);
                }
//                }
            }
        }
    }


    public boolean callArgumentListMatchesArgumentList(ExecutableResolveHelper ehlp, CallArgumentList callArgs) { //}, ArgumentList argList) {
        List<String> errors = new ArrayList<String>();
// todo -- fix me        new CallArgumentResolver(facade.getLLResolver()).validate(ehlp, callArgs.getArguments(), errors);
        return errors.size() == 0;
    }

    public String getParameterCloseChars() {
        return ")";
    }

    public boolean tracksParameterIndex() {
        return false;
    }

    public void updateUI(Object p, ParameterInfoUIContext context) {
        String text = Formatter.formatArgList(((ExecutableResolveHelper) p).getArgumentSpecification(), false);
        StringBuffer buffer = new StringBuffer(text);
        context.setupUIComponentPresentation(
                buffer.toString(),
                -1,
                -1,
                !context.isUIComponentEnabled(),
                false,
                false,
                context.getDefaultParameterColor()
        );
    }

    private ExecutableResolveHelper[] createMethods(ResolveDescriptor[] el) { //PsiElement[] el) {
        List<ExecutableResolveHelper> out = new ArrayList<ExecutableResolveHelper>();
        for (int i = 0; i < el.length; i++) {
            if (el[i] instanceof ExecutableResolveHelper) {
                out.add((ExecutableResolveHelper) el[i]);
            }
        }
        return out.toArray(new ExecutableResolveHelper[0]);
    }

}
