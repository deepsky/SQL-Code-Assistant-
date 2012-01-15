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

package com.deepsky.lang.plsql.resolver.psibased;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.*;
import com.deepsky.lang.plsql.psi.impl.GenericTableBase;
import com.deepsky.lang.plsql.psi.internal.CreateViewColumnDefInternal;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.index.ContextItem;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.utils.ArgumentSpec;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.resolver.utils.PsiElementUtil;
import com.deepsky.lang.plsql.resolver.utils.ResolveUtil;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.validation.ValidationException;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import java.util.ArrayList;
import java.util.List;

public class NamesIndexer extends PlSqlElementVisitor {

    protected IndexTree itree;

    public void index(ASTNode r, final IndexTree itree) {
        this.itree = itree;

        PsiElement element = r.getPsi();
        element.accept(this);
    }

    public void visitElement(PsiElement element) {
        PsiElement child = element.getFirstChild();
        while (child != null) {
            IElementType itype = child.getNode().getElementType();
            if (!PsiElementUtil.toSkip.contains(itype)) {
                try {
                    child.accept(this);
                } catch (SyntaxTreeCorruptedException e) {
                    // just skip failed element
                    int h = 1;
                } catch (ValidationException e) {
                    // just skip failed element
                    int h = 1;
                } catch (Throwable e) {
                    int h = 1;
                }
            }
            child = child.getNextSibling();
        }
    }

    protected void saveCtxValue(String ctxPath, String value){
        itree.addContextPath(ctxPath, value);
    }
    
    public void visitPlSqlFile(PlSqlFile plSqlFile) {
        visitElement(plSqlFile);
    }

    public void visitPackageBody(PackageBody node) {
        // assign default value
        saveCtxValue(node.getCtxPath1().getPath(), "");
        visitElement(node);
    }

    public void visitPackageSpec(final PackageSpec node) {
        // assign default value
        saveCtxValue(node.getCtxPath1().getPath(), "");
        visitElement(node);
    }


    /**
     * Encoding format: "P:<CONSTRANT_NAME>:<OWN_COLUMNS>"
     *
     * @param constraint
     */
    public void visitPrimaryKeyConstraint(PrimaryKeyConstraint constraint) {
        String path = constraint.getCtxPath1().getPath();
        String[] primaryKeys = constraint.getPrimaryKeys();
        String constrName = constraint.getConstraintName();

        String value = itree.getContextPathValue(path);
        String newValue = ContextPathUtil.encodePKValue(constrName, primaryKeys);
        value = (value == null || value.length() == 0) ? newValue : value + "|" + newValue;
        saveCtxValue(path, value);
    }

    /**
     * Encoding format: "F:<CONSTRANT_NAME>:<OWN_COLUMNS>:<REFERENCED_TABLE>:<REFERENCED_COLUMNS>"
     *
     * @param constraint
     */
    public void visitForeignKeyConstraint(ForeignKeyConstraint constraint) {
        String path = constraint.getCtxPath1().getPath();
        String refTable = constraint.getReferencedTable();
        String[] refColumns = constraint.getReferencedColumns();
        String[] ownColumns = constraint.getOwnColumns();
        String constrName = constraint.getConstraintName();

        String value = itree.getContextPathValue(path);
        String newValue = ContextPathUtil.encodeFKValue(constrName, ownColumns, refTable, refColumns);
        value = (value == null || value.length() == 0) ? newValue : value + "|" + newValue;
        saveCtxValue(path, value);
    }

    public void visitCreateSynonym(CreateSynonym node) {
        String schema = node.getTargetSchema();
        String object = node.getTargetObject();

        boolean isPublic = node.isPublic();
        String synonymOwner = node.getSynonymOwner();

        String value = ContextPathUtil.encodeSynonymValue(schema, object, isPublic ? "PUBLIC" : synonymOwner);
        saveCtxValue(node.getCtxPath1().getPath(), value.toLowerCase()); //fullName.toLowerCase());
    }

    public void visitCreateSequence(CreateSequence node) {
        saveCtxValue(node.getCtxPath1().getPath(), "");
    }

    public void visitSelectStatementUnion(SelectStatementUnion node) {
        SelectStatement[] selects = node.getSelectStatements();
        StringBuilder b = new StringBuilder();
        for (SelectStatement select : selects) {
            if (b.length() > 0) {
                b.append(',');
            }
            b.append(select.getCtxPath1().getPath());
        }

        saveCtxValue(node.getCtxPath1().getPath(), b.toString());

        super.visitSelectStatementUnion(node);
    }

    public boolean isColumnNameCorrect(String columnRef) {
        return columnRef.matches("^[a-zA-Z][a-zA-Z0-9_]*");
    }

