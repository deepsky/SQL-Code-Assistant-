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

import com.deepsky.database.ConnectionManagerListener;
import com.deepsky.database.DBException;
import com.deepsky.database.DbMetaInfo;
import com.deepsky.database.ora.ConnectionHolder;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PluginKeys;
import com.intellij.openapi.project.Project;

import java.sql.Connection;

public abstract class AsynchronousConnector2 implements AsynchronousConnector {

    int status = 0;
    boolean cachedAutoCommit = true;
    Project project;

    private class ConnectWorker implements Runnable {

        DbUrl url = null;
        int request;

        public ConnectWorker(DbUrl url, int request) {
            this.url = url;
            this.request = request;
        }

        public void run() {
            try {
                if (request == CONNECT) {
                    connectStarted();
                    try {
                        holder = new ConnectionHolder(url);
                        holder.getConnection();
                        cachedAutoCommit = PluginKeys.PLUGIN_SETTINGS.getData(project).isAutoCommit();
                        holder.setAutoCommit(cachedAutoCommit);
                        connectFinished();
                    } catch (DBException e) {
                        connectionFailed();
                    }

                } else if (request == DISCONNECT) {
                    disconnectStarted();
                    if (holder != null) {
                        holder.disconnect();
                    }
                    disconnectFinished();
                }
            } catch (CancelConnectException e) {
                connectCanceled();
            } catch (Throwable e) {
                connectionFailed();
            }
        }

        public DbUrl getTargetUrl() {
            return url;
        }

    }

    // status codes
//    final static int CONNECTED = 1;
//    final static int DISCONNECTED = 2;
//    final static int CONNECT_IN_PROGRESS = 3;
//    final static int DISCONNECT_IN_PROGRESS = 4;

    // request codes
    final static int CONNECT = 101;
    final static int DISCONNECT = 102;

//    ConnectionHelper connHelper = new ConnectionHelper();
    ConnectionHolder holder;
    ConnectWorker worker;

    int pendingRequest = -1;
    DbUrl pendingUrl;

    public synchronized void connect(DbUrl url) {
        if(holder != null){
            throw new Error("Already connected");
        }

        Thread schemaMonitor = new Thread(new ConnectWorker(url, CONNECT));
        schemaMonitor.setPriority(3);
        schemaMonitor.setDaemon(true);
        schemaMonitor.start();
    }


    public synchronized void disconnect() {
        if(holder == null){
            throw new Error("Already disconnected");
        }

        Thread schemaMonitor = new Thread(new ConnectWorker(null, DISCONNECT));
        schemaMonitor.setPriority(3);
        schemaMonitor.setDaemon(true);
        schemaMonitor.start();
    }

    abstract void connectCanceled();

    abstract void connectStarted();

    abstract void connectFinished();

    abstract void disconnectStarted();

    abstract void disconnectFinished();

    abstract void connectionFailed();


//    abstract void connected();
//
//    abstract void disconnected();


    private class ConnectionHelper {

        ConnectionHolder holder;
        boolean cachedAutoCommit = true;

        public void disconnect() {
            if (isConnected()) {
                holder.disconnect();
                holder = null;
                //notifyLsnrs(new StateEventImpl(ConnectionManagerListener.DISCONNECTED));
            }
        }

        public DbMetaInfo getDbMetaInfo() {
            if (isConnected()) {
                return new DbMetaInfo() {
                    public String getDbVersion() {
                        return holder.getDatabaseVersion();
                    }
                };
            }

            return null;
        }

        public DbUrl getDbUrl() {
            if (holder != null) {
                return holder.getDbUrl();
            }
            return null;
        }

        public boolean isConnected() {
            return holder != null && holder.isConnected();
        }

        public Connection getConnection() throws DBException {
            if (holder != null)
                return holder.getConnection();
            else {
                throw new DBException("Not connected");
            }
        }

        public void connect(DbUrl url) throws DBException {
            if (holder != null) {
                if (holder.getDbUrl().equals(url)) {
                    return;
                } else {
                    holder.disconnect();
                }
            }
            holder = new ConnectionHolder(url);
            holder.getConnection();
            cachedAutoCommit = PluginKeys.PLUGIN_SETTINGS.getData(project).isAutoCommit();
            holder.setAutoCommit(cachedAutoCommit);
        }

        public ConnectionHolder cloneConnectionHolder() throws DBException {
            if (isConnected()) {
                return holder.cloneConnectionHolder();
            } else {
                throw new DBException("Not connected");
            }
        }

        public void setAutoCommit(boolean state) throws DBException {
            if (isConnected()) {
                holder.setAutoCommit(state);
                cachedAutoCommit = state;
                PluginKeys.PLUGIN_SETTINGS.getData(project).setAutoCommit(cachedAutoCommit);
            } else {
                throw new DBException("Not connected");
            }
        }

        /**
         * Quick method to get current AutoCommit state
         *
         * @return - autocommit state
         */
        public boolean getAutoCommit() {
            return cachedAutoCommit;
        }
    }

}
