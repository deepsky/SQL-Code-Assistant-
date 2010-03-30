package test.deepsky.lang.plsql.struct.parser;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.net.URISyntaxException;

import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.Procedure;

public class PlSqlASTParser_ProcedureTest extends TestCase {
    String proc1 = "proc1.sql";
    String audit = "audit.sql";
    String get_date_for_tz = "get_date_for_tz.sql";

    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("procedures").toURI());
    }

    public void test_proc1() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, proc1));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof Procedure);

        Procedure v = (Procedure)elems[0];

        assertEquals("proc1", v.getEName());
        // check return type
        assertEquals(0, v.getArguments().length);
    }

    public void test_audit() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, audit));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof Procedure);

        Procedure v = (Procedure)elems[0];

        assertEquals("audit", v.getEName());
        // check return type
        assertEquals(2, v.getArguments().length);
        assertEquals("p_message", v.getArguments()[0].getArgumentName());
        assertEquals("p_to_alert_log", v.getArguments()[1].getArgumentName());
    }

}
