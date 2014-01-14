package com.deepsky.lang.plsql.completion.processors;

import com.deepsky.lang.plsql.completion.SyntaxTreePath;

public class UpdateStmtProcessor {

    @SyntaxTreePath("/#UPDATE_COMMAND//#UPDATE ..#TABLE_ALIAS/1#TABLE_REF")
    public void process$Update1() {
        // TODO - implement me
    }


}
