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
import com.deepsky.database.exec.UpdatableRecordCache;
import com.deepsky.database.ora.types.LONGRAWType;
import com.deepsky.database.ora.types.RAWType;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.ROWID;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

class UpdatableRecordCache2Impl extends RecordCacheImpl implements UpdatableRecordCache {

    private RSUpdateSupport rsUpdateSupport;
    private Map<Row, Map<Integer, Object>> rowsBeingUpdated = new HashMap<Row, Map<Integer, Object>>();

    public UpdatableRecordCache2Impl(Connection conn, RSUpdateSupport rsUpdateSupport, int chunk_size) throws DBException, NotUpdatableResultSetException {
        this.conn = conn;
        this.chunk_size = chunk_size;
        this.rsUpdateSupport = rsUpdateSupport;
        this.selectStmtORIGIN = rsUpdateSupport.getSelectScript();
        reloadRS(conn, rsUpdateSupport.getSelectScript());
        this.lastSelectStmt = rsUpdateSupport.getSelectScript();
    }


    protected void reloadRS(Connection conn, String sql) throws DBException {
        long ms = System.currentTimeMillis();
        try {
            stmt = conn.createStatement();
            stmt.setFetchSize(chunk_size + 10);
            rs = stmt.executeQuery(sql);

            ResultSetMetaData meta = rs.getMetaData();
            cinfos = new ColumnInfo[meta.getColumnCount() + 1];
            for (int i = 0; i < cinfos.length - 1; i++) {
                String label = meta.getColumnLabel(i + 1);
                String clazz = meta.getColumnClassName(i + 1);
                int columnType = meta.getColumnType(i + 1);
                if (clazz == null) {
                    // could not recognize column class, possible case: ROWID column for an external table
                    throw new NotUpdatableResultSetException();
                }
                // FIX some collisions related to Oracle impl
                switch (columnType) {
                    case OracleTypes.BINARY:    //-2:
                    case OracleTypes.VARBINARY: //-3:
                        clazz = RAWType.class.getName();
                        break;
                    case OracleTypes.LONGVARBINARY: //-4:
                        clazz = LONGRAWType.class.getName();
                        break;
                    case OracleTypes.DATE: //91:
                        // Looks like an Oracle bug:
                        //      ResultSetMetaData reports column type as Timestamp but returns Date
                        clazz = java.sql.Timestamp.class.getName();
                        break;
                }

                String typeName = meta.getColumnTypeName(i + 1);
                int precision = meta.getColumnDisplaySize(i + 1); //meta.getPrecision(i+1);

                cinfos[i + 1] = new ColumnInfo(label,
                        columnType,
                        clazz,
                        label + " " + typeName + (precision > 0 ? "(" + precision + ")" : ""),
                        meta.isNullable(i + 1) == 0
                );
            }

            rowsBeingUpdated.clear();
            __ASSERT_CANCEL__();
        } catch (SQLException e) {
            closeInternal();
            // Check error message on ORA-01446
            // ORA-01446: cannot select ROWID from, or sample, a view with DISTINCT, GROUP BY, etc.
            String emessage = e.getMessage();
            if (emessage != null && emessage.indexOf("ORA-01446:") != -1) {
                throw new NotUpdatableResultSetException();
            }
            // Check error message on ORA-00937
            // todo --

            throw new DBException("Could not execute query. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            closeInternal();
            throw new DBException("Could not execute query. " + e.getMessage());
        }

        rowCache.clear();
        loadNextShunk();
        timeSpentOnFetch = System.currentTimeMillis() - ms;
    }


    /**
     * Because we always have the last column ROWID, correct number of columns
     *
     * @return - number of columns
     */
    public int getColumnCount() {
        return cinfos.length - 2; // -1;
    }

    public boolean isColumnEditable(int columnIndex) {
        return rsUpdateSupport.isFieldUpdatable(columnIndex);
    }

    public boolean isColumnNotNull(int columnIndex) {
        return cinfos[columnIndex].isNotNull;
    }


    private ROWID getROWID(int rowIndex) {
        return (ROWID) rowCache.get(rowIndex).get(cinfos.length - 2);
    }

    private ROWID getROWID(Row row) {
        return (ROWID) row.get(cinfos.length - 2);
    }

    private void setROWID(Row row, ROWID rowId) {
        row.set(cinfos.length - 2, rowId);
    }

    public void deleteRows(int firstRow, int lastRow) throws DBException {
        loadCache(lastRow);
        int cacheSize = rowCache.size();

        // check bottom boundary
        lastRow = cacheSize <= lastRow ? cacheSize : lastRow;

        RSUpdateSupport.DeleteStmtBuilder delete = rsUpdateSupport.getDeletor();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(delete.getStmt());

            for (int i = 0; i < (lastRow - firstRow + 1); i++) {
                final ROWID rowid = getROWID(firstRow - 1);
                if (rowid == null) {
                    // attempt to delete a row added but not posted? skip running delete statement
                } else {
                    pstmt.setObject(1, rowid);
                    pstmt.execute();
                }

                // it's supposed the row cache keeps all records being deleted
                Row row = rowCache.remove(firstRow - 1);
                // just a paranoid check
                if (row != null) {
                    rowsBeingUpdated.remove(row);
                    if (rowsBeingUpdated.size() == 0) {
                        // todo -- notify ALL the post is not need any more
                    }
                }
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public void addRow(int rowIndex) throws DBException {
        loadCache(rowIndex);
        int cacheSize = rowCache.size();

        if (cacheSize < rowIndex) {
            // out of boundaries
            return;
        }
        Row row = createRow();
        rowCache.add(rowIndex, row);
        rowsBeingUpdated.put(row, new HashMap<Integer, Object>());
    }

    public void setValueAt(int rowIndex, int columnIndex, Object value) throws DBException {

        Row row = rowCache.get(rowIndex);
        Map<Integer, Object> colIdx2value = rowsBeingUpdated.get(row);
        if (colIdx2value == null) {
            // first request for update of the row
            // check the new value against existing one
            Object o = row.get(columnIndex - 1);
            if (o == null && value == null) {
                // skip saving value
                return;
            } else if (o != null && o.equals(value)) {
                // skip saving value
                return;
            }

            colIdx2value = new HashMap<Integer, Object>();
            rowsBeingUpdated.put(row, colIdx2value);
        }
        // one more check if type is string
        Object _value = (value instanceof String && ((String) value).length() == 0) ? null : value;
        colIdx2value.put(columnIndex, _value);
        row.set(columnIndex - 1, _value);
    }

    public void completeUpdate() throws DBException {

        if (rowsBeingUpdated.size() == 0) {
            return;
        }

        for (Map.Entry<Row, Map<Integer, Object>> e : rowsBeingUpdated.entrySet()) {
            Row row = e.getKey();
            ROWID rowId = getROWID(row);
            if (rowId == null) {
                // row was inserted recently
                rowId = completeRowInsert(e.getValue());
                setROWID(row, rowId);
            } else {
                // row is a subject to update
                completeRowUpdate(e.getValue(), rowId);
            }
        }

        // call SELECT to refresh fields of the updated/inserted row
        reloadRows(rowsBeingUpdated);
        rowsBeingUpdated.clear();
    }


    /**
     * Call SELECT to refresh fields of the specified rows
     *
     * @param rowsBeingUpdated
     * @throws DBException
     */
    private void reloadRows(Map<Row, Map<Integer, Object>> rowsBeingUpdated) throws DBException {
        // call SELECT to refresh fields of the updated/inserted row
        RSUpdateSupport.SelectStmtBuilder rowSelector = rsUpdateSupport.getSelector();
        String selectStmt = rowSelector.getStmt();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(selectStmt);
            for (Map.Entry<Row, Map<Integer, Object>> e : rowsBeingUpdated.entrySet()) {
                Row row = e.getKey();
                ROWID rowId = getROWID(row);
                pstmt.setObject(1, rowId);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    updateRow(rs, row);
                    // replace existing row with returned from the db
//                    rowCache.set(updateRowIndex, row1);
                } else {
                    // todo -- is it possible?
                }
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public void discardChanges() throws DBException {
        if (rowsBeingUpdated.size() > 0) {
            reloadRows(rowsBeingUpdated);
            rowsBeingUpdated.clear();
        }
    }

    public boolean changesNotPosted() {
        return rowsBeingUpdated.size() != 0;
    }


    private ROWID completeRowInsert(Map<Integer, Object> colIdx2value) throws DBException {
        RSUpdateSupport.InsertStmtBuilder builder = rsUpdateSupport.getInserter();
        for (Map.Entry<Integer, Object> e : colIdx2value.entrySet()) {
            String column = rsUpdateSupport.mapColumnIndexToColumnName(e.getKey());
            if (column == null) {
                // index points to readonly field
            } else {
                builder.addFieldBeingInserted(column, e.getValue());
            }
        }

        OraclePreparedStatement pstmt = null;
        try {
            pstmt = (OraclePreparedStatement) conn.prepareStatement(builder.getStmt());
            final OraclePreparedStatement finalPstmt = pstmt;
            builder.iterateOverFields(new RSUpdateSupport.FieldProcessor() {
                public void process(int position, String fieldName, Object value, boolean isROWID) throws SQLException {
                    finalPstmt.setObject(position, value);
                }
            });

            // register return parameters
            pstmt.registerReturnParameter(builder.getRetROWIDPos(), OracleTypes.ROWID);

            // process the DML returning SQL statement
            pstmt.executeUpdate();
            ResultSet rset = pstmt.getReturnResultSet();

            if (rset.next()) {
                return (ROWID) rset.getObject(1);
            }
            throw new DBException("Cannot insert row");
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException ignored) {
            }
        }
    }

    private void completeRowUpdate(Map<Integer, Object> colIdx2value, final ROWID rowId) throws DBException {
        RSUpdateSupport.UpdateStmtBuilder builder = rsUpdateSupport.getUpdater();
        for (Map.Entry<Integer, Object> e : colIdx2value.entrySet()) {
            String column = rsUpdateSupport.mapColumnIndexToColumnName(e.getKey());
            if (column == null) {
                // index points to readonly field
            } else {
                builder.addFieldBeingUpdated(column, e.getValue());
            }
        }

        // execute updated
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(builder.getStmt());
            final PreparedStatement finalPstmt = pstmt;
            builder.iterateOverFields(new RSUpdateSupport.FieldProcessor() {
                public void process(int position, String fieldName, Object value, boolean isROWID) throws SQLException {
                    if (isROWID) {
                        finalPstmt.setObject(position, rowId);
                    } else {
                        if (value instanceof RAWType) {
                            byte[] _value = ((RAWType) value).getValue();
                            finalPstmt.setObject(position, _value);
                        } else if (value instanceof LONGRAWType) {
                            byte[] _value = ((LONGRAWType) value).getValue();
                            finalPstmt.setObject(position, _value);
                        } else {
                            finalPstmt.setObject(position, value);
                        }
                    }
                }
            });

            boolean ret = pstmt.execute();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException ignored) {
            }
        }
    }

    /**
     * Create a row with default field values
     *
     * @return
     */
    private Row createRow() {
        Row row = new Row();
        int colnum = getColumnCount();
        for (int i = 0; i < colnum + 1; i++) {
            row.add(null);
        }
        return row;
    }

/*
The following code example also illustrates the use of DML returning.
However, in this case, the maximum size of the return parameters is not known.
Therefore, the registerReturnParameter(int paramIndex, int externalType) method is used.

...
OraclePreparedStatement pstmt = (OraclePreparedStatement)conn.prepareStatement(
  "insert into lobtab values (100, empty_clob()) returning col1, col2 into ?, ?");

// register return parameters
pstmt.registerReturnParameter(1, OracleTypes.INTEGER);
pstmt.registerReturnParameter(2, OracleTypes.CLOB);

// process the DML returning SQL statement
pstmt.executeUpdate();
ResultSet rset = pstmt.getReturnResultSet();
int r;
CLOB clob;
if (rset.next())
{
  r = rset.getInt(1);
  System.out.println(r);
  clob = (CLOB)rset.getClob(2);
  ...
}
...

*/
}
