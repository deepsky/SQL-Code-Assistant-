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

package com.deepsky.view.query_pane;

import com.deepsky.view.Icons;

import javax.swing.*;
import java.awt.*;

public class StatusLinePanel extends JPanel {

    JLabel timeSpentLabel;
    JLabel rowsAffectedLabel;

    public StatusLinePanel() {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JLabel lab1 = new JLabel("Time spent, ms: ");
        lab1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        add(lab1);

        timeSpentLabel = new JLabel("0");
        int height = timeSpentLabel.getPreferredSize().getSize().height;
        timeSpentLabel.setPreferredSize(new Dimension(40, height));
        add(timeSpentLabel);

        JLabel separator = new JLabel(Icons.SEPARATOR);
        separator.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        add(separator);

        // row number
        rowsAffectedLabel = new JLabel("0 rows fetched");
//        int height2 = rowsAffectedLabel.getPreferredSize().getSize().height;
//        rowsAffectedLabel.setPreferredSize(new Dimension(140, height2));
        add(rowsAffectedLabel);

        separator = new JLabel(Icons.SEPARATOR);
        separator.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
    }

    public void setTimeSpent(long ms) {
        timeSpentLabel.setText(Long.toString(ms));
    }

    public void setResponseMessage(String text) {
        rowsAffectedLabel.setText(text);
    }
}
