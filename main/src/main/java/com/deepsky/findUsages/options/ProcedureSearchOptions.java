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

import com.deepsky.lang.plsql.psi.Procedure;
import com.deepsky.lang.plsql.psi.ProcedureSpec;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class ProcedureSearchOptions extends AbstractSqlSearchOptions {

    Procedure procedure;
    ProcedureSpec procedureSpec;

    public ProcedureSearchOptions(Procedure procedure) {
        this.procedure = procedure;
    }

    public ProcedureSearchOptions(ProcedureSpec procedureSpec) {
        this.procedureSpec = procedureSpec;
    }

    @NotNull
    public PsiElement getTargetElement() {
        return procedure != null? procedure: procedureSpec;
    }

    @NotNull
    public PsiElement[] getElementsToSearch() {
        // todo -- implement me
        return new PsiElement[]{getTargetElement()};
    }
    
    public String getSearchTitle() {
        return "Procedure " + formatSearchString();

//        String packageName = getPackage();
//        if(packageName != null){
//            return "Procedure " + formatSign().toLowerCase() + " of package " + packageName.toLowerCase();
//        } else {
//            return "Procedure " + formatSign().toLowerCase();
//        }
    }

    @NotNull
    public SearchOption[] getSearchOptions() {
        return new SearchOption[0];
    }

    @NotNull
    public String getTargetNode() {
        return "Procedure";
    }

    @NotNull
    public String getTargetPresentableText() {
        return formatSearchString(); //procedure!=null? procedure.getEName(): procedureSpec.getEName();
    }

    @NotNull
    public String getPresentableSearchParameters() {

        return "Usages of procedure " + formatSign();
    }

    private String formatSign(){
        return (procedure != null)? Formatter.formatSignature(procedure, 60): Formatter.formatSignature(procedureSpec, 60);
    }

    private String formatSearchString(){
        String packageName = getPackage();
        if(packageName != null){
//            return formatSign().toLowerCase() + " of package " + packageName.toLowerCase();
            return formatSign() + " of package " + packageName;
        } else {
//            return formatSign().toLowerCase();
            return formatSign();
        }
    }

    private String getPackage(){
        if(procedure != null){
            return procedure.getPackageName();
        } else {
            return procedureSpec.getPackageName();
        }
    }

}


