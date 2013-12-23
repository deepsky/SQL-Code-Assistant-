package com.deepsky.lang.integration;

import com.deepsky.lang.common.PlSqlFileType;
import com.deepsky.lang.integration.utils.MyMockFileTypeManager;
import com.deepsky.lang.integration.utils.StringSet;
import com.intellij.mock.MockApplication;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class FSContentTrackerTest extends FsAbstractTest {

    Set<VirtualFile> files = new HashSet<VirtualFile>();
    Set<String> filePaths = new HashSet<String>();

    public FSContentTrackerTest() {
        super("fs_tracker", ".txt");
    }

    public void setUp() throws URISyntaxException {

        super.setUp();
        try {
            InputStream _files = getClass().getClassLoader().getResourceAsStream("fs_tracker/files.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(_files));
            String str = null;
            while ((str = r.readLine()) != null) {
                if(str.matches("^/[a-zA-Z0-9].*")){
                    files.add(fs.findFileByPath(str));
                    filePaths.add(str);
                }
            }
        } catch (Throwable e) {
            throw new Error(e);
        }

        FileTypeManager ftManager = new MyMockFileTypeManager(PlSqlFileType.FILE_TYPE);
        Application app = ApplicationManager.getApplication();
        ((MockApplication)app).addComponent(FileTypeManager.class, ftManager);
    }


    public void testInit_single_ext() throws IOException {
        // Initializes FileTypeManager with "sql" mapping
        FileTypeManager ftManager = FileTypeManager.getInstance();
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "sql");
        ftManager.removeAssociatedExtension(PlSqlFileType.FILE_TYPE, "pkb");
        ftManager.removeAssociatedExtension(PlSqlFileType.FILE_TYPE, "pks");
        // End of initialization ------------------------------------------

        Set<MContent> entries = loadEntries(getFilePath());
        FSContentTracker helper = new FSContentTracker();

        final StringSet result = new StringSet();
        helper.updateContent(FSContentTracker.ADD_CONTENT, entries, new LocalFileProcessor() {
            @Override
            public void process(VirtualFile file, int command) {
                assertFalse("Duplicate: " + file.getPath(), result.contains(file.getPath()));
                result.add(file.getPath());
                assertEquals(LocalFileProcessor.ADD_TO_INDEX, command);
            }
        });

        StringSet expected = new StringSet(filePaths)
                .filterOut(".*/target/.*")
                .filterOut(".*/spool/.*")
                .filterOut(".*/mainTwo/.*")
                .keepMatching(".*\\.sql$");

        assertEquals(expected, result);
    }


    public void testInit_multi_ext() throws IOException {
        // Initializes FileTypeManager with "sql", "pks" and "pkb" mappings
        FileTypeManager ftManager = FileTypeManager.getInstance(); //new MyMockFileTypeManager(PlSqlFileType.FILE_TYPE);
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "sql");
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "pkb");
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "pks");
        // End of initialization ------------------------------------------

        Set<MContent> entries = loadEntries(getFilePath());
        FSContentTracker helper = new FSContentTracker();

        final StringSet result = new StringSet();
        helper.updateContent(FSContentTracker.ADD_CONTENT, entries, new LocalFileProcessor() {
            @Override
            public void process(VirtualFile file, int command) {
                assertFalse("Duplicate: " + file.getPath(), result.contains(file.getPath()));
                result.add(file.getPath());
                assertEquals(LocalFileProcessor.ADD_TO_INDEX, command);
            }
        });

        StringSet expected = new StringSet(filePaths)
                .filterOut(".*/target/.*")
                .filterOut(".*/spool/.*")
                .filterOut(".*/mainTwo/.*")
                .keepMatching(".*\\.[sp][kq][bls]$");

        assertEquals(expected, result);
    }


    public void testDouble_update_multi_ext() throws IOException {

        // Initializes FileTypeManager with "sql", "pks" and "pkb" mappings
        FileTypeManager ftManager = FileTypeManager.getInstance();
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "sql");
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "pkb");
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "pks");
        // End of initialization ------------------------------------------

        Set<MContent> entries = loadEntries(getFilePath());
        FSContentTracker helper = new FSContentTracker();

        final StringSet result = new StringSet();
        helper.updateContent(FSContentTracker.ADD_CONTENT, entries, new LocalFileProcessor() {
            @Override
            public void process(VirtualFile file, int command) {
                assertFalse("Duplicate: " + file.getPath(), result.contains(file.getPath()));
                result.add(file.getPath());
                assertEquals(LocalFileProcessor.ADD_TO_INDEX, command);
            }
        });

        StringSet expected = new StringSet(filePaths)
                .filterOut(".*/target/.*")
                .filterOut(".*/spool/.*")
                .filterOut(".*/mainTwo/.*")
                .keepMatching(".*\\.[sp][kq][bls]$");

        assertEquals(expected, result);


        final StringSet result1 = new StringSet();
        // new root
        helper.updateContent(FSContentTracker.ADD_CONTENT, entries, new LocalFileProcessor() {
            @Override
            public void process(VirtualFile file, int command) {
                assertFalse("Duplicate: " + file.getPath(), result1.contains(file.getPath()));
                result1.add(file.getPath());
                assertEquals(LocalFileProcessor.ADD_TO_INDEX, command);
            }
        });

        assertEquals(expected, result1);
    }


    public void test_add_content_root() throws IOException {
        // Initializes FileTypeManager with "sql", "pks" and "pkb" mappings
        FileTypeManager ftManager = FileTypeManager.getInstance();
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "sql");
        ftManager.removeAssociatedExtension(PlSqlFileType.FILE_TYPE, "pkb");
        ftManager.removeAssociatedExtension(PlSqlFileType.FILE_TYPE, "pks");
        // End of initialization ------------------------------------------

        Set<MContent> entries = loadEntries(getFilePathFor("main_root"));
        FSContentTracker helper = new FSContentTracker();

        final StringSet result = new StringSet();
        helper.updateContent(FSContentTracker.ADD_CONTENT, entries, new LocalFileProcessor() {
            @Override
            public void process(VirtualFile file, int command) {
                assertFalse("Duplicate: " + file.getPath(), result.contains(file.getPath()));
                result.add(file.getPath());
                assertEquals(LocalFileProcessor.ADD_TO_INDEX, command);
            }
        });

        StringSet expected = new StringSet(filePaths)
                .filterOut(".*/target/.*")
                .keepMatching(".*/main/.*\\.sql$");

        assertEquals(108, expected.size());
        assertEquals(expected, result);

        final StringSet result1 = new StringSet();
        // new root
        Set<MContent> updatedRoots = loadEntries(getFilePathFor("db_browser_root"));
        helper.updateContent(FSContentTracker.ADD_CONTENT, updatedRoots, new LocalFileProcessor() {
            @Override
            public void process(VirtualFile file, int command) {
                assertFalse("Duplicate: " + file.getPath(), result1.contains(file.getPath()));
                result1.add(file.getPath());
                assertEquals(LocalFileProcessor.ADD_TO_INDEX, command);
            }
        });

        StringSet expected2 = new StringSet(filePaths)
                .filterOut(".*/target/.*")
                .keepMatching(".*/db_browser/.*\\.sql$");

        assertEquals(110, expected2.size());
        assertEquals(expected2, result1);
    }


    public void test_remove_content_root() throws IOException {
        // Initializes FileTypeManager with "sql", "pks" and "pkb" mappings
        FileTypeManager ftManager = FileTypeManager.getInstance();
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "sql");
        ftManager.removeAssociatedExtension(PlSqlFileType.FILE_TYPE, "pkb");
        ftManager.removeAssociatedExtension(PlSqlFileType.FILE_TYPE, "pks");
        // End of initialization ------------------------------------------

        // Add main and tools roots
        Set<MContent> entries = loadEntries(getFilePathFor("main_root"));
        FSContentTracker helper = new FSContentTracker();

        final StringSet result = new StringSet();
        helper.updateContent(FSContentTracker.ADD_CONTENT, entries, new LocalFileProcessor() {
            @Override
            public void process(VirtualFile file, int command) {
                assertFalse("Duplicate: " + file.getPath(), result.contains(file.getPath()));
                result.add(file.getPath());
                assertEquals(LocalFileProcessor.ADD_TO_INDEX, command);
            }
        });

        entries = loadEntries(getFilePathFor("db_browser_root"));
        helper.updateContent(FSContentTracker.ADD_CONTENT, entries, new LocalFileProcessor() {
            @Override
            public void process(VirtualFile file, int command) {
                assertFalse("Duplicate: " + file.getPath(), result.contains(file.getPath()));
                result.add(file.getPath());
                assertEquals(LocalFileProcessor.ADD_TO_INDEX, command);
            }
        });

        StringSet expected = new StringSet(filePaths)
                .filterOut(".*/target/.*")
                .keepMatching(".*/main/.*\\.sql$", ".*/db_browser/.*\\.sql$");


        assertEquals(218, expected.size());
        assertEquals(expected, result);

        final StringSet result1 = new StringSet();
        // root being deleted
        entries = loadEntries(getFilePathFor("main_root"));
        helper.updateContent(FSContentTracker.REMOVE_CONTENT, entries, new LocalFileProcessor() {
            @Override
            public void process(VirtualFile file, int command) {
                assertFalse("Duplicate: " + file.getPath(), result1.contains(file.getPath()));
                result1.add(file.getPath());
                assertEquals(LocalFileProcessor.REMOVE_FROM_INDEX, command);
            }
        });

        StringSet expected2 = new StringSet(filePaths)
                .filterOut(".*/target/.*")
                .keepMatching(".*/main/.*\\.sql$");
        assertEquals(108, expected2.size());
        assertEquals(expected2, result1);
    }


    public void testSource_added_to_main_root() throws IOException {
        // Initializes FileTypeManager with "sql", "pks" and "pkb" mappings
        FileTypeManager ftManager = FileTypeManager.getInstance();
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "sql");
        ftManager.removeAssociatedExtension(PlSqlFileType.FILE_TYPE, "pkb");
        ftManager.removeAssociatedExtension(PlSqlFileType.FILE_TYPE, "pks");
        // End of initialization ------------------------------------------

        // Add main and tools roots
        Set<MContent> before = loadEntries(getFilePathFor("long_list_root"));
        Set<MContent> after = loadEntries(getFilePath());
        final StringSet result = new StringSet();

        FSContentTracker.identifyChanges(before, after, new MContentHandler() {
            @Override
            public void sourceRootAdded(MContent e, VirtualFile sourceRoot) {
                assertEquals("/home/sky/sqlcode_assistant/sql_assistant_v13/main", e.getRoot().getPath());
                result.add(sourceRoot.getPath());
            }

            @Override
            public void sourceRootRemoved(MContent e, VirtualFile sourceRoot) {
                assertFalse("Wrong deletion of source root", true);
            }

            @Override
            public void excludeRootAdded(MContent e, VirtualFile excludeRoot) {
                assertFalse("Wrong addition of exclude root", true);
            }

            @Override
            public void excludeRootRemoved(MContent e, VirtualFile excludeRoot) {
                assertFalse("Wrong deletion of exclude root", true);
            }
        });

        assertEquals(2, result.size());
        assertTrue(result.contains("/home/sky/sqlcode_assistant/sql_assistant_v13/main/sql/resources"));
        assertTrue(result.contains("/home/sky/sqlcode_assistant/sql_assistant_v13/main/antlr"));
    }


    public void testExclude_removed_from_tools() throws IOException {
        // Initializes FileTypeManager with "sql", "pks" and "pkb" mappings
        FileTypeManager ftManager = FileTypeManager.getInstance();
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "sql");
        ftManager.removeAssociatedExtension(PlSqlFileType.FILE_TYPE, "pkb");
        ftManager.removeAssociatedExtension(PlSqlFileType.FILE_TYPE, "pks");
        // End of initialization ------------------------------------------

        // Add main and tools roots
        Set<MContent> before = loadEntries(getFilePathFor("long_list_root"));
        Set<MContent> after = loadEntries(getFilePath());
        final StringSet result = new StringSet();

        FSContentTracker.identifyChanges(before, after, new MContentHandler() {
            @Override
            public void sourceRootAdded(MContent e, VirtualFile sourceRoot) {
                assertFalse("Wrong addition of source root", true);
            }

            @Override
            public void sourceRootRemoved(MContent e, VirtualFile sourceRoot) {
                assertFalse("Wrong deletion of source root", true);
            }

            @Override
            public void excludeRootAdded(MContent e, VirtualFile excludeRoot) {
                assertFalse("Wrong addition of exclude root", true);
            }

            @Override
            public void excludeRootRemoved(MContent e, VirtualFile excludeRoot) {
                assertEquals("/home/sky/sqlcode_assistant/sql_assistant_v13/tools", e.getRoot().getPath());
                result.add(excludeRoot.getPath());
            }
        });

        assertEquals(2, result.size());
        assertTrue(result.contains("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/classes"));
        assertTrue(result.contains("/home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/surefire"));
    }


    public void test_updateContentChanges_remove_excludes() throws IOException {
        // Initializes FileTypeManager with "sql", "pks" and "pkb" mappings
        FileTypeManager ftManager = FileTypeManager.getInstance();
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "sql");
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "pkb");
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "pks");
        // End of initialization ------------------------------------------

        Set<MContent> before = loadEntries(getFilePathFor("long_list_root2"));
        FSContentTracker helper = new FSContentTracker();

        final StringSet result = new StringSet();
        helper.updateContent(FSContentTracker.ADD_CONTENT, before, new LocalFileProcessor() {
            @Override
            public void process(VirtualFile file, int command) {
                assertFalse("Duplicate: " + file.getPath(), result.contains(file.getPath()));
                result.add(file.getPath());
                assertEquals(LocalFileProcessor.ADD_TO_INDEX, command);
            }
        });

        StringSet expected = new StringSet(filePaths)
                .filterOut(".*/target/.*")
                .filterOut(".*/mainTwo/.*")
                .filterOut(".*/spool/.*")
                .keepMatching(".*\\.sql$", ".*\\.pkb$", ".*\\.pks$");

        assertEquals(234, expected.size());
        assertEquals(expected, result);

        final StringSet result1 = new StringSet();
        Set<MContent> after = loadEntries(getFilePathFor("long_list_root2_remove_main_target"));
        helper.updateContentChanges(before, after, new LocalFileProcessor() {
            @Override
            public void process(VirtualFile file, int command) {
                assertFalse("Duplicate: " + file.getPath(), result1.contains(file.getPath()));
                result1.add(file.getPath());
                assertEquals(LocalFileProcessor.ADD_TO_INDEX, command);
            }
        });

        StringSet expected2 = new StringSet(filePaths)
                .keepMatching(".*/main/target/.*\\.sql$", ".*/main/target/.*\\.pkb$", ".*/main/target/.*\\.pks$");

        assertEquals(123, expected2.size());
        assertEquals(expected2, result1);
    }


    public void _test_processFilesRecursively() throws IOException {
        // Initializes FileTypeManager with "sql", "pks" and "pkb" mappings
        FileTypeManager ftManager = FileTypeManager.getInstance();
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "sql");
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "pkb");
        ftManager.associateExtension(PlSqlFileType.FILE_TYPE, "pks");
        // End of initialization ------------------------------------------

        Set<MContent> entries = loadEntries(getFilePathFor("db_browser_root"));
        assertEquals(1, entries.size());
        MContent content = entries.toArray(new MContent[1])[0];

        assertEquals("/home/sky/sqlcode_assistant/sql_assistant_v13/db_browser", content.getRoot().getPath());
        final VirtualFile[] excludedFolders = content.getExcludeFolders();
        FSContentTracker.processFilesRecursively(content.getRoot(), new Processor<VirtualFile>() {
            public boolean process(VirtualFile virtualFile) {
                if (FSContentTracker.isFileInExcludedContent(virtualFile, excludedFolders)) {
                    // File is already in excluded content, no need to remove
                    return false;
                } else {
                    // put file in the queue for removal form the index
                    //enqueueFile(virtualFile, LocalFileProcessor.REMOVE_FROM_INDEX, processor);
                }
                return true;
            }
        });

    }

}
