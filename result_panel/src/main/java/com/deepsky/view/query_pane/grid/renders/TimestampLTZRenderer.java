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

package com.deepsky.view.query_pane.grid.renders;

import com.deepsky.view.query_pane.grid.DateTimeFormatProvider;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPLTZ;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class TimestampLTZRenderer extends DefaultTableCellRenderer.UIResource implements DateTimeFormatProvider {
    DateFormat formatter;

    public TimestampLTZRenderer() {
        super();
        setHorizontalAlignment(JLabel.RIGHT);
    }

    public void setValue(Object _value) {
        Timestamp value = null;
        try {
            if (_value != null) {
                byte[] bytes = ((TIMESTAMPLTZ) _value).getBytes();
                value = TIMESTAMP.toTimestamp(bytes);
            } else {
                value = null;
            }
        } catch (SQLException e) {
            // todo - handle
        }
        if (formatter == null) {
            String pattern = getFormat();
            if (pattern != null) {
                formatter = new SimpleDateFormat(pattern);
            } else {
                formatter = DateFormat.getDateInstance();
            }
        }
        setText((value == null) ? "" : formatter.format(value));
    }
}
