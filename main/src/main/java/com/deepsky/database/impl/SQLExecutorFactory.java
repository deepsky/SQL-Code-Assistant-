package com.deepsky.database.impl;

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.DBException;
import com.deepsky.database.exec.SQLExecutor;


public class SQLExecutorFactory {

    public static SQLExecutor create(ConnectionManager conn) throws DBException {
        return ((ConnectionManagerImpl)conn).getSQLExecutor();
    }
}
