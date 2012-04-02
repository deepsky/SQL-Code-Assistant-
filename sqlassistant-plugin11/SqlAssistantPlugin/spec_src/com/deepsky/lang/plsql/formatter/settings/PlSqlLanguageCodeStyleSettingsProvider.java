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

import com.deepsky.lang.common.PlSqlFileType;
import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;

public class PlSqlLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {
    @NotNull
    @Override
    public Language getLanguage() {
        return PlSqlFileType.PLSQL_LANGUAGE;
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        switch (settingsType) {
            case INDENT_SETTINGS:
                return INDENT_OPTIONS_SAMPLE;
            case SPACING_SETTINGS:
                return SPACING_SAMPLE;
            case WRAPPING_AND_BRACES_SETTINGS:
                return LANGUAGE_SPECIFIC_SAMPLE;
            case BLANK_LINES_SETTINGS:
                return BLANK_LINE_SAMPLE;
            default:
                return "";
        }
    }

    @Override
    public IndentOptionsEditor getIndentOptionsEditor() {
        return new SmartIndentOptionsEditor() {
//            private JTextField myLabelIndent;
            //private JLabel myLabelIndentLabel;

            //private JCheckBox myLabelIndentAbsolute;

            protected void addComponents() {
                super.addComponents();

/*
                myLabelIndent = new JTextField(4);
                add(myLabelIndentLabel = new JLabel(ApplicationBundle.message("editbox.indent.label.indent")), myLabelIndent);

                myLabelIndentAbsolute = new JCheckBox(ApplicationBundle.message("checkbox.indent.absolute.label.indent"));
                add(myLabelIndentAbsolute, true);
*/
            }

            public boolean isModified(final CodeStyleSettings settings, final CommonCodeStyleSettings.IndentOptions options) {
                boolean isModified = super.isModified(settings, options);

//                isModified |= isFieldModified(myLabelIndent, options.LABEL_INDENT_SIZE);
//                isModified |= isFieldModified(myLabelIndentAbsolute, options.LABEL_INDENT_ABSOLUTE);

                return isModified;
            }

            public void apply(final CodeStyleSettings settings, final CommonCodeStyleSettings.IndentOptions options) {
                super.apply(settings, options);
//                options.LABEL_INDENT_SIZE = getFieldValue(myLabelIndent, Integer.MIN_VALUE, options.LABEL_INDENT_SIZE);
//                options.LABEL_INDENT_ABSOLUTE = myLabelIndentAbsolute.isSelected();
            }

            public void reset(@NotNull final CodeStyleSettings settings, @NotNull final CommonCodeStyleSettings.IndentOptions options) {
                super.reset(settings, options);
//                myLabelIndent.setText(Integer.toString(options.LABEL_INDENT_SIZE));
//                myLabelIndentAbsolute.setSelected(options.LABEL_INDENT_ABSOLUTE);
            }

            public void setEnabled(final boolean enabled) {
                super.setEnabled(enabled);
//                myLabelIndent.setEnabled(enabled);
//                myLabelIndentLabel.setEnabled(enabled);
//                myLabelIndentAbsolute.setEnabled(enabled);
            }
        };
    }

    @Override
    public boolean usesSharedPreview() {
        return false;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer,
                                  @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.INDENT_SETTINGS) {
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "TAB_SIZE", "Tab size", CodeStyleSettingsCustomizable.WRAPPING_BRACES);
            return;
        }
        if (settingsType == SettingsType.WRAPPING_AND_BRACES_SETTINGS) {
//            consumer.showAllStandardOptions();
            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "ALIGNMENT_IN_COLUMN_DEF", "Column definition", "Alignment",
                    new String[]{"Don't change", "Types", "Types & NULL"}, new int[]{1, 2, 3});

            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "ALIGN_DATATYPE_IN_DECL", "Declaration list", "Alignment");
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "ALIGN_DATATYPE_IN_ARGUMENT_LIST", "Argument list", "Alignment");
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "ALIGN_ALIAS_NAME_IN_SELECT", "Alias in column expressions", "Alignment");
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "ALIGN_ASSIGNMENTS", "Assignment statements", "Alignment");
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "WRAP_OPEN_PAREN_IN_CREATE_TABLE", "Wrap '(' in CREATE TABLE", "Wrapping");
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "WRAP_SEQUENCE_OPTIONS", "Wrap options in CREATE SEQUENCE", "Wrapping");
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "WRAP_USER_OPTIONS", "Wrap options in CREATE USER", "Wrapping");
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "DONT_WRAP_SELECT_IF_SIMPLE", "Don't wrap Select statement if trivial", "Wrapping");

            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "COMMENT_AT_FIRST_COLUMN", "Comment at first column", "Keep when reformatting");
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "KEEP_LINE_BREAKS", "Line breaks", "Keep when reformatting");

            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "COMMA_AFTER_SELECT_EXPR", "In relation to column expression in SELECT", "Comma placement",
                    new String[]{"Don't change", "After", "Before"}, new int[]{1, 2, 3});
            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "COMMA_AFTER_COLUMN_DEFINITION", "In relation to column definition in CREATE TABLE", "Comma placement",
                    new String[]{"Don't change", "After", "Before"}, new int[]{1, 2, 3});

            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "NAMES_CASE", "Case of Names", "Others",
                    new String[]{"Don't change", "Upper", "Lower"}, new int[]{1, 2, 3});
            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "KEYWORD_CASE", "Case of Keywords", "Others",
                    new String[]{"Don't change", "Upper", "Lower"}, new int[]{1, 2, 3});

            return;
        }
        if (settingsType == SettingsType.SPACING_SETTINGS) {
//            consumer.showAllStandardOptions();
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "SPACE_BEFORE_OPEN_PAREN_IN_DATATYPE", "Datatype parentheses", CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "SPACE_BEFORE_PARAMETERS", "Method call parentheses", CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            return;
        }
        if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
            // Blank Line to KEEP
            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "MAX_LINES_BETWEEN_FILE_LEVEL_STMT",
                    "Between CREATE, ALTER, DROP, etc",
                    CodeStyleSettingsCustomizable.BLANK_LINES_KEEP);
            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "MAX_LINES_BETWEEN_BLOCK_LEVEL_STMT",
                    "Between statements inside a block",
                    CodeStyleSettingsCustomizable.BLANK_LINES_KEEP);
            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "MAX_LINES_BETWEEN_PKG_LEVEL_STMT",
                    "Between declarations in package body/specification",
                    CodeStyleSettingsCustomizable.BLANK_LINES_KEEP);

            // Min blank lines
            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "MIN_LINES_BETWEEN_FILE_LEVEL_STMT",
                    "Between CREATE, ALTER, DROP, etc",
                    CodeStyleSettingsCustomizable.BLANK_LINES);
            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "MIN_LINES_BETWEEN_BLOCK_LEVEL_STMT",
                    "Between statements inside a block",
                    CodeStyleSettingsCustomizable.BLANK_LINES);
            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "MIN_LINES_BEFORE_BLOCK",
                    "Before a PL/SQL block",
                    CodeStyleSettingsCustomizable.BLANK_LINES);
            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "MIN_LINES_BEFORE_MULTILINE_COMMENT",
                    "Before C Style comment",
                    CodeStyleSettingsCustomizable.BLANK_LINES);
            consumer.showCustomOption(PlSqlCodeStyleSettings.class,
                    "MIN_LINES_AFTER_MULTILINE_COMMENT",
                    "After C Style comment",
                    CodeStyleSettingsCustomizable.BLANK_LINES);
        }
