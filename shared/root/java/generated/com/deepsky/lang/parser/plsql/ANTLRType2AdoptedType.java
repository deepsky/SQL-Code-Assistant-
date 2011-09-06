package com.deepsky.lang.parser.plsql;

import com.intellij.psi.tree.IElementType;
import com.deepsky.generated.plsql.PLSqlTokenTypes;

public class ANTLRType2AdoptedType {
	static public IElementType[] type2etype = new IElementType[355];
	static {
		type2etype[PLSqlTokenTypes.DROP_VIEW] = PLSqlTypesAdopted.DROP_VIEW;
		type2etype[PLSqlTokenTypes.CREATE_VIEW_COLUMN_DEF] = PLSqlTypesAdopted.CREATE_VIEW_COLUMN_DEF;
		type2etype[PLSqlTokenTypes.PROCEDURE_SPEC] = PLSqlTypesAdopted.PROCEDURE_SPEC;
		type2etype[PLSqlTokenTypes.MERGE_COMMAND] = PLSqlTypesAdopted.MERGE_COMMAND;
		type2etype[PLSqlTokenTypes.DML_TRIGGER_CLAUSE] = PLSqlTypesAdopted.DML_TRIGGER_CLAUSE;
		type2etype[PLSqlTokenTypes.DROP_DB_LINK] = PLSqlTypesAdopted.DROP_DB_LINK;
		type2etype[PLSqlTokenTypes.SQLPLUS_EXIT] = PLSqlTypesAdopted.SQLPLUS_EXIT;
		type2etype[PLSqlTokenTypes.HEAP_ORGINIZED] = PLSqlTypesAdopted.HEAP_ORGINIZED;
		type2etype[PLSqlTokenTypes.SYNONYM_OBJ] = PLSqlTypesAdopted.SYNONYM_OBJ;
		type2etype[PLSqlTokenTypes.SQLPLUS_VARIABLE] = PLSqlTypesAdopted.SQLPLUS_VARIABLE;
		type2etype[PLSqlTokenTypes.SELECT_EXPRESSION] = PLSqlTypesAdopted.SELECT_EXPRESSION;
		type2etype[PLSqlTokenTypes.DROP_PACKAGE] = PLSqlTypesAdopted.DROP_PACKAGE;
		type2etype[PLSqlTokenTypes.FUNCTION_SPEC] = PLSqlTypesAdopted.FUNCTION_SPEC;
		type2etype[PLSqlTokenTypes.OBJECT_NAME] = PLSqlTypesAdopted.OBJECT_NAME;
		type2etype[PLSqlTokenTypes.STRING_LITERAL] = PLSqlTypesAdopted.STRING_LITERAL;
		type2etype[PLSqlTokenTypes.EXCEPTION_SECTION] = PLSqlTypesAdopted.EXCEPTION_SECTION;
		type2etype[PLSqlTokenTypes.DROP_SEQUENCE] = PLSqlTypesAdopted.DROP_SEQUENCE;
		type2etype[PLSqlTokenTypes.FORALL_LOOP_SPEC] = PLSqlTypesAdopted.FORALL_LOOP_SPEC;
		type2etype[PLSqlTokenTypes.SYSTIMESTAMP_CONST] = PLSqlTypesAdopted.SYSTIMESTAMP_CONST;
		type2etype[PLSqlTokenTypes.MEMBER_OF] = PLSqlTypesAdopted.MEMBER_OF;
		type2etype[PLSqlTokenTypes.TABLE_NAME_DDL] = PLSqlTypesAdopted.TABLE_NAME_DDL;
		type2etype[PLSqlTokenTypes.DROP_TRIGGER] = PLSqlTypesAdopted.DROP_TRIGGER;
		type2etype[PLSqlTokenTypes.PLSQL_VAR_REF] = PLSqlTypesAdopted.PLSQL_VAR_REF;
		type2etype[PLSqlTokenTypes.LAG_FUNCTION] = PLSqlTypesAdopted.LAG_FUNCTION;
		type2etype[PLSqlTokenTypes.CREATE_TRIGGER] = PLSqlTypesAdopted.CREATE_TRIGGER;
		type2etype[PLSqlTokenTypes.PARTITION_SPEC] = PLSqlTypesAdopted.PARTITION_SPEC;
		type2etype[PLSqlTokenTypes.SERIALLY_REUSABLE_PRAGMA] = PLSqlTypesAdopted.SERIALLY_REUSABLE_PRAGMA;
		type2etype[PLSqlTokenTypes.TRUNCATE_TABLE] = PLSqlTypesAdopted.TRUNCATE_TABLE;
		type2etype[PLSqlTokenTypes.COLUMN_OUTER_JOIN] = PLSqlTypesAdopted.COLUMN_OUTER_JOIN;
		type2etype[PLSqlTokenTypes.FROM_SUBQUERY] = PLSqlTypesAdopted.FROM_SUBQUERY;
		type2etype[PLSqlTokenTypes.SQLPLUS_PROMPT] = PLSqlTypesAdopted.SQLPLUS_PROMPT;
		type2etype[PLSqlTokenTypes.SQLPLUS_DEFINE] = PLSqlTypesAdopted.SQLPLUS_DEFINE;
		type2etype[PLSqlTokenTypes.CURSOR_DECLARATION] = PLSqlTypesAdopted.CURSOR_DECLARATION;
		type2etype[PLSqlTokenTypes.ELSIF_STATEMENT] = PLSqlTypesAdopted.ELSIF_STATEMENT;
		type2etype[PLSqlTokenTypes.WHERE_CONDITION] = PLSqlTypesAdopted.WHERE_CONDITION;
		type2etype[PLSqlTokenTypes.BETWEEN_CONDITION] = PLSqlTypesAdopted.BETWEEN_CONDITION;
		type2etype[PLSqlTokenTypes.CUSTOM_AGGR_FUNCTION] = PLSqlTypesAdopted.CUSTOM_AGGR_FUNCTION;
		type2etype[PLSqlTokenTypes.NOT_NULL_STMT] = PLSqlTypesAdopted.NOT_NULL_STMT;
		type2etype[PLSqlTokenTypes.PARAMETER_NAME] = PLSqlTypesAdopted.PARAMETER_NAME;
		type2etype[PLSqlTokenTypes.RANGE_PARTITION] = PLSqlTypesAdopted.RANGE_PARTITION;
		type2etype[PLSqlTokenTypes.CURSOR_STATE] = PLSqlTypesAdopted.CURSOR_STATE;
		type2etype[PLSqlTokenTypes.SORTED_DEF] = PLSqlTypesAdopted.SORTED_DEF;
		type2etype[PLSqlTokenTypes.VAR_REF] = PLSqlTypesAdopted.VAR_REF;
		type2etype[PLSqlTokenTypes.CONDITIONAL_COMPILATION] = PLSqlTypesAdopted.CONDITIONAL_COMPILATION;
		type2etype[PLSqlTokenTypes.DROP_ROLE] = PLSqlTypesAdopted.DROP_ROLE;
		type2etype[PLSqlTokenTypes.CURSOR_LOOP_SPEC] = PLSqlTypesAdopted.CURSOR_LOOP_SPEC;
		type2etype[PLSqlTokenTypes.EXPR_COLUMN] = PLSqlTypesAdopted.EXPR_COLUMN;
		type2etype[PLSqlTokenTypes.TRIGGER_TARGET] = PLSqlTypesAdopted.TRIGGER_TARGET;
		type2etype[PLSqlTokenTypes.RANK_FUNCTION] = PLSqlTypesAdopted.RANK_FUNCTION;
		type2etype[PLSqlTokenTypes.REF_CURSOR] = PLSqlTypesAdopted.REF_CURSOR;
		type2etype[PLSqlTokenTypes.DROP_FUNCTION] = PLSqlTypesAdopted.DROP_FUNCTION;
		type2etype[PLSqlTokenTypes.IF_STATEMENT] = PLSqlTypesAdopted.IF_STATEMENT;
		type2etype[PLSqlTokenTypes.LEAD_FUNCTION] = PLSqlTypesAdopted.LEAD_FUNCTION;
		type2etype[PLSqlTokenTypes.TABLE_FUNCTION] = PLSqlTypesAdopted.TABLE_FUNCTION;
		type2etype[PLSqlTokenTypes.PARENTHESIZED_EXPR] = PLSqlTypesAdopted.PARENTHESIZED_EXPR;
		type2etype[PLSqlTokenTypes.ERROR_TOKEN_A] = PLSqlTypesAdopted.ERROR_TOKEN_A;
		type2etype[PLSqlTokenTypes.EXCEPTION_PRAGMA] = PLSqlTypesAdopted.EXCEPTION_PRAGMA;
		type2etype[PLSqlTokenTypes.COND_COMP_ERROR] = PLSqlTypesAdopted.COND_COMP_ERROR;
		type2etype[PLSqlTokenTypes.DROP_INDEX] = PLSqlTypesAdopted.DROP_INDEX;
		type2etype[PLSqlTokenTypes.PARAMETER_SPEC] = PLSqlTypesAdopted.PARAMETER_SPEC;
		type2etype[PLSqlTokenTypes.EXTERNAL_TYPE] = PLSqlTypesAdopted.EXTERNAL_TYPE;
		type2etype[PLSqlTokenTypes.TABLE_REF] = PLSqlTypesAdopted.TABLE_REF;
		type2etype[PLSqlTokenTypes.CALLABLE_NAME_REF] = PLSqlTypesAdopted.CALLABLE_NAME_REF;
		type2etype[PLSqlTokenTypes.CURSOR_REF_LOOP_SPEC] = PLSqlTypesAdopted.CURSOR_REF_LOOP_SPEC;
		type2etype[PLSqlTokenTypes.TIMEZONE_SPEC] = PLSqlTypesAdopted.TIMEZONE_SPEC;
		type2etype[PLSqlTokenTypes.SQLPLUS_COLUMN] = PLSqlTypesAdopted.SQLPLUS_COLUMN;
		type2etype[PLSqlTokenTypes.RELATION_CONDITION] = PLSqlTypesAdopted.RELATION_CONDITION;
		type2etype[PLSqlTokenTypes.SELECT_EXPRESSION_UNION] = PLSqlTypesAdopted.SELECT_EXPRESSION_UNION;
		type2etype[PLSqlTokenTypes.DATATYPE] = PLSqlTypesAdopted.DATATYPE;
		type2etype[PLSqlTokenTypes.ELSE_STATEMENT] = PLSqlTypesAdopted.ELSE_STATEMENT;
		type2etype[PLSqlTokenTypes.NUMERIC_LOOP_SPEC] = PLSqlTypesAdopted.NUMERIC_LOOP_SPEC;
		type2etype[PLSqlTokenTypes.PLSQL_EXPR_LIST_USING] = PLSqlTypesAdopted.PLSQL_EXPR_LIST_USING;
		type2etype[PLSqlTokenTypes.FUNCTION_CALL] = PLSqlTypesAdopted.FUNCTION_CALL;
		type2etype[PLSqlTokenTypes.CONSTRAINT] = PLSqlTypesAdopted.CONSTRAINT;
		type2etype[PLSqlTokenTypes.INTERFACE_PRAGMA] = PLSqlTypesAdopted.INTERFACE_PRAGMA;
		type2etype[PLSqlTokenTypes.PARAMETER_REF] = PLSqlTypesAdopted.PARAMETER_REF;
		type2etype[PLSqlTokenTypes.SQLPLUS_REPFOOTER] = PLSqlTypesAdopted.SQLPLUS_REPFOOTER;
		type2etype[PLSqlTokenTypes.DROP_LIBRARY] = PLSqlTypesAdopted.DROP_LIBRARY;
		type2etype[PLSqlTokenTypes.TABLE_REFERENCE_LIST] = PLSqlTypesAdopted.TABLE_REFERENCE_LIST;
		type2etype[PLSqlTokenTypes.COND_COMP_SEQ] = PLSqlTypesAdopted.COND_COMP_SEQ;
		type2etype[PLSqlTokenTypes.NUMERIC_LITERAL] = PLSqlTypesAdopted.NUMERIC_LITERAL;
		type2etype[PLSqlTokenTypes.V_COLUMN_DEF] = PLSqlTypesAdopted.V_COLUMN_DEF;
		type2etype[PLSqlTokenTypes.EXCEPTION_DECL] = PLSqlTypesAdopted.EXCEPTION_DECL;
		type2etype[PLSqlTokenTypes.FETCH_STATEMENT] = PLSqlTypesAdopted.FETCH_STATEMENT;
		type2etype[PLSqlTokenTypes.NUM_LOOP_INDEX] = PLSqlTypesAdopted.NUM_LOOP_INDEX;
		type2etype[PLSqlTokenTypes.C_RECORD_ITEM_REF] = PLSqlTypesAdopted.C_RECORD_ITEM_REF;
		type2etype[PLSqlTokenTypes.DROP_DIRECTORY] = PLSqlTypesAdopted.DROP_DIRECTORY;
		type2etype[PLSqlTokenTypes.GOTO_STATEMENT] = PLSqlTypesAdopted.GOTO_STATEMENT;
		type2etype[PLSqlTokenTypes.FK_SPEC] = PLSqlTypesAdopted.FK_SPEC;
		type2etype[PLSqlTokenTypes.SPEC_CALL_ARGUMENT_LIST] = PLSqlTypesAdopted.SPEC_CALL_ARGUMENT_LIST;
		type2etype[PLSqlTokenTypes.BIND_VAR] = PLSqlTypesAdopted.BIND_VAR;
		type2etype[PLSqlTokenTypes.SUBQUERY_UPDATE_COMMAND] = PLSqlTypesAdopted.SUBQUERY_UPDATE_COMMAND;
		type2etype[PLSqlTokenTypes.INSERT_COMMAND] = PLSqlTypesAdopted.INSERT_COMMAND;
		type2etype[PLSqlTokenTypes.SQLPLUS_EXEC] = PLSqlTypesAdopted.SQLPLUS_EXEC;
		type2etype[PLSqlTokenTypes.LOGICAL_EXPR] = PLSqlTypesAdopted.LOGICAL_EXPR;
		type2etype[PLSqlTokenTypes.SUBQUERY] = PLSqlTypesAdopted.SUBQUERY;
		type2etype[PLSqlTokenTypes.ALIAS_NAME] = PLSqlTypesAdopted.ALIAS_NAME;
		type2etype[PLSqlTokenTypes.CREATE_SYNONYM] = PLSqlTypesAdopted.CREATE_SYNONYM;
		type2etype[PLSqlTokenTypes.SQLPLUS_WHENEVER] = PLSqlTypesAdopted.SQLPLUS_WHENEVER;
		type2etype[PLSqlTokenTypes.COLUMN_PK_SPEC] = PLSqlTypesAdopted.COLUMN_PK_SPEC;
		type2etype[PLSqlTokenTypes.ASTERISK_COLUMN] = PLSqlTypesAdopted.ASTERISK_COLUMN;
		type2etype[PLSqlTokenTypes.ROWCOUNT_EXRESSION] = PLSqlTypesAdopted.ROWCOUNT_EXRESSION;
		type2etype[PLSqlTokenTypes.DROP_USER] = PLSqlTypesAdopted.DROP_USER;
		type2etype[PLSqlTokenTypes.SYNONYM_NAME] = PLSqlTypesAdopted.SYNONYM_NAME;
		type2etype[PLSqlTokenTypes.SQLPLUS_RUNSCRIPT] = PLSqlTypesAdopted.SQLPLUS_RUNSCRIPT;
		type2etype[PLSqlTokenTypes.FOR_UPDATE_CLAUSE] = PLSqlTypesAdopted.FOR_UPDATE_CLAUSE;
		type2etype[PLSqlTokenTypes.SUBTYPE_DECL] = PLSqlTypesAdopted.SUBTYPE_DECL;
		type2etype[PLSqlTokenTypes.RAISE_STATEMENT] = PLSqlTypesAdopted.RAISE_STATEMENT;
		type2etype[PLSqlTokenTypes.SEQUENCE_EXPR] = PLSqlTypesAdopted.SEQUENCE_EXPR;
		type2etype[PLSqlTokenTypes.RETURN_TYPE] = PLSqlTypesAdopted.RETURN_TYPE;
		type2etype[PLSqlTokenTypes.COMMIT_STATEMENT] = PLSqlTypesAdopted.COMMIT_STATEMENT;
		type2etype[PLSqlTokenTypes.SIMPLE_UPDATE_COMMAND] = PLSqlTypesAdopted.SIMPLE_UPDATE_COMMAND;
		type2etype[PLSqlTokenTypes.HASH_PARTITION] = PLSqlTypesAdopted.HASH_PARTITION;
		type2etype[PLSqlTokenTypes.FROM_PLAIN_TABLE] = PLSqlTypesAdopted.FROM_PLAIN_TABLE;
		type2etype[PLSqlTokenTypes.PK_SPEC] = PLSqlTypesAdopted.PK_SPEC;
		type2etype[PLSqlTokenTypes.SCHEMA_NAME] = PLSqlTypesAdopted.SCHEMA_NAME;
		type2etype[PLSqlTokenTypes.AT_TIME_ZONE_EXPR] = PLSqlTypesAdopted.AT_TIME_ZONE_EXPR;
		type2etype[PLSqlTokenTypes.ISNULL_CONDITION] = PLSqlTypesAdopted.ISNULL_CONDITION;
		type2etype[PLSqlTokenTypes.SQLPLUS_QUIT] = PLSqlTypesAdopted.SQLPLUS_QUIT;
		type2etype[PLSqlTokenTypes.INDEX_NAME] = PLSqlTypesAdopted.INDEX_NAME;
		type2etype[PLSqlTokenTypes.SQLPLUS_COMMAND] = PLSqlTypesAdopted.SQLPLUS_COMMAND;
		type2etype[PLSqlTokenTypes.SQLPLUS_SPOOL] = PLSqlTypesAdopted.SQLPLUS_SPOOL;
		type2etype[PLSqlTokenTypes.CAST_FUNC] = PLSqlTypesAdopted.CAST_FUNC;
		type2etype[PLSqlTokenTypes.BOOLEAN_LITERAL] = PLSqlTypesAdopted.BOOLEAN_LITERAL;
		type2etype[PLSqlTokenTypes.IN_CONDITION] = PLSqlTypesAdopted.IN_CONDITION;
		type2etype[PLSqlTokenTypes.SUBQUERY_EXPR] = PLSqlTypesAdopted.SUBQUERY_EXPR;
		type2etype[PLSqlTokenTypes.DBTIMEZONE] = PLSqlTypesAdopted.DBTIMEZONE;
		type2etype[PLSqlTokenTypes.VARRAY_COLLECTION] = PLSqlTypesAdopted.VARRAY_COLLECTION;
		type2etype[PLSqlTokenTypes.SYNONYM_OBJ_WITH_LINK] = PLSqlTypesAdopted.SYNONYM_OBJ_WITH_LINK;
		type2etype[PLSqlTokenTypes.SUBQUERY_CONDITION] = PLSqlTypesAdopted.SUBQUERY_CONDITION;
		type2etype[PLSqlTokenTypes.COLUMN_TYPE_REF] = PLSqlTypesAdopted.COLUMN_TYPE_REF;
		type2etype[PLSqlTokenTypes.COLLECTION_METHOD_CALL] = PLSqlTypesAdopted.COLLECTION_METHOD_CALL;
		type2etype[PLSqlTokenTypes.INSTEADOF_TRIGGER] = PLSqlTypesAdopted.INSTEADOF_TRIGGER;
		type2etype[PLSqlTokenTypes.TIMESTAMP_CONST] = PLSqlTypesAdopted.TIMESTAMP_CONST;
		type2etype[PLSqlTokenTypes.LAST_STMT_RESULT_BOOL] = PLSqlTypesAdopted.LAST_STMT_RESULT_BOOL;
		type2etype[PLSqlTokenTypes.CALL_ARGUMENT] = PLSqlTypesAdopted.CALL_ARGUMENT;
		type2etype[PLSqlTokenTypes.ANSI_JOIN_TAB_SPEC] = PLSqlTypesAdopted.ANSI_JOIN_TAB_SPEC;
		type2etype[PLSqlTokenTypes.TABLE_COLLECTION] = PLSqlTypesAdopted.TABLE_COLLECTION;
		type2etype[PLSqlTokenTypes.LAST_STMT_RESULT_NUM] = PLSqlTypesAdopted.LAST_STMT_RESULT_NUM;
		type2etype[PLSqlTokenTypes.UPDATE_COMMAND] = PLSqlTypesAdopted.UPDATE_COMMAND;
		type2etype[PLSqlTokenTypes.TRIM_FUNC] = PLSqlTypesAdopted.TRIM_FUNC;
		type2etype[PLSqlTokenTypes.CONSTRAINT_NAME] = PLSqlTypesAdopted.CONSTRAINT_NAME;
		type2etype[PLSqlTokenTypes.ORDER_CLAUSE] = PLSqlTypesAdopted.ORDER_CLAUSE;
		type2etype[PLSqlTokenTypes.FUNCTION_BODY] = PLSqlTypesAdopted.FUNCTION_BODY;
		type2etype[PLSqlTokenTypes.COLUMN_SPEC_LIST] = PLSqlTypesAdopted.COLUMN_SPEC_LIST;
		type2etype[PLSqlTokenTypes.PACKAGE_INIT_SECTION] = PLSqlTypesAdopted.PACKAGE_INIT_SECTION;
		type2etype[PLSqlTokenTypes.ASSIGNMENT_STATEMENT] = PLSqlTypesAdopted.ASSIGNMENT_STATEMENT;
		type2etype[PLSqlTokenTypes.IDENT_ASTERISK_COLUMN] = PLSqlTypesAdopted.IDENT_ASTERISK_COLUMN;
		type2etype[PLSqlTokenTypes.OBJECT_TYPE_DEF] = PLSqlTypesAdopted.OBJECT_TYPE_DEF;
		type2etype[PLSqlTokenTypes.SQLCODE_SYSVAR] = PLSqlTypesAdopted.SQLCODE_SYSVAR;
		type2etype[PLSqlTokenTypes.LOOP_STATEMENT] = PLSqlTypesAdopted.LOOP_STATEMENT;
		type2etype[PLSqlTokenTypes.SQLPLUS_START] = PLSqlTypesAdopted.SQLPLUS_START;
		type2etype[PLSqlTokenTypes.CURSOR_LOOP_INDEX] = PLSqlTypesAdopted.CURSOR_LOOP_INDEX;
		type2etype[PLSqlTokenTypes.INTERVAL_YEAR_TO_MONTH_EXPR] = PLSqlTypesAdopted.INTERVAL_YEAR_TO_MONTH_EXPR;
		type2etype[PLSqlTokenTypes.DROP_PROCEDURE] = PLSqlTypesAdopted.DROP_PROCEDURE;
		type2etype[PLSqlTokenTypes.LABEL_NAME] = PLSqlTypesAdopted.LABEL_NAME;
		type2etype[PLSqlTokenTypes.COLLECTION_METHOD_CALL2] = PLSqlTypesAdopted.COLLECTION_METHOD_CALL2;
		type2etype[PLSqlTokenTypes.COLUMN_SPEC] = PLSqlTypesAdopted.COLUMN_SPEC;
		type2etype[PLSqlTokenTypes.PACKAGE_BODY] = PLSqlTypesAdopted.PACKAGE_BODY;
		type2etype[PLSqlTokenTypes.SEQUENCE_REF] = PLSqlTypesAdopted.SEQUENCE_REF;
		type2etype[PLSqlTokenTypes.VARIABLE_DECLARATION] = PLSqlTypesAdopted.VARIABLE_DECLARATION;
		type2etype[PLSqlTokenTypes.SQLPLUS_SHOW] = PLSqlTypesAdopted.SQLPLUS_SHOW;
		type2etype[PLSqlTokenTypes.TYPE_NAME_REF] = PLSqlTypesAdopted.TYPE_NAME_REF;
		type2etype[PLSqlTokenTypes.ROWID] = PLSqlTypesAdopted.ROWID;
		type2etype[PLSqlTokenTypes.PROCEDURE_CALL] = PLSqlTypesAdopted.PROCEDURE_CALL;
		type2etype[PLSqlTokenTypes.EXTRACT_DATE_FUNC] = PLSqlTypesAdopted.EXTRACT_DATE_FUNC;
		type2etype[PLSqlTokenTypes.CURRENT_OF_CLAUSE] = PLSqlTypesAdopted.CURRENT_OF_CLAUSE;
		type2etype[PLSqlTokenTypes.DATATYPE_PARAM_INFO] = PLSqlTypesAdopted.DATATYPE_PARAM_INFO;
		type2etype[PLSqlTokenTypes.ROWNUM] = PLSqlTypesAdopted.ROWNUM;
		type2etype[PLSqlTokenTypes.COMMENT] = PLSqlTypesAdopted.COMMENT;
		type2etype[PLSqlTokenTypes.EXEC_NAME_REF] = PLSqlTypesAdopted.EXEC_NAME_REF;
		type2etype[PLSqlTokenTypes.TABLE_REF_WITH_LINK] = PLSqlTypesAdopted.TABLE_REF_WITH_LINK;
		type2etype[PLSqlTokenTypes.COLUMN_CHECK_CONSTRAINT] = PLSqlTypesAdopted.COLUMN_CHECK_CONSTRAINT;
		type2etype[PLSqlTokenTypes.CREATE_TEMP_TABLE] = PLSqlTypesAdopted.CREATE_TEMP_TABLE;
		type2etype[PLSqlTokenTypes.FIELD_NAME] = PLSqlTypesAdopted.FIELD_NAME;
		type2etype[PLSqlTokenTypes.RECORD_ITEM] = PLSqlTypesAdopted.RECORD_ITEM;
		type2etype[PLSqlTokenTypes.OWNER_COLUMN_NAME_LIST] = PLSqlTypesAdopted.OWNER_COLUMN_NAME_LIST;
		type2etype[PLSqlTokenTypes.TABLE_TYPE_REF] = PLSqlTypesAdopted.TABLE_TYPE_REF;
		type2etype[PLSqlTokenTypes.DELETE_COMMAND] = PLSqlTypesAdopted.DELETE_COMMAND;
		type2etype[PLSqlTokenTypes.COLLECTION_METHOD_NAME] = PLSqlTypesAdopted.COLLECTION_METHOD_NAME;
		type2etype[PLSqlTokenTypes.TIMESTAMPG_PRAGMA] = PLSqlTypesAdopted.TIMESTAMPG_PRAGMA;
		type2etype[PLSqlTokenTypes.COLUMN_FK_SPEC] = PLSqlTypesAdopted.COLUMN_FK_SPEC;
		type2etype[PLSqlTokenTypes.INTERVAL_DAY_TO_SEC_EXPR] = PLSqlTypesAdopted.INTERVAL_DAY_TO_SEC_EXPR;
		type2etype[PLSqlTokenTypes.TRIGGER_FOR_EACH] = PLSqlTypesAdopted.TRIGGER_FOR_EACH;
		type2etype[PLSqlTokenTypes.COMPLEX_NAME] = PLSqlTypesAdopted.COMPLEX_NAME;
		type2etype[PLSqlTokenTypes.TRIGGER_COLUMN_REF] = PLSqlTypesAdopted.TRIGGER_COLUMN_REF;
		type2etype[PLSqlTokenTypes.TABLE_ALIAS] = PLSqlTypesAdopted.TABLE_ALIAS;
		type2etype[PLSqlTokenTypes.PACKAGE_SPEC] = PLSqlTypesAdopted.PACKAGE_SPEC;
		type2etype[PLSqlTokenTypes.PLSQL_BLOCK] = PLSqlTypesAdopted.PLSQL_BLOCK;
		type2etype[PLSqlTokenTypes.CASE_STATEMENT] = PLSqlTypesAdopted.CASE_STATEMENT;
		type2etype[PLSqlTokenTypes.LIKE_CONDITION] = PLSqlTypesAdopted.LIKE_CONDITION;
		type2etype[PLSqlTokenTypes.SYSDATE_CONST] = PLSqlTypesAdopted.SYSDATE_CONST;
		type2etype[PLSqlTokenTypes.SQLERRM_SYSVAR] = PLSqlTypesAdopted.SQLERRM_SYSVAR;
		type2etype[PLSqlTokenTypes.VIEW_NAME_DDL] = PLSqlTypesAdopted.VIEW_NAME_DDL;
		type2etype[PLSqlTokenTypes.MERGE_WHEN_COMMAND] = PLSqlTypesAdopted.MERGE_WHEN_COMMAND;
		type2etype[PLSqlTokenTypes.TABLE_REFERENCE_LIST_FROM] = PLSqlTypesAdopted.TABLE_REFERENCE_LIST_FROM;
		type2etype[PLSqlTokenTypes.ALTER_TRIGGER] = PLSqlTypesAdopted.ALTER_TRIGGER;
		type2etype[PLSqlTokenTypes.SQLPLUS_SET] = PLSqlTypesAdopted.SQLPLUS_SET;
		type2etype[PLSqlTokenTypes.ALTER_TABLE] = PLSqlTypesAdopted.ALTER_TABLE;
		type2etype[PLSqlTokenTypes.COLUMN_DEF] = PLSqlTypesAdopted.COLUMN_DEF;
		type2etype[PLSqlTokenTypes.COLUMN_NAME_LIST] = PLSqlTypesAdopted.COLUMN_NAME_LIST;
		type2etype[PLSqlTokenTypes.ALIAS_IDENT] = PLSqlTypesAdopted.ALIAS_IDENT;
		type2etype[PLSqlTokenTypes.COLUMN_NAME_DDL] = PLSqlTypesAdopted.COLUMN_NAME_DDL;
		type2etype[PLSqlTokenTypes.COLUMN_NAME_REF] = PLSqlTypesAdopted.COLUMN_NAME_REF;
		type2etype[PLSqlTokenTypes.TRIGGER_NAME] = PLSqlTypesAdopted.TRIGGER_NAME;
		type2etype[PLSqlTokenTypes.ALTER_GENERIC] = PLSqlTypesAdopted.ALTER_GENERIC;
		type2etype[PLSqlTokenTypes.EXIT_STATEMENT] = PLSqlTypesAdopted.EXIT_STATEMENT;
		type2etype[PLSqlTokenTypes.TYPE_SPEC] = PLSqlTypesAdopted.TYPE_SPEC;
		type2etype[PLSqlTokenTypes.DROP_OPERATOR] = PLSqlTypesAdopted.DROP_OPERATOR;
		type2etype[PLSqlTokenTypes.PRAGMA] = PLSqlTypesAdopted.PRAGMA;
		type2etype[PLSqlTokenTypes.INTO_CLAUSE] = PLSqlTypesAdopted.INTO_CLAUSE;
		type2etype[PLSqlTokenTypes.ROLLBACK_STATEMENT] = PLSqlTypesAdopted.ROLLBACK_STATEMENT;
		type2etype[PLSqlTokenTypes.INTERVAL_CONST] = PLSqlTypesAdopted.INTERVAL_CONST;
		type2etype[PLSqlTokenTypes.RELATION_OP] = PLSqlTypesAdopted.RELATION_OP;
		type2etype[PLSqlTokenTypes.CURSOR_NAME] = PLSqlTypesAdopted.CURSOR_NAME;
		type2etype[PLSqlTokenTypes.CALL_ARGUMENT_LIST] = PLSqlTypesAdopted.CALL_ARGUMENT_LIST;
		type2etype[PLSqlTokenTypes.CHARACTER_SET] = PLSqlTypesAdopted.CHARACTER_SET;
		type2etype[PLSqlTokenTypes.RETURNING_CLAUSE] = PLSqlTypesAdopted.RETURNING_CLAUSE;
		type2etype[PLSqlTokenTypes.INSERT_INTO_SUBQUERY_COMMAND] = PLSqlTypesAdopted.INSERT_INTO_SUBQUERY_COMMAND;
		type2etype[PLSqlTokenTypes.UNIQUE_CONSTRAINT] = PLSqlTypesAdopted.UNIQUE_CONSTRAINT;
		type2etype[PLSqlTokenTypes.EXPR_LIST] = PLSqlTypesAdopted.EXPR_LIST;
		type2etype[PLSqlTokenTypes.VARIABLE_NAME] = PLSqlTypesAdopted.VARIABLE_NAME;
		type2etype[PLSqlTokenTypes.CREATE_SEQUENCE] = PLSqlTypesAdopted.CREATE_SEQUENCE;
		type2etype[PLSqlTokenTypes.RETURN_STATEMENT] = PLSqlTypesAdopted.RETURN_STATEMENT;
		type2etype[PLSqlTokenTypes.DROP_TYPE] = PLSqlTypesAdopted.DROP_TYPE;
		type2etype[PLSqlTokenTypes.SQLPLUS_REPHEADER] = PLSqlTypesAdopted.SQLPLUS_REPHEADER;
		type2etype[PLSqlTokenTypes.DB_EVNT_TRIGGER_CLAUSE] = PLSqlTypesAdopted.DB_EVNT_TRIGGER_CLAUSE;
		type2etype[PLSqlTokenTypes.TYPE_NAME] = PLSqlTypesAdopted.TYPE_NAME;
		type2etype[PLSqlTokenTypes.FIPSFLAG_PRAGMA] = PLSqlTypesAdopted.FIPSFLAG_PRAGMA;
		type2etype[PLSqlTokenTypes.ARITHMETIC_EXPR] = PLSqlTypesAdopted.ARITHMETIC_EXPR;
		type2etype[PLSqlTokenTypes.ARGUMENT_LIST] = PLSqlTypesAdopted.ARGUMENT_LIST;
		type2etype[PLSqlTokenTypes.DROP_SYNONYM] = PLSqlTypesAdopted.DROP_SYNONYM;
		type2etype[PLSqlTokenTypes.NAME_FRAGMENT] = PLSqlTypesAdopted.NAME_FRAGMENT;
		type2etype[PLSqlTokenTypes.PROCEDURE_BODY] = PLSqlTypesAdopted.PROCEDURE_BODY;
		type2etype[PLSqlTokenTypes.RESTRICT_REF_PRAGMA] = PLSqlTypesAdopted.RESTRICT_REF_PRAGMA;
		type2etype[PLSqlTokenTypes.CASE_EXPRESSION_SRCH] = PLSqlTypesAdopted.CASE_EXPRESSION_SRCH;
		type2etype[PLSqlTokenTypes.CASE_EXPRESSION_SMPL] = PLSqlTypesAdopted.CASE_EXPRESSION_SMPL;
		type2etype[PLSqlTokenTypes.CREATE_VIEW] = PLSqlTypesAdopted.CREATE_VIEW;
		type2etype[PLSqlTokenTypes.DECLARE_LIST] = PLSqlTypesAdopted.DECLARE_LIST;
		type2etype[PLSqlTokenTypes.DDL_TRIGGER_CLAUSE] = PLSqlTypesAdopted.DDL_TRIGGER_CLAUSE;
		type2etype[PLSqlTokenTypes.COUNT_FUNC] = PLSqlTypesAdopted.COUNT_FUNC;
		type2etype[PLSqlTokenTypes.TABLE_DEF] = PLSqlTypesAdopted.TABLE_DEF;
		type2etype[PLSqlTokenTypes.RECORD_TYPE_DECL] = PLSqlTypesAdopted.RECORD_TYPE_DECL;
		type2etype[PLSqlTokenTypes.GROUP_CLAUSE] = PLSqlTypesAdopted.GROUP_CLAUSE;
		type2etype[PLSqlTokenTypes.EXISTS_EXPR] = PLSqlTypesAdopted.EXISTS_EXPR;
		type2etype[PLSqlTokenTypes.COND_COMP_SEQ2] = PLSqlTypesAdopted.COND_COMP_SEQ2;
		type2etype[PLSqlTokenTypes.DEFAULT] = PLSqlTypesAdopted.DEFAULT;
		type2etype[PLSqlTokenTypes.NULL_STATEMENT] = PLSqlTypesAdopted.NULL_STATEMENT;
		type2etype[PLSqlTokenTypes.STATEMENT_LIST] = PLSqlTypesAdopted.STATEMENT_LIST;
		type2etype[PLSqlTokenTypes.BUILTIN_PRAGMA] = PLSqlTypesAdopted.BUILTIN_PRAGMA;
		type2etype[PLSqlTokenTypes.USER_CONST] = PLSqlTypesAdopted.USER_CONST;
		type2etype[PLSqlTokenTypes.PACKAGE_NAME] = PLSqlTypesAdopted.PACKAGE_NAME;
		type2etype[PLSqlTokenTypes.IOT_TYPE] = PLSqlTypesAdopted.IOT_TYPE;
		type2etype[PLSqlTokenTypes.CURRENT_TIMESTAMP_CONST] = PLSqlTypesAdopted.CURRENT_TIMESTAMP_CONST;
		type2etype[PLSqlTokenTypes.CREATE_INDEX] = PLSqlTypesAdopted.CREATE_INDEX;
		type2etype[PLSqlTokenTypes.RECORD_ITEM_NAME] = PLSqlTypesAdopted.RECORD_ITEM_NAME;
		type2etype[PLSqlTokenTypes.IMMEDIATE_COMMAND] = PLSqlTypesAdopted.IMMEDIATE_COMMAND;
		type2etype[PLSqlTokenTypes.CONNECT_CLAUSE] = PLSqlTypesAdopted.CONNECT_CLAUSE;
		type2etype[PLSqlTokenTypes.CREATE_DIRECTORY] = PLSqlTypesAdopted.CREATE_DIRECTORY;
		type2etype[PLSqlTokenTypes.DROP_TABLE] = PLSqlTypesAdopted.DROP_TABLE;
		type2etype[PLSqlTokenTypes.COMMENT_STR] = PLSqlTypesAdopted.COMMENT_STR;
		type2etype[PLSqlTokenTypes.SQLPLUS_SERVEROUTPUT] = PLSqlTypesAdopted.SQLPLUS_SERVEROUTPUT;
		type2etype[PLSqlTokenTypes.CREATE_DB_LINK] = PLSqlTypesAdopted.CREATE_DB_LINK;
	}
}
