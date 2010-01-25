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

package com.deepsky.view.schema_pane.impl;

import com.deepsky.database.cache.Cache;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.view.schema_pane.DboDescriptorViewFactory;
import com.deepsky.view.schema_pane.ItemViewListener;
import com.deepsky.view.schema_pane.ItemViewWrapper;
import com.deepsky.view.utils.*;
import com.deepsky.view.Icons;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.*;
import com.deepsky.gui.ConnectionSettings2;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.actionSystem.AnActionEvent;


import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;


public class DbSchemaItemView extends ItemViewWrapperBase implements ItemViewWrapper, SessionListener {

    final int CONNECT = 1001;
    final int DISCONNECT = 1002;
    final int EDIT = 1003;
    final int TEST_CONNECTION = 1004;
    final int REMOVE_SESSION = 1005;

    ObjectCache cache;
    ConnectionInfo cinfo;
    List<Integer> pendingRequests = new ArrayList<Integer>();

    boolean uiLocked = false;

    public DbSchemaItemView(ItemViewWrapper parent, ConnectionInfo cinfo) {
        this.parent = parent;
        this.cinfo = cinfo;
        this.cinfo.addListener(this);

        cache = ObjectCacheFactory.getObjectCache(); //cinfo.getObjectCache();
        updateDescendentNodes();
    }

    void updateDescendentNodes() {
        children.clear();
        if (cache != null) {
            children.add(new DbSchemaTypeNode(this, DbObject.TABLE));
            children.add(new DbSchemaTypeNode(this, DbObject.VIEW));
            children.add(new DbSchemaTypeNode(this, DbObject.PACKAGE));
            children.add(new DbSchemaTypeNode(this, DbObject.FUNCTION));
            children.add(new DbSchemaTypeNode(this, DbObject.PROCEDURE));
            children.add(new DbSchemaTypeNode(this, DbObject.TRIGGER));
            children.add(new DbSchemaTypeNode(this, DbObject.TYPE));
            children.add(new DbSchemaTypeNode(this, DbObject.SEQUENCE));
        }
    }

    /**
     * Connection Status
     * Database Version
     * Connect on Start
     * User Name
     * Host
     * SID
     *
     * @return
     */
    public Object[][] getTabularData() {

        Object[][] out = new Object[6][];

        out[0] = new String[]{"Connection status", ((cinfo.isConnected()) ? "Logged in" : "Not logged")};

        if (ConnectionManagerImpl.getInstance().getDbMetaInfo() != null) {
            DbMetaInfo meta = ConnectionManagerImpl.getInstance().getDbMetaInfo();
            out[1] = new String[]{"Database version", meta.getDbVersion()};
        } else {
            // offline
            out[1] = new String[]{"Database version", "Not available"};
        }
        out[2] = new String[]{"Connect on Start", Boolean.toString(cinfo.loginOnStart())};
        out[3] = new String[]{"Auto refresh", Integer.toString(cinfo.refreshPeriod())};
        out[4] = new String[]{"Reconnect, if dropped", Boolean.toString(cinfo.repaireFailedConnection())};
        out[5] = new String[]{"Last login time", (cinfo.lastLoginTime() == null)? "": cinfo.lastLoginTime().toString()};

        return out;
    }

    public Object[] getColumnNames() {
        return new String[]{"Parameter", "Value"};
    }

    public void render(DefaultTreeCellRenderer renderer) {

        String text;
        if(cinfo.isConnected()){
            text = Formatter.formatHtmlBasedDbSchema(
                    cinfo.getUrl().getUserHostPortServiceName());
            renderer.setIcon(Icons.DB_SCHEMA);
        } else {
            text = cinfo.getUrl().getUserHostPortServiceName();
            renderer.setIcon(Icons.DB_SCHEMA_DISABLED);
        }
        renderer.setText(text);
    }

