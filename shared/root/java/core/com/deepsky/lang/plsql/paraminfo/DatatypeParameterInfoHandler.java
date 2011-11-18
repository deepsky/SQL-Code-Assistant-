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

import com.deepsky.lang.plsql.psi.types.DataType;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiSubstitutor;
import com.intellij.psi.infos.CandidateInfo;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;

public class DatatypeParameterInfoHandler implements ParameterInfoHandler {
    public boolean couldShowInLookup() {
        return true;
    }

    public Object[] getParametersForLookup(LookupElement item, ParameterInfoContext context) {
        return new Object[0];
    }

    public Object[] getParametersForDocumentation(Object p, ParameterInfoContext context) {
        return ArrayUtil.EMPTY_OBJECT_ARRAY;
    }

    public Object findElementForParameterInfo(CreateParameterInfoContext context) {
        PsiElement el = context.getFile().findElementAt(context.getOffset()).getParent();
        if(el instanceof DataType){
            datatype = (DataType) el;
            context.setItemsToShow(create(el));
            return datatype;
        }
        return null;
    }

    public void showParameterInfo(@NotNull Object element, CreateParameterInfoContext context) {
        context.showHint((PsiElement) element, 1, this);
    }

    DataType datatype;

    public Object findElementForUpdatingParameterInfo(UpdateParameterInfoContext context) {
        PsiElement candidate = findDatatype(
                context.getFile(), context.getOffset(), context.getParameterListStart()
                );

        int offset = context.getEditor().getCaretModel().getOffset();
        if(candidate == datatype && cursorInsideParentasis(datatype, offset)){ //instanceof DataType){
            return candidate;
        }
        return null;
    }

    private boolean cursorInsideParentasis(DataType datatype, int offset) {
        int offsetInParent = offset - datatype.getTextOffset();
        if(offsetInParent > 0){
            String text = datatype.getText();
            if( text.indexOf('(') < offsetInParent && text.indexOf(')') >= offsetInParent){
                return true;
            }
        }
        return false;
    }

    private PsiElement findDatatype(final PsiFile file, int offset, int parameterStart) {
        PsiElement ele = file.findElementAt(offset);
        return ele.getParent();
    }

    public void updateParameterInfo(@NotNull Object o, UpdateParameterInfoContext context) {
        int h =0;
    }

    public String getParameterCloseChars() {
        return ")";//ParameterInfoUtils.DEFAULT_PARAMETER_CLOSE_CHARS;
    }

    public boolean tracksParameterIndex() {
        return false;
    }

    public void updateUI(Object p, ParameterInfoUIContext context) {
        StringBuffer buffer = new StringBuffer("number");
        context.setupUIComponentPresentation(
          buffer.toString(),
          0,
          buffer.length(),
          !context.isUIComponentEnabled(),
          false, //method.isDeprecated(),
          false,
          context.getDefaultParameterColor()
        );
    }

    CandidateInfo[] create(PsiElement el){
        return new CandidateInfo[]{
                new CandidateInfo(el, PsiSubstitutor.EMPTY)
        };
    }
}
