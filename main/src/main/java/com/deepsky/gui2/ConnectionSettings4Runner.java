package com.deepsky.gui2;

import javax.swing.*;

public class ConnectionSettings4Runner {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        //Set the look and feel.
//        initLookAndFeel();

        ConnectionSettings4 settings = null; //new ConnectionSettings4();
        //Create and set up the window.
        JFrame frame = new JFrame("Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        settings.$$$getRootComponent$$$().setOpaque(true); //content panes must be opaque
        frame.setContentPane(settings.$$$getRootComponent$$$());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
