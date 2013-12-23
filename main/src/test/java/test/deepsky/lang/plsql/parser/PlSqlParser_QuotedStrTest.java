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
import com.deepsky.lang.plsql.psi.SelectFieldExpr;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.StringLiteral;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.TokenSet;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.IOException;

public class PlSqlParser_QuotedStrTest extends PlSqlParser_AbstractTest {

    String select1 = "select1.sql";

    public PlSqlParser_QuotedStrTest() {
        super("quoted_str");
    }

    public void test_select1() throws IOException {

        ASTNode root = parseScript(select1);

        assertNotNull(root);
        assertTrue(root.getPsi() instanceof PsiFile);
        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.SELECT_EXPRESSION));
        assertEquals(6, nodes.length);


        ASTNode column = nodes[0].findChildByType(PlSqlElementTypes.EXPR_COLUMN);
        assertNotNull(column);
        ASTNode str_lit = column.findChildByType(PlSqlElementTypes.STRING_LITERAL);
        assertNotNull(str_lit);
        assertEquals("'hello'", str_lit.getText());

        ASTNode column3 = nodes[3].findChildByType(PlSqlElementTypes.EXPR_COLUMN);
        assertNotNull(column3);
        ASTNode str_lit3 = column3.findChildByType(PlSqlElementTypes.STRING_LITERAL);
        assertNotNull(str_lit3);
        assertEquals("q'[7484]'", str_lit3.getText());

        ASTNode column4 = nodes[4].findChildByType(PlSqlElementTypes.EXPR_COLUMN);
        assertNotNull(column4);
        ASTNode arith4 = column4.findChildByType(PlSqlElementTypes.ARITHMETIC_EXPR);
        assertNotNull(arith4);
        ASTNode[] nodes4 = arith4.getChildren(TokenSet.create(PlSqlElementTypes.STRING_LITERAL, PlSqlElementTypes.NUMERIC_LITERAL));
        assertEquals(3, nodes4.length);
        assertEquals(PlSqlElementTypes.STRING_LITERAL, nodes4[0].getElementType());
        assertEquals(PlSqlElementTypes.NUMERIC_LITERAL, nodes4[1].getElementType());
        assertEquals(PlSqlElementTypes.STRING_LITERAL, nodes4[2].getElementType());

        assertEquals("'hello'", nodes4[0].getText());
        assertEquals("1", nodes4[1].getText());
        assertEquals("q'(7484)'", nodes4[2].getText());

        ASTNode column5 = nodes[5].findChildByType(PlSqlElementTypes.EXPR_COLUMN);
        assertNotNull(column5);
        ASTNode arith5 = column5.findChildByType(PlSqlElementTypes.ARITHMETIC_EXPR);
        assertNotNull(arith5);
        ASTNode[] nodes5 = arith5.getChildren(TokenSet.create(PlSqlElementTypes.STRING_LITERAL, PlSqlElementTypes.NUMERIC_LITERAL));
        assertEquals(2, nodes5.length);
        assertEquals(PlSqlElementTypes.STRING_LITERAL, nodes5[0].getElementType());
        assertEquals(PlSqlElementTypes.STRING_LITERAL, nodes5[1].getElementType());

        assertEquals("q'[7484\n" +
                "    LLL o UUU\n" +
                "    NMB]'", nodes5[0].getText().replace("\r", ""));
        assertEquals("'eee'", nodes5[1].getText());
    }

}
