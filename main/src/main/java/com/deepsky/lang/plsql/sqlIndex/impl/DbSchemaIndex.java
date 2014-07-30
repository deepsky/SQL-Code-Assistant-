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
import com.deepsky.database.ora.ItemToUpdate;
import com.deepsky.database.ora2.DbObjectCache;
import com.deepsky.database.ora2.loaders.SynonymLoader;
import com.deepsky.lang.common.PlSqlSupportLoader;
import com.deepsky.lang.plsql.completion.NameProvider;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.index.ContextItem;
import com.deepsky.lang.plsql.resolver.index.IndexEntriesWalkerInterruptable;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.psibased.NamesIndexer;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.PsiUtil;
import com.deepsky.lang.plsql.sqlIndex.*;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbSchemaIndex extends SqlIndexBase {

    private final LoggerProxy log = LoggerProxy.getInstance("#DbSchemaIndex");

    private Project project;

    public DbSchemaIndex(Project project, IndexManager indexManager, @NotNull File indexDir, @NotNull DbUID dbUID) {
        super(indexManager, indexDir, dbUID);
        this.project = project;

        // load SYS schema index -- todo -- SYS version number should be configurable from GUI
        PlSqlSupportLoader c = (PlSqlSupportLoader) ApplicationManager.getApplication().getComponent(PlSqlSupportLoader.PLSQL_APPLICATION);
        addIndex(c.getSysSchemaIndexProvider().getSysUserIndex(SysSchemaIndexProvider.SYS_10, this));

        // load public synonyms for default version SYS explicitly
        c.getSysSchemaIndexProvider().loadPublicSynonyms(SysSchemaIndexProvider.SYS_10, synonymsITree);
    }


    // for test only
    protected DbSchemaIndex(@NotNull File indexDir, @NotNull DbUID dbUID) {
        super(null, indexDir, dbUID);
    }

    @NotNull
    public DbObjectCache getObjectCache(@NotNull String userName) {
        AbstractSchema diHolder = user2index.get(userName.toLowerCase());

        // make sure index exists if not create one
        if (diHolder == null) {
            // create index directory, if it does not exist
            addIndex(userName);
        }

        IndexTree itree = user2index.get(userName.toLowerCase()).getIndexTree();
        return new DbObjectCacheAdaptor(userName, itree);
    }


    private class DbObjectCacheAdaptor implements DbObjectCache {

        final static int FILES_LIMIT = 40;
        private IndexTree itree;
        private String userName;
        long modCounter = -1;
        int filesUpdated = 0;

        public DbObjectCacheAdaptor(String userName, IndexTree itree) {
            this.userName = userName;
            this.itree = itree;
        }

        public Date objectDDLTime(String type, String name) {
            if (name == null) {
                return new Date(-1L);
            }
            String fileName = encodeFileName(type, name);
            String timestamp = itree.getFileAttribute(fileName, IndexTree.TIMESTAMP_ATTR);//"timestamp");
            return new Date(timestamp == null ? -1 : Long.parseLong(timestamp));
        }

        public void update(final String name, final String type, String source, Date updateDate, final boolean isValid) {

            // encode file name
            String fileName = encodeFileName(type, name);
            try {
                long ms0 = System.currentTimeMillis();
                MarkupGeneratorEx2 generator = new MarkupGeneratorEx2(fileName);
                ASTNode root = generator.parse(source);
                long ms1 = System.currentTimeMillis();

                int indexes = itree.getEntriesCount();
                int added = 0;

                String rootObjectCtxPath = null;
                int nbrRootObjects = 0;

                // find root node
                for (int t : ContextPathUtil.dbType2ctxTypeMulti(type)) {
                    String[] ctxPaths = PsiUtil.findCtxPathAmongChilds(root, t, name);
                    if (ctxPaths.length == 1) {
                        rootObjectCtxPath = ctxPaths[0];
                        nbrRootObjects++;
                    }
                }

                if (nbrRootObjects != 1) {
                    // NOTE: the object looks invalid:
                    //      type of the object not supported or several root objects found
                    // todo -- build vanilla object context path and set STATUS_ATTR to false

                    // assign status for root object
                    itree.setContextPathAttr(
                            rootObjectCtxPath,
                            ContextPathUtil.STATUS_ATTR,
                            Boolean.toString(false)
                    );
                } else {
                    // Remove top object being indexed from the Index first
                    String fileCtx = ContextPathUtil.extractFileCtx(rootObjectCtxPath);
                    itree.remove(fileCtx);

                    NamesIndexer indexer = new NamesIndexer();
                    indexer.index(root, itree);
                    added = itree.getEntriesCount() - indexes;
                    indexes = itree.getEntriesCount();

                    // assign status for root object
                    itree.setContextPathAttr(
                            rootObjectCtxPath,
                            ContextPathUtil.STATUS_ATTR,
                            Boolean.toString(isValid)
                    );
                }

                long ms2 = System.currentTimeMillis();
                log.debug("[Indexing], time spent (ms): " + (ms2 - ms1) + " [Parsing], time (ms): " + (ms1 - ms0) + " indexes: " + added + "(" + indexes + ") File: " + fileName);
            } catch (Throwable e) {
                log.warn("[Indexing] ERROR: " + e.getMessage() + " File: " + fileName);
            }

            // save object source according to the type of the object
            if (DbObject.SYNONYM.equals(type)) {
                // skip source saving, index has enough info about the object
                itree.setFileAttribute(fileName, "timestamp", Long.toString(updateDate.getTime()));
            } else if (DbObject.VIEW_INTERNAL_TYPE.equals(type)) {
                // skip source saving, internal stuff
            } else {
                // do saving
                itree.setFileAttribute(fileName, "timestamp", Long.toString(updateDate.getTime()));
                try {
                    File filePath = new File(getIndexDir(userName), fileName);//
                    StringUtils.string2file(source, filePath); //new File(getIndexDir0(), fileName));

                    filesUpdated++;

                    if (project != null) {
                        MessageBus bus1 = project.getMessageBus();
                        bus1.asyncPublisher(WordIndexChangeListener.TOPIC)
                                .handleUpdate(dbUID.buildDbUrl(userName), fileName);
                    }

                } catch (IOException e) {
                    log.error("Could not save file " + fileName + " in the directory: " + indexDir);
                }
            }

            if (filesUpdated > FILES_LIMIT) {
                DbSchemaIndex.this.flush();
                log.info("[Indexing] intermediate index saving done.");
                filesUpdated = 0;
            }
        }

//        private File getIndexDir0(){
//            return getIndexDir(userName);
//        }

        public void remove(String type, String name) {
            String fileName = encodeFileName(type, name);
            String ctxPath = ContextPathUtil.encodeFilePathCtx(fileName);
            itree.remove(ctxPath);
        }

        public int getNbrObjectForType(String type) {
            final int[] ctxTypes = ContextPathUtil.dbType2ctxTypeMulti(type);
            if (ctxTypes.length == 0) {
                return 0;
            }

            final int[] nbrOfObjects = {0};
            itree.iterateTopNodes(new IndexEntriesWalkerInterruptable() {
                public boolean process(String ctxPath, String value) {
                    ContextPathUtil.CtxPathParser ctxParser = new ContextPathUtil.CtxPathParser(ctxPath);
                    int t = ctxParser.extractLastCtxType();
                    for (int ct : ctxTypes) {
                        if (ct == t) {
                            nbrOfObjects[0]++;
                        }
                    }
                    return true;
                }
            });

            return nbrOfObjects[0];
        }

        public Date lastDDLTimeForType(String type) {
            final int[] ctxTypes = ContextPathUtil.dbType2ctxTypeMulti(type);
            if (ctxTypes.length == 0) {
                return null;
            }

            final long[] timestamp = {-1L};
            itree.iterateTopNodes(new IndexEntriesWalkerInterruptable() {
                public boolean process(String ctxPath, String value) {
                    ContextPathUtil.CtxPathParser ctxParser = new ContextPathUtil.CtxPathParser(ctxPath);
                    int t = ctxParser.extractLastCtxType();
                    for (int ct : ctxTypes) {
                        if (ct == t) {
                            String fileName = ctxParser.extractFileName();
                            String _timestamp = itree.getFileAttribute(fileName, IndexTree.TIMESTAMP_ATTR);//"timestamp");
                            if (_timestamp != null) {
                                long ts = Long.parseLong(_timestamp);
                                if (ts > timestamp[0]) {
                                    timestamp[0] = ts;
                                }
                            }
                        }
                    }
                    return true;
                }
            });

            return new Date(timestamp[0]);
        }

        @NotNull
        public String[] listTypes() {
            List<String> out = new ArrayList<String>();
            for (String type : ContextPathUtil.listSupportedDbTypes()) { //dbType2ctx.keySet()) {
                out.add(type.replace('_', ' '));
            }
            return out.toArray(new String[out.size()]);
        }

        public List<ItemToUpdate> getObjectListForType(final String type) {

            final int[] ctxTypes = ContextPathUtil.dbType2ctxTypeMulti(type);
            final List<ItemToUpdate> out = new ArrayList<ItemToUpdate>();
            if (ctxTypes.length == 0) {
                return out;
            }

            itree.iterateTopNodes(new IndexEntriesWalkerInterruptable() {
                public boolean process(String ctxPath, String value) {
                    ContextPathUtil.CtxPathParser ctxParser = new ContextPathUtil.CtxPathParser(ctxPath);
                    int type2 = ctxParser.extractLastCtxType();
                    for (int t : ctxTypes) {
                        if (type2 == t) {
                            out.add(new ItemToUpdate(null, ctxParser.lastCtxName(), type));
                        }
                    }
                    return true;
                }
            });
            return out;
        }

        public void flush() {
            if (modCounter != itree.getModificationCount()) {
                // save index
                DbSchemaIndex.this.flush();

                modCounter = itree.getModificationCount();
                filesUpdated = 0;
            }
        }
    }

    private String encodeFileName(String type, String name) {
        return (type + "!" + name + ".dump").toLowerCase();
    }

    private String decodeObjectName(String fileName) {
        if (fileName.endsWith(".dump")) {
            String[] parts = fileName.split("\\!");
            if (parts.length == 2 && parts[1].length() > ".dump".length()) {
                return parts[1].substring(0, parts[1].length() - ".dump".length());
            }
        }
        return null;
    }

    protected AbstractSchema createSimpleIndex(DbUrl dbUrl, IndexTree itree, File indexDirPath, String indexFileName) {
        return new DbSchema(dbUrl, itree, indexDirPath, indexFileName);
    }


    private class DbSchema extends AbstractSchemaBase {
        DbUrl dbUrl;

        public DbSchema(DbUrl dbUrl, IndexTree itree, File indexDirPath, String indexFileName) {
            super(dbUrl.getUser(), itree, indexDirPath, indexFileName);
            this.dbUrl = dbUrl;
        }


        public NameProvider getNameProvider() {
            AbstractSchema proxy = DbSchemaIndex.this.getSimpleIndex(userName);
            final ResolveHelper resolver = proxy.getResolveHelper();
            return new NameProvider() {
                public String getContextPathValue(String ctxPath) {
                    return getIndexTree().getContextPathValue(ctxPath);
                }

                @NotNull
                public ContextItem[] findTopLevelItems(int[] ctxTypes, @Nullable String name) {
                    return getIndexTree().findCtxItems(ctxTypes, name);
                }

                @NotNull
                public ContextItem[] findLocalCtxItems(String ctxPath, int[] ctxTypes) {
                    return getIndexTree().findCtxItems(ctxPath, ctxTypes);
                }

                @NotNull
                @Override
                public ResolveHelper getResolver() {
                    return resolver;
                }
            };
        }


        public SqlFile getSourceFile(String ctxPath) {
            String fileName = ContextPathUtil.extractFilePath(ctxPath);
            File f = new File(indexDirPath, fileName);
            String objName = decodeObjectName(fileName);
            String path = "[" + dbUrl.getAlias() + "] " + objName;
            String _timestamp = itree.getFileAttribute(fileName, IndexTree.TIMESTAMP_ATTR);
            long timestamp = 0L;
            if (_timestamp != null) {
                try {
                    timestamp = Long.parseLong(_timestamp);
                } catch (NumberFormatException ignored) {
                }
            }

            String content = "";
            if (!f.exists()) {
                // Check whether the script can be generate for the object
                int otype = ContextPathUtil.extractLastCtxType(ctxPath);
                switch (otype) {
                    case ContextPath.SYNONYM: /// generate script
                        String value = itree.getContextPathValue(ctxPath);
                        ContextPathUtil.SynonymAttributes attr = ContextPathUtil.decodeSynonymValue(value);
                        content = SynonymLoader.createScript(
                                objName, attr.synonymOwner, attr.targetSchema, attr.targetObj
                        ).toUpperCase();
                        break;
                    case ContextPath.SYSTEM_FUNC:
                        // TODO -- generate content on the fly
                        return new SysFuncFile(
                                dbUrl,
                                path,
                                objName,
                                fileName,
                                content) {

                            @Override
                            public AbstractSchema getSimpleIndex() {
                                return DbSchemaIndex.this.getSimpleIndex(userName);
                            }
                        };
                }


                return new DbDumpedSqlFile(
                        dbUrl,
                        objName, //path,
                        fileName, //f.getAbsolutePath(),
                        path,
                        content,
                        timestamp,
                        null//f
                ) {

                    @Override
                    public AbstractSchema getSimpleIndex() {
                        return DbSchemaIndex.this.getSimpleIndex(userName);

                    }
                };
            } else {
                return new DbDumpedSqlFile(
                        dbUrl,
                        objName, //path,
                        fileName, //f.getAbsolutePath(),
                        path,
                        null, //content,
                        timestamp,
                        f
                ) {

                    @Override
                    public AbstractSchema getSimpleIndex() {
                        return DbSchemaIndex.this.getSimpleIndex(userName);

                    }
                };
            }
        }

        public boolean isImmutable() {
            // mutable by default
            return false;
        }

        public void flush() {
            try {
                String idxFile = new File(indexDirPath, indexFileName).getAbsolutePath();
                itree.dumpNames(idxFile, new IndexTree.IndexEntryFilter() {
                    public boolean accept(String ctxPath, String value) {
                        // save all entries except System ones
                        return ctxPath != null &&
                                ContextPathUtil.extractLastCtxType(ctxPath) != ContextPath.SYSTEM_FUNC;
                    }
                }
                );
            } catch (IOException e) {
                // todo  -- handle failing of index creation
            }
        }

    }

}
