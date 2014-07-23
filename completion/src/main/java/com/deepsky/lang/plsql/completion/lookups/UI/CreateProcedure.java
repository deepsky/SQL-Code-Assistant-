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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class CreateProcedure implements ParamProviderPopup {
    private JButton buttonCancel;
    private JButton buttonOk;
    private JTextField procedureName;
    private JTextField packageName;
    private JPanel rootPanel;

    private List<CloseEventListener> listeners= new ArrayList<CloseEventListener>();


    public CreateProcedure(String eName, String pkgName) {
        procedureName.setText(eName);
        packageName.setText(pkgName);

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireOKevent();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireCancelEvent();
            }
        });

        buttonOk.addKeyListener(new KeyListener());
        procedureName.addKeyListener(new KeyListener());
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


    @Override
    public void addCloseEventLister(CloseEventListener c) {
        if(c != null){
            listeners.add(c);
        }
    }

    @Override
    public JComponent getRootComponent() {
        return rootPanel;
    }

    @Override
    public JComponent getFocusedComponent() {
        return procedureName;
    }

    @Override
    public String getTitle() {
        return "Create Procedure";
    }

    public String getName() {
        return procedureName.getText();
    }

    private class KeyListener extends KeyAdapter {
        public void keyPressed(@NotNull KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                fireOKevent();
            }
        }
    }

}
