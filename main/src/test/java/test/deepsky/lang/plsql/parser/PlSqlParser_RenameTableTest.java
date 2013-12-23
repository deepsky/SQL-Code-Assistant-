package test.deepsky.lang.plsql.parser;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.ddl.RenameTable;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.IOException;

public class PlSqlParser_RenameTableTest extends PlSqlParser_AbstractTest {

    String rename_table1 = "rename_table1.sql";

    public PlSqlParser_RenameTableTest() {
        super("rename_table");
    }

    public void test_rename_table1() throws IOException {
        ASTNode root = parseScript(rename_table1);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.RENAME_TABLE));
        assertEquals(3, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof RenameTable);
        assertTrue(nodes[1].getPsi() instanceof RenameTable);
        assertTrue(nodes[2].getPsi() instanceof RenameTable);
    }
}
