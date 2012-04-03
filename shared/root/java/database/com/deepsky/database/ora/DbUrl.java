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

public abstract class DbUrl {

    final static public String jdbc = "jdbc";
    final static public String company = "oracle";
    final static public String driverType = "thin";
    final static public String driverPrefix = "jdbc:oracle:thin:";

    private String alias;

    public abstract String getUser();

    public abstract String getPwd();

    public abstract String getPort();

    public abstract String getHost();

    public abstract String getSID_ServiceName();

    /**
     * Old syntax means this:
     *      jdbc:oracle:thin:@[HOST][:PORT]:SID
     * new syntax:
     *      jdbc:oracle:thin:@//[HOST][:PORT]/SERVICE
     * @return
     */
    public abstract boolean isOldSyntax();

    /**
     * @return - jdbc:oracle:thin:test22@192.168.3.1:1521:localdomain.test1
     */
    public abstract String getFullUrl();

    /**
     * @return - test22@192.168.3.1:1521:localdomain.test1
     */
    public abstract String getUserHostPortServiceName();


    /**
     * @return - 192.168.1.23:1521:ORA1 or //192.168.1.23:1521/ORA1
     */
    public abstract String getHostPortServiceName();


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias){
        this.alias = alias;
    }

    public String toString(){
        return getUserHostPortServiceName();
    }


    final private static Pattern DB_URL = Pattern.compile(
            "jdbc:oracle:thin:(?:[A-Za-z0-9\\_\\$]+(?:/[A-Za-z0-9\\_\\.\\-\\$]+)?)?\\@((?://)?[A-Za-z0-9\\_\\.\\-]+):(?:[0-9]+)(/|:)([A-Za-z0-9\\_\\.]+)"
    );

    public static DbUrl parse(String dbUrl) throws ConfigurationException {
        if(dbUrl == null){
            throw new ConfigurationException("Database URL not specified");
        }

        Matcher m = DB_URL.matcher(dbUrl);
        if(!m.find()){
            // TODO -- temp solution
            if(!dbUrl.startsWith("jdbc:oracle:thin:")){
                // try to prefix url with driver type and parse one more time
                m = DB_URL.matcher("jdbc:oracle:thin:" + dbUrl);
                if(m.find()){
                    // we are lucky!
                    dbUrl = "jdbc:oracle:thin:" + dbUrl;
                } else {
                    throw new ConfigurationException("Database URL is malformed");
                }
            } else {
                throw new ConfigurationException("Database URL is malformed");
            }
        }
        String host = m.group(1);
        String determinator = m.group(2);

        if(host != null && host.startsWith("//") && "/".equals(determinator)){
            // new syntax URL
            return new DbUrlServiceName(dbUrl);
        } else if(host != null && !host.startsWith("//") && ":".equals(determinator)){
            // old syntax URL
            return new DbUrlSID(dbUrl);
        } else {
            throw new ConfigurationException("Cannot parse database URL");
        }
    }
}
