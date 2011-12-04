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

package com.deepsky.lang.common;

import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.integration.PlSqlElementType;
import com.deepsky.lang.lexer.PlSqlPsiLexer;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.impl.*;
import com.deepsky.lang.plsql.psi.impl.BooleanLiteral;
import com.deepsky.lang.plsql.psi.impl.ctrl.CommitStatementImpl;
import com.deepsky.lang.plsql.psi.impl.ctrl.RollbackStatementImpl;
import com.deepsky.lang.plsql.psi.impl.ddl.*;
import com.deepsky.lang.plsql.psi.impl.internal.CreateViewColumnDefInternalImpl;
import com.deepsky.lang.plsql.psi.impl.ref.SequenceRefImpl;
import com.deepsky.lang.plsql.psi.impl.ref.TableRefImpl;
import com.deepsky.lang.plsql.psi.impl.ref.TableRefWithLinkImpl;
import com.deepsky.lang.plsql.psi.impl.spec_func_call.*;
import com.deepsky.lang.plsql.psi.impl.types.ColumnTypeRefImpl;
import com.deepsky.lang.plsql.psi.impl.types.DataTypeImpl;
import com.deepsky.lang.plsql.psi.impl.types.RowtypeTypeImpl;
import com.deepsky.lang.plsql.psi.impl.types.TypeNameReferenceImpl;
import com.deepsky.lang.plsql.sqlIndex.DbDumpedSqlFile;
import com.deepsky.lang.plsql.sqlIndex.FSSqlFile;
import com.deepsky.lang.plsql.sqlIndex.SysSqlFile;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.apache.commons.collections.map.AbstractReferenceMap;
import org.apache.commons.collections.map.ReferenceMap;
import org.jetbrains.annotations.NotNull;

public class PlSqlParserDefinition implements ParserDefinition {

    private ReferenceMap path2file = new ReferenceMap(AbstractReferenceMap.SOFT, AbstractReferenceMap.WEAK);

    @NotNull
    public Lexer createLexer(Project project) {
        return new PlSqlPsiLexer();
    }

    public IFileElementType getFileNodeType() {
        return PlSqlTokenTypes.FILE;
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return TokenSet.create(PlSqlTokenTypes.WS, PlSqlTokenTypes.LF, TokenType.WHITE_SPACE);
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return PlSqlTokenTypes.COMMENTS;
    }


