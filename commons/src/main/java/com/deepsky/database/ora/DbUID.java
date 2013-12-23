package com.deepsky.database.ora;

public interface DbUID {

    DbUrl buildDbUrl(String user);

    boolean derivedFrom(DbUrl dbUrl);

    String key();

}
