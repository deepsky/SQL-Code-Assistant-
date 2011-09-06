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

import com.deepsky.database.*;
import com.deepsky.database.impl.ConnectionManagerImpl;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.DbUrlSID;
import com.deepsky.database.ora2.DbSchemaIndexer;
import com.deepsky.lang.integration.LocalFSChangeTracker;
import com.deepsky.lang.integration.PlSqlFileChangeTracker;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.indexMan.FSIndexer;
import com.deepsky.lang.plsql.indexMan.IndexManagerImpl;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.navigation.NameLookupService;
import com.deepsky.navigation.impl.NameLookupServiceImpl;
import com.deepsky.navigation.DbObjectContributor;
import com.deepsky.navigation.impl.DbObjectContribNewImpl;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.deepsky.view.query_pane.QueryResultWindow;
//import com.deepsky.view.schema_pane.DBBrowserWindow;
import com.deepsky.view.query_pane.converters.TIMESTAMPLTZ_Convertor;
import com.deepsky.view.query_pane.converters.TIMESTAMPTZ_Convertor;
import com.deepsky.view.query_pane.converters.TIMESTAMP_Convertor;
import com.deepsky.view.utils.ProgressIndicatorHelper;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;


public class PlSqlProjectComponent implements ProjectComponent {

    private final Logger log = Logger.getInstance("#PlSqlProjectComponent");

    static final String componentName = "PlSqlProjectComponent";

    Project project;

    ConnectionManagerListener connectionListener;
    CacheManagerListener cacheListener;

    ConnectionManagerImpl connectionManager;

    QueryResultWindow qrWindow;
//    DBBrowserWindow dbBrowser;

    DbSchemaIndexer dbIndexer;
    FSIndexer fsIndexer;
    IndexManager indexManager;

    DbUrl dbUrl;
    NameLookupService nameLookupService;

    public PlSqlProjectComponent(Project project) {
        this.project = project;
    }

