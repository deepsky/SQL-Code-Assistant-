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

package com.deepsky.view.query_pane;

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.ConnectionManagerListener;
import com.deepsky.database.DBException;
import com.deepsky.gui.ExportSettings;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.conf.PluginSettingsBean;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.utils.StringUtils;
import com.deepsky.view.Icons;
import com.deepsky.view.query_pane.markup.SqlStatementMarker;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class QueryResultWindow implements ConnectionManagerListener {

    public static final String QUERY_RESULT_PANE = "Query Result";
    public static final String DATAGRID_PANE = "Data Grid";

    public static final String JTABBED_CONTROL = "JTABBED.CONTROL";
    public static final String CREATE_TIME = "CREATE.TIME";

    final static int REFRESH_RESULTSET = 1;
    final static int STICKY_OPTION = 3;
    final static int EXPORT_DATA = 4;
    final static int CLOSE_PANEL = 5;

    Project project;
    ConnectionManager connectionManager;

    //int maxTabs = 5;
    boolean isConnected = false;


    public static synchronized QueryResultWindow getInstance() {
        return PluginKeys.QR_WINDOW.getData(); //instance;
    }

    public QueryResultWindow(Project project, ConnectionManager connectionManager) {
        this.project = project; //DataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
        this.connectionManager = connectionManager;

        if (connectionManager.isConnected()) {
            //statusLine.setSchemaName(ConnectionManagerImpl.getInstance().getDbUrl().getUserHostPortServiceName().toLowerCase());
            isConnected = true;
        } else {
            //statusLine.setSchemaName("Not logged in");
            //throw new IllegalStateException();
        }

        connectionManager.addStateListener(this);
    }

    private void assertTabCount(Content content) {
//        PluginSettingsBean bean = (PluginSettingsBean) SharedObjectPool.getUserData(SharedConstants.PLUGIN_SETTINGS);
        PluginSettingsBean bean = PluginKeys.PLUGIN_SETTINGS.getData();

        if (bean == null) {
            bean = new PluginSettingsBean();
        }

        int max = bean.getNumberOfTabs();

        if (max <= getTabComponent(content).getTabCount()) {
            // remove the oldest tab
            JTabbedPane tabbedPane = getTabComponent(content);
            long lastMin = Long.MAX_VALUE;
            int index = 0;
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                JComponent tab = (JComponent) tabbedPane.getTabComponentAt(i);
                Long time = (Long) tab.getClientProperty(CREATE_TIME);
                if (time != null && lastMin < time) {
                    lastMin = time;
                    index = i;
                }
            }

            tabbedPane.remove(index);
        }
    }

    @NotNull
    public QueryResultPanel createResultPanel(int panelType, SqlStatementMarker sqlMarker, Icon icon, String toolTip) {

        String contentName = connectionManager.getDbUrl().getUserHostPortServiceName().toLowerCase();
        Content content = createContent(contentName);

        Component component = getTab(content, sqlMarker.getName());
        if (panelType == QueryResultPanel.SELECT_RESULT) {
            if (component == null) {
                assertTabCount(content);
                return addGridPanelTab(content, sqlMarker, icon, toolTip);
//            } else if (component instanceof DataGridPanel) {
//                return (QueryResultPanel) component;
            } else {
                // component != null && !(component instanceof DataGridPanel)
                // replace old panel with new one
                removeTab(content, sqlMarker.getName());
                return addGridPanelTab(content, sqlMarker, icon, toolTip);
            }

        } else if (panelType == QueryResultPanel.DML_QUERY_RESULT) {
            if (component == null) {
                assertTabCount(content);
                return addDMLStatsTab(content, sqlMarker, icon, toolTip);
//            } else if (component instanceof QueryResultPanel) {
//                return (QueryResultPanel) component;
            } else {
                // component != null && !(component instanceof DataGridPanel)
                // replace old panel with new one
                removeTab(content, sqlMarker.getName());
                return addDMLStatsTab(content, sqlMarker, icon, toolTip);
            }
        } else {
            throw new NotSupportedException();
        }
    }

    @NotNull
    public QueryResultPanel createResultPanel(int panelType, String tabName, Icon icon, String toolTip) {

        String contentName = connectionManager.getDbUrl().getUserHostPortServiceName().toLowerCase();
        Content content = createContent(contentName);

        Component component = getTab(content, tabName);
        if (panelType == QueryResultPanel.SELECT_RESULT) {
            if (component == null) {
                assertTabCount(content);
                return addGridPanelTab(content, tabName, icon, toolTip);
//            } else if (component instanceof DataGridPanel) {
//                return (QueryResultPanel) component;
            } else {
                // component != null && !(component instanceof DataGridPanel)
                // replace old panel with new one
                removeTab(content, tabName);
                return addGridPanelTab(content, tabName, icon, toolTip);
            }
        } else {
            throw new NotSupportedException();
        }
    }

    private DataGridPanel addGridPanelTab(@NotNull Content content, String tabName, Icon icon, String toolTip) {
        JTabbedPane tabbedPane = getTabComponent(content);
        DataGridPanel dataGridPanel = new DataGridPanel();
        dataGridPanel.putClientProperty(CREATE_TIME, new Date().getTime());

        tabbedPane.addTab(tabName, dataGridPanel);
        int index = tabbedPane.indexOfTab(tabName);
        ButtonTabComponent buttonTab = new ButtonTabComponent(tabbedPane, icon);
        if (toolTip != null) {
            buttonTab.setToolTipText(toolTip);
        }
        tabbedPane.setTabComponentAt(index, buttonTab);
        return dataGridPanel;
    }

    private DataGridPanel addGridPanelTab(@NotNull Content content, SqlStatementMarker sqlMarker, Icon icon, String toolTip) {
        JTabbedPane tabbedPane = getTabComponent(content);
        DataGridPanel dataGridPanel = new DataGridPanel();
        dataGridPanel.putClientProperty(CREATE_TIME, new Date().getTime());

        tabbedPane.addTab(sqlMarker.getName(), dataGridPanel);
        int index = tabbedPane.indexOfTab(sqlMarker.getName());
        ButtonTabComponent buttonTab = new ButtonTabComponent(tabbedPane, icon, sqlMarker);
        if (toolTip != null) {
            buttonTab.setToolTipText(toolTip);
        }
        tabbedPane.setTabComponentAt(index, buttonTab);
        return dataGridPanel;
    }


    private QueryStatisticsPanel addDMLStatsTab(@NotNull Content content, SqlStatementMarker sqlMarker, Icon icon, String s) {
//        Component[] comps = (content.getComponent()).getComponents();
//        JTabbedPane tabbedPane = (JTabbedPane) comps[1];
        JTabbedPane tabbedPane = getTabComponent(content);
        QueryStatisticsPanel dmlStatsPanel = new QueryStatisticsPanel();
        dmlStatsPanel.putClientProperty(CREATE_TIME, new Date().getTime());

        tabbedPane.addTab(sqlMarker.getName(), dmlStatsPanel);
        int index = tabbedPane.indexOfTab(sqlMarker.getName());
        tabbedPane.setTabComponentAt(index,
                new ButtonTabComponent(tabbedPane, icon, sqlMarker));

        return dmlStatsPanel;
    }

    private Component getTab(@NotNull Content content, String displayName) {
//        Component[] comps = (content.getComponent()).getComponents();
//        JTabbedPane tabbedPane = (JTabbedPane) comps[1];
        JTabbedPane tabbedPane = getTabComponent(content);
        int index = tabbedPane.indexOfTab(displayName);
        if (index == -1) {
            return null;
        }

        return tabbedPane.getComponentAt(index);
    }

    private void removeTab(@NotNull Content content, String displayName) {
//        Component[] comps = (content.getComponent()).getComponents();
//        JTabbedPane tabbedPane = (JTabbedPane) comps[1];
        JTabbedPane tabbedPane = getTabComponent(content);
        int index = tabbedPane.indexOfTab(displayName);
        if (index == -1) {
            return;
        }

        tabbedPane.remove(index);
    }

    private QueryResultPanel getSelectedTab(@NotNull Content content) {
//        Component[] comps = (content.getComponent()).getComponents();
//        JTabbedPane tabbedPane = (JTabbedPane) comps[1];
        JTabbedPane tabbedPane = getTabComponent(content);
        int index = tabbedPane.getSelectedIndex();
        if (index == -1) {
            return null;
        }

        return (QueryResultPanel) tabbedPane.getComponentAt(index);
    }


    private JTabbedPane getTabComponent(Content content) {
        Component[] comps = (content.getComponent()).getComponents();
        JTabbedPane tabbedPane = (JTabbedPane) comps[1];
        return tabbedPane;
    }

    @NotNull
    private Content createContent(String contentName) {
        if (project == null) {
            throw new IllegalStateException("Project not opened");
        }

        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ToolWindow wm = ToolWindowManager.getInstance(project).getToolWindow(QUERY_RESULT_PANE);

        if (wm == null) {
            wm = toolWindowManager.registerToolWindow(
                    QUERY_RESULT_PANE, true, ToolWindowAnchor.BOTTOM
            );
            wm.setIcon(Icons.QUERY_RESULT_PANE);
            wm.setToHideOnEmptyContent(true);
        }

        Content content = wm.getContentManager().findContent(contentName);
        if (content == null) {
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addContainerListener(new JTabbedPaneListener());

            tabbedPane.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    ((JComponent) e.getComponent()).putClientProperty(JTABBED_CONTROL, e.isControlDown());
                }

                public void keyReleased(KeyEvent e) {
                    ((JComponent) e.getComponent()).putClientProperty(JTABBED_CONTROL, e.isControlDown());
                }
            });
            tabbedPane.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    Object source = e.getSource();
                    Component c = e.getOppositeComponent();
                }

                public void focusLost(FocusEvent e) {
                    ((JComponent) e.getSource()).putClientProperty(JTABBED_CONTROL, false);
                }

            });

            JPanel jpanel = new JPanel(new BorderLayout());

            DefaultActionGroup actionGroup = new DefaultActionGroup("PsiActionGroup22", false);
            actionGroup.add(new LocalToggleAction("Refresh Query Result", "Refresh Query Result", Icons.REFRESH_RESULTSET, REFRESH_RESULTSET));
            actionGroup.add(new LocalToggleAction("Copy", "Copy", Icons.EXPORT_DATA, EXPORT_DATA));

