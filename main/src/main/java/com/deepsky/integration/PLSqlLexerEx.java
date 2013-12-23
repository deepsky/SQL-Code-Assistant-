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
import antlr.collections.Stack;
import antlr.collections.impl.LList;
import com.deepsky.generated.plsql.PLSql2TokenTypes;
import com.deepsky.generated.plsql.PLSqlLexer;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.generated.plsql.SymbolTable;
import com.deepsky.integration.lexer.PlSql10gQuotedStringLexer;

import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;


/**
 * Extend ANTLR generated PLSqlLexer with support of:
 * 1. 10g quoted string
 * 2. Processing REM, PROMPT, SPOOL, etc SQLPLUS commands
 */
public class PLSqlLexerEx extends PLSqlLexer {

    /**
     * The set of inputs to the MUX
     */
    private final Map<String, TokenStream> inputStreamNames = new HashMap<String, TokenStream>();
    /**
     * Used to track stack of input streams
     */
    private final Stack streamStack = new LList();
    /**
     * The currently-selected token stream input
     */
    private TokenStream input;


    public PLSqlLexerEx(InputStream in) {
        this(new ByteBuffer(in));
    }

    public PLSqlLexerEx(Reader in) {
        this(new CharBuffer(in));
    }

    public PLSqlLexerEx(InputBuffer ib) {
        this(new LexerSharedInputState(ib));
    }

    public PLSqlLexerEx(LexerSharedInputState state) {
        super(state);

        // Make the lexer multiplexed
        inputStreamNames.put("main", this);
        PlSql10gQuotedStringLexer l = new PlSql10gQuotedStringLexer(getInputState()) {
            public void popLexer() {
                input = (TokenStream) streamStack.pop();
            }
        };

        inputStreamNames.put("qStringLexer", l);
        select("main"); // start with main PL/SQL lexer
    }

    // [START] Lexer multiplexing support
    protected void selectorPush(String name) {
        streamStack.push(input);
        select(name);
    }

    private void select(String sname) throws IllegalArgumentException {
        input = getStream(sname);
    }

    private TokenStream getStream(String sname) {
        TokenStream stream = (TokenStream) inputStreamNames.get(sname);
        if (stream == null) {
            throw new IllegalArgumentException("TokenStream " + sname + " not found");
        }
        return stream;
    }
    // [END] Lexer multiplexing support


    private Token deferred = null;
    private boolean prompt = false;

    public Token nextToken() throws TokenStreamException {
        if (deferred != null) {
            final Token t = deferred;
            deferred = null;
            return t;
        }

        final Token token = (input == this) ? super.nextToken() : input.nextToken();

        // workaround to get "prompt" and "rem" clauses (SQLPLUS tool) processed correctly
        if (token == null) {
            return null;
        }

        final int tokenType = token.getType();
        if (prompt) {
            if (tokenType == PLSqlTokenTypes.LF || tokenType == PLSqlTokenTypes.EOF) {
                deferred = token;
                prompt = false;
                return new CommonToken(PLSqlTokenTypes.CUSTOM_TOKEN, "");
            }
        } else {
            // TODO -- make me more generic
            switch (tokenType) {
                case PLSqlTokenTypes.LITERAL_prompt:
                case PLSqlTokenTypes.LITERAL_rem:
                case PLSqlTokenTypes.LITERAL_spool:
                case PLSqlTokenTypes.LITERAL_host:
                case PLSqlTokenTypes.AT_PREFIXED:
                    prompt = true;
                    break;
            }
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

    /**
     * Test the text passed in against the literals table
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


    protected final Token makeToken(int t) {
        Token tok = new antlr.CommonToken();
        tok.setType(t);
        return tok;
    }


    public void mNUMBER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        int _ttype;
        Token _token = null;
        int _begin = text.length();
        _ttype = NUMBER;

        if (LA(1) >= '0' && LA(1) <= '9') {
                mN(false);
                if (LA(1) == '.' && LA(2) >= '0' && LA(2) <= '9') {
                    match('.');
                    mN(false);
                } else if (LA(1) == 'k' || LA(1) == 'm') {
                    match(LA(1));
                    if (_createToken) {
                        _token = makeToken(PLSql2TokenTypes.STORAGE_SIZE);
                        _token.setText(new String(text.getBuffer(), _begin, text.length() - _begin));
                    }
                    _returnToken = _token;
                    return;
                }
        } else if (LA(1) == '.' && LA(2) >= '0' && LA(2) <= '9') {
            match('.');
            mN(false);
        } else {
            throw new NoViableAltForCharException((char) LA(1), getFilename(), getLine(), getColumn());
        }

        if (LA(1) == 'e'){
            match('e');
            if(LA(1) == '+' || LA(1) == '-'){
                match(LA(1));
                if(LA(1) >= '0' && LA(1) <= '9'){
                    mN(false);
                } else {
                    throw new NoViableAltForCharException((char) LA(1), getFilename(), getLine(), getColumn());
                }
            } else if(LA(1) >= '0' && LA(1) <= '9') {
                mN(false);
            } else {
                _ttype = PLSql2TokenTypes.BAD_NUMBER_LITERAL;
            }
        }

        if (_createToken && _token == null && _ttype != Token.SKIP) {
            _token = makeToken(_ttype);
            _token.setText(new String(text.getBuffer(), _begin, text.length() - _begin));
        }
        _returnToken = _token;
    }


    // Optimization execution of the LA() method
    boolean updated = true;
    boolean insideConsume = false;
    final char[] cachedChar = new char[]{(char)-1, (char)-1, (char)-1, (char)-1};

    public void consume() throws CharStreamException {
        try {
            insideConsume = true;
            updated=true;
            super.consume();
            cachedChar[0]= (char) -1;
            cachedChar[1]= (char) -1;
            cachedChar[2]= (char) -1;
            cachedChar[3]= (char) -1;
        } finally {
            insideConsume = false;
        }
    }

    public void setInputState(LexerSharedInputState state) {
        updated=true;
        super.setInputState(state);
    }

    public char LA(int i) throws CharStreamException {
        if(updated){
            updated = insideConsume;
            return cachedChar[i] = super.LA(i);
        } else {
            return cachedChar[i]== (char)-1? cachedChar[i]=super.LA(i): cachedChar[i];
        }
    }

    public void rewind(int pos) {
        updated=true;
        super.rewind(pos);
    }

}
