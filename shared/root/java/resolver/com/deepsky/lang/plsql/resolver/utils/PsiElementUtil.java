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

package com.deepsky.lang.plsql.resolver.utils;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;

public interface PsiElementUtil {

    static TokenSet toSkip = TokenSet.create(
            TokenType.WHITE_SPACE,
            PlSqlTokenTypes.WS,
            PlSqlTokenTypes.ML_COMMENT,
            PlSqlTokenTypes.SL_COMMENT,
            PlSqlTokenTypes.BAD_ML_COMMENT,
            PlSqlElementTypes.ERROR_TOKEN_A,

            PlSqlTokenTypes.IDENTIFIER,

            PlSqlTokenTypes.DOT,
            PlSqlTokenTypes.SEMI,
            PlSqlTokenTypes.COMMA,
            PlSqlTokenTypes.COLON,
            PlSqlTokenTypes.OPEN_PAREN,
            PlSqlTokenTypes.CLOSE_PAREN,

            PlSqlTokenTypes.LT,
            PlSqlTokenTypes.LE,
            PlSqlTokenTypes.GE,
            PlSqlTokenTypes.GT,
            PlSqlTokenTypes.EQ,
            PlSqlTokenTypes.NOT_EQ,
            PlSqlTokenTypes.PLUS,
            PlSqlTokenTypes.MINUS,
            PlSqlTokenTypes.DIVIDE,
            PlSqlTokenTypes.ASTERISK,
            PlSqlTokenTypes.CONCAT,
            PlSqlTokenTypes.PERCENTAGE,
            PlSqlTokenTypes.ASSIGNMENT_EQ,
            PlSqlTokenTypes.OR,
            PlSqlTokenTypes.AND,
            PlSqlTokenTypes.QUOTED_STR,
            PlSqlTokenTypes.NUMBER,
            PlSqlTokenTypes.IDENTIFIER,
            PlSqlTokenTypes.QUOTED_STR,
            PlSqlTokenTypes.DOLLAR,
            PlSqlTokenTypes.DOUBLEDOT

    );
}