    @NotNull
    public TokenSet getStringLiteralElements() {
        return PlSqlTokenTypes.STRING_LITERALS;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new PlSqlPsiParser();
    }

    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return createFileInternal(fileViewProvider);
    }

    private synchronized PsiFile createFileInternal(FileViewProvider fileViewProvider){
        VirtualFile file = fileViewProvider.getVirtualFile();
        if(file instanceof DbDumpedSqlFile){
            // SQL file from DbSchemaIndex
            PlSqlFile f = (PlSqlFile) path2file.get(file.getPath());
            if(f == null){
                f = new PlSqlFile(fileViewProvider);
                path2file.put(file.getPath(), f);
            }
            return f;
        } else if(file instanceof FSSqlFile){
            // SQL file from FSIndex
            return new PlSqlFile(fileViewProvider);
        } else if(file instanceof SysSqlFile){
            PlSqlFile f = (PlSqlFile) path2file.get(file.getPath());
            if(f == null){
                f = new PlSqlFile(fileViewProvider);
                path2file.put(file.getPath(), f);
            }
            return f;
        } else {
            // SQL file from FSIndex or proxied file
            return new PlSqlFile(fileViewProvider);
        }
    }


    public ParserDefinition.SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode astNode, ASTNode astNode1) {
        // TODO
        return null;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        try {
            PsiElement element = createElement2(node);
            if (element != null)
                return element;
        } catch (Throwable ignored) {
        }

        return new ASTWrapperPsiElement(node);
    }


    public static PsiElement createElement2(ASTNode node) {
        final IElementType type = node.getElementType();
        int ttype = PlSqlElementType.mapTo_TokenType(type);

        switch (ttype) {
            case PLSqlTokenTypes.LAG_FUNCTION:
                return new LagFunction(node);
            case PLSqlTokenTypes.LEAD_FUNCTION:
                return new LeadFunction(node);
            case PLSqlTokenTypes.RETURN_STATEMENT:
                return new ReturnStatementImpl(node);
            case PLSqlTokenTypes.EXPR_LIST:
                return new ExpressionListImpl(node);
            case PLSqlTokenTypes.ASSIGNMENT_STATEMENT:
                return new AssignmentStatementImpl(node);
            case PLSqlTokenTypes.SELECT_EXPRESSION:
                return new SelectStatementImpl(node);
            case PLSqlTokenTypes.SELECT_EXPRESSION_UNION:
                return new SelectStatementUnionImpl(node);
            case PLSqlTokenTypes.INSERT_COMMAND:
                return new InsertStatementImpl(node);
            case PLSqlTokenTypes.MERGE_COMMAND:
                return new MergeStatementImpl(node);
            case PLSqlTokenTypes.UPDATE_COMMAND:
                return new UpdateStatementImpl(node);
            case PLSqlTokenTypes.DELETE_COMMAND:
                return new DeleteStatementImpl(node);
            case PLSqlTokenTypes.CONSTRAINT:
                if (node.findChildByType(PlSqlElementTypes.FK_SPEC) != null) {
                    return new ForeignKeyConstraintImpl(node);
                } else if (node.findChildByType(PlSqlElementTypes.PK_SPEC) != null) {
                    return new PrimaryKeyConstraintImpl(node);
                } else {
                    // todo - not implemented
                }
                break;
            case PLSqlTokenTypes.COLUMN_FK_SPEC:
                return new ColumnFKSpecImpl(node);
            case PLSqlTokenTypes.COLUMN_PK_SPEC:
                return new ColumnPKSpecImpl(node);
            case PLSqlTokenTypes.ASTERISK_COLUMN:
                return new SelectFieldAsteriskImpl(node);
            case PLSqlTokenTypes.IDENT_ASTERISK_COLUMN:
                return new SelectFieldIdentAsteriskImpl(node);
            case PLSqlTokenTypes.EXPR_COLUMN:
                return new SelectFieldExprImpl(node);
            case PLSqlTokenTypes.TABLE_REFERENCE_LIST_FROM:
                return new FromClauseImpl(node);
            case PLSqlTokenTypes.CURSOR_DECLARATION:
                return new CursorDeclImpl(node);
            case PLSqlTokenTypes.TABLE_ALIAS:
                return new TableAliasImpl(node);
            case PLSqlTokenTypes.TABLE_FUNCTION:
                return new TableFunctionImpl(node);
            case PLSqlTokenTypes.FUNCTION_BODY:
                return new FunctionImpl(node);
            case PLSqlTokenTypes.PLSQL_BLOCK:
                return new PlSqlBlockImpl(node);
            case PLSqlTokenTypes.SUBQUERY_EXPR:
                return new SubqueryExpressionImpl(node);
            case PLSqlTokenTypes.PROCEDURE_BODY:
                return new ProcedureImpl(node);

//            case PLSqlTokenTypes.CREATE_FUNCTION:
//                return new CreateFunctionImpl(node);
//            case PLSqlTokenTypes.CREATE_PROCEDURE:
//                return new CreateProcedureImpl(node);

            case PLSqlTokenTypes.PACKAGE_BODY:
                return new PackageBodyImpl(node);
            case PLSqlTokenTypes.PACKAGE_SPEC:
                return new PackageSpecImpl(node);
            case PLSqlTokenTypes.PACKAGE_INIT_SECTION:
                return new PackageInitSectionImpl(node);
            case PLSqlTokenTypes.FUNCTION_SPEC:
                return new FunctionSpecImpl(node);
            case PLSqlTokenTypes.PROCEDURE_SPEC:
                return new ProcedureSpecImpl(node);
//        } else if (type == PLSqlTypesAdopted.FROM_PLAIN_TABLE:
//            return new PlainTableImpl(node);
            case PLSqlTokenTypes.FROM_SUBQUERY:
                return new FromSubqueryImpl(node);
            case PLSqlTokenTypes.SUBQUERY:
                return new SubqueryImpl(node);
            case PLSqlTokenTypes.OBJECT_NAME:
                return new ObjectNameImpl(node);
            case PLSqlTokenTypes.TABLE_REF_WITH_LINK:
                // reference to a table
                return new TableRefWithLinkImpl(node);
            case PLSqlTokenTypes.TABLE_REF:
                // reference to a table
                return new TableRefImpl(node);

// [ ------------------- ]
            case PLSqlTokenTypes.NAME_FRAGMENT:
                return new NameFragmentRefImpl(node);
            case PLSqlTokenTypes.EXEC_NAME_REF:
                return new ExecNameRefImpl(node);
// [ +++++++++++++++++++ ]

            case PLSqlTokenTypes.CURSOR_NAME_REF:
                return new CursorNameRefImpl(node);
            case PLSqlTokenTypes.COLUMN_NAME_REF:
                return new ColumnNameRefImpl(node);
            case PLSqlTokenTypes.COLUMN_NAME_DDL:
                return new ColumnNameDDLImpl(node);
//            case PLSqlTokenTypes.TABLE_NAME_WITH_LINK:
//                return new TableNameWithLinkImpl(node);
//            case PLSqlTokenTypes.TABLE_NAME:
//                return new TableImpl(node);
            case PLSqlTokenTypes.TABLE_NAME_DDL:
                return new DDLTableImpl(node);
            case PLSqlTokenTypes.VIEW_NAME_DDL:
                return new DDLViewImpl(node);
            case PLSqlTokenTypes.FUNCTION_CALL:
                return new FunctionCallImpl(node);
            case PLSqlTokenTypes.PROCEDURE_CALL:
                return new ProcedureCallImpl(node);
            case PLSqlTokenTypes.CALLABLE_NAME_REF:
                return new CallableCompositeNameBase(node);
            case PLSqlTokenTypes.VAR_REF:
                return new ObjectReferenceImpl(node, false);
            case PLSqlTokenTypes.PLSQL_VAR_REF:
                return new ObjectReferenceImpl(node, true);
            case PLSqlTokenTypes.PARAMETER_REF:
                return new ParameterReferenceImpl(node);
            case PLSqlTokenTypes.TRIGGER_COLUMN_REF:
                return new TriggerColumnNameRefImpl(node);
            case PLSqlTokenTypes.COLUMN_OUTER_JOIN:
                return new ColumnOuterJoinImpl(node);
            case PLSqlTokenTypes.PARENTHESIZED_EXPR:
                return new ParenthesizedExprImpl(node);
            case PLSqlTokenTypes.NUMERIC_LITERAL:
                return new NumericLiteralImpl(node);
            case PLSqlTokenTypes.STRING_LITERAL:
                return new StringLiteralImpl(node);
            case PLSqlTokenTypes.BOOLEAN_LITERAL:
                return new BooleanLiteral(node);
            case PLSqlTokenTypes.DECLARE_LIST:
                return new DeclarationListImpl(node);
            case PLSqlTokenTypes.VARIABLE_DECLARATION:
                return new VariableDeclImpl(node);
            case PLSqlTokenTypes.VARIABLE_NAME:
                return new VariableNameImpl(node);
            case PLSqlTokenTypes.PARAMETER_NAME:
                return new ParameterNameImpl(node);
            case PLSqlTokenTypes.ARGUMENT_LIST:
                return new ArgumentListImpl(node);
            case PLSqlTokenTypes.CALL_ARGUMENT_LIST:
                return new CallArgumentListImpl(node);
            case PLSqlTokenTypes.PARAMETER_SPEC:
                return new ArgumentImpl(node);
            case PLSqlTokenTypes.SYSTIMESTAMP_CONST:
                return new SystimestampLiteral(node);
            case PLSqlTokenTypes.CURRENT_TIMESTAMP_CONST:
                return new CurrentTimestampLiteral(node);
            case PLSqlTokenTypes.ROWNUM:
                return new RownumLiteral(node);
            case PLSqlTokenTypes.ROWID:
                return new RowidLiteral(node);
            case PLSqlTokenTypes.NULL_STATEMENT:
                return new NullLiteralImpl(node);
            case PLSqlTokenTypes.CASE_EXPRESSION_SMPL:
                return new CaseExpressionImpl(node, CaseExpression.SIMPLE);
            case PLSqlTokenTypes.CASE_EXPRESSION_SRCH:
                return new CaseExpressionImpl(node, CaseExpression.SEARCHED);
            case PLSqlTokenTypes.COUNT_FUNC:
                return new SystemFunctionImpl(node, "COUNT", TypeFactory.createTypeById(Type.NUMBER), true);
            case PLSqlTokenTypes.DBTIMEZONE:
                return new SystemFunctionImpl(node, "DBTIMEZONE", TypeFactory.createTypeById(Type.VARCHAR));
            case PLSqlTokenTypes.SYSDATE_CONST:
                return new SystemFunctionImpl(node, "SYSDATE", TypeFactory.createTypeById(Type.DATE));
            case PLSqlTokenTypes.ROWCOUNT_EXRESSION:
                return new SystemVariableImpl(node, TypeFactory.createTypeById(Type.NUMBER));
            case PLSqlTokenTypes.CURRENT_OF_CLAUSE:
                return new SystemVariableImpl(node, TypeFactory.createTypeById(Type.BOOLEAN));
            case PLSqlTokenTypes.CURSOR_STATE:
                return new SystemVariableImpl(node, TypeFactory.createTypeById(Type.BOOLEAN));
            case PLSqlTokenTypes.SQLCODE_SYSVAR:
                return new SystemVariableImpl(node, TypeFactory.createTypeById(Type.INTEGER));
            case PLSqlTokenTypes.SQLERRM_SYSVAR:
                return new SystemVariableImpl(node, TypeFactory.createTypeById(Type.CHAR));
            case PLSqlTokenTypes.USER_CONST:
                return new UserConstImpl(node);
            case PLSqlTokenTypes.EXTRACT_DATE_FUNC:
                return new ExtractFunctionImpl(node);
            case PLSqlTokenTypes.TRIM_FUNC:
                return new TrimFunctionImpl(node);
            case PLSqlTokenTypes.RANK_FUNCTION:
                return new RankFunction(node);
            case PLSqlTokenTypes.CAST_FUNC:
                return new CastExpressionImpl(node);
            case PLSqlTokenTypes.SEQUENCE_EXPR:
                return new SequenceExprImpl(node);
            case PLSqlTokenTypes.CALL_ARGUMENT:
                return new CallArgumentImpl(node);
            case PLSqlTokenTypes.INTERVAL_DAY_TO_SEC_EXPR:
                return new IntervalExpressionImpl(node);
            case PLSqlTokenTypes.ARITHMETIC_EXPR:
                return new ArithmeticExpressionImpl(node);
            case PLSqlTokenTypes.LOGICAL_EXPR:
                return new LogicalExpressionImpl(node);
            case PLSqlTokenTypes.WHERE_CONDITION:
                return new WhereConditionImpl(node);
            case PLSqlTokenTypes.SUBQUERY_CONDITION:
                return new SubqueryConditionImpl(node);
            case PLSqlTokenTypes.EXISTS_EXPR:
                return new ExistsConditionImpl(node);
            case PLSqlTokenTypes.RELATION_CONDITION:
                return new RelationConditionImpl(node);
            case PLSqlTokenTypes.IN_CONDITION:
                return new InConditionImpl(node);
            case PLSqlTokenTypes.LIKE_CONDITION:
                return new LikeConditionImpl(node);
            case PLSqlTokenTypes.BETWEEN_CONDITION:
                return new BetweenConditionImpl(node);
            case PLSqlTokenTypes.ISNULL_CONDITION:
                return new IsNullConditionImpl(node);
            case PLSqlTokenTypes.AT_TIME_ZONE_EXPR:
                return new AtTimeZoneExpressionImpl(node);

// [ === Type bindings ===]
            case PLSqlTokenTypes.TYPE_NAME_REF:
                return new TypeNameReferenceImpl(node);
            case PLSqlTokenTypes.COLUMN_TYPE_REF:
                return new ColumnTypeRefImpl(node);
            case PLSqlTokenTypes.TABLE_TYPE_REF:
                return new RowtypeTypeImpl(node);
            case PLSqlTokenTypes.DATATYPE:
                return new DataTypeImpl(node);
// [ ==================== ]

            case PLSqlTokenTypes.SEQUENCE_REF:
                return new SequenceRefImpl(node);

            case PLSqlTokenTypes.RECORD_TYPE_DECL:
                RecordTypeDecl r = new RecordTypeDeclImpl(node);
                return r;
            case PLSqlTokenTypes.TABLE_COLLECTION:
                TableCollectionDecl tc = new TableCollectionDeclImpl(node);
                return tc;
            case PLSqlTokenTypes.VARRAY_COLLECTION:
                VarrayCollectionDecl varray = new VarrayCollectionDeclImpl(node);
                return varray;
            case PLSqlTokenTypes.OBJECT_TYPE_DEF:
                ObjectTypeDecl ot = new ObjectTypeDeclImpl(node);
                return ot;
            case PLSqlTokenTypes.REF_CURSOR:
                return new RefCursorDeclImpl(node);

//            case PLSqlTokenTypes.CREATE_TYPE:
//                return new CreateTypeImpl(node);


// [ === DDL Statements ===]
            case PLSqlTokenTypes.CREATE_TABLESPACE:
                return new CreateTablespaceImpl(node);
            case PLSqlTokenTypes.DROP_TABLESPACE:
                return new DropTablespaceImpl(node);
            case PLSqlTokenTypes.ALTER_TABLESPACE:
                return new AlterTablespaceImpl(node);
            case PLSqlTokenTypes.TABLE_DEF:
                return new TableDefinitionImpl(node);
            case PLSqlTokenTypes.CREATE_TEMP_TABLE:
                return new CreateTempTableImpl(node);
            case PLSqlTokenTypes.CREATE_INDEX:
                return new CreateIndexImpl(node);
            case PLSqlTokenTypes.CREATE_VIEW:
                return new CreateViewImpl(node);
            case PLSqlTokenTypes.CREATE_VIEW_COLUMN_DEF: // IMPORTANT!!! FOR INTERNAL USE ONLY
                return new CreateViewColumnDefInternalImpl(node);
            case PLSqlTokenTypes.CREATE_SEQUENCE:
                return new CreateSequenceImpl(node);
            case PLSqlTokenTypes.CREATE_DB_LINK:
                return new CreateDbObjectGenericImpl(node, "DB LINK");
            case PLSqlTokenTypes.CREATE_SYNONYM:
                return new CreateSynonymImpl(node);

            case PLSqlTokenTypes.DROP_OPERATOR:
                return new DropDbObjectGenericImpl(node, "OPERATOR");
            case PLSqlTokenTypes.DROP_PACKAGE:
            case PLSqlTokenTypes.DROP_TYPE:
                return new DropDbObjectGenericImpl(node, "TYPE");
            case PLSqlTokenTypes.DROP_ROLE:
                return new DropDbObjectGenericImpl(node, "ROLE");
            case PLSqlTokenTypes.DROP_USER:
                return new DropDbObjectGenericImpl(node, "USER");
            case PLSqlTokenTypes.DROP_LIBRARY:
                return new DropDbObjectGenericImpl(node, "LIBRARY");
            case PLSqlTokenTypes.DROP_DB_LINK:
                return new DropDbObjectGenericImpl(node, "DB LINK");
            case PLSqlTokenTypes.DROP_SYNONYM:
                return new DropDbObjectGenericImpl(node, "SYNONYM");

            case PLSqlTokenTypes.ALTER_TABLE:
                return new AlterTableImpl(node);

            case PLSqlTokenTypes.COMMENT:
                return new CommentImpl(node);
            case PLSqlTokenTypes.RANGE_PARTITION:
                return new RangePartitionImpl(node);
            case PLSqlTokenTypes.HASH_PARTITION:
                return new HashPartitionImpl(node);
// [ ==================== ]

            case PLSqlTokenTypes.COLUMN_DEF:
                return new ColumnDefinitionImpl(node);
            case PLSqlTokenTypes.V_COLUMN_DEF:
                return new VColumnDefinitionImpl(node);
            case PLSqlTokenTypes.RECORD_ITEM:
                return new RecordTypeItemImpl(node);
            case PLSqlTokenTypes.COLUMN_SPEC_LIST:
                return new ColumnSpecListImpl(node);
            case PLSqlTokenTypes.COLUMN_SPEC:
                return new ColumnSpecImpl(node);
            case PLSqlTokenTypes.ORDER_CLAUSE:
                return new OrderByClauseImpl(node);
            case PLSqlTokenTypes.FOR_UPDATE_CLAUSE:
                return new ForUpdateClauseImpl(node);
            case PLSqlTokenTypes.GROUP_CLAUSE:
                return new GroupByClauseImpl(node);
            case PLSqlTokenTypes.LAST_STMT_RESULT_BOOL:
                return new LastStatementResultImpl(node, TypeFactory.createTypeById(Type.BOOLEAN));
            case PLSqlTokenTypes.LAST_STMT_RESULT_NUM:
                return new LastStatementResultImpl(node, TypeFactory.createTypeById(Type.NUMBER));
            case PLSqlTokenTypes.ERROR_TOKEN_A:
                return new ErrorTokenWrapperImpl(node);
            case PLSqlTokenTypes.COLLECTION_METHOD_CALL:
                return new CollectionMethodCallImpl(node);
            case PLSqlTokenTypes.COLLECTION_METHOD_CALL2:
                return new CollectionMethodCall2Impl(node);
            case PLSqlTokenTypes.C_RECORD_ITEM_REF:
                return new CRecordItemRefImpl(node);
            case PLSqlTokenTypes.SQLPLUS_PROMPT:
                return new SqlPlusPromptRem(node);
            case PLSqlTokenTypes.NUM_LOOP_INDEX:
                return new NumericLoopIndexImpl(node);
            case PLSqlTokenTypes.CURSOR_LOOP_INDEX:
                return new CursorLoopIndexImpl(node);
            case PLSqlTokenTypes.LOOP_STATEMENT:
                return new LoopStatementImpl(node);

            case PLSqlTokenTypes.CURSOR_LOOP_SPEC:
                return new CursorLoopSpecImpl(node);

            // [start] TODO -- trick - must be revised
            case PLSqlTokenTypes.COLON_OLD:
//            return new TriggerSpecColumn(node);
                return new NameFragmentRefImpl(node);
            case PLSqlTokenTypes.COLON_NEW:
//            return new TriggerSpecColumn(node);
                return new NameFragmentRefImpl(node);
            // [end] TODO -- trick - must be revised

            case PLSqlTokenTypes.CREATE_TRIGGER:
                if (node.findChildByType(PlSqlElementTypes.DML_TRIGGER_CLAUSE) != null) {
                    return new CreateTriggerDMLImpl(node);
                } else if (node.findChildByType(PlSqlElementTypes.DDL_TRIGGER_CLAUSE) != null) {
                    return new CreateTriggerDDLImpl(node);
                } else if (node.findChildByType(PlSqlElementTypes.DB_EVNT_TRIGGER_CLAUSE) != null) {
                    return new CreateTriggerDbEventImpl(node);
                } else if (node.findChildByType(PlSqlElementTypes.INSTEADOF_TRIGGER) != null) {
                    // todo - workaround for now
                    return new CreateTriggerGenericImpl(node);
                } else {
                    // todo - workaround for now
                    return new CreateTriggerGenericImpl(node);
                }
            case PLSqlTokenTypes.SQLPLUS_SET:
            case PLSqlTokenTypes.SQLPLUS_SHOW:
            case PLSqlTokenTypes.SQLPLUS_VARIABLE:
            case PLSqlTokenTypes.SQLPLUS_COLUMN:
            case PLSqlTokenTypes.SQLPLUS_EXEC:
            case PLSqlTokenTypes.SQLPLUS_WHENEVER:
            case PLSqlTokenTypes.SQLPLUS_DEFINE:
            case PLSqlTokenTypes.SQLPLUS_EXIT:
            case PLSqlTokenTypes.SQLPLUS_QUIT:
            case PLSqlTokenTypes.SQLPLUS_SPOOL:
            case PLSqlTokenTypes.SQLPLUS_RUNSCRIPT:
            case PLSqlTokenTypes.SQLPLUS_START:
            case PLSqlTokenTypes.SQLPLUS_SERVEROUTPUT:
            case PLSqlTokenTypes.SQLPLUS_REPFOOTER:
            case PLSqlTokenTypes.SQLPLUS_REPHEADER:
                return new SqlPlusCommandImpl(node);

            case PLSqlTokenTypes.COMMIT_STATEMENT:
                return new CommitStatementImpl(node);
            case PLSqlTokenTypes.ROLLBACK_STATEMENT:
                return new RollbackStatementImpl(node);
        }
        return null;
    }

}
