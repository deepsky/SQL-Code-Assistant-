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

import com.deepsky.conf.CacheLocator;
import com.deepsky.database.ConnectionManagerListener;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.plsql.objTree.SchemaTreeBuilder;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.utils.PlSqlUtil;
import com.deepsky.lang.plsql.resolver.index.IndexEntriesWalker;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.sqlIndex.*;
import com.deepsky.lang.plsql.sqlIndex.impl.DbSchemaIndex;
import com.deepsky.lang.plsql.sqlIndex.impl.FSIndex;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.*;


public class IndexManagerImpl implements IndexManager {

    private final LoggerProxy log = LoggerProxy.getInstance("#IndexManagerImpl");

    final static int INDEX_REMOVED = 1;
    final static int INDEX_ADDED = 2;

    Project project;
    Map<String, SqlDomainIndex> uid2index = new HashMap<String, SqlDomainIndex>();

    List<IndexManagerListener> listeners = new ArrayList<IndexManagerListener>();
    final LinkedList<ItemToIndex> items = new LinkedList<ItemToIndex>();

    private FSIndex fsIndex;

    boolean offlineCacheEnabled = false;
    DbUrl connectedDbUrl = null;

    public IndexManagerImpl(@NotNull Project project) {
        this.project = project;

        // load FS Index on start
        this.fsIndex = (FSIndex) findOrCreateIndex(IndexManager.FS_URL);

        MessageBus bus1 = project.getMessageBus();
        // listen for index updates
        WordIndexChangeListener ll = new WordIndexChangeListener() {
            public void handleUpdate(DbUrl source, String path) {
                addItem(source, path);
            }
        };

        bus1.connect().subscribe(WordIndexChangeListener.TOPIC, ll);
    }

    @NotNull
    public SqlDomainIndex findOrCreateIndex(DbUrl dbUrl) {
        DbUID dbUid = DbUID.getDbUID(dbUrl);
        SqlDomainIndex ix = uid2index.get(dbUid.key());
        if (ix == null) {
            // create index home directory
//            File indexDir = getIndexHomeDir(project, dbUrl);
            File indexDir = getIndexHomeDir(project, dbUid);

//            DbUrl LOCAL_FS_URL = PluginKeys.LOCAL_FS_URL.getData(project);
//            if(dbUid.derivedFrom(LOCAL_FS_URL)){
//                ix = new FSIndex(indexDir, DbUID.getDbUID(LOCAL_FS_URL));
//            } else {
//                ix = new DbSchemaIndex(project, indexDir, dbUid);
//            }
            ix = (dbUid.derivedFrom(IndexManager.FS_URL))?
//            ix = (dbUrl == IndexManager.FS_URL) ?
                    new FSIndex(this, indexDir, DbUID.getDbUID(IndexManager.FS_URL)) :
                    new DbSchemaIndex(project, this, indexDir, dbUid);

            uid2index.put(dbUid.key(), ix);
            fireEvent(INDEX_ADDED, dbUrl);
        }

        ix.addIndex(dbUrl.getUser());
        return ix;
    }

    @NotNull
    public AbstractSchema findOrCreateIndex(DbUrl dbUrl, int dummy){
        DbUID dbUid = DbUID.getDbUID(dbUrl);
        SqlDomainIndex ix = uid2index.get(dbUid.key());
        if (ix == null) {
            // create index home directory
//            File indexDir = getIndexHomeDir(project, dbUrl);
            File indexDir = getIndexHomeDir(project, dbUid);
            ix = (dbUid.derivedFrom(IndexManager.FS_URL))?
                    new FSIndex(this, indexDir, DbUID.getDbUID(IndexManager.FS_URL)) :
                    new DbSchemaIndex(project, this, indexDir, dbUid);

            uid2index.put(dbUid.key(), ix);
            fireEvent(INDEX_ADDED, dbUrl);
        }

        ix.addIndex(dbUrl.getUser());
        return ix.getSimpleIndex(dbUrl.getUser());
    }


    public SqlDomainIndex getIndex(DbUID dbUid) {
        return uid2index.get(dbUid.key());
    }


