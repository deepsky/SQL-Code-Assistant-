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

import com.deepsky.database.exec.SQLUpdateStatistics;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class QueryStatisticsPanel extends JPanel implements QueryResultPanel {

    private JComponent central;
    private StatusLinePanel statusLine;
    private JTextArea textArea;

    final private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 14);

    public QueryStatisticsPanel() {
        super(new BorderLayout());

        setFont(font);

        central = new JPanel(new BorderLayout());
        central.setBackground(UIUtil.getTreeTextBackground());
        add(central, BorderLayout.CENTER);

        statusLine = new StatusLinePanel();
        add(statusLine, BorderLayout.SOUTH);
    }


    public void init(@NotNull Object data) {
        SQLUpdateStatistics stats = (SQLUpdateStatistics) data;

        setVisible(false);

        if (central != null) {
            remove(central);
        }
        central = new JPanel(new BorderLayout());
        central.setBackground(UIUtil.getTreeTextBackground());

        PlainDocument doc = new PlainDocument();

        textArea = new JTextArea(doc);
        textArea.setFont(font);
        central.add(new JBScrollPane(textArea), BorderLayout.CENTER);
        add(central, BorderLayout.CENTER);

        String errors = stats.errorMessages();
        try {
            SimpleAttributeSet set = new SimpleAttributeSet();
            StyleConstants.setFontFamily(set, Font.MONOSPACED);

            if(stats.getSqlStatement() != null){
                doc.insertString(0, stats.getSqlStatement() + "\n", set);
            }
            if(errors != null){
                Position endPos = doc.getEndPosition();
                doc.insertString(endPos.getOffset(), errors, set);
                endPos = doc.getEndPosition();
                doc.insertString(endPos.getOffset(), "\n\n", set);
            }
        } catch (BadLocationException ignored) {
        }

        textArea.setCaretPosition(doc.getEndPosition().getOffset()-1);

        statusLine.setResponseMessage(stats.resultMessage());
        statusLine.setTimeSpent(stats.timeSpent());

        setVisible(true);
    }


    public void append(@NotNull SQLUpdateStatistics stats) {
        if (textArea == null) {
            init(stats);
        } else {
            Document doc = textArea.getDocument();
            String errors = stats.errorMessages();
            try {
                Position endPos = doc.getEndPosition();
                SimpleAttributeSet set = new SimpleAttributeSet();
                StyleConstants.setFontFamily(set, Font.MONOSPACED);

                if(stats.getSqlStatement() != null){
                    doc.insertString(endPos.getOffset(), stats.getSqlStatement() + "\n\n", set);
                }

                if(errors != null){
                    endPos = doc.getEndPosition();
                    doc.insertString(endPos.getOffset(), errors, set);
                    endPos = doc.getEndPosition();
                    doc.insertString(endPos.getOffset(), "\n\n", set);
                }
            } catch (BadLocationException ignored) {
            }

            textArea.setCaretPosition(doc.getEndPosition().getOffset()-1);

            statusLine.setResponseMessage(stats.resultMessage());
            statusLine.setTimeSpent(stats.timeSpent());
        }
    }
}
