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
import com.deepsky.lang.plsql.completion.NameProvider;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.res_newlook.NameResolver;
import com.intellij.openapi.vfs.VirtualFile;

public interface AbstractSchema {

    String getName();
    DbUrl getDbUrl();
    
    IndexTree getIndexTree();
    String getIndexPath();
    String getIndexFileName();


    VirtualFile getSourceFile(String ctxPath);

    /**
     * Get absolute path to the source file,
     * ResolverDescriptor may reference an index with another user name,
     * on this case domain index will look up for appropriate index
     *
     * @param desc - resolver
     * @return  file path
     */
    VirtualFile getSourceFile(ResolveDescriptor desc);


    ResolveFacade getResolveFacade();

    NameProvider getNameProvider();

    /**
     * Get resolve helper
     *
     * Actually, implementation of the SimpleIndex must NOT take care of the resolver,
     * it should redirect a call to SqlDomainIndex which manages all resolvers
     *
     * @return
     */
    ResolveHelper getResolveHelper();

    NameResolver getLLResolver();

    WordIndexManager getWordIndexManager();

    boolean isImmutable();
    void flush();
}
