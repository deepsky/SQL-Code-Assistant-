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
import com.deepsky.database.ora.desc.FunctionDescriptorImpl;

import java.util.Date;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class SequenceDescriptorImpl implements SequenceDescriptor{

    static final long serialVersionUID = 5272778396823692788L;

    String name;
    Date lastDDLTime;
    SqlScriptLocator url;

    public SequenceDescriptorImpl(String name){
        this.name = name.toUpperCase();
    }

    public int incrementBy() {
        // todo -
        return 1;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return DbObject.SEQUENCE;
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

    @NotNull
    public DbObject getRootContext() {
        return this;
    }

    public Date getLastDDLTime() {
        return lastDDLTime;
    }

    public FunctionDescriptor findMethodByName(String part) {
        if(part.equalsIgnoreCase("nextval")){
            return new FunctionDescriptorImpl(null, "NEXTVAL", TypeFactory.createTypeById(Type.INTEGER));
        } else if(part.equalsIgnoreCase("curval")){
            return new FunctionDescriptorImpl(null, "CURVAL", TypeFactory.createTypeById(Type.INTEGER));
        }
        return null;
    }

    public boolean isValid() {
        return true;
    }
}
