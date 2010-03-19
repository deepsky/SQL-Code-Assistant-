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

package com.deepsky.view.schema_pane.impl;

import com.deepsky.database.ConnectionInfo;
import com.deepsky.database.ConnectionManager;
import com.deepsky.database.ConnectionManagerImpl;
import com.deepsky.database.DbConfigurationException;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.gui.ConnectionSettings2;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.view.Icons;
import com.deepsky.view.schema_pane.ItemViewListener;
import com.deepsky.view.schema_pane.ItemViewWrapper;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;


public class ConnectionItemListView extends ItemViewWrapperBase implements ItemViewWrapper, ItemViewListener {

    ConnectionManager manager;

    public ConnectionItemListView(Project project) {
        super(project);
        manager = PluginKeys.CONNECTION_MANAGER.getData(project);

//        for (ConnectionInfo cinfo : ConnectionManagerImpl.getInstance().getSessionList()) {
        for (ConnectionInfo cinfo : manager.getSessionList()) {
            DbSchemaItemView schema = new DbSchemaItemView(project, this, cinfo);
            schema.setListener(this);
            children.add(schema);
        }
    }

    protected ConnectionItemListView itself() {
        return this;
    }

    public Object[][] getTabularData() {
        return new Object[0][];
    }

    public Object[] getColumnNames() {
        return new Object[0];
    }

    public void render(DefaultTreeCellRenderer renderer) {
        renderer.setText("Database Connections");
        renderer.setIcon(Icons.SCHEMA_LIST);
    }

    @NotNull
    public ToggleAction[] getActions() {
        return new ToggleAction[]{
                new LocalToggleAction("New Connection", "New Connection", Icons.NEW_CONNECTION)
        };
    }

    public void updated(int command, Object o) {
        if (command == ItemViewListener.REMOVE_NODE && o instanceof DbSchemaItemView) {
            listener.updated(ItemViewListener.REMOVE_NODE, o);
            DbSchemaItemView schema = (DbSchemaItemView) o;
            manager.removeSession(schema.cinfo);
//            ConnectionManagerImpl.getInstance().removeSession(schema.cinfo);
        } else {
            listener.updated(command, o);
        }
    }


    class LocalToggleAction extends ToggleAction {

        ConnectionItemListView parent;

        public LocalToggleAction(ConnectionItemListView parent, String actionName, String toolTip, Icon icon, boolean inverse) {
            super(actionName, toolTip, icon);
            this.parent = parent;
        }

        public LocalToggleAction(String actionName, String toolTip, Icon icon) {
            super(actionName, toolTip, icon);
            this.getTemplatePresentation().setEnabled(true);
//            this.setEnabledInModalContext(true);
        }

        public boolean isSelected(AnActionEvent event) {
            return false;
        }

        public void setSelected(AnActionEvent event, boolean b) {

            final Project project = event.getData(LangDataKeys.PROJECT);
            ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
//            String[] hosts = ConnectionManagerImpl.getInstance().getHostList();
            String[] hosts = manager.getHostList();
            ConnectionSettings2 settings = new ConnectionSettings2(project, hosts);
            settings.show();

            if (settings.isOK()) {

                DbUrl url = new DbUrl(
                        settings.getUserName(),
                        settings.getPassword(),
                        settings.getHost(),
                        settings.getPort(),
                        settings.getServiceName()
                );

                boolean loginOnStartup = settings.getLoginOnStartup();
                boolean repair = settings.getRepairIfDropped();
                int period = settings.getRefreshPeriod();

                try {
//                    ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
                    ConnectionInfo cinfo = manager.createSession(url, loginOnStartup, repair, period );
//                    ConnectionInfo cinfo = ConnectionManagerImpl.getInstance().createSession(url, loginOnStartup, repair, period );

                    DbSchemaItemView item = new DbSchemaItemView(project, itself(), cinfo);
                    children.add(item);
                    item.setListener(itself());

                    if (listener != null) {
                        listener.updated(ItemViewListener.ADD_NODE, item);
                    }
                } catch (DbConfigurationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
