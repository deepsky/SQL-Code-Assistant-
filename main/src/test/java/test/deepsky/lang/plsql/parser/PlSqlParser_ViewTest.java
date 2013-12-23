/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package test.deepsky.lang.plsql.parser;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.File;
import java.io.IOException;

public class PlSqlParser_ViewTest extends PlSqlParser_AbstractTest {

    String all_apply = "all_apply.sql";
    String all_apply_error = "all_apply_error.sql";
    String all_audit_policy_columns = "all_audit_policy_columns.sql";
    String dba_audit_trail = "dba_audit_trail.sql";
    String dba_fga_audit_trail = "dba_fga_audit_trail.sql";
    String dba_thresholds = "dba_thresholds.sql";
    String user_scheduler_jobs = "user_scheduler_jobs.sql";
    String XDV_STA_FTRN_T_TRN_MIS_V = "XDV_STA_FTRN_T_TRN_MIS_V.SQL";
    String AQ$XDV_CDI_TRN_TRANSFER_QT = "AQ$XDV_CDI_TRN_TRANSFER_QT.SQL";

    File base;

    public PlSqlParser_ViewTest() {
        super("view_definitions");
    }

    public void test_AQ$XDV_CDI_TRN_TRANSFER_QT() throws IOException {
        ASTNode root = parseScript(AQ$XDV_CDI_TRN_TRANSFER_QT);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_VIEW));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof CreateView);

        CreateView view = (CreateView) nodes[0].getPsi();
        assertEquals(27, view.getColumnDefs().length);

        SelectStatement select = view.getSelectExpr();

        assertEquals(27, select.getSelectFieldList().length);
        SelectFieldCommon[] fields = select.getSelectFieldList();

        assertEquals("QUEUE", ((SelectFieldExpr) fields[0]).getAlias());
        assertEquals("MSG_ID", ((SelectFieldExpr) fields[1]).getAlias());
        assertEquals("CORR_ID", ((SelectFieldExpr) fields[2]).getAlias());
        assertEquals("MSG_PRIORITY", ((SelectFieldExpr) fields[3]).getAlias());
        assertEquals("MSG_STATE", ((SelectFieldExpr) fields[4]).getAlias());
        assertEquals("DELAY", ((SelectFieldExpr) fields[5]).getAlias());
        assertEquals("DELAY_TIMESTAMP", ((SelectFieldExpr) fields[6]).getAlias());
        assertNull(((SelectFieldExpr) fields[7]).getAlias());
        assertEquals("ENQ_TIME", ((SelectFieldExpr) fields[8]).getAlias());
        assertEquals("ENQ_TIMESTAMP", ((SelectFieldExpr) fields[9]).getAlias());

        assertNotNull(select.getWhereCondition());
        assertNotNull(select.getFromClause());

        assertEquals(1, select.getFromClause().getTableList().length);

        assertNull(select.getFromClause().getTableList()[0].getAlias());

        assertNull(select.getOrderByClause());
        assertNull(select.getGroupByClause());
    }

    public void test_XDV_STA_FTRN_T_TRN_MIS_V() throws IOException {
        ASTNode root = parseScript(XDV_STA_FTRN_T_TRN_MIS_V);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_VIEW));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof CreateView);

        CreateView view = (CreateView) nodes[0].getPsi();
        assertEquals(6, view.getColumnDefs().length);

        SelectStatement select = view.getSelectExpr();

        assertEquals(6, select.getSelectFieldList().length);
        assertNull(select.getWhereCondition());
        assertNotNull(select.getFromClause());

        assertEquals(2, select.getFromClause().getTableList().length);

        assertEquals("trn", select.getFromClause().getTableList()[0].getAlias());
        assertEquals("dt", select.getFromClause().getTableList()[1].getAlias());

        assertNotNull(select.getOrderByClause());
        assertNull(select.getGroupByClause());

        assertEquals(1, select.getOrderByClause().getOrderPairList().length);
        assertEquals("dt.id", select.getOrderByClause().getOrderPairList()[0].getExpession().getText());
    }

    public void test_user_scheduler_jobs() throws IOException {
        ASTNode root = parseScript(user_scheduler_jobs);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_VIEW));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof CreateView);

        CreateView view = (CreateView) nodes[0].getPsi();
        assertEquals(53, view.getColumnDefs().length);

        SelectStatement select = view.getSelectExpr();
        assertNotNull(select);

        assertTrue(select instanceof SelectStatementUnion);
        SelectStatementUnion selectUnion = (SelectStatementUnion) select;

        SelectStatement[] selects = selectUnion.getSelectStatements();
        assertEquals(2, selects.length);

        // 1st select
        assertEquals(53, selects[0].getSelectFieldList().length);
        assertEquals(4, selects[0].getFromClause().getTableList().length);
        assertNotNull(selects[0].getWhereCondition());
        assertTrue(selects[0].getWhereCondition().getCondition() instanceof LogicalExpression);
        LogicalExpression lex = (LogicalExpression) selects[0].getWhereCondition().getCondition();

        Expression[] parts = lex.getList();
        assertEquals(4, parts.length);

        // 2nd select
        assertEquals(53, selects[1].getSelectFieldList().length);
        assertEquals(5, selects[1].getFromClause().getTableList().length);
        assertNotNull(selects[1].getWhereCondition());
        assertTrue(selects[1].getWhereCondition().getCondition() instanceof LogicalExpression);
        LogicalExpression lex1 = (LogicalExpression) selects[1].getWhereCondition().getCondition();

        Expression[] parts1 = lex1.getList();
        assertEquals(6, parts1.length);
    }

    public void test_dba_audit_trail() throws IOException {
        ASTNode root = parseScript(dba_audit_trail);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_VIEW));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof CreateView);

        CreateView view = (CreateView) nodes[0].getPsi();
        assertEquals(41, view.getColumnDefs().length);

        SelectStatement select = view.getSelectExpr();
        assertNotNull(select);

        assertEquals(41, select.getSelectFieldList().length);
        assertEquals(5, select.getFromClause().getTableList().length);
        assertNotNull(select.getWhereCondition());
        assertTrue(select.getWhereCondition().getCondition() instanceof LogicalExpression);
        LogicalExpression lex = (LogicalExpression) select.getWhereCondition().getCondition();

        Expression[] parts = lex.getList();
        assertEquals(4, parts.length);
    }

    public void test_dba_fga_audit_trail() throws IOException {
        ASTNode root = parseScript(dba_fga_audit_trail);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_VIEW));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof CreateView);

        CreateView view = (CreateView) nodes[0].getPsi();
        assertEquals(25, view.getColumnDefs().length);

        SelectStatement select = view.getSelectExpr();
        assertNotNull(select);

        assertEquals(25, select.getSelectFieldList().length);
        assertEquals(1, select.getFromClause().getTableList().length);
        assertNull(select.getWhereCondition());
    }

    public void test_dba_thresholds() throws IOException {
        ASTNode root = parseScript(dba_thresholds);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_VIEW));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof CreateView);

        CreateView view = (CreateView) nodes[0].getPsi();
        assertEquals(11, view.getColumnDefs().length);

        SelectStatement select = view.getSelectExpr();
        assertTrue(select instanceof SelectStatementUnion);
        SelectStatementUnion selectUnion = (SelectStatementUnion) select;

        SelectStatement[] selects = selectUnion.getSelectStatements();
        assertEquals(2, selects.length);

        // 1st select
        assertEquals(11, selects[0].getSelectFieldList().length);
        assertEquals(3, selects[0].getFromClause().getTableList().length);
        assertNotNull(selects[0].getWhereCondition());
        assertTrue(selects[0].getWhereCondition().getCondition() instanceof LogicalExpression);
        LogicalExpression lex = (LogicalExpression) selects[0].getWhereCondition().getCondition();

        Expression[] parts = lex.getList();
        assertEquals(3, parts.length);

        // 2nd select
        assertEquals(11, selects[1].getSelectFieldList().length);
        assertEquals(4, selects[1].getFromClause().getTableList().length);
        assertNotNull(selects[1].getWhereCondition());
        assertTrue(selects[1].getWhereCondition().getCondition() instanceof LogicalExpression);
        LogicalExpression lex1 = (LogicalExpression) selects[1].getWhereCondition().getCondition();

        Expression[] parts1 = lex1.getList();
        assertEquals(4, parts1.length);
    }

    public void test_all_apply() throws IOException {
        ASTNode root = parseScript(all_apply);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_VIEW));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof CreateView);

        CreateView view = (CreateView) nodes[0].getPsi();
        assertEquals("ALL_APPLY", view.getViewName());
        assertEquals(20, view.getColumnDefs().length);

        SelectStatement select = view.getSelectExpr();
        assertNotNull(select);

        assertEquals(20, select.getSelectFieldList().length);
        assertEquals(2, select.getFromClause().getTableList().length);
        assertNotNull(select.getWhereCondition());
        assertTrue(select.getWhereCondition().getCondition() instanceof LogicalExpression);
        LogicalExpression lex = (LogicalExpression) select.getWhereCondition().getCondition();

        Expression[] parts = lex.getList();
        assertEquals(4, parts.length);
    }


    public void test_all_apply_error() throws IOException {
        ASTNode root = parseScript(all_apply_error);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_VIEW));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof CreateView);

        CreateView view = (CreateView) nodes[0].getPsi();
        assertEquals("ALL_APPLY_ERROR", view.getViewName());
        assertEquals(14, view.getColumnDefs().length);

        SelectStatement select = view.getSelectExpr();
        assertTrue(select instanceof SelectStatementUnion);
        SelectStatementUnion selectUnion = (SelectStatementUnion) select;

        assertEquals(14, select.getSelectFieldList().length);
        assertEquals(3, select.getFromClause().getTableList().length);
        assertNotNull(select.getWhereCondition());
        assertTrue(select.getWhereCondition().getCondition() instanceof LogicalExpression);
        LogicalExpression lex = (LogicalExpression) select.getWhereCondition().getCondition();

        Expression[] parts = lex.getList();
        assertEquals(3, parts.length);

        SelectStatement[] selects = selectUnion.getSelectStatements();
        assertEquals(2, selects.length);

        assertEquals(14, selects[1].getSelectFieldList().length);
        assertEquals(1, selects[1].getFromClause().getTableList().length);
        assertNotNull(selects[1].getWhereCondition());
        assertTrue(selects[1].getWhereCondition().getCondition() instanceof InCondition);
        InCondition inCondition = (InCondition) selects[1].getWhereCondition().getCondition();

        assertEquals("e.recipient_id", inCondition.getLeftPart().getText());
        assertEquals("(select user_id from dba_users)", inCondition.getRightPart().getText());
    }


    public void test_all_audit_policy_columns() throws IOException {
        ASTNode root = parseScript(all_audit_policy_columns);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_VIEW));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof CreateView);

        CreateView view = (CreateView) nodes[0].getPsi();
        assertEquals(4, view.getColumnDefs().length);

        SelectStatement select = view.getSelectExpr();
        assertTrue(select instanceof SelectStatementUnion);
        SelectStatementUnion selectUnion = (SelectStatementUnion) select;

        SelectStatement[] selects = selectUnion.getSelectStatements();
        assertEquals(2, selects.length);

        assertEquals(4, selects[0].getSelectFieldList().length);
        assertEquals(2, selects[0].getFromClause().getTableList().length);
        assertNotNull(selects[0].getWhereCondition());
        assertTrue(selects[0].getWhereCondition().getCondition() instanceof LogicalExpression);
        LogicalExpression lex0 = (LogicalExpression) selects[0].getWhereCondition().getCondition();

        assertEquals("d.OBJECT_SCHEMA = t.OWNER", lex0.getList()[0].getText());
        assertEquals("d.OBJECT_NAME = t.TABLE_NAME", lex0.getList()[1].getText());

        assertEquals(4, selects[1].getSelectFieldList().length);
        assertEquals(2, selects[1].getFromClause().getTableList().length);
        assertNotNull(selects[1].getWhereCondition());
        assertTrue(selects[1].getWhereCondition().getCondition() instanceof LogicalExpression);
        LogicalExpression lex1 = (LogicalExpression) selects[1].getWhereCondition().getCondition();

        assertEquals("d.OBJECT_SCHEMA = v.OWNER", lex1.getList()[0].getText());
        assertEquals("d.OBJECT_NAME = v.VIEW_NAME", lex1.getList()[1].getText());
    }
}
