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

package com.deepsky.database.ora;


import org.jetbrains.annotations.NotNull;

public class DbUrlSID extends JdbcDbUrl {

    private String user;
    private String pwd;
    private String port;
    private String host;
    private String sid;
    private String alias;

    public DbUrlSID(String user, String pwd, String host, String port, String sid) {
        this.user = user;
        this.pwd = pwd;
        this.port = port;
        this.host = host;
        this.sid = sid;
        this.alias = getKey();
    }

    public DbUrlSID(String user, String host, int port, String sid) {
        this.user = user;
        this.port = Integer.toString(port);
        this.host = host;
        this.sid = sid;
        this.alias = getKey();
    }


    public DbUrlSID(@NotNull DbUrlSID url) {
        this.user = url.getUser();
        this.pwd = url.getPwd();
        this.port = url.getPort();
        this.host = url.getHost();
        this.sid = url.getSID_ServiceName();
        this.alias = url.getKey();
    }


    public String getAlias() {
        return alias;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DbUrlSID) {
            DbUrlSID dbUrl = (DbUrlSID) obj;
            if (user != null && user.equalsIgnoreCase(dbUrl.getUser())
                    && port != null && port.equalsIgnoreCase(dbUrl.getPort())
                    && host != null && host.equalsIgnoreCase(dbUrl.getHost())
                    && sid != null && sid.equalsIgnoreCase(dbUrl.getSID_ServiceName())
                    ) {
                return DbUrlUtil.equals(dbUrl.getSshSettings(), getSshSettings());
            }
        }
        return false;
    }


    public int hashCode(){
        return getFullUrl(true).hashCode();
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

    @Override
    public String getSID_ServiceName() {
        return sid;
    }

    public String serialize(){
        return DbUrlUtil.serialize(this); //getFullUrl(false);
    }

    /**
     * @return - jdbc:oracle:thin:test22@192.168.3.1:1521:localdomain.test1
     */
    public String getFullUrl() {
        return getFullUrl(false);
    }

    public String getFullUrl(boolean withoutPwd) {
        String up = user!=null? user: "";
        if(!withoutPwd){
            if (pwd != null && pwd.length() != 0) {
                up = up + "/" + pwd;
            }
        }

        return jdbc + ":" + company + ":" + driverType + ":" + up + "@" + host + ":" + port + ":" + sid;
    }

    @Override
    public void setAlias(String alias) {
        if(alias != null && alias.length() > 0){
            this.alias = alias;
        }
    }


    /**
     * @return - test22@192.168.3.1:1521:localdomain.test1
     */
    public String getKey() {
        String up = "";
        if (user.length() != 0) {
            up = user;
        }

        return up + "@" + host + ":" + port + ":" + sid;
    }

    /**
     * @return - 192.168.1.23:1521:ORA1
     */
    public String getHostPortServiceName() {
        return host + ":" + port + ":" + sid;
    }


    public DbUID getDbUID(){
        return new DbUID(){

            public DbUrl buildDbUrl(String user) {
                return new DbUrlSID(user.toLowerCase(), pwd, getHost(), getPort(), getSID_ServiceName());
            }

            public boolean derivedFrom(DbUrl dbUrl) {
                return dbUrl instanceof JdbcDbUrl
                        && getHostPortServiceName().equalsIgnoreCase(((JdbcDbUrl)dbUrl).getHostPortServiceName());
            }

            public String key() {
                return getHostPortServiceName().toLowerCase();
            }

            public String toString() {
                return key();
            }
        };
    }

}
