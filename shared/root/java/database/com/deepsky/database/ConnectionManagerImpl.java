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

package com.deepsky.database;

import com.deepsky.database.exec.SQLExecutor;
import com.deepsky.database.exec.impl.SQLExecutorDefault;
import com.deepsky.database.ora.ConnectionHolder;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.sql.Connection;
import java.util.*;

public class ConnectionManagerImpl implements ConnectionManager, CacheManagerListener {

    static LoggerProxy log = LoggerProxy.getInstance("#ConnectionManagerImpl");

    File cacheDir = null;

    ConnectionHolder conn;
    ConnectionInfoHelper ciHelper;
    CacheManager cacheManager;

    List<ConnectionManagerListener> lnrs = new ArrayList<ConnectionManagerListener>();


    public static ConnectionManager getInstance() {
        return PluginKeys.CONNECTION_MANAGER.getData();
//        return instance;
    }

    @NotNull
    public String[] getHostList(){
        Set<String> hostSet = new HashSet<String>();
        ConnectionInfo[] l = ciHelper.getList();
        for(ConnectionInfo ci: l){
            hostSet.add(ci.getUrl().getHost().toLowerCase());
        }
        
        return hostSet.toArray(new String[hostSet.size()]);
    }

    public void close() {
        cacheManager.stop();

        if (conn != null && conn.isConnected()) {
            conn.disconnect();
            notifyLsnrs(new StateEventImpl(ConnectionManagerListener.DISCONNECTED));
        }

        cacheManager.removeListener(this);
    }

    public void addStateListener(ConnectionManagerListener l) {
        lnrs.add(l);
    }

    public void removeStateListener(ConnectionManagerListener listener) {
        lnrs.remove(listener);
    }

    ConnectionProgressIndicatorImpl startupProgressInd;

    public ConnectionManagerImpl(CacheManager cacheManager) {

        cacheDir = CacheLocator.getCacheDirectory();

        this.cacheManager = cacheManager; //CacheManager3.getInstance();
        this.cacheManager.addListener(this);//init(cacheDir);
//        cache = new OraObjectCache3(); //cacheDir);

        ciHelper = new ConnectionInfoHelper(new File(cacheDir.toString()));
        ciHelper.init(new ConnectionInfoFactoryImpl());

/*
        // activate session if there is ACTIVE_SESSION_KEY reference
        ConnectionInfo cinfo = ciHelper.getActive();
        if (cinfo != null) {
            if (cinfo.loginOnStart()) {
                startupProgressInd = new ConnectionProgressIndicatorImpl(
                        cinfo.getUrl().getUserHostPortServiceName().toLowerCase() ){
                    public void setStatus(ProgressStatus status) {
                        super.setStatus(status);
                        if(status != ProgressStatus.INPROGRESS){
                            // connection task completed
                            startupProgressInd = null;
                        }
                    }
                };

                ciHelper.deActivate();
                Thread schemaMonitor = new Thread(
                        new AsynchronousConnector(startupProgressInd, (ConnectionInfoImpl) cinfo)
                );
                schemaMonitor.setPriority(3);
                schemaMonitor.setDaemon(true);
                schemaMonitor.start();
            } else {
                ciHelper.deActivate();
            }
        }
*/
    }

    public MyProgressIndicator getStartupConnectionIndicator() {
        return startupProgressInd;
    }

    public DbMetaInfo getDbMetaInfo() {
        if (conn != null && conn.isConnected()) {
            return new DbMetaInfo() {
                public String getDbVersion() {
                    return conn.getDatabaseVersion();
                }
            };
        } else {
            return null;
        }
    }

    public DbUrl getDbUrl() {
        if (conn != null) {
            ConnectionInfo cinfo = ciHelper.getActive();
            if (cinfo != null) {
                return cinfo.getUrl();
            }
        }

        return null;
    }

    public boolean isConnected() {
        return conn != null && ciHelper.getActive() != null;
    }

    public void handleUpdate(int state) {
        if (state == CacheManagerListener.CACHE_UPDATED) {
            ConnectionInfoImpl cinfo = (ConnectionInfoImpl) ciHelper.getActive();
            if (cinfo != null) {
                cinfo.stateChanged(SessionListener.OBJECT_CACHE_UPDATED);
            }
        }
    }

