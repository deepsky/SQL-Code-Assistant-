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

package com.deepsky.view.query_pane;

import com.deepsky.view.Icons;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class StatusLinePanel extends JPanel {

    JLabel timeSpentLabel;
    JLabel rowsAffectedLabel;
    JLabel schemaLabel;

    public StatusLinePanel() {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
//        FlowLayout lman = new FlowLayout(FlowLayout.LEFT, 0, 0);
//        setLayout(lman);
//        lman.setAlignment(FlowLayout.LEFT);
        
//        lman.setVgap(2);
//        lman.setHgap(2);
/////        this.setLayout(lman);
//        this.setBorder(new EtchedBorder());

        // time spent
//        JPanel timeSpent = new JPanel();
//        FlowLayout lman2 = new FlowLayout();
//        timeSpent.setLayout(lman2);
//        lman2.setVgap(2);
        //JComponent t = new JLabel("Time spent, ms: ");

//        timeSpent.add(new JLabel("Time spent, ms: "));

        JLabel lab1 = new JLabel("Time spent, ms: ");
        lab1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        add(lab1);

        timeSpentLabel = new JLabel("0");
        int height = timeSpentLabel.getPreferredSize().getSize().height;
        timeSpentLabel.setPreferredSize(new Dimension(40, height));
        add(timeSpentLabel);
//        timeSpent.add(timeSpentLabel);
//        timeSpent.setBorder(new EtchedBorder());
//        this.add(timeSpent);

        JLabel separator = new JLabel(Icons.SEPARATOR);
        separator.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        add( separator);

        // row number
//        JPanel rowNbr = new JPanel();
//        FlowLayout lman3 = new FlowLayout();
//        rowNbr.setLayout(lman3);
//        lman3.setVgap(2);
        rowsAffectedLabel = new JLabel("0 rows fetched");
        add(rowsAffectedLabel);
//        rowNbr.add(rowsAffectedLabel);
//        rowNbr.setBorder(new EtchedBorder());
//        this.add(rowNbr);

/*
        JLabel separator2 = new JLabel(Icons.SEPARATOR);
        separator2.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        add( separator2);
        // db name
//        JPanel dbname = new JPanel();
//        FlowLayout lman4 = new FlowLayout();
//        dbname.setLayout(lman4);
//        lman4.setVgap(2);

        schemaLabel = new JLabel("Not logged in");
        add(schemaLabel);
*/

//        dbname.add(schemaLabel);
//        dbname.setBorder(new EtchedBorder());
//        this.add(dbname);

        //this.setSize(-1, 12);
//        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    public void setTimeSpent(long ms) {
        timeSpentLabel.setText(Long.toString(ms));
    }

    public void setResponseMessage(String text) {
        rowsAffectedLabel.setText(text);
    }

//    public void setSchemaName(final String name) {
//        // todo - dirty hack, needs to be fixed asap
//        SwingUtilities.invokeLater(
//                new Runnable() {
//                    public void run() {
//                        schemaLabel.setText(name);
//                    }
//                }
//        );
//    }
}
