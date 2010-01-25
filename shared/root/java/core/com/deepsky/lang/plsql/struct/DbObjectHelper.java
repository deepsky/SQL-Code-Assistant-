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

import java.util.HashMap;
import java.util.Map;

public class DbObjectHelper {

    private static Map<String, Integer> dbo2typecode;

    static {
        dbo2typecode = new HashMap<String, Integer>();

        dbo2typecode.put(DbObject.COLUMN, DbObject.COLUMN_INT);
        dbo2typecode.put(DbObject.FUNCTION, DbObject.FUNCTION_INT);
        dbo2typecode.put(DbObject.PROCEDURE, DbObject.PROCEDURE_INT);
        dbo2typecode.put(DbObject.PACKAGE, DbObject.PACKAGE_INT);
        dbo2typecode.put(DbObject.SEQUENCE, DbObject.SEQUENCE_INT);
        dbo2typecode.put(DbObject.SYNONYM, DbObject.SYNONYM_INT);
        dbo2typecode.put(DbObject.TABLE, DbObject.TABLE_INT);
        dbo2typecode.put(DbObject.TYPE, DbObject.TYPE_INT);
        dbo2typecode.put(DbObject.VARIABLE, DbObject.VARIABLE_INT);
        dbo2typecode.put(DbObject.VIEW, DbObject.VIEW_INT);
    }


    public static int dbo2typecode(DbObject dbo){
        return dbo2typecode.get(dbo.getTypeName().toUpperCase());
    }
}
