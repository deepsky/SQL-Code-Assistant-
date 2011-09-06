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

import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;

public class ArgumentListHelper {

    ArgumentSpec[] formalList;
    int numberOfReqArguments = -1;

    public ArgumentListHelper(ArgumentSpec[] formalList) {
        this.formalList = formalList;
    }

    public int numberOfReqArguments() {
        numberOfReqArguments = 0;
        for (ArgumentSpec a : formalList){
            if (a != null && !a.defaultExist()) numberOfReqArguments++;
        }
        return numberOfReqArguments;
    }

    public ArgumentSpec getArgumentByName(String name) {
        for (int i = 0; i < formalList.length; i++) {
            ArgumentSpec a = formalList[i];
            if (a != null && a.getName().equalsIgnoreCase(name)) {
                formalList[i] = null;
                return a;
            }
        }
        return null;
    }

    public ArgumentSpec getArgumentByPos(int pos) {
        for (int i = 0; i < formalList.length; i++) {
            ArgumentSpec a = formalList[i];
            if (a != null && i == pos) {
                formalList[i] = null;
                return a;
            }
        }
        return null;
    }



    /**
     * Build a string argument specifications without argument names
     * <encodeTyope>:<isDefault>, ...
     *
     * i.e. "1|INTEGER:0,2|VARCHAR2:1, ..."
     *
     * @return
     */
    public String encodeArgumentsWoNames() {
        StringBuilder b = new StringBuilder();
        for (ArgumentSpec arg : formalList) {
            Type t = arg.getType();
            String encodedType = (t == null) ? "" : TypeFactory.encodeType(t);
            String isDefault = arg.defaultExist() ? "1" : "0";

            if (b.length() > 0) {
                b.append(',');
            }
            b.append(encodedType).append(':').append(isDefault);
        }

        b.append('!');
        return b.toString();
    }

}
