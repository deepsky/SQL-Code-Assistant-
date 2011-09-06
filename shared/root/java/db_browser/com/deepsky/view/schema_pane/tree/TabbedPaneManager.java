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
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.indexMan.IndexBulkChangeListener;
import com.deepsky.lang.plsql.objTree.*;
import com.deepsky.lang.plsql.objTree.impl.PackageTreeElement;
import com.deepsky.lang.plsql.objTree.ui.DbObjectTreeCellRenderer;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.deepsky.view.Icons;
import com.deepsky.view.schema_pane.ui.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.util.*;

public class TabbedPaneManager implements Test501Listener {

    IndexManager indexManager;
    boolean updatePended = false;
    Project project;

    int splitDividerLocation;
    Map<String, TabbedPaneWrapper> url2Pane = new HashMap<String, TabbedPaneWrapper>();

    public TabbedPaneManager(Project project) {
        this.project = project;
        this.indexManager = PluginKeys.SQL_INDEX_MAN.getData(project);

//        MessageBus bus1 = project.getMessageBus();
//        SqlCodeAssistantSettings settings = PluginKeys.PLUGIN_SETTINGS.getData(project);
        final SqlCodeAssistantSettings settings = SqlCodeAssistantSettings.getInstance(project);
        splitDividerLocation = settings.getDbBrowserSplitDividerLocation();

        if (splitDividerLocation == 0) {
            // initial size
            splitDividerLocation = 40;
        }

/*
        IndexBulkChangeListener ll = new IndexBulkChangeListener() {
            public void handleUpdate(final DbUrl source, final String[] types) {
            }
        };
*/

        // listen for index updates
/*
        IndexBulkChangeListener ll2 = new IndexBulkChangeListener() {
            public void handleUpdate(final DbUrl source, final String[] types) {
                TabbedPaneWrapper wrapper = url2Pane.get(source.getUserHostPortServiceName());
                if (wrapper != null) {
                    // if the panel is currently visible invoke refresh of it
                    // otherwise save changes till having it visible
//                    if(wrapper.pane.isVisible()) {
                    ApplicationManager.getApplication().invokeLater(new Runnable() {
                        public void run() {
                            TabbedPaneWrapper wrapper = url2Pane.get(source.getUserHostPortServiceName());

                            DbElementRoot[] dbTypes = createDbSchemaTree(types, source);
                            int selectedTab = -1;
                            TreePath selectedPath = null;

                            for (DbElementRoot elem : dbTypes) {
                                for (int i = 0; i < wrapper.tabbed.getTabCount(); i++) {
                                    String title = wrapper.tabbed.getTitleAt(i);
                                    JComponent c = (JComponent) wrapper.tabbed.getComponentAt(i);
                                    if (selectedTab == -1 && c.isVisible()) {
// todo -- selection of the item in the tree
//                                            JTree tree = test501.getMainTree();
//                                            selectedPath = tree.getSelectionModel().getSelectionPath();
                                        selectedTab = i;
                                    }
                                    if (elem.getName().equalsIgnoreCase(title)) {
                                        // replace old tab with new one
                                        TabFormBase test501 = (TabFormBase) c.getClientProperty(TabFormBase.TEST501_COMPONENT);
                                        test501.removeListener(TabbedPaneManager.this);
                                        wrapper.tabbed.remove(c);

                                        JComponent panel = createTab(elem, splitDividerLocation);
                                        wrapper.tabbed.insertTab(elem.getName(), null, panel, null, i);
                                        break;
                                    }
                                }
                            }

                            wrapper.tabbed.setSelectedIndex(selectedTab);
                            wrapper.tabbed.validate();
                        }
                    });
//                    } else {
//                        // just save changes
//                        wrapper.addChanges(types);
//                        updatePended = true;
//                    }
                }
                System.out.println("Types updated: " + Arrays.toString(types) + " Source: " + source.getUserHostPortServiceName());
            }
        };
*/

//        bus1.connect().subscribe(IndexBulkChangeListener.TOPIC, ll);
    }


