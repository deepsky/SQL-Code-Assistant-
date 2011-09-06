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

package com.deepsky.lang.plsql.indexMan;

import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.psibased.NamesIndexerWithChangesCollecting;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.resolver.utils.IndexTreeUtil;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.lang.plsql.sqlIndex.SqlDomainIndex;
import com.deepsky.lang.plsql.struct.DbObject;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;

import java.io.File;
import java.util.Arrays;
import java.util.Set;


public class FSIndexer implements Indexer {

    private final Logger log = Logger.getInstance("#FSIndexer");

    IndexTree itree;
    SqlDomainIndex sqlIndex;
    long modCounter = -1L;
    Project project;

    public FSIndexer(Project project, SqlDomainIndex sqlIndex) {
        this.sqlIndex = sqlIndex;
        this.itree = sqlIndex.getIndex(IndexManager.FS_URL.getUser());
    }


    public IndexUpdater getUpdater() {
        return new IndexUpdaterImpl();
    }

    public void indexPlSqlFile(PlSqlElement file, DbTypeChangeListener listener) {
        NamesIndexerWithChangesCollecting indexer = new NamesIndexerWithChangesCollecting();

        long ms = System.currentTimeMillis();

        PsiFile psi = file.getContainingFile();
        VirtualFile vf = psi.getVirtualFile();
        if (vf == null) {
            // PsiFile Proxy
            vf = psi.getViewProvider().getVirtualFile();
        }

        Set<String> types = IndexTreeUtil.getTypesInFile(itree, vf.getPath());
        itree.remove(file.getCtxPath1().getPath());
        indexer.parse(file.getNode(), itree);

        setFileTimestamp(vf.getPath(), vf.getModificationCount(), vf.getModificationStamp());
        ms = System.currentTimeMillis() - ms;
        int sizeAfter = itree.getEntriesCount();

        types.addAll(Arrays.asList(indexer.getUpdatedTypes()));
        // notify about types have been updated
        listener.handleUpdatedTypes(types.toArray(new String[types.size()]));

//        MessageBus bus1 = project.getMessageBus();
//        bus1.asyncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, filePath);

        log.info("INDEXING entries: " + sizeAfter + " time spent: " + ms);
    }


    public void setFileTimestamp(String fileName, long time, long cnt) {
        itree.setFileAttribute(fileName, "timestamp", Long.toString(time));
        itree.setFileAttribute(fileName, "mod_cnt", Long.toString(cnt));
    }


    /**
     * Get timestamp for file on the file system
     *
     * @param path - file path
     * @return -
     */
    public long getFileTimestamp(String path) {
        String fileName = new File(path).getPath(); //Name();
        String timestamp = itree.getFileAttribute(fileName, "timestamp");
        return timestamp == null ? 0L : Long.parseLong(timestamp);
    }

    public long getFileModCount(String path) {
        String fileName = new File(path).getPath(); //Name();
        String mod_cnt = itree.getFileAttribute(fileName, "mod_cnt");
        return mod_cnt == null ? 0L : Long.parseLong(mod_cnt);
    }


    /**
     * Delete file from the index and return types in the file
     * @param path
     * @return
     */
    public Set<String> deleteFile(String path) {
        Set<String> types = IndexTreeUtil.getTypesInFile(itree, path);
        String ctxPath = ContextPathUtil.encodeFilePathCtx(path);
        itree.remove(ctxPath);

        // replace FUNCTION_BODY with FUNCTION, PROCEDURE_BODY with PROCEDURE (if that is a case)
        if(types.remove(DbObject.FUNCTION_BODY)){
           types.add(DbObject.FUNCTION);
        }
        if(types.remove(DbObject.PROCEDURE_BODY)){
           types.add(DbObject.PROCEDURE);
        }

        return types;
    }


    public void start() {
        // restore index from the cache directory
/*
        long ms0 = System.currentTimeMillis();
        String indexFile = new File(cacheDir, indexFileName).toString();
        try {
            itree.loadNames(indexFile);
        } catch (IOException e) {
            // todo -- handle
        }
        long ms1 = System.currentTimeMillis();
        int entries = itree.getEntriesCount();
        modCounter = itree.getModificationCount();
        log.info("[Index load], time spent (ms): " + (ms1 - ms0) + " indexes: " + entries);
*/
    }

    public void stop() {
        // save index in the cache directory
        if (modCounter != itree.getModificationCount()) {
            // save index
            sqlIndex.flush();
        }
    }

    /**
     * Move file and ste modification timestamp for it
     * @param oldF
     * @param newF
     * @param newModificationStamp
     * @return
     */
    public Set<String> moveFile(File oldF, File newF, long newModificationStamp) {
        Set<String> types = IndexTreeUtil.getTypesInFile(itree, oldF.getPath());
//        String oldCtxPath = ContextPathUtil.encodeFilePathCtx(oldF.getPath());
//        String newCtxPath = ContextPathUtil.encodeFilePathCtx(newF.getPath());
        itree.changeFileName(oldF.getPath(), newF.getPath());
        itree.setFileAttribute(newF.getPath(), "mod_cnt", Long.toString(newModificationStamp));

        return types;
    }


    private class IndexUpdaterImpl implements IndexUpdater {

        public long getFileTimestamp(String path) {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public void setFileTimestamp(String fileName, long time, long cnt) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void indexPlSqlFile(PlSqlElement file, DbTypeChangeListener listener) {
            FSIndexer.this.indexPlSqlFile(file, listener);
        }

        public void completeUpdate(DbTypeChangeListener listener) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

}
