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

public class DDL_BaseCompletionTest  extends BaseCompletionTest {
    @Override
    public String getPath() {
        return "completion/base/ddl";
    }

    // CREATE TABLE
    public void testCreateTable$pk_constraint() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "country_id", "country_name", "currency_name", "currency_symbol", "region");
    }

    public void testCreateTable$composite_pk_constraint() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "currency_name", "currency_symbol", "region");
    }

    public void testCreateTable$partition_by_range() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "TAT_ID", "TAC_ID", "TRC_ID", "TRS_ID", "TRQ_ID", "DDE_ID", "DTE_ID");
    }

    public void testCreateTable$fk_constraint() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "TAT_ID", "TAC_ID", "TRC_ID", "TRS_ID", "TRQ_ID", "DDE_ID", "DTE_ID");
    }

    public void testCreateTable$composite_fk_constraint() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "TAT_ID", "TAC_ID", "TRC_ID", "TRS_ID", "TRQ_ID");
    }

    public void testCreateTable$fk_constraint_ref_tab() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "employees", "employees_temp", "emp_audit", "parent");
    }

    // Not applicable
    public void _testRenameTable$renameTable1() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "t1", "t2", "t3");
    }

    // Not applicable
    public void _testRenameTable$renameTable2() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "t1", "t2", "t3");
    }

    public void testRenameTable$renameTable3() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "t1", "t2", "t3");
    }

    public void testRenameTable$renameTable4() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "t1", "t2", "t3");
    }

    // TODO - implement me
    public void _testRenameTable$renameTable5() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "t2", "t3");
    }

    public void testRenameTable$renameTable6() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "t1", "t2", "t3");
    }

    // COMMENT
    public void testComments$comment_table0() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "parent", "child");
    }

    public void testComments$comment_column0() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "parent", "child");
    }

    public void testComments$comment_column1() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "id", "text");
    }


}
