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

package com.deepsky.settings;

import com.deepsky.database.ConnectionInfo;
import com.deepsky.database.ConnectionManager;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlSqlPluginSettingsForm {

    private JPanel root;
    private JTabbedPane tabbedPane1;
    private JComboBox tabNumberComboBox;
    private JComboBox fetchRecordsComboBox;
    private JComboBox dateFormatComboBox;
    private JComboBox timeFormatComboBox;
    //    private JComboBox timezonePresentation;
    private JCheckBox validateSyntax;
    private JCheckBox validateTableName;
    private JCheckBox validateFunc;
    private JCheckBox resolveRef;
    private JCheckBox resolveUdt;
    private JCheckBox validateInsert;
    private JCheckBox enableAccessOffline;
    private JCheckBox showRowCountingCheckBox;

    SqlCodeAssistantSettings settings;
    Project project;

    public PlSqlPluginSettingsForm(SqlCodeAssistantSettings settings, Project project) {
        // do something
        $$$setupUI$$$();

        this.project = project;
        this.settings = settings;

        dateFormatComboBox.setSelectedItem(settings.getDateFormat());
        timeFormatComboBox.setSelectedItem(settings.getTimeFormat());
        //timezonePresentation.setSelectedItem(settings.getTimeZoneFormat());
        tabNumberComboBox.setSelectedItem(Integer.toString(settings.getNumberOfTabs()));
        fetchRecordsComboBox.setSelectedItem(Integer.toString(settings.getFetchRecords()));

        validateSyntax.setSelected(settings.isHighlightSyntaxErrors());
        validateTableName.setSelected(settings.isValidateTables());
        validateFunc.setSelected(settings.isValidateFunc());
        resolveRef.setSelected(settings.isResolveReference());
        resolveUdt.setSelected(settings.isResolveUdt());

        enableAccessOffline.setSelected(settings.isAccessOfflineEnabled());
        showRowCountingCheckBox.setSelected(settings.getShowRowCounting());
    }

    public JComponent getRootComponent() {
        return root;
    }

    private void createUIComponents() {
        //timezonePresentation = new JComboBox();
        //timezonePresentation.setRenderer(new MyCellRenderer());
    }

    public void apply() {
        settings.setDateFormat((String) dateFormatComboBox.getSelectedItem());
        settings.setTimeFormat((String) timeFormatComboBox.getSelectedItem());
        //settings.setTimeZoneFormat((String) timezonePresentation.getSelectedItem());
        settings.setNumberOfTabs(Integer.parseInt((String) tabNumberComboBox.getSelectedItem()));
        settings.setFetchRecords(Integer.parseInt((String) fetchRecordsComboBox.getSelectedItem()));
        settings.setShowRowCounting(showRowCountingCheckBox.isSelected());

        if (enableAccessOffline.isSelected() != settings.isAccessOfflineEnabled()) {
            IndexManager indexManager = PluginKeys.SQL_INDEX_MAN.getData(project);
            ConnectionManager connMan = PluginKeys.CONNECTION_MANAGER.getData(project);

            boolean flag = enableAccessOffline.isSelected();
            DbUrl[] urls = new DbUrl[connMan.getSessionList().length];
            ConnectionInfo[] infos = connMan.getSessionList();
            for (int i = 0; i < infos.length; i++) {
                urls[i] = infos[i].getUrl();
            }
            indexManager.enableOfflineCache(urls, flag);

            settings.setAccessOfflineEnabled(flag);
        }


        if (isResolveStuffModified()) {
            // rerun annotator
            runCodeAnalyzer();
            // apply settings
            settings.setHighlightSyntaxErrors(validateSyntax.isSelected());
            settings.setValidateTables(validateTableName.isSelected());
            settings.setValidateFunc(validateFunc.isSelected());
            settings.setResolveReference(resolveRef.isSelected());
            settings.setResolveUdt(resolveUdt.isSelected());
        }
    }

    public void reset() {
        dateFormatComboBox.setSelectedItem(settings.getDateFormat());
        timeFormatComboBox.setSelectedItem(settings.getTimeFormat());
        //timezonePresentation.setSelectedItem(settings.getTimeZoneFormat());
        tabNumberComboBox.setSelectedItem(Integer.toString(settings.getNumberOfTabs()));
        fetchRecordsComboBox.setSelectedItem(Integer.toString(settings.getFetchRecords()));

        validateSyntax.setSelected(settings.isHighlightSyntaxErrors());
        validateTableName.setSelected(settings.isValidateTables());
        validateFunc.setSelected(settings.isValidateFunc());
        resolveRef.setSelected(settings.isResolveReference());
        resolveUdt.setSelected(settings.isResolveUdt());

        enableAccessOffline.setSelected(settings.isAccessOfflineEnabled());
    }

    private void runCodeAnalyzer() {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                if (!project.isDisposed()) {
                    DaemonCodeAnalyzer.getInstance(project).restart();
                }
            }
        });
    }

    private boolean isResolveStuffModified() {
        if (validateSyntax.isSelected() != settings.isHighlightSyntaxErrors()) {
            return true;
        } else if (validateTableName.isSelected() != settings.isValidateTables()) {
            return true;
        } else if (validateFunc.isSelected() != settings.isValidateFunc()) {
            return true;
        } else if (resolveRef.isSelected() != settings.isResolveReference()) {
            return true;
        } else if (resolveUdt.isSelected() != settings.isResolveUdt()) {
            return true;
        }
        return false;

    }

    public boolean isModified() {
        if (!dateFormatComboBox.getSelectedItem().equals(settings.getDateFormat())) {
            return true;
        } else if (!timeFormatComboBox.getSelectedItem().equals(settings.getTimeFormat())) {
            return true;
//        } else if (!timezonePresentation.getSelectedItem().equals(settings.getTimeZoneFormat())) {
//            return true;
        } else if (!tabNumberComboBox.getSelectedItem().equals(Integer.toString(settings.getNumberOfTabs()))) {
            return true;
        } else if (!fetchRecordsComboBox.getSelectedItem().equals(Integer.toString(settings.getFetchRecords()))) {
            return true;
        } else if (enableAccessOffline.isSelected() != settings.isAccessOfflineEnabled()) {
            return true;
        } else if (showRowCountingCheckBox.isSelected() != settings.getShowRowCounting()) {
            return true;
        }

        return isResolveStuffModified();
    }


