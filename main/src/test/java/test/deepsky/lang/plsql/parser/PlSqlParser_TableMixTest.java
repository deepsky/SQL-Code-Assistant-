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
import com.deepsky.lang.plsql.psi.GenericConstraint;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class PlSqlParser_TableMixTest extends TestCase {

    String demobld7 = "DEMOBLD7.SQL";

    File base;

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("sqlplus_oracle").toURI());
    }

    public void test_demobld7() throws IOException {
        ASTNode root = parseScript(demobld7);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.TABLE_DEF));
        assertEquals(10, nodes.length);

        assertTrue(nodes[0].getPsi() instanceof TableDefinition);
        TableDefinition dept = (TableDefinition) nodes[0].getPsi();
        assertEquals("DEPT", dept.getTableName().toUpperCase());

        assertTrue(nodes[1].getPsi() instanceof TableDefinition);
        TableDefinition emp = (TableDefinition) nodes[1].getPsi();
        assertEquals("EMP", emp.getTableName().toUpperCase());
        assertEquals(8, emp.getColumnDefs().length);
        assertEquals("EMPNO", emp.getColumnDefs()[0].getColumnName());
        assertEquals("ENAME", emp.getColumnDefs()[1].getColumnName());
        assertEquals("JOB", emp.getColumnDefs()[2].getColumnName());
        assertEquals("MGR", emp.getColumnDefs()[3].getColumnName());
        assertEquals("HIREDATE", emp.getColumnDefs()[4].getColumnName());
        assertEquals("SAL", emp.getColumnDefs()[5].getColumnName());
        assertEquals("COMM", emp.getColumnDefs()[6].getColumnName());
        assertEquals("DEPTNO", emp.getColumnDefs()[7].getColumnName());

        assertTrue(nodes[2].getPsi() instanceof TableDefinition);
        TableDefinition bonus = (TableDefinition) nodes[2].getPsi();
        assertEquals("BONUS", bonus.getTableName().toUpperCase());

        assertTrue(nodes[3].getPsi() instanceof TableDefinition);
        TableDefinition salgrade = (TableDefinition) nodes[3].getPsi();
        assertEquals("SALGRADE", salgrade.getTableName().toUpperCase());


        assertTrue(nodes[4].getPsi() instanceof TableDefinition);
        TableDefinition dummy = (TableDefinition) nodes[4].getPsi();
        assertEquals("DUMMY", dummy.getTableName().toUpperCase());

        assertTrue(nodes[5].getPsi() instanceof TableDefinition);
        TableDefinition customer = (TableDefinition) nodes[5].getPsi();
        assertEquals("CUSTOMER", customer.getTableName().toUpperCase());

        assertTrue(nodes[6].getPsi() instanceof TableDefinition);
        TableDefinition ord = (TableDefinition) nodes[6].getPsi();
        assertEquals("ORD", ord.getTableName().toUpperCase());
        assertEquals(6, ord.getColumnDefs().length);
        assertEquals("ORDID", ord.getColumnDefs()[0].getColumnName());
        assertEquals("ORDERDATE", ord.getColumnDefs()[1].getColumnName());
        assertEquals("COMMPLAN", ord.getColumnDefs()[2].getColumnName());
        assertFalse(ord.getColumnDefs()[2].isNotNull());

        assertEquals("CUSTID", ord.getColumnDefs()[3].getColumnName());
        assertTrue(ord.getColumnDefs()[3].isNotNull());

        assertEquals("SHIPDATE", ord.getColumnDefs()[4].getColumnName());
        assertEquals("TOTAL", ord.getColumnDefs()[5].getColumnName());
        assertTrue(ord.getColumnDefs()[5].hasCheckConstraint());
        assertFalse(ord.getColumnDefs()[5].isNotNull());

        GenericConstraint[] constraints = ord.getConstraints();
        assertEquals(2, constraints.length);

        assertTrue(nodes[7].getPsi() instanceof TableDefinition);
        TableDefinition item = (TableDefinition) nodes[7].getPsi();
        assertEquals("ITEM", item.getTableName().toUpperCase());
    }


    private ASTNode parseScript(String file) throws IOException {
        String content = StringUtils.file2string(new File(base, file));
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        return generator.parse(content);
    }

}
