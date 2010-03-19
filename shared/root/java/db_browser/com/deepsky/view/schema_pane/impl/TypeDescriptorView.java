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

import com.deepsky.database.SqlScriptManager;
import com.deepsky.database.cache.Cache;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.view.Icons;
import com.deepsky.view.schema_pane.ItemViewListener;
import com.deepsky.view.schema_pane.ItemViewWrapper;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.DefaultTreeCellRenderer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TypeDescriptorView extends ItemViewWrapperBase implements ToggleActionListener {

    final private int OPEN = 1001;

    String name;
    String baseType = "";
    String typeSpec = "";
    Cache cache;
    String packageName;

    Date lastUpdateDate;

    public TypeDescriptorView(Project project, Cache c0, String name) {
        super(project);
        this.cache = c0;
        this.name = name;
        DbObject dbo = cache.get(name, DbObject.TYPE);
        if(dbo != null){
            this.lastUpdateDate = dbo.getLastDDLTime();

            if (dbo instanceof ObjectTypeDescriptor) {
                typeSpec = "[Object Type]";
            } else if (dbo instanceof RecordTypeDescriptor) {
                typeSpec = "[Record Type]";
            } else if (dbo instanceof TableCollectionDescriptor) {
                typeSpec = "[Collection Type]";
                baseType = ((TableCollectionDescriptor) dbo).getBaseType().toString();
            } else if (dbo instanceof VarrayCollectionDescriptor) {
                typeSpec = "[Varray Type]";
                baseType = ((VarrayCollectionDescriptor) dbo).getBaseType().toString();
            }
        }
    }

    public TypeDescriptorView(Project project, ItemViewWrapper parent, UserDefinedTypeDescriptor dbo, Cache cache) {
        super(project);
        this.name = dbo.getName();
        this.cache = cache;
        this.packageName = dbo.getPackage().getName();
        this.parent = parent;
        this.lastUpdateDate = dbo.getLastDDLTime();

        if (dbo instanceof ObjectTypeDescriptor) {
            typeSpec = "[Object Type]";
        } else if (dbo instanceof RecordTypeDescriptor) {
            typeSpec = "[Record Type]";
        } else if (dbo instanceof TableCollectionDescriptor) {
            typeSpec = "[Collection Type]";
            baseType = ((TableCollectionDescriptor) dbo).getBaseType().toString();
        } else if (dbo instanceof VarrayCollectionDescriptor) {
            typeSpec = "[Varray Type]";
            baseType = ((VarrayCollectionDescriptor) dbo).getBaseType().toString();
        }
    }

    public ItemViewWrapper getParent() {
        return parent;
    }

    public List<ItemViewWrapper> getChildren() {
        return new ArrayList<ItemViewWrapper>();
    }

    public Object[][] getTabularData() {
        List<Object[]> out = new ArrayList<Object[]>();

        Object[] params = new Object[2];
        params[0] = "Type name";
        params[1] = name;
        out.add(params);

        params = new Object[2];
        params[0] = "Update date";
        params[1] = lastUpdateDate == null ? "Not available" : lastUpdateDate;
        out.add(params);

        params = new Object[2];
        params[0] = "Base type";
        params[1] = baseType;
        out.add(params);

        return out.toArray(new Object[out.size()][]);
    }

    public Object[] getColumnNames() {
        return new Object[]{"Parameters", "Values"};
    }

    public void render(DefaultTreeCellRenderer renderer) {
        renderer.setText(name + " " + typeSpec);
        renderer.setIcon(Icons.RECORD_TYPE_DECL);
    }

    @NotNull
    public ToggleAction[] getActions() {
        if (cache != null) {
            // Schema wide type
            LocalToggleAction open = new LocalToggleAction("Open Definition", "Open Definition", Icons.VIEW_DEF, OPEN, this);
            return new ToggleAction[]{open};
        } else {
            // type defined in the package scope
            return new ToggleAction[0];
        }
    }

    public void setListener(ItemViewListener listener) {

    }

    public void handle(AnActionEvent event, int command) {
        switch (command) {
            case OPEN: {
                DbObject dbo = cache.get(name, DbObject.TYPE);
                //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
                boolean result = SqlScriptManager.openFileInEditor(project, dbo);
                break;
            }
        }
    }

    public void runDefaultAction() {
        if (packageName != null) {
            PackageDescriptor pkg = (PackageDescriptor) cache.get(packageName, DbObject.PACKAGE);
            PlSqlObject[] objs = pkg.findObjectByName(name);
            if (objs.length == 1) {
                //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
                boolean result = SqlScriptManager.openFileInEditor(project, objs[0]);
            }
        } else {
            UserDefinedTypeDescriptor udt = (UserDefinedTypeDescriptor) cache.get(name, DbObject.TYPE);
                //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
                boolean result = SqlScriptManager.openFileInEditor(project, udt);
        }
    }

}
