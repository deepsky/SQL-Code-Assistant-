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

package com.deepsky.lang.plsql.completion.lookups;

import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;


public class DataTypeParenthesesHandler extends ParenthesesInsertHandler<LookupElement> {
    //  private final PsiMethod myMethod;
    private final boolean myOverloadsMatter = false;
    boolean hasParams;

    public DataTypeParenthesesHandler(boolean hasParams) {
        this.hasParams = hasParams;
    }

    public DataTypeParenthesesHandler(
                                  boolean hasParams,
                                  final boolean overloadsMatter,
                                  final boolean spaceBeforeParentheses, final boolean spaceBetweenParentheses,
                                  final boolean insertRightParenthesis) {
    super(spaceBeforeParentheses, spaceBetweenParentheses, insertRightParenthesis);
        this.hasParams = hasParams;
  }

//  public MethodParenthesesHandler(final PsiMethod method, boolean overloadsMatter) {
//    myMethod = method;
//    myOverloadsMatter = overloadsMatter;
//  }
//
//  public MethodParenthesesHandler(final PsiMethod method,
//                                  final boolean overloadsMatter,
//                                  final boolean spaceBeforeParentheses, final boolean spaceBetweenParentheses,
//                                  final boolean insertRightParenthesis) {
//    super(spaceBeforeParentheses, spaceBetweenParentheses, insertRightParenthesis);
//    myMethod = method;
//    myOverloadsMatter = overloadsMatter;
//  }

    protected boolean placeCaretInsideParentheses(final InsertionContext context, final LookupElement item) {
        return hasParams; //(item, context.getElements(), myOverloadsMatter, myMethod);
    }

    public static boolean hasParams(LookupElement item, LookupElement[] allItems, final boolean overloadsMatter) { //}, final PsiMethod method) {
//    boolean hasParams = method.getParameterList().getParametersCount() > 0;
//    if (overloadsMatter){
//      hasParams |= hasOverloads(item, allItems, method);
//    }
        return true;
    }

    private static boolean hasOverloads(LookupElement item, LookupElement[] allItems) { //, final PsiMethod method) {
//    String name = method.getName();
//    for (LookupElement item1 : allItems) {
//      final Object o = item1.getObject();
//      if (item.getObject() != o && o instanceof PsiMethod && ((PsiMethod)o).getName().equals(name)) {
//        return true;
//      }
//    }
        return false;
    }

    @Override
    protected PsiElement findNextToken(final InsertionContext context) {
        PsiElement element = context.getFile().findElementAt(context.getTailOffset());
        if (element instanceof PsiWhiteSpace &&
                element.getText().contains("\n") &&
                !CodeStyleSettingsManager.getSettings(context.getProject()).METHOD_PARAMETERS_LPAREN_ON_NEXT_LINE) {
            return null;
        }

        return super.findNextToken(context);
    }

}


