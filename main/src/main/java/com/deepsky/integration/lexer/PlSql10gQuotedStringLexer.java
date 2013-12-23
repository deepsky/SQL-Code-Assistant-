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

package com.deepsky.integration.lexer;

import antlr.*;
import com.deepsky.generated.plsql.PLSql2TokenTypes;
import com.deepsky.generated.plsql.PLSqlTokenTypes;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


/**
 * Lexer which provides support for the new quoting mechanism in PL/SQL.
 *
 *  This is a new feature of 10g that enables us to embed single-quotes in literal strings without
 *  having to resort to double, triple or sometimes quadruple quote characters. This is particularly
 *  useful for building dynamic SQL statements that contain quoted literals.
 *
 *  The mechanism is invoked with a simple "q" in PL/SQL only. The syntax is q'[...]',
 *  where the "[" and "]" characters can be any of the following as long as they do not already appear in the string.
 *
 * '!' , '[ ]', '{ }', '( )', '< >'
 *
 */
public abstract class PlSql10gQuotedStringLexer extends antlr.CharScanner implements TokenStream {

    public abstract void popLexer();

    public PlSql10gQuotedStringLexer(LexerSharedInputState state) {
        super(state);
        caseSensitiveLiterals = false;
        setCaseSensitive(false);
        literals = new Hashtable();
    }

    public final Token nextToken() throws TokenStreamException {
        Token theRetToken = null;
        tryAgain:
        for (; ; ) {
            Token _token = null;
            int _ttype = Token.INVALID_TYPE;
            resetText();
            try {   // for char stream error handling
                try {   // for lexical error handling
                    if (((LA(1) >= '\u0000' && LA(1) <= '\uffff'))) {
                        mQUOTED_STR_END();
                        theRetToken = _returnToken;
                    } else {
                        if (LA(1) == EOF_CHAR) {
                            uponEOF();
                            _returnToken = makeToken(Token.EOF_TYPE);
                        } else {
                            Token __token = makeToken(PLSqlTokenTypes.BAD_CHARACTER);
                            __token.setText(String.valueOf(LA(1)));

                            consume();
                            _returnToken = __token;
                        }
                    }

                    if (_returnToken == null) continue tryAgain; // found SKIP token
                    _ttype = _returnToken.getType();
                    _returnToken.setType(_ttype);
                    return _returnToken;
                } catch (RecognitionException e) {
                    Token __token = makeToken(PLSqlTokenTypes.BAD_CHARACTER);
                    __token.setText(new String(text.getBuffer(), 0, text.length()));
                    _returnToken = __token;
                    return _returnToken;
                }
            } catch (CharStreamException cse) {
                if (cse instanceof CharStreamIOException) {
                    throw new TokenStreamIOException(((CharStreamIOException) cse).io);
                } else {
                    throw new TokenStreamException(cse.getMessage());
                }
            }
        }
    }

    private static final Map<Character, Character> startEndMarkers;

    static {
        startEndMarkers = new HashMap<Character, Character>();
        startEndMarkers.put('!', '!');
        startEndMarkers.put('[', ']');
        startEndMarkers.put('{', '}');
        startEndMarkers.put('<', '>');
        startEndMarkers.put('(', ')');
    }

    public final void mQUOTED_STR_END() throws RecognitionException, CharStreamException, TokenStreamException {
        int _ttype;
        Token _token = null;
        int _begin = text.length();
        _ttype = PLSql2TokenTypes.QUOTED_STR_END;
        int _saveIndex;
        int index = 0;
        Character markerCharacter = null;

        do {
            final char LA1 = LA(1);
            if (LA1 == '\uffff') {
                _saveIndex = text.length();
                match('\uFFFF');
                text.setLength(_saveIndex);
                _ttype = PLSql2TokenTypes.BAD_QUOTED_STRING;
                break;
            }

            if (index == 0) {
                // First pass check head character and identify ending character
                markerCharacter = startEndMarkers.get(LA1);
                if (markerCharacter == null) {
                    // Found character is not allowed
                    _ttype = PLSql2TokenTypes.BAD_QUOTED_STRING;
                }
            } else if (markerCharacter != null && markerCharacter == LA1 && '\'' == LA(2)) {
                // Found end of quoted string
                match(LA1);
                match('\'');
                break;
            } else if (markerCharacter == null && '\'' == LA1) {
                match(LA1);
                _ttype = PLSql2TokenTypes.BAD_QUOTED_STRING;
                break;
            }

            consume();
            index++;

        } while (true);

        popLexer();

        _token = makeToken(_ttype);
        _token.setText(new String(text.getBuffer(), _begin, text.length() - _begin));
        _returnToken = _token;
    }

}
