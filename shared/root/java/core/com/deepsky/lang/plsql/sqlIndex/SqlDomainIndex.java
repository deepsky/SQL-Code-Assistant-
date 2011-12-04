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

import com.deepsky.database.ora2.DbObjectCache;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;

public interface SqlDomainIndex {

    DbUID getDbUID();

    File getIndexHomeDir();

    @NotNull
    DbObjectCache getObjectCache(@NotNull String userName);

    boolean addIndex(@NotNull String userName);

    void loadPublicSynonyms(InputStream stream);

    /**
     * Add new schema index or replace existing one
     *
     * @param index
     * @return
     */
    boolean addIndex(@NotNull AbstractSchema index);

    boolean removeIndex(@NotNull String userName);

    /**
     * Remove index from list of available ones
     *
     * @param userName
     * @return - true if the specified index is the only user index
     */
    boolean detachIndex(@NotNull String userName);

    /**
     * Get lowlevel index
     *
     * @param userName - index name, if name is null then master index will be returned
     * @return - lowlevel index
     */
    IndexTree getIndex(@NotNull String userName);

    ResolveHelper getResolver(@NotNull String userName);

    void flush();

    AbstractSchema getSimpleIndex(@NotNull String user);

    AbstractSchema[] getIndexes();

}
