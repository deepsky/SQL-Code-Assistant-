package com.deepsky.lang.integration;

import com.deepsky.lang.common.PlSqlFileType;
import com.deepsky.lang.integration.utils.MyMockFileTypeManager;
import com.intellij.mock.MockApplication;
import com.intellij.mock.MockVirtualFileSystem;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileTypes.FileTypeManager;
import org.jetbrains.annotations.NotNull;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class FsAbstractTest extends PlSqlParser_AbstractTest {

    MockVirtualFileSystem fs = new MockVirtualFileSystem();

    protected FsAbstractTest(String baseDir, String ext) {
        super(baseDir, ext);
    }

    protected FsAbstractTest(String baseDir) {
        super(baseDir);
    }

    public void setUp() throws URISyntaxException {
        super.setUp();

        FileTypeManager ftManager = new MyMockFileTypeManager(PlSqlFileType.FILE_TYPE);
        Application app = ApplicationManager.getApplication();
        ((MockApplication)app).addComponent(FileTypeManager.class, ftManager);
    }

    protected Set<MContent> loadEntries(@NotNull File file) throws IOException {
        return loadEntries(file.getPath());
    }

    protected Set<MContent> loadEntries(@NotNull String file) throws IOException {
        Set<MContent> entries = new HashSet<MContent>();
        InputStream _folders = new FileInputStream(new File(file));
        BufferedReader r = new BufferedReader(new InputStreamReader(_folders));
        String str = null;
        MContent last = null;
        while ((str = r.readLine()) != null) {
            if(str.matches(" *//.*")){
                // Skip comments
                continue;
            }
            Matcher m = Pattern.compile("Root: *(.*)").matcher(str);
            if (m.find()) {
                // Root
                // Example:     Root: /home/sky/sqlcode_assistant/sql_assistant_v13/tools
                String path = m.group(1);
                if(last != null){
                    last.init();
                }
                last = new MContent(fs.findFileByPath(path));
                entries.add(last);
            } else {
                m = Pattern.compile("Excluded folder: *(.*)").matcher(str);
                if (m.find()) {
                    // Excluded folders
                    // Example:     Excluded folder: /home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/surefire-reports
                    String path = m.group(1);
                    last.addExcludeFolder(fs.findFileByPath(path));
                } else {
                    m = Pattern.compile("Source folder: *(.*)").matcher(str);
                    if (m.find()) {
                        // Excluded folders
                        // Example:     Excluded folder: /home/sky/sqlcode_assistant/sql_assistant_v13/tools/target/surefire-reports
                        String path = m.group(1);
                        last.addSourceFolder(fs.findFileByPath(path));
                    }
                }
            }
        }

        if(last != null){
            last.init();
        }

        r.close();
        _folders.close();

        return entries;
    }

}
