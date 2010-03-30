package test.deepsky.lang.plsql.processors;

import junit.framework.TestCase;

import java.io.*;
import java.net.URISyntaxException;

import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.processors.SQLGenerator;


public class SQLGeneratorVisitorTest extends TestCase {

    String select_from_dual0 = "select_from_dual0.sql";
    String select_from_dual1 = "select_from_dual1.sql";
    String select_from_dual2 = "select_from_dual2.sql";
    String select_from_dual3 = "select_from_dual3.sql";
    String select_from_dual4 = "select_from_dual4.sql";
    String select_from_dual5 = "select_from_dual5.sql";
    String select_from_dual6 = "select_from_dual6.sql";
    String select_from_dual7 = "select_from_dual7.sql";
    String select_from_dual8 = "select_from_dual8.sql";
    String select_from_dual9 = "select_from_dual9.sql";
    String select_from_dual10 = "select_from_dual10.sql";
    String select_from_dual11 = "select_from_dual11.sql";
    String select01 = "select01.sql";
    String select02 = "select02.sql";
    String select03 = "select03.sql";
    String select04 = "select04.sql";
    String select05 = "select05.sql";


    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("selects").toURI());
    }

    public void test_select_from_dual0() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual0));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);

        SelectStatement select = (SelectStatement) elems[0];
        assertEquals(2, select.getSelectFieldList().length);

        assertTrue( select.getSelectFieldList()[0] instanceof SelectFieldAsterisk );
        assertTrue( select.getSelectFieldList()[1] instanceof SelectFieldAsterisk );

        assertTrue(select.getSelectFieldList()[0] instanceof SelectFieldAsterisk);
        assertTrue(select.getSelectFieldList()[1] instanceof SelectFieldIdentAsterisk);
        assertEquals("d", ((SelectFieldIdentAsterisk)select.getSelectFieldList()[1]).getTableRef().toLowerCase());

        assertNotNull(select.getFromClause());
        assertNotNull(select.getFromClause().getTableList());
        assertEquals(1, select.getFromClause().getTableList().length);
        assertTrue(select.getFromClause().getTableList()[0] instanceof PlainTable);
        assertNotNull(select.getFromClause().getTableList()[0].getAlias());
        assertEquals("d", select.getFromClause().getTableList()[0].getAlias().toLowerCase());
        
        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_select_from_dual1() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual1));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
        SelectStatement select = (SelectStatement) elems[0];
        assertEquals(1, select.getFromClause().getTableList().length);

        assertTrue(select.getFromClause().getTableList()[0] instanceof Subquery);
        Subquery sub = (Subquery) select.getFromClause().getTableList()[0];

        SelectStatement select2 = sub.getSelectStatement();
        assertNotNull(select2.getFromClause());
        assertEquals(1, select2.getFromClause().getTableList().length);

        assertNotNull(select2.getFollowingSelectStatement());
        assertEquals(SelectStatement.UNION_TYPE, select2.getFollowingSelectStatementType());


        SQLGenerator gen = new SQLGenerator();
        gen.outToString(elems[0]);
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }


    public void test_select_from_dual2() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual2));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

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

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

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

        SQLGenerator gen = new SQLGenerator();
        OutputStream out = new ByteArrayOutputStream();
        gen.printOut(elems[0], out);

        String replicated = out.toString();

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

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

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

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

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

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

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

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

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

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

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

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

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }


    public void test_select_from_dual11() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select_from_dual11));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }


    public void test_select01() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select01));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_select02() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select02));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_select03() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select03));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_select04() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select04));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);

        SQLGenerator gen = new SQLGenerator();
        String replicated = gen.outToString(elems[0]);

        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }


    public void test_select05() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, select05));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);

        SQLGenerator gen = new SQLGenerator();
        OutputStream out = new ByteArrayOutputStream();
        gen.printOut(elems[0], out);

        String replicated = out.toString();
        String rr = elems[0].getText();
        assertEquals(rr, replicated);

        // parse replicated SQL
        Reader r1 = new StringReader(replicated);
        PlSqlElement[] elems2 = parser.parseStream(r1);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }
}

