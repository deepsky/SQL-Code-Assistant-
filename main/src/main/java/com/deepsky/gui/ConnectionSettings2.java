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

package com.deepsky.gui;

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.ConnectionStatus;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.DbUrlSID;
import com.deepsky.database.ora.DbUrlServiceName;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.view.utils.ProgressIndicatorHelper;
import com.deepsky.view.utils.ProgressIndicatorListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class ConnectionSettings2 extends DialogWrapper implements ActionListener {
    private JPanel rootComponent;
    private JCheckBox loginOnStartupCheckBox;
    private JButton testConnectionButton;
    private JComboBox host;
    private JTextField serviceName;
    private JTextField sid;
    private JTextField port;
    private JTextField userName;
    //    private JTextField password;
    private JCheckBox reconnectIfConnectionDroppedCheckBox;
    private JTextField refreshPeriod;
    private JPasswordField passwordField;
    private JRadioButton SIDRadio;
    private JRadioButton serviceNameRadio;

    final String TEST_CONNECTION = "Test Connection1";

    Project project;

    protected ConnectionSettings2(Project project) {
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
    }

    public ConnectionSettings2(Project project, @NotNull String[] hosts) {
        this(project);
        init();

        Set<String> temp = new HashSet<String>();
        for (String host : hosts) {
            temp.add(host.toLowerCase());
        }
        for (String host : temp) {
            this.host.addItem(host);
        }
        if (!temp.contains("localhost")) {
            this.host.addItem("localhost");
        }

        this.serviceName.setText("ORA");
        this.port.setText("1521");

        this.refreshPeriod.setText("60");
        this.loginOnStartupCheckBox.setSelected(true);
        this.reconnectIfConnectionDroppedCheckBox.setSelected(true);

        testConnectionButton.setActionCommand(TEST_CONNECTION);
        testConnectionButton.addActionListener(this);

        // Service Name by default
        serviceNameRadio.setSelected(true);
        serviceName.setEnabled(true);
        sid.setEnabled(false);
    }


    public ConnectionSettings2(
            Project project,
            DbUrl url,
            int refreshPeriod, boolean loginOnStartup, boolean reconnect) {
        this(project);
        init();
/*
TODO -- remove the class

        this.host.addItem(url.getHost());
        this.port.setText(url.getPort());
        this.userName.setText(url.getUser());
        this.passwordField.setText(url.getPwd());
        if (url.isOldSyntax()) {
            // set SID
            SIDRadio.setSelected(true);
            sid.setEnabled(true);
            sid.setText(url.getSID_ServiceName());
            serviceName.setEnabled(false);
        } else {
            // set Service Name
            serviceNameRadio.setSelected(true);
            serviceName.setEnabled(true);
            serviceName.setText(url.getSID_ServiceName());
            sid.setEnabled(false);
        }
*/
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
        if (((String) host.getSelectedItem()).length() == 0) {
            throw new ValidationException(host, "Host parameter must be specified");
        }

        if (!((String) host.getSelectedItem()).matches("[a-zA-Z0-9\\.\\_\\-]+")) {
            throw new ValidationException(host, "Host parameter contains not permissible characters");
        }

        validateSID_ServiceName();

        if (port.getText().length() == 0) {
            throw new ValidationException(port, "Port parameter must be specified");
        }

        if (!port.getText().matches("[0-9]+")) {
            throw new ValidationException(port, "Port parameter contains not permissible characters");
        }

        if (userName.getText().length() == 0) {
            throw new ValidationException(userName, "User Name parameter must be specified");
        }

        if (!userName.getText().matches("[a-zA-Z0-9\\$\\.\\_]+")) {
            throw new ValidationException(userName, "User Name parameter contains not permissible characters");
        }
    }

    public void doOKAction() {
        try {
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

    public String getHost() {
        return ((String) host.getSelectedItem()).trim();
    }

//    public String getServiceName() {
//        return serviceName.getText().trim();
//    }

    public String getPort() {
        return port.getText().trim();
    }

    public String getUserName() {
        return userName.getText().trim();
    }

    public String getPassword() {
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
            ProgressIndicatorHelper progress = new ProgressIndicatorHelper(project, "Test Connection");
            final String[] message = new String[1];
//            final String hostName = ((String) host.getSelectedItem()).trim();
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
                        message[0] = "Check parameters";
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

        }

    }

/*
    boolean d(Project project) {
        return ProgressManager.getInstance().runProcessWithProgressSynchronously(new Runnable() {
                    public void run() {
                        final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
                        if (progressIndicator != null) {
                            progressIndicator.setText("prepare.for.deployment.common");
                            progressIndicator.setIndeterminate(true);
                        }
                        try {

                            Thread.currentThread().sleep(2000);
                            progressIndicator.setText("Hello World!");
                            Thread.currentThread().sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "prepare.for.deployment", true, project);
    }
*/


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
        rootComponent = new JPanel();
        rootComponent.setLayout(new GridLayoutManager(4, 7, new Insets(0, 0, 0, 0), -1, -1));
        final Spacer spacer1 = new Spacer();
        rootComponent.add(spacer1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootComponent.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        rootComponent.add(panel1, new GridConstraints(0, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "URL parameters"));
        final JLabel label1 = new JLabel();
        label1.setText("Host:");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Port:");
        panel1.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("User Name:");
        panel1.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Password:");
        panel1.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        port = new JTextField();
        panel1.add(port, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        userName = new JTextField();
        panel1.add(userName, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordField = new JPasswordField();
        panel1.add(passwordField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        host = new JComboBox();
        host.setEditable(true);
        panel1.add(host, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "SID/Service Name"));
        SIDRadio = new JRadioButton();
        SIDRadio.setText("SID");
        panel2.add(SIDRadio, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        serviceNameRadio = new JRadioButton();
        serviceNameRadio.setText("Service Name");
        panel2.add(serviceNameRadio, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sid = new JTextField();
        panel2.add(sid, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        serviceName = new JTextField();
        panel2.add(serviceName, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        testConnectionButton = new JButton();
        testConnectionButton.setText("Test Connection");
        rootComponent.add(testConnectionButton, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        reconnectIfConnectionDroppedCheckBox = new JCheckBox();
        reconnectIfConnectionDroppedCheckBox.setText("Reconnect, if connection lost");
        rootComponent.add(reconnectIfConnectionDroppedCheckBox, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loginOnStartupCheckBox = new JCheckBox();
        loginOnStartupCheckBox.setText("Login on Startup");
        rootComponent.add(loginOnStartupCheckBox, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        rootComponent.add(panel3, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        refreshPeriod = new JTextField();
        panel3.add(refreshPeriod, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Refresh period:");
        panel3.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(SIDRadio);
        buttonGroup.add(serviceNameRadio);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootComponent;
    }


    private class ValidationException extends Exception {
        JComponent cause;

        public ValidationException(JComponent cause, String message) {
            super(message);
            this.cause = cause;
        }
    }
}
