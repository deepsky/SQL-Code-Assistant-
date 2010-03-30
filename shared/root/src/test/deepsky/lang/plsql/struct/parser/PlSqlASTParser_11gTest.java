package test.deepsky.lang.plsql.struct.parser;

import com.deepsky.lang.plsql.psi.PackageSpec;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URISyntaxException;


public class PlSqlASTParser_11gTest extends TestCase {

    String dbms_logmnr_pks = "dbms_logmnr.pks";
    String dbms_utility_pks = "dbms_utility.pks";
    String dbms_stats_pks = "dbms_stats.pks";
    String dbms_describe_pks = "dbms_describe.pks";
    String dbms_advisor_pks = "dbms_advisor.pks";

    String DBMS_CRYPTO_PKG = "DBMS_CRYPTO.PKS";

    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("11g").toURI());
    }

    public void test_DBMS_CRYPTO_PKG() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, DBMS_CRYPTO_PKG));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_advisor_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_advisor_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_logmnr_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_logmnr_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_utility_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_utility_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_stats_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_stats_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_describe_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_describe_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }
}
