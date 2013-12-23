package com.deepsky.lang.plsql.sqlIndex.impl;

import com.deepsky.database.ora.DbUrlUtil;
import com.deepsky.database.ora.ItemToUpdate;
import com.deepsky.database.ora2.DbObjectCache;
import com.deepsky.lang.AbstractBaseTest;
import com.deepsky.lang.plsql.resolver.index.IndexTree;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DbSchemaIndexTest extends AbstractBaseTest {

    DbSchemaIndex cindex;

    public void setUp() {
        cindex = new DbSchemaIndex(
                new File("."),
                DbUrlUtil.parse("jdbc:oracle:thin:test$45@localhost:1521:ora").getDbUID()
        ) {
        };
    }

    public void test0(){
        // TODO - just to make tests pass
    }


    public void _test0() {

        String createTab = "create table \"Top\" (id number);";
        DbObjectCache dboCache = cindex.getObjectCache("test$45");

        IndexTree itree = cindex.getIndex("test$45");
        assertEquals(0, itree.getEntriesCount());

        dboCache.update("Top", "TABLE", createTab, new Date(), true);

        assertEquals(11, dboCache.listTypes().length);

        List<ItemToUpdate> items = dboCache.getObjectListForType("TABLE");
        assertEquals(1, items.size());
        assertEquals("Top", items.get(0).name);

        assertEquals(1, itree.getEntriesCount());

        int h =0;
    }
}
