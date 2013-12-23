package com.deepsky.lang.plsql.psi.ddl;


public interface RenameTable extends SqlDDLStatement {

    String getOriginTable(int group);
}
