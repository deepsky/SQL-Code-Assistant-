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
import com.deepsky.integration.PlSqlTokenType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.Language;
//import com.deepsky.integration.PlSqlElementType;
import com.deepsky.integration.lexer.generated.PlSqlBaseTokenTypes;
import com.deepsky.integration.PlSqlElementType;

public interface PlSqlTokenTypes extends PlSqlBaseTokenTypes {

    IFileElementType FILE = new IFileElementType(Language.findInstance(PlSqlLanguage.class));

    PlSqlTokenType EOF = new PlSqlTokenType(PLSqlTokenTypes.EOF, "EOF");

    PlSqlTokenType OR = new PlSqlTokenType(PLSqlTokenTypes.OR_LOGICAL, "OR");
    PlSqlTokenType AND = new PlSqlTokenType(PLSqlTokenTypes.AND_LOGICAL, "AND");
    IElementType PROXY_KEYWORD = new PlSqlTokenType(10001, "PROXY_KEYWORD");

    TokenSet COMMENTS = TokenSet.create(
            SL_COMMENT, ML_COMMENT, BAD_ML_COMMENT
    );

    TokenSet NUMERIC_LITERAL  = TokenSet.create(
            NUMBER
    );

//    TokenSet KEYWORDS = TokenSet.create(
//            KEYWORD
//    );

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

    TokenSet ASSIGNMENT_OPERATIONS = TokenSet.create(
      ASSIGNMENT_EQ
    );

    TokenSet EQUALITY_OPERATIONS = TokenSet.create(
      EQ, NOT_EQ
    );


    TokenSet STRING_LITERALS  = TokenSet.create(
        QUOTED_STR      
    );
}
