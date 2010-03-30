package test.deepsky.lang.plsql.struct.parser;

import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.psi.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.net.URISyntaxException;

import junit.framework.TestCase;

public class PlSqlASTParser_DMLTest extends TestCase {

    String inserts1 = "inserts1.sql";
    String inserts2 = "inserts2.sql";
    String updates1 = "updates1.sql";
    String deletes1 = "deletes1.sql";
    String merge = "merge.sql";
    String merge2 = "merge2.sql";
    String merge_list = "merge_list.sql";

    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("dml").toURI());
    }

    public void test_inserts1() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, inserts1));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(3, elems.length );
        assertTrue(elems[0] instanceof InsertStatement);
    }

//    public void test_inserts2() throws FileNotFoundException {
//        Reader r = new FileReader(new File(base, inserts2));
//        PlSqlElement[] elems = parser.parseStream(r);
//
//        assertNotNull(elems);
//        assertEquals(1, elems.length );
//        assertTrue(elems[0] instanceof InsertStatement);
//    }

    public void test_updates1() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, updates1));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(2, elems.length );
        assertTrue(elems[0] instanceof UpdateStatement);
        assertTrue(elems[1] instanceof SelectStatement);
    }


    public void test_merge() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, merge));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(2, elems.length );
        assertTrue(elems[0] instanceof SqlPlusCommand);
        assertTrue(elems[1] instanceof MergeStatement);
    }


    public void test_merge2() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, merge2));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof MergeStatement);
    }


    public void test_merge_list() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, merge_list));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(7, elems.length );
        assertTrue(elems[0] instanceof MergeStatement);
        assertTrue(elems[1] instanceof MergeStatement);
        assertTrue(elems[2] instanceof MergeStatement);
        assertTrue(elems[3] instanceof MergeStatement);
        assertTrue(elems[4] instanceof MergeStatement);
        assertTrue(elems[5] instanceof MergeStatement);
        assertTrue(elems[6] instanceof MergeStatement);
    }
//    public void test_deletes1() throws FileNotFoundException {
//        Reader r = new FileReader(new File(base, deletes1));
//        PlSqlElement[] elems = parser.parseStream(r);
//
//        assertNotNull(elems);
//        assertEquals(1, elems.length );
//        assertTrue(elems[0] instanceof DeleteStatement);
//    }

}
