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

package com.deepsky.lang.plsql.psi.resolve;

import com.deepsky.database.ObjectCache;
import com.deepsky.database.ObjectCacheFactory;
import com.deepsky.lang.plsql.struct.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PackageDescriptorAggregate2 { //implements PackageDescriptor {

    PackageSpecDescriptor spec;
    PackageBodyDescriptor body;
    String packageName;

    public PackageDescriptorAggregate2(PackageSpecDescriptor spec) throws NameNotResolvedException {
        this.spec = spec;

        DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(ObjectCache.PACKAGE_BODY, spec.getName());
        if(objects.length == 1){
            this.body = (PackageBodyDescriptor) objects[0];
        }

        this.packageName = spec.getName();
    }

    public PackageDescriptorAggregate2(PackageBodyDescriptor body) throws NameNotResolvedException {
        this.spec = null;
        this.body = body;
        this.packageName = body.getName();
    }

    public PackageDescriptorAggregate2(String packageName) throws NameNotResolvedException {

        DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(ObjectCache.PACKAGE, packageName);
        if(objects.length != 1 || !(objects[0] instanceof PackageSpecDescriptor)){
            throw new NameNotResolvedException("Package " + packageName + " not found");
        }
        this.spec = (PackageSpecDescriptor) objects[0];

        objects = ObjectCacheFactory.getObjectCache().findByNameForType(ObjectCache.PACKAGE_BODY, packageName);
        if(objects.length == 1){
            this.body = (PackageBodyDescriptor) objects[0];
        }

        this.packageName = packageName;
    }

    static private void addWithFiltering(PlSqlObject what, List<PlSqlObject> where){
        for(PlSqlObject e: where){
            if(e.equalsTo(what)){
                // attempt to add a duplicate, skip adding
                return;
            }
        }
        where.add(what);
    }


    public String getPackageName(){
        return packageName;
    }

    public PlSqlObject[] getObjects() {
        List<PlSqlObject> out = new ArrayList<PlSqlObject>();
        if(this.spec == null){
            DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(ObjectCache.PACKAGE, packageName);
            if(objects.length == 1 && objects[0] instanceof PackageSpecDescriptor){
                this.spec = (PackageSpecDescriptor) objects[0];
                processSpec(out);
            }
        } else {
            processSpec(out);
        }

        if(body != null){
            for(PlSqlObject o: body.getObjects()){
                addWithFiltering(o, out);
            }
        }

        return out.toArray(new PlSqlObject[out.size()]);
    }


    @NotNull
    public PlSqlObject[] findObjectByName(String part) {
        List<PlSqlObject> out = new ArrayList<PlSqlObject>();
        if(this.spec == null){
            DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(ObjectCache.PACKAGE, packageName);
            if(objects.length == 1 && objects[0] instanceof PackageSpecDescriptor){
                this.spec = (PackageSpecDescriptor) objects[0];
                processSpec(out, part);
            }
        } else {
            processSpec(out, part);
        }

//        for(PlSqlObject o: spec.findObjectByName(part)){
//            addWithFiltering(o, out);
//        }

        if(body != null){
            for(PlSqlObject o: body.findObjectByName(part)){
                addWithFiltering(o, out);
            }
        }
        return out.toArray(new PlSqlObject[out.size()]);
    }

    public PackageSpecDescriptor getPackageSpec(){
        return spec;
    }
    
    private void processSpec(List<PlSqlObject> out){
        for(PlSqlObject o: spec.getObjects()){
            addWithFiltering(o, out);
        }
    }

    private void processSpec(List<PlSqlObject> out, String name){
        for(PlSqlObject o: spec.findObjectByName(name)){
            addWithFiltering(o, out);
        }
    }

    private void processBody(List<PlSqlObject> out){
        for(PlSqlObject o: body.getObjects()){
            addWithFiltering(o, out);
        }
    }


/*
    public boolean isObjectPublic(DbObject o) {
        // todo --
        return false;
    }

    public boolean isWrapped() {
        // todo --
        return false;
    }

    public void setWrapped(boolean wrapped) {
        // todo --
    }

    public boolean isErrored() {
        // todo --
        return false;
    }

    public void setErrored(boolean errored) {
        // todo --
    }

    public String getName() {
        return packageName;
    }

    public String getTypeName() {
        return DbObject.PACKAGE;
    }

    public Date getLastDDLTime() {
        // todo --
        return null;
    }

    public void setLastDDLTime(Date lastDDL) {
        // todo --
    }

    public SqlScriptLocator getLocator() {
        // todo --
        return null;
    }

    public void setLocator(SqlScriptLocator url) {
        // todo --
    }

    public boolean isValid() {
        // todo --
        return true;
    }

    @NotNull
    public DbObject getRootContext() {
        // todo --
        return this;
    }

    public boolean equalsTo(DbObject o) {
        return o instanceof ;
    }

    public void setValid(boolean valid) {
    }
*/

    public UserDefinedTypeDescriptor findUdtByName(String typeName) {
        for(PlSqlObject obj: findObjectByName(typeName)){
            if(obj instanceof UserDefinedTypeDescriptor){
                return (UserDefinedTypeDescriptor) obj;
            }
        }
        return null;
    }
}
