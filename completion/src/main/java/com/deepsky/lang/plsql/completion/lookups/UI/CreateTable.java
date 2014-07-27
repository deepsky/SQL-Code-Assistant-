/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.completion.lookups.UI;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTable extends ParamProviderPopup {
    private JTextField tableName;
    private JRadioButton regularTableButton;
    private JRadioButton tempTableButton;
    private JRadioButton asSelectButton;
    private JButton cancelButton;
    private JButton OKButton;
    private JPanel rootPanel;
    private JCheckBox addIDToRegularCheck;
    private JCheckBox addIDToTempCheck;
    private JPanel regularTablePanel;
    private JPanel tempTablePanel;
    private JPanel asSelectPanel;
    private JCheckBox globalCheckBox;
    private JRadioButton preserveRadioButton;
    private JRadioButton deleteRadioButton;
    private JTextField tablespaceTextField;
    private JCheckBox asSelectParallelCheck;
    private JCheckBox tempTableAsSelectCheck;
    private JLabel tablespaceLabel;

    public CreateTable(String defaultTableName) {
        super("Create table");

        tableName.setText(defaultTableName);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireOKevent();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireCancelEvent();
            }
        });

        OKButton.addKeyListener(new KeyListener());
        tableName.addKeyListener(new KeyListener());
        tablespaceTextField.addKeyListener(new KeyListener());

        regularTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addIDToRegularCheck.setEnabled(true);
                addIDToTempCheck.setEnabled(false);

                preserveRadioButton.setEnabled(false);
                deleteRadioButton.setEnabled(false);
                globalCheckBox.setEnabled(false);
                asSelectParallelCheck.setEnabled(false);
                tempTableAsSelectCheck.setEnabled(false);
            }
        });
        tempTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addIDToRegularCheck.setEnabled(false);
                addIDToTempCheck.setEnabled(true);

                preserveRadioButton.setEnabled(true);
                deleteRadioButton.setEnabled(true);
                globalCheckBox.setEnabled(true);
                asSelectParallelCheck.setEnabled(false);
                tempTableAsSelectCheck.setEnabled(true);
            }
        });
        asSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addIDToRegularCheck.setEnabled(false);
                addIDToTempCheck.setEnabled(false);

                preserveRadioButton.setEnabled(false);
                deleteRadioButton.setEnabled(false);
                globalCheckBox.setEnabled(false);
                asSelectParallelCheck.setEnabled(true);
                tempTableAsSelectCheck.setEnabled(false);
            }
        });
        tempTableAsSelectCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tempTableAsSelectCheck.isSelected()) {
                    addIDToTempCheck.setEnabled(false);
                } else {
                    addIDToTempCheck.setEnabled(true);
                }
            }
        });
    }


    public String getStatementText() {
        StringBuilder b = new StringBuilder("create ");
        if (regularTableButton.isSelected()) {
            b.append("table ").append(tableName.getText()).append(" (\n");
            b.append("id number primary key\n");
            b.append("-- Add more columns here\n");
            b.append(" ) ");

            if (tablespaceTextField.getText().length() > 0) {
                b.append("\ntablespace ").append(tablespaceTextField.getText());
            }

        } else if (tempTableButton.isSelected()) {
            if (globalCheckBox.isSelected()) {
                b.append("global ");
            }
            b.append("temporary table ").append(tableName.getText());
            if (tempTableAsSelectCheck.isSelected()) {
                b.append("\non commit ");
                if (preserveRadioButton.isSelected()) b.append("preserve ");
                else b.append("delete ");
                b.append("rows\n");

                if (tablespaceTextField.getText().length() > 0) {
                    b.append("tablespace ").append(tablespaceTextField.getText()).append("\n");
                }
                b.append("as select * from table1");
            } else {
                b.append(" (\n");
                b.append("\tid number primary key\n");
                b.append("\t-- Add more columns here\n");
                b.append(") on commit ");
                if (preserveRadioButton.isSelected()) b.append("preserve ");
                else b.append("delete ");
                b.append("rows");

                if (tablespaceTextField.getText().length() > 0) {
                    b.append("\ntablespace ").append(tablespaceTextField.getText());
                }
            }

        } else {
            // AS SELECT selected
            b.append("table ").append(tableName.getText()).append("\n");
            if (tablespaceTextField.getText().length() > 0) {
                b.append("tablespace ").append(tablespaceTextField.getText()).append("\n");
            }
            if (asSelectParallelCheck.isSelected()) {
                b.append("parallel\n");
            }
            b.append("as select * from table1");
        }

        b.append(";\n");
        return b.toString();
    }

    @Override
    public JComponent getRootComponent() {
        return rootPanel;
    }

    @Override
    public JComponent getFocusedComponent() {
        return tableName;
    }

    @Override
    public String getName() {
        return tableName.getText();
    }

    public boolean isIDAutogenerated() {
        return regularTableButton.isSelected() ? addIDToRegularCheck.isSelected() : addIDToTempCheck.isSelected();
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
        rootPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(8, 6, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(panel1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), null));
        final JLabel label1 = new JLabel();
        label1.setText("Name:");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tableName = new JTextField();
        panel1.add(tableName, new GridConstraints(0, 1, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        regularTableButton = new JRadioButton();
        regularTableButton.setSelected(true);
        regularTableButton.setText("Regular table");
        panel1.add(regularTableButton, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        regularTablePanel = new JPanel();
        regularTablePanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(regularTablePanel, new GridConstraints(2, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        regularTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null));
        addIDToRegularCheck = new JCheckBox();
        addIDToRegularCheck.setSelected(true);
        addIDToRegularCheck.setText("Add ID Column");
        regularTablePanel.add(addIDToRegularCheck, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tempTableButton = new JRadioButton();
        tempTableButton.setText("Temporary table");
        panel1.add(tempTableButton, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tempTablePanel = new JPanel();
        tempTablePanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(tempTablePanel, new GridConstraints(4, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tempTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        addIDToTempCheck = new JCheckBox();
        addIDToTempCheck.setEnabled(false);
        addIDToTempCheck.setSelected(true);
        addIDToTempCheck.setText("Add ID Column");
        tempTablePanel.add(addIDToTempCheck, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        globalCheckBox = new JCheckBox();
        globalCheckBox.setEnabled(false);
        globalCheckBox.setText("Global");
        tempTablePanel.add(globalCheckBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        tempTablePanel.add(panel2, new GridConstraints(0, 1, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "On Commit"));
        preserveRadioButton = new JRadioButton();
        preserveRadioButton.setEnabled(false);
        preserveRadioButton.setSelected(true);
        preserveRadioButton.setText("Preserve");
        panel2.add(preserveRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        deleteRadioButton = new JRadioButton();
        deleteRadioButton.setEnabled(false);
        deleteRadioButton.setText("Delete");
        panel2.add(deleteRadioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tempTableAsSelectCheck = new JCheckBox();
        tempTableAsSelectCheck.setEnabled(false);
        tempTableAsSelectCheck.setText("AS SELECT");
        tempTablePanel.add(tempTableAsSelectCheck, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        asSelectButton = new JRadioButton();
        asSelectButton.setText("Create table AS SELECT");
        panel1.add(asSelectButton, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        asSelectPanel = new JPanel();
        asSelectPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(asSelectPanel, new GridConstraints(6, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        asSelectPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null));
        asSelectParallelCheck = new JCheckBox();
        asSelectParallelCheck.setEnabled(false);
        asSelectParallelCheck.setText("Parallel");
        asSelectPanel.add(asSelectParallelCheck, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(panel3, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), null));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panel3.add(cancelButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        OKButton = new JButton();
        OKButton.setText("   OK   ");
        panel3.add(OKButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel3.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(panel4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Optional"));
        tablespaceLabel = new JLabel();
        tablespaceLabel.setText("Tablespace:");
        panel4.add(tablespaceLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tablespaceTextField = new JTextField();
        panel4.add(tablespaceTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(regularTableButton);
        buttonGroup.add(tempTableButton);
        buttonGroup.add(asSelectButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(deleteRadioButton);
        buttonGroup.add(preserveRadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
