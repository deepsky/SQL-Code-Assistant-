package test.deepsky.lang.plsql.parser;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.IOException;

public class PlSqlParser_RevokeTest extends PlSqlParser_AbstractTest {

    String revoke_oracle_docs1 = "revoke_oracle_docs1.sql";

    public PlSqlParser_RevokeTest() {
        super("revoke_samples");
    }

    public void test_oracle_docs1() throws IOException {
        ASTNode root = parseScript(revoke_oracle_docs1);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.REVOKE_COMMAND));
        assertEquals(9, nodes.length);

        ASTNode[] nodes1 = root.getChildren(TokenSet.create(PlSqlElementTypes.GRANT_COMMAND));
        assertEquals(4, nodes1.length);

        ASTNode[] nodes2 = root.getChildren(TokenSet.create(PlSqlElementTypes.TABLE_DEF));
        assertEquals(1, nodes2.length);
    }

}
