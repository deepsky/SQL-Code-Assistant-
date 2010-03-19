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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class FunctionDescriptorImpl implements FunctionDescriptor {

    static final long serialVersionUID = -1143376890875341301L;

    String name;
    List<String> arguments = new ArrayList<String>();
    Map<String, ParamStuff> argTypes = new HashMap<String, ParamStuff>();
    Type returnType;
    boolean isEmbedded = false;
    SqlScriptLocator url;
    PackageDescriptor pdesc;
    int reqArgs = 0;

    boolean errored = false;
    Date lastDDLime;

    public FunctionDescriptorImpl(SqlScriptLocator url, String name, Type returnType) {
        this.url = url;
        this.name = name.toUpperCase();
        this.returnType = returnType;
    }

    public FunctionDescriptorImpl(SqlScriptLocator url, String name, Type returnType, boolean isEmbedded) {
        this.url = url;
        this.name = name;
        this.returnType = returnType;
        this.isEmbedded = isEmbedded;
    }

    public FunctionDescriptorImpl(PackageDescriptor _package, String name, Type returnType, boolean isEmbedded) {
        this.pdesc = _package;
        this.name = name;
        this.returnType = returnType;
        this.isEmbedded = isEmbedded;
    }

    @NotNull
    public DbObject getRootContext() {
        return (pdesc != null) ? pdesc.getRootContext() : this;
    }

    public boolean equalsTo(PlSqlObject o) {
        // todo -- package is not checked
        return o.getTypeName().equals(DbObject.FUNCTION) && signatureEqualsInternal((FunctionDescriptor) o);
    }


    public boolean signatureEquals(ExecutableDescriptor o) {
        return o.getTypeName().equals(DbObject.FUNCTION) && signatureEqualsInternal((FunctionDescriptor) o);
    }

    private boolean signatureEqualsInternal(FunctionDescriptor f) {
        if (!f.getName().equalsIgnoreCase(name) || arguments.size() != f.getArgumentNames().length) return false;
        if (!f.getReturnType().equals(getReturnType())) return false;

        int i = 0;
        for (String arg : f.getArgumentNames()) {
            if (!arguments.get(i).equalsIgnoreCase(arg)) {
                return false;
            } else {
                if (!f.getArgumentType(arg).equals(argTypes.get(arg).type)) {
                    return false;
                }
            }

            i++;
        }

        return true;
    }

    public boolean isValid() {
        return !errored;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return DbObject.FUNCTION;
    }

    public Date getLastDDLTime() {
        return lastDDLime;
    }

    public void setErrored(boolean b) {
        this.errored = b;
    }

    @NotNull
    public String[] getArgumentNames() {
        return arguments.toArray(new String[arguments.size()]);
    }

    public Type getArgumentType(String _argName) {
        String argName = _argName.toUpperCase();
        ParamStuff p = argTypes.get(argName);
        if (p != null) {
            return p.type;
        } else {
            return null;
        }
    }

    public Type getArgumentType(int argPosition) {
        return getArgumentType(arguments.get(argPosition));
    }


    public Type getReturnType() {
        return returnType;
    }

    public boolean isEmbedded() {
        return isEmbedded;
    }

    public boolean hasDefault(String _name) {
        String name = _name.toUpperCase();
        ParamStuff p = argTypes.get(name);
        return p != null && p.hasDefault;
    }

    public int numberOfReqArguments() {
        return reqArgs;
    }

    public String getArgumentName(int pos) {
        if (pos >= arguments.size()) {
            return null;
        } else {
            return arguments.get(pos);
        }
    }

    public void addParameter(Type type, String _name) {
        String name = _name.toUpperCase();
        arguments.add(name);
        argTypes.put(name, new ParamStuff(type, false));
        reqArgs++;
    }

    public void addParameter(Type type, String _name, boolean def) {
        String name = _name.toUpperCase();
        arguments.add(name);
        argTypes.put(name, new ParamStuff(type, def));
        reqArgs = (def) ? reqArgs : reqArgs + 1;
    }

    public void setLastDDLTime(Date lastDDLime) {
        this.lastDDLime = lastDDLime;
    }

    @Nullable
    public SqlScriptLocator getLocator() {
        if (url == null) {
            if (pdesc != null) {
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

}
