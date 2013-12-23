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

import com.deepsky.lang.plsql.struct.types.IntegerType;
import com.deepsky.lang.plsql.struct.types.NumberType;

import java.io.Serializable;

public interface Type extends Serializable {

    public final int INTEGER = 1;

    /**
     * Variable length character string having maximum length size bytes.
     * You must specify size
     * Max size = 4000 bytes minimum is 1 
     */
    public final int VARCHAR2 = 2;

    /**
     * Now deprecated - VARCHAR is a synonym for VARCHAR2
     * but this usage may change in future versions.
     */
    public final int VARCHAR = 3;

    /**
     * Fixed length character data of length size bytes.
     * This should be used for fixed length data. Such as codes A100, B102...
     * Max size = 2000 bytes. Default and minimum size is 1 byte.
     */
    public final int CHAR = 4;

    /**
     * Number having precision p and scale s.
     */
    public final int NUMBER = 5;

    /**
     * Character data of variable length (A bigger version the VARCHAR2 datatype)
     * Max size = 2 Gigabytes - but now deprecated
     */
    public final int LONG = 6;

    /**
     * Valid date range
     * from January 1, 4712 BC to December 31, 9999 AD.
     */
    public final int DATE = 7;
    
    public final int TIMESTAMP = 8;
    public final int INTERVAL = 9;
    public final int BOOLEAN = 10;
    public final int INTERVAL_DAY_TO_SEC = 11;
    public final int INTERVAL_YEAR_TO_MONTH = 12;
    public final int USER_DEFINED = 13;
    public final int RECORD_TYPE = 14; // TYPE myType IS RECORD ( .... )
    public final int BINARY_INTEGER = 15;
    public final int RAW = 16;
    public final int ROWTYPE = 17;
    public final int REF_CURSOR = 18;
    public final int NVARCHAR2 = 19;
    public final int BLOB = 20;
//    public final int LONGTYPE = 21;
    public final int NULL = 22;
    public final int TABLE_COLUMN_REF_TYPE = 23;  // v_EmpName emp.ename%TYPE

    /**
     * Hexadecimal string representing the unique address of a row in its table.
     * (primarily for values returned by the ROWID pseudocolumn.)
     * Max size =  10 bytes
     */
    public final int ROWID = 24;

    final int ANYDATA = 0;
    final int UNKNOWN = -1;

    /**
     * Number having precision p and scale s.
     */
    public final int NUMERIC = 25;
    
    public final int PLS_INTEGER = 26;
    public final int FLOAT = 27;
    public final int CLOB = 28;
    public final int SMALLINT = 29;

    public final int OBJECT_TYPE = 30;
    public final int VARRAY_TYPE = 31;
    public final int NCLOB = 32;
    public final int BFILE = 33;

    public final int TIMESTAMP_TZ = 34;
    public final int TIMESTAMP_LTZ = 35;

    public NumberType NUMBER_TYPE = new NumberType();
    public IntegerType INTEGER_TYPE = new IntegerType();

    int typeId();

    String typeName();

    int dataLength();

    boolean isUserDefined();

    boolean isTypeReference();

    boolean equals(Object o);
}
