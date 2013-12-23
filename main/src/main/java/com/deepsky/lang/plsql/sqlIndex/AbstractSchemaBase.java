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
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.res_newlook.NameResolver;
import com.deepsky.lang.plsql.sqlIndex.impl.WordIndexManagerImpl;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

public abstract class AbstractSchemaBase implements AbstractSchema {

    protected IndexTree itree;
    protected File indexDirPath;
    protected String indexFileName;
    protected String userName;

    public AbstractSchemaBase(String userName, IndexTree itree, File indexDirPath, String indexFileName) {
        this.userName = userName;
        this.itree = itree;
        this.indexDirPath = indexDirPath;
        this.indexFileName = indexFileName;
    }

    public String getName() {
        return userName;
    }

    public IndexTree getIndexTree() {
        return itree;
    }

    public String getIndexPath() {
        return indexDirPath.getAbsolutePath();
    }

    public String getIndexFileName() {
        return indexFileName;
    }

    public DbUrl getDbUrl() {
        throw new ConfigurationException("Call should be redirected to an enclosing class!");
    }

    WordIndexManager wordIndexManager;
    public WordIndexManager getWordIndexManager(){
        if(wordIndexManager == null){
            wordIndexManager = new WordIndexManagerImpl(this);
        }
        return wordIndexManager;
    }


    public ResolveFacade getResolveFacade() {
        throw new ConfigurationException("Call should be redirected to an enclosing class!");
    }

    public ResolveHelper getResolveHelper() {
        throw new ConfigurationException("Call should be redirected to an enclosing class!");
    }

    public VirtualFile getSourceFile(ResolveDescriptor desc) {
        throw new ConfigurationException("Call should be redirected to an enclosing class!");
    }


    public NameResolver getLLResolver(){
        throw new ConfigurationException("Call should be redirected to an enclosing class!");
    }

}
