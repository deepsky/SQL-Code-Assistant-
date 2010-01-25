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

import java.util.*;

public class ProcedureDescriptorImpl implements ProcedureDescriptor {

    static final long serialVersionUID = 6474519918226753391L;

    String name;
    List<String> arguments = new ArrayList<String>();
    Map<String,ParamStuff> argTypes = new HashMap<String, ParamStuff>();

    SqlScriptLocator url;
    PackageDescriptor pdesc;
    int reqArgs = 0;

    boolean errored = false;
    Date lastDDLime;

    public ProcedureDescriptorImpl(PackageDescriptor pdesc, String name){
        this.name = name.toUpperCase();
        this.pdesc = pdesc;
    }

    public ProcedureDescriptorImpl(SqlScriptLocator url, String name){
        this.name = name;
        this.url = url;
    }

    public String getName(){
        return name;
    }

    public String getTypeName() {
        return DbObject.PROCEDURE;
    }

    public Date getLastDDLTime() {
        return lastDDLime;
    }

    public void setLastDDLTime(Date lastDDLime) {
        this.lastDDLime = lastDDLime;
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

    public PackageDescriptor getPackage() {
        return pdesc;
    }

    public boolean signatureEquals(ExecutableDescriptor o) {
        return o instanceof ProcedureDescriptor && signatureEqualsInternal((ProcedureDescriptor) o);
    }

    private boolean signatureEqualsInternal(ProcedureDescriptor p) {
        if(!p.getName().equalsIgnoreCase(name) || arguments.size() != p.getArgumentNames().length) return false;

        int i = 0;
        for(String arg: p.getArgumentNames()){
            if(!arguments.get(i).equalsIgnoreCase(arg)){
                return false;
            } else {
                if(!p.getArgumentType(arg).equals(argTypes.get(arg).type)){
                    return false;
                }
            }

            i++;
        }

        return true;
    }

    public boolean isValid() {
        return true;
    }

    @NotNull
    public String[] getArgumentNames(){
        return arguments.toArray(new String[arguments.size()]);
    }

    public Type getArgumentType(String argName){
        ParamStuff p = argTypes.get(argName);
        if(p != null){
            return p.type;
        } else {
            return null;
        }
    }

    public Type getArgumentType(int argPosition) {
        return getArgumentType(arguments.get(argPosition));
    }

    public boolean hasDefault(String name) {
        ParamStuff p = argTypes.get(name);
        return p != null && p.hasDefault;
    }

    public int numberOfReqArguments() {
        return reqArgs;
    }

    public String getArgumentName(int pos) {
        if(pos>=arguments.size()){
            return null;
        } else {
            return arguments.get(pos);
        }
    }

    public void addParameter(Type type, String name) {
        arguments.add(name);
        argTypes.put(name, new ParamStuff(type, false));
        reqArgs++;
    }

    public void addParameter(Type type, String name, boolean def) {
        arguments.add(name);
        argTypes.put(name, new ParamStuff(type, def));
        reqArgs = (def)? reqArgs: reqArgs+1;
    }

    public void setErrored(boolean errored) {
        this.errored = errored;
    }

    @NotNull
    public DbObject getRootContext() {
        return (pdesc != null)? pdesc.getRootContext(): this;
    }

    public boolean equalsTo(PlSqlObject o) {
        // todo -- package is not checked
        return o instanceof ProcedureDescriptor && signatureEqualsInternal((ProcedureDescriptor) o);
    }

}
