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


public class GenericCompletionTest extends BaseCompletionTest {

    @Override
    public String getPath() {
        return "completion";
    }

    public void test_start() throws Exception {
        configureByText("<caret>");
        assertLookup(myItems, "declare", "alter", "begin", "comment", "create", "delete", "drop", "insert", "select","update");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////// SELECT
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void test_start12() throws Exception {
        configureByText("select c<caret>");
        assertLookup(myItems, "count", "cast", "chr", "case");
    }

    public void test_start13() throws Exception {
        configureByText("select count(*) <caret>");
        assertLookup(myItems, "from");
    }

    public void test_select_start() throws Exception {
        configureByText("select my_func() <caret>");
        assertLookup(myItems, "from");
    }

    public void test_select_start1() throws Exception {
        configureByText("select my_func(id, name) <caret>");
        assertLookup(myItems, "from");
    }

    public void test_select_start2() throws Exception {
        configureByText("select shift_timestamp(TIMESTAMP '2005-09-11 01:50:42')\n" +
                ", TIMESTAMP '2005-09-11 01:50:42' <caret>");
        assertLookup(myItems, "from");
    }

    public void test_select_start3() throws Exception {
        configureByText("select sys<caret>");
        assertLookup(myItems, "sysdate", "systimestamp", "sys_extract_utc");
    }

    public void test_select_start31() throws Exception {
        configureByText("select 'Hello' || sys<caret>");
        assertLookup(myItems, "sysdate", "systimestamp", "sys_extract_utc");
    }

    public void test_select_start32() throws Exception {
        configureByText("select (1-2/98) + to_n<caret>");
        assertLookup(myItems, "to_number", "to_number");
    }

    public void test_select_start4() throws Exception {
        configureByText("select ses<caret>");
        assertLookup(myItems, "sessiontimezone");
    }

    public void test_select_start5() throws Exception {
        configureByText("select to_time<caret>");
        assertLookup(myItems, "to_timestamp_tz", "to_timestamp");
    }

    public void test_select_start6() throws Exception {
        configureByText("select sysdate, to_time<caret>");
        assertLookup(myItems, "to_timestamp_tz", "to_timestamp");
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
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop",
                "insert", "select", "update", "order", "group", "where",
                "full join", "left join", "right join", "inner join");
    }