    protected List<String> describeSelectFields(SelectStatement stmt) {
        List<String> columns = new ArrayList<String>();
        for (SelectFieldCommon f : stmt.getSelectFieldList()) {
            if (f instanceof SelectFieldExpr) {
                SelectFieldExpr s = (SelectFieldExpr) f;
                String aliasName = s.getAlias();
                Expression expr = s.getExpression();

                if (aliasName != null) {
                    columns.add(aliasName);
                } else {
                    // special case - alias not specified!
                    String columnRef = expr.getText();
                    if (isColumnNameCorrect(columnRef)) {
                        columns.add(columnRef);
                    } else {
                        // name is not plain, needs some special handling ...
                        // 1. name may be composed of several parts: "tab1.name"
                        if (expr instanceof CompositeName) {
                            NameFragmentRef[] names = ((CompositeName) expr).getNamePieces();
                            columns.add(names[names.length - 1].getText());
                        } else {
                            // 2. it looks this is not a valid name (might be a numeric/string literal, arithmetic expression)
                            // ORA-00904: : invalid identifier
                            int i = 0;
                            continue;
                        }
                    }
                }
            } else if (f instanceof SelectFieldIdentAsterisk) {
//                        SelectFieldIdentAsterisk a = (SelectFieldIdentAsterisk) f;
//                        String alias = a.getTableRef();
//                        for (GenericTable t1 : stmt.getFromClause().getTableList()) {
//                            if (alias == null || (t1.getAlias() != null && t1.getAlias().equalsIgnoreCase(alias))) {
//                                TableDescriptorLegacy tdesc = t1.describe(); ///SupportStuff.describeTable(t1);
//                                if (tdesc == null) {
//                                    // todo - cache is not fresh??
//                                    break;
//                                }
//                                for (String s : tdesc.getColumnNames()) {
//                                    columns.add(s);
//                                    types.add(tdesc.getColumnType(s));
//                                }
//
//                                break;
//                            }
//                        }
            } else {
                // TODO not supported !
            }
        }

        return columns;
    }


    // Encoding example:

    // /FL!..$ ...queues_stats_v/Se!00$ user_queues*uq,./FL!..$view!queues_stats_v.dump/Vd!00$queues_stats_v/Se!00$/Sb!00$*xq
    public void visitSelectStatement(SelectStatement node) {
        List<GenericTable> tabs = new ArrayList<GenericTable>();
        FromClause from = node.getFromClause();
        if (from instanceof AnsiFromClause) {
            AnsiFromClause ansiFrom = (AnsiFromClause) from;
            GenericTable lead = ansiFrom.getLeadTable();
            tabs.add(lead);

            for (AnsiFromClause.JoinPart join : ansiFrom.getJoinParts()) {
                GenericTable t = join.getTable();
                tabs.add(t);
            }

        } else {
            for (GenericTable t : from.getTableList()) {
                tabs.add(t);
            }
        }

        StringBuilder b = new StringBuilder();
        for (GenericTable t : tabs) {
            if (b.length() > 0) {
                b.append(',');
            }
            String alias = t.getAlias();
            if (t instanceof TableAlias) {
                b.append(((TableAlias) t).getTableName().toLowerCase()).append('*').append(alias != null ? alias : "");
            } else if (t instanceof TableFunction) {
                // todo -- implement TABLE FUNCTION
            } else {
                // t instanceof FromSubquery
                String ctxPath = ((FromSubquery) t).getSubquery().getCtxPath1().getPath();
                b.append('.').append(ctxPath).append('*').append(alias != null ? alias : "");
            }
        }

        saveCtxValue(node.getCtxPath1().getPath(), b.toString());

        super.visitSelectStatement(node);
    }

    public void visitUpdateStatement(UpdateStatement node) {
        TableAlias tab = node.getTargetTable();
        String alias = tab.getAlias();
//        String value = tab.getTableName().toLowerCase() + "|" + (alias != null ? alias : "");
        String value = tab.getTableName().toLowerCase() + ContextPathUtil.TABLE_SEP + (alias != null ? alias : "");
        saveCtxValue(node.getCtxPath1().getPath(), value);

        super.visitUpdateStatement(node);
    }

    public void visitDeleteStatement(DeleteStatement node) {
        TableAlias tab = node.getTargetTable();
        String alias = tab.getAlias();
//        String value = tab.getTableName().toLowerCase() + "|" + (alias != null ? alias : "");
        String value = tab.getTableName().toLowerCase() + ContextPathUtil.TABLE_SEP + (alias != null ? alias : "");
        saveCtxValue(node.getCtxPath1().getPath(), value);

        super.visitDeleteStatement(node);
    }

