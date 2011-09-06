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

package com.deepsky.findUsages.ui;

import com.deepsky.findUsages.options.AbstractSqlSearchOptions;
import com.deepsky.findUsages.options.SqlSearchOptions;
import com.intellij.find.FindSettings;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.ex.WindowManagerEx;
import com.intellij.openapi.wm.impl.IdeFrameImpl;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.ScreenUtil;
import com.intellij.ui.StateRestoringCheckBox;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class SQLFindUsagesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox openInNewTabCheckBox;
    private JLabel elementToLookFor;
    private JLabel searchScope;
    private JPanel customePanel;
    private JPanel nouthPanel;

    private final ActionListener myUpdateAction;

    private SQLFindUsagesDialog() {
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        myUpdateAction = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                update();
            }
        };
    }


    Project project;

    public SQLFindUsagesDialog(Project project, String elemToLookFor, String searchScope) {
        this();
        this.project = project;
        this.setTitle("Find Usages");

        this.elementToLookFor.setText(elemToLookFor);
        this.searchScope.setText(searchScope);
//        init();
//        validate();

    }

    protected void update() {
    }

    boolean OK = false;
    private JPanel findWhatPanel;

    public SQLFindUsagesDialog(Project project, AbstractSqlSearchOptions searchOptions) {
        this();
        this.project = project;
        this.setTitle("Find Usages");

        this.elementToLookFor.setText(searchOptions.getPresentableSearchParameters());
        this.searchScope.setText(searchOptions.getTargetSchema().getAlias());
        findWhatPanel = createFindWhatPanel(searchOptions.getSearchOptions());
    }

    private JPanel createFindWhatPanel(SqlSearchOptions.SearchOption[] options) {
        if (options.length == 0) {
            return null;
        }

        JPanel findPanel = new JPanel();
        findPanel.setBorder(IdeBorderFactory.createTitledBorder("Find"));
        findPanel.setLayout(new BoxLayout(findPanel, BoxLayout.Y_AXIS));
//        addUsagesOptions(findPanel);

        for (SqlSearchOptions.SearchOption op : options) {
            StateRestoringCheckBox sb = addCheckboxToPanel(op.getOptionName(), FindSettings.getInstance().isSkipResultsWithOneUsage(), findPanel, false);
            sb.setSelected(op.isEnabled());
            optionsList.add(new Pair(sb, op));
        }

        return findPanel;
    }


    protected StateRestoringCheckBox addCheckboxToPanel(String name, boolean toSelect, JPanel panel, boolean toUpdate) {
        StateRestoringCheckBox cb = new StateRestoringCheckBox(name);
        cb.setSelected(toSelect);
        panel.add(cb);
        if (toUpdate) {
            cb.addActionListener(myUpdateAction);
        }
        return cb;
    }

    List<Pair> optionsList = new ArrayList<Pair>();

    void t() {
        customePanel = new JPanel();
        customePanel.setLayout(new BorderLayout(0, 0));
        customePanel.setVisible(true);
//        nouthPanel.add(customePanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));

        final JPanel options = new JPanel();
        options.setBorder(BorderFactory.createTitledBorder("Options"));
        options.setLayout(new BorderLayout(0, 0));
        JCheckBox checkBox1 = new JCheckBox();
        checkBox1.setText("Skip results tab with one usage");
        options.add(checkBox1, BorderLayout.CENTER);

        if (findWhatPanel != null) {
            customePanel.add(findWhatPanel, BorderLayout.CENTER);
            customePanel.add(options, BorderLayout.EAST);
        } else {
            customePanel.add(options, BorderLayout.CENTER);
        }


    }


    private void createUIComponents() {
        customePanel = new JPanel();
        customePanel.setLayout(new BorderLayout(0, 0));
        customePanel.setVisible(true);

        final JPanel options = new JPanel();
        options.setBorder(BorderFactory.createTitledBorder("Options"));
        options.setLayout(new BorderLayout(0, 0));
        JCheckBox checkBox1 = new JCheckBox();
        checkBox1.setText("Skip results tab with one usage");
        options.add(checkBox1, BorderLayout.CENTER);

        if (findWhatPanel != null) {
            customePanel.add(findWhatPanel, BorderLayout.CENTER);
            customePanel.add(options, BorderLayout.EAST);
        } else {
            customePanel.add(options, BorderLayout.CENTER);
        }
//        final JPanel options = new JPanel();
//        options.setBorder(BorderFactory.createTitledBorder("Options"));
//        options.setLayout(new BorderLayout(0, 0));
//        customePanel.add(options, BorderLayout.CENTER);
//        JCheckBox checkBox1 = new JCheckBox();
//        checkBox1.setText("Skip results tab with one usage");
//        options.add(checkBox1, BorderLayout.CENTER);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setIcon(new ImageIcon(getClass().getResource("/icons/find.png")));
        buttonOK.setText("Find");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nouthPanel = new JPanel();
        nouthPanel.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(nouthPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        elementToLookFor = new JLabel();
        elementToLookFor.setText("Label");
        nouthPanel.add(elementToLookFor, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        customePanel.setVisible(true);
        nouthPanel.add(customePanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        nouthPanel.add(panel3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        searchScope = new JLabel();
        searchScope.setText("Particular Scope");
        panel3.add(searchScope, BorderLayout.CENTER);
        final JLabel label1 = new JLabel();
        label1.setText("Scope:   ");
        panel3.add(label1, BorderLayout.WEST);
        openInNewTabCheckBox = new JCheckBox();
        openInNewTabCheckBox.setSelected(false);
        openInNewTabCheckBox.setText("Open in new tab");
        nouthPanel.add(openInNewTabCheckBox, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

    /*
        private void createUIComponents() {
            // TODO: place custom component creation code here
            customePanel = new JPanel();
    //        customePanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    //        customePanel.setVisible(true);
    //        nouthPanel.add(customePanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    //        customePanel.setBorder(BorderFactory.createTitledBorder("Options"));
    //        skipResultsTabWithCheckBox = new JCheckBox();
    //        skipResultsTabWithCheckBox.setText("Skip results tab with one usage");
    //        customePanel.add(skipResultsTabWithCheckBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        }
    */


    private class Pair {
        public Pair(StateRestoringCheckBox sb, SqlSearchOptions.SearchOption op) {
            this.sb = sb;
            this.op = op;
        }

        public StateRestoringCheckBox sb;
        public SqlSearchOptions.SearchOption op;
    }


    public void show0() {
        pack();

        WindowManagerEx myWindowManager = null;
        Application application = ApplicationManager.getApplication();
        if (application != null && application.hasComponent(WindowManager.class)) {
            myWindowManager = (WindowManagerEx) WindowManager.getInstance();
        }

        if (myWindowManager != null) {

            if (project == null) {
                project = PlatformDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
            }


            Window window = myWindowManager.suggestParentWindow(project);
            if (window == null) {
                Window focusedWindow = myWindowManager.getMostRecentFocusedWindow();
                if (focusedWindow instanceof IdeFrameImpl) {
                    window = focusedWindow;
                }
            }

            setLocationRelativeTo(window);
        }


//        setLocationRelativeTo(getOwner());

        final Rectangle bounds = getBounds();
        ScreenUtil.fitToScreen(bounds);
        setBounds(bounds);

        setVisible(true);
    }


//    protected JComponent createCenterPanel() {
//        return contentPane;
//    }

    public boolean isOK() {
        return OK;
    }


    private void onOK() {
        OK = true;
        dispose();
    }

    private void onCancel() {
        OK = false;
        dispose();
    }

    public static void main(String[] args) {
        SQLFindUsagesDialog dialog = new SQLFindUsagesDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public boolean isShowInSeparateWindow() {
        return openInNewTabCheckBox.isSelected();
    }

}
