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

import java.sql.SQLException;

public interface RSUpdateSupport {

    /**
     * We always have ROWID as a last column
     * SELECT COL1, COL2, ..., ROWID FROM ...
     * @return - select script
     */
    String getSelectScript();

    /**
     * Find a column name pointed out by index (columnIndex starts with 1, 2, ...)
     * @param columnIndex - column index
     * @return - name of the table column or null if index points to unupdatable field
     */
    String mapColumnIndexToColumnName(int columnIndex);
    // columnIndex starts with 1, 2, ...
    boolean isFieldUpdatable(int columnIndex);

    UpdateStmtBuilder getUpdater();
    InsertStmtBuilder getInserter();
    SelectStmtBuilder getSelector();
    DeleteStmtBuilder getDeletor();

    interface UpdateStmtBuilder {
        // resulting statement: UPDATE <TABLE> SET COL1=?, COL2=? WHERE ROWID = ?
        String getStmt();
        void addFieldBeingUpdated(String fieldName, Object value);
        void iterateOverFields(FieldProcessor processor) throws SQLException;
    }

    interface InsertStmtBuilder {
        // resulting statement: INSERT INTO <TABLE> (COL1, COL2, ...) VALUES (VAL1, VAL2, ...) returning ROWID INTO ?
        String getStmt();
        void addFieldBeingInserted(String fieldName, Object value);
        void iterateOverFields(FieldProcessor processor) throws SQLException;

        /**
         * Get position of the returning ROWID
         * @return
         */
        int getRetROWIDPos();
    }

    interface SelectStmtBuilder {
        // resulting statement: SELECT field1, filed2, ... FROM ... WHERE ROWID = ?
        String getStmt();
    }

    interface DeleteStmtBuilder {
        // resulting statement: DELETE FROM <TABLE> WHERE ROWID = ?
        String getStmt();
    }

    interface FieldProcessor {
        void process(int position, String fieldName, Object value, boolean isROWID) throws SQLException;
    }
}
