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

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CreateFunction implements FunctionParamPopup {
    private JPanel rootPanel;
    private JButton cancelButton;
    private JButton OKButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JLabel packageOwnerName;


    private List<CloseEventListener> listeners= new ArrayList<CloseEventListener>();


    public CreateFunction(String defaultFuncName, String defaultType, String pkgName) {

        textField1.setText(defaultFuncName);
        packageOwnerName.setText(pkgName);
        comboBox1.getModel().setSelectedItem(defaultType);

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
        textField1.addKeyListener(new KeyListener());
        comboBox1.addKeyListener(new KeyListener());
    }

    private void fireCancelEvent() {
        for(CloseEventListener e: listeners){
            e.close(false);
        }
    }

    private void fireOKevent() {
        for(CloseEventListener e: listeners){
            e.close(true);
        }
    }

    public JComponent getRootComponent() {
        return rootPanel;
    }

    public JComponent getFocusedComponent() {
        return textField1;
    }

    @Override
    public String getTitle() {
        return "Create Function";
    }


    @Override
    public void addCloseEventLister(CloseEventListener c) {
        if(c != null){
            listeners.add(c);
        }
    }

    public String getName() {
        return textField1.getText();
    }

    public String getFunctionType() {
        return (String) comboBox1.getModel().getSelectedItem();
    }


    private class KeyListener extends KeyAdapter {
        public void keyPressed(@NotNull KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                fireOKevent();
            }
        }
    }
}
