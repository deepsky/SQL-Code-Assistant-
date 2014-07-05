/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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

package test.deepsky.lang.plsql.completion2;

public class PlSqlGenericCompletionTest extends BaseCompletionTest {

    @Override
    public String getPath() {
        return "completion";
    }

    public void test_start() throws Exception {
        configureByText("<caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select","update", "begin");
    }

    public void test_create() throws Exception {
        configureByText("create <caret>");
        assertLookup(myItems, "table", "trigger", "package", "package body");
    }

    public void test_start_in_package() throws Exception {
        configureByText("create or replace package my_pkg <caret> end;");
        assertLookup(myItems, "is", "as");
    }

    public void test_start_in_package_1() throws Exception {
        configureByText("create package my_pkg <caret> end;");
        assertLookup(myItems, "is", "as");
    }

    public void test_start_in_package2() throws Exception {
        configureByText("create or replace package my_pkg is <caret> end;");
        assertLookup(myItems, "procedure", "function");
    }

    public void test_start_in_package21() throws Exception {
        configureByText("create package my_pkg is <caret> end;");
        assertLookup(myItems, "procedure", "function");
    }

    public void test_start_in_package3() throws Exception {
        configureByText("create or replace package my_pkg as <caret> end;");
        assertLookup(myItems, "procedure", "function");
    }

    public void test_start_in_package_body() throws Exception {
        configureByText("create or replace package body my_pkg as <caret> end;");
        assertLookup(myItems, "procedure", "function");
    }

    public void test_start_in_package_body_1() throws Exception {
        configureByText("create package body my_pkg as <caret> end;");
        assertLookup(myItems, "procedure", "function");
    }

    public void test_start_in_package_body2() throws Exception {
        configureByText("create or replace package body my_pkg <caret> end;");
        assertLookup(myItems, "is", "as");
    }

    public void test_start_in_package_body21() throws Exception {
        configureByText("create package body my_pkg <caret> end;");
        assertLookup(myItems, "is", "as");
    }

    public void test_start_in_package_body3() throws Exception {
        configureByText("create or replace package body my_pkg is <caret> end;");
        assertLookup(myItems, "procedure", "function");
    }

    public void test_start_in_package_body31() throws Exception {
        configureByText("create or replace package body my_pkg as <caret> end;");
        assertLookup(myItems, "procedure", "function");
    }

    public void test_inside_block() throws Exception {
        configureByText("begin <caret> end;");
        assertLookup(myItems,
//                "for",  // FOR LOOP
//                "open", // OPEN CURSOR
//                "IF",   // IF statement
                "commit",       // COMMIT
                "rollback",     // ROLLBACK
                "begin",
                "delete",
                "insert",
                "update"
                );
    }

    public void test_start_begin() throws Exception {
        configureByText("begin <caret>");
        assertLookup(myItems, "end", "begin", "commit", "delete", "insert", "rollback", "update");
    }

    public void test_start_begin_with_declare() throws Exception {
        configureByText("declare x varchar2(10); begin <caret>");
        assertLookup(myItems, "end", "begin", "commit", "delete", "insert", "rollback", "update");
    }

    public void test_start_begin_nested0() throws Exception {
        configureByText("begin begin <caret> end");
        assertLookup(myItems, "begin", "commit", "delete", "insert", "rollback", "update");
    }

    public void test_start_begin_nested0_1() throws Exception {
        configureByText("begin begin select 1 into v1 from dual; <caret> end");
        assertLookup(myItems, "begin", "commit", "delete", "insert", "rollback", "update");
    }

    public void test_start_begin_nested0_2() throws Exception {
        configureByText("begin begin select 1 into v1 from dual; <caret> end; end");
        assertLookup(myItems, "begin", "commit", "delete", "insert", "rollback", "update");
    }

    public void test_start_begin_nested0_3() throws Exception {
        configureByText("begin begin select 1 into v1 from dual; <caret> end end");
        assertLookup(myItems, "begin", "commit", "delete", "insert", "rollback", "update");
    }

    public void test_start_begin_nested2() throws Exception {
        configureByText("begin begin <caret> end;");
        assertLookup(myItems, "begin", "commit", "delete", "insert", "rollback", "update");
    }

    public void test_start_begin_nested3() throws Exception {
        configureByText("begin begin select 1 into v1 from dual; <caret> end;");
        assertLookup(myItems, "begin", "commit", "delete", "insert", "rollback", "update");
    }

    public void test_start_begin_nested2_with_declare() throws Exception {
        configureByText("declare x varchar2(10); begin begin <caret> end;");
        assertLookup(myItems, "begin", "commit", "delete", "insert", "rollback", "update");
    }
    public void test_start_declare() throws Exception {
        configureByText("declare <caret>");
        assertEquals(0, myItems.length);
    }

    public void test_start_declare_var_type() throws Exception {
        configureByText("declare x <caret>");
        assertLookup(myItems,
                "blob", "char", "clob", "date", "integer", "number", "nvarchar2", "rowid", "timestamp", "varchar2", "cursor");
    }

    public void test_start_declare_var_type2() throws Exception {
        configureByText("declare x varchar2(10); x1 <caret>");
        assertLookup(myItems,
                "blob", "char", "clob", "date", "integer", "number", "nvarchar2", "rowid", "timestamp", "varchar2", "cursor");
    }

    public void test_start_declare_var_type3() throws Exception {
        configureByText("declare x varchar2(10); x1 vachar2(10):= 'a'; x2 <caret>");
        assertLookup(myItems,
                "blob", "char", "clob", "date", "integer", "number", "nvarchar2", "rowid", "timestamp", "varchar2", "cursor");
    }

    public void test_start_declare_begin() throws Exception {
        configureByText("declare x <caret> begin end;");
        assertLookup(myItems,
                "blob", "char", "clob", "date", "integer", "number", "nvarchar2", "rowid", "timestamp", "varchar2", "cursor");
    }

    public void test_start_declare_begin2() throws Exception {
        configureByText("declare x varchar2(10); x1 <caret> begin NULL; end;");
        assertLookup(myItems,
                "blob", "char", "clob", "date", "integer", "number", "nvarchar2", "rowid", "timestamp", "varchar2", "cursor");
    }

    public void test_stmt_inside_block_delete() throws Exception {
        configureByText("create table emp1 (\n" +
                "    id      number,\n" +
                "    deptno  number,\n" +
                "    sal     number\n" +
                "); declare x varchar2(10) begin delete <caret>; end;");
        assertLookup(myItems, "from", "emp1");
    }

    public void test_stmt_inside_block_delete_from() throws Exception {
        configureByText("create table emp (\n" +
                "    id      number,\n" +
                "    deptno  number,\n" +
                "    sal     number\n" +
                "); declare x varchar2(10) begin delete from <caret>; end;");
        assertLookup(myItems, "emp");
    }
}
