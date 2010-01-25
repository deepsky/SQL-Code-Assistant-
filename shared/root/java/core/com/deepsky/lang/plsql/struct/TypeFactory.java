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

package com.deepsky.lang.plsql.struct;

import com.deepsky.lang.plsql.struct.types.*;

public class TypeFactory {

    public static Type createTypeByName(String typeName){
        if(typeName.toUpperCase().startsWith("NUMBER")){
            // create number type
            return new NumberType();
        } else if(typeName.toUpperCase().startsWith("NUMERIC")){
            return new NumericType();
        } else if(typeName.toUpperCase().startsWith("VARCHAR")){
            // create varchar
            return new Varchar2Type();
        } else if(typeName.toUpperCase().startsWith("VARCHAR2")){
            // create varchar
            return new Varchar2Type();
        } else if(typeName.toUpperCase().startsWith("CHAR")){
            return new CharType();
        } else if(typeName.toUpperCase().startsWith("RAW")){
            return new RawType();
        } else if(typeName.toUpperCase().startsWith("BOOLEAN")){
            return new BooleanType();
        } else if(typeName.toUpperCase().startsWith("INTEGER")){
            return new IntegerType();
        } else if(typeName.toUpperCase().startsWith("PLS_INTEGER")){
            return new PlsIntegerType();
        } else if(typeName.toUpperCase().startsWith("DATE")){
            return new DateType();
        } else if(typeName.toUpperCase().startsWith("INTERVAL")){
            return new IntervalDayToSecType();
        } else if(typeName.toUpperCase().startsWith("TIMESTAMP")){
            return new TimestampType();
        } else if(typeName.toUpperCase().startsWith("NULL")){
            return new NULLType();
        } else if(typeName.toUpperCase().startsWith("ANY")){
            return new AnyType();
        } else if(typeName.toUpperCase().endsWith("%TYPE")){
            String[] parts = typeName.split("[\\.%]");
            if(parts.length == 3){
                return new TableColumnRefType(parts[0], parts[1]);
            }
            return new TypeBase();
        } else {
            // UNKNOWN type
//            return new TypeBase();
            return new UserDefinedType(null, typeName);
        }
    }


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
            return new TimestampType();
        } else if(typeName.toUpperCase().startsWith("NULL")){
            return new NULLType();
        } else if(typeName.toUpperCase().startsWith("ANY")){
            return new AnyType();
        } else if(typeName.toUpperCase().startsWith("ROWID")){
            return new RowidType();
        } else {
            // UNKNOWN type
            return new TypeBase();
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
            case Type.INTERVAL:
                return null;
            case Type.NUMBER:
                return new NumberType();
            case Type.NUMERIC:
                return new NumberType();
            case Type.TIMESTAMP:
                return new TimestampType();
            case Type.VARCHAR:
                return new Varchar2Type();
            case Type.VARCHAR2:
                return new Varchar2Type();
            case Type.NVARCHAR2:
                return new NVarchar2Type();
            case Type.ANY:
                return new AnyType();
            case Type.BOOLEAN:
                return new BooleanType();
            case Type.NULL:
                return new NULLType();
            default:
                return new TypeBase();
        }
    }

    // TODO - if the package name is null then it is assumed the package name is the name of the current schema
    public static Type createUserType(String packageName, String typename) {
        return new UserDefinedType(packageName, typename);
    }

    public static String[] listDataTypes() {
        return new String[]{
            "NUMBER","NUMERIC","VARCHAR","VARCHAR2","CHAR",
            "BOOLEAN","INTEGER","PLS_INTEGER","DATE","TIMESTAMP",
            "ROWID", "INTERVAL", "NVARCHAR2", "BLOB", "CLOB", "FLOAT"
        };
    }
}
