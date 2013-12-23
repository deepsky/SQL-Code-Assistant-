package com.deepsky.lang.lexer;

import antlr.Token;
import com.deepsky.generated.plsql.adopted.PLSqlTokenTypes;
import com.deepsky.integration.PLSqlLexerEx;
import com.deepsky.integration.lexer.generated.PlSqlTokenTypesMapping;
import com.intellij.psi.tree.IElementType;


public class PlSqlPsiLexer extends GenericLexer<PLSqlLexerEx> {


    public PlSqlPsiLexer() {
        super(PLSqlLexerEx.class);
    }


    public IElementType getTokenType() {
        return PlSqlTokenTypesMapping.table[token.getType()];
    }


    public int getTokenEnd() {
        int end;

        if (token != null && PLSqlTokenTypes.EOF != token.getType()) {
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
            } else if (PLSqlTokenTypes.EOF == token.getType()) {
                // todo
            } else {
                offset += token.getText().length();
            }
        }

        advance0();
    }

}
