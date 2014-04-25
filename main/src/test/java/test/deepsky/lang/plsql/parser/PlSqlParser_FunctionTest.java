package test.deepsky.lang.plsql.parser;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.Function;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.IOException;

public class PlSqlParser_FunctionTest extends PlSqlParser_AbstractTest {

    String regular_func = "regular_func.sql";
    String result_cache = "result_cache.sql";
    String result_cache2 = "result_cache2.sql";

    public PlSqlParser_FunctionTest() {
        super("functions");
    }

    public void test_regular_func() throws IOException {
        ASTNode root = parseScript(regular_func);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.FUNCTION_BODY));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof Function);

        Function f = (Function) nodes[0].getPsi();
        assertEquals("one_employee", f.getEName());
        assertEquals(Type.ROWTYPE, f.getReturnType().typeId());
    }

    public void test_result_cache() throws IOException {
        ASTNode root = parseScript(result_cache);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.FUNCTION_BODY));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof Function);

        Function f = (Function) nodes[0].getPsi();
        assertEquals("get_test_value", f.getEName());
        assertEquals(Type.NUMBER, f.getReturnType().typeId());
    }

    public void test_result_cache2() throws IOException {
        ASTNode root = parseScript(result_cache2);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.FUNCTION_BODY));
        assertEquals(2, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof Function);
        assertTrue(nodes[1].getPsi() instanceof Function);

        Function f = (Function) nodes[0].getPsi();
        assertEquals("get_test_value", f.getEName());
        assertEquals(Type.NUMBER, f.getReturnType().typeId());

        f = (Function) nodes[1].getPsi();
        assertEquals("one_employee", f.getEName());
        assertEquals(Type.ROWTYPE, f.getReturnType().typeId());
    }
}
