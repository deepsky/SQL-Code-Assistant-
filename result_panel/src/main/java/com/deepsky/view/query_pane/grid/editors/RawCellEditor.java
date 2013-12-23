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

import com.deepsky.view.query_pane.ConversionException;
import com.deepsky.view.query_pane.DataAccessor;
import com.deepsky.view.query_pane.ValueConvertor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class RawCellEditor<T> extends AbstractCellEditor1 {

    final ValueConvertor<T> convertor;
    private Object value1;

    public RawCellEditor(Font font, ValueConvertor<T> convertor) {
        super(font, true);
        this.convertor = convertor;
        setHorizontalAlignment(JTextField.LEFT);
    }

    public boolean stopCellEditing() {
        String s = (String) super.getCellEditorValue();
        if ("".equals(s)) {
            value1 = null;
            return super.stopCellEditing();
        }

        try {
            value1 = convertor.stringToValue(s);
        } catch (ConversionException e) {
            setInputErrored();
            setToolTip("Entered value is not valid");
            return false;
        }
        return super.stopCellEditing();
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 int row, int column) {
        this.value1 = null;
        // only RAWType type expected
        try {
            value = convertor.valueToString((T) value);
        } catch (SQLException e) {
            value = null;
        }
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    public Object getCellEditorValue() {
        return value1;
    }

    protected DataAccessor getDataAccessor() {
        T value = null;
        try {
            value = convertor.stringToValue(RawCellEditor.this.getTextValue());
        } catch (ConversionException e) {
            // todo -- handle error
            e.printStackTrace();
        }
        return new DataAccessor<T>(value, convertor) {
            public void loadFromString(String text) throws ConversionException {
                super.loadFromString(text);
                RawCellEditor.this.setValue(text);
/*
todo -- size of the cell value may be really big
                if(text != null && text.length() > 1000){
                    textField.setText(text.substring(0, 1000) + "...");
                } else {
                    textField.setText(text);
                }
*/
            }

            public boolean isReadOnly() {
                return false;
            }
        };
    }

}
