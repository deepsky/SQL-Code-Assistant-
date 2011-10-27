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

import com.deepsky.utils.FileUtils;
import com.deepsky.utils.StringUtils;
import com.deepsky.view.query_pane.ColumnReadException;
import com.deepsky.view.query_pane.ConversionException;
import com.deepsky.view.query_pane.DataAccessor;
import com.deepsky.view.query_pane.converters.ConversionUtil;
import com.intellij.openapi.fileChooser.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;

public class BinaryEditorDialog extends DialogWrapper {
    private JTextArea textArea1;
    private JButton copyToCB_HexBttn;
    private JButton saveToFileBttn;
    private JPanel rootPanel;
    private JScrollPane scrollPane;
    private JButton loadFromFileBttn;

    private DataAccessor accessor;


    public BinaryEditorDialog(final Project project, String columnName, DataAccessor _accessor) throws ColumnReadException {
        super(project);
        this.accessor = _accessor;

        $$$setupUI$$$();
        this.setTitle("Column " + columnName.toUpperCase());
        scrollPane.setPreferredSize(new Dimension(560, 170));

        //setOKButtonText("Close");
        try {
            // Check value size if it exceeds 100K Notice and fail opening
            if (accessor.size() > 100000) {
                Messages.showErrorDialog(project,
                        "Loading of the values with size more then 100K is not supported at the moment",
                        "Reading column value failed"
                );
                throw new ColumnReadException();
            }
            textArea1.setText(accessor.convertToString());
        } catch (SQLException e) {
            Messages.showErrorDialog(project, "Cannot open column value in the editor.", "Reading column value failed");
            throw new ColumnReadException();
        }

        init();
        textArea1.setEditable(!accessor.isReadOnly());

//        getOKAction().addPropertyChangeListener(new PropertyChangeListener() {
//            public void propertyChange(PropertyChangeEvent evt) {
//                int hh = 0;
//            }
//        });
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
                        String text = ((HexTextArea) textArea1).getMergedText();
                        byte[] temp = ConversionUtil.convertHEXString2ByteArray(text);
                        OutputStream out = new FileOutputStream(saveTo);
                        out.write(temp);
                        out.close();
                    } catch (IOException e1) {
                        Messages.showErrorDialog(project,
                                "Could not save the column value in the file: " + saveTo
                                , "File saving failed");
                    }
                }
            }
        });
        loadFromFileBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, false, false, false, false);
                FileChooserDialog dialog = FileChooserFactory.getInstance().createFileChooser(descriptor, project);
                // todo -- use selected directory previously as a start directory
                VirtualFile[] chosen = dialog.choose(null, project);
                if (chosen.length == 1) {
                    try {
                        File f = new File(chosen[0].getPath());
                        InputStream in = new FileInputStream(f);
                        String encodedBytes = ConversionUtil.convertBinaryStream2HEXString(in);
                        textArea1.setText(encodedBytes);
                    } catch (IOException e1) {
                        Messages.showErrorDialog(project,
                                "Could not open file for reading : " + chosen[0].getPath()
                                , "File opening failed");
                    }
                }
            }
        });
        copyToCB_HexBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // save in Clipboard
                StringSelection stsel = new StringSelection(textArea1.getText());
                Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
                system.setContents(stsel, stsel);
            }
        });
    }

    protected void doOKAction() {
        // Save content if accessor is not ReadOnly
        if (!accessor.isReadOnly() && getOKAction().isEnabled()) {
            try {
                accessor.loadFromString(((HexTextArea) textArea1).getMergedText());
            } catch (ConversionException e) {
                // todo -- handle error
                e.printStackTrace();
            }
        }

        super.doOKAction();
    }

    @Nullable
    public JComponent getPreferredFocusedComponent() {
        if (!accessor.isReadOnly()) {
            return textArea1;
        } else {
            return null;
        }
    }

    @Override
    protected JComponent createCenterPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        textArea1 = new HexTextArea();
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
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setFloatable(false);
        toolBar1.setRollover(true);
        toolBar1.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
        rootPanel.add(toolBar1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        copyToCB_HexBttn = new JButton();
        copyToCB_HexBttn.setAutoscrolls(false);
        copyToCB_HexBttn.setBorderPainted(false);
        copyToCB_HexBttn.setFocusPainted(false);
        copyToCB_HexBttn.setIcon(new ImageIcon(getClass().getResource("/icons/copy.png")));
        copyToCB_HexBttn.setInheritsPopupMenu(true);
        copyToCB_HexBttn.setMargin(new Insets(0, 0, 0, 0));
        copyToCB_HexBttn.setRolloverEnabled(true);
        copyToCB_HexBttn.setText("");
        copyToCB_HexBttn.setToolTipText("Copy value to Clipboard");
        toolBar1.add(copyToCB_HexBttn);
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
        loadFromFileBttn.setToolTipText("Save value in the file");
        toolBar1.add(loadFromFileBttn);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder("ColumnValue"));
        scrollPane = new JScrollPane();
        panel1.add(scrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane.setViewportView(textArea1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
