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

import com.deepsky.generated.plsql.PLSqlTokenTypes;
import antlr.CharBuffer;
import antlr.LexerSharedInputState;
import antlr.Token;
import antlr.TokenStreamException;
import com.deepsky.integration.PLSqlLexerEx;

import java.io.ByteArrayInputStream;
import java.io.Reader;

public class PLSqlFilteredLexerImpl extends PLSqlLexerEx implements PLSqlFilteredLexer { // allow ignoring lexer errors - //PLSqlLexer {

    public PLSqlFilteredLexerImpl(Reader in) {
        super(in);
    }

    public PLSqlFilteredLexerImpl() {
        super(new ByteArrayInputStream(new byte[0]));
    }

//    public Token nextToken() throws TokenStreamException {
//        Token t = super.nextToken();
//        if(t != null){
//            if(t.getType() == PLSqlTokenTypes.WS || t.getType() == PLSqlTokenTypes.LF
//                || t.getType() == PLSqlTokenTypes.SL_COMMENT
//                || t.getType() == PLSqlTokenTypes.ML_COMMENT
//                || t.getType() == PLSqlTokenTypes.BAD_ML_COMMENT){
//                return nextToken();
//            }
//        }
//
//        return t;
//    }

    public Token nextToken() throws TokenStreamException {
        Token t = super.nextToken();
        if(t != null){
            switch(t.getType()){
                case PLSqlTokenTypes.WS:
                case PLSqlTokenTypes.LF:
                case PLSqlTokenTypes.SL_COMMENT:
                case PLSqlTokenTypes.ML_COMMENT:
                case PLSqlTokenTypes.BAD_ML_COMMENT:
                    return nextToken();
                default:
                    break;
            }
        }

        return t;
    }

    public void reload(Reader in){
        inputState = new LexerSharedInputState(new CharBuffer(in));
    }

//    public void rewind(int pos){
//        super.rewind(pos);
//    }
//
//
//    public int mark() {
//        int mark = super.mark();
//        return mark;
//    }
}
