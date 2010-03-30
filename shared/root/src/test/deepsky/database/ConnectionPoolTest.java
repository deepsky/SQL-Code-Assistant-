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

package test.deepsky.database;

import junit.framework.TestCase;
import com.deepsky.database.*;
import com.deepsky.database.exec.SQLExecutor;
import com.deepsky.database.exec.RowSetModel;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.CacheManager3;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;

public class ConnectionPoolTest extends TestCase {

    ConnectionManagerImpl cpool;
    ConnectionInfo sess;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws DbConfigurationException {

        System.setProperty(CacheLocator.TEST_ROOT_PATH_PROP, new File(".").getAbsolutePath() + "/ttest1");

        CacheManager cacheManager = new CacheManager3();
        // todo !!!!! - null
        cpool = new ConnectionManagerImpl(null, cacheManager);
//        cpool = ConnectionManagerImpl.getInstance();
//        cpool.init();

        ConnectionInfo[] infos = cpool.getSessionList();
        for (ConnectionInfo info : infos) {
            cpool.removeSession(info);
        }

        final boolean[] updated = new boolean[]{false};
//        CacheManager3.getInstance().addListener(new CacheManagerListener() {
        cacheManager.addListener(new CacheManagerListener() {
            public void handleUpdate(int state) {
                if (state == CacheManagerListener.CACHE_UPDATED) {
                    updated[0] = true;
                }
            }
        });

        DbUrl url = new DbUrl("xdvmed", "xdvmed", "pollux.lab254.telcordia.com", "1521", "xdvmed");
        sess = cpool.createSession(url, false, false, 100);
        try {
            sess.connect();
        } catch (DBException e) {
            assertTrue(false);
        }


        try {
            while (true) {
                if (updated[0] == true) {
                    break;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
        }
    }

    public void tearDown() {
        assertTrue(sess.disconnect());
        cpool.close();
    }

    public void test1() throws DbConfigurationException, DBException {
        Reader r = new StringReader("select 1, 2 from dual");
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof SelectStatement);

        SelectStatement select = (SelectStatement) elems[0];

        SQLExecutor exec = cpool.getSQLExecutor();
        RowSetModel model = exec.executeQuery(select.getText());

        assertEquals(2, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));

        assertEquals(1, model.getFetchedRowCount());
        assertEquals("1", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
    }


    public void test10() throws DbConfigurationException, DBException {
        Reader r = new StringReader("select id, id2 from (select 22 as id, 33 as id2 from dual)");
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof SelectStatement);

        SelectStatement select = (SelectStatement) elems[0];

        SQLExecutor exec = cpool.getSQLExecutor();
        RowSetModel model = exec.executeQuery(select.getText());

        assertEquals(2, model.getColumnCount());
        assertEquals("id", model.getColumnName(0).toLowerCase());
        assertEquals("id2", model.getColumnName(1).toLowerCase());

        assertEquals(1, model.getFetchedRowCount());
        assertEquals("22", model.getValueAt(0, 0).toString());
        assertEquals("33", model.getValueAt(0, 1).toString());
    }


    public void test11() throws DbConfigurationException, DBException {
        Reader r = new StringReader(
                "select * \n" +
                "from (\n" +
                "   select 1, 2, 3\n" +
                "   from dual\n" +
                ") d1,dual d2");

        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof SelectStatement);

        SelectStatement select = (SelectStatement) elems[0];

        SQLExecutor exec = cpool.getSQLExecutor();
        RowSetModel model = exec.executeQuery(select.getText());

        assertEquals(4, model.getColumnCount());
        assertEquals("1", model.getColumnName(0).toLowerCase());
        assertEquals("2", model.getColumnName(1).toLowerCase());
        assertEquals("3", model.getColumnName(2).toLowerCase());
        assertEquals("dummy", model.getColumnName(3).toLowerCase());

        assertEquals(1, model.getFetchedRowCount());
        assertEquals("1", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
        assertEquals("3", model.getValueAt(0, 2).toString());
        assertEquals("X", model.getValueAt(0, 3).toString());
    }


    public void test12() throws DbConfigurationException, DBException {
        Reader r = new StringReader(
                "select *\n" +
                "from (\n" +
                "   select 4-1, 2, 3\n" +
                "   from dual\n" +
                "   union\n" +
                "   select 33, 23, 23\n" +
                "   from dual\n" +
                ")");

        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof SelectStatement);

