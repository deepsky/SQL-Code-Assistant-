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

package com.deepsky.lang.plsql.tree;

import com.deepsky.lang.plsql.struct.parser.SyntaxErrorException;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.integration.PlSqlTokenType;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import antlr.*;
import antlr.collections.impl.BitSet;
import org.apache.log4j.Logger;


public class MarkupGenerator {

    static final LoggerProxy log = LoggerProxy.getInstance("#MarkupGenerator");

    PLSqlParser2 parser;
    PsiBuilder builder;

    int modeP; // 0 - do not pass the rulezz token to the parser, 1 - pass the token to the parser
    boolean RulezzCameUp = false;
    List<Token> tqueue = new ArrayList<Token>(1000);
    int predicting = 0;
    int counter;


    public TreeNodeBuilder parse0(String input) {
        TreeNodeBuilder builder = new TreeNodeBuilder(input.toCharArray());

        parse(PlSqlTokenTypes.FILE, builder);
        return builder;
    }

//    public TreeNodeBuilderEx parse1(String input) {
//        TreeNodeBuilderEx builder = new TreeNodeBuilderEx(input.toCharArray());
//
//        parse(PlSqlTokenTypes.FILE, builder);
//        return builder;
//    }

    public void parse(TreeNodeBuilder builder) {
        parse(PlSqlTokenTypes.FILE, builder);
    }

    protected void parse(IElementType root, TreeNodeBuilder builder) {

        log.info("#parse root:" + root + " builder:" + builder);

        this.builder = builder;

        long ms1 = System.currentTimeMillis();
        parser = new PLSqlParser2(new TokenStreamAdapter(builder), builder);
        ms1 = System.currentTimeMillis() - ms1;
        long ms2_ = System.currentTimeMillis();

        modeP = 0;
        tqueue.clear();
        final PsiBuilder.Marker rootMarker = builder.mark();

        RulezzCameUp = false;
        try {
            try {
                parser.start_rule();
                long ms2 = System.currentTimeMillis() - ms2_;
                log.info("[PARSER] counter1: " + counter + " time: " + (ms1 + ms2) + "[" + ms1 + ":" + ms2 + "]");
            } catch (RecognitionException e) {
                log.info("[PARSER EXCEPTION] : " + e.getMessage());
            } catch (TokenStreamException e) {
                log.info("[PARSER EXCEPTION] : " + e.getMessage());
            }

            rootMarker.done(root);
            long ms2 = System.currentTimeMillis() - ms2_;

            long ms3_ = System.currentTimeMillis();
//            ASTNode astNode = builder.getTreeBuilt();
            long ms3 = System.currentTimeMillis() - ms3_;

            log.info("[PARSER] create parser (ms1): " + ms1 + ", parsing (ms2): " + ms2 + ", buildtree (ms3): " + ms3);

        } catch (ParserSpecException e1) {
            throw new SyntaxErrorException("", null);
        }

    }


    class TokenStreamAdapter implements TokenStream {
        PsiBuilder builder;

        public TokenStreamAdapter(PsiBuilder builder) {
            this.builder = builder;
            counter = 1;
        }

        // 'IntellijIdeaRulezzz'
        public Token nextToken() throws TokenStreamException {
            String last_text = null;
            Token tt = null;
            if (builder.getTokenType() != null) {
                PsiBuilder.Marker r = builder.mark();
                for (int i = 0; i < counter; i++) {
                    PlSqlTokenType etype = ((PlSqlTokenType) builder.getTokenType());
                    if (etype != null) {
                        last_text = builder.getTokenText();
                        tt = new Token(etype.tokenId()); ///getRawToken();
                        tt.setText(last_text);
                        builder.advanceLexer();
                    } else {
                        tt = null;
                        break;
                    }
                }

                if (tt != null) {
                    counter++;
                } else {
                    tt = new Token(1, null);
                }

                r.rollbackTo();
            } else {
                tt = new Token(1, null);
            }

            if (modeP == 0) {
                // insert token in queue
                tqueue.add(tt);
            } else {
                if (tqueue.size() > 0) {
                    // get token from queue
                    tt = tqueue.remove(0);
                } else {
                    // leave assigned token untoutched
                }
            }
//            log.info("[LEXER] nextToken(): " + ((tt == null) ? "null" : tt) + " builder last_text: " + last_text + ", builder: '" + builder.getTokenText() + "'");

            return tt;
        }
    }

    class PLSqlParser2 extends com.deepsky.lang.parser.plsql.PLSqlParserAdoptedExt {

        TreeNodeBuilder builder;

        public int getPredicting() {
            return predicting;
        }

        public PLSqlParser2(TokenStream lexer, TreeNodeBuilder builder) {
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

        public void leave_one_ws(){
            builder.handle_ws(1);
        }

        public void no_ws(){
            builder.handle_ws(0);
        }

        public void consume() throws TokenStreamException {
            if (predicting == 0 && builder.getTokenType() != null) {
                String tte = builder.getTokenText();

                if (builder.getTokenType() != null) {
//                    log.info("[PARSER] consume, text: '" + builder.getTokenText() + "', counter: " + counter);
                    PlSqlTokenType etype = (PlSqlTokenType) builder.getTokenType();

                    boolean skipMarker =
                            etype == PlSqlTokenTypes.NUMBER || etype == PlSqlTokenTypes.IDENTIFIER || etype == PlSqlTokenTypes.QUOTED_STR;

                    PsiBuilder.Marker r = null;
                    if (!skipMarker) {
                        r = builder.mark();
                    }

                    builder.advanceLexer();

                    if (!skipMarker) {
                        r.done(etype);
                    }
                    counter--;
                } else {
//                    log.info("[PARSER] consume, 'EOF'");
                }
                // ----------------------------------
            }

            super.consume();
        }

        public void reportError(RecognitionException ex) {
            throw new ParserSpecException();
//
//            log.info("[PARSER] Syntax Error: " + ex + ", RulezzCameUp: " + RulezzCameUp);
//            if (RulezzCameUp) {
//                if (modeP == 0) {
//                    log.info("[PARSER] Syntax Error, make last attempt to parse ..");
//                    throw new ParserSpecException();
//                } else {
//                    log.info("[PARSER] Syntax Error, finish, mode = " + modeP);
//                }
//            }
        }

        /**
         * Parser error-reporting function can be overridden in subclass
         */
        public void reportError(String s) {
            log.info("[PARSER] Syntax Error2: " + s);
        }

        public void recover(RecognitionException ex, BitSet tokenSet) throws TokenStreamException {
//            log.info("[PARSER] Recover start ------------");
//            throw new ParserSpecException();
//            builder.error(ex.getMessage());
            super.recover(ex, tokenSet);
//            log.info("[PARSER] Recover end --------------");
        }
    }

    class ParserSpecException extends Error {
    }


    TreeNodeBuilder createMarkupBuilder(String input) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
//        InvocationHandler handler = new PlSqlMarkupBuilder();
//        Class proxyClass = Proxy.getProxyClass(
//            this.getClass().getClassLoader(), new Class[] { PsiBuilder.class });
//
//        PsiBuilder builder = (PsiBuilder) proxyClass.
//            getConstructor(new Class[] { InvocationHandler.class }).newInstance(new Object[] { handler });


        return new TreeNodeBuilder(input.toCharArray());
    }


    /// ==================


    class PlSqlMarkupBuilder implements InvocationHandler {
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            String methodName = method.getName();
            if (methodName.equalsIgnoreCase("toString")) {
                return this.toString();
            } else {

                return null;
            }
        }
    }
}