    public SqlDomainIndex getIndex(DbUrl dbUrl){
        return uid2index.get(DbUID.getDbUID(dbUrl).key());
    }

    @Nullable
    public AbstractSchema getIndex(DbUrl dbUrl, int dummy){
        SqlDomainIndex domainIndex = uid2index.get(DbUID.getDbUID(dbUrl).key());
        if(domainIndex != null){
            return domainIndex.getSimpleIndex(dbUrl.getUser());
        }

        return null;
    }


    private void fireEvent(int event, DbUrl dbUrl) {
        for (IndexManagerListener l : listeners) {
            if (event == INDEX_ADDED) {
                l.indexAdded(dbUrl);
            } else if (event == INDEX_REMOVED) {
                l.indexRemoved(dbUrl);
            }
        }
    }


/*
OLD
    public void removeIndex(DbUrl dbUrl) {
        SqlDomainIndex ix = uid2index.remove(dbUrl.getUserHostPortServiceName().toLowerCase());
        if (ix == null) {
            return;
        }
        File indexHomeDir = getIndexHomeDir(project, dbUrl);
        final File renamedTo = new File(indexHomeDir.getParentFile(), indexHomeDir.getName() + ".toremove");

        if (indexHomeDir.renameTo(renamedTo)) {
            ApplicationManager.getApplication().invokeLater(new Runnable() {
                public void run() {
                    // do real index deletion
                    if (!renamedTo.delete()) {
                        // could not delete
                        log.warn("Could not delete index home dir: " + renamedTo);
                    }
                }
            });
        }

        fireEvent(INDEX_REMOVED, dbUrl);
    }
*/

    public void removeIndex(DbUrl dbUrl) {
        SqlDomainIndex ix = uid2index.get(DbUID.getDbUID(dbUrl).key());
        if (ix == null) {
            return;
        } else {
            boolean res = ix.removeIndex(dbUrl.getUser());
            if(res){
                if(ix.getIndexes().length == 1){
                    // no users except SYS, safe to remove
                    finalizeDomainIndexRemove(DbUID.getDbUID(dbUrl));
                }
                fireEvent(INDEX_REMOVED, dbUrl);
            }
        }

    }

    private void removeIndexFromRegistry(DbUrl dbUrl) {
        SqlDomainIndex ix = uid2index.get(DbUID.getDbUID(dbUrl).key());
        if (ix != null) {
            boolean res = ix.detachIndex(dbUrl.getUser());
            if(res){
                uid2index.remove(DbUID.getDbUID(dbUrl).key());
            }
            fireEvent(INDEX_REMOVED, dbUrl);
        }
    }

    private void finalizeDomainIndexRemove(DbUID dbUid) {
        SqlDomainIndex ix = uid2index.remove(dbUid.key());
        //
        if (ix != null) {
/*
NOTE: do not delete index directory for now
            File indexHomeDir = ix.getIndexHomeDir();

            final File renamedTo = new File(indexHomeDir.getParentFile(), indexHomeDir.getName() + ".toremove");
            if (indexHomeDir.renameTo(renamedTo)) {
                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    public void run() {
                        // do real index deletion
                        if (!renamedTo.delete()) {
                            // could not delete
                            log.warn("Could not delete index home dir: " + renamedTo);
                        }
                    }
                });
            }
*/
        }
    }

    public SqlDomainIndex[] getIndexes() {
        return uid2index.values().toArray(new SqlDomainIndex[0]);
    }

    public void addListener(IndexManagerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IndexManagerListener listener) {
        listeners.remove(listener);
    }


    private File getIndexHomeDir(Project project, DbUID dbUid) {
        VirtualFile projectBaseDir = project.getBaseDir();
        String url = "./dummy";
        if (projectBaseDir == null) {
            // dummy project? fix me
        } else {
            url = projectBaseDir.getPresentableUrl();
        }

        File cacheDir = CacheLocator.getCacheDirectory();
        // create index own directory
        String indexDir = StringUtils.hash_MD5(url + "*" + dbUid.key());
        return new File(cacheDir, indexDir);
    }

