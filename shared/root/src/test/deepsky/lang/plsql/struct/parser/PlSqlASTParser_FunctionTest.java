package test.deepsky.lang.plsql.struct.parser;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.net.URISyntaxException;

import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.Function;

public class PlSqlASTParser_FunctionTest extends TestCase {

    String func1 = "func1.sql";
    String get_ivl_diff = "get_ivl_diff.sql";
    String get_date_for_tz = "get_date_for_tz.sql";

    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("functions").toURI());
    }

    public void test_func1() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, func1));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof Function);

        Function v = (Function)elems[0];

        assertEquals("func1", v.getEName());
        // check return type
        assertTrue(TypeFactory.createTypeById(Type.INTEGER).equals(v.getReturnType()));
        assertEquals(0, v.getArguments().length);
    }

    public void test_get_ivl_diff() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, get_ivl_diff));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof Function);

        Function v = (Function)elems[0];

        assertEquals("get_ivl_diff", v.getEName());
        // check return type
        assertTrue(TypeFactory.createTypeById(Type.INTEGER).equals(v.getReturnType()));
        assertEquals(4, v.getArguments().length);
        assertEquals("a_begin_ts", v.getArguments()[0].getArgumentName());
        assertEquals("a_end_ts", v.getArguments()[1].getArgumentName());
        assertEquals("a_tz_id", v.getArguments()[2].getArgumentName());
        assertEquals("a_ivl", v.getArguments()[3].getArgumentName());

    }

    public void test_get_date_for_tz() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, get_date_for_tz));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof Function);

        Function v = (Function)elems[0];

        assertEquals("get_date_for_tz", v.getEName());
        // check return type
        assertTrue(TypeFactory.createTypeById(Type.DATE).equals(v.getReturnType()));

        assertEquals(3, v.getArguments().length);
        assertEquals("p_date", v.getArguments()[0].getArgumentName());
        assertEquals("p_source_tz", v.getArguments()[1].getArgumentName());
        assertEquals("p_target_tz", v.getArguments()[2].getArgumentName());
    }
}
