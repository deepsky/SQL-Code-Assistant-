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

package com.deepsky.tools.parser;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.psi.utils.PlSqlUtil;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class BulkFileParser {

    String filenameFilter;
    int successed = 0;
    int failed = 0;
    int filesCnt = 0;
    int failed_files = 0;

    long overall_parsingTime = 0;
    long overall_traverseTime = 0;

    public BulkFileParser(String filter) {
        this.filenameFilter = filter;
    }

    private void run(String directory) {
        File dir = new File(directory);
        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("Target directory does not exist: " + dir);
            System.exit(-1);
        }

        long ms = System.currentTimeMillis();
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.matches(filenameFilter);
            }
        });

        for (File f1 : files) {
            filesCnt++;
            try {
                resolveRefs(f1);
            } catch (Throwable e) {
                failed_files++;
                System.out.println("ERROR [File] " + f1 + " Could not process: " + e.getMessage() + "\n");
            }
        }

        ms = System.currentTimeMillis() - ms;
        System.out.println("Files: " + filesCnt + " Bad files: " + failed_files);
        System.out.println("Parsing time: " + overall_parsingTime + " TraverseTime: " + overall_traverseTime);
    }


    private void resolveRefs(File file) throws IOException {
        String content = StringUtils.file2string(file);
        long ms0 = System.currentTimeMillis();

        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2(file.getName());
        ASTNode root = generator.parse(content);
        PsiElement element = root.getPsi();

        long ms1 = System.currentTimeMillis();

        final int[] errCounter = {0};
            PlSqlUtil.iterateOver(root, new PlSqlUtil.ASTNodeProcessor() {
                public void process(ASTNode node) {
                    if (node.getElementType() == PLSqlTypesAdopted.ERROR_TOKEN_A) {
                        // syntax error occurred
                        errCounter[0]++;
                    }
                }
            });


        long ms2 = System.currentTimeMillis();
        long parsingTime = ms1-ms0;
        long traverseTime = ms2-ms1;

        overall_parsingTime += parsingTime;
        overall_traverseTime += traverseTime;

        if(errCounter[0] > 0){
            System.out.println(
                    "Parsing time: " + parsingTime
                    + " Traversing time: " + traverseTime
                    + " Errors: " + errCounter[0]
                    + " File: " + file.getName()
            );
        }
    }


    public static void main(String[] args) throws IOException {

        System.out.println("Parse files in the directory and report syntax errors.");
        if (args.length < 1) {
            System.err.println("Too less arguments, expecting as least target directory and respomse file");
            System.err.println("Example: <proc> /directory_with_files [file_filter_regExp]");
            System.exit(-1);
        }
        System.out.println("Directory:      " + args[0]);
        String filter = "[^\\$].*dump$";
//        String filter = "[^\\$].*";
//        String filter = "[^\\$].*[^d][^x]$";
//        String filter = "package body!ota_network_pkg.dump";
//        String filter = "package body!ota_reference_loader_pkg.dump";
        if (args.length == 2) {
            System.out.println("File filter:  " + args[1]);
            filter = args[1];
        } else {
            System.out.println("No filter specified");
        }

        BulkFileParser conv = new BulkFileParser(filter);
        conv.run(args[0]);


        System.out.println("Done.");
    }
}
