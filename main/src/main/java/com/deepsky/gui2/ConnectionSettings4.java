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
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.psi.tree.TokenSet;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ConnectionSettings4 {

    private JComboBox connectTypeCombo;
    private JPanel connectionType;
    private JPanel rootPanel;
    private JCheckBox sshTunnelCheckBox;
    private JTextField sshLoginName;
    private JTextField sshHost;
    private JCheckBox reconnectIfConnectionLostCheckBox;
    private JCheckBox connectOnStartupCheckBox;
    private JTextField refreshPeriodField;
    private JButton testConnectionButton;

    private JLabel refreshPeriodLabel;
    private JLabel sshLoginLabel;
    private JLabel sshHostLabel;
    //    private JTextField connectionName;
    private JPasswordField passwordField1;
    private JTextField userName;
    private JCheckBox authPublicKeyCheckBox;
    private JButton choosePKFileButton;
    private JTextField privateKeyFile;

    private JDBCUrlPanel jdbcSettings;
    private TNSParamsPanel2 tnsSettings;

    private Project project;

    /**
     * It's called to create a new connection
     *
     * @param project
     * @param dbHosts
     */
    public ConnectionSettings4(Project project, String[] dbHosts) {
        this(project);
        if (dbHosts != null && dbHosts.length > 0) {
            jdbcSettings.setHost(dbHosts);
        }
        sshTunnelCheckBox.setSelected(false);
        sshHost.setEnabled(false);
        sshLoginName.setEnabled(false);
        sshHostLabel.setEnabled(false);
        sshLoginLabel.setEnabled(false);
        authPublicKeyCheckBox.setEnabled(false);
        privateKeyFile.setEnabled(false);
        choosePKFileButton.setEnabled(false);
    }

    public ConnectionSettings4(Project project, DbUrl[] dbUrls, boolean loginOnStartup,
                               boolean repairIfDropped, int refreshPeriod) {
        this(project);
        if (dbUrls != null && dbUrls.length > 0) {
            passwordField1.setText(dbUrls[0].getPwd());
            userName.setText(dbUrls[0].getUser());

            if (dbUrls[0] instanceof DbUrlSID) {
                connectTypeCombo.setSelectedItem("JDBC");
                jdbcSettings.setSelectedDbIdentType(JDBCUrlPanel.DB_IDENT_TYPE.SID);
                jdbcSettings.setSelectedDbName(((JdbcDbUrl) dbUrls[0]).getSID_ServiceName());
                jdbcSettings.setHost(((JdbcDbUrl) dbUrls[0]).getHost());
                jdbcSettings.setPort(((JdbcDbUrl) dbUrls[0]).getPort());
            } else if (dbUrls[0] instanceof DbUrlServiceName) {
                connectTypeCombo.setSelectedItem("JDBC");
                jdbcSettings.setSelectedDbIdentType(JDBCUrlPanel.DB_IDENT_TYPE.SERVICE_NAME);
                jdbcSettings.setSelectedDbName(((JdbcDbUrl) dbUrls[0]).getSID_ServiceName());
                jdbcSettings.setHost(((JdbcDbUrl) dbUrls[0]).getHost());
                jdbcSettings.setPort(((JdbcDbUrl) dbUrls[0]).getPort());

            } else if (dbUrls[0] instanceof TNSUrl) {
                connectTypeCombo.setSelectedItem("TNS Names");
                TNSUrl tnsUrl = (TNSUrl) dbUrls[0];
                tnsSettings.setTnsFilePath(tnsUrl.getTnsFilePath());
                parseTNSFile(new File(tnsUrl.getTnsFilePath()));
                tnsSettings.setSelectedItem(tnsUrl.getNetworkAlias());
            }

            DbUrl.SshSettings settings = dbUrls[0].getSshSettings();
            if (settings != null) {
                sshTunnelCheckBox.setSelected(true);
                sshLoginName.setText(settings.getUserName());
                sshHost.setText(settings.getHost());
                sshHost.setEnabled(true);
                sshLoginName.setEnabled(true);
                sshHostLabel.setEnabled(true);
                sshLoginLabel.setEnabled(true);
                authPublicKeyCheckBox.setEnabled(true);
                if (settings.getPrivateKeyFile() != null) {
                    authPublicKeyCheckBox.setSelected(true);
                    privateKeyFile.setText(settings.getPrivateKeyFile());
                    privateKeyFile.setEnabled(true);
                    choosePKFileButton.setEnabled(true);
                } else {
                    authPublicKeyCheckBox.setSelected(false);
                    privateKeyFile.setEnabled(false);
                    choosePKFileButton.setEnabled(false);
                }
            } else {
                sshTunnelCheckBox.setSelected(false);
                sshHost.setEnabled(false);
                sshLoginName.setEnabled(false);
                sshHostLabel.setEnabled(false);
                sshLoginLabel.setEnabled(false);
                authPublicKeyCheckBox.setEnabled(false);
                privateKeyFile.setEnabled(false);
                choosePKFileButton.setEnabled(false);
            }
        }

        connectOnStartupCheckBox.setSelected(loginOnStartup);
        reconnectIfConnectionLostCheckBox.setSelected(repairIfDropped);
        refreshPeriodField.setText(String.valueOf(refreshPeriod));
    }

    public ConnectionSettings4(Project project) {
        $$$setupUI$$$();
        this.project = project;

        final SqlCodeAssistantSettings settings = PluginKeys.PLUGIN_SETTINGS.getData(ConnectionSettings4.this.project);
        // Initialization rest of components

        jdbcSettings = new JDBCUrlPanel(
                JDBCUrlPanel.DB_IDENT_TYPE.SID, "", new String[]{"localhost"}, 1521
        );

        tnsSettings = new TNSParamsPanel2();

        connectionType.add(
                "JDBC".equals(connectTypeCombo.getSelectedItem()) ?
                        jdbcSettings.getRootPanel() : tnsSettings.getRootPanel()
                , null);


        // Initialize listeners
        connectTypeCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        connectionType.remove(0);
                        if ("JDBC".equals(connectTypeCombo.getSelectedItem())) {
                            connectionType.add(jdbcSettings.getRootPanel(), null);
                        } else {
                            connectionType.add(tnsSettings.getRootPanel(), null);

                        }

                        rootPanel.revalidate();
                        rootPanel.repaint();
                    }
                });
            }
        });

        sshTunnelCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (sshTunnelCheckBox.isSelected()) {
                    sshHost.setEnabled(true);
                    sshLoginName.setEnabled(true);
                    sshLoginLabel.setEnabled(true);
                    sshHostLabel.setEnabled(true);
                    authPublicKeyCheckBox.setEnabled(true);
                    if (authPublicKeyCheckBox.isSelected()) {
                        privateKeyFile.setEnabled(true);
                        choosePKFileButton.setEnabled(true);
                    }
                } else {
                    sshHost.setEnabled(false);
                    sshLoginName.setEnabled(false);
                    sshLoginLabel.setEnabled(false);
                    sshHostLabel.setEnabled(false);
                    authPublicKeyCheckBox.setEnabled(false);
                    privateKeyFile.setEnabled(false);
                    choosePKFileButton.setEnabled(false);
                }
            }
        });

        authPublicKeyCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authPublicKeyCheckBox.isSelected()) {
                    privateKeyFile.setEnabled(true);
                    choosePKFileButton.setEnabled(true);
                } else {
                    privateKeyFile.setEnabled(false);
                    choosePKFileButton.setEnabled(false);
                }
            }
        });


        choosePKFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pkPath = null;
                if (settings.getPKFilePath() == null || settings.getPKFilePath().length() == 0) {
                    if (new File(settings.getDefaultPKFilePath()).exists())
                        pkPath = settings.getDefaultPKFilePath();

                } else {
                    pkPath = settings.getPKFilePath();
                }

                File file = PathUtils.choosePKfile(ConnectionSettings4.this.project, pkPath);
                if (file != null) {
                    settings.setPKFilePath(file.getPath());
                    privateKeyFile.setText(file.getPath());
                } else {
                    privateKeyFile.setText("");
                }
            }
        });


        tnsSettings.addChooseTnsFileListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = null;
                if (tnsSettings.getTnsFilePath().length() == 0) {
                    path = settings.getDefaultTNSFilePath();
                } else {
                    path = tnsSettings.getTnsFilePath();
                }

                File file = PathUtils.chooseTNSNamesSource(ConnectionSettings4.this.project, path);
                if (file != null) {
                    settings.setDefaultTNSFilePath(file.getPath());
                }
                parseTNSFile(file);
            }
        });

        tnsSettings.addOpenTnsFileListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tnsSettings.getTnsFilePath().length() == 0) {
                    // Path to tnsnames.ora file nt specified, skip opening
                } else {
                    // Display the file
                    File tnsFile = new File(tnsSettings.getTnsFilePath());
                    if (tnsFile.exists() && tnsFile.isFile()) {
                        try {
                            String content = StringUtils.file2string(tnsFile);
                            JBPopup popup = ShowTNSNamesFile.createTNSNamesPopup(
                                    null, tnsFile.getPath(), content);
                            popup.show($$$getRootComponent$$$());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        reconnectIfConnectionLostCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (reconnectIfConnectionLostCheckBox.isSelected()) {
                    refreshPeriodField.setEnabled(true);
                    refreshPeriodLabel.setEnabled(true);
                } else {
                    refreshPeriodField.setEnabled(false);
                    refreshPeriodLabel.setEnabled(false);
                }
            }
        });

        testConnectionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Check connection parameters first
                    validateFields();
                    ProgressIndicatorHelper progress = new ProgressIndicatorHelper(
                            ConnectionSettings4.this.project, "Test Connection");

                    final String[] message = new String[1];
                    boolean res = progress.run(new ProgressIndicatorListener() {
                        boolean result;
                        DbUrl url = getDbUrl();

                        public boolean isComplete() {
                            try {
                                ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(
                                        ConnectionSettings4.this.project);
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
                            Messages.showInfoMessage(
                                    ConnectionSettings4.this.project, message[0], "Test Connection Status");
                        } else {
                            Messages.showErrorDialog(
                                    ConnectionSettings4.this.project, message[0], "Test Connection Status");
                        }
                    }

                } catch (ValidationException e1) {
                    Messages.showErrorDialog(e1.getMessage(), "Validation Error");
                    e1.cause.requestFocus();
                }
            }
        });
    }

    public void validateFields() throws ValidationException {

        userName.setText(userName.getText().trim());
        passwordField1.setText(passwordField1.getText().trim());

        if (userName.getText().length() == 0) {
            throw new ValidationException(userName, "User Name must be specified");
        } else if (passwordField1.getText().length() == 0) {
            throw new ValidationException(passwordField1, "Password must be specified");
        } else if (!userName.getText().matches("[A-Za-z0-9\\_\\.\\-\\$!]+")) {
            throw new ValidationException(userName, "User Name contains not permissible characters");
        } else if (!passwordField1.getText().matches("[A-Za-z0-9\\_\\.\\-\\$!#]+")) {
            throw new ValidationException(passwordField1, "Password contains not permissible characters");
        }

        if (isJDBC()) {
            // JDBC specified
            jdbcSettings.validate();
        } else {
            tnsSettings.validate();
        }

        if (sshTunnelCheckBox.isSelected()) {
            validateSSHTunnelSettings();
        }
    }

    private void validateSSHTunnelSettings() throws ValidationException {
        if (sshLoginName.getText().length() == 0) {
            throw new ValidationException(sshLoginName, "Login Name must be specified");
        }

        if (!sshHost.getText().matches("[a-zA-Z0-9\\.\\_\\-]+")) {
            throw new ValidationException(sshHost, "Host name contains not permissible characters");
        }

        if (authPublicKeyCheckBox.isSelected()) {
            if (privateKeyFile.getText().length() == 0) {
                throw new ValidationException(sshLoginName, "Private Key file is not specified");
            }

            if (!new File(privateKeyFile.getText()).exists()) {
                throw new ValidationException(sshLoginName, "Private Key file not found");
            }
        }
    }

    public boolean doOKAction() {
        try {
            // If new connection was requested, save current configuration
            validateFields();
            return true;
        } catch (ValidationException e) {
            Messages.showErrorDialog(e.getMessage(), "Validation Error");
            e.cause.requestFocus();
        }
        return false;
    }


    private void parseTNSFile(File file) {
        if (file != null) {
            // Try use selected files
            try {
                tnsSettings.removeAllItems();
                String content = StringUtils.file2string(file);
                TNSFileParser parser = new TNSFileParser();
                ASTNode root = parser.parse(content);
                ASTNode[] nodes = root.getChildren(TokenSet.create(TNSTypesAdopted.NET_SERVICE_DESC));
                boolean errorHappened = false;
                for (ASTNode node : nodes) {
                    if (PsiUtil.isASTValid(node)) {
                        tnsSettings.addItem(new NetServiceItem(node));
                    } else {
                        errorHappened = true;
                    }
                }

                if (errorHappened) {
                    Messages.showErrorDialog(
                            ConnectionSettings4.this.project,
                            file.getPath() + " was parsed with errors",
                            "Parse Error");
                }

                if (tnsSettings.getItemCount() > 0) {
                    tnsSettings.setTnsFilePath(file.getPath());
                }

            } catch (IOException e1) {
                // TODO - put error in log
                e1.printStackTrace();
            }
        }
    }

    public boolean getLoginOnStartup() {
        return connectOnStartupCheckBox.isSelected();
    }

    public boolean getRepairIfDropped() {
        return reconnectIfConnectionLostCheckBox.isSelected();
    }

    public int getRefreshPeriod() {
        try {
            return Integer.parseInt(refreshPeriodField.getText());
        } catch (NumberFormatException e) {
            return 60;
        }
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
        rootPanel.setLayout(new GridLayoutManager(4, 15, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(panel1, new GridConstraints(0, 0, 1, 14, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("User name");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Password");
        panel1.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Connection type");
        panel1.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(connectTypeCombo, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, -1), new Dimension(100, -1), new Dimension(100, -1), 0, false));
        passwordField1 = new JPasswordField();
        panel1.add(passwordField1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        userName = new JTextField();
        panel1.add(userName, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        connectionType = new JPanel();
        connectionType.setLayout(new BorderLayout(0, 0));
        rootPanel.add(connectionType, new GridConstraints(1, 0, 1, 15, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 120), new Dimension(-1, 120), new Dimension(-1, 120), 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(panel2, new GridConstraints(2, 0, 1, 13, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "SSH tunnel"));
        sshTunnelCheckBox = new JCheckBox();
        sshTunnelCheckBox.setText("Enable");
        panel2.add(sshTunnelCheckBox, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sshLoginLabel = new JLabel();
        sshLoginLabel.setEnabled(false);
        sshLoginLabel.setText("Login name");
        panel2.add(sshLoginLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 3, false));
        sshHostLabel = new JLabel();
        sshHostLabel.setEnabled(false);
        sshHostLabel.setText("Host");
        panel2.add(sshHostLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 3, false));
        sshHost = new JTextField();
        sshHost.setEnabled(false);
        panel2.add(sshHost, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sshLoginName = new JTextField();
        panel2.add(sshLoginName, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        authPublicKeyCheckBox = new JCheckBox();
        authPublicKeyCheckBox.setText("Auth Public Key");
        panel2.add(authPublicKeyCheckBox, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 3, false));
        privateKeyFile = new JTextField();
        panel2.add(privateKeyFile, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), null, null, 0, false));
        choosePKFileButton = new JButton();
        choosePKFileButton.setText("...");
        panel2.add(choosePKFileButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(panel3, new GridConstraints(3, 0, 1, 15, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        testConnectionButton = new JButton();
        testConnectionButton.setText("Test connection");
        panel3.add(testConnectionButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(90, -1), new Dimension(150, -1), 0, false));
        final Spacer spacer1 = new Spacer();
        panel3.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(panel4, new GridConstraints(2, 13, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        connectOnStartupCheckBox = new JCheckBox();
        connectOnStartupCheckBox.setSelected(true);
        connectOnStartupCheckBox.setText("Connect on startup");
        panel4.add(connectOnStartupCheckBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        reconnectIfConnectionLostCheckBox = new JCheckBox();
        reconnectIfConnectionLostCheckBox.setSelected(true);
        reconnectIfConnectionLostCheckBox.setText("Reconnect, if connection lost");
        panel4.add(reconnectIfConnectionLostCheckBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        refreshPeriodLabel = new JLabel();
        refreshPeriodLabel.setText("Refresh period, sec");
        panel4.add(refreshPeriodLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 3, false));
        refreshPeriodField = new JTextField();
        refreshPeriodField.setText("60");
        panel4.add(refreshPeriodField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(40, -1), new Dimension(40, -1), 0, false));
        final Spacer spacer2 = new Spacer();
        panel4.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        label3.setLabelFor(connectTypeCombo);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }


    class NetServiceItem {
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


    public DbUrl getDbUrl() {
        if (isJDBC()) {
            // JDBC
            JdbcDbUrl url = null;
            JDBCUrlPanel jdbc = getJDBCPanel();
            if (jdbc.getSelectedDbIdentType() == JDBCUrlPanel.DB_IDENT_TYPE.SERVICE_NAME) {
                // new syntax
                url = new DbUrlServiceName(
                        userName.getText(),
                        new String(passwordField1.getPassword()),
                        jdbc.getHost(),
                        String.valueOf(jdbc.getPort()),
                        jdbc.getSelectedDbName().trim()
                );
            } else {
                // old syntax
                url = new DbUrlSID(
                        userName.getText(),
                        new String(passwordField1.getPassword()),
                        jdbc.getHost(),
                        String.valueOf(jdbc.getPort()),
                        jdbc.getSelectedDbName().trim()
                );
            }
            if (sshTunnelCheckBox.isSelected()) {
                url.setSshSettings(
                        new SshSettingsImpl(
                                sshLoginName.getText(),
                                sshHost.getText(),
                                authPublicKeyCheckBox.isSelected() ? privateKeyFile.getText() : null));
            }
            return url;
        } else {
            // TNS Names
            NetServiceItem ni = tnsSettings.getSelectedItem();
            if (ni != null) {
                TNSUrl url = new TNSUrl(
                        userName.getText(),
                        new String(passwordField1.getPassword()),
                        ni.toString(),
                        (NetServiceDesc) ni.getNode().getPsi(),
                        tnsSettings.getTnsFilePath()
                );

                if (sshTunnelCheckBox.isSelected()) {
                    if (authPublicKeyCheckBox.isSelected()) {
                        url.setSshSettings(new SshSettingsImpl(sshLoginName.getText(), sshHost.getText(), privateKeyFile.getText()));
                    } else {
                        url.setSshSettings(new SshSettingsImpl(sshLoginName.getText(), sshHost.getText()));
                    }
                }
                return url;
            }
        }

        return null;
    }

    private boolean isJDBC() {
        return "JDBC".equals(connectTypeCombo.getSelectedItem());
    }

    private JDBCUrlPanel getJDBCPanel() {
        JPanel panel = (JPanel) connectionType.getComponent(0);
        return (JDBCUrlPanel) panel.getClientProperty(JDBCUrlPanel.JDBC_PANEL);
    }

    private TNSParamsPanel2 getTNSNamesPanel() {
        JPanel panel = (JPanel) connectionType.getComponent(0);
        return (TNSParamsPanel2) panel.getClientProperty(TNSParamsPanel2.TNS_NAMES_PANEL);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        connectTypeCombo = new JComboBox(new String[]{"JDBC", "TNS Names"});
    }


    public static class ValidationException extends Exception {
        JComponent cause;

        public ValidationException(JComponent cause, String message) {
            super(message);
            this.cause = cause;
        }
    }

    private static class SshSettingsImpl implements DbUrl.SshSettings {
        String user, host;
        String pkFile;

        public SshSettingsImpl(String user, String host) {
            this.user = user;
            this.host = host;
        }

        public SshSettingsImpl(String user, String host, String pkFile) {
            this.user = user;
            this.host = host;
            this.pkFile = pkFile;
        }

        public String getHost() {
            return host;
        }

        public String getUserName() {
            return user;
        }

        @Override
        public String getPrivateKeyFile() {
            return pkFile;
        }
    }

}
