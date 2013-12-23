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

package com.deepsky.database.impl;

import com.deepsky.database.ConnectionInfo;
import com.deepsky.database.DBException;
import com.deepsky.database.MyProgressIndicator;
import com.deepsky.database.SessionListener;
import com.deepsky.database.ora.DbUrl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class ConnectionInfoBase implements ConnectionInfo {

    protected DbUrl url;
    protected boolean loginOnStart;
    protected boolean repaireFailedConnection;
    protected int refreshPeriod;
    protected Date lastLoginTime;

    protected Set<SessionListener> listeners = new HashSet<SessionListener>();

    public ConnectionInfoBase(DbUrl url, boolean loginOnStart) {
        this.url = url;
        this.loginOnStart = loginOnStart;
    }

    public ConnectionInfoBase(DbUrl url, boolean loginOnStart, boolean repaireFailedConnection, int refreshPeriod) {
        this.url = url;
        this.loginOnStart = loginOnStart;
        this.repaireFailedConnection = repaireFailedConnection;
        this.refreshPeriod = refreshPeriod;
    }

    public ConnectionInfoBase(DbUrl url, boolean loginOnStart, boolean repaireFailedConnection, int refreshPeriod, Date lastLogin) {
        this.url = url;
        this.loginOnStart = loginOnStart;
        this.repaireFailedConnection = repaireFailedConnection;
        this.refreshPeriod = refreshPeriod;
        this.lastLoginTime = lastLogin;
    }

    public DbUrl getUrl() {
        return url;
    }

    public boolean equals(Object o) {
        if (o instanceof ConnectionInfoBase) {
            ConnectionInfoBase ext = (ConnectionInfoBase) o;
            return url.serialize().equalsIgnoreCase(ext.getUrl().serialize());
        }

        return false;
    }

    public boolean loginOnStart() {
        return loginOnStart;
    }

    public boolean repaireFailedConnection() {
        return repaireFailedConnection;
    }

    public int refreshPeriod() {
        return refreshPeriod;
    }

    public Date lastLoginTime() {
        return lastLoginTime;
    }

    public void addListener(SessionListener lstnr) {
        listeners.add(lstnr);
    }

    public void removeListener(SessionListener lstnr) {
        listeners.remove(lstnr);
    }

    public void stateChanged(int command) {
        for (SessionListener l : listeners) {
            l.updated(this, command);
        }
    }

    public abstract void connect() throws DBException;

    public abstract MyProgressIndicator connectAsynchronously();

    public abstract boolean isConnected();

    public abstract boolean disconnect();
    public abstract void refreshSession();


    public void setUrl(DbUrl url) {
        this.url = url;
    }

    public void setLoginOnStart(boolean loginOnStartup) {
        this.loginOnStart = loginOnStartup;
    }

    public void setRepaireFailedConnection(boolean repair) {
        this.repaireFailedConnection = repair;
    }

    public void setRefreshPeriod(int period) {
        this.refreshPeriod = period;
    }

    public void setLastLiginTime(Date date) {
        this.lastLoginTime = date;
    }
    
}


