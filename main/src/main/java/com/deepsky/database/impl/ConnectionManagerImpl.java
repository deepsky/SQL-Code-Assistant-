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

import com.deepsky.conf.CacheLocator;
import com.deepsky.database.*;
import com.deepsky.database.exec.SQLExecutor;
import com.deepsky.database.exec.impl.RespMessageTable;
import com.deepsky.database.exec.impl.SQLExecutorDefault;
import com.deepsky.database.ora.LowLevelConnectionProvider;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.DbSchemaIndexer;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.common.PluginKeys2;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.messages.MessageBus;
import com.jcraft.jsch.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.sql.Connection;
import java.util.*;
import java.util.List;

public class ConnectionManagerImpl implements ConnectionManager { //}, CacheManagerListener {

    static LoggerProxy log = LoggerProxy.getInstance("#ConnectionManagerImpl");

    private File cacheDir = null;

    private ConnectionHelper connHelper = new ConnectionHelperImplExt();
    private ConnectionPersistStorage ciHelper;
    private DbSchemaIndexer dbIndexer;

    private List<ConnectionManagerListener> lnrs = new ArrayList<ConnectionManagerListener>();

    private List<String> dmlStatementList = new ArrayList<String>();
    SQLExecutor sqlExecutor = null;

    private Project project;

    @NotNull
    public DbUrl[] getDbUrlList() {
        Set<DbUrl> hostSet = new HashSet<DbUrl>();
        ConnectionInfo[] l = ciHelper.getList();
        for (ConnectionInfo ci : l) {
            hostSet.add(ci.getUrl());
        }

        return hostSet.toArray(new DbUrl[hostSet.size()]);
    }

    public void close() {
        dbIndexer.stop();

        connHelper.disconnect();
//        dbIndexer.removeListener(this);
    }

    public void addStateListener(ConnectionManagerListener l) {
        lnrs.add(l);
    }

    public void removeStateListener(ConnectionManagerListener listener) {
        lnrs.remove(listener);
    }


    public ConnectionManagerImpl(Project project, DbSchemaIndexer dbIndexer) {

        this.project = project;
        this.cacheDir = CacheLocator.getCacheDirectory();
        this.dbIndexer = dbIndexer;

        ciHelper = new ConnectionPersistStorage(new File(cacheDir.toString()));
        try {
            ciHelper.init(new ConnectionInfoFactoryImpl());
        } catch(Throwable e){
            // Report about issue
            log.warn("Connection init issue: " + e.getMessage());
        }

        // subscribe to DbSchemaIndexer events
        CacheManagerListener ll = new CacheManagerListener() {
            public void handleUpdate(int state) {
                if (state == CacheManagerListener.CACHE_UPDATED) {
                    ConnectionInfoImpl cinfo = (ConnectionInfoImpl) ciHelper.getActive();
                    if (cinfo != null) {
                        cinfo.stateChanged(SessionListener.OBJECT_CACHE_UPDATED);
                    }
                }
            }
        };

        MessageBus bus = project.getMessageBus();
        bus.connect().subscribe(CacheManagerListener.TOPIC, ll);
    }


    public DbMetaInfo getDbMetaInfo() {
        return connHelper.getDbMetaInfo();
    }

    @Nullable
    public DbUrl getDbUrl() {
        return connHelper.getDbUrl();
    }

    public void setAutoCommit(boolean state) throws DBException {
        if (isConnected()) {
            int cmd = 0;
            if (!connHelper.getAutoCommit() && state) {
                // going to set AutoCommit ON, check number of DML statements since last commit first
                if (dmlStatementList.size() == 0) {
                    // set autocommit silently
                } else {
                    // ask user for confirmation
                    cmd = Messages.showChooseDialog(
                            "There are not committed changes, do you want commit changes first?",
                            "Confirmation on changes committing",
                            new String[]{"Commit", "Rollback", "Cancel"}, "Commit", Messages.getWarningIcon());
                }
            }
            switch (cmd) {
                case 0: // commit
                    connHelper.setAutoCommit(state);
                    dmlStatementList.clear();
                    break;
                case 1: // rollback
                    rollbackChanges();
                    connHelper.setAutoCommit(state);
                    dmlStatementList.clear();
                    break;
                case 2: // cancel
                    break;
            }
//            connHelper.setAutoCommit(state);
//            if(state){
//                // autocommit turned ON
//                dmlStatementList.clear();
//            }
        }
    }

