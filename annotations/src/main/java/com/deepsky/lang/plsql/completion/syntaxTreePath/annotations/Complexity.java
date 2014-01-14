package com.deepsky.lang.plsql.completion.syntaxTreePath.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public @interface Complexity {
    public enum Level {
        VERY_SIMPLE, SIMPLE, MEDIUM, COMPLEX, VERY_COMPLEX;
    }

    Level value();
}
