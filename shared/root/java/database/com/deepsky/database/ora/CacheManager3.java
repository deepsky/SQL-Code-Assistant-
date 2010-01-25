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

package com.deepsky.database.ora;

import com.deepsky.database.*;
import com.deepsky.database.cache.Cache;
import com.deepsky.database.cache.UpdatableCache;
import com.deepsky.database.ora.handlers.*;
import com.deepsky.lang.common.PlSqlSupportLoader;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


public class CacheManager3 implements CacheManager, Runnable {

    static LoggerProxy log = LoggerProxy.getInstance("#CacheManager3");

    final int CACHE_MANAGER_THREAD_PRIOR = 4;

    final String listSysObjectsSql =
            "SELECT OWNER, OBJECT_NAME, OBJECT_TYPE, STATUS, CREATED, LAST_DDL_TIME, TIMESTAMP " +
                    "FROM ALL_OBJECTS " +
                    "WHERE OBJECT_TYPE IN ( <OBJECT_TYPES> ) " +
                    "AND OWNER = 'SYS' AND OBJECT_NAME <> 'STANDARD'";

    final private String _listUserObjectsSql =
            "SELECT OBJECT_NAME, OBJECT_TYPE, STATUS, CREATED, LAST_DDL_TIME, TIMESTAMP " +
                    "FROM USER_OBJECTS a " +
                    "WHERE OBJECT_TYPE IN ( <OBJECT_TYPES> ) " +
                    "<CHECK_RECYCLEBIN> \n" +
                    "ORDER BY OBJECT_TYPE";

    final private String _listUpdatesSql =
            "select\n" +
                    "   object_type,\n" +
                    "   to_char(MAX_TIMESTAMP, 'yyyy-mm-dd:hh24:mi:ss') AS TIMESTAMP,\n" +
                    "   NBR_ELEMENTS\n" +
                    "from (\n" +
                    "   select \n" +
                    "       object_type,\n" +
                    "       MAX(to_date(TIMESTAMP, 'yyyy-mm-dd:hh24:mi:ss')) as MAX_TIMESTAMP,\n" +
                    "       count(*) as NBR_ELEMENTS\n" +
                    "   from all_objects a\n" +
                    "   where owner IN (<OWNERS>)\n" +
                    "   and object_type in ('PACKAGE', 'PACKAGE BODY', 'SEQUENCE', 'TYPE', 'TABLE', 'VIEW', 'PROCEDURE', 'FUNCTION', 'TRIGGER')\n" +
                    "   <CHECK_RECYCLEBIN> \n" +
                    "   group by object_type\n" +
                    "   ) ";

    final private String recyleBinSubquery =
            "   and not exists (select * from recyclebin b where b.object_name = a.object_name\n" +
                    "       and b.type = a.object_type)";

    private String listUpdatesSql, listUserObjectsSql;

    final static int PRELOAD_THRESHOLD = 2;

    Map<String, BaseHandler> type2handler = new HashMap<String, BaseHandler>();
    final Object synch = new Object();
    List<ItemToUpdate> queueItem2Update = Collections.synchronizedList(new ArrayList<ItemToUpdate>());

    ReentrantLock preloadListLock = new ReentrantLock();
    List<Triad> preloadList = new ArrayList<Triad>();

    ConnectionHolder conn;
    DbUrl url;
    String databaseVersionShort;

    UpdatableCache userObjs;
    Cache sysObjs;

    String userName;

    private int TIMEOUT = 30 * 1000; // milliseconds

    //    StateListener lsnr = null;
    List<CacheManagerListener> lsnrs = new ArrayList<CacheManagerListener>();

    StartupCacheManagerListener startupListener;

    Thread schemaMonitor;
    boolean stop = false;
    boolean firstUpdateDone = false;

    String storeDir;
    File dbObjectSpoolDir;
    private Map<String, Cache> caches = new HashMap<String, Cache>();

    public CacheManager3() {

        File cacheDir = CacheLocator.getCacheDirectory();
        if (!cacheDir.exists()) {
            throw new ConfigurationException("Store directory not accessible: " + cacheDir.getAbsolutePath());
        }

        storeDir = cacheDir.getAbsolutePath();
        dbObjectSpoolDir = new File(new File(storeDir), "uspool");
        // todo -- lack of validation
        boolean result = dbObjectSpoolDir.mkdir();

        // add type specific processors
        addHandler(new TableHandler());
        addHandler(new FunctionHandler());
        addHandler(new ProcedureHandler());
        addHandler(new ViewHandler());
        addHandler(new SequenceHandler());
        addHandler(new TypeHandler());
        addHandler(new PackageSpecHandler());
        addHandler(new PackageBodyHandler());
        addHandler(new TriggerHandler());
        addHandler(new SynonymHandler());

    }

