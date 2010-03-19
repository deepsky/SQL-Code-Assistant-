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
import com.deepsky.database.DBException;
import com.deepsky.database.SqlScriptManager;
import com.deepsky.database.cache.Cache;
import com.deepsky.database.exec.RowSetModel;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.struct.ColumnDescriptor;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.ViewDescriptor;
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

import javax.swing.tree.DefaultTreeCellRenderer;
import java.util.ArrayList;
import java.util.List;

public class ViewDescriptorView  extends ItemViewWrapperBase implements ItemViewWrapper, ToggleActionListener {

    final private int OPEN = 1001;
    final private int DROP = 1002;
    final private int QUERY = 1003;

    Cache cache;
    String name;

    public ViewDescriptorView(Project project, ItemViewWrapper parent, Cache cache, String name){
        super(project);
        this.cache = cache;
        this.name = name;
        this.parent = parent;
    }

    public DbObject describe(){
        return cache.get(name, DbObject.VIEW);    
    }

    public Object[][] getTabularData() {
        ViewDescriptor dbo = (ViewDescriptor) cache.get(name, DbObject.VIEW);
        List<String[]> out = new ArrayList<String[]>();
        if(dbo != null){
            for(String column: dbo.getColumnNames()){
                String[] params = new String[3];
                ColumnDescriptor cdesc = dbo.getColumnDescriptor(column);
                params[0] = column;
                params[1] = (cdesc.getType()!=null)? cdesc.getType().toString(): "NULL!!!";

                out.add(params);
            }
        }
        return out.toArray(new Object[0][]);
    }

    public Object[] getColumnNames() {
        return new Object[]{"COLUMN NAME", "TYPE"};
    }

    public void render(DefaultTreeCellRenderer renderer) {
        renderer.setText(name);
        renderer.setIcon(Icons.TABLE);
    }

    @NotNull
    public ToggleAction[] getActions() {
        LocalToggleAction open = new LocalToggleAction("Open Definition", "Open Definition", Icons.VIEW_DEF, OPEN, this);
//        LocalToggleAction drop = new LocalToggleAction("Drop", "Drop", Icons.DROP, DROP, this);
        LocalToggleAction query = new LocalToggleAction("Query Data", "Query Data", Icons.QUERY_DATA, QUERY, this);

        return new ToggleAction[]{open, query};
    }

    @NotNull
    public ToggleAction[] getPopupActions() {
        LocalToggleAction open = new LocalToggleAction("Open Definition", "Open Definition", Icons.VIEW_DEF, OPEN, this);
        LocalToggleAction query = new LocalToggleAction("Query Data", "Query Data", Icons.QUERY_DATA, QUERY, this);

        return new ToggleAction[]{open, query};
    }

    public void handle(AnActionEvent event, int command) {
        switch (command) {
            case OPEN: {
                DbObject dbo = cache.get(name, DbObject.VIEW);
                //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());

                boolean result = SqlScriptManager.openFileInEditor(project, dbo);
                break;
            }
            case DROP:
                break;
            case QUERY:
                try {
                    ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
                    QueryResultWindow qrwn = PluginKeys.QR_WINDOW.getData(project);

//                    RowSetModel t = ConnectionManagerImpl.getInstance().getSQLExecutor().executeQuery("SELECT * FROM " + name);
                    RowSetModel t = manager.getSQLExecutor().executeQuery("SELECT * FROM " + name);
                    QueryResultPanel resultPanel = qrwn.createResultPanel(
                            QueryResultPanel.SELECT_RESULT, getTabName(), Icons.VIEW, null /* ToolTip text */
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

    private String getTabName(){
        return name.toUpperCase() + " [VIEW]";
    }

    public void runDefaultAction(){
        DbObject dbo = cache.get(name, DbObject.VIEW);
        //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
        boolean result = SqlScriptManager.openFileInEditor(project, dbo);
    }
}

