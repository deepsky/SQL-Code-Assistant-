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

package com.deepsky.lang.plsql.psi;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.ctrl.CommitStatement;
import com.deepsky.lang.plsql.psi.ctrl.RollbackStatement;
import com.deepsky.lang.plsql.psi.ddl.*;
import com.deepsky.lang.plsql.psi.impl.*;
import com.deepsky.lang.plsql.psi.impl.spec_func_call.SpecFunctionCallBaseImpl;
import com.deepsky.lang.plsql.psi.internal.CreateViewColumnDefInternal;
import com.deepsky.lang.plsql.psi.ref.*;
import com.deepsky.lang.plsql.psi.types.ColumnTypeRef;
import com.deepsky.lang.plsql.psi.types.DataType;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiReferenceExpression;


public class PlSqlElementVisitor extends PsiElementVisitor {

    public void visitReferenceExpression(PsiReferenceExpression psiReferenceExpression) {
    }

    public void visitPlSqlElement(PlSqlElement node) {
        visitElement(node);
    }

    public void visitSelectStatement(SelectStatement node) {
        visitElement(node);
    }

    public void visitUpdateStatement(UpdateStatement node) {
        visitElement(node);
    }

    public void visitInsertStatement(InsertStatement node) {
        visitElement(node);
    }

    public void visitFromClause(FromClause node) {
        // todo - commenting it out prevents Structure View from showing Subquery!!!
        visitElement(node);
    }

    public void visitDeleteStatement(DeleteStatement node) {
        visitElement(node);
    }

    public void visitPlainTable(TableAlias node) {
        visitElement(node);
    }

    public void visitPlSqlBlock(PlSqlBlock node) {
        for (PlSqlElement psi : node.getObjectList()) {
            psi.accept(this);
        }
//        visitElement(node);
    }

    public void visitFunction(Function node) {
        visitElement(node);
    }

    public void visitFunctionSpec(FunctionSpec node) {
        visitElement(node);
    }

//    public void visitSubquery(FromSubquery node) {
    public void visitSubquery(Subquery node) {
        visitElement(node);
    }

    public void visitProcedure(Procedure node) {
        visitElement(node);
    }

    public void visitProcedureSpec(ProcedureSpec node) {
        visitElement(node);
    }

    public void visitPackageBody(PackageBody node) {
        visitElement(node);
    }

    public void visitPackageSpec(final PackageSpec node) {
        visitElement(node);
    }

    public void visitObjectName(ObjectName node) {
//todo --        visitElement(node);
    }

    public void visitReferenceExpression(ReferenceExpression node) {
//todo --        visitElement(node);
    }


    public void visitObjectReference(ObjectReference node) {
        visitElement(node);
    }

    public void visitExpression(Expression node) {
        visitElement(node);
    }

    public void visitInsertSubquery(InsertSubquery node) {
        visitElement(node);
    }


    public void visitArithmeticExpression(ArithmeticExpression node) {
        visitElement(node);
    }

    public void visitCallArgument(CallArgument node) {
        visitElement(node);
    }

    public void visitWhereCondition(WhereCondition node) {
        visitElement(node);
    }

    public void visitFunctionCall(FunctionCall node) {
//        visitElement(node);
        node.getCompositeName().accept(this);
        for (CallArgument arg : node.getCallArguments()) {
            arg.accept(this);
        }
    }

    public void visitProcedureCall(ProcedureCall node) {
        node.getCompositeName().accept(this);
        for (CallArgument arg : node.getCallArguments()) {
            arg.accept(this);
        }
    }

    public void visitCondition(Condition node) {
        visitElement(node);
    }

    public void visitPlSqlFile(PlSqlFile plSqlFile) {
        visitElement(plSqlFile);
    }

    public void visitCallArgumentList(CallArgumentList node) {
        for (CallArgument arg : node.getArguments()) {
            arg.accept(this);
        }
    }

//    public void visitTable(Table table) {
//    }

    public void visitColumnSpecList(ColumnSpecList node) {
        visitElement(node);
    }

    public void visitCursorDecl(CursorDecl node) {
        visitElement(node);
    }

    public void visitVariableDecl(VariableDecl node) {
        visitElement(node);
    }

    public void visitPackageInitSection(PackageInitSection node) {
        visitElement(node);
    }

    public void visitRecordTypeDecl(RecordTypeDecl node) {
        for (RecordTypeItem ri : node.getItems()) {
            ri.accept(this);
        }
    }

    public void visitTableCollectionDecl(TableCollectionDecl node) {
        visitElement(node);
    }

    public void visitSelectFieldExpr(SelectFieldExpr expr) {
        visitElement(expr);
    }

    public void visitErrorTokenWrapper(ErrorTokenWrapper node) {
    }

    public void visitTypeNameReference(TypeNameReference reference) {
        visitElement(reference);
    }

    public void visitVarrayCollectionDecl(VarrayCollectionDecl decl) {
        visitElement(decl);
    }

    public void visitSequenceExpr(SequenceExpr expr) {
        visitElement(expr);
    }

    public void visitColumnSpec(ColumnSpec spec) {
        visitElement(spec);
    }

    public void visitTableDefinition(TableDefinition tableDefinition) {
        // give a chance to catch any exception in the closest catch block
        visitElement(tableDefinition);
       
//        for (GenericConstraint constr : tableDefinition.getConstraints()) {
//            constr.accept(this);
//        }
//
//        for (ColumnDefinition cdef : tableDefinition.getColumnDefs()) {
//            cdef.accept(this);
//        }
//
//        PartitionSpecification parti = tableDefinition.getPartitionSpec();
//        if(parti != null){
//            parti.accept(this);
//        }
    }

    public void visitCreateView(CreateView view) {
        view.getSelectExpr().accept(this);
    }

