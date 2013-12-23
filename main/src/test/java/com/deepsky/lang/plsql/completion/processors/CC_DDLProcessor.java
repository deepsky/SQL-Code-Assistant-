package com.deepsky.lang.plsql.completion.processors;

import com.deepsky.lang.plsql.completion.CC_BaseProcessor;
import com.deepsky.lang.plsql.completion.syntaxTreePath.SyntaxTreePath;

public class CC_DDLProcessor extends CC_BaseProcessor {

    @SyntaxTreePath("/AlterTable/TableRef")
    public void process$alterTable() {
        // TODO - implement me
    }

    @SyntaxTreePath("/CreateIndex/TableRef")
    public void process$createIndex() {
        // TODO - implement me
    }

    @SyntaxTreePath("/RenameTable/TableRef")
    public void process$2() {
        // TODO - implement me
    }

}