//        consumer.showAllStandardOptions();
    }


    private final static String INDENT_OPTIONS_SAMPLE =
            "create table SALES(\n" +
                    "    id number,\n" +
                    "    text varchar2(64)\n" +
                    ");";


    private final static String SPACING_SAMPLE =
            "select substr(text, 2, 6)\n" +
                    "from SALES;" +
                    "\n" +
                    "\n" +
                    "create table SALES(\n" +
                    "    id number,\n" +
                    "    text varchar2(64)\n" +
                    ");";


    private final static String LANGUAGE_SPECIFIC_SAMPLE =
                    "create table SALES(\n" +
                    "    Id number primary key,\n" +
                    "    Text varchar2(255) not null,\n" +
                    "    Email_address varchar2(64) not null," +
                    "    CONSTRAINT \"SALES_EMAIL\" UNIQUE (\"EMAIL_ADDRESS\")\n" +
                    ");" +
                    "\n" +
                    "FUNCTION GetProperty (\n" +
                    "    token binary_integer,\n" +
                    "    PropName IN NOCOPY VARCHAR2,\n" +
                    "    argCount binary_integer,\n" +
                    "    retVal out double precision)\n" +
                    "RETURN binary_integer\n" +
                    "IS\n" +
                    "    inArgTable       INTEGER;\n" +
                    "    inArgTypeTable   DATE;\n" +
                    "    dblVal VARCHAR2(50) := P_TIME_ZONE;\n" +
                    "    ret binary_integer;\n" +
                    "BEGIN\n" +
                    "       -- Find number\n" +
                    "    ret := OAgetNumber(token, PropName, inArgTable, inArgTypeTable, dblVal, argCount);\n" +
                    "    retVal := ret;\n" +
                    "    RETURN ret;\n" +
                    "END GetProperty;" +
                    "\n"+
                    "CREATE SEQUENCE T1_SEQ START WITH 100000\n" +
                            " INCREMENT BY 2\n" +
                            " MINVALUE 1\n" +
                            "MAXVALUE 9999999999999999999999999999  MINVALUE 1\n" +
                            "CYCLE CACHE 25\n" +
                            "NOORDER;\n" +
                            "\n" +
                            "SELECT\n" +
                            "p1.min_tspan AS TSPAN\n" +
                            "\t,\n" +
                            "        msk.id AS MASK_ID\n" +
                            "        ,         msk.mask_name AS MASK_NAME,\n" +
                            "        cr.column_name AS COLUMN_NAME,\n" +
                            "        cr.column_id AS COLUMN_ID\n" +
                            "    FROM\n" +
                            "    meta_data cr, mask_options msk, column p1;";


    private final static String BLANK_LINE_SAMPLE =
            "create table SALES(\n" +
                    "    id number,\n" +
                    "    text varchar2(64)\n" +
                    ");\n" +
                    "FUNCTION GetProperty (\n" +
                    "    token binary_integer,\n" +
                    "    PropName IN NOCOPY VARCHAR2,\n" +
                    "    argCount binary_integer,\n" +
                    "    retVal out double precision)\n" +
                    "RETURN binary_integer\n" +
                    "IS\n" +
                    "    inArgTable       INTEGER;\n" +
                    "    inArgTypeTable   DATE;\n" +
                    "    dblVal VARCHAR2(50) := P_TIME_ZONE;\n" +
                    "    retval binary_integer;\n" +
                    "BEGIN\n" +
                    "    retval := OAgetNumber(token, PropName, inArgTable, inArgTypeTable, dblVal, argCount);\n" +
                    "    RETURN retval;\n" +
                    "END GetProperty;";

}
