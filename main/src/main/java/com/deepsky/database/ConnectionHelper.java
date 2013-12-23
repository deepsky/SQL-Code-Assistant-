package com.deepsky.database;

import com.deepsky.database.DBException;
import com.deepsky.database.DbMetaInfo;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.LowLevelConnectionProvider;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;

public interface ConnectionHelper {

    void connect(DbUrl url) throws DBException;
    void disconnect();

    DbMetaInfo getDbMetaInfo();

    @Nullable
    DbUrl getDbUrl();

    boolean isConnected();

    Connection getConnection() throws DBException;

    LowLevelConnectionProvider cloneConnectionHolder() throws DBException;

    void setAutoCommit(boolean enable) throws DBException;

    boolean getAutoCommit();
}
