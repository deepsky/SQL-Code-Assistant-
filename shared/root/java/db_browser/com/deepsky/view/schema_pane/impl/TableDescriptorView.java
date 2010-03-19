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

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.ConnectionManagerImpl;
import com.deepsky.database.DBException;
import com.deepsky.database.SqlScriptManager;
import com.deepsky.database.cache.Cache;
import com.deepsky.database.exec.RowSetModel;
import com.deepsky.database.ora.desc.OraTableDescriptor;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.struct.ColumnDescriptor;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.view.Icons;
import com.deepsky.view.query_pane.QueryResultPanel;
import com.deepsky.view.query_pane.QueryResultWindow;
import com.deepsky.view.schema_pane.ItemViewWrapper;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.util.ArrayList;
import java.util.List;

public class TableDescriptorView extends ItemViewWrapperBase implements ItemViewWrapper {

    final private int CREATE_SCRIPT = 1001;
    final private int DROP = 1002;
    final private int CREATE = 1003;
    final private int QUERY = 1004;

    Cache cache;
    String name;
    int tableType;

    public TableDescriptorView(Project project, ItemViewWrapper parent, Cache cache, String name) {
        super(project);
        this.cache = cache;
        this.name = name;
        this.parent = parent;
        this.tableType = ((OraTableDescriptor) cache.get(name, DbObject.TABLE)).getType();

    }

    public DbObject describe(){
        return cache.get(name, DbObject.TABLE);
    }

    public Object[][] getTabularData() {
        OraTableDescriptor dbo = (OraTableDescriptor) cache.get(name, DbObject.TABLE);

        List<String[]> out = new ArrayList<String[]>();
        if (dbo != null) {
            for (String column : dbo.getColumnNames()) {
                String[] params = new String[7];
                ColumnDescriptor cdesc = dbo.getColumnDescriptor(column);
                // "Column Name"
                params[0] = column;
                // "Type"
                params[1] = cdesc.getType().toString();
                // "IsNull"
                params[2] = cdesc.isNullable() ? "Yes" : "No";
                // "IsPK"
                params[3] = cdesc.isPK() ? "Yes" : "";
                // "IsFK"
                params[4] = cdesc.isFK() ? "Yes" : "";
                // "Referenced Table"
                params[5] = cdesc.isFK() ? cdesc.getReferencedTable() : "";
                // "Referenced Column"
                params[6] = cdesc.isFK() ? cdesc.getReferencedColumn() : "";

                out.add(params);
            }
        }
        return out.toArray(new Object[out.size()][]);
    }

    public Object[] getColumnNames() {
        return new Object[]{"Column Name", "Type", "IsNull", "IsPK", "IsFK", "Ref Table", "Ref Column"};
    }

    public void render(DefaultTreeCellRenderer renderer) {
        renderer.setText(name);
        switch (tableType) {
            case TableDescriptor.EXTERNAL:
                renderer.setIcon(Icons.EXT_TABLE);
                break;
            case TableDescriptor.PARTITIONED:
                renderer.setIcon(Icons.PARTI_TABLE);
                break;
            case TableDescriptor.TEMPORARY:
                renderer.setIcon(Icons.TEMP_TABLE);
                break;
            case TableDescriptor.IOT:
                renderer.setIcon(Icons.TABLE);
                break;
            default:
                // TableDescriptor.REGULAR
                renderer.setIcon(Icons.TABLE);
                break;
        }
    }

    @NotNull
    public ToggleAction[] getActions() {
        LocalToggleAction open = new LocalToggleAction("Create Script", "Create Script", Icons.VIEW_DEF, CREATE_SCRIPT);
//        LocalToggleAction drop = new LocalToggleAction("Drop", "Drop", Icons.DROP, DROP);
//        LocalToggleAction create = new LocalToggleAction("Create Table", "Create Table", Icons.CONNECT,  CREATE);
        LocalToggleAction queryData = new LocalToggleAction("Query Data", "Query Data", Icons.QUERY_DATA, QUERY);

        return new ToggleAction[]{open, queryData};
    }

    @NotNull
    public ToggleAction[] getPopupActions() {
        LocalToggleAction open = new LocalToggleAction("Create Script", "Create Script", Icons.VIEW_DEF, CREATE_SCRIPT);
        LocalToggleAction queryData = new LocalToggleAction("Query Data", "Query Data", Icons.QUERY_DATA, QUERY);
        return new ToggleAction[]{open, queryData};
    }

    private void notifyListeners(int command) {
        switch (command) {
            case CREATE_SCRIPT: {
//                try {
//                select DBMS_METADATA.GET_DDL('TABLE', 'TODAY_SALES', 'PLN')
//                from dual
//                    SQLExecutor executor = ConnectionManagerImpl.getInstance().getSQLExecutor();
//                    new SqlRunnerHelper(executor, "select bb", new SqlRunnerHelper.QueryResultListener() {
//                        public void handleQueryResult(RowSetModel result) {
//                        }
//                    });
//                } catch (DBException e) {
//                    e.printStackTrace();
//                }

                OraTableDescriptor dbo = (OraTableDescriptor) cache.get(name, DbObject.TABLE);
                //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
                boolean result = SqlScriptManager.openFileInEditor(project, dbo);
                break;
            }
//            case DROP:
//                try {
//                    boolean res = ConnectionManagerImpl.getInstance().getSQLExecutor().dropTable(name);
//                } catch (DBException e) {
//                    Project project = DataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
//                    Messages.showErrorDialog(project, e.getMessage(), "Table drop failed");
//                }
//                break;
            case QUERY:
                try {
                    ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
                    QueryResultWindow qrwn = PluginKeys.QR_WINDOW.getData(project);
//                    RowSetModel t = ConnectionManagerImpl.getInstance().getSQLExecutor().executeQuery("SELECT * FROM " + name);
                    RowSetModel t = manager.getSQLExecutor().executeQuery("SELECT * FROM " + name);
                    QueryResultPanel resultPanel = qrwn.createResultPanel(
                            QueryResultPanel.SELECT_RESULT, getTabName(), Icons.TABLE, null /* ToolTip text */
                    );
                    resultPanel.init(t);

                    // show content pane
                    qrwn.showContent(getTabName());

                } catch (DBException e) {
                    //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
                    Messages.showErrorDialog(project, e.getMessage(), "Data load failed");
                }

                break;
        }
    }

    private String getTabName() {
        return name.toUpperCase() + " [TABLE]";
    }

    class LocalToggleAction extends ToggleAction {

        int command;

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

    public void runDefaultAction() {
        OraTableDescriptor dbo = (OraTableDescriptor) cache.get(name, DbObject.TABLE);
        //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
        boolean result = SqlScriptManager.openFileInEditor(project, dbo);
    }
}