    public void refreshSchemaTree(final DbUrl source, final String[] types) {
        // if the panel is currently visible invoke refresh of it
        // otherwise save changes till having it visible
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                TabbedPaneWrapper wrapper = url2Pane.get(source.getUserHostPortServiceName());
                if (wrapper != null) {
                    DbElementRoot[] dbTypes = createDbSchemaTree(types, source);
                    int selectedTab = -1;
                    TreePath selectedPath = null;

                    for (DbElementRoot elem : dbTypes) {
                        for (int i = 0; i < wrapper.tabbed.getTabCount(); i++) {
                            String title = wrapper.tabbed.getTitleAt(i);
                            JComponent c = (JComponent) wrapper.tabbed.getComponentAt(i);
                            if (selectedTab == -1 && c.isVisible()) {
// todo -- selection of the item in the tree
//                                            JTree tree = test501.getMainTree();
//                                            selectedPath = tree.getSelectionModel().getSelectionPath();
                                selectedTab = i;
                            }
                            if (elem.getName().equalsIgnoreCase(title)) {
                                // replace old tab with new one
                                TabFormBase test501 = (TabFormBase) c.getClientProperty(TabFormBase.TEST501_COMPONENT);
                                test501.removeListener(TabbedPaneManager.this);
                                wrapper.tabbed.remove(c);

                                JComponent panel = createTab(elem, splitDividerLocation);
                                wrapper.tabbed.insertTab(elem.getName(), null, panel, null, i);
                                break;
                            }
                        }
                    }

                    wrapper.tabbed.setSelectedIndex(selectedTab);
                    wrapper.tabbed.validate();
                }
                System.out.println("Types updated: " + Arrays.toString(types) + " Source: " + source.getUserHostPortServiceName());
            }
        });
    }

    public JTabbedPane selectSheet(DbUrl dbUrl) {

        TabbedPaneWrapper wrapper = url2Pane.get(dbUrl.getUserHostPortServiceName());
        if (wrapper == null) {
            // create Tab and populate it with objects
            JTabbedPane tabbed = new JTabbedPane();
            tabbed.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
            wrapper = new TabbedPaneWrapper(tabbed);

            // build database object hierarchy
            DbElementRoot[] dbTypes = createDbSchemaTree(null, dbUrl);

            // populate Tab panels with the database objects
            for (DbElementRoot el : dbTypes) {
                JComponent panel = createTab(el, splitDividerLocation);
                tabbed.addTab(el.getName(), panel);
            }

            url2Pane.put(dbUrl.getUserHostPortServiceName(), wrapper);
        }

        return wrapper.tabbed;
    }


    public void removeSheet(DbUrl dbUrl) {
        // dispose listeners
        TabbedPaneWrapper wrapper = url2Pane.remove(dbUrl.getUserHostPortServiceName());
        if (wrapper != null) {
            for (int i = 0; i < wrapper.tabbed.getTabCount(); i++) {
                JComponent c = (JComponent) wrapper.tabbed.getComponentAt(i);
                TabFormBase test501 = (TabFormBase) c.getClientProperty(TabFormBase.TEST501_COMPONENT);
                test501.removeListener(this);
            }
        }
    }


    private class TabbedPaneWrapper {
        public JTabbedPane tabbed;
        public boolean isUpdatePended = false;
        Set<String> changes = new HashSet<String>();

        public TabbedPaneWrapper(JTabbedPane pane) {
            this.tabbed = pane;
        }

        public synchronized String[] takeChanges() {
            String[] out = changes.toArray(new String[changes.size()]);
            changes.clear();
            return out;
        }

        public synchronized void addChanges(String[] types) {
            changes.addAll(Arrays.asList(types));
        }
    }


    public void dividerLoactionChanged(JComponent source, int newLocation) {

        for (TabbedPaneWrapper wrapper : url2Pane.values()) {
            JTabbedPane tabbed = wrapper.tabbed;
            int selectedIndex = tabbed.indexOfComponent(source);
            for (int i = 0; i < tabbed.getTabCount(); i++) {
                if (i != selectedIndex) {
                    JComponent c = (JComponent) tabbed.getComponentAt(i);

                    ((TabFormBase) c.getClientProperty(TabFormBase.TEST501_COMPONENT)).setDividerLocation(newLocation);
                }
            }
        }

        splitDividerLocation = newLocation;
//        SqlCodeAssistantSettings settings = PluginKeys.PLUGIN_SETTINGS.getData(project);
        final SqlCodeAssistantSettings settings = SqlCodeAssistantSettings.getInstance(project);
        settings.setDbBrowserSplitDividerLocation(newLocation);
    }

    private JComponent createTab(DbElementRoot el, int divider) {
        if (el instanceof TableTypeRoot) {
            TableTabForm form = new TableTabForm((TableTypeRoot) el);
            if (divider != -1) {
                form.setDividerLocation(divider);
            }
            form.addListener(this);
            return form.getRootPanel();
        } else if (el instanceof ViewTypeRoot) {
            ViewTabForm form = new ViewTabForm((ViewTypeRoot) el);
            if (divider != -1) {
                form.setDividerLocation(divider);
            }
            form.addListener(this);
            return form.getRootPanel();
        } else if (el instanceof PackageTypeRoot) {
            PackageTabForm form = new PackageTabForm((PackageTypeRoot) el);
            if (divider != -1) {
                form.setDividerLocation(divider);
            }
            form.addListener(this);
            return form.getRootPanel();
        } else if (el instanceof TypeTypeRoot) {
            TypeTabForm form = new TypeTabForm((TypeTypeRoot) el);
            if (divider != -1) {
                form.setDividerLocation(divider);
            }
            form.addListener(this);
            return form.getRootPanel();
        } else {
            GenericTabForm test501 = new GenericTabForm(el);
            if (divider != -1) {
                test501.setDividerLocation(divider);
            }
            test501.addListener(this);
            return test501.getRootPanel();
        }
    }


    private DbElementRoot[] createDbSchemaTree(String[] types, final DbUrl dbUrl) {
        DbTypeElementFactory factory = new DbTypeElementFactory() {
            public DbElementRoot create(String type) {
                if (type.equals(DbObject.TABLE)) {
                    return new TableTypeElementImpl(project, dbUrl, type);
                } else if (type.equals(DbObject.VIEW)) {
                    return new ViewTypeElementImpl(project, dbUrl, type);
                } else if (type.equals(DbObject.PACKAGE)) {
                    return new PackageTypeElementImpl(project, dbUrl, type);
                } else if (type.equals(DbObject.TYPE)) {
                    return new DbTypeElementImpl(project, dbUrl, type);
                } else {
                    return new DbTypeElementImpl(project, dbUrl, type);
                }
            }
        };

        SchemaTreeBuilder bld = types == null ?
                new SchemaTreeBuilderImpl(factory) :
                new SchemaTreeBuilderImpl(types, factory);
        indexManager.populateSchemaTree(dbUrl, bld);
        return bld.getTreeBuilt();
    }


    private class DbTypeElementImpl extends DbElementRootAbstract {
        Project project;
        DbUrl dbUrl;

        public DbTypeElementImpl(Project project, DbUrl dbUrl, String name) {
            super(name);
            this.project = project;
            this.dbUrl = dbUrl;
        }

        public Project getProject() {
            return project;
        }

        public DbUrl getDbUrl() {
            return dbUrl;
        }
    }


    private class TableTypeElementImpl extends DbTypeElementImpl implements TableTypeRoot {

        public TableTypeElementImpl(Project project, DbUrl dbUrl, String name) {
            super(project, dbUrl, name);
        }

        @NotNull
        public DbTypeElementAction[] getActions() {
            DbTypeElementAction open = createAction("Create Script", "Create Script", Icons.VIEW_DEF, CREATE_SCRIPT);
            DbTypeElementAction queryData = createAction("Query Data", "Query Data", Icons.QUERY_DATA, QUERY_TABLE);

            return new DbTypeElementAction[]{open, queryData};
        }
/*
        @NotNull
        public AnAction[] getActions() {
            ToggleAction open = new TabFormBase.LocalToggleAction("Create Script", "Create Script", Icons.VIEW_DEF, CREATE_SCRIPT);
            ToggleAction queryData = new TabFormBase.LocalToggleAction("Query Data", "Query Data", Icons.QUERY_DATA, QUERY_TABLE);

            return new ToggleAction[]{open, queryData};
        }
*/
    }


    private class ViewTypeElementImpl extends DbTypeElementImpl implements ViewTypeRoot {

        public ViewTypeElementImpl(Project project, DbUrl dbUrl, String name) {
            super(project, dbUrl, name);
        }

        @NotNull
        public DbTypeElementAction[] getActions() {
            DbTypeElementAction open = createAction("Create Script", "Create Script", Icons.VIEW_DEF, CREATE_SCRIPT);
            DbTypeElementAction queryData = createAction("Query Data", "Query Data", Icons.QUERY_DATA, QUERY_TABLE);

            return new DbTypeElementAction[]{open, queryData};
        }
/*
        @NotNull
        public AnAction[] getActions() {
            ToggleAction open = new TabFormBase.LocalToggleAction("Create Script", "Create Script", Icons.VIEW_DEF, CREATE_SCRIPT);
            ToggleAction queryData = new TabFormBase.LocalToggleAction("Query Data", "Query Data", Icons.QUERY_DATA, QUERY_TABLE);

            return new ToggleAction[]{open, queryData};
        }
*/
    }

    private class PackageTypeElementImpl extends DbTypeElementImpl implements PackageTypeRoot {

        public PackageTypeElementImpl(Project project, DbUrl dbUrl, String name) {
            super(project, dbUrl, name);
        }

        @NotNull
        public DbTypeElementAction[] getActions() {
            DbTypeElementAction sort = createAction("Sort Alphabetically", "Sort Alphabetically", Icons.SORT_BY_NAME, SORT_ITEMS);
            DbTypeElementAction filter = createAction("Hide package scope objects", "Hide package scope objects", Icons.HIDE_PKG_BODY_OBJS, HIDE_PACKAGE_SCOPE_ITEMS);
//            DbTypeElementAction openSpec = createAction("Open Specification", "Open", Icons.VIEW_PKG_SPEC, OPEN_PACKAGE_SPEC);
//            DbTypeElementAction opneBody = createAction("Open Body", "Open", Icons.VIEW_PKG_BODY, OPEN_PACKAGE_BODY);
//            DbTypeElementAction separator = createAction();

            return new DbTypeElementAction[]{sort, filter}; //separator, openSpec, opneBody};
        }

        boolean isSortedAlphabetically = false;
        boolean isHide = false;
        Set<ModelChangeListener> listeners = new HashSet<ModelChangeListener>();

        protected void handleSetSelectedFromAction(int command, String selectedCtxPath) {
            if (command == SORT_ITEMS) {
                isSortedAlphabetically = !isSortedAlphabetically;
                sort(isSortedAlphabetically ? DbTreeElement.SORT_ALPHABETICALLY : DbTreeElement.SORT_NATIVE_ORDER);
                for (ModelChangeListener l : listeners) {
                    l.modelChanged();
                }
            } else if (command == HIDE_PACKAGE_SCOPE_ITEMS) {
                isHide = !isHide;
                filter(isHide);
                for (ModelChangeListener l : listeners) {
                    l.modelChanged();
                }
            }
        }

        public boolean isSelectedFromAction(int command) {
            if (command == SORT_ITEMS) {
                return isSortedAlphabetically;
            } else if (command == HIDE_PACKAGE_SCOPE_ITEMS) {
                return isHide;
            } else {
                return super.isSelectedFromAction(command);
            }
        }

        public void sort(int sortOrder) {
            for (DbTreeElement e : children) {
                PackageGroupElement pGr = (PackageGroupElement) e;
                pGr.sort(sortOrder);
            }
        }

        public void filter(boolean hide) {
            for (DbTreeElement e : children) {
                PackageGroupElement pGr = (PackageGroupElement) e;
                pGr.filter(hide);
            }
        }

        public void addListener(ModelChangeListener listener) {
            listeners.add(listener);
        }

        public void removeListener(ModelChangeListener listener) {
            listeners.remove(listener);
        }

        private void add1(DbTreeElement e) {
            for (DbTreeElement e0 : children) {
                PackageGroupElement pGr = (PackageGroupElement) e0;
                if (pGr.getName().equalsIgnoreCase(e.getName())) {
                    pGr.add(e);
                    return;
                }
            }

            PackageGroupElement pGr = new PackageGroupElement(e.getName());
            children.add(pGr);
            pGr.setParent(this);

            pGr.add(e);
        }

        public void completeBuild() {
            List<DbTreeElement> temp = new ArrayList<DbTreeElement>();
            temp.addAll(children);
            children.clear();
            for (DbTreeElement e : temp) {
                add1(e);
            }

            // sort by name pkg wrappers by name
            Collections.sort(children, new Comparator<DbTreeElement>() {
                public int compare(DbTreeElement o1, DbTreeElement o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            // sort package content in native order
            for (DbTreeElement e : children) {
                PackageGroupElement pGr = (PackageGroupElement) e;
                pGr.sort(DbTreeElement.SORT_NATIVE_ORDER);
//                e.sort(DbTreeElement.SORT_NATIVE_ORDER);
            }

        }

        private class PackageGroupElement extends DbTreeElementAbstract {
            public PackageGroupElement(String name) {
                super(name);
            }

            @Override
            public void render(DbObjectTreeCellRenderer renderer) {
                renderer.setIcon(Icons.PKG_SPEC_BODY);
                renderer.setText(name);
                renderer.setIsValid(true);
            }

            public void runDefaultAction() {
                // By default: do nothing
            }

            public void sort(int sortOrder) {
                for (DbTreeElement e : children) {
                    e.sort(sortOrder);
                }
            }

            public void filter(boolean hide) {
                for (DbTreeElement e : children) {
                    PackageTreeElement pkg = (PackageTreeElement) e;
                    pkg.filterPackageScopeItems(hide);
                }
            }
        }
    }

}
