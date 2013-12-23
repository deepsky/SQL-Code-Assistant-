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

package com.deepsky.lang.plsql.resolver;

import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;


public class RefHolder {

    private String ctxPath;
    private String[] names;
    String fullName;
    int refType;
    
    public RefHolder(int refType, String ctxPath, String[] names){
        this.refType = refType;
        this.ctxPath = ctxPath;
        this.names = names;
        // NOTE: names expected to be in lower case
        switch(names.length){
            case 1: fullName = names[0]; break;
            case 2: fullName = names[0] + "." + names[1]; break;
            case 3: fullName = names[0] + "." + names[1] + "." + names[2]; break;
            case 4: fullName = names[0] + "." + names[1] + "." + names[2] + "." + names[3]; break;
            case 5: fullName = names[0] + "." + names[1] + "." + names[2] + "." + names[3] + "." + names[4]; break;
            default:
                throw new ConfigurationException("Too many parts");
        }
    }

    public RefHolder(int refType, String ctxPath, String rawName){
        this.refType = refType;
        this.ctxPath = ctxPath;
        this.fullName = rawName.toLowerCase();
        this.names = fullName.split(" *\\. *");
    }

    public String getCtxPath() {
        return ctxPath;
    }

    public int getRefType(){
        return refType;
    }

    public String getFront(){
        return names[0];
    }

    public String[] getNames(){
        return names;
    }

    public String toString(){
        return ContextPathUtil.searchTarget2Text(refType) + " " + fullName + " [Context] " + ctxPath;
    }

    public String getFullName() {
        return fullName;
//        switch(names.length){
//            case 1: return names[0];
//            case 2: return names[0] + "." + names[1];
//            case 3: return names[0] + "." + names[1] + "." + names[2];
//            case 4: return names[0] + "." + names[1] + "." + names[2] + "." + names[3];
//            default:
//                throw new ConfigurationException("Too many parts");
//        }
    }
}
