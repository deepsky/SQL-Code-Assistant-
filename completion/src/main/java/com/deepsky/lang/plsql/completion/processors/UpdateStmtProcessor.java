package com.deepsky.lang.plsql.completion.processors;

import com.deepsky.lang.plsql.completion.SyntaxTreePath;

public class UpdateStmtProcessor {

    @SyntaxTreePath("/#UPDATE_COMMAND//#UPDATE #TABLE_ALIAS/1#TABLE_REF #ALIAS_NAME//#C_MARKER")
    public void process$UpdateTabAlias() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#UPDATE_COMMAND//#UPDATE #TABLE_ALIAS/1#TABLE_REF/#C_MARKER")
    public void process$UpdateTabRef() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#UPDATE_COMMAND//#UPDATE #TABLE_ALIAS #SET #ERROR_TOKEN_A/#C_MARKER")
    public void process$UpdateColumnName() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#UPDATE_COMMAND//#UPDATE 1#TABLE_ALIAS #SET .. 2#COLUMN_SPEC #EQ #VAR_REF//#C_MARKER")
    public void process$UpdateColumnVar() {
        // TODO - implement me
    }


}
