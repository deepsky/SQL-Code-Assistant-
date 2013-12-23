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

package com.deepsky.database.exec;


import com.deepsky.database.DBException;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.Subquery;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public interface SQLExecutor {

    // synchronous statement running
    RowSetManager executeQuery(String stmt) throws DBException;

    RowSetManager executeQuery(SelectStatement select) throws DBException;

    RowSetManager executeQuery(Subquery subquery) throws DBException;

    // todo -- should be narrowed
    SQLUpdateStatistics execute(ASTNode node) throws DBException;

    // Object specific management -- todo -- should be revised
    boolean dropTable(String name) throws DBException;

    boolean dropPackage(String name) throws DBException;

    boolean compilePackage(String name) throws DBException;

    boolean dropView(String name) throws DBException;

    boolean dropTrigger(String name) throws DBException;

    boolean enableTrigger(String name) throws DBException;

    boolean disableTrigger(String name) throws DBException;
    //

    /**
     * Cancel current query
     */
    void cancel();

    void close();

    @NotNull
    TrivialResult executeExplainPlanQuery(String query) throws DBException;

    interface TrivialResult {
        List<String> getRowSet();
        long getSpentTime();
    }
}
