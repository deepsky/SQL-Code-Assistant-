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

package com.deepsky.gui;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.GridConstraints;
import com.deepsky.utils.CustomClassLoader;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.io.IOException;

public class SQLAssistanceConfigForm {
    private JComboBox libNameComboBox;
    private JButton button4;
    private JTextField driverName;
    private JPanel rootComponent;
    private JComboBox autocommitComboBox;

    final String sqlDriverClass = "java.sql.Driver";

    public SQLAssistanceConfigForm() {
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleLocalFileDescriptor();

//                descriptor.setTitle(DevKitBundle.message("sandbox.home"));
//                descriptor.setDescription(DevKitBundle.message("sandbox.purpose"));

//                String SANDBOX_HISTORY = "DEVKIT_SANDBOX_HISTORY";
//                TextFieldWithStoredHistory mySandboxHome = new TextFieldWithStoredHistory(SANDBOX_HISTORY);

                VirtualFile[] files = FileChooser.chooseFiles(getRootComponent(), descriptor);
                if (files.length != 0 && files[0].getExtension().equalsIgnoreCase("jar")) {
                    try {
                        String jarPath = files[0].getPath();
                        ClassLoader l = this.getClass().getClassLoader();
                        CustomClassLoader loader = new CustomClassLoader(l, jarPath);

                        Class target = Class.forName(sqlDriverClass);

                        JarFile zip = new JarFile(files[0].getPath());
                        Enumeration enumm = zip.entries();
                        int counter = 0;
                        while (enumm.hasMoreElements()) {
                            JarEntry o = (JarEntry) enumm.nextElement();
                            if (!o.isDirectory() && o.getName().endsWith(".class")) {
                                String name = o.getName();
                                String clazzName = o.getName().substring(0, o.getName().length() - 6);
                                clazzName = clazzName.replace('/', '.');
                                Class c = loader.loadClass(clazzName);
                                boolean res = isClassDerivedFrom(c, target);
                                if (res) {
                                    // driver found
                                    libNameComboBox.addItem(jarPath);
                                    libNameComboBox.setSelectedItem(jarPath);
                                    driverName.setText(clazzName);
                                    return;
                                }

                            }
                            int kk = 0;
                            counter++;
                        }

                        int hh = counter;
                    } catch (IOException e1) {
                        //e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (ClassNotFoundException e1) {
                        //e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

//                  mySandboxHome.setText(
//                    FileUtil.toSystemDependentName(files[0].getPath()));
                }

                //FileChooserFactory factory = com.intellij.openapi.fileChooser.FileChooserFactory.getInstance();
            }
        });
    }

    private boolean isClassDerivedFrom(Class probe, Class target) {
        Class[] ancestors = probe.getInterfaces();
        for (Class ancestor : ancestors) {
            if (target.getName().equals(ancestor.getName())) {
                return true;
            } else {
                if (isClassDerivedFrom(ancestor, target)) {
                    return true;
                } else {
                    //
                }
            }
        }

        return false;
    }


    public String getDriverName() {
        return driverName.getText();
    }

    public void setDriverName(String name) {
        driverName.setText(name);
    }

    public String getJdbcLibrary() {
        Object o = libNameComboBox.getSelectedItem();
        return (o == null) ? "" : o.toString();
    }

    public void setJdbcLibrary(String libName) {
        libNameComboBox.addItem(libName);
        libNameComboBox.setSelectedItem(libName);
    }

    public JComponent getRootComponent() {
        return rootComponent;
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
        rootComponent = new JPanel();
        rootComponent.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        rootComponent.add(panel1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        libNameComboBox = new JComboBox();
        libNameComboBox.setEditable(true);
        panel1.add(libNameComboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Oracle JDBC library:");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button4 = new JButton();
        button4.setText("...");
        panel1.add(button4, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(30, 30), 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("JDBC Driver Class:");
        panel1.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        driverName = new JTextField();
        driverName.setEnabled(false);
        panel1.add(driverName, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Autocommit:");
        panel1.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        autocommitComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("true");
        defaultComboBoxModel1.addElement("false");
        autocommitComboBox.setModel(defaultComboBoxModel1);
        panel1.add(autocommitComboBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootComponent;
    }
}
