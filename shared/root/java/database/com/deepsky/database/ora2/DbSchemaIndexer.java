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

package com.deepsky.database.ora2;

import com.deepsky.conf.CacheLocator;
import com.deepsky.database.CacheManagerListener;
import com.deepsky.database.DBException;
import com.deepsky.database.StartupCacheManagerListener;
import com.deepsky.database.ora.ClosedConnectionException;
import com.deepsky.database.ora.ConnectionHolder;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.ItemToUpdate;
import com.deepsky.database.ora2.loaders.*;
import com.deepsky.lang.common.PlSqlSupportLoader;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.indexMan.IndexBulkChangeListener;
import com.deepsky.lang.plsql.indexMan.Indexer;
import com.deepsky.lang.plsql.psi.utils.PlSqlUtil;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.lang.plsql.sqlIndex.SqlDomainIndex;
import com.deepsky.lang.plsql.sqlIndex.SysSchemaIndexProvider;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;


public class DbSchemaIndexer implements Indexer, Runnable {

    static LoggerProxy log = LoggerProxy.getInstance("#DbSchemaIndexer");

    final int CACHE_MANAGER_THREAD_PRIOR = 4;
    final static int PRELOAD_THRESHOLD = 2;

    final Object synch = new Object();

    ConnectionHolder conn;
    DbUrl url;

    String userName;

    // (database) object type were updated on last cache synch-ing 
    Set<String> updatedTypes = new HashSet<String>();

    private int TIMEOUT = 30 * 1000; // milliseconds

    //    StateListener lsnr = null;
//    List<CacheManagerListener> lsnrs = new ArrayList<CacheManagerListener>();

    StartupCacheManagerListener startupListener;

    Thread schemaMonitor;
    boolean stop = false;
    boolean firstUpdateDone = false;
    boolean forceWakeup = false;

    String storeDir;

    MessageBus bus;
    Project project;

    IndexManager indexManager;

    DbObjectCache objectCache;
    DbCacheManager dbObjectManager;
    Map<String, DbObjectLoader> type2handler = new HashMap<String, DbObjectLoader>();

    public DbSchemaIndexer(Project project, IndexManager indexManager) {

        this.project = project;
        this.indexManager = indexManager;

        bus = project.getMessageBus();

        File cacheDir = CacheLocator.getCacheDirectory();
        if (!cacheDir.exists()) {
            throw new ConfigurationException("Store directory not accessible: " + cacheDir.getAbsolutePath());
        }

        storeDir = cacheDir.getAbsolutePath();

        // add type specific processors
        type2handler.put(DbObject.TABLE, new TableLoader());
        type2handler.put(DbObject.SYNONYM, new SynonymLoader());
        type2handler.put(DbObject.VIEW, new ViewLoader());
        type2handler.put(DbObject.SEQUENCE, new SequenceLoader());
        type2handler.put(DbObject.PACKAGE, new PackageSpecLoader());
        type2handler.put(DbObject.PACKAGE_BODY, new PackageBodyLoader());
        type2handler.put(DbObject.TYPE, new TypeLoader());
        type2handler.put(DbObject.FUNCTION, new FunctionLoader());
        type2handler.put(DbObject.PROCEDURE, new ProcedureLoader());
        type2handler.put(DbObject.TRIGGER, new TriggerLoader());

    }


    // --------------------------------------------------------------------------------
    final private int INIT_MANAGER = 0;
    final private int START_MANAGER = 1;
    final private int INIT_USER_CACHE = 2;
    final private int INIT_SYS_CACHE = 3;
    final private int CHECKUP_CHANGES = 4;
    final private int REQUEST_LAST_CHANGES = 5;
    final private int UPDATE_CACHE = 6;
    final private int CACHE_READY = StartupCacheManagerListener.STARTUP_COMPLETED;

    int currentStep = INIT_MANAGER;

    public int getStartupStepsNbr() {
        // todo ---
        return 7;
    }

    private void __reportStartupProgress__(@NotNull String message) {
        if (startupListener != null) {
            startupListener.handleProgressMessage(currentStep, message);
        }
    }

    private void __reportStartupProgress__(int stepNumber, @NotNull String message) {
        currentStep = stepNumber;
        if (startupListener != null) {
            startupListener.handleProgressMessage(currentStep, message);
            if (stepNumber == StartupCacheManagerListener.STARTUP_COMPLETED) {
                // dispose listener
                startupListener = null;
            }
        }
    }
// --------------------------------------------------------------------------------

    private void __assertShutdown__() {
        if (stop) throw new CancelUpdateException();
    }


