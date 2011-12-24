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

import com.deepsky.actions.SqlScriptRunService;
import com.deepsky.actions.SqlScriptRunner;
import com.deepsky.database.DBException;
import com.deepsky.database.exec.RowSetManager;
import com.deepsky.database.exec.SQLUpdateStatistics;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.findUsages.SqlFindUsagesHelper;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.objTree.DbElementRoot;
import com.deepsky.lang.plsql.objTree.DbTreeElementAbstract;
import com.deepsky.lang.plsql.objTree.DetailsTableModel;
import com.deepsky.lang.plsql.objTree.TabularTreeElement;
import com.deepsky.lang.plsql.objTree.ui.DbObjectTreeCellRenderer;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.resolver.factory.PlSqlElementLocator;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.utils.StringUtils;
import com.deepsky.view.Icons;
import com.deepsky.view.query_pane.QueryResultPanel;
import com.deepsky.view.query_pane.QueryResultWindow;
import com.deepsky.view.query_pane.QueryStatisticsPanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import javax.swing.table.TableModel;
import java.util.*;

public class TableTreeElement extends DbTreeElementAbstract implements TabularTreeElement {
    final int PK_CONSTRAINT = 1;
    final int FK_CONSTRAINT = 2;

    List<ColumnSpecification> columns = new ArrayList<ColumnSpecification>();
    String value;
    List<ConstraintItem> constraints = new ArrayList<ConstraintItem>();
    Set<TriggerSpec> triggers = new HashSet<TriggerSpec>();

    public TableTreeElement(String ctxPath, String name, String value) {
        super(name);
        this.value = value;
        this.ctxPath = ctxPath;

        String[] columns = ContextPathUtil.extractPKColumnsFromTableValue(value);
        if (columns != null) {
            // PK found
            constraints.add(new ConstraintItem(
                    ContextPathUtil.extractPKConstrNameFromTableValue(value),
                    PK_CONSTRAINT,
                    columns,
                    null,
                    null)
            );
        }

        List<ContextPathUtil.FKConstraintSpec> spec = ContextPathUtil.extractFKConstraintsFromTableValue(value);
        if (spec != null && spec.size() > 0) {
            // FK found
            for (ContextPathUtil.FKConstraintSpec fkSpec : spec) {
                constraints.add(new ConstraintItem(
                        fkSpec.getName(),
                        FK_CONSTRAINT,
                        fkSpec.getOwnColumns(),
                        fkSpec.getRefColumns(),
                        fkSpec.getRefTable())
                );
            }
        }
    }

    public void render(DbObjectTreeCellRenderer renderer) {
        renderer.setText(name.toUpperCase());
        renderer.setIcon(Icons.TABLE);
        int tableType = ContextPathUtil.extractTableTypeFromTableValue(value);
        switch (tableType) {
            case TableDefinition.TEMPORARY:
                renderer.setIcon(Icons.TEMP_TABLE);
                break;
            case TableDefinition.EXTERNAL_ORGANIZED:
                renderer.setIcon(Icons.EXT_TABLE);
                break;
            case TableDefinition.IOT_ORGANIZED:
            case TableDefinition.HEAP_ORGANIZED:
            default:
                int partitionType = ContextPathUtil.extractPartitionTypeFromTableValue(value);
                switch (partitionType) {
                    case TableDefinition.PARTITION_BY_HASH:
                    case TableDefinition.PARTITION_BY_RANGE:
                        renderer.setIcon(Icons.PARTI_TABLE);
                        break;
                    default:
                        renderer.setIcon(Icons.TABLE);
                        break;
                }
                break;
        }
    }

/*
    public void runDefaultAction(){
        DbTypeElement typeElem = getTypeElement();
        Project project = typeElem.getProject();
        DbUrl dbUrl = typeElem.getDbUrl();

        boolean result = PlSqlElementLocator.openFileInEditor(project,dbUrl, ctxPath);
    }
*/


