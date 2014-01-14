package com.deepsky.lang.plsql.completion.syntaxTreePath.annotations;

public @interface Author {
    String name();
    String created();
    int revision() default 1;
    String[] reviewers() default {};
}