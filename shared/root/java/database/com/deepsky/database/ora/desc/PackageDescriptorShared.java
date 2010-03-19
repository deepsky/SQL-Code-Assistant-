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

import com.deepsky.lang.plsql.struct.PlSqlObject;
import com.deepsky.lang.plsql.struct.SqlScriptLocator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackageDescriptorShared implements Serializable {

    static final long serialVersionUID = -5026180378952292245L;

    List<PlSqlObject> objects = new ArrayList<PlSqlObject>();

    String name;

    boolean wrapped = false;
    boolean errored = false;
    boolean valid = false;

    Date lastDDLTime;
    SqlScriptLocator url;

    public void setWrapped(boolean b) {
        this.wrapped = b;
    }

    public void setErrored(boolean b) {
        this.errored = b;
    }

    public boolean isWrapped(){
        return wrapped;
    }

    public boolean isErrored(){
        return errored;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid(){
        return valid;
    }


    public Date getLastDDLTime() {
        return lastDDLTime;
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


    public String getName() {
        return name;
    }

    @NotNull
    public PlSqlObject[] findObjectByName(String name) {

        List<PlSqlObject> out = new ArrayList<PlSqlObject>();
        for(PlSqlObject dbo : objects){
            if(dbo.getName().equalsIgnoreCase(name)){
                out.add(dbo);
            }
        }
        return out.toArray(new PlSqlObject[out.size()]);
    }

    public void add(PlSqlObject dbo){
        objects.add(dbo);
    }

    @NotNull
    public PlSqlObject[] getObjects() {
        return objects.toArray(new PlSqlObject[objects.size()]);
    }

}
