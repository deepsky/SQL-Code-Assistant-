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

package com.deepsky.view.query_pane.grid.editors;


import com.deepsky.view.query_pane.DataAccessor;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EventObject;

public abstract class AbstractCellEditor1 extends AbstractCellEditor implements TableCellEditor {

//
//  Instance Variables
//

    /**
     * The Swing component being edited.
     */
    protected JComponent editorComponent;
    /**
     * The delegate class which handles all methods sent from the
     * <code>CellEditor</code>.
     */
    protected EditorDelegate delegate;
    /**
     * An integer specifying the number of clicks needed to start editing.
     * Even if <code>clickCountToStart</code> is defined as zero, it
     * will not initiate until a click occurs.
     */
    protected int clickCountToStart = 1;

    private JTextField textField;

    private java.util.List<TextCellEditorListener> listeners = new ArrayList<TextCellEditorListener>();

    final Color c1 = new Color(253, 254, 224);
    final Font font;

//
//  Constructors
//

    public AbstractCellEditor1(Font font, boolean addButton) {
        this.font = font;
        textField = new JTextField();

        textField.setFont(font);
        textField.setBackground(c1);

        if(addButton){
            JPanel panel = new JPanel(new BorderLayout()) {
                protected boolean processKeyBinding(KeyStroke ks, KeyEvent e,
                                                    int condition, boolean pressed) {
/*
todo -- fix lost typed key
                    if (!textField.isFocusOwner()) {
                        InputMap map = textField.getInputMap();//condition, false);
                        ActionMap am = textField.getActionMap();//false);

                        if (map != null && am != null && isEnabled()) {
                            Object binding = map.get(ks);
                            Action action = (binding == null) ? null : am.get(binding);
                            if (action != null) {
                                int k = 0;
                            }
                        }
                    }
*/
                    return super.processKeyBinding(ks, e, condition, pressed);
                }
            };
//            panel.setRequestFocusEnabled(true);
            panel.setFocusable(true);
            panel.setEnabled(true);
            panel.setVisible(true);
            panel.setOpaque(true);

            // Forward focus to textField component
            panel.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    textField.requestFocusInWindow();
                }

                public void focusLost(FocusEvent e) {
                }
            });


//        panel.setOpaque(true);
            JButton button = new JButton("..");
//        button.setOpaque(true);
            button.setRolloverEnabled(false);
            button.setMaximumSize(new Dimension(18, -1));
            button.setMinimumSize(new Dimension(18, -1));
            button.setPreferredSize(new Dimension(18, -1));
            button.setMargin(new Insets(2, 1, 2, 1));
            textField.setMargin(new Insets(0, 0, 0, 0));
            textField.setHorizontalAlignment(JTextField.LEFT);
            panel.add(button, BorderLayout.EAST);
            panel.add(textField, BorderLayout.CENTER);

            editorComponent = panel;

            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    for(TextCellEditorListener l: listeners){
                        l.invokeExternalEditor(getDataAccessor());
                    }
                }
            });

        } else {
            editorComponent = textField;
        }

        this.clickCountToStart = 2;
        delegate = new EditorDelegate() {
            public void setValue(Object value) {
                textField.setText((value != null) ? value.toString() : "");
            }

            public Object getCellEditorValue() {
                return textField.getText();
            }
        };
        textField.addActionListener(delegate);
    }

    /**
     * Give a chance descendants to create specific accessor type
     * @return - data accessor
     */
    protected abstract DataAccessor getDataAccessor();

    public String getTextValue(){
        return textField.getText();
    }

    public void setToolTip(@Nullable String text){
        textField.setToolTipText(text);
    }

    public void setHorizontalAlignment(int alignment){
        textField.setHorizontalAlignment(alignment);
    }

    public void setInputErrored(){
        Border border = new LineBorder(Color.red);
        textField.setBorder(border);
    }

    public void addActionListener(TextCellEditorListener l){
        listeners.add(l);
    }

    public void removeActionListener(TextCellEditorListener l){
        listeners.remove(l);
    }


    /**
     * Returns a reference to the editor component.
     *
     * @return the editor <code>Component</code>
     */
    public Component getComponent() {
        return editorComponent;
    }

