package com.deepsky.lang.plsql.completion.legacy;

public interface Visitor {
    boolean accept(SyntaxTreePathWrap node);
}
