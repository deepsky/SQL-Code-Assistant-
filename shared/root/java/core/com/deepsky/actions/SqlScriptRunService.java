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

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.DBException;
import com.deepsky.database.exec.RowSetManager;
import com.deepsky.database.exec.SQLExecutor;
import com.deepsky.database.exec.SQLUpdateStatistics;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.Subquery;
import com.deepsky.view.utils.ProgressIndicatorController;
import com.deepsky.view.utils.ProgressIndicatorHelper;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import oracle.sql.CLOB;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class SqlScriptRunService implements SqlScriptRunner {

    private Project project;
    private SQLExecutor executor;
    private int status = SqlRunnerStatusIndicator.IDLE;
    private String errorMessage = "";
    private long startMs = 0;

    public SqlScriptRunService(Project project){
        this.project = project;
    }

    public static SqlScriptRunner getInstance(Project project) {
      return ServiceManager.getService(project, SqlScriptRunner.class);
    }

    public synchronized void runSqlStatement(
            @NotNull ASTNode statement,
            @NotNull SqlStatementResultListener listener) throws AlreadyStartedException, DBException {

        if(status == SqlRunnerStatusIndicator.INPROGRESS){
            throw new AlreadyStartedException();
        }

        ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
        this.executor = manager.getSQLExecutor();
        runSqlStatementInternal(
                new SqlStmtRunner(statement, listener)
        );
    }

    public void generateDDLScript(
            @NotNull String objectType,
            @NotNull final String objectName,
            @NotNull final DMLResultListener listener) throws AlreadyStartedException, DBException {

        if(status == SqlRunnerStatusIndicator.INPROGRESS){
            throw new AlreadyStartedException();
        }

        String select = "select dbms_metadata.get_ddl( '<OBJECT_TYPE>', '<OBJECT_NAME>' ) from dual";
        select = select.replace("<OBJECT_TYPE>", objectType.toUpperCase());
        select = select.replace("<OBJECT_NAME>", objectName.toUpperCase());

        ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
        this.executor = manager.getSQLExecutor();
        runSqlStatementInternal(
                new QueryRunner(select, new QueryResultListener(){
                    public void handleQueryResult(RowSetManager result) {
                        try {
                            if (result.getModel().getFetchedRowCount() == 1) {
                                Object o = result.getModel().getValueAt(0, 1);
                                if (o instanceof CLOB) {
                                    listener.handleDMLResult(
                                        new DDLScript(1,
                                                result.getModel().timeSpentOnFetch(),
                                                "", clob2string((CLOB) o))
                                    );
                                }
                            }
                        } catch (Exception e) {
                            final String message = "Could not create DDL script for " + objectName.toUpperCase() + ". " + e.getMessage();
                            ApplicationManager.getApplication().invokeLater(new Runnable() {
                                public void run() {
                                    Messages.showErrorDialog(project, message, "SQL statement execution failed");
                                }
                            });
                        } catch (Throwable e) {
                            final String message = "Could not create DDL script for " + objectName.toUpperCase() + ". " + e.getMessage();
                            ApplicationManager.getApplication().invokeLater(new Runnable() {
                                public void run() {
                                    Messages.showErrorDialog(project, message, "SQL statement execution failed");
                                }
                            });
                        } finally {
                            result.getModel().close();
                        }
                    }
                })
        );
    }

    public void runQuery(
            @NotNull String select, @NotNull final QueryResultListener listener
        ) throws AlreadyStartedException, DBException {

        if(status == SqlRunnerStatusIndicator.INPROGRESS){
            throw new AlreadyStartedException();
        }
        ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
        this.executor = manager.getSQLExecutor();
        runSqlStatementInternal(
                new QueryRunner(select, new QueryResultListener(){
                    public void handleQueryResult(RowSetManager result) {
                        try {
                            listener.handleQueryResult(result);
                        } catch (Throwable ignored) {
//                        } finally {
//                            result.getModel().close();
                        }
                    }
                })
        );
    }

    public boolean inProgress() {
        return status == ProgressIndicatorController.INPROGRESS;
    }

    public boolean isIdle() {
        return status == SqlRunnerStatusIndicator.IDLE
                || status == SqlRunnerStatusIndicator.CANCELED
                || status == SqlRunnerStatusIndicator.DONE_SUCCESSFULLY
                || status == SqlRunnerStatusIndicator.FAILED;
    }

    private void runSqlStatementInternal(Runnable runner) {
        SqlRunnerStatusIndicator ind = new SqlRunnerStatusIndicator();

        Thread schemaMonitor = new Thread(runner, "SQL Runner Thread");
        status = ProgressIndicatorController.INPROGRESS;

        schemaMonitor.setPriority(3);
        schemaMonitor.setDaemon(true);
        schemaMonitor.start();

        // if query was executed quickly - do not run progress indicator
        //sleep(500, false);

        switch (status) {
            case ProgressIndicatorController.INPROGRESS:
                // run progress indicator
                new ProgressIndicatorHelper(project, "Execute SQL statement").runBackgrounableWithProgressInd(ind, false);
                return;
            case ProgressIndicatorController.FAILED:
                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    public void run() {
                        Messages.showErrorDialog(project, errorMessage, "SQL statement execution failed");
                    }
                });
                break;
        }
    }

    /**
     * Runner of generic statement
     */
    private class SqlStmtRunner implements Runnable {

        private SqlStatementResultListener listener;
        private ASTNode statement;

        public SqlStmtRunner(ASTNode statement, SqlStatementResultListener listener){
            this.statement = statement;
            this.listener = listener;
        }

        public void run() {
            try {
                startMs = System.currentTimeMillis();
                if(PlSqlElementTypes.QUERIES.contains(statement.getElementType())){
                    // Run query
                    final RowSetManager rowSet;
                    // run Query
                    if(statement.getElementType() == PLSqlTypesAdopted.SUBQUERY){
                        rowSet = executor.executeQuery((Subquery)statement.getPsi());
                    } else {
                        rowSet = executor.executeQuery((SelectStatement) statement.getPsi());
                    }
                    status = ProgressIndicatorController.DONE_SUCCESSFULLY;

                    sleep(100, false);
                    ApplicationManager.getApplication().invokeLater(new Runnable() {
                        public void run() {
                            listener.handleQueryResult(rowSet);
                        }
                    });
                } else {
                    // Run DML or DDL statement
                    final SQLUpdateStatistics stats = executor.execute(statement);
                    status = ProgressIndicatorController.DONE_SUCCESSFULLY;

                    sleep(100, false);
                    ApplicationManager.getApplication().invokeLater(new Runnable() {
                        public void run() {
                            listener.handleDMLResult(stats);
                        }
                    });
                }

            } catch (DBException e) {
                errorMessage = e.getMessage();
                status = ProgressIndicatorController.FAILED;
            } catch (SyntaxTreeCorruptedException e) {
                errorMessage = "Cannot recognize syntax of the statement";
                status = ProgressIndicatorController.FAILED;
            } catch (Throwable e) {
                errorMessage = e.getMessage();
                status = ProgressIndicatorController.FAILED;
            }
        }
    }


    /**
     * Query runner
     */
    private class QueryRunner implements Runnable {

        private QueryResultListener listener;
        private String query;

        public QueryRunner(String query, QueryResultListener listener){
            this.query = query;
            this.listener = listener;
        }

        public void run() {
            try {
                startMs = System.currentTimeMillis();
                final RowSetManager rowSet = executor.executeQuery(query);
                status = ProgressIndicatorController.DONE_SUCCESSFULLY;

                sleep(100, false);
                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    public void run() {
                        listener.handleQueryResult(rowSet);
                    }
                });

            } catch (DBException e) {
                errorMessage = e.getMessage();
                status = ProgressIndicatorController.FAILED;
            } catch (SyntaxTreeCorruptedException e) {
                errorMessage = "Cannot recognize syntax of the statement";
                status = ProgressIndicatorController.FAILED;
            } catch (Throwable e) {
                errorMessage = e.getMessage();
                status = ProgressIndicatorController.FAILED;
            }
        }
    }


    private void sleep(int time, boolean reportError) {
        Object synh = new Object();
        synchronized (synh) {
            try {
                synh.wait(time);
            } catch (InterruptedException e) {
                if (reportError) {
                    errorMessage = "Internal Error: " + e.getMessage();
                    status = ProgressIndicatorController.FAILED;
                }
            }
        }
    }

    private class SqlRunnerStatusIndicator implements ProgressIndicatorController {

        final static int IDLE = 10;

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


    /**
     * Wrapper of DDL script
     */
    private class DDLScript implements SQLUpdateStatistics {

        int rowsAffected;
        long timeSpent;
        String message;
        String errors;

        public DDLScript(int rowsAffected, long timeSpent, String message, String errors) {
            this.rowsAffected = rowsAffected;
            this.timeSpent = timeSpent;
            this.message = message;
            this.errors = errors;
        }

        public int rowsAffected() {
            return rowsAffected;
        }

        public long timeSpent() {
            return timeSpent;
        }

        public String errorMessages() {
            return errors;
        }

        public String resultMessage() {
            return message;
        }
    }


    /**
     * CLOB to string convertor
     * @param clob source
     * @return string extracted from CLOB
     * @throws SQLException
     */
    private String clob2string(CLOB clob) throws SQLException {
        if (clob == null || clob.isEmptyLob() || clob.length() == 0) {
            return "";
        } else {
            InputStream r = clob.getAsciiStream();
            ByteArrayOutputStream writer = new ByteArrayOutputStream();
            byte[] buff = new byte[1000];
            int size = 0;
            try {
                while ((size = r.read(buff)) > 0) {
                    writer.write(buff, 0, size);
                }

                // Converts the buffer's contents into a string decoding bytes using the
                // platform's default character set.
                return writer.toString();
            } catch (IOException e) {
                throw new SQLException(e.getMessage());
            } finally {
                if(r != null){
                    try {
                        r.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }
    }

}
