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
import com.deepsky.view.Icons;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class DbSchemaPane1001 extends JPanel {

    ConnectionElementImpl connector;
    JComboBox connectionComboBox;
    Project project;
    TabbedPaneManager tabbedPane;
//    JTabbedPane lastCenterComp = null;
    TabbedPaneWrapper lastCenterPane = null;

    JLabel statusLabel;

    public DbSchemaPane1001(Project project, TabbedPaneManager tabbedPaneManager) {
        super(new BorderLayout());
        this.project = project;

        tabbedPane = tabbedPaneManager; //new TabbedPaneManager(project);

        this.setBorder(new EmptyBorder(2, 2, 2, 2));

        // create Connector and notification channel --------------------
        this.connector = new ConnectionElementImpl(project) {
            public void setSelectedItem(Object anItem) {
                super.setSelectedItem(anItem);
                connectorSelectedChanged((String) anItem);
            }
        };
        this.connector.addListener(new ConnectionElementListener() {
            public void connectionStatusUpdated(String source, int state) {
                connectionStatusChanged(source, state);
            }
        });
        // --------------------------------------------------------------

        this.connector.init();

        this.add(createConnectSelector(), BorderLayout.NORTH);
    }

    class AncestorListenerImpl implements AncestorListener {
        public void ancestorAdded(AncestorEvent event) {
/*
            if (_tree.getLastSelectedPathComponent() == null) {
//                LOG.warn("ancestorAdded");
                _model = new DbSchemaTreeModel();

                ItemViewWrapper root = new ConnectionItemListView(project);
                root.setListener(new ItemViewListenerImpl());

                _model.setRoot(root);
                _tree.setModel(_model);
            } else {
                setSelectedElement2(_tree.getLastSelectedPathComponent());
            }
*/
        }

        public void ancestorRemoved(AncestorEvent event) {
        }

        public void ancestorMoved(AncestorEvent event) {
        }
    }


    private void createTabbedPane(final DbUrl dbUrl) {
        if (lastCenterPane != null) {
            remove(lastCenterPane.getTabbedPane());
        }
        lastCenterPane = tabbedPane.selectSheet(dbUrl);
        add(lastCenterPane.getTabbedPane(), BorderLayout.CENTER);
        validate();

        lastCenterPane.getTabbedPane().repaint();
    }

    public JComboBox getConnectionComboBox() {
        return connectionComboBox;
    }

    public TabbedPaneWrapper selectTabbedPane(DbUrl dbUrl){
        connectionComboBox.setSelectedItem(dbUrl.getUserHostPortServiceName());
        return lastCenterPane;
    }

    private JPanel createConnectSelector() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBorder(new EmptyBorder(4, 2, 4, 2));

        JPanel connectionPane = new JPanel(new BorderLayout());
        Border border = new EtchedBorder();
        connectionPane.setBorder(new TitledBorder(border, " Connection Status "));

        connectionComboBox = new JComboBox();
//        connectionComboBox.setMinimumSize(new Dimension(35, -1));
        //comboBox.setPreferredSize(new Dimension(40, -1));
        connectionPane.add(connectionComboBox, BorderLayout.CENTER);
        connectionComboBox.setRenderer(new ConnectionItemRenderer());
        connectionComboBox.setModel(connector);

        statusLabel = new JLabel(" OFFLINE");
//        statusLabel.setBackground(Color.GREEN);
        connectionPane.add(statusLabel, BorderLayout.EAST);

        main.add(connectionPane, BorderLayout.NORTH);

        ActionManager actionManager = ActionManager.getInstance();
        DefaultActionGroup actionGroup = new DefaultActionGroup("DBSchemaActionGroup", false);
        actionGroup.add(new DependedToggleAction("Connect", "Connect", Icons.CONNECT, CMD_CONNECT, true));
        actionGroup.add(new DependedToggleAction("Disconnect", "Disconnect", Icons.DISCONNECT, CMD_DISCONNECT, false));
        actionGroup.add(new DependedToggleAction("Edit Connection Settings", "Edit", Icons.EDIT_CONNECTION_PARAMS, CMD_EDIT_SETTINGS, true));
        actionGroup.add(new DependedToggleAction("Test Connection", "Test", Icons.TEST_CONNECTION, CMD_TEST_CONNECTION, true));
        actionGroup.add(new DependedToggleAction("Refresh", "Refresh", Icons.REFRESH_SESSION, CMD_REFRESH, false));
        actionGroup.addSeparator();
        actionGroup.add(new LocalToggleAction("Create Connection", "New", Icons.NEW_CONNECTION, CMD_CREATE_CONNECT));
        actionGroup.add(new DependedToggleAction("Remove Connection", "Remove", Icons.REMOVE_CONNECTION, CMD_REMOVE_CONNECT, true));

        ActionToolbar toolBar = actionManager.createActionToolbar("DBSchemaActionToolbar", actionGroup, true);
        main.add(toolBar.getComponent(), BorderLayout.CENTER); //"North");
        JSeparator separtor = new JSeparator();
        //separtor.setBorder(new EmptyBorder(1, 2, 1, 2));
        main.add(separtor, BorderLayout.SOUTH);

        return main;
    }

    private void connectionStatusChanged(String source, int state) {
        switch (state) {
            case ConnectionElementListener.CONNECTED:
                if (connector.getSelectedItem().equals(source))
                    setConnectionStatus(true);
                break;
            case ConnectionElementListener.DISCONNECTED:
                if (connector.getSelectedItem().equals(source))
                    setConnectionStatus(false);
                break;
        }
    }

    private void connectorSelectedChanged(String source) {
        if (statusLabel != null) {
            if (connector != null && connector.isConnected(source)) {
                statusLabel.setText(" ONLINE ");
            } else {
                statusLabel.setText(" OFFLINE");
            }
        }

        if (tabbedPane != null) {
            createTabbedPane(connector.getSelected());
        }
    }

    private final int CMD_CONNECT = 1001;
    private final int CMD_DISCONNECT = 1002;
    private final int CMD_CREATE_CONNECT = 1003;
    private final int CMD_REMOVE_CONNECT = 1004;
    private final int CMD_EDIT_SETTINGS = 1005;
    private final int CMD_TEST_CONNECTION = 1006;
    private final int CMD_REFRESH = 1007;


    private void handleEvent(int cmd) {
        switch (cmd) {
            case CMD_CONNECT:
                connector.connect();
                break;
            case CMD_DISCONNECT:
                connector.disconnect();
                break;
            case CMD_CREATE_CONNECT:
                connector.createConnection();
                break;
            case CMD_REMOVE_CONNECT: {
                DbUrl selected = connector.getSelected();
                if (connector.removeConnection()) {
                    tabbedPane.removeSheet(selected);
                }
                break;
            }
            case CMD_EDIT_SETTINGS:
                connector.editConnectionSettings();
                break;
            case CMD_TEST_CONNECTION:
                connector.testConnection();
                break;
            case CMD_REFRESH:
                break;
        }
    }

    private void setConnectionStatus(final boolean connected) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                if (connected)
                    statusLabel.setText(" ONLINE ");
                else
                    statusLabel.setText(" OFFLINE");
            }
        });
    }


    /**
     * Action handlers
     */
    public class LocalToggleAction extends ToggleAction {
        int command;

        public LocalToggleAction(String actionName, String toolTip, Icon icon, int command) {
            super(actionName, toolTip, icon);
            boolean enabled = true;
            this.getTemplatePresentation().setEnabled(enabled);
            this.command = command;
        }

        public boolean isSelected(AnActionEvent event) {
            return false;
        }

        public void setSelected(AnActionEvent event, boolean b) {
            handleEvent(command);
        }
    }

    public class DependedToggleAction extends LocalToggleAction {
        boolean inverse;

        public DependedToggleAction(String actionName, String toolTip, Icon icon, int command, boolean inverse) {
            super(actionName, toolTip, icon, command);
            boolean enabled = true;
            this.getTemplatePresentation().setEnabled(enabled);
            this.inverse = inverse;
        }

        public boolean isSelected(AnActionEvent event) {
            String selected = (String) connectionComboBox.getSelectedItem();
            if (selected != null && connector.isConnected(selected)) {
                event.getPresentation().setEnabled(!inverse);
            } else {
                if (selected != null && selected.equals(connector.getLocalFS())) {
                    // selected LOCAL FS
                    event.getPresentation().setEnabled(false);
                } else {
                    event.getPresentation().setEnabled(inverse);
                }
            }
            return false;
        }
    }


    /**
     * Combo Box list item custom renderer
     */
    private class ConnectionItemRenderer extends JLabel implements ListCellRenderer {

        Font defaultFont;
        protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

        public ConnectionItemRenderer() {
            setOpaque(true);
            defaultFont = getFont();
        }

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Font theFont = null;
            Color theForeground = null;
            Color theBackground = null;
            Icon theIcon = null;
            String theText = null;

            JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (connector.isConnected((String) value)) {
                theFont = list.getFont().deriveFont(Font.BOLD);
                theIcon = Icons.DB_SCHEMA;
            } else {
                theFont = defaultFont;
                theIcon = Icons.DB_SCHEMA_DISABLED;
            }
            theText = (String) value;

            theForeground = Color.BLACK;
            if (isSelected) {
                theBackground = new Color(185, 185, 255);
                renderer.setForeground(theForeground);
                renderer.setBackground(theBackground);
            } else {
                theBackground = Color.WHITE;
                renderer.setForeground(theForeground);
                renderer.setBackground(theBackground);
            }
            renderer.setIcon(theIcon);
            renderer.setText(theText);
            renderer.setFont(theFont);
            return renderer;
        }
    }


}
