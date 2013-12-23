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
import com.deepsky.lang.PsiUtil;
import com.intellij.lang.ASTNode;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.IOException;
import java.util.List;

public class PlSqlParser_PLSQLTest extends PlSqlParser_AbstractTest {

    String number_with_leading_dot = "number_with_leading_dot.sql";
    String return_cursor_in_decl = "return_cursor_in_decl.sql";
    String back_divide_fails_func_parsing = "back_divide_fails_func_parsing.sql";
    String xdv_agr_dt_bloat_pkg = "xdv_agr_dt_bloat_pkg.pks" ;

    public PlSqlParser_PLSQLTest() {
        super("plsql_samples");
    }

    public void test_number_with_leading_dot() throws IOException {
        ASTNode root = parseScript(number_with_leading_dot);

        List<ASTNode> users = PsiUtil.visibleChildren(root);
        assertEquals(1, users.size());
        assertEquals(PlSqlElementTypes.PROCEDURE_BODY, users.get(0).getElementType());
        PlSqlBlock blk = ((Procedure) users.get(0).getPsi()).getBlock();
        PlSqlElement[] elems = blk.getObjectList();
        assertEquals(1, elems.length);
        assertEquals(PlSqlElementTypes.PROCEDURE_CALL, elems[0].getNode().getElementType());
        ProcedureCall call = (ProcedureCall) elems[0];
        CallArgument[] args = call.getCallArguments();
        assertEquals(18, args.length);
        assertTrue(args[0].getExpression() instanceof NumericLiteral);
        assertEquals(".5", args[0].getExpression().getText());
        assertTrue(args[1].getExpression() instanceof NumericLiteral);
        assertEquals(".8", args[1].getExpression().getText());
        assertTrue(args[2].getExpression() instanceof NumericLiteral);
        assertEquals(".5", args[2].getExpression().getText());
        assertTrue(args[3].getExpression() instanceof NumericLiteral);
        assertEquals(".8", args[3].getExpression().getText());

        assertTrue(args[17].getExpression() instanceof NumericLiteral);
        assertEquals(".8", args[17].getExpression().getText());
    }


    public void test_return_cursor_in_decl() throws IOException {
        ASTNode root = parseScript(return_cursor_in_decl);
        List<ASTNode> users = PsiUtil.visibleChildren(root);
        assertEquals(1, users.size());
        assertEquals(PlSqlElementTypes.SQLPLUS_ANONYM_PLSQL_BLOCK, users.get(0).getElementType());
        PlSqlBlock blk = ((SqlPlusAnonymPlSqlBlock) users.get(0).getPsi()).getBlock();
        Declaration[] declList = blk.getDeclarations();
        assertEquals(6, declList.length);
        assertTrue(declList[0] instanceof VariableDecl);
        assertEquals("my_emp_id", declList[0].getDeclName());

        assertTrue(declList[1] instanceof VariableDecl);
        assertEquals("my_job_id", declList[1].getDeclName());

        assertTrue(declList[2] instanceof VariableDecl);
        assertEquals("my_sal", declList[2].getDeclName());

        assertTrue(declList[3] instanceof CursorDecl);
        assertEquals("c1", declList[3].getDeclName());

        assertTrue(declList[4] instanceof VariableDecl);
        assertEquals("my_dept", declList[4].getDeclName());

        assertTrue(declList[5] instanceof CursorDecl);
        assertEquals("c2", declList[5].getDeclName());
    }

    public void test_back_divide_fails_func_parsing() throws IOException {
        ASTNode root = parseScript(back_divide_fails_func_parsing);

        List<ASTNode> users = PsiUtil.visibleChildren(root);

        assertEquals(7, users.size());
        assertTrue(users.get(0).getPsi() instanceof Function);
        assertTrue(users.get(1).getPsi() instanceof ErrorTokenWrapper);
        assertTrue(users.get(2).getPsi() instanceof SelectStatement);
        // SLASH CHARACTER
        assertTrue(users.get(4).getPsi() instanceof ErrorTokenWrapper);
        assertTrue(users.get(5).getPsi() instanceof ErrorTokenWrapper);
        assertTrue(users.get(6).getPsi() instanceof SelectStatement);
    }

    public void test_xdv_agr_dt_bloat_pkg() throws IOException {
        ASTNode root = parseScript(xdv_agr_dt_bloat_pkg);

        List<ASTNode> users = PsiUtil.visibleChildren(root);
        assertEquals(1, users.size());
        assertTrue(users.get(0).getPsi() instanceof PackageSpec);
    }
}
