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

import antlr.InputBuffer;
import antlr.Token;
import antlr.TokenStreamException;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.integration.PLSqlLexerEx;

import java.util.HashSet;
import java.util.Set;

public class PLSqlIdxFilterLexer extends PLSqlLexerEx {

    boolean keyword = false;
    WordProcessor processor = new WordIndexBuilder();

    public PLSqlIdxFilterLexer(InputBuffer ib) {
        super(ib);
    }

    public Token nextToken() throws TokenStreamException {
        Token t = super.nextToken();

        if (t.getType() != PLSqlTokenTypes.EOF) {
            switch(t.getType()){
                case PLSqlTokenTypes.WS:
                case PLSqlTokenTypes.LF:
                case PLSqlTokenTypes.OPEN_PAREN:
                case PLSqlTokenTypes.CLOSE_PAREN:
                case PLSqlTokenTypes.COMMA:
                case PLSqlTokenTypes.DOT:
                case PLSqlTokenTypes.SEMI:
                case PLSqlTokenTypes.PERCENTAGE:
                case PLSqlTokenTypes.COLON:
                case PLSqlTokenTypes.ASTERISK:
                case PLSqlTokenTypes.PLUS:
                case PLSqlTokenTypes.MINUS:
                case PLSqlTokenTypes.DIVIDE:
                case PLSqlTokenTypes.EQ:

                case PLSqlTokenTypes.SL_COMMENT:
                case PLSqlTokenTypes.ML_COMMENT:
                case PLSqlTokenTypes.BAD_ML_COMMENT:
                case PLSqlTokenTypes.BAD_CHARACTER:
                case PLSqlTokenTypes.BAD_CHAR_LITERAL:
                case PLSqlTokenTypes.BAD_NUMBER_LITERAL:

                case PLSqlTokenTypes.QUOTED_STR:
                case PLSqlTokenTypes.DOUBLE_QUOTED_STRING: // todo - requries special processing
                case PLSqlTokenTypes.NUMBER:
                case PLSqlTokenTypes.DOLLAR:

                case PLSqlTokenTypes.LITERAL_number:
                case PLSqlTokenTypes.LITERAL_numeric:
                case PLSqlTokenTypes.LITERAL_varchar:
                case PLSqlTokenTypes.LITERAL_boolean:
                case PLSqlTokenTypes.LITERAL_null:

                case PLSqlTokenTypes.LITERAL_date: // todo -- is it possible ident DATE? need to check
                case PLSqlTokenTypes.LITERAL_integer:
                case PLSqlTokenTypes.LITERAL_pls_integer:

                    break;
                case PLSqlTokenTypes.IDENTIFIER:
                    if(processor != null){
                        processor.process(t.getText());
                    }
                    break;
                default:
                    if(processor != null){
                        processor.process(t.getText());
                    }
                    break;
            }
        } else {
            int hh =0;
        }

        return t;
    }


    static class WordIndexBuilder implements WordProcessor {

        Set<String> words = new HashSet<String>();
        int wordsNum = 0;

        public void process(String text) {
            words.add(text.toLowerCase());
            wordsNum++;
        }

        public String getIndex() {
            StringBuilder sb = new StringBuilder();
            for (String s : words) {
                sb.append(s).append(".");
            }

            return "." + sb.toString();
        }
    }

}
