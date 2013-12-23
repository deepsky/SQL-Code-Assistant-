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

package com.deepsky.lang.plsql.struct.parser;

import antlr.*;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.integration.PLSqlLexerEx;
import com.deepsky.utils.*;

import java.io.ByteArrayInputStream;
import java.io.Reader;

public class PLSqlIndexingLexer extends PLSqlLexerEx implements PLSqlFilteringLexer {

    boolean keyword = false;
    WordProcessor processor = null;

    public PLSqlIndexingLexer(){
        super(new ByteArrayInputStream(new byte[0]));
    }

    public PLSqlIndexingLexer(InputBuffer ib) {
        super(ib);
    }

    public PLSqlIndexingLexer(Reader in){
        super(in);
    }

    public void setWordProcessor(WordProcessor processor) {
        this.processor = processor;
    }

    public Token nextToken() throws TokenStreamException {
        Token t = super.nextToken();
        if (t != null) {
            switch(t.getType()){
                case PLSqlTokenTypes.WS:
                case PLSqlTokenTypes.LF:
                case PLSqlTokenTypes.SL_COMMENT:
                case PLSqlTokenTypes.ML_COMMENT:
                case PLSqlTokenTypes.BAD_ML_COMMENT:
                    return nextToken();

                case PLSqlTokenTypes.LITERAL_number:
                case PLSqlTokenTypes.LITERAL_numeric:
                case PLSqlTokenTypes.LITERAL_varchar:
                case PLSqlTokenTypes.LITERAL_boolean:
                case PLSqlTokenTypes.LITERAL_null:

                case PLSqlTokenTypes.LITERAL_date: // todo -- is it possible ident DATE? need to check
                case PLSqlTokenTypes.LITERAL_integer:
                case PLSqlTokenTypes.LITERAL_pls_integer:
                    keyword = false;
                    return nextToken();

                case PLSqlTokenTypes.IDENTIFIER:
                    if(processor != null){
                        processor.process(t.getText());
                    }
                    break;
                case PLSqlTokenTypes.DOUBLE_QUOTED_STRING:
                    if(processor != null){
                        processor.process(com.deepsky.utils.StringUtils.discloseDoubleQuotes(getText()));
                    }
                    break;
                default:
                    if(keyword && processor != null){
                        processor.process(t.getText());
                    }
                    break;
            }
        }

        keyword = false;
        return t;
    }

    public void reload(Reader in) {
        inputState = new LexerSharedInputState(new CharBuffer(in));
    }

    public int testLiteralsTable(int ttype) {
        int ret = super.testLiteralsTable(ttype);
        keyword = ret != ttype;
        return ret;
    }

    public int testLiteralsTable(String text, int ttype) {
        int ret = super.testLiteralsTable(text, ttype);
        keyword = ret != ttype;
        return ret;
    }

}
