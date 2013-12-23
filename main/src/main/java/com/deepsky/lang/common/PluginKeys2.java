package com.deepsky.lang.common;

import com.deepsky.database.DbSchemaIndexer;
import com.deepsky.lang.integration.FSContentTracker;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;

public class PluginKeys2 {
    public static final PluginKey<DbSchemaIndexer> DB_NAMES_INDEXER = PluginKey.create(SharedConstants.CACHE_MANAGER);
    public static final PluginKey<IndexManager> SQL_INDEX_MAN = PluginKey.create(SharedConstants.SQL_INDEX_MANAGER);

    public static final PluginKey<FSContentTracker> FS_CONTENT_TRACKER = PluginKey.create(SharedConstants.FS_CONTENT_TRACKER);
}