    public void addColumn(String path, String name, String value) { //String name, Type type, boolean notNull){
        Type type = ContextPathUtil.decodeColumnTypeFromValue(value);
        boolean isNotNull = ContextPathUtil.decodeColumnNotNull(value);

        // check column constraints
        boolean isPK = ContextPathUtil.extractIsPKFromColumnValue(value);
        if (isPK) {
            // PK found
            constraints.add(new ConstraintItem(
                    ContextPathUtil.extractConstraintNameFromColumnValue(value),
                    PK_CONSTRAINT,
                    new String[]{name},
                    null,
                    null)
            );
        }

        String refColumn = ContextPathUtil.extractRefColumnFromColumnValue(value);
        if (refColumn != null) {
            // FK found
            String refTable = ContextPathUtil.extractRefTableFromColumnValue(value);
            constraints.add(new ConstraintItem(
                    ContextPathUtil.extractConstraintNameFromColumnValue(value),
                    FK_CONSTRAINT,
                    new String[]{name},
                    new String[]{refColumn},
                    refTable)
            );
        }

        columns.add(new ColumnSpecification(path, name, type, isNotNull));
    }

    public String getValue() {
        return value;
    }

    public void addTrigger(String path, String name, String type) {
        triggers.add(new TriggerSpec(path, name, type));
    }


    private class ColumnSpecification {
        public String name;
        public Type type;
        boolean isNotNull;
        String ctxPath;

        public ColumnSpecification(String path, String name, Type type, boolean isNotNull) {
            this.ctxPath = path;
            this.type = type;
            this.name = name;
            this.isNotNull = isNotNull;
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
        DetailsTableModel model = new DetailsTableModel(new String[]{"No", "Column Name", "Type", "NULL"});
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

            out.add(c.isNotNull ? "N" : "Y");
            model.addRow(out);
            i++;
        }

        return model;
    }

    public TableModel getTriggers() {
        // build model
        DetailsTableModel model = new DetailsTableModel(new String[]{"Trigger Name", "Type"});
        for (TriggerSpec c : triggers) {
            List<String> out = new ArrayList<String>();
            out.add(c.name.toUpperCase());
            out.add(c.type.toUpperCase());
            model.addRow(out);
        }

        return model;
    }

    public TableModel getConstraints() {
        // build model
        DetailsTableModel model = new DetailsTableModel(new String[]{
                "Constraint Name",
                "Constraint Type",
                "Own Columns",
                "Referenced Columns",
                "Referenced Table"
        });
        int i = 1;
        for (ConstraintItem c : constraints) {
            List<String> out = new ArrayList<String>();
            out.add(c.constraintName == null ? "" : c.constraintName.toUpperCase());
            out.add(c.constraintType);
            out.add(StringUtils.convert2listStringsWoQuotes(c.ownColumns).toUpperCase());
            out.add(c.refColumns == null ? "" : StringUtils.convert2listStringsWoQuotes(c.refColumns).toUpperCase());
            out.add(c.refTable == null ? "" : c.refTable.toUpperCase());

            model.addRow(out);
            i++;
        }

        return model;
    }


    @NotNull
    public TableModel getProperties() {
        DetailsTableModel model = new DetailsTableModel(new String[]{"Property", "Value"});

        int tableType = ContextPathUtil.extractTableTypeFromTableValue(value);
        switch (tableType) {
            case TableDefinition.TEMPORARY:
                model.addRow(Arrays.asList("TABLE TYPE", "TEMPORARY"));
                break;
            case TableDefinition.EXTERNAL_ORGANIZED:
                model.addRow(Arrays.asList("TABLE TYPE", "EXTERNAL"));
                break;
            case TableDefinition.IOT_ORGANIZED:
                model.addRow(Arrays.asList("TABLE TYPE", "INDEX ORGANIZED"));
                break;
            case TableDefinition.HEAP_ORGANIZED:
                model.addRow(Arrays.asList("TABLE TYPE", "HEAP ORGANIZED"));
                break;
            default:
                model.addRow(Arrays.asList("TABLE TYPE", "REGULAR"));
                break;
        }

        int partitionType = ContextPathUtil.extractPartitionTypeFromTableValue(value);
        switch (partitionType) {
            case TableDefinition.PARTITION_BY_HASH:
                model.addRow(Arrays.asList("PARTITION TYPE", "HASH"));
                break;
            case TableDefinition.PARTITION_BY_RANGE:
                model.addRow(Arrays.asList("PARTITION TYPE", "RANGE"));
                break;
            default:
                model.addRow(Arrays.asList("PARTITION TYPE", "NOT PARTITIONED"));
                break;
        }

        String filePath = ContextPathUtil.extractFilePath(ctxPath);

        // discover the domain the object is originited from
        if (filePath.startsWith("/") || filePath.matches("^[a-zA-Z]:[\\\\/].*")) {
            // object is defined in the file on FS
            model.addRow(Arrays.asList("Location", filePath));
        }
        model.addRow(Arrays.asList("Timestamp", new Date().toString()));

        return model;
    }


