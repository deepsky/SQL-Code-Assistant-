package test.deepsky.lang.plsql.struct.parser;

import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.io.File;
import java.net.URISyntaxException;

import junit.framework.TestCase;

public class PlSqlASTParser_ExtTableTest extends TestCase {

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

    File base;

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("exttab_definitions").toURI());
    }

    public void test_XDV_ADS_APP_AGGR_ET() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_ADS_APP_AGGR_ET));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_ADS_APP_AGGR_ET", t.getTableName());
        assertEquals(30, t.getColumnDefs().length);
    }

    public void test_XDV_SINGTEL_AMA_CS_ET() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_SINGTEL_AMA_CS_ET));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_SINGTEL_AMA_CS_ET", t.getTableName());
        assertEquals(102, t.getColumnDefs().length);
    }

    public void test_deptXTec3() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, deptXTec3_sql));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("deptXTec3", t.getTableName());
        assertEquals(0, t.getColumnDefs().length);
    }

    public void test_all_objects_big_compressed_xt_sql() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, all_objects_big_compressed_xt_sql));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("all_objects_big_compressed_xt", t.getTableName());
        assertEquals(0, t.getColumnDefs().length);
    }

    public void test_xtab_preproc() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xtab_preproc));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("xtab", t.getTableName());
        assertEquals(1, t.getColumnDefs().length);
    }

    public void test_skip_tab_sql() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, skip_tab_sql));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("skip_tab", t.getTableName());
        assertEquals(4, t.getColumnDefs().length);
    }

    public void test_export_empl_info_sql() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, export_empl_info_sql));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("export_empl_info", t.getTableName());
        assertEquals(0, t.getColumnDefs().length);
    }

    public void test_extempl_sql() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, extempl_sql));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("extemp1", t.getTableName());
        assertEquals(8, t.getColumnDefs().length);
    }

    public void test_SYS_SQLLDR_X_EXT_EMPLOYEES_SQL() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, SYS_SQLLDR_X_EXT_EMPLOYEES_SQL));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("SYS_SQLLDR_X_EXT_EMPLOYEES", t.getTableName());
        assertEquals(11, t.getColumnDefs().length);
    }

    public void test_import_empl_info_sql() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, import_empl_info_sql));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("import_empl_info", t.getTableName());
        assertEquals(5, t.getColumnDefs().length);
    }

    public void test_ext_tab2_sql() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, ext_tab2_sql));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("ext_tab2", t.getTableName());
        assertEquals(4, t.getColumnDefs().length);
    }

    public void test_log_table_sql() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, log_table_sql));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("log_table", t.getTableName());
        assertEquals(1, t.getColumnDefs().length);
    }

    public void test_ext_tab_sql() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, ext_tab_sql));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("ext_tab", t.getTableName());
        assertEquals(4, t.getColumnDefs().length);
    }

    public void test_ext_tab3_sql() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, ext_tab3_sql));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("ext_tab3", t.getTableName());
        assertEquals(4, t.getColumnDefs().length);
    }

    public void test_scott_et_sql() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, scott_et_sql));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("et", t.getTableName());
        assertEquals(2, t.getColumnDefs().length);
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

    public void test_dp_departments_sql() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dp_departments_sql));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("dp_departments", t.getTableName());
        assertEquals(0, t.getColumnDefs().length);
    }

    public void test_XDV_SINGTEL_SMS_ET() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_SINGTEL_SMS_ET));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_SINGTEL_SMS_ET", t.getTableName());
        assertEquals(30, t.getColumnDefs().length);
    }


    public void test_XDV_SINGTEL_WAP_ET() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_SINGTEL_WAP_ET));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_SINGTEL_WAP_ET", t.getTableName());
        assertEquals(16, t.getColumnDefs().length);
    }

}
