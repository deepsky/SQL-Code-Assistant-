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
import com.deepsky.database.ora2.DbObjectCache;
import com.deepsky.lang.common.PlSqlSupportLoader;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.completion.NameProvider;
import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.deepsky.lang.plsql.completion.VariantsProviderImpl;
import com.deepsky.lang.plsql.resolver.*;
import com.deepsky.lang.plsql.resolver.index.ContextItem;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.res_newlook.CompositeRefResolver;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.sqlIndex.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class FSIndex extends SqlIndexBase {


    public FSIndex(@NotNull IndexManager indexManager, @NotNull File indexDir, @NotNull DbUID dbUID) {
        super(indexManager, indexDir, dbUID);

        cacheListener = new FSRefResolverListenerImpl();

        // load FS index content
        addIndex(IndexManager.FS_SCHEMA_NAME);

        // load SYS schema index -- todo -- SYS version number should be configurable from GUI   
        PlSqlSupportLoader c = (PlSqlSupportLoader) ApplicationManager.getApplication().getComponent(PlSqlSupportLoader.PLSQL_APPLICATION);
        addIndex(c.getSysSchemaIndexProvider().getSysUserIndex(SysSchemaIndexProvider.SYS_10, this));

        // load public synonyms for default version SYS explicitly
        c.getSysSchemaIndexProvider().loadPublicSynonyms(SysSchemaIndexProvider.SYS_10, synonymsITree);
    }


    @NotNull
    public DbObjectCache getObjectCache(@NotNull String userName) {
        throw new NotSupportedException("DbObjectCache not supported for FSIndex");
    }


    protected AbstractSchema createSimpleIndex(DbUrl dbUrl, IndexTree itree, File indexDirPath, String indexFileName) {
        return new FSSchema(dbUrl, itree, indexDirPath, indexFileName);
    }


    AbstractSchema attached;

    public void attach(@NotNull AbstractSchema aschema) {
        attached = aschema;
        ((CompositeRefResolver) user2resolver.get(IndexManager.FS_SCHEMA_NAME)).setExternalResolver(aschema.getLLResolver());
    }

    public void detach() {
        attached = null;
        ((CompositeRefResolver) user2resolver.get(IndexManager.FS_SCHEMA_NAME)).setExternalResolver(null);
    }

    private class FSRefResolverListenerImpl implements RefResolverListener {

        public void resolveReference(List<ResolveDescriptor> out, String schemaName, RefHolder ref) {
            if (attached != null && attached.getName().equalsIgnoreCase(schemaName)) {
                // try to resolve against attached schema
                ResolveDescriptor[] descs = attached.getResolveHelper().resolveReferencePoly(ref);
                out.addAll(Arrays.asList(descs));
            } else {
                RefResolver c = user2resolver.get(schemaName);
                if (c != null) {
                    c.resolve(out, ref);
                } else {
                    // todo -- dynamically load more indexes?
                    int h = 0;
                }
            }
        }
    }


    private class FSSchema extends AbstractSchemaBase {//implements SimpleIndex {
        DbUrl dbUrl;

        public FSSchema(DbUrl dbUrl, IndexTree itree, File indexDirPath, String indexFileName) {
            super(dbUrl.getUser(), itree, indexDirPath, indexFileName);
            this.dbUrl = dbUrl;
        }

        public VariantsProvider getVariantsProvider() {
            AbstractSchema proxy = FSIndex.this.getSimpleIndex(userName);
            ResolveHelper resolver = proxy.getResolveHelper();
            return new VariantsProviderImpl(new NameProvider() {

                public String getContextPathValue(String ctxPath) {
                    return getIndexTree().getContextPathValue(ctxPath);
                }

                @NotNull
                public ContextItem[] findTopLevelItems(int[] ctxTypes, @Nullable String name) {
                    if (attached != null) {
                        return attached.getIndexTree().findCtxItems(ctxTypes, name);
                    } else {
                        return getIndexTree().findCtxItems(ctxTypes, name);
                    }
                }

                @NotNull
                public ContextItem[] findLocalCtxItems(String ctxPath, int[] ctxTypes) {
                    return getIndexTree().findCtxItems(ctxPath, ctxTypes);
                }
            }, resolver);
        }


        public VirtualFile getSourceFile(String ctxPath) {
            String path = ContextPathUtil.extractFilePath(ctxPath);

            if (!new File(path).exists() || new File(path).isDirectory()) {
                return null;
            }

            try {
                URI uri = new File(path).toURI();
                return VfsUtil.findFileByURL(uri.toURL());
            } catch (MalformedURLException e) {
                return null;
            }
        }

        public boolean isImmutable() {
            // mutable by default
            return false;
        }

        public void flush() {
            try {
                String idxFile = new File(indexDirPath, indexFileName).getAbsolutePath();
                itree.dumpNames( idxFile, new IndexTree.IndexEntryFilter(){
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

