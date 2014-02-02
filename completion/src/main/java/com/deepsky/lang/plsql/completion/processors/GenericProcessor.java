package com.deepsky.lang.plsql.completion.processors;

import com.deepsky.lang.plsql.completion.SyntaxTreePath;
import com.intellij.lang.ASTNode;

public class GenericProcessor {

    @SyntaxTreePath("/#ERROR_TOKEN_A/1#C_MARKER")
    public void process$Start(C_Context context, ASTNode node) {
        // TODO - implement me
    }
}
