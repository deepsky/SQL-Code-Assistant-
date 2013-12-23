package test.deepsky.lang.plsql.parser;


import com.deepsky.integration.lexer.generated.PlSqlBaseTokenTypes;
import com.deepsky.lang.PsiUtil;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.psi.ddl.CreateMaterializedView;
import com.deepsky.lang.plsql.psi.ddl.CreateMaterializedViewLog;
import com.intellij.lang.ASTNode;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.util.List;


public class PlSqlParser_MViewTest extends PlSqlParser_AbstractTest {
    public PlSqlParser_MViewTest() {
        super("materialized_view");
    }


    public void testPeriodic_refresh_mview() throws Exception {
        ASTNode root = parseScript(getFilePath());
        assertNotNull(root);

        ASTNode[] nodes = root.getChildren(null);
        assertEquals(3, nodes.length);
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW_LOG, nodes[0].getElementType());
        assertEquals(PlSqlBaseTokenTypes.LF, nodes[1].getElementType());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW, nodes[2].getElementType());

        CreateMaterializedViewLog viewLog = (CreateMaterializedViewLog)nodes[0].getPsi();
        assertEquals("employees",viewLog.getMasterTableName());

        CreateMaterializedView view = (CreateMaterializedView)nodes[2].getPsi();
        assertEquals("emp_data",view.getViewName());

        assertNotNull(view.getSelectExpr());
    }

    public void testPrebuilt_mview() throws Exception {
        ASTNode root = parseScript(getFilePath());
        assertNotNull(root);

        List<ASTNode> nodes = PsiUtil.visibleChildren(root, null);
        assertEquals(2, nodes.size());
        assertEquals(PLSqlTypesAdopted.TABLE_DEF, nodes.get(0).getElementType());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW, nodes.get(1).getElementType());

        CreateMaterializedView view = (CreateMaterializedView)nodes.get(1).getPsi();
        assertEquals("sales_sum_table",view.getViewName());

        assertNotNull(view.getSelectExpr());
    }


    public void testRowid_mview() throws Exception {
        ASTNode root = parseScript(getFilePath());
        assertNotNull(root);

        List<ASTNode> nodes = PsiUtil.visibleChildren(root, null);
        assertEquals(1, nodes.size());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW, nodes.get(0).getElementType());

        CreateMaterializedView view = (CreateMaterializedView)nodes.get(0).getPsi();
        assertEquals("order_data",view.getViewName());

        assertNotNull(view.getSelectExpr());
    }

    public void testSubquery_mview() throws Exception {
        ASTNode root = parseScript(getFilePath());
        assertNotNull(root);

        List<ASTNode> nodes = PsiUtil.visibleChildren(root, null);
        assertEquals(1, nodes.size());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW, nodes.get(0).getElementType());

        CreateMaterializedView view = (CreateMaterializedView)nodes.get(0).getPsi();
        assertEquals("foreign_customers",view.getViewName());

        assertNotNull(view.getSelectExpr());
    }

    public void testAggregate_mview() throws Exception {
        ASTNode root = parseScript(getFilePath());
        assertNotNull(root);

        List<ASTNode> nodes = PsiUtil.visibleChildren(root, null);
        assertEquals(3, nodes.size());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW_LOG, nodes.get(0).getElementType());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW_LOG, nodes.get(1).getElementType());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW, nodes.get(2).getElementType());

        CreateMaterializedViewLog viewLog = (CreateMaterializedViewLog)nodes.get(0).getPsi();
        assertEquals("times",viewLog.getMasterTableName());
        CreateMaterializedViewLog viewLog1 = (CreateMaterializedViewLog)nodes.get(1).getPsi();
        assertEquals("products",viewLog1.getMasterTableName());

        CreateMaterializedView view = (CreateMaterializedView)nodes.get(2).getPsi();
        assertEquals("sales_mv",view.getViewName());

        assertNotNull(view.getSelectExpr());
    }


    public void testFast_refreshable_mview() throws Exception {
        ASTNode root = parseScript(getFilePath());
        assertNotNull(root);

        List<ASTNode> nodes = PsiUtil.visibleChildren(root, null);
        assertEquals(2, nodes.size());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW_LOG, nodes.get(0).getElementType());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW, nodes.get(1).getElementType());

        CreateMaterializedViewLog viewLog = (CreateMaterializedViewLog)nodes.get(0).getPsi();
        assertEquals("inventories",viewLog.getMasterTableName());

        CreateMaterializedView view = (CreateMaterializedView)nodes.get(1).getPsi();
        assertEquals("warranty_orders",view.getViewName());

        assertNotNull(view.getSelectExpr());
    }

    public void testJoin_mview() throws Exception {
        ASTNode root = parseScript(getFilePath());
        assertNotNull(root);

        List<ASTNode> nodes = PsiUtil.visibleChildren(root, null);
        assertEquals(1, nodes.size());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW, nodes.get(0).getElementType());

        CreateMaterializedView view = (CreateMaterializedView)nodes.get(0).getPsi();
        assertEquals("sales_by_month_by_state",view.getViewName());

        assertNotNull(view.getSelectExpr());
    }

    public void testNested_mview() throws Exception {
        ASTNode root = parseScript(getFilePath());
        assertNotNull(root);

        List<ASTNode> nodes = PsiUtil.visibleChildren(root, null);
        assertEquals(1, nodes.size());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW, nodes.get(0).getElementType());

        CreateMaterializedView view = (CreateMaterializedView)nodes.get(0).getPsi();
        assertEquals("my_warranty_orders",view.getViewName());

        assertNotNull(view.getSelectExpr());
    }

    public void testPrimarykey_mview() throws Exception {
        ASTNode root = parseScript(getFilePath());
        assertNotNull(root);

        List<ASTNode> nodes = PsiUtil.visibleChildren(root, null);
        assertEquals(1, nodes.size());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW, nodes.get(0).getElementType());

        CreateMaterializedView view = (CreateMaterializedView)nodes.get(0).getPsi();
        assertEquals("catalog",view.getViewName());

        assertNotNull(view.getSelectExpr());
    }

    public void testAutorefresh_times_mview() throws Exception {
        ASTNode root = parseScript(getFilePath());
        assertNotNull(root);

        List<ASTNode> nodes = PsiUtil.visibleChildren(root, null);
        assertEquals(1, nodes.size());
        assertEquals(PLSqlTypesAdopted.CREATE_MATERIALIZED_VIEW, nodes.get(0).getElementType());

        CreateMaterializedView view = (CreateMaterializedView)nodes.get(0).getPsi();
        assertEquals("all_customers",view.getViewName());

        assertNotNull(view.getSelectExpr());
    }
}
