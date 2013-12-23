package com.deepsky.lang.common.tnsnames;

import antlr.*;
import antlr.collections.impl.BitSet;
import com.deepsky.generated.tns.TNSTokenTypes;
import com.deepsky.integration.tns.TNSTokenType;
import com.deepsky.lang.parser.tns.ANTLRType2AdoptedType;
import com.deepsky.lang.parser.tns.TNSParserAdoptedExt;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class TNSPsiParser implements PsiParser {

    static final LoggerProxy log = LoggerProxy.getInstance("#TNSPsiParser");

    private PLSqlParser2 parser;
    private PsiBuilder builder;

    int predicting = 0;
    int counter;

    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder) {

        this.builder = builder;
//        builder.setDebugMode(true);

        long ms1 = System.currentTimeMillis();
        parser = new PLSqlParser2(new TokenStreamAdapter(builder), builder);
        ms1 = System.currentTimeMillis() - ms1;

        long ms2 = 0L, ms4 = 0L;
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
    final static Token EOF_TOKEN = new Token(TNSTokenTypes.EOF, null);

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
                        TNSTokenType etype = (TNSTokenType) builder.getTokenType();
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


    class PLSqlParser2 extends TNSParserAdoptedExt {

        final PsiBuilder builder;

        final public int getPredicting() {
            return predicting;
        }

        public PLSqlParser2(TokenStream lexer, PsiBuilder builder) {
            super(lexer, builder);
            this.builder = builder;
        }

        final public void rewind(int pos) {
            super.rewind(pos);
            predicting--;
        }

        final public int mark() {
            int mark = super.mark();
            predicting++;
            return mark;
        }

        final public void consume() throws TokenStreamException {
            TNSTokenType etype = (TNSTokenType) builder.getTokenType();
            if (predicting == 0 && etype != null) {
                    PsiBuilder.Marker r = builder.mark();
                    builder.advanceLexer();
                    r.done(etype);

                counter--;
                cached.removeFirst();
            }

            super.consume();
        }

        final public void reportError(RecognitionException ex) {
            // Do not report syntax errors
        }

/**
         * Parser error-reporting function can be overridden in subclass
*/

        final public void reportError(String s) {
//            log.debug("[PARSER] Syntax Error2: " + s);
        }


        protected boolean recoverErrorAndCheckEOF() throws TokenStreamException, MismatchedTokenException {
            if (LA(1) == EOF) {
                match(EOF);
                return false;
            } else {
                PsiBuilder.Marker m = builder.mark();
                super.recover(null, _tokenSet_2);
                m.done(ANTLRType2AdoptedType.type2etype[ERROR_TOKEN_A]);
                return true;
            }
        }


        final public void recover(RecognitionException ex, BitSet tokenSet) throws TokenStreamException {
//            PsiBuilder.Marker m = builder.mark();
            super.recover(ex, tokenSet);
//            m.done(ANTLRType2AdoptedType.type2etype[ERROR_TOKEN_A]);
            returnType = ERROR_TOKEN_A;
        }


    }


}