    public void start(ConnectionHolder conn) throws DBException {
        start(conn, null);
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
        if (stop) throw new UrgeShutdownException();
    }


    public void start(ConnectionHolder conn, StartupCacheManagerListener startupListener) throws DBException {

        // make sure the connection was established successfully
        __reportStartupProgress__(INIT_MANAGER, "Bootstrapping cache manager ...");
        conn.getConnection();

        this.conn = conn;
        this.url = conn.getDbUrl();
        this.startupListener = startupListener;
        this.userName = conn.getDbUrl().getUser().toUpperCase();

        databaseVersionShort = conn.getDatabaseVersionShort();

        // tune SQL query according to database version
        if ("9".equals(databaseVersionShort)) {
            listUserObjectsSql = _listUserObjectsSql.replace("<CHECK_RECYCLEBIN>", "");
            listUpdatesSql = _listUpdatesSql.replace("<CHECK_RECYCLEBIN>", "");
        } else {
            // 10 and higher
            listUserObjectsSql = _listUserObjectsSql.replace("<CHECK_RECYCLEBIN>", recyleBinSubquery);
            listUpdatesSql = _listUpdatesSql.replace("<CHECK_RECYCLEBIN>", recyleBinSubquery);
        }

        PlSqlSupportLoader c = (PlSqlSupportLoader) ApplicationManager.getApplication().getComponent(PlSqlSupportLoader.PLSQL_APPLICATION);
        sysObjs = c.getSysObjectCache(databaseVersionShort);
        userObjs = c.getUserObjectCache(url.getUserHostPortServiceName());

        caches.put("SYS", sysObjs);
        caches.put(userName, userObjs);

        stop = false;
        schemaMonitor = new Thread(this);
        int prior = schemaMonitor.getPriority();
        schemaMonitor.setPriority(CACHE_MANAGER_THREAD_PRIOR);
        schemaMonitor.setDaemon(true);
        schemaMonitor.start();

        __reportStartupProgress__(START_MANAGER, "Initializing cache thread ...");
    }


    public void stop() {
        stop = true;
        synchronized (synch) {
            synch.notify();
        }

        if (schemaMonitor != null) {
            try {
                schemaMonitor.join();
            } catch (InterruptedException e) {
            }
        }

        if (userObjs != null) {
            userObjs.close();
            userObjs = null;
        }

        if (sysObjs != null) {
            sysObjs.close();
            sysObjs = null;
        }

        caches.clear();
    }

    public synchronized void addListener(CacheManagerListener lsnr) {
        lsnrs.add(lsnr);
    }

    public synchronized void removeListener(CacheManagerListener lsnr) {
        lsnrs.remove(lsnr);
    }

    private void addHandler(BaseHandler handler) {
        if (handler != null && handler.getId() != null) {
            type2handler.put(handler.getId(), handler);
        }
    }

    public void preloadTable(String schema, String name) {
        boolean notify = false;
        preloadListLock.lock();
        try {
            preloadList.add(new Triad(schema, "TABLE", name));
            if (preloadList.size() >= PRELOAD_THRESHOLD) {
                notify = true;
            }
        } finally {
            preloadListLock.unlock();
        }

        if (notify) {
            // notify the monitor loop
            synchronized (synch) {
                synch.notify();
            }
        }
    }

    public void preloadPackage(String schema, String name) {
        boolean notify = false;
        preloadListLock.lock();
        try {
            preloadList.add(new Triad(schema, "PACKAGE", name));
            if (preloadList.size() >= PRELOAD_THRESHOLD) {
                notify = true;
            }
        } finally {
            preloadListLock.unlock();
        }
        if (notify) {
            // notify the monitor loop
            synchronized (synch) {
                synch.notify();
            }
        }
    }

    public synchronized void setState(int state) {
        for (CacheManagerListener l : lsnrs) {
            l.handleUpdate(state);
        }

        if (state == CacheManagerListener.CACHE_UPDATED) {
            firstUpdateDone = true;
        } else if (state == CacheManagerListener.STARTED || state == CacheManagerListener.STOPPED) {
            firstUpdateDone = false;
        }
//        if(lsnr != null){
//            if (state == UP_TO_DATE) {
//                lsnr.stateUpdated(new StateEvent(StateListener.CACHE_UPDATED, conn.getDbUrl()));
//            } else if (state == OUT_OF_DATE) {
//                lsnr.stateUpdated(new StateEvent(StateListener.DISCONNECTED, conn.getDbUrl()));
//            }
//        }
//
//        currentState = state;
    }

