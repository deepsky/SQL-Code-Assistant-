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

package com.deepsky.lang.plsql.objTree.impl;

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.DBException;
import com.deepsky.database.exec.RowSetManager;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.findUsages.SqlFindUsagesHelper;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.objTree.DbElementRoot;
import com.deepsky.lang.plsql.objTree.DbTreeElementAbstract;
import com.deepsky.lang.plsql.objTree.DetailsTableModel;
import com.deepsky.lang.plsql.objTree.TabularTreeElement;
import com.deepsky.lang.plsql.objTree.ui.DbObjectTreeCellRenderer;
import com.deepsky.lang.plsql.resolver.factory.PlSqlElementLocator;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.view.Icons;
import com.deepsky.view.query_pane.QueryResultPanel;
import com.deepsky.view.query_pane.QueryResultWindow;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import javax.swing.table.TableModel;
import java.util.*;


public class ViewTreeElement extends DbTreeElementAbstract implements TabularTreeElement {

    List<ColumnSpecification> columns = new ArrayList<ColumnSpecification>();

    public ViewTreeElement(String ctxPath, String name) {
        super(name);
        this.ctxPath = ctxPath;
    }

    public void render(DbObjectTreeCellRenderer renderer) {
        renderer.setText(name.toUpperCase());
        renderer.setIcon(Icons.VIEW);
    }

    public void addColumn(String path, String name, Type type) {
        columns.add(new ColumnSpecification(path, name, type));
    }

    private class ColumnSpecification {
        public String name;
        public Type type;
        String ctxPath;

        public ColumnSpecification(String path, String name, Type type) {
            this.ctxPath = path;
            this.type = type;
            this.name = name;
        }
    }

    public TableModel getColumnProps() {
        // sort columns
        Collections.sort(columns, new Comparator<ColumnSpecification>() {
            public int compare(ColumnSpecification o1, ColumnSpecification o2) {
                return o1.ctxPath.compareTo(o2.ctxPath);
            }
        });

        // build model
        DetailsTableModel model = new DetailsTableModel(new String[]{"Number", "Column Name", "Type"});
        int i = 1;
        for (ColumnSpecification c : columns) {
            List<String> out = new ArrayList<String>();
            out.add(Integer.toString(i));
            out.add(c.name.toUpperCase());
            if (c.type != null) {
                out.add(c.type.toString());
            } else {

                out.add("");
            }

            //out.add(c.isNull? "Y": "N");
            model.addRow(out);
            i++;
        }

        return model;
    }

    @NotNull
//    public DetailsTableModel getDetailProps(){
    public TableModel getProperties() {
        DetailsTableModel model = new DetailsTableModel(new String[]{"Property", "Value"});

        String filePath = ContextPathUtil.extractFilePath(ctxPath);

        // discover the domain the object is originited from
        if (filePath.startsWith("/") || filePath.matches("^[a-zA-Z]:[\\\\/].*")) {
            // object is defined in the file on FS
            model.addRow(Arrays.asList("Location", filePath));
        }
        model.addRow(Arrays.asList("Timestamp", new Date().toString()));

        return model;
    }


    public AnAction[] getActions() {
        return new AnAction[]{
                new PopupAction("Find Usages", Icons.FIND) {
                    protected void handleSelected(Project project, DbUrl dbUrl, DbElementRoot root) {
                        PsiElement _psi = PlSqlElementLocator.locatePsiElement(project, dbUrl, ctxPath);
                        if (_psi != null) {
                            SqlFindUsagesHelper.runFindUsages(project, _psi);
                        }
                    }
                },
                new PopupAction("Query Data", Icons.QUERY_DATA) {
                    @Override
                    protected void handleSelected(Project project, DbUrl dbUrl, DbElementRoot root) {
                        try {
                            ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
                            QueryResultWindow qrwn = PluginKeys.QR_WINDOW.getData(project);

                            RowSetManager t = manager.getSQLExecutor().executeQuery("SELECT * FROM " + name);
                            QueryResultPanel resultPanel = qrwn.createResultPanel(
                                    QueryResultPanel.SELECT_RESULT, getName(), Icons.TABLE, null /* ToolTip text */
                            );
                            resultPanel.init(t);

                            // show content pane
                            qrwn.showContent(getName());

                        } catch (DBException e) {
                            Messages.showErrorDialog(project, e.getMessage(), "Data load failed");
                        }
                    }
                },
                new PopupAction("Open Script", null) {
                    @Override
                    protected void handleSelected(Project project, DbUrl dbUrl, DbElementRoot root) {
                        boolean result = PlSqlElementLocator.openFileInEditor(project, dbUrl, ctxPath);
                    }
                }
        };
    }
}
