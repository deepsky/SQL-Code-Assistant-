package test.deepsky.lang.plsql.parser;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.ctrl.GrantCommand;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.IOException;

public class PlSqlParser_GrantTest extends PlSqlParser_AbstractTest {

    String burleson_cases = "burleson_cases.sql";
    String oracle_docs1 = "oracle_docs1.sql";

    public PlSqlParser_GrantTest() {
        super("grant_samples");
    }

    public void test_burleson_cases() throws IOException {
        ASTNode root = parseScript(burleson_cases);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.GRANT_COMMAND));
        assertEquals(6, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof GrantCommand);
    }

    public void test_oracle_docs1() throws IOException {
        ASTNode root = parseScript(oracle_docs1);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.GRANT_COMMAND));
        assertEquals(15, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof GrantCommand);
    }


}
