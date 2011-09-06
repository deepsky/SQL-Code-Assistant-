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

public class RowSetModelImpl {//}  implements RowSetModel, TableResizeListener {
/*

        List<String> columns;
        RecordSetWrapper wrapper;
        List<TableResizeListener> listeners = new ArrayList<TableResizeListener>();

        String sqlStmt;

        public RowSetModelImpl(String sqlStmt) throws DBException {
            this.sqlStmt = sqlStmt;
            this.wrapper = new RecordSetWrapper(this.sqlStmt); //wrapper;
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
            wrapper = new RecordSetWrapper(sqlStmt);
            wrapper.setListener(this);

            int newSize = wrapper.getRowCount();
            // notify dependend objects
            handle(oldSize, newSize);
        }

        public void orderByColumn(int columnId, int direction, boolean stickyTag) throws DBException {

            int oldSize = wrapper.getRowCount();

            sqlStmt = adoptOrderByClause(sql, columnId, direction);

            wrapper.close();
            wrapper = new RecordSetWrapper(sqlStmt);
            wrapper.setListener(this);

            int newSize = wrapper.getRowCount();
            // notify dependend objects
            handle(oldSize, newSize);
        }

        public void close() {
            wrapper.close();
        }

        public void deleteRows(int firstRow, int lastRow) throws DBException {
            try {
                wrapper.deleteRows(firstRow, lastRow);
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }

        public void startInsertRow(int row) {
            try {
                wrapper.addRow(row);
            } catch (DBException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        public void completeInsertRow() {
            //todo
        }

        public void handle(int oldSize, int newSize) {
            // forward event
            for (TableResizeListener l : listeners) {
                l.handle(oldSize, newSize);
            }
        }




    class RecordSetWrapper {
        int[] columnTypes;
        boolean completed = false;
        List<List> rowCache = new ArrayList<List>();
        List<String> columnNames = new ArrayList<String>();
        long timeSpentOnFetch = 0;
        TableResizeListener listener;
        boolean isUpdatable = false;

        public RecordSetWrapper(String sql) throws DBException {
            long ms = System.currentTimeMillis();
            try {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                stmt.setFetchSize(SHUNK_SIZE + 10);
                rs = stmt.executeQuery(sql);

                isUpdatable = rs.getConcurrency() == ResultSet.CONCUR_UPDATABLE;

                ResultSetMetaData meta = rs.getMetaData();
                columnTypes = new int[meta.getColumnCount() + 1];
                for (int i = 0; i < columnTypes.length - 1; i++) {
                    String label = meta.getColumnLabel(i + 1);
                    columnNames.add(label);
                    columnTypes[i + 1] = meta.getColumnType(i + 1);
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
            if (canceled) {
                throw new SQLException("SQL query canceled by user request.");
            }
        }

        public long timeSpentOnFetch() {
            return timeSpentOnFetch;
        }

        public List<String> getColumnNames() {
            return columnNames;
        }

        public boolean isUpdatable(){
            return isUpdatable;
        }

        void loadNextShunk() throws DBException {
            try {
                int counter = 0;
                while (rs.next()) {

                    __ASSERT_CANCEL__();

                    List row = new ArrayList();
                    int colnum = columnTypes.length - 1;
                    for (int i = 1; i <= colnum; i++) {
                        switch (columnTypes[i]) {
                            case 91: { // date
                                Date d0 = rs.getDate(i);
                                if (d0 != null) {
                                    long d = d0.getTime();
                                    long t = rs.getTime(i).getTime();
                                    row.add(dateToString.format(new Date(d + t)));
                                } else {
                                    row.add(null);
                                }
                                break;
                            }
                            case 93: { // timestamp without timezone ?
                                Timestamp t = rs.getTimestamp(i);
                                if (t != null) {
                                    row.add(timestampToString.format(t));
                                } else {
                                    row.add(null);
                                }
                                break;
                            }
                            case -101: { // timestamp with timezone ?
                                Timestamp t = rs.getTimestamp(i);
                                if (t != null) {
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

                    rowCache.add(row);

                    if (++counter >= SHUNK_SIZE) {
                        completed = false;
                        return;
                    }
                }

                completed = true;
//                close();
            } catch (SQLException e) {
//                close();
                throw new DBException("Could not load resultset. " + e.getMessage());
            } catch (Throwable e) {
//                close();
                throw new DBException("Could not load resultset. " + e.getMessage());
            }
        }

        public void close() {
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

        public int getRowCount() {
            return rowCache.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) throws DBException {
            loadCache(rowIndex);
*/
/*
            if (!completed && rowIndex >= rowCache.size() - 5) {
                // load next shunk
                int oldSize = rowCache.size();
                long ms = System.currentTimeMillis();
                loadNextShunk();
                timeSpentOnFetch = System.currentTimeMillis() - ms;
                // notify ..
                if (listener != null) {
                    listener.handle(oldSize, rowCache.size());
                }
            }
*//*


            return rowCache.get(rowIndex).get(columnIndex);
        }

        public void setListener(TableResizeListener listener) {
            this.listener = listener;
        }

        public boolean allRowsFetched() {
            return completed;
        }

        public void deleteRows(int firstRow, int lastRow) throws SQLException, DBException {
            if(isUpdatable){
                loadCache(firstRow);
                int cacheSize = rowCache.size();

                if(cacheSize < firstRow){
                    // out of boundaries
                    return;
                }
                rs.absolute(firstRow);

                // check bottom boundary
                lastRow = cacheSize<=lastRow? cacheSize: lastRow;

                for(int i=0; i< (lastRow-firstRow+1); i++){
                    rs.deleteRow();
                    // it's supposed the row cache keeps all records being deleted
                    rowCache.remove(firstRow-1);
                }

                // todo -- fire event?
            }
        }

        public void addRow(int row) throws DBException, SQLException {
            loadCache(row);
            int cacheSize = rowCache.size();

            if(cacheSize <= row){
                // out of boundaries
                return;
            }
            rs.absolute(row);
            rs.moveToInsertRow();
        }

        private void loadCache(int lastRow) throws DBException {
            boolean loadDone = false;
            int oldSize = rowCache.size();
            while (!completed && lastRow >= rowCache.size() - 5) {
                // load next shunk
                long ms = System.currentTimeMillis();
                loadNextShunk();
                timeSpentOnFetch = System.currentTimeMillis() - ms;
//                // notify ..
//                if (listener != null) {
//                    listener.handle(oldSize, rowCache.size());
//                }
                loadDone = true;
            }

            if(loadDone){
                // notify ..
                if (listener != null) {
                    listener.handle(oldSize, rowCache.size());
                }
            }
        }

    }


*/
 }


