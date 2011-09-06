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

package com.deepsky.view.query_pane.ui;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class HexTextArea extends JTextArea {

    final ReplaceDocument rdoc = new ReplaceDocument();
    int lastCaretPosition = 0;

    final private int MAGIC_NUMBER = 9;

    public HexTextArea() {

        this.setDocument(rdoc);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);

        Action doNothing = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //do nothing
                int hh = 0;
            }
        };

        Action doBackspace = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int offset = HexTextArea.this.getCaretPosition();
                if (offset == 0 || offset < rdoc.getLength() - 1) {
                    //do nothing
                    return;
                }
                try {
                    rdoc.removeBack(offset - 1, 1);
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        };

        Action doDelete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int start = HexTextArea.this.getSelectionStart();
                int end = HexTextArea.this.getSelectionEnd();

                if (end == rdoc.getLength()) {
                    try {
                        rdoc.removeBack(start, end - start);
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };

        this.setFont(Font.decode("Monospaced"));
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "doNothing");
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "doDelete");
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "doBackspace");

        this.getActionMap().put("doNothing", doNothing);
        this.getActionMap().put("doDelete", doDelete);
        this.getActionMap().put("doBackspace", doBackspace);

        this.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                int dot = e.getDot();
                int mark = e.getMark();

                if (!insertStrInProgress) {
                    // cursor movement only
                    final int caretPosition = HexTextArea.this.getCaretPosition();
                    if ((caretPosition + 1) % MAGIC_NUMBER == 0) {
                        // dir > 0 go right
                        // dir < 0 go left
                        final int dir = caretPosition - lastCaretPosition;
//                        int length = HexTextArea.this.getDocument().getLength();
                        lastCaretPosition = HexTextArea.this.getCaretPosition();
                    } else {
                        lastCaretPosition = caretPosition;
                    }
                } else {
                    final int caretPosition = HexTextArea.this.getCaretPosition();
                    if ((caretPosition + 1) % MAGIC_NUMBER == 0) {
                        // dir > 0 go right
                        // dir < 0 go left
                        final int dir = caretPosition - lastCaretPosition;
                        int length = HexTextArea.this.getDocument().getLength();
                        if (dir > 0) {
                            // do nothing
                        } else {
                            lastCaretPosition = caretPosition;// - 1;
                        }
                    } else {
                        lastCaretPosition = caretPosition;
                    }
                }
            }
        });
    }

    private boolean insertStrInProgress = false;
    private boolean copyInProgress = false;

    public String getSelectedText() {
        String txt = super.getSelectedText();
        if (copyInProgress && txt != null) {
            return txt.replace(" ", "");
        }
        return txt;
    }

    public void copy() {
        try {
            copyInProgress = true;
            super.copy();
        } finally {
            copyInProgress = false;
        }
    }

    public void cut() {
        // do nothing
    }

    public String getMergedText() {
        return getText().replace(" ", "");
    }


    class ReplaceDocument extends PlainDocument {

        public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
            try {
                insertStrInProgress = true;
                String str1 = validateString(str);
                int len = str1.length();

                int off = 0;
                for (int i = 0; i < len; i++, off++) {
                    if ((offset + off + 1) % MAGIC_NUMBER == 0) {
                        super.insertString(offset + off, " ", a);
                        off++;
                    }
                    String e = String.valueOf(str1.charAt(i));
                    super.insertString(offset + off, e, a);
                }

                if (offset + off < getLength())
                    super.remove(offset + off, off);

                HexTextArea.this.setCaretPosition(offset + off);

            } finally {
                insertStrInProgress = false;
            }
        }

        public void removeBack(int offset, int len) throws BadLocationException {
            super.remove(offset, len);
        }

        public void remove(int offset, int len) throws BadLocationException {
            // super.remove(offset, len);
        }

        private String validateString(String str) {
            StringBuilder builder = new StringBuilder(str);
            int n = str.length();
            for (int i = 0; i < n; i++) {
                if (Character.isDigit(str.charAt(i))) {
                    // correct
                } else {
                    switch (str.charAt(i)) {
                        case 'a':
                        case 'A':
                            builder.setCharAt(i, 'a');
                            break;
                        case 'b':
                        case 'B':
                            builder.setCharAt(i, 'b');
                            break;
                        case 'c':
                        case 'C':
                            builder.setCharAt(i, 'c');
                            break;
                        case 'd':
                        case 'D':
                            builder.setCharAt(i, 'd');
                            break;
                        case 'e':
                        case 'E':
                            builder.setCharAt(i, 'e');
                            break;
                        case 'f':
                        case 'F':
                            builder.setCharAt(i, 'f');
                            break;
                        default:
                            builder.setCharAt(i, '0');
                            break;
                    }
                }
            }
            return builder.toString();
        }
    }

}

