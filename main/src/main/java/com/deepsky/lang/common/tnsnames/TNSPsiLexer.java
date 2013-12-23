package com.deepsky.lang.common.tnsnames;

import antlr.Token;

import com.deepsky.generated.tns.TNSLexer;
import com.deepsky.generated.tns.TNSTokenTypes;
import com.deepsky.integration.lexer.generated.tns.TNSTokenTypesMapping;
import com.deepsky.lang.lexer.GenericLexer;
import com.intellij.psi.tree.IElementType;

public class TNSPsiLexer  extends GenericLexer<TNSLexer> {


    public TNSPsiLexer() {
        super(TNSLexer.class);
    }


    public IElementType getTokenType() {
        return TNSTokenTypesMapping.table[token.getType()];
    }


    public int getTokenEnd() {
        int end;

        if (token != null && TNSTokenTypes.EOF != token.getType()) {
            end = offset + token.getText().length();
        } else {
            end = offset;
        }

        return end;
    }

    public void advance() {

        if (token != null) {
            if (Token.INVALID_TYPE == token.getType()) {
                // todo
            } else if (TNSTokenTypes.EOF == token.getType()) {
                // todo
            } else {
                offset += token.getText().length();
            }
        }

        advance0();
    }

}
