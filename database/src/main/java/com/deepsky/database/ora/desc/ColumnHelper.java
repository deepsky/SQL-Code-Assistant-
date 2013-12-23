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

package com.deepsky.database.ora.desc;

import com.deepsky.lang.plsql.struct.Type;

import java.io.Serializable;

public class ColumnHelper implements Serializable {

    static final long serialVersionUID = 3995824070744122435L;
    
    public Type type;
    public int columnId;
    public boolean isNullable;
    public boolean isPK;
    public boolean isFK;
    public String refTable;
    public String refColumn;
    public String constraintNameFK;
    public String constraintNamePK;
    String defaultValue;

    public ColumnHelper(Type type, int columnId) {
        this.type = type;
        this.columnId = columnId;
    }

    public ColumnHelper(Type type, int columnId, boolean isNullable, boolean isPK) {
        this.type = type;
        this.columnId = columnId;
        this.isNullable = isNullable;
        this.isPK = isPK;
    }

    public ColumnHelper(
            Type type, int columnId, boolean isNullable,
            boolean isPK, boolean isFK, String refTable, String refColumn) {
        this.type = type;
        this.columnId = columnId;
        this.isNullable = isNullable;
        this.isPK = isPK;
        this.isFK = isFK;
        this.refTable = refTable;
        this.refColumn = refColumn;
    }

    
    public ColumnHelper(Type type, int columnId, boolean isNullable, boolean isPK, String defaultValue) {
        this.type = type;
        this.columnId = columnId;
        this.isNullable = isNullable;
        this.isPK = isPK;
        this.defaultValue = defaultValue;
    }
}

