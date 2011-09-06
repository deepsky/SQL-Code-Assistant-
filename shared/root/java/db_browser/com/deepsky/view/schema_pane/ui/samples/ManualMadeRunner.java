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

package com.deepsky.view.schema_pane.ui.samples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ManualMadeRunner extends JFrame {

    // The initial width and height of the frame
    static final int WIDTH = 250;
    static final int HEIGHT = 400;

    public ManualMadeRunner(){
        super("ManualMadeRunner");

        Container content = getContentPane();
        JPanel panel = new ManualMadePanel();
        content.add(panel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Frame f = (Frame)e.getSource();
                f.setVisible(false);
                f.dispose();
                // System.exit(0);
            }
        });

        setSize(WIDTH, HEIGHT);
    }

    public static void main(String[] args){
        try {
                // Set cross-platform Java L&F (also called "Metal")
                UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            }
            catch (UnsupportedLookAndFeelException e) {
               // handle exception
            }
            catch (ClassNotFoundException e) {
               // handle exception
            }
            catch (InstantiationException e) {
               // handle exception
            }
            catch (IllegalAccessException e) {
               // handle exception
            }



        ManualMadeRunner frame = new ManualMadeRunner();
        frame.setVisible(true);

    }

}
