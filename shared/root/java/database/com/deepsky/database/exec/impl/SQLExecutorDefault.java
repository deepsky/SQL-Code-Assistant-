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
import com.deepsky.database.exec.SQLUpdateStatistics;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.intellij.openapi.project.Project;
import oracle.sql.CLOB;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
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

/*
    public SQLUpdateStatistics generateDDLScript(@NotNull String objectType, @NotNull String objectName) throws DBException {

        String select = "select dbms_metadata.get_ddl( '<OBJECT_TYPE>', '<OBJECT_NAME>' ) from dual";
        select = select.replace("<OBJECT_TYPE>", objectType.toUpperCase());
        select = select.replace("<OBJECT_NAME>", objectName.toUpperCase());

        RowSetManager rsManager = executeQuery(select);
        try {
            if (rsManager.getModel().getFetchedRowCount() == 1) {
                Object o = rsManager.getModel().getValueAt(0, 1);
                if (o instanceof CLOB) {
                    return new DDLScript(1, 0, "DDL script", clob2string((CLOB) o));
                }
            }
        } catch (SQLException e) {
            throw new DBException("Could not create DDL script for " + objectName.toUpperCase() + ". " + e.getMessage());
        } finally {
            rsManager.getModel().close();
        }

        throw new DBException("Could not create DDL script for " + objectName.toUpperCase() + ".");
    }


    private class DDLScript implements SQLUpdateStatistics {

        int rowsAffected;
        long timeSpent;
        String message;
        String errors;

        public DDLScript(int rowsAffected, long timeSpent, String message, String errors) {
            this.rowsAffected = rowsAffected;
            this.timeSpent = timeSpent;
            this.message = message;
            this.errors = errors;
        }

        public int rowsAffected() {
            return rowsAffected;
        }

        public long timeSpent() {
            return timeSpent;
        }

        public String errorMessages() {
            return errors;
        }

        public String resultMessage() {
            return message;
        }
    }


    private String clob2string(CLOB blob) throws SQLException {
        if (blob == null || blob.isEmptyLob() || blob.length() == 0) {
            return "";
        } else {
            InputStream r = blob.getAsciiStream();
            ByteArrayOutputStream writer = new ByteArrayOutputStream();
            byte[] buff = new byte[1000];
            int size = 0;
            try {
                while ((size = r.read(buff)) > 0) {
                    writer.write(buff, 0, size);
                }

                // Converts the buffer's contents into a string decoding bytes using the
                // platform's default character set.
                return writer.toString();
            } catch (IOException e) {
                throw new SQLException(e.getMessage());
            } finally {
                if(r != null){
                    try {
                        r.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }
    }
*/

}
