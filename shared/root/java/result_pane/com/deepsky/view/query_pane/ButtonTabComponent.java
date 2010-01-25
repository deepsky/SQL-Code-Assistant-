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

import com.deepsky.view.query_pane.markup.SqlStatementMarker;
import com.deepsky.view.query_pane.markup.SqlStatementMarkerListener;
import com.deepsky.database.SqlScriptManager;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.view.Icons;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ButtonTabComponent extends JPanel implements SqlStatementMarkerListener {
    private final JTabbedPane pane;

    String labelText = null;
    final JLabel label;
    SqlStatementMarker sqlMarker;

    boolean mouseInside = false;

    public ButtonTabComponent(final JTabbedPane pane, Icon icon, SqlStatementMarker sqlMarker) {
        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        if (pane == null) {
            throw new NullPointerException("TabbedPane is null");
        }
        this.pane = pane;
        this.sqlMarker = sqlMarker;

        pane.addPropertyChangeListener(QueryResultWindow.JTABBED_CONTROL, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                Object value = evt.getNewValue();
                int i = pane.indexOfTabComponent(ButtonTabComponent.this);
                if (i == pane.getSelectedIndex() && value instanceof Boolean) {
                    boolean _control = (Boolean) value;
                    if (!_control && mouseInside) {
                        fireMyEvent(false);
                    }
                }
            }
        });

        setOpaque(false);
        this.sqlMarker = sqlMarker;

        //make JLabel read titles from JTabbedPane
        label = new JLabel(icon) {
            public String getText() {
                if (super.getText() == null) {
                    int i = pane.indexOfTabComponent(ButtonTabComponent.this);
                    if (i != -1) {
                        return pane.getTitleAt(i);
                    }
                } else {
                    return super.getText();
                }

                return null;
            }
        };

        add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

        if (sqlMarker != null) {
            String text = sqlMarker.getText();
            label.setToolTipText(Formatter.sql2htmlBase(text, 70, 8));

            // highlighting title stuff
            label.addMouseListener(new LabelMouseListener());

            label.addMouseMotionListener(new MouseMotionListener() {
                public void mouseDragged(MouseEvent e) {
                }

                public void mouseMoved(MouseEvent e) {
                    Component component = e.getComponent();
                    if (component instanceof JLabel) {
                        int i = pane.indexOfTabComponent(ButtonTabComponent.this);
                        if (i == pane.getSelectedIndex() && e.isControlDown()) {
                            mouseInside = true;
                            fireMyEvent(true);
                        }
                    }
                }
            });

            sqlMarker.addStateListener(this);
        }

        //tab button
        JButton button = new TabButton();
        add(button);
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }


    public ButtonTabComponent(final JTabbedPane pane, Icon icon) {
        this(pane, icon, null);
    }

    void underlineLabel() {
        int i = pane.indexOfTabComponent(ButtonTabComponent.this);
        if (i != -1) {
            String text = pane.getTitleAt(i);
            label.setText("<html><a href='/poltava/'>" + text + "</a></html>");
        }
    }

    void justLable() {
        int i = pane.indexOfTabComponent(ButtonTabComponent.this);
        if (i != -1) {
            String text = pane.getTitleAt(i);
            label.setText(text);
        }
    }

    boolean state = false;
    boolean toggle = false;
    boolean striked = false;

    private void fireMyEvent(boolean _state) {
        if (!striked && state != _state) {
            state = _state;
            fireMyEventInternal(state);
        }
    }

    Cursor cursor;
    private void fireMyEventInternal(boolean state) {
        if (state) {
            // control key is down
            underlineLabel();
            toggle = true;
            cursor = label.getCursor();
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            // control key released
            label.setCursor(cursor);
            toggle = false;
            justLable();

        }
    }

    // marker is not valid any longer
    public void invalidated(SqlStatementMarker marker) {
        marker.removeStateListener(this);
        striked = true;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                int i = pane.indexOfTabComponent(ButtonTabComponent.this);
                if (i != -1) {
                    String text = pane.getTitleAt(i);
                    label.setText("<html><strike>" + text + "</strike></html>");
                }
            }
        });
    }

    private class LabelMouseListener extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof JLabel) {
                int i = pane.indexOfTabComponent(ButtonTabComponent.this);
                if (e.isControlDown() && i == pane.getSelectedIndex()) {
                    mouseInside = true;
                    fireMyEvent(true);
                }
            }
        }

        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof JLabel) {
                if (mouseInside) {
                    fireMyEvent(false);
                    mouseInside = false;
                }
            }
        }

        public void mouseClicked(MouseEvent e) {
            int i = pane.indexOfTabComponent(ButtonTabComponent.this);
            if (pane.getSelectedIndex() == i) {
                if (toggle && sqlMarker != null) {
                    if (sqlMarker.valid()) {
                        SqlScriptManager.moveToOffset(
                                sqlMarker.getPlSqlFile(),
                                sqlMarker.getHighlighter().getStartOffset()
                        );
                    }
                }
            } else {
                pane.requestFocusInWindow();
                pane.setSelectedIndex(i);
            }
        }

        public void mousePressed(MouseEvent e) {
            pane.requestFocusInWindow();
            int i = pane.indexOfTabComponent(ButtonTabComponent.this);
            pane.setSelectedIndex(i);
        }
    }


    private class TabButton extends JButton implements ActionListener {
        public TabButton() {
            super(Icons.CLOSE);
            int size = 16;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("Close Row Set");
            //Make the button looks the same for all Laf's
            setUI(new BasicButtonUI());
            //Make it transparent
            setContentAreaFilled(false);
            //No need to be focusable
            setFocusable(false);
//            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            //Making nice rollover effect
            //we use the same listener for all buttons
            addMouseListener(buttonMouseListener);
            setRolloverEnabled(true);
            //Close the proper tab by clicking the button
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            int i = pane.indexOfTabComponent(ButtonTabComponent.this);
            // remove marker if exists
            if (sqlMarker != null) {
                sqlMarker.remove();
            }
            if (i != -1) {
                pane.remove(i);
            }
        }

        //we don't want to update UI for this button
        public void updateUI() {
        }
    }

    private final static MouseListener buttonMouseListener = new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setIcon(Icons.CLOSE_HOVERED);
                //setBorderPainted(true);
            }
        }

        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setIcon(Icons.CLOSE);
//                button.setBorderPainted(false);
            }
        }
    };
}
