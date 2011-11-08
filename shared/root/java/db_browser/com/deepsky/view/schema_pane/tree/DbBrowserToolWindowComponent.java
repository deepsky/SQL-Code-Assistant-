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

package com.deepsky.view.schema_pane.tree;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.plsql.indexMan.IndexBulkChangeListener;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.deepsky.view.Icons;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.*;

public class DbBrowserToolWindowComponent implements ProjectComponent {

    static private final String COMPONENT_NAME = "DbBrowserToolWindowComponent";
    static public final String DB_BROWSER_PANE = "DatabaseBrowser";

    private Project project;
    private int splitDividerLocation;

    private Thread dispatcher = new Thread(new DbObjectTreeManager());
    private final Object synch = new Object();
    private boolean stop = false;
    private long timeout = 5 * 1000;
    private int requestToUpdate = 0;

    final Map<DbUrl, String[]> url2types = new HashMap<DbUrl, String[]>();

    TabbedPaneManager tabbedPaneManager;
    public DbBrowserToolWindowComponent(Project project){
        this.project = project;
    }

    public void projectOpened() {
        final SqlCodeAssistantSettings settings = SqlCodeAssistantSettings.getInstance(project);
        splitDividerLocation = settings.getDbBrowserSplitDividerLocation();

        if (splitDividerLocation == 0) {
            // initial call
            splitDividerLocation = 30;
        }

        tabbedPaneManager = new TabbedPaneManager(project);
        JPanel windowPanel = new DbSchemaPane1001(project, tabbedPaneManager);

        ToolWindow toolWindow = getToolWindow(windowPanel);
        toolWindow.setIcon(Icons.DB_BROWSER);

        MessageBus bus1 = project.getMessageBus();
        bus1.connect().subscribe(IndexBulkChangeListener.TOPIC, new IndexBulkChangeListener() {
            public void handleUpdate(final DbUrl source, final String[] types) {
                synchronized (url2types){
                    String[] _types = url2types.get(source);
                    if(_types == null){
                        url2types.put(source, types);
                    } else {
                        if(_types.length == 0){
                            // updating all of types already requested
                        } else if(types == null){
                            url2types.put(source, new String[0]);
                        } else {
                            Set<String> temp = new HashSet<String>(Arrays.asList(_types));
                            temp.addAll(Arrays.asList(types));
                            url2types.put(source, temp.toArray(new String[temp.size()]));
                        }
                    }
                    requestToUpdate++;
                }

                synchronized (synch) {
                    synch.notify();
                }
            }
        });

        stop = false;
        dispatcher.setDaemon(true);
        dispatcher.setPriority(2);
        dispatcher.start();
    }

    private ToolWindow getToolWindow(JPanel panel) {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        if (isToolWindowRegistered())
            return toolWindowManager.getToolWindow(DB_BROWSER_PANE);
        else
            return toolWindowManager.registerToolWindow(DB_BROWSER_PANE, panel, ToolWindowAnchor.RIGHT);
    }


    private boolean isToolWindowRegistered() {
        return ToolWindowManager.getInstance(project).getToolWindow(DB_BROWSER_PANE) != null;
    }

    public void setSplitDividerLocation(int splitDividerLocation) {
        if (this.splitDividerLocation != splitDividerLocation) {
            final SqlCodeAssistantSettings settings = SqlCodeAssistantSettings.getInstance(project);
            settings.setDbBrowserSplitDividerLocation(splitDividerLocation);
        }
        this.splitDividerLocation = splitDividerLocation;
    }

    public int getSplitDividerLocation() {
        return splitDividerLocation;
    }


    public void projectClosed() {
        if (isToolWindowRegistered())
            ToolWindowManager.getInstance(project).unregisterToolWindow(DB_BROWSER_PANE);

        stop = true;
        synchronized (synch) {
            synch.notify();
        }

        if (dispatcher != null) {
            try {
                dispatcher.join();
            } catch (InterruptedException e) {
            }
        }
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }

    @NotNull
    public String getComponentName() {
        return COMPONENT_NAME;
    }

    private class DbObjectTreeManager implements Runnable {

        public void run() {
            int requestToUpdateCopy = 0;

            synchronized (synch) {
                while (!stop) {
                    synchronized (url2types){
                        if(requestToUpdate == requestToUpdateCopy){
                            // time to refresh schema browser if there are pending requests
                            // todo -- to minimize number of requests on update checking of the panel visibility requried
                            if(url2types.size() > 0){
                                Iterator<Map.Entry<DbUrl, String[]>> iter = url2types.entrySet().iterator();
                                while(iter.hasNext()){
                                    Map.Entry<DbUrl, String[]> e = iter.next();
                                    tabbedPaneManager.refreshSchemaTree(e.getKey(), e.getValue());
                                    iter.remove();
                                }
                            }
                        } else {
                            requestToUpdateCopy = requestToUpdate;
                        }
                    }

                    try {
                        synch.wait(timeout);
                    } catch (InterruptedException e) {
                        // todo
                    }
                }

            }
        }
    }
}
