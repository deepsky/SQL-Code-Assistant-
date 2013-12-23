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

public class DbUrlServiceName extends JdbcDbUrl {

    private String user;
    private String pwd;
    private String host;
    private String port;
    private String service;
    private String alias;

    public DbUrlServiceName(String user, String pwd, String host, String port, String service) {
        this.user = user;
        this.pwd = pwd;
        this.host = host;
        this.port = port;
        this.service = service;
        this.alias = getKey();
    }


    public String getAlias() {
        return alias;
    }


    public String getUser() {
        return user.toLowerCase();
    }


    public String getPwd() {
        return pwd.toLowerCase();
    }

    @Override
    public String getPort() {
        return port;
    }

    @Override
    public String getHost() {
        return host.toLowerCase();
    }

    @Override
    public String getSID_ServiceName() {
        return service.toLowerCase();
    }

    @Override
    public void setAlias(String alias) {
        if(alias != null && alias.length() > 0){
            this.alias = alias;
        }
    }

    public String serialize(){
        return DbUrlUtil.serialize(this); //getFullUrl(false);
    }

    public String getFullUrl() {
        return getFullUrl(false);
    }

    public String getKey() {
        return (user + "@//" + host + ":" + port + "/" + service).toLowerCase() ;
    }

    @Override
    public String getHostPortServiceName() {
        return ("//" + host + ":" + port + "/" + service).toLowerCase() ;
    }

    public boolean equals(Object obj) {
        if(obj instanceof DbUrlServiceName){
            if(getFullUrl(true).equals(((DbUrlServiceName) obj).getFullUrl(true))){
                DbUrlServiceName dbUrl = (DbUrlServiceName) obj;
                return DbUrlUtil.equals(dbUrl.getSshSettings(), getSshSettings());
            }
        }

        return false;
    }



    public int hashCode(){
        return getFullUrl(true).hashCode();
    }

    private String getFullUrl(boolean withoutPwd) {
        String up = user!=null? user: "";
        if(!withoutPwd){
            if (pwd != null && pwd.length() != 0) {
                up = up + "/" + pwd;
            }
        }

        return (jdbc + ":" + company + ":" + driverType + ":" + up + "@//" + host + ":" + port + "/" + service).toLowerCase();
    }


    public DbUID getDbUID(){
        return new DbUID(){

            public DbUrl buildDbUrl(String user) {
                return new DbUrlServiceName(user.toLowerCase(), pwd, getHost(), getPort(), getSID_ServiceName());
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
