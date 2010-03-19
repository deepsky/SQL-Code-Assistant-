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

import com.deepsky.lang.plsql.struct.TriggerDescriptor;
import com.deepsky.lang.plsql.struct.SqlScriptLocator;
import com.deepsky.lang.plsql.struct.DbObject;

import java.util.Date;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class TriggerDescriptorImpl implements TriggerDescriptor {

    static final long serialVersionUID = 3844713477870905408L;

    String name;
    String tableName;
    Date lastDDL;
    SqlScriptLocator url;

    boolean valid;
    boolean errored;
    boolean wrapped;

    public TriggerDescriptorImpl(SqlScriptLocator url, String name, String tableName){
        this.url = url;
        this.name = name;
        this.tableName = tableName;
    }

    public TriggerDescriptorImpl(String name, String tableName){
        this.name = name;
        this.tableName = tableName;
    }


    public String getTableName() {
        return tableName;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return DbObject.TRIGGER;
    }

    public Date getLastDDLTime() {
        return lastDDL;
    }

    public void setLastDDLTime(Date lastDDL) {
        this.lastDDL = lastDDL;
    }

    @Nullable
    public SqlScriptLocator getLocator() {
        return url;
    }

    public void setLocator(SqlScriptLocator url) {
        this.url = url;
    }

    public boolean isValid() {
        return valid;
    }

    @NotNull
    public DbObject getRootContext() {
        return this;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setWrapped(boolean wrapped) {
        this.wrapped = wrapped;
    }

    public void setErrored(boolean errored) {
        this.errored = errored;
    }
}
