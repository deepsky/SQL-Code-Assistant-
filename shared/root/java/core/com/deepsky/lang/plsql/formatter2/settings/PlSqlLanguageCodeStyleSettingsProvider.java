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

import com.deepsky.lang.common.PlSqlFileType;
import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.application.options.codeStyle.WrappingAndBracesPanel;
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
//            case BLANK_LINES_SETTINGS: return BLANK_LINE_SAMPLE;
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
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "ALIGN_DATATYPE_IN_COLUMN_DEF", "In column definition", "Alignment");
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "ALIGN_DATATYPE_IN_DECL", "In variable declaration", "Alignment");
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "ALIGN_DATATYPE_IN_ARGUMENT_LIST", "In argument list", "Alignment");
            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "WRAP_OPEN_PAREN_IN_CRATE_TABLE", "Wrap '(' in CREATE TABLE", "Wrapping");
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

//            consumer.showCustomOption(PlSqlCodeStyleSettings.class, "NAMES_CASE_UPPER", "Case of Names", CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            return;
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
                    "    Text varchar2(255),\n" +
                    "    Email_address varchar2(64)," +
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
                    "    load_id       INTEGER;\n" +
                    "    load_st_dt    DATE;\n" +
                    "    load_end_dt   DATE;\n" +
                    "    tz              VARCHAR2(50) := P_TIME_ZONE;\n" +
                    "BEGIN\n" +
                    "    i := OAgetNumber(token, PropName, inArgTable, inArgTypeTable, dblVal, argCount);\n" +
                    "    retval := dblVal;\n" +
                    "    RETURN i;\n" +
                    "END GetProperty;";
}
