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

package com.deepsky.integration;

import antlr.*;

import java.io.InputStream;
import java.io.Reader;

import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.generated.plsql.SymbolTable;
import com.deepsky.generated.plsql.PLSqlLexer;


public class PLSqlLexerEx extends PLSqlLexer {

    public PLSqlLexerEx(InputStream in) {
        super(in);
    }

    public PLSqlLexerEx(Reader in) {
        super(in);
    }

    public PLSqlLexerEx(InputBuffer ib) {
        super(ib);
    }

    public PLSqlLexerEx(LexerSharedInputState state) {
        super(state);
    }

    Token deffered = null;
    private boolean prompt = false;

    public Token nextToken() throws TokenStreamException {

//        if(deffered == null){
//            return super.nextToken();
//        } else {
//            Token t = deffered;
//            deffered = null;
//            return t;
//        }

        if(deffered != null){
            Token t = deffered;
            deffered = null;
            return t;
        }

        Token token =  super.nextToken();

        // workaround to get "prompt" and "rem" clauses (SQLPLUS tool) processed correctly

        if (prompt) {
            if (token == null) {
                //if( token.getType() == PLSqlTokenTypes.LF) {
                int h =0;
            } else if( token.getType() == PLSqlTokenTypes.LF || token.getType() == PLSqlTokenTypes.EOF) {
                //aLexer.putBack(token);
                deffered = token;
                token = new CommonToken(PLSqlTokenTypes.CUSTOM_TOKEN, "");
//                token = new CommonToken(PLSqlTokenTypes.CUSTOM_TOKEN, token.getText());
                //token.
                prompt = false;
            }
        } else if (token != null &&
                ("prompt".equalsIgnoreCase(token.getText()) || "rem".equalsIgnoreCase(token.getText())
                || token.getType() == PLSqlTokenTypes.AT_PREFIXED) ) {
            prompt = true;
        }


        return token;

    }

    // Test the token text against the literals table
    // Override this method to perform a different literals test
    public int testLiteralsTable(int ttype) {
        String test = String.valueOf(text.getBuffer(), 0, text.length());
        Integer literalsIndex = SymbolTable.get(test);
        if (literalsIndex != null) {
            ttype = literalsIndex;
        }
        return ttype;
    }

    /** Test the text passed in against the literals table
     * Override this method to perform a different literals test
     * This is used primarily when you want to test a portion of
     * a token.
     */
    public int testLiteralsTable(String text, int ttype) {
        Integer literalsIndex = SymbolTable.get(text);
        if (literalsIndex != null) {
            ttype = literalsIndex;
        }
        return ttype;
    }
    
}
