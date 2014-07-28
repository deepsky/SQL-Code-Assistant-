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

package com.deepsky.database.exec.impl;

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.DBCancelException;
import com.deepsky.database.DBException;
import com.deepsky.database.exec.RecordCache;
import com.deepsky.database.exec.RowSetManager;
import com.deepsky.database.exec.SQLExecutor;
import com.deepsky.database.exec.SQLUpdateStatistics;
import com.deepsky.lang.common.ResolveProvider;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.OrderByClause;
import com.deepsky.lang.plsql.psi.PlSqlBlock;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.Subquery;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandler;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.deepsky.lang.plsql.psi.spices.CompilableObject;
import com.deepsky.lang.plsql.psi.utils.PlSqlUtil;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class SQLExecutorSimple implements SQLExecutor {

    private Connection conn;
    private ConnectionManager connectionManager;
    private Statement stmt;

    boolean canceled = false;

    SimpleDateFormat dateToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    SimpleDateFormat timestampToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    SimpleDateFormat timestampTZToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS Z");

    RowSetManagerImpl rsmodel;
    int SHUNK_SIZE = 200;

    public SQLExecutorSimple(Connection conn, ConnectionManager connectionManager) {
        this.conn = conn;
        this.connectionManager = connectionManager;
    }


    public abstract ResolveFacade getDefaultResolver();

    public RowSetManager executeQuery(@NotNull String select) throws DBException {
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode ast = generator.parse(select);
        ASTNode node = ast.findChildByType(PLSqlTypesAdopted.SELECT_EXPRESSION);
        if (node == null) {
            throw new DBException("Could not find SELECT statement to execute");
        }

        ResolveFacade domainResolver = getDefaultResolver();
        PsiElement element = ast.getPsi();
        ((ResolveProvider) element).setResolver(domainResolver);

        return executeQuery((SelectStatement) node.getPsi());
    }

    public RowSetManager executeQuery(@NotNull SelectStatement select) throws DBException {
        if (rsmodel != null) {
            rsmodel.close();
        }

        rsmodel = new RowSetManagerImpl(select);
        rsmodel.refresh();
        return rsmodel;
    }

    public RowSetManager executeQuery(@NotNull Subquery subquery) throws DBException {
        if (rsmodel != null) {
            rsmodel.close();
        }

        rsmodel = new RowSetManagerImpl(subquery.getSelectStatement());
        return rsmodel;
    }

    private SelectStatement adoptOrderByClause(SelectStatement select, int columnId, int dir) throws DBException {
        OrderByClause orderBy = select.getOrderByClause();
        String sql_text = select.getText();
        String adoptedSelect = sql_text;
        if (orderBy != null) {
            // only one 'ORDER BY' allowed
            // remove the clause
            int start = orderBy.getTextRange().getStartOffset();
            int end = orderBy.getTextRange().getEndOffset();
            adoptedSelect = sql_text.substring(0, start) + sql_text.substring(end, sql_text.length());
        }

        adoptedSelect += " ORDER BY " + (columnId + 1) + ((dir == RowSetManager.DESCENDING) ? " DESC" : " ASC");

        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode ast = generator.parse(adoptedSelect);
        ASTNode node = ast.findChildByType(PLSqlTypesAdopted.SELECT_EXPRESSION);
        if (node != null) {
            SelectStatement select2 = (SelectStatement) node.getPsi();
            ResolveFacade facade = ((ResolveProvider) select.getContainingFile()).getResolver();
            ((ResolveProvider) select2.getContainingFile()).setResolver(facade);
            return select2;
        }

        throw new DBException("Could not build a query with sorting");
    }

    private String adoptOrderByClause(String sql_text, int columnId, int dir) {
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode ast = generator.parse(sql_text);
        ASTNode _select = ast != null ? ast.findChildByType(PLSqlTypesAdopted.SELECT_EXPRESSION) : null;
        if (_select != null) {
            SelectStatement select = (SelectStatement) _select.getPsi();
            OrderByClause orderBy = select.getOrderByClause();
            String cuttedSql = sql_text;
            if (orderBy != null) {
                // only one 'ORDER BY' allowed
                // remove the clause
                int start = orderBy.getTextRange().getStartOffset();
                int end = orderBy.getTextRange().getEndOffset();
                cuttedSql = sql_text.substring(0, start) + sql_text.substring(end, sql_text.length());
            }

            cuttedSql += " ORDER BY " + (columnId + 1) + ((dir == RowSetManager.DESCENDING) ? " DESC" : " ASC");
            return cuttedSql;
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    public SQLUpdateStatistics execute(ASTNode node) throws DBException {
        String responseMessage = "";
        int size = 0;

        // close result set if exists
        close();

        IElementType etype = node.getElementType();
        long ms = System.currentTimeMillis();
        String messages = null;

        // DML statement processing
        if (etype == PLSqlTypesAdopted.DELETE_COMMAND) {
            size = executeUpdate(node.getText());
            responseMessage = size + " rows deleted";
        } else if (etype == PLSqlTypesAdopted.INSERT_COMMAND) {
            size = executeUpdate(node.getText());
            responseMessage = size + " rows inserted";
        } else if (etype == PLSqlTypesAdopted.UPDATE_COMMAND) {
            size = executeUpdate(node.getText());
            responseMessage = size + " rows updated";
        } else if (etype == PLSqlTypesAdopted.MERGE_COMMAND) {
            size = executeUpdate(node.getText());
            responseMessage = size + " rows updated";
        } else {
            // DDL statement processing
            responseMessage = RespMessageTable.getResponseMessage(etype);
            if (responseMessage == null) {
                throw new NotSupportedException("Specified SQL statement not supported");
            }

            if (node.getPsi() instanceof CompilableObject) {
                messages = executeCreateObject((CompilableObject) node.getPsi());
            } else if (node.getPsi() instanceof PlSqlBlock) {
                messages = executePlSqlBlock((PlSqlBlock) node.getPsi());
            } else {
                executeUpdate(node.getText());
            }
        }

        ms = System.currentTimeMillis() - ms;

        connectionManager.addProcessedStatement(node);
        return new SQLUpdateStatisticsImpl(node.getText(), size, ms, responseMessage, messages);
    }

    private String executePlSqlBlock(PlSqlBlock plSqlBlock) throws DBException {
        if (plSqlBlock.getParent() == null) {
            throw new DBException("Syntax of the PL/SQL BLOCK is not correct");
        } else if (plSqlBlock.getParent().getNode() == null) {
            throw new DBException("Syntax of the PL/SQL BLOCK is not correct");
        } else if (plSqlBlock.getParent().getNode().getElementType() != PLSqlTypesAdopted.ANONYM_PLSQL_BLOCK) {
            throw new DBException("PL/SQL BLOCK should be a top-level statement");
        } else if (bindVarFound(plSqlBlock)) {
            throw new DBException("Sorry, BIND variables not supported at the moment.");
        }

        DbmsOutput dbmsOutput = null;
        Statement stmt = null;
        try {
            dbmsOutput = new DbmsOutput(conn);
            dbmsOutput.enable(100000);

            stmt = conn.createStatement();
            //stmt.execute("begin emp_report; end;");
            stmt.execute(plSqlBlock.getText() + ";");
            stmt.close();

            return dbmsOutput.show();

        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ignored) {
                }
            }

            if (dbmsOutput != null) {
                try {
                    dbmsOutput.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    /**
     * Search against
     *
     * @param plSqlBlock
     * @return
     */
    private boolean bindVarFound(@NotNull PlSqlBlock plSqlBlock) {
        try {
            PlSqlUtil.iterateOver(plSqlBlock.getNode(), new PlSqlUtil.ASTNodeProcessor() {
                public void process(ASTNode node) {
                    if (node.getElementType() == PLSqlTypesAdopted.BIND_VAR) {
                        // bind variable found
                        throw new Error();
                    }
                }
            });

            return false;
        } catch (Error e) {
            // bind varible found
            return true;
        }
    }

    private ASTNode findEncosingPackage(@NotNull ASTNode node) {
        final ASTNode[] pkgNode = {null};

        ASTTreeProcessor runner = new ASTTreeProcessor();
        runner.add(new ASTNodeHandler() {
            @NotNull
            public TokenSet getTokenSet() {
                return TokenSet.create(
                        PlSqlElementTypes.PACKAGE_BODY,
                        PlSqlElementTypes.PACKAGE_SPEC);
            }

            public boolean handleNode(@NotNull ASTNode node) {
                if (node.getElementType() == PlSqlElementTypes.PACKAGE_BODY) {
                    pkgNode[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.PACKAGE_SPEC) {
                    pkgNode[0] = node;
                }
                //
                return false;
            }
        });

        runner.process(node);
        return pkgNode[0];
    }

    private String executeCreateObject(CompilableObject object) throws DBException {
        String text = object.getCreateQuery();
        try {
            stmt = conn.createStatement();
            int size = stmt.executeUpdate(text);

            String object_type = object.getObjectType().toUpperCase();
            String object_name = object.getObjectName().toUpperCase();
            return requestErrors(object_type, object_name);
        } catch (SQLException e) {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e1) {
                }
            }

            throw new DBException(e.getMessage());
        }
    }

    private int executeUpdate(String _text) throws DBException {
        String text = _text.endsWith(";") ? _text.substring(0, _text.length() - 1) : _text;
        try {
            stmt = conn.createStatement();
            int size = stmt.executeUpdate(text);

            return size;
        } catch (SQLException e) {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e1) {
                }
            }

            throw new DBException(e.getMessage());
        }
    }

    private String requestErrors(String object_type, String object_name) {
        String select = buildErrorRequestSelect(object_type, object_name);
        StringBuffer sb = new StringBuffer();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(select);
            while (rs.next()) {
                int line = rs.getInt("line");
                int position = rs.getInt("position");
                String message = rs.getString("text");
                sb.append("Line: ").append(line).append(" ");
                sb.append("Position: ").append(position).append("\n");
                sb.append(message).append("\n");
            }
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                }
            }
        }
        return sb.length() == 0 ? null : sb.toString();
    }

    private String buildErrorRequestSelect(String object_type, String object_name) {
        String sql =
                "select line, position, text from user_errors " +
                        " where type = '" + object_type + "'" +
                        " and name = '" + object_name + "'" +
                        " order by sequence ASC";
        return sql;
    }

    public void close() {
        if (rsmodel != null) {
            rsmodel.close();
            rsmodel = null;
        }
    }

    class SQLUpdateStatisticsImpl implements SQLUpdateStatistics {

        int rowsAffected;
        long timeSpent;
        String message;
        String errors;
        String sqlStatement;

        public SQLUpdateStatisticsImpl(int rowsAffected, long timeSpent, String message, String errors) {
            this.rowsAffected = rowsAffected;
            this.timeSpent = timeSpent;
            this.message = message;
            this.errors = errors;
        }

        public SQLUpdateStatisticsImpl(String sqlStatement, int rowsAffected, long timeSpent, String message, String errors) {
            this.sqlStatement = sqlStatement;
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

        @Override
        public String getSqlStatement() {
            // Fix statement i.e. add semicolon at the end if absent
// TODO fix statement
//            if(sqlStatement != null){
//                if(!sqlStatement.endsWith(";")){
//                    return sqlStatement + ";";
//                }
//            }
            return sqlStatement;
        }
    }


    class RowSetManagerImpl implements RowSetManager {

        RecordCache recordCache;

        public RowSetManagerImpl(SelectStatement selectStmt) throws DBException {
            this.recordCache = RecordCacheFactory.request(conn, selectStmt, SHUNK_SIZE);
        }

        public RecordCache getModel() {
            return recordCache;
        }

        public void close() {
            recordCache.close();
        }

        public void cancel() {
            recordCache.cancel();
        }

        public void refresh() throws DBException {
            recordCache.refresh();
        }
    }


    String formatDateValue(ResultSet rs, int columnIndex) throws SQLException {
        long d = rs.getDate(columnIndex).getTime();
        long t = rs.getTime(columnIndex).getTime();

        return dateToString.format(new Date(d + t));
    }


    // Object specific management
    // todo -- should be revised

    public boolean dropTable(String name) throws DBException {
        String sql = "drop table " + name.trim().toLowerCase() + " cascade constraints";
        int size = executeUpdate(sql);
        return true;
    }

    public boolean dropPackage(String name) throws DBException {
        //"DROP PACKAGE [BODY] [schema.]package_name;"
        String sql = "drop package " + name.trim().toLowerCase();
        int size = executeUpdate(sql);
        return true;
    }

    public boolean compilePackage(String name) throws DBException {
        //"ALTER PACKAGE emp_mgmt  COMPILE PACKAGE;"
        String sql = "alter package " + name.trim().toLowerCase() + " compile package";
        int size = executeUpdate(sql);
        return true;
    }

    public boolean dropView(String name) throws DBException {
        String sql = "drop view " + name.trim().toLowerCase() + " cascade constraints";
        int size = executeUpdate(sql);
        return true;
    }

    public boolean dropTrigger(String name) throws DBException {
        String sql = "drop trigger " + name.trim().toLowerCase();
        int size = executeUpdate(sql);
        return true;
    }

    public boolean enableTrigger(String name) throws DBException {
        String sql = "alter trigger " + name.trim().toLowerCase() + " enable";
        int size = executeUpdate(sql);
        return true;
    }

    public boolean disableTrigger(String name) throws DBException {
        String sql = "alter trigger " + name.trim().toLowerCase() + " disable";
        int size = executeUpdate(sql);
        return true;
    }

    public void cancel() {
        if (stmt != null) {
            try {
                stmt.cancel();
            } catch (SQLException e) {
            }

            canceled = true;
        }

        if (rsmodel != null) {
            rsmodel.cancel();
        }
    }


    @NotNull
    public TrivialResult executeExplainPlanQuery(String query) throws DBException {

        final List<String> out = new ArrayList<String>();
        long ms = System.currentTimeMillis();
        ResultSet resultSet = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            stmt.execute("explain plan for " + query);
            stmt.setFetchSize(50);
            resultSet = stmt.executeQuery("select plan_table_output from table(dbms_xplan.display())");

            while (resultSet.next()) {
                out.add(resultSet.getString(1));
            }

            final long spentTime = System.currentTimeMillis() - ms;

            return new TrivialResult(){
                public List<String> getRowSet() {
                    return out;
                }

                public long getSpentTime() {
                    return spentTime;
                }
            };
        } catch (SQLException e) {
            if(e.getErrorCode() == 1013){
                // "ORA-01013: sql execution cancel by user"
                throw new DBCancelException();
            }
            throw new DBException("Could not execute query. " + e.getMessage());
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ignored) {
                }
                stmt = null;
            }

            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }



}
