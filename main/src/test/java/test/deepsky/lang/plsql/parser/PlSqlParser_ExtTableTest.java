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

package test.deepsky.lang.plsql.parser;

import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.PsiUtil;
import com.intellij.lang.ASTNode;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.*;

public class PlSqlParser_ExtTableTest extends PlSqlParser_AbstractTest {

    String XDV_SINGTEL_SMS_ET = "XDV_SINGTEL_SMS_ET.SQL";
    String XDV_SINGTEL_WAP_ET = "XDV_SINGTEL_WAP_ET.SQL";

    String dp_departments_sql = "dp_departments.sql";
    String log_table_sql = "log_table.sql";
    String ext_tab_sql = "ext_tab.sql";
    String ext_tab3_sql = "ext_tab3.sql";
    String scott_et_sql = "scott.et.sql";
    String ext_write_sql = "ext_write.sql";
    String extempl_sql = "extempl.sql";
    String SYS_SQLLDR_X_EXT_EMPLOYEES_SQL = "SYS_SQLLDR_X_EXT_EMPLOYEES.SQL";
    String import_empl_info_sql = "import_empl_info.sql";
    String ext_tab2_sql = "ext_tab2.sql";
    String skip_tab_sql = "skip_tab.sql";
    String export_empl_info_sql = "export_empl_info.sql";
    String xtab_preproc = "xtab_preproc.sql";
    String all_objects_big_compressed_xt_sql = "all_objects_big_compressed_xt.sql";
    String deptXTec3_sql = "deptXTec3.sql";

    String XDV_ADS_APP_AGGR_ET = "XDV_ADS_APP_AGGR_ET.SQL";
    String XDV_SINGTEL_AMA_CS_ET = "XDV_SINGTEL_AMA_CS_ET.SQL";


    public PlSqlParser_ExtTableTest() {
        super("exttab_definitions");
    }


    public void test_XDV_ADS_APP_AGGR_ET() throws IOException {
        ASTNode root = parseScript(XDV_ADS_APP_AGGR_ET);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(root);
        assertEquals(6, elems.length);
        assertTrue(elems[5].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[5].getPsi();
        assertEquals("XDV_ADS_APP_AGGR_ET", t.getTableName());
        assertEquals(30, t.getColumnDefs().length);
    }

    public void test_XDV_SINGTEL_AMA_CS_ET() throws IOException {
        ASTNode root = parseScript(XDV_SINGTEL_AMA_CS_ET);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(3, elems.length);
        assertTrue(elems[1].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[1].getPsi();
        assertEquals("XDV_SINGTEL_AMA_CS_ET", t.getTableName());
        assertEquals(102, t.getColumnDefs().length);
    }

    public void test_deptXTec3() throws IOException {
        ASTNode root = parseScript(deptXTec3_sql);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("deptXTec3", t.getTableName());
        assertEquals(0, t.getColumnDefs().length);
    }

    public void test_all_objects_big_compressed_xt_sql() throws IOException {
        ASTNode root = parseScript(all_objects_big_compressed_xt_sql);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("all_objects_big_compressed_xt", t.getTableName());
        assertEquals(0, t.getColumnDefs().length);
    }

    public void test_xtab_preproc() throws IOException {
        ASTNode root = parseScript(xtab_preproc);
        ASTNode[] elems = root.getChildren(null);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("xtab", t.getTableName());
        assertEquals(1, t.getColumnDefs().length);
    }

    public void test_skip_tab_sql() throws IOException {
        ASTNode root = parseScript(skip_tab_sql);
        ASTNode[] elems = root.getChildren(null);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("skip_tab", t.getTableName());
        assertEquals(4, t.getColumnDefs().length);
    }


    public void test_export_empl_info_sql() throws IOException {
        ASTNode root = parseScript(export_empl_info_sql);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("export_empl_info", t.getTableName());
        assertEquals(0, t.getColumnDefs().length);
    }

    public void test_extempl_sql() throws IOException {
        ASTNode root = parseScript(extempl_sql);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("extemp1", t.getTableName());
        assertEquals(8, t.getColumnDefs().length);
    }

    public void test_SYS_SQLLDR_X_EXT_EMPLOYEES_SQL() throws IOException {
        ASTNode root = parseScript(SYS_SQLLDR_X_EXT_EMPLOYEES_SQL);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("SYS_SQLLDR_X_EXT_EMPLOYEES", t.getTableName());
        assertEquals(11, t.getColumnDefs().length);
    }

    public void test_import_empl_info_sql() throws IOException {
        ASTNode root = parseScript(import_empl_info_sql);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("import_empl_info", t.getTableName());
        assertEquals(5, t.getColumnDefs().length);
    }


    public void test_ext_tab2_sql() throws IOException {
        ASTNode root = parseScript(ext_tab2_sql);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("ext_tab2", t.getTableName());
        assertEquals(4, t.getColumnDefs().length);
    }

    public void test_log_table_sql() throws IOException {
        ASTNode root = parseScript(log_table_sql);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(2, elems.length);
        assertTrue(elems[1].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[1].getPsi();
        assertEquals("log_table", t.getTableName());
        assertEquals(1, t.getColumnDefs().length);
    }

    public void test_ext_tab_sql() throws IOException {
        ASTNode root = parseScript(ext_tab_sql);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("ext_tab", t.getTableName());
        assertEquals(4, t.getColumnDefs().length);
    }

    public void test_ext_tab3_sql() throws IOException {
        ASTNode root = parseScript(ext_tab3_sql);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(2, elems.length);
        assertTrue(elems[1].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[1].getPsi();
        assertEquals("ext_tab3", t.getTableName());
        assertEquals(4, t.getColumnDefs().length);
    }

    public void test_scott_et_sql() throws IOException {
        ASTNode root = parseScript(scott_et_sql);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("et", t.getTableName());
        assertEquals(2, t.getColumnDefs().length);
    }


    public void test_dp_departments_sql() throws IOException {
        ASTNode root = parseScript(dp_departments_sql);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("dp_departments", t.getTableName());
        assertEquals(0, t.getColumnDefs().length);
    }

    public void test_XDV_SINGTEL_SMS_ET() throws IOException {
        ASTNode root = parseScript(XDV_SINGTEL_SMS_ET);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("XDV_SINGTEL_SMS_ET", t.getTableName());
        assertEquals(30, t.getColumnDefs().length);
    }


    public void test_XDV_SINGTEL_WAP_ET() throws IOException {
        ASTNode root = parseScript(XDV_SINGTEL_WAP_ET);
        ASTNode[] elems = PsiUtil.visibleChildren(root).toArray(new ASTNode[0]);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0].getPsi() instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0].getPsi();
        assertEquals("XDV_SINGTEL_WAP_ET", t.getTableName());
        assertEquals(16, t.getColumnDefs().length);
    }


    // todo ----
//    public void test_ext_write_sql() throws FileNotFoundException {
//        Reader r = new FileReader(new File(base, ext_write_sql));
//        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);
//
//        assertNotNull(elems);
//        assertEquals(1, elems.length);
//        assertTrue(elems[0] instanceof TableDefinition);
//
//        TableDefinition t = (TableDefinition) elems[0];
//        assertEquals("ext_write", t.getTableName());
//        assertEquals(30, t.getColumnDefs().length);
//    }

}