    public void visitInsertStatement(InsertStatement node) {
        TableAlias tab = node.getIntoTable();
        String value = tab.getTableName().toLowerCase() + ContextPathUtil.TABLE_SEP; //"|";
        saveCtxValue(node.getCtxPath1().getPath(), value);

        super.visitInsertStatement(node);
    }

    public void visitMergeStatement(MergeStatement node) {
        TableAlias into = node.getIntoTable();
        String intoAlias = into.getAlias();
//        String value1 = into.getTableName().toLowerCase() + "|" + (intoAlias != null ? intoAlias : "");
        String value1 = into.getTableName().toLowerCase() + ContextPathUtil.TABLE_SEP + (intoAlias != null ? intoAlias : "");

        GenericTable using = node.getUsingTable();
        String usingAlias = using.getAlias();
        String value2 = "";
        if (using instanceof TableAlias) {
//            value2 = ((TableAlias) using).getTableName().toLowerCase() + "|" + (usingAlias != null ? usingAlias : "");
            value2 = ((TableAlias) using).getTableName().toLowerCase() + ContextPathUtil.TABLE_SEP + (usingAlias != null ? usingAlias : "");
        } else {
//            value2 = ".|" + (usingAlias != null ? usingAlias : "");
            value2 = ".*" + (usingAlias != null ? usingAlias : "");
        }

        saveCtxValue(node.getCtxPath1().getPath(), value1 + "," + value2);

        super.visitMergeStatement(node);
    }

    public void visitPlSqlBlock(PlSqlBlock node) {
        saveCtxValue(node.getCtxPath1().getPath(), "");

        super.visitPlSqlBlock(node);
    }

    public void visitLoopIndex(LoopIndex loopIndex) {
        Type t = loopIndex.getExpressionType();
        saveCtxValue(loopIndex.getCtxPath1().getPath(), TypeFactory.encodeType(t));
    }

    public void visitCursorLoopSpec(CursorLoopSpec node) {
//            SelectStatement select = node.getSelect();
//            String fields = encodeSelectFieldList(select.getSelectFieldList());
//            String val = fields + "@" + select.getCtxPath1().getPath();
//            saveCtxValue(node.getCtxPath1().getPath(), val); //node.getSelect().getCtxPath1().getPath());

        Subquery sb = node.getSubquery();
        //String fields = encodeSelectFieldList(select.getSelectFieldList());
        //String val = fields + "@" + select.getCtxPath1().getPath();
        saveCtxValue(node.getCtxPath1().getPath(), sb.getCtxPath1().getPath()); //node.getSelect().getCtxPath1().getPath());

        super.visitCursorLoopSpec(node);
    }


    public void visitRecordTypeItem(RecordTypeItem rti) {
        saveCtxValue(rti.getCtxPath1().getPath(), TypeFactory.encodeType(rti.getType()));
    }

    public void visitObjectTypeDecl(ObjectTypeDecl decl) {
        saveCtxValue(decl.getCtxPath1().getPath(), null);

        super.visitObjectTypeDecl(decl);
    }

    public void visitRecordTypeDecl(RecordTypeDecl rt) {
        saveCtxValue(rt.getCtxPath1().getPath(), null);

        super.visitRecordTypeDecl(rt);
    }

    public void visitTableCollectionDecl(TableCollectionDecl node) {
        saveCtxValue(node.getCtxPath1().getPath(), TypeFactory.encodeType(node.getBaseType()));
    }

    public void visitVarrayCollectionDecl(VarrayCollectionDecl node) {
        saveCtxValue(node.getCtxPath1().getPath(), TypeFactory.encodeType(node.getBaseType()));
    }

    public void visitRefCursorDecl(RefCursorDecl node) {
        saveCtxValue(node.getCtxPath1().getPath(), "");
    }

    public void visitVariableDecl(VariableDecl node) {
        saveCtxValue(node.getCtxPath1().getPath(), TypeFactory.encodeType(node.getType()));
    }

    public void visitArgument(Argument node) {
        saveCtxValue(node.getCtxPath1().getPath(), TypeFactory.encodeType(node.getType()));
    }

    public void visitTableDefinition(TableDefinition node) {
        String path = node.getCtxPath1().getPath();
        String value = itree.getContextPathValue(path);

        int partitionType = node.getPartitionType();
        int tableType = node.getTableType();
        String v = "partition=" + partitionType + "|tabletype=" + tableType;

        saveCtxValue(path, (value == null) ? v : value + "|" + v);

        super.visitTableDefinition(node);
    }

