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

package com.deepsky.gui2;

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.ConnectionStatus;
import com.deepsky.database.ora.*;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.parser.tns.TNSTypesAdopted;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.PathUtils;
import com.deepsky.lang.PsiUtil;
import com.deepsky.lang.tnsnames.psi.NetServiceDesc;
import com.deepsky.lang.tnsnames.tree.TNSFileParser;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.deepsky.utils.StringUtils;
import com.deepsky.view.utils.ProgressIndicatorHelper;
import com.deepsky.view.utils.ProgressIndicatorListener;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.psi.tree.TokenSet;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ConnectionSettings3 extends DialogWrapper implements ActionListener {
    private JPanel rootComponent;
    private JCheckBox loginOnStartupCheckBox;
    private JButton testConnectionButton;
    private JComboBox jdbcHost;
    private JTextField serviceName;
    private JTextField sid;
    //    private JTextField port1;
    private JTextField userName;
    //    private JTextField password;
    private JCheckBox reconnectIfConnectionDroppedCheckBox;
    private JTextField refreshPeriod;
    private JPasswordField passwordField;
    private JRadioButton SIDRadio;
    private JRadioButton serviceNameRadio;
    private JTextField jdbcPort;
    private JTextField tnsFilePathText;
    private JButton tnsChooseFileBttn;
    private JComboBox tnsServiceIdCombo;
    private JRadioButton JDBCRadioButton;
    private JRadioButton TNSNamesRadioButton;
    private JLabel tnsFilePathLabel;
    private JLabel tnsServiceIdLabel;
    private JPanel tnsNamesPanel;
    private JPanel jdbcPanel;
    private JLabel jdbcHostLabel;
    private JLabel jdbcPortLabel;
    private JPanel sidServicePanel;
    private JButton tnsShowCntBtn;

    final String TEST_CONNECTION = "Test Connection1";

    private Project project;
    private ASTNode selectNetworkService;
    private boolean createNewConnection = false;

    private ConnectionSettings3(final Project project) {
        super(project, false);
        this.project = project;
        this.setTitle("Setup Connection");

        SIDRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // SID chosen
                serviceName.setEnabled(false);
                sid.setEnabled(true);
            }
        });
        serviceNameRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // SERVICE NAME chosen
                sid.setEnabled(false);
                serviceName.setEnabled(true);
            }
        });
        JDBCRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableJDBCPanel();
            }
        });
        TNSNamesRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableTNSPanel();
            }
        });
        tnsChooseFileBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SqlCodeAssistantSettings settings = PluginKeys.PLUGIN_SETTINGS.getData(project);
                String path = null;
                if (tnsFilePathText.getText().length() == 0) {
                    path = settings.getDefaultTNSFilePath();
                } else {
                    path = tnsFilePathText.getText();
                }

                File file = PathUtils.chooseTNSNamesSource(project, path);
                if (file != null) {
                    settings.setDefaultTNSFilePath(file.getPath());
                }
                parseTNSFile(file);
            }
        });
        tnsShowCntBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tnsFilePathText.getText().length() == 0) {
                    // TODO - show error?
                } else {
                    // Check file
                    File tnsFile = new File(tnsFilePathText.getText());
                    if (tnsFile.exists() && tnsFile.isFile()) {
                        try {
                            String content = StringUtils.file2string(tnsFile);
                            String[] items = new String[tnsServiceIdCombo.getItemCount()];
                            for (int i = 0; i < items.length; i++) {
                                items[i] = tnsServiceIdCombo.getItemAt(i).toString();
                            }
                            JBPopup popup = ShowTNSNamesFile.createTNSNamesPopup(
                                    items, tnsFile.getPath(), content);
                            popup.show(rootComponent);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

        tnsServiceIdCombo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                System.out.println("Item changed: " + e.getItem());
                selectNetworkService = ((NetServiceItem) e.getItem()).getNode();
            }
        });
    }


    private void enableJDBCPanel() {
        tnsNamesPanel.setEnabled(false);
        tnsChooseFileBttn.setEnabled(false);
        tnsFilePathLabel.setEnabled(false);
        tnsFilePathText.setEnabled(false);
        tnsServiceIdCombo.setEnabled(false);
        tnsServiceIdLabel.setEnabled(false);
        tnsShowCntBtn.setEnabled(false);

        jdbcPanel.setEnabled(true);
        sidServicePanel.setEnabled(true);
        jdbcHostLabel.setEnabled(true);
        jdbcPortLabel.setEnabled(true);
        jdbcHost.setEnabled(true);
        jdbcPort.setEnabled(true);
        SIDRadio.setEnabled(true);
        serviceNameRadio.setEnabled(true);
        if (SIDRadio.isSelected()) {
            sid.setEnabled(false);
        } else {
            serviceName.setEnabled(true);
        }
    }

    private void enableTNSPanel() {
        tnsNamesPanel.setEnabled(true);
        tnsChooseFileBttn.setEnabled(true);
        tnsFilePathLabel.setEnabled(true);
        tnsFilePathText.setEnabled(true);
        tnsServiceIdCombo.setEnabled(true);
        tnsServiceIdLabel.setEnabled(true);
        tnsShowCntBtn.setEnabled(true);

        jdbcPanel.setEnabled(false);
        sidServicePanel.setEnabled(false);
        jdbcHostLabel.setEnabled(false);
        jdbcPortLabel.setEnabled(false);
        jdbcHost.setEnabled(false);
        jdbcPort.setEnabled(false);
        sid.setEnabled(false);
        serviceName.setEnabled(false);
        SIDRadio.setEnabled(false);
        serviceNameRadio.setEnabled(false);

    }

    private void parseTNSFile(File file) {
        if (file != null) {
            // Try use selected files
            try {
                tnsServiceIdCombo.removeAllItems();
                String content = StringUtils.file2string(file);
                TNSFileParser parser = new TNSFileParser();
                ASTNode root = parser.parse(content);
                ASTNode[] nodes = root.getChildren(TokenSet.create(TNSTypesAdopted.NET_SERVICE_DESC));
                for (ASTNode node : nodes) {
                    if (PsiUtil.isASTValid(node)) {
                        tnsServiceIdCombo.addItem(new NetServiceItem(node));
                    } else {
                        // TODO -- issue error logger.
                    }
                }

                if (tnsServiceIdCombo.getItemCount() > 0) {
                    selectNetworkService = ((NetServiceItem) tnsServiceIdCombo.getItemAt(0)).getNode();
                    tnsFilePathText.setText(file.getPath());
                }

            } catch (IOException e1) {
                // TODO - put error in log
                e1.printStackTrace();
            }
        }
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
        rootComponent.setLayout(new GridLayoutManager(6, 10, new Insets(0, 0, 0, 0), -1, -1));
        final Spacer spacer1 = new Spacer();
        rootComponent.add(spacer1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootComponent.add(spacer2, new GridConstraints(3, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 4, new Insets(4, 4, 4, 4), -1, -1));
        rootComponent.add(panel1, new GridConstraints(0, 0, 1, 10, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("User Name:");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        userName = new JTextField();
        panel1.add(userName, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Password:");
        panel1.add(label2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordField = new JPasswordField();
        panel1.add(passwordField, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JDBCRadioButton = new JRadioButton();
        JDBCRadioButton.setSelected(true);
        JDBCRadioButton.setText("JDBC");
        panel1.add(JDBCRadioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TNSNamesRadioButton = new JRadioButton();
        TNSNamesRadioButton.setText("TNS Names");
        panel1.add(TNSNamesRadioButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jdbcPanel = new JPanel();
        jdbcPanel.setLayout(new GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        rootComponent.add(jdbcPanel, new GridConstraints(1, 0, 1, 10, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jdbcPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "JDBC parameters"));
        jdbcHostLabel = new JLabel();
        jdbcHostLabel.setText("Host:");
        jdbcPanel.add(jdbcHostLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jdbcPortLabel = new JLabel();
        jdbcPortLabel.setText("Port:");
        jdbcPanel.add(jdbcPortLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jdbcPort = new JTextField();
        jdbcPanel.add(jdbcPort, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(150, -1), new Dimension(50, -1), 0, false));
        sidServicePanel = new JPanel();
        sidServicePanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        jdbcPanel.add(sidServicePanel, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        sidServicePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "SID/Service Name"));
        SIDRadio = new JRadioButton();
        SIDRadio.setText("SID");
        sidServicePanel.add(SIDRadio, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        serviceNameRadio = new JRadioButton();
        serviceNameRadio.setText("Service Name");
        sidServicePanel.add(serviceNameRadio, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sid = new JTextField();
        sidServicePanel.add(sid, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        serviceName = new JTextField();
        sidServicePanel.add(serviceName, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        jdbcHost = new JComboBox();
        jdbcHost.setEditable(true);
        jdbcPanel.add(jdbcHost, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tnsNamesPanel = new JPanel();
        tnsNamesPanel.setLayout(new GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        tnsNamesPanel.setEnabled(false);
        rootComponent.add(tnsNamesPanel, new GridConstraints(2, 0, 1, 10, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tnsNamesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "TNS Names"));
        tnsFilePathText = new JTextField();
        tnsFilePathText.setEditable(false);
        tnsFilePathText.setEnabled(true);
        tnsNamesPanel.add(tnsFilePathText, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tnsFilePathLabel = new JLabel();
        tnsFilePathLabel.setEnabled(false);
        tnsFilePathLabel.setText("Location");
        tnsNamesPanel.add(tnsFilePathLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tnsChooseFileBttn = new JButton();
        tnsChooseFileBttn.setEnabled(false);
        tnsChooseFileBttn.setIcon(new ImageIcon(getClass().getResource("/icons/menu-open.png")));
        tnsChooseFileBttn.setText("");
        tnsNamesPanel.add(tnsChooseFileBttn, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tnsServiceIdLabel = new JLabel();
        tnsServiceIdLabel.setEnabled(false);
        tnsServiceIdLabel.setText("Network Alias");
        tnsNamesPanel.add(tnsServiceIdLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tnsServiceIdCombo = new JComboBox();
        tnsServiceIdCombo.setEnabled(false);
        tnsNamesPanel.add(tnsServiceIdCombo, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tnsShowCntBtn = new JButton();
        tnsShowCntBtn.setIcon(new ImageIcon(getClass().getResource("/icons/preview.png")));
        tnsShowCntBtn.setText("");
        tnsNamesPanel.add(tnsShowCntBtn, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        reconnectIfConnectionDroppedCheckBox = new JCheckBox();
        reconnectIfConnectionDroppedCheckBox.setText("Reconnect, if connection lost");
        rootComponent.add(reconnectIfConnectionDroppedCheckBox, new GridConstraints(3, 9, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Refresh period:");
        rootComponent.add(label3, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        refreshPeriod = new JTextField();
        rootComponent.add(refreshPeriod, new GridConstraints(3, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, -1), null, 0, false));
        loginOnStartupCheckBox = new JCheckBox();
        loginOnStartupCheckBox.setText("Login on Startup");
        rootComponent.add(loginOnStartupCheckBox, new GridConstraints(4, 9, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        testConnectionButton = new JButton();
        testConnectionButton.setText("Test Connection");
        rootComponent.add(testConnectionButton, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(SIDRadio);
        buttonGroup.add(serviceNameRadio);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(JDBCRadioButton);
        buttonGroup.add(TNSNamesRadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootComponent;
    }

    private class NetServiceItem {

        private ASTNode node;

        public NetServiceItem(@NotNull ASTNode node) {
            this.node = node;
        }

        public String toString() {
            return node.findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText().toLowerCase();
        }

        @NotNull
        public ASTNode getNode() {
            return node;
        }
    }

    public ConnectionSettings3(Project project, @NotNull DbUrl[] urls) {
        this(project);
        init();

        createNewConnection = true;
        Set<String> temp = new HashSet<String>();
        for (DbUrl dbUrl : urls) {
            if (dbUrl instanceof JdbcDbUrl) {
                temp.add(((JdbcDbUrl) dbUrl).getHost().toLowerCase());
            }
        }

        for (String host : temp) {
            this.jdbcHost.addItem(host);
        }
        if (!temp.contains("localhost")) {
            this.jdbcHost.addItem("localhost");
        }

        this.serviceName.setText("ORA");
        this.jdbcPort.setText("1521");

        this.refreshPeriod.setText("60");
        this.loginOnStartupCheckBox.setSelected(true);
        this.reconnectIfConnectionDroppedCheckBox.setSelected(true);

        testConnectionButton.setActionCommand(TEST_CONNECTION);
        testConnectionButton.addActionListener(this);


        SqlCodeAssistantSettings settings = PluginKeys.PLUGIN_SETTINGS.getData(project);
        if (settings.isJDBCSettingsSelected()) {
            // Service Name by default
            JDBCRadioButton.setSelected(true);
            enableJDBCPanel();

            if (settings.isServiceNameSelected()) {
                serviceNameRadio.setSelected(true);
                serviceName.setEnabled(true);
                SIDRadio.setSelected(false);
                sid.setEnabled(false);
            } else {
                serviceNameRadio.setSelected(false);
                serviceName.setEnabled(false);
                SIDRadio.setSelected(true);
                sid.setEnabled(true);
            }
        } else {
            TNSNamesRadioButton.setSelected(true);
            enableTNSPanel();
            if (settings.isServiceNameSelected()) {
                serviceNameRadio.setSelected(true);
                SIDRadio.setSelected(false);
            } else {
                serviceNameRadio.setSelected(true);
                SIDRadio.setSelected(true);
            }
        }
    }


    public ConnectionSettings3(
            Project project,
            DbUrl url,
            int refreshPeriod, boolean loginOnStartup, boolean reconnect) {
        this(project);
        init();

        this.userName.setText(url.getUser());
        this.passwordField.setText(url.getPwd());
        if (url instanceof DbUrlSID) {
            JDBCRadioButton.setSelected(true);
            enableJDBCPanel();
            // set SID
            this.jdbcHost.addItem(((JdbcDbUrl) url).getHost());
            this.jdbcPort.setText(((JdbcDbUrl) url).getPort());

            SIDRadio.setSelected(true);
            sid.setEnabled(true);
            sid.setText(((JdbcDbUrl) url).getSID_ServiceName());
            serviceName.setEnabled(false);
        } else if (url instanceof DbUrlServiceName) {
            JDBCRadioButton.setSelected(true);
            enableJDBCPanel();
            // set Service Name
            this.jdbcHost.addItem(((JdbcDbUrl) url).getHost());
            this.jdbcPort.setText(((JdbcDbUrl) url).getPort());

            serviceNameRadio.setSelected(true);
            serviceName.setEnabled(true);
            serviceName.setText(((JdbcDbUrl) url).getSID_ServiceName());
            sid.setEnabled(false);
        } else if (url instanceof TNSUrl) {
            // set TNS NameService Name
            TNSNamesRadioButton.setSelected(true);
            enableTNSPanel();
            TNSUrl tnsUrl = (TNSUrl) url;
            tnsFilePathText.setText(tnsUrl.getTnsFilePath());
            parseTNSFile(new File(tnsUrl.getTnsFilePath()));
        } else {
            throw new ConfigurationException("Database URL not supported: " + url);
        }

        this.refreshPeriod.setText(Integer.toString(refreshPeriod));
        this.loginOnStartupCheckBox.setSelected(loginOnStartup);
        this.reconnectIfConnectionDroppedCheckBox.setSelected(reconnect);

        testConnectionButton.setActionCommand(TEST_CONNECTION);
        testConnectionButton.addActionListener(this);
    }

    @Nullable
    protected JComponent createCenterPanel() {
        return rootComponent;
    }

    public void validateFields() throws ValidationException {

        if (userName.getText().length() == 0) {
            throw new ValidationException(userName, "User Name parameter must be specified");
        } else if (!userName.getText().matches("[a-zA-Z0-9\\$\\.\\_]+")) {
            throw new ValidationException(userName, "User Name parameter contains not permissible characters");
        }

        if (tnsNamesPanel.isEnabled()) {
            // TNS specified
            if (tnsServiceIdCombo.getItemCount() == 0) {
                throw new ValidationException(tnsServiceIdCombo, "Network Alias must be specified");
            }

        } else if (jdbcPanel.isEnabled()) {
            // JDBC specified
            if (((String) jdbcHost.getSelectedItem()).length() == 0) {
                throw new ValidationException(jdbcHost, "Host parameter must be specified");
            }

            if (!((String) jdbcHost.getSelectedItem()).matches("[a-zA-Z0-9\\.\\_\\-]+")) {
                throw new ValidationException(jdbcHost, "Host parameter contains not permissible characters");
            }

            validateSID_ServiceName();

            if (jdbcPort.getText().length() == 0) {
                throw new ValidationException(jdbcPort, "Port parameter must be specified");
            }

            if (!jdbcPort.getText().matches("[0-9]+")) {
                throw new ValidationException(jdbcPort, "Port parameter contains not permissible characters");
            }
        }
    }

    public void doOKAction() {
        try {
            // If new connection was requested, save current configuration
            if (createNewConnection) {
                SqlCodeAssistantSettings settings = PluginKeys.PLUGIN_SETTINGS.getData(project);
                settings.setJDBCSettingsSelected(JDBCRadioButton.isSelected());
                settings.setServiceNameSelected(serviceNameRadio.isSelected());
            }

            validateFields();
            super.doOKAction();
        } catch (ValidationException e) {
            Messages.showErrorDialog(e.getMessage(), "Validation Error");
            e.cause.requestFocus();
        }
    }

    public boolean getLoginOnStartupCheckBox() {
        return loginOnStartupCheckBox.isEnabled();
    }

    private String getHost() {
        return ((String) jdbcHost.getSelectedItem()).trim();
    }

    private String getPort() {
        return jdbcPort.getText().trim();
    }

    private String getUserName() {
        return userName.getText().trim();
    }

    private String getPassword() {
        return passwordField.getText().trim();
    }

    public int getRefreshPeriod() {
        try {
            int p = Integer.parseInt(refreshPeriod.getText().trim());
            return (p < 30) ? 30 : p;
        } catch (NumberFormatException e) {
            return 60;
        }
    }

    public boolean getLoginOnStartup() {
        return loginOnStartupCheckBox.isSelected();
    }

    public boolean getRepairIfDropped() {
        return reconnectIfConnectionDroppedCheckBox.isSelected();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(TEST_CONNECTION)) {
            try {
                // Check connection parameters first
                validateFields();
                ProgressIndicatorHelper progress = new ProgressIndicatorHelper(project, "Test Connection");

                final String[] message = new String[1];
                boolean res = progress.run(new ProgressIndicatorListener() {
                    boolean result;
                    DbUrl url = getDbUrl();

                    public boolean isComplete() {
                        try {
                            ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
                            ConnectionStatus st = manager.checkConnection(url);
                            result = st.isConnected();
                            message[0] = st.getErrorMessage();
                        } catch (ConfigurationException e1) {
                            result = false;
                            message[0] = "Error: " + e1.getMessage();
                        }
                        return true;
                    }

                    public boolean getResult() {
                        return result;
                    }

                    public String getText() {
                        return "Testing " + url.getKey() + " ...";
                    }

                    public void updated(int step) {
                    }
                });

                if (res) {
                    // progress was NOT interrupted
                    if (progress.getResult()) {
                        Messages.showInfoMessage(project, message[0], "Test connection status");
                    } else {
                        Messages.showErrorDialog(project, message[0], "Test connection status");
                    }
                }

            } catch (ValidationException e1) {
                Messages.showErrorDialog(e1.getMessage(), "Validation Error");
                e1.cause.requestFocus();
            }
        }
    }


    private void validateSID_ServiceName() throws ValidationException {
        if (SIDRadio.isSelected()) {
            String sid1 = sid.getText().trim();
            if (sid1.length() == 0) {
                throw new ValidationException(sid, "SID must be specified");
            }

            if (!sid1.matches("[a-zA-Z0-9\\.\\_\\-]+")) {
                throw new ValidationException(sid, "SID contains not permissible characters");
            }
        } else {
            String serviceName1 = serviceName.getText().trim();
            if (serviceName1.length() == 0) {
                throw new ValidationException(serviceName, "Service Name must be specified");
            }

            if (!serviceName1.matches("[a-zA-Z0-9\\.\\_\\-]+")) {
                throw new ValidationException(serviceName, "Service Name contains not permissible characters");
            }
        }
    }

    public DbUrl getDbUrl() {
        if (tnsNamesPanel.isEnabled()) {
            // connect with TNS
            return new TNSUrl(
                    getUserName(),
                    getPassword(),
                    getNetworkAlias(),
                    (NetServiceDesc) getNetServiceAddress().getPsi(),
                    getTNSNamesFile()
            );

        } else {
            // connect with JDBC
            if (SIDRadio.isSelected()) {
                // old syntax
                return new DbUrlSID(
                        getUserName(),
                        getPassword(),
                        getHost(),
                        getPort(),
                        sid.getText().trim()
                );
            } else {
                // new syntax
                return new DbUrlServiceName(
                        getUserName(),
                        getPassword(),
                        getHost(),
                        getPort(),
                        serviceName.getText().trim()
                );
            }
        }
    }

    private ASTNode getNetServiceAddress() {
        return selectNetworkService;
    }

    private String getNetworkAlias() {
        if (selectNetworkService != null) {
            return selectNetworkService.findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText();
        }
        return null;
    }

    private String getTNSNamesFile() {
        return tnsFilePathText.getText();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


    private class ValidationException extends Exception {
        JComponent cause;

        public ValidationException(JComponent cause, String message) {
            super(message);
            this.cause = cause;
        }
    }
}