    public boolean activateSessionOnStart(DbUrl dbUrl) {
        for (ConnectionInfo cinfo : ciHelper.getList()) {
            if (cinfo.getUrl().equals(dbUrl)) {
                if (cinfo.loginOnStart()) {
                    // activate session
                    ciHelper.deActivate();

                    startupProgressInd = new ConnectionProgressIndicatorImpl(
                            cinfo.getUrl().getUserHostPortServiceName().toLowerCase()) {
                        public void setStatus(ProgressStatus status) {
                            super.setStatus(status);
                            if (status != ProgressStatus.INPROGRESS) {
                                // connection task completed
                                startupProgressInd = null;
                            }
                        }
                    };

                    Thread schemaMonitor = new Thread(
                            new AsynchronousConnector(startupProgressInd, (ConnectionInfoImpl) cinfo)
                    );
                    schemaMonitor.setPriority(3);
                    schemaMonitor.setDaemon(true);
                    schemaMonitor.start();
                    return true;
                }
            }
        }
        return false;
    }


    class StateEventImpl implements ConnectionManagerListener.StateEvent {
        int state;
        DbUrl url;

        public StateEventImpl(int state, DbUrl url) {
            this.state = state;
            this.url = url;
        }

        public StateEventImpl(int state) {
            this.state = state;
        }

        public int getStatus() {
            return state;
        }

        public DbUrl getUrl() {
            return url;
        }
    }

    void notifyLsnrs(ConnectionManagerListener.StateEvent state) {
        for (ConnectionManagerListener l : lnrs) {
            l.handleUpdate(state);
        }
    }

    public ConnectionInfo createSession(DbUrl url, boolean loginOnStart, boolean repaireFailedConn, int refreshPeriod) throws DbConfigurationException {
        ConnectionInfoImpl cinfo = new ConnectionInfoImpl(url, loginOnStart, repaireFailedConn, refreshPeriod);
        ciHelper.put(cinfo);
        return cinfo;
    }

    public boolean checkConnection(DbUrl url) {
        ConnectionHolder holder = new ConnectionHolder(url);
        try {
            holder.getConnection();
            holder.disconnect();

            return true;
        } catch (DBException e) {
            return false;
        }
    }

    public ConnectionStatus checkConnectionEx(DbUrl url) {
        ConnectionHolder holder = new ConnectionHolder(url);
        try {
            holder.getConnection();
            holder.disconnect();

            return new ConnectionStatusImpl(true, "Connection available");
        } catch (DBException e) {
            return new ConnectionStatusImpl(false, e.getMessage());
        }
    }

    class ConnectionStatusImpl implements ConnectionStatus {

        boolean status;
        String errorMessage;

        public ConnectionStatusImpl(boolean status, String errorMessage) {
            this.status = status;
            this.errorMessage = errorMessage;
        }

