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

package com.deepsky.lang.plsql.tree.impl;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.psi.impl.ddl.*;
import com.deepsky.lang.plsql.tree.NodeVisitor;
import com.deepsky.lang.plsql.tree.Node;
import com.deepsky.lang.plsql.tree.ASTNodeInvocationHandler;
import com.deepsky.lang.plsql.psi.impl.*;
import com.deepsky.lang.plsql.psi.impl.types.*;
import com.deepsky.lang.plsql.psi.CaseExpression;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.database.ObjectCacheFactory;
import com.deepsky.database.ObjectCache;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

import java.lang.reflect.Proxy;

public class NodeVisitorImpl implements NodeVisitor {

    public void accept(Node node) {
        try {
            ASTNodeInvocationHandler handler = ASTNodeInvocationHandler.class.newInstance();
            handler.setNode(node);

            ASTNode f = (ASTNode) Proxy.newProxyInstance(
                    ASTNode.class.getClassLoader(),
                    new Class[]{ASTNode.class},
                    handler);

            node.putUserData(ASTNodeInvocationHandler.ASTNODE_PROXY_KEY, f);
            PsiElement psi = createElement(f);
            node.putUserData(ASTNodeInvocationHandler.PSI_PROXY_KEY, psi);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public PsiElement createElement(ASTNode node){
        final IElementType type = node.getElementType();
//        String tt = node.getText();
//        if (tt != null && tt.length() > 25) {
//            tt = tt.substring(0, 25) + " ...";
//        }
//        log.info("#create, text [" + tt + "] elem type: " + node.getElementType() + ", parent [" + node.getTreeParent().getElementType() + "]");

        if (type == PLSqlTypesAdopted.SELECT_EXPRESSION) {
            return new SelectStatementImpl(node);
//        } else if (type == PLSqlTypesAdopted.SELECT_SUBSEQ) {
//            // todo -- needs in review
//            return new SelectStatementImpl(node);
        } else if (type == PLSqlTypesAdopted.SELECT_EXPRESSION_UNION) {
            return new SelectStatementUnionImpl(node);
        } else if (type == PLSqlTypesAdopted.INSERT_COMMAND) {
            return new InsertStatementImpl(node);
        } else if (type == PLSqlTypesAdopted.UPDATE_COMMAND) {
            return new UpdateStatementImpl(node);
        } else if (type == PLSqlTypesAdopted.DELETE_COMMAND) {
            return new DeleteStatementImpl(node);
        } else if (type == PLSqlTypesAdopted.PLSQL_BLOCK) {
            return new PlSqlBlockImpl(node);
        } else if (type == PLSqlTypesAdopted.CONSTRAINT) {
            if (node.findChildByType(PLSqlTypesAdopted.FK_SPEC) != null) {
                return new ForeignKeyConstraintImpl(node);
            } else if (node.findChildByType(PLSqlTypesAdopted.PK_SPEC) != null) {
                return new PrimaryKeyConstraintImpl(node);
            } else {
                // todo - not implemented
            }
        } else if (type == PLSqlTypesAdopted.ASTERISK_COLUMN) {
            return new SelectFieldAsteriskImpl(node);
        } else if (type == PLSqlTypesAdopted.IDENT_ASTERISK_COLUMN) {
            return new SelectFieldIdentAsteriskImpl(node);
        } else if (type == PLSqlTypesAdopted.EXPR_COLUMN) {
            return new SelectFieldExprImpl(node);
        } else if (type == PLSqlTypesAdopted.TABLE_REFERENCE_LIST_FROM) {
            return new FromClauseImpl(node);
        } else if (type == PLSqlTypesAdopted.CURSOR_DECLARATION) {
            return new CursorDeclImpl(node);
        } else if (type == PLSqlTypesAdopted.TABLE_ALIAS) {
            return new PlainTableImpl(node);
        } else if (type == PLSqlTypesAdopted.FUNCTION_BODY) {
            return new FunctionImpl(node);
        } else if (type == PLSqlTypesAdopted.PLSQL_BLOCK) {
            return new PlSqlBlockImpl(node);
//        } else if (type == PLSqlTypesAdopted.INSERT_SUBQUERY) {
//            return new InsertSubqueryImpl(node);
        } else if (type == PLSqlTypesAdopted.SUBQUERY_EXPR) {
            return new SubqueryExpressionImpl(node);
        } else if (type == PLSqlTypesAdopted.PROCEDURE_BODY) {
            return new ProcedureImpl(node);
        } else if (type == PLSqlTypesAdopted.PACKAGE_BODY) {
            return new PackageBodyImpl(node);
        } else if (type == PLSqlTypesAdopted.PACKAGE_SPEC) {
            return new PackageSpecImpl(node);
        } else if (type == PLSqlTypesAdopted.PACKAGE_INIT_SECTION) {
            return new PackageInitSectionImpl(node);
        } else if (type == PLSqlTypesAdopted.FUNCTION_SPEC) {
            return new FunctionSpecImpl(node);
        } else if (type == PLSqlTypesAdopted.PROCEDURE_SPEC) {
            return new ProcedureSpecImpl(node);
//        } else if (type == PLSqlTypesAdopted.FROM_PLAIN_TABLE) {
//            return new PlainTableImpl(node);
        } else if (type == PLSqlTypesAdopted.FROM_SUBQUERY) {
            return new FromSubqueryImpl(node);
        } else if (type == PLSqlTypesAdopted.OBJECT_NAME) {
            return new ObjectNameImpl(node);
//        } else if(type == PLSqlTypesAdopted.REFERENCE_TO){
//        } else if(type == PLSqlTypesAdopted.IDENT){
//        } else if(type == PLSqlTypesAdopted.VAR_REF){
//            return new ReferenceExpressionImpl(node);
        } else if (type == PLSqlTypesAdopted.TABLE_REF) {
            // reference to a table
            return new TableNameRefImpl(node);
//            return new NameReferenceFragmentImpl(node);
// [ ------------------- ]
        } else if (type == PLSqlTypesAdopted.NAME_FRAGMENT) {
            return new NameFragmentRefImpl(node);
// [ +++++++++++++++++++ ]

        } else if (type == PLSqlTypesAdopted.COLUMN_NAME_REF) {
            return new ColumnNameRefImpl(node);
        } else if (type == PLSqlTypesAdopted.COLUMN_NAME_DDL) {
            return new ColumnNameDDLImpl(node);
        } else if (type == PLSqlTypesAdopted.TABLE_NAME) {
            String text = node.getText();
            ObjectCacheFactory.getObjectCache().preloadObject(ObjectCache.TABLE | ObjectCache.VIEW, null, text);

            return new TableImpl(node);
        } else if (type == PLSqlTypesAdopted.TABLE_NAME_DDL) {
            return new DDLTableImpl(node);
        } else if (type == PLSqlTypesAdopted.VIEW_NAME_DDL) {
            return new DDLViewImpl(node);
        } else if (type == PLSqlTypesAdopted.FUNCTION_CALL) {
/*
            ASTNode name = node.findChildByType(PLSqlTypesAdopted.FUNCTION_NAME);
            String text = name.getText();
            FunctionNameDTO dto = ASTParseHelper.parseFunctionName(text);
            if(dto.getSchema().length() > 0 ){
                ObjectCacheFactory.getObjectCache().refreshDbObject(ObjectCache.PACKAGE, dto.getSchema(), dto.getPackage());
            } else if(dto.getPackage().length() > 0 ){
                ObjectCacheFactory.getObjectCache().refreshDbObject(ObjectCache.PACKAGE, null, dto.getPackage());
            } else {
                // three cases possible:
                //  1. this is an embedded function
                //  2. function defined outside any package
                //  3. function defined inside the package
                ObjectCacheFactory.getObjectCache().refreshDbObject(ObjectCache.USER_FUNCTION, null, dto.getName());
            }
*/
            return new FunctionCallImpl(node);
        } else if (type == PLSqlTypesAdopted.PROCEDURE_CALL) {
/*
            ASTNode name = node.findChildByType(PLSqlTypesAdopted.FUNCTION_NAME);
            String text = name.getText();
            FunctionNameDTO dto = ASTParseHelper.parseFunctionName(text);
            if(dto.getSchema().length() > 0 ){
                ObjectCacheFactory.getObjectCache().refreshDbObject(ObjectCache.PACKAGE, dto.getSchema(), dto.getPackage());
            } else if(dto.getPackage().length() > 0 ){
                ObjectCacheFactory.getObjectCache().refreshDbObject(ObjectCache.PACKAGE, null, dto.getPackage());
            } else {
                // three cases possible:
                //  1. this is an embedded function
                //  2. function defined outside any package
                //  3. function defined inside the package
                ObjectCacheFactory.getObjectCache().refreshDbObject(ObjectCache.USER_PROCEDURE, null, dto.getName());
            }
*/
            return new ProcedureCallImpl(node);
/*
        } else if(type == PLSqlTypesAdopted.FUNC_NAME_REF){
            //return new FunctionReferenceImpl(node);
//            return new FunctionNameRefImpl(node);
//            return new CallableNameReferenceImpl(node);
            return new CallableNameCompositBase(node);
        } else if(type == PLSqlTypesAdopted.PROC_NAME_REF){
            //return new FunctionReferenceImpl(node);
//            return new ProcedureNameRefImpl(node);
            return new CallableNameCompositBase(node);
//            return new CallableNameReferenceImpl(node);
*/
        } else if (type == PLSqlTypesAdopted.CALLABLE_NAME_REF) {
            return new CallableCompositeNameBase(node);
//        } else if(type == PLSqlTypesAdopted.EXEC_NAME_REF){
//            return new CallableNameReferenceImpl(node);
        } else if (type == PLSqlTypesAdopted.VAR_REF) {
            return new ObjectReferenceImpl(node);
        } else if (type == PLSqlTypesAdopted.PLSQL_VAR_REF) {
            return new ObjectReferenceImpl(node);
        } else if (type == PLSqlTypesAdopted.TRIGGER_COLUMN_REF) {
            return new TriggerColumnNameRefImpl(node);
        } else if (type == PLSqlTypesAdopted.COLUMN_OUTER_JOIN) {
            return new ColumnOuterJoinImpl(node);
        } else if (type == PLSqlTypesAdopted.PARENTHESIZED_EXPR) {
            return new ParenthesizedExprImpl(node);
        } else if (type == PLSqlTypesAdopted.NUMERIC_LITERAL) {
            return new NumericLiteralImpl(node);
        } else if (type == PLSqlTypesAdopted.STRING_LITERAL) {
            return new StringLiteralImpl(node);
        } else if (type == PLSqlTypesAdopted.BOOLEAN_LITERAL) {
            return new BooleanLiteral(node);
        } else if (type == PLSqlTypesAdopted.DECLARE_LIST) {
            return new DeclarationListImpl(node);
        } else if (type == PLSqlTypesAdopted.VARIABLE_DECLARATION) {
            return new VariableDeclImpl(node);
        } else if (type == PLSqlTypesAdopted.VARIABLE_NAME) {
            return new VariableNameImpl(node);
        } else if (type == PLSqlTypesAdopted.PARAMETER_NAME) {
            return new ParameterNameImpl(node);
//        } else if (type == PLSqlTypesAdopted.RECORD_ITEM_NAME) {
//            return new RecordItemNameImpl(node);
        } else if (type == PLSqlTypesAdopted.ARGUMENT_LIST) {
            return new ArgumentListImpl(node);
        } else if (type == PLSqlTypesAdopted.CALL_ARGUMENT_LIST) {
            return new CallArgumentListImpl(node);
        } else if (type == PLSqlTypesAdopted.PARAMETER_SPEC) {
            return new ArgumentImpl(node);
        } else if (type == PLSqlTypesAdopted.SYSTIMESTAMP_CONST) {
            return new SystimestampLiteral(node);
        } else if (type == PLSqlTypesAdopted.CURRENT_TIMESTAMP_CONST) {
            return new CurrentTimestampLiteral(node);
        } else if (type == PLSqlTypesAdopted.SYSDATE_CONST) {
            return new SysdateLiteral(node);
//        } else if(type == PLSqlTypesAdopted.ROWCOUNT_EXRESSION){
//            return new RowcountExpressionImpl(node);
        } else if (type == PLSqlTypesAdopted.ROWNUM) {
            return new RownumLiteral(node);
        } else if (type == PLSqlTypesAdopted.NULL_STATEMENT) {
            return new NullLiteralImpl(node);
        } else if (type == PLSqlTypesAdopted.CASE_EXPRESSION_SMPL) {
            return new CaseExpressionImpl(node, CaseExpression.SIMPLE);
        } else if (type == PLSqlTypesAdopted.CASE_EXPRESSION_SRCH) {
            return new CaseExpressionImpl(node, CaseExpression.SEARCHED);
        } else if (type == PLSqlTypesAdopted.COUNT_FUNC) {
            return new SystemFunctionImpl(node, "COUNT", TypeFactory.createTypeById(Type.NUMBER));
        } else if (type == PLSqlTypesAdopted.DBTIMEZONE) {
            return new SystemFunctionImpl(node, "DBTIMEZONE", TypeFactory.createTypeById(Type.VARCHAR));
        } else if (type == PLSqlTypesAdopted.SYSDATE_CONST) {
            return new SystemFunctionImpl(node, "SYSDATE", TypeFactory.createTypeById(Type.DATE));
        } else if (type == PLSqlTypesAdopted.ROWCOUNT_EXRESSION) {
            return new SystemVariableImpl(node, TypeFactory.createTypeById(Type.NUMBER));
        } else if (type == PLSqlTypesAdopted.CURRENT_OF_CLAUSE) {
            return new SystemVariableImpl(node, TypeFactory.createTypeById(Type.BOOLEAN));
        } else if (type == PLSqlTypesAdopted.CURSOR_STATE) {
            return new SystemVariableImpl(node, TypeFactory.createTypeById(Type.BOOLEAN));
        } else if (type == PLSqlTypesAdopted.SQLCODE_SYSVAR) {
            return new SystemVariableImpl(node, TypeFactory.createTypeById(Type.INTEGER));
        } else if (type == PLSqlTypesAdopted.SQLERRM_SYSVAR) {
            return new SystemVariableImpl(node, TypeFactory.createTypeById(Type.CHAR));
        } else if (type == PLSqlTypesAdopted.USER_CONST) {
            return new UserConstImpl(node);
        } else if (type == PLSqlTypesAdopted.EXTRACT_DATE_FUNC) {
            return new ExtractFunctionImpl(node);
        } else if (type == PLSqlTypesAdopted.TRIM_FUNC) {
            return new TrimFunctionImpl(node);
        } else if (type == PLSqlTypesAdopted.CAST_EXPR) {
            return new CastExpressionImpl(node);
        } else if (type == PLSqlTypesAdopted.SEQUENCE_EXPR) {
            return new SequenceExprImpl(node);
        } else if (type == PLSqlTypesAdopted.CALL_ARGUMENT) {
            return new CallArgumentImpl(node);
        } else if (type == PLSqlTypesAdopted.INTERVAL_DAY_TO_SEC_EXPR) {
            return new IntervalExpressionImpl(node);
        } else if (type == PLSqlTypesAdopted.ARITHMETIC_EXPR) {
            return new ArithmeticExpressionImpl(node);
        } else if (type == PLSqlTypesAdopted.LOGICAL_EXPR) {
            return new LogicalExpressionImpl(node);
        } else if (type == PLSqlTypesAdopted.WHERE_CONDITION) {
            return new WhereConditionImpl(node);
        } else if (type == PLSqlTypesAdopted.SUBQUERY_CONDITION) {
            return new SubqueryConditionImpl(node);
        } else if (type == PLSqlTypesAdopted.EXISTS_EXPR) {
            return new ExistsConditionImpl(node);
        } else if (type == PLSqlTypesAdopted.RELATION_CONDITION) {
            return new RelationConditionImpl(node);
        } else if (type == PLSqlTypesAdopted.IN_CONDITION) {
            return new InConditionImpl(node);
        } else if (type == PLSqlTypesAdopted.LIKE_CONDITION) {
            return new LikeConditionImpl(node);
        } else if (type == PLSqlTypesAdopted.BETWEEN_CONDITION) {
            return new BetweenConditionImpl(node);
        } else if (type == PLSqlTypesAdopted.ISNULL_CONDITION) {
            return new IsNullConditionImpl(node);
//        } else if(type == PLSqlTypesAdopted.TYPE_SPEC){
//            return new TypeSpecVariantImpl(node);
        } else if (type == PLSqlTypesAdopted.AT_TIME_ZONE_EXPR) {
            return new AtTimeZoneExpressionImpl(node);

// [ === Type bindings ===]
        } else if (type == PLSqlTypesAdopted.TYPE_NAME_REF) {
            return new TypeNameReferenceImpl(node);
        } else if (type == PLSqlTypesAdopted.COLUMN_TYPE_REF) {
            return new ColumnTypeRefImpl(node);
        } else if (type == PLSqlTypesAdopted.TABLE_TYPE_REF) {
            return new RowtypeTypeImpl(node);
        } else if (type == PLSqlTypesAdopted.DATATYPE) {
            return new DataTypeImpl(node);
// [ ==================== ]


        } else if (type == PLSqlTypesAdopted.RECORD_TYPE_DECL) {
            return new RecordTypeDeclImpl(node);
        } else if (type == PLSqlTypesAdopted.TABLE_COLLECTION) {
            return new TableCollectionDeclImpl(node);
        } else if (type == PLSqlTypesAdopted.VARRAY_COLLECTION) {
            return new VarrayCollectionDeclImpl(node);
        } else if (type == PLSqlTypesAdopted.OBJECT_TYPE_DEF) {
            return new ObjectTypeDeclImpl(node);
        } else if (type == PLSqlTypesAdopted.REF_CURSOR) {
            return new RefCursorDeclImpl(node);


// [ === DDL Statements ===]
        } else if (type == PLSqlTypesAdopted.TABLE_DEF) {
            return new TableDefinitionImpl(node);
        } else if (type == PLSqlTypesAdopted.CREATE_TEMP_TABLE) {
            return new CreateTempTableImpl(node);
        } else if (type == PLSqlTypesAdopted.CREATE_INDEX) {
            return new CreateIndexImpl(node);
        } else if (type == PLSqlTypesAdopted.CREATE_VIEW) {
            return new CreateViewImpl(node);
        } else if (type == PLSqlTypesAdopted.ALTER_TABLE) {
            return new AlterTableImpl(node);
        } else if (type == PLSqlTypesAdopted.COMMENT) {
            return new CommentImpl(node);
// [ ==================== ]


        } else if (type == PLSqlTypesAdopted.COLUMN_DEF) {
            return new ColumnDefinitionImpl(node);
        } else if (type == PLSqlTypesAdopted.V_COLUMN_DEF) {
            return new VColumnDefinitionImpl(node);
//        } else if (type == PLSqlTypesAdopted.NAME_PREFIX) {
//            return new NamePrefixImpl(node);
        } else if (type == PLSqlTypesAdopted.RECORD_ITEM) {
            return new RecordTypeItemImpl(node);
        } else if (type == PLSqlTypesAdopted.COLUMN_SPEC_LIST) {
            return new ColumnSpecListImpl(node);
        } else if (type == PLSqlTypesAdopted.COLUMN_SPEC) {
            return new ColumnSpecImpl(node);
        } else if (type == PLSqlTypesAdopted.ORDER_CLAUSE) {
            return new OrderByClauseImpl(node);
        } else if (type == PLSqlTypesAdopted.LAST_STMT_RESULT_BOOL) {
            return new LastStatementResultImpl(node, TypeFactory.createTypeById(Type.BOOLEAN));
        } else if (type == PLSqlTypesAdopted.LAST_STMT_RESULT_NUM) {
            return new LastStatementResultImpl(node, TypeFactory.createTypeById(Type.NUMBER));
        } else if (type == PLSqlTypesAdopted.ERROR_TOKEN_A) {
            return new ErrorTokenWrapperImpl(node);
        } else if (type == PLSqlTypesAdopted.COLLECTION_METHOD_CALL) {
            return new CollectionMethodCallImpl(node);
        } else if (type == PLSqlTypesAdopted.SQLPLUS_PROMPT) {
            return new SqlPlusPromptRem(node);

            // [start] TODO -- trick - must be revised
        } else if (type == PlSqlTokenTypes.COLON_OLD) {
//            return new TriggerSpecColumn(node);
            return new NameFragmentRefImpl(node);
        } else if (type == PlSqlTokenTypes.COLON_NEW) {
//            return new TriggerSpecColumn(node);
            return new NameFragmentRefImpl(node);
            // [end] TODO -- trick - must be revised

        } else if (type == PLSqlTypesAdopted.CREATE_TRIGGER) {
            if (node.findChildByType(PLSqlTypesAdopted.DML_TRIGGER_CLAUSE) != null) {
                return new CreateTriggerDMLImpl(node);
            } else if (node.findChildByType(PLSqlTypesAdopted.DDL_TRIGGER_CLAUSE) != null) {
                // todo - implement me
                return new CreateTriggerGeneric(node);
            } else if (node.findChildByType(PLSqlTypesAdopted.DB_EVNT_TRIGGER_CLAUSE) != null) {
                // todo - implement me
                return new CreateTriggerGeneric(node);
            } else if (node.findChildByType(PLSqlTypesAdopted.INSTEADOF_TRIGGER) != null) {
                // todo - implement me
                return new CreateTriggerGeneric(node);
            } else {
                // todo - implement me
            }

        } else {
//            log.info("#create, [NOT SUPPORTED YET] text [" + tt + "] elem type: " + node.getElementType() + ", parent [" + node.getTreeParent().getElementType() + "]");
        }
//        log.info("#create, text [" + tt + "] elem type: " + node.getElementType() + ", parent [" + node.getTreeParent().getElementType() + "]");


        return new ASTWrapperPsiElement(node);
    }

}

