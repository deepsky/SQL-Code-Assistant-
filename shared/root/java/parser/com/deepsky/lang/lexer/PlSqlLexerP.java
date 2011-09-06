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

package com.deepsky.lang.lexer;

import com.deepsky.integration.PLSqlLexerEx;
import com.deepsky.lang.parser.adoptee.CharBufferAdopted;

public class PlSqlLexerP extends PlSqlPsiLexer {

    public void start(CharSequence charSequence, int i, int i1, int i2) {
        this.charSequence = charSequence;

//        long ms = System.currentTimeMillis();
        String rt = charSequence.toString();
        int start = rt.indexOf("IntellijIdeaRulezzz ");
        this.chars = rt.toCharArray();
        if(start != -1){
            this.chars[start+19] = '1';
        }
//        ms = System.currentTimeMillis() - ms;

        this.offset = i;
        this.size = i1;
        CharBufferAdopted adapter = new CharBufferAdopted(chars, i, i1 - i);

//        log.info("#start: i: " + i + ", i1: " + i1 + ", i2: " + i2 + ", ms: " + ms);

        aLexer = new PLSqlLexerEx(adapter);
//        aLexer = new PLSqlIdxFilterLexer(adapter);
        advance0();
    }
}
