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
import com.deepsky.lang.plsql.NotSupportedException;

//import java.sql.Date;

import org.jetbrains.annotations.NotNull;


public class PackageSpecDescriptorImpl extends PackageDescriptorShared implements PackageSpecDescriptor {

    static final long serialVersionUID = 4960222427750030739L;
    
    public PackageSpecDescriptorImpl(SqlScriptLocator url, String name){
        this.name = name.toUpperCase();
//        ctx = new PkgDbDefinitionCtx(url, this);
        this.url = url;
    }

    public PackageSpecDescriptorImpl(String name){
        this.name = name.toUpperCase();
    }

    public boolean isObjectPublic(DbObject o) {
        // todo
        return true;
    }

    public String getTypeName() {
        return DbObject.PACKAGE;
    }

    @NotNull
    public DbObject getRootContext() {
        return this;
    }

    public PackageBodyDescriptor findBody() {
        // todo ---
        throw new NotSupportedException();
    }
}
