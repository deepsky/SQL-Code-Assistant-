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

package com.deepsky.view.query_pane.grid;

import com.deepsky.database.exec.RecordCache;
import com.deepsky.database.ora.types.LONGRAWType;
import com.deepsky.database.ora.types.RAWType;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.view.query_pane.*;
import com.deepsky.view.query_pane.grid.renders.*;
import com.deepsky.view.query_pane.ui.TextEditorDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import oracle.sql.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ImmutableGrid extends AbstractDataGrid {

    public ImmutableGrid(final Project project, TableModel model, RecordCache recordCache) {
        super(project, model, recordCache);

        Font font = PluginKeys.PLUGIN_SETTINGS.getData(project).getGridFont();

        setDefaultRenderer(Color.class, new ColumnNumberRenderer(true));
        setDefaultRenderer(BigDecimal.class, new NumberRenderer(font));
//        _table.setDefaultRenderer(java.sql.Time.class, new DateRenderer());
        setDefaultRenderer(TIMESTAMP.class, new TimestampRenderer(font, PluginKeys.TS_CONVERTOR.getData(project)));
        setDefaultRenderer(TIMESTAMPLTZ.class, new TimestampRenderer(font, PluginKeys.TS_CONVERTOR.getData(project)));
        setDefaultRenderer(TIMESTAMPTZ.class, new TimestampRenderer(font, PluginKeys.TSTZ_CONVERTOR.getData(project)));

        DateRenderer dateRenderer = new DateRenderer(font) {
            public String getFormat() {
                return getDateTimeFormat();
            }
        };
        setDefaultRenderer(java.util.Date.class, dateRenderer);
        setDefaultRenderer(java.sql.Date.class, dateRenderer);
        setDefaultRenderer(Timestamp.class, dateRenderer);

        setDefaultRenderer(RAWType.class, new RAWTypeRenderer(font));
        setDefaultRenderer(LONGRAWType.class, new LONGRAWTypeRenderer(font));
        setDefaultRenderer(CLOB.class, new CLOBTypeRenderer());
        setDefaultRenderer(BLOB.class, new BLOBTypeRenderer());
        setDefaultRenderer(BFILE.class, new BFILETypeRenderer());

//        _table.getInputMap().put(copy, this);
//        addHotKey("copy", getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK), new CopyAction());

//        mm.setTableHeader(_table.getTableHeader());
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //_table.getSelectionModel().setSelectionMode(0);
        getSelectionModel().addListSelectionListener(new RowListener());
        getColumnModel().getSelectionModel().addListSelectionListener(new ColumnListener());

//        setColumnSelectionAllowed(true);
//        setCellSelectionEnabled(true);
        setRowSelectionAllowed(true);
        setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    }

    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2) {
            // double click, open dialog
            int columnIndex = getSelectedColumn();
            int rowIndex = getSelectedRow();

            Class columnClazz = getModel().getColumnClass(columnIndex);
            Object value = getModel().getValueAt(rowIndex, columnIndex);
            DataAccessor accessor = DataAccessorFactory.createReadOnly(project, columnClazz, value);
//            Object value = getModel().getValueAt(rowIndex, columnIndex);
//            accessor.setValue(value);
            if (accessor != null) {
                try {
                    if (accessor.size() > 1000000) {
                        // todo -- prompt user for approval to load
                    }
                    String columnName = getColumnName(columnIndex);
                    TextEditorDialog dialog = new TextEditorDialog(project, columnName, accessor);
                    dialog.show();
                } catch (SQLException e1) {
                    Messages.showErrorDialog(project, e1.getMessage(), "Reading column value failed");
                }
            }
        }
    }

    private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
        }
    }

    private class ColumnListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
        }
    }


    @Override
    public boolean isFirst() {
        // todo -- implement me
        return false;
    }

    @Override
    public boolean isLast() {
        // todo -- implement me
        return false;
    }

    @Override
    public void prev() {
        // todo -- implement me
    }

    @Override
    public void next() {
        // todo -- implement me
    }

    @Override
    public void first() {
        // todo -- implement me
    }

    @Override
    public void last() {
        // todo -- implement me
    }

    @Override
    public void insertRow() {
        // not supported by immutable record set
    }

    @Override
    public void deleteRow() {
        // not supported by immutable record set
    }

    @Override
    public void submitUpdate() {
        // not supported by immutable record set
    }

    @Override
    public boolean changesNotPosted() {
        // not editable by default
        return false;
    }

}