    @NotNull
    public ToggleAction[] getActions() {
        LocalToggleAction conn = new LocalToggleAction("Log In", "Log In", Icons.CONNECT, true, CONNECT);
        LocalToggleAction disconn = new LocalToggleAction("Log Out", "Log Out", Icons.DISCONNECT, false, DISCONNECT);
        LocalToggleAction edit = new LocalToggleAction("Edit Connection Settings", "Edit", Icons.EDIT_CONNECTION_PARAMS, true, EDIT);
        LocalToggleAction remove = new LocalToggleAction("Remove", "Remove", Icons.REMOVE_CONNECTION, REMOVE_SESSION);
        LocalToggleAction test = new LocalToggleAction("Test Connection", "Test Connection", Icons.TEST_CONNECTION, true, TEST_CONNECTION);

        return new ToggleAction[]{conn, disconn, edit, remove, test};
    }

    private void notifyListeners(int command) {
        switch (command) {
            case CONNECT: {
                MyProgressIndicator indicator = cinfo.connectAsynchronously();
                String url = cinfo.getUrl().getUserHostPortServiceName();
                new ProgressIndicatorHelper("Connecting to " + url).runBackgrounableWithProgressInd(indicator, false);
                break;
            }
            case DISCONNECT:
                cinfo.disconnect();
                break;
            case TEST_CONNECTION:
                new TestConnectionProgressIndicator(cinfo.getUrl()).checkConnection();
                break;
            case EDIT: {
                DbUrl url = cinfo.getUrl();
                ConnectionSettings2 settings = new ConnectionSettings2(
                    url.getHost(), url.getServiceName(), url.getPort(), url.getUser(), url.getPwd(),
                        cinfo.refreshPeriod(), cinfo.loginOnStart(), cinfo.repaireFailedConnection());

                settings.show();

                if (settings.isOK()) {
                    url = new DbUrl(
                            settings.getUserName(),
                            settings.getPassword(),
                            settings.getHost(),
                            settings.getPort(),
                            settings.getServiceName()
                    );

                    boolean loginOnStartup = settings.getLoginOnStartup();
                    boolean repair = settings.getRepairIfDropped();
                    int period = settings.getRefreshPeriod();

                    ConnectionManagerImpl.getInstance().updateSession(cinfo, url, loginOnStartup, repair, period);
                    listener.updated(ItemViewListener.REFRESH_NODE_PROPS, this);
                }

                break;
            }
            case REMOVE_SESSION:
                // todo - listener should be unregistered
                //notifyListeners(ItemViewListener.REMOVE_SESSION);
                listener.updated(ItemViewListener.REMOVE_NODE, this);
                break;
        }


        int hh =0;
    }

    /**
     * Callback function for listening events from ConnectionInfo
     *
     * @param state
     */
    public void updated(final int state) {
        if (uiLocked) {
            pendingRequests.add(state);
        } else {
            // todo - dirty hack, needs to be fixed asap
            SwingUtilities.invokeLater(
                    new Runnable() {
                        public void run() {
                            processState(state);
                        }
                    }
            );
        }
    }

