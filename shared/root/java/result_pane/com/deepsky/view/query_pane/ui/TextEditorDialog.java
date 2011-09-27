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

package com.deepsky.view.query_pane.ui;

import com.deepsky.utils.StringUtils;
import com.deepsky.view.query_pane.ConversionException;
import com.deepsky.view.query_pane.DataAccessor;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.fileChooser.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;
import org.codehaus.groovy.util.StringUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class TextEditorDialog extends DialogWrapper {
    private JTextArea textArea1;
    private JPanel rootPanel;
    private JButton copyToClipboardBttn;
    private JButton saveToFileBttn;
    private JScrollPane scrollPane;
    private JButton loadFromFileBttn;


    //Project project;
    DataAccessor accessor;
//    private boolean isReadOnly;

    public TextEditorDialog(final Project project, String columnName, DataAccessor accessor) throws SQLException {
        super(project);
        this.accessor = accessor;

        $$$setupUI$$$();
        this.setTitle("Column " + columnName.toUpperCase());

        scrollPane.setPreferredSize(new Dimension(560, 170));

        //setOKButtonText("Close");

        loadFromFileBttn.setEnabled(!accessor.isReadOnly());
        textArea1.setEditable(!accessor.isReadOnly());
        //
        init();

        copyToClipboardBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // save in Clipboard
                StringSelection stsel = new StringSelection(textArea1.getText());
                Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
                system.setContents(stsel, stsel);
            }
        });
        // handle "Save to file" action
        saveToFileBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // save in the file
                FileSaverDescriptor fileSaverDescriptor = new FileSaverDescriptor(
                        "Save Column Value in the file", "", new String[0]
                );
                FileSaverDialog dialog = FileChooserFactory.getInstance().createSaveFileDialog(fileSaverDescriptor, project);
                // todo -- use selected directory previously as a start directory
                VirtualFileWrapper f = dialog.save(null, null);
                if (f != null) {
                    File saveTo = f.getFile();
                    try {
                        StringUtils.string2file(textArea1.getText(), saveTo);
                    } catch (IOException e1) {
                        Messages.showErrorDialog(project,
                                "Could not save the column value in the file: " + saveTo
                                , "File saving failed");
                    }
                }
            }
        });

        // handle "Load from file" action
        loadFromFileBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, false, false, false, false);
                FileChooserDialog dialog = FileChooserFactory.getInstance().createFileChooser(descriptor, project);
                // todo -- use selected directory previously as a start directory
                VirtualFile[] chosen = dialog.choose(null, project);
                if (chosen.length == 1) {
                    try {
                        File f = new File(chosen[0].getPath());
                        textArea1.setText(StringUtils.file2string(f));

                    } catch (IOException e1) {
                        Messages.showErrorDialog(project,
                                "Could not open file for reading : " + chosen[0].getPath()
                                , "File opening failed");
                    }
                }
            }
        });

        String text = accessor.convertToString();
        textArea1.setText(text);
        textArea1.setCaretPosition(text.length());
    }

    @Nullable
    public JComponent getPreferredFocusedComponent() {
        if (!accessor.isReadOnly()) {
            return textArea1;
        } else {
            return null;
        }
    }

    protected Action[] createActions() {
        return new Action[]{getOKAction(), getCancelAction()};
    }

    protected void doOKAction() {
        if (!accessor.isReadOnly() && getOKAction().isEnabled()) {
            try {
                accessor.loadFromString(textArea1.getText());
            } catch (ConversionException e) {
                // todo -- handle error
                e.printStackTrace();
            }
        }

        super.doOKAction();
    }

    @Override
    protected JComponent createCenterPanel() {
        return rootPanel;
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        rootPanel.add(panel1, BorderLayout.CENTER);
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Column Value"));
        scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(30);
        scrollPane.setPreferredSize(new Dimension(245, 94));
        panel1.add(scrollPane, BorderLayout.CENTER);
        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        scrollPane.setViewportView(textArea1);
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setFloatable(false);
        toolBar1.setRollover(true);
        toolBar1.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
        rootPanel.add(toolBar1, BorderLayout.NORTH);
        copyToClipboardBttn = new JButton();
        copyToClipboardBttn.setAutoscrolls(false);
        copyToClipboardBttn.setBorderPainted(false);
        copyToClipboardBttn.setFocusPainted(false);
        copyToClipboardBttn.setIcon(new ImageIcon(getClass().getResource("/icons/copy.png")));
        copyToClipboardBttn.setInheritsPopupMenu(true);
        copyToClipboardBttn.setMargin(new Insets(0, 0, 0, 0));
        copyToClipboardBttn.setRolloverEnabled(true);
        copyToClipboardBttn.setText("");
        copyToClipboardBttn.setToolTipText("Copy value to Clipboard");
        toolBar1.add(copyToClipboardBttn);
        saveToFileBttn = new JButton();
        saveToFileBttn.setBorderPainted(false);
        saveToFileBttn.setEnabled(true);
        saveToFileBttn.setFocusCycleRoot(false);
        saveToFileBttn.setFocusable(false);
        saveToFileBttn.setHideActionText(true);
        saveToFileBttn.setIcon(new ImageIcon(getClass().getResource("/icons/save2file.png")));
        saveToFileBttn.setLabel("");
        saveToFileBttn.setMargin(new Insets(0, 0, 0, 0));
        saveToFileBttn.setRolloverEnabled(true);
        saveToFileBttn.setText("");
        saveToFileBttn.setToolTipText("Save value in the file");
        toolBar1.add(saveToFileBttn);
        loadFromFileBttn = new JButton();
        loadFromFileBttn.setBorderPainted(false);
        loadFromFileBttn.setEnabled(true);
        loadFromFileBttn.setFocusCycleRoot(false);
        loadFromFileBttn.setFocusable(false);
        loadFromFileBttn.setHideActionText(true);
        loadFromFileBttn.setIcon(new ImageIcon(getClass().getResource("/icons/loadFromFile.png")));
        loadFromFileBttn.setLabel("");
        loadFromFileBttn.setMargin(new Insets(0, 0, 0, 0));
        loadFromFileBttn.setRolloverEnabled(true);
        loadFromFileBttn.setText("");
        loadFromFileBttn.setToolTipText("Load from file");
        toolBar1.add(loadFromFileBttn);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }


    public class DependedToggleAction extends ToggleAction {
        boolean inverse;

        public DependedToggleAction(String actionName, String toolTip, Icon icon, int command, boolean inverse) {
            //super(actionName, toolTip, icon, command);
            boolean enabled = true;
            this.getTemplatePresentation().setEnabled(enabled);
            this.inverse = inverse;
        }

        public boolean isSelected(AnActionEvent event) {
//            String selected = (String) connectionComboBox.getSelectedItem();
//            if (selected != null && connector.isConnected(selected)) {
//                event.getPresentation().setEnabled(!inverse);
//            } else {
//                event.getPresentation().setEnabled(inverse);
//            }
            return false;
        }

        @Override
        public void setSelected(AnActionEvent e, boolean state) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }


}
