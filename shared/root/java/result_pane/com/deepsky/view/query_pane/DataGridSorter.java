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

package com.deepsky.view.query_pane;

import com.deepsky.database.exec.TableResizeListener;
import com.deepsky.database.exec.RowSetModel;
import com.deepsky.database.DBException;
import com.deepsky.gui.DataGridOrderSettings;
import com.deepsky.view.Icons;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.ui.Messages;
import com.intellij.ide.DataManager;

import javax.swing.table.*;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

public class DataGridSorter extends AbstractTableModel {

    private static final Logger log = Logger.getInstance("#DataGridSorter");

    protected TableModel tableModel;
    TableModelAdapter adapter;
    RowSetModel rowsetModel;

    public static final int DESCENDING = -1;
    public static final int NOT_SORTED = 0;
    public static final int ASCENDING = 1;

    private static Directive EMPTY_DIRECTIVE = new Directive(-1, NOT_SORTED);

    private JTableHeader tableHeader;
    private MouseListener mouseListener;
    private TableModelListener tableModelListener;
    private List sortingColumns = new ArrayList();

    public DataGridSorter(RowSetModel rowsetModel) {
        this.mouseListener = new MouseHandler();
        this.rowsetModel = rowsetModel;
        adapter = new TableModelAdapter(rowsetModel);
        setTableModel(adapter);
    }

//    public DataGridSorter(TableModel tableModel, JTableHeader tableHeader) {
//        this();
//        setTableHeader(tableHeader);
//        setTableModel(tableModel);
//    }

//    private void clearSortingState() {
//        viewToModel = null;
//        modelToView = null;
//    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(TableModel tableModel) {
        if (this.tableModel != null) {
            this.tableModel.removeTableModelListener(tableModelListener);
        }

        this.tableModel = tableModel;
        if (this.tableModel != null) {
            //this.tableModel.addTableModelListener(tableModelListener);
        }

        fireTableStructureChanged();
    }

    public JTableHeader getTableHeader() {
        return tableHeader;
    }


    public void setTableHeader(JTableHeader tableHeader) {
        this.tableHeader = tableHeader;
        this.tableHeader.addMouseListener(mouseListener);

        SortableHeaderRenderer renderer = new SortableHeaderRenderer();
        Enumeration<TableColumn> en =  this.tableHeader.getColumnModel().getColumns();
        while(en.hasMoreElements()){
            TableColumn tc0 = en.nextElement();
            tc0.setHeaderRenderer( renderer );
        }
    }


    public boolean isSorting() {
        return sortingColumns.size() != 0;
    }

    private Directive getDirective(int column) {
        for (int i = 0; i < sortingColumns.size(); i++) {
            Directive directive = (Directive) sortingColumns.get(i);
            if (directive.column == column) {
                return directive;
            }
        }
        return EMPTY_DIRECTIVE;
    }

    public int getSortingStatus(int column) {
        return getDirective(column).direction;
    }

    private void sortingStatusChanged() {
        fireTableDataChanged();
        if (tableHeader != null) {
            tableHeader.repaint();
        }
    }

    public void setSortingStatus(int column, int status) {

        try {
            String st = "";
            if (status == NOT_SORTED) {
                st = "NOT_SORTED";
            } else if (status == DESCENDING) {
                rowsetModel.orderByColumn(column, RowSetModel.DESCENDING, false);
                st = "DESC";
            } else if (status == ASCENDING) {
                rowsetModel.orderByColumn(column, RowSetModel.ASCENDING, false);
                st = "ASC";
            } else {
                st = Integer.toString(status);
            }

            sortingColumns.clear();
//            Directive directive = getDirective(column);
//            if (directive != EMPTY_DIRECTIVE) {
//                sortingColumns.remove(directive);
//            }
            if (status != NOT_SORTED) {
                sortingColumns.add(new Directive(column, status));
            }

            log.info("[setSortingStatus] column: " + column + ", status: " + st);
            sortingStatusChanged();

        } catch (DBException e) {
            Messages.showErrorDialog( e.getMessage(), "Query failed");
            log.warn("[setSortingStatus] could not perform ordering");
        }
    }

    protected Icon getHeaderRendererIcon(int column, int size) {
        Directive directive = getDirective(column);
        if (directive == EMPTY_DIRECTIVE) {
            return null;
        } else if (directive.direction == DESCENDING) {
            return Icons.ARROW_DOWN;
        } else {
            return Icons.ARROW_UP;
        }
    }

    // TableModel interface methods

    public int getRowCount() {
        return (tableModel == null) ? 0 : tableModel.getRowCount();
    }

    public int getColumnCount() {
        return (tableModel == null) ? 0 : tableModel.getColumnCount();
    }

    public String getColumnName(int column) {
        return tableModel.getColumnName(column);
    }

    public Class getColumnClass(int column) {
        return tableModel.getColumnClass(column);
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Object getValueAt(int row, int column) {
        return tableModel.getValueAt(row, column);
    }

    public void setValueAt(Object aValue, int row, int column) {
    }

    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            JTableHeader h = (JTableHeader) e.getSource();
            TableColumnModel columnModel = h.getColumnModel();
            int viewColumn = h.columnAtPoint(e.getPoint());
            int column = columnModel.getColumn(viewColumn).getModelIndex();
            if (column != -1) {
                int status = getSortingStatus(column);
//                if (!e.isControlDown()) {
//                    cancelSorting();
//                }
                // Cycle the sorting states through {NOT_SORTED, ASCENDING, DESCENDING} or
                // {NOT_SORTED, DESCENDING, ASCENDING} depending on whether shift is pressed.

                String columnName = (String) columnModel.getColumn(viewColumn).getHeaderValue();
                int sst =
                        (status == DESCENDING)?
                                DataGridOrderSettings.DESC:
                                ((status == ASCENDING)?DataGridOrderSettings.ASC: DataGridOrderSettings.NOT_SORTED);

                DataGridOrderSettings settings = new DataGridOrderSettings(columnName, sst);
                settings.show();
                if(settings.isOK()){
                    int newStatus;
                    switch(settings.getSortOption()){
                        case DataGridOrderSettings.DESC:
                            newStatus = DESCENDING;
                            break;
                        case DataGridOrderSettings.ASC:
                            newStatus = ASCENDING;
                            break;
                        default:
                            newStatus = NOT_SORTED;
                            break;
                    }

                    if(newStatus == status){
                        // leave sort options as it is
                    } else {
                        setSortingStatus(column, newStatus);
                    }
                }
            }
        }
    }


    private class SortableHeaderRenderer extends DefaultTableHeaderCellRenderer {

        public SortableHeaderRenderer() {
        }

        protected Icon getIcon(JTable table, int column){
            int modelColumn = table.convertColumnIndexToModel(column);
            return getHeaderRendererIcon(modelColumn, -1);
        }
//        public Component getTableCellRendererComponent(JTable table,
//                                                       Object value,
//                                                       boolean isSelected,
//                                                       boolean hasFocus,
//                                                       int row,
//                                                       int column) {
//            Component c = tableCellRenderer.getTableCellRendererComponent(table,
//                    value, isSelected, hasFocus, row, column);
//            if (c instanceof JLabel) {
//                JLabel l = (JLabel) c;
//                l.setHorizontalTextPosition(JLabel.LEFT);
//                int modelColumn = table.convertColumnIndexToModel(column);
//                l.setIcon(getHeaderRendererIcon(modelColumn, l.getFont().getSize()));
//            }
//            return c;
//        }
    }

