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

package com.deepsky.lang.plsql.formatter;

import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.integration.PlSqlFileChangeTrackerAbstract;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class PlSqlFormatterTest extends PlSqlFormatterTestCase {

    @Override
    protected String getBasePath() {
        return "formatter/";
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getCommonSettings().CLASS_BRACE_STYLE = CommonCodeStyleSettings.END_OF_LINE;
        getCommonSettings().METHOD_BRACE_STYLE = CommonCodeStyleSettings.END_OF_LINE;
        getCommonSettings().BRACE_STYLE = CommonCodeStyleSettings.END_OF_LINE;

        PluginKeys.PLSQLFILE_CHANGE_TRACKER.putData(new PlSqlFileChangeTrackerAbstract() {
            public void indexPlSqlFile(PsiFile plSqlFile) {
                // do nothing
            }
        }, getProject());
    }

    public void testComplex_view() throws URISyntaxException {
        getCustomSettings().COMMA_AFTER_SELECT_EXPR = 2;
        doTest();
    }

    public void testCreate_table_abc() throws URISyntaxException {
        doTest();
    }

    public void testCreate_table_abc1() throws URISyntaxException {
        doTest();
    }

    public void testFunc1() throws URISyntaxException {
        doTest();
    }

    public void testLong_datatypes() throws URISyntaxException {
        doTest();
    }

    public void testBlanklines_inside_select() {
        getCustomSettings().ALIGN_ALIAS_NAME_IN_SELECT = false;
        doTest();
    }

    public void testSelect_in_cursor() {
        doTest();
    }

    public void testSubquery_in_cursor() {
        doTest();
    }

    public void testSelect_expr() {
        doTest();
    }

    public void testSelect_expr2() {
        doTest();
    }

    public void testSelect_union() {
        doTest();
    }

    public void testSubqueries() {
        getCustomSettings().MAX_LINES_BETWEEN_FILE_LEVEL_STMT = 3;
        getCustomSettings().MIN_LINES_BETWEEN_FILE_LEVEL_STMT = 0;
        doTest();
    }

    public void testConditions() {
        doTest();
    }

    public void testInsert_into() {
        doTest();
    }

    public void testView1() {
        getCustomSettings().COMMA_AFTER_SELECT_EXPR = 1;
        doTest();
    }

    public void testView2() {
        getCustomSettings().COMMA_AFTER_SELECT_EXPR = 2;
        doTest();
    }

    public void testView3() {
        getCustomSettings().COMMA_AFTER_SELECT_EXPR = 3;
        doTest();
    }

    public void testView_1() {
        getCustomSettings().COMMA_AFTER_SELECT_EXPR = 1;
        doTest();
    }

    public void testView_2() {
        getCustomSettings().COMMA_AFTER_SELECT_EXPR = 2;
        doTest();
    }

    public void testView_3() {
        getCustomSettings().COMMA_AFTER_SELECT_EXPR = 3;
        doTest();
    }

    public void testCreate_tables() {
        getCustomSettings().MAX_LINES_BETWEEN_FILE_LEVEL_STMT = 3;
        getCustomSettings().MIN_LINES_BETWEEN_FILE_LEVEL_STMT = 1;
        getCustomSettings().ALIGNMENT_IN_COLUMN_DEF = 3;
        doTest();
    }

    public void testAlter_table() {
        getCustomSettings().ALIGNMENT_IN_COLUMN_DEF = 2;
        doTest();
    }

    public void testAlias_name_alignment() {
        doTest();
    }


    public void testNotnull_alignment() {
        getCustomSettings().ALIGNMENT_IN_COLUMN_DEF = 3;
        doTest();
    }


    public void testCreate_trigger() {
        doTest();
    }

    public void testTypes_in_decl() {
        doTest();
    }

    public void testSubquery_update() {
        doTest();
    }

    public void testIn_select() {
        doTest();
    }

    public void testConcat_in_plsql_blk() {
        doTest();
    }


    public void testIndent_in_where_clause() {
        doTest();
    }

    public void testCreate_object_type() {
        getCustomSettings().ALIGNMENT_IN_COLUMN_DEF = 2;
        doTest();
    }

    public void testPkg_var_types() {
        doTest();
    }

    public void testProcedure_body() {
        getCustomSettings().ALIGN_ASSIGNMENTS = true;
        doTest();
    }

    public void testComments_in_pkg() {
        doTest();
    }

    public void testComments_in_pkg_no_indent() {
        getCustomSettings().COMMENT_AT_FIRST_COLUMN = true;
        doTest();
    }

    public void testFk_pk_spacing() {
        doTest();
    }

    public void testPlsql_blk_exc() {
        doTest();
    }

    public void testAnsi_join_tab() {
        doTest();
    }

    public void testAlter_table_uppercase() {
        getCustomSettings().NAMES_CASE = 2;
        doTest();
    }

    public void testAlter_table_lowercase() {
        getCustomSettings().NAMES_CASE = 3;
        doTest();
    }

    public void testRename_table() {
        doTest();
    }

    public void testCaseOfNames_in_double_quotes() {
        getCustomSettings().NAMES_CASE = 2;
        doTest();
    }

    public void testGrant1() {
        getCustomSettings().NAMES_CASE = 3;
        getCustomSettings().KEYWORD_CASE = 2;

        doTest();
    }

    public void testRevoke1() {
        getCustomSettings().NAMES_CASE = 3;
        getCustomSettings().KEYWORD_CASE = 2;

        doTest();
    }


    public void testAlignment_in_arguments() {
        doTest();
    }

    public void testAlignment_in_arguments2() {
        doTest();
    }

    public void testAlignment_in_proc() {
        doTest();
    }



    // --- stuff to run test cases
    public void doTest() {
        final List<String> data = readInput(getTestDataPath2() + "/" + getTestName(true) + ".test");
        checkFormatting(data.get(0), StringUtil.trimEnd(data.get(1), "\n"));
    }

    public String getTestDataPath2() {
        URL url = this.getClass().getClassLoader().getResource("formatter/");
        if (url == null) {
            return new File("./").getPath();
        } else {
            try {
                return new File(url.toURI()).getPath();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static List<String> readInput(String filePath) {
        String content;
        try {
            content = StringUtil.convertLineSeparators(FileUtil.loadFile(new File(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(content);

        List<String> input = new ArrayList<String>();

        int separatorIndex;
        content = StringUtil.replace(content, "\r", ""); // for MACs

        // Adding input  before -----
        while ((separatorIndex = content.indexOf("-----")) >= 0) {
            input.add(content.substring(0, separatorIndex - 1));
            content = content.substring(separatorIndex);
            while (StringUtil.startsWithChar(content, '-')) {
                content = content.substring(1);
            }
            if (StringUtil.startsWithChar(content, '\n')) {
                content = content.substring(1);
            }
        }
        input.add(content);

        Assert.assertTrue("No data found in source file", input.size() > 0);
        Assert.assertNotNull("Test output points to null", input.size() > 1);

        return input;
    }

}
