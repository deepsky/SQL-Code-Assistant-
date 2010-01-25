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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;


public class VariableDescriptorImpl implements VariableDescriptor {

    static final long serialVersionUID = -6969586435517136467L;
    
    String name;
    Type type;
    boolean constant;
    PackageDescriptor pdesc;

    public VariableDescriptorImpl(PackageDescriptor pdesc, String name, Type type, boolean constant){
        this.pdesc = pdesc;
        this.name = name.toUpperCase();
        this.type = type;
        this.constant = constant;
    }

    public boolean isConstant() {
        return constant;
    }

    public Type getType() {
        return type;
    }

    public boolean isValid() {
        return true;
    }
    
    public PackageDescriptor getPackage() {
        return pdesc;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return DbObject.VARIABLE;
    }

    public Date getLastDDLTime() {
        // not appliable
        return null;
    }

    public void setLastDDLTime(Date lastDDL) {
        // not appliable
    }

    @Nullable
    public SqlScriptLocator getLocator() {
        return pdesc.getLocator();
    }

    public void setLocator(SqlScriptLocator url) {
    }

    @NotNull
    public DbObject getRootContext() {
        return (pdesc != null)? pdesc.getRootContext(): this;
    }

    public boolean equalsTo(PlSqlObject o) {
        if( o instanceof VariableDescriptor){
            VariableDescriptor var = (VariableDescriptor) o;
            return var.getName().equalsIgnoreCase(name) && var.getType().equals(type);
        }
        return false;
    }
}
