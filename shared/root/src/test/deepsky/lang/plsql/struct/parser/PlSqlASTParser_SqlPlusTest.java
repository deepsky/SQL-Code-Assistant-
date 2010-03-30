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

package test.deepsky.lang.plsql.struct.parser;

import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.PlainTable;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.SqlPlusCommand;
import com.deepsky.lang.plsql.psi.ddl.AlterTable;
import com.deepsky.lang.plsql.struct.impl.SelectExpressionImpl;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URISyntaxException;

public class PlSqlASTParser_SqlPlusTest extends TestCase {

    String ticketing = "ticketing.sql";
    String obj_with_link = "obj_with_link.sql";
    String sqlplus_mix1 = "sqlplus_mix1.sql";

    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("sqlplus").toURI());
    }

    public void _test_ticketing() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, ticketing));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(49, elems.length );
        assertTrue(elems[0] instanceof SqlPlusCommand);
        assertTrue(elems[1] instanceof SqlPlusCommand);
        assertTrue(elems[2] instanceof SqlPlusCommand);
        assertTrue(elems[3] instanceof SqlPlusCommand);
        assertTrue(elems[4] instanceof SqlPlusCommand);
        assertTrue(elems[5] instanceof SqlPlusCommand);

        // todo -- ....
    }


    public void test_obj_with_link() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, obj_with_link));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(40, elems.length );

        assertTrue(elems[0] instanceof SelectStatement);
        SelectStatement select0 = (SelectStatement) elems[0];
        assertEquals(1, select0.getFromClause().getTableList().length);
        assertTrue(select0.getFromClause().getTableList()[0] instanceof PlainTable);
        PlainTable t0 = (PlainTable) select0.getFromClause().getTableList()[0];
        assertEquals("tab1", t0.getTableName());                                                                             

        assertTrue(elems[1] instanceof SelectStatement);
        SelectStatement select1 = (SelectStatement) elems[1];
        assertEquals(1, select1.getFromClause().getTableList().length);
        assertTrue(select0.getFromClause().getTableList()[0] instanceof PlainTable);
        PlainTable t1 = (PlainTable) select1.getFromClause().getTableList()[0];
        assertEquals("tab2", t1.getTableName());

        assertTrue(elems[2] instanceof SelectStatement);
        SelectStatement select2 = (SelectStatement) elems[2];
        assertEquals(1, select2.getFromClause().getTableList().length);
        assertTrue(select0.getFromClause().getTableList()[0] instanceof PlainTable);
        PlainTable t2 = (PlainTable) select2.getFromClause().getTableList()[0];
        assertEquals("tan2", t2.getTableName());

        assertTrue(elems[3] instanceof AlterTable);

        assertTrue(elems[4] instanceof SqlPlusCommand);
        assertTrue(elems[5] instanceof SqlPlusCommand);
        assertTrue(elems[6] instanceof SqlPlusCommand);

        assertTrue(elems[7] instanceof AlterTable);
        assertTrue(elems[8] instanceof AlterTable);
        assertTrue(elems[9] instanceof AlterTable);
        assertTrue(elems[10] instanceof AlterTable);
        assertTrue(elems[11] instanceof AlterTable);

        assertTrue(elems[12] instanceof SqlPlusCommand);
        assertTrue(elems[13] instanceof SqlPlusCommand);
        assertTrue(elems[14] instanceof SqlPlusCommand);
        assertTrue(elems[15] instanceof SqlPlusCommand);
        assertTrue(elems[16] instanceof SqlPlusCommand);
        assertTrue(elems[17] instanceof SqlPlusCommand);
        assertTrue(elems[18] instanceof SqlPlusCommand);
        assertTrue(elems[19] instanceof SqlPlusCommand);
        assertTrue(elems[20] instanceof SqlPlusCommand);
        assertTrue(elems[21] instanceof SqlPlusCommand);

        assertTrue(elems[22] instanceof SqlPlusCommand);
        assertTrue(elems[23] instanceof SqlPlusCommand);
        assertTrue(elems[24] instanceof SqlPlusCommand);
        assertTrue(elems[25] instanceof SqlPlusCommand);
        assertTrue(elems[26] instanceof SqlPlusCommand);
        assertTrue(elems[27] instanceof SqlPlusCommand);

        assertTrue(elems[28] instanceof SqlPlusCommand);
        assertTrue(elems[29] instanceof SqlPlusCommand);

        assertTrue(elems[30] instanceof SqlPlusCommand);
        assertTrue(elems[31] instanceof SqlPlusCommand);
        assertTrue(elems[32] instanceof SqlPlusCommand);
    }


    public void test_sqlplus_mix1() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, sqlplus_mix1));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(49, elems.length );
    }
}