    private File getIndexHomeDir(Project project, DbUrl dbUrl) {
        VirtualFile projectBaseDir = project.getBaseDir();
        String url = "./dummy";
        if (projectBaseDir == null) {
            // dummy project? fix me
        } else {
            url = projectBaseDir.getPresentableUrl();
        }

        File cacheDir = CacheLocator.getCacheDirectory();
        // create index own directory
        String indexDir = StringUtils.hash_MD5(url + "*" + dbUrl.getUserHostPortServiceName());
        return new File(cacheDir, indexDir);
    }

    @NotNull
    public SqlDomainIndex getFSIndex() {
        return findOrCreateIndex(IndexManager.FS_URL);
    }

    public ResolveHelper getResolver(DbUrl dbUrl) {
        SqlDomainIndex ix = uid2index.get(DbUID.getDbUID(dbUrl).key()); //dbUrl.getUserHostPortServiceName().toLowerCase());
        if (ix != null) {
            return ix.getResolver(dbUrl.getUser());
        }
        return null;
    }


    public void populateSchemaTree(DbUrl dbUrl, final SchemaTreeBuilder builder) {
        SqlDomainIndex ix = uid2index.get(DbUID.getDbUID(dbUrl).key());

        if (ix != null) {
            long time = System.currentTimeMillis();
            final IndexTree itree = ix.getIndex(dbUrl.getUser());
            if (itree != null) {
                itree.iterateThru(new IndexEntriesWalker() {
                    public void process(String ctxPath, String value, String[] attrs) {
                        builder.handle(ctxPath, value, attrs);
                    }
                });
            }
            time = System.currentTimeMillis() - time;
            log.info("[populateSchemaTree] time spent: " + time + " DB: " + dbUrl.getUserHostPortServiceName());
        } else {
            log.info("[populateSchemaTree] schema not found");
        }
    }


    private void addItem(DbUrl dbUrl, String filePath){
        synchronized (items){
            items.addLast(new ItemToIndex(dbUrl, filePath));
        }
    }

    private ItemToIndex[] loadItems(){
        ItemToIndex[] out = null;
        synchronized (items){
            out = items.toArray(new ItemToIndex[items.size()]);
            items.clear();
        }
        return out;
    }

    private class ItemToIndex {
        public DbUrl dbUrl;
        public String filePath;

        public ItemToIndex(DbUrl dbUrl, String filePath){
            this.dbUrl = dbUrl;
            this.filePath = filePath;
        }

        public int hashCode(){
            return filePath.hashCode();
        }

        public boolean equals(Object o){
            return o instanceof ItemToIndex
                    && ((ItemToIndex)o).dbUrl.equals(dbUrl)
                    && ((ItemToIndex)o).filePath.equals(filePath);
        }
    }



    public void start(){
        stop = false;
        dispatcher = new Thread(new WordIndexerDispatcher());
        //int prior = schemaMonitor.getPriority();
        //dispatcher.setPriority(CACHE_MANAGER_THREAD_PRIOR);
        dispatcher.setDaemon(true);
        dispatcher.start();
    }

    public void stop() {
        stop = true;
        synchronized (synch) {
            synch.notify();
        }

        if (dispatcher != null) {
            try {
                dispatcher.join();
            } catch (InterruptedException e) {
            }
        }
    }


    public void enableOfflineCache(DbUrl[] dbUrls, boolean flag) {
        offlineCacheEnabled = flag;
        if(flag){
            // enable offline caches
            for(DbUrl dbUrl: dbUrls){
                processOfflineCacheStatus(dbUrl, flag);
            }
        } else {
            // disable offline caches
            // close all openned windows first
            for(DbUrl dbUrl: dbUrls){
                if(connectedDbUrl != null && dbUrl.equals(connectedDbUrl)){
                   // skip
                   continue;
                }

                processOfflineCacheStatus(dbUrl, flag);
            }
        }
    }

