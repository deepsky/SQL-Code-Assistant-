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
import com.deepsky.database.exec.RowSetManager;
import com.deepsky.database.exec.UpdatableRecordCache;
import com.deepsky.gui.ExportSettings;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;
import com.deepsky.view.Icons;
import com.deepsky.view.query_pane.grid.EditableGrid;
import com.deepsky.view.query_pane.grid.ImmutableGrid;
import com.deepsky.view.query_pane.grid.PopupListener;
import au.com.bytecode.opencsv.CSVWriter;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.components.JBScrollPane;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;


public class DataGridPanel extends JPanel implements QueryResultPanel, TableModelListener, ActionListener {

    static LoggerProxy log = LoggerProxy.getInstance("#DataGridPanel");

    final static int REFRESH_RESULTSET = 1;
    final static int STICKY_OPTION = 3;
    final static int EXPORT_DATA = 4;
    final static int POST_CHANGES = 10;
    final static int INSERT_RECORD = 11;
    final static int DELETE_RECORD = 12;

    final static int GOTO_FIRST_RECORD = 13;
    final static int GOTO_LAST_RECORD = 14;

    final int ROWNUM_TO_MAKE_DECISION = 8;
    final int MAX_WIDTH = 200;
    final int ARROW_APPROXIMATE_WIDTH = 16;
    final int ROW_COUNTER_WIDTH = 30;

    private JComponent central;
    private AbstractDataGrid _grid;
    private StatusLinePanel statusLine;
    private RowSetManager rsModel;
    private Project project;

    private DataGridSorter mm;

    private boolean isGridEditable = false;

    private DataGridPanel(LayoutManager2 layout) {
        setLayout(layout);
    }

    public DataGridPanel(Project project) {
        this(new BorderLayout());
        this.project = project;
/* todo - turn off support of "Sticky" for now
        actionGroup.add(new LocalToggleAction("Sticky option", "Sticky option", Icons.CONNECT, STICKY_OPTION));
*/

        DefaultActionGroup actionGroup = new DefaultActionGroup("PsiActionGroup22", false);
/*
todo - implement me
        actionGroup.add(new LocalToggleAction("Go to First", "Go to First Row", Icons.ARROW_LEFT, GOTO_FIRST_RECORD));
        actionGroup.add(new LocalToggleAction("Go to Last", "Go to Last Row", Icons.ARROW_RIHGT, GOTO_LAST_RECORD));
        actionGroup.addSeparator();
*/
        actionGroup.add(new LocalToggleAction("Insert Row", "Insert Row", Icons.INSERT_ROW, INSERT_RECORD, true));
        actionGroup.add(new LocalToggleAction("Delete Row", "Delete Row", Icons.DELETE_ROW, DELETE_RECORD, true));
        actionGroup.add(new LocalToggleAction("Submit changes", "Submit changes", Icons.SUBMIT_CHANGES, POST_CHANGES, true));
        actionGroup.addSeparator();
        actionGroup.add(new LocalToggleAction("Refresh Query Result", "Refresh Query Result", Icons.REFRESH_RESULTSET, REFRESH_RESULTSET));
        actionGroup.addSeparator();
        actionGroup.add(new LocalToggleAction("Export", "Export", Icons.EXPORT_DATA, EXPORT_DATA));

        ActionManager actionManager = ActionManager.getInstance();
        ActionToolbar toolBar = actionManager.createActionToolbar("DataGridActionToolbar", actionGroup, true);
        add(toolBar.getComponent(), "North");

/*
        central = new JPanel(new BorderLayout());
        central.setBackground(UIUtil.getTreeTextBackground());
        add(central, BorderLayout.CENTER);
*/

        statusLine = new StatusLinePanel();
        add(statusLine, BorderLayout.SOUTH);
    }

    public void init(@NotNull Object data) {
        rsModel = (RowSetManager) data;
        setVisible(false);
        if (central != null) {
            remove(central);
        }

        int fetched = rsModel.getModel().getFetchedRowCount();
        boolean allFetched = rsModel.getModel().allRowsFetched();
        String rowsMessage =
                Integer.toString(fetched)
                        + " rows fetched"
                        + (allFetched ? "" : " (more rows exists)");
        statusLine.setResponseMessage(rowsMessage);
        statusLine.setTimeSpent(rsModel.getModel().timeSpentOnFetch());

        central = createTable2(rsModel);
        add(central, BorderLayout.CENTER);
        setVisible(true);
    }

/*
    class TableHeaderRenderer1 implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return null;
        }
    }
*/

