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

import com.deepsky.database.DBException;
import com.deepsky.database.exec.UpdatableRecordCache;
import com.deepsky.database.ora.types.LONGRAWType;
import com.deepsky.database.ora.types.RAWType;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.deepsky.view.query_pane.*;
import com.deepsky.view.query_pane.converters.ConversionUtil;
import com.deepsky.view.query_pane.converters.RAWType_Convertor;
import com.deepsky.view.query_pane.grid.editors.*;
import com.deepsky.view.query_pane.grid.renders.*;
import com.deepsky.view.query_pane.ui.BinaryEditorDialog;
import com.deepsky.view.query_pane.ui.TextEditorDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import oracle.sql.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

public class EditableGrid extends AbstractDataGrid {

    UpdatableRecordCache rowset;

    int lastEditRow = -1;

    public EditableGrid(final Project project, TableModel model, final UpdatableRecordCache rowset) {
        super(project, model, rowset);
        this.rowset = rowset;

        getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (lastEditRow != -1 && lastEditRow != EditableGrid.this.getSelectedRow()) {
                    // commit changes
                    try {
                        int cnt = rowset.getColumnCount();
                        for (int i = 0; i < cnt; i++) {
                            Object o = rowset.getValueAt(lastEditRow, i + 1);
                            if (o == null && rowset.isColumnNotNull(i + 1) && rowset.isColumnEditable(i + 1)) {
                                //
                                final int columnIndex = i + 1;
                                SwingUtilities.invokeLater(new Runnable() {
                                    final Object k = new Object();

                                    public void run() {
                                        try {
                                            synchronized (k) {
                                                k.wait(400);
                                            }
                                            EditableGrid.this.setRowSelectionInterval(lastEditRow, lastEditRow);
                                            EditableGrid.this.editCellAt(lastEditRow, columnIndex);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                // skip row saving
                                return;
                            }
                        }

//todo -- experimental                        rowset.completeUpdate();
                    } catch (DBException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        });

        SqlCodeAssistantSettings settings = PluginKeys.PLUGIN_SETTINGS.getData(project);
        Font font = settings.getGridFont();

        // create column renderers
        setDefaultRenderer(Color.class, new ColumnNumberRenderer(true));
        setDefaultRenderer(BigDecimal.class, new NumberRenderer(font));
        setDefaultRenderer(String.class, new TextRenderer(font));
        setDefaultRenderer(ROWID.class, new ROWIDRenderer(font));

//        _table.setDefaultRenderer(java.sql.Time.class, new DateRenderer());
        setDefaultRenderer(RAWType.class, new RAWTypeRenderer(font));
        setDefaultRenderer(LONGRAWType.class, new LONGRAWTypeRenderer(font));
        setDefaultRenderer(CLOB.class, new CLOBTypeRenderer());
        setDefaultRenderer(BLOB.class, new BLOBTypeRenderer());
        setDefaultRenderer(BFILE.class, new BFILETypeRenderer());

        setDefaultRenderer(TIMESTAMP.class, new TimestampRenderer(font, PluginKeys.TS_CONVERTOR.getData(project)));
        setDefaultRenderer(TIMESTAMPLTZ.class, new TimestampRenderer(font, PluginKeys.TSLTZ_CONVERTOR.getData(project)));
        setDefaultRenderer(TIMESTAMPTZ.class, new TimestampRenderer(font, PluginKeys.TSTZ_CONVERTOR.getData(project)));

        DateRenderer dateRenderer = new DateRenderer(font) {
            public String getFormat() {
                return getDateTimeFormat();
            }
        };
        setDefaultRenderer(java.util.Date.class, dateRenderer);
        setDefaultRenderer(java.sql.Date.class, dateRenderer);
        setDefaultRenderer(Timestamp.class, dateRenderer);

        // create column editors
        TextCellEditor textEditor = new TextCellEditor(font);
        textEditor.addActionListener(new TextCellEditorListener() {
            public void invokeExternalEditor(DataAccessor accessor) {
                openColumnValueEditor(accessor);
            }
        });

        RawCellEditor rawEditor = new RawCellEditor<RAWType>(font, new RAWType_Convertor());
        rawEditor.addActionListener(new TextCellEditorListener() {
            public void invokeExternalEditor(DataAccessor accessor) {
                openColumnValueEditor(accessor);
            }
        });

        setDefaultEditor(BigDecimal.class, new NumberCellEditor(font));
        setDefaultEditor(String.class, textEditor);
        setDefaultEditor(RAWType.class, rawEditor);
        setDefaultEditor(LONGRAWType.class, rawEditor);
        setDefaultEditor(java.sql.Timestamp.class, new TimestampCellEditor(settings));
        setDefaultEditor(java.sql.Date.class, new DateCellEditor(settings));
        setDefaultEditor(TIMESTAMP.class, new OracleTimestampCellEditor(font, PluginKeys.TS_CONVERTOR.getData(project)));
        setDefaultEditor(TIMESTAMPLTZ.class, new OracleTimestampCellEditor(font, PluginKeys.TSLTZ_CONVERTOR.getData(project)));
        setDefaultEditor(TIMESTAMPTZ.class, new OracleTimestampCellEditor(font, PluginKeys.TSTZ_CONVERTOR.getData(project)));

        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setRowSelectionAllowed(true);
        setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        setSurrendersFocusOnKeystroke(true);
    }


    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2) {
            // double click, open Editor dialog if column is BLOB/CLOB/BFILE
            int columnIndex = getSelectedColumn();
            int rowIndex = getSelectedRow();

            Class columnClazz = getModel().getColumnClass(columnIndex);
            if(columnClazz.isAssignableFrom(BLOB.class)){
                DataAccessor accessor = createAccessor(columnIndex, rowIndex);
                openColumnValueEditor(accessor);
            } else if(columnClazz.isAssignableFrom(CLOB.class)){
                DataAccessor accessor = createAccessor(columnIndex, rowIndex);
                openColumnValueEditor(accessor);
            } else if(columnClazz.isAssignableFrom(BFILE.class)){
                openColumnValueViewer(columnClazz, columnIndex);
            } else {
                // open for other cases if the cell is readOnly
                if(!getModel().isCellEditable(rowIndex, columnIndex)){
                    openColumnValueViewer(columnClazz, columnIndex);
                }
            }
        }
    }

    private DataAccessor createAccessor(final int columnIndex, final int rowIndex){
        Class columnClazz = getModel().getColumnClass(columnIndex);
        boolean isEditable = getModel().isCellEditable(rowIndex, columnIndex);
        Object value = getValueAt(rowIndex, columnIndex);
        if(columnClazz.isAssignableFrom(BFILE.class)){
            // BFILE is read only
            return DataAccessorFactory.createReadOnly(project, columnClazz, value);
        } else if(isEditable){
            // editable column value
            DataAccessor accessor = DataAccessorFactory.createReadOnly(project, columnClazz, value);
            if(columnClazz.isAssignableFrom(BLOB.class)){
                return new DataAccessorWrapper<BLOB>(accessor){
                    public void loadFromString(String text) throws ConversionException {
                        BLOB blob = getValue();
                        try {
                            if(blob == null || !blob.isTemporary()){
                                blob = BLOB.createTemporary(rowset.getConnection(), true,  BLOB.DURATION_SESSION);
                            }

                            OutputStream out = blob.setBinaryStream(1);
                            byte[] array = ConversionUtil.convertHEXString2ByteArray(text);
                            out.write(array);
                            out.close();
                            setValueAt(blob, rowIndex, columnIndex);
                        } catch (SQLException e) {
                            // todo -- do appropriate handling
                            e.printStackTrace();
                        } catch (IOException e) {
                            // todo -- do appropriate handling
                            e.printStackTrace();
                        }
                    }
                    public boolean isReadOnly(){
                        return false;
                    }
                };
            } else if(columnClazz.isAssignableFrom(CLOB.class)){
                return new DataAccessorWrapper<CLOB>(accessor){
                    public void loadFromString(String text) throws ConversionException {
                        CLOB clob = getValue();
                        try {
                            if(clob == null || !clob.isTemporary()){
                                clob = CLOB.createTemporary(rowset.getConnection(), true,  CLOB.DURATION_SESSION);
                            }

                            OutputStream out = clob.setAsciiStream(1);
                            out.write(text.getBytes());
                            out.close();
                            setValueAt(clob, rowIndex, columnIndex);
                        } catch (SQLException e) {
                            // todo -- do appropriate handling
                            e.printStackTrace();
                        } catch (IOException e) {
                            // todo -- do appropriate handling
                            e.printStackTrace();
                        }
                    }
                    public boolean isReadOnly(){
                        return false;
                    }
                };
            } else {
                // todo -- make accessor ReadWrite
                return accessor;
            }
        } else {
            // read only column value
            return DataAccessorFactory.createReadOnly(project, columnClazz, value);
        }
    }

    private class DataAccessorWrapper<E> extends DataAccessor<E> {
        protected DataAccessor<E> delegate;
        public DataAccessorWrapper(DataAccessor<E> delegate){
            this.delegate = delegate;
        }
        public long size() throws SQLException{
            return delegate.size();
        }

        public String convertToString() throws SQLException{
            return delegate.convertToString();
        }

        public void loadFromString(String text) throws ConversionException {
            delegate.loadFromString(text);
        }

        public void saveValueTo(File file) throws IOException {
            delegate.saveValueTo(file);
        }

        public boolean isReadOnly(){
            return delegate.isReadOnly();
        }

        public E getValue(){
            return delegate.getValue();
        }
    }

    private void openColumnValueViewer(Class columnClazz, int columnIndex) {
        int rowIndex = getSelectedRow();
        Object value = getValueAt(rowIndex, columnIndex);
        DataAccessor accessor = DataAccessorFactory.createReadOnly(project, columnClazz, value);
        if(accessor != null){
            String columnName = getColumnName(columnIndex);
            try {
                DialogWrapper dialog = buildDialog(project, columnClazz, columnName, accessor);
                dialog.show();
            } catch (ColumnReadException e) {
                // todo - log exception
            }
        }
    }

    private void openColumnValueEditor(DataAccessor accessor){
        int columnIndex = getSelectedColumn();
        String columnName = getColumnName(columnIndex);
        Class columnClazz = getModel().getColumnClass(columnIndex);

        try {
            DialogWrapper dialog = buildDialog(project, columnClazz, columnName, accessor);
            dialog.show();
        } catch (ColumnReadException e) {
            // todo - log exception
        } finally {
            Component eComponent = getEditorComponent();
            if(eComponent != null){
                TableCellEditor editor = getDefaultEditor(columnClazz);
                if(editor != null){
                    editor.stopCellEditing();
                }
            }
        }
    }

    private void updateModel(TableModelEvent e) {
        switch (e.getType()) {
            case TableModelEvent.DELETE: {
                try {
                    final int firstRow = e.getFirstRow();
                    rowset.deleteRows(firstRow, e.getLastRow());
                    if (rowset.getFetchedRowCount() > 0) {//= firstRow) {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                int position = firstRow - 1;
                                if (EditableGrid.this.getModel().getRowCount() <= position) {
                                    position--;
                                }
                                EditableGrid.this.setRowSelectionInterval(position, position);
                            }
                        });
                    }
                } catch (DBException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case TableModelEvent.INSERT: {
                final int firstRow = e.getFirstRow();
                try {
                    rowset.addRow(firstRow);
                    lastEditRow = firstRow;
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            int position = firstRow;// - 1;
                            EditableGrid.this.setRowSelectionInterval(position, position);
                        }
                    });
                } catch (DBException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case TableModelEvent.UPDATE: {
                int _1st = e.getFirstRow();
                int _last = e.getLastRow();

                lastEditRow = _1st;
            }
        }
    }

