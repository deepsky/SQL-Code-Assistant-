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

import java.io.Serializable;


public class DbUrl implements Serializable {

    static final long serialVersionUID = 4430663516973571090L;

    String user;
    String pwd;
    String port;
    String host;
    String serviceName;
    String jdbc = "jdbc";
    String company = "oracle";
    String driverType = "thin";

    public DbUrl(String user, String pwd, String host, String port, String serviceName) {
        this.user = user;
        this.pwd = pwd;
        this.port = port;
        this.host = host;
        this.serviceName = serviceName;
    }

    public DbUrl(String user, String pwd, String url) {
        this.user = user == null ? "" : user;
        this.pwd = pwd == null ? "" : pwd;

        // parse url: jdbc:oracle:thin:test22@192.168.3.1:1521:localdomain.test1
        String[] parts = url.split(":");
        if (parts.length != 6) {
            throw new ConfigurationException("URL specification error: malformed.");
        }
        int i = 0;
        for (String p : parts) {
            switch (i++) {
                case 0: // jdbc
                    if (!p.equals("jdbc")) {
                        throw new ConfigurationException("URL specification error: driver is not jdbc compatible.");
                    }
                    this.jdbc = "jdbc";
                    break;
                case 1: // company
                    if (!p.equals("oracle")) {
                        throw new ConfigurationException("URL specification error: company name is not 'oracle'.");
                    }
                    this.company = p;
                    break;
                case 2: // driverType
                    this.driverType = p;
                    break;
                case 3: // user+password+host
                    String[] parts2 = p.split("@");
                    switch (parts2.length) {
                        case 1: // without name and pwd
                            this.host = parts2[0];
                            break;
                        case 2: // with name (and pwd)
                            // parse the first part to expract name/password
                            String[] name_pwd = parts2[0].split("/");
                            switch (name_pwd.length) {
                                case 1: // name without passowrd
                                    break;
                                case 2: // name with passowrd
                                    break;
                            }

                            this.host = parts2[1];
                            break;
                    }
                    break;
                case 4: // port
                    this.port = p;
                    break;
                case 5: // service name
                    this.serviceName = p;
                    break;
            }
        }
    }

    public DbUrl(String url) {
        this.user = "";
        this.pwd = "";

        // parse url: jdbc:oracle:thin:test22@192.168.3.1:1521:localdomain.test1
        String[] parts = url.split(":");
        if (parts.length != 6) {
            throw new ConfigurationException("URL specification error: malformed.");
        }
        int i = 0;
        for (String p : parts) {
            switch (i++) {
                case 0: // jdbc
                    if (!p.equals("jdbc")) {
                        throw new ConfigurationException("URL specification error: driver is not jdbc compatible.");
                    }
                    this.jdbc = "jdbc";
                    break;
                case 1: // company
                    if (!p.equals("oracle")) {
                        throw new ConfigurationException("URL specification error: company name is not 'oracle'.");
                    }
                    this.company = p;
                    break;
                case 2: // driverType
                    this.driverType = p;
                    break;
                case 3: // user+password+host
                    String[] parts2 = p.split("@");
                    switch (parts2.length) {
                        case 1: // without name and pwd
                            this.host = parts2[0];
                            break;
                        case 2: // with name (and pwd)
                            // parse the first part to expract name/password
                            String[] name_pwd = parts2[0].split("/");
                            switch (name_pwd.length) {
                                case 1: // name without passowrd
                                    this.user = name_pwd[0];
                                    break;
                                case 2: // name with passowrd
                                    this.user = name_pwd[0];
                                    this.pwd = name_pwd[1];
                                    break;
                            }

                            this.host = parts2[1];
                            break;
                    }
                    break;
                case 4: // port
                    this.port = p;
                    break;
                case 5: // service name
                    this.serviceName = p;
                    break;
            }
        }
    }


    public boolean equals(Object obj) {
        if (obj instanceof DbUrl) {
            DbUrl dburl = (DbUrl) obj;
            if (user != null && user.equalsIgnoreCase(dburl.getUser())
                    && pwd != null && pwd.equalsIgnoreCase(dburl.getPwd())
                    && port != null && port.equalsIgnoreCase(dburl.getPort())
                    && host != null && host.equalsIgnoreCase(dburl.getHost())
                    && serviceName != null && serviceName.equalsIgnoreCase(dburl.getServiceName())
                    && jdbc != null && jdbc.equalsIgnoreCase(dburl.jdbc)
                    && company != null && company.equalsIgnoreCase(dburl.company)
                    && driverType != null && driverType.equalsIgnoreCase(dburl.driverType)
                    ) {
                return true;
            }
        }
        return false;
    }


    public int hashCode(){
        return getFullUrl().hashCode();    
    }

    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }

    public String getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public String getServiceName() {
        return serviceName;
    }

    /**
     * @return - jdbc:oracle:thin:test22@192.168.3.1:1521:localdomain.test1
     */
    public String getFullUrl() {
        String up = "";
        if (user.length() != 0 && pwd.length() != 0) {
            up = user + "/" + pwd;
        } else if (user.length() != 0) {
            up = user;
        }

        return jdbc + ":" + company + ":" + driverType + ":" + up + "@" + host + ":" + port + ":" + serviceName;
    }

    /**
     * @return - test22@192.168.3.1:1521:localdomain.test1
     */
    public String getUserHostPortServiceName() {
        String up = "";
        if (user.length() != 0) {
            up = user;
        }

        return up + "@" + host + ":" + port + ":" + serviceName;
    }

    /**
     * @return - test22@192.168.3.1
     */
    public String getUserHost() {
        String up = "";
        if (user.length() != 0) {
            up = user;
        }

        return up + "@" + host;
    }


    /**
     * @return - 192.168.1.23:1521:ORA1
     */
    public String getHostPortServiceName() {
        return host + ":" + port + ":" + serviceName;
    }

}