    private final int CACHE_INITED = 0;
    private final int BOOTSTRAP_DONE = 1;
    private final int CACHE_UPDATED = 2;

    private int startupStatus = -1;

//    public StartupStatus getStartUpStatus() {
//        switch (startupStatus) {
//            case CACHE_INITED:
//                return new StartupStatus("Initilizing cache ...");
//            case BOOTSTRAP_DONE:
//                return new StartupStatus("Load updates ...");
//            case CACHE_UPDATED:
//                return new StartupStatus("Cache is ready");
//            default:
//                return new StartupStatus("Starting up cache ...");
//        }
//    }


    public void run() {

        boolean init = true;

        setState(CacheManagerListener.STARTED);

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

                            __reportStartupProgress__(CHECKUP_CHANGES, "Checking the latest changes in USER schema ...");
                            // check up changes in database schema
                            String[] changedTypes = userSchemaChanged();
                            int nbrUpdatedItems = 0;
                            if (changedTypes.length > 0) {
                                String user = url.getUser().toUpperCase();
                                __reportStartupProgress__(REQUEST_LAST_CHANGES, "Getting list of objects ...");
                                List<ItemToUpdate> dbObjVsCacheObj = createUserObjList(
                                        user,
                                        userObjs,
                                        listUserObjectsSql,
                                        changedTypes
                                );

                                __reportStartupProgress__(UPDATE_CACHE, "Updating USER objects ...");
                                nbrUpdatedItems = makeUpdate(dbObjVsCacheObj, userObjs, changedTypes);
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

                                startupStatus = CACHE_UPDATED;
                                // notify about updating of the cache
                                setState(CacheManagerListener.CACHE_UPDATED);

                                __reportStartupProgress__(CACHE_READY, "Connection established successfully");
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

                    int preloaded = preloadList.size();
                    if (preloaded > 0) {
                        log.info("Start object preloading, size: " + preloaded);
                        while (preloadList.size() > 0) {
                            preloadListLock.lock();
                            Triad key = null;
                            try {
                                key = preloadList.remove(0);
                            } finally {
                                preloadListLock.unlock();
                            }

                            if (key.owner != null) {
                                Cache c2 = caches.get(key.owner);
                                if (c2 != null) {
                                    c2.get(key.name, key.type);
                                }
                            } else {
                                for (Cache c2 : caches.values()) {
                                    c2.get(key.name, key.type);
                                }
                            }
                        }
                        log.info("... preloading done.");
                    }

                    runDbCheckup = readyForCheckup(ms);
                }
            }

