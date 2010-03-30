package test.deepsky.lang.plsql.struct.parser;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.net.URISyntaxException;

import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.ddl.CreateTriggerDML;

public class PlSqlASTParser_TriggerTest extends TestCase {

    String xdv_dev_grp_mobile_stat_ins_tr = "xdv_dev_grp_mobile_stat_ins_tr.sql";
    String xdv_dev_grp_mobile_station_tr = "xdv_dev_grp_mobile_station_tr.sql";
    String xdv_dev_ta2vm_map_tr = "xdv_dev_ta2vm_map_tr.sql";
    String xdv_net_facility_t = "xdv_net_facility_t.sql";
    String xdv_prd_cfg_after_tr = "xdv_prd_cfg_after_tr.sql";
    String xdv_srv_grp_server_ins_tr = "xdv_srv_grp_server_ins_tr.sql";
    String xdv_srv_grp_server_tr = "xdv_srv_grp_server_tr.sql";
    String xdv_sub_grp_network_ins_tr = "xdv_sub_grp_network_ins_tr.sql";
    String xdv_sub_grp_network_tr = "xdv_sub_grp_network_tr.sql";
    String xdv_sub_grp_subscriber_ins_tr = "xdv_sub_grp_subscriber_ins_tr.sql";
    String xdv_sub_grp_subscriber_tr = "xdv_sub_grp_subscriber_tr.sql";

    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("triggers").toURI());
    }

    public void test_xdv_prd_cfg_after_tr() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_prd_cfg_after_tr));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof CreateTriggerDML);
        assertEquals("xdv_prd_cfg_after_tr", ((CreateTriggerDML)elems[0]).getTriggerName().toLowerCase());
    }

    public void test_xdv_srv_grp_server_ins_tr() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_srv_grp_server_ins_tr));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof CreateTriggerDML);
        assertEquals("xdv_srv_grp_server_ins_tr", ((CreateTriggerDML)elems[0]).getTriggerName().toLowerCase());
    }

    public void test_xdv_srv_grp_server_tr() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_srv_grp_server_tr));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof CreateTriggerDML);
        assertEquals("xdv_srv_grp_server_tr", ((CreateTriggerDML)elems[0]).getTriggerName().toLowerCase());
    }

    public void test_xdv_sub_grp_network_ins_tr() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_sub_grp_network_ins_tr));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof CreateTriggerDML);
        assertEquals("xdv_sub_grp_network_ins_tr", ((CreateTriggerDML)elems[0]).getTriggerName().toLowerCase());
    }

    public void test_xdv_sub_grp_network_tr() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_sub_grp_network_tr));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof CreateTriggerDML);
        assertEquals("xdv_sub_grp_network_tr", ((CreateTriggerDML)elems[0]).getTriggerName().toLowerCase());
    }

    public void test_xdv_sub_grp_subscriber_ins_tr() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_sub_grp_subscriber_ins_tr));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof CreateTriggerDML);
        assertEquals("xdv_sub_grp_subscriber_ins_tr", ((CreateTriggerDML)elems[0]).getTriggerName().toLowerCase());
    }

    public void test_xdv_sub_grp_subscriber_tr() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_sub_grp_subscriber_tr));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof CreateTriggerDML);
        assertEquals("xdv_sub_grp_subscriber_tr", ((CreateTriggerDML)elems[0]).getTriggerName().toLowerCase());
    }


    public void test_xdv_dev_grp_mobile_stat_ins_tr() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_dev_grp_mobile_stat_ins_tr));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof CreateTriggerDML);
        assertEquals("xdv_dev_grp_mobile_stat_ins_tr", ((CreateTriggerDML)elems[0]).getTriggerName().toLowerCase());
    }


    public void test_xdv_dev_grp_mobile_station_tr() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_dev_grp_mobile_station_tr));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof CreateTriggerDML);
        assertEquals("xdv_dev_grp_mobile_station_tr", ((CreateTriggerDML)elems[0]).getTriggerName().toLowerCase());
    }

    public void test_xdv_dev_ta2vm_map_tr() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_dev_ta2vm_map_tr));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof CreateTriggerDML);
        assertEquals("xdv_dev_ta2vm_map_tr", ((CreateTriggerDML)elems[0]).getTriggerName().toLowerCase());
    }

    public void test_xdv_net_facility_t() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_net_facility_t));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof CreateTriggerDML);
        assertEquals("XDV_FACILITY_NAME_TR", ((CreateTriggerDML)elems[0]).getTriggerName().toUpperCase());
    }

}
