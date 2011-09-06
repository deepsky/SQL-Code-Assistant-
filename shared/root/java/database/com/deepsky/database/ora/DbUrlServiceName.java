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

import com.deepsky.lang.plsql.ConfigurationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbUrlServiceName extends DbUrl {

    final private static Pattern NEW_FASHION_URL = Pattern.compile(
            "jdbc:oracle:thin:([A-Za-z0-9\\_\\$]+(/[A-Za-z0-9\\_\\.\\-\\$]+)?)?\\@//([A-Za-z0-9\\_\\.]+):([0-9]+)/([A-Za-z0-9\\_\\.]+)"
    );

    private String user;
    private String pwd;
    private String host;
    private String port;
    private String service;

    public DbUrlServiceName(String user, String pwd, String host, String port, String service) {
        this.user = user;
        this.pwd = pwd;
        this.host = host;
        this.port = port;
        this.service = service;
    }

    // jdbc:oracle:thin:[USER/PASSWORD]@//[HOST][:PORT]/SERVICE
    public DbUrlServiceName(String dbUrl) {
        if(dbUrl == null){
            throw new ConfigurationException("Database URL not specified");
        }

        Matcher m = NEW_FASHION_URL.matcher(dbUrl);
        if(!m.find()){
            throw new ConfigurationException("Database URL is malformed");
        }

        user = m.group(1);
        pwd = m.group(2);
        if(pwd != null){
            user = user.substring(0, user.length() - pwd.length());
            pwd = pwd.substring(1);
        }

        // normalize
        user = user==null? "": user;
        pwd = pwd==null? "": pwd;

        host = m.group(3);
        port = m.group(4);
        service = m.group(5);
    }

    public DbUrlServiceName(String userName, String pwd, String url) {
        this(url);
        this.user = userName;
        this.pwd = pwd;
    }

    @Override
    public String getUser() {
        return user.toLowerCase();
    }

    @Override
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
    public boolean isOldSyntax() {
        return false;
    }

    @Override
    public String getFullUrl() {
        return getFullUrl(false);
    }

    @Override
    public String getUserHostPortServiceName() {
        return (user + "@//" + host + ":" + port + "/" + service).toLowerCase() ;
    }

    @Override
    public String getHostPortServiceName() {
        return ("//" + host + ":" + port + "/" + service).toLowerCase() ;
    }

    public boolean equals(Object obj) {
        return obj instanceof DbUrlServiceName
                && getFullUrl(true).equals(((DbUrlServiceName) obj).getFullUrl(true));
    }


    public int hashCode(){
        return getFullUrl(true).hashCode();
    }

    private String getFullUrl(boolean withoutPwd) {
        String up = user!=null? user: "";
        if(!withoutPwd){
            if (pwd.length() != 0) {
                up = up + "/" + pwd;
            }
        }

        return (jdbc + ":" + company + ":" + driverType + ":" + up + "@//" + host + ":" + port + "/" + service).toLowerCase();
    }
}
