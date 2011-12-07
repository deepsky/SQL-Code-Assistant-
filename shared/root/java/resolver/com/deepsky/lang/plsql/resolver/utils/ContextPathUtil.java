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

package com.deepsky.lang.plsql.resolver.utils;

import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.psi.Argument;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.ContextPathProvider;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.struct.types.NumberType;
import com.deepsky.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContextPathUtil {

    // table name separator
    final static public String TABLE_SEP = "*";

    final static public String STATUS_ATTR = "status"; 

    private static Map<String, Integer> dbType2ctx = new HashMap<String, Integer>();
    private static Map<String, int[]> dbType2ctxMulti = new HashMap<String, int[]>();

    static {
        dbType2ctx.put(DbObject.TABLE, ContextPath.TABLE_DEF);
        dbType2ctx.put(DbObject.PACKAGE, ContextPath.PACKAGE_SPEC);
        dbType2ctx.put(DbObject.PACKAGE_BODY, ContextPath.PACKAGE_BODY);
        dbType2ctx.put(DbObject.VIEW, ContextPath.VIEW_DEF);
        dbType2ctx.put(DbObject.TRIGGER, ContextPath.CREATE_TRIGGER);
        dbType2ctx.put(DbObject.SEQUENCE, ContextPath.SEQUENCE);
        dbType2ctx.put(DbObject.SYNONYM, ContextPath.SYNONYM);
        dbType2ctx.put(DbObject.FUNCTION, ContextPath.FUNCTION_BODY);
        dbType2ctx.put(DbObject.PROCEDURE, ContextPath.PROCEDURE_BODY);
        dbType2ctx.put(DbObject.FUNCTION_BODY, ContextPath.FUNCTION_BODY);
        dbType2ctx.put(DbObject.PROCEDURE_BODY, ContextPath.PROCEDURE_BODY);
        //dbType2ctx.put(DbObject.TYPE, ContextPath.R);

        dbType2ctxMulti.put(DbObject.TABLE, new int[]{ContextPath.TABLE_DEF});
        dbType2ctxMulti.put(DbObject.PACKAGE, new int[]{ContextPath.PACKAGE_SPEC});
        dbType2ctxMulti.put(DbObject.PACKAGE_BODY, new int[]{ContextPath.PACKAGE_BODY});
        dbType2ctxMulti.put(DbObject.VIEW, new int[]{ContextPath.VIEW_DEF});
        dbType2ctxMulti.put(DbObject.TRIGGER, new int[]{ContextPath.CREATE_TRIGGER});
        dbType2ctxMulti.put(DbObject.SEQUENCE, new int[]{ContextPath.SEQUENCE});
        dbType2ctxMulti.put(DbObject.SYNONYM, new int[]{ContextPath.SYNONYM});
        dbType2ctxMulti.put(DbObject.FUNCTION, new int[]{ContextPath.FUNCTION_BODY});
        dbType2ctxMulti.put(DbObject.PROCEDURE, new int[]{ContextPath.PROCEDURE_BODY});
        dbType2ctxMulti.put(DbObject.TYPE, new int[]{
                ContextPath.COLLECTION_TYPE,
                ContextPath.RECORD_TYPE,
                ContextPath.OBJECT_TYPE,
                ContextPath.VARRAY_TYPE});
        dbType2ctxMulti.put(DbObject.VIEW_INTERNAL_TYPE, new int[]{ContextPath.VIEW_DEF});

    }

    // todo -- TYPE is not handled correctly
    @Deprecated
    public static int dbType2ctxType(String dbType) {
        Integer _ctxType = dbType2ctx.get(dbType);
        return (_ctxType != null) ? _ctxType : -1;
    }

    public static int[] dbType2ctxTypeMulti(String dbType) {
        int[] types = dbType2ctxMulti.get(dbType);
        return types==null? new int[0]: types;
    }

    public static String ctxType2dbType(int type) {
        // todo - Dirty hack, should be fixed ASAP
        switch (type) {
            case ContextPath.PROCEDURE_BODY:
                return DbObject.PROCEDURE;
            case ContextPath.FUNCTION_BODY:
                return DbObject.FUNCTION;
            default:
                for (Map.Entry<String, Integer> e : dbType2ctx.entrySet()) {
                    if (e.getValue() == type) {
                        return e.getKey();
                    }
                }
        }
        return null;
    }


    public static String[] listSupportedDbTypes() {
        return dbType2ctxMulti.keySet().toArray(new String[0]);
    }


    /**
     * Extract sequence number from the path,
     *
     * @param path - example: /Pb!00$get_queue_owner_and_name/Bl!02$
     * @return - 0
     */
    public static int extractSeqNumFormFirstPathSegment(String path) {
        try {
            return Integer.parseInt(path.substring(4, 6), 16);
        } catch (Throwable e) {
            return -1;
        }
    }

    public static int extractLastCtxType(String ctxPath) {
        int excl = ctxPath.lastIndexOf("!");
        return ContextPathUtil.prefix2type(ctxPath.substring(excl - 3, excl + 1));
    }

    public static String extractLastCtxName(String ctxPath) {
        int excl = ctxPath.lastIndexOf("!");
        return excl != -1? ctxPath.substring(excl + 4): null;
    }

    public static String extractParentCtx(String ctxPath) {
        int excl = ctxPath.lastIndexOf("!");
        final String ctxName = ctxPath.substring(excl + 4, ctxPath.length());
        final String ret = ctxPath.substring(0, ctxPath.length() - (ctxName.length() + 4 + 3));
        return ret != null && ret.length()==0? null: ret; //ctxPath.substring(0, ctxPath.length() - (ctxName.length() + 4 + 3));
    }

    public static boolean argumentEquals(ArgumentSpec[] a1, ArgumentSpec[] a2) {
        if (a1.length != a2.length) {
            return false;
        }
        for (int i = 0; i < a1.length; i++) {
            if (!a1[i].getName().equals(a2[i].getName())
                    || !a1[i].getType().equals(a2[i].getType())
                    || !(a1[i].defaultExist() == a2[i].defaultExist())) {
                return false;
            }
        }
        return true;
    }

    public static ContextAttributes extractPackageCtx(String ctxPath) {
        // TODO -- implement me
        return null;  //To change body of created methods use File | Settings | File Templates.
    }


    static public class ContextEncoder {
        StringBuilder b;

        public ContextEncoder(String filePath) {
            b = new StringBuilder(fileCtxHeader + filePath);
        }

        public String getPath() {
            return b.toString();
        }

        public ContextEncoder appendContext(int ctxType, int seqNum, String name) {
            b.append(ContextPathUtil.encodeCtx(ctxType, ((seqNum == -1) ? ".." : encodeSeqNum(seqNum)) + name));
            return this;
        }
    }

    public static String encodeFilePathCtx(String filePath) {
        filePath = filePath == null ? "" : filePath.replace(' ', '?').replace('\\', '|').replace('/', '|');
        return ContextPathUtil.encodeCtx(ContextPath.FILE_CTX, "..$" + filePath);
    }

    private static final String fileCtxHeader = ContextPath.FILE_CTX_PRX + "..$";

    public static String extractFilePath(String ctxPath) {
        if (ctxPath.startsWith(fileCtxHeader)) {
            String rawFilePath = ctxPath.substring(fileCtxHeader.length());
            int index = rawFilePath.indexOf('/');
            if (index != -1) {
                rawFilePath = rawFilePath.substring(0, index);
            }
            return rawFilePath == null ? "" : rawFilePath.replace('?', ' ').replace('|', '/');
        }
        return null;
    }

    // For the path: /FL!..$synonym!dba_policy_groups.dump/Sy!..$dba_policy_groups
    // FileContext is /FL!..$synonym!dba_policy_groups.dump
    public static String extractFileCtx(String ctxPath) {
        if (ctxPath.startsWith(fileCtxHeader)) {
            String[] ctxPaths = ctxPath.split("/");
            if(ctxPaths.length > 1){
                return "/" + ctxPaths[1];
            }
        }
        return null;
    }

    // For the path: /FL!..$synonym!dba_policy_groups.dump/Sy!..$dba_policy_groups
    // Object name is: dba_policy_groups
    // Object type is: SYNONYM

    public static String extractTopObjectName(String ctxPath) {
        String[] paths = ctxPath.split("/");
        if (paths.length > 2) {
            return paths[2].substring(6);
        }

        return null;
    }

    public static int extractTopObjectType(String ctxPath) {
        String[] paths = ctxPath.split("/");
        if (paths.length > 2) {
            String name = "/" + paths[2];
            return ContextPathUtil.prefix2type(name.substring(0, 4));
        }

        return -1;
    }


    /**
     * Extract package name if top object is package (todo -- package spec vs package body?)
     *
     * @param ctxPath
     * @return
     */
    public static String extractPackageName(String ctxPath) {
        String[] paths = ctxPath.split("/");
        if (paths.length > 2 ){
            int startWith = ("/" + paths[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;
            if(ContextPath.PACKAGE_SPEC_PRX.equals("/" + paths[startWith].substring(0, 3))) {
                return paths[startWith].substring(6);
            } else if(ContextPath.PACKAGE_BODY_PRX.equals("/" + paths[startWith].substring(0, 3))) {
                return paths[startWith].substring(6);
            }
        }
//        if (paths.length > 2 && ContextPath.PACKAGE_SPEC_PRX.equals("/" + paths[2].substring(0, 3))) {
//            return paths[2].substring(6);
//        }

        return null;
    }


    public static String encodeCtx(int type, String name) {
        switch (type) {
            case ContextPath.SCHEMA_CTX:
                return ContextPath.SCHEMA_CTX_PRX + name;
            case ContextPath.PACKAGE_SPEC:
                return ContextPath.PACKAGE_SPEC_PRX + name;
            case ContextPath.PACKAGE_BODY:
                return ContextPath.PACKAGE_BODY_PRX + name;
            case ContextPath.FUNCTION_SPEC:
                return ContextPath.FUNCTION_SPEC_PRX + name;
            case ContextPath.FUNCTION_BODY:
                return ContextPath.FUNCTION_BODY_PRX + name;
            case ContextPath.PROCEDURE_SPEC:
                return ContextPath.PROCEDURE_SPEC_PRX + name;
            case ContextPath.PROCEDURE_BODY:
                return ContextPath.PROCEDURE_BODY_PRX + name;
            case ContextPath.CREATE_TRIGGER:
                return ContextPath.CREATE_TRIGGER_PRX + name;

            case ContextPath.RECORD_TYPE:
                return ContextPath.RECORD_TYPE_PRX + name;
            case ContextPath.OBJECT_TYPE:
                return ContextPath.OBJECT_TYPE_PRX + name;
            case ContextPath.COLLECTION_TYPE:
                return ContextPath.COLLECTION_TYPE_PRX + name;
            case ContextPath.VARRAY_TYPE:
                return ContextPath.VARRAY_TYPE_PRX + name;

            case ContextPath.TABLE_DEF:
                return ContextPath.TABLE_DEF_PRX + name;
            case ContextPath.VIEW_DEF:
                return ContextPath.VIEW_DEF_PRX + name;
            case ContextPath.COLUMN_DEF:
                return ContextPath.COLUMN_DEF_PRX + name;

            case ContextPath.VARIABLE_DECL:
                return ContextPath.VARIABLE_DECL_PRX + name;
            case ContextPath.ARGUMENT:
                return ContextPath.ARGUMENT_PRX + name;
            case ContextPath.RECORD_ITEM:
                return ContextPath.RECORD_ITEM_PRX + name;
            case ContextPath.SELECT_EXPR:
                return ContextPath.SELECT_EXPR_PRX + name;
            case ContextPath.FROM_TABLE_REF:
                return ContextPath.FROM_TABLE_REF_PRX + name;
            case ContextPath.FROM_ALIAS_REF:
                return ContextPath.FROM_ALIAS_REF_PRX + name;

            case ContextPath.LOOP_STMT:
                return ContextPath.LOOP_STMT_PRX + name;
            case ContextPath.LOOP_INDEX:
                return ContextPath.LOOP_INDEX_PRX + name;
            case ContextPath.UPDATE_STMT:
                return ContextPath.UPDATE_STMT_PRX + name;
            case ContextPath.DELETE_STMT:
                return ContextPath.DELETE_STMT_PRX + name;
            case ContextPath.INSERT_STMT:
                return ContextPath.INSERT_STMT_PRX + name;
            case ContextPath.MERGE_STMT:
                return ContextPath.MERGE_STMT_PRX + name;
            case ContextPath.PLSQL_BLOCK:
                return ContextPath.PLSQL_BLOCK_PRX + name;
            case ContextPath.SUBQUERY:
                return ContextPath.SUBQUERY_PRX + name;
            case ContextPath.REF_CURSOR:
                return ContextPath.REF_CURSOR_PRX + name;

            case ContextPath.SYSTEM_FUNC:
                return ContextPath.SYSTEM_FUNC_PRX + name;

            case ContextPath.SEQUENCE:
                return ContextPath.SEQUENCE_PRX + name;

            case ContextPath.FILE_CTX:
                return ContextPath.FILE_CTX_PRX + name;

            case ContextPath.SYNONYM:
                return ContextPath.SYNONYM_PRX + name;
            case ContextPath.CURSOR_DECL:
                return ContextPath.CURSOR_DECL_PRX + name;
            case ContextPath.CURSOR_LOOP_SPEC:
                return ContextPath.CURSOR_LOOP_SPEC_PRX + name;
            case ContextPath.SELECT_EXPR_UNION:
                return ContextPath.SELECT_EXPR_UNION_PRX + name;
        }

        throw new ConfigurationException("");
    }


    private static String t =
            ContextPath.SCHEMA_CTX_PRX +
                    ContextPath.PACKAGE_SPEC_PRX + ContextPath.PACKAGE_BODY_PRX +
                    ContextPath.FUNCTION_SPEC_PRX + ContextPath.FUNCTION_BODY_PRX +
                    ContextPath.PROCEDURE_SPEC_PRX + ContextPath.PROCEDURE_BODY_PRX +
                    ContextPath.CREATE_TRIGGER_PRX +
                    ContextPath.RECORD_TYPE_PRX + ContextPath.OBJECT_TYPE_PRX +
                    ContextPath.COLLECTION_TYPE_PRX + ContextPath.VARRAY_TYPE_PRX +
                    ContextPath.TABLE_DEF_PRX + ContextPath.VIEW_DEF_PRX +
                    ContextPath.COLUMN_DEF_PRX +
                    ContextPath.VARIABLE_DECL_PRX + ContextPath.ARGUMENT_PRX +
                    ContextPath.RECORD_ITEM_PRX + ContextPath.SELECT_EXPR_PRX +
                    ContextPath.FROM_TABLE_REF_PRX + ContextPath.FROM_ALIAS_REF_PRX +
                    ContextPath.LOOP_STMT_PRX + ContextPath.LOOP_INDEX_PRX +
                    ContextPath.UPDATE_STMT_PRX + ContextPath.DELETE_STMT_PRX +
                    ContextPath.INSERT_STMT_PRX + ContextPath.MERGE_STMT_PRX +
                    ContextPath.PLSQL_BLOCK_PRX + ContextPath.SUBQUERY_PRX +
                    ContextPath.REF_CURSOR_PRX + ContextPath.SYSTEM_FUNC_PRX +
                    ContextPath.SEQUENCE_PRX + ContextPath.FILE_CTX_PRX +
                    ContextPath.SYNONYM_PRX + ContextPath.CURSOR_DECL_PRX +
                    ContextPath.CURSOR_LOOP_SPEC_PRX + ContextPath.SELECT_EXPR_UNION_PRX;


    public static int prefix2type(String name) {
        switch (t.indexOf(name)) {
            case 0:
                return ContextPath.SCHEMA_CTX;
            case 4:
                return ContextPath.PACKAGE_SPEC;
            case 2 * 4:
                return ContextPath.PACKAGE_BODY;
            case 3 * 4:
                return ContextPath.FUNCTION_SPEC;
            case 4 * 4:
                return ContextPath.FUNCTION_BODY;
            case 5 * 4:
                return ContextPath.PROCEDURE_SPEC;
            case 6 * 4:
                return ContextPath.PROCEDURE_BODY;
            case 7 * 4:
                return ContextPath.CREATE_TRIGGER;

            case 8 * 4:
                return ContextPath.RECORD_TYPE;
            case 9 * 4:
                return ContextPath.OBJECT_TYPE;
            case 10 * 4:
                return ContextPath.COLLECTION_TYPE;
            case 11 * 4:
                return ContextPath.VARRAY_TYPE;

            case 12 * 4:
                return ContextPath.TABLE_DEF;
            case 13 * 4:
                return ContextPath.VIEW_DEF;
            case 14 * 4:
                return ContextPath.COLUMN_DEF;

            case 15 * 4:
                return ContextPath.VARIABLE_DECL;
            case 16 * 4:
                return ContextPath.ARGUMENT;
            case 17 * 4:
                return ContextPath.RECORD_ITEM;
            case 18 * 4:
                return ContextPath.SELECT_EXPR;

            case 19 * 4:
                return ContextPath.FROM_TABLE_REF;
            case 20 * 4:
                return ContextPath.FROM_ALIAS_REF;

            case 21 * 4:
                return ContextPath.LOOP_STMT;
            case 22 * 4:
                return ContextPath.LOOP_INDEX;
            case 23 * 4:
                return ContextPath.UPDATE_STMT;
            case 24 * 4:
                return ContextPath.DELETE_STMT;
            case 25 * 4:
                return ContextPath.INSERT_STMT;
            case 26 * 4:
                return ContextPath.MERGE_STMT;
            case 27 * 4:
                return ContextPath.PLSQL_BLOCK;
            case 28 * 4:
                return ContextPath.SUBQUERY;
            case 29 * 4:
                return ContextPath.REF_CURSOR;
            case 30 * 4:
                return ContextPath.SYSTEM_FUNC;
            case 31 * 4:
                return ContextPath.SEQUENCE;
            case 32 * 4:
                return ContextPath.FILE_CTX;
            case 33 * 4:
                return ContextPath.SYNONYM;
            case 34 * 4:
                return ContextPath.CURSOR_DECL;
            case 35 * 4:
                return ContextPath.CURSOR_LOOP_SPEC;
            case 36 * 4:
                return ContextPath.SELECT_EXPR_UNION;
            default:
                return -1;
        }
    }


    public static String searchTarget2Text(int target) {
        switch (target) {
            case ContextPath.GENERIC_NAME_REF:
                return "[Gen Ref]";
            case ContextPath.PLSQL_NAME_REF:
                return "[PLSQL Ref]";
            case ContextPath.TYPE_REF:
                return "[Type Ref]";
            case ContextPath.TABLE_REF:
                return "[Table Ref]";
            case ContextPath.TABLE_COLUMN_REF:
                return "[TabCol Ref]";
            case ContextPath.RECORD_ITEM_REF:
                return "[RecItem Ref]";
            case ContextPath.FUNC_CALL:
                return "[Func Call]";
            case ContextPath.PROC_CALL:
                return "[Proc Call]";
            default:
                return "[$$$]";
        }
    }

    public static int extractPartitionTypeFromTableValue(String value) {
        int start = value.indexOf("partition=");
        if (start >= 0) {
            int end = value.indexOf("|", start);
            end = (end == -1) ? value.length() : end;
            return Integer.parseInt(value.substring(start + "partition=".length(), end));
        }
        return -1;
    }

    /**
     * @param value -
     *              return value being returned matches to the value returned by the call to TableDefinition.getTableType()
     */
    public static int extractTableTypeFromTableValue(String value) {
        int start = value.indexOf("tabletype=");
        if (start >= 0) {
            int end = value.indexOf("|", start);
            end = (end == -1) ? value.length() : end;
            return Integer.parseInt(value.substring(start + "tabletype=".length(), end));
        }
        return -1;
    }


    public static class CtxPathParser {
        int ctxType;
        String ctxName = "";
        String ctxPath;

        public CtxPathParser(String ctxPath) {
            if (ctxPath == null || ctxPath.length() == 0) {
                ctxType = ContextPath.SCHEMA_CTX;
            } else {
                int excl = ctxPath.lastIndexOf("!");
                ctxType = ContextPathUtil.prefix2type(ctxPath.substring(excl - 3, excl + 1));
                ctxName = ctxPath.substring(excl + 4, ctxPath.length());
            }

            this.ctxPath = ctxPath;
        }

        public int extractLastCtxType() {
            return ctxType;
        }

        public String lastCtxName() {
            return ctxName;
        }

        public String getParentCtx() {
            return ctxPath.substring(0, ctxPath.length() - (ctxName.length() + 4 + 3));
        }

        public CtxPathParser getParentCtxParser() {
            String p = ctxPath.substring(0, ctxPath.length() - (ctxName.length() + 4 + 3));
            if(p != null){
                return new CtxPathParser(p);
            }
            return null;
        }

        public String lastCtx() {
            return ctxPath.substring(ctxPath.length() - (ctxName.length() + 4 + 3), ctxPath.length());
        }

        public String extractFileName() {
            if (ContextPathUtil.prefix2type(ctxPath.substring(0, 4)) == ContextPath.FILE_CTX) {
                int end = ctxPath.indexOf("/", 4);
                return ctxPath.substring(7, end);
            }
            return null;
        }


    }


    public static class CtxPathIterator {
        String ctxPath;
        int end;

        public CtxPathIterator(String ctxPath) {
            this.ctxPath = ctxPath;
            end = ctxPath != null && ctxPath.length() > 0 ? -100 : -1;
        }

        public boolean next() {
            switch (end) {
                case -100:
                    end = 0;
                    return true;
                case -1:
                    return false;
                default:
                    return (end = ctxPath.indexOf("/", end + 1)) != -1;
            }
        }

        public int getType() {
            if (end >= 0) {
                return ContextPathUtil.prefix2type(ctxPath.substring(end, end + 4));
            } else {
                return -1;
            }
        }

        public String getName() {
            if (end >= 0) {
                int endPos = ctxPath.indexOf("/", end + 7);
                return ctxPath.substring(end + 7, endPos != -1 ? endPos : ctxPath.length());
            } else {
                return null;
            }
        }

        public String getPath() {
            if (end >= 0) {
                int endPos = ctxPath.indexOf("/", end + 7);
                return ctxPath.substring(0, endPos != -1 ? endPos : ctxPath.length());
            } else {
                return null;
            }
        }

        public boolean last() {
            switch (end) {
                case -100:
                case -1:
                    return false;
                default:
                    return ctxPath.indexOf("/", end + 1) == -1;
            }
        }
    }


    public static class CtxPathImpl implements ContextPathProvider.CtxPath {
        private int sequence = 0;
        String path;

        public CtxPathImpl(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

        public String getSeqNEXT() {
            return ids[0xff & sequence++];
        }
    }

    public static String encodeSeqNum(int seq) {
        return ids[0xff & seq];
    }


    final static String[] ids = {
            "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F",
            "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F",
            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F",
            "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F",
            "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F",
            "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F",
            "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "0A", "0B", "0C", "0D", "0E", "0F",
            "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F",
            "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F",
            "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F",
            "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF",
            "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF",
            "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF",
            "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF",
            "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF",
            "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF"
    };

    // [Contex Management Stuff] End ---------------------------------


    public static SynonymAttributes decodeSynonymValue(String value) {
        String[] parts = value.split("\\|");
        String synonymOwner = "PUBLIC"; // by default
        String[] schema_name;
        switch (parts.length) {
            case 2:
                // You cannot specify a schema for the synonym if you have specified PUBLIC. (Oracle Database SQL Reference)
                // decode target
                synonymOwner = parts[1];
            case 1:
                schema_name = parts[0].split("\\.");
                return new SynonymAttributes(
                        schema_name.length == 1 ? null : schema_name[0],
                        schema_name.length == 1 ? schema_name[0] : schema_name[1],
                        synonymOwner
                );
            default:
                return null;
        }
    }

    public static String encodeSynonymValue(String targetSchema, String targetObj, String synonymOwner) {
        String out = targetObj;
        if (targetSchema != null) {
            out = targetSchema + "." + targetObj;
        }

        out = out + "|";
        if (synonymOwner != null) {
            out = out + synonymOwner;
        }
        return out;
    }


    /*
     * Encoding format: "P:<CONSTRANT_NAME>:<OWN_COLUMNS>"
     */

    public static String encodePKValue(String constrName, String[] primaryKeys) {
        return ("p:" + constrName + ":" + StringUtils.convert2listStringsWoQuotes(primaryKeys)).toLowerCase();
    }

    private final static Pattern pkPattern = Pattern.compile("p:([^\\|:]+):([^\\|:]+)");
    private final static Pattern fkPattern = Pattern.compile("f:([^\\|:]+):([^\\|:]+):([^\\|:]+):([^\\|:]+)");
    private final static Pattern fkConstrPattern = Pattern.compile("(f:[^\\|:]+:[^\\|:]+:[^\\|:]+:[^\\|:]+)");

    public static String[] extractPKColumnsFromTableValue(String value) {
        Matcher m = pkPattern.matcher(value);
        if (m.find()) {
            return m.group(2).split("\\,");
        }
        return null;
    }

    public static String extractPKConstrNameFromTableValue(String value) {
        Matcher m = pkPattern.matcher(value);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    public static interface FKConstraintSpec {
        String getName();

        String getRefTable();

        String[] getOwnColumns();

        String[] getRefColumns();
    }

    public static interface ContextAttributes {
        String getCtxPath();
        String getCtxName();
        int getCtxType();
    }

    public static List<FKConstraintSpec> extractFKConstraintsFromTableValue(String value) {
        List<FKConstraintSpec> out = new ArrayList<FKConstraintSpec>();
        Matcher m = fkConstrPattern.matcher(value);
        while (m.find()) {
            out.add(new FKConstraintSpecImpl(m.group(1)));
        }
        return out;
    }


    private static class FKConstraintSpecImpl implements FKConstraintSpec {
        String value;

        public FKConstraintSpecImpl(String value) {
            this.value = value;
        }

        public String getName() {
            Matcher m = fkPattern.matcher(value);
            if (m.find()) {
                return m.group(1);
            }
            return null;
        }

        public String getRefTable() {
            Matcher m = fkPattern.matcher(value);
            if (m.find()) {
                return m.group(3);
            }
            return null;
        }

        public String[] getOwnColumns() {
            Matcher m = fkPattern.matcher(value);
            if (m.find()) {
                return m.group(2).split("\\,");
            }
            return null;
        }

        public String[] getRefColumns() {
            Matcher m = fkPattern.matcher(value);
            if (m.find()) {
                return m.group(4).split("\\,");
            }
            return null;
        }
    }

    public static String extractFKConstrNameFromTableValue(String value) {
        Matcher m = fkPattern.matcher(value);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    public static String[] extractFKOwnColumnsFromTableValue(String value) {
        Matcher m = fkPattern.matcher(value);
        if (m.find()) {
            return m.group(2).split("\\,");
        }
        return null;
    }

    public static String extractFKRefTableFromTableValue(String value) {
        Matcher m = fkPattern.matcher(value);
        if (m.find()) {
            return m.group(3);
        }
        return null;
    }

    public static String[] extractFKRefColumnsFromTableValue(String value) {
        Matcher m = fkPattern.matcher(value);
        if (m.find()) {
            return m.group(4).split("\\,");
        }
        return null;
    }

    /*
     * Encoding format: "F:<CONSTRANT_NAME>:<OWN_COLUMNS>:<REFERENCED_TABLE>:<REFERENCED_COLUMNS>"
     */

    public static String encodeFKValue(String constrName, String[] ownColumns, String refTable, String[] refColumns) {
        return ("f:" + constrName
                + ":" + StringUtils.convert2listStringsWoQuotes(ownColumns)
                + ":" + refTable
                + ":" + StringUtils.convert2listStringsWoQuotes(refColumns)
        ).toLowerCase();
    }


    /*
     * Encoding format: "<TYPE_NAME>:notnull=true"
     */

    public static String encodeColumnValue(Type type, boolean notNull) {
        String t = "";
        if (type != null) {
            t = TypeFactory.encodeType(type);
        }

        return t + ":notnull=" + notNull;
    }


    /*
     * Encoding format: "<TYPE_NAME>:notnull=true|p::|f:::<REFERENCED_TABLE>:<REFERENCED_COLUMN>"
     */
    public static String encodeColumnValue(Type type, boolean notNull, boolean isPK, String fk_refColumn, String fk_refTable) {
        StringBuilder sb = new StringBuilder();
        if (type != null) {
            sb.append(TypeFactory.encodeType(type));
        }
        sb.append(":notnull=").append(notNull);
        if (isPK) {
            sb.append("|p::");
        }

        if (fk_refColumn != null) {
            sb.append("|f:::").append(fk_refTable).append(":").append(fk_refColumn);
        }

        return sb.toString();
    }

    public static String encodeColumnValue(Type type, String constraintName, boolean notNull, boolean isPK, String fk_refColumn, String fk_refTable) {
        StringBuilder sb = new StringBuilder();
        if (type != null) {
            sb.append(TypeFactory.encodeType(type));
        }
        sb.append(":notnull=").append(notNull);
        if (isPK) {
            if(constraintName != null){
                sb.append("|p:").append(constraintName.toLowerCase()).append(":");
            } else {
                sb.append("|p::");
            }
        } else {
            if (fk_refColumn != null) {
                if(constraintName != null){
                    sb.append("|f:").append(constraintName.toLowerCase()).append("::");
                } else {
                    sb.append("|f:::");
                }
                sb.append(fk_refTable).append(":").append(fk_refColumn);
            }
        }

        return sb.toString();
    }

    public static boolean extractIsPKFromColumnValue(String encoded) {
        return encoded.contains("|p:");
    }


    // Encoding format: "F:<CONSTRANT_NAME>:<OWN_COLUMNS>:<REFERENCED_TABLE>:<REFERENCED_COLUMNS>"
    private static Pattern FK_PATTERN = Pattern.compile(".*\\|f:([^:]*):([^:]*):([^:]*):([^:]*).*");
    private static Pattern PK_FK_CONSTR_PATTERN = Pattern.compile("(\\|p:([^:]*):[^:]*)|(\\|f:([^:]*):[^:]*:[^:]*:[^:]*)");

    public static String extractRefColumnFromColumnValue(String encoded) {
        Matcher m = FK_PATTERN.matcher(encoded);
        if(m.find()){
            return m.group(4);
        }
        return null;
    }

    public static String extractRefTableFromColumnValue(String encoded) {
        Matcher m = FK_PATTERN.matcher(encoded);
        if(m.find()){
            return m.group(3);
        }
        return null;
    }

    public static String extractConstraintNameFromColumnValue(String encoded) {
        Matcher m = PK_FK_CONSTR_PATTERN.matcher(encoded);
        if(m.find()){
            final String constraintName = m.group(2);
            if(constraintName != null){
                return constraintName;
            } else {
                return m.group(4);
            }
        }
        return null;
    }


    public static Type decodeColumnTypeFromValue(String encoded) {
        String[] splitted = encoded.split("\\:notnull=");
        return TypeFactory.decodeType(splitted[0]);
    }

    public static boolean decodeColumnNotNull(String encoded) {
        String[] splitted = encoded.split("\\:notnull=");
        return splitted[1].startsWith("true");
    }

    public static class SynonymAttributes {
        public String targetObj;
        public String targetSchema;
        public String synonymOwner;

        public SynonymAttributes(String targetSchema, String targetObj, String synonymOwner) {
            this.targetSchema = targetSchema;
            this.targetObj = targetObj;
            this.synonymOwner = synonymOwner;
        }
    }


    static private Pattern args_return_type = Pattern.compile("([a-zA-Z0-9_\\$\\&:\\.,#%\\|]*)!([a-zA-Z0-9\\|_\\.\\$\\&#%]*)");
    static private Pattern arg_params = Pattern.compile("([a-zA-Z0-9_\\$\\.#%]+):([a-zA-Z0-9_\\$\\.#%\\|]+):([0-9]+)");

    // arg1:1|INTEGER:0,arg2:2|VARCHAR2:1, ...

    static public String encodeArguments(Argument[] args) {
        StringBuilder b = new StringBuilder();
        for (Argument arg : args) {
            String name = arg.getArgumentName().toLowerCase();
            String encodedType = TypeFactory.encodeType(arg.getType());
            String isDefault = arg.getDefaultExpr() != null ? "1" : "0";

            if (b.length() > 0) {
                b.append(',');
            }
            b.append(name).append(':').append(encodedType).append(':').append(isDefault);
        }

        b.append('!');
        return b.toString();
    }

    // arg1:1|INTEGER:0,arg2:2|VARCHAR2:1, ...

    static public String encodeArguments(ArgumentSpec[] args) {
        StringBuilder b = new StringBuilder();
        for (ArgumentSpec arg : args) {
            String name = arg.getName().toLowerCase();
            Type t = arg.getType();
            String encodedType = (t == null) ? "" : TypeFactory.encodeType(t);
            String isDefault = arg.defaultExist() ? "1" : "0";

            if (b.length() > 0) {
                b.append(',');
            }
            b.append(name).append(':').append(encodedType).append(':').append(isDefault);
        }

        b.append('!');
        return b.toString();
    }


    // arg1:1|INTEGER:0,arg2:2|VARCHAR2:1, ... !1|INTEGER

    static public String encodeArgumentsReturnType(Argument[] args, Type type) {
        StringBuilder b = new StringBuilder();
        for (Argument arg : args) {
            String name = arg.getArgumentName().toLowerCase();
            String encodedType = TypeFactory.encodeType(arg.getType());
            String isDefault = arg.getDefaultExpr() != null ? "1" : "0";

            if (b.length() > 0) {
                b.append(',');
            }
            b.append(name).append(':').append(encodedType).append(':').append(isDefault);
        }

        b.append('!').append(TypeFactory.encodeType(type));
        return b.toString();
    }

    // arg1:1|INTEGER:0,arg2:2|VARCHAR2:1, ...

    static public String encodeArgumentsReturnType(ArgumentSpec[] args, Type type) {
        StringBuilder b = new StringBuilder();
        for (ArgumentSpec arg : args) {
            String name = arg.getName().toLowerCase();
            String encodedType = TypeFactory.encodeType(arg.getType());
            String isDefault = arg.defaultExist() ? "1" : "0";

            if (b.length() > 0) {
                b.append(',');
            }
            b.append(name).append(':').append(encodedType).append(':').append(isDefault);
        }

        b.append('!').append(TypeFactory.encodeType(type));
        return b.toString();
    }

    public static ArgumentSpec[] extractArguments(String uid) {
        Matcher m = args_return_type.matcher(uid);
        if (m.find()) {
            String args = m.group(1);
            if (args.length() == 0) {
                return new ArgumentSpec[0];
            }
            String retType = m.group(2);
            String[] pairs = args.split(",");
            ArgumentSpec[] out = new ArgumentSpec[pairs.length];
            for (int i = 0; i < pairs.length; i++) {
                String[] name_type_def = pairs[i].split(":");
                String tt = name_type_def[1];
                out[i] = new ArgumentSpecImpl(
                        name_type_def[0],
                        (tt == null || tt.length() == 0) ? null : TypeFactory.decodeType(tt),
                        !name_type_def[2].equals("0")
                );

                ArgumentSpecImpl ddd = new ArgumentSpecImpl(
                        name_type_def[0],
                        (tt == null || tt.length() == 0) ? null : TypeFactory.decodeType(tt),
                        !name_type_def[2].equals("0")
                );
                ddd.name = "";
            }
            return out;
        }

        return new ArgumentSpec[0];
    }


    public static Type extractRetType(String uid) {
        Matcher m = args_return_type.matcher(uid);
        if (m.find()) {
            String retType = m.group(2);
            if (retType != null && retType.length() > 0) {
                return TypeFactory.decodeType(retType);
            }
        }

        return null;
    }


    private static class ArgumentSpecImpl implements ArgumentSpec {
        String name;
        Type type;
        boolean def;

        public ArgumentSpecImpl(String name, Type type, boolean def) {
            this.name = name;
            this.type = type;
            this.def = def;
        }

        public String getName() {
            return name;
        }

        public Type getType() {
            return type;
        }

        public boolean defaultExist() {
            return def;
        }
    }


    public static class ExtAttributeParser {
        String[] attrs;
        public ExtAttributeParser(String[] attrs) {
            this.attrs = attrs;
        }

        public String get(String attrName) {
            for (String attr : attrs) {
                String _attrName = decodeAttrName(attr);
                if (_attrName.equals(attrName)) {
                    return decodeAttrValue(attr);
                }
            }
            return null;
        }

        public ExtAttributeParser setAttrValue(String attrName, String value){
            int i = 0;
            for(; i<attrs.length; i++){
                String _attrName = decodeAttrName(attrs[i]);
                if(_attrName.equals(attrName)){
                    // replace existing attribute with new one
                    attrs[i] = encodeAttrValue(attrName, value);
                    return this;
                }
            }
            attrs = increaseArrayByOne(attrs);
            // replace existing attribute with new one
            attrs[i] = encodeAttrValue(attrName, value);
            return this;
        }

        public String[] getAttrs(){
            return attrs;
        }
    }

    static private String[] increaseArrayByOne(String[] extAttr) {
        String[] newExtAttr = new String[extAttr.length+1];
        System.arraycopy(extAttr, 0, newExtAttr, 0, extAttr.length);
        return newExtAttr;
    }

    static private String encodeAttrValue(String attrName, String value) {
        String attrLen;
        if(attrName.length() > 99){
            throw new ConfigurationException("Attribute name is too long: " + attrName);
        } else if(attrName.length() <= 9){
            attrLen = "0" + Integer.toString(attrName.length());
        } else {
            // attribute name exceed 9 characters
            attrLen = Integer.toString(attrName.length());
        }

        return attrLen + attrName + "|" + value;
    }


    // <attr_name_size><attr_name>|<value>
    // <attr_name_size> is size of two digits
    // example: "09timestamp|....."
    static private String decodeAttrName(String value) {
        int len = value.length();
        if(len < 4){
            return ""; // not valid attribute
        }

        try {
            int size = Integer.parseInt(value.substring(0, 2));
            return len >= size + 2 +1? value.substring(2, 2+size): "";
        } catch(NumberFormatException  e){
            // ignore
        }
        return "";
    }

    static private String decodeAttrValue(String value) {
        int len = value.length();
        if(len < 4){
            return ""; // not valid attribute
        }

        try {
            int size = Integer.parseInt(value.substring(0, 2));
            return len >= size + 2 +1? value.substring(2+size + 1): "";
        } catch(NumberFormatException  e){
            // ignore
        }
        return "";
    }

}
