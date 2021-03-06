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

package com.deepsky.lang.plsql.psi.ddl;

import com.deepsky.lang.plsql.psi.ColumnDefinition;
import com.deepsky.lang.plsql.psi.GenericConstraint;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public interface TableDefinition extends SqlDDLStatement {
    int REGULAR = 0;
    int TEMPORARY = 1;
    int EXTERNAL_ORGANIZED = 2;
    int IOT_ORGANIZED = 3;
    int HEAP_ORGANIZED = 4;

    // partition type
    int PARTITION_BY_RANGE  = 10;
    int PARTITION_BY_HASH   = 11;
    int PARTITION_NONE      = -1;

    String getTableName();
    PsiElement getTableNameElement();

    @NotNull
    ColumnDefinition[] getColumnDefs();

    ColumnDefinition getColumnByName(String name);

    @NotNull
    GenericConstraint[] getConstraints();

    int getTableType();
    int getPartitionType();

    @Nullable
    PartitionSpecification getPartitionSpec();

    boolean definedAsSelect();

    /**
     * Return SELECT statement if tabled defined AS SELECT * FROM ...
     * @return
     */
    @Nullable
    SelectStatement getSelectStatement();
}
