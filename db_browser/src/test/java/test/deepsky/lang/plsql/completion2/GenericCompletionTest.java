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

public class GenericCompletionTest  extends BaseCompletionTest {
    @Override
    public String getPath() {
        return "completion";
    }

    public void test_start() throws Exception {
        configureByText("<caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select","select",  "update");
    }

    public void test_start12() throws Exception {
        configureByText("select c<caret>");
        assertLookup(myItems, "count");
    }

    public void test_start13() throws Exception {
        configureByText("select count(*) <caret>");
        assertLookup(myItems, "from");
    }

    public void test_start131() throws Exception {
        configureByText("select col1 <caret>");
        assertLookup(myItems, "from");
    }

    public void test_start132() throws Exception {
        configureByText("select * <caret>");
        assertLookup(myItems, "from");
    }

    public void test_start133() throws Exception {
        configureByText("select count(*) <caret>");
        assertLookup(myItems, "from");
    }

    public void test_start14() throws Exception {
        configureByText("select id, num <caret>");
        assertLookup(myItems, "from");
    }

    public void test_start1() throws Exception {
        configureByText("select * from tab <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start2() throws Exception {
        configureByText("create table tab (a number) <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }


    public void test_start51() throws Exception {
        configureByText("select * from dual update tab set a = 7 where is = 1 <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update", "where");
    }

    public void test_start6() throws Exception {
        configureByText("delete from tab  <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update", "where");
    }

    public void test_start7() throws Exception {
        configureByText("delete from tab where id < 8 <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start8() throws Exception {
        configureByText("drop table tab <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start9() throws Exception {
        configureByText("drop view tab <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start10() throws Exception {
        configureByText("drop sequence tab <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start11() throws Exception {
        configureByText("drop index idx_1 <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start_1() throws Exception {
        configureByText("select * from tab; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start_2() throws Exception {
        configureByText("create table tab (a integer); <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start_3() throws Exception {
        configureByText("insert into tab values(1, 2); <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start_4() throws Exception {
        configureByText("update tab set a = 7; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start_5() throws Exception {
        configureByText("update tab set a = 7 where is = 1; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start_6() throws Exception {
        configureByText("delete from tab;  <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start_7() throws Exception {
        configureByText("delete from tab where id < 8; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start_8() throws Exception {
        configureByText("drop table tab; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start_9() throws Exception {
        configureByText("drop view tab; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start_10() throws Exception {
        configureByText("drop sequence tab; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    public void test_start_11() throws Exception {
        configureByText("drop index idx_1; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////         INSERT
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void test_insert_1() throws Exception {
        configureByText("insert <caret>");
        assertLookup(myItems, "into");
    }

    public void test_insert_2() throws Exception {
        configureByText("create table tab1 (id number); insert into <caret>");
        assertLookup(myItems, "tab1");
    }

    public void test_insert_3() throws Exception {
        configureByText("insert into tab1 <caret>");
        assertLookup(myItems, "values");
    }

    public void test_start3() throws Exception {
        configureByText("insert into tab values(1, 2) <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select","update");
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////         UPDATE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void test_update_1() throws Exception {
        configureByText("create table tab1 (id number);create table tab2 (id number, text varchar2(10));update <caret>");
        assertLookup(myItems, "tab1", "tab2");
    }

    public void test_update_2() throws Exception {
        configureByText("update tab1 <caret>");
        assertLookup(myItems, "set");
    }

    public void test_update_4() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); update tab1 set <caret>");
        assertLookup(myItems, "id", "text", "(");
    }

    public void test_update_5() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); update tab1 set id = 4 <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select", "update", "where");
    }

    public void test_update_6() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); update tab1 set id = 4 where <caret>");
        assertLookup(myItems, "id", "text", "exists");
    }

    public void test_update_7() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); update tab1 set (id, text) <caret>");
        assertLookup(myItems, "=");
    }

    public void test_update_8() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); update tab1 set (<caret>)");
        assertLookup(myItems, "id", "text");
    }

    public void test_update_9() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); update tab1 set (<caret>) = ");
        assertLookup(myItems, "id", "text");
    }

    public void test_update_10() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); update tab1 set (id, text) = (<caret>)");
        assertLookup(myItems, "select");
    }

    public void test_update_11() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); create table tab2 (text varchar2(10)) update <caret> set");
        assertLookup(myItems, "tab2", "tab1");
    }

    public void test_update_12() throws Exception {
        configureByText("update <caret> set create table tab1 (id number, text varchar2(10)); create table tab2 (text varchar2(10)) ");
        assertLookup(myItems, "tab2");
    }

    public void test_update_13() throws Exception {
        configureByText("update tab1 set id <caret> create table tab1 (id number, text varchar2(10)); create table tab2 (text varchar2(10)) ");
        assertLookup(myItems, "=");
    }

    public void test_update_14() throws Exception {
        configureByText("update tab1 set <caret> create table tab1 (id number, text varchar2(10)); create table tab2 (text varchar2(10)) ");
        assertLookup(myItems, "(", "id", "text");
    }

    public void test_update_15() throws Exception {
        configureByText("update tab set a = 7 <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select","update", "where");
    }

    public void test_update_151() throws Exception {
        configureByText("update tab set a = 7 <caret>;");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select","update", "where");
    }
    public void test_update_16() throws Exception {
        configureByText("update tab set a = 7 where is = 1 <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "select","update", "where");
    }

}
