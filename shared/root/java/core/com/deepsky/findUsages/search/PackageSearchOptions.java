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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.findUsages.search;

import com.deepsky.findUsages.persistence.SqlSearchParameters;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class PackageSearchOptions implements GenericSearchOptions {

    String packageName;
    public PackageSearchOptions(String packageName){
        this.packageName = packageName;
    }

    @NotNull
    public PsiElement getTarget() {
        return null;
    }

    public String getSearchTitle() {
        return "Package " + packageName.toUpperCase();
    }

    @NotNull
    public SearchOption[] getSearchOptions() {
        SqlSearchParameters searchParams = SqlSearchParameters.getInstance();
        SearchOption[] out = new SearchOption[4];
        out[0] = new SearchOptionImpl("Usages", searchParams.isPackageUsages);
        out[1] = new SearchOptionImpl("Usages of Procedures", searchParams.isUsagesOfProcedures);
        out[2] = new SearchOptionImpl("Usages of Functions", searchParams.isUsagesOfFunctions);
        out[3] = new SearchOptionImpl("Usages of Variables", searchParams.isUsagesOfVariables);
        return out;
    }

    @NotNull
    public String getTargetNode() {
        return "Package";
    }

    @NotNull
    public String getTargetPresentableText() {
        return "Target text";
    }

    @NotNull
    public String getPresentableSearchParameters() {
        return "Usage of Package ...";
    }

    class SearchOptionImpl implements SearchOption {
        String opName;
        boolean enabled;

        public SearchOptionImpl(String opName, boolean enabled){
            this.opName = opName;
            this.enabled = enabled;
        }
        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean value) {
            enabled = value;

            //todo -- implement me
        }

        public String getOptionName() {
            return opName;
        }
    }

}
