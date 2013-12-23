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

import com.deepsky.database.ora.types.LONGRAWType;
import com.deepsky.database.ora.types.RAWType;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.utils.StringUtils;
import com.intellij.openapi.project.Project;
import oracle.sql.*;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ValueConvertor4Factory {

    public static ValueConvertor4 create(final Project project, Object value) {
        if(value instanceof CLOB){
            final CLOB clob = (CLOB) value;
            return new ValueConvertor4(){
                public long size() throws SQLException {
                    return clob.length();
                }

                public String convertToString() throws SQLException {
                    if(clob.length() == 0){
                        return "";
                    } else {
                        Reader r = clob.getCharacterStream();
                        Writer writer = new StringWriter();
                        char[] buff = new char[1000];
                        int size = 0;
                        try {
                            while((size =r.read(buff)) > 0){
                                writer.write(buff, 0, size);
                            }
                            r.close();

                            return writer.toString();
                        } catch (IOException e) {
                            throw new SQLException(e.getMessage());
                        }
                    }
                }

                public void saveValueTo(File file) throws IOException {
                    try {
                        if(clob.length() == 0){
                            StringUtils.string2file("", file);
                        } else {
                            Reader r = clob.getCharacterStream();
                            StringUtils.stream2file(r, file);
                            r.close();
                        }
                    } catch (SQLException e) {
                        throw new IOException(e.getMessage());
                    }
                }
            };
        } else if(value instanceof BLOB){
            final BLOB blob = (BLOB) value;
            return new ValueConvertor4(){
                public long size() throws SQLException {
                    return blob.length();
                }

                public String convertToString() throws SQLException {
                    if(blob.length() == 0){
                        return "";
                    } else {
                        InputStream r = blob.binaryStreamValue();
                        ByteArrayOutputStream writer = new ByteArrayOutputStream();
                        byte[] buff = new byte[1000];
                        int size = 0;
                        try {
                            while((size =r.read(buff)) > 0){
                                writer.write(buff, 0, size);
                            }
                            r.close();

                            // todo - character set??
                            return writer.toString();
                        } catch (IOException e) {
                            throw new SQLException(e.getMessage());
                        }
                    }
                }
                public void saveValueTo(File file) throws IOException {
                    try {
                        if(blob.length() == 0){
                            StringUtils.string2file("", file);
                        } else {
                            InputStream r = blob.binaryStreamValue();
                            StringUtils.stream2file(r, file);
                            r.close();
                        }
                    } catch (SQLException e) {
                        throw new IOException(e.getMessage());
                    }
                }
            };
        } else if(value instanceof BFILE){
            final BFILE bfile = (BFILE) value;
            return new ValueConvertor4(){
                public long size() throws SQLException {
                    return bfile.length();
                }

                public String convertToString() throws SQLException {
                    if(bfile.length() == 0){
                        return "";
                    } else {
                        bfile.open();
                        InputStream r = bfile.binaryStreamValue();
                        ByteArrayOutputStream writer = new ByteArrayOutputStream();
                        byte[] buff = new byte[1000];
                        int size = 0;
                        try {
                            while((size =r.read(buff)) > 0){
                                writer.write(buff, 0, size);
                            }
                            r.close();

                            // todo - character set??
                            return writer.toString();
                        } catch (IOException e) {
                            throw new SQLException(e.getMessage());
                        } finally {
                            bfile.close();
                        }
                    }
                }
                public void saveValueTo(File file) throws IOException {
                    try {
                        if(bfile.length() == 0){
                            StringUtils.string2file("", file);
                        } else {
                            bfile.open();
                            InputStream r = bfile.binaryStreamValue();
                            StringUtils.stream2file(r, file);
                            r.close();
                        }
                        bfile.close();
                    } catch (SQLException e) {
                        throw new IOException(e.getMessage());
                    }
                }
            };
        } else if(value instanceof RAW){
            final RAW raw = (RAW) value;
            return new ValueConvertor4(){
                public long size() throws SQLException {
                    return raw.getLength();
                }

                public String convertToString() throws SQLException {
                    if(raw.getLength() == 0){
                        return "";
                    } else {
                        InputStream r = raw.binaryStreamValue();
                        ByteArrayOutputStream writer = new ByteArrayOutputStream();
                        byte[] buff = new byte[1000];
                        int size = 0;
                        try {
                            while((size =r.read(buff)) > 0){
                                writer.write(buff, 0, size);
                            }
                            r.close();

                            // todo - character set??
                            return writer.toString();
                        } catch (IOException e) {
                            throw new SQLException(e.getMessage());
                        }
                    }
                }
                public void saveValueTo(File file) throws IOException {
                    try {
                        if(raw.getLength() == 0){
                            StringUtils.string2file("", file);
                        } else {
                            InputStream r = raw.binaryStreamValue();
                            StringUtils.stream2file(r, file);
                            r.close();
                        }
                    } catch (SQLException e) {
                        throw new IOException(e.getMessage());
                    }
                }
            };

       } else if(value instanceof String){
            final String string = (String) value;
            return new ValueConvertor4(){
                public long size() throws SQLException {
                    return string.length();
                }

                public String convertToString() throws SQLException {
                    return string;
                }
                public void saveValueTo(File file) throws IOException {
                    StringUtils.string2file(string, file);
                }
            };
        } else if(value instanceof BigDecimal){
             final BigDecimal dec = (BigDecimal) value;
             return new ValueConvertor4(){
                 public long size() throws SQLException {
                     return dec.toString().length();
                 }

                 public String convertToString() throws SQLException {
                     return dec.toString();
                 }
                 public void saveValueTo(File file) throws IOException {
                     StringUtils.string2file(dec.toString(), file);
                 }
             };
        } else if(value instanceof RAWType){
             final RAWType dec = (RAWType) value;
             return new ValueConvertor4(){
                 public long size() throws SQLException {
                     return dec.getValue().length*2; // take in attantion
                 }

                 public String convertToString() throws SQLException {
                     return convertByteArray2HEXString(dec.getValue());
//                     return new String(dec.getValue());
                 }
                 public void saveValueTo(File file) throws IOException {
                     if(dec.getValue().length > 0){
//                         ByteArrayInputStream in = new ByteArrayInputStream(dec.getValue());
                         String content = convertByteArray2HEXString(dec.getValue());
                         StringUtils.string2file(content, file);
                         //in.close();
                     } else {
                         StringUtils.string2file("", file);
                     }
                 }
             };
        } else if(value instanceof LONGRAWType){
             final LONGRAWType dec = (LONGRAWType) value;
             return new ValueConvertor4(){
                 public long size() throws SQLException {
                     return dec.getValue().length;
                 }

                 public String convertToString() throws SQLException {
                     return new String(dec.getValue());
                 }
                 public void saveValueTo(File file) throws IOException {
                     if(dec.getValue().length > 0){
                         ByteArrayInputStream in = new ByteArrayInputStream(dec.getValue());
                         StringUtils.stream2file(in, file);
                         in.close();
                     } else {
                         StringUtils.string2file("", file);
                     }
                 }
             };
        } else if(value instanceof java.sql.Date){
            final java.sql.Date date = (java.sql.Date) value;
            return new ValueConvertor4(){
                String pattern = PluginKeys.PLUGIN_SETTINGS.getData(project).getDateFormat();
                String pattern2 = PluginKeys.PLUGIN_SETTINGS.getData(project).getTimeFormat();

                public long size() throws SQLException {
                    return new SimpleDateFormat(pattern + " " + pattern2).format(date).length();
                }

                public String convertToString() throws SQLException {
                    return new SimpleDateFormat(pattern + " " + pattern2).format(date);
                }
                public void saveValueTo(File file) throws IOException {
                    StringUtils.string2file(new SimpleDateFormat(pattern + " " + pattern2).format(date), file);
                }
            };
        } else if(value instanceof java.sql.Timestamp){
            final java.sql.Timestamp date = (java.sql.Timestamp) value;
            return new ValueConvertor4(){
                String pattern = PluginKeys.PLUGIN_SETTINGS.getData(project).getDateFormat();
                String pattern2 = PluginKeys.PLUGIN_SETTINGS.getData(project).getTimeFormat();

                public long size() throws SQLException {
                    return new SimpleDateFormat(pattern + " " + pattern2).format(date).length();
                }

                public String convertToString() throws SQLException {
                    return new SimpleDateFormat(pattern + " " + pattern2).format(date);
                }
                public void saveValueTo(File file) throws IOException {
                    StringUtils.string2file(new SimpleDateFormat(pattern + " " + pattern2).format(date), file);
                }
            };
        } else if(value instanceof TIMESTAMP){
            final TIMESTAMP ts = (TIMESTAMP) value;
            return new ValueConvertor4(){
                String pattern = PluginKeys.PLUGIN_SETTINGS.getData(project).getDateFormat();
                String pattern2 = PluginKeys.PLUGIN_SETTINGS.getData(project).getTimeFormat();

                public long size() throws SQLException {
                    Timestamp timestamp = convertOracleTS(ts);
                    return new SimpleDateFormat(pattern + " " + pattern2).format(timestamp).length();
                }

                public String convertToString() throws SQLException {
                    Timestamp timestamp = convertOracleTS(ts);
                    return new SimpleDateFormat(pattern + " " + pattern2).format(timestamp);
                }
                public void saveValueTo(File file) throws IOException {
                    try {
                        Timestamp timestamp = convertOracleTS(ts);
                        StringUtils.string2file(new SimpleDateFormat(pattern + " " + pattern2).format(timestamp), file);
                    } catch (SQLException e) {
                        throw new IOException(e.getMessage());
                    }
                }
            };
        } else if(value instanceof TIMESTAMPTZ){
            final TIMESTAMPTZ ts = (TIMESTAMPTZ) value;
            return new ValueConvertor4(){
                String pattern = PluginKeys.PLUGIN_SETTINGS.getData(project).getDateFormat();
                String pattern2 = PluginKeys.PLUGIN_SETTINGS.getData(project).getTimeFormat();

                public long size() throws SQLException {
                    Timestamp timestamp = convertOracleTS(ts);
                    return new SimpleDateFormat(pattern + " " + pattern2).format(timestamp).length();
                }

                public String convertToString() throws SQLException {
                    Timestamp timestamp = convertOracleTS(ts);
                    return new SimpleDateFormat(pattern + " " + pattern2).format(timestamp);
                }
                public void saveValueTo(File file) throws IOException {
                    try {
                        Timestamp timestamp = convertOracleTS(ts);
                        StringUtils.string2file(new SimpleDateFormat(pattern + " " + pattern2).format(timestamp), file);
                    } catch (SQLException e) {
                        throw new IOException(e.getMessage());
                    }
                }
            };
        } else if(value instanceof TIMESTAMPLTZ){
            final TIMESTAMPLTZ ts = (TIMESTAMPLTZ) value;
            return new ValueConvertor4(){
                String pattern = PluginKeys.PLUGIN_SETTINGS.getData(project).getDateFormat();
                String pattern2 = PluginKeys.PLUGIN_SETTINGS.getData(project).getTimeFormat();

                public long size() throws SQLException {
                    Timestamp timestamp = convertOracleTS(ts);
                    return new SimpleDateFormat(pattern + " " + pattern2).format(timestamp).length();
                }

                public String convertToString() throws SQLException {
                    Timestamp timestamp = convertOracleTS(ts);
                    return new SimpleDateFormat(pattern + " " + pattern2).format(timestamp);
                }
                public void saveValueTo(File file) throws IOException {
                    try {
                        Timestamp timestamp = convertOracleTS(ts);
                        StringUtils.string2file(new SimpleDateFormat(pattern + " " + pattern2).format(timestamp), file);
                    } catch (SQLException e) {
                        throw new IOException(e.getMessage());
                    }
                }
            };
        }
        return null;
    }

    private static Timestamp convertOracleTS(TIMESTAMPTZ value) throws SQLException {
        byte[] bytes = value.getBytes();
        byte[] _bytes = new byte[11];
        System.arraycopy(bytes, 0, _bytes, 0, 11);
        return TIMESTAMP.toTimestamp(_bytes);
    }

    private static Timestamp convertOracleTS(TIMESTAMPLTZ value) throws SQLException {
        byte[] bytes = value.getBytes();
        byte[] _bytes = new byte[11];
        System.arraycopy(bytes, 0, _bytes, 0, 11);
        return TIMESTAMP.toTimestamp(_bytes);
    }

    private static Timestamp convertOracleTS(TIMESTAMP value) throws SQLException {
        return value.timestampValue();
    }

    private static String convertByteArray2HEXString(byte[] value){
        if(value == null){
            return "";
        } else {
            char[] out = new char[value.length*2];
            int i = 0;
            for(byte b: value){
                out[i++] = Integer.toHexString(b).charAt(0);
            }
            return new String(out);
        }
    }


    private static byte[] convertHEXString2ByteArray(String value){
        if(value == null || value.length() == 0){
            return null;
        } else {
            char[] array = value.toCharArray();
            byte[] out = new byte[array.length/2];
            for(int i=0; i<array.length; i+=2){
                String part = new String(array, i, 2);
                out[i++] = (byte) Integer.parseInt(part, 16);
            }
            return out;
        }
    }
}
