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

package com.deepsky.lang.plsql.struct.types;

import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.psi.resolve.TypeNotResolvedException;
import com.deepsky.database.ObjectCacheFactory;
import com.deepsky.database.ObjectCache;

public class TableColumnRefType extends TypeBase{
    static final long serialVersionUID = 2216176371502220275L;

    String table;
    String column;

    public TableColumnRefType(String table, String column){
        super(Type.TABLE_COLUMN_REF_TYPE, "TABLE_COLUMN_REF_TYPE");
        this.table = table;
        this.column = column;
    }

    public boolean isTypeReference() {
        return true;
    }

    public String getTable() {
        return table;
    }

    public String getColumn() {
        return column;
    }

    public String typeName() {
        return table + "." + column + "%type";
    }

    public String toString() {
        return table + "." + column + "%type";
    }

    public Type getRealType(){
        DbObject[] objects = ObjectCacheFactory
                .getObjectCache()
                .findByNameForType(ObjectCache.TABLE, table);

        if (objects.length == 1 && objects[0] instanceof TableDescriptor) {
            TableDescriptor tdesc = (TableDescriptor) objects[0];
            Type t = tdesc.getColumnType(column);
            if(t != null){
                return t;
            } else {
                throw new TypeNotResolvedException("Table " + table + " has no column " + column);
            }
        } else {
            throw new TypeNotResolvedException("Table not found: " + table);
        }
    }
}
