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
import com.deepsky.lang.plsql.psi.ddl.AlterTablespace;
import com.deepsky.lang.plsql.psi.ddl.CreateTablespace;
import com.deepsky.lang.plsql.psi.ddl.DropTablespace;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.IOException;

public class PlSqlParser_TablespaceTest extends PlSqlParser_AbstractTest {

    String create_tablespace_mix = "create_tablespace_mix.sql";
    String drop_tablespaces = "drop_tablespaces.sql";
    String alter_tablespace = "alter_tablespace.sql";

    public PlSqlParser_TablespaceTest() {
        super("tablespaces");
    }

    public void test_create_tablespace_mix() throws IOException {
        ASTNode root = parseScript(create_tablespace_mix);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_TABLESPACE));
        assertEquals(8, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof CreateTablespace);
        assertTrue(nodes[1].getPsi() instanceof CreateTablespace);
        assertTrue(nodes[2].getPsi() instanceof CreateTablespace);
        assertTrue(nodes[3].getPsi() instanceof CreateTablespace);
        assertTrue(nodes[4].getPsi() instanceof CreateTablespace);
        assertTrue(nodes[5].getPsi() instanceof CreateTablespace);
        assertTrue(nodes[6].getPsi() instanceof CreateTablespace);
        assertTrue(nodes[7].getPsi() instanceof CreateTablespace);

        assertEquals("tbs_temp_02", ((CreateTablespace) nodes[0].getPsi()).getTablespaceName());
        assertEquals("tbs_01", ((CreateTablespace) nodes[1].getPsi()).getTablespaceName());
        assertEquals("tbs_03", ((CreateTablespace) nodes[2].getPsi()).getTablespaceName());
        assertEquals("tbs_02", ((CreateTablespace) nodes[3].getPsi()).getTablespaceName());
        assertEquals("tbs_04", ((CreateTablespace) nodes[4].getPsi()).getTablespaceName());
        assertEquals("TEST01_TS", ((CreateTablespace) nodes[5].getPsi()).getTablespaceName());
        assertEquals("undots1", ((CreateTablespace) nodes[6].getPsi()).getTablespaceName());
        assertEquals("bigtbs_01", ((CreateTablespace) nodes[7].getPsi()).getTablespaceName());
    }

    public void test_drop_tablespaces() throws IOException {
        ASTNode root = parseScript(drop_tablespaces);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.DROP_TABLESPACE));
        assertEquals(2, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof DropTablespace);
        assertTrue(nodes[1].getPsi() instanceof DropTablespace);

        assertEquals("tbs_01", ((DropTablespace) nodes[0].getPsi()).getTablespaceName());
        assertEquals("tbs_02", ((DropTablespace) nodes[1].getPsi()).getTablespaceName());
    }


    public void test_alter_tablespace() throws IOException {
        ASTNode root = parseScript(alter_tablespace);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.ALTER_TABLESPACE));
        assertEquals(11, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof AlterTablespace);
        assertTrue(nodes[1].getPsi() instanceof AlterTablespace);
        assertTrue(nodes[2].getPsi() instanceof AlterTablespace);
        assertTrue(nodes[3].getPsi() instanceof AlterTablespace);
        assertTrue(nodes[4].getPsi() instanceof AlterTablespace);
        assertTrue(nodes[5].getPsi() instanceof AlterTablespace);
        assertTrue(nodes[6].getPsi() instanceof AlterTablespace);
        assertTrue(nodes[7].getPsi() instanceof AlterTablespace);
        assertTrue(nodes[8].getPsi() instanceof AlterTablespace);
        assertTrue(nodes[9].getPsi() instanceof AlterTablespace);
        assertTrue(nodes[10].getPsi() instanceof AlterTablespace);

        assertEquals("tbs_01", ((AlterTablespace) nodes[0].getPsi()).getTablespaceName());
        assertEquals("tbs_01", ((AlterTablespace) nodes[1].getPsi()).getTablespaceName());
        assertEquals("tbs_02", ((AlterTablespace) nodes[2].getPsi()).getTablespaceName());
        assertEquals("tbs_02", ((AlterTablespace) nodes[3].getPsi()).getTablespaceName());
        assertEquals("tbs_02", ((AlterTablespace) nodes[4].getPsi()).getTablespaceName());
        assertEquals("tbs_03", ((AlterTablespace) nodes[5].getPsi()).getTablespaceName());
        assertEquals("omf_ts1", ((AlterTablespace) nodes[6].getPsi()).getTablespaceName());
        assertEquals("tbs_03", ((AlterTablespace) nodes[7].getPsi()).getTablespaceName());
        assertEquals("tbs_03", ((AlterTablespace) nodes[8].getPsi()).getTablespaceName());
        assertEquals("undots1", ((AlterTablespace) nodes[9].getPsi()).getTablespaceName());
        assertEquals("undots1", ((AlterTablespace) nodes[10].getPsi()).getTablespaceName());
    }

}
