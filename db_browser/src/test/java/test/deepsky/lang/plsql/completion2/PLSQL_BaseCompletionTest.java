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

public class PLSQL_BaseCompletionTest  extends BaseCompletionTest {
    @Override
    public String getPath() {
        return "completion/base/plsql";
    }

    // UPDATE
    public void testUpdate$update_in_func0() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "parent", "child");
    }

    // VARIABLES
    public void testVariables$complete_var0() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "l_ret", "l_date");
    }

    public void testVariables$complete_var1() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "l_ret", "l_date", "a_date");
    }

    public void testVariables$complete_var_in_anonymous_blk() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "l_ret", "l_date");
    }

    public void testVariables$complete_var_in_anonymous_blk1() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "lsdf");
    }


    // SQL implicit cursor
    public void testPercentage$sql_cursor_attr() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "found", "notfound", "isopen", "rowcount", "bulk_rowcount");
    }

    public void testPercentage$anonymous_blk() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "bulk_rowcount");
    }

    public void testPercentage$rowtype() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "rowtype");
    }

    public void testPercentage$ref_cursor() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "rowtype", "type");
    }
}
