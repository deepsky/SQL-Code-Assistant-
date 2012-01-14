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

package com.deepsky.lang.integration;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.plsql.completion.Constants;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.psibased.NamesIndexerSpecific;
import com.deepsky.lang.plsql.resolver.psibased.NamesIndexerWithChangesCollecting;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.resolver.utils.IndexTreeUtil;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.lang.plsql.sqlIndex.SqlFile;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Arrays;
import java.util.Set;

public class PlSqlFileChangeTracker {

    private static final LoggerProxy log = LoggerProxy.getInstance("#PlSqlFileChangeTracker");

    long lastModCounter = -1;

    public void indexPlSqlFile(PlSqlFile plSqlFile) {

        PlSqlFile actualPlSqFile = (PlSqlFile) plSqlFile.getOriginalFile();

        // NOTE: changes made in the file should affect only FS index,
        // database index should be affected thru big cycle:
        // 1. pooling changes from DB
        // 2. updating db cache
        // 3. updating index
        // todo -- make changes in the DB schema also -
        if (IndexManager.FS_URL.equals(actualPlSqFile.getDbUrl())) {

            // todo -- is there direct call to FSIndexer.indexPlSqlFIle(...) needed?
            AbstractSchema sindex = actualPlSqFile.getResolver().getSimpleIndex();
            DbUrl dbUrl = sindex.getDbUrl();
            IndexTree itree = sindex.getIndexTree();
            final Project project = actualPlSqFile.getProject();
            long ms = System.currentTimeMillis();

            // check whether parsing called on non-physical copy of a file

            long h = 0;
            if (actualPlSqFile == plSqlFile) {
                final VirtualFile vf = actualPlSqFile.getVirtualFile();
                lastModCounter = vf != null ? vf.getModificationCount() : lastModCounter;
                String filePath = getFilePath(vf);
                Set<String> types = IndexTreeUtil.getTypesInFile(itree, filePath);
                // todo FUNCTION vs FUNCTION BODY, see below
                boolean res = itree.remove(actualPlSqFile.getCtxPath1().getPath());
                NamesIndexerWithChangesCollecting indexer = new NamesIndexerWithChangesCollecting();

                indexer.index(plSqlFile.getNode(), itree);

                // todo -- not correct for non FS files
                itree.setFileAttribute(filePath, "timestamp", Long.toString(vf.getModificationCount()));
                itree.setFileAttribute(filePath, "mod_cnt", Long.toString(vf.getModificationStamp()));

                ms = System.currentTimeMillis() - ms;

                h = System.currentTimeMillis();
                types.addAll(Arrays.asList(indexer.getUpdatedTypes()));

                CodeChangeEventAggregator.getInstance(project).updateIndex(dbUrl, types);
                CodeChangeEventAggregator.getInstance(project).updateWordIndex(dbUrl, filePath);
            } else {
                // make sure completion patch was applied
                if (Constants.IDENT_PATCHER.wasPatched()) {
                    // Completion request
                    // NOTE: there is a need to reset caches because of IDEA's caching mechanism for PsiFile
                    plSqlFile.resetCaches();
                    final VirtualFile vf = plSqlFile.getVirtualFile();
                    final String filePath = getFilePath(vf);
                    Set<String> types = IndexTreeUtil.getTypesInFile(itree, filePath);

                    // todo FUNCTION vs FUNCTION BODY, see below
                    boolean res = itree.remove(plSqlFile.getCtxPath1().getPath());

                    NamesIndexerWithChangesCollecting indexer = new NamesIndexerSpecific();
                    indexer.index(plSqlFile.getNode(), itree);
                    types.addAll(Arrays.asList(indexer.getUpdatedTypes()));

                    CodeChangeEventAggregator.getInstance(project).updateIndex(dbUrl, types);
                    CodeChangeEventAggregator.getInstance(project).updateWordIndex(dbUrl, filePath);
                    Constants.IDENT_PATCHER.cleanSignal();
                }
                h = System.currentTimeMillis();
            }
            h = System.currentTimeMillis() - h;
        }

    }


    private static String getFilePath(VirtualFile vf) {
        if (vf instanceof SqlFile) {
            SqlFile sqlFile = (SqlFile) vf;
            return ContextPathUtil.extractFilePath(sqlFile.getEncodedFilePathCtx());
        } else {
            return vf.getPath();
        }
    }

    /**
     * Delete file from the index and return types in the file
     *
     * @param path
     * @param itree
     * @return
     */
    public Set<String> deleteFile(String path, IndexTree itree) {
        Set<String> types = IndexTreeUtil.getTypesInFile(itree, path);
        String ctxPath = ContextPathUtil.encodeFilePathCtx(path);
        itree.remove(ctxPath);

        // replace FUNCTION_BODY with FUNCTION, PROCEDURE_BODY with PROCEDURE (if that is a case)
        if (types.remove(DbObject.FUNCTION_BODY)) {
            types.add(DbObject.FUNCTION);
        }
        if (types.remove(DbObject.PROCEDURE_BODY)) {
            types.add(DbObject.PROCEDURE);
        }

        return types;
    }


}
