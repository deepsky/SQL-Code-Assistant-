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
import com.deepsky.lang.plsql.psi.ddl.AlterIndex;
import com.deepsky.lang.plsql.psi.ddl.CreateIndex;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.IOException;

public class PlSqlParser_IndexTest extends PlSqlParser_AbstractTest {

    String XDV_AAI_15M_ADV_BI = "XDV_AAI_15M_ADV_BI.SQL";
    String script = "script.sql";

    public PlSqlParser_IndexTest() {
        super("indexes");
    }

    public void test_XDV_AAI_15M_ADV_BI() throws IOException {
        ASTNode root = parseScript(XDV_AAI_15M_ADV_BI);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_INDEX));
        assertEquals(22, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof CreateIndex);

        CreateIndex index = (CreateIndex) nodes[0].getPsi();
        assertEquals("XDV_AAI_15M_ADV_BI", index.getIndexName().toUpperCase());
        assertEquals("XDV_AAI_15M_INTAPA_FT", index.getTableName().toUpperCase());
    }

    public void test_script() throws IOException {
        ASTNode root = parseScript(script);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.ALTER_INDEX));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof AlterIndex);

        AlterIndex index = (AlterIndex) nodes[0].getPsi();
        assertEquals("abs_idx", index.getIndexName());
    }

}
