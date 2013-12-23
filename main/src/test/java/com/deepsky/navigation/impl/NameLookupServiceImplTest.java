package com.deepsky.navigation.impl;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NameLookupServiceImplTest {

    @Test
    public void test_nameExists(){

        StringBuilder sb = new StringBuilder("name1");
        assertTrue(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("name21.name1");
        assertTrue(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("name21.name1.top");
        assertTrue(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("name1.name12.top");
        assertTrue(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("name1.name13.top");
        assertTrue(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("name198.name13.top.name1");
        assertTrue(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("name198.name13.name1.top.name12");
        assertTrue(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("name");
        assertFalse(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("name12");
        assertFalse(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("name12.name17");
        assertFalse(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("name21.name.top");
        assertFalse(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("name14.name12.top");
        assertFalse(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("supername1.name12.top");
        assertFalse(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("supername1.name12.topname1");
        assertFalse(NameLookupServiceImpl.nameExists(sb, "name1"));

        sb = new StringBuilder("supername1.aname1.topname1");
        assertFalse(NameLookupServiceImpl.nameExists(sb, "name1"));
    }
}
