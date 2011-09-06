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

package com.deepsky.lang.plsql.sqlIndex.impl;

import com.deepsky.findUsages.windex.SqlScriptIndexer;
import com.deepsky.findUsages.wordProc.FileProcessor;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.lang.plsql.sqlIndex.SqlFile;
import com.deepsky.lang.plsql.sqlIndex.WordIndexManager;
import com.deepsky.lang.plsql.struct.index.BuildIndexException;
import com.deepsky.utils.StringUtils;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class WordIndexManagerImpl implements WordIndexManager {

    final private static String WIDX_EXT = "widx";
    final private static int WIDX_MASK = 0xF;

    File indexHomeDir;
    //SqlDomainIndex sqlIndex;
    AbstractSchema sindex;

//    public WordIndexManagerImpl(File indexHomeDir) {
//        this.indexHomeDir = indexHomeDir;
//    }

    public WordIndexManagerImpl(AbstractSchema sindex){ //SqlDomainIndex sqlIndex) {
//        this.sqlIndex = sqlIndex;
        this.sindex = sindex;
        this.indexHomeDir = new File(sindex.getIndexPath()); //sqlIndex.getIndexHomeDir();
    }

    public void updateIndexForFile(String filePath) {
        File ff = new File(filePath);
        if(!ff.isAbsolute()){
            ff = new File(indexHomeDir, filePath);
        }
        try {
             // if file exists index it otherwise text with zero length means file was deleted
            String text = "";
            if(ff.exists()){
                text = StringUtils.file2string(ff);
            }
            String index = SqlScriptIndexer.buildIndex(text);
            appendIndexEntry(filePath, index);
        } catch (IOException e) {
            // todo -- report error
            e.printStackTrace();
        } catch (BuildIndexException e) {
            // todo -- report error
            e.printStackTrace();
        }
    }

    public void updateIndexForFiles(File[] fullPaths) {

        Map<String, List<String>> widxNames = new HashMap<String, List<String>>();
        for (File f : fullPaths) {
            String correctedPath;
            if (f.getAbsolutePath().startsWith(indexHomeDir.getAbsolutePath())) {
                String t = f.getAbsolutePath().substring(indexHomeDir.getAbsolutePath().length());
                correctedPath = t.charAt(0) == '/' || t.charAt(0) == '\\' ? t.substring(1) : t;
            } else {
                correctedPath = f.getPath();
            }
            String widx = fileName2widxName(correctedPath);
            List<String> l = widxNames.get(widx); //fileName2widxName(f.getPath()));
            if (l == null) {
                l = new ArrayList<String>();
                widxNames.put(widx, l);
            }
            l.add(correctedPath);
        }

        for (Map.Entry<String, List<String>> e : widxNames.entrySet()) {
            Writer writer = null;
            long ms = System.currentTimeMillis();
            int entries = 0;
            try {
                writer = new FileWriter(new File(indexHomeDir, e.getKey()), true);
                for (String filePath : e.getValue()) {
                    entries++;
                    try {
                        File f = isPathRelative(filePath) ? new File(indexHomeDir, filePath) : new File(filePath);
                        String text = StringUtils.file2string(f); //new File(relPath));
                        String index = SqlScriptIndexer.buildIndex(text);

                        String entry = encodeWidxEntry(filePath, index);
                        writer.write(entry);
                    } catch (IOException e1) {
                        // todo -- report error
                        e1.printStackTrace();
                    } catch (BuildIndexException e1) {
                        // todo -- report error
                        e1.printStackTrace();
                    }
                }

            } catch (IOException e1) {
                // todo -- report error
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } finally {
                if (writer != null)
                    try {
                        writer.close();
                    } catch (IOException e2) {
                        // ignore
                    }
            }

            long ms1 = System.currentTimeMillis();
            System.out.println("Index: " + e.getKey() + " entries: " + entries + " time spent: " + (ms1 - ms));
        }
    }

    private boolean isPathRelative(String filePath) {
        if (filePath.charAt(0) == '/' || filePath.charAt(0) == '\\') {
            return false;
        } else if (filePath.length() > 1 && filePath.charAt(1) == ':') {
            return false;
        }
        return true;
    }


    public void deleteIndexForFile(String fullPath) {
        appendIndexEntry(fullPath, "");
    }

    private void appendIndexEntry(String fullPath, String idx) {
        String widx = fileName2widxName(fullPath);

        // save index
        Writer writer = null;
        try {
            writer = new FileWriter(new File(indexHomeDir, widx), true);
            String entry = encodeWidxEntry(fullPath, idx);
            writer.write(entry);
        } catch (IOException e) {
            // ignore
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    // ignore
                }
        }
    }

    public boolean rebuildIndex(String widxName) {
        Map<String, String> idxEntries = new HashMap<String, String>();

        File f = new File(indexHomeDir, widxName);
        BufferedReader r = null;
        boolean newFile = false;
        try {
            r = new BufferedReader(new FileReader(f));
            String s = null;
            while ((s = r.readLine()) != null) {
                String fileName = extractFileName(s);
                String idx = extractIndex(s);
                idxEntries.put(fileName, idx);
            }

        } catch (FileNotFoundException e) {
            // file does not exist, consider it as a new one
            newFile = true;
        } catch (IOException e) {
            // skip the file
        } finally {
            if (r != null)
                try {
                    r.close();
                } catch (IOException e) {
                    // ignore
                }
        }

        // delete old index file
        if (!(newFile || f.delete())) {
            //todo -- cannot delete old index!
            return false;
        }

        // save index
        Writer writer = null;
        try {
            writer = new FileWriter(f);
            for (Map.Entry<String, String> e : idxEntries.entrySet()) {
                String entry = encodeWidxEntry(e.getKey(), e.getValue());
                writer.write(entry);
            }
        } catch (IOException e) {
            // ignore
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    // ignore
                }
        }

        return true;
    }

    private List<String> uniqList(String[] input) {
        List<String> target = Arrays.asList(input);
        Collections.sort(target);

        List<String> out = new ArrayList<String>();
        for (String s : target) {
            if (out.size() == 0 || !out.get(out.size() - 1).equalsIgnoreCase(s)) {
                out.add(s.toLowerCase());
            }
        }
        return out;
    }

    /**
     * Search widx files in the specified directory for occurence of the 'text',
     * call iterator.processFile for each file containing the 'text'.
     *
     * @param text     - target word list to search
     * @param iterator - listener found files
     * @return -  array of widx file names needed in rebuilding (more then 30% entries are duplicates )
     */
    public String[] scanDir(
            @NotNull final Project project,
            @NotNull String[] text,
            @NotNull final FileProcessor iterator) {

        // remove duplicates from the target text
        List<String> targetWords = uniqList(text);
        // prepair text for search
        for (int i = 0; i < targetWords.size(); i++) {
            targetWords.set(i, "." + targetWords.get(i) + ".");
        }


//        String targetWord = "." + text.toLowerCase() + ".";
//        Set<String> hitTable = new HashSet<String>();
        final Map<String, Set<String>> hitTable2 = new HashMap<String, Set<String>>();
        Set<String> tableSet = new HashSet<String>();
        Map<String, Integer> duplicatesTable = new HashMap<String, Integer>();
        Map<String, CountHelper> widxStat = new HashMap<String, CountHelper>();

        for (File f : generateIdxFileName(indexHomeDir)) { //widxList){
            int idxCount = 0;
            BufferedReader r = null;
            try {
                r = new BufferedReader(new FileReader(f));
                String s = null;
                while ((s = r.readLine()) != null) {
                    idxCount++;
                    String fileName = extractFileName(s);
                    String idx = extractIndex(s);

                    for (String word : targetWords) {
                        if (idx.indexOf(word) != -1) {
                            // hitted!
                            addHit(hitTable2, fileName, word);
                        } else {
                            // when we have an obsoleted index string
                            // this index should be removed
                            removeHit(hitTable2, fileName, word);
                        }
                    }

                    if (!tableSet.add(fileName)) {
                        // duplicate found
                        Integer counter = duplicatesTable.put(fileName, 1);
                        if (counter != null) {
                            duplicatesTable.put(fileName, counter + 1);
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                // skip the file
            } catch (IOException e) {
                // skip the file
            } finally {
                if (r != null)
                    try {
                        r.close();
                    } catch (IOException e) {
                        // ignore
                    }
            }

            widxStat.put(f.getName(), new CountHelper(idxCount));
        }

        for (final String fileName : hitTable2.keySet()) {
            final String encodedName = ContextPathUtil.encodeFilePathCtx(fileName);
            final VirtualFile vf1 = sindex.getSourceFile(encodedName);
            if(vf1 == null){
                // file was deleted or moved?
                continue;
            }

            ApplicationManager.getApplication().runReadAction(new Runnable() {
                public void run() {
                   String filePath = vf1.getPath();
                   PsiFile file = null;
                   if (filePath != null) {
                       final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
                       for (VirtualFile v : fileEditorManager.getOpenFiles()) {
                           if (v.getPath().equals(filePath)) {
                               //
                               file = PsiManager.getInstance(project).findFile(v);
                              break;
                           }
                       }
                   }

                   if(file == null){
                      VirtualFile vf = sindex.getSourceFile(encodedName);
                      file = PsiManager.getInstance(project).findFile(vf);
                   }

                   if(file != null){
                      Set<String> words = hitTable2.get(fileName);
                      iterator.processFile((PlSqlElement) file, words.toArray(new String[0]));
                   }
                }
            });

/*
            try {
                File f = isPathRelative(fileName)? new File(indexHomeDir, fileName): new File(fileName);

                String content = StringUtils.file2string(f);
                MarkupGeneratorEx2 generator = new MarkupGeneratorEx2(fileName);
                ASTNode root = generator.parse(content);
                PsiElement element = root.getPsi();
                ((ResolveProvider) element).setResolver(sqlIndex.getDomainResolver());

                iterator.processFile((PlSqlElement) element);

//                Runtime.getRuntime().gc();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/
        }

        // process duplicate indexes
        Set<String> widxToRebuild = new HashSet<String>();
        // Calculate statistics for each word index
        // and choose word index file with duplicates more then 30%
        for (Map.Entry<String, Integer> e : duplicatesTable.entrySet()) {
            //
            String widx = fileName2widxName(e.getKey());
            CountHelper ch = widxStat.get(widx);
            if (ch == null) {
                // looks impossible! design mistake?
                continue;
            }

            ch.duplicateCount += e.getValue();
            if (ch.idxCount / 3 < ch.duplicateCount) {
                widxToRebuild.add(widx);
            }
        }

        return widxToRebuild.toArray(new String[widxToRebuild.size()]);
    }


    private void addHit(Map<String, Set<String>> tab, String fileName, String word) {
        Set<String> words = tab.get(fileName);
        if (words == null) {
            words = new HashSet<String>();
            tab.put(fileName, words);
        }
        words.add(word.substring(1, word.length() - 1));
    }

    private void removeHit(Map<String, Set<String>> tab, String fileName, String word) {
        Set<String> words = tab.get(fileName);
        if (words != null) {
            words.remove(word.substring(1, word.length() - 1));
            if (words.size() == 0) {
                tab.remove(fileName);
            }
        }
    }

    public static File[] generateIdxFileName(File indexHomeDir) {

        File[] out = new File[WIDX_MASK + 1];
        for (int i = 0; i < WIDX_MASK + 1; i++) {
            out[i] = new File(indexHomeDir, i + "." + WIDX_EXT);
        }
        return out;
    }

    private String encodeWidxEntry(String key, String value) {
        return key + "*" + value + "\n";
    }

    private String fileName2widxName(String fileName) {
        return Integer.toString(fileName.hashCode() & WIDX_MASK) + "." + WIDX_EXT;
    }

    // "<file_name>*<index_content>"
    // where index_content is: ".<text1>.<text2>. .. .<textN>."

    private String extractIndex(String s) {
        String[] pair = s.split("\\*");
        return pair.length == 2 ? pair[1] : "";
    }

    private String extractFileName(String s) {
        String[] pair = s.split("\\*");
        return pair.length > 0 ? pair[0] : "";
    }


    static class CountHelper {
        public int idxCount;
        public int duplicateCount;

        public CountHelper(int idxCount) {
            this.idxCount = idxCount;
        }
    }
}
