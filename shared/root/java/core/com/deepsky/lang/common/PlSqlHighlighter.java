package com.deepsky.lang.common;

import com.deepsky.lang.lexer.PlSqlHiLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.StringEscapesTokenTypes;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PlSqlHighlighter extends SyntaxHighlighterBase {

    private static Map<IElementType, TextAttributesKey> keys1;
    private static Map<IElementType, TextAttributesKey> keys2;

    @NotNull
    public Lexer getHighlightingLexer() {
        return new PlSqlHiLexer();
    }

    static final TextAttributesKey PLSQL_KEYWORD = TextAttributesKey.createTextAttributesKey(
            "PLSQL.KEYWORD",
            SyntaxHighlighterColors.KEYWORD.getDefaultAttributes()
    );

    static final TextAttributesKey PLSQL_STRING = TextAttributesKey.createTextAttributesKey(
            "PLSQL.STRING",
            SyntaxHighlighterColors.STRING.getDefaultAttributes()
    );

    static final TextAttributesKey PLSQL_NUMBER = TextAttributesKey.createTextAttributesKey(
            "PLSQL.NUMBER",
            SyntaxHighlighterColors.NUMBER.getDefaultAttributes()
    );


    static final TextAttributesKey PLSQL_LINE_COMMENT = TextAttributesKey.createTextAttributesKey(
            "PLSQL.LINE_COMMENT",
            SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes()
    );

    static final TextAttributesKey PLSQL_BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey(
            "PLSQL.BLOCK_COMMENT",
            SyntaxHighlighterColors.JAVA_BLOCK_COMMENT.getDefaultAttributes()
    );

    static final TextAttributesKey PLSQL_OPERATION_SIGN = TextAttributesKey.createTextAttributesKey(
            "PLSQL.OPERATION_SIGN",
            SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
    );

    static final TextAttributesKey PLSQL_PARENTHS = TextAttributesKey.createTextAttributesKey(
            "PLSQL.PARENTHS",
            SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
    );

    static final TextAttributesKey PLSQL_COMMA = TextAttributesKey.createTextAttributesKey(
            "PLSQL.COMMA",
            SyntaxHighlighterColors.COMMA.getDefaultAttributes()
    );

    static final TextAttributesKey PLSQL_DOT = TextAttributesKey.createTextAttributesKey(
            "PLSQL.DOT",
            SyntaxHighlighterColors.DOT.getDefaultAttributes()
    );

    static final TextAttributesKey PLSQL_SEMICOLON = TextAttributesKey.createTextAttributesKey(
            "PLSQL.SEMICOLON",
            SyntaxHighlighterColors.JAVA_SEMICOLON.getDefaultAttributes()
    );

    static final TextAttributesKey SQL_COLUMN = TextAttributesKey.createTextAttributesKey(
            "SQL.COLUMN",
            //SyntaxHighlighterColors.JAVA_SEMICOLON.getDefaultAttributes()
            new TextAttributes(new Color(40, 40, 40), null, null, null, Font.PLAIN)
    );

    static final TextAttributesKey PLSQL_VAR = TextAttributesKey.createTextAttributesKey(
            "PLSQL.VAR",
            new TextAttributes(new Color(124, 26, 132), null, null, null, Font.BOLD)
    );

    static final TextAttributesKey PLSQL_SYSFUNC = TextAttributesKey.createTextAttributesKey(
            "PLSQL.SYSFUNC",
            new TextAttributes(new Color(190, 13, 3), null, null, null, Font.BOLD)
    );

    static final TextAttributesKey PLSQL_ERR_HIGHTL = TextAttributesKey.createTextAttributesKey(
            "PLSQL.ERR_HIGHTL",
            new TextAttributes(Color.black, null, Color.RED, EffectType.WAVE_UNDERSCORE, Font.PLAIN)
    );

    static final TextAttributesKey PLSQL_SYSFUNC_ERRORED = TextAttributesKey.createTextAttributesKey(
            "PLSQL.SYSFUNC.ERRORED",
            new TextAttributes(new Color(166, 68, 87), null, Color.RED, EffectType.WAVE_UNDERSCORE, Font.BOLD)
    );

    static final TextAttributesKey SQLPLUS_CMD = TextAttributesKey.createTextAttributesKey(
            "SQLPLUS.CMD",
            new TextAttributes(new Color(157, 24, 207), null, null, null, Font.BOLD)
    );

    static final TextAttributesKey SQL_TABLE = TextAttributesKey.createTextAttributesKey(
            "SQL.TABLE",
            new TextAttributes(new Color(102, 36, 89), null, null, null, Font.PLAIN)
    );

    static final TextAttributesKey SQL_TABLE_BOLD = TextAttributesKey.createTextAttributesKey(
            "SQL.TABLE.BOLD",
            new TextAttributes(new Color(102, 36, 89), null, null, null, Font.BOLD)
    );

    static final TextAttributesKey SQL_VIEW = TextAttributesKey.createTextAttributesKey(
            "SQL.VIEW",
            new TextAttributes(new Color(44, 131, 81), null, null, null, Font.PLAIN)
    );

    static final TextAttributesKey SQL_VIEW_BOLD = TextAttributesKey.createTextAttributesKey(
            "SQL.VIEW.BOLD",
            new TextAttributes(new Color(44, 131, 81), null, null, null, Font.BOLD)
    );

    static final TextAttributesKey SQL_USER_DEFINED_TYPE = TextAttributesKey.createTextAttributesKey(
            "SQL.USER_DEFINED_TYPE",
            new TextAttributes(new Color(23, 98, 76), null, null, null, Font.BOLD)
    );

    static final TextAttributesKey SQL_DATA_TYPE = TextAttributesKey.createTextAttributesKey(
            "SQL.DATA_TYPE",
            new TextAttributes(new Color(69, 69, 226), null, null, null, Font.BOLD)
    );

    static final TextAttributesKey SQL_SYNONYM = TextAttributesKey.createTextAttributesKey(
            "SQL.SYNONYM",
            new TextAttributes(new Color(118, 106, 37), null, null, null, Font.PLAIN)
    );

    static {
        keys1 = new HashMap<IElementType, TextAttributesKey>();
        keys2 = new HashMap<IElementType, TextAttributesKey>();

        fillMap(keys1, PlSqlTokenTypes.KEYWORDS, PLSQL_KEYWORD);
        fillMap(keys1, PlSqlTokenTypes.OPERATIONS, PLSQL_OPERATION_SIGN);

        fillMap(keys1, PlSqlTokenTypes.NUMERIC_LITERAL, PLSQL_NUMBER);

        keys1.put(StringEscapesTokenTypes.VALID_STRING_ESCAPE_TOKEN, SyntaxHighlighterColors.VALID_STRING_ESCAPE);
        keys1.put(StringEscapesTokenTypes.INVALID_CHARACTER_ESCAPE_TOKEN, SyntaxHighlighterColors.INVALID_STRING_ESCAPE);
        keys1.put(StringEscapesTokenTypes.INVALID_UNICODE_ESCAPE_TOKEN, SyntaxHighlighterColors.INVALID_STRING_ESCAPE);
        keys1.put(PlSqlTokenTypes.QUOTED_STR, PLSQL_STRING);

        keys1.put(PlSqlTokenTypes.OPEN_PAREN, PLSQL_PARENTHS);
        keys1.put(PlSqlTokenTypes.CLOSE_PAREN, PLSQL_PARENTHS);

        keys1.put(PlSqlTokenTypes.COMMA, PLSQL_COMMA);
        keys1.put(PlSqlTokenTypes.DOT, PLSQL_DOT);
        keys1.put(PlSqlTokenTypes.SEMI, PLSQL_SEMICOLON);

        keys1.put(PlSqlTokenTypes.ML_COMMENT, PLSQL_BLOCK_COMMENT);
        keys1.put(PlSqlTokenTypes.BAD_ML_COMMENT, PLSQL_BLOCK_COMMENT);
        keys1.put(PlSqlTokenTypes.SL_COMMENT, PLSQL_LINE_COMMENT);

        keys1.put(PlSqlTokenTypes.BAD_CHARACTER, HighlighterColors.BAD_CHARACTER);
        keys1.put(PlSqlTokenTypes.BAD_CHAR_LITERAL, HighlighterColors.BAD_CHARACTER);
        keys1.put(PlSqlTokenTypes.BAD_NUMBER_LITERAL, HighlighterColors.BAD_CHARACTER);
    }

    @NotNull
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(keys1.get(tokenType), keys2.get(tokenType));
    }

    public Map<IElementType, TextAttributesKey> getKeys1() {
        return (Map<IElementType, TextAttributesKey>) ((HashMap) keys1).clone();
    }

    public Map<IElementType, TextAttributesKey> getKeys2() {
        return (Map<IElementType, TextAttributesKey>) ((HashMap) keys2).clone();
    }
}

