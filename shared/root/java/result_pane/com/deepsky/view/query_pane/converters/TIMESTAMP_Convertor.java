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

package com.deepsky.view.query_pane.converters;

import com.deepsky.settings.SqlCodeAssistantSettings;
import com.deepsky.utils.StringUtils;
import com.deepsky.view.query_pane.ConversionException;
import com.deepsky.view.query_pane.ValueConvertor;
import oracle.sql.TIMESTAMP;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TIMESTAMP_Convertor implements ValueConvertor<TIMESTAMP> {
    SqlCodeAssistantSettings settings;

    public TIMESTAMP_Convertor(SqlCodeAssistantSettings settings){
        this.settings = settings;
    }

    public long size(TIMESTAMP value) throws SQLException {
        return valueToString(value).length();
    }

    public String valueToString(TIMESTAMP value) throws SQLException {
        if(value == null){
            return "";
        } else {
            Timestamp timestamp = convertOracleTS(value);
            return new SimpleDateFormat(settings.getDateFormat() + " " + settings.getTimeFormat()).format(timestamp);
        }
    }

    public TIMESTAMP stringToValue(String stringPresentation) throws ConversionException {
//            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
//            java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
//            value = new TIMESTAMP(sqlDate);
        if(stringPresentation != null && stringPresentation.length() > 0){
            try {
                Date date = new SimpleDateFormat(settings.getDateFormat() + " " + settings.getTimeFormat()).parse(stringPresentation);
                java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
                return new TIMESTAMP(sqlDate);
            } catch (ParseException e) {
                throw new ConversionException();
            } catch (Throwable e) {
                throw new ConversionException();
            }
        }

        return null;
    }

    private static Timestamp convertOracleTS(TIMESTAMP value) throws SQLException {
        return value.timestampValue();
    }

}
