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
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.Language;
//import com.deepsky.integration.PlSqlElementType;
import com.deepsky.integration.lexer.generated.PlSqlBaseTokenTypes;
import com.deepsky.integration.PlSqlElementType;

public interface PlSqlTokenTypes extends PlSqlBaseTokenTypes {

    IFileElementType FILE = new IFileElementType(Language.findInstance(PlSqlLanguage.class));

    PlSqlElementType EOF = new PlSqlElementType("EOF", PLSqlTokenTypes.EOF);

/*
    PlSqlElementType WS = new PlSqlElementType("WS");
    PlSqlElementType SL_COMMENT = new PlSqlElementType("SL_COMMENT");
    PlSqlElementType ML_COMMENT = new PlSqlElementType("ML_COMMENT");
    PlSqlElementType BAD_ML_COMMENT = new PlSqlElementType("BAD_ML_COMMENT");

    PlSqlElementType NUMBER = new PlSqlElementType("NUMBER");
    PlSqlElementType IDENTIFIER = new PlSqlElementType("IDENTIFIER");

    PlSqlElementType COMMA = new PlSqlElementType("COMMA");
    PlSqlElementType DOT = new PlSqlElementType("DOT");
    PlSqlElementType SEMI = new PlSqlElementType("SEMI");
    PlSqlElementType QUOTED_STRING = new PlSqlElementType("QUOTED_STRING");
    PlSqlElementType DOUBLE_QUOTED_STRING = new PlSqlElementType("DOUBLE_QUOTED_STRING");
    PlSqlElementType COLON = new PlSqlElementType("COLON");
    PlSqlElementType ASTERISK = new PlSqlElementType("ASTERISK");
    PlSqlElementType AT_SIGN = new PlSqlElementType("AT_SIGN");
    PlSqlElementType OPEN_PAREN = new PlSqlElementType("OPEN_PAREN");
    PlSqlElementType CLOSE_PAREN = new PlSqlElementType("CLOSE_PAREN");
    PlSqlElementType DOUBLEDOT = new PlSqlElementType("DOUBLEDOT");
    PlSqlElementType CONCAT = new PlSqlElementType("CONCAT");
    PlSqlElementType START_LABEL = new PlSqlElementType("START_LABEL");
    PlSqlElementType END_LABEL = new PlSqlElementType("END_LABEL");
    PlSqlElementType PASS_BY_NAME = new PlSqlElementType("PASS_BY_NAME");
    PlSqlElementType VERTBAR = new PlSqlElementType("VERTBAR");

    PlSqlElementType LT = new PlSqlElementType("LT");
    PlSqlElementType LE = new PlSqlElementType("LE");
    PlSqlElementType GE = new PlSqlElementType("GE");
    PlSqlElementType GT = new PlSqlElementType("GT");
    PlSqlElementType EQ = new PlSqlElementType("EQ");
    PlSqlElementType NOT_EQ = new PlSqlElementType("NOT_EQ");

    PlSqlElementType PLUS = new PlSqlElementType("PLUS");
    PlSqlElementType MINUS = new PlSqlElementType("MINUS");
    PlSqlElementType DIVIDE = new PlSqlElementType("DIVIDE");
    PlSqlElementType PERCENTAGE = new PlSqlElementType("PERCENTAGE");
    PlSqlElementType ASSIGNMENT_EQ = new PlSqlElementType("ASSIGNMENT_EQ");

    // ????
    PlSqlElementType KEYWORD = new PlSqlElementType("KEYWORD");

    PlSqlElementType BAD_CHARACTER = new PlSqlElementType("BAD_CHARACTER");
    PlSqlElementType BAD_CHAR_LITERAL = new PlSqlElementType("BAD_CHAR_LITERAL");
    PlSqlElementType BAD_NUMBER_LITERAL = new PlSqlElementType("BAD_NUMBER_LITERAL");
*/

//    int ANY_CHARACTER = 11;
//    int N = 37;

    PlSqlElementType OR = new PlSqlElementType("OR", PLSqlTokenTypes.OR_LOGICAL);
    PlSqlElementType AND = new PlSqlElementType("AND", PLSqlTokenTypes.AND_LOGICAL);
    IElementType PROXY_KEYWORD = new PlSqlElementType("PROXY_KEYWORD", 10001);

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
