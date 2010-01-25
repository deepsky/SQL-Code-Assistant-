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

package com.deepsky.lang.plsql.struct.parser;

import java.util.ArrayList;
import java.util.List;


public class ContextTrackerImpl implements ContextTracker {

    List<String> paths = new ArrayList<String>();

    String schemaName;
    public ContextTrackerImpl(String schemaName){
        this.schemaName = schemaName.toLowerCase();
    }

    public ContextTrackerImpl(){
    }

    public void init() {
        paths.clear();
        if(schemaName != null){
            paths.add(ctxid2name(ContextTracker.SCHEMA_CTX) + schemaName);
        }
    }

    public void __openContext__(int type, String name) {
        paths.add(ctxid2name(type) + name.toLowerCase());
    }

    public void __closeContext__() {
        paths.remove(paths.size()-1);
    }

    private String ctxid2name(int type){
        switch(type){
            case ContextTracker.SCHEMA_CTX: return "Sh:";
            case ContextTracker.PACKAGE_SPEC: return "Ps:";
            case ContextTracker.PACKAGE_BODY: return "Pb:";
            case ContextTracker.FUNCTION_SPEC: return "Fs:";
            case ContextTracker.FUNCTION_BODY: return "Fb:";
            case ContextTracker.PROCEDURE_SPEC: return "PRs:";
            case ContextTracker.PROCEDURE_BODY: return "PRb:";
            case ContextTracker.CREATE_TRIGGER: return "Tr:";
            case ContextTracker.RECORD_TYPE: return "Rt:";
            case ContextTracker.OBJECT_TYPE: return "Ot:";
        }

        return "";
    }
    public String getPath() {
        StringBuilder b = new StringBuilder();
        for(String s: paths){
            if(b.length() > 0){
                b.append(".");
            }
            b.append(s);
        }
        return b.toString();
    }
}