    public void start(ConnectionHolder conn, StartupCacheManagerListener startupListener) throws DBException {

        // make sure the connection was established successfully
        __reportStartupProgress__(INIT_MANAGER, "Bootstrapping cache manager ...");
        conn.getConnection();

        this.conn = conn;
        this.url = conn.getDbUrl();
        this.startupListener = startupListener;
        this.userName = conn.getDbUrl().getUser().toUpperCase();

        dbObjectManager = new DbCacheManager(conn);

        String databaseVersionShort = conn.getDatabaseVersionShort();
        PlSqlSupportLoader c = (PlSqlSupportLoader) ApplicationManager.getApplication().getComponent(PlSqlSupportLoader.PLSQL_APPLICATION);

        SqlDomainIndex domainIndex = indexManager.findOrCreateIndex(url);
        if ("9".equals(databaseVersionShort)) {
/*
todo -- implement 9i index support
            domainIndex.addIndex(c.getSysSchemaIndexProvider().getSysUserIndex(SysSchemaIndexProvider.SYS_9, domainIndex));
            domainIndex.loadPublicSynonyms(c.getSysSchemaIndexProvider().getPublicSynonyms(SysSchemaIndexProvider.SYS_9));
*/
            domainIndex.addIndex(c.getSysSchemaIndexProvider().getSysUserIndex(SysSchemaIndexProvider.SYS_10, domainIndex));
            domainIndex.loadPublicSynonyms(c.getSysSchemaIndexProvider().getPublicSynonyms(SysSchemaIndexProvider.SYS_10));
        } else if ("10".equals(databaseVersionShort)) {
            domainIndex.addIndex(c.getSysSchemaIndexProvider().getSysUserIndex(SysSchemaIndexProvider.SYS_10, domainIndex));
            domainIndex.loadPublicSynonyms(c.getSysSchemaIndexProvider().getPublicSynonyms(SysSchemaIndexProvider.SYS_10));
        } else {
            // use 11g by default
            domainIndex.addIndex(c.getSysSchemaIndexProvider().getSysUserIndex(SysSchemaIndexProvider.SYS_11, domainIndex));
            domainIndex.loadPublicSynonyms(c.getSysSchemaIndexProvider().getPublicSynonyms(SysSchemaIndexProvider.SYS_11));
        }

        // create an interceptor for the object cache updater
        DbObjectCache objectCache1 = domainIndex.getObjectCache(url.getUser());
        InvocationHandler interceptor = new DbCacheEventNotifier(objectCache1);

        objectCache = (DbObjectCache) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{DbObjectCache.class},
                interceptor);


        stop = false;
        schemaMonitor = new Thread(this);
        int prior = schemaMonitor.getPriority();
        schemaMonitor.setPriority(CACHE_MANAGER_THREAD_PRIOR);
        schemaMonitor.setDaemon(true);
        schemaMonitor.start();

