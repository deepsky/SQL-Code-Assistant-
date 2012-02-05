/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.lang.common;

import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.integration.PlSqlElementType;
import com.deepsky.integration.lexer.generated.PlSqlBaseTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;

public interface PlSqlTokenTypes extends PlSqlBaseTokenTypes {

    //    IFileElementType FILE = new IFileElementType(Language.findInstance(PlSqlLanguage.class));
    IFileElementType FILE = new PlSqlFileElementType();

    PlSqlElementType EOF = new PlSqlElementType("EOF", PLSqlTokenTypes.EOF);

    PlSqlElementType OR = new PlSqlElementType("OR", PLSqlTokenTypes.OR_LOGICAL);
    PlSqlElementType AND = new PlSqlElementType("AND", PLSqlTokenTypes.AND_LOGICAL);
    IElementType PROXY_KEYWORD = new PlSqlElementType("PROXY_KEYWORD", 10001);

    TokenSet COMMENTS = TokenSet.create(
            SL_COMMENT, ML_COMMENT, BAD_ML_COMMENT
    );

    TokenSet COMMENTS2 = TokenSet.create(
            SL_COMMENT, ML_COMMENT
    );

    TokenSet WS_TOKENS = TokenSet.create(
            TokenType.WHITE_SPACE,
            WS, SL_COMMENT, ML_COMMENT, BAD_ML_COMMENT, LF
    );

    TokenSet NUMERIC_LITERAL = TokenSet.create(
            NUMBER
    );

    TokenSet OPERATIONS = TokenSet.create(
            LT, LE, GE, GT, EQ, NOT_EQ, PLUS, MINUS, DIVIDE, PERCENTAGE, ASSIGNMENT_EQ, OR, AND
    );

    TokenSet RELATIONAL_OPERATIONS = TokenSet.create(
            LT, GT, LE, GE, EQ, NOT_EQ
    );

    TokenSet ADDITIVE_OPERATIONS = TokenSet.create(
            PLUS, MINUS
    );

    TokenSet MULTIPLICATIVE_OPERATIONS = TokenSet.create(
            ASTERISK, DIVIDE
    );

//    TokenSet ASSIGNMENT_OPERATIONS = TokenSet.create(
//            ASSIGNMENT_EQ
//    );

    TokenSet EQUALITY_OPERATIONS = TokenSet.create(
            EQ, NOT_EQ
    );


    TokenSet STRING_LITERALS = TokenSet.create(
            QUOTED_STR
    );

    TokenSet SORTED_DEF_TOKENS = TokenSet.create(
            PlSqlTokenTypes.KEYWORD_ASC, PlSqlTokenTypes.KEYWORD_DESC,
            PlSqlTokenTypes.KEYWORD_NULLS, PlSqlTokenTypes.KEYWORD_FIRST,
            PlSqlTokenTypes.KEYWORD_LAST
    );


    final static TokenSet PACKAGE_LEVEL_WORDS =
            TokenSet.create(
                    PlSqlTokenTypes.KEYWORD_CREATE,
                    PlSqlTokenTypes.KEYWORD_OR,
                    PlSqlTokenTypes.KEYWORD_REPLACE,
                    PlSqlTokenTypes.KEYWORD_PACKAGE,
                    PlSqlTokenTypes.KEYWORD_BODY,
                    PlSqlTokenTypes.KEYWORD_END,
                    PlSqlTokenTypes.KEYWORD_AS,
                    PlSqlTokenTypes.KEYWORD_IS
            );

}