    public void projectOpened() {
        dbUrl = new DbUrlSID(IndexManager.FS_URL.getFullUrl()){
            public String getAlias() {
                return project.getName();
            }
        };
        PluginKeys.LOCAL_FS_URL.putData(dbUrl, project);

        final SqlCodeAssistantSettings settings = SqlCodeAssistantSettings.getInstance(project);
        PluginKeys.PLUGIN_SETTINGS.putData(settings, project);

        PluginKeys.TS_CONVERTOR.putData(new TIMESTAMP_Convertor(settings), project);
        PluginKeys.TSTZ_CONVERTOR.putData(new TIMESTAMPTZ_Convertor(settings), project);
        PluginKeys.TSLTZ_CONVERTOR.putData(new TIMESTAMPLTZ_Convertor(settings), project);

        connectionListener = new StateListenerImpl();
        cacheListener = new CacheManagerListenerImpl();

//        cacheManager = new CacheManager3();
//        PluginKeys.CACHE_MANAGER.putData(cacheManager, project);
//        cacheManager.addListener(cacheListener);
//
//        objectCache = new OraObjectCache3(null); //cacheManager);
//        PluginKeys.OBJECT_CACHE.putData(objectCache, project);

        indexManager = new IndexManagerImpl(project);
        PluginKeys.SQL_INDEX_MAN.putData(indexManager, project);
        indexManager.start();


        // create USER schema database index
        dbIndexer = new DbSchemaIndexer(project, indexManager);
        PluginKeys.DB_NAMES_INDEXER.putData(dbIndexer, project);
        dbIndexer.addListener(cacheListener);

        // create SYS schema database index
        // todo -- implement

        connectionManager = new ConnectionManagerImpl(project, dbIndexer);
        PluginKeys.CONNECTION_MANAGER.putData(connectionManager, project);
        connectionManager.addStateListener(connectionListener);
        connectionManager.addStateListener(indexManager);

        // activate connection if it is elegable for logging on start
        final String session = settings.getConnection();
        if (session != null) {
            // run some later to avoid collisions with setting of Project ref in the context
            ApplicationManager.getApplication().invokeLater(new Runnable() {
                public void run() {
                    try {
                        DbUrl dbUrl = DbUrl.parse(session);
                        MyProgressIndicator indicator = connectionManager.activateSessionOnStart(dbUrl);
                        if (indicator != null) {
                            String url = indicator.targetDatabase();
                            new ProgressIndicatorHelper(project, "Connecting to " + url).runBackgrounableWithProgressInd(indicator, true);
                        }

                    } catch (ConfigurationException e) {
                        // incorrect URL ?
                        settings.setConnection(null);
                    }
                }
            });
        }

        long ms = System.currentTimeMillis();
        // preload indexes on start
        int nbr = 0;

        boolean flag = settings.isAccessOfflineEnabled();
        ConnectionInfo[] infos = connectionManager.getSessionList();
        DbUrl[] urls = new DbUrl[infos.length];
        for(int i =0; i<infos.length; i++){
            urls[i] = infos[i].getUrl();
            nbr++;
        }
        indexManager.enableOfflineCache(urls, flag);

/*
        for(ConnectionInfo info: connectionManager.getSessionList()){
            DbUrl url = info.getUrl();
            //indexManager.findOrCreateIndex(url);
            nbr++;
        }
*/
        ms = System.currentTimeMillis() - ms;
        log.info("Nbr preloaded indexes: " + nbr + ", time spent: " + ms);

        // Create the Request Result Pane
        qrWindow = new QueryResultWindow(project, connectionManager);
        PluginKeys.QR_WINDOW.putData(qrWindow, project);

        // Create DB Browser window
//        dbBrowser = new DBBrowserWindow(project);

        DbObjectContributor dbObjContrib = new DbObjectContribNewImpl(); //utorImpl();
        PluginKeys.DB_OBJECT_CONTR.putData(dbObjContrib, project);

        // create SQL file indexer
        fsIndexer = new FSIndexer(project, indexManager.getFSIndex());
        fsIndexer.start();
//        PluginKeys.FS_SQL_NAMES_INDEXER.putData(fsIndexManager, project);

/*
        MessageBus bus1 = project.getMessageBus();
        TestEventListener ll = new TestEventListener(){

            public void checkMe() {
                int hh =0;
            }
        };
        bus1.connect().subscribe(TestEventListener.TOPIC, ll);
*/

/*
        PsiTreeChangeAggregator aggr = new PsiTreeChangeAggregator();
//        PsiManager.getInstance(project).addPsiTreeChangeListener(aggr, project);

        MessageBus bus1 = project.getMessageBus();
        bus1.connect().subscribe(PsiDocumentTransactionListener.TOPIC, aggr);
*/

/*
        PluginKeys.EVNT_CHANNEL.putData(aggr, project);
*/

        LocalFSChangeTracker localFSAggr = new LocalFSChangeTracker(project, fsIndexer);
        localFSAggr.start();

        PlSqlFileChangeTracker tracker = new PlSqlFileChangeTracker();
        PluginKeys.PLSQLFILE_CHANGE_TRACKER.putData(tracker, project);

        nameLookupService = new NameLookupServiceImpl(project);
        PluginKeys.NAME_LOOKUP.putData(nameLookupService, project);

//        Class clazz = StoresFactory.getProjectStoreClass(false);

        int hh = 0;
        // Setup annotator
//        updateAnnotator(current);
        // --------------

//        VirtualFileManager vfman = VirtualFileManager.getInstance();


/*
        MessageBus bus = project.getMessageBus();
        bus.connect().subscribe(VirtualFileManager.VFS_CHANGES, new BulkFileListener() {
            public void before(List<? extends VFileEvent> events) {
                for (VFileEvent event : events) {
                    String path = event.getPath().toLowerCase();
                    for(FileNameMatcher m : FileTypeManager.getInstance().getAssociations(PlSqlSupportLoader.PLSQL)){
                        if( m.accept(path)){
                            log.info("###### " + event );
                        }
                    }
//                    if (path.endsWith(".sql") || path.endsWith(".pkb") || path.endsWith(".pks")) {
//                        log.info("###### " + event.getPath());
//                    }
                }
            }

            public void after(List<? extends VFileEvent> events) {
                for (VFileEvent event : events) {
                    String path = event.getPath().toLowerCase();
                    for(FileNameMatcher m : FileTypeManager.getInstance().getAssociations(PlSqlSupportLoader.PLSQL)){
                        if( m.accept(path)){
                            log.info("_____ " + event );
                        }
                    }
//                    if (path.endsWith(".sql") || path.endsWith(".pkb") || path.endsWith(".pks")) {
//                        log.info("_____ " + event.getPath());
//                    }
                }
            }
        });
*/

/*
        bus.connect().subscribe(ProjectTopics.MODULES, new ModuleListener() {
            public void moduleAdded(Project project, Module module) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void beforeModuleRemoved(Project project, Module module) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void moduleRemoved(Project project, Module module) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void modulesRenamed(Project project, List<Module> modules) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
*/

    }


