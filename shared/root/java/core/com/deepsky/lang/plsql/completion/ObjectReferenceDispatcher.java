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

package com.deepsky.lang.plsql.completion;

import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.integration.PlSqlElementType;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.psi.*;
import com.intellij.psi.PsiElement;

public class ObjectReferenceDispatcher {

    static public void process(NameFragmentRef ref, ParentNodeHandler handler){
        PsiElement parent = ref.getParent();

        if (parent instanceof ColumnSpec) {
            handler.visitColumnSpec((ColumnSpec) parent);
        } else if (parent instanceof ObjectReference) {

            if(parent.getNode().getElementType() == PLSqlTypesAdopted.PLSQL_VAR_REF){
                handler.visitPlSqlReference((ObjectReference) parent);
                return;
            }

            PsiElement expr = parent.getParent();
            int ttype = PlSqlElementType.mapTo_TokenType(expr.getNode().getElementType());

            switch(ttype){
                case PLSqlTokenTypes.SELECT_EXPRESSION:
                    handler.visitSelectStatement( expr);
                    break;
                case PLSqlTokenTypes.EXPR_COLUMN:
                    handler.visitSelectFieldExpr((SelectFieldExpr) expr);
                    break;
                case PLSqlTokenTypes.LOGICAL_EXPR:
                    handler.visitLogicalExpression((LogicalExpression) expr);
                    break;
                case PLSqlTokenTypes.WHERE_CONDITION:
                    handler.visitWhereCondition((WhereCondition) expr);
                    break;
                case PLSqlTokenTypes.RELATION_CONDITION:
                    handler.visitRelationCondition((RelationCondition) expr);
                    break;
                case PLSqlTokenTypes.IN_CONDITION:
                    handler.visitInCondition((InCondition) expr);
                    break;
                case PLSqlTokenTypes.BETWEEN_CONDITION:
                    handler.visitBetweenCondition((BetweenCondition) expr);
                    break;
                case PLSqlTokenTypes.VARIABLE_DECLARATION:
                    handler.visitDefaultExprForVariable((VariableDecl) expr);
                    break;
                case PLSqlTokenTypes.ARITHMETIC_EXPR:
                    handler.visitArithmeticExpression((ArithmeticExpression) expr);
                    break;
                case PLSqlTokenTypes.CALL_ARGUMENT:
                    handler.visitCallArgument((CallArgument) expr);
                    break;
                case PLSqlTokenTypes.ASSIGNMENT_STATEMENT:
                    handler.visitAssignmentStatement((AssignmentStatement) expr);
                    break;
                case PLSqlTokenTypes.GROUP_CLAUSE:
                    handler.visitGroupByClause((GroupByClause) expr);
                    break;
                case PLSqlTokenTypes.SORTED_DEF:
                    handler.visitOrderByClause((OrderByClause) expr.getParent());
                    break;
                case PLSqlTokenTypes.EXPR_LIST:
                    handler.visitExpressionList((ExpressionList) expr);
                    break;
                case PLSqlTokenTypes.RETURN_STATEMENT:
                    handler.visitReturnStatement((ReturnStatement) expr);
                    break;
                default:
                    handler.visitUnhandledNode(expr);
                    break;
            }

//            } else if (parent.getNode().getElementType() == PlSqlElementTypes.PLSQL_VAR_REF) {
//                if (getExecCtx() != null) {
//                    return provider.collectVarVariants(
//                            usageCtxPath,
//                            lookUpStr
//                    );
//                }
        } else if (parent instanceof TypeNameReference) {
            handler.visitTypeNameReference((TypeNameReference) parent);
        } else if (parent instanceof CallableCompositeName) {
            handler.visitCallableCompositeName((CallableCompositeName) parent);
        } else if (parent instanceof ParameterReference) {
            handler.visitParameterNameRef((ParameterReference) parent);
        } else {
            handler.visitUnhandledNode(parent);
        }

    }


    public interface ParentNodeHandler {
        void visitLogicalExpression(LogicalExpression l);
        void visitSelectFieldExpr(SelectFieldExpr l);
        void visitRelationCondition(RelationCondition l);
        void visitWhereCondition(WhereCondition l);
        void visitInCondition(InCondition l);

        void visitBetweenCondition(BetweenCondition l);
        void visitArithmeticExpression(ArithmeticExpression l);
        void visitCallArgument(CallArgument l);
        void visitGroupByClause(GroupByClause l);
        void visitOrderByClause(OrderByClause l);
        void visitColumnSpec(ColumnSpec l);
        void visitExpressionList(ExpressionList l);

        void visitDefaultExprForVariable(VariableDecl l);
        void visitAssignmentStatement(AssignmentStatement l);
        void visitTypeNameReference(TypeNameReference l);
        void visitCallableCompositeName(CallableCompositeName l);
        void visitParameterNameRef(ParameterReference l);
        void visitReturnStatement(ReturnStatement l);

        void visitSelectStatement(PsiElement expr);
        void visitPlSqlReference(ObjectReference reference);

        void visitUnhandledNode(PsiElement e);

    }
}
