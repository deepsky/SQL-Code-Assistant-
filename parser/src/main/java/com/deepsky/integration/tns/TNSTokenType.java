package com.deepsky.integration.tns;

import com.deepsky.lang.common.PlSqlFileType;
import com.intellij.psi.tree.IElementType;

public class TNSTokenType  extends IElementType {

    int tokenId;

    public TNSTokenType(int tokenId, @org.jetbrains.annotations.NotNull String s) {
        super(s, PlSqlFileType.FILE_TYPE.getLanguage());
        this.tokenId = tokenId;
    }

    public final int tokenId(){
        return tokenId;
    }

    public String toString() {
        return "TNS:" + super.toString();
    }

}