// todo -- problem with keeping sql markers and tabs in sync
//            actionGroup.add(new LocalToggleAction("Close", "Close", Icons.CLOSE_PANEL, CLOSE_PANEL));

            ActionManager actionManager = ActionManager.getInstance();
            ActionToolbar toolBar = actionManager.createActionToolbar("DataGridActionToolbar", actionGroup, false);
            jpanel.add(toolBar.getComponent(), "West");
            jpanel.add(tabbedPane, "Center");

//            ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
//            ContentFactory contentFactory = PeerFactory.getInstance().getContentFactory();
            ContentFactory contentFactory = ContentFactoryEx.getContentFactory();
            content = contentFactory.createContent(jpanel, contentName, false);
            //content.setActions(actionGroup, "UKNOWN", null);
            content.setIcon(Icons.DISCONNECT);
            wm.getContentManager().addContent(content);
        }

        return content;
    }

    public void showContent(String displayName) {
        showContent(displayName, 500);
    }

    /**
     * Set the requested content "selected" and show ToolWindow if it hided
     *
     * @param displayName
     * @param wait
     */
    public void showContent(String displayName, final int wait) {
//        Project project = DataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ToolWindow wm = toolWindowManager.getToolWindow(QUERY_RESULT_PANE);

        if (wm == null) {
            wm = toolWindowManager.registerToolWindow(QUERY_RESULT_PANE, true, ToolWindowAnchor.BOTTOM);
            wm.setIcon(Icons.QUERY_RESULT_PANE);
            wm.setToHideOnEmptyContent(true);
        }

        String contentName = connectionManager.getDbUrl().getUserHostPortServiceName().toLowerCase();
        Content content = wm.getContentManager().findContent(contentName);

        if (content == null) {
            return;
        }

        wm.getContentManager().setSelectedContent(content);
        JTabbedPane tabbedPane = getTabComponent(content);
        int index = tabbedPane.indexOfTab(displayName);
        if (index == -1) {
            return;
        }

        tabbedPane.requestFocusInWindow();
        tabbedPane.setSelectedIndex(index);

        if (!wm.isVisible()) {
            wm.activate(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(wait);
                    } catch (InterruptedException e1) {
                    }
                }
//            }, false);
            }, true);
        }
    }


    private QueryResultPanel getSelectedResultPanel() {
        ToolWindow wm = ToolWindowManager.getInstance(project).getToolWindow(QUERY_RESULT_PANE);
        Content selected = wm.getContentManager().getSelectedContent();
        if (selected != null) {
            return getSelectedTab(selected);
        }

        return null;
    }

    public void close() {
        connectionManager.removeStateListener(this);

        //getTabComponent(Content content)
//        Project project = DataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());

        ToolWindowManager toolWindowManager = null;
        try {
            toolWindowManager = ToolWindowManager.getInstance(project);
        } catch (Throwable e) {
            // todo -- fix me
        }
        if (toolWindowManager != null) {
            toolWindowManager.unregisterToolWindow(QUERY_RESULT_PANE);
        }
    }

    public void handleUpdate(StateEvent state) {
        switch (state.getStatus()) {
            case ConnectionManagerListener.CONNECTED:
//                statusLine.setSchemaName(state.getUrl().getUserHostPortServiceName());
                break;
            case ConnectionManagerListener.DISCONNECTED:
//                statusLine.setSchemaName("Not logged in");
                break;
        }
    }


    class JTabbedPaneListener implements ContainerListener {
        public void componentAdded(ContainerEvent e) {
        }

        public void componentRemoved(ContainerEvent e) {
            if (e.getChild() instanceof QueryResultPanel) {
                // finalize QueryResultPanel
                ((QueryResultPanel) e.getChild()).close();
            }
        }
    }

    class LocalToggleAction extends ToggleAction {

        int command;
        boolean selected = false;

        public LocalToggleAction(String actionName, String toolTip, Icon icon, int command) {
            super(actionName, toolTip, icon);
            this.getTemplatePresentation().setEnabled(true); //isConnected && rsModel != null);
            this.command = command;
        }

        public boolean isSelected(AnActionEvent event) {
            return selected;
        }

        public void setSelected(AnActionEvent event, boolean b) {
            switch (command) {
                case REFRESH_RESULTSET: {
                    QueryResultPanel resultPanel = getSelectedResultPanel();
                    if (isConnected && resultPanel instanceof DataGridPanel && resultPanel.isRefreshSupported()) {
                        try {
                            resultPanel.refresh();
                        } catch (DBException e) {
                        }
                    }
                    break;
                }
                case CLOSE_PANEL:
                    close();
                    break;
                case STICKY_OPTION:
                    selected ^= true;
                    break;
                case EXPORT_DATA: {
                    QueryResultPanel resultPanel = getSelectedResultPanel();
                    if (resultPanel instanceof DataGridPanel) {
                        DataGridPanel grid = (DataGridPanel) resultPanel;
                        JTable _table = grid.getTable();

                        int numcols = _table.getSelectedColumnCount();
                        int numrows = _table.getSelectedRowCount();

                        if (numcols == 0 && numrows == 0) {
                            // nothing to copy
                            return;
                        }

                        ExportSettings settings = new ExportSettings();
                        settings.show();
                        if (!settings.isOK()) {
                            return;
                        }

                        File fileToSave = null;
                        if (settings.saveToFile()) {
                            String path = settings.getFilePathToSave();
                            if (path == null || path.length() == 0) {
                                Messages.showInfoMessage(
                                        "File not specified, content will be saved in Clipboard",
                                        "File not specified"
                                );
//                            } else if (new File(path).getParentFile().exists()) {
                            } else {
                                // todo --
                                fileToSave = new File(path);
                            }
                        }

                        String delimiter;
                        switch (settings.getDelimiter()) {
                            case TAB:
                                delimiter = "\t";
                                break;
                            case COMMA:
                                delimiter = ",";
                                break;
                            default: //  SEMICOLON:
                                delimiter = ";";
                                break;
                        }

                        String content = extractContent(
                                grid, delimiter, settings.saveColumnHeaders(), settings.encloseFieldsInDowubleQuotes());
                        if (content != null) {
                            if (fileToSave != null) {
                                // save in the file
                                try {
                                    StringUtils.string2file(content, fileToSave);
                                } catch (IOException e) {
                                    Messages.showErrorDialog(e.getMessage(), "Export failed");
                                }
                            } else {
                                // save in Clipboard
                                StringSelection stsel = new StringSelection(content);
                                Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
                                system.setContents(stsel, stsel);
                            }
                        }
                    }
                    break;
                }
            }
            //notifyListeners(command);
        }
    }


    private String extractContent(DataGridPanel grid, String delimiter, boolean saveHeader, boolean ecloseWithDowbleQuotes) {
        JTable _table = grid.getTable();

        int numcols = _table.getSelectedColumnCount();
        int numrows = _table.getSelectedRowCount();

        if (numcols > 0 && numrows > 0) {
            StringBuffer sbf = new StringBuffer();
            int[] rowsselected = _table.getSelectedRows();
            int[] colsselected = _table.getSelectedColumns();

            if (saveHeader) {
                // put header name list
                for (int j = 0; j < numcols; j++) {
                    String text = (String) _table.getTableHeader().getColumnModel().getColumn(colsselected[j]).getHeaderValue();
                    sbf.append(text);
                    if (j < numcols - 1) sbf.append(delimiter);
                }
                sbf.append("\n");
            }

            // put content
            for (int i = 0; i < numrows; i++) {
                for (int j = 0; j < numcols; j++) {
                    Object value = _table.getValueAt(rowsselected[i], colsselected[j]);
                    String _value = "";
                    if (value != null) {
                        _value = value.toString();
                    }
                    if (ecloseWithDowbleQuotes) {
                        sbf.append("\"").append(_value).append("\"");
                    } else {
                        sbf.append(_value);
                    }

                    if (j < numcols - 1) sbf.append(delimiter);
                }
                sbf.append("\n");
            }

//            StringSelection stsel = new StringSelection(sbf.toString());
//            Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
//            system.setContents(stsel, stsel);
            return sbf.toString();
        }

        return null;
    }

/*
    public void invalidateContent(String displayName){
//        Project project = DataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
        ToolWindow wm = ToolWindowManager.getInstance(project).getToolWindow(QUERY_RESULT_PANE);

        if(wm == null){
            // nothing to do
        } else {
            Content content =  wm.getContentManager().findContent(displayName);
            if(content != null){
                // todo
                wm.getContentManager().removeContent(content, true);
                return;
            }

        }
    }
*/

}