    private void processState(int state) {
        if (state == SessionListener.DISCONNECTED) {
            cache = null;

            // remove all descendend nodes in cycle
            while (children.size() > 0) {
                ItemViewWrapper i = children.get(children.size() - 1);
                listener.updated(ItemViewListener.REMOVE_NODE, i);
            }

            // refresh properties
            listener.updated(ItemViewListener.REFRESH_NODE_PROPS, this);
        } else if (state == SessionListener.OBJECT_CACHE_UPDATED) {

            // remove all descendend nodes in cycle
            while (children.size() > 0) {
                ItemViewWrapper i = children.get(children.size() - 1);
                listener.updated(ItemViewListener.REMOVE_NODE, i);
            }

            cache = ObjectCacheFactory.getObjectCache(); //cinfo.getObjectCache();

            children.add(new DbSchemaTypeNode(this, DbObject.TABLE));
            listener.updated(ItemViewListener.ADD_NODE, children.get(children.size() - 1));
            children.add(new DbSchemaTypeNode(this, DbObject.VIEW));
            listener.updated(ItemViewListener.ADD_NODE, children.get(children.size() - 1));
            children.add(new DbSchemaTypeNode(this, DbObject.PACKAGE));
            listener.updated(ItemViewListener.ADD_NODE, children.get(children.size() - 1));
            children.add(new DbSchemaTypeNode(this, DbObject.FUNCTION));
            listener.updated(ItemViewListener.ADD_NODE, children.get(children.size() - 1));
            children.add(new DbSchemaTypeNode(this, DbObject.PROCEDURE));
            listener.updated(ItemViewListener.ADD_NODE, children.get(children.size() - 1));
            children.add(new DbSchemaTypeNode(this, DbObject.TRIGGER));
            listener.updated(ItemViewListener.ADD_NODE, children.get(children.size() - 1));
            children.add(new DbSchemaTypeNode(this, DbObject.TYPE));
            listener.updated(ItemViewListener.ADD_NODE, children.get(children.size() - 1));
            children.add(new DbSchemaTypeNode(this, DbObject.SEQUENCE));
            listener.updated(ItemViewListener.ADD_NODE, children.get(children.size() - 1));

            listener.updated(ItemViewListener.REFRESH_NODE_PROPS, this);
        }
    }

    class DbSchemaTypeNode extends ItemViewWrapperBase implements ItemViewWrapper {

        String type;
        public DbSchemaTypeNode(ItemViewWrapper parent, String type) {
            this.parent = parent;
            this.type = type;
            if (cinfo.isConnected()) {
                String user = cinfo.getUrl().getUser();
                Cache c = cache.getCache(user);

                children.addAll(DboDescriptorViewFactory.findByType(this, c, type));
            }
        }

        public Object[][] getTabularData() {
            return new Object[0][];
        }

        public Object[] getColumnNames() {
            return new Object[0];
        }

        public void render(DefaultTreeCellRenderer renderer) {
            renderer.setText(type + " (" + children.size() + ")");
        }

        @NotNull
        public ToggleAction[] getActions() {
            return new ToggleAction[0];
        }
    }

    class LocalToggleAction extends ToggleAction {

        int command;
        public LocalToggleAction(String actionName, String toolTip, Icon icon, boolean inverse, int command) {
            super(actionName, toolTip, icon);
            boolean enabled = cinfo.isConnected() ? !inverse : inverse;
            this.getTemplatePresentation().setEnabled(enabled);
            this.command = command;
        }

        public LocalToggleAction(String actionName, String toolTip, Icon icon, int command) {
            super(actionName, toolTip, icon);
            this.getTemplatePresentation().setEnabled(true);
            this.command = command;
        }

        public boolean isSelected(AnActionEvent event) {
            return false;
        }

        public void setSelected(AnActionEvent event, boolean b) {
            notifyListeners(command);
        }
    }


    class ProcessStatusReporterAdaptor implements ProcessStatusReporter {
        MyProgressIndicator indicator;

        public ProcessStatusReporterAdaptor(MyProgressIndicator indicator){
            this.indicator = indicator;
        }

        @NotNull
        public String getTitle() {
            return "Connecting ...";
        }

        public boolean isCompleted() {
            return indicator.getStatus() != MyProgressIndicator.ProgressStatus.INPROGRESS;
        }

        public boolean cancel() {
            return indicator.cancel();
        }

        public int getNumberOfChunks() {
            return indicator.getNumberOfAllChunks();
        }

        public int getCurrentStep() {
            int done = indicator.getNumberOfAllChunks()- indicator.getRestOfShunks();
            return done>=0? done: 0;
        }

        @NotNull
        public String getCurrentStepName() {
            return indicator.getCurrentStepName();
        }
    }
}