    public void test_start2() throws Exception {
        configureByText("create table tab (a number) <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }


    public void test_start51() throws Exception {
        configureByText("select * from dual update tab set a = 7 where is = 1 <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update", "where");
    }


    public void test_select_1() throws Exception {
        configureByText("select * from dual  \n <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert",
                "select", "update", "where", "order", "group",
                "full join", "left join", "right join", "inner join");
    }

    public void test_select_11() throws Exception {
        configureByText("create sequence seq1; select * from dual  \n <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert",
                "select", "update", "where", "order", "group",
                "full join", "left join", "right join", "inner join");
    }

    public void test_select_12() throws Exception {
        configureByText("create sequence seq1; select * from dual where \n <caret>");
        assertLookupFilterOutFunc(myItems, "exists");
    }

    public void test_select_2() throws Exception {
        configureByText("create table tab (a integer); select * from tab where <caret>");
        assertLookupFilterOutFunc(myItems, "a", "exists");
    }

    public void test_select_21() throws Exception {
        configureByText("create table tab (a integer, b varchar2(1)); select * from tab where a = 1 or <caret>");
        assertLookup(myItems, "a", "b", "exists", "current_timestamp", "dbtimezone", "sysdate", "systimestamp");
    }

    public void test_select_22() throws Exception {
        configureByText("create table tab (a integer, b varchar2(1)); select * from tab where (a = 1) or <caret>");
        assertLookup(myItems, "a", "b", "exists", "current_timestamp", "dbtimezone", "sysdate", "systimestamp");
    }

    public void test_select_23() throws Exception {
        configureByText("create table tab (a integer, b varchar2(1)); select * from tab where (a = 1) or <caret>");
        assertLookup(myItems, "a", "b", "exists", "current_timestamp", "dbtimezone", "sysdate", "systimestamp");
    }

    public void test_select_24() throws Exception {
        configureByText("create table tab (a integer, b varchar2(1)); select * from tab where b like '%2' or <caret>");
        assertLookup(myItems, "a", "b", "exists", "current_timestamp", "dbtimezone", "sysdate", "systimestamp");
    }

    public void test_select_3() throws Exception {
        configureByText("create table tab (a integer); select * from tab where a <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update", "order", "group", "or", "and", "like");
    }

    public void test_select_4() throws Exception {
        configureByText("create table tab (a integer); select * from tab order <caret>");
        assertLookup(myItems, "by");
    }

    public void test_select_41() throws Exception {
        configureByText("create table tab (a integer); select * from tab order by <caret>");
        assertLookupFilterOutFunc(myItems, "a");
    }

    public void test_select_42() throws Exception {
        configureByText("create table tab (a integer); select * from tab where to_custom(<caret>)");
        assertLookupFilterOutFunc(myItems, "a", "current_timestamp","dbtimezone","sysdate","systimestamp");
    }

    public void test_select_43() throws Exception {
        configureByText("create table tab (a integer); select * from tab where to_<caret>");
        assertLookup(myItems, "to_number", "to_number", "to_timestamp", "to_timestamp_tz", "to_char", "to_char", "to_date","to_date","to_dsinterval");
    }

    public void test_select_44() throws Exception {
        configureByText("create table tab (a integer); select * from tab where to_custom(sub<caret>)");
        assertLookup(myItems, "substr",  "substr");
    }

    public void test_select_45() throws Exception {
        configureByText("create table tab (a integer); select * from tab where to_custom(substr(<caret>))");
        assertLookupFilterOutFunc(myItems, "a", "current_timestamp","dbtimezone","sysdate","systimestamp");
    }

    public void test_select_46() throws Exception {
        configureByText("create table tab (a integer); select * from tab where to_custom('1234' || substr(<caret>))");
        assertLookupFilterOutFunc(myItems, "a","current_timestamp","dbtimezone","sysdate","systimestamp");
    }


    public void test_select_5() throws Exception {
        configureByText("create table tab (a integer); select * from tab group <caret>");
        assertLookup(myItems, "by");
    }

    public void test_select_51() throws Exception {
        configureByText("create table tab (a integer); select * from tab group by <caret>");
        assertLookup(myItems, "a");
    }

    public void test_select_6() throws Exception {
        configureByText("create table tab (a integer); select * from tab where exists <caret>");
        assertLookup(myItems, "(select");
    }

    public void test_select_61() throws Exception {
        configureByText("create table tab (a integer); select * from (select * from tab where exists <caret>)");
        assertLookup(myItems, "(select");
    }

    public void test_select_7() throws Exception {
        configureByText("select *\n" +
                "    from a101, (select * from tab1) a\n" +
                " <caret>;");
        assertLookup(myItems, "where", "order", "group", "full join", "inner join", "left join", "right join");
    }

    public void test_select_71() throws Exception {
        configureByText("select *\n" +
                "    from a101, (select * from tab1) a\n" +
                " group by id <caret>; )");
        assertLookup(myItems, "order", "having");
    }

    public void test_select_72() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30)); select *\n" +
                "    from tab1 e\n" +
                " group by e.a, <caret>; )");
        assertLookup(myItems, "text", "a");
    }

    public void test_select_73() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30)); select *\n" +
                "    from tab1 e\n" +
                " group by e.a, substr(<caret>); )");
        assertLookupFilterOutFunc(myItems, "text", "a");
    }

    public void test_select_74() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30)); select *\n" +
                "from tab1 where exists (select * from tab1 <caret>);\n");
        assertLookupFilterOutFunc(myItems, "where", "order", "group",
                "full join", "left join", "right join", "inner join");
    }

    public void test_select_75() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30)); select *\n" +
                "from tab1 where exists (select * from tab1 <caret> where 1<>2);\n");
        assertLookupFilterOutFunc(myItems, "full join", "left join", "right join", "inner join");
    }

    public void test_select_8() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30)); select *\n" +
                "    from tab1 e\n" +
                " group by e.a, to_char(<caret>); )");
        assertLookupFilterOutFunc(myItems, "text", "a");
    }


    public void test_select_9() throws Exception {
      //  CodeInsightSettings.getInstance().AUTOCOMPLETE_ON_CODE_COMPLETION = false;
        configureByText("create table tab1 (a integer, text varchar2(30)); select *\n" +
                "from bookingevents where channel li<caret>");
        assertLookupFilterOutFunc(myItems, "like");
    }

    public void test_select_91() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30)); select *\n" +
                "from bookingevents where channel = 0 o<caret>");
        assertLookupFilterOutFunc(myItems, "comment", "drop", "or", "order", "group");
    }

    public void test_select_92() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30)); select *\n" +
                "from bookingevents where (channel = 2) a<caret>");
        assertLookupFilterOutFunc(myItems, "alter", "and", "create", "update");
    }

    public void test_select_93() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30)); select *\n" +
                "from bookingevents where channel < 2 a<caret>");
        assertLookupFilterOutFunc(myItems, "alter", "and", "create", "update");
    }

    // TODO FIX ME
    public void _test_select_94() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30)); select *\n" +
                "from bookingevents where channel = 2 and (airline > 4 and event_date bet<caret>)" );
        assertLookupFilterOutFunc(myItems, "between");
    }

    public void test_select_A() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30)); select * <caret>\n" +
                "    tab1\n" +
                "where success = 0\n" +
                "order by eventdatetime desc");
        assertLookupFilterOutFunc(myItems, "from");
    }

    public void test_select_A1() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30)); select *\n" +
                "from tab1\n" +
                "<caret>\n" +
                "order by eventdatetime desc");
        assertLookupFilterOutFunc(myItems, "where", "group",  "full join","inner join","left join","right join");
    }

    public void test_select_A11() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30)); select *\n" +
                "from tab1\n" +
                "inner <caret>\n" +
                "order by eventdatetime desc");
        assertLookupFilterOutFunc(myItems, "inner join");
    }

    public void test_select_A2() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30), name varchar2(20)); select *\n" +
                "from tab1\n" +
                "<caret>\n" +
                "group by eventdatetime");
        assertLookupFilterOutFunc(myItems, "where", "full join","inner join","left join","right join");
    }

    public void test_select_B1() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30), name varchar2(20)); \n" +
                "select a\n" +
                "from tab1\n" +
                "group by a || <caret>");
        assertLookupFilterOutFunc(myItems, "a", "text", "name", "current_timestamp", "dbtimezone", "sysdate", "systimestamp");
    }

    public void test_select_B2() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30), name varchar2(20)); \n" +
                "select a\n" +
                "from tab1\n" +
                "group by a || text || <caret>");
        assertLookupFilterOutFunc(myItems, "a", "text", "name", "current_timestamp", "dbtimezone", "sysdate", "systimestamp");
    }

    public void test_select_B3() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30), name varchar2(20)); \n" +
                "select a\n" +
                "from tab1\n" +
                "group by a || text || to_char(<caret>)");
        assertLookupFilterOutFunc(myItems, "a", "text", "name");
    }


    public void test_select_B4() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30), name varchar2(20)); \n" +
                "select a\n" +
                "from tab1\n" +
                "group by a || text || substr(to_char(<caret>))");
        assertLookupFilterOutFunc(myItems, "a",  "text", "name");
    }

    public void test_select_B5() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30), name varchar2(20)); \n" +
                "select a || <caret>\n" +
                "from tab1\n" +
                "group by a || text || substr(to_char(a), 1)");
        assertLookupFilterOutFunc(myItems, "a",  "text", "name", "current_timestamp", "dbtimezone", "sysdate", "systimestamp");
    }

    public void test_select_B6() throws Exception {
        configureByText("create table tab1 (a integer, text varchar2(30), name varchar2(20)); \n" +
                "select a || substr(to_char(<caret>))\n" +
                "from tab1\n" +
                "group by a || text || substr(to_char(a), 1)");
        assertLookupFilterOutFunc(myItems, "a",  "text", "name", "current_timestamp", "dbtimezone", "sysdate", "systimestamp");
    }

    public void test_select_C1() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select *\n" +
                "from tab1\n" +
                "where event_date < sys<caret>");
        assertLookupFilterOutFunc(myItems, "sysdate",  "systimestamp");
    }

    public void test_select_inner_join() throws Exception {
        configureByText("select * from tab1 inner <caret>");
        assertLookupFilterOutFunc(myItems, "inner join");
    }

    public void test_select_left_join() throws Exception {
        configureByText("select * from tab1 left <caret>");
        assertLookupFilterOutFunc(myItems, "left join", "left outer join");
    }

    public void test_select_left_join2() throws Exception {
        configureByText("select * from tab1 left outer <caret>");
        assertLookupFilterOutFunc(myItems, "left outer join");
    }

    public void test_select_right_join() throws Exception {
        configureByText("select * from tab1 right <caret>");
        assertLookupFilterOutFunc(myItems, "right join", "right outer join");
    }

    public void test_select_right_join2() throws Exception {
        configureByText("select * from tab1 right outer <caret>");
        assertLookupFilterOutFunc(myItems, "join");
    }

    public void test_select_full_join() throws Exception {
        configureByText("select * from tab1 full <caret>");
        assertLookupFilterOutFunc(myItems, "full join", "full outer join");
    }

    public void test_select_full_join2() throws Exception {
        configureByText("select * from tab1 full outer <caret>");
        assertLookupFilterOutFunc(myItems, "full outer join");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////// CREATE TABLE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void test_create_table_1() throws Exception {
        configureByText("create table paymentevents_01_02 as\n" +
                "select * from paymentevents\n" +
                "<caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "group",
                "order", "insert", "select", "update", "where",
                "full join", "left join", "right join", "inner join");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////// DELETE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void test_delete1() throws Exception {
        configureByText("delete from tab  <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update", "where");
    }

    public void test_delete2() throws Exception {
        configureByText("delete from tab where id < 8 <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_delete3() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(3)); delete from <caret> where id < 8;");
        assertLookup(myItems, "tab1");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////// DROP
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void test_start8() throws Exception {
        configureByText("drop table tab <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start9() throws Exception {
        configureByText("drop view tab <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start10() throws Exception {
        configureByText("drop sequence tab <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start11() throws Exception {
        configureByText("drop index idx_1 <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_1() throws Exception {
        configureByText("select * from tab; <caret>");
        assertLookup(myItems, "declare", "alter", "begin", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_2() throws Exception {
        configureByText("create table tab (a integer); <caret>");
        assertLookup(myItems, "declare", "alter", "begin", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_3() throws Exception {
        configureByText("insert into tab values(1, 2); <caret>");
        assertLookup(myItems, "declare", "alter", "begin", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_31() throws Exception {
        configureByText("insert into tab (id, text) select * from tab <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert",
                "select", "update", "group", "order", "where",
                "full join", "left join", "right join", "inner join");
    }

    public void test_start_32() throws Exception {
        configureByText("insert into tab (id, text) select * from tab <caret>;");
        assertLookup(myItems, "where", "group", "order" ,
                "full join", "left join", "right join", "inner join");

    }

    public void test_start_33() throws Exception {
        configureByText("insert into tab (id, text) select * from tab where id < 2 <caret>;");
        assertLookup(myItems, "group", "order");
    }

    public void test_start_4() throws Exception {
        configureByText("update tab set a = 7; <caret>");
        assertLookup(myItems, "declare", "alter", "begin", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_5() throws Exception {
        configureByText("update tab set a = 7 where is = 1; <caret>");
        assertLookup(myItems, "declare", "alter", "begin", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_6() throws Exception {
        configureByText("delete from tab;  <caret>");
        assertLookup(myItems, "declare", "alter", "begin", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_7() throws Exception {
        configureByText("delete from tab where id < 8; <caret>");
        assertLookup(myItems, "declare", "alter", "begin", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_8() throws Exception {
        configureByText("drop table tab; <caret>");
        assertLookup(myItems, "declare", "alter", "begin", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_9() throws Exception {
        configureByText("drop view tab; <caret>");
        assertLookup(myItems, "declare", "alter", "begin", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_10() throws Exception {
        configureByText("drop sequence tab; <caret>");
        assertLookup(myItems, "declare", "alter", "begin", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void test_start_11() throws Exception {
        configureByText("drop index idx_1; <caret>");
        assertLookup(myItems, "declare", "alter", "begin", "comment", "create", "delete", "drop", "insert", "select", "update");
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
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select","update");
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
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select", "update", "where");
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

    public void test_update_91() throws Exception {
        configureByText("update tab1 set (<caret>) = ; create table tab1 (id number, text varchar2(10)); ");
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
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select","update", "where");
    }

    public void test_update_151() throws Exception {
        configureByText("update tab set a = 7 <caret>;");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select","update", "where");
    }
    public void test_update_16() throws Exception {
        configureByText("update tab set a = 7 where is = 1 <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select","update", "where");
    }

    public void test_update_3() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10));" +
                " create table aTab (id number, text varchar2(10)); update <caret> set id = 4;");
        assertLookup(myItems, "tab1", "atab");
    }

    public void test_update_0() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10));" +
                " create table aTab (id number, text varchar2(10)); update tab1  set id = <caret>;");
        assertLookup(myItems, "id", "text", "sysdate", "systimestamp", "current_timestamp", "dbtimezone");
    }

    public void test_update_01() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10));" +
                " create table aTab (id number, text varchar2(10)); update tab1  set id = <caret> + 1000;");
        assertLookup(myItems, "id", "text", "sysdate", "systimestamp", "current_timestamp", "dbtimezone");
    }

    public void test_update_011() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10));" +
                " create table aTab (id number, text varchar2(10)); update tab1  set id = substr(<caret>) + 1000;");
        assertLookup(myItems, "id", "text", "sysdate", "systimestamp", "current_timestamp", "dbtimezone");
    }

    public void test_update_02() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10));" +
                " create table aTab (id number, text varchar2(10)); update tab1  set id = <caret>");
        assertLookup(myItems, "id", "text", "sysdate", "systimestamp", "current_timestamp", "dbtimezone");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////         COMMENT
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void test_comment_1() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); comm<caret> ");
        assertLookup(myItems, "comment");
    }

    public void test_comment_11() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); comment <caret> ");
        assertLookup(myItems, "on column", "on table");
    }

    public void test_comment_12() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); comment on <caret> ");
        assertLookup(myItems, "column", "table");
    }

    public void test_comment_13() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); comment on col<caret> ");
        assertLookup(myItems, "column");
    }

    public void test_comment_131() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); comment on column <caret> ");
        assertLookup(myItems, "tab1");
    }

    public void test_comment_132() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); comment on column tab1.<caret> ");
        assertLookup(myItems, "id", "text");
    }

    public void test_comment_14() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); comment on tab<caret> ");
        assertLookup(myItems, "table");
    }

    public void test_comment_141() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); comment on table <caret> ");
        assertLookup(myItems, "tab1");
    }

    public void test_comment_16() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); " +
                "create table abc (id number, text3 varchar2(10)); " +
                "comment on table <caret> is '';");
        assertLookup(myItems, "tab1", "abc");
    }

    public void test_comment_17() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); " +
                "create table abc (id number, text3 varchar2(10)); " +
                "comment on column <caret> is '';");
        assertLookup(myItems, "tab1", "abc");
    }

    public void test_comment_171() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10)); " +
                "create table abc (id number, text3 varchar2(10)); " +
                "comment on column abc.<caret> is '';");
        assertLookup(myItems, "id", "text3");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////         CREATE TABLE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void test_create_table_as_select() throws Exception {
        configureByText("create table TAB123\n" +
                "as select *\n" +
                "   from TAB\n" +
                "  where id <20000000 <caret> ");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select","update");
    }

    public void test_create_table_as_select1() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10));" +
                "create table TAB123\n" +
                "as select *\n" +
                "   from TAB1\n" +
                "  where id <20000000 or <caret> ");
        assertLookupFilterOutFunc(myItems, "id", "text", "exists", "current_timestamp", "dbtimezone", "sysdate", "systimestamp");
    }

    public void test_create_table_as_select2() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10));" +
                "create table TAB123\n" +
                "as select *\n" +
                "   from TAB1\n" +
                "  where id <20000000 and <caret> ");
        assertLookupFilterOutFunc(myItems, "id", "text", "exists", "current_timestamp", "dbtimezone", "sysdate", "systimestamp");
    }

    public void test_complete_after_select_from_subquery() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10));" +
                "select * from (select * from a1) <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop", "insert", "select","update");
    }

    public void test_complete_after_select_from_subquery2() throws Exception {
        configureByText("create table tab1 (id number, text varchar2(10));" +
                "select * from (select * from a1) q1 <caret>");
        assertLookup(myItems, "alter", "comment", "create", "delete", "drop",
                "insert", "select", "update", "order", "group", "where",
                "full join", "left join", "right join", "inner join");
    }
}


