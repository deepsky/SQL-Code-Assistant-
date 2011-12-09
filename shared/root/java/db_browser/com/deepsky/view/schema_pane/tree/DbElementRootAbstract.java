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

import com.deepsky.lang.plsql.objTree.DbElementRoot;
import com.deepsky.lang.plsql.objTree.DbTreeElement;
import com.deepsky.lang.plsql.objTree.DbTypeElementAction;
import com.deepsky.lang.plsql.objTree.guiSpec.MutableTreeNodeImpl;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.DbObject;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public abstract class DbElementRootAbstract extends MutableTreeNodeImpl implements DbElementRoot {

    public static int SORT_ITEMS      = 10;
    public static int HIDE_PACKAGE_SCOPE_ITEMS  = 11;
    public static int CREATE_SCRIPT   = 101;
    public static int QUERY_TABLE     = 102;
    public static int OPEN_PACKAGE_SPEC = 110;
    public static int OPEN_PACKAGE_BODY = 111;


    String name;

    public DbElementRootAbstract(String name) {
        this.name = name;
    }

    /**
     * For testing only
     */
    protected DbElementRootAbstract() {
    }

    public void render(DefaultTreeCellRenderer renderer) {
        renderer.setName(name);
    }

    public void sort(int sortOrder) {
        // sort by name
        Collections.sort(children, new Comparator<DbTreeElement>() {
            public int compare(DbTreeElement o1, DbTreeElement o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (DbTreeElement e : children) {
            e.sort(sortOrder);
        }
    }

/*
    @NotNull
    public AnAction[] getActions() {
        return new ToggleAction[0];
    }
*/

    @NotNull
    public DbTypeElementAction[] getActions() {
        return new DbTypeElementAction[0];
    }

    protected DbTypeElementAction createAction(String actionName, String toolTip, Icon icon, int command){
        return new DbTypeElementActionImpl(actionName, toolTip, icon, command);
    }

    protected DbTypeElementAction createAction(){
        return new DbTypeElementActionImpl("SEPARATOR", null, null, -1); 
    }

    private class DbTypeElementActionImpl implements DbTypeElementAction {
        String actionName;
        String toolTip;
        Icon icon;
        int command;
        public DbTypeElementActionImpl(String actionName, String toolTip, Icon icon, int command) {
            this.actionName = actionName;
            this.toolTip = toolTip;
            this.icon = icon;
            this.command = command;
        }

        public int getCommand() {
            return command;
        }

        public Icon getIcon() {
            return icon;
        }

        public String getName() {
            return actionName;
        }

        public String getTooltip() {
            return toolTip;
        }

        public void setSelected(int command, String selectedCtxPath) {
            handleSetSelectedFromAction(command, selectedCtxPath);
        }

        public boolean isSelected(int command) {
            return isSelectedFromAction(command);
        }
    }


    protected void handleSetSelectedFromAction(int command, String selectedCtxPath){
        // do nothing by default
    }

    protected boolean isSelectedFromAction(int command){
        return false;
    }

    public String getName() {
        return name;
    }

    public List<DbTreeElement> getChildren() {
        return children;
    }

    public Iterator<DbTreeElement> childrenIterator(){
        return children.iterator();    
    }

    public void add(DbTreeElement e) {
        children.add(e);
        e.setParent(this);
    }

    public void completeBuild() {
        // sort by name
        Collections.sort(children, new Comparator<DbTreeElement>() {
            public int compare(DbTreeElement o1, DbTreeElement o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (DbTreeElement e : children) {
            e.sort(DbTreeElement.SORT_NATIVE_ORDER);
        }
    }

    public TreePath getTreePathFor(String ctxPath){
        int objectType = ContextPathUtil.extractTopObjectType(ctxPath);
        String rootName = ContextPathUtil.ctxType2dbType(objectType);

        if(getName().equalsIgnoreCase(rootName)){
            String objectName = ContextPathUtil.extractTopObjectName(ctxPath);
            // todo -- do search in deep
            for(DbTreeElement e: getChildren()){
                if(e.getName().equalsIgnoreCase(objectName)){
                    String elemCtxPath = e.getCtxPath();
                    if(elemCtxPath != null && ctxPath.startsWith(elemCtxPath)){
                        return new TreePath(new Object[]{this, e});
                    }
                }
            }
        }
        return null;
    }

}
