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
import com.deepsky.database.exec.SQLExecutor;
import com.deepsky.database.exec.RowSetModel;
import com.deepsky.database.exec.TableResizeListener;
import com.deepsky.database.exec.SQLUpdateStatistics;
import com.deepsky.database.DBException;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.conf.PluginSettingsBean;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.intellij.openapi.project.Project;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class SQLExecutorDefault implements SQLExecutor {

    Connection conn;
    ConnectionManager connectionManager;
    java.sql.Statement stmt;
    ResultSet rs;
    String sql = null;

    boolean canceled = false;

    SimpleDateFormat dateToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    SimpleDateFormat timestampToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    SimpleDateFormat timestampTZToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS Z");

    RowSetModel rsmodel;
    int SHUNK_SIZE = 200;

    public SQLExecutorDefault(Project project, Connection conn, ConnectionManager connectionManager) {
        this.conn = conn;
        this.connectionManager = connectionManager;

//        PluginSettingsBean bean = (PluginSettingsBean) SharedObjectPool.getUserData(SharedConstants.PLUGIN_SETTINGS);
        PluginSettingsBean bean = PluginKeys.PLUGIN_SETTINGS.getData(project);
                
        if (bean == null) {
            bean = new PluginSettingsBean();
        }

        String format = bean.getDateFormat() + " " + bean.getTimeFormat();
        dateToString = new SimpleDateFormat(format);

        format = format.replace("ss", "ss\\.SSS");
        timestampToString = new SimpleDateFormat(format);

        format = format + " Z";
        timestampTZToString = new SimpleDateFormat(format);

        SHUNK_SIZE = bean.getFetchRecords();
    }

    public RowSetModel executeQuery(@NotNull String select) throws DBException {
        // trim semicolon if exists
        sql = select.endsWith(";")? select.substring(0, select.length()-1): select; //.getText();
        // load the result set with the shunk size
        if (rsmodel != null) {
            rsmodel.close();
        }
        rsmodel = new RowModelImpl();
        return rsmodel;
    }

    private String adoptOrderByClause(String sql_text, int columnId, int dir) {
        String hh = ".*(order[ \n]+by.+)$";
        Pattern p = Pattern.compile(hh);
        Matcher m1 = p.matcher(sql_text.replace("\n", " "));
        String cuttedSql = sql_text;
        if(m1.find()){
            cuttedSql = sql_text.substring(0, m1.start(1));
        }

        cuttedSql += " ORDER BY " + (columnId + 1) + ((dir == RowSetModel.DESCENDING) ? " DESC" : " ASC");
        return cuttedSql;

    }

    public SQLUpdateStatistics execute(String statement, IElementType etype) throws DBException {
        String responseMessage = "";
        int size = 0;

        // close result set if exists
        close();

        long ms = System.currentTimeMillis();

        // DML statement processing
        if (etype == PLSqlTypesAdopted.DELETE_COMMAND){
            size = executeUpdate(statement);
            responseMessage = size + " rows deleted";
        } else if ( etype == PLSqlTypesAdopted.INSERT_COMMAND){
            size = executeUpdate(statement);
            responseMessage = size + " rows inserted";
        } else if (etype == PLSqlTypesAdopted.UPDATE_COMMAND){
            size = executeUpdate(statement);
            responseMessage = size + " rows updated";
        } else if (etype == PLSqlTypesAdopted.MERGE_COMMAND){
            size = executeUpdate(statement);
            responseMessage = size + " rows updated";

            // DDL statement processing
        } else if (etype == PLSqlTypesAdopted.TRUNCATE_TABLE){
            size = executeUpdate(statement);
            responseMessage = "Table truncated";
        } else if (etype == PLSqlTypesAdopted.TABLE_DEF){
            size = executeUpdate(statement);
            responseMessage = "Table created";
        } else if (etype == PLSqlTypesAdopted.CREATE_TEMP_TABLE){
            size = executeUpdate(statement);
            responseMessage = "Table created";
        } else if (etype == PLSqlTypesAdopted.CREATE_VIEW) {
            size = executeUpdate(statement);
            responseMessage = "View created";
        } else if (etype == PLSqlTypesAdopted.CREATE_DIRECTORY) {
            size = executeUpdate(statement);
            responseMessage = "Directory created";
        } else if (etype == PLSqlTypesAdopted.ALTER_TABLE) {
            size = executeUpdate(statement);
            responseMessage = "Table altered";
        } else if (etype == PLSqlTypesAdopted.CREATE_SEQUENCE) {
            size = executeUpdate(statement);
            responseMessage = "Sequence created";
        } else if (etype == PLSqlTypesAdopted.CREATE_INDEX) {
            size = executeUpdate(statement);
            responseMessage = "Index created";
        } else if (etype == PLSqlTypesAdopted.DROP_TABLE) {
            size = executeUpdate(statement);
            responseMessage = "Table dropped";
        } else if (etype == PLSqlTypesAdopted.DROP_VIEW) {
            size = executeUpdate(statement);
            responseMessage = "View dropped";
        } else if (etype == PLSqlTypesAdopted.DROP_FUNCTION) {
            size = executeUpdate(statement);
            responseMessage = "Function dropped";
        } else if (etype == PLSqlTypesAdopted.DROP_PROCEDURE) {
            size = executeUpdate(statement);
            responseMessage = "Procedure dropped";
        } else if (etype == PLSqlTypesAdopted.DROP_PACKAGE) {
            size = executeUpdate(statement);
            responseMessage = "Package dropped";
        } else if (etype == PLSqlTypesAdopted.DROP_SEQUENCE) {
            size = executeUpdate(statement);
            responseMessage = "Sequence dropped";
        } else if (etype == PLSqlTypesAdopted.COMMIT_STATEMENT) {
            size = executeUpdate(statement);
            responseMessage = "Commited";
        } else if (etype == PLSqlTypesAdopted.ROLLBACK_STATEMENT) {
            size = executeUpdate(statement);
            responseMessage = "Rollbacked";
        } else {
            throw new NotSupportedException("Specified SQL statement not supported");
        }
        ms = System.currentTimeMillis() - ms;

        connectionManager.addProcessedStatement(statement, etype);
        return new SQLUpdateStatisticsImpl(size, ms, responseMessage);
    }

    private int executeUpdate(String _text) throws DBException {
        String trimmed = _text.trim();
        String text = trimmed.endsWith(";")? trimmed.substring(0, trimmed.length()-1): trimmed;
        try {
            stmt = conn.createStatement();
            int size = stmt.executeUpdate(text);

            return size;
        } catch (SQLException e) {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e1) {
                }
            }

            throw new DBException(e.getMessage());
        }
    }

    public void close() {
        if (rsmodel != null) {
            rsmodel.close();
            rsmodel = null;
        }
    }

    class SQLUpdateStatisticsImpl implements SQLUpdateStatistics {

        int rowsAffected;
        long timeSpent;
        String message;
        public SQLUpdateStatisticsImpl(int rowsAffected, long timeSpent, String message){
            this.rowsAffected = rowsAffected;
            this.timeSpent =timeSpent;
            this.message = message;
        }
        public int rowsAffected() {
            return rowsAffected;
        }

        public long timeSpent() {
            return timeSpent;
        }

        public String resultMessage() {
            return message;
        }
    }

    class RecordSetWrapper {
        int[] columnTypes;
        boolean completed = false;
        List<List> table = new ArrayList<List>();
        List<String> columnNames = new ArrayList<String>();
        long timeSpentOnFetch = 0;
        TableResizeListener listener;

        public RecordSetWrapper(String sql) throws DBException {
            long ms = System.currentTimeMillis();
            try {
                stmt = conn.createStatement();
                stmt.setFetchSize(SHUNK_SIZE + 10);
                rs = stmt.executeQuery(sql);

                ResultSetMetaData meta = rs.getMetaData();
                columnTypes = new int[meta.getColumnCount()+1];
                for (int i = 0; i < columnTypes.length-1; i++) {
                    String label = meta.getColumnLabel(i + 1);
                    columnNames.add(label);
                    columnTypes[i+1] = meta.getColumnType(i + 1);
                }

                __ASSERT_CANCEL__();
            } catch (SQLException e) {
                close();
                throw new DBException("Could not execute query. " + e.getMessage());
            }

            loadNextShunk();
            timeSpentOnFetch = System.currentTimeMillis() - ms;
        }

        private void __ASSERT_CANCEL__() throws SQLException {
            if(canceled){
                throw new SQLException("SQL query canceled by user request.");
            }
        }

        public long timeSpentOnFetch() {
            return timeSpentOnFetch;
        }

        public List<String> getColumnNames() {
            return columnNames;
        }

        void loadNextShunk() throws DBException {
            try {
                int counter = 0;
                while (rs.next()) {

                    __ASSERT_CANCEL__();

                    List row = new ArrayList();
                    int colnum = columnTypes.length-1;
                    for (int i = 1; i <= colnum; i++) {
                        switch(columnTypes[i]){
                            case 91: { // date
                                Date d0 = rs.getDate(i);
                                if(d0 != null){
                                    long d = d0.getTime();
                                    long t = rs.getTime(i).getTime();
                                    row.add(dateToString.format(new Date(d+t)));
                                } else {
                                    row.add(null);
                                }
                                break;
                            }
                            case 93: { // timestamp without timezone ?
                                Timestamp t = rs.getTimestamp(i);
                                if(t != null){
                                    row.add(timestampToString.format(t));
                                } else {
                                    row.add(null);
                                }
                                break;
                            }
                            case -101: { // timestamp with timezone ?
                                Timestamp t = rs.getTimestamp(i);
                                if(t != null){
                                    row.add(timestampTZToString.format(t));
                                } else {
                                    row.add(null);
                                }
                                break;
                            }
                            default:
                                row.add(rs.getObject(i));
                                break;
                        }
                    }

                    table.add(row);

                    if (++counter >= SHUNK_SIZE) {
                        completed = false;
                        return;
                    }
                }

                completed = true;
                close();
            } catch (SQLException e) {
                close();
                throw new DBException("Could not load resultset. " + e.getMessage());
            } catch (Throwable e) {
                close();
                throw new DBException("Could not load resultset. " + e.getMessage());
            }
        }

        public void close() {
            completed = true;
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e1) {
                }
                stmt = null;
            }

        }

        public int getRowCount() {
            return table.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) throws DBException {
            if (!completed && rowIndex >= table.size() - 5) {
                // load next shunk
                int oldSize = table.size();
                long ms = System.currentTimeMillis();
                loadNextShunk();
                timeSpentOnFetch = System.currentTimeMillis() - ms;
                // notify ..
                if (listener != null) {
                    listener.handle(oldSize, table.size());
                }
            }

            return table.get(rowIndex).get(columnIndex);
        }

        public void setListener(TableResizeListener listener) {
            this.listener = listener;
        }

        public boolean allRowsFetched() {
            return completed;
        }
    }

    class RowModelImpl implements RowSetModel, TableResizeListener {

        List<String> columns;
        RecordSetWrapper wrapper;
        List<TableResizeListener> listeners = new ArrayList<TableResizeListener>();

        String _sql;

        public RowModelImpl() throws DBException {
            _sql = sql;
            this.wrapper = new RecordSetWrapper(_sql); //wrapper;
            this.columns = wrapper.getColumnNames();
            wrapper.setListener(this);
        }

        public int getFetchedRowCount() {
            return wrapper.getRowCount();
        }

        public long timeSpentOnFetch() {
            return wrapper.timeSpentOnFetch();
        }

        public boolean allRowsFetched() {
            return wrapper.allRowsFetched();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            try {
                return wrapper.getValueAt(rowIndex, columnIndex);
            } catch (DBException e) {
                // todo --
                return null;
            }
        }

        public int getColumnCount() {
            return columns.size();
        }

        public String getColumnName(int columnIndex) {
            return columns.get(columnIndex);
        }

        public void addListener(TableResizeListener l) {
            listeners.add(l);
        }

        public void removeListener(TableResizeListener l) {
            listeners.remove(l);
        }

        public void refresh() throws DBException {
            int oldSize = wrapper.getRowCount();

            wrapper.close();
            wrapper = new RecordSetWrapper(_sql);
            wrapper.setListener(this);

            int newSize = wrapper.getRowCount();
            // notify dependend objects
            handle(oldSize, newSize);
        }

        public void orderByColumn(int columnId, int direction, boolean stickyTag) throws DBException {

            int oldSize = wrapper.getRowCount();

            _sql = adoptOrderByClause(sql, columnId, direction);

            wrapper.close();
            wrapper = new RecordSetWrapper(_sql);
            wrapper.setListener(this);

            int newSize = wrapper.getRowCount();
            // notify dependend objects
            handle(oldSize, newSize);
        }

        public void close() {
            wrapper.close();
        }

        public void handle(int oldSize, int newSize) {
            // forward event
            for (TableResizeListener l : listeners) {
                l.handle(oldSize, newSize);
            }
        }
    }

    String formatDateValue(ResultSet rs, int columnIndex) throws SQLException {
        long d = rs.getDate(columnIndex).getTime();
        long t = rs.getTime(columnIndex).getTime();

        return dateToString.format(new Date(d+t));
    }


    // Object specific management
    // todo -- should be revised
    public boolean dropTable(String name) throws DBException {
        String sql = "drop table " + name.trim().toLowerCase() + " cascade constraints";
        int size = executeUpdate(sql);
        return true;
    }

    public boolean dropPackage(String name) throws DBException {
        //"DROP PACKAGE [BODY] [schema.]package_name;"
        String sql = "drop package " + name.trim().toLowerCase();
        int size = executeUpdate(sql);
        return true;
    }

    public boolean compilePackage(String name) throws DBException {
        //"ALTER PACKAGE emp_mgmt  COMPILE PACKAGE;"
        String sql = "alter package " + name.trim().toLowerCase() + " compile package";
        int size = executeUpdate(sql);
        return true;
    }

    public boolean dropView(String name) throws DBException {
        String sql = "drop view " + name.trim().toLowerCase() + " cascade constraints";
        int size = executeUpdate(sql);
        return true;
    }

    public boolean dropTrigger(String name) throws DBException {
        String sql = "drop trigger " + name.trim().toLowerCase();
        int size = executeUpdate(sql);
        return true;
    }

    public boolean enableTrigger(String name) throws DBException {
        String sql = "alter trigger " + name.trim().toLowerCase() + " enable";
        int size = executeUpdate(sql);
        return true;
    }

    public boolean disableTrigger(String name) throws DBException {
        String sql = "alter trigger " + name.trim().toLowerCase() + " disable";
        int size = executeUpdate(sql);
        return true;
    }

    public void cancel() {
        if(stmt != null){
            try {
                stmt.cancel();
            } catch (SQLException e) {
            }

            canceled = true;
        }
    }

}
