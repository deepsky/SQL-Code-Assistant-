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

package com.deepsky.lang.plsql.formatter2.settings;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class PlSqlCodeStyleSettings extends CustomCodeStyleSettings {
    /**
     * Defines if 'flying geese' style should be used for curly braces formatting, e.g. if we want to format code like
     * <p/>
     * <pre>
     *     class Test {
     *         {
     *             System.out.println();
     *         }
     *     }
     * </pre>
     * to
     * <pre>
     *     class Test { {
     *         System.out.println();
     *     } }
     * </pre>
     */
    public boolean USE_FLYING_GEESE_BRACES = false;

    public boolean ALIGN_DATATYPE_IN_COLUMN_DEF = true;
    public boolean ALIGN_DATATYPE_IN_DECL = true;
    public boolean ALIGN_DATATYPE_IN_ARGUMENT_LIST = true;
    public boolean SPACE_BEFORE_OPEN_PAREN_IN_DATATYPE = false;
    public boolean SPACE_BEFORE_PARAMETERS = false;
    public boolean WRAP_OPEN_PAREN_IN_CRATE_TABLE = false;
    public boolean WRAP_SEQUENCE_OPTIONS = true;
    public boolean WRAP_USER_OPTIONS = true;
    public boolean ALIGN_ASSIGNMENTS = false;

    public int MIN_LINES_BETWEEN_FILE_LEVEL_STMT = 1;
    public int MIN_LINES_BETWEEN_BLOCK_LEVEL_STMT = 1;
    public int MIN_LINES_BEFORE_BLOCK = 1;
    public int MIN_LINES_BEFORE_MULTILINE_COMMENT = 1;
    public int MIN_LINES_AFTER_MULTILINE_COMMENT = 1;
    public int MIN_LINES_AFTER_VARIBLE_DECL = 1;

    public int MAX_LINES_BETWEEN_FILE_LEVEL_STMT = 2;
    public int MAX_LINES_BETWEEN_BLOCK_LEVEL_STMT = 1;

    final static public int CASE_NAME_DONT_CHANGE = 1;
    final static public int CASE_NAME_UPPER = 2;
    final static public int CASE_NAME_LOWER = 3;

    // 1 - "Don't change", 2 - "Upper", 3 - "Lower"
    public int NAMES_CASE = 1;
    public int KEYWORD_CASE = 1;


    public PlSqlCodeStyleSettings(CodeStyleSettings container) {
        super("PlSqlCodeStyleSettings", container);
    }

    // indent parameters
    public int TAB_SIZE = 4;

}