/*
    private class SortableHeaderRenderer implements TableCellRenderer {
        private TableCellRenderer tableCellRenderer;

        public SortableHeaderRenderer(TableCellRenderer tableCellRenderer) {
            this.tableCellRenderer = tableCellRenderer;
        }

        public Component getTableCellRendererComponent(JTable table,
                                                       Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int row,
                                                       int column) {
            Component c = tableCellRenderer.getTableCellRendererComponent(table,
                    value, isSelected, hasFocus, row, column);
            if (c instanceof JLabel) {
                JLabel l = (JLabel) c;
                l.setHorizontalTextPosition(JLabel.LEFT);
                int modelColumn = table.convertColumnIndexToModel(column);
                l.setIcon(getHeaderRendererIcon(modelColumn, l.getFont().getSize()));
            }
            return c;
        }
    }
*/

    private static class Directive {
        private int column;
        private int direction;

        public Directive(int column, int direction) {
            this.column = column;
            this.direction = direction;
        }
    }

//    public void fireTableRowsInserted(int firstRow, int lastRow){
//
//    }

    class TableModelAdapter implements TableModel, TableResizeListener {

        RowSetModel model;
//        List<TableModelListener> listeners = new ArrayList<TableModelListener>();

        public TableModelAdapter(RowSetModel model) {
            this.model = model;
            this.model.addListener(this);
        }

        public int getRowCount() {
            return model.getFetchedRowCount();
        }

        public int getColumnCount() {
            return model.getColumnCount();
        }

        public String getColumnName(int columnIndex) {
            return model.getColumnName(columnIndex);
        }

        public Class<?> getColumnClass(int columnIndex) {
            return Object.class;
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return model.getValueAt(rowIndex, columnIndex);
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        }

        public void addTableModelListener(TableModelListener l) {
//            listeners.add(l);
        }

        public void removeTableModelListener(TableModelListener l) {
//            listeners.remove(l);
        }

        public void handle(int oldSize, int newSize) {
            TableModelEvent event = null;
            fireTableRowsInserted(oldSize, newSize);
        }
    }


}
