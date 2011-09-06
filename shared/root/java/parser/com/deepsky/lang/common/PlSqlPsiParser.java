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
import com.deepsky.integration.CustomRecognitionException;
import com.deepsky.integration.PlSqlTokenType;
import com.deepsky.lang.parser.plsql.ANTLRType2AdoptedType;
import com.deepsky.lang.parser.plsql.PLSqlParserAdoptedExt;
import com.deepsky.lang.plsql.parser.WrappedPackageException;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;


public class PlSqlPsiParser implements PsiParser {

    static final LoggerProxy log = LoggerProxy.getInstance("#PlSqlPsiParser");

    PLSqlParser2 parser;
    PsiBuilder builder;

    int predicting = 0;
    int counter;

    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder) {

        this.builder = builder;
//        builder.setDebugMode(true);

        long ms1 = System.currentTimeMillis();
        parser = new PLSqlParser2(new TokenStreamAdapter(builder), builder);
        ms1 = System.currentTimeMillis() - ms1;

        long ms2 = 0L, ms4 =0L;
        ASTNode astNode = null;
        long ms2_ = System.currentTimeMillis();

        PsiBuilder.Marker rootMarker = builder.mark();
        try {
            try {
                parser.start_rule();
            } catch (RecognitionException e) {
                log.warn("[PARSER EXCEPTION] : " + e.getMessage());
            } catch (TokenStreamException e) {
                log.warn("[PARSER EXCEPTION] : " + e.getMessage());
            }

            rootMarker.done(root);
            ms2 = System.currentTimeMillis() - ms2_;

            long ms3_ = System.currentTimeMillis();
            astNode = builder.getTreeBuilt();
            ms4 = System.currentTimeMillis() - ms3_;

        } finally {
            log.debug("[PARSER] create parser: " + ms1 + ", parsing: " + ms2 + " build tree: " + ms4);
        }

        return astNode;
    }


/* NOTE: for evaluation only
    int treeWalker(ASTNode parent) {
        ASTNode child = parent.getFirstChildNode();
        int ret = 0;
        while (child != null) {
            ret++;
            if (!(child instanceof LeafElement)) {
                ret += treeWalker(child);
            }
            child = child.getTreeNext();
        }


        return ret;
    }
*/

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
                // let's save some resources - skip non significant (leaf) tokens
//                boolean skipMarker =
//                        // todo -- should be skipped all elements except defined in the PLSqlTypesAdopted interface
//                        etype == PlSqlTokenTypes.NUMBER
//                        || etype == PlSqlTokenTypes.IDENTIFIER
//                        || etype == PlSqlTokenTypes.QUOTED_STR
//                        || etype == PlSqlTokenTypes.COMMA
//                        || etype == PlSqlTokenTypes.DOT
//                        || etype == PlSqlTokenTypes.OPEN_PAREN
//                        || etype == PlSqlTokenTypes.CLOSE_PAREN;

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

        public void reportError(RecognitionException ex) {
//            log.warn("[PARSER] Syntax Error: " + ex); // + ", RulezzCameUp: " + RulezzCameUp);
        }

        /**
         * Parser error-reporting function can be overridden in subclass
         */
        public void reportError(String s) {
//            log.debug("[PARSER] Syntax Error2: " + s);
        }

        public void recover(RecognitionException ex, BitSet tokenSet) throws TokenStreamException {
//            log.warn("[PARSER] Recover start ------------");
            PsiBuilder.Marker m = builder.mark();
            super.recover(ex, tokenSet);
            m.done(ANTLRType2AdoptedType.type2etype[ERROR_TOKEN_A]);

//            log.warn("[PARSER] Recover end --------------");
        }

        protected void process_wrapped_package(String package_name) {
            // ignore wrapped package
        }

        public void procedure_spec() throws RecognitionException, TokenStreamException {
            try {
                super.procedure_spec();
            } catch (CustomRecognitionException ex) {
                returnType = -1;
                recover(ex, _tokenSet_10101);
            }
            returnAST = null;
        }

        public void function_spec() throws RecognitionException, TokenStreamException {
            try {
                super.function_spec();
            } catch (CustomRecognitionException ex) {
                returnType = -1;
                recover(ex, _tokenSet_10101);
            }
            returnAST = null;
        }

/*
        public void function_body() throws RecognitionException, TokenStreamException {
            try {
                super.function_body();
            } catch (CustomRecognitionException ex) {
                returnType = -1;
                recover(ex, _tokenSet_10101);
            }
            returnAST = null;
        }

        public void procedure_body() throws RecognitionException, TokenStreamException {
            try {
                super.procedure_body();
            } catch (CustomRecognitionException ex) {
                returnType = -1;
                recover(ex, _tokenSet_10101);
            }
            returnAST = null;
        }
*/
    }

    public static final BitSet _tokenSet_10101 = new BitSet();

    static {
        _tokenSet_10101.add(PLSqlTokenTypes.LITERAL_procedure);
        _tokenSet_10101.add(PLSqlTokenTypes.LITERAL_function);
        _tokenSet_10101.add(PLSqlTokenTypes.LITERAL_type);
        _tokenSet_10101.add(PLSqlTokenTypes.LITERAL_subtype);
        _tokenSet_10101.add(PLSqlTokenTypes.LITERAL_pragma);
    }
}





