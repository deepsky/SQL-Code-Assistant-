package com.deepsky.lang.lexer;

import com.deepsky.integration.lexer.generated.PlSqlTokenTypesMapping;
import com.deepsky.integration.PLSqlLexerEx;
import com.deepsky.lang.parser.adoptee.CharBufferAdapted;
import com.deepsky.generated.plsql.adopted.PLSqlTokenTypes;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.lexer.LexerState;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import antlr.Token;
import antlr.TokenStreamException;


public class PlSqlBaseLexer extends //PlSqlTokenTypesMapping implements
        Lexer {

    private static final Logger log = Logger.getInstance("#PlSqlBaseLexer");

    PLSqlLexerEx aLexer;
    Token token;

    int offset; // tracks position of the lexer
    int size;
    char[] chars;
    CharSequence charSequence;

    public PlSqlBaseLexer() {
    }

    public void start(char[] chars) {

//        log.info("#start:1");
        this.chars = chars;
        this.offset = 0;
        this.size = chars.length;

        CharBufferAdapted adapter = new CharBufferAdapted(chars);
        aLexer = new PLSqlLexerEx(adapter);

        advance0();
    }

    public void start(char[] chars, int i, int i1) {

//        log.info("#start:2, beg=" + i + " end:" + i1);

        try {
            this.chars = chars;
            this.offset = i;
            this.size = i1;

//            log.info("#start: [" + new String(chars, i, i1) + "] size: " + i1);
            CharBufferAdapted adapter = new CharBufferAdapted(chars, i, i1 - i);
            aLexer = new PLSqlLexerEx(adapter);

            advance0();
        } catch (Error e) {
            log.error("Error: " + e.getMessage());
            throw e;
        }
    }

    public void start(char[] chars, int i, int i1, int i2) {

//        String temp = new String(chars);
//        temp = temp.replace('\r', 'R');
//        temp = temp.replace('\n', 'N');
//        log.info("#start:3, beg=" + i + " end=" + i1 + " state=" + i2); //+ "==" + temp + "==");

        this.chars = chars;
        this.offset = i;
        this.size = i1;

        CharBufferAdapted adapter = new CharBufferAdapted(chars, i, i1 - i);
        aLexer = new PLSqlLexerEx(adapter);

        advance0();
    }

    public void start(CharSequence charSequence, int i, int i1, int i2) {
        this.charSequence = charSequence;

        String rt = charSequence.toString();
        this.chars = rt.toCharArray();

        this.offset = i;
        this.size = i1;
        CharBufferAdapted adapter = new CharBufferAdapted(chars, i, i1 - i);

        aLexer = new PLSqlLexerEx(adapter);
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

    String token2str(Token t){
        String text = t.getText();
        if(text == null){
            text = "null";
        } else if(text.equals("\n")){
            text = "LF";
        } else if(text.equals("\r")){
            text = "CR";
        }

        return "[\"" + text + "\"," + t.getType() + ", line="+ t.getLine() + ", col=" + t.getColumn() + "]";
    }

    public int getTokenStart() {
//        log.info("#getTokenStart, offset: " + offset);
        return offset;
    }

    public int getTokenEnd() {
        int end;

        if (token != null && PLSqlTokenTypes.EOF != token.getType()) {
            end = offset + token.getText().length();
        } else {
            end = offset;
        }

//        log.info("#getTokenEnd " + end + " offset=" + offset);
        return end;
    }

    public void advance() {
//        log.info("#advance");

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
//            e.printStackTrace();
            log.error(e);
        }
    }

    public LexerPosition getCurrentPosition() {
        return new LexerPositionImpl(offset, new LexerStateImpl());
    }

    public void restore(LexerPosition lexerPosition) {
//        log.info("#restore +++");
        if (lexerPosition != null) {
            start(chars, lexerPosition.getOffset(), size);
        }
//        log.info("#restore ---");
    }

    public char[] getBuffer() {
        return chars;
    }

    public CharSequence getBufferSequence() {
        return this.charSequence;
    }

    public int getBufferEnd() {
//        log.info("#getBufferEnd: " + (offset + size));
//        log.info("#getBufferEnd: " + size);
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
