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

package com.deepsky.lang.plsql.formatter.settings;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class PlSqlCodeStyleSettings extends CustomCodeStyleSettings {

    // 1 - "Don't change", 2 - "Type", 3 - "Type&Null"
    public int ALIGNMENT_IN_COLUMN_DEF = 2;

    public boolean SPACE_WITHIN_BRACKETS = false;

    /**
     * Keep up to this amount of blank lines in code
     */
    public int KEEP_BLANK_LINES_IN_CODE = 0;

    public boolean ALIGN_DATATYPE_IN_COLUMN_DEF = true;
    public boolean ALIGN_DATATYPE_IN_DECL = true;
    public boolean ALIGN_DATATYPE_IN_ARGUMENT_LIST = true;
    public boolean SPACE_BEFORE_OPEN_PAREN_IN_DATATYPE = false;
    public boolean SPACE_BEFORE_PARAMETERS = false;
    public boolean WRAP_OPEN_PAREN_IN_CREATE_TABLE = false;
    public boolean WRAP_SEQUENCE_OPTIONS = true;
    public boolean WRAP_USER_OPTIONS = true;
    public boolean ALIGN_ASSIGNMENTS = true;
    public boolean DONT_WRAP_SELECT_IF_SIMPLE = true;

    public boolean KEEP_LINE_BREAKS = true;

    /**
     * Controls END_OF_LINE_COMMENT's and C_STYLE_COMMENT's
     */
    public boolean COMMENT_AT_FIRST_COLUMN = false;


    // If true then alignment will not go out of the group similar declarations
    public boolean KEEP_ALIGNMENT_IN_GROUP = true;   // todo -- make it configurable

    // todo -- put into setting dialog
    public boolean ALIGN_ALIAS_NAME_IN_SELECT = true;

    public int MIN_LINES_BETWEEN_FILE_LEVEL_STMT = 1;
    public int MIN_LINES_BETWEEN_BLOCK_LEVEL_STMT = 1;
    public int MIN_LINES_BEFORE_BLOCK = 1;
    public int MIN_LINES_BEFORE_MULTILINE_COMMENT = 1;
    public int MIN_LINES_AFTER_MULTILINE_COMMENT = 1;
    public int MIN_LINES_AFTER_VARIBLE_DECL = 1;
    public int MIN_LINES_BETWEEN_SL_COMMENTS = 0;

    public int MAX_LINES_BETWEEN_FILE_LEVEL_STMT = 2;
    public int MAX_LINES_BETWEEN_BLOCK_LEVEL_STMT = 1;
    public int MAX_LINES_BETWEEN_SL_COMMENTS = 0;
    public int MAX_LINES_BETWEEN_PKG_LEVEL_STMT = 2;

    final static public int CASE_NAME_DONT_CHANGE = 1;
    final static public int CASE_NAME_UPPER = 2;
    final static public int CASE_NAME_LOWER = 3;

    // 1 - "Don't change", 2 - "Upper", 3 - "Lower"
    public int NAMES_CASE = 1;
    public int KEYWORD_CASE = 1;

    // 1 - "Don't change", 2 - "After", 3 - "Before"
    public int COMMA_AFTER_SELECT_EXPR = 1;
    public int COMMA_AFTER_COLUMN_DEFINITION = 1;

    public PlSqlCodeStyleSettings(CodeStyleSettings container) {
        super("PlSqlCodeStyleSettings", container);
    }

    // indent parameters
    public int TAB_SIZE = 4;

}