        public boolean isConnected() {
            return status;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    public ConnectionInfo[] getSessionList() {
        return ciHelper.getList();
    }

    public boolean removeSession(ConnectionInfo cinfo) {
        if (ciHelper.getActive() != null) {
            if (ciHelper.getActive().equals(cinfo)) {
                // session is active at the moment, close it first
                ciHelper.getActive().disconnect();
            }
        }

        return ciHelper.remove(cinfo.getUrl()) != null;
    }

    public void updateSession(ConnectionInfo cinfo, DbUrl url, boolean loginOnStartup, boolean repair, int period) {

        ConnectionInfo _cinfo = ciHelper.remove(cinfo.getUrl());
        if (_cinfo == null) {
            // todo -- throw exception
        } else {
            ConnectionInfoImpl _cinfo2 = (ConnectionInfoImpl) _cinfo;
            _cinfo2.url = url;
            _cinfo2.loginOnStart = loginOnStartup;
            _cinfo2.repaireFailedConnection = repair;
            _cinfo2.refreshPeriod = period;

            ciHelper.put(_cinfo2);
        }
    }

    SQLExecutor sqlExecutor = null;

    public SQLExecutor getSQLExecutor() throws DBException {
        if (conn != null) {
            if (sqlExecutor != null) {
                sqlExecutor.close();
            }
            sqlExecutor = new SQLExecutorDefault(conn.getConnection());
            return sqlExecutor;
        } else {
            throw new DBException("Connection was not established.");
        }
    }

    public Connection getConnection() throws DBException {
        if (conn != null) {
            return conn.getConnection();
        } else {
            throw new DBException("Connection was not established.");
        }
    }

    public class ConnectionInfoImpl implements ConnectionInfo {

        DbUrl url;
        boolean loginOnStart;
        boolean repaireFailedConnection;
        int refreshPeriod;
        Date lastLoginTime;

        List<SessionListener> listeners = new ArrayList<SessionListener>();

        public ConnectionInfoImpl(DbUrl url, boolean loginOnStart) {
            this.url = url;
            this.loginOnStart = loginOnStart;
        }

        public ConnectionInfoImpl(DbUrl url, boolean loginOnStart, boolean repaireFailedConnection, int refreshPeriod) {
            this.url = url;
            this.loginOnStart = loginOnStart;
            this.repaireFailedConnection = repaireFailedConnection;
            this.refreshPeriod = refreshPeriod;
        }

        public ConnectionInfoImpl(DbUrl url, boolean loginOnStart, boolean repaireFailedConnection, int refreshPeriod, Date lastLogin) {
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
            if (o instanceof ConnectionInfoImpl) {
                ConnectionInfoImpl ext = (ConnectionInfoImpl) o;
                return url.getFullUrl().equalsIgnoreCase(ext.getUrl().getFullUrl());
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

        public void stateChanged(int command) {
            for (SessionListener l : listeners) {
                l.updated(command);
            }
        }

        public void connect() throws DBException {
            if (ciHelper.getActive() != null) {
                if (ciHelper.getActive().equals(this)) {
                    // session already activated
                    return;
                }

                ciHelper.getActive().disconnect();
            }

            conn = new ConnectionHolder(url);
            try {
                conn.getConnection();
                cacheManager.setTimeout(refreshPeriod());
                ConnectionHolder cloned = conn.cloneConnectionHolder();
                cacheManager.start(cloned);
                ciHelper.setActive(url);

                // todo - why do we have two listeners??? StateListener and SessionListener
                stateChanged(SessionListener.CONNECTED);
                notifyLsnrs(new StateEventImpl(ConnectionManagerListener.CONNECTED, url));
                // registry login time
                lastLoginTime = new Date();
                ciHelper.put(this);
            } catch (ConnectionInfoHelper.HelperException e) {
                throw new DBException(e.getMessage());
            }
        }


        public MyProgressIndicator connectAsynchronously() {

            ConnectionProgressIndicatorImpl ind = new ConnectionProgressIndicatorImpl(
                    this.getUrl().getUserHostPortServiceName().toLowerCase()
            );
            Thread schemaMonitor = new Thread(
                    new AsynchronousConnector(ind, this)
            );
//            int prior = schemaMonitor.getPriority();
            schemaMonitor.setPriority(3);
            schemaMonitor.setDaemon(true);
            schemaMonitor.start();

            return ind;
        }

        public boolean isConnected() {
            return conn != null && ciHelper.getActive() != null && ciHelper.getActive().equals(this);
        }

        public boolean disconnect() {
            if (ciHelper.getActive() != null && ciHelper.getActive().equals(this)) {
                cacheManager.stop();
                conn.disconnect();
                stateChanged(SessionListener.DISCONNECTED);
                notifyLsnrs(new StateEventImpl(ConnectionManagerListener.DISCONNECTED));
                ciHelper.deActivate();

                return true;
            }

            return false;
        }

        public boolean testConnection() {
            // todo
            return false;
        }

//        public ObjectCache getObjectCache() {
//            if (ciHelper.getActive() != null && ciHelper.getActive().equals(this)) {
//                return cache;
//            }
//            return null;
//        }
    }


    class ConnectionInfoFactoryImpl implements ConnectionInfoFactory {
        public ConnectionInfo create(DbUrl url, boolean loginOnStart, boolean repaireFailedConn, int refreshPeriod, Date lastLogin) {
            return new ConnectionInfoImpl(url, loginOnStart, repaireFailedConn, refreshPeriod, lastLogin);
        }

        public ConnectionInfo create(DbUrl url, boolean loginOnStart, boolean repaireFailedConn, int refreshPeriod) {
            return new ConnectionInfoImpl(url, loginOnStart, repaireFailedConn, refreshPeriod);
        }

        public ConnectionInfo create(DbUrl url, boolean loginOnStart, boolean repaireFailedConn) {
            return new ConnectionInfoImpl(url, loginOnStart);
        }
    }


    private class AsynchronousConnector implements Runnable {
        boolean cacheLoaded = false;
        boolean cancel = false;
        ConnectionProgressIndicatorImpl ind;
        ConnectionInfoImpl itself;

        public AsynchronousConnector(@NotNull ConnectionProgressIndicatorImpl ind, ConnectionInfoImpl itself) {
            this.ind = ind;
            this.itself = itself;
        }

        public void run() {

            Object sync = new Object();

            int nbr = cacheManager.getStartupStepsNbr(); //CacheManager3.getInstance().getStartupStepsNbr();
            ind.setConnector(this);
            try {
                if (ciHelper.getActive() != null) {
                    if (ciHelper.getActive().equals(itself)) {
                        // session already activated
                        ind.setConnector(null);
                        ind.setStatus(MyProgressIndicator.ProgressStatus.DONE_SUCCESSFUL);
                        return;
                    }

                    //  defined number of steps
                    ind.setNumberOfChunks(2 + nbr);
                    ind.setCurrentMessage("Disconnecting from " + ciHelper.getActive().getUrl().getUserHostPortServiceName() + " ...");
                    ciHelper.getActive().disconnect();
                    // update completed number of steps
                    __oneMoreStepDone__();
                    __assertCancel__();
                } else {
                    //
                    ind.setNumberOfChunks(1 + nbr);
                }

                conn = new ConnectionHolder(itself.url);
                try {
                    ind.setCurrentMessage("Connecting to " + itself.url.getUserHostPortServiceName() + " ...");
                    conn.getConnection();
                    __oneMoreStepDone__();
                    __assertCancel__();

                    ind.setCurrentMessage("Loading objects ...");

                    cacheManager.setTimeout(itself.refreshPeriod());
                    ConnectionHolder cloned = conn.cloneConnectionHolder();
                    cacheManager.start(cloned, new StartupCacheManagerListener() {
                        int lastStep = -1;

                        public void handleProgressMessage(int stepNumber, @NotNull String msg) {
                            if (stepNumber == STARTUP_COMPLETED) {
                                // cache loaded
                                cacheLoaded = true;
                            } else {
                                ind.setCurrentMessage(msg);
                                if (lastStep != stepNumber) {
                                    lastStep = stepNumber;
                                    __oneMoreStepDone__();
                                }
                            }
                        }
                    });
//                    __oneMoreStepDone__(1);
                    __assertCancel__();

                    ciHelper.setActive(itself.url);

                    // todo - why do we have two listeners??? StateListener and SessionListener
                    itself.stateChanged(SessionListener.CONNECTED);
                    notifyLsnrs(new StateEventImpl(ConnectionManagerListener.CONNECTED, itself.url));
                    // registry login time
                    itself.lastLoginTime = new Date();
                    ciHelper.put(itself);

                    while (!cacheLoaded) {
                        synchronized (sync) {
                            sync.wait(100);
                            __assertCancel__();
                        }
                    }
                    ind.setStatus(MyProgressIndicator.ProgressStatus.DONE_SUCCESSFUL);
                } catch (ConnectionInfoHelper.HelperException e) {
                    ciHelper.deActivate();
                    ind.setErrorMessage(e.getMessage());
                    ind.setStatus(MyProgressIndicator.ProgressStatus.FAILED);
                } catch (DBException e) {
                    ciHelper.deActivate();
                    ind.setErrorMessage(e.getMessage());
                    ind.setStatus(MyProgressIndicator.ProgressStatus.FAILED);
                } catch (InterruptedException e) {
                    ciHelper.deActivate();
                    ind.setErrorMessage(e.getMessage());
                    ind.setStatus(MyProgressIndicator.ProgressStatus.FAILED);
                }
            } catch (UrgeCancelExeception e) {
                cacheManager.stop();

                conn.disconnect();
                itself.stateChanged(SessionListener.DISCONNECTED);
                notifyLsnrs(new StateEventImpl(ConnectionManagerListener.DISCONNECTED));
                ciHelper.deActivate();

                ind.setStatus(MyProgressIndicator.ProgressStatus.CANCELED);
            } catch (Throwable e) {
                cacheManager.stop();

                conn.disconnect();
                itself.stateChanged(SessionListener.DISCONNECTED);
                notifyLsnrs(new StateEventImpl(ConnectionManagerListener.DISCONNECTED));
                ciHelper.deActivate();

                String error = e.getMessage();
                ind.setErrorMessage(error != null? error: "Could not establish connection!");
                ind.setStatus(MyProgressIndicator.ProgressStatus.FAILED);
            }

            ind.setConnector(null);
        }

        private void __assertCancel__() {
            if (cancel) throw new UrgeCancelExeception();
        }

        private void __oneMoreStepDone__() {
            ind.setDoneNumber(1);
        }

        public void cancel() {
            cancel = true;
        }

        class UrgeCancelExeception extends RuntimeException {
        }
    }


    private class ConnectionProgressIndicatorImpl implements MyProgressIndicator {

        int overallNbr = -1;
        int rest = -1;
        String message;
        AsynchronousConnector asynchronousConnector;
        String error = "Unknown error";
        String targetDatabase;

        ProgressStatus status = ProgressStatus.INPROGRESS;

        public ConnectionProgressIndicatorImpl(@NotNull String targetDatabase) {
            this.targetDatabase = targetDatabase;
        }

        @NotNull
        public String targetDatabase() {
            return targetDatabase;
        }

        public ProgressStatus getStatus() {
            return status;
        }

        public int getNumberOfAllChunks() {
            return overallNbr;
        }

        public int getRestOfShunks() {
            return overallNbr == -1 ? -1 : rest;
        }

        public int getNumberOfDoneChunks() {
            return overallNbr == -1 ? -1 : overallNbr - rest;
        }

        @NotNull
        public String getCurrentStepName() {
            return message != null ? message : "Connecting ...";
        }

        public boolean cancel() {
            if (asynchronousConnector != null) {
                asynchronousConnector.cancel();
                return true;
            }
            return false;
        }

        @NotNull
        public String getErrorMessage() {
            return error;
        }

        public void setErrorMessage(@NotNull String error) {
            this.error = error;
        }

        public void setNumberOfChunks(int i) {
            overallNbr = i;
            rest = i;
        }

        public void setDoneNumber(int i) {
            rest -= i;
        }

        public void setStatus(ProgressStatus status) {
            this.status = status;
        }

        public void setConnector(AsynchronousConnector asynchronousConnector) {
            this.asynchronousConnector = asynchronousConnector;
        }

        public void setCurrentMessage(String message) {
            this.message = message;
        }
    }


/*
    File discoverCacheDir() {

        File base;
        // / 1. Relative to the 'classes' directory
        URL url = this.getClass().getClassLoader().getResource(".");
/ *
        if (url != null && new File(url.getPath()).exists()) {
            base = new File(url.getPath()).getParentFile();
            File cacheDir = new File(base, "caches");
            if (cacheDir.exists()) {
                if (!cacheDir.isDirectory()) {
                    throw new ConfigurationException(cacheDir.toString() + " supposed to be a directory.");
                }
            } else {
                if (!cacheDir.mkdir()) {
                    throw new ConfigurationException("Could not create " + cacheDir.toString() + " cache directory.");
                }
            }

            return cacheDir;
        }
* /
        // 2. Based on idea.plugins.dir prop
        String idea_plugins_dir = System.getProperty("idea.plugins.path");
        if (idea_plugins_dir != null && new File(idea_plugins_dir).exists()) {
            // todo - find plsql plugin directory
        }

        // 3. Based on java temporary directory
        String java_io_tmpdir = System.getProperty("java.io.tmpdir");
        if (java_io_tmpdir != null && new File(java_io_tmpdir).exists()) {
            // todo
        }

        throw new ConfigurationException("Could not located cache directory.");
    }
*/
}
