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

package com.deepsky.tools.resolver;

import com.deepsky.lang.plsql.resolver.index.IndexTreeBase;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.psibased.NamesIndexer;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;

import java.io.*;

public class FileIndexer {

    public FileIndexer(String filenameFileter) {
        this.filenameFileter = filenameFileter;
    }

    String filenameFileter;
    NamesIndexer indexer = new NamesIndexer();
    IndexTree itree;

    Writer logger;

    private void run(String _dir, String responseFile) throws IOException {
        File dir = new File(_dir);
        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("Target directory does not exist: " + dir);
            System.exit(-1);
        }

        File f = new File(responseFile).getAbsoluteFile();
        if (!f.getParentFile().exists() || !f.getParentFile().isDirectory() || f.isDirectory()) {
            System.err.println("Response file not available: " + responseFile);
            System.exit(-1);
        }

        logger = new FileWriter(new File(dir, "$$FileIndexer$$.log"));
        itree = new IndexTreeBase(logger);

        long ms = System.currentTimeMillis();
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.matches(filenameFileter);
            }
        });

        for (File f1 : files) {
            indexFile(f1);
        }

        ms = System.currentTimeMillis() - ms;
        System.out.println("Indexing took time (ms): " + ms);

        ms = System.currentTimeMillis();
        itree.dumpNames(responseFile);
        ms = System.currentTimeMillis() - ms;
        System.out.println("Name dumping took time (ms): " + ms);

        logger.close();
    }


    void indexFile(File file) throws IOException {
        // index file's entities
        String file2 = file.getName();
        long ms0 = System.currentTimeMillis();

        try {
            String content = StringUtils.file2string(file);
            MarkupGeneratorEx2 generator = new MarkupGeneratorEx2(file.getName());
//            MarkupGenerator generator = new MarkupGenerator(file.getAbsolutePath());
            ASTNode root = generator.parse(content);
            long ms1 = System.currentTimeMillis();

            if (root == null) {
                System.out.println("ERROR [File] " + file2 + " Could not parse");
            } else {
                int cacheSizeBefore = itree.getEntriesCount();
                indexer.index(root, itree);
                int sizeAfter = itree.getEntriesCount();
                int added = sizeAfter - cacheSizeBefore;

                long ms2 = System.currentTimeMillis();
                System.out.println("[Indexing] time (ms): " + (ms2 - ms1) + " \t[Parsing] time (ms): " + (ms1 - ms0) + " \t[Indexes] " + added + "(" + sizeAfter + ")" + "\t[File] " + file2);
            }
        } catch (Throwable e) {
            System.out.println("ERROR [File] " + file2 + " Could not process");
        }
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Index files in the directory.");
        if (args.length < 2) {
            System.err.println("Too less arguments, expecting as least target directory and response file");
            System.err.println("Example: <proc> /directory_with_files ./names.dump.txt [file_filter_regExp]");
            System.exit(-1);
        }
        System.out.println("Directory:      " + args[0]);
        System.out.println("Response file:  " + args[1]);
        String filter = ".*dump";
//        String filter ="package body!ota_network_pkg.dump";
//        String filter = "package body!ota_optimizer_stats_pkg.dump";
//        String filter = "view!ota_sta_facts_exc_part_stats_v.dump";
        if (args.length == 3) {
            System.out.println("File filter:  " + args[2]);
            filter = args[2];
        } else {
            System.out.println("No filter specified");
        }

        FileIndexer conv = new FileIndexer(filter);
        conv.run(args[0], args[1]);
        System.out.println("Done.");
    }
}
