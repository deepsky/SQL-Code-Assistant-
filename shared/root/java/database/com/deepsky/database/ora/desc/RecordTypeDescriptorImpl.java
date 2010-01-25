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
import com.deepsky.lang.plsql.struct.types.RecordType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RecordTypeDescriptorImpl implements RecordTypeDescriptor {

    static final long serialVersionUID = 2778181510386632876L;
    
    //DefinitionContext context;
    SqlScriptLocator url;
    String name;
    List<RecordTypeItemDescriptor> out = new ArrayList<RecordTypeItemDescriptor>();

    Date lastDDLTime;
    PackageDescriptor pdesc;
    
//    public RecordTypeDescriptorImpl(DefinitionContext context, String name){
//        this.context = context;
//        this.name = name.toUpperCase();
//    }
//
    public RecordTypeDescriptorImpl(SqlScriptLocator url, String name){
        this.url = url;
        this.name = name.toUpperCase();
    }

    public RecordTypeDescriptorImpl(PackageDescriptor pdesc, String name){
        this.pdesc = pdesc;
        this.name = name.toUpperCase();
    }

    public boolean isValid() {
        return true;
    }
    
    public void addRecordItem(String name, Type type){
        out.add(new RecordTypeItemImpl(this, name, type));
    }

    public RecordTypeItemDescriptor[] getItems() {
        return out.toArray(new RecordTypeItemDescriptor[0]);
    }

    public RecordTypeItemDescriptor findItem(String name) {
        for(RecordTypeItemDescriptor item: out){
            if(name.equalsIgnoreCase(item.getName())){
                return item;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return DbObject.TYPE;
    }

    public Date getLastDDLTime() {
        return lastDDLTime;
    }

    public void setLastDDLTime(Date lastDDL) {
        lastDDLTime = lastDDL;
    }

    @Nullable
    public SqlScriptLocator getLocator() {
        if(url == null){
            if(pdesc != null){
                return pdesc.getLocator();
            }
        }
        return url;
    }

    public void setLocator(SqlScriptLocator url) {
        this.url = url;
    }

    @NotNull
    public DbObject getRootContext() {
        return (pdesc != null)? pdesc.getRootContext(): this;
    }

    public Type getType() {
        return new RecordType(name);
    }

    public PackageDescriptor getPackage() {
        return pdesc;
//        if(context != null && context instanceof PkgDbDefinitionCtx){
//            return ((PkgDbDefinitionCtx)context).getPackageDescriptor();
//        } else {
//            return null;
//        }
    }

    public boolean equalsTo(PlSqlObject o) {
        if( o instanceof RecordTypeDescriptor){
            RecordTypeDescriptor rtype = (RecordTypeDescriptor) o;
            return rtype.getType().equals(getType());
        }
        return false;
    }
}
