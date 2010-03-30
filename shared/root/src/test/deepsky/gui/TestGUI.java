package test.deepsky.gui;

import com.deepsky.gui.DataSourceConfiguration;

import javax.swing.*;

public class TestGUI {

    public static void main(String[] args){
        DataSourceConfiguration g = new DataSourceConfiguration();

        JFrame jf = new JFrame();
        jf.getContentPane().add(g.getRootComponent());
        //jf.setSize(50, 80);
        jf.pack();
        jf.setVisible(true);

        int hh=0;
    }
}