    /**
     * Encoding format: "F:<CONSTRANT_NAME>:<OWN_COLUMNS>:<REFERENCED_TABLE>:<REFERENCED_COLUMNS>"
     *
     * @param node
     */
    public void visitColumnDefinition(ColumnDefinition node) {
        ColumnFKSpec fkSpec = node.getForeignKeySpec();
        ColumnPKSpec pkSpec = node.getPrimaryKeySpec();
        if (pkSpec != null || fkSpec != null) {
            String refColumn = fkSpec!=null? fkSpec.getReferencedColumn(): null;
            String refTable = fkSpec!=null? fkSpec.getReferencedTable(): null;
            String constraintName = pkSpec != null? pkSpec.getConstraintName(): fkSpec.getConstraintName();
            String value = ContextPathUtil.encodeColumnValue(
                    node.getType(), constraintName, node.isNotNull(), pkSpec != null, refColumn, refTable
            );
            saveCtxValue(node.getCtxPath1().getPath(), value);
        } else {
            String value = ContextPathUtil.encodeColumnValue(node.getType(), node.isNotNull());
            saveCtxValue(node.getCtxPath1().getPath(), value);
        }
    }

    public void visitLoopStatement(LoopStatement statement) {
        saveCtxValue(statement.getCtxPath1().getPath(), "");

        super.visitLoopStatement(statement);
    }

    public void visitFunction(Function node) {
        try {
            String encoded = ContextPathUtil.encodeArgumentsReturnType(node.getArguments(), node.getReturnType());
            saveCtxValue(node.getCtxPath1().getPath(), encoded);
            super.visitFunction(node);
        } catch (ValidationException e) {
            // todo --
        }
    }

    public void visitFunctionSpec(FunctionSpec node) {
        try {
            String encoded = ContextPathUtil.encodeArgumentsReturnType(node.getArguments(), node.getReturnType());
            saveCtxValue(node.getCtxPath1().getPath(), encoded);
            super.visitFunctionSpec(node);
        } catch (ValidationException e) {
            // todo --
        }
    }

    public void visitProcedure(Procedure node) {
        try {
            String encoded = ContextPathUtil.encodeArguments(node.getArguments());
            saveCtxValue(node.getCtxPath1().getPath(), encoded);
            super.visitProcedure(node);
        } catch (ValidationException e) {
            // todo --
        }
    }

    public void visitProcedureSpec(ProcedureSpec node) {
        try {
            String encoded = ContextPathUtil.encodeArguments(node.getArguments());
            saveCtxValue(node.getCtxPath1().getPath(), encoded);
            super.visitProcedureSpec(node);
        } catch (ValidationException e) {
            // todo --
        }
    }

    public void visitCreateTriggerGeneric(CreateTriggerGeneric trigger) {
        ASTNode[] nodes = trigger.getNode().getChildren(TokenSet.create(
                PlSqlElementTypes.DB_EVNT_TRIGGER_CLAUSE, PlSqlElementTypes.DDL_TRIGGER_CLAUSE, PlSqlElementTypes.INSTEADOF_TRIGGER)
        );

        if(nodes != null && nodes.length == 1){
            if(nodes[0].getElementType() == PlSqlElementTypes.DB_EVNT_TRIGGER_CLAUSE ){
                CreateTriggerDbEvent t = (CreateTriggerDbEvent) trigger;
                String value = "db_event|" + t.getEventName() + "|" + t.getTarget();
                saveCtxValue(trigger.getCtxPath1().getPath(), value.toLowerCase());
            } else if(nodes[0].getElementType() == PlSqlElementTypes.DDL_TRIGGER_CLAUSE ){
                CreateTriggerDDL t = (CreateTriggerDDL) trigger;
                String value = "ddl|" + t.getEventName() + "|" + t.getTarget();
                saveCtxValue(trigger.getCtxPath1().getPath(), value);
            } else if(nodes[0].getElementType() == PlSqlElementTypes.INSTEADOF_TRIGGER ){
                saveCtxValue(trigger.getCtxPath1().getPath(), "insteadof|");
            }
        } else {
            saveCtxValue(trigger.getCtxPath1().getPath(), "generic|");
        }

        super.visitCreateTriggerGeneric(trigger);
    }

    public void visitCreateTriggerDML(CreateTriggerDML trigger) {
        String tableName = StringUtils.discloseDoubleQuotes(trigger.getTableName());

        String clause = trigger.getConditionClause();
        if(clause != null){
            String cclause = "|" + clause.replace(" ", ".").toLowerCase();
            saveCtxValue(trigger.getCtxPath1().getPath(), "dml|" + tableName.toLowerCase() + cclause);
        } else {
            // todo -- syntax corrupted? subject to review
            saveCtxValue(trigger.getCtxPath1().getPath(), "dml|" + tableName.toLowerCase());
        }

        super.visitCreateTriggerDML(trigger);
    }

