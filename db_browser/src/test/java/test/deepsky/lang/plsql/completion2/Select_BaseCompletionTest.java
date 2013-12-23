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

package test.deepsky.lang.plsql.completion2;


public class Select_BaseCompletionTest extends BaseCompletionTest {


    @Override
    public String getPath() {
        return "completion/base/";
    }


    // SELECT
    public void testSelect$select_field() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "text");
    }

    public void testSelect$select_field_dot() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "t1.id", "t1.text");
    }

    public void testSelect$where1() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "text");
    }

    public void testSelect$from_table() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "tab", "tba", "tbb");
    }

    public void testSelect$seq_in_select_field() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "text", "te_seq", "id");
    }

    public void testSelect$seq_in_select_field1() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "nextval", "curval");
    }

    public void testSelect$seq_in_where() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "text");
    }

    public void testSelect$exists() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "child", "parent");
    }

    public void testSelect$exists1() throws Exception {
        configureByFile(getFilePath());
//        assertSelectFieldLookup(myItems, "parent_id", "text", "id", "text");
        assertSelectFieldLookup(myItems, "parent_id", "child.text", "id", "parent.text");
    }

    public void testSelect$exists2() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "parent_id", "text");
    }

    public void testSelect$exists3() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "p.id", "p.text");
    }

    public void testSelect$exists3_1() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "p.parent_id", "p.text");
    }

    public void testSelect$exists4() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "child", "parent");
    }

    public void testSelect$exists4_1() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "c.parent_id", "c.reflect_id", "c.text");
    }

    public void testSelect$complex_relation() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "p.id", "text", "parent_id", "p.text1");
    }

    public void testSelect$complex_relation1() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "p.id", "p.text1");
    }

    public void testSelect$group_by() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "text1");
    }

    public void testSelect$group_by2() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "text1");
    }

    public void testSelect$group_by_func() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "text1");
    }

    public void testSelect$order_by() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "text1");
    }

    public void testSelect$order_by2() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text1", "cnt");
    }

    public void testSelect$order_by2_view() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text1", "cnt");
    }

    public void testSelect$order_by3() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text1", "cnt");
    }

    public void testSelect$group_order() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "cnt", "text1");
    }

    public void testSelect$group_order2() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "cnt", "year1");
    }

    public void testSelect$group_order2_view() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "cnt", "year1");
    }

    public void testSelect$case_when() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "text1");
    }

    public void testSelect$case_when2() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "text1");
    }

    public void testSelect$select_from() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "id1", "text2");
    }

    public void testSelect$select_from2() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "id1", "text2");
    }

    public void testSelect$nested_order() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "parent_id", "text1");
    }

    public void testSelect$subqueries_instead_columns() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "emp", "emp1");
    }

    public void testSelect$subqueries_instead_columns1() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "e.deptno", "e.id", "e.sal", "deptno1", "id1", "sal1");
    }

    public void testSelect$asterisk() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "parent_id", "text");
    }

    public void testSelect$asterisk_alias() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "s1.parent_id", "s1.text");
    }

    public void testSelect$asterisk_2L() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "parent_id", "text");
    }

    public void testSelect$asterisk_3L() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "parent_id", "text");
    }

    public void testSelect$asterisk_3L_two_subq() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "parent_id", "text", "id", "text1");
    }

    public void testSelect$asterisk_3L_two_subq2() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "txt");
    }

    public void testSelect$asterisk_3L_two_subq3() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "txt");
    }

    public void testSelect$asterisk_3L_two_subq4() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "txt", "a129", "hello");
    }

    // ANSI SELECT
    public void testSelect$ansi_select0() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "text");
    }

    public void testSelect$ansi_select1() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "parent.text", "parent_id", "child.text");
    }

    public void testSelect$ansi_select2() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "parent_id", "text");
    }

    public void testSelect$ansi_select3() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "p.id", "p.text", "parent_id", "child.text");
    }

    public void testSelect$ansi_select4() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "parent.text", "parent_id", "child.text");
    }

    public void testSelect$select_from_view0() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "text");
    }

    public void testSelect$select_from_view1() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "id", "text");
    }

    public void testSelect$select_from_view2() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "parent", "parent_v", "parent_v2");
//        assertSelectFieldLookup(myItems, "id", "text");
    }

    public void testSelect$select_from_view3() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "parent", "parent_v", "parent_v2");
//        assertSelectFieldLookup(myItems, "id", "text");
    }

    // CURSOR
    public void testSelect$cursor_in_anonymous_blk() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text");
    }

    public void testSelect$cursor_in_anonymous_blk1() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "first_name", "last_name");
    }

    public void testSelect$lead_lag() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text", "hiredate", "sal", "dept");
    }

    public void testSelect$lead_lag2() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text", "hiredate", "sal", "dept");
    }

    public void testSelect$lead_lag3() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text", "hiredate", "sal", "dept");
    }

    public void testSelect$lead_lag4() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text", "hiredate", "sal", "dept");
    }
}



