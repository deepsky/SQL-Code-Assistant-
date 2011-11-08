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

package com.deepsky.findUsages.persistence;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.xmlb.XmlSerializerUtil;

@State(
        name = "SqlSearchParameters",
        storages = {
                @Storage(
                        id = "myId",
                        file = "$WORKSPACE_FILE$"
                )}
)
public class SqlSearchParameters implements PersistentStateComponent<SqlSearchParameters> {

    public boolean isTableUsages = true;
    public boolean isViewUsages = true;
    public boolean isColumnUsages = true;
    public boolean isPackageUsages = true;
    public boolean isFunctionUsages = true;
    public boolean isProcedureUsages = true;
    public boolean isVariableUsages = true;
    public boolean isSequenceUsages = true;

    // Package specific usages options
    public boolean isUsagesOfProcedures = false;
    public boolean isUsagesOfFunctions = false;
    // package scope variables
    public boolean isUsagesOfVariables = false;
    public boolean isUsagesOfTypes = false;
    public boolean isUsagesOfColumnTypeRef = false;

    // Column name specific usages options
    public boolean isQueryUsages = false;
    public boolean isDDLUsages = false;
    public boolean isDMLUsages = false;
    public boolean isUsagesInType = false;

    // Table specific usages options
    public boolean isUsagesOfColumns = false;

    public SearchScope searchScope;

    public static SqlSearchParameters getInstance(Project project) {
        return ServiceManager.getService(project, SqlSearchParameters.class);
    }

    public SqlSearchParameters getState() {
        // innocuous trick to avoid serialization of SearchScope object
        SqlSearchParameters out = new SqlSearchParameters();
        XmlSerializerUtil.copyBean(this, out);
        out.searchScope = null;
        return out;
    }

    public void loadState(SqlSearchParameters state) {
        XmlSerializerUtil.copyBean(state, this);
    }

}
