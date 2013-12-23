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

import com.deepsky.settings.SqlCodeAssistantSettings;
import com.deepsky.view.query_pane.DataAccessor;
import com.deepsky.view.query_pane.util.DateTimeParser;
import com.intellij.openapi.project.Project;
import com.joestelmach.natty.Parser;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateCellEditor  extends AbstractCellEditor1 {

    private SqlCodeAssistantSettings settings;
    private Object value;
    private Project project;

    public DateCellEditor(Project project, SqlCodeAssistantSettings settings) {
        super(settings.getGridFont(), false);
        this.settings = settings;
        this.project = project;

        setHorizontalAlignment(JLabel.RIGHT);
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
            value = null;
            return super.stopCellEditing();
        }

        try {
            Calendar calendar = DateTimeParser.getInstance(project).parse(s);
            if (calendar == null) {
                return super.stopCellEditing();
            }
            value = new Date(calendar.getTimeInMillis());
        } catch (Throwable e) {
            setInputErrored();
            setToolTip("Entered timestamp value is not valid");
            return false;
        }

        return super.stopCellEditing();
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 int row, int column) {

        this.value = null;
        String value1 = null;
        if (value != null) {
            Date d = (Date) value;
            value1 = new SimpleDateFormat(settings.getDateFormat() + " " + settings.getTimeFormat()).format(d);
        }
        return super.getTableCellEditorComponent(table, value1, isSelected, row, column);
    }

    @Override
    protected DataAccessor getDataAccessor() {
        // Timestamp does not need External Editor
        return null;
    }

    public Object getCellEditorValue() {
        return value;
    }
}
