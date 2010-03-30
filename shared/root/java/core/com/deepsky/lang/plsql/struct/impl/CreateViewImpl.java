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

package com.deepsky.lang.plsql.struct.impl;


import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import org.jetbrains.annotations.NotNull;


public class CreateViewImpl extends PsiElementDumb implements CreateView {

    private String name;
    private SelectStatement select;
    private VColumnDefinition[] vcolumns;

    public CreateViewImpl() {
        super(PLSqlTypesAdopted.CREATE_VIEW);
    }

    @NotNull
    public String getViewName() {
        return name;
    }

    @NotNull
    public SelectStatement getSelectExpr() {
        return select;
    }

    @NotNull
    public VColumnDefinition[] getColumnDefs() {
        return vcolumns; 
    }

    public VColumnDefinition getColumnByName(String name) {
        for (VColumnDefinition v : vcolumns) {
            if (v.getColumnName().equalsIgnoreCase(name)) {
                return v;
            }
        }
        return null;
    }

    public int getColumnPos(String columnName) {
        int pos = 0;
        for (VColumnDefinition v : vcolumns) {
            if (v.getColumnName().equalsIgnoreCase(columnName)) {
                return pos;
            }

            pos++;
        }

        return -1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSelect(SelectStatement select) {
        this.select = select;
    }

    public void setVcolumns(VColumnDefinition[] vcolumns) {
        this.vcolumns = vcolumns;
    }
}
