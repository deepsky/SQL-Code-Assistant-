package test.deepsky.lang.plsql.struct.parser;

import com.deepsky.lang.plsql.psi.*;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.net.URISyntaxException;

import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;

public class PlSqlASTParser_ConditionTest extends TestCase {

    String select_from_dual2 = "select_from_dual2.sql";
    String select_from_dual3 = "select_from_dual3.sql";
    String select_from_dual4 = "select_from_dual4.sql";
    String select_from_dual5 = "select_from_dual5.sql";
    String select_from_dual6 = "select_from_dual6.sql";
    String select_from_dual7 = "select_from_dual7.sql";
    String select_from_dual8 = "select_from_dual8.sql";
    String select_from_dual9 = "select_from_dual9.sql";
    String select_from_dual10 = "select_from_dual10.sql";
    String select_connect_by = "select_connect_by.sql";

    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("cond").toURI());
    }

    public void test_select_from_dual2() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual2));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_select_from_dual3() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual3));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_select_from_dual4() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual4));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_select_from_dual5() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual5));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_select_from_dual6() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual6));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_select_from_dual7() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual7));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_select_from_dual8() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual8));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_select_from_dual9() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual9));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_select_from_dual10() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual10));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);

        assertNotNull(((SelectStatement)elems[0]).getWhereCondition());

        Expression expr = ((SelectStatement)elems[0]).getWhereCondition().getCondition();
        assertTrue(expr instanceof LogicalExpression);

        LogicalExpression lexpr = (LogicalExpression) expr;

        assertFalse(lexpr.isOr());
        assertEquals(3, lexpr.getList().size());

    }

    public void test_select_connect_by() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_connect_by));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(6, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
        assertTrue(elems[1] instanceof SelectStatement);
        assertTrue(elems[2] instanceof SelectStatement);
        assertTrue(elems[3] instanceof SelectStatement);
        assertTrue(elems[4] instanceof SqlPlusCommand);
        assertTrue(elems[5] instanceof SelectStatement);
    }

}
