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

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;

import java.awt.*;

public class DefaultHighlighter {

    static final public TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey(
            "PLSQL.KEYWORD",
            DefaultLanguageHighlighterColors.KEYWORD
    );

    static final public TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(
            "PLSQL.STRING",
            DefaultLanguageHighlighterColors.STRING
    );

    static final public TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey(
            "PLSQL.NUMBER",
            DefaultLanguageHighlighterColors.NUMBER
    );


    static final public TextAttributesKey LINE_COMMENT = TextAttributesKey.createTextAttributesKey(
            "PLSQL.LINE_COMMENT",
            DefaultLanguageHighlighterColors.LINE_COMMENT
    );

    static final public TextAttributesKey BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey(
            "PLSQL.BLOCK_COMMENT",
            DefaultLanguageHighlighterColors.BLOCK_COMMENT
    );

    static final public TextAttributesKey OPERATION_SIGN = TextAttributesKey.createTextAttributesKey(
            "PLSQL.OPERATION_SIGN",
            DefaultLanguageHighlighterColors.OPERATION_SIGN
    );

    static final public TextAttributesKey PARENTHESES = TextAttributesKey.createTextAttributesKey(
            "PLSQL.PARENTHESES",
            DefaultLanguageHighlighterColors.OPERATION_SIGN
    );

    static final public TextAttributesKey COMMA = TextAttributesKey.createTextAttributesKey(
            "PLSQL.COMMA",
            DefaultLanguageHighlighterColors.COMMA
    );

    static final public TextAttributesKey DOT = TextAttributesKey.createTextAttributesKey(
            "PLSQL.DOT",
            DefaultLanguageHighlighterColors.DOT
    );

    static final public TextAttributesKey SEMICOLON = TextAttributesKey.createTextAttributesKey(
            "PLSQL.SEMICOLON",
            DefaultLanguageHighlighterColors.SEMICOLON
    );

    static final public TextAttributesKey CONSTRAINT_NAME = TextAttributesKey.createTextAttributesKey(
            "SQL.CONSTRAINT_NAME",
            new TextAttributes(new Color(40, 40, 40), null, null, null, Font.PLAIN)
    );

    static final public TextAttributesKey COLUMN = TextAttributesKey.createTextAttributesKey(
            "SQL.COLUMN",
            new TextAttributes(new Color(64, 64, 64), null, null, null, Font.PLAIN)
    );

    static final public TextAttributesKey ALIAS_IDENT = TextAttributesKey.createTextAttributesKey(
            "SQL.ALIAS_IDENT",
            new TextAttributes(new Color(164, 64, 64), null, null, null, Font.PLAIN)
    );

    static final public TextAttributesKey GENERIC_REF = TextAttributesKey.createTextAttributesKey(
            "SQL.GENERIC_REF",
            DefaultLanguageHighlighterColors.SEMICOLON
    );

    static final public TextAttributesKey PLSQL_VAR = TextAttributesKey.createTextAttributesKey(
            "PLSQL.VAR",
            new TextAttributes(new Color(124, 26, 132), null, null, null, Font.BOLD)
    );

    static final public TextAttributesKey PLSQL_PARAMETER = TextAttributesKey.createTextAttributesKey(
            "PLSQL.PARAMETER",
            new TextAttributes(new Color(124, 26, 132), null, null, null, Font.BOLD)
    );

    static final public TextAttributesKey BUILT_IN_FUNC = TextAttributesKey.createTextAttributesKey(
            "PLSQL.BUILT_IN_FUNC",
            new TextAttributes(new Color(190, 13, 3), null, null, null, Font.BOLD)
    );

    static final public TextAttributesKey UDF = TextAttributesKey.createTextAttributesKey(
            "PLSQL.UDF",
            new TextAttributes(new Color(40, 40, 40), null, null, null, Font.PLAIN)
    );

    static final public TextAttributesKey SEQUENCE = TextAttributesKey.createTextAttributesKey(
            "PLSQL.SEQUENCE",
            new TextAttributes(new Color(40, 40, 40), null, null, null, Font.PLAIN)
    );

    static final public TextAttributesKey SEQUENCE_REF = TextAttributesKey.createTextAttributesKey(
            "PLSQL.SEQUENCE_REF",
            new TextAttributes(new Color(40, 40, 40), null, null, null, Font.PLAIN)
    );

    static final public TextAttributesKey SYNONYM = TextAttributesKey.createTextAttributesKey(
            "SQL.SYNONYM",
            new TextAttributes(new Color(118, 106, 37), null, null, null, Font.PLAIN)
    );

    static final public TextAttributesKey SQLPLUS_CMD = TextAttributesKey.createTextAttributesKey(
            "SQLPLUS.SQLPLUS_CMD",
            new TextAttributes(new Color(157, 24, 207), null, null, null, Font.BOLD)
    );

    static final public TextAttributesKey OBJECT_NAME = TextAttributesKey.createTextAttributesKey(
            "PLSQL.OBJECT_NAME",
            new TextAttributes(new Color(40, 40, 40), null, null, null, Font.PLAIN)
    );

    static final public TextAttributesKey TABLE_NAME = TextAttributesKey.createTextAttributesKey(
            "SQL.TABLE_NAME",
            new TextAttributes(new Color(102, 36, 89), null, null, null, Font.BOLD)
    );

    static final public TextAttributesKey FUNC_REF_NOT_RESOLVED = TextAttributesKey.createTextAttributesKey(
            "SQL.FUNC_REF_NOT_RESOLVED",
            new TextAttributes(new Color(60, 60, 60), null, null, null, Font.PLAIN)
    );

    static final public TextAttributesKey TABLE_NAME_NOT_RESOLVED = TextAttributesKey.createTextAttributesKey(
            "SQL.TABLE_REF_NOT_RESOLVED",
            new TextAttributes(new Color(60, 60, 60), null, null, null, Font.PLAIN)
    );

    static final public TextAttributesKey VIEW_NAME = TextAttributesKey.createTextAttributesKey(
            "SQL.VIEW",
            new TextAttributes(new Color(44, 131, 81), null, null, null, Font.PLAIN)
    );

    static final public TextAttributesKey USER_DEFINED_TYPE = TextAttributesKey.createTextAttributesKey(
            "SQL.USER_DEFINED_TYPE",
            new TextAttributes(new Color(23, 98, 76), null, null, null, Font.BOLD)
    );

    static final public TextAttributesKey DATA_TYPE = TextAttributesKey.createTextAttributesKey(
            "SQL.DATA_TYPE",
            new TextAttributes(new Color(69, 69, 226), null, null, null, Font.BOLD)
    );


}
