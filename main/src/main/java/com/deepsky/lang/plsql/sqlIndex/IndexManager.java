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

import com.deepsky.database.ConnectionManagerListener;
import com.deepsky.database.ora.DbUID;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.DbUrlSID;
import com.deepsky.lang.plsql.objTree.SchemaTreeBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * Class responsible for management Object and Word indexes
 */
public interface IndexManager extends ConnectionManagerListener {

    String  FS_SCHEMA_NAME = "local_fs";

    // todo -- how to garantee uniqeness?
    DbUrlSID   FS_URL = new DbUrlSID(FS_SCHEMA_NAME, "localhost", 80, FS_SCHEMA_NAME){
        public String getAlias() {
            return "Project";
        }
    };

    @Deprecated
    @NotNull
    SqlDomainIndex findOrCreateIndex(DbUrl dbUrl);

    @NotNull
    AbstractSchema findOrCreateIndex(DbUrl dbUrl, int dummy);

    @Deprecated
    @Nullable
    SqlDomainIndex getIndex(DbUrl dbUrl);

    @Nullable
    AbstractSchema getIndex(DbUrl dbUrl, int dummy);

    @Deprecated
    void removeIndex(DbUrl dbUrl);

    SqlDomainIndex[] getIndexes();

    SqlDomainIndex getIndex(DbUID dbUid);
//    void removeIndex(DbUID dbUid);

    /**
     * Enable/disable offline cache
     *
     * @param dbUrl - cache url
     * @param flag - true- enable offline cache, false - desable
     */
    void enableOfflineCache(DbUrl[] dbUrl, boolean flag);

    @NotNull
    SqlDomainIndex getFSIndex();

    void populateSchemaTree(DbUrl dbUrl, SchemaTreeBuilder builder);

    void addListener(IndexManagerListener listener);
    void removeListener(IndexManagerListener listener);

    void start();
    void stop();

    void handleUpdate(StateEvent state);
}
