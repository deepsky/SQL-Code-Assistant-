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

import java.util.Date;

import org.jetbrains.annotations.NotNull;

public class RecordTypeItemImpl implements RecordTypeItemDescriptor {

//    static final long serialVersionUID = 9183412932392267939L;
    
    String name;
    Type type;
    UserDefinedTypeDescriptor tdesc;

    public RecordTypeItemImpl(UserDefinedTypeDescriptor tdesc, String name, Type type) {
        this.tdesc = tdesc;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return "RECORD_TYPE_ITEM";
    }

    public Date getLastDDLTime() {
        return null;
    }

    public void setLastDDLTime(Date lastDDL) {
    }

    @NotNull
    public SqlScriptLocator getLocator() {
        return tdesc.getLocator();
    }

    public void setLocator(SqlScriptLocator url) {
        // todo --
    }

    public boolean isValid() {
        return true;
    }

    public Type getType() {
        return type;
    }

    public UserDefinedTypeDescriptor getParent() {
        return tdesc;
    }

    @NotNull
    public DbObject getRootContext() {
        return (tdesc != null)? tdesc.getRootContext(): this;
    }

}

