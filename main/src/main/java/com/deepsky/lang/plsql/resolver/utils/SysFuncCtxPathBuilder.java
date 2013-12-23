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

package com.deepsky.lang.plsql.resolver.utils;

import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.resolver.*;
import com.deepsky.lang.plsql.struct.Type;

import java.util.ArrayList;
import java.util.List;

public class SysFuncCtxPathBuilder {

    String name;
    Type returnType;
    List<ArgumentSpec> args = new ArrayList<ArgumentSpec>();

    int ctxEnum = 0;
    String parentPath;

    PlSqlElement.CtxPath ctxPath;

    public SysFuncCtxPathBuilder(String parentPath){
        ctxPath = new ContextPathUtil.CtxPathImpl(ContextPathUtil.encodeCtx(ContextPath.SYSTEM_FUNC, 0 + "$"));
        this.parentPath = parentPath == null? "": parentPath;
    }

    public void setName(String name){
        this.name = name.toLowerCase();
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public void addArgParams(String arg_name, Type arg_type, boolean opt) {
        args.add(new ArgumentSpecImpl(arg_name, arg_type, opt));
    }

    // /Sf!..$ota_flow_manager_pkg/
    public String buildPath() {
        return parentPath + new ContextPathUtil.CtxPathImpl(ContextPathUtil.encodeCtx(ContextPath.SYSTEM_FUNC, ctxPath.getSeqNEXT() + "$")).getPath() + name;
    }

    public String getEncodedArguments() {
        return ContextPathUtil.encodeArgumentsReturnType(args.toArray(new ArgumentSpec[0]), returnType);
    }

    /**
     * Start building a new function
     */
    public void start() {
        ctxEnum++;
        args.clear();
    }

    private class ArgumentSpecImpl implements ArgumentSpec {

        String name;
        Type type;
        boolean defaultExists;

        public ArgumentSpecImpl(String name, Type type, boolean defaultExists){
            this.name = name;
            this.type= type;
            this.defaultExists = defaultExists;
        }

        public String getName() {
            return name;
        }

        public Type getType() {
            return type;
        }

        public boolean defaultExist() {
            return defaultExists;
        }
    }
}
