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
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start1() throws Exception {
        configureByText("select * from tab <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start2() throws Exception {
        configureByText("create table tab (a number) <caret>");
        assertLookup(myItems, "rowtype", "type");
    }

    public void test_start3() throws Exception {
        configureByText("insert into tab values(1, 2) <caret>");
        assertLookup(myItems, "rowtype", "type");
    }

    public void test_start4() throws Exception {
        configureByText("update tab set a = 7 <caret>");
        assertLookup(myItems, "rowtype", "type");
    }

    public void test_start5() throws Exception {
        configureByText("update tab set a = 7 where is = 1 <caret>");
        assertLookup(myItems, "rowtype", "type");
    }

    public void test_start51() throws Exception {
        configureByText("select * from dual update tab set a = 7 where is = 1 <caret>");
        assertLookup(myItems, "rowtype", "type");
    }

    public void test_start6() throws Exception {
        configureByText("delete from tab  <caret>");
        assertLookup(myItems, "rowtype", "type");
    }

    public void test_start7() throws Exception {
        configureByText("delete from tab where id < 8 <caret>");
        assertLookup(myItems, "rowtype", "type");
    }

    public void test_start8() throws Exception {
        configureByText("drop table tab <caret>");
        assertLookup(myItems, "rowtype", "type");
    }

    public void test_start9() throws Exception {
        configureByText("drop view tab <caret>");
        assertLookup(myItems, "rowtype", "type");
    }

    public void test_start10() throws Exception {
        configureByText("drop sequence tab <caret>");
        assertLookup(myItems, "rowtype", "type");
    }

    public void test_start11() throws Exception {
        configureByText("drop index idx_1 <caret>");
        assertLookup(myItems, "rowtype", "type");
    }

    public void test_start_1() throws Exception {
        configureByText("select * from tab; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_2() throws Exception {
        configureByText("create tab (a int); <caret>");
        assertLookup(myItems, "rowtype", "type");
    }

    public void test_start_3() throws Exception {
        configureByText("insert into tab values(1, 2); <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_4() throws Exception {
        configureByText("update tab set a = 7; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_5() throws Exception {
        configureByText("update tab set a = 7 where is = 1; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_6() throws Exception {
        configureByText("delete from tab;  <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_7() throws Exception {
        configureByText("delete from tab where id < 8; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_8() throws Exception {
        configureByText("drop table tab; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_9() throws Exception {
        configureByText("drop view tab; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_10() throws Exception {
        configureByText("drop sequence tab; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_11() throws Exception {
        configureByText("drop index idx_1; <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }
}
