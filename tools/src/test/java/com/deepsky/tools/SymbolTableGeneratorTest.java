package com.deepsky.tools;


import junit.framework.TestCase;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.net.URISyntaxException;

public class SymbolTableGeneratorTest extends TestCase {

    File base;

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("sql_keywords").toURI());
    }

    public void test_populateKeywordTable(){
        List<File> keywordExtSources = new ArrayList<File>();

        //case 1:
        File test1 = new File(base, "test1.kw.txt");
        keywordExtSources.add(test1);
/*
//BLOB
BODY
//BOOLEAN
BOTH
//BUFFER_POOL
//BUILTIN
UI_
*/

        Map<String, Boolean> reservedKeywords = SymbolTableGenerator.populateKeywordTable(keywordExtSources);
        assertEquals(8, reservedKeywords.size());
        assertEquals(Boolean.FALSE, reservedKeywords.get("BLOB"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("BODY"));
        assertEquals(Boolean.FALSE, reservedKeywords.get("BOOLEAN"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("BOTH"));
        assertEquals(Boolean.FALSE, reservedKeywords.get("BUFFER_POOL"));
        assertEquals(Boolean.FALSE, reservedKeywords.get("BUILTIN"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("UI_"));
    }

    public void test_populateKeywordTable2(){
        List<File> keywordExtSources = new ArrayList<File>();

        //case 2:
/*
CATALOG
//CHAR
//CHARACTER
CHAR_LENGTH
CHARACTER_LENGTH
CHECK
CLOSE
COALESCE
*/
        File test2 = new File(base, "test2.kw.txt");
        keywordExtSources.clear();
        keywordExtSources.add(test2);

        Map<String, Boolean> reservedKeywords = SymbolTableGenerator.populateKeywordTable(keywordExtSources);
        assertEquals(8, reservedKeywords.size());
        assertEquals(Boolean.TRUE, reservedKeywords.get("CATALOG"));
        assertEquals(Boolean.FALSE, reservedKeywords.get("CHAR"));
        assertEquals(Boolean.FALSE, reservedKeywords.get("CHARACTER"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("CHAR_LENGTH"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("CHARACTER_LENGTH"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("CHECK"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("CLOSE"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("COALESCE"));
    }

    public void test_populateKeywordTable3(){
        List<File> keywordExtSources = new ArrayList<File>();
        //case 3:
        keywordExtSources.clear();
        File test1 = new File(base, "test1.kw.txt");
        File test2 = new File(base, "test2.kw.txt");

        keywordExtSources.add(test1);
        keywordExtSources.add(test2);

        Map<String, Boolean> reservedKeywords = SymbolTableGenerator.populateKeywordTable(keywordExtSources);
        assertEquals(16, reservedKeywords.size());
        assertEquals(Boolean.FALSE, reservedKeywords.get("BLOB"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("BODY"));
        assertEquals(Boolean.FALSE, reservedKeywords.get("BOOLEAN"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("BOTH"));
        assertEquals(Boolean.FALSE, reservedKeywords.get("BUFFER_POOL"));
        assertEquals(Boolean.FALSE, reservedKeywords.get("BUILTIN"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("UI_"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("CATALOG"));
        assertEquals(Boolean.FALSE, reservedKeywords.get("CHAR"));
        assertEquals(Boolean.FALSE, reservedKeywords.get("CHARACTER"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("CHAR_LENGTH"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("CHARACTER_LENGTH"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("CHECK"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("CLOSE"));
        assertEquals(Boolean.TRUE, reservedKeywords.get("COALESCE"));
    }
}