        __reportStartupProgress__(START_MANAGER, "Initializing cache thread ...");
    }


    public void start() {
        // do nothing?
    }

    /**
     * Stop database schema updater thread (synhronouse method)
     */
    public void stop() {
        stop = true;
        if (dbObjectManager != null) {
            dbObjectManager.cancelUpdate();
        }
        synchronized (synch) {
            synch.notify();
        }

        if (schemaMonitor != null) {
            try {
                schemaMonitor.join();
            } catch (InterruptedException e) {
            }
        }


        if (objectCache != null) {
            objectCache.flush();
            objectCache = null;
        }

    }


    private final int CACHE_INITED = 0;
    private final int BOOTSTRAP_DONE = 1;
    private final int CACHE_UPDATED = 2;

    private int startupStatus = -1;


    private void cacheUpdated(){
        for(CacheManagerListener l: listeners){
            l.handleUpdate(CacheManagerListener.CACHE_UPDATED);
        }
    }

    public void run() {

        boolean init = true;
        firstUpdateDone = false;
//        setState(CacheManagerListener.STARTED);

        try {
            startupStatus = CACHE_INITED;
            startupStatus = BOOTSTRAP_DONE;

            long ms = 0;
            boolean runDbCheckup = true;

            synchronized (synch) {
                while (!stop) {
                    if (runDbCheckup) {
                        try {
                            long ms1 = System.currentTimeMillis();
                            updatedTypes.clear();

                            __reportStartupProgress__(CHECKUP_CHANGES, "Checking the latest changes in USER schema ...");
                            log.info("[DB Checkup] Start ...");
                            // check up changes in database schema
//                            String[] changedTypes = userSchemaChanged();
                            String[] changedTypes = dbObjectManager.getTypesNeedInUpdate(
                                    objectCache, type2handler.keySet().toArray(new String[0])
                                );
                            __assertShutdown__();
                            int nbrUpdatedItems = 0;
                            if (changedTypes.length > 0) {
//                                String user = url.getUser().toUpperCase();
                                __reportStartupProgress__(REQUEST_LAST_CHANGES, "Getting list of objects ...");

                                Map<String,ItemToUpdate> dbObjVsCacheObj = dbObjectManager.createUserObjList(objectCache, changedTypes);
                                __assertShutdown__();

                                __reportStartupProgress__(UPDATE_CACHE, "Updating USER objects ...");
                                nbrUpdatedItems = dbObjectManager.makeUpdate(dbObjVsCacheObj, type2handler, objectCache, changedTypes);
                            } else {
                                __reportStartupProgress__(REQUEST_LAST_CHANGES, "Getting list of objects ...");
                                __reportStartupProgress__(UPDATE_CACHE, "Updating USER objects ...");
                            }


                            // avoid tracking of SYS
//                            if (init && sysObjs.size() == 0) {
//                                String[] types = new String[]{"TABLE", "PACKAGE", "VIEW"};
//                                List<ItemToUpdate> items2 =
//                                        createUserObjList("SYS", sysObjs, listSysObjectsSql, types);
//                                nbrUpdatedItems += makeUpdate(items2, sysObjs, types);
//                                init = false;
//                            }

                            ms1 = System.currentTimeMillis() - ms1;
                            if (!firstUpdateDone || nbrUpdatedItems > 0) {
                                log.info("[DB Checkup] Cache was updated and ready, time spent, (ms): " + ms1);
                                firstUpdateDone = true;
                                startupStatus = CACHE_UPDATED;
                                // notify about updating of the cache
                                //setState(CacheManagerListener.CACHE_UPDATED);
                                cacheUpdated();
                                __reportStartupProgress__(CACHE_READY, "Connection established successfully");

                                bus.syncPublisher(CacheManagerListener.TOPIC).handleUpdate(CacheManagerListener.CACHE_UPDATED);

                                if(updatedTypes.size() > 0){
                                    String[] types = updatedTypes.toArray(new String[updatedTypes.size()]);
                                    bus.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(url, types);

//                                    AbstractSchema schema = indexManager.findOrCreateIndex(url).getSimpleIndex(userName);
                                    AbstractSchema schema = indexManager.findOrCreateIndex(url, 0);
                                    verifyFilesOpenedInEditors(schema);
                                }
                            } else {
                                log.info("[DB Checkup] Up to date, time spent, (ms): " + ms1);
                            }

                        } catch (DBException e) {
                            log.warn("[Connection error] " + e.getMessage());
                        } catch (ClosedConnectionException e) {
                            // connection was lost, try to reconnect
                            try {
                                log.info("[Connection broken] Try to recover ...");
                                conn.reconnect();
                                log.info("[Connection broken] ... connection reestablished.");
                            } catch (DBException e1) {
                                log.info("[Connection broken] ... could not restore, will try later.");
                            }
                        }
                        runDbCheckup = false;
                        ms = System.currentTimeMillis();
                    }


                    runDbCheckup = readyForCheckup(ms);
                }
            }

            // correct shutdown
        } catch (CancelUpdateException e) {
            // urge shutdown
        }

        conn.disconnect();
//        setState(CacheManagerListener.STOPPED);
        dbObjectManager = null;
        log.info("Cache got down");
    }

    private void verifyFilesOpenedInEditors(final AbstractSchema index) {
        ApplicationManager.getApplication().invokeLater(new Runnable(){
            public void run() {
                PlSqlUtil.verifyDbOriginatedFilesInEditor(project, index);
            }
        });
    }

    public void refresh() {
        synchronized (synch) {
            forceWakeup = true;
            synch.notify();
        }
    }

    boolean readyForCheckup(long finishedTime) {
        try {
            long ms1 = System.currentTimeMillis();
            long timeout = finishedTime + TIMEOUT - ms1;
            if (timeout <= 0) {
                timeout = 5;
            }
            synch.wait(timeout);

            if (forceWakeup || System.currentTimeMillis() - ms1 >= (timeout - 5)) {
                // time to check the database
                forceWakeup = false;
                return true;
            }

        } catch (InterruptedException e) {
            // todo
        }
        return false;
    }


    public int getTimeout() {
        return TIMEOUT;
    }

    public void setTimeout(int timeout) {
        if (timeout < 30) {
            timeout = 30;
        }
        this.TIMEOUT = timeout * 1000;
    }

    public String getCurrentUser() {
        return userName;
    }


    private List<CacheManagerListener> listeners = new ArrayList<CacheManagerListener>();
    public void addListener(CacheManagerListener cacheListener) {
        listeners.add(cacheListener);
    }

    public void removeListener(CacheManagerListener cacheListener) {
        listeners.remove(cacheListener);
    }


    private class DbCacheEventNotifier implements InvocationHandler {

        DbObjectCache cache;

        public DbCacheEventNotifier(DbObjectCache cache) {
            this.cache = cache;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("update")) {
                // void update(String name, String type, String source, Date updateDate);
                String type = (String) args[1];
                updatedTypes.add(type);

            } else if (method.getName().equals("remove")) {
                // void remove(String type, String name);
                String type = (String) args[0];
                updatedTypes.add(type);

            }
            return method.invoke(cache, args);
        }
    }


    class UrgeShutdownException extends RuntimeException {
    }

}
