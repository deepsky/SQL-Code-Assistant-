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

import com.deepsky.database.ora.DbUID;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.DbUrlSID;
import com.deepsky.database.ora2.DbObjectCache;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.resolver.*;
import com.deepsky.lang.plsql.resolver.factory.ResolveHelperImpl;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.index.IndexTreeBase;
import com.deepsky.lang.plsql.resolver.res_newlook.CompositeIndex;
import com.deepsky.lang.plsql.resolver.res_newlook.CompositeRefResolver;
import com.deepsky.lang.plsql.resolver.res_newlook.RefRes;
import com.deepsky.lang.plsql.sqlIndex.*;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class SqlIndexBase implements SqlDomainIndex {

    private final LoggerProxy log = LoggerProxy.getInstance("#SqlIndexBase");

    private final static String REVISION_FILE = "a12_345_$1.rev";
    private final static String REVISION_ATTR = "revision";
    private final static String INDEX_REVISION = "1203311530"; // Change value to get the index rebuilt on startup

    protected Map<String, RefResolver> user2resolver = new HashMap<String, RefResolver>();
    protected Map<String, AbstractSchema> user2index = new HashMap<String, AbstractSchema>();
    protected RefResolverListener cacheListener = new RefResolverListenerImpl();

    protected File indexDir;
    protected DbUID dbUID;
    IndexManager indexManager;

    protected IndexTreeBase synonymsITree = new IndexTreeBase();

    public SqlIndexBase(IndexManager indexManager, @NotNull File indexDir, @NotNull DbUID dbUID) {
        this.indexManager = indexManager;
        this.indexDir = indexDir;
        this.dbUID = dbUID;

        // check index directory
        if (!indexDir.exists()) {
            if (!indexDir.mkdir()) {
                throw new ConfigurationException("Not able to create index directory");
            }
        }
    }

    public DbUID getDbUID() {
        return dbUID;
    }

    public File getIndexHomeDir() {
        return indexDir;
    }

    protected File getIndexDir(String userName) {
        return new File(indexDir, userName.toLowerCase());
    }

    public void loadPublicSynonyms(InputStream stream) {
        synonymsITree.cleanUp();
        try {
            synonymsITree.loadNames(stream);
        } catch (IOException e) {
            log.error("Cannot load SYS public synonyms");
        }
    }


    // for test purpose only
    public void addIndex(String userName, String fileIndexName) throws IOException {
        if (!new File(fileIndexName).exists()) {
            throw new ConfigurationException("File not found: " + fileIndexName);
        }
        IndexTree itree = new IndexTreeBase();

        // load index
        long ms0 = System.currentTimeMillis();
        itree.loadNames(fileIndexName);
        long ms1 = System.currentTimeMillis();

        String _userName = userName.toLowerCase();
        RefResolver resolver = createRefResolver(_userName, (IndexTreeBase) itree);

        resolver.addListener(cacheListener);
        user2resolver.put(_userName, resolver);

        AbstractSchema si = createSimpleIndex(
                new DbUrlSID(userName, userName, "localhost", "1888", "test"),
                itree,
                SqlIndexBase.this.indexDir,
                fileIndexName
        );

        user2index.put(_userName, overrideSimpleIndex(si));

        int entries = itree.getEntriesCount();
        log.info("[Index loading] name: " + userName
                + " Time spent, ms: " + (ms1 - ms0)
                + " Entries: " + entries);
    }

    // for test purpose only
    public void addIndex(String userName, IndexTree itree) {

        String _userName = userName.toLowerCase();
        RefResolver resolver = createRefResolver(_userName, (IndexTreeBase) itree);

        resolver.addListener(cacheListener);
        user2resolver.put(_userName, resolver);

        AbstractSchema si = createSimpleIndex(
                new DbUrlSID(userName, userName, "localhost", "1888", "test"),
                itree,
                SqlIndexBase.this.indexDir,
                userName + ".idx"
        );

        user2index.put(_userName, overrideSimpleIndex(si));

        int entries = itree.getEntriesCount();
        log.info("[Index loading] name: " + userName
                + " Entries: " + entries);
    }


    protected RefResolver createRefResolver(String userName, IndexTreeBase itree) {
        CompositeIndex compositeIndex = new CompositeIndex(itree);
        if (!userName.equalsIgnoreCase("sys")) {
            compositeIndex.setAdditionalIndex(synonymsITree);
            return new CompositeRefResolver(dbUID.buildDbUrl(userName), compositeIndex);
        } else {
            return new CompositeRefResolver(dbUID.buildDbUrl(userName), compositeIndex);
        }
    }

    public AbstractSchema getSimpleIndex(@NotNull String user) {
        return user2index.get(user.toLowerCase());
    }

    public AbstractSchema[] getIndexes() {
        // return all schemas except SYS
        // todo -- FIX ME if user is connected to SYS schema 
        List<AbstractSchema> out = new ArrayList<AbstractSchema>();
        for (Map.Entry<String, AbstractSchema> e : user2index.entrySet()) {
            if (!e.getKey().equalsIgnoreCase("sys")) {
                out.add(e.getValue());
            }
        }

        return out.toArray(new AbstractSchema[out.size()]);
    }


    /**
     * Delegate responsibility on index creation to derived classes
     *
     * @param dbUrl         - database url used to connect to
     *                      IMPORTANT: userName and user from dbUrl may be different which means the database object
     *                      is originating from userName schema and source of it should be prefexed with userName
     * @param itree         - index tree
     * @param indexDirPath  - index directory
     * @param indexFileName - index file name
     * @return - index
     */
    protected abstract AbstractSchema createSimpleIndex(DbUrl dbUrl, IndexTree itree, File indexDirPath, String indexFileName);

    @NotNull
    public abstract DbObjectCache getObjectCache(@NotNull String userName);


    public boolean addIndex(@NotNull AbstractSchema index) {
        String userName = index.getName().toLowerCase();
        if (user2index.get(userName) != null) {
            return false;
        } else {
            user2index.put(userName, overrideSimpleIndex(index));

            RefResolver resolver = createRefResolver(userName, (IndexTreeBase) index.getIndexTree());
            resolver.addListener(cacheListener);
            user2resolver.put(userName, resolver);
            return true;
        }
    }

    public boolean addIndex(@NotNull String _userName) {
        String userName = _userName.toLowerCase();
        if (user2index.get(userName) == null) {
            // create new index stuff
            AbstractSchema i = createIndexStuff(userName, userName.toLowerCase() + ".idx");
            user2index.put(userName, i);

            File indexFile = new File(new File(i.getIndexPath()), i.getIndexFileName());
            //load index content
            if (indexFile.exists()) {
                long ms0 = System.currentTimeMillis();
                try {
                    IndexTree itree = user2index.get(userName).getIndexTree();
                    itree.loadNames(indexFile.getAbsolutePath());

                    // Check index revision
                    String value = itree.getFileAttribute(REVISION_FILE, REVISION_ATTR);
                    if(value == null || !INDEX_REVISION.equals(value)){
                        itree.clear();
                        log.info("Index triggered for rebuilding: " + userName + " Old marker: " + value + " New: " + INDEX_REVISION);
                        itree.setFileAttribute(REVISION_FILE, REVISION_ATTR, INDEX_REVISION);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                long ms1 = System.currentTimeMillis();
                int entries = user2index.get(userName).getIndexTree().getEntriesCount();
                log.info("[Index loading] name: " + userName + " Time spent, ms: " + (ms1 - ms0) + " Entries: " + entries);
            }

            RefResolver resolver = createRefResolver(userName, (IndexTreeBase) i.getIndexTree());

            resolver.addListener(cacheListener);
            user2resolver.put(userName, resolver);

            return true;
        } else {
            return true;
        }
    }

    public synchronized boolean removeIndex(@NotNull String _userName) {
        String userName = _userName.toLowerCase();
        if (user2resolver.get(userName) != null) {
            final File renamedTo = new File(indexDir, userName + ".toremove");
            File original = new File(indexDir, userName);
            if (original.renameTo(renamedTo)) {
                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    public void run() {
                        // do real index deletion
                        if (!renamedTo.delete()) {
                            // could not delete
                            log.warn("Could not delete index home dir: " + renamedTo);

                            // try to delete later
                            renamedTo.deleteOnExit();
                        }
                    }
                });

                // do some housekeeping
                user2resolver.remove(userName);
                user2index.remove(userName);
                return true;
            }
        }

        return false;
    }


    public synchronized boolean detachIndex(@NotNull String _userName) {
        String userName = _userName.toLowerCase();
        if (user2resolver.get(userName) != null) {
            user2resolver.remove(userName);
            user2index.remove(userName);

            if(user2index.size() == 0 ){
                return true;
            } else if(user2index.size() == 1 && user2resolver.get("sys") != null){
                return true;
            }
        }

        return false;
    }

    private AbstractSchema createIndexStuff(@NotNull String userName, @NotNull String indexName) {
        File _indexDir = new File(indexDir, userName.toLowerCase());
        if (!_indexDir.exists()) {
            if (!_indexDir.mkdir()) {
                throw new ConfigurationException("Cannot create directory: " + _indexDir);
            }
        }
        IndexTree itree = new IndexTreeBase();
        AbstractSchema sindex = createSimpleIndex(dbUID.buildDbUrl(userName), itree, _indexDir, indexName);

        // intercept call of the  getResolverHelper() method
        return overrideSimpleIndex(sindex);
    }


    protected AbstractSchema overrideSimpleIndex(AbstractSchema sindex) {
        // intercept call of the  getResolverHelper() method
        InvocationHandler interceptor = new SimpleIndexResolveHandler(sindex);

        return (AbstractSchema) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{AbstractSchema.class},
                interceptor);
    }

    /**
     * Intercept call of the  getResolverHelper() method to SimpleIndex
     * and redirect it to the SqlIndexBase which is responsible for all resolvers
     */
    private class SimpleIndexResolveHandler implements InvocationHandler {

        AbstractSchema sindex;
        ResolveFacade facade;

        public SimpleIndexResolveHandler(AbstractSchema sindex) {
            this.sindex = sindex;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("getResolveHelper")) {
                return SqlIndexBase.this.getResolver(sindex.getName());
            } else if (method.getName().equals("getResolveFacade")) {
                if (facade == null) {
                    facade = new ResolveFacadeImpl(SqlIndexBase.this.getSimpleIndex(sindex.getName()));
                }

                return facade;
            } else if (method.getName().equals("getLLResolver")) {
                // todo -- dirty hack, should be fixed ASAP
                RefResolver rr = user2resolver.get(sindex.getName());
                return (RefRes)rr;
            } else if (method.getName().equals("getSourceFile") && args.length == 1 && args[0] instanceof ResolveDescriptor) {
                ResolveDescriptor rhlp = (ResolveDescriptor) args[0];
                AbstractSchema schema = null;
                DbUrl dbUrl = rhlp.getDbUrl();
                if(dbUrl.getDbUID().equals(dbUID)){
                    schema = user2index.get(dbUrl.getUser());
                } else {
                    // dbUrl external
                    schema = indexManager.getIndex(dbUrl, 0);
                }
                return (schema != null) ? schema.getSourceFile(rhlp.getCtxPath()) : null;
            } else if (method.getName().equals("getDbUrl")) {
                // todo - password?
                return dbUID.buildDbUrl(sindex.getName());
            }
            return method.invoke(sindex, args);
        }
    }


    public IndexTree getIndex(@NotNull String userName) {
        AbstractSchema i = user2index.get(userName.toLowerCase());
        return i != null ? i.getIndexTree() : null;
    }

    public ResolveHelper getResolver(@NotNull String userName) {
        RefResolver rr = user2resolver.get(userName.toLowerCase());
        if (rr == null) {
            throw new ConfigurationException("Index for user " + userName + " not found");
        }
        return new ResolveHelperImpl(rr); //, this);
    }


    public void flush() {
        // save index
        for (AbstractSchema i : user2index.values()) {
            if (!i.isImmutable()) {
                i.flush();
            }
        }
    }

    private class RefResolverListenerImpl implements RefResolverListener {

        public void resolveReference(List<ResolveDescriptor> out, String schemaName, RefHolder ref) {
            RefResolver c = user2resolver.get(schemaName);
            if (c != null) {
                c.resolve(out, ref);
            } else {
                // todo -- dynamically load more indexes?
                int h = 0;
            }
        }

/*
        public void resolveName(List<ResolveDescriptor> out, String schemaName, String objectName) {
        }
*/
    }


}
