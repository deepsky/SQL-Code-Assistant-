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

package com.deepsky.database.ora;

import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.database.DBException;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.lang.common.SharedConstants;
import com.deepsky.lang.common.SharedObjectPool;
import com.deepsky.utils.CustomClassLoader;

import java.sql.*;
import java.util.Properties;
import java.io.IOException;

public class ConnectionHolder {

    static LoggerProxy log = LoggerProxy.getInstance("#ConnectionHolder");

    final private String ORACLE_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
    DbUrl url;
    Connection conn;
    String dbVersion;

    public ConnectionHolder(DbUrl url) {
        this.url = url;
    }


    private Connection createConnection(DbUrl url) throws DBException {

        try {
            // 1. look at classpath
            try {
                Class.forName(ORACLE_DRIVER_CLASS);
                return DriverManager.getConnection(url.getFullUrl(), new Properties());
            } catch (ClassNotFoundException e) {
                // no driver on classpath
                int hh =0;
            } catch (SQLException e) {
                // could not connect try out another driver
                throw new DBException("Could not establish connection: " + e.getMessage());
            }

            // 2. check explicitly defined jar and driver class name
            String driverNameClass = (String) SharedObjectPool.getUserData(SharedConstants.ORACLE_JDBC_DRIVER);
            String libPath = (String) SharedObjectPool.getUserData(SharedConstants.ORACLE_JDBC_JAR_PATH);

            if (driverNameClass == null || libPath == null) {
                throw new DBException("Oracle JDBC driver class not specified.\n" +
                        "1. Choose Settings | SQL Assistant\n" +
                        "2. Specify path to the Oracle JDBC library\n" +
                        "3. Try to establish connection one more."
                );
            }

            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            cl = new CustomClassLoader(cl, libPath);
            Class driver = cl.loadClass(driverNameClass);
            Driver d = (Driver) driver.newInstance();
            return d.connect(url.getFullUrl(), new Properties());
        } catch (IOException e) {
            throw new DBException("Could not establish connection. Make sure that Oracle JDBC driver was specified correctly:\n" +
                    "1. Choose Settings | SQL Assistant\n" +
                    "2. Specify path to the Oracle JDBC library\n" +
                    "3. Try to establish connection one more."
            );
        } catch (ClassNotFoundException e) {
            throw new DBException("Could not establish connection: " + e.getMessage());
        } catch (SQLException e) {
            throw new DBException("Could not establish connection: " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new DBException("Could not establish connection: " + e.getMessage());
        } catch (InstantiationException e) {
            throw new DBException("Could not establish connection. Oracle JDBC driver class not specified.\n" +
                    "1. Choose Settings | SQL Assistant\n" +
                    "2. Specify path to the Oracle JDBC library\n" +
                    "3. Try to establish connection one more."
            );
        }
    }

    public Connection getConnection() throws DBException {
        if (conn != null) {
            return conn;
        }

        Statement stmt = null;
        ResultSet rs = null;

        // try to establish connection
        conn = createConnection(url);

        try {
            // connection created, request for version number
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT BANNER FROM V$VERSION WHERE BANNER LIKE 'CORE%'");
            if (rs.next()) {
                String v = rs.getString("BANNER");
                dbVersion = v.replaceAll("[^0-9\\.]", "");
            }

            log.info("Connection established, url: " + url.getUserHostPortServiceName());

            String ver = getDatabaseVersionShort();
            if("11".equals(ver) || "10".equals(ver) || "9".equals(ver)){
                return conn;
            } else {
                // database version is not supported
                String full_ver = this.getDatabaseVersion();
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                }

                conn = null;
                throw new DBException(
                        "Database version " + full_ver +
                        " is not supported by the plug-in, only Oracle 9i, 10g and 11g versions are supported currently."
                );
            }

        } catch (SQLException e) {
            throw new DBException("Could not establish a connection: " + e.getMessage());
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

    public ConnectionHolder cloneConnectionHolder() throws DBException{
        ConnectionHolder cloned = new ConnectionHolder(url);
        if(conn != null){
            cloned.getConnection();
        }

        return cloned;
    }


    public void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
            conn = null;

            log.info("Disconnected");
        }
    }

    public void reconnect() throws DBException {
        if (conn == null) {
            throw new DBException("Connection was not established.");
        }

        try {
            conn.close();
        } catch (SQLException e) {
        }

        conn = createConnection(url);
//            conn = DriverManager.getConnection(url.getFullUrl());
    }

    public DbUrl getDbUrl() {
        return url;
    }

    public String getDatabaseVersion() {
        if (conn != null) {
            return dbVersion;
        } else {
            throw new ConfigurationException("Connection was not established");
        }
    }

    public String getDatabaseVersionShort() {
        if (conn != null) {
            String[] parts = dbVersion.split("\\.");
            switch (parts.length) {
                case 0:
                case 1:
                    return null;
                default:
                    return parts[0]; // take only the first digit   /// + "." + parts[1];
            }
        } else {
            throw new ConfigurationException("Connection was not established");
        }
    }


    public boolean isConnected() {
        return conn != null;
    }
}
