package com.deepsky.lang.plsql.completion.syntaxTreePath;

public interface Visitor {
    boolean accept(SyntaxTreePathWrap node);
}
