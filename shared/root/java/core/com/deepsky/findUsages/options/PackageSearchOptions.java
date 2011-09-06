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

import com.deepsky.findUsages.persistence.SqlSearchParameters;
import com.deepsky.lang.plsql.psi.PackageSpec;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PackageSearchOptions extends AbstractSqlSearchOptions {

//    String packageName;
//    Project project;
    PackageSpec packageSpec;

    public PackageSearchOptions(PackageSpec packageSpec){ //Project project, String packageName){
        this.packageSpec = packageSpec;
    }

//    public PackageSearchOptions(Project project, String packageName){
//        this.packageName = packageName;
//        this.project = project;
//    }

    @NotNull
    public PsiElement getTargetElement() {
        return packageSpec;
    }

    @NotNull
    public PsiElement[] getElementsToSearch() {
        List<PsiElement> out = new ArrayList<PsiElement>();
        SqlSearchParameters searchParams = SqlSearchParameters.getInstance(packageSpec.getProject());
        if(searchParams.isPackageUsages){
            out.add(packageSpec);
        }

        if(searchParams.isUsagesOfProcedures){
            out.addAll(Arrays.asList(packageSpec.getProcedurSpecList()));
        }

        if(searchParams.isUsagesOfFunctions){
            out.addAll(Arrays.asList(packageSpec.getFunctionSpecList()));
        }

        if(searchParams.isUsagesOfVariables){
            out.addAll(Arrays.asList(packageSpec.getVariableList()));
        }

        if(searchParams.isUsagesOfTypes){
            out.addAll(Arrays.asList(packageSpec.getTypeList()));
        }

        return out.toArray(new PsiElement[out.size()]);
    }
    
    public String getSearchTitle() {
        return "Package " + packageSpec.getPackageName(); //packageName.toUpperCase();
    }

    @NotNull
    public SearchOption[] getSearchOptions() {

        Project project = packageSpec.getProject();
        SqlSearchParameters searchParams = SqlSearchParameters.getInstance(project);
        SearchOption[] out = new SearchOption[5];
        out[0] = new SearchOptionImpl("Usages", searchParams.isPackageUsages, 0);
        out[1] = new SearchOptionImpl("Usages of Procedures", searchParams.isUsagesOfProcedures, 1);
        out[2] = new SearchOptionImpl("Usages of Functions", searchParams.isUsagesOfFunctions, 2);
        out[3] = new SearchOptionImpl("Usages of Variables", searchParams.isUsagesOfVariables, 3);
        out[4] = new SearchOptionImpl("Usages of Types", searchParams.isUsagesOfTypes, 4);
        return out;
    }

    @NotNull
    public String getTargetNode() {
        return "Package ";
    }

    @NotNull
    public String getTargetPresentableText() {
        return "Package " + packageSpec.getPackageName();
    }

    @NotNull
    public String getPresentableSearchParameters() {
        return "Usage of Package " + packageSpec.getPackageName();
    }

    class SearchOptionImpl implements SearchOption {
        String opName;
        boolean enabled;
        int tag;

        public SearchOptionImpl(String opName, boolean enabled, int tag){
            this.opName = opName;
            this.enabled = enabled;
            this.tag = tag;
        }
        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean value) {
            enabled = value;
            SqlSearchParameters searchParams = SqlSearchParameters.getInstance(packageSpec.getProject());

            switch(tag){
                case 0: searchParams.isPackageUsages = value;
                case 1: searchParams.isUsagesOfProcedures = value;
                case 2: searchParams.isUsagesOfFunctions = value;
                case 3: searchParams.isUsagesOfVariables = value;
                case 4: searchParams.isUsagesOfTypes = value;
            }
        }

        public String getOptionName() {
            return opName;
        }
    }

}
