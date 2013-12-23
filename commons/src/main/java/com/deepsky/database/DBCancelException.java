package com.deepsky.database;

public class DBCancelException extends DBException {
    public DBCancelException() {
        super("ORA-01013");
    }
}
