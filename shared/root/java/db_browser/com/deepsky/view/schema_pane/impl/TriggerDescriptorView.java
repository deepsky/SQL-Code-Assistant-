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

import com.deepsky.database.SqlScriptManager;
import com.deepsky.database.cache.Cache;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.TriggerDescriptor;
import com.deepsky.view.Icons;
import com.deepsky.view.schema_pane.ItemViewWrapper;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.DefaultTreeCellRenderer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TriggerDescriptorView extends ItemViewWrapperBase implements ItemViewWrapper, ToggleActionListener {


    final private int OPEN = 1001;
    final private int DROP = 1002;
    final private int DISABLE = 1003;
    final private int ENABLE = 1004;

    Cache cache;
    String name;

    public TriggerDescriptorView(ItemViewWrapper parent, Cache cache, String name) {
        this.cache = cache;
        this.name = name;
        this.parent = parent;
    }

    public Object[][] getTabularData() {
        TriggerDescriptor dbo = (TriggerDescriptor) cache.get(name, DbObject.TRIGGER);
        Date lastUpdateDate = dbo.getLastDDLTime();

        List<Object[]> out = new ArrayList<Object[]>();

        String table = dbo.getTableName();
        if (table != null && table.length() > 0) {
            Object[] params = new Object[2];
            params[0] = "Table";
            params[1] = table.toUpperCase();
            out.add(params);
        }

        Object[] params = new Object[2];
        params[0] = "Update date";
        params[1] = lastUpdateDate == null ? "Not available" : lastUpdateDate;
        out.add(params);

        return out.toArray(new Object[out.size()][]);
    }

    public Object[] getColumnNames() {
        return new Object[]{"Parameters", "Values"};
    }

    public void render(DefaultTreeCellRenderer renderer) {
        renderer.setText(name);
        renderer.setIcon(Icons.TRIGGER);
    }

    @NotNull
    public ToggleAction[] getActions() {
        LocalToggleAction open = new LocalToggleAction("Open Definition", "Open Definition", Icons.VIEW_DEF, OPEN, this);
//        LocalToggleAction drop = new LocalToggleAction("Drop", "Drop", Icons.DROP, DROP, this);
//        LocalToggleAction enable = new LocalToggleAction("Enable", "Enable", Icons.ENABLE_TRIGGER, ENABLE, this);
//        LocalToggleAction disable = new LocalToggleAction("Disable", "Disable", Icons.DISABLE_TRIGGER, DISABLE, this);

        return new ToggleAction[]{open}; //, drop, enable, disable};
    }

    public void handle(int command) {
        switch (command) {
            case OPEN: {
                DbObject dbo = cache.get(name, DbObject.TRIGGER);
                Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());

                boolean result = SqlScriptManager.openFileInEditor(project, dbo);
                break;
            }
//            case DROP:
//                try {
//                    ConnectionManagerImpl.getInstance().getSQLExecutor().dropTrigger(name);
//                } catch (DBException e) {
//                    Project project = DataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
//                    Messages.showErrorDialog(project, e.getMessage(), "Drop failed");
//                }
//                break;
//            case ENABLE:
//                try {
//                    ConnectionManagerImpl.getInstance().getSQLExecutor().enableTrigger(name);
//                } catch (DBException e) {
//                    Project project = DataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
//                    Messages.showErrorDialog(project, e.getMessage(), "Drop failed");
//                }
//                break;
//            case DISABLE:
//                try {
//                    ConnectionManagerImpl.getInstance().getSQLExecutor().disableTrigger(name);
//                } catch (DBException e) {
//                    Project project = DataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
//                    Messages.showErrorDialog(project, e.getMessage(), "Drop failed");
//                }
//                break;
        }
    }

    public void runDefaultAction() {
        DbObject dbo = cache.get(name, DbObject.TRIGGER);
        Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
        boolean result = SqlScriptManager.openFileInEditor(project, dbo);
    }

}