        SelectStatement select = (SelectStatement) elems[0];

        SQLExecutor exec = cpool.getSQLExecutor();
        RowSetModel model = exec.executeQuery(select.getText());

        assertEquals(3, model.getColumnCount());
        assertEquals("4-1", model.getColumnName(0).toLowerCase());
        assertEquals("2", model.getColumnName(1).toLowerCase());
        assertEquals("3", model.getColumnName(2).toLowerCase());

        assertEquals(2, model.getFetchedRowCount());
        assertEquals("3", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
        assertEquals("3", model.getValueAt(0, 2).toString());

        assertEquals("33", model.getValueAt(1, 0).toString());
        assertEquals("23", model.getValueAt(1, 1).toString());
        assertEquals("23", model.getValueAt(1, 2).toString());
    }

    public void test2() throws DbConfigurationException, DBException {
        Reader r = new StringReader("select 11, t.* from xdv_adp_import_status_t t");
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof SelectStatement);

        SelectStatement select = (SelectStatement) elems[0];

        SQLExecutor exec = cpool.getSQLExecutor();
        RowSetModel model = exec.executeQuery(select.getText());

        assertEquals(19, model.getColumnCount());
        assertEquals("11", model.getColumnName(0));
        assertEquals("ID", model.getColumnName(1));
        assertEquals("ADAPTER_ID", model.getColumnName(2));
        assertEquals("PRV_ID", model.getColumnName(3));
        assertEquals("PROBE_NAME", model.getColumnName(4));
        assertEquals("ORIG_FLNAME", model.getColumnName(5));
        assertEquals("CHANGED_FLNAME", model.getColumnName(6));
        assertEquals("FLSIZE", model.getColumnName(7));
        assertEquals("PROBETS", model.getColumnName(8));
        assertEquals("READYTS", model.getColumnName(9));
        assertEquals("STAGINGTS", model.getColumnName(10));
        assertEquals("PARSEDTS", model.getColumnName(11));
        assertEquals("FLSTATUS", model.getColumnName(12));
        assertEquals("GOOD_RECS", model.getColumnName(13));
        assertEquals("BAD_RECS", model.getColumnName(14));
        assertEquals("TOTAL_RECS", model.getColumnName(15));
        assertEquals("LOADED_RECS", model.getColumnName(16));
        assertEquals("FLOW_STATE", model.getColumnName(17));
        assertEquals("FLOW_FINISHED", model.getColumnName(18));

