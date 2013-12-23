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

package com.deepsky.database;

import java.sql.*;

public class ResultSetHelper {

    Connection conn;
    int fetchSize = 1000;

    public ResultSetHelper(Connection conn) {
        this.conn = conn;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    public void populateFromResultSet(String sql, MappingHelper helper) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            stmt.setFetchSize(fetchSize);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                helper.processRow(rs);
            }

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public PreparedStatementPopulator prepareStatementSingle(String sql) throws SQLException {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setFetchSize(fetchSize);
        } catch (SQLException e) {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e2) {
                }
            }
            throw e;
        }

        return new PreparedStatementPopulatorImpl(stmt);
    }

    private class PreparedStatementPopulatorImpl implements PreparedStatementPopulator {
        PreparedStatement stmt;

        public PreparedStatementPopulatorImpl(PreparedStatement stmt) {
            this.stmt = stmt;
        }

        public void populateFromResultSet(String[] values, MappingHelper helper) throws SQLException {
            ResultSet rs = null;
            try {
                for (String v : values) {
                    stmt.setString(1, v);
                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        helper.processRow(rs);
                    }

                    rs.close();
                    rs = null;
                }
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
    }


    public interface PreparedStatementPopulator {
        void populateFromResultSet(String[] values, MappingHelper helper) throws SQLException;
    }
}
