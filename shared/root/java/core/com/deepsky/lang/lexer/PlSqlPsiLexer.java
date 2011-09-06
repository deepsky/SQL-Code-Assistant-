package com.deepsky.lang.lexer;

import antlr.Token;
import antlr.TokenStreamException;
import com.deepsky.generated.plsql.adopted.PLSqlTokenTypes;
import com.deepsky.integration.PLSqlLexerEx;
import com.deepsky.integration.lexer.generated.PlSqlTokenTypesMapping;
import com.deepsky.lang.parser.adoptee.CharBufferAdopted;
import com.intellij.lexer.LexerBase;
import com.intellij.lexer.LexerPosition;
import com.intellij.lexer.LexerState;
import com.intellij.psi.tree.IElementType;


public class PlSqlPsiLexer extends LexerBase { //Lexer {

    PLSqlLexerEx aLexer;
    Token token;

    int offset; // tracks position of the lexer
    int size;
    char[] chars;
    CharSequence charSequence;

    public PlSqlPsiLexer() {
    }


    public void start(char[] chars) {

        this.chars = chars;
        this.offset = 0;
        this.size = chars.length;

        CharBufferAdopted adapter = new CharBufferAdopted(chars);
        aLexer = new PLSqlLexerEx(adapter);

        advance0();
    }


    public void start(char[] chars, int i, int i1) {

        this.chars = chars;
        this.offset = i;
        this.size = i1;

        CharBufferAdopted adapter = new CharBufferAdopted(chars, i, i1 - i);
        aLexer = new PLSqlLexerEx(adapter);

        advance0();
    }

    public void start(CharSequence charSequence, int i, int i1, int i2) {
        this.charSequence = charSequence;

        String rt = charSequence.toString();
        this.chars = rt.toCharArray();

        this.offset = i;
        this.size = i1;
        CharBufferAdopted adapter = new CharBufferAdopted(chars, i, i1 - i);

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
        return size;
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
    }

}
