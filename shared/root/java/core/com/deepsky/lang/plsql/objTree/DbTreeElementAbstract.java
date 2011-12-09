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

package com.deepsky.lang.plsql.objTree;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.plsql.objTree.guiSpec.MutableTreeNodeImpl;
import com.deepsky.lang.plsql.objTree.ui.DbObjectTreeCellRenderer;
import com.deepsky.lang.plsql.resolver.factory.PlSqlElementLocator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public abstract class DbTreeElementAbstract extends MutableTreeNodeImpl implements DbTreeElement {

    protected String ctxPath;
    protected String name;

    public DbTreeElementAbstract(String name) {
        this.name = name;
    }

    public DbElementRoot getTypeElement() {

        TreeNode parent = getParent();
        while (parent != null && !(parent instanceof DbElementRoot)) {
            parent = parent.getParent();
        }

        return (DbElementRoot) parent;
    }

    public DbTreeElement[] getChildren() {
        return children.toArray(new DbTreeElement[children.size()]);
    }

    public void add(DbTreeElement elem) {
        children.add(elem);
        elem.setParent(this);
    }

    public TableModel getProperties() {
        return new DetailsTableModel(new String[]{});
    }

    public String getName() {
        return name;
    }

    public abstract void render(DbObjectTreeCellRenderer renderer);

    public String getCtxPath() {
        return ctxPath;
    }

    public void sort(int sortOrder) {
    }

    public void runDefaultAction() {
        // By default: navigate to the element
        DbElementRoot typeElem = getTypeElement();
        Project project = typeElem.getProject();
        DbUrl dbUrl = typeElem.getDbUrl();

        boolean result = PlSqlElementLocator.openFileInEditor(project, dbUrl, ctxPath);
    }

    public AnAction[] getActions() {
        return new AnAction[0];
    }


    public TreePath buildPath(TreePath parent, String ctxPath){
        if(ctxPath != null && ctxPath.startsWith(getCtxPath())){
            return parent.pathByAddingChild(this);
        }
        return parent;
    }

    protected abstract class PopupAction extends ToggleAction {

        public PopupAction(String name, Icon icon) {
            super(name, name, icon);
        }

        public PopupAction(String name, Icon icon, boolean enabled) {
            super(name, name, icon);
            getTemplatePresentation().setEnabled(enabled);
        }

        @Override
        public boolean isSelected(AnActionEvent e) {

            return false;
        }

        @Override
        public void setSelected(AnActionEvent e, boolean state) {
            DbElementRoot rootElem = getTypeElement();
            Project project = rootElem.getProject();
            DbUrl dbUrl = rootElem.getDbUrl();

            handleSelected(project, dbUrl, rootElem);
        }

        protected abstract void handleSelected(Project project, DbUrl dbUrl, DbElementRoot root);
    }

}
