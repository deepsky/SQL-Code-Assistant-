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

import antlr.Token;
import antlr.TokenStreamException;
import com.deepsky.generated.plsql.adopted.PLSqlTokenTypes;
import com.deepsky.integration.PlSqlHighlightLexer;
import com.deepsky.integration.lexer.generated.PlSqlTokenTypesMapping;
import com.deepsky.lang.parser.adoptee.CharBufferAdopted;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.lexer.LexerState;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;


public class PlSqlHiLexer extends Lexer {

    private static final Logger log = Logger.getInstance("#PlSqlHiLexer");

    PlSqlHighlightLexer aLexer;
    Token token;

    int offset; // tracks position of the lexer
    int size;
    char[] chars;
    CharSequence charSequence;

    public PlSqlHiLexer() {
    }

   private void start(char[] chars, int i, int i1) {
        try {
            this.chars = chars;
            this.offset = i;
            this.size = i1;

            CharBufferAdopted adapter = new CharBufferAdopted(chars, i, i1 - i);
            aLexer = new PlSqlHighlightLexer(adapter);

            advance0();
        } catch (Error e) {
            log.error("Error: " + e.getMessage());
            throw e;
        }
    }

    public void start(CharSequence charSequence, int i, int i1, int i2) {
        this.charSequence = charSequence;

        String rt = charSequence.toString();
        this.chars = rt.toCharArray();

        this.offset = i;
        this.size = i1;
        CharBufferAdopted adapter = new CharBufferAdopted(chars, i, i1 - i);

        aLexer = new com.deepsky.integration.PlSqlHighlightLexer(adapter);
        advance0();
    }

    public int getState() {
        return 0;
    }


    public java.lang.CharSequence getTokenSequence() {
        return new String(chars, offset, getTokenEnd());
    }

    public java.lang.String getTokenText() {
        return new String(chars, offset, getTokenEnd());
    }


    public IElementType getTokenType() {
        return PlSqlTokenTypesMapping.table[token.getType()];
    }


    public int getTokenStart() {
        return offset;
    }

    public int getTokenEnd() {
        int end;

        if (token != null && PLSqlTokenTypes.EOF != token.getType()) {
            end = offset + token.getText().length();
        } else {
            end = offset;
        }

       return end;
    }

    public void advance() {
        if (token != null) {
            if (Token.INVALID_TYPE == token.getType()) {
                // todo
            } else if (PLSqlTokenTypes.EOF == token.getType()) {
                // todo
            } else {
                offset += token.getText().length();
            }
        }

        advance0();
    }

    protected void advance0() {
        try {
            token = aLexer.nextToken();
        } catch (TokenStreamException e) {
            log.error(e);
        }
    }

    public LexerPosition getCurrentPosition() {
        return new LexerPositionImpl(offset, new LexerStateImpl());
    }

    public void restore(LexerPosition lexerPosition) {
        if (lexerPosition != null) {
            start(chars, lexerPosition.getOffset(), size);
        }
    }

    public char[] getBuffer() {
        return chars;
    }

    public CharSequence getBufferSequence() {
        return this.charSequence;
    }

    public int getBufferEnd() {
        return size; //offset + size;
    }


    class LexerStateImpl implements LexerState {
        public short intern() {
            return 0;
        }
    }

    class LexerPositionImpl implements LexerPosition {

        int offset;
        LexerState state;

        public LexerPositionImpl(int offset, LexerState state) {
            this.offset = offset;
            this.state = state;
        }

        public int getOffset() {
            return offset;
        }

        public int getState() {
            // todo
            return 0;
        }

//        public LexerState getState() {
//            return state;
//        }
    }

}

