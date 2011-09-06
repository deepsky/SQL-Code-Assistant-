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

package com.deepsky.database.exec.impl;

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.DBException;
import com.deepsky.database.exec.RowSetManager;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.psi.Subquery;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.intellij.openapi.project.Project;

import java.sql.Connection;
import java.text.SimpleDateFormat;

public class SQLExecutorDefault extends SQLExecutorSimple {

    private Project project;
    private DbUrl dbUrl;

    public SQLExecutorDefault(Project project, DbUrl dbUrl, Connection conn, ConnectionManager connectionManager) {
        super(conn, connectionManager);
        this.project = project;
        this.dbUrl = dbUrl;

        SqlCodeAssistantSettings bean = PluginKeys.PLUGIN_SETTINGS.getData(project);
        String format = bean.getDateFormat() + " " + bean.getTimeFormat();
        dateToString = new SimpleDateFormat(format);

        format = format.replace("ss", "ss\\.SSS");
        timestampToString = new SimpleDateFormat(format);

        format = format + " Z";
        timestampTZToString = new SimpleDateFormat(format);

        SHUNK_SIZE = bean.getFetchRecords();

    }

    @Override
    public ResolveFacade getDefaultResolver() {
        IndexManager indexMan = PluginKeys.SQL_INDEX_MAN.getData(project);
        ResolveFacade domainResolver = indexMan
                            .findOrCreateIndex(dbUrl, 0)
                            .getResolveFacade();
        return domainResolver;
    }

}
