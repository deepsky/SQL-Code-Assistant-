package com.deepsky.lang.common.tnsnames;

import com.deepsky.integration.lexer.generated.tns.TNSPsiBaseTokenTypes;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;

public interface TNSPsiTokenTypes extends TNSPsiBaseTokenTypes {
    IFileElementType FILE = new TNSFileElementType();

    TokenSet COMMENTS = TokenSet.create(
            SL_COMMENT, ML_COMMENT, BAD_ML_COMMENT
    );

    TokenSet STRING_LITERALS = TokenSet.create(
            QUOTED_STRING
    );

}
