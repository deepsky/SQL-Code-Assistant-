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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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
import com.deepsky.database.exec.RowSetModel;
import com.deepsky.database.exec.SQLExecutor;
import com.deepsky.database.exec.SQLUpdateStatistics;
import com.deepsky.lang.plsql.tree.Node;
import com.deepsky.view.utils.ProgressIndicatorController;
import com.deepsky.view.utils.ProgressIndicatorHelper;
import com.intellij.openapi.application.ApplicationManager;

public class SqlRunnerHelper implements Runnable {

    SQLExecutor executor;
    QueryResultListener queryListener;
    DMLResultListener dmlListener;

    String select;
    Node statement;
    SqlQueryRunnerIndicatorImpl ind;
    String errorMessage = "";
    long startMs = 0;

    int status = ProgressIndicatorController.INPROGRESS;

    public SqlRunnerHelper(SQLExecutor executor, String select, QueryResultListener queryListener) {
        this.executor = executor;
        this.select = select;
        this.queryListener = queryListener;
    }


    public SqlRunnerHelper(SQLExecutor executor, Node statement, DMLResultListener dmlListener) {
        this.executor = executor;
        this.statement = statement;
        this.dmlListener = dmlListener;
    }

    public void runSqlStatement() {
        ind = new SqlQueryRunnerIndicatorImpl();

        Thread schemaMonitor = new Thread(this, "SQL Runner Thread");

        schemaMonitor.setPriority(3);
        schemaMonitor.setDaemon(true);
        schemaMonitor.start();

        // run progress indicator
        new ProgressIndicatorHelper("Execute SQL statement").runBackgrounableWithProgressInd(ind, false);
    }

    public void run() {
        try {
            startMs = System.currentTimeMillis();
            if (queryListener != null) {
                // run Query
                final RowSetModel rowSet = executor.executeQuery(select);
                status = ProgressIndicatorController.DONE_SUCCESSFUL;

                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    public void run() {
                        queryListener.handleQueryResult(rowSet);
                    }
                });
            } else {
                // run DML or DDL
                final SQLUpdateStatistics stats = executor.execute(statement);
                status = ProgressIndicatorController.DONE_SUCCESSFUL;

                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    public void run() {
                        dmlListener.handleDMLResult(stats);
                    }
                });
            }

        } catch (DBException e) {
            errorMessage = e.getMessage();
            status = ProgressIndicatorController.FAILED;
        } catch (Throwable e) {
            errorMessage = e.getMessage();
            status = ProgressIndicatorController.FAILED;
        }
    }

    public boolean isCompleted() {
        return status != ProgressIndicatorController.INPROGRESS;
    }


    private class SqlQueryRunnerIndicatorImpl implements ProgressIndicatorController {

        long seconds = 0;

        public int getStatus() {
            return status;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void cancel() {
            executor.cancel();
            status = ProgressIndicatorController.CANCELED;
        }

        public String getCurrentStepName() {
            seconds = (System.currentTimeMillis() - startMs) / 1000;
            if (seconds > 0) {
                return "Running " + seconds + " seconds ...";
            } else {
                return "Running ...";
            }
        }

        public String errorDialogTitle() {
            return "SQL Statement execution failed";
        }
    }

    public interface QueryResultListener {
        void handleQueryResult(RowSetModel result);
    }

    public interface DMLResultListener {
        void handleDMLResult(SQLUpdateStatistics result);
    }

}