    public void visitVColumnDefinition(VColumnDefinition vColumnDefinition) {
    }

    public void visitNameFragmentRef(NameFragmentRef ref) {
    }

    public void SqlPlusPromptRem(SqlPlusPromptRem promptRem) {
    }

    public void visitComment(Comment comment) {
        visitElement(comment);
    }

    public void visitAlterTable(AlterTable table) {
        visitElement(table);
    }

    public void visitCreateIndex(CreateIndex index) {
        visitElement(index);
    }

    public void visitCreateTriggerGeneric(CreateTriggerGeneric trigger) {
        visitElement(trigger);
    }

    public void visitObjectTypeDecl(ObjectTypeDecl decl) {
        visitElement(decl);
    }

    public void visitCreateTriggerDML(CreateTriggerDML trigger) {
        visitElement(trigger);
    }

    public void visitSelectStatementUnion(SelectStatementUnion select) {
        visitElement(select);
    }

    public void visitDatatype(DataType dataType) {
    }

    public void visitDDLTable(DDLTable table) {
    }

    public void visitForeignKeyConstraint(ForeignKeyConstraint constraint) {
        TableRef tab = constraint.getReferencedTable2();
        tab.accept(this);
        for (ColumnNameRef c : constraint.getReferencedColumns2()) {
            c.accept(this);
        }
        for (ColumnNameRef c : constraint.getOwnColumns2()) {
            c.accept(this);
        }
    }

    public void visitColumnNameDDL(ColumnNameDDL columnNameDDL) {
    }

    public void visitDDLView(DDLView ddlView) {
    }

    public void visitColumnTypeRef(ColumnTypeRef columnTypeRef) {
        columnTypeRef.getTableRef().accept(this);
        columnTypeRef.getColumnRef().accept(this);
    }

    public void visitColumnNameRef(ColumnNameRef columnNameRef) {
    }

    public void visitCallableCompositeName(CallableCompositeName compositeName) {
    }

    public void visitParameterReference(ParameterReference reference) {
    }

    public void visitCRecordItemRef(CRecordItemRef ref) {
    }

    public void visitMergeStatement(MergeStatement stmt) {
        visitElement(stmt);
    }

//    public void visitTableWithLink(TableWithLink tableNameWithLink) {
//        visitElement(tableNameWithLink);
//    }

    public void visitSqlPlusCommand(SqlPlusCommand command) {
    }

    public void visitCommitStatement(CommitStatement statement) {
    }

    public void visitRollbackStatement(RollbackStatement statement) {
    }

    public void visitDeclarationList(DeclarationList declarationList) {
        for (Declaration decl : declarationList.getDeclList()) {
            try {
                decl.accept(this);
            }catch(SyntaxTreeCorruptedException e){
                // skip failed declaration
            }
        }
    }

    public void visitLoopIndex(LoopIndex loopIndex) {
    }

    public void visitRecordTypeItem(RecordTypeItem recordTypeItem) {
        visitElement(recordTypeItem);
    }

    public void visitArgument(Argument argument) {
        argument.getTypeSpec().accept(this);
        Expression expr = argument.getDefaultExpr();
        if (expr != null) {
            expr.accept(this);
        }
    }

    public void visitColumnDefinition(ColumnDefinition definition) {
        visitElement(definition);
    }

    public void visitLoopStatement(LoopStatement statement) {
        visitElement(statement);
    }

    public void visitCollectionMethodCall(CollectionMethodCall methodCall) {
    }

    public void visitRefCursorDecl(RefCursorDecl refCursorDecl) {
    }

    public void visitCollectionMethodCall2(CollectionMethodCall2 methodCall2) {
        visitElement(methodCall2);
    }

    public void visitCreateSynonym(CreateSynonym synonym) {
    }

    public void visitCreateSequence(CreateSequence sequence) {
    }

    public void visitCreateViewColumnDefInternal(CreateViewColumnDefInternal node) {
    }

    public void visitOrderByClause(OrderByClause orderByClause) {
        visitElement(orderByClause);
    }

    public void visitCursorLoopSpec(CursorLoopSpec spec) {
        visitElement(spec);
    }

    public void visitPrimaryKeyConstraint(PrimaryKeyConstraint constraint) {
        for (ColumnNameRef c : constraint.getPKColumns()) {
            c.accept(this);
        }
    }

    public void visitTableRef(TableRef ref) {
    }

    public void visitTableRefWithLink(TableRefWithLink refWithLink) {
    }

    public void visitSequenceRef(SequenceRef ref) {
    }

    public void visitHashPartition(HashPartition partition) {
        for(ColumnNameRef c: partition.getPartitionColumns()){
            c.accept(this);
        }
    }

    public void visitRangePartition(RangePartition partition) {
        for(ColumnNameRef c: partition.getPartitionColumns()){
            c.accept(this);
        }
    }

    public void visitColumnFKSpec(ColumnFKSpec columnFKSpec) {
        visitElement(columnFKSpec);
    }

    public void visitTrimFunctionCall(FunctionCall function) {
        visitElement(function);
    }

    public void visitSystemFunctionCall(FunctionCall function) {
        visitElement(function);
    }

    public void visitExtractFunctionCall(FunctionCall function) {
        visitElement(function);
    }

    public void visitReturnStatement(ReturnStatement statement) {
        visitElement(statement);
    }

    public void visitExpressionList(ExpressionList list) {
        visitElement(list);
    }

    public void visitSpecFunctionCall(SpecFunctionCall functionCall) {
        visitElement(functionCall);
    }

    public void visitGroupByClause(GroupByClause clause) {
        visitElement(clause);
    }

    public void visitForUpdateClause(ForUpdateClause clause) {
        visitElement(clause);
    }
}