    public void handleUpdate(StateEvent state) {
        if (state.getStatus() == ConnectionManagerListener.DISCONNECTED) {
            // disconnected
            fsIndex.detach();

            if(state.getUrl() != null){
                connectedDbUrl = null;
                if(!offlineCacheEnabled){
                    processOfflineCacheStatus(state.getUrl(), false);
                }
//                removeIndexFromRegistry(state.getUrl());
//                MessageBus bus1 = project.getMessageBus();
//                bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(state.getUrl(), null);
            }

        } else if (state.getStatus() == ConnectionManagerListener.CONNECTED) {
            // connected
            final DbUrl dbUrl = state.getUrl();
            SqlDomainIndex db = findOrCreateIndex(dbUrl);
//            SqlDomainIndex db = uid2index.get(DbUID.getDbUID(dbUrl).key());
            fsIndex.attach(db.getSimpleIndex(dbUrl.getUser()));

            connectedDbUrl = dbUrl;
            processOfflineCacheStatus(state.getUrl(), true);

            //
//            MessageBus bus1 = project.getMessageBus();
//            bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(dbUrl, null);
        }
    }

    private void processOfflineCacheStatus(final DbUrl dbUrl, boolean enable){
        if(enable){
            if(getIndex(dbUrl, 0) == null){
                SqlDomainIndex db = findOrCreateIndex(dbUrl);
            }
            MessageBus bus1 = project.getMessageBus();
            bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(dbUrl, null);
        } else {
            // close all openned windows
            final AbstractSchema schema = getIndex(dbUrl, 0);
            if(schema != null && !project.isDisposed()){
                ApplicationManager.getApplication().invokeLater(new Runnable(){
                    public void run() {
                        // Do one more check because the project may be already disposed
                        if(!project.isDisposed()){
                            PlSqlUtil.closeEditorsForSchema(project, schema);
                        }
                    }
                });

                removeIndexFromRegistry(dbUrl);
                MessageBus bus1 = project.getMessageBus();
                bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(dbUrl, null);
            }
        }
    }

    Thread dispatcher;
    private final Object synch = new Object();
    private boolean stop = false;
    private long timeout = 5 * 1000;

    private class WordIndexerDispatcher implements Runnable {

        public void run() {

            synchronized (synch) {
                while (!stop) {
                    Set<ItemToIndex> set = new HashSet<ItemToIndex>();
                    set.addAll(Arrays.asList(loadItems()));
                    for(final ItemToIndex item: set){
                        if(item.dbUrl.getUser().equalsIgnoreCase("sys")){
                            // do not index SYS schema
                            continue;
                        }

                        final SqlDomainIndex index = uid2index.get(DbUID.getDbUID(item.dbUrl).key());
                        // index may be NULL on closing of the project, so check it before indexing
                        if(index != null){
//                            final AbstractSchema sindex = index.getSimpleIndex(item.dbUrl.getUser().toLowerCase());
//                            sindex.getWordIndexManager().updateIndexForFile(item.filePath);

                            // Use actual file content for word indexing ,
                            // i.e. if file is opened in the Editor use PsiFile
                            ApplicationManager.getApplication().runReadAction(new Runnable() {
                                public void run() {
                                    PsiFile file = null;
                                    if (item.filePath != null) {
                                        final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
                                        for (VirtualFile v : fileEditorManager.getOpenFiles()) {
                                            if (v.getPath().equals(item.filePath)) {
                                                // file found in the Editor
                                                file = PsiManager.getInstance(project).findFile(v);
                                                break;
                                            }
                                        }
                                    }

                                    AbstractSchema sindex = index.getSimpleIndex(item.dbUrl.getUser().toLowerCase());
                                    if (file == null) {
                                        sindex.getWordIndexManager().updateIndexForFile(item.filePath);
                                    } else {
                                        sindex.getWordIndexManager().updateIndexForFile(item.filePath, file.getText());
                                    }
                                }
                            });

                        }
                    }

                    try {
                        synch.wait(timeout);
                    } catch (InterruptedException e) {
                        // todo
                    }
                }
            }
        }
    }



   /**
    * For test purposes only
    */
    protected IndexManagerImpl() {
    }

    // for test purposes only
    public void addIndex(@NotNull DbUrl dbUrl, @NotNull SqlDomainIndex index) {
       uid2index.put(DbUID.getDbUID(dbUrl).key(), index);
    }

}
