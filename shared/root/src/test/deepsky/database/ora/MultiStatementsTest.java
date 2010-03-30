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

package test.deepsky.database.ora;

import com.deepsky.database.DBException;
import com.deepsky.database.ora.ConnectionHolder;
import com.deepsky.database.ora.DbUrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MultiStatementsTest {

    ConnectionHolder holder;
    Connection conn;

    public MultiStatementsTest() throws DBException {
        DbUrl url = new DbUrl("jdbc:oracle:thin:pln/pln@192.168.132.1:1521:ora9");
        holder = new ConnectionHolder(url);
        conn = holder.getConnection();
    }

    public StatementHolder createHolder() throws SQLException {
        return new StatementHolder();
    }

    public DMLStatement createDML() throws SQLException {
        return new DMLStatement();
    }


    static public void main(String[] args) throws DBException, SQLException {

        MultiStatementsTest test = new MultiStatementsTest();

        StatementHolder h1 = test.createHolder();
        StatementHolder h2 = test.createHolder();
        StatementHolder h3 = test.createHolder();
        StatementHolder h4 = test.createHolder();

        ResultSet rs2 = h2.execQuery("Select * from all_objects");
        rs2.next();
        String oname = rs2.getString("OBJECT_NAME");

        ResultSet rs1 = h1.execQuery("Select * from all_views");
        rs1.next();
        rs1.next();
        String oname2 = rs2.getString("OBJECT_NAME");

        rs2.next();
        String oname3 = rs2.getString("OBJECT_NAME");
        rs2.next();
        String oname4 = rs2.getString("OBJECT_NAME");

        DMLStatement dml  = test.createDML();
        //dml.execUpdate("CREATE TABLE TT1 (ID NUMBER)");

        dml.execUpdate("INSERT INTO TT1 (ID) VALUES(1)");
        rs1.next();

        dml.execUpdate("INSERT INTO TT1 (ID) VALUES(2)");
        dml.execUpdate("INSERT INTO TT1 (ID) VALUES(3)");
        dml.execUpdate("INSERT INTO TT1 (ID) VALUES(4)");
        rs1.next();

        ResultSet rs4 = h4.execQuery("Select 33 AS tt from dual");
        rs4.next();
        int tt = rs4.getInt("tt");

        ResultSet rs3 = h3.execQuery("Select * from dual");

        h1.close();
        h4.close();
        h2.close();
        h3.close();

        int jj =0 ;
    }



    class StatementHolder {

        Statement stmt;
        ResultSet rs;
        public StatementHolder() throws SQLException {
            stmt = conn.createStatement();
        }

        public ResultSet execQuery(String query) throws SQLException {
            rs = stmt.executeQuery(query);
            return rs;
        }
        public void close(){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class DMLStatement {

        Statement stmt;
        ResultSet rs;
        public DMLStatement() throws SQLException {
            stmt = conn.createStatement();
        }

        public int execUpdate(String sql) throws SQLException {
            return stmt.executeUpdate(sql);
        }
        public void close(){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
