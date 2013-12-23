package com.deepsky.integration.tns;

import com.deepsky.integration.plsql.PlSqlTokenType;
import com.intellij.psi.tree.IElementType;

public interface TNSPsiBaseTokenTypes {

    IElementType WS = new TNSTokenType(49, "WS");
    IElementType SL_COMMENT = new PlSqlTokenType(50, "SL_COMMENT");
    IElementType ML_COMMENT = new PlSqlTokenType(51, "ML_COMMENT");
    IElementType BAD_ML_COMMENT = new PlSqlTokenType(19, "BAD_ML_COMMENT");

    IElementType QUOTED_STRING = new PlSqlTokenType(23, "QUOTED_STRING");
    IElementType NET_SERVICE_NAME = new PlSqlTokenType(5, "NET_SERVICE_NAME");
}
