package com.deepsky.lang.plsql.completion.syntaxTreePath.logic;

public interface TreePathIterator {

    Object next() throws EOFException;

    Object peek() throws EOFException;

    boolean hasNext();

    void setState(int lexerState) throws InvalidLexerStateException;

    int saveState();

}
