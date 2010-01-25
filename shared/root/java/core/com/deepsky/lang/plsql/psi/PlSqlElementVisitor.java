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

package com.deepsky.lang.plsql.psi;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.plsql.psi.ddl.*;
import com.deepsky.lang.plsql.psi.impl.ParameterReferenceImpl;
import com.deepsky.lang.plsql.psi.impl.SqlPlusPromptRem;
import com.deepsky.lang.plsql.psi.ref.DDLTable;
import com.deepsky.lang.plsql.psi.ref.DDLView;
import com.deepsky.lang.plsql.psi.ref.Table;
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

    public void visitPlainTable(PlainTable node) {
        visitElement(node);
    }

    public void visitPlSqlBlock(PlSqlBlock node) {
        for(PlSqlElement psi: node.getObjectList()){
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
        for(CallArgument arg: node.getCallArgumentList()){
            arg.accept(this);
        }
    }

    public void visitProcedureCall(ProcedureCall node) {
        node.getCompositeName().accept(this);
        for(CallArgument arg: node.getCallArgumentList()){
            arg.accept(this);
        }
    }

    public void visitCondition(Condition node) {
        visitElement(node);
    }

    public void visitPlSqlFile(PlSqlFile plSqlFile) {
    }

    public void visitCallArgumentList(CallArgumentList node) {
    }

    public void visitTable(Table table) {
    }

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
        visitElement(node);
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
    }

    public void visitColumnSpec(ColumnSpec spec) {
        visitElement(spec);
    }

    public void visitTableDefinition(TableDefinition tableDefinition) {
        for(GenericConstraint constr: tableDefinition.getConstraints()){
            constr.accept(this);
        }

        // todo -- columns?
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
    }

    public void visitCreateIndex(CreateIndex index) {
    }

    public void visitCreateTrigger(CreateTrigger trigger) {
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
        DDLTable tab = constraint.getReferencedTable2();
        tab.accept(this);
        for(ColumnNameDDL c: constraint.getReferencedColumns2()){
            c.accept(this);
        }
        ///visitElement(constraint);
    }

    public void visitColumnNameDDL(ColumnNameDDL columnNameDDL) {
    }

    public void visitDDLView(DDLView ddlView) {
    }

    public void visitColumnTypeRef(ColumnTypeRef columnTypeRef) {
        columnTypeRef.getTableName2().accept(this);
        columnTypeRef.getColumnName().accept(this);
    }

    public void visitColumnNameRef(ColumnNameRef columnNameRef) {
    }

    public void visitCallableCompositeName(CallableCompositeName compositeName) {
    }

    public void visitParameterReference(ParameterReferenceImpl reference) {
    }

    public void visitCRecordItemRef(CRecordItemRef ref) {
    }
}