    private void rollbackChanges() {
        // TODO ---- should be simplified !!!!
        SQLExecutor exec = null;
        try {
            MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
            ASTNode node = generator.parse("ROLLBACK");
            ASTNode rollback = node.findChildByType(PlSqlElementTypes.ROLLBACK_STATEMENT);
            if (rollback != null) {
                exec = getSQLExecutor();
                exec.execute(rollback);
            }

/*
            TreeNodeBuilder builder = generator.parse0("ROLLBACK");
            Node root = builder.buildASTTree();

            Node[] nodes = root.findChildrenByType(PlSqlElementTypes.ROLLBACK_STATEMENT);
            if (nodes.length == 1) {
                exec = getSQLExecutor();
                exec.execute(nodes[0]);
            }
*/

        } catch (DBException e) {
            // todo -- handle error
        } catch (Throwable e1) {
            // todo -- handle error
        } finally {
            if (exec != null) {
                exec.close();
            }
        }
    }


    public boolean getAutoCommit() {
        return connHelper.getAutoCommit();
    }

    public boolean isConnected() {
        return connHelper.isConnected();
//        return conn != null && ciHelper.getActive() != null;
    }

/*
    public void handleUpdate(int state) {
        if (state == CacheManagerListener.CACHE_UPDATED) {
            ConnectionInfoImpl cinfo = (ConnectionInfoImpl) ciHelper.getActive();
            if (cinfo != null) {
                cinfo.stateChanged(SessionListener.OBJECT_CACHE_UPDATED);
            }
        }
    }
*/

