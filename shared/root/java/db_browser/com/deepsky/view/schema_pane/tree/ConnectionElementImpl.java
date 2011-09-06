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

import com.deepsky.database.*;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.gui.ConnectionSettings2;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.deepsky.view.schema_pane.tree.impl.ComboBoxModelImpl;
import com.deepsky.view.utils.ProgressIndicatorHelper;
import com.deepsky.view.utils.TestConnectionProgressIndicator;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.HashSet;
import java.util.Set;

public class ConnectionElementImpl extends ComboBoxModelImpl
        implements ConnectionElement, SessionListener {

    Project project;
    ConnectionManager manager;
    Set<ConnectionElementListener> internalListeners = new HashSet<ConnectionElementListener>();
    final String localFS;

    public ConnectionElementImpl(Project project) {
        this.project = project;
        manager = PluginKeys.CONNECTION_MANAGER.getData(project);
        localFS = project.getName();
    }


    public String getLocalFS(){
        return localFS;
    }

    public void init(){
        // Local FS schema exists always
        children.add(localFS);

        // scan saved connections
        for (ConnectionInfo cinfo : manager.getSessionList()) {
            children.add(cinfo.getUrl().getUserHostPortServiceName());
            cinfo.addListener(this);
        }

        SqlCodeAssistantSettings settings = PluginKeys.PLUGIN_SETTINGS.getData2(project);
        String selected = settings.getDbBrowserSelectedConnection();

        if(children.indexOf(selected) != -1 ){
            setSelectedItem(selected);
        } else {
            // make Local FS selected initially
            setSelectedItem(localFS);
        }
    }


    public void setSelectedItem(Object anItem) {
        super.setSelectedItem(anItem);
        SqlCodeAssistantSettings settings = PluginKeys.PLUGIN_SETTINGS.getData2(project);
        settings.setDbBrowserSelectedConnection(anItem.toString());
    }

    public boolean isConnected(String name) {
        return findCInfo(name) != null && findCInfo(name).isConnected();
    }

    public void createConnection() {
        String[] hosts = manager.getHostList();
        ConnectionSettings2 settings = new ConnectionSettings2(project, hosts);
        settings.show();

        if (settings.isOK()) {

            DbUrl url = settings.getDbUrl();

            boolean loginOnStartup = settings.getLoginOnStartup();
            boolean repair = settings.getRepairIfDropped();
            int period = settings.getRefreshPeriod();

            try {
                ConnectionInfo cinfo = manager.createSession(url, loginOnStartup, repair, period);
                children.add(cinfo.getUrl().getUserHostPortServiceName());
                cinfo.addListener(this);

                for (ListDataListener l : listeners) {
                    l.intervalAdded(
                            new ListDataEvent(
                                    this, ListDataEvent.INTERVAL_ADDED, children.size() - 1, children.size())
                    );
                }

                if (children.size() == 1) {
                    selectItem = children.get(0);
                }
            } catch (DbConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean removeConnection() {
        if (findCInfo(selectItem) != null) {
            ConnectionInfo ci = findCInfo(selectItem);
            int index = children.indexOf(selectItem);
            if(manager.removeSession(ci)){
                ci.removeListener(this);

                children.remove(index);
                for (ListDataListener l : listeners) {
                    l.intervalRemoved(
                            new ListDataEvent(
                                    this, ListDataEvent.INTERVAL_REMOVED, index, index + 1)
                    );
                }

                String selectItem = children.size() > 0 ? (index >= children.size() ? children.get(index - 1) : children.get(index)) : null;
                setSelectedItem(selectItem);
                return true;
            } else {
                // todo -- not able to delete session??
            }
        }
        return false;
    }

    @NotNull
    public DbUrl getSelected(){
        ConnectionInfo cinfo = findCInfo(selectItem);
        if(cinfo!= null){
            return cinfo.getUrl();
        } else {
            // Virtual FS Schema?
            return IndexManager.FS_URL;
        }
    }

    public void disconnect() {
        if (findCInfo(selectItem) != null) {
            findCInfo(selectItem).disconnect();
        }
    }

    public void connect() {
        if (findCInfo(selectItem) != null) {
            ConnectionInfo ci = findCInfo(selectItem);
            MyProgressIndicator indicator = ci.connectAsynchronously();
            String url = ci.getUrl().getUserHostPortServiceName();
            new ProgressIndicatorHelper(project, "Connecting to " + url).runBackgrounableWithProgressInd(indicator, false);
        }
    }

    public void editConnectionSettings() {
        ConnectionInfo ci = findCInfo(selectItem);
        if (ci != null) {
            DbUrl url = ci.getUrl();
            ConnectionSettings2 settings = new ConnectionSettings2(project,
                    url,
                    ci.refreshPeriod(), ci.loginOnStart(), ci.repaireFailedConnection());

            settings.show();

            if (settings.isOK()) {
                url = settings.getDbUrl();

                boolean loginOnStartup = settings.getLoginOnStartup();
                boolean repair = settings.getRepairIfDropped();
                int period = settings.getRefreshPeriod();

                ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
                manager.updateSession(ci, url, loginOnStartup, repair, period);
            }
        }
    }

    public void testConnection() {
        ConnectionInfo ci = findCInfo(selectItem);
        if (ci != null) {
            new TestConnectionProgressIndicator(project, ci.getUrl()).checkConnection();
        }
    }

    public void addListener(ConnectionElementListener l) {
        internalListeners.add(l);
    }

    public void removeListener(ConnectionElementListener l) {
        internalListeners.remove(l);
    }

    private ConnectionInfo findCInfo(String name) {
        for (ConnectionInfo i : manager.getSessionList()) {
            if (i.getUrl().getUserHostPortServiceName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return null;
    }

    public void updated(ConnectionInfo source, int state) {
        String _source = source.getUrl().getUserHostPortServiceName();
        switch (state) {
            case SessionListener.CONNECTED:
                for (ConnectionElementListener l : internalListeners) {
                    l.connectionStatusUpdated(_source, ConnectionElementListener.CONNECTED);
                }
                break;
            case SessionListener.DISCONNECTED:
                for (ConnectionElementListener l : internalListeners) {
                    l.connectionStatusUpdated(_source, ConnectionElementListener.DISCONNECTED);
                }
                break;
            case SessionListener.OBJECT_CACHE_UPDATED:
                // todo
                break;

        }
    }

}
