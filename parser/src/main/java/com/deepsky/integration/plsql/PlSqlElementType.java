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

package com.deepsky.integration.plsql;

import com.deepsky.lang.common.PlSqlFileType;
import com.intellij.psi.tree.IElementType;

import java.util.HashMap;
import java.util.Map;


public class PlSqlElementType extends IElementType {

    static private final Map<Short, Integer> ielem2antlr_tokentype = new HashMap<Short, Integer>();

    static final public int NOT_REGISTER = -1;
    int antlrTokenType;

    public PlSqlElementType(String debugName, int antlrTokenType) {
        super(debugName, PlSqlFileType.FILE_TYPE.getLanguage());
        this.antlrTokenType = antlrTokenType;

        ielem2antlr_tokentype.put(getIndex(), antlrTokenType);
    }

    public String toString() {
        return "PLSQL:" + super.toString();
    }

    public int getElementTyoe() {
        return antlrTokenType;
    }

    static public int mapTo_TokenType(IElementType elem) {
        Integer tokenType = ielem2antlr_tokentype.get(elem.getIndex());
        if (tokenType != null) {
            return tokenType;
        } else {
            return NOT_REGISTER;
        }
    }
}
