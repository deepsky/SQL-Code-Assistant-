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

import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.openapi.project.Project;
import com.intellij.util.ui.UIUtil;
import com.deepsky.database.exec.RowSetModel;
import com.deepsky.database.*;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

import org.jetbrains.annotations.NotNull;


public class DataGridPanel extends JPanel implements QueryResultPanel, //ConnectionManagerListener,
        TableModelListener, ActionListener {

    final static int REFRESH_RESULTSET = 1;
    final static int STICKY_OPTION = 3;
    final static int EXPORT_DATA = 4;

    private JComponent central;
    JTable _table;
    StatusLinePanel statusLine;
    RowSetModel rsModel;
    Project project;

    //    KeyStroke copy;
//    boolean isConnected = false;

    private DataGridPanel(LayoutManager2 layout) {
        setLayout(layout);
    }
    public DataGridPanel(Project project) {
        this(new BorderLayout());
        this.project = project;
//        setLayout(new BorderLayout());
//        copy = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK, false);


/*
        ActionManager actionManager = ActionManager.getInstance();
        DefaultActionGroup actionGroup = new DefaultActionGroup("PsiActionGroup22", false);

*/
/* todo - turn off for now support of "Sticky"
        actionGroup.add(new LocalToggleAction("Sticky option", "Sticky option", Icons.CONNECT, STICKY_OPTION));
*/
/*
        actionGroup.add(new LocalToggleAction("Refresh Query Result", "Refresh Query Result", Icons.REFRESH_RESULTSET, REFRESH_RESULTSET));
        actionGroup.add(new LocalToggleAction("Copy", "Copy", Icons.EXPORT_DATA, EXPORT_DATA));

        ActionToolbar toolBar = actionManager.createActionToolbar("DataGridActionToolbar", actionGroup, true);
//        add(toolBar.getComponent(), "North");
*/

        central = new JPanel(new BorderLayout());
        central.setBackground(UIUtil.getTreeTextBackground());
        add(central, BorderLayout.CENTER);

        statusLine = new StatusLinePanel();
        add(statusLine, BorderLayout.SOUTH);

/*
        if (ConnectionManagerImpl.getInstance().isConnected()) {
            statusLine.setSchemaName(ConnectionManagerImpl.getInstance().getDbUrl().getUserHostPortServiceName().toLowerCase());
            isConnected = true;
        } else {
            statusLine.setSchemaName("Not logged in");
            isConnected = false;
        }

        ConnectionManagerImpl.getInstance().addStateListener(this);
*/

/*
        this.addComponentListener(new ComponentListener(){
            public void componentResized(ComponentEvent e) {
            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentShown(ComponentEvent e) {
                logger.info("StatusLine shown");
            }

            public void componentHidden(ComponentEvent e) {
                logger.info("StatusLine hidden");
            }
        });

        this.addContainerListener(new ContainerListener(){
            public void componentAdded(ContainerEvent e) {
                logger.info("StatusLine comp added");
            }

            public void componentRemoved(ContainerEvent e) {
                logger.info("StatusLine comp removed");
            }
        });

        this.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e) {
                logger.info("StatusLine focus gained");
            }

            public void focusLost(FocusEvent e) {
                logger.info("StatusLine focus lost");
            }
        });
*/

    }


    public void init(@NotNull Object data) {
//        setBackground(Color.WHITE);
        rsModel = (RowSetModel) data;
        setVisible(false);
//        setLayout(new BorderLayout(0, 0));
        if (central != null) {
            remove(central);
        }
//        removeAll();

        int fetched = rsModel.getFetchedRowCount();
        boolean allFetched = rsModel.allRowsFetched();
        String rowsMessage =
                Integer.toString(fetched)
                        + " rows fetched"
                        + (allFetched ? "" : " (more rows exists)");
        statusLine.setResponseMessage(rowsMessage);
        statusLine.setTimeSpent(rsModel.timeSpentOnFetch());

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
/*
    static class DateRenderer extends DefaultTableCellRenderer {
        DateFormat formatter;
        public DateRenderer(){
        }

        public void setValue(Object value) {
            if (formatter==null) {
                formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //DateFormat.getDateInstance();
            }
            setText((value == null) ? "" : formatter.format(value));
        }
    }

    DateRenderer dateRenderer = new DateRenderer();
*/

    final JPopupMenu popupMenu = new JPopupMenu();
    private JScrollPane createTable2(RowSetModel model) {

        DataGridSorter mm = new DataGridSorter(project, model);
        mm.addTableModelListener(this);
        if (_table != null) {
            _table.getModel().removeTableModelListener(this);
//            _table.getInputMap().remove(copy);
        }

        _table = new JTable(mm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        _table.getInputMap().put(copy, this);
//        addHotKey("copy", getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK), new CopyAction());

        mm.setTableHeader(_table.getTableHeader());
        _table.setAutoResizeMode(0);
        //_table.getSelectionModel().setSelectionMode(0);
        _table.getSelectionModel().addListSelectionListener(new RowListener());
        _table.getColumnModel().getSelectionModel().addListSelectionListener(new ColumnListener());

        _table.setColumnSelectionAllowed(true);
        _table.setCellSelectionEnabled(true);
        _table.setRowSelectionAllowed(true);
        _table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);



        JMenuItem menuItem = new JMenuItem("Copy to Clipboard");
        menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String content = extractContent(",", false, false);
                if(content != null){
                    // save in Clipboard
                    StringSelection stsel = new StringSelection(content);
                    Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
                    system.setContents(stsel, stsel);
                }
            }
        });
        popupMenu.add(menuItem);


        _table.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e) {
              showPopup(e);
            }

            public void mouseReleased(MouseEvent e) {
              showPopup(e);
            }

            private void showPopup(MouseEvent e) {
              if (e.isPopupTrigger()) {
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
              }
            }
        });
        //_table.getSeSelectionModel().
        //Cell selection is ok in this mode.