    public MyProgressIndicator activateSessionOnStart(DbUrl dbUrl) {
        for (ConnectionInfo cinfo : ciHelper.getList()) {
            if (cinfo.getUrl().equals(dbUrl)) {
                if (cinfo.loginOnStart()) {
                    // activate session
                    ciHelper.deActivate();
                    return cinfo.connectAsynchronously();
                }
            }
        }
        return null;
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

    public ConnectionStatus checkConnection(DbUrl url) {
        try {
            ConnectionHelperImpl helper = new ConnectionHelperImpl();
            helper.connect(url);
            helper.disconnect();

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

        IndexManager indexMan = PluginKeys2.SQL_INDEX_MAN.getData(project);
        indexMan.removeIndex(cinfo.getUrl());
        return ciHelper.remove(cinfo.getUrl()) != null;
    }

    public void updateSession(ConnectionInfo cinfo, DbUrl url, boolean loginOnStartup, boolean repair, int period) {

        ConnectionInfo _cinfo = ciHelper.remove(cinfo.getUrl());
        if (_cinfo == null) {
            // todo -- throw exception
        } else {
            ConnectionInfoImpl _cinfo2 = (ConnectionInfoImpl) _cinfo;
            _cinfo2.setUrl(url);
            _cinfo2.setLoginOnStart(loginOnStartup);
            _cinfo2.setRepaireFailedConnection(repair);
            _cinfo2.setRefreshPeriod(period);

            ciHelper.put(_cinfo2);
        }
    }

    public SQLExecutor getSQLExecutor() throws DBException {
        if (connHelper.isConnected()) {
            if (sqlExecutor != null) {
                // todo -- rely on a user who should take care of the result set
//                sqlExecutor.close();
            }
            sqlExecutor = new SQLExecutorDefault(project, connHelper.getDbUrl(), connHelper.getConnection(), this);
            return sqlExecutor;
        } else {
            throw new DBException("Connection was not established.");
        }
    }

    public void refreshSession() {
        if (ciHelper.getActive() != null) {
//            cacheManager.refresh();
            dbIndexer.refresh();
        }
    }


    public void addProcessedStatement(ASTNode node) {
        if (!connHelper.getAutoCommit()) {
            // AutoCommit is OFF, check issued DML statements
            if (PlSqlElementTypes.DML_STATEMENTS.contains(node.getElementType())) {
                dmlStatementList.add(node.getText());
            } else if (node.getElementType() == PlSqlElementTypes.COMMIT_STATEMENT) {
                dmlStatementList.clear();
            } else if (node.getElementType() == PlSqlElementTypes.ROLLBACK_STATEMENT) {
                dmlStatementList.clear();
            } else {
                // todo
            }
        } else {
            dmlStatementList.clear();
        }

        // refresh db cache if DDL was run
        if (RespMessageTable.isStatementDDL(node.getElementType())) {
            refreshSession();
        }
//        if (PlSqlElementTypes.DDL_STATEMENTS.contains(node.getElementType())) {
//            refreshSession();
//        }
    }

    public List<String> statementListSinceLastCommit() {
        return dmlStatementList;
    }


    public class ConnectionInfoImpl extends ConnectionInfoBase {

        ConnectionProgressIndicator indicator = null;

        public ConnectionInfoImpl(DbUrl url, boolean loginOnStart) {
            super(url, loginOnStart);
        }

        public ConnectionInfoImpl(DbUrl url, boolean loginOnStart, boolean repaireFailedConnection, int refreshPeriod) {
            super(url, loginOnStart, repaireFailedConnection, refreshPeriod);
        }

        public ConnectionInfoImpl(DbUrl url, boolean loginOnStart, boolean repaireFailedConnection, int refreshPeriod, Date lastLogin) {
            super(url, loginOnStart, repaireFailedConnection, refreshPeriod, lastLogin);
        }

        public void connect() throws DBException {
            if (ciHelper.getActive() != null) {
                if (ciHelper.getActive().equals(this)) {
                    // session already activated
                    return;
                }

                ciHelper.getActive().disconnect();
            }

            connHelper.connect(url);
            try {
                dbIndexer.setTimeout(refreshPeriod());
                dbIndexer.start(connHelper, null);
                ciHelper.setActive(url);

                // todo - why do we have two listeners??? StateListener and SessionListener
                stateChanged(SessionListener.CONNECTED);
                notifyLsnrs(new StateEventImpl(ConnectionManagerListener.CONNECTED, url));
                // registry login time
                lastLoginTime = new Date();
                ciHelper.put(this);
            } catch (ConnectionPersistStorage.HelperException e) {
                throw new DBException(e.getMessage());
            }
        }


        public MyProgressIndicator connectAsynchronously() {
            indicator = new ConnectionProgressIndicator(
                    this.getUrl().getKey().toLowerCase()) {
                public void setStatus(ProgressStatus status) {
                    super.setStatus(status);
                    if (status != ProgressStatus.INPROGRESS) {
                        // connection task completed
                        indicator = null;
                    }
                }
            };

            Thread schemaMonitor = new Thread(
                    new AsynchronousConnector(indicator, this)
            );

//            int prior = schemaMonitor.getPriority();
            schemaMonitor.setPriority(3);
            schemaMonitor.setDaemon(true);
            schemaMonitor.start();

            return indicator;
        }

        public boolean isConnected() {
            return connHelper.isConnected() && ciHelper.getActive() != null && ciHelper.getActive().equals(this);
        }

        public boolean disconnect() {
            if (ciHelper.getActive() != null && ciHelper.getActive().equals(this)) {
                if (indicator != null) {
                    indicator.cancel();
                }
                dbIndexer.stop();
                connHelper.disconnect();
                stateChanged(SessionListener.DISCONNECTED);
                ciHelper.deActivate();

                return true;
            }

            return false;
        }

        public void refreshSession() {
            if (ciHelper.getActive() != null && ciHelper.getActive().equals(this)) {
                dbIndexer.refresh();
            }
        }
    }


    private class ConnectionInfoFactoryImpl implements ConnectionInfoFactory {
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
        ConnectionProgressIndicator ind;
        ConnectionInfoImpl itself;

        public AsynchronousConnector(@NotNull ConnectionProgressIndicator ind, ConnectionInfoImpl itself) {
            this.ind = ind;
            this.itself = itself;
        }

        public void run() {

            Object sync = new Object();

            int nbr = dbIndexer.getStartupStepsNbr();
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
                    ind.setCurrentMessage("Disconnecting from " + ciHelper.getActive().getUrl().getKey() + " ...");
                    ciHelper.getActive().disconnect();

                    // update completed number of steps
                    __oneMoreStepDone__();
                    __assertCancel__();
                } else {
                    //
                    ind.setNumberOfChunks(1 + nbr);
                }

                connHelper.connect(itself.getUrl());
                try {
                    ind.setCurrentMessage("Connecting to " + itself.getUrl().getKey() + " ...");
                    __oneMoreStepDone__();
                    __assertCancel__();

                    ind.setCurrentMessage("Loading objects ...");

                    dbIndexer.setTimeout(itself.refreshPeriod());
                    dbIndexer.start(connHelper, new StartupCacheManagerListener() {
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

                    ciHelper.setActive(itself.getUrl());

                    // todo - why do we have two listeners??? StateListener and SessionListener
                    itself.stateChanged(SessionListener.CONNECTED);
                    notifyLsnrs(new StateEventImpl(ConnectionManagerListener.CONNECTED, itself.getUrl()));
                    // registry login time
                    itself.setLastLiginTime(new Date());
                    ciHelper.put(itself);

                    while (!cacheLoaded) {
                        synchronized (sync) {
                            sync.wait(100);
                            __assertCancel__();
                        }
                    }
                    ind.setStatus(MyProgressIndicator.ProgressStatus.DONE_SUCCESSFUL);
                } catch (ConnectionPersistStorage.HelperException e) {
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
                dbIndexer.stop();
                connHelper.disconnect();
                itself.stateChanged(SessionListener.DISCONNECTED);
                notifyLsnrs(new StateEventImpl(ConnectionManagerListener.DISCONNECTED));
                ciHelper.deActivate();

                ind.setStatus(MyProgressIndicator.ProgressStatus.CANCELED);
            } catch (Throwable e) {
                dbIndexer.stop();

                connHelper.disconnect();
                itself.stateChanged(SessionListener.DISCONNECTED);
                notifyLsnrs(new StateEventImpl(ConnectionManagerListener.DISCONNECTED));
                ciHelper.deActivate();

                String error = e.getMessage();
                ind.setErrorMessage(error != null ? error : "Could not establish connection!");
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


    private class ConnectionProgressIndicator implements MyProgressIndicator {

        int overallNbr = -1;
        int rest = -1;
        String message;
        AsynchronousConnector asynchronousConnector;
        String error = "Unknown error";
        String targetDatabase;

        ProgressStatus status = ProgressStatus.INPROGRESS;

        public ConnectionProgressIndicator(@NotNull String targetDatabase) {
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


    // --------------------------------------------------
    private class ConnectionHelperImplExt extends ConnectionHelperImpl {

        public void disconnect() {
            if (isConnected()) {
                notifyLsnrs(new StateEventImpl(ConnectionManagerListener.DISCONNECTED, super.getDbUrl()));
                super.disconnect();
            }
        }

        public void connect(DbUrl url) throws DBException {
            super.connect(url);
            boolean autoCommit = PluginKeys.PLUGIN_SETTINGS.getData(project).isAutoCommit();
            super.setAutoCommit(autoCommit);
        }

        public void setAutoCommit(boolean enabled) throws DBException {
            super.setAutoCommit(enabled);
            PluginKeys.PLUGIN_SETTINGS.getData(project).setAutoCommit(enabled);
        }
    }


}
