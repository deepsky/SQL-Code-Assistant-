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

package com.deepsky.lang.plsql.highlighter;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for syntax highlighting of Color&Fonts configuration
 * ATTENTION: keep it in sync with SyntaxHighlightAnnotator
 */
public abstract class PlSqlHighlighterBase extends SyntaxHighlighterBase {

    private static final Map<IElementType, TextAttributesKey> keys1;
    private static final Map<IElementType, TextAttributesKey> keys2;

    // Color and Font Page support stuff
    private static final TokenSet BUILT_IN_FUNC = TokenSet.create(
            PlSqlElementTypes.COUNT_FUNC
    );

    private static final TokenSet OBJECT_NAME = TokenSet.create(
            PlSqlElementTypes.TYPE_NAME,
            PlSqlElementTypes.SCHEMA_NAME,

            // TODO -- subject to replace with a specific name
            PlSqlElementTypes.OBJECT_NAME,
            PlSqlElementTypes.PACKAGE_NAME,
            PlSqlElementTypes.TRIGGER_NAME
    );


    private static final TokenSet PLSQL_VARIABLE = TokenSet.create(
            PlSqlElementTypes.VARIABLE_NAME, PlSqlElementTypes.PLSQL_VAR_REF
    );

    private static final TokenSet PLSQL_ARGUMENT = TokenSet.create(
            PlSqlElementTypes.PARAMETER_NAME, PlSqlElementTypes.PARAMETER_REF
    );

    private static final TokenSet COLUMN = TokenSet.create(
            PlSqlElementTypes.COLUMN_NAME_DDL, PlSqlElementTypes.COLUMN_NAME_REF,
            PlSqlElementTypes.GENERIC_REF
    );

    private static final TokenSet SEQUENCE = TokenSet.create(
            PlSqlElementTypes.SEQUENCE_NAME, PlSqlElementTypes.SEQUENCE_REF
    );

    private static final TokenSet TABLE_NAME = TokenSet.create(
            PlSqlElementTypes.TABLE_NAME_DDL, PlSqlElementTypes.TABLE_NAME_REF
    );


    static {
        keys1 = new HashMap<IElementType, TextAttributesKey>();
        keys2 = new HashMap<IElementType, TextAttributesKey>();

//        fillMap(keys1, PlSqlTokenTypes.KEYWORDS, DefaultHighlighter.KEYWORD);
        fillMap(keys1, PlSqlTokenTypes.OPERATION_SIGNS, DefaultHighlighter.OPERATION_SIGN);
//        fillMap(keys1, BUILT_IN_FUNC, DefaultHighlighter.BUILT_IN_FUNC);
        fillMap(keys1, OBJECT_NAME, DefaultHighlighter.OBJECT_NAME);
        fillMap(keys1, PLSQL_VARIABLE, DefaultHighlighter.PLSQL_VAR);
        fillMap(keys1, PLSQL_ARGUMENT, DefaultHighlighter.PLSQL_PARAMETER);
        fillMap(keys1, COLUMN, DefaultHighlighter.COLUMN);
        fillMap(keys1, SEQUENCE, DefaultHighlighter.SEQUENCE);
        fillMap(keys1, TABLE_NAME, DefaultHighlighter.TABLE_NAME);

        fillMap(keys1, PlSqlElementTypes.SQLPLUS_COMMANDS, DefaultHighlighter.SQLPLUS_CMD);

        keys1.put(PlSqlElementTypes.BUILT_IT_FUNCTION_CALL, DefaultHighlighter.BUILT_IN_FUNC);
        keys1.put(PlSqlElementTypes.ALIAS_IDENT, DefaultHighlighter.ALIAS_IDENT);
        keys1.put(PlSqlElementTypes.TABLE_REF_NOT_RESOLVED, DefaultHighlighter.TABLE_NAME_NOT_RESOLVED);
        keys1.put(PlSqlElementTypes.CALL_NOT_RESOLVED, DefaultHighlighter.FUNC_REF_NOT_RESOLVED);
        keys1.put(PlSqlElementTypes.DATATYPE, DefaultHighlighter.DATA_TYPE);
        keys1.put(PlSqlElementTypes.CONSTRAINT_NAME, DefaultHighlighter.CONSTRAINT_NAME);

        keys1.put(PlSqlTokenTypes.KEYWORD, DefaultHighlighter.KEYWORD);
        keys1.put(PlSqlTokenTypes.QUOTED_STR, DefaultHighlighter.STRING);
        keys1.put(PlSqlTokenTypes.QUOTED_STR_START, DefaultHighlighter.STRING);
        keys1.put(PlSqlTokenTypes.QUOTED_STR_END, DefaultHighlighter.STRING);
        keys1.put(PlSqlTokenTypes.NUMBER, DefaultHighlighter.NUMBER);

        keys1.put(PlSqlTokenTypes.OPEN_PAREN, DefaultHighlighter.PARENTHESES);
        keys1.put(PlSqlTokenTypes.CLOSE_PAREN, DefaultHighlighter.PARENTHESES);

        keys1.put(PlSqlTokenTypes.COMMA, DefaultHighlighter.COMMA);
        keys1.put(PlSqlTokenTypes.DOT, DefaultHighlighter.DOT);
        keys1.put(PlSqlTokenTypes.SEMI, DefaultHighlighter.SEMICOLON);

        keys1.put(PlSqlTokenTypes.ML_COMMENT, DefaultHighlighter.BLOCK_COMMENT);
        keys1.put(PlSqlTokenTypes.BAD_ML_COMMENT, DefaultHighlighter.BLOCK_COMMENT);
        keys1.put(PlSqlTokenTypes.SL_COMMENT, DefaultHighlighter.LINE_COMMENT);

        keys1.put(PlSqlTokenTypes.BAD_CHARACTER, HighlighterColors.BAD_CHARACTER);
        keys1.put(PlSqlTokenTypes.BAD_CHAR_LITERAL, HighlighterColors.BAD_CHARACTER);
        keys1.put(PlSqlTokenTypes.BAD_NUMBER_LITERAL, HighlighterColors.BAD_CHARACTER);
        keys1.put(PlSqlTokenTypes.BAD_QUOTED_STRING, HighlighterColors.BAD_CHARACTER);
    }

    @NotNull
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(keys1.get(tokenType), keys2.get(tokenType));
    }

}
