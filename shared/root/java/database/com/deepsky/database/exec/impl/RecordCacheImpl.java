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

import com.deepsky.database.DBException;
import com.deepsky.database.exec.RecordCache;
import com.deepsky.database.exec.RowSetManager;
import com.deepsky.database.exec.TableResizeListener;
import com.deepsky.database.ora.types.LONGRAWType;
import com.deepsky.database.ora.types.RAWType;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.psi.ForUpdateClause;
import com.deepsky.lang.plsql.psi.OrderByClause;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.intellij.lang.ASTNode;
import oracle.jdbc.OracleTypes;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordCacheImpl implements RecordCache {

    protected String selectStmtORIGIN;
    protected String lastSelectStmt;

    protected Connection conn;
    protected Statement stmt;
    protected ResultSet rs;

    private boolean completed = false;
    protected long timeSpentOnFetch = 0;
    private List<TableResizeListener> listeners = new ArrayList<TableResizeListener>();

    protected List<Row> rowCache = new ArrayList<Row>();
    protected ColumnInfo[] cinfos;
    protected int chunk_size;

    private boolean canceled = false;

    protected RecordCacheImpl() {
    }

    public RecordCacheImpl(Connection conn, String sql, int chunk_size) throws DBException {
        this.conn = conn;
        this.chunk_size = chunk_size;
        this.selectStmtORIGIN = sql;

        reloadRS(conn, sql);
        // save last query
        this.lastSelectStmt = sql;
    }

    protected void reloadRS(Connection conn, String sql) throws DBException {
        long ms = System.currentTimeMillis();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            stmt.setFetchSize(chunk_size + 10);
            rs = stmt.executeQuery(sql);

            ResultSetMetaData meta = rs.getMetaData();
            cinfos = new ColumnInfo[meta.getColumnCount() + 1];
            for (int i = 0; i < cinfos.length - 1; i++) {
                String label = meta.getColumnLabel(i + 1);
                int columnType = meta.getColumnType(i + 1);
                String typeName = meta.getColumnTypeName(i + 1);
                int precision = meta.getColumnDisplaySize(i + 1); //meta.getPrecision(i+1);

                String columnClassName = meta.getColumnClassName(i + 1);
                // FIX some collisions related to Oracle impl
                switch (columnType) {
                    case OracleTypes.BINARY:    //-2:
                    case OracleTypes.VARBINARY: //-3:
                        columnClassName = RAWType.class.getName();
                        break;
                    case OracleTypes.LONGVARBINARY: //-4:
                        columnClassName = LONGRAWType.class.getName();
                        break;
                    case OracleTypes.DATE: //91:
                        // Looks like an Oracle bug:
                        //      ResultSetMetaData reports column type as Timestamp but returns Date
                        columnClassName = java.sql.Date.class.getName();
                        break;
                }
                cinfos[i + 1] = new ColumnInfo(
                        label,
                        columnType,
                        columnClassName,
                        label + " " + typeName + (precision > 0 ? "(" + precision + ")" : "")
                );
//                cinfos[i+1] = new ColumnInfo(label, meta.getColumnType(i + 1));
            }

            __ASSERT_CANCEL__();
        } catch (SQLException e) {
            closeInternal();
            throw new DBException("Could not execute query. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            closeInternal();
            throw new DBException("Could not execute query. " + e.getMessage());
        }

        rowCache.clear();
        loadNextShunk();
        timeSpentOnFetch = System.currentTimeMillis() - ms;
    }

    protected void __ASSERT_CANCEL__() throws SQLException {
        if (canceled) {
            throw new SQLException("SQL query canceled by user request.");
        }
    }

    public void cancel() {
        this.canceled = true;
    }

    public Connection getConnection() {
        return conn;
    }

    public int getColumnCount() {
        return cinfos.length - 1;
    }

    public String getColumnName(int columnIndex) {
        return cinfos[columnIndex].label;
    }

    public long timeSpentOnFetch() {
        return timeSpentOnFetch;
    }

    public String[] getColumnNames() {
        String[] out = new String[cinfos.length - 1];
        for (int i = 0; i < cinfos.length - 1; i++) {
            out[i] = cinfos[i + 1].label;
        }

        return out;
    }

    public int getColumnType(int columnIndex) {
        return cinfos[columnIndex].type;
    }

    public Class getColumnClass(int columnIndex) {
        return cinfos[columnIndex].clazz;
    }

    public boolean isColumnEditable(int columnIndex) {
        return false;
    }

    public boolean isColumnNotNull(int columnIndex) {
        return false;
    }

    public String getColumnSpecification(int columnIndex) {
        return cinfos[columnIndex].columnSpecification;
    }


    void loadNextShunk() throws DBException {
        try {
            int counter = 0;
            while (rs.next()) {
                __ASSERT_CANCEL__();
                rowCache.add(loadRow(rs));

                if (++counter >= chunk_size) {
                    completed = false;
                    return;
                }
            }

            completed = true;
        } catch (SQLException e) {
            throw new DBException("Could not load resultset. " + e.getMessage());
        } catch (Throwable e) {
            throw new DBException("Could not load resultset. " + e.getMessage());
        }
    }


    protected Row loadRow(ResultSet rs) throws SQLException {
        Row row = new Row();
        int colnum = cinfos.length - 1;
        for (int i = 1; i <= colnum; i++) {

            switch (cinfos[i].type) {
                case OracleTypes.BINARY: { // RAW (Types.BINARY)
                    byte[] value = (byte[]) rs.getObject(i);
                    row.add(rs.wasNull() ? null : new RAWType(value));
                    break;
                }
                case OracleTypes.VARBINARY: { // RAW (Types.VARBINARY)
                    byte[] value = (byte[]) rs.getObject(i);
                    row.add(rs.wasNull() ? null : new RAWType(value));
                    break;
                }
                case OracleTypes.LONGVARBINARY: { // LONGRAW (Types.LONGVARBINARY)
                    byte[] value = (byte[]) rs.getObject(i);
                    row.add(rs.wasNull() ? null : new LONGRAWType(value));
                    break;
                }
                case OracleTypes.DATE:
                    // in order do not loss time part of the date use getTimestamp
                    row.add(rs.getTimestamp(i));
                    break;
                default:
                    row.add(rs.getObject(i));
                    break;
            }
        }

        return row;
    }

    protected void updateRow(@NotNull ResultSet rs, @NotNull Row row) throws SQLException {
        row.clear();
        int colnum = cinfos.length - 1;
        for (int i = 1; i <= colnum; i++) {
            switch (cinfos[i].type) {
                case OracleTypes.BINARY: { // RAW (Types.BINARY)
                    byte[] value = (byte[]) rs.getObject(i);
                    row.add(rs.wasNull() ? null : new RAWType(value));
                    break;
                }
                case OracleTypes.VARBINARY: { // RAW (Types.VARBINARY)
                    byte[] value = (byte[]) rs.getObject(i);
                    row.add(rs.wasNull() ? null : new RAWType(value));
                    break;
                }
                case OracleTypes.LONGVARBINARY: { // LONGRAW (Types.LONGVARBINARY)
                    byte[] value = (byte[]) rs.getObject(i);
                    row.add(rs.wasNull() ? null : new LONGRAWType(value));
                    break;
                }
                case OracleTypes.DATE:
                    // in order do not loss time part of the date use getTimestamp
                    row.add(rs.getTimestamp(i));
                    break;
                default:
                    row.add(rs.getObject(i));
                    break;
            }
        }
    }

    public void close() {
        closeInternal();
        listeners.clear();
    }

    protected void closeInternal() {
        completed = true;
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ignored) {
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ignored) {
            }
            stmt = null;
        }
    }

    public void sortByColumn(int columnId, int direction, boolean stickyTag) throws DBException {
        int type = cinfos[columnId].type;
        if (type == OracleTypes.LONGVARCHAR) {
            // Illegal use of datatype LONG. It was used in a function
            // or in a DISTINCT, WHERE, CONNECT BY, GROUP BY, or ORDER BY clause.
            // A LONG value can only be used in a SELECT clause.
            String errorMessage = "Unable to sort by a column with LONG datatype (ORA-00997)";
            throw new DBException(errorMessage);
        } else if (type == OracleTypes.BLOB || type == OracleTypes.CLOB || type == OracleTypes.BFILE) {
            String errorMessage = "Unable to sort by a column with BLOB/CLOB/BFILE datatype (ORA-00932)";
            throw new DBException(errorMessage);
        } else {
            // todo -- more checks needed
        }
        lastSelectStmt = adoptOrderByClause(selectStmtORIGIN, columnId, direction);
        closeInternal();
        int oldSize = rowCache.size();

        reloadRS(conn, lastSelectStmt);

        // notify ..
        for (TableResizeListener l : listeners) {
            l.handle(oldSize, rowCache.size());
        }
    }

    public void refresh() throws DBException {
        closeInternal();
        int oldSize = rowCache.size();

        reloadRS(conn, lastSelectStmt);

        // notify ..
        for (TableResizeListener l : listeners) {
            l.handle(oldSize, rowCache.size());
        }
    }


    /**
     * Create a select statement with requested sorting settings
     *
     * @param select      - a select statement
     * @param columnIndex - column index starting with 1 (1, 2, ... and so on)
     * @param dir
     * @return
     * @throws DBException
     */
    private String adoptOrderByClause(String select, int columnIndex, int dir) throws DBException {
        SelectStatement selectStmt = parse(select);
        OrderByClause orderBy = selectStmt.getOrderByClause();
        String sql_text = selectStmt.getText();
        String adoptedSelect;
        String orderByAddon = "ORDER BY " + columnIndex + ((dir == RowSetManager.DESCENDING) ? " DESC" : " ASC");
        /* check the case
        ....
        ( order_clause )?
        ( update_clause )?
          */
        if (orderBy != null) {
            // only one 'ORDER BY' allowed
            int start = orderBy.getTextRange().getStartOffset();
            int end = orderBy.getTextRange().getEndOffset();
            adoptedSelect = sql_text.substring(0, start) + orderByAddon + " " + sql_text.substring(end, sql_text.length());
        } else {
            // check 'for update' clause
            ForUpdateClause forUpdate = selectStmt.getForUpdateClause();
            if (forUpdate != null) {
                int start = forUpdate.getTextRange().getStartOffset();
                adoptedSelect =
                        sql_text.substring(0, start)
                                + orderByAddon
                                + " " + sql_text.substring(start, sql_text.length());
            } else {
                // there is neither OrderBy nor ForUpdate clauses
                int end = selectStmt.getTextRange().getEndOffset();
                adoptedSelect = sql_text.substring(0, end) + " " + orderByAddon + " " + sql_text.substring(end, sql_text.length());
            }
        }

        // todo -- is additional parsing needed?
        return parse(adoptedSelect).getText();
    }


    private SelectStatement parse(String select) throws DBException {
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode ast = generator.parse(select);
        ASTNode node = ast != null ? ast.findChildByType(PLSqlTypesAdopted.SELECT_EXPRESSION) : null;
        if (node != null) {
            return (SelectStatement) node.getPsi();
        }

        throw new DBException("Could not build a query with sorting");
    }

    public int getFetchedRowCount() {
        return rowCache.size();
    }


    public Object getValueAt(int rowIndex, int columnIndex) throws DBException {
        loadCache(rowIndex);
        return rowCache.get(rowIndex).get(columnIndex - 1);
//        return rowCache.get(rowIndex).get(columnIndex);
    }

    public void setValueAt(int rowIndex, int columnIndex, Object value) throws DBException {
        // not supported by default
        throw new DBException("Not supported by RecordCache");
    }

    public void addListener(TableResizeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(TableResizeListener listener) {
        listeners.remove(listener);
    }

    public boolean allRowsFetched() {
        return completed;
    }

    protected void loadCache(int lastRow) throws DBException {
        boolean loadDone = false;
        int oldSize = rowCache.size();
        while (!completed && lastRow >= rowCache.size() - 5) {
            // load next shunk
            long ms = System.currentTimeMillis();
            loadNextShunk();
            timeSpentOnFetch = System.currentTimeMillis() - ms;
            loadDone = true;
        }

        if (loadDone) {
            // notify ..
            for (TableResizeListener l : listeners) {
                l.handle(oldSize, rowCache.size());
            }
        }
    }


    private int hashCode = 0;

    protected class Row extends ArrayList {

        private final int _hashCode;
        public Row(){
            this._hashCode = ++hashCode;
        }
        // override hashing algorithm in base class
        public int hashCode(){
            return _hashCode;
        }

        public boolean equals(Object o){
            return (o instanceof Row) && ((Row) o)._hashCode == _hashCode;
        }
    }
}
