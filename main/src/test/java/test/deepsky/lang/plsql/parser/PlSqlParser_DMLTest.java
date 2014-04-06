/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.IOException;

public class PlSqlParser_DMLTest extends PlSqlParser_AbstractTest {

    String insert_into_errlog = "insert_into_errlog.sql";
    String err_logging_misc = "err_logging_misc.sql";

    public PlSqlParser_DMLTest() {
        super("dml");
    }

    public void test_insert_into_errlog() throws IOException {
        ASTNode root = parseScript(insert_into_errlog);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.INSERT_COMMAND));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof InsertStatement);

        InsertStatement insert = (InsertStatement) nodes[0].getPsi();
        assertEquals(8, insert.getColumnList().length);

        SelectStatement select = insert.getSubquery();

        assertEquals(8, select.getSelectFieldList().length);
        SelectFieldCommon[] fields = select.getSelectFieldList();

        assertEquals("ID", ((SelectFieldExpr) fields[0]).getAlias());
        assertEquals("AIRLINE", ((SelectFieldExpr) fields[1]).getAlias());
        assertEquals("TME", ((SelectFieldExpr) fields[2]).getAlias());
        assertEquals("apikey", ((SelectFieldExpr) fields[3]).getAlias());
        assertNull(((SelectFieldExpr) fields[4]).getAlias());
        assertEquals("numinfants", ((SelectFieldExpr) fields[5]).getAlias());
        assertEquals("socialtwitterid", ((SelectFieldExpr) fields[6]).getAlias());
        assertNull(((SelectFieldExpr) fields[7]).getAlias());

        assertNotNull(select.getWhereCondition());
        assertNotNull(select.getFromClause());

        assertEquals(1, select.getFromClause().getTableList().length);

        assertNull(select.getFromClause().getTableList()[0].getAlias());

        assertNull(select.getOrderByClause());
        assertNull(select.getGroupByClause());
    }

    public void test_err_logging_misc() throws IOException {
        ASTNode root = parseScript(err_logging_misc);

        ASTNode[] nodes = root.getChildren(TokenSet.create(
                PlSqlElementTypes.INSERT_COMMAND,
                PlSqlElementTypes.UPDATE_COMMAND,
                PlSqlElementTypes.DELETE_COMMAND,
                PlSqlElementTypes.MERGE_COMMAND));
        assertEquals(4, nodes.length);

        assertTrue(nodes[0].getPsi() instanceof InsertStatement);
        InsertStatement insert = (InsertStatement) nodes[0].getPsi();
        assertEquals(0, insert.getColumnList().length);

        assertTrue(nodes[1].getPsi() instanceof UpdateStatement);
        UpdateStatement update = (UpdateStatement) nodes[1].getPsi();
        assertEquals("dest", update.getTargetTable().getTableName());

        assertTrue(nodes[2].getPsi() instanceof MergeStatement);
        MergeStatement merge = (MergeStatement) nodes[2].getPsi();
        assertEquals("dest", merge.getIntoTable().getTableName());

        assertTrue(nodes[3].getPsi() instanceof DeleteStatement);
        DeleteStatement delete = (DeleteStatement) nodes[3].getPsi();
        assertEquals("dest", delete.getTargetTable().getTableName());
    }
}
