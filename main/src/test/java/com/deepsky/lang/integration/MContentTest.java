package com.deepsky.lang.integration;


import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MContentTest extends FsAbstractTest {

    public MContentTest() {
        super("mcontent", ".txt");
    }

    public void testNot_intersected() throws IOException {
        Set<MContent> entries = loadEntries(getFilePath());

        assertEquals(1, entries.size());
        MContent content = entries.toArray(new MContent[1])[0];

        VirtualFile[] sources = content.getSourceFolders();
        assertEquals(2, sources.length);
        Set<VirtualFile> s1 = new HashSet<VirtualFile>(Arrays.asList(sources));
        assertEquals(2, s1.size());
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src")));
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/sql")));

        VirtualFile[] excluded = content.getExcludeFolders();
        assertEquals(3, excluded.length);
        Set<VirtualFile> s2 = new HashSet<VirtualFile>(Arrays.asList(excluded));
        assertEquals(3, s2.size());
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/surefire-reports")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/classes")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/test-classes")));
    }


    public void testExclude_intersected() throws IOException {
        Set<MContent> entries = loadEntries(getFilePath());

        assertEquals(1, entries.size());
        MContent content = entries.toArray(new MContent[1])[0];

        VirtualFile[] sources = content.getSourceFolders();
        assertEquals(2, sources.length);
        Set<VirtualFile> s1 = new HashSet<VirtualFile>(Arrays.asList(sources));
        assertEquals(2, s1.size());
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src")));
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/sql")));

        VirtualFile[] excluded = content.getExcludeFolders();
        assertEquals(4, excluded.length);
        Set<VirtualFile> s2 = new HashSet<VirtualFile>(Arrays.asList(excluded));
        assertEquals(4, s2.size());
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/surefire-reports")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/classes")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/test-classes")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/maven-archiver")));
    }

    public void testSource_intersected() throws IOException {
        Set<MContent> entries = loadEntries(getFilePath());

        assertEquals(1, entries.size());
        MContent content = entries.toArray(new MContent[1])[0];

        VirtualFile[] sources = content.getSourceFolders();
        assertEquals(2, sources.length);
        Set<VirtualFile> s1 = new HashSet<VirtualFile>(Arrays.asList(sources));
        assertEquals(2, s1.size());
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src")));
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/antlr")));

        VirtualFile[] excluded = content.getExcludeFolders();
        assertEquals(3, excluded.length);
        Set<VirtualFile> s2 = new HashSet<VirtualFile>(Arrays.asList(excluded));
        assertEquals(3, s2.size());
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/surefire-reports")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/classes")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/test-classes")));
    }

    public void testExclude_under_sources() throws IOException {
        Set<MContent> entries = loadEntries(getFilePath());

        assertEquals(1, entries.size());
        MContent content = entries.toArray(new MContent[1])[0];

        VirtualFile[] sources = content.getSourceFolders();
        assertEquals(2, sources.length);
        Set<VirtualFile> s1 = new HashSet<VirtualFile>(Arrays.asList(sources));
        assertEquals(2, s1.size());
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src")));
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/sql")));

        VirtualFile[] excluded = content.getExcludeFolders();
        assertEquals(5, excluded.length);
        Set<VirtualFile> s2 = new HashSet<VirtualFile>(Arrays.asList(excluded));
        assertEquals(5, s2.size());
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/surefire-reports")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/classes")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/test-classes")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src/test/resources")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src/test/spec")));
    }


    public void testExclude_under_sources2() throws IOException {
        Set<MContent> entries = loadEntries(getFilePath());

        assertEquals(1, entries.size());
        MContent content = entries.toArray(new MContent[1])[0];

        VirtualFile[] sources = content.getSourceFolders();
        assertEquals(2, sources.length);
        Set<VirtualFile> s1 = new HashSet<VirtualFile>(Arrays.asList(sources));
        assertEquals(2, s1.size());
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src")));
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/sql")));

        VirtualFile[] excluded = content.getExcludeFolders();
        assertEquals(5, excluded.length);
        Set<VirtualFile> s2 = new HashSet<VirtualFile>(Arrays.asList(excluded));
        assertEquals(5, s2.size());
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/surefire-reports")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/classes")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/test-classes")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src/test/resources")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src/test/spec")));
    }


    public void testSources_under_exclude() throws IOException {
        Set<MContent> entries = loadEntries(getFilePath());

        assertEquals(1, entries.size());
        MContent content = entries.toArray(new MContent[1])[0];

        VirtualFile[] sources = content.getSourceFolders();
        assertEquals(2, sources.length);
        Set<VirtualFile> s1 = new HashSet<VirtualFile>(Arrays.asList(sources));
        assertEquals(2, s1.size());
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src")));
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/sql")));

        VirtualFile[] excluded = content.getExcludeFolders();
        assertEquals(3, excluded.length);
        Set<VirtualFile> s2 = new HashSet<VirtualFile>(Arrays.asList(excluded));
        assertEquals(3, s2.size());
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src/test/resources")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src/test/spec")));
    }


    public void testSources_under_exclude2() throws IOException {
        Set<MContent> entries = loadEntries(getFilePath());

        assertEquals(1, entries.size());
        MContent content = entries.toArray(new MContent[1])[0];

        VirtualFile[] sources = content.getSourceFolders();
        assertEquals(2, sources.length);
        Set<VirtualFile> s1 = new HashSet<VirtualFile>(Arrays.asList(sources));
        assertEquals(2, s1.size());
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src")));
        assertTrue(s1.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/sql")));

        VirtualFile[] excluded = content.getExcludeFolders();
        assertEquals(3, excluded.length);
        Set<VirtualFile> s2 = new HashSet<VirtualFile>(Arrays.asList(excluded));
        assertEquals(3, s2.size());
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src/test/resources")));
        assertTrue(s2.contains(fs.findFileByPath("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/src/test/spec")));
    }
}
