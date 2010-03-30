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
import com.deepsky.lang.plsql.psi.SqlPlusCommand;
import com.deepsky.lang.plsql.psi.ddl.AlterTable;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URISyntaxException;

public class PlSqlASTParser_AlterTest extends TestCase {

    String alter_table = "alter_table.sql";
    String alter_table2 = "alter_table2.sql";
    String alter_table_list = "alter_table_list.sql";

    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("alter").toURI());
    }

    public void test_alter_table() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, alter_table));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(2, elems.length );
        assertTrue(elems[0] instanceof SqlPlusCommand);
        assertTrue(elems[1] instanceof AlterTable);
    }


    public void test_alter_table2() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, alter_table2));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(2, elems.length );
        assertTrue(elems[0] instanceof SqlPlusCommand);
        assertTrue(elems[1] instanceof AlterTable);
    }

    public void test_alter_table_list() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, alter_table_list));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(7, elems.length );
        assertTrue(elems[0] instanceof AlterTable);
        assertTrue(elems[1] instanceof AlterTable);
        assertTrue(elems[2] instanceof AlterTable);
        assertTrue(elems[3] instanceof AlterTable);
        assertTrue(elems[4] instanceof AlterTable);
        assertTrue(elems[5] instanceof AlterTable);
        assertTrue(elems[6] instanceof AlterTable);
    }
}
