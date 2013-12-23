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

import com.deepsky.database.DBException;
import com.deepsky.database.exec.RecordCache;
import com.deepsky.lang.common.ResolveProvider;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.resolver.helpers.TableResolveHelper;
import com.deepsky.lang.plsql.resolver.index.ContextItem;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.sqlIndex.ResolveHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class RecordCacheFactory {


    static public RecordCache request(Connection conn, SelectStatement sqlStmt, int chunk_size) throws DBException {

        try {
            if (sqlStmt instanceof SelectStatementUnion) {
                // for UNION not updatable result set available only
                throw new NotUpdatableResultSetException();
            } else {
                RSUpdateSupport helper = new RSUpdateSupportImpl(sqlStmt);
                return new UpdatableRecordCache2Impl(conn, helper, chunk_size);
            }
        } catch (NotUpdatableResultSetException ignored) {
        }

        // plain, not updatable result set
        return new RecordCacheImpl(conn, sqlStmt.getText(), chunk_size);
    }

    private static void assertNoDistinct(SelectStatement select) throws NotUpdatableResultSetException {
        if (select.isDistinctOrUniqueSpecified()) {
            // neither DISTINCT nor UNIQUE allowed for updatable query
            throw new NotUpdatableResultSetException();
        }
    }

    private static String[] expandAsterisk(TableResolveHelper tHelper) {
        final List<String> out = new ArrayList<String>();

        Iterator<ContextItem> ite = tHelper.iterateOverChildren();
        while (ite.hasNext()) {
            ContextItem item = ite.next();
            String columnCtx = item.getCtxPath();
            int type = ContextPathUtil.extractLastCtxType(columnCtx);
            if (type == ContextPath.COLUMN_DEF) {
                String columnName = ContextPathUtil.extractLastCtxName(columnCtx);
                out.add(columnName);
            }
        }

        return out.toArray(new String[out.size()]);
    }

    private static void assertIdentAsteriskReferTable(SelectFieldIdentAsterisk ast, String tableCtxPath) {
        // todo -- make sure that ident reference the table
    }

    private static TableAlias analyzeTable(FromClause from) throws NotUpdatableResultSetException {
        if (from.getTableList().length != 1) {
            // only one table supported so far
            throw new NotUpdatableResultSetException();
        } else if(!(from.getTableList()[0] instanceof TableAlias)) {
            throw new NotUpdatableResultSetException();
        }

        return (TableAlias)from.getTableList()[0];
    }

    private static TableResolveHelper calcTableCtxPath(GenericTable table, ResolveHelper rhelper)
            throws NotUpdatableResultSetException {
        if (table instanceof TableAlias) {
            TableRef ref = ((TableAlias) table).getTableNameElement();
            ResolveDescriptor desc = rhelper.resolveTableRef(ref.getTableName());
            if (desc != null && desc.getTargetType() == ResolveDescriptor.TABLE) {
                if(ref.getTableName().equalsIgnoreCase("dual")){
                    // somehow ROWID for dual returned!
                    throw new NotUpdatableResultSetException();
                }

                return (TableResolveHelper) desc;
            } else {
                // not resolved or not a table
            }
        } else if (table instanceof FromSubquery) {
            // todo - implement me
        } else {
            // only tableAlias/subquery supported so far
        }

        throw new NotUpdatableResultSetException();
    }

    private static void analyzeFieldExpr(ResolveFacade resolver, SelectFieldExpr fieldExpr, ColumnHandler handler)
            throws NotUpdatableResultSetException {
        Expression expr = fieldExpr.getExpression();
        if (expr instanceof Literal) {
            // BooleanLiteral
            // NullLiteral
            // NumericLiteral
            // RownumLiteral
            // RowidLiteral
            // StringLiteral
            // SysdateLiteral
        } else if (expr instanceof TimestampLiteral) {
        } else if (expr instanceof ArithmeticExpression) {
            // need recursive analysis
            // todo -- implement me
        } else if (expr instanceof Condition) {
            // nonsence,  prohibited case for select field!
            // just for the case
        } else if (expr instanceof AtTimeZoneExpression) {
        } else if (expr instanceof CaseExpression) {
            // todo -- implement me
        } else if (expr instanceof FunctionCall) {
            // make sure the function is not an aggregate one
            if(((FunctionCall)expr).isAggregate()){
                throw new NotUpdatableResultSetException();
            }
        } else if (expr instanceof ObjectReference) {
            ObjectReference ref = (ObjectReference) expr;
            switch (ref.getNamePieces().length) {
                case 1:
                    handler.handleName(ref.getFragmentByPos(0).getText());
                    return;
                case 2:
                    handler.handleName(ref.getFragmentByPos(1).getText());
                    return;
                default:
                    break;

            }
        }

        // literal or calc field
        handler.handleName(null);
    }

    interface ColumnHandler {
        void handleName(@Nullable String column);
    }


    private static class FieldMetaInfo {
        String columnName;
        String aliasName;

        public FieldMetaInfo(String columnName, String aliasName) {
            this.columnName = columnName;
            this.aliasName = aliasName;
        }
    }

    private static class RSUpdateSupportImpl implements RSUpdateSupport {

        List<FieldMetaInfo> fieldList;
        SelectStatement select;
        TableAlias table;

        RSUpdateSupportImpl(@NotNull SelectStatement select) throws NotUpdatableResultSetException {
            this.select = select;

            // Check HINTS
            // todo -- implement me

            assertNoDistinct(select);

            // analyse FROM clause
            FromClause from = select.getFromClause();
            if (from instanceof AnsiFromClause) {
                // ANSI FROM not supported at the moment - todo implement me
                throw new NotUpdatableResultSetException();
            }

            // make sure we have only one table in FROM clause
            table = analyzeTable(from);

            // make sure there is no GROUP BY clause
            if( select.getGroupByClause() != null){
                throw new NotUpdatableResultSetException();
            }

            // get resolver and index
            ResolveFacade resolver = ((ResolveProvider) select.getContainingFile()).getResolver();

            // make sure that we have a table
            TableResolveHelper tHelper = calcTableCtxPath(from.getTableList()[0], resolver.getLLResolver());

            fieldList = new ArrayList<FieldMetaInfo>();
            // Scan the select field list
            // 1. make sure there is no aggregate functions
            // 2. collect info for each field: isUpdatable, hasDefault, isNotNull
            for (final SelectFieldCommon f : select.getSelectFieldList()) {
                if (f instanceof SelectFieldExpr) {
                    analyzeFieldExpr(resolver, (SelectFieldExpr) f, new ColumnHandler() {
                        public void handleName(String column) {
                            fieldList.add(new FieldMetaInfo(column, f.getText()));
                        }
                    });
                } else if (f instanceof SelectFieldAsterisk) {
                    // expand asterisk to column list
                    String[] fieldNames = expandAsterisk(tHelper);
                    for (String field : fieldNames) {
                        fieldList.add(new FieldMetaInfo(field, field));
                    }
                } else if (f instanceof SelectFieldIdentAsterisk) {
                    assertIdentAsteriskReferTable((SelectFieldIdentAsterisk) f, tHelper.getCtxPath());
                    // expand asterisk to column list
                    String[] fieldNames = expandAsterisk(tHelper);
                    for (String field : fieldNames) {
                        fieldList.add(new FieldMetaInfo(field, field));
                    }
                }
            }

            // Make sure all not-null table columns are presented in SELECT fields
            checkNotNullColumns(fieldList, tHelper);

            // Append ROWID
            fieldList.add(new FieldMetaInfo(null, "ROWID"));
        }

        public static<E> E findFirst(Collection<E> collection, E e1, Comparator<E> comparator){
            for(E e: collection){
                if( comparator.compare(e, e1) == 0)
                    return e;
            }
            return null;
        }

        private void checkNotNullColumns(List<FieldMetaInfo> fieldList, TableResolveHelper tHelper) {
            Iterator<ContextItem> iterator = tHelper.iterateOverChildren();
            while(iterator.hasNext()){
                ContextItem ctxItem = iterator.next();
                String columnCtx = ctxItem.getCtxPath();
                int ctxType = ContextPathUtil.extractLastCtxType(columnCtx);
                if (ctxType == ContextPath.COLUMN_DEF) {
                    String columnName = ContextPathUtil.extractLastCtxName(columnCtx);
                    String ctxValue = ctxItem.getValue();
                    boolean isNotNull = ContextPathUtil.decodeColumnNotNull(ctxValue);
                    boolean isPK = ContextPathUtil.extractIsPKFromColumnValue(ctxValue);
                    if(isNotNull || isPK){
                        if(findFirst(fieldList, new FieldMetaInfo(columnName, null), new Comparator<FieldMetaInfo>() {
                            public int compare(FieldMetaInfo o1, FieldMetaInfo o2) {
                                return o2.columnName.equalsIgnoreCase(o1.columnName)? 0: 1;
                            }
                        }) == null){
                            throw new NotUpdatableResultSetException();
                        }
                    }
                }
            }
        }

        public String getSelectScript() {
            StringBuilder b = new StringBuilder("SELECT ");
            for (int i = 0; i < fieldList.size(); i++) {
                if (i > 0) {
                    b.append(", ");
                }

                b.append(fieldList.get(i).aliasName);
            }

            b.append(" ");

            // todo - impl does not support subquery case
            int startOffset = select.getTextRange().getStartOffset();
            int endOffset = select.getTextRange().getEndOffset();
            int startOffsetFrom = select.getFromClause().getTextRange().getStartOffset();

            String selectText = select.getText();
            String cuttedSelectText = selectText.substring(startOffsetFrom - startOffset, endOffset - startOffset);
            b.append(cuttedSelectText);

            return b.toString();
        }

        public String mapColumnIndexToColumnName(int columnIndex) {
            return fieldList.get(columnIndex-1).columnName;
        }

        public boolean isFieldUpdatable(int columnIndex) {
            return fieldList.get(columnIndex-1).columnName != null;
        }

        public UpdateStmtBuilder getUpdater() {
            return new UpdateStmtBuilderImpl();
        }

        public InsertStmtBuilder getInserter() {
            return new InsertStmtBuilderImpl();
        }

        public SelectStmtBuilder getSelector() {
            return new SelectStmtBuilderImpl();
        }

        public DeleteStmtBuilder getDeletor() {
            return new DeleteStmtBuilderImpl();
        }


        private class DeleteStmtBuilderImpl implements DeleteStmtBuilder {

            public String getStmt() {
                StringBuilder b = new StringBuilder("DELETE FROM ");
                b.append(table.getTableName()).append(" WHERE ROWID = ?");
                return b.toString();
            }
        }

        // InsertStmtBuilder
        private class InsertStmtBuilderImpl implements InsertStmtBuilder {

            Map<String, Object> field2value = new HashMap<String, Object>();
            // resulting statement: INSERT INTO <TABLE> (COL1, COL2, ...) VALUES (VAL1, VAL2, ...) returning ROWID INTO ?
            public String getStmt() {
                StringBuilder b = new StringBuilder("INSERT INTO ");
                b.append(table.getTableName()).append(" (");
                int cnt = 0;
                for(String columnName: field2value.keySet()){
                    if(cnt > 0){
                        b.append(", ");
                    }
                    b.append(columnName);
                    cnt++;
                }

                b.append(")").append(" VALUES (");
                cnt = 0;
                for(String columnName: field2value.keySet()){
                    if(cnt > 0){
                        b.append(", ");
                    }
                    b.append("?");
                    cnt++;
                }
                b.append(")").append(" RETURNING ROWID INTO ?");
                
                return b.toString();
            }

            public void addFieldBeingInserted(String fieldName, Object value) {
                field2value.put(fieldName.toLowerCase(), value);
            }

            public void iterateOverFields(RSUpdateSupport.FieldProcessor processor) throws SQLException {
                int cnt = 0;
                for(String columnName: field2value.keySet()){
                    Object value = field2value.get(columnName.toLowerCase());
                    processor.process(cnt+1, columnName.toLowerCase(), value, false);
                    cnt++;
                }
            }

            public int getRetROWIDPos() {
                return field2value.size()+1;
            }
        }


        // SelectStmtBuilder
        private class SelectStmtBuilderImpl implements SelectStmtBuilder {

            // resulting statement: SELECT field1, filed2, ... FROM ... WHERE ROWID = ?
            public String getStmt() {
                StringBuilder b = new StringBuilder("SELECT ");
                for (int i = 0; i < fieldList.size(); i++) {
                    if (i > 0) {
                        b.append(", ");
                    }

                    b.append(fieldList.get(i).aliasName);
                }

                b.append(" ");

                // todo - impl does not support subquery case
                String selectText = select.getText();
                int startOffset = select.getTextRange().getStartOffset();
                int endOffset = select.getTextRange().getEndOffset();
                int startOffsetFrom = select.getFromClause().getTextRange().getStartOffset();
                int endOffsetFrom = select.getFromClause().getTextRange().getEndOffset();

                String adoptedText = selectText; //b.toString();
                WhereCondition where = select.getWhereCondition();
                if(where != null){
                    adoptedText = adoptedText.replace(where.getText(), "WHERE ROWID=?");
                } else {
                    // where = null
                    int offset = endOffsetFrom-startOffset;
                    adoptedText = adoptedText.substring(0, offset) + " WHERE ROWID=? " + selectText.substring(offset);
                }

                String cuttedSelectText = adoptedText.substring(startOffsetFrom - startOffset); //, endOffset - startOffset);
                b.append(cuttedSelectText);

                return b.toString();
            }
        }

        // UpdateStmtBuilder
        private class UpdateStmtBuilderImpl implements RSUpdateSupport.UpdateStmtBuilder {

            Map<String, Object> field2value = new HashMap<String, Object>();

            // UPDATE <TABLE> SET COL1=?, COL2=? WHERE ROWID = ?
            public String getStmt() {
                StringBuilder b = new StringBuilder("UPDATE ");
                b.append(table.getTableName()).append(" SET ");
                int cnt = 0;
                for(String columnName: field2value.keySet()){
                    if(cnt > 0){
                        b.append(", ");
                    }
                    b.append(columnName).append("=?");
                    cnt++;
                }

                b.append(" WHERE ROWID=?");
                return b.toString();
            }

            public void addFieldBeingUpdated(String fieldName, Object value) {
                field2value.put(fieldName.toLowerCase(), value);
            }

            public void iterateOverFields(RSUpdateSupport.FieldProcessor processor) throws SQLException {
                int cnt = 0;
                for(String columnName: field2value.keySet()){
                    Object value = field2value.get(columnName.toLowerCase());
                    processor.process(cnt+1, columnName.toLowerCase(), value, false);
                    cnt++;
                }

                // call handler for ROWID
                processor.process(cnt+1, "ROWID", null, true);
            }
        }

    }
}
