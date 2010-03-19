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

import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.view.schema_pane.ItemViewListener;
import com.deepsky.view.schema_pane.ItemViewWrapper;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ItemViewWrapperBase implements ItemViewWrapper {

    List<ItemViewWrapper> children= new ArrayList<ItemViewWrapper>();
    ItemViewWrapper parent;
    ItemViewListener listener;
    Project project;

    public ItemViewWrapperBase(Project project){
        this.project = project;
    }

    public ItemViewWrapperBase(Project project, ItemViewWrapper parent){
        this.project = project;
        this.parent = parent;
    }

    public Project getProject(){
        return project;
    }

    public ItemViewWrapper getParent() {
        return parent;
    }

    public List<ItemViewWrapper> getChildren() {
        return children;
    }

    public Object[][] getTabularData() {
        return new Object[0][];
    }

    public Object[] getColumnNames() {
        return new Object[0];
    }

    @NotNull
    public ToggleAction[] getActions() {
        return new ToggleAction[0];
    }

    @NotNull
    public ToggleAction[] getPopupActions() {
        return new ToggleAction[0];
    }

    public void setListener(ItemViewListener listener) {
        this.listener = listener;
    }

    public int getIndex(ItemViewWrapper node) {
        return children.indexOf(node) ;
    }

    public void remove(int i) {
        children.remove(i);
    }

    public void runDefaultAction(){
    }

    public DbObject describe(){
        return null;
    }

    public JPopupMenu getPopupMenu(){
        return null;
    }
}
