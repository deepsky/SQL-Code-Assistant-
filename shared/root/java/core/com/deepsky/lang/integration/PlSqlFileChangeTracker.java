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
import com.deepsky.lang.plsql.indexMan.IndexBulkChangeListener;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.psibased.NamesIndexerExists;
import com.deepsky.lang.plsql.resolver.psibased.NamesIndexerWithChangesCollecting;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.resolver.utils.IndexTreeUtil;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.lang.plsql.sqlIndex.SqlFile;
import com.deepsky.lang.plsql.sqlIndex.WordIndexChangeListener;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.MessageBus;

import javax.annotation.processing.Completion;
import java.util.Arrays;
import java.util.Set;

public class PlSqlFileChangeTracker {

    private static final LoggerProxy log = LoggerProxy.getInstance("#PlSqlFileChangeTracker");

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

            long ms = System.currentTimeMillis();
            // check whether parsing called on non-physical copy of a file
            VirtualFile vf = actualPlSqFile.getVirtualFile();
//                    (plSqlFile.getOriginalFile() != null) ?
//                    plSqlFile.getOriginalFile().getVirtualFile() :
//                    plSqlFile.getVirtualFile(); //getFilePath(plSqlFile);

            long h;
//            indexer.parse(actualPlSqFile.getNode(), itree);
            if(actualPlSqFile == plSqlFile){
                String filePath = getFilePath(vf);
                Set<String> types = IndexTreeUtil.getTypesInFile(itree, filePath);

                itree.remove(actualPlSqFile.getCtxPath1().getPath());
                NamesIndexerWithChangesCollecting indexer = new NamesIndexerWithChangesCollecting();

                indexer.parse(plSqlFile.getNode(), itree);

//                int sizeAfter = itree.getEntriesCount();

                // todo -- not correct for non FS files
                itree.setFileAttribute(filePath, "timestamp", Long.toString(vf.getModificationCount()));
                itree.setFileAttribute(filePath, "mod_cnt", Long.toString(vf.getModificationStamp()));

                ms = System.currentTimeMillis() - ms;

                h = System.currentTimeMillis();
                types.addAll(Arrays.asList(indexer.getUpdatedTypes()));
                MessageBus bus1 = actualPlSqFile.getProject().getMessageBus();
                if (types.size() > 0) {
                    bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(dbUrl, types.toArray(new String[types.size()]));
                }

                bus1.asyncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(dbUrl, filePath);
            } else {
                // Completion request
                new NamesIndexerExists().parse(plSqlFile.getNode(), itree);
                h = System.currentTimeMillis();
            }
            h = System.currentTimeMillis() - h;

            log.info("Index built: " + ms + " rest of things: " + h);
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

}