//
//  Modifying
//

    /**
     * Specifies the number of clicks needed to start editing.
     *
     * @param count an int specifying the number of clicks needed to start editing
     * @see #getClickCountToStart
     */
    public void setClickCountToStart(int count) {
        clickCountToStart = count;
    }

    /**
     * Returns the number of clicks needed to start editing.
     *
     * @return the number of clicks needed to start editing
     */
    public int getClickCountToStart() {
        return clickCountToStart;
    }

//
//  Override the implementations of the superclass, forwarding all methods
//  from the CellEditor interface to our delegate.
//

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     *
     * @see EditorDelegate#getCellEditorValue
     */
    public Object getCellEditorValue() {
        return delegate.getCellEditorValue();
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     *
     * @see EditorDelegate#isCellEditable(java.util.EventObject)
     */
    public boolean isCellEditable(EventObject anEvent) {
        return  delegate.isCellEditable(anEvent);
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     *
     * @see EditorDelegate#shouldSelectCell(EventObject)
     */
    public boolean shouldSelectCell(EventObject anEvent) {
        return delegate.shouldSelectCell(anEvent);
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     *
     * @see EditorDelegate#stopCellEditing
     */
    public boolean stopCellEditing() {

        textField.getInputMap().remove(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        textField.getActionMap().remove("doCancel");

        return delegate.stopCellEditing();
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     *
     * @see EditorDelegate#cancelCellEditing
     */
    public void cancelCellEditing() {
        textField.getInputMap().remove(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        textField.getActionMap().remove("doCancel");

        delegate.cancelCellEditing();
    }


    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     *
     * @param value
     * @see EditorDelegate#setValue(Object)
     */
    public void setValue(Object value) {
        delegate.setValue(value);
    }

//
//  Implementing the CellEditor Interface
//
    /**
     * Implements the <code>TableCellEditor</code> interface.
     */
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {

        Border border = new LineBorder(Color.blue);
        textField.setBorder(border);

        // Initialize KeyStroke
        Action doCancel = new CancelEditAction();
        textField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "doCancel");
        textField.getActionMap().put("doCancel", doCancel);

        delegate.setValue(value);
        return editorComponent;
    }

    private class CancelEditAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            cancelCellEditing();
        }
    }
//
//  Protected EditorDelegate class
//

    /**
     * The protected <code>EditorDelegate</code> class.
     */
    protected class EditorDelegate implements ActionListener, ItemListener, Serializable {

        /**
         * The value of this cell.
         */
        protected Object value;

        /**
         * Returns the value of this cell.
         *
         * @return the value of this cell
         */
        public Object getCellEditorValue() {
            return value;
        }

        /**
         * Sets the value of this cell.
         *
         * @param value the new value of this cell
         */
        public void setValue(Object value) {
            this.value = value;
        }

        /**
         * Returns true if <code>anEvent</code> is <b>not</b> a
         * <code>MouseEvent</code>.  Otherwise, it returns true
         * if the necessary number of clicks have occurred, and
         * returns false otherwise.
         *
         * @param anEvent the event
         * @return true  if cell is ready for editing, false otherwise
         */
        public boolean isCellEditable(EventObject anEvent) {
            if (anEvent instanceof MouseEvent) {
                return ((MouseEvent) anEvent).getClickCount() >= clickCountToStart;
            }
            return true;
        }

        /**
         * Returns true to indicate that the editing cell may
         * be selected.
         *
         * @param anEvent the event
         * @return true
         */
        public boolean shouldSelectCell(EventObject anEvent) {
            return true;
        }

        /**
         * Returns true to indicate that editing has begun.
         *
         * @param anEvent the event
         */
        public boolean startCellEditing(EventObject anEvent) {
            return true;
        }

        /**
         * Stops editing and
         * returns true to indicate that editing has stopped.
         * This method calls <code>fireEditingStopped</code>.
         *
         * @return true
         */
        public boolean stopCellEditing() {
            fireEditingStopped();
            return true;
        }

        /**
         * Cancels editing.  This method calls <code>fireEditingCanceled</code>.
         */
        public void cancelCellEditing() {
            fireEditingCanceled();
        }

        /**
         * When an action is performed, editing is ended.
         *
         * @param e the action event
         * @see #stopCellEditing
         */
        public void actionPerformed(ActionEvent e) {
            AbstractCellEditor1.this.stopCellEditing();
        }

        /**
         * When an item's state changes, editing is ended.
         *
         * @param e the action event
         * @see #stopCellEditing
         */
        public void itemStateChanged(ItemEvent e) {
            AbstractCellEditor1.this.stopCellEditing();
        }
    }

}
