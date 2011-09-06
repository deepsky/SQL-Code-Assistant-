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

package com.deepsky.view.schema_pane.ui;

import com.deepsky.lang.plsql.objTree.DbTreeElement;
import com.deepsky.lang.plsql.objTree.DetailsTableModel;
import com.deepsky.view.schema_pane.tree.TypeTypeRoot;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class TypeTabForm extends TabFormBase {
    private JPanel rootPanel;
    private JTree centralTree;
    private JTabbedPane tabbedPane1;
    private JTable detailsTable;
    private JSplitPane splitPane;
    private JPanel bottomPanel;
    private JPanel top;

    TypeTypeRoot packageType;

    public TypeTabForm(TypeTypeRoot tableType) {
        this.packageType = tableType;

        rootPanel.putClientProperty(TEST501_COMPONENT, this);
        init(tableType);
    }

    public JComponent getRootPanel() {
        return rootPanel;
    }

    @Override
    public JPanel getTopPanel() {
        return top;
    }

    @Override
    public JTree getCentralTree() {
        return centralTree;
    }

    public JSplitPane getSplitPane() {
        return splitPane;
    }

    protected void updatePropertySheet(Object lastSelectedComponent) {
        // update Detail Tab
        TableModel detailsModel;
        if (lastSelectedComponent != null) {
            detailsModel = ((DbTreeElement) lastSelectedComponent).getProperties(); //DetailProps();
        } else {
            detailsModel = new DetailsTableModel(new String[]{"Property", "Value"});
        }

        detailsTable.setModel(detailsModel);
        bottomPanel.validate();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout(0, 0));
        splitPane = new JSplitPane();
        splitPane.setOrientation(0);
        rootPanel.add(splitPane, BorderLayout.CENTER);
        top = new JPanel();
        top.setLayout(new BorderLayout(0, 0));
        splitPane.setLeftComponent(top);
        final JScrollPane scrollPane1 = new JScrollPane();
        top.add(scrollPane1, BorderLayout.CENTER);
        centralTree = new JTree();
        centralTree.setRootVisible(false);
        centralTree.setShowsRootHandles(true);
        scrollPane1.setViewportView(centralTree);
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout(0, 0));
        splitPane.setRightComponent(bottomPanel);
        tabbedPane1 = new JTabbedPane();
        bottomPanel.add(tabbedPane1, BorderLayout.CENTER);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        tabbedPane1.addTab("Properties", panel1);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel1.add(scrollPane2, BorderLayout.CENTER);
        detailsTable = new JTable();
        scrollPane2.setViewportView(detailsTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}