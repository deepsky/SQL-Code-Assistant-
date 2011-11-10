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

package com.deepsky.lang.plsql.struct;

import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.struct.types.*;
import com.deepsky.lang.validation.ValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeFactory {

    private final static Map<String, Type> name2type = new HashMap<String, Type>();

    static {
        name2type.put("NUMBER", new NumberType());
        name2type.put("NUMERIC", new NumericType());
        name2type.put("VARCHAR", new Varchar2Type());
        name2type.put("VARCHAR2", new Varchar2Type());
        name2type.put("CHAR", new CharType());
        name2type.put("RAW", new RawType());
        name2type.put("BOOLEAN", new BooleanType());
        name2type.put("INTEGER", new IntegerType());
        name2type.put("PLS_INTEGER", new PlsIntegerType());
        name2type.put("DATE", new DateType());
        name2type.put("INTERVAL", new IntervalDayToSecType());
        name2type.put("TIMESTAMP", new TimestampType());
        name2type.put("TIMESTAMP WITH TIME ZONE", new TimestampTZType());
        name2type.put("TIMESTAMP WITH LOCAL TIME ZONE", new TimestampLocalTZType());
        name2type.put("NULL", new NULLType());
        name2type.put("ANYDATA", new AnydataType());
        name2type.put("ROWID", new RowidType());
        name2type.put("LONG", new LongType());
        name2type.put("CLOB", new ClobType());
        name2type.put("NCLOB", new NClobType());
        name2type.put("BLOB", new BlobType());
        name2type.put("BFILE", new BFileType());
    }


    // TODO -- the method should be replaced with referencing the constant type like: Type.INTEGER_TYPE
    public static Type createTypeByName(String typeName){
        if(typeName == null){
            return null;
        }

        Type t = name2type.get(typeName.toUpperCase());
        if(t != null){
            return t;
        } else if(typeName.toUpperCase().endsWith("%TYPE")){
            String[] parts = typeName.split("[\\.%]");
            switch(parts.length){
                case 3: // table.column%type
                    return new TableColumnRefType(parts[0], parts[1]);
                case 4: // schema.table.column%type
                    return new TableColumnRefType(parts[1], parts[2]);
            }
        } else if(typeName.toUpperCase().endsWith("%ROWTYPE")){
            String[] parts = typeName.split("[\\.%]");
            switch(parts.length){
                case 2: // table%rowtype
                    return new RowtypeType(parts[0]);
                case 3: // schema.table%rowtype
                    return new RowtypeType(parts[1]);
            }
        }

        // if none above were matched, assume it is an user defined type
        return new UserDefinedType(null, typeName);
    }


    final private static Pattern TIMESTAMP_LTZ_MATCHER = Pattern.compile("TIMESTAMP.*WITH +LOCAL +TIME +ZONE");
    final private static Pattern TIMESTAMP_TZ_MATCHER = Pattern.compile("TIMESTAMP.*WITH +TIME +ZONE");

    // todo - subject to optimize
    public static Type createTypeByName(String typeName, int data_length){
        if(typeName.toUpperCase().startsWith("NUMBER")){
            // create number type
            return new NumberType(data_length);
        } else if(typeName.toUpperCase().startsWith("NUMERIC")){
            return new NumericType(data_length);
        } else if(typeName.toUpperCase().startsWith("VARCHAR")){
            // create varchar
            return new Varchar2Type(data_length);
        } else if(typeName.toUpperCase().startsWith("VARCHAR2")){
            // create varchar
            return new Varchar2Type(data_length);
        } else if(typeName.toUpperCase().startsWith("NVARCHAR2")){
            // create varchar
            return new NVarchar2Type(data_length);
        } else if(typeName.toUpperCase().startsWith("CHAR")){
            return new CharType(data_length);
        } else if(typeName.toUpperCase().startsWith("RAW")){
            return new RawType(data_length);
        } else if(typeName.toUpperCase().startsWith("BOOLEAN")){
            return new BooleanType();
        } else if(typeName.toUpperCase().startsWith("INTEGER")){
            return new IntegerType(data_length);
        } else if(typeName.toUpperCase().startsWith("PLS_INTEGER")){
            return new PlsIntegerType(data_length);
        } else if(typeName.toUpperCase().startsWith("DATE")){
            return new DateType();
        } else if(typeName.toUpperCase().startsWith("INTERVAL")){
            return new IntervalDayToSecType();
        } else if(typeName.toUpperCase().startsWith("TIMESTAMP")){
            if(TIMESTAMP_LTZ_MATCHER.matcher(typeName).find()){
                return new TimestampLocalTZType();
            } else if(TIMESTAMP_TZ_MATCHER.matcher(typeName).find()){
                return new TimestampTZType();
            } else {
                return new TimestampType();
            }
/*
            "TIMESTAMP(6) WITH TIME ZONE"
            "TIMESTAMP(6) WITH LOCAL TIME ZONE"
*/
        } else if(typeName.toUpperCase().startsWith("NULL")){
            return new NULLType();
        } else if(typeName.toUpperCase().startsWith("ANYDATA")){
            return new AnydataType();
        } else if(typeName.toUpperCase().startsWith("ROWID")){
            return new RowidType();
        } else if(typeName.toUpperCase().startsWith("LONG")){
            return new LongType();
        } else if(typeName.toUpperCase().startsWith("CLOB")){
            return new ClobType();
        } else if(typeName.toUpperCase().startsWith("NCLOB")){
            return new NClobType();
        } else if(typeName.toUpperCase().startsWith("BLOB")){
            return new BlobType();
        } else if(typeName.toUpperCase().startsWith("BFILE")){
            return new BFileType();
        } else {
            // UNKNOWN type
            return new UserDefinedType(null, typeName);
//            return new TypeBase();
        }
    }

    public static Type createTypeById(int typeId)
    {
        switch(typeId){
            case Type.CHAR:
                return new CharType();
            case Type.DATE:
                return new DateType();
            case Type.INTEGER:
                return new IntegerType();
            case Type.PLS_INTEGER:
                return new PlsIntegerType();
            case Type.BINARY_INTEGER:
                return new BinaryIntegerType();
            case Type.INTERVAL_DAY_TO_SEC:
                return new IntervalDayToSecType();
            case Type.NUMBER:
                return new NumberType();
            case Type.NUMERIC:
                return new NumericType();
            case Type.TIMESTAMP:
                return new TimestampType();
            case Type.TIMESTAMP_LTZ:
                return new TimestampLocalTZType();
            case Type.TIMESTAMP_TZ:
                return new TimestampTZType();
            case Type.VARCHAR:
                return new Varchar2Type();
            case Type.VARCHAR2:
                return new Varchar2Type();
            case Type.NVARCHAR2:
                return new NVarchar2Type();
            case Type.ANYDATA:
                return new AnydataType();
            case Type.ROWID:
                return new RowidType();
            case Type.BOOLEAN:
                return new BooleanType();
            case Type.NULL:
                return new NULLType();
            case Type.LONG:
                return new LongType();
            case Type.CLOB:
                return new ClobType();
            case Type.NCLOB:
                return new NClobType();
            case Type.BLOB:
                return new BlobType();
            case Type.BFILE:
                return new BFileType();
            default:
                return new TypeBase();
        }
    }


    public static String encodeType(Type type) {
        String base = type.typeId() + "|" + type.typeName();
        switch(type.typeId()){
            case Type.CHAR:
                return base + "." + type.dataLength();
            case Type.DATE:
                return base;
            case Type.INTEGER:
            case Type.PLS_INTEGER:
            case Type.BINARY_INTEGER:
                return base + "." + type.dataLength();
//            case Type.INTERVAL:
//                return null;
            case Type.NUMERIC: {
                int dataLen = type.dataLength();
                return base + "." + ((dataLen<10)? "0" + dataLen: dataLen) + "00";
            }
            case Type.NUMBER: {
                int dataLen = type.dataLength();
                int part = ((NumberType)type).getPartSize();
                return base + "." + ((dataLen<10)? "0" + dataLen: dataLen) + ((part<10)? "0" + part: part);
            }
            case Type.TIMESTAMP:
                return base;
            case Type.TIMESTAMP_LTZ:
                return type.typeId() + "|" + "TIMESTAMP_LTZ";
            case Type.TIMESTAMP_TZ:
                return type.typeId() + "|" + "TIMESTAMP_TZ";
            case Type.VARCHAR:
                return base + "." + type.dataLength();
            case Type.VARCHAR2:
                return base + "." + type.dataLength();
            case Type.NVARCHAR2:
                return base + "." + type.dataLength();
            case Type.ANYDATA:
            case Type.BOOLEAN:
            case Type.NULL:
            case Type.LONG:
            case Type.CLOB:
            case Type.NCLOB:
            case Type.BLOB:
            case Type.BFILE:
            case Type.RAW:
            case Type.INTERVAL_DAY_TO_SEC:
            case Type.USER_DEFINED:
            case Type.ROWTYPE:
            case Type.REF_CURSOR:
            case Type.TABLE_COLUMN_REF_TYPE: {
                return base;
            }
            default:
                throw new ConfigurationException("Not able to decode type");
        }
    }

    public static Type decodeType(String encodedType) {

        String[] parts = encodedType.split("\\|");
        if(parts.length != 2){
            throw new ValidationException("Not able to decode type");
        }

        int typeId = Integer.parseInt(parts[0]);

        switch(typeId){
            case Type.CHAR:
                return new CharType(parserTypeLen(parts[1]));
            case Type.DATE:
                return new DateType();
            case Type.INTEGER:
                return new IntegerType(parserTypeLen(parts[1]));
            case Type.PLS_INTEGER:
                return new PlsIntegerType(parserTypeLen(parts[1]));
            case Type.BINARY_INTEGER:
                return new BinaryIntegerType(parserTypeLen(parts[1]));
//            case Type.INTERVAL:
//                return null;
            case Type.NUMERIC: {   
                int idx = parts[1].indexOf(".");
                if(idx != -1){
                    int dataLen = Integer.parseInt(parts[1].substring(idx+1, idx+3));
                    int partLen = Integer.parseInt(parts[1].substring(idx+3, idx+5));
                    return new NumericType(dataLen, partLen);
                }

                return new NumericType();
            }
            case Type.NUMBER: {   
                int idx = parts[1].indexOf(".");
                if(idx != -1){
                    int dataLen = Integer.parseInt(parts[1].substring(idx+1, idx+3));
                    int partLen = Integer.parseInt(parts[1].substring(idx+3, idx+5));
                    return new NumberType(dataLen, partLen);
                }

                return new NumberType();
            }
            case Type.TIMESTAMP:
                return new TimestampType();
            case Type.TIMESTAMP_LTZ:
                return new TimestampLocalTZType();
            case Type.TIMESTAMP_TZ:
                return new TimestampTZType();
            case Type.VARCHAR:
                return new Varchar2Type(parserTypeLen(parts[1]));
            case Type.VARCHAR2:
                return new Varchar2Type(parserTypeLen(parts[1]));
            case Type.NVARCHAR2:
                return new NVarchar2Type(parserTypeLen(parts[1]));
            case Type.ANYDATA:
                return new AnydataType();
            case Type.BOOLEAN:
                return new BooleanType();
            case Type.NULL:
                return new NULLType();
            case Type.LONG:
                return new LongType();
            case Type.CLOB:
                return new ClobType();
            case Type.NCLOB:
                return new NClobType();
            case Type.BLOB:
                return new BlobType();
            case Type.BFILE:
                return new BFileType();
            case Type.RAW:
                return new RawType();
            case Type.INTERVAL_DAY_TO_SEC:
                return new IntervalDayToSecType();
            case Type.USER_DEFINED:{
                String[] args = parts[1].split("\\.");
                String pkg = args.length == 2? args[0]: null;
                String typeRef = args.length == 2? args[1]: args[0];
                return new UserDefinedType(pkg, typeRef);
            }
            case Type.ROWTYPE: {
                String[] args = parts[1].split("[%]");
                if(parts.length == 2){
                    return new RowtypeType(args[0]);
                }
                throw new ConfigurationException("Not able to decode type");
            }
            case Type.REF_CURSOR:
                return new RefCursorType();
//                throw new ConfigurationException("Not able to decode type");
            case Type.TABLE_COLUMN_REF_TYPE: {
                String[] args = parts[1].split("[\\.%]");
                if(args.length == 3){
                    return new TableColumnRefType(args[0], args[1]);
                }
                throw new ConfigurationException("Not able to decode type");
            }
            default:
                throw new ConfigurationException("Not able to decode type");
        }
    }

    // TODO - if the package name is null then it is assumed the package name is the name of the current schema
    public static Type createUserType(String packageName, String typename) {
        return new UserDefinedType(packageName, typename);
    }


    final private static Pattern EXTRACT_LENGTH = Pattern.compile(".*\\.([0-9]+).*");

    /**
     * Parse length of the type, example:
     *      INTEGER.4
     *      VARCHAR2.34
     *      VARCHAR2.1000:notnull=false
     * @param type
     * @return
     */
    private static int parserTypeLen(String type){
        Matcher m = EXTRACT_LENGTH.matcher(type);
        if(m.find()){
            String len = m.group(1);
            return Integer.parseInt(len);
        }
       return 0;
    }



}
