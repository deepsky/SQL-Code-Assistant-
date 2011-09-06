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

package com.deepsky.database.ora2.loaders;

import com.deepsky.database.DBException;
import com.deepsky.database.MappingHelper;
import com.deepsky.database.ResultSetHelper;
import com.deepsky.database.ora.Mix;
import com.deepsky.database.ora2.DbObjectLoader;
import com.deepsky.database.ora2.DbObjectSpec;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewLoader extends DbObjectLoaderAbstract {

    static LoggerProxy log = LoggerProxy.getInstance("#ViewLoader");

//    static final public String VIEW_INTERNAL_TYPE = "VIEW_INTERNAL";

    final String listViewSql =
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
        return DbObject.VIEW;
    }

    public List<DbObjectSpec> load(Connection conn, Mix mix) throws DBException {
        String sql = listViewSql.replace("<NAMES>", StringUtils.convert2listStrings(mix.names));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(mix.owners));
        ResultSetHelper rsHlp = new ResultSetHelper(conn);

        log.info( "VIEWS: " + StringUtils.convert2listStrings(mix.names));
        final List<DbObjectSpec> out = new ArrayList<DbObjectSpec>();

        // collect view columns
        final Map<String, List<ColumnDef>> tables = new HashMap<String, List<ColumnDef>>();
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

                    List<ColumnDef> columns = tables.get(name);
                    if (columns == null) {
                        columns = new ArrayList<ColumnDef>();
                        tables.put(name, columns);
                    }
                    Type t = TypeFactory.createTypeByName(data_type, data_len);
                    columns.add(new ColumnDef(column, t.toString()));
                }
            });

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }


        sql = listViewSourceSql.replace("<NAMES>", StringUtils.convert2listStrings(mix.names));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(mix.owners));

        ResultSetHelper rsHlp2 = new ResultSetHelper(conn);
        // load VIEW text
        try {
            rsHlp2.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String name = rs.getString("TABLE_NAME");
                    String text = rs.getString("TEXT");

                    List<ColumnDef> columns = tables.get(name);
                    if (columns != null && text != null) {
                        out.add(new DbObjectSpec(
                                DbObject.VIEW,
                                name,
                                createViewScript(name, text, columns)
                            ));

                        out.add(new DbObjectSpec(
                                DbObject.VIEW_INTERNAL_TYPE,
                                name,
                                createViewInternalScript(name, columns)
                            ));
                    }
                }
            });

            return out;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }


    private String createViewInternalScript(String viewName, List<ColumnDef> columns){
        String source = "create view_column_def_$internal$ " + viewName + "\n";
        source += "(";
        int i = 0;
        for (ColumnDef column : columns) {
            if (i++ != 0) {
                source += ", ";
            }
            source += column.name;
            source += " ";
            source += column.columnType;

            source += "\n";
        }
        source += ");\n";
        return source;
    }

    private String createViewScript(String viewName, String text, List<ColumnDef> columns){
        String source = "CREATE OR REPLACE VIEW " + viewName + "\n";
        source += "(";
        int i = 0;
        for (ColumnDef column : columns) {
            if (i++ != 0) {
                source += ", ";
            }
            source += column.name;
            if ((i % 6) == 0) {
                source += "\n";
            }
        }
        source += ") AS\n";
        source += text;
        return source;
    }


    class ColumnDef {
        public String name;
        public String columnType;

        public ColumnDef(String name, String columnType){
            this.columnType = columnType;
            this.name = name;
        }
    }
}
