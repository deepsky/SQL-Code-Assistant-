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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FSIndexer implements Indexer {

    private final Logger log = Logger.getInstance("#FSIndexer");

    final IndexTree itree;
    final SqlDomainIndex sqlIndex;
    final long modCounter = -1L;

    public FSIndexer(SqlDomainIndex sqlIndex) {
        this.sqlIndex = sqlIndex;
        this.itree = sqlIndex.getIndex(IndexManager.FS_URL.getUser());
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
//        itree.remove(file.getCtxPath1().getPath());
        indexer.index(file.getNode(), itree);

        setFileTimestamp(vf.getPath(), vf.getModificationCount(), vf.getModificationStamp());
        ms = System.currentTimeMillis() - ms;
        int sizeAfter = itree.getEntriesCount();

        types.addAll(Arrays.asList(indexer.getUpdatedTypes()));
        // notify about types have been updated
        listener.handleUpdatedTypes(types.toArray(new String[types.size()]));

        log.info("INDEXING entries: " + sizeAfter + " time spent: " + ms);
    }


    public void setFileTimestamp(String fileName, long time, long cnt) {
        itree.setFileAttribute(fileName, "timestamp", Long.toString(time));
        itree.setFileAttribute(fileName, "mod_cnt", Long.toString(cnt));
    }

    public IndexTree getIndex(){
        return itree;
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
     * Delete a file from the index and return types in the file
     * @param path file path
     * @return types found in the file
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


    /**
     * Delete files from the index and return types found in the files
     * @param paths file path
     * @return types found in the file
     */
    public Set<String> deleteFile(List<String> paths) {
        Set<String> types = new HashSet<String>();
        for(String path: paths){
            types.addAll(IndexTreeUtil.getTypesInFile(itree, path));
            String ctxPath = ContextPathUtil.encodeFilePathCtx(path);
            itree.remove(ctxPath);
        }

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
    }

    public void stop() {
        // save index in the cache directory
        if (modCounter != itree.getModificationCount()) {
            // save index
            sqlIndex.flush();
        }
    }

}