    final JPopupMenu popupMenu = new JPopupMenu();

    private JScrollPane createTable2(RowSetManager rsManager) {
        if (mm != null) {
            mm.removeTableModelListener(this);
//            _table.getInputMap().remove(copy);
        }
        mm = new DataGridSorter(project, rsManager.getModel()){
            public void sortByColumn(int columnIndex, int dir) throws DBException {
                _grid.sortByColumn(columnIndex, dir);
            }

            @Override
            public boolean isColumnSortable(int columnIndex) {
                return _grid.isColumnSortable(columnIndex);
            }
        };
        mm.addTableModelListener(this);

        if (rsManager.getModel() instanceof UpdatableRecordCache) {
            _grid = new EditableGrid(project, mm, (UpdatableRecordCache) rsManager.getModel());
            isGridEditable = true;
        } else {
            _grid = new ImmutableGrid(project, mm, rsManager.getModel());
        }

//        _grid.setNumerateRows(true);

//        _table.getInputMap().put(copy, this);
//        addHotKey("copy", getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK), new CopyAction());
        mm.setTableHeader(_grid.getTableHeader());

        // Save selected column in the Clipboard
        JMenuItem menuItem = new JMenuItem("Copy column value");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int column = _grid.getSelectedColumn();
                    int row = _grid.getSelectedRow();
                    int j = _grid.isToNumerate()? 1: 0;
                    if(column-j < 0){
                        // Column number selected, skip value copying
                        return;
                    }
                    String content = getValue(rsModel.getModel(), row, column-j);
                    if (content != null) {
                        // save in Clipboard
                        StringSelection stsel = new StringSelection(content);
                        Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
                        system.setContents(stsel, stsel);
                    }
                } catch (DBException e1) {
                    // todo - handle exc
                }
            }
        });
        popupMenu.add(menuItem);

        // Save selected row entirely in the Clipboard
        menuItem = new JMenuItem("Copy row");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String content = extractContent(",", false, false, false);
                    if (content != null) {
                        // save in Clipboard
                        StringSelection stsel = new StringSelection(content);
                        Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
                        system.setContents(stsel, stsel);
                    }
                } catch (DBException e1) {
                    // todo - handle exc
                }
            }
        });
        popupMenu.add(menuItem);


        _grid.addPopupListener(new PopupListener() {
            public void popupFared(MouseEvent e) {
                // todo -- add a code to dispose the listener
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        // todo -- add code to toggle selection model
        //_table.getSeSelectionModel().
        //Cell selection is ok in this mode.
//         cellCheck.setEnabled(true);

        int i = 0;
        // if grid has a row counter set it size first
        if(_grid.isToNumerate()){
            // set size of the row counter field
            TableColumn column = _grid.getColumnModel().getColumn(0);
            column.setPreferredWidth(ROW_COUNTER_WIDTH);
            i = 1;
        }
        for (; i < _grid.getColumnCount(); i++){ //rsManager.getModel().getColumnCount(); i++) {
            packColumn(_grid, i, 1);
        }

        return new JBScrollPane(_grid);
    }

    public JTable getTable() {
        return _grid;
    }

    public void close() {
        //Messages.getQuestionIcon()
        //Messages.showYesNoDialog()WarningDialog();ChooseDialog(project, "", "Title", null, new String[0], "");
        rsModel.close();
        mm.dispose();
    }

    public String extractContent(
            final String fieldDelimiter, boolean saveHeader, final boolean encloseInDoubleQuotes, boolean saveAllRows)
            throws DBException {
        final int numcols = rsModel.getModel().getColumnCount();
        final int numrows = saveAllRows? -1: _grid.getSelectedRowCount();

        if ( numrows != 0) {
            final String lineSeparator = getLineSeparator();

            final StringWriter stringWriter = new StringWriter();
            final CSVWriter writer = new CSVWriter(
                    stringWriter, fieldDelimiter.charAt(0),
                    encloseInDoubleQuotes? '"': CSVWriter.NO_QUOTE_CHARACTER,
                    lineSeparator);
            if (saveHeader) {
                // put header name list
                int j = _grid.isToNumerate()? 1: 0;
                int _numcols = _grid.isToNumerate()? numcols+1: numcols;
                String[] values = new String[_numcols-j];
                for (int i=0; j < _numcols; j++, i++) {
                    String text = (String) _grid.getTableHeader().getColumnModel().getColumn(j).getHeaderValue();
                    values[i] = text;
                }
                writer.writeNext(values);
            }

            // Save content
            final int[] selectedRows = saveAllRows? new int[0]: _grid.getSelectedRows();
            iterateOverSelectedRows(selectedRows, new RecordHandler() {
                public void handleRecord(RecordCache rCache, int rowIndex, boolean isRecord_1st) throws DBException {
                    String[] values = new String[numcols];
                    // Save column values
                    for (int j = 0; j < numcols; j++) {
                        values[j] = getValue(rCache, rowIndex, j);
                    }
                    writer.writeNext(values);
                }
            });

            try {
                writer.close();
                return stringWriter.toString();
            } catch (IOException e) {
                log.warn(e.getMessage());
            }
            return "";
        }

        return null;
    }

    private String getLineSeparator() {
        String lineSeparator= System.getProperty("line.separator");
        return lineSeparator!=null? lineSeparator: "\n";
    }

    private String getValue(RecordCache rCache, int rowIndex, int column) throws DBException {
        Object value = rCache.getValueAt(rowIndex, column + 1);
        if (value != null) {
            ValueConvertor4 convertor = ValueConvertor4Factory.create(project, value);
            try {
                return convertor != null ? convertor.convertToString() : "";
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
        return "";
    }

    private void iterateOverSelectedRows(final int[] selectedRows, RecordHandler handler) throws DBException {
        if(selectedRows.length > 0){
            for(int i=0; i<selectedRows.length; i++){
                handler.handleRecord(rsModel.getModel(), selectedRows[i], i == 0);
            }
        } else {
            // Iterate over all records
            int i = 0;
            while(true){
                if(i < rsModel.getModel().getFetchedRowCount() ){
                    // keep iterating
                } else if(rsModel.getModel().allRowsFetched()){
                    break;
                } else {
                    // run one more cycle to
                    continue;
                }

                handler.handleRecord(rsModel.getModel(), i, i == 0);
                i++;
            }
        }
    }

    private interface RecordHandler {
        void handleRecord(RecordCache rCache, int rowIndex, boolean isRecord_1st) throws DBException;
    }

    private static class CopyAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            Object editor = e.getSource();
//            editor.undo();
        }
    }

    public void addHotKey(String id, KeyStroke key, Action a) {
        getInputMap().put(key, id);
        getActionMap().put(id, a);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().compareTo("Copy") == 0) {
            int hh = 0;
        }
    }


/*
    public void handleUpdate(ConnectionManagerListener.StateEvent state) {
        switch (state.getStatus()) {
            case ConnectionManagerListener.CONNECTED:
                statusLine.setSchemaName(state.getUrl().getKey());
                break;
            case ConnectionManagerListener.DISCONNECTED:
                statusLine.setSchemaName("Not logged in");
                break;
        }
    }
*/

    public void tableChanged(TableModelEvent e) {
        switch(e.getType()){
            case TableModelEvent.UPDATE:
            case TableModelEvent.INSERT: {
//                int first = e.getFirstRow();
//                int last = e.getLastRow();

                int rowFetched = rsModel.getModel().getFetchedRowCount();
                String rowsMessage =
                        Integer.toString(rowFetched)
                                + " rows fetched"
                                + (rsModel.getModel().allRowsFetched() ? "" : " (more rows exists)");
                statusLine.setResponseMessage(rowsMessage);
                statusLine.setTimeSpent(rsModel.getModel().timeSpentOnFetch());
                break;
            }
        }
    }

    private void packColumn(JTable table, int colIndex, int margin) {
        int colHeaderWidth = getColumnHeaderWidth(table, colIndex);
        int colCellWidth = getColumnCellWidth(table, colIndex);

        int width = Math.max(colHeaderWidth + ARROW_APPROXIMATE_WIDTH, colCellWidth);
        width += 2 * margin;
        TableColumn column = table.getColumnModel().getColumn(colIndex);
        column.setPreferredWidth((width > MAX_WIDTH) ? MAX_WIDTH : width);
    }

    private int getColumnCellWidth(JTable table, int colIndex) {
        int width = 0;
        for (int r = 0; r < table.getRowCount() && r < ROWNUM_TO_MAKE_DECISION; r++) {
            TableCellRenderer renderer = table.getCellRenderer(r, colIndex);
            Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, colIndex), false, false, r, colIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }

        return width;
    }

    private int getColumnHeaderWidth(JTable table, int colIndex) {
        TableColumn col = table.getColumnModel().getColumn(colIndex);
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null)
            renderer = table.getTableHeader().getDefaultRenderer();
        Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);
        return comp.getPreferredSize().width;
    }


    class LocalToggleAction extends ToggleAction {

        boolean editOnly = false;
        int cmd;

        public LocalToggleAction(String actionName, String toolTip, Icon icon, int cmd, boolean editOnly) {
            super(actionName, toolTip, icon);
            this.editOnly = editOnly;
            this.cmd = cmd;
        }

        public LocalToggleAction(String actionName, String toolTip, Icon icon, int cmd) {
            super(actionName, toolTip, icon);
            this.cmd = cmd;
        }

        public boolean isSelected(AnActionEvent event) {
            return false;
        }

        public void setSelected(AnActionEvent event, boolean b) {
            switch (cmd) {
                case INSERT_RECORD: {
                    _grid.insertRow();
                    break;
                }
                case DELETE_RECORD: {
                    _grid.deleteRow();
                    break;
                }
                case POST_CHANGES: {
                    try {
                        _grid.submitUpdate();
                    } catch (DBException e) {
                        Messages.showErrorDialog(project, e.getMessage(), "Post changes failed");
                    }
                    break;
                }
                case REFRESH_RESULTSET: {
                    try {
                        rsModel.getModel().refresh();
                    } catch (DBException e) {
                        String error = e.getMessage();
                        error = error == null || error.length() == 0 ? "Check database connection" : error;
                        Messages.showErrorDialog(project, error, "Query failed");
                    }
                    break;
                }
                case EXPORT_DATA: {
                    int numrows = _grid.getSelectedRowCount();
                    ExportSettings settings = new ExportSettings(project, numrows > 1);
                    settings.show();
                    if (!settings.isOK()) {
                        return;
                    }

                    File fileToSave = null;
                    if (settings.saveToFile()) {
                        String path = settings.getFilePathToSave();
                        if (path == null || path.length() == 0) {
                            Messages.showInfoMessage(
                                    "File not specified, content will be saved in Clipboard",
                                    "File not specified"
                            );
                        } else {
                            // todo --
                            fileToSave = new File(path);
                        }
                    }

                    String delimiter;
                    switch (settings.getDelimiter()) {
                        case TAB:
                            delimiter = "\t";
                            break;
                        case COMMA:
                            delimiter = ",";
                            break;
                        default: //  SEMICOLON:
                            delimiter = ";";
                            break;
                    }

                    String content = null;
                    try {
                        content = extractContent(
                                delimiter, settings.saveColumnHeaders(),
                                settings.encloseFieldsInDowubleQuotes(),
                                settings.saveAllRows()
                        );
                        if (content != null) {
                            if (fileToSave != null) {
                                // save in the file
                                try {
                                    StringUtils.string2file(content, fileToSave);
                                } catch (IOException e) {
                                    Messages.showErrorDialog(e.getMessage(), "Export failed");
                                }
                            } else {
                                // save in Clipboard
                                StringSelection stsel = new StringSelection(content);
                                Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
                                system.setContents(stsel, stsel);
                            }
                        }
                    } catch (DBException e) {
                        Messages.showErrorDialog(project, e.getMessage(), "Saving failed");
                    }
                    break;
                }
            }
        }

        public void update(final AnActionEvent e) {
            super.update(e);
            switch (cmd) {
                case POST_CHANGES: {
                    if(isGridEditable && _grid != null && _grid.changesNotPosted()){
                        e.getPresentation().setEnabled(true);
                    } else {
                        e.getPresentation().setEnabled(false);
                    }
                    break;
                }
                case DELETE_RECORD: {
                    if(isGridEditable && _grid != null && _grid.getModel().getRowCount() > 0){
                        e.getPresentation().setEnabled(true);
                    } else {
                        e.getPresentation().setEnabled(false);
                    }
                    break;
                }
                default: {
                    e.getPresentation().setEnabled(!editOnly || (editOnly & isGridEditable));
                    break;
                }
            }
        }
    }

}
