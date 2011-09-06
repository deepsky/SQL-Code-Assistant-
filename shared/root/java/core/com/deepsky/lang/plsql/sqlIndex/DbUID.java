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

package com.deepsky.lang.plsql.sqlIndex;

import com.deepsky.database.ora.DbUrl;

final public class DbUID {

    String hostPortSID_ServiceName;

    private DbUID(String hostPortSID_ServiceName){
        this.hostPortSID_ServiceName = hostPortSID_ServiceName;
    }

    public static DbUID getDbUID(DbUrl dbUrl){
        return new DbUID(dbUrl.getHostPortServiceName());
    }

    public DbUrl buildDbUrl(String user){
        return DbUrl.parse(DbUrl.driverPrefix + user.toLowerCase() + "@" + hostPortSID_ServiceName);
    }

    public String key(){
        return hostPortSID_ServiceName.toLowerCase();
    }

    public boolean derivedFrom(DbUrl dbUrl) {
        return hostPortSID_ServiceName.equalsIgnoreCase(dbUrl.getHostPortServiceName());
    }

    public String toString(){
        return key();
    }
}
