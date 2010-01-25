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

package com.deepsky.database.ora.desc;

import com.deepsky.lang.plsql.struct.*;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
//import java.sql.Date;
import java.io.Serializable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OraTableDescriptor  implements TableDescriptor, Serializable {

    static final long serialVersionUID = 19616069955152125L;
    
    String name;
    SqlScriptLocator url;

    int tableType;

    Map<String, ColumnHelper> columns = new HashMap<String, ColumnHelper>();
    Date lastDDLTime;
//    DefinitionContext ctx;
    
    public OraTableDescriptor(SqlScriptLocator url, String name){
        this.url = url;
        this.name = name;
        this.tableType = TableDescriptor.REGULAR;
    }

    public OraTableDescriptor(SqlScriptLocator url, String name, int tableType){
        this.url = url;
        this.name = name;
        this.tableType = tableType;
    }

    public String[] getColumnNames() {
        String[] out = new String[columns.size()];
        for(Map.Entry<String, ColumnHelper> e: columns.entrySet()){
            out[e.getValue().columnId-1] = e.getKey();
        }
        return out;
    }

    public Type getColumnType(String column) {
        ColumnHelper v = columns.get(column.toUpperCase());
        return (v!=null)? v.type: null;
    }

    public boolean isColumnNullable(String column) {
        ColumnHelper v = columns.get(column.toUpperCase());
        return (v == null) || v.isNullable;
    }

    public ColumnDescriptor getColumnDescriptor(String column) {

        ColumnHelper v = columns.get(column.toUpperCase());
        if(v == null){
            throw new Error("Column " + column + " not found");
        }
        return new ColumnDescriptorImpl(this, column, v);
    }

    public int getType() {
        return tableType;
    }

    public boolean isValid() {
        return true;
    }
    
    public void addColumn(String name, Type type, boolean nullable, int columnId){
        columns.put(name, new ColumnHelper(type, columnId, nullable, false));
    }

    public void addColumn(String name, Type type, boolean nullable, int columnId, boolean isPK){
        columns.put(name, new ColumnHelper(type, columnId, nullable, isPK));
    }

    public void addColumn(String name, Type type, boolean nullable, int columnId, boolean isPK, boolean isFK, String refTable, String refColumn){
        columns.put(name, new ColumnHelper(type, columnId, nullable, isPK, isFK, refTable, refColumn));
    }


    public String getName() {
        return name;
    }

    public String getTypeName() {
        return DbObject.TABLE;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLastDDLTime(Date lastDDLTime) {
        this.lastDDLTime = lastDDLTime;
    }

    @Nullable
    public SqlScriptLocator getLocator() {
        return url;
    }

    public void setLocator(SqlScriptLocator url) {
        this.url = url;
    }

    public Date getLastDDLTime() {
        return lastDDLTime;
    }

    public void setFKAttribute(String column, String refTable, String refColumn) {
        ColumnHelper chlp = columns.get(column);
        if(chlp == null){
            // todo -- looks strange
        } else {
            chlp.isFK = true;
            chlp.refTable = refTable;
            chlp.refColumn = refColumn;
        }
    }

    public void setType(int type) {
        this.tableType = type;
    }

    public void setPKAttribute(String column) {
        ColumnHelper chlp = columns.get(column);
        if(chlp == null){
            // todo -- looks strange
        } else {
            chlp.isPK = true;
        }
    }

    @NotNull
    public DbObject getRootContext() {
        return this;
    }

    public void setConstraintNamePK(String column, String constraint_name) {
        ColumnHelper chlp = columns.get(column);
        if(chlp == null){
            // todo -- looks strange
        } else {
            chlp.constraintNamePK = constraint_name;
        }
    }

    public void setConstraintNameFK(String column, String constraint_name) {
        ColumnHelper chlp = columns.get(column);
        if(chlp == null){
            // todo -- looks strange
        } else {
            chlp.constraintNameFK = constraint_name;
        }
    }
}
