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

package com.deepsky.lang.common;

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora2.DbSchemaIndexer;
import com.deepsky.lang.integration.PlSqlFileChangeTracker;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.navigation.NameLookupService;
import com.deepsky.navigation.DbObjectContributor;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.deepsky.view.query_pane.QueryResultWindow;
import com.deepsky.view.query_pane.converters.TIMESTAMPLTZ_Convertor;
import com.deepsky.view.query_pane.converters.TIMESTAMPTZ_Convertor;
import com.deepsky.view.query_pane.converters.TIMESTAMP_Convertor;


public final class PluginKeys {

    public static final PluginKey<ConnectionManager> CONNECTION_MANAGER = PluginKey.create(SharedConstants.CONNECTION_MANAGER);
    public static final PluginKey<DbObjectContributor> DB_OBJECT_CONTR = PluginKey.create(SharedConstants.DB_OBJECT_CONTR);

    public static final PluginKey<QueryResultWindow> QR_WINDOW = PluginKey.create(SharedConstants.QR_WINDOW);
    public static final PluginKey<SqlCodeAssistantSettings> PLUGIN_SETTINGS = PluginKey.create(SharedConstants.PLUGIN_SETTINGS);

    public static final PluginKey<PlSqlFileChangeTracker> PLSQLFILE_CHANGE_TRACKER = PluginKey.create(SharedConstants.PLSQLFILE_CHANGE_TRACKER);
    public static final PluginKey<DbSchemaIndexer> DB_NAMES_INDEXER = PluginKey.create(SharedConstants.CACHE_MANAGER);
    public static final PluginKey<IndexManager> SQL_INDEX_MAN = PluginKey.create(SharedConstants.SQL_INDEX_MANAGER);
    public static final PluginKey<NameLookupService> NAME_LOOKUP = PluginKey.create(SharedConstants.NAME_LOOKUP);
    public static final PluginKey<DbUrl> LOCAL_FS_URL = PluginKey.create(SharedConstants.LOCAL_FS_URL);


    public static final PluginKey<TIMESTAMP_Convertor> TS_CONVERTOR = PluginKey.create(SharedConstants.TIMESTAMP_ValueConvertor);
    public static final PluginKey<TIMESTAMPTZ_Convertor> TSTZ_CONVERTOR = PluginKey.create(SharedConstants.TIMESTAMPTZ_ValueConvertor);
    public static final PluginKey<TIMESTAMPLTZ_Convertor> TSLTZ_CONVERTOR = PluginKey.create(SharedConstants.TIMESTAMPLTZ_ValueConvertor);
}
