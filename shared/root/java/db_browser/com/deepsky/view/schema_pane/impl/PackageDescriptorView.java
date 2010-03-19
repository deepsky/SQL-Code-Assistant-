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

import com.deepsky.database.cache.Cache;
import com.deepsky.database.SqlScriptManager;
import com.deepsky.lang.plsql.struct.PackageDescriptor;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.view.schema_pane.ItemViewWrapper;
import com.deepsky.view.schema_pane.DboDescriptorViewFactory;
import com.deepsky.view.Icons;
import com.deepsky.view.schema_pane.ItemViewListener;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.ide.DataManager;

import javax.swing.tree.DefaultTreeCellRenderer;
import java.util.*;

import org.jetbrains.annotations.NotNull;

public class PackageDescriptorView extends ItemViewWrapperBase implements ToggleActionListener {

    final private int OPEN_SPEC = 1001;
    final private int OPEN_BODY = 1002;

    Cache cache;
    String name;
    List<ItemViewWrapper> out;
    boolean valid;
    Date lastUpdateDate;
    ItemViewWrapper parent;

    boolean packageSpecAvailable = true;
    boolean packageBodyAvailable = true;

    public PackageDescriptorView(Project project, ItemViewWrapper parent, Cache cache, String name) {
        super(project);
        this.cache = cache;

        packageSpecAvailable = cache.get(name, DbObject.PACKAGE) != null;
        packageBodyAvailable = cache.get(name, DbObject.PACKAGE_BODY) != null;

        this.name = name.toLowerCase();
        this.parent = parent;
    }

    public ItemViewWrapper getParent() {
        return parent;
    }

    public List<ItemViewWrapper> getChildren() {
        if (out != null) {
            return out;
        }

        out = new ArrayList<ItemViewWrapper>();

        PackageDescriptor desc = (PackageDescriptor) cache.get(name, DbObject.PACKAGE);
        DbObject[] dbos = desc.getObjects();

        Arrays.sort(dbos, new Comparator<DbObject>(){
            public int compare(DbObject o1, DbObject o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (DbObject dbo : dbos) {
            out.add(DboDescriptorViewFactory.createPackageItemView(this, dbo, cache));
        }

        lastUpdateDate = desc.getLastDDLTime();
        valid = desc.isValid();
        return out;
    }

    public Object[][] getTabularData() {
        List<Object[]> out = new ArrayList<Object[]>();
        Object[] params = new Object[2];
        params[0] = "Package status";
        params[1] = valid ? "VALID" : "NOT VALID";

        out.add(params);

        params = new Object[2];
        params[0] = "Update date";
        params[1] = lastUpdateDate;

        out.add(params);
        return out.toArray(new Object[0][]);
    }

    public Object[] getColumnNames() {
        return new Object[]{"Parameters", "Values"};
    }

    public void render(DefaultTreeCellRenderer renderer) {
        renderer.setText(name);
        renderer.setIcon(Icons.PACKAGE_BODY);
    }

    @NotNull
    public ToggleAction[] getActions() {
        LocalToggleAction openSpec = new LocalToggleAction(
                "Open Specification", "Open", Icons.VIEW_PKG_SPEC, OPEN_SPEC, this, packageSpecAvailable
            );
        LocalToggleAction openBody = new LocalToggleAction(
                "Open Body", "Open", Icons.VIEW_PKG_BODY, OPEN_BODY, this, packageBodyAvailable
            );

        return new ToggleAction[]{openSpec, openBody};
    }


    @NotNull
    public ToggleAction[] getPopupActions() {
        LocalToggleAction openSpec = new LocalToggleAction(
                "Open Specification", "Open", Icons.VIEW_PKG_SPEC, OPEN_SPEC, this, packageSpecAvailable
            );
        LocalToggleAction openBody = new LocalToggleAction(
                "Open Body", "Open", Icons.VIEW_PKG_BODY, OPEN_BODY, this, packageBodyAvailable
            );

        return new ToggleAction[]{openSpec, openBody};
    }

    public void setListener(ItemViewListener listener) {
    }

    public void handle(AnActionEvent event, int command) {
        switch (command) {
            case OPEN_SPEC: {
                DbObject dbo =  cache.get(name, DbObject.PACKAGE);
                //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
                boolean result = SqlScriptManager.openFileInEditor(project, dbo);
                // todo -- analyze the result

                break;
            }
            case OPEN_BODY:{
                DbObject dbo =  cache.get(name, DbObject.PACKAGE_BODY);
                if(dbo != null){
                    //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
                    boolean result = SqlScriptManager.openFileInEditor(project, dbo);
                    // todo -- analyze the result
                } else {
                    // todo ---
                }

                break;
            }
        }
    }

}

