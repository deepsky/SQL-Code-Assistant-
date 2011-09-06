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

package com.deepsky.view.query_pane;

import com.deepsky.database.DBException;
import com.deepsky.database.exec.RecordCache;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.view.query_pane.grid.PopupListener;
import com.intellij.openapi.project.Project;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

public abstract class AbstractDataGrid extends JTable {

    protected Project project;
    protected java.util.List<PopupListener> listeners = new ArrayList<PopupListener>();
    protected RecordCache recordCache;
    private boolean toNumerate = false;

    public AbstractDataGrid(Project project, @NotNull TableModel model, RecordCache recordCache) {
        this.project = project;
        this.recordCache = recordCache;
        toNumerate = PluginKeys.PLUGIN_SETTINGS.getData(project).getShowRowCounting();

        setModel(overrideTableModel(model));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                AbstractDataGrid.this.mousePressed(e);
            }

            public void mouseReleased(MouseEvent e) {
                AbstractDataGrid.this.mouseReleased(e);
            }
        });

//        this.requestFocusInWindow();
//        this.getSelectionModel().setSelectionInterval(0, 1);//setSelectionMode();
    }

    public void addPopupListener(PopupListener l) {
        listeners.add(l);
    }

    public void removePopupListener(PopupListener l) {
        listeners.remove(l);
    }

    public String getDateTimeFormat() {
        String dateFormat = PluginKeys.PLUGIN_SETTINGS.getData(project).getDateFormat();
        if (dateFormat != null) {
            return dateFormat + " " + PluginKeys.PLUGIN_SETTINGS.getData(project).getTimeFormat();
        }
        return null;
    }

    protected JTableHeader createDefaultTableHeader() {
        //Implement table header tool tips.
        return new JTableHeader(columnModel) {
            public String getToolTipText(MouseEvent e) {
//                String tip = null;
                java.awt.Point p = e.getPoint();
                int index = columnModel.getColumnIndexAtX(p.x);
                if(index >= 0){
                    int realIndex = columnModel.getColumn(index).getModelIndex();
                    return columnToolTip(realIndex);
                } else {
                    return null;
                }
            }
        };
    }

    protected String columnToolTip(int columnIndex) {
        if(toNumerate){
            if(columnIndex == 0){
                return null;
            }
            columnIndex -= 1;
//            return recordCache.getColumnSpecification(columnIndex + 2);
        } else {
//            return recordCache.getColumnSpecification(columnIndex + 1);
        }
        return recordCache.getColumnSpecification(columnIndex + 1);
    }

    public void mousePressed(MouseEvent e) {
        // do nothing by default
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            for (PopupListener l : listeners) {
                l.popupFared(e);
            }
        }
    }

    private TableModel overrideTableModel(TableModel model) {
        // intercept call of the  getResolverHelper() method
        InvocationHandler interceptor = new TableModelInterceptor(model);
        return (TableModel) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{TableModel.class},
                interceptor);
    }

    public void sortByColumn(int columnIndex, int dir) throws DBException {
        recordCache.sortByColumn(columnIndex + 1 + (toNumerate ? -1 : 0), dir, false);
    }

    public boolean isColumnSortable(int columnIndex) {
        return !(columnIndex == 0 && toNumerate);
    }

    public boolean isToNumerate() {
        return toNumerate;
    }

    public void setToNumerate(boolean toNumerate) {
        this.toNumerate = toNumerate;
    }


    private class TableModelInterceptor implements InvocationHandler {

        TableModel model;
        public TableModelInterceptor(TableModel model) {
            this.model = model;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("getRowCount")) {
                return getRowCount1();
            } else if (method.getName().equals("getColumnCount")) {
                return getColumnCount1() + (toNumerate? 1: 0);
            } else if (method.getName().equals("getColumnName")) {
                int columnIndex = (Integer) args[0];
                if(toNumerate){
                    if(columnIndex== 0){
                        return "";
                    }
                    columnIndex -= 1;
                }
                return getColumnName1(columnIndex);
            } else if (method.getName().equals("getColumnClass")) {
                int columnIndex = (Integer) args[0];
                if(toNumerate){
                    if(columnIndex== 0){
                        return Color.class;
                    }
                    columnIndex -= 1;
                }
                return getColumnClass1(columnIndex);
            } else if (method.getName().equals("isCellEditable")) {
                int rowIndex = (Integer) args[0];
                int columnIndex = (Integer) args[1];
                if(toNumerate){
                    if(columnIndex== 0){
                        return false;
                    }
                    columnIndex -= 1;
                }

                return isCellEditable1(rowIndex, columnIndex);
            } else if (method.getName().equals("getValueAt")) {
                int rowIndex = (Integer) args[0];
                int columnIndex = (Integer) args[1];
                if(toNumerate){
                    if(columnIndex== 0){
                        return rowIndex;
                    }
                    columnIndex -= 1;
                }

                return getValueAt1(rowIndex, columnIndex);
            } else if (method.getName().equals("setValueAt")) {
                Object aValue = args[0];
                int rowIndex = (Integer) args[1];
                int columnIndex = (Integer) args[2];

                if(toNumerate){
                    if(columnIndex== 0){
                        return null;
                    }
                    columnIndex -= 1;
                }
                setValueAt1(aValue, rowIndex, columnIndex);
                return null;
            } else {
                return method.invoke(model, args);
            }
        }
    }

    // TableModel interface methods
    private int getRowCount1() {
       return recordCache.getFetchedRowCount();
    }

    private int getColumnCount1() {
       return recordCache.getColumnCount();
    }

    private String getColumnName1(int columnIndex) {
       return recordCache.getColumnName(columnIndex + 1);
    }

    private Class getColumnClass1(int columnIndex) {
        return recordCache.getColumnClass(columnIndex + 1);
    }

    private boolean isCellEditable1(int row, int column) {
        return recordCache.isColumnEditable(column + 1);
    }

    private Object getValueAt1(int rowIndex, int columnIndex) {
        try {
            return recordCache.getValueAt(rowIndex, columnIndex + 1);
        } catch (DBException e) {
            return null;
        }
    }

    private void setValueAt1(Object aValue, int row, int column) {
        try {
            recordCache.setValueAt(row, column + 1, aValue);
        } catch (DBException ignored) {
        }
    }
/*

    public abstract int getRowCount();
    public abstract int getColumnCount();
    public abstract String getColumnName(int columnIndex);
    public abstract Class getColumnClass(int columnIndex);
    public abstract boolean isCellEditable(int row, int column);

    public abstract Object getValueAt(int rowIndex, int columnIndex);
    public abstract void setValueAt(Object aValue, int row, int column);
*/



//    public abstract boolean isEditable();

    public abstract boolean isFirst();

    public abstract boolean isLast();

    public abstract void prev();

    public abstract void next();

    public abstract void first();

    public abstract void last();

    public abstract void insertRow();

    public abstract void deleteRow();

    public abstract void submitUpdate() throws DBException;

    public abstract boolean changesNotPosted();
}