    public void projectClosed() {
//        log.info("#projectClosed :" + project);
        connectionManager.removeStateListener(connectionListener);

        connectionManager.close();
//        objectCache.close();

        qrWindow.close();
//        dbBrowser.close();

        fsIndexer.stop();
        indexManager.stop();
    }

    @NotNull
    public String getComponentName() {
        return componentName;
    }

    public void initComponent() {
        int h = 0;
    }

    public void disposeComponent() {
        int h = 0;
    }

    private class CacheManagerListenerImpl implements CacheManagerListener {
        public void handleUpdate(int state) {
            if (state == CacheManagerListener.CACHE_UPDATED) {
                runCodeAnalyzer();
            }
        }
    }

    private class StateListenerImpl implements ConnectionManagerListener {
        public void handleUpdate(StateEvent state) {
            if (state.getStatus() == ConnectionManagerListener.DISCONNECTED) {

//                PluginSettingsBean current = getPluginSettings();
//                current.setLastConnection(null);
//                savePluginSettings(current);

                PluginKeys.PLUGIN_SETTINGS.getData(project).setConnection(null);
                runCodeAnalyzer();
            } else if (state.getStatus() == ConnectionManagerListener.CONNECTED) {
//                PluginSettingsBean current = getPluginSettings();
//                current.setLastConnection(connectionManager.getDbUrl().getFullUrl());
//                savePluginSettings(current);
                PluginKeys.PLUGIN_SETTINGS.getData(project).setConnection(
                        connectionManager.getDbUrl().getFullUrl()
                );
            }
        }
    }


    private void runCodeAnalyzer() {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                if (!project.isDisposed()) {
                    DaemonCodeAnalyzer.getInstance(project).restart();
                    log.info("#RESTART code analyzer");
                }
            }
        });
    }

/*
    @Nls
    public String getDisplayName() {
        return "SQL Code Assistant";
    }

    @Nullable
    public Icon getIcon() {
        return null;
    }

    @Nullable
    public String getHelpTopic() {
        return null;
    }
*/

/*
    public PluginSettingsBean getState() {
        PluginSettingsBean current = getPluginSettings();
        PluginSettingsBean bean = new PluginSettingsBean();
        XmlSerializerUtil.copyBean(current, bean);

        return bean;
    }


    public void loadState(PluginSettingsBean state) {
        PluginSettingsBean bean = new PluginSettingsBean();
        XmlSerializerUtil.copyBean(state, bean);
        savePluginSettings(bean);
    }


    private void savePluginSettings(PluginSettingsBean bean) {
//        settings = bean;
        PluginKeys.PLUGIN_SETTINGS.putData(bean, project);
    }

    @NotNull
    private PluginSettingsBean getPluginSettings() {

        PluginSettingsBean settings = PluginKeys.PLUGIN_SETTINGS.getData2(project);
        if (settings == null) {
            settings = new PluginSettingsBean();
            PluginKeys.PLUGIN_SETTINGS.putData(settings, project);
        }
        return settings;
    }
*/


}