    private class ConstraintItem {
        public String constraintName;
        public String constraintType;
        public String[] ownColumns;
        public String[] refColumns;
        public String refTable;

        public ConstraintItem(
                String constraintName,
                int constraintType,
                String[] ownColumns,
                String[] refColumns,
                String refTable) {

            this.constraintName = constraintName;
            this.constraintType = constraintType == PK_CONSTRAINT ? "Primary Key" : "Foreign Key";
            this.ownColumns = ownColumns;
            this.refColumns = refColumns;
            this.refTable = refTable;
        }
    }


    private class TriggerSpec {
        public String name;
        public String ctxPath;
        // BEFORE INSERT OR UPDATE ON ota_dev_grp_mobile_it
        // AFTER INSERT OR UPDATE OR DELETE ON ota_dev_grp_mobile_it
        public String type;

        public TriggerSpec(String ctxPath, String name, String type) {
            this.name = name;
            this.ctxPath = ctxPath;
            this.type = type;
        }

        public int hashCode() {
            return name.hashCode();
        }

        public boolean equals(Object o) {
            return o instanceof TriggerSpec && ((TriggerSpec) o).name.equals(name);
        }
    }


    public MenuItemAction[] getActions() {
        return new MenuItemAction[]{
                new PopupAction("Find Usages", Icons.FIND) {
                    @Override
                    protected void handleSelected(Project project, DbUrl dbUrl, DbElementRoot root) {
                        PsiElement _psi = PlSqlElementLocator.locatePsiElement(project, dbUrl, ctxPath);
                        if (_psi != null) {
                            SqlFindUsagesHelper.runFindUsages(project, _psi);
                        }
                    }
                },
                new PopupActionConnectionSensitive("Query Data", Icons.QUERY_DATA) {
                    @Override
                    protected void handleSelected(final Project project, DbUrl dbUrl, DbElementRoot root) {
                        try {
                            final QueryResultWindow qrwn = PluginKeys.QR_WINDOW.getData(project);
                            SqlScriptRunService.getInstance(project).runQuery(
                                    "SELECT * FROM " + getName(),
                                    new SqlScriptRunner.QueryResultListener() {
                                        public void handleQueryResult(RowSetManager result) {
                                            QueryResultPanel resultPane = qrwn.createResultPanel(
                                                    QueryResultPanel.SELECT_RESULT, getName(), Icons.TABLE, null /* ToolTip text */
                                            );
                                            resultPane.init(result);
                                            // show content pane
                                            qrwn.showContent(getName());
                                        }
                                    });
                        } catch (SqlScriptRunner.AlreadyStartedException e) {
                            Messages.showErrorDialog(
                                    project,
                                    "Cannot run SQL until the SQL statement running at the moment will be finished",
                                    "Data load failed");
                        } catch (DBException e) {
                            Messages.showErrorDialog(project, e.getMessage(), "Data load failed");
                        }
                    }
                },
                new PopupActionConnectionSensitive("Create DDL script", Icons.QUERY_DATA) {
                    @Override
                    protected void handleSelected(Project project, DbUrl dbUrl, DbElementRoot root) {
                        try {
                            final QueryResultWindow qrwn = PluginKeys.QR_WINDOW.getData(project);
                            SqlScriptRunService.getInstance(project).generateDDLScript(
                                    DbObject.TABLE, getName(),
                                    new SqlScriptRunner.DMLResultListener() {
                                        public void handleDMLResult(SQLUpdateStatistics result) {
                                            QueryStatisticsPanel resultPane = qrwn.findOrCreateDMLResultPanel(
                                                    DDL_TAB_NAME, Icons.TABLE, null /* ToolTip text */
                                            );
                                            resultPane.append(result);
                                            // show content pane
                                            qrwn.showContent(DDL_TAB_NAME);
                                        }
                                    });
                        } catch (SqlScriptRunner.AlreadyStartedException e) {
                            Messages.showErrorDialog(
                                    project,
                                    "Cannot run SQL until the SQL statement running at the moment will be finished",
                                    "Data load failed");
                        } catch (DBException e) {
                            Messages.showErrorDialog(project, e.getMessage(), "Data load failed");
                        }
                    }
                },
                new PopupAction("Open autogenerated DDL script", null) {
                    @Override
                    protected void handleSelected(Project project, DbUrl dbUrl, DbElementRoot root) {
                        boolean result = PlSqlElementLocator.openFileInEditor(project, dbUrl, ctxPath);
                    }
                }
        };
    }

}
