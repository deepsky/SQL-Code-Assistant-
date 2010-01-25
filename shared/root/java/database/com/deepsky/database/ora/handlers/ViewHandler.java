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

package com.deepsky.database.ora.handlers;

import com.deepsky.database.ora.*;
import com.deepsky.database.ora.desc.ViewDescriptorImpl;
import com.deepsky.database.DBException;
import com.deepsky.database.ResultSetHelper;
import com.deepsky.database.MappingHelper;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.utils.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewHandler implements BaseHandler {

    static LoggerProxy log = LoggerProxy.getInstance("#ViewHandler");

    final String listViewSql =
//            "SELECT c.OWNER, c.TABLE_NAME, c.COLUMN_NAME, c.DATA_TYPE, c.DATA_LENGTH, c.NULLABLE, c.COLUMN_ID,\n" +
//                    "v.TEXT\n" +
//                    "FROM ALL_TAB_COLUMNS c, all_objects a, SYS.VIEW$ v\n" +
//                    "WHERE c.OWNER IN (<OWNERS>) AND c.TABLE_NAME IN (<NAMES>)\n" +
//                    "AND a.OBJECT_NAME = c.TABLE_NAME AND a.OBJECT_TYPE = 'VIEW'\n" +
//                    "AND a.OBJECT_ID = v.OBJ#\n" +
//                    "ORDER BY c.OWNER, c.TABLE_NAME, c.COLUMN_ID";

// todo - loading of the VIEW source is not supported at the moment - time consuming task !!!
            "SELECT c.OWNER, c.TABLE_NAME, c.COLUMN_NAME, c.DATA_TYPE, c.DATA_LENGTH, c.NULLABLE, c.COLUMN_ID " +
            "FROM ALL_TAB_COLUMNS c " +
            "WHERE c.OWNER IN (<OWNERS>) AND c.TABLE_NAME IN (<NAMES>) " +
            "ORDER BY c.OWNER, c.TABLE_NAME, c.COLUMN_ID";

    final String listViewSourceSql =
            "SELECT a.OBJECT_NAME as TABLE_NAME, v.TEXT as TEXT\n" +
                    "FROM all_objects a, ALL_VIEWS v\n" +
                    "WHERE a.OWNER IN (<OWNERS>) AND a.OBJECT_NAME IN (<NAMES>)\n" +
                    "AND a.OBJECT_TYPE = 'VIEW'\n" +
                    "AND a.OBJECT_NAME = v.VIEW_NAME\n" +
                    "ORDER BY a.OWNER";


    public String getId() {
        return "VIEW";
    }

    public List<DbObjectEx> load(final ConnectionHolder conn, Mix mix) throws DBException {
        String sql = listViewSql.replace("<NAMES>", StringUtils.convert2listStrings(mix.names));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(mix.owners));
        ResultSetHelper rsHlp = new ResultSetHelper(conn.getConnection());

        // load VIEW descriptors
        log.info( "VIEWS: " + StringUtils.convert2listStrings(mix.names));

        final Map<String, DbObjectEx> tables = new HashMap<String, DbObjectEx>();
        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String owner = rs.getString("OWNER");
                    String name = rs.getString("TABLE_NAME");
                    String column = rs.getString("COLUMN_NAME");
                    String data_type = rs.getString("DATA_TYPE");
                    int data_len = rs.getInt("DATA_LENGTH");
                    String nullable = rs.getString("NULLABLE");
                    long columnId = rs.getLong("COLUMN_ID");

                    DbObjectEx dbo = tables.get(name);
                    if (dbo == null) {
                        ViewDescriptorImpl view = new ViewDescriptorImpl(
                                new DbObjectScriptLocator(conn.getDbUrl(), DbObject.VIEW, name),
                                name
                        );

                        // todo -- no source!
                        dbo = new DbObjectEx(owner, view, null);
                        tables.put(name, dbo);
                    }
                    ViewDescriptorImpl tab = (ViewDescriptorImpl) dbo.dbo;
                    Type t = TypeFactory.createTypeByName(data_type, data_len);
                    tab.addColumn(column, t, "Y".equals(nullable), (int) columnId);
                }
            });

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }


        sql = listViewSourceSql.replace("<NAMES>", StringUtils.convert2listStrings(mix.names));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(mix.owners));

        ResultSetHelper rsHlp2 = new ResultSetHelper(conn.getConnection());
        // load VIEW text
        try {
            rsHlp2.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String name = rs.getString("TABLE_NAME");
                    String text = rs.getString("TEXT");

                    DbObjectEx ex = tables.get(name);
                    if (ex != null && text != null) {
                        ex.setSource(
                            new ViewScriptGenerator(text).generate(ex.dbo)
                        );
                    }
                }
            });

            List<DbObjectEx> out = new ArrayList<DbObjectEx>();
            for (DbObjectEx ex : tables.values()) {
                out.add(ex);
            }

            return out;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

    }

    public void setListener(ParseEventListener listener) {
        // not support
    }
}
