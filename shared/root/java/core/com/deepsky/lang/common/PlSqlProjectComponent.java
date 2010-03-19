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
import com.deepsky.database.ora.CacheManager3;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.OraObjectCache3;
import com.deepsky.lang.conf.PluginSettingsBean;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.view.query_pane.QueryResultWindow;
import com.deepsky.view.schema_pane.DBBrowserWindow;
import com.deepsky.view.utils.ProgressIndicatorHelper;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


@State(
        name = PlSqlProjectComponent.componentName,
        storages = {@Storage(id = "myId", file = "$PROJECT_FILE$")}
)
public class PlSqlProjectComponent implements ProjectComponent, PersistentStateComponent<PluginSettingsBean> {

    private final Logger log = Logger.getInstance("#PlSqlProjectComponent");

    static final String componentName = "PlSqlProjectComponent";

    Project project;

    ConnectionManagerListener connectionListener;
    CacheManagerListener cacheListener;

    CacheManager cacheManager;
    ConnectionManagerImpl connectionManager;
    ObjectCache objectCache;

    QueryResultWindow qrWindow;
    DBBrowserWindow dbBrowser;

    public PlSqlProjectComponent(Project project) {
        this.project = project;
    }

    public void projectOpened() {
        final PluginSettingsBean current = getPluginSettings();

        connectionListener = new StateListenerImpl();
        cacheListener = new CacheManagerListenerImpl();

        cacheManager = new CacheManager3();
        PluginKeys.CACHE_MANAGER.putData(cacheManager, project);
        cacheManager.addListener(cacheListener);

        objectCache = new OraObjectCache3(cacheManager);
        PluginKeys.OBJECT_CACHE.putData(objectCache, project);

        connectionManager = new ConnectionManagerImpl(project, cacheManager);
        PluginKeys.CONNECTION_MANAGER.putData(connectionManager, project);
        connectionManager.addStateListener(connectionListener);

        // activate connection if it is elgable for logging on start
        final String session = current.getLastConnection();
        if (session != null) {
            // run some later to avoid collisions with setting of Project ref in the context
            ApplicationManager.getApplication().invokeLater(new Runnable() {
                public void run() {
                    try {
                        DbUrl dbUrl = new DbUrl(session);
                        connectionManager.activateSessionOnStart(dbUrl);
                    } catch (ConfigurationException e) {
                        // incorrect URL ?
                        current.setLastConnection(null);
                    }
                }
            });
        }

        MyProgressIndicator indicator = connectionManager.getStartupConnectionIndicator();
        if (indicator != null) {
            String url = indicator.targetDatabase();
            new ProgressIndicatorHelper(project, "Connecting to " + url).runBackgrounableWithProgressInd(indicator, true);
        }

        qrWindow = new QueryResultWindow(project, connectionManager);
        PluginKeys.QR_WINDOW.putData(qrWindow, project);

        dbBrowser = new DBBrowserWindow(project);

        // Setup annotator
//        updateAnnotator(current);
        // --------------


//        ProjectRootManager man = ProjectRootManager.getInstance(project);
//        ProjectFileIndex idx = man.getFileIndex();
//        idx.iterateContent(new ContentIterator(){
//            public boolean processFile(VirtualFile fileOrDir) {
//                log.info("$$$$$ " + fileOrDir.getPath());
//                return false;
//            }
//        });

/*
        Module[] modules = ModuleManager.getInstance(project).getModules();
        for (Module module : modules) {
          VirtualFile[] contentRoots = ModuleRootManager.getInstance(module).getContentRoots();
          for (VirtualFile contentRoot : contentRoots) {

            // todo
          }
        }
*/

/*
        VirtualFileManager man = VirtualFileManager.getInstance();
        man.addVirtualFileListener(new VirtualFileListener(){
            public void propertyChanged(VirtualFilePropertyEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void contentsChanged(VirtualFileEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void fileCreated(VirtualFileEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void fileDeleted(VirtualFileEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void fileMoved(VirtualFileMoveEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void fileCopied(VirtualFileCopyEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void beforePropertyChange(VirtualFilePropertyEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void beforeContentsChange(VirtualFileEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void beforeFileDeletion(VirtualFileEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void beforeFileMovement(VirtualFileMoveEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
*/
/*
        PsiManager psiManager = PsiManager.getInstance(project);
        final PsiModificationTracker modificationTracker =  psiManager.getModificationTracker();

          final MessageBus bus = project.getMessageBus();
          bus.connect().subscribe(ProjectTopics.MODIFICATION_TRACKER, new PsiModificationTracker.Listener() {
              public void modificationCountChanged() {
                  modificationTracker.
                  //To change body of implemented methods use File | Settings | File Templates.
              }
          });
*/
    }


    public void projectClosed() {
//        log.info("#projectClosed :" + project);
        connectionManager.removeStateListener(connectionListener);
        cacheManager.removeListener(cacheListener);

        connectionManager.close();
        objectCache.close();

        qrWindow.close();
        dbBrowser.close();
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

    class CacheManagerListenerImpl implements CacheManagerListener {
        public void handleUpdate(int state) {
            if (state == CacheManagerListener.CACHE_UPDATED) {
                runCodeAnalyzer();
            }
        }
    }

    class StateListenerImpl implements ConnectionManagerListener {
        public void handleUpdate(StateEvent state) {
            if (state.getStatus() == ConnectionManagerListener.DISCONNECTED) {

                PluginSettingsBean current = getPluginSettings();
                current.setLastConnection(null);
                savePluginSettings(current);

                runCodeAnalyzer();
            } else if (state.getStatus() == ConnectionManagerListener.CONNECTED) {

                PluginSettingsBean current = getPluginSettings();
                current.setLastConnection(connectionManager.getDbUrl().getFullUrl());
                savePluginSettings(current);
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

        PluginSettingsBean settings = PluginKeys.PLUGIN_SETTINGS.getData(project);
        if (settings == null) {
            settings = new PluginSettingsBean();
            PluginKeys.PLUGIN_SETTINGS.putData(settings, project);
        }
        return settings;
    }

}

