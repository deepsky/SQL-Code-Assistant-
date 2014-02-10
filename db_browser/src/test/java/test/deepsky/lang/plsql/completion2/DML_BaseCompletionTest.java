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

public class DML_BaseCompletionTest extends BaseCompletionTest {
    @Override
    public String getPath() {
        return "completion/base/dml";
    }

    // UPDATE
    public void testUpdate$update0() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "parent", "child");
    }

    public void testUpdate$update1() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text", "(");
    }

    public void testUpdate$update2() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text");
    }

    public void testUpdate$update3() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text", "cnt");
    }

    public void testUpdate$update01() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text", "(");
    }

    public void testUpdate$update02() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "set");
    }

    public void testUpdate$update11() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text");
    }

    public void testUpdate$update21() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text", "exists");
    }

    public void testUpdate$correlated_update() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "ticket_price", "venueno");
    }

    public void testUpdate$correlated_update2() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "events", "venues");
    }

    public void testUpdate$correlated_update3() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "ticket_price", "venueno");
    }

    public void testUpdate$correlated_update4() throws Exception {
        configureByFile(getFilePath());
//        assertSelectFieldLookup(myItems, "e.id", "e.ticket_price", "e.venueno", "venues.capacity", "venues.id", "venues.venueno");
        assertSelectFieldLookup(myItems, "capacity", "e.id", "e.ticket_price", "e.venueno", "id", "venueno");

    }

    public void testUpdate$correlated_update_with_subquery() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "a.deptno", "a.id", "a.sal", "b.deptno", "b.id", "b.sal");
    }


    // INSERT
    public void testInsert$insert0() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "emp", "child");
    }

    public void testInsert$insert01() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "into");
    }

    public void testInsert$insert1() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "deptno", "id", "sal");
    }

    public void testInsert$insert2() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "deptno", "id", "sal");
    }

    public void testInsert$insert3() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "deptno", "id", "sal");
    }

    public void testInsert$insert4() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "values", "select");
    }

    public void testInsert$trivial_insert() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "deptno", "id", "sal");
    }

    public void testInsert$trivial_insert1() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "e.deptno", "e.id", "e.sal");
    }


    // DELETE
    public void testDelete$correlated_subquery_delete() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "a.deptno", "a.id", "a.sal", "exists");
    }

    public void testDelete$delete0() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "parent", "child");
    }

    public void testDelete$delete1() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "exists", "p.id", "p.text");
    }

}
