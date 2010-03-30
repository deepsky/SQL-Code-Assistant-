package test.deepsky.lang.plsql.struct.parser;

import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URISyntaxException;

public class PlSqlASTParser_ViewTest extends TestCase {

    String XDV_FLT_FILTER_OPERATOR_V = "XDV_FLT_FILTER_OPERATOR_V.SQL";
    String XDV_PRD_INDEX_REF_DEF_V = "XDV_PRD_INDEX_REF_DEF_V.SQL";
    String XDV_PRD_LOG_V = "XDV_PRD_LOG_V.SQL";
    String XDV_SCH_JOB_ARGS_V = "XDV_SCH_JOB_ARGS_V.SQL";

    String ALL_OBJECTS = "ALL_OBJECTS.SQL";
    String DBA_WARNING_SETTINGS = "DBA_WARNING_SETTINGS.SQL";
    String DBA_VIEWS = "DBA_VIEWS.SQL";
    String DBA_WORKLOAD_CAPTURES = "DBA_WORKLOAD_CAPTURES.SQL";
    String DBA_WAITERS = "DBA_WAITERS.SQL";
    String DEFCALL = "DEFCALL.SQL";
    String STRADDLING_RS_OBJECTS = "STRADDLING_RS_OBJECTS.SQL";
    String _ALL_FILE_GROUP_TABLESPACES = "_ALL_FILE_GROUP_TABLESPACES.SQL";
    String xsl_alt_alert_v = "xsl_alt_alert_v.sql";

    String XSL_DVR_PARAM_V = "XSL_DVR_PARAM_V.SQL";

    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("view_definitions").toURI());
    }

    public void test_XSL_DVR_PARAM_V() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XSL_DVR_PARAM_V));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("XSL_DVR_PARAM_V", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(6, v.getColumnDefs().length);
    }

    public void test_xsl_alt_alert_v() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_alt_alert_v));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(14, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertTrue(elems[1] instanceof CreateView);
        assertTrue(elems[2] instanceof CreateView);
        assertTrue(elems[3] instanceof CreateView);
        assertTrue(elems[4] instanceof CreateView);
        assertTrue(elems[5] instanceof CreateView);
        assertTrue(elems[6] instanceof CreateView);
        assertTrue(elems[7] instanceof CreateView);
        assertTrue(elems[8] instanceof CreateView);
        assertTrue(elems[9] instanceof CreateView);
        assertTrue(elems[10] instanceof CreateView);
        assertTrue(elems[11] instanceof CreateView);
        assertTrue(elems[12] instanceof CreateView);
        assertTrue(elems[13] instanceof CreateView);

        assertEquals("xsl_alt_alert_v", ((CreateView) elems[0]).getViewName());
        assertEquals("xsl_alt_alert_opt_fld_v", ((CreateView) elems[1]).getViewName());
        assertEquals("XSL_SG_ALERTS_BY_FQN_V", ((CreateView) elems[2]).getViewName());
        assertEquals("XSL_SG_CRITICAL_ALERTS_V", ((CreateView) elems[3]).getViewName());
        assertEquals("XSL_SG_MAJOR_ALERTS_V", ((CreateView) elems[4]).getViewName());
        assertEquals("XSL_SG_MINOR_ALERTS_V", ((CreateView) elems[5]).getViewName());
        assertEquals("XSL_SG_WARNING_ALERTS_V", ((CreateView) elems[6]).getViewName());
        assertEquals("XSL_SG_ALERT_INFO_V", ((CreateView) elems[7]).getViewName());
        assertEquals("XSL_SG_IDS_WITH_ALERTS_V", ((CreateView) elems[8]).getViewName());
        assertEquals("XSL_SG_FQN_V", ((CreateView) elems[9]).getViewName());
        assertEquals("XSL_ALM_ALERT_ENCODED_V", ((CreateView) elems[10]).getViewName());
        assertEquals("XSL_ALA_ALERT_ENCODED_AGGR_V", ((CreateView) elems[11]).getViewName());
        assertEquals("XSL_AMA_ALERT_MON_AGGR_V", ((CreateView) elems[12]).getViewName());
        assertEquals("XSL_ASM_SUR_FOR_MAP_V", ((CreateView) elems[13]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(68, v.getColumnDefs().length);
    }

    public void test__ALL_FILE_GROUP_TABLESPACES() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, _ALL_FILE_GROUP_TABLESPACES));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("\"_ALL_FILE_GROUP_TABLESPACES\"", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(6, v.getColumnDefs().length);
    }

    public void test_STRADDLING_RS_OBJECTS() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, STRADDLING_RS_OBJECTS));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("STRADDLING_RS_OBJECTS", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(9, v.getColumnDefs().length);
    }

    public void test_DEFCALL() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, DEFCALL));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("DEFCALL", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(6, v.getColumnDefs().length);
    }

    public void test_DBA_WAITERS() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, DBA_WAITERS));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("DBA_WAITERS", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(7, v.getColumnDefs().length);
    }

    public void test_DBA_WORKLOAD_CAPTURES() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, DBA_WORKLOAD_CAPTURES));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("DBA_WORKLOAD_CAPTURES", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(35, v.getColumnDefs().length);
    }

    public void test_DBA_VIEWS() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, DBA_VIEWS));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("DBA_VIEWS", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(11, v.getColumnDefs().length);
    }

    public void test_DBA_WARNING_SETTINGS() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, DBA_WARNING_SETTINGS));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("DBA_WARNING_SETTINGS", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(6, v.getColumnDefs().length);
    }

    public void test_ALL_OBJECTS() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, ALL_OBJECTS));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("ALL_OBJECTS", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(13, v.getColumnDefs().length);
    }

    public void test_XDV_FLT_FILTER_OPERATOR_V() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_FLT_FILTER_OPERATOR_V));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("XDV_FLT_FILTER_OPERATOR_V", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(9, v.getColumnDefs().length);
    }

    public void test_XDV_PRD_INDEX_REF_DEF_V() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_PRD_INDEX_REF_DEF_V));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("XDV_PRD_INDEX_REF_DEF_V", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(9, v.getColumnDefs().length);
    }


    public void test_XDV_PRD_LOG_V() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_PRD_LOG_V));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("XDV_PRD_LOG_V", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(6, v.getColumnDefs().length);
    }


    public void test_XDV_SCH_JOB_ARGS_V() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_SCH_JOB_ARGS_V));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof CreateView);
        assertEquals("XDV_SCH_JOB_ARGS_V", ((CreateView) elems[0]).getViewName());

        CreateView v = (CreateView) elems[0];
        assertEquals(9, v.getColumnDefs().length);
    }
}