        assertEquals(200, model.getFetchedRowCount());
    }


    public void testOrdering() throws DbConfigurationException, DBException {
        Reader r = new StringReader("select 1, 2, 3 from dual");
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof SelectStatement);

        SelectStatement select = (SelectStatement) elems[0];

        SQLExecutor exec = cpool.getSQLExecutor();
        RowSetModel model = exec.executeQuery(select.getText());

        assertEquals(3, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));
        assertEquals("3", model.getColumnName(2));

        assertEquals(1, model.getFetchedRowCount());
        assertEquals("1", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
        assertEquals("3", model.getValueAt(0, 2).toString());

        // order by first column
        model.orderByColumn(0, RowSetModel.DESCENDING, false);
        assertEquals(3, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));
        assertEquals("3", model.getColumnName(2));

        assertEquals(1, model.getFetchedRowCount());
        assertEquals("1", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
        assertEquals("3", model.getValueAt(0, 2).toString());

        // order by second column
        model.orderByColumn(1, RowSetModel.ASCENDING, false);
        assertEquals(3, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));
        assertEquals("3", model.getColumnName(2));

        assertEquals(1, model.getFetchedRowCount());
        assertEquals("1", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
        assertEquals("3", model.getValueAt(0, 2).toString());

        // order by third column
        model.orderByColumn(2, RowSetModel.DESCENDING, false);
        assertEquals(3, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));
        assertEquals("3", model.getColumnName(2));

        assertEquals(1, model.getFetchedRowCount());
        assertEquals("1", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
        assertEquals("3", model.getValueAt(0, 2).toString());
    }


    public void testOrdering2() throws DbConfigurationException, DBException {
        Reader r = new StringReader("select 1, 2, 3 from dual order by 1, 2");
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof SelectStatement);

        SelectStatement select = (SelectStatement) elems[0];

        SQLExecutor exec = cpool.getSQLExecutor();
        RowSetModel model = exec.executeQuery(select.getText());

        assertEquals(3, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));
        assertEquals("3", model.getColumnName(2));

        assertEquals(1, model.getFetchedRowCount());
        assertEquals("1", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
        assertEquals("3", model.getValueAt(0, 2).toString());

        // order by first column
        model.orderByColumn(0, RowSetModel.DESCENDING, false);
        assertEquals(3, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));
        assertEquals("3", model.getColumnName(2));

        assertEquals(1, model.getFetchedRowCount());
        assertEquals("1", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
        assertEquals("3", model.getValueAt(0, 2).toString());

        // order by second column
        model.orderByColumn(1, RowSetModel.ASCENDING, false);
        assertEquals(3, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));
        assertEquals("3", model.getColumnName(2));

        assertEquals(1, model.getFetchedRowCount());
        assertEquals("1", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
        assertEquals("3", model.getValueAt(0, 2).toString());

        // order by third column
        model.orderByColumn(2, RowSetModel.DESCENDING, false);
        assertEquals(3, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));
        assertEquals("3", model.getColumnName(2));

        assertEquals(1, model.getFetchedRowCount());
        assertEquals("1", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
        assertEquals("3", model.getValueAt(0, 2).toString());
    }


    public void testOrdering3() throws DbConfigurationException, DBException {
        Reader r = new StringReader("select 1, 2, 3 from dual union select 33, 9, 11 from dual order by 1 desc, 2");
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length);
        assertTrue(elems[0] instanceof SelectStatement);

        SelectStatement select = (SelectStatement) elems[0];

        SQLExecutor exec = cpool.getSQLExecutor();
        RowSetModel model = exec.executeQuery(select.getText());

        assertEquals(3, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));
        assertEquals("3", model.getColumnName(2));

        assertEquals(2, model.getFetchedRowCount());
        assertEquals("1", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
        assertEquals("3", model.getValueAt(0, 2).toString());
        assertEquals("33", model.getValueAt(1, 0).toString());
        assertEquals("9", model.getValueAt(1, 1).toString());
        assertEquals("11", model.getValueAt(1, 2).toString());

        // order by first column
        model.orderByColumn(0, RowSetModel.DESCENDING, false);
        assertEquals(3, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));
        assertEquals("3", model.getColumnName(2));

        assertEquals(2, model.getFetchedRowCount());
        assertEquals("33", model.getValueAt(0, 0).toString());
        assertEquals("9", model.getValueAt(0, 1).toString());
        assertEquals("11", model.getValueAt(0, 2).toString());
        assertEquals("1", model.getValueAt(1, 0).toString());
        assertEquals("2", model.getValueAt(1, 1).toString());
        assertEquals("3", model.getValueAt(1, 2).toString());

        // order by second column
        model.orderByColumn(1, RowSetModel.ASCENDING, false);
        assertEquals(3, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));
        assertEquals("3", model.getColumnName(2));

        assertEquals(2, model.getFetchedRowCount());
        assertEquals("1", model.getValueAt(0, 0).toString());
        assertEquals("2", model.getValueAt(0, 1).toString());
        assertEquals("3", model.getValueAt(0, 2).toString());
        assertEquals("33", model.getValueAt(1, 0).toString());
        assertEquals("9", model.getValueAt(1, 1).toString());
        assertEquals("11", model.getValueAt(1, 2).toString());

        // order by third column
        model.orderByColumn(2, RowSetModel.DESCENDING, false);
        assertEquals(3, model.getColumnCount());
        assertEquals("1", model.getColumnName(0));
        assertEquals("2", model.getColumnName(1));
        assertEquals("3", model.getColumnName(2));

        assertEquals(2, model.getFetchedRowCount());
        assertEquals("33", model.getValueAt(0, 0).toString());
        assertEquals("9", model.getValueAt(0, 1).toString());
        assertEquals("11", model.getValueAt(0, 2).toString());
        assertEquals("1", model.getValueAt(1, 0).toString());
        assertEquals("2", model.getValueAt(1, 1).toString());
        assertEquals("3", model.getValueAt(1, 2).toString());
    }
}
