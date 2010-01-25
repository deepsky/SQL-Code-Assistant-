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

package com.deepsky.lang.plsql.struct.impl;

import com.deepsky.lang.plsql.psi.TempTableDefinition;
import com.deepsky.lang.plsql.psi.ColumnDefinition;
import com.deepsky.lang.plsql.psi.GenericConstraint;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TempTableDefinitionImpl extends PsiElementDumb implements TempTableDefinition {

    String tableName;
    ColumnDefinition[] columns;
    List<GenericConstraint> constraints = new ArrayList<GenericConstraint>();

    boolean isGlobal;

    public TempTableDefinitionImpl(String tableName){
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    @NotNull
    public ColumnDefinition[] getColumnDefs() {
        return columns;
    }

    public void setColumnDefs(ColumnDefinition[] columns) {
        this.columns = columns;
    }

    public ColumnDefinition getColumnByName(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public GenericConstraint[] getConstraints() {
        return constraints.toArray(new GenericConstraint[0]);
    }

    public void addConstraint(GenericConstraint constr) {
        constraints.add(constr);
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean isGlobal){
        this.isGlobal = isGlobal;
    }
}
