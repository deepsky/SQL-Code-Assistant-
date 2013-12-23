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

import com.deepsky.view.Icons;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class PlSqlColorsAndFontsPage implements ColorSettingsPage {
    public Icon getIcon() {
        return Icons.SQL_FILE;
    }

    @NotNull
    public SyntaxHighlighter getHighlighter() {
        return new PlSqlHighlighterForCFPage();
    }

    @NotNull
    public String getDemoText() {
        return
                "-- SQLPlus script sample\n" +
                "\n" +
                "spool script_result.log\n" +
                "\n" +
                "create table emp (\n" +
                "   id number primary key,\n" +
                "   first_name varchar2(32) constraint first_name_nnl not null,\n" +
                "   last_name varchar2(32),\n" +
                "   position varchar2(32),\n" +
                "   dept varchar2(32)\n" +
                ");\n" +
                "\n" +
                "create sequence emp_seq start with 10000;\n" +
                "\n" +
                "insert into emp values (emp_seq.nextval, 'John', 'Doe', 'manager', 'IT');\n" +
                "insert into emp values (emp_seq.nextval, 'John', 'Smith', 'manager', 'Sales');\n" +
                "\n" +
                "-- Insert into the table which is not resolved\n" +
                "insert into employee values (emp_seq.nextval, 'John', 'Smith', 'manager', 'Sales');\n" +
                "\n" +
                "/*\n" +
                "   Calculate number of employees for the specified department \t\n" +
                "*/\n" +
                "create or replace function count_for (a_dept varchar2)\n" +
                "return number\n" +
                "is\n" +
                "   l_ret number(22) := -1;\n" +
                "begin\n" +
                "   select count(*) into l_ret\n" +
                "   from emp where dept = a_dept;\n" +
                "   \n" +
                "   return l_ret + calc_ext(1);\n" +
                "end;\n" +
                "/\n" +
                "\n" +
                "spool off\n" +
                "\n" +
                "exit" +
                "-- End of script\n" +
                "SELECT \n" +
                "    FIRST_NAME AS First, \n" +
                "    LAST_NAME AS last, \n" +
                "    DEPARTMENT_ID AS DepT\n" +
                "FROM EMPLOYEES;\n" +
                "/\n";
    }

    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return new HashMap<String, TextAttributesKey>();
    }

    @NotNull
    public AttributesDescriptor[] getAttributeDescriptors() {
        return ATTRS;
    }

    @NotNull
    public ColorDescriptor[] getColorDescriptors() {
        return new ColorDescriptor[0];
    }

    @NotNull
    public String getDisplayName() {
        return "PlSql";
    }

    private static final AttributesDescriptor[] ATTRS =
            new AttributesDescriptor[]{
                    new AttributesDescriptor("Line comment", DefaultHighlighter.LINE_COMMENT),
                    new AttributesDescriptor("Block comment", DefaultHighlighter.BLOCK_COMMENT),
                    new AttributesDescriptor("Keyword", DefaultHighlighter.KEYWORD),
                    new AttributesDescriptor("Number", DefaultHighlighter.NUMBER),
                    new AttributesDescriptor("Semicolon", DefaultHighlighter.SEMICOLON),
                    new AttributesDescriptor("String", DefaultHighlighter.STRING),
                    new AttributesDescriptor("Parentheses", DefaultHighlighter.PARENTHESES),
                    new AttributesDescriptor("Operation sign", DefaultHighlighter.OPERATION_SIGN),

                    new AttributesDescriptor("SQL function (Built-In)", DefaultHighlighter.BUILT_IN_FUNC),
                    new AttributesDescriptor("User defined function", DefaultHighlighter.UDF),
                    new AttributesDescriptor("Function/procedure not resolved", DefaultHighlighter.FUNC_REF_NOT_RESOLVED),
                    new AttributesDescriptor("SQLPlus command", DefaultHighlighter.SQLPLUS_CMD),
                    new AttributesDescriptor("User defined type", DefaultHighlighter.USER_DEFINED_TYPE),
                    new AttributesDescriptor("Data type", DefaultHighlighter.DATA_TYPE),
                    new AttributesDescriptor("PL/SQL Variable", DefaultHighlighter.PLSQL_VAR),
                    new AttributesDescriptor("PL/SQL Parameter", DefaultHighlighter.PLSQL_PARAMETER),
                    new AttributesDescriptor("Database object name", DefaultHighlighter.OBJECT_NAME),

                    new AttributesDescriptor("Table name", DefaultHighlighter.TABLE_NAME),
                    new AttributesDescriptor("View name", DefaultHighlighter.VIEW_NAME),
                    new AttributesDescriptor("Table name not resolved", DefaultHighlighter.TABLE_NAME_NOT_RESOLVED),
                    new AttributesDescriptor("Sequence name", DefaultHighlighter.SEQUENCE),
                    new AttributesDescriptor("Column name", DefaultHighlighter.COLUMN),
                    new AttributesDescriptor("Constraint name", DefaultHighlighter.CONSTRAINT_NAME),
                    new AttributesDescriptor("Column alias", DefaultHighlighter.ALIAS_IDENT),


/*
                    new AttributesDescriptor("Bad character", DefaultHighlighter.BAD_CHARACTER),
                    new AttributesDescriptor("Wrong string literal", DefaultHighlighter.WRONG_STRING),
                    new AttributesDescriptor("Unresolved reference access", DefaultHighlighter.UNRESOLVED_ACCESS),
                    new AttributesDescriptor("List/map to object conversion", DefaultHighlighter.LITERAL_CONVERSION),
                    new AttributesDescriptor("Annotation", DefaultHighlighter.ANNOTATION),
                    new AttributesDescriptor("Local variable", DefaultHighlighter.LOCAL_VARIABLE),
                    new AttributesDescriptor("Reassigned local variable", DefaultHighlighter.REASSIGNED_LOCAL_VARIABLE),
                    new AttributesDescriptor("Parameter", DefaultHighlighter.PARAMETER),
                    new AttributesDescriptor("Reassigned parameter", DefaultHighlighter.REASSIGNED_PARAMETER),
                    new AttributesDescriptor("Static field", DefaultHighlighter.STATIC_FIELD),
                    new AttributesDescriptor("Instance field", DefaultHighlighter.INSTANCE_FIELD),
                    new AttributesDescriptor("Instance method call", DefaultHighlighter.METHOD_CALL),
                    new AttributesDescriptor("Static method call", DefaultHighlighter.STATIC_METHOD_ACCESS),
                    new AttributesDescriptor("Method declaration", DefaultHighlighter.METHOD_DECLARATION),
                    new AttributesDescriptor("Class reference", DefaultHighlighter.CLASS_REFERENCE),
                    new AttributesDescriptor("Map key accessed as a property", DefaultHighlighter.MAP_KEY),
                    new AttributesDescriptor("Instance property reference", DefaultHighlighter.INSTANCE_PROPERTY_REFERENCE),
                    new AttributesDescriptor("Static property reference", DefaultHighlighter.STATIC_PROPERTY_REFERENCE),
                    new AttributesDescriptor("Valid string escape", DefaultHighlighter.VALID_STRING_ESCAPE),
                    new AttributesDescriptor("Invalid string escape", DefaultHighlighter.INVALID_STRING_ESCAPE),
*/
            };


/*
-- SQLPlus script sample

spool script_result.log

create table emp (
   id number primary key,
   first_name varchar2(32),
   last_name varchar2(32),
   position varchar2(32)
   dept varchar2(32)
);

create sequence emp_seq start with 10000;

insert into emp values (emp_seq.nextval, 'John', 'Dow', 'manager', 'IT');
insert into emp values (emp_seq.nextval, 'John', 'Smith', 'manager', 'Sales');

/ *
   Calculate number of employees for the specified department
* /
create or replace function count_for (a_dept varchar2)
    return number
            is
    l_ret number(22) := -1;
    begin
    select count(*) into l_ret
    from emp where dept = a_dept;

    return l_ret;
    end;
    /

    spool off

    exit
*/
}