/*
    class MyCellRenderer extends JLabel implements ListCellRenderer {
        public MyCellRenderer() {
            setOpaque(true);
            setHorizontalAlignment(JLabel.RIGHT);
        }

        public Component getListCellRendererComponent(JList list,
                                                      Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {

            setText(value.toString());

            Color background;
            Color foreground;

            // check if this cell represents the current DnD drop location
            JList.DropLocation dropLocation = list.getDropLocation();
            if (dropLocation != null
                    && !dropLocation.isInsert()
                    && dropLocation.getIndex() == index) {

                background = Color.BLUE;
                foreground = Color.WHITE;

                // check if this cell is selected
            } else if (isSelected) {
                background = Color.RED;
                foreground = Color.WHITE;

                // unselected, and not the DnD drop location
            } else {
                background = Color.WHITE;
                foreground = Color.BLACK;
            }
            ;

            setBackground(background);
            setForeground(foreground);

            return this;
        }
    }
*/

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        root = new JPanel();
        root.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1 = new JTabbedPane();
        root.add(tabbedPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Query Result Pane", panel1);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Number of Tabs:");
        panel2.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        tabNumberComboBox = new JComboBox();
        tabNumberComboBox.setEditable(false);
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("1");
        defaultComboBoxModel1.addElement("3");
        defaultComboBoxModel1.addElement("5");
        defaultComboBoxModel1.addElement("7");
        defaultComboBoxModel1.addElement("9");
        tabNumberComboBox.setModel(defaultComboBoxModel1);
        panel2.add(tabNumberComboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Fetch records:");
        panel3.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        fetchRecordsComboBox = new JComboBox();
        fetchRecordsComboBox.setEditable(false);
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("200");
        defaultComboBoxModel2.addElement("500");
        defaultComboBoxModel2.addElement("1000");
        fetchRecordsComboBox.setModel(defaultComboBoxModel2);
        panel3.add(fetchRecordsComboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Date format:");
        panel4.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        dateFormatComboBox = new JComboBox();
        dateFormatComboBox.setEditable(false);
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("yyyy-MM-dd");
        defaultComboBoxModel3.addElement("MM/dd/yyyy");
        defaultComboBoxModel3.addElement("dd/MM/yyyy");
        defaultComboBoxModel3.addElement("EEE, MMM d, yy");
        dateFormatComboBox.setModel(defaultComboBoxModel3);
        panel4.add(dateFormatComboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel5, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Time format:");
        panel5.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        timeFormatComboBox = new JComboBox();
        timeFormatComboBox.setEditable(false);
        timeFormatComboBox.setEnabled(true);
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        defaultComboBoxModel4.addElement("HH:mm:ss");
        defaultComboBoxModel4.addElement("h:mm:ss a");
        defaultComboBoxModel4.addElement("K:mm a");
        timeFormatComboBox.setModel(defaultComboBoxModel4);
        panel5.add(timeFormatComboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setEnabled(true);
        panel6.setVisible(true);
        panel1.add(panel6, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        showRowCountingCheckBox = new JCheckBox();
        showRowCountingCheckBox.setSelected(true);
        showRowCountingCheckBox.setText("Show Row Counting");
        panel6.add(showRowCountingCheckBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("SQL Code Editor", panel7);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel7.add(panel8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Check Syntax");
        panel8.add(label5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        validateSyntax = new JCheckBox();
        validateSyntax.setSelected(true);
        validateSyntax.setText("");
        panel8.add(validateSyntax, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel7.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Resolver", panel9);
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel9.add(panel10, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Validate table/view names");
        panel10.add(label6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        validateTableName = new JCheckBox();
        validateTableName.setSelected(true);
        validateTableName.setText("");
        panel10.add(validateTableName, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel9.add(panel11, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Validate function/procedure call signature");
        panel11.add(label7, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        validateFunc = new JCheckBox();
        validateFunc.setSelected(true);
        validateFunc.setText("");
        panel11.add(validateFunc, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel9.add(panel12, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Resolve referencies");
        panel12.add(label8, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        resolveRef = new JCheckBox();
        resolveRef.setSelected(true);
        resolveRef.setText("");
        panel12.add(resolveRef, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel9.add(panel13, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Resolve User Defined Types");
        panel13.add(label9, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        resolveUdt = new JCheckBox();
        resolveUdt.setSelected(true);
        resolveUdt.setText("");
        panel13.add(resolveUdt, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel14.setEnabled(false);
        panel9.add(panel14, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setEnabled(false);
        label10.setText("Validate INSERT statement:");
        panel14.add(label10, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        validateInsert = new JCheckBox();
        validateInsert.setEnabled(false);
        validateInsert.setSelected(false);
        validateInsert.setText("");
        panel14.add(validateInsert, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel9.add(spacer3, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Object Cache", panel15);
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel15.add(panel16, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        enableAccessOffline = new JCheckBox();
        enableAccessOffline.setText("Enable access to database objects in OFFLINE mode");
        panel16.add(enableAccessOffline, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel16.add(spacer4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel15.add(spacer5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }
}