    public void visitCursorDecl(CursorDecl node) {
        String val = encodeSelectFieldList(node.getSelect().getSelectFieldList());
        saveCtxValue(node.getCtxPath1().getPath(), val); //b.toString()); //(alias!=null? alias.toLowerCase(): ""));

        super.visitCursorDecl(node);
    }

    public void visitCreateView(CreateView view) {
        // NOTE: building the index of the VIEW consists of two step process
        // 1. collect names of the VIEW columns
        // 2. assign types for the columns with visitCreateViewColumnDefInternal(..)
        List<ArgumentSpec> cols = new ArrayList<ArgumentSpec>();
        for (VColumnDefinition c : view.getColumnDefs()) {
            String name = c.getColumnName();
            cols.add(new ArgumentSpecImpl(name.toLowerCase(), null));
        }

        if(cols.size() == 0){
            // no explicit column definition, try to extract column names from SELECT column list
            for(SelectFieldCommon f: view.getSelectExpr().getSelectFieldList()){
                if( f instanceof SelectFieldExpr){
                    SelectFieldExpr fieldExpr = (SelectFieldExpr)f;
                    String alias = fieldExpr.getAlias();
                    if(alias != null){
                        cols.add(new ArgumentSpecImpl(alias.toLowerCase(), null));
                    } else {
                        Expression expr = fieldExpr.getExpression();
                        String exprText = expr.getText();
                        if(GenericTableBase.isColumnNameCorrect(exprText)){
                            cols.add(new ArgumentSpecImpl(expr.getText(), null));
                        } else if(GenericTableBase.isColumnNameCorrectWithDot(exprText)){
                            String[] alias_name = exprText.split("\\.");
                            cols.add(new ArgumentSpecImpl(alias_name[1], null));
                        }
                    }
                }
            }
        }
        String encoded = ContextPathUtil.encodeArguments(cols.toArray(new ArgumentSpec[cols.size()]));
        saveCtxValue(view.getCtxPath1().getPath(), encoded);

        super.visitCreateView(view);
    }


    public void visitCreateViewColumnDefInternal(CreateViewColumnDefInternal node) {
        // todo -- subject to revise - it is expected view will be indexed before indexing of the view_internal
        String name = ContextPathUtil.extractLastCtxName(node.getCtxPath1().getPath());
        ContextItem[] items = itree.findCtxItems(new int[]{ContextPath.VIEW_DEF}, name); //[0].getCtxPath()
        if(items.length == 1){

            List<ArgumentSpec> cols = new ArrayList<ArgumentSpec>();
            for (ColumnDefinition c : node.getColumnDefs()) {
                String name1 = c.getColumnName();
                cols.add(new ArgumentSpecImpl(name1.toLowerCase(), c.getType()));
            }

            String encoded = ContextPathUtil.encodeArguments(cols.toArray(new ArgumentSpec[cols.size()]));
            saveCtxValue(items[0].getCtxPath(), encoded);
        }
    }

    public void visitSubquery(Subquery node) {
        String val = encodeSelectFieldList(node.getSelectStatement().getSelectFieldList());
        saveCtxValue(node.getCtxPath1().getPath(), val);

        super.visitSubquery(node);
    }


    private String encodeSelectFieldList(SelectFieldCommon[] fields) {
        StringBuilder b = new StringBuilder();
        boolean delim = false;
        for (SelectFieldCommon field : fields) {
            if (field instanceof SelectFieldExpr) {
                SelectFieldExpr fexpr = (SelectFieldExpr) field;
                String alias = fexpr.getAlias();

                if (delim) b.append(',');
                delim = true;
                if (alias != null) {
                    b.append(".|").append(alias.toLowerCase());
                } else {
                    // check whether expression looks like <alias>.<column> or <column>
                    ASTNode varRef = fexpr.getNode().findChildByType(PlSqlElementTypes.VAR_REF);
                    if (varRef != null) {
                        b.append(varRef.getText().toLowerCase()).append("|.");
                    } else {
                        delim = false;
                    }
                }
            }
        }

        return b.toString();
    }

    private class ArgumentSpecImpl implements ArgumentSpec {

        String column;
        Type type;

        public ArgumentSpecImpl(String column, Type type) {
            this.column = column;
            this.type = type;
        }

        public String getName() {
            return column;
        }

        public Type getType() {
            return type;
        }

        public boolean defaultExist() {
            return false;
        }
    }

}
