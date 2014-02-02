package com.deepsky.lang.plsql.completion.processors;

import com.intellij.codeInsight.completion.CompletionResultSet;

public interface C_Context {
    String getLookup();
    CompletionResultSet getResultSet();
}
