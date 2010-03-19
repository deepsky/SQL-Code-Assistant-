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

package com.deepsky.lang.common;

import com.deepsky.cache.EhcacheWrapper2;
import com.deepsky.cache.GenericCache;
import com.deepsky.database.CacheLocator;
import com.deepsky.database.cache.*;
import com.deepsky.database.cache.impl.CacheImpl;
import com.deepsky.database.cache.impl.UpdatableCacheImpl;
import com.deepsky.database.cache.impl.UpdatableSourceTextProviderImpl;
import com.deepsky.database.ora.BaseHandler;
import com.deepsky.database.ora.DbObjectKey;
import com.deepsky.database.ora.VersionMarker;
import com.deepsky.database.ora.handlers.*;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.struct.DbObject;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class PlSqlSupportLoader implements ApplicationComponent {

    private static final Logger log = Logger.getInstance("#PlSqlSupportLoader");

    public static final LanguageFileType PLSQL = new PlSqlFileType();

    public static final String PLSQL_APPLICATION = "PL/SQL support loader";

    Map<String, BaseHandler> type2handler = new HashMap<String, BaseHandler>();
    String storeDir;

    EhcacheWrapper2 userObjs;
    EhcacheWrapper2 sysObjs;

    final String[] dbSupportedVersions = new String[]{"9", "10", "11"};


    public void initComponent() {
        log.info("#initComponent");

        File cacheDir = CacheLocator.getCacheDirectory();
        if (!cacheDir.exists()) {
            throw new ConfigurationException("Store directory not accessible: " + cacheDir.getAbsolutePath());
        }

        storeDir = cacheDir.getAbsolutePath();

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

        bootstrapCaches();

        //FileTypeManager fman = FileTypeManager.getInstance();
        FileTypeManager.getInstance().registerFileType(PLSQL, "sql", "pks", "pkb");


// todo -- how to access outside?
//      PlSqlSupportLoader c = (PlSqlSupportLoader) ApplicationManager.getApplication().getComponent(PlSqlSupportLoader.PLSQL_APPLICATION);
    }

    public void disposeComponent() {
        log.info("#disposeComponent");
        if (userObjs != null) {
            userObjs.shutdown();
            userObjs = null;
        }

        if (sysObjs != null) {
            sysObjs.shutdown();
            sysObjs = null;
        }

//        caches.clear();
    }

    @NotNull
    public String getComponentName() {
        log.info("#getComponentName");
        return PLSQL_APPLICATION;
    }


    private void addHandler(BaseHandler handler) {
        if (handler != null && handler.getId() != null) {
            type2handler.put(handler.getId(), handler);
        }
    }

    public Cache getSysObjectCache(final String version) {
        return new CacheImpl(
                sysObjs.getCache(version),
                new SourceTextProvider() {
                    public String getText(String name, String type) {
                        String scriptName = version + "/" + Utils.encryptFileName(type, name);
                        InputStream in = getClass().getClassLoader().getResourceAsStream(scriptName);
                        if (in != null) {
                            ByteArrayOutputStream out = null;
                            try {
                                out = new ByteArrayOutputStream();
                                byte[] temp = new byte[1000];
                                int size;
                                while ((size = in.read(temp)) > 0) {
                                    out.write(temp, 0, size);
                                }

                                return out.toString();
                            } catch (IOException e) {
                                // ignore
                            } finally {
                                try {
                                    in.close();
                                } catch (IOException e) {
                                }
                            }
                        }
                        return null;
                    }
                });
    }

    public UpdatableCache getUserObjectCache(String name) {
        GenericCache gcache = userObjs.getCache(name);
        Object o = gcache.get(VersionMarker.TAG);
        if (o == null) {
            // new cache?
            VersionMarker marker = new VersionMarker();
            gcache.update(VersionMarker.TAG, marker);
            userObjs.flush();
        } else if (!(o instanceof VersionMarker) || !o.equals(new VersionMarker())) {
            // version marker corrupted or obsoleted
            log.info("Version marker not found or differ, clean up cache: " + name);
            gcache.removeAll();

            VersionMarker marker = new VersionMarker();
            gcache.update(VersionMarker.TAG, marker);
            userObjs.flush();
        }

        String id = userObjs.getCacheIdFor(name);
        UpdatableSourceTextProvider textProvider =
                new UpdatableSourceTextProviderImpl(new File(new File(storeDir), id).getAbsolutePath());
        return new UpdatableCacheImpl(gcache, textProvider);
    }


    private void bootstrapCaches() {
        // user schema
        log.info("[bootstrap cache] start");
//        __assertShutdown__();
//        __reportStartupProgress__(INIT_USER_CACHE, "Initializing USER object cache ...");

        try {
            userObjs = startupUserCache(storeDir, "USER");
        } catch (Throwable e) {
            if (new File(storeDir).exists() && new File(storeDir).isDirectory()) {
                removeCacheStuffFiles(new File(storeDir), "USER");
            }
            // try initialize one more
            userObjs = startupUserCache(storeDir, "USER");
        }

        // sys schema
//        __assertShutdown__();
//        __reportStartupProgress__(INIT_SYS_CACHE, "Initializing SYS object cache ...");

        try {
            sysObjs = startupSysCache(storeDir, "SYS");
        } catch (Throwable e) {
            if (new File(storeDir).exists() && new File(storeDir).isDirectory()) {
                removeCacheStuffFiles(new File(storeDir), "SYS");
            }
            // try initialize one more
            sysObjs = startupSysCache(storeDir, "SYS");
        }

        log.info("[bootstrap cache] end");

//        __assertShutdown__();
    }


    private EhcacheWrapper2 startupSysCache(String storeDir, String name) {
        EhcacheWrapper2 sysObjs = null;
        try {
            sysObjs = new EhcacheWrapper2(); //"SYS");
            sysObjs.setFileName(name);
            sysObjs.setStoreDir(storeDir);
            sysObjs.init();

            for (String version : dbSupportedVersions) {
                GenericCache c = sysObjs.getCache(version);
                Object o = c.get(VersionMarker.TAG);
                if (!(o instanceof VersionMarker) || !o.equals(new VersionMarker())) {
                    // reinitialize cache
                    c.removeAll();
                    log.info("Start load objects for " + version + " ...");
                    boolean result = bootstrapSysCache(version, c);
                    if (result) {
                        log.info("... done.");
                    } else {
                        log.info("... failed.");
                    }
                }
            }

            sysObjs.flush();
            return sysObjs;
        } catch (Throwable e) {
            if(sysObjs != null){
                sysObjs.shutdown();
            }
            throw new Error(e);
        }
    }

    private EhcacheWrapper2 startupUserCache(String storeDir, String name) {
        EhcacheWrapper2 userObjs = null;
        try {
            userObjs = new EhcacheWrapper2(); //"USER");
            userObjs.setFileName(name);
            userObjs.setStoreDir(storeDir);
            userObjs.init();

            for (String cacheName : userObjs.getCacheNames()) {
                GenericCache c = userObjs.getCache(cacheName);
                Object o = c.get(VersionMarker.TAG);
                if (!(o instanceof VersionMarker) || !o.equals(new VersionMarker())) {
                    // version marke is absent of obsoleted,  reinitialize the cache
                    log.info("Version marker not found or differ, clean up the USER cache");
                    c.removeAll();

                    VersionMarker marker = new VersionMarker();
                    c.update(VersionMarker.TAG, marker);
                }
            }
            userObjs.flush();
            return userObjs;
        } catch (Throwable e) {
            if(userObjs != null){
                userObjs.shutdown();
            }
            throw new Error(e);
        }
    }

    private void removeCacheStuffFiles(File storeDir, String cacheName) {
        File[] files = storeDir.listFiles();
        for (File f : files) {
            if (f.getName().toLowerCase().startsWith(cacheName.toLowerCase() + ".")) {
                boolean res = f.delete();
            }
        }
    }

    private boolean bootstrapSysCache(String version, GenericCache gcache) {

        // initial cache populating
        for (String type : type2handler.keySet()) {
            loadSerializedType(version, type, gcache);
        }

        // deserialize marker
        InputStream in = this.getClass()
                .getClassLoader()
                .getResourceAsStream(version + "#serialized#marker");

        if (in == null) {
            log.warn("Version marker object not found");
        } else {
            ObjectInputStream ostream = null;
            try {
                ostream = new ObjectInputStream(in);
                Object o = ostream.readObject();
                if (o instanceof VersionMarker) {
                    VersionMarker marker = (VersionMarker) o;
                    gcache.update(VersionMarker.TAG, marker);
                    if (!marker.equals(new VersionMarker())) {
                        log.warn("Version of the serialized data does not match to the last VersionMarker ID, database " + version +
                                " (serialized version: " + marker.getName() + " -> " + new VersionMarker().getName() + ")");
                    }

                    gcache.flush();
                } else {
                    log.warn("Could not deserialize SYS version marker");
                }
                return true;
            } catch (IOException e) {
                log.warn("[IOException] Could not load SYS schema objects for version: " + version + ", " + e.getMessage());
            } catch (ClassNotFoundException e) {
                log.warn("[ClassNotFoundException] Could not load SYS schema objects for version: " + version + ", " + e.getMessage());
            }
        }

        return true;
    }

    private boolean loadSerializedType(String version, String type, GenericCache gcache) {
        InputStream in = this.getClass()
                .getClassLoader()
                .getResourceAsStream(version + "#serialized#" + type.toLowerCase());

        if (in == null) {
            return false;
        } else {
            long ms, ms2, ms3;
            ObjectInputStream ostream = null;
            try {
                ms = System.currentTimeMillis();

                ostream = new ObjectInputStream(in);
                Object o = ostream.readObject();
                ms2 = System.currentTimeMillis();

//                __assertShutdown__();

                if (o instanceof Map) {
                    Map<DbObjectKey, DbObject> res = (Map<DbObjectKey, DbObject>) o;
                    for (Map.Entry<DbObjectKey, DbObject> e : res.entrySet()) {
                        try {
//                            __assertShutdown__();
//                            __reportStartupProgress__("Loading " + type + " objects ...");
                            gcache.update(
                                    e.getKey().getType() + ":" + e.getKey().getName(),
                                    e.getValue()
                            );
                        } catch (Throwable e1) {
                            int hh = 0;
                        }
                    }

//                    sysObjs.flush();
//                    __assertShutdown__();

                    ms3 = System.currentTimeMillis();
                    log.info(
                            "Object type: " +
                                    type + ", number of loaded objects: " + res.size() +
                                    ", time spent (ms): " + (ms2 - ms) + "/" + (ms3 - ms2));
                } else {
                    log.warn("Could not deserialize SYS objects");
                }
                return true;
            } catch (IOException e) {
                log.warn("[IOException] Could not load SYS schema objects for version: " + version + ", " + e.getMessage());
            } catch (ClassNotFoundException e) {
                log.warn("[ClassNotFoundException] Could not load SYS schema objects for version: " + version + ", " + e.getMessage());
            }
        }
        return false;
    }

}