//         cellCheck.setEnabled(true);
        for(int i =0; i<model.getColumnCount(); i++){
            packColumn(_table, i, 1);
        }

        return new JScrollPane(_table);
    }

    public JTable getTable(){
        return _table;
    }

    public void close(){
        rsModel.close();        
    }

    public boolean isRefreshSupported() {
        return true;
    }

    public boolean isExportSupported() {
        return true;
    }

    public void refresh() throws DBException {
        rsModel.refresh();
    }

    public String extractContent(String delimiter, boolean saveHeader, boolean ecloseWithDowbleQuotes) {
        int numcols = _table.getSelectedColumnCount();
        int numrows = _table.getSelectedRowCount();

        if (numcols > 0 && numrows > 0) {
            StringBuffer sbf = new StringBuffer();
            int[] rowsselected = _table.getSelectedRows();
            int[] colsselected = _table.getSelectedColumns();

            if (saveHeader) {
                // put header name list
                for (int j = 0; j < numcols; j++) {
                    String text = (String) _table.getTableHeader().getColumnModel().getColumn(colsselected[j]).getHeaderValue();
                    sbf.append(text);
                    if (j < numcols - 1) sbf.append(delimiter);
                }
                sbf.append("\n");
            }

            // put content
            for (int i = 0; i < numrows; i++) {
                if(i > 0){
                    sbf.append("\n");
                }
                for (int j = 0; j < numcols; j++) {
                    Object value = _table.getValueAt(rowsselected[i], colsselected[j]);
                    String _value = "";
                    if (value != null) {
                        _value = value.toString();
                    }
                    if (ecloseWithDowbleQuotes) {
                        sbf.append("\"").append(_value).append("\"");
                    } else {
                        sbf.append(_value);
                    }

                    if (j < numcols - 1) sbf.append(delimiter);
                }
            }

//            StringSelection stsel = new StringSelection(sbf.toString());
//            Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
//            system.setContents(stsel, stsel);
            return sbf.toString();
        }

        return null;
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
                statusLine.setSchemaName(state.getUrl().getUserHostPortServiceName());
                break;
            case ConnectionManagerListener.DISCONNECTED:
                statusLine.setSchemaName("Not logged in");
                break;
        }
    }
*/

    public void tableChanged(TableModelEvent e) {
        if (e.getType() == TableModelEvent.INSERT) {
            int first = e.getFirstRow();
            int last = e.getLastRow();

            String rowsMessage =
                    Integer.toString(last)
                            + " rows fetched"
                            + (rsModel.allRowsFetched() ? "" : " (more rows exists)");
            statusLine.setResponseMessage(rowsMessage);
            statusLine.setTimeSpent(rsModel.timeSpentOnFetch());
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

    int ROWNUM_TO_MAKE_DECISION = 8;
    int MAX_WIDTH = 200;
    int ARROW_APPROXIMATE_WIDTH = 16;

    private void packColumn(JTable table, int colIndex, int margin) {
        int colHeaderWidth = getColumnHeaderWidth(table, colIndex);
        int colCellWidth = getColumnCellWidth(table, colIndex);


        int width = Math.max(colHeaderWidth + ARROW_APPROXIMATE_WIDTH, colCellWidth);
        width += 2 * margin;
        TableColumn column = table.getColumnModel().getColumn(colIndex);
        column.setPreferredWidth((width>MAX_WIDTH)? MAX_WIDTH: width);
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

}
