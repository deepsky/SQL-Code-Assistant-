package test.deepsky.lang.plsql.struct.parser;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.net.URISyntaxException;

import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.psi.*;

public class PlSqlASTParser_PlSqlTest extends TestCase {

    String xsl_alt_alert_t_pkg_pks = "xsl_alt_alert_t_pkg.pks";
    String xsl_alt_alert_t_pkg_pkb = "xsl_alt_alert_t_pkg.pkb";
    String xsl_purge_pkg_pkb = "xsl_purge_pkg.pkb";
    String xsl_purge_pkg_pks = "xsl_purge_pkg.pks";
    String xsl_utl_lock_pkg_pks = "xsl_utl_lock_pkg.pks";
    String xsl_utl_lock_pkg_pkb = "xsl_utl_lock_pkg.pkb";
    String xsl_pdc_pkg_pks = "xsl_pdc_pkg.pks";
    String xsl_pdc_pkg_pkb = "xsl_pdc_pkg.pkb";
    String xsl_utilities_pkg_pks = "xsl_utilities_pkg.pks";
    String xsl_utilities_pkg_pkb = "xsl_utilities_pkg.pkb";
    String xtl_pdc_pkg_pks = "xtl_pdc_pkg.pks";
    String xtl_pdc_pkg_pkb = "xtl_pdc_pkg.pkb";
    String xsl_rdp_queue_pkg_pkb = "xsl_rdp_queue_pkg.pkb";
    String xsl_rdp_queue_pkg_pks = "xsl_rdp_queue_pkg.pks";
    String xsl_aq_utilities_pkg_pkb = "xsl_aq_utilities_pkg.pkb";
    String xsl_aq_utilities_pkg_pks = "xsl_aq_utilities_pkg.pks";
    String xsl_interval_pkg_pks = "xsl_interval_pkg.pks";
    String xsl_interval_pkg_pkb = "xsl_interval_pkg.pkb";
    String xsl_pdp_pkg_pks = "xsl_pdp_pkg.pks";
    String xsl_pdp_pkg_pkb = "xsl_pdp_pkg.pkb";

    String xdv_query_builder_pkg = "xdv_query_builder_pkg.pks";

    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("plsql_pkg").toURI());
    }

    public void test_xdv_query_builder_pkg() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_query_builder_pkg));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageSpec);
        assertEquals("xdv_query_builder_pkg", ((PackageSpec) elems[0]).getPackageName());

        PackageSpec pspec = (PackageSpec) elems[0];
        PlSqlElement[] dbo = pspec.findObjectByName("ta_load_id");
        assertEquals(1, dbo.length);
        assertTrue(dbo[0] instanceof TableCollectionDecl);

        PlSqlElement[] dbo1 = pspec.findObjectByName("r_window");
        assertEquals(1, dbo1.length);
        assertTrue(dbo1[0] instanceof RecordTypeDecl);


    }

    public void test_xsl_pdp_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_pdp_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageSpec);
        assertEquals("xsl_pdp_pkg", ((PackageSpec) elems[0]).getPackageName());
    }

    public void test_xsl_pdp_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_pdp_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof TableCollectionDecl);
        assertEquals("xsl_alt_alert_t_pkg", ((TableCollectionDecl) elems[0]).getDeclName());
    }


    public void test_xsl_aq_utilities_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_aq_utilities_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageBody);
        assertEquals("xsl_aq_utilities_pkg", ((PackageBody) elems[0]).getPackageName());
    }

    public void test_xsl_aq_utilities_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_aq_utilities_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageSpec);
        assertEquals("xsl_aq_utilities_pkg", ((PackageSpec) elems[0]).getPackageName());
    }

    public void test_xsl_interval_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_interval_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageSpec);
        assertEquals("xsl_interval_pkg", ((PackageSpec) elems[0]).getPackageName());
    }

    public void test_xsl_interval_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_interval_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageBody);
        assertEquals("xsl_interval_pkg", ((PackageBody) elems[0]).getPackageName());
    }




    public void test_xtl_pdc_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xtl_pdc_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageSpec);
        assertEquals("xtl_pdc_pkg", ((PackageSpec) elems[0]).getPackageName());
    }

    public void test_xtl_pdc_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xtl_pdc_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageBody);
        assertEquals("xtl_pdc_pkg", ((PackageBody) elems[0]).getPackageName());
    }

    public void test_xsl_rdp_queue_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_rdp_queue_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageBody);
        assertEquals("xsl_rdp_queue_pkg", ((PackageBody) elems[0]).getPackageName());
    }

    public void test_xsl_rdp_queue_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_rdp_queue_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageSpec);
        assertEquals("xsl_rdp_queue_pkg", ((PackageSpec) elems[0]).getPackageName());
    }

    public void test_xsl_pdc_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_pdc_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageSpec);
        assertEquals("xsl_pdc_pkg", ((PackageSpec) elems[0]).getPackageName());
    }

    public void test_xsl_pdc_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_pdc_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageBody);
        assertEquals("xsl_pdc_pkg", ((PackageBody) elems[0]).getPackageName());
    }

    public void test_xsl_utilities_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_utilities_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageSpec);
        assertEquals("xsl_utilities_pkg", ((PackageSpec) elems[0]).getPackageName());
    }

    public void test_xsl_utilities_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_utilities_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageBody);
        assertEquals("xsl_utilities_pkg", ((PackageBody) elems[0]).getPackageName());
    }


    public void test_xsl_alt_alert_t_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_alt_alert_t_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageSpec);
        assertEquals("xsl_alt_alert_t_pkg", ((PackageSpec) elems[0]).getPackageName());
    }

    public void test_xsl_alt_alert_t_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_alt_alert_t_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageBody);
        assertEquals("xsl_alt_alert_t_pkg", ((PackageBody) elems[0]).getPackageName().toLowerCase());
    }


    public void test_xsl_purge_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_purge_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageBody);
        assertEquals("xsl_purge_pkg", ((PackageBody) elems[0]).getPackageName());
    }

    public void test_xsl_purge_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_purge_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageSpec);
        assertEquals("xsl_purge_pkg", ((PackageSpec) elems[0]).getPackageName());
    }


    public void test_xsl_utl_lock_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_utl_lock_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageSpec);
        assertEquals("xsl_utl_lock_pkg", ((PackageSpec) elems[0]).getPackageName().toLowerCase());
    }


    public void test_xsl_utl_lock_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_utl_lock_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof PackageBody);
        assertEquals("xsl_utl_lock_pkg", ((PackageBody) elems[0]).getPackageName().toLowerCase());
    }
}
