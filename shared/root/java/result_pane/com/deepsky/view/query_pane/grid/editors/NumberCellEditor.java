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

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberCellEditor extends AbstractCellEditor1 {

    private Class[] argTypes = new Class[]{String.class};
    private Constructor constructor;
    private Object value1;
    private DecimalFormat formatter;

    public NumberCellEditor(Font font) {
        super(font, false);
        setHorizontalAlignment(JTextField.RIGHT);
    }

    public boolean stopCellEditing() {
        String s = (String) super.getCellEditorValue();
        // Here we are dealing with the case where a user
        // has deleted the string value in a cell, possibly
        // after a failed validation. Return null, so that
        // they have the option to replace the value with
        // null or use escape to restore the original.
        // For Strings, return "" for backward compatibility.
        if ("".equals(s)) {
            if (constructor.getDeclaringClass() == String.class) {
                value1 = s;
            }
            value1 = null;
            return super.stopCellEditing();
        }
        if (formatter == null) {
            formatter = (DecimalFormat) NumberFormat.getInstance();
            formatter.setParseBigDecimal(true);
        }

//      Locale.setDefault(new Locale("nl", "NL"));
//		String s =  "2.343.298,09324798";
//		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
//		df.setParseBigDecimal(true);
//		BigDecimal bd = (BigDecimal) df.parse(s);

        try {
            value1 = constructor.newInstance(s);
            setToolTip(null);
        } catch (Exception e) {
            setInputErrored();
            setToolTip("Entered number is not valid");
            return false;
        }

        return super.stopCellEditing();
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 int row, int column) {
        this.value1 = null;
        // only BigDecimal type expected
        try {
            Class type = table.getColumnClass(column);
            // Since our obligation is to produce a value which is
            // assignable for the required type it is OK to use the
            // String constructor for columns which are declared
            // to contain Objects. A String is an Object.
            if (type == Object.class) {
                type = String.class;
            }
            constructor = type.getConstructor(argTypes);
        } catch (Exception e) {
            return null;
        }
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    @Override
    protected DataAccessor getDataAccessor() {
        //number does not need External Editor
        return null;
    }

    public Object getCellEditorValue() {
        return value1;
    }
}

