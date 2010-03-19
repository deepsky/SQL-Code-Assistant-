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

package com.deepsky.findUsages.search;

import com.deepsky.findUsages.persistence.SqlSearchParameters;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class ViewColumnSearchOptions  implements GenericSearchOptions{

    VColumnDefinition cdef;

    public ViewColumnSearchOptions(VColumnDefinition vcolumnDef) {
        this.cdef = vcolumnDef;
    }

    @NotNull
    public PsiElement getTarget() {
        return cdef;
    }

    public String getSearchTitle() {
        return "Column " + cdef.getColumnName().toUpperCase() + " of view " + cdef.getViewDefinition().getViewName().toUpperCase();
    }

    @NotNull
    public SearchOption[] getSearchOptions() {
        SqlSearchParameters searchParams = SqlSearchParameters.getInstance(cdef.getProject());
        SearchOption[] out = new SearchOption[2];
        out[0] = new SearchOptionImpl("Usages", searchParams.isColumnUsages, 0);
        out[1] = new SearchOptionImpl("Usages in Type Definitions", searchParams.isUsagesOfColumnTypeRef, 1);
        return out;
    }

    @NotNull
    public String getTargetNode() {
        return "Column of View";
    }

    @NotNull
    public String getTargetPresentableText() {
        return  cdef.getColumnName().toUpperCase() + "of view " + cdef.getViewDefinition().getViewName().toUpperCase();
    }

    @NotNull
    public String getPresentableSearchParameters() {
        //"Usages of Column RESOURCE_NAME in Database Schema"
        return "Usages of Column " + cdef.getColumnName().toLowerCase();
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
            SqlSearchParameters searchParams = SqlSearchParameters.getInstance(cdef.getProject());
            if(tag == 0){
                searchParams.isColumnUsages = value;
            } else if(tag == 1){
                searchParams.isUsagesOfColumnTypeRef = value;
            }
        }

        public String getOptionName() {
            return opName;
        }
    }
}
