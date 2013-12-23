package com.deepsky.integration.tns;

import com.deepsky.lang.common.PlSqlFileType;
import com.intellij.psi.tree.IElementType;

import java.util.HashMap;
import java.util.Map;

public class TNSElementType extends IElementType {

    static private final Map<Short, Integer> ielem2antlr_tokentype = new HashMap<Short, Integer>();

    static final public int NOT_REGISTER = -1;
    int antlrTokenType;

    public TNSElementType(String debugName, int antlrTokenType) {
        super(debugName, PlSqlFileType.FILE_TYPE.getLanguage());
        this.antlrTokenType = antlrTokenType;

        ielem2antlr_tokentype.put(getIndex(), antlrTokenType);
    }

    public String toString() {
        return "TNS:" + super.toString();
    }

    public int getElementTyoe() {
        return antlrTokenType;
    }

    static public int mapTo_TokenType(IElementType elem) {
        Integer tokenType = ielem2antlr_tokentype.get(elem.getIndex());
        if (tokenType != null) {
            return tokenType;
        } else {
            return NOT_REGISTER;
        }
    }
}

