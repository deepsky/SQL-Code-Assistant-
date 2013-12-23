package test.deepsky.lang.plsql.parser;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.utils.PlSqlUtil;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.IOException;

public class PlSqlParser_PackageBody1Test extends PlSqlParser_AbstractTest {

    String xdv_partition_status_pkg_pkb = "xdv_partition_status_pkg.pkb";

    public PlSqlParser_PackageBody1Test() {
        super("plsql_samples");
    }

    public void test_xdv_partition_status_pkg_pkb() throws IOException {
        ASTNode root = parseScript(xdv_partition_status_pkg_pkb);

        final int[] errorNodes = new int[]{0};
        PlSqlUtil.iterateOver(root, new PlSqlUtil.ASTNodeProcessor() {
            public void process(ASTNode node) {
                if (node.getElementType() == PLSqlTypesAdopted.ERROR_TOKEN_A) {
                    errorNodes[0]++;
                }
            }
        });

        assertEquals(0, errorNodes[0]);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.PACKAGE_BODY));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof PackageBody);

        PackageBody tab = (PackageBody) nodes[0].getPsi();
        assertEquals("xdv_partition_status_pkg", tab.getPackageName());

        PlSqlElement[] elements =  tab.getObjects();
        assertEquals(16, elements.length);

        assertTrue(elements[0] instanceof Function);
        assertEquals("obj2jt", ((Executable) elements[0]).getEName());

        assertTrue(elements[1] instanceof Function);
        assertEquals("get_part_status_table_name", ((Executable) elements[1]).getEName());

        assertTrue(elements[2] instanceof Function);
        assertEquals("get_config_value", ((Executable) elements[2]).getEName());

        assertTrue(elements[3] instanceof Function);
        assertEquals("get_stability_delay", ((Executable) elements[3]).getEName());

        assertTrue(elements[4] instanceof Function);
        assertEquals("get_stability_period", ((Executable) elements[4]).getEName());

        assertTrue(elements[5] instanceof Function);
        assertEquals("get_delay_limit", ((Executable) elements[5]).getEName());

        assertTrue(elements[6] instanceof Procedure);
        assertEquals("send_part_status_msg", ((Executable) elements[6]).getEName());

        assertTrue(elements[7] instanceof Procedure);
        assertEquals("process_job", ((Executable) elements[7]).getEName());

        assertTrue(elements[8] instanceof Function);
        assertEquals("get_dde_id_ivl_mins", ((Executable) elements[8]).getEName());

        assertTrue(elements[9] instanceof Procedure);
        assertEquals("create_schedule", ((Executable) elements[9]).getEName());

        assertTrue(elements[10] instanceof Procedure);
        assertEquals("drop_schedule", ((Executable) elements[10]).getEName());

        assertTrue(elements[11] instanceof Function);
        assertEquals("get_part_status_table_name", ((Executable) elements[1]).getEName());

        assertTrue(elements[12] instanceof Function);
        assertEquals("get_job_name_old", ((Executable) elements[11]).getEName());

        assertTrue(elements[13] instanceof Procedure);
        assertEquals("drop_job", ((Executable) elements[13]).getEName());

        assertTrue(elements[14] instanceof Procedure);
        assertEquals("unschedule_job", ((Executable) elements[14]).getEName());

        assertTrue(elements[15] instanceof Procedure);
        assertEquals("schedule_job", ((Executable) elements[15]).getEName());

    }

}