            // correct shutdown
        } catch (UrgeShutdownException e) {
            // urge shutdown
        }
        
        conn.disconnect();
        setState(CacheManagerListener.STOPPED);
        log.info("Cache got down");
    }


    boolean readyForCheckup(long finishedTime) {
        try {
            long ms1 = System.currentTimeMillis();
            long timeout = finishedTime + TIMEOUT - ms1;
            if (timeout <= 0) {
                timeout = 5;
            }
            synch.wait(timeout);

            if (System.currentTimeMillis() - ms1 >= (timeout - 5)) {
                // time to check the database
                return true;
            }

        } catch (InterruptedException e) {
            // todo
        }
        return false;
    }

    //    int makeUpdate(List<ItemToUpdate> dbObjVsCacheObj, EhcacheWrapper ecache, String[] types) throws DBException {
    int makeUpdate(List<ItemToUpdate> dbObjVsCacheObj, UpdatableCache ecache, String[] types) throws DBException {

        PrioritizedList l = new PrioritizedList(dbObjVsCacheObj, queueItem2Update);
        log.info("[" + userName + "] Number of objects being updated: " + l.size());

        Map<DbObjectKey, DbObjectEx> _dbObjList = new HashMap<DbObjectKey, DbObjectEx>();

        int counter = 0;
        while (l.size() > 0) {
            __assertShutdown__();
//                            if (queueItem2Update.size() > 0) {
//                                ItemToUpdate i = queueItem2Update.remove(0);
//                                l.force(i);
//                            }

            Mix ordered_items = l.pop2();
            BaseHandler h = type2handler.get(ordered_items.type);
            if (h == null) {
                // todo - there is no handler for the type??
            } else {

                // [START] Progress status reporting ----------------------------------------
                String names = StringUtils.convert2listStringsWoQuotes(ordered_items.names).toLowerCase();
                names = (names.length() > 50) ? (names.substring(0, 49) + " ...") : names;
                __reportStartupProgress__("Loading " + names);
                // [END]   Progress status reporting ----------------------------------------

                List<DbObjectEx> dbObjects = h.load(conn, ordered_items);
                for (DbObjectEx dboEx : dbObjects) {
                    _dbObjList.put(
                            new DbObjectKey(dboEx.dbo.getTypeName(), dboEx.dbo.getName().toUpperCase()),
                            dboEx);
                }
            }

            try {
                synch.wait(10);
            } catch (InterruptedException e) {
            }
        }

        List<ItemToUpdate> cacheObj = new ArrayList<ItemToUpdate>();
        for (String type : types) {
            cacheObj.addAll(ecache.getObjectList(userName, type));
        }

        log.info("[" + userName + "] Update cache ...");

        //
        __assertShutdown__();

        int counter0 = 0;
        int counter1 = 0;

        // Update the cache
        // 1. go thru list to get list of object being updated (added or updated)
        for (ItemToUpdate item : dbObjVsCacheObj) {
            if (!item.upToDate) {
                DbObjectEx dbo = _dbObjList.remove(
                        item2key(item)
                );

                if (dbo != null) {
                    // update cache with the object
                    dbo.dbo.setLastDDLTime(item.lastDDL);
                    ecache.update(item.type, item.name, dbo.dbo);
                    counter0++;

                    // save object's source in the spool directory
                    if (dbo.source != null) {
                        ecache.updateText(item.type, item.name, dbo.source);
                    } else {
                        int hh =0;
                    }
                } else {
                    // looks strange, object is not up to date but was not loaded, error on load?
                    // todo -
                }
            } else {
                // object up to date
            }
        }

        __assertShutdown__();

        // 2. go thru the cached objects to get list of objects being deleted
        for (ItemToUpdate item : cacheObj) {
            if (!objectExists(dbObjVsCacheObj, item)) {
                // object not found in the database, delete it from the cache
                ecache.remove(item.name, item.type);
                counter1++;

                // remove object text source from spool directroy
//                userObjs.removeText(item.type, item.name);
//                new File(dbObjectSpoolDir, Utils.encryptFileName(item.type, item.name)).delete();
            }
        }
        ecache.flush();
        log.info("[" + userName + "] ... done, updated/deleted " + counter0 + "/" + counter1 + " nbr objects in cache: ???");
        return counter0 + counter1;
    }


    List<ItemToUpdate> createUserObjList(final String user, final UpdatableCache _ddata, String _sql, String[] types) throws DBException {
//    List<ItemToUpdate> createUserObjList(final String user, final EhcacheWrapper _ddata, String _sql, String[] types) throws DBException {

        // get list of objects updated since the last session
        final List<ItemToUpdate> items2Update = new ArrayList<ItemToUpdate>();
        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String sql = _sql.replace("<OBJECT_TYPES>", StringUtils.convert2listStrings(types));

        final int[] counters = new int[14];
        ResultSetHelper rsHlp = new ResultSetHelper(conn.getConnection());

        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String type = rs.getString("OBJECT_TYPE");
                    String name = rs.getString("OBJECT_NAME");
                    Date lastDDLTime = null;
                    try {
                        lastDDLTime = new Date(fmt.parse(rs.getString("TIMESTAMP")).getTime());
                    } catch (ParseException e) {
                        lastDDLTime = new Date(0);
                    }

                    ItemToUpdate item = null;
                    if (DbObject.PACKAGE.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.PACKAGE, name);
                        if (d == null || lastDDLTime.after(d)) { //pack.getLastDDLTime())) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[0]++;
                        } else {
                            // package definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[1]++;
                        }

                    } else if (DbObject.PACKAGE_BODY.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.PACKAGE_BODY, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
//                            counters[2]++;
                        } else {
                            // table definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
//                            counters[3]++;
                        }
                    } else if (DbObject.TABLE.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.TABLE, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[2]++;
                        } else {
                            // table definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[3]++;
                        }
                    } else if (DbObject.SEQUENCE.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.SEQUENCE, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[4]++;
                        } else {
                            // sequence definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[5]++;
                        }
                    } else if (DbObject.TYPE.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.TYPE, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[6]++;
                        } else {
                            // sequence definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[7]++;
                        }
                    } else if (DbObject.VIEW.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.VIEW, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[8]++;
                        } else {
                            // sequence definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[9]++;
                        }
                    } else if (DbObject.FUNCTION.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.FUNCTION, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[10]++;
                        } else {
                            // sequence definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[11]++;
                        }
                    } else if (DbObject.PROCEDURE.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.PROCEDURE, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[12]++;
                        } else {
                            // sequence definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[13]++;
                        }
                    } else if (DbObject.TRIGGER.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.TRIGGER, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
//                            counters[12]++;
                        } else {
                            // sequence definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
//                            counters[13]++;
                        }
                    } else {
                        // todo - not supported at the moment
                    }

                    if (item != null) {
                        items2Update.add(item);
                    }
                }
            }
            );

            log.info("[" + user + "] Up to date (packages/tables/sequences/types/views/func/proc): "
                    + counters[1] + "/" + counters[3] + "/" + counters[5]
                    + "/" + counters[7] + "/" + counters[9]
                    + "/" + counters[11] + "/" + counters[13]
                    + " Out of date: " + counters[0] + "/" + counters[2] + "/" + counters[4]
                    + "/" + counters[6] + "/" + counters[8]
                    + "/" + counters[10] + "/" + counters[12]
            );
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        return items2Update;
    }

    public Map<String, Cache> getCaches() {
        return caches;
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

    public String getSysDbObjectSource(String type, String name) {
        if (startupStatus == CACHE_UPDATED) {
            return sysObjs.getTextSource(name, type);
        }

        return null;
    }

    public String getDbObjectSource(String url/* UserHostPortServiceName */, String type, String name) {
        if (startupStatus == CACHE_UPDATED) {
            return userObjs.getTextSource(name, type);
        }

        return null;
    }

    class ValueHelper {
        public Date date;
        public int nbr;

        public ValueHelper(Date d, int nbr) {
            this.date = d;
            this.nbr = nbr;
        }
    }

    private String[] userSchemaChanged() throws ClosedConnectionException, DBException {
        final String owner = url.getUser();
        String sql = listUpdatesSql.replace("<OWNERS>", StringUtils.convert2listStrings(new String[]{owner}));
        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

        // adopt SQL query for version 9
        if ("9".equals(databaseVersionShort)) {
            sql = sql.replace("<CHECK_RECYCLEBIN>", "");
        } else {
            // 10 and higher
            sql = sql.replace("<CHECK_RECYCLEBIN>", recyleBinSubquery);
        }

        final Set<String> changed = new HashSet<String>();
        final Map<String, ValueHelper> typeLastDDLTime = new HashMap<String, ValueHelper>();

        try {
            ResultSetHelper rsHlp = new ResultSetHelper(conn.getConnection());

            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String object_type = rs.getString("object_type");
                    int nbr = rs.getInt("NBR_ELEMENTS");
                    Date timestamp = null;
                    try {
                        timestamp = new Date(fmt.parse(rs.getString("TIMESTAMP")).getTime());
                        typeLastDDLTime.put(object_type, new ValueHelper(timestamp, nbr));
                    } catch (ParseException e) {
                    }
                }
            });

            // check add/updated objects
            for (Map.Entry<String, ValueHelper> e : typeLastDDLTime.entrySet()) {
                Date d = userObjs.lastDDLTimeForType(e.getKey());
                if (d == null || d.before(e.getValue().date)) {
                    // some object were updated
                    changed.add(e.getKey());
                }
            }

            // check deleted objects
            for (String type : userObjs.listTypes()) {
                int nbr = userObjs.getObjectList(userName, type).size();
                ValueHelper v = typeLastDDLTime.get(type);
                if (v == null || v.nbr != nbr) {
                    // one or more objectes in the database were deleted
                    changed.add(type);
                }
            }

            if (changed.size() > 0) {
                String[] out = changed.toArray(new String[changed.size()]);
                // changes in the schema objects detected
                log.info("Objects needed in updating: " + StringUtils.convert2listStrings(out));
                return out;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 17008 || e.getErrorCode() == 17002) {
                throw new ClosedConnectionException();
            }
            log.warn("Error on pulling of the schema changes: " + e.getMessage());
            return new String[0];
        } catch (Error e) {
            // changes were detected
            log.warn("Error on pulling of the schema changes, ignore: " + e.getMessage());
            return new String[0];
        }

        return new String[0];
    }


    public void enqueueUpdate(int objType, String schema, String name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private boolean objectExists(List<ItemToUpdate> dbObj_vs_cacheObj, ItemToUpdate item) {
        for (ItemToUpdate i : dbObj_vs_cacheObj) {
            if (i.type.equalsIgnoreCase(item.type) &&
                    i.name.equalsIgnoreCase(item.name)) {
                return true;
            }
        }
        return false;
    }

    DbObjectKey item2key(ItemToUpdate item) {
        return new DbObjectKey(item.type, item.name);
    }


    class Triad {
        public String owner;
        public String type;
        public String name;

        public Triad(String owner, String type, String name) {
            this.owner = owner;
            this.type = type;
            this.name = name;
        }
    }

    class UrgeShutdownException extends RuntimeException {
    }
}
