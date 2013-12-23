package com.deepsky.lang.lexer;

import antlr.Token;
import antlr.TokenStreamException;
import com.deepsky.lang.parser.adoptee.CharBufferAdopted;
import com.intellij.lexer.LexerBase;
import com.intellij.lexer.LexerPosition;
import com.intellij.lexer.LexerState;
import com.intellij.psi.tree.IElementType;

import java.lang.reflect.InvocationTargetException;


public abstract class GenericLexer<T extends antlr.CharScanner> extends LexerBase {

    protected T aLexer;
    protected Class<T> clazz;

    protected Token token;

    protected int offset; // tracks position of the lexer
    protected int size;
    protected char[] chars;
    protected CharSequence charSequence;

    public GenericLexer(Class<T> clazz) {
        this.clazz = clazz;
    }


    public void start(char[] chars) {

        this.chars = chars;
        this.offset = 0;
        this.size = chars.length;

        CharBufferAdopted adapter = new CharBufferAdopted(chars);
        try {
            aLexer = clazz.getConstructor(antlr.InputBuffer.class).newInstance(adapter);
        } catch (InstantiationException e) {
            throw new Error(e);
        } catch (IllegalAccessException e) {
            throw new Error(e);
        } catch (InvocationTargetException e) {
            throw new Error(e);
        } catch (NoSuchMethodException e) {
            throw new Error(e);
        }

        advance0();
    }


    public void start(char[] chars, int i, int i1)  {

        this.chars = chars;
        this.offset = i;
        this.size = i1;

        CharBufferAdopted adapter = new CharBufferAdopted(chars, i, i1 - i);
        try {
            aLexer = clazz.getConstructor(antlr.InputBuffer.class).newInstance(adapter);
        } catch (InstantiationException e) {
            throw new Error(e);
        } catch (IllegalAccessException e) {
            throw new Error(e);
        } catch (InvocationTargetException e) {
            throw new Error(e);
        } catch (NoSuchMethodException e) {
            throw new Error(e);
        }

        advance0();
    }

    public void start(CharSequence charSequence, int i, int i1, int i2)  {
        this.charSequence = charSequence;

        String rt = charSequence.toString();
        this.chars = rt.toCharArray();

        this.offset = i;
        this.size = i1;
        CharBufferAdopted adapter = new CharBufferAdopted(chars, i, i1 - i);

        try {
            aLexer = clazz.getConstructor(antlr.InputBuffer.class).newInstance(adapter);
        } catch (NoSuchMethodException e) {
            throw new Error(e);
        } catch (InvocationTargetException e) {
            throw new Error(e);
        } catch (InstantiationException e) {
            throw new Error(e);
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }
        advance0();
    }

    public int getState() {
        return 0;
    }


    public java.lang.CharSequence getTokenSequence() {
        return new String(chars, offset, getTokenEnd());
    }

    public java.lang.String getTokenText() {
        return new String(chars, offset, getTokenEnd() - offset);
    }


    public abstract IElementType getTokenType();


    public int getTokenStart() {
        return offset;
    }

    public abstract int getTokenEnd();

    public abstract void advance();

    protected void advance0() {
        try {
            token = aLexer.nextToken();
        } catch (TokenStreamException ignored) {
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

