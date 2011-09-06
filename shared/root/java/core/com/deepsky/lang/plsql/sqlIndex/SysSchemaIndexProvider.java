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

package com.deepsky.lang.plsql.sqlIndex;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.index.IndexTreeBase;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SysSchemaIndexProvider {

    LoggerProxy log = LoggerProxy.getInstance("SysSchemaIndexProvider");

    public final static int SYS_9 = 9;
    public final static int SYS_10 = 10;
    public final static int SYS_11 = 11;

    private File indexBaseDir;
    private Map<Integer, IndexSpec> version2Index = new HashMap<Integer, IndexSpec>();


    // load SYS schema indexes from FS
    public SysSchemaIndexProvider(String baseDir) {
        // generate SYS index base directory
        String indexDirName = StringUtils.hash_MD5("sys");
        indexBaseDir = new File(baseDir, indexDirName);
        if (!indexBaseDir.exists()) {
            // try to create
            if (!indexBaseDir.mkdir()) {
                throw new ConfigurationException("Cannot create SYS base directory: " + indexBaseDir.getAbsolutePath());
            }
        }

        version2Index.put(SYS_9, loadIndexFromFS("sys9", indexBaseDir));
        version2Index.put(SYS_10, loadIndexFromFS("sys10", indexBaseDir));
        version2Index.put(SYS_11, loadIndexFromFS("sys11", indexBaseDir));
    }

    // load SYS schema indexes from JAR file
    public SysSchemaIndexProvider() {
        String indexDirName = StringUtils.hash_MD5("sys");
        indexBaseDir = new File(indexDirName);

        version2Index.put(SYS_9, loadIndexFromJar("sys9", indexBaseDir));
        version2Index.put(SYS_10, loadIndexFromJar("sys10", indexBaseDir));
        version2Index.put(SYS_11, loadIndexFromJar("sys11", indexBaseDir));
    }

    public AbstractSchema getSysUserIndex(int VERSION_ID, SqlDomainIndex owner) {
        IndexSpec spec = version2Index.get(VERSION_ID);
        if (spec == null) {
            throw new ConfigurationException("Version not supported: " + VERSION_ID);
        }
        return new SysIndexImpl(spec.itree, spec.indexDirPath, spec.indexFileName, owner);
    }

    private IndexSpec loadIndexFromJar(String userName, File indexBaseDir) {
        IndexTree itree = new IndexTreeBase();
        String indexDirName = StringUtils.hash_MD5(userName);
        File indexSpecDir = new File(indexBaseDir, indexDirName);
        String indexFileName = userName.toLowerCase() + ".idx";

        String relativeDir = indexBaseDir.getName() + "/" + indexDirName + "/" + indexFileName;
        InputStream in = getClass().getClassLoader().getResourceAsStream(relativeDir);

        if (in != null) {
            long ms0 = System.currentTimeMillis();
            try {
                itree.loadNames(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            long ms1 = System.currentTimeMillis();
            int entries = itree.getEntriesCount();
            log.info("[Index loading] name: " + userName + " Time spent, ms: " + (ms1 - ms0) + " Entries: " + entries);
        }

        return new IndexSpec(itree, indexSpecDir.getAbsoluteFile(), indexFileName);
    }

    private IndexSpec loadIndexFromFS(String userName, File indexBaseDir) {
        IndexTree itree = new IndexTreeBase();
        String indexDirName = StringUtils.hash_MD5(userName);
        File indexSpecDir = new File(indexBaseDir, indexDirName);
        String indexFileName = userName.toLowerCase() + ".idx";

        if (!indexSpecDir.exists()) {
            // try to create
            if (!indexSpecDir.mkdir()) {
                throw new ConfigurationException("Cannot create SYS base directory: " + indexSpecDir.getAbsolutePath());
            }
        } else {
            try {
                InputStream in = new FileInputStream(new File(indexSpecDir, indexFileName));
                long ms0 = System.currentTimeMillis();
                try {
                    itree.loadNames(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                long ms1 = System.currentTimeMillis();
                int entries = itree.getEntriesCount();
                log.info("[Index loading] name: " + userName + " Time spent, ms: " + (ms1 - ms0) + " Entries: " + entries);
            } catch (FileNotFoundException e) {
                log.warn("Index file not find: " + new File(indexSpecDir, indexFileName));
            }
        }

        return new IndexSpec(itree, indexSpecDir.getAbsoluteFile(), indexFileName);
    }

    public void loadPublicSynonyms(int VERSION_ID, @NotNull IndexTree itree) {
        IndexSpec index = version2Index.get(VERSION_ID);
        if (index == null) {
            throw new ConfigurationException("Version not supported: " + VERSION_ID);
        } else {

            String relativeDir = indexBaseDir.getName() + "/" + index.indexDirPath.getName() + "/" + "public_syn.idx";
            InputStream in = getClass().getClassLoader().getResourceAsStream(relativeDir);
            if (in != null) {
                try {
                    itree.loadNames(in);
                } catch (IOException e) {
                    throw new ConfigurationException("Cannot load public synonyms from: " + relativeDir);
                }

            }
        }
    }

    public InputStream getPublicSynonyms(int VERSION_ID) {
        IndexSpec index = version2Index.get(VERSION_ID);
        if (index == null) {
            throw new ConfigurationException("Version not supported: " + VERSION_ID);
        } else {

            String relativeDir = indexBaseDir.getName() + "/" + index.indexDirPath.getName() + "/" + "public_syn.idx";
            InputStream in = getClass().getClassLoader().getResourceAsStream(relativeDir);
            if (in == null) {
                log.error("Public synonyms SYS not found");
            }
            return in;
        }
    }

    private class IndexSpec {
        public IndexTree itree;
        public File indexDirPath;
        public String indexFileName;

        public IndexSpec(IndexTree itree, File indexDirPath, String indexFileName) {
            this.itree = itree;
            this.indexDirPath = indexDirPath;
            this.indexFileName = indexFileName;
        }
    }

    private class SysIndexImpl extends AbstractSchemaBase { //implements SimpleIndex {

        SqlDomainIndex owner;

        public SysIndexImpl(IndexTree itree, File indexDirPath, String indexFileName, SqlDomainIndex owner) {
            super("sys", itree, indexDirPath, indexFileName);
            this.owner = owner;
        }

/*
        public String getSourceFilePath(String ctxPath) {
            String path = ContextPathUtil.extractFilePath(ctxPath);
            if (path == null) {
                throw new ConfigurationException("Context Path is not valid: " + ctxPath);
            }
            return new File(indexDirPath, path).getAbsolutePath();
        }
*/

        public SqlFile getSourceFile(String ctxPath) {
            String filePath = ContextPathUtil.extractFilePath(ctxPath);
//            File f = new File(indexDirPath, path);

            String objectName = ContextPathUtil.extractLastCtxName(ctxPath);
            // NOTE: have to do indirect call to the owner
            DbUrl dbUrl = owner.getSimpleIndex("sys").getDbUrl();
            String path = "[sys] " + objectName; 

            String relativeDir = indexBaseDir.getName() + "/" + indexDirPath.getName() + "/" + filePath;
            InputStream in = getClass().getClassLoader().getResourceAsStream(relativeDir);
            if (in != null) {
                try {
                    String content = StringUtils.instream2string(in);
                    return new SysSqlFile(
                            dbUrl,
                            path,
                            objectName, //f.getAbsolutePath(),
                            filePath,
                            content) {

                        @Override
                        public AbstractSchema getSimpleIndex() {
                            return owner.getSimpleIndex("sys"); //SysIndexImpl.this;
                        }
                    };
                } catch (IOException e) {
                    throw new ConfigurationException("Cannot open file: " + filePath + " on FS");
                }
            } else {
                return new SysSqlFile(
                        dbUrl,
                        path,
                        objectName, //f.getAbsolutePath(),
                        filePath,
                        "") {

                    @Override
                    public AbstractSchema getSimpleIndex() {
                        return owner.getSimpleIndex("sys"); //SysIndexImpl.this;
                    }
                };
            }
        }

/*
        public String getSourceText(String ctxPath) {
            throw new ConfigurationException("Not supported");
        }
*/

        public VariantsProvider getVariantsProvider() {
            // no variants provider for SYS schema
            return null;
        }

        // no FindUSages for SYS schema
        public WordIndexManager getWordIndexManager(){
            return null;
        }

        public boolean isImmutable() {
            return true;
        }

        public void flush() {
            // index is immutable
        }
    }

}
