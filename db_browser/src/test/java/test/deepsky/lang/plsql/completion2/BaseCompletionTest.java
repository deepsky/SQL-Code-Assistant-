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

package test.deepsky.lang.plsql.completion2;

import com.deepsky.conf.CacheLocator;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.common.PluginKeys2;
import com.deepsky.lang.integration.FSContentTracker;
import com.deepsky.lang.integration.LocalFSChangeTracker;
import com.deepsky.lang.integration.PlSqlFileChangeTracker;
import com.deepsky.lang.plsql.completion.lookups.SelectFieldLookupElement;
import com.deepsky.lang.plsql.indexMan.FSIndexer;
import com.deepsky.lang.plsql.indexMan.IndexManagerImpl;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.intellij.codeInsight.lookup.LookupElement;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseCompletionTest extends LightFixtureCompletionTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        myFixture.setTestDataPath(getTestDataPath21());

        System.setProperty(CacheLocator.TEST_ROOT_PATH_PROP, ".");

        PluginKeys.PLSQLFILE_CHANGE_TRACKER.putData(new PlSqlFileChangeTracker(), getProject());
        IndexManager indexManager = new IndexManagerImpl(getProject());
        PluginKeys2.SQL_INDEX_MAN.putData(indexManager, getProject());

        // create SQL file indexer
        FSIndexer fsIndexer = new FSIndexer(indexManager.getFSIndex());
        fsIndexer.start();

        FSContentTracker contentTracker = new FSContentTracker();
        LocalFSChangeTracker localFSAggr = new LocalFSChangeTracker(getProject(), contentTracker, fsIndexer);
        localFSAggr.start();

        PluginKeys2.FS_CONTENT_TRACKER.putData(contentTracker, getProject());
    }


    public abstract String getPath();


    public void assertLookup(LookupElement[] elements, String... text) {

        Arrays.sort(text);
        String[] lookups = lookupElementsToPlainString(elements);
        Arrays.sort(lookups);

        assertEquals(Arrays.toString(text).toLowerCase(), Arrays.toString(lookups).toLowerCase());
    }

    static String[] funcs = new String[]{
            "abs", "add_months", "avg", "bfilename", "bitand", "bitor", "cast", "chr", "count",
            "decode", "deleting", "empty_blob", "empty_clob", "extract", "floor", "from_tz",
            "greatest", "inserting", "instr", "instr", "instr", "lag", "lead", "least", "length",
            "lower", "lpad", "lpad", "ltrim", "ltrim", "max", "min", "mod", "numtodsinterval", "numtoyminterval",
            "nvl", "nvl2", "rawtohex", "regexp_substr", "replace", "round", "round", "round", "round", "rpad",
            "rpad", "sign", "substr", "substr", "sum", "sys_extract_utc", "to_char", "to_char", "to_date",
            "to_date", "to_dsinterval", "to_number", "to_number", "to_timestamp", "to_timestamp_tz", "translate", "trim", "trunc", "trunc",
            "trunc", "updating", "upper", "userenv"
    };

    public void assertLookupFilterOutFunc(LookupElement[] elements, String... text) {

        Arrays.sort(funcs);
        Arrays.sort(text);
        String[] lookups = lookupElementsToPlainString(elements, new Filter(){
            @Override
            public boolean doFilterOut(String text) {
                return Arrays.binarySearch(funcs, text)>=0;
            }
        });
        Arrays.sort(lookups);
        assertEquals(Arrays.toString(text).toLowerCase(), Arrays.toString(lookups).toLowerCase());
    }

    public void assertSelectFieldLookup(LookupElement[] elements, String... text) {

        Arrays.sort(text);
        String[] lookups = selectFieldLookuLookupElementsToString(elements);
        Arrays.sort(lookups);

        assertEquals(Arrays.toString(text).toLowerCase(), Arrays.toString(lookups).toLowerCase());
    }

    interface Filter {
        boolean doFilterOut(String text);
    }

    private String[] lookupElementsToPlainString(LookupElement[] elements) {
        String[] out = new String[elements != null?elements.length: 0];
        for (int i = 0; elements != null && i < elements.length; i++) {
            out[i] = elements[i].getLookupString();
        }
        return out;
    }

    private String[] lookupElementsToPlainString(LookupElement[] elements, Filter filter) {
        List<String> out = new ArrayList<String>();
        for (int i = 0; elements != null && i < elements.length; i++) {
            String lookup = elements[i].getLookupString();
            if(!filter.doFilterOut(lookup)){
                out.add(lookup);
            }
        }
        return out.toArray(new String[out.size()]);
    }

    private String[] selectFieldLookuLookupElementsToString(LookupElement[] elements) {
        String[] out = new String[elements.length];
        for (int i = 0; i < elements.length; i++) {
            if(elements[i] instanceof SelectFieldLookupElement){
                String name = ((SelectFieldLookupElement) elements[i]).getSuggestedName();
                String prefix = ((SelectFieldLookupElement) elements[i]).getSuggestedQualifyName();
                out[i] = (prefix != null ? (prefix + "." + name) : name).toLowerCase();
            } else {
                out[i] = elements[i].getLookupString();
            }
        }
        return out;
    }


    public String getFilePath() {
        return getTestDataPath21() + "/" + getTestName(true).replaceAll("\\$", "/") + ".sql";
    }

    private String getTestDataPath21() {
        URL url = this.getClass().getClassLoader().getResource(getPath());
        if (url == null) {
            return new File("./").getPath();
        } else {
            try {
                return new File(url.toURI()).getPath();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
