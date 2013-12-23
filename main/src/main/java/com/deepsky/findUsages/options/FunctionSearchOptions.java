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

package com.deepsky.findUsages.options;

import com.deepsky.lang.plsql.psi.Function;
import com.deepsky.lang.plsql.psi.FunctionSpec;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class FunctionSearchOptions extends AbstractSqlSearchOptions {

    Function function;
    FunctionSpec functionSpec;

    public FunctionSearchOptions(Function function) {
        this.function = function;
    }

    public FunctionSearchOptions(FunctionSpec functionSpec) {
        this.functionSpec = functionSpec;
    }

    @NotNull
    public PsiElement getTargetElement() {
        return function != null? function: functionSpec;
    }

    @NotNull
    public PsiElement[] getElementsToSearch() {
        // todo -- implement me
        return new PsiElement[]{getTargetElement()};
    }
    

    public String getSearchTitle() {
//        String packageName = getPackage();
//        if(packageName != null){
//            return "Function " + formatSign().toUpperCase() + " of package " + packageName.toUpperCase();
//        } else {
//            return "Function " + formatSign().toUpperCase();
//        }

        return "Function " + formatSearchString();
    }

    @NotNull
    public SearchOption[] getSearchOptions() {
        return new SearchOption[0];
    }

    @NotNull
    public String getTargetNode() {
        return "Function";
    }

    @NotNull
    public String getTargetPresentableText() {
        return formatSearchString(); //function!= null? function.getEName().toUpperCase(): functionSpec.getEName().toUpperCase();
    }

    @NotNull
    public String getPresentableSearchParameters() {

        return "Usages of function " + formatSign();
    }

    private String formatSearchString(){
        String packageName = getPackage();
        if(packageName != null){
            return formatSign() + " of package " + packageName;
//            return formatSign().toLowerCase() + " of package " + packageName.toLowerCase();
        } else {
            return formatSign();
//            return formatSign().toLowerCase();
        }
    }

    private String formatSign(){
        return (function != null)? Formatter.formatSignature(function, 60): Formatter.formatSignature(functionSpec, 60);
    }

    private String getPackage(){
        if(function != null){
            return function.getPackageName();
        } else {
            return functionSpec.getPackageName();
        }
    }


}

