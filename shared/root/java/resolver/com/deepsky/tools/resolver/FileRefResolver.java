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

import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.DbUrlSID;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.psi.impl.types.DataTypeImpl;
import com.deepsky.lang.plsql.resolver.psibased.ReferenceExtractor2;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.lang.plsql.sqlIndex.DbUID;
import com.deepsky.lang.plsql.sqlIndex.impl.DbSchemaIndex;
import com.deepsky.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class FileRefResolver {

    public FileRefResolver(String filenameFileter) {
        this.filenameFileter = filenameFileter;
    }

    String filenameFileter;
    Writer logger;

    private void run(String _dir, String responseFile) throws IOException {
        File dir = new File(_dir);
        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("Target directory does not exist: " + dir);
            System.exit(-1);
        }

        File f = new File(responseFile); //.getAbsoluteFile();
        if (!f.exists() ||  f.isDirectory()) {
            System.err.println("Names Dump file not found: " + responseFile);
            System.exit(-1);
        }

        logger = new FileWriter(new File("file4_ref_resolver.log"));


        long ms = System.currentTimeMillis();
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.matches(filenameFileter);
            }
        });


        DbSchemaIndex cindex = new DbSchemaIndex(
                new File(f.getAbsolutePath()).getParentFile(), //f.getParentFile(),
                DbUID.getDbUID(new DbUrlSID("jdbc:oracle:thin:test@localhost:1521:ora"))
        );

        // load public synonyms
        long ms234 = System.currentTimeMillis();
        InputStream in = new FileInputStream("public_syn.idx");
        cindex.loadPublicSynonyms(in);
        ms234 = System.currentTimeMillis() - ms234;
        System.out.println("Loading sysnonyms took time (ms): " + ms234);


        // load schemas indexes
//        cindex.addIndex("sys", "sys@11.dump");
//        cindex.addIndex("sys", "sys10.idx");
        cindex.addIndex("sys", "sys11.idx");
        cindex.addIndex("test", f.getPath()); //getName());

//        ResolveHelper resolver = cindex.getResolver("test");
        AbstractSchema sindex = cindex.getSimpleIndex("test");
        

        for (File f1 : files) {
            filesCnt++;
            try {
                resolveRefs(f1, sindex);
            } catch (Throwable e) {
                failed_files++;
                System.out.println("ERROR [File] " + f1 + " Could not process: " + e.getMessage() + "\n");
            }
        }

        ms = System.currentTimeMillis() - ms;
        System.out.println("Processing took time: " + ms + " Parsing: " + parsingTime
                + " Extracting Time: " + extr_ResolveTime
                + " Pure Resolve time: " + pureResolveTime);
        System.out.println("Successed ref: " + successed + " Failed: " + failed + " Files: " + filesCnt + " Bad files: " + failed_files);

//        ms = System.currentTimeMillis();
//        cache.dumpNames(responseFile);
//        ms = System.currentTimeMillis() - ms;
//        System.out.println("Name dumping took time (ms): " + ms);

        logger.close();
    }

/*
    private class DbSchemaIndexExt extends DbSchemaIndex {
        public DbSchemaIndexExt(@NotNull File indexDir, @NotNull DbUID dbUID, String synonymList) throws IOException {
            super(indexDir, dbUID);

            long ms = System.currentTimeMillis();
            InputStream in = new FileInputStream(synonymList);
            synonymsITree.loadNames(in);

            ms = System.currentTimeMillis() - ms;
            System.out.println("Loading sysnonyms took time (ms): " + ms);
        }
    }
*/

    int successed = 0;
    int failed = 0;
    int filesCnt = 0;
    int failed_files = 0;

    long pureResolveTime;
    long parsingTime;
    long extr_ResolveTime;


    private void resolveRefs(File file, AbstractSchema sindex) throws IOException {
//        cache.resolveTime = 0;
//        cache.resolveRequest = 0;
        sindex.getResolveHelper().clearStatistics();
        System.out.println("[Resolving:Start] ====== " + file.getName() + " ========================");
        String content = StringUtils.file2string(file);
        long ms2 = System.currentTimeMillis();
        ReferenceExtractor2 refs3 = new ReferenceExtractor2(logger, sindex);
        refs3.processScript(content, file.getName());
        long ms3 = System.currentTimeMillis();
        System.out.println("[Resolving:End] ====== " + file.getName() + " ========================");


        System.out.println("Time statistics: time spent: " + (ms3 - ms2)
                + " Pure resolve time: " + refs3.getPureResolveTime()
                + " Parsing time: " + refs3.getParsingTime()
                + " Ref Extracting time: " + refs3.getExtractingTime()
        );

        System.out.println("Ref statistics: Successed: " + refs3.successed + " Failed: " + refs3.failed
                + " Ref counter: " + refs3.refCounter
                + " Unique refs: " + refs3.uniqueRefs.size() + " ResolveRequests: " //+ cache.resolveRequest
        );
        System.out.print("\n");

        pureResolveTime+=refs3.getPureResolveTime();
        parsingTime += refs3.getParsingTime();
        extr_ResolveTime += refs3.getExtractingTime();

        successed += refs3.successed;
        failed += refs3.failed;
    }


    public static void main(String[] args) throws IOException {

        System.out.println("Index files in the directory.");
        if (args.length < 2) {
            System.err.println("Too less arguments, expecting as least target directory and respomse file");
            System.err.println("Example: <proc> /directory_with_files ./names.dump.txt [file_filter_regExp]");
            System.exit(-1);
        }
        System.out.println("Directory:      " + args[0]);
        System.out.println("Names Dump file:  " + args[1]);
        String filter = "[^\\$].*dump$";
//        String filter = "[^\\$].*";
//        String filter = "[^\\$].*[^d][^x]$";
//        String filter = "package body!ota_network_pkg.dump";
//        String filter = "package body!ota_tablespace_pkg.dump";
        if (args.length == 3) {
            System.out.println("File filter:  " + args[2]);
            filter = args[2];
        } else {
            System.out.println("No filter specified");
        }

        FileRefResolver conv = new FileRefResolver(filter);
        conv.run(args[0], args[1]);


        System.out.println("Done.");
    }
}
