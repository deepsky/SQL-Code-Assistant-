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

public class Alter_BaseCompletionTest extends BaseCompletionTest {

    @Override
    public String getPath() {
        return "completion/base/";
    }

    // ALTER
    public void testAlter$alter_start() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "table", "index");
    }

    public void testAlter$alter_table() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "child", "parent");
    }

    public void testAlter$alter_table2() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "child", "parent");
    }

    public void testAlter$alter_table_drop() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "column", "primary key", "constraint", "unique");
    }

    public void testAlter$alter_table_drop2() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "column", "primary key", "constraint", "unique");
    }

    public void testAlter$alter_table_drop_column() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "parent_id", "text");
    }

    public void testAlter$alter_table_drop_column2() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "parent_id", "text");
    }

    public void testAlter$alter_table_drop_pk() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "key");
    }

    public void testAlter$alter_table_drop_pk2() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems, "cascade", "keep", "drop");
    }

    public void testAlter$alter_table_drop_pk3() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems,  "keep", "drop");
    }

    public void testAlter$alter_table_drop_pk4() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems,  "index");
    }

    public void testAlter$alter_table_drop_constraint() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems,  "ITEM_FOREIGN_KEY", "ITEM_PRIMARY_KEY");
    }

    public void testAlter$alter_table_drop_constraint2() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems,  "cascade");
    }

    public void testAlter$alter_table_drop_constraint3() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems,  "cascade", "alter", "comment", "create", "delete", "drop", "insert", "select", "update");
    }

    public void testAlter$alter_table_rename() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems,  "column", "constraint", "to");
    }

    public void testAlter$alter_table_rename_column() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems,  "parent_id", "text");
    }

    public void testAlter$alter_table_rename_constraint() throws Exception {
        configureByFile(getFilePath());
        assertSelectFieldLookup(myItems,  "item_foreign_key", "item_primary_key");
    }
}
