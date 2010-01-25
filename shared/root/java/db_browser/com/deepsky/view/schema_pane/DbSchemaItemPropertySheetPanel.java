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

package com.deepsky.view.schema_pane;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.diagnostic.Logger;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class DbSchemaItemPropertySheetPanel extends JPanel {

    //    private Object _target;
    private JTable _table;
    private static final Logger LOG = Logger.getInstance("#DbSchemaItemPropertySheetPanel");

    TableModel model;

    public DbSchemaItemPropertySheetPanel() {
//        setLayout(new GridBagLayout());
        setLayout(new BorderLayout());

    }

    public void setTarget(ItemViewWrapper view) {
//        debug("setTarget=" + view);
        setBackground(Color.WHITE);
        setVisible(false);
        setLayout(new BorderLayout(0, 0));
        removeAll();

// todo
//        if(view == null){
//            int hh =0;
//            return;
//        }

        ActionManager actionManager = ActionManager.getInstance();
        DefaultActionGroup actionGroup = new DefaultActionGroup("PsiActionGroup22", false);

        for (ToggleAction act : view.getActions()) {
            actionGroup.add(act);
        }
        ActionToolbar toolBar = actionManager.createActionToolbar("PsiActionToolbar33", actionGroup, true);
        add(toolBar.getComponent(), "North");


//        JButton button = new JButton();
//        button.setName("Activate");
//        button.setActionCommand("Activate");
//        button.setToolTipText("Activate me!");
//        button.setIcon(IconLoader.getIcon("/fileTypes/javaScript.png"));
//
//        JToolBar toolBar = new JToolBar("Still draggable");
//        toolBar.add(button);
//        add(toolBar, BorderLayout.PAGE_START);

//        ItemViewWrapper wrp = (ItemViewWrapper) view;
        add(createTable(view.getTabularData(), view.getColumnNames(), view.getPopupMenu()), BorderLayout.CENTER);
//        add(createTable(wrp.getTabularData(), wrp.getColumnNames()));
        setVisible(true);
    }

    public JTable getTable() {
        return _table;
    }

    private JScrollPane createTable(Object tableData[][], Object columnTitle[], JPopupMenu popup) {
        _table = new JTable(tableData, columnTitle) {

            private static final boolean INCLUDE_INTERCELL_SPACING = true;

            public boolean isCellEditable(int row, int column) {
                return false;
            }

//            public JToolTip createToolTip() {
//                PropertySheetToolTip.getInstance().setComponent(this);
//                return PropertySheetToolTip.getInstance();
//            }

            public String getToolTipText(MouseEvent event) {
                int col = columnAtPoint(event.getPoint());
                int row = rowAtPoint(event.getPoint());
                String tip = (String) getValueAt(row, col);
                Graphics2D g2 = (Graphics2D) getGraphics();
                Rectangle2D tipRect = getFont().getStringBounds(tip, g2.getFontRenderContext());
                g2.dispose();
                Rectangle visibleCell = getVisibleRect().intersection(getCellRect(row, col, false));
                if (tipRect.getWidth() + 1.0D < visibleCell.getWidth())
                    tip = null;
                return tip;
            }

            public Point getToolTipLocation(MouseEvent event) {
                int col = columnAtPoint(event.getPoint());
                int row = rowAtPoint(event.getPoint());
                return getCellRect(row, col, true).getLocation();
            }

        };
        _table.setAutoResizeMode(0);
        _table.getSelectionModel().setSelectionMode(0);

//        JPopupMenu popup = new JPopupMenu();
//        popup.add(new JMenuItem("Hello"));
//
        // add Popup Menu
        if (popup != null) {
// todo -- implement me
//            MouseListener popupListener = new PopupListener(popup);
//            _table.addMouseListener(popupListener);
        }

        for (int i = 0; i < columnTitle.length; i++) {
            packColumn(_table, i, 2);
        }
//        packColumn(_table, 0, 2);
//        packColumn(_table, 1, 2);
        return new JScrollPane(_table);
    }
/*
    class PopupListener extends MouseAdapter {
        JPopupMenu popup;

        public PopupListener(JPopupMenu popup) {
            this.popup = popup;
        }

        public void mousePressed(MouseEvent e) {
            showPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            showPopup(e);
        }

        private void showPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }
*/

    private void packColumn(JTable table, int colIndex, int margin) {
        int width = Math.max(getColumnHeaderWidth(table, colIndex), getColumnCellWidth(table, colIndex));
        width += 2 * margin;
        TableColumn column = table.getColumnModel().getColumn(colIndex);
        column.setPreferredWidth(width);
// todo        column.setMaxWidth(width);
    }

    private int getColumnCellWidth(JTable table, int colIndex) {
        int width = 0;
        for (int r = 0; r < table.getRowCount(); r++) {
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
