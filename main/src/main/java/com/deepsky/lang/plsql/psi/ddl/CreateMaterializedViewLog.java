package com.deepsky.lang.plsql.psi.ddl;

import org.jetbrains.annotations.NotNull;

public interface CreateMaterializedViewLog extends SqlDDLStatement {

    @NotNull
    String getMasterTableName();
}
