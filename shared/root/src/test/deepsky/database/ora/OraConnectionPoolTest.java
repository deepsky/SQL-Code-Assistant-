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

import junit.framework.TestCase;
import java.sql.*;
//import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import javax.sql.PooledConnection;


public class OraConnectionPoolTest extends TestCase {

    PooledConnection pc;

    public void setUp() throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        String dbVersion;
/*
        // try to establish connection
        try {

            OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();

            ocpds.setDriverType("thin");
            ocpds.setServerName("192.168.132.1");
            ocpds.setNetworkProtocol("tcp");
            ocpds.setDatabaseName("ora");
            ocpds.setPortNumber(1521);
            ocpds.setUser("pln");
            ocpds.setPassword("pln");

            pc = ocpds.getPooledConnection();

            conn = pc.getConnection();

//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            conn = DriverManager.getConnection("");

            // connection created, request for version number
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT BANNER FROM V$VERSION WHERE BANNER LIKE 'CORE%'");
            if(rs.next()){
                String v = rs.getString("BANNER");
                dbVersion = v.replaceAll("[^0-9\\.]", "");
            }

        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }

            if(conn != null){
                conn.close();
            }
        }
*/  
    }


    public void test1() throws SQLException {

        Connection conn1 = pc.getConnection();
        Connection conn2 = pc.getConnection();
        Connection conn3 = pc.getConnection();

        String ver1 = t(conn1);
        conn1.close();

        String ver2 = t(conn2);
        String ver3 = t(conn3);

        conn3.close();
        conn2.close();

        int gg =0;
    }


    String t (Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        String dbVersion = "";

        // try to establish connection
        try {

            // connection created, request for version number
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT BANNER FROM V$VERSION WHERE BANNER LIKE 'CORE%'");
            if(rs.next()){
                String v = rs.getString("BANNER");
                dbVersion = v.replaceAll("[^0-9\\.]", "");
            }

            return dbVersion;
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