    /**
     * Submit row editing if any
     */
    public void submitUpdate() throws DBException {

        final int lastEditRow1 = EditableGrid.this.getSelectedRow();
        TableCellEditor cellEditor = EditableGrid.this.getCellEditor();
        if(cellEditor != null){
            if(cellEditor.stopCellEditing()!=true){
                // cell editor failed, abort submitting changes
                Messages.showWarningDialog("   Entered value is not valid   ", "Cannot save changes");
                return;
            }
        }
        // check cells on NOT NULL constraints
        int cnt = rowset.getColumnCount();
        for (int i = 0; i < cnt; i++) {
            final int columnIndex = i + 1;
            Object o = rowset.getValueAt(lastEditRow1, columnIndex);
            if (o == null && rowset.isColumnNotNull(columnIndex) && rowset.isColumnEditable(columnIndex)) {
                //
                SwingUtilities.invokeLater(new Runnable() {
                    final Object k = new Object();

                    public void run() {
                        try {
                            synchronized (k) {
                                k.wait(400);
                            }
                            EditableGrid.this.setRowSelectionInterval(lastEditRow1, lastEditRow1);
                            EditableGrid.this.editCellAt(lastEditRow1, columnIndex);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                // skip row saving
                return;
            }
        }

        rowset.completeUpdate();
        fireTableRowsUpdated1(lastEditRow1, lastEditRow1);
        // todo -- is lastEditRow really needed?
        lastEditRow = -1;
    }

    @Override
    public boolean changesNotPosted() {
        return rowset.changesNotPosted();
    }


    @Override
    public boolean isFirst() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isLast() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void prev() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void next() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void first() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void last() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Insert new row before selected row
     */
    public void insertRow() {
        int selectedRow = EditableGrid.this.getSelectedRow();
        selectedRow = (selectedRow!=-1)? selectedRow: 0;
        fireTableRowsInserted1(selectedRow, selectedRow);
    }

    /**
     * Delete currently selected row
     */
    public void deleteRow() {
        int[] selectedRows = EditableGrid.this.getSelectedRows();
        if(selectedRows.length == 0){
            Messages.showWarningDialog("  Rows being deleted are not selected  ", "Cannot delete records");
            return;
        }

        fireTableRowsDeleted1(selectedRows[0] + 1, selectedRows[selectedRows.length - 1] + 1);
    }

    private void fireTableRowsUpdated1(int firstRow, int lastRow) {
        TableModel tableModel = EditableGrid.this.getModel();

        TableModelEvent e = new TableModelEvent(tableModel, firstRow, lastRow,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE);
        this.tableChanged(e);
        updateModel(e);
    }

    private void fireTableRowsDeleted1(int firstRow, int lastRow) {
        TableModel tableModel = EditableGrid.this.getModel();

        TableModelEvent e = new TableModelEvent(tableModel, firstRow, lastRow,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        this.tableChanged(e);
        updateModel(e);
    }

    private void fireTableRowsInserted1(int firstRow, int lastRow) {
        TableModel tableModel = EditableGrid.this.getModel();

        TableModelEvent e = new TableModelEvent(tableModel, firstRow, lastRow,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        this.tableChanged(e);
        updateModel(e);
    }


    public static DialogWrapper buildDialog(
            @NotNull Project project, @NotNull Class columnClazz, String columnName, DataAccessor accessor) throws ColumnReadException {

        try {
            if(columnClazz.isAssignableFrom(RAWType.class)){
                return new BinaryEditorDialog(project, columnName, accessor);
            } else if(columnClazz.isAssignableFrom(LONGRAWType.class)){
                return new BinaryEditorDialog(project, columnName, accessor);
            } else if(columnClazz.isAssignableFrom(BLOB.class)){
                return new BinaryEditorDialog(project, columnName, accessor);
            } else {
                return new TextEditorDialog(project, columnName, accessor);
            }
        } catch (SQLException e) {
            // todo -- save in the log
            e.printStackTrace();
        }

        throw new ColumnReadException("Editor for class " + columnClazz + " not found");
    }

}
