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

package com.deepsky.lang.plsql.psi.resolve;

import com.deepsky.lang.plsql.struct.Type;

import java.util.List;
import java.util.ArrayList;

public class ExecBodyResolveCtx implements ResolveContext {

    List<NameDescriptorImpl> decls = new ArrayList<NameDescriptorImpl>();
    public String[] getVariants(String prefix) {
        List<String> names = new ArrayList<String>();
        for(NameDescriptorImpl decl: decls){
            if(decl.getName().toUpperCase().startsWith(prefix.toUpperCase())){
                names.add(decl.getName());
            }
        }
        return names.toArray(new String[names.size()]);
    }

    public NameDescriptor describe(String name) {
        for(NameDescriptorImpl decl: decls){
            if(name.equalsIgnoreCase(decl.getName())){
                return decl;
            }
        }
        return null;
    }

//    public void addDecl(VariableDecl decl) {
//        decls.add(new NameDescriptorImpl(decl.getRecordItemName(), decl.getType()));
//    }

    public void addDecl(String name, Type type) {
        decls.add(new NameDescriptorImpl(name, type));
    }

    class NameDescriptorImpl implements ExpressionDescriptor { //NameDescriptor {
        String name;
        Type t;
        public NameDescriptorImpl(String name, Type t){
            this.name = name;
            this.t = t;
        }
        public String getName() {
            return name;
        }

        public Type getType() {
            return t;
        }
    }
}
