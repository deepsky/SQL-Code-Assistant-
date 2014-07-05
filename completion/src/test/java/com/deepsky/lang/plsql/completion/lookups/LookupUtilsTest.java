package com.deepsky.lang.plsql.completion.lookups;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LookupUtilsTest {

    @Test
    public void test1() {

        assertEquals("create", LookupUtils.calcLookupPrefix("create package", "create"));
        assertEquals("", LookupUtils.calcLookupPrefix("create package", "craate"));
        assertEquals("", LookupUtils.calcLookupPrefix("create package", "bobauiwcreate"));
        assertEquals("create", LookupUtils.calcLookupPrefix("create package", "ywuw iwquq bobauiw create"));

        assertEquals("create pa", LookupUtils.calcLookupPrefix("create package", "create pa"));
        assertEquals("create ", LookupUtils.calcLookupPrefix("create package", "create   "));
        assertEquals("create package", LookupUtils.calcLookupPrefix("create package", "create   package"));
        assertEquals("create package", LookupUtils.calcLookupPrefix("create package", "create   package   "));

    }

}
