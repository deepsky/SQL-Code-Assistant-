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

package com.deepsky.lang.common;

import antlr.RecognitionException;
import antlr.Token;
import antlr.TokenStream;
import antlr.TokenStreamException;
import antlr.collections.impl.BitSet;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.integration.PlSqlTokenType;
import com.deepsky.lang.parser.plsql.ANTLRType2AdoptedType;
import com.deepsky.lang.parser.plsql.PLSqlParserAdoptedExt;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;


public class PlSqlPsiParser implements PsiParser {

    PLSqlParser2 parser;
    PsiBuilder builder;

    int predicting = 0;
    int counter;

    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder) {

        this.builder = builder;
        parser = new PLSqlParser2(new TokenStreamAdapter(builder), builder);
        PsiBuilder.Marker rootMarker = builder.mark();

        try {
            parser.start_rule();
        } catch (RecognitionException e) {
        } catch (TokenStreamException e) {
        }

        rootMarker.done(root);
        return builder.getTreeBuilt();
    }

    private LinkedList<Token> cached = new LinkedList<Token>();
    final static Token EOF_TOKEN = new Token(PLSqlTokenTypes.EOF, null);

    class TokenStreamAdapter implements TokenStream {
        PsiBuilder builder;

        public TokenStreamAdapter(PsiBuilder builder) {
            this.builder = builder;
            counter = 1;
        }

        public Token nextToken() throws TokenStreamException {
            int idx = counter - 1;
            if (cached.size() > idx) {
                counter++;
                return cached.get(idx);
            } else {
                if (builder.getTokenType() != null) {
                    PsiBuilder.Marker r = builder.mark();
                    int top = counter + 100;
                    for (int i = 0; i < top; i++) {
                        PlSqlTokenType etype = (PlSqlTokenType) builder.getTokenType();
                        if (etype != null) {
                            if (i >= idx) {
                                cached.addLast(new Token(etype.tokenId()));
                            }
                            builder.advanceLexer();
                        } else {
                            break;
                        }
                    }

                    Token tt = cached.size() > idx ? cached.get(idx) : null;
                    if (tt != null) {
                        counter++;
                    } else {
                        tt = EOF_TOKEN;
                    }

                    r.rollbackTo();
                    return tt;
                } else {
                    return EOF_TOKEN;
                }
            }
        }
    }

    class PLSqlParser2 extends PLSqlParserAdoptedExt {

        PsiBuilder builder;

        public int getPredicting() {
            return predicting;
        }

        public PLSqlParser2(TokenStream lexer, PsiBuilder builder) {
            super(lexer, builder);
            this.builder = builder;
        }

        public void rewind(int pos) {
            super.rewind(pos);
            predicting--;
        }

        public int mark() {
            int mark = super.mark();
            predicting++;
            return mark;
        }

        public void consume() throws TokenStreamException {
            PlSqlTokenType etype = (PlSqlTokenType) builder.getTokenType();
            if (predicting == 0 && etype != null) {
                boolean doNotSkipMarker =
                        etype == PlSqlTokenTypes.COLON_NEW
                                || etype == PlSqlTokenTypes.COLON_OLD;

                if (doNotSkipMarker) {
                    PsiBuilder.Marker r = builder.mark();
                    builder.advanceLexer();
                    r.done(etype);
                } else {
                    builder.advanceLexer();
                }

                counter--;
                cached.removeFirst();
            }

            super.consume();
        }

        public void recover(RecognitionException ex, BitSet tokenSet) throws TokenStreamException {
            PsiBuilder.Marker m = builder.mark();
            super.recover(ex, tokenSet);
            m.done(ANTLRType2AdoptedType.type2etype[ERROR_TOKEN_A]);
        }

        protected void process_wrapped_package(String package_name) {
            // ignore wrapped package
        }
    }
}





