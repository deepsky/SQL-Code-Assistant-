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

package com.deepsky.actions;

import com.deepsky.database.DBException;
import com.deepsky.database.exec.RowSetManager;
import com.deepsky.database.exec.SQLUpdateStatistics;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public interface SqlScriptRunner {

    void runSqlStatement(
            @NotNull ASTNode statement,
            @NotNull SqlStatementResultListener listener
    ) throws AlreadyStartedException, DBException;

    void generateDDLScript(
            @NotNull String objectType,
            @NotNull String objectName,
            @NotNull DMLResultListener listener
    ) throws AlreadyStartedException, DBException;

    void runQuery(
            @NotNull String query,
            @NotNull QueryResultListener listener
    ) throws AlreadyStartedException, DBException;

    /**
     * Execute Explain plan for the specified SQL statement
     * @param sqlStatement to generate execution plan for
     * @param listener
     * @throws AlreadyStartedException
     * @throws DBException
     */
    void runExplainPlan(
            @NotNull String sqlStatement,
            @NotNull DMLResultListener listener
    ) throws AlreadyStartedException, DBException;


    boolean inProgress();

    boolean isIdle();


    public interface SqlStatementResultListener {
        void handleQueryResult(RowSetManager result);
        void handleDMLResult(SQLUpdateStatistics result);
    }

    public interface QueryResultListener {
        void handleQueryResult(RowSetManager result);
    }

    public interface DMLResultListener {
        void handleDMLResult(SQLUpdateStatistics result);
    }

    public class AlreadyStartedException extends Exception {
        public AlreadyStartedException(){
        }

        public AlreadyStartedException(String message){
            super(message);
        }
    }
}
