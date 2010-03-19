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

import com.deepsky.lang.plsql.struct.*;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ViewDescriptorImpl implements ViewDescriptor {

    static final long serialVersionUID = 3844713477876605408L;
    
    Map<String, ColumnHelper> columns = new HashMap<String, ColumnHelper>();

    SqlScriptLocator contextUrl;
    String name;
    Date lastDDLTime;

    public ViewDescriptorImpl(SqlScriptLocator contextUrl, String name) {
        this.name = name;
        this.contextUrl = contextUrl;
    }

    public boolean isValid() {
        return true;
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
        return false;
    }

    public ColumnDescriptor getColumnDescriptor(String column) {

        ColumnHelper v = columns.get(column.toUpperCase());
        if(v == null){
            throw new Error("Column " + column + " not found");
        }
        return new ColumnDescriptorImpl(this, column, v);
    }

    public int getType() {
        return TableDescriptor.REGULAR;
    }

    public void addColumn(String name, Type type, boolean nullable, int columnId){
        columns.put(name, new ColumnHelper(type, columnId));
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return DbObject.VIEW;
    }

    public Date getLastDDLTime() {
        return lastDDLTime;
    }

    public void setLastDDLTime(Date lastDDLTime) {
        this.lastDDLTime = lastDDLTime;
    }

    @Nullable
    public SqlScriptLocator getLocator() {
        return contextUrl;
    }

    public void setLocator(SqlScriptLocator url) {
        this.contextUrl = url;
    }

    @NotNull
    public DbObject getRootContext() {
        return this;
    }

}
