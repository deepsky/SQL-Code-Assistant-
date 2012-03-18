header {
package com.deepsky.generated.plsql.adopted;
}


class PLSqlParserAdopted extends Parser;

options {
    importVocab = PLSql2;
    exportVocab = PLSql;
    k = 3;
    buildAST = false;
}

tokens {
    START_RULE;
    ERROR_TOKEN_A;
    PACKAGE_SPEC; PACKAGE_BODY; PACKAGE_NAME; PACKAGE_OBJ_BODY;
    RECORD_TYPE_DECL;
    SELECT_EXPRESSION; SELECT_EXPRESSION_UNION;
    PLSQL_BLOCK; PLSQL_BLOCK_END;
    CURSOR_DECLARATION;
    VARIABLE_DECLARATION;
    PROCEDURE_BODY;
    FUNCTION_BODY;
    PARAMETER_SPEC;
    IF_STATEMENT;
    LOOP_STATEMENT;
    STATEMENT;
    TABLE_REFERENCE_LIST;
    TABLE_REFERENCE_LIST_FROM;
    WHERE_CONDITION;
    SUBQUERY;
    SQL_IDENTIFIER;
    FUNCTION;
    GROUP_FUNCTION;
    USER_FUNCTION;
    MULTIPLY;
    ARGUMENT;
    ARGUMENT_LIST;
    FIELD_NAME;
    PLSQL_EXPR_LIST_USING; EXPR_LIST;
    DECLARE_LIST;
    FUNCTION_CALL;
    PACKAGE_INIT_SECTION;
//    CONCAT;
    CALL_ARGUMENT_LIST; SPEC_CALL_ARGUMENT_LIST; QUERY_PARTITION_CLAUSE;
    EXTRACT_OPTIONS;
    CALL_ARGUMENT;
    BASE_EXRESSION;
    COLUMN_SPEC;
    LOGICAL_AND;
    LOGICAL_OR;
    CASE_EXPRESSION_SMPL;
    CASE_EXPRESSION_SRCH;
    CASE_STATEMENT;
    COUNT_FUNC;

    SQLPLUS_ANONYM_PLSQL_BLOCK;

    RANK_FUNCTION; LEAD_FUNCTION; LAG_FUNCTION;
    TRIM_FUNC; DECODE_FUNC;

    COLUMN_SPEC_LIST;
    INSERT_COMMAND; UPDATE_COMMAND; DELETE_COMMAND; MERGE_COMMAND; MERGE_WHEN_COMMAND;
    STRING_LITERAL; NUMERIC_LITERAL; BOOLEAN_LITERAL;
    RETURN_TYPE;
    TYPE_SPEC;
    VARIABLE_NAME;
    COLUMN_OUTER_JOIN;
    LOGICAL_FACTOR;
    TABLE_ALIAS;
    CAST_FUNC;
    VAR_REF; PLSQL_VAR_REF; PARAMETER_REF;
    EXCEPTION_SECTION;

    SELECTED_TABLE;
    CREATE_PROCEDURE;
    CREATE_FUNCTION;

    COMMIT_STATEMENT; ROLLBACK_STATEMENT;
    NULL_STATEMENT;
    ASSIGNMENT_STATEMENT;
    PROCEDURE_CALL;
    RETURN_STATEMENT;

    LOCK_TABLE_STATEMENT; OPEN_STATEMENT;
    FETCH_STATEMENT; SET_TRN_STATEMENT; CLOSE_STATEMENT;

    OBJECT_NAME;
    PARAMETER_NAME;
    DEFAULT;
//    CONDITION;

    THEN_STATEMENT;
    ELSIF_STATEMENT;
    ELSE_STATEMENT;

    STATEMENT_LIST;

    RELATION_CONDITION;
    ISNULL_CONDITION;
    LIKE_CONDITION;
    BETWEEN_CONDITION;
    IN_CONDITION;
    PARENTHESIZED_EXPR;

    ORDER_CLAUSE;
    CONNECT_CLAUSE;
    GROUP_CLAUSE;
    INTO_CLAUSE;
    FOR_UPDATE_CLAUSE;

    PARTITION_NAME;

    ASTERISK1;
    ROWCOUNT_EXRESSION;
    MULTIPLICATION_OP;
    DIVIDE_OP;
    PLUS_OP;
    MINUS_OP;
    CONCAT_OP;

    CURSOR_NAME; CURSOR_NAME_REF;
    DATATYPE;
    EXTRACT_DATE_FUNC;
    ANSI_JOIN_TAB_SPEC;
    // DAY_TO_EXPR;
    INTERVAL_DAY_TO_SEC_EXPR;
    INTERVAL_YEAR_TO_MONTH_EXPR;
    INTERVAL_CONST;
    USER_CONST;
    SYSDATE_CONST;
    SYSTIMESTAMP_CONST; CURRENT_TIMESTAMP_CONST;
    ALIAS_NAME;
    EXISTS_EXPR;
    SUBQUERY_EXPR;
    TYPE_NAME_REF;
    TYPE_NAME;
    SORTED_DEF;
    RAISE_STATEMENT;
    PACKAGE_OBJ_SPEC;
    PROCEDURE_SPEC;
    SIMPLE_UPDATE_COMMAND;
    SUBQUERY_UPDATE_COMMAND;
    IMMEDIATE_COMMAND;
    FUNCTION_SPEC;
    NEGATE_FACTOR;
    TABLE_TYPE_REF;
    COLUMN_TYPE_REF;
    STATEMENT_ANNOT;
    ASTERISK_COLUMN;
    IDENT_ASTERISK_COLUMN;
    EXPR_COLUMN;

    TABLE_REF;
    TABLE_REF_WITH_LINK;

    FROM_SUBQUERY;
    FROM_PLAIN_TABLE;
    SCHEMA_NAME;
    COLUMN_NAME_REF;
    ARITHMETIC_EXPR;
    ASSIGNMENT_OP;
    DBTIMEZONE;
    SUBQUERY_CONDITION;
    RECORD_ITEM;
    EXCEPTION_DECL;

    COMPLEX_NAME;
    CHARACTER_SET;
    AUTHID;

    RESTRICT_REF_PRAGMA; AUTONOMOUS_TRN_PRAGMA; EXCEPTION_PRAGMA;
    FIPSFLAG_PRAGMA; BUILTIN_PRAGMA; INTERFACE_PRAGMA; TIMESTAMPG_PRAGMA;
    SERIALLY_REUSABLE_PRAGMA;

    TIMESTAMP_CONST;
    SUBTYPE_DECL;
    MEMBER_OF;

    SQLPLUS_SET;
    //SQLPLUS_COMMAND;
    SQLPLUS_SHOW; SQLPLUS_DEFINE; SQLPLUS_VARIABLE; SQLPLUS_EXEC; SQLPLUS_WHENEVER;
    SQLPLUS_PROMPT; SQLPLUS_COLUMN; SQLPLUS_START;
    SQLPLUS_SERVEROUTPUT;  SQLPLUS_REPFOOTER;  SQLPLUS_REPHEADER;
    SQLPLUS_EXIT; SQLPLUS_QUIT; SQLPLUS_RUNSCRIPT; SQLPLUS_SPOOL;

    OR_LOGICAL; AND_LOGICAL; NOT_LOGICAL; LOGICAL_EXPR; RELATION_OP;
    SEQUENCE_EXPR; SEQUENCE_REF;
    ALIAS_IDENT;
    COLUMN_NAME_DDL; COLUMN_DEF; TABLE_DEF;
    A_COLUMN_DEF;
    TABLE_COLLECTION; VARRAY_COLLECTION;
    REF_CURSOR;
    OBJECT_TYPE_DEF;

    AT_TIME_ZONE_EXPR;
    TIMEZONE_SPEC;

    GRANT_COMMAND; REVOKE_COMMAND;

    ALTER_TABLE; ALTER_GENERIC; ALTER_TABLE_CONSTRAINT;

    CREATE_TEMP_TABLE;
    COMMENT; COMMENT_STR;
    CREATE_INDEX;
    INSERT_INTO_SUBQUERY_COMMAND;

    CONDITIONAL_COMPILATION;
    COND_COMP_SEQ;
    COND_COMP_SEQ2;
    COND_COMP_ERROR;
    COLUMN_NAME_LIST;
    OWNER_COLUMN_NAME_LIST;

    CREATE_VIEW;
    DATATYPE_PARAM_INFO;

    CREATE_VIEW_COLUMN_DEF; // IMPORTANT! It is not an Oracle syntax construction! It is needed only for Resolver (internal)
    /*  The column view definition generated automatically during VIEW discovering.
        Main purpose is to complete describing VIEW with column types.

        Example:
        create view_column_def_$internal$ QE$_OTA_PARSE_QT_F
        (Q_NAME VARCHAR2(30)
        , ROW_ID ROWID
        , MSGID RAW(16)
        , CORRID VARCHAR2(128)
        ....
        );

    */

    TABLE_FUNCTION;
    ROWNUM; ROWID;
    CUSTOM_AGGR_FUNCTION;
    LAST_STMT_RESULT_NUM;
    LAST_STMT_RESULT_BOOL;
    CURRENT_OF_CLAUSE;
    CURSOR_STATE;
    SQLCODE_SYSVAR;
    SQLERRM_SYSVAR;
    COLLECTION_METHOD_CALL; COLLECTION_METHOD_CALL2; C_RECORD_ITEM_REF;
    CALLABLE_NAME_REF;
    TRUNCATE_TABLE;

    DROP_VIEW; DROP_TABLE; DROP_FUNCTION; DROP_PROCEDURE; DROP_PACKAGE; DROP_INDEX; DROP_SEQUENCE; DROP_TYPE;
    DROP_OPERATOR; DROP_SYNONYM; DROP_USER; DROP_ROLE; DROP_LIBRARY; DROP_DB_LINK; DROP_DIRECTORY;
    DROP_TRIGGER;

    CREATE_SYNONYM; SYNONYM_NAME; SYNONYM_OBJ; SYNONYM_OBJ_WITH_LINK;

    COLUMN_PK_SPEC;
    COLUMN_FK_SPEC;
    NOT_NULL_STMT;
    COLUMN_CHECK_CONSTRAINT; COLUMN_NOT_NULL_CONSTRAINT;
//    CONSTRAINT;
    PK_SPEC; FK_SPEC;
    UNIQUE_CONSTRAINT;CHECK_CONSTRAINT;

    CONSTRAINT_NAME;
    ADD_CONSTRAINT;
    ADD_PRIMARY_KEY;

    ALTER_COLUMN_SPEC;

    IOT_TYPE; HEAP_ORGINIZED; EXTERNAL_TYPE;

    EXTERNAL_TABLE_SPEC;
    WRITE_ACCESS_PARAMS_SPEC; LOADER_ACCESS_PARAMS;
    EXT_FIELD_SPEC_LIST; EXT_FIELD_SPEC; RECORD_FMT_SPEC;
    EXT_TABLE_LOCATION_SPEC;

    STORAGE_PARAM; STORAGE_SPEC;
    PARALLEL_CLAUSE; REJECT_SPEC;

    //
    NAME_FRAGMENT; EXEC_NAME_REF;
    COLLECTION_METHOD_NAME;
    V_COLUMN_DEF;
    TABLE_NAME_DDL; VIEW_NAME;
    INDEX_NAME; VIEW_NAME_DDL;
//    SEQUENCE_NAME;

    CREATE_TRIGGER; CREATE_DIRECTORY; CREATE_DB_LINK; CREATE_SEQUENCE;
    TRIGGER_FOR_EACH; TRIGGER_NAME; ALTER_TRIGGER;
    DB_EVNT_TRIGGER_CLAUSE;
    DDL_TRIGGER_CLAUSE;
    DML_TRIGGER_CLAUSE;
    TRIGGER_COLUMN_REF;
    INSTEADOF_TRIGGER;
    TRIGGER_TARGET;

    FORALL_LOOP_SPEC; CURSOR_REF_LOOP_SPEC;

    CURSOR_LOOP_SPEC; CURSOR_LOOP_INDEX; NUM_LOOP_INDEX; NUMERIC_LOOP_SPEC;

    RECORD_ITEM_NAME;
//    FORALL_STATEMENT;
    GOTO_STATEMENT;
    EXIT_STATEMENT;
    LABEL_NAME;
    PARTITION_SPEC; RANGE_PARTITION; HASH_PARTITION;

    MONITORING_CLAUSE;

    CREATE_TABLESPACE; DROP_TABLESPACE; TABLESPACE_NAME; ALTER_TABLESPACE;
    CREATE_USER; CREATE_TYPE;
    BIND_VAR;
    RETURNING_CLAUSE;
/*
    CREATE_OBJECT_TYPE_DEF;
    CREATE_TABLE_COLLECTION;
    CREATE_RECORD_TYPE_DECL;
    CREATE_REF_CURSOR;
    CREATE_VARRAY_COLLECTION;
*/
}

{
    protected int depth = 0;
    protected int returnType = -1;
    public void __markRule(int type){
        returnType = type;
    }

    final int _NO_WS_ = 0;
    final int _KEEP_WS_ = 1;
    final int _SINGLE_WS_ = 2;
    final int _NEWLINE_WS_ = 3;

//    public void handle_ws( int action ){
//    }

    protected void process_wrapped_package(String package_name){
        // default action if the package is wrapped
        throw new com.deepsky.lang.plsql.parser.WrappedPackageException(package_name);
    }
}

// dirty hack, should be fixed ASAP ----------------
no_one_should_call_me:
    identifier
    {  __markRule(ERROR_TOKEN_A);}
    ;
/*
start_rule:
    (
        (create_or_replace (DIVIDE!)?)
        | package_spec
            {  __markRule(PACKAGE_SPEC); }
        | (function_body (DIVIDE!)?)
        | (procedure_body (DIVIDE!)?)
        | (sql_statement (SEMI!)?)
        | ("alter") => (alter_command (SEMI!)?)
        | (comment (SEMI!)?)
        | (type_definition (SEMI!)?)
        | ( drop_command (SEMI!)?)
        | (sqlplus_command_internal)
    )* EOF!
    ;
*/

// -------------------------------------------------

// IMPORTANT: code generated by ANTLR for start_rule should be revised because it supposed a particual refereences being generated
start_rule:
    (   start_rule_inner
        | {
              if (LA(1)==EOF) {
                    match(EOF);
                    break;
              } else {
                    // consume();
                    // consumeUntil(_tokenSet_2);
                    recover(null,_tokenSet_2);
              }
        }
    )*
    ;

start_rule_inner
{Integer retVal = -1; }:
        create_or_replace
        | ("package" "body") => (package_body  (DIVIDE!)?)
            {  __markRule(PACKAGE_BODY); }
        | ("package") => (package_spec  (DIVIDE!)?)
            {  __markRule(PACKAGE_SPEC); }
        | (retVal=function_body (DIVIDE!)?)
            {  __markRule(retVal); }
        | (retVal=procedure_body (DIVIDE!)?)
            {  __markRule(retVal); }
        | (create_trigger (SEMI!)? (DIVIDE!)?)
            {  __markRule(CREATE_TRIGGER);}
        | (select_command (SEMI)?)

        | insert_command
        | (update_command (SEMI)?)
            {  __markRule(UPDATE_COMMAND); }
        | (delete_command (SEMI)?)
            {  __markRule(DELETE_COMMAND); }
        | (merge_command (SEMI)?)
            {  __markRule(MERGE_COMMAND); }
        | (grant_command (SEMI)?)
            {  __markRule(GRANT_COMMAND); }
        | (revoke_command  (SEMI)?)
            {  __markRule(REVOKE_COMMAND); }
        | (rollback_statement (SEMI)?)
            {  __markRule(ROLLBACK_STATEMENT); }
        | (commit_statement (SEMI)?)
          { __markRule(COMMIT_STATEMENT);}

        | ("alter") => (alter_command)
        | associate_statistics
        | comment
        | type_definition
        | drop_command
        | truncate_command
        | sqlplus_command_internal
    ;


drop_command:
    "drop"! (
        ("table"! table_ref ("purge")?)
            {  __markRule(DROP_TABLE);}
        | ("view" table_ref ("cascade" "constraints")?)
            {  __markRule(DROP_VIEW);}
        | ("function" callable_name_ref)
            {  __markRule(DROP_FUNCTION);}
        | ("procedure" callable_name_ref)
            {  __markRule(DROP_PROCEDURE);}
        | ("package" ("body")? (schema_name DOT!)? package_name )  // todo - "package_name" should be replaced with "package_name_ref"
            {  __markRule(DROP_PACKAGE);}
        | ("index" (schema_name DOT!)? index_name ("force")? )
            {  __markRule(DROP_INDEX);}
        | ("sequence" object_name )
            {  __markRule(DROP_SEQUENCE);}
// todo - "column_name_ref" should be replaced with "type_name_ref"
        | ("type" ("body")? object_name ("force"|"validate")? )
            {  __markRule(DROP_TYPE);}
        | ( ("public")? "synonym" object_name  )
            {  __markRule(DROP_SYNONYM);}
        | ("operator"  object_name  ("force")? )
            {  __markRule(DROP_OPERATOR);}
        | ("user"  object_name ("cascade")? )
            {  __markRule(DROP_USER);}
        | ("role"  object_name )
            {  __markRule(DROP_ROLE);}
        | ("directory"  object_name )
            {  __markRule(DROP_DIRECTORY);}
        | ("library"  object_name )
            {  __markRule(DROP_LIBRARY);}
        | ("database" "link" object_name)
            {  __markRule(DROP_DB_LINK);}
        | ("trigger" object_name)
            {  __markRule(DROP_TRIGGER);}
        | drop_tablespace
            {  __markRule(DROP_TABLESPACE);}
     ) (SEMI)?
    ;

// ASSOCIATE STATISTICS -----------------------------------------------------------------------
associate_statistics:
    "associate" "statistics"  "with" (column_association|function_association) (storage_table_clause)? (SEMI!)?
    ;

column_association:
    "column"  column_spec_ex (COMMA column_spec_ex)* using_statistics_type
    ;

column_spec_ex:
    (name_fragment2 DOT!)+ column_name_ref
    ;

function_association:
    (   ("functions"  object_name (COMMA object_name)* )
        | ("packages" object_name (COMMA object_name)* )
        | ("types"  object_name (COMMA object_name)* )
        | ("indexes" object_name (COMMA object_name)* )
        | ("indextypes" object_name (COMMA object_name)* )
    )
    (default_clause | using_statistics_type)
    ;

storage_table_clause:
    "with" ("user"|"system") "managed" "storage" "table"
    ;

default_clause:
    "default"
    (   ("cost" OPEN_PAREN numeric_literal COMMA numeric_literal COMMA numeric_literal CLOSE_PAREN)
        | ("selectivity" numeric_literal)
    )
    ;

using_statistics_type:
    "using" ("null"|((schema_name DOT)? statistics_type))
    ;

// todo -- should be revised
statistics_type:
    identifier
    ;
// --------------------------------------------------------------------------------------------

truncate_command:
    "truncate"! "table"! table_ref (SEMI)?
    {  __markRule(TRUNCATE_TABLE);}
    ;

comment:
    "comment"! "on"! (
        ("table"! table_ref "is"! comment_string)
        | ("column"! table_ref DOT column_name_ref "is"! comment_string)
     ) (SEMI)?
    {  __markRule(COMMENT);}
    ;

comment_string:
    string_literal
    {  __markRule(COMMENT_STR);}
    ;

column_def:
    column_name_ddl type_spec (column_constraint)*
    {  __markRule(COLUMN_DEF);}
    ;

not_null:
     ("not")? "null" ("disable"|"enable")?
    {  __markRule(NOT_NULL_STMT);}
    ;

row_movement_clause:
    ("disable"|"enable") "row" "movement"
    ;

column_constraint:
    ("constraint" constraint_name (
        ("primary" "key"  ("disable"|"enable")? )
            {  __markRule(COLUMN_PK_SPEC);}
        | ("references"! (schema_name DOT)? table_ref OPEN_PAREN! column_name_ref CLOSE_PAREN! ("rely")? ("disable"|"enable")?)
            {  __markRule(COLUMN_FK_SPEC);}
        | ("check" condition ("disable"|"enable")? )
            {  __markRule(COLUMN_CHECK_CONSTRAINT);}
        | (("not")? "null" ("disable"|"enable")? ("unique")?)
            {  __markRule(COLUMN_NOT_NULL_CONSTRAINT);}
    ) )
    | ("primary" "key"  ("disable"|"enable")? )
            {  __markRule(COLUMN_PK_SPEC);}
    | ("references"! (schema_name DOT)? table_ref OPEN_PAREN! column_name_ref CLOSE_PAREN! ("rely")? ("disable"|"enable")?)
            {  __markRule(COLUMN_FK_SPEC);}
    | ("check" condition ("disable"|"enable")? )
            {  __markRule(COLUMN_CHECK_CONSTRAINT);}
    | (("not")? "null" ("disable"|"enable")? ("unique")?)
            {  __markRule(COLUMN_NOT_NULL_CONSTRAINT);}
    | ("default" ("sysdate"|"systimestamp"|numeric_literal|string_literal))
    ;


sqlplus_command_internal:
    (sqlplus_command)+
    ;

sqlplus_command:
    ("set" (identifier2|"long")? (identifier2|numeric_literal|string_literal)*   ( SEMI! )*)
        {  __markRule(SQLPLUS_SET);}
    | ("show"  identifier ( base_expression ) *  ( SEMI! )*)
        {  __markRule(SQLPLUS_SHOW);}
    | (("var"|"variable")  identifier2 datatype  ( SEMI! )*)
        {  __markRule(SQLPLUS_VARIABLE);}
    | (("col"|"column")  identifier2 (identifier2)*  ( SEMI! )*)
        {  __markRule(SQLPLUS_COLUMN);}
    | (("exec"|"execute")  sqlplus_exec_statement ( SEMI! )*)
        {  __markRule(SQLPLUS_EXEC);}
    | ("whenever" ("sqlerror"| "oserror") ("exit"|"continue"|"commit"|"rollback"|"none") (SEMI!)* )
        {  __markRule(SQLPLUS_WHENEVER);}
//    | (("def"|"define") identifier2 (EQ (plsql_expression | DOUBLE_QUOTED_STRING | STORAGE_SIZE))? )
    | (("def"|"define") identifier2 (EQ (plsql_expression | STORAGE_SIZE))? )
        {  __markRule(SQLPLUS_DEFINE);}
    | ("prompt" (~CUSTOM_TOKEN)* CUSTOM_TOKEN)
        {  __markRule(SQLPLUS_PROMPT);}
    | "rem" (~CUSTOM_TOKEN)* CUSTOM_TOKEN
        {  __markRule(SQLPLUS_PROMPT);}
    | "host" (~CUSTOM_TOKEN)* CUSTOM_TOKEN
    | ("exit"  (identifier2|numeric_literal)? ( SEMI! )*)
        {  __markRule(SQLPLUS_EXIT);}
    | ("quit" (SEMI!)?)
        {  __markRule(SQLPLUS_QUIT);}
//    | "spool" ( "off"|(identifier4 (DOT! identifier4)*) )
    | ( "spool" identifier4 (DOT! identifier4)*)
        {  __markRule(SQLPLUS_SPOOL);}
    | ( ("sta"|"start") sqlplus_path )    // identifier4 (DOT! identifier4)* )
        {  __markRule(SQLPLUS_START);}
    | ( "repfooter" ("off"|"on"|("is" "null")) (SEMI!)?)
        {  __markRule(SQLPLUS_REPFOOTER);}
    | ( "repheader" ("off"|"on"|("is" "null")) (SEMI!)?)
        {  __markRule(SQLPLUS_REPHEADER);}
    | ( "serveroutput" ("off"|"on") (SEMI!)?)
        {  __markRule(SQLPLUS_SERVEROUTPUT);}

    | ("begin"|"declare") => (begin_block (SEMI!)? (DIVIDE!)? )
        {  __markRule(SQLPLUS_ANONYM_PLSQL_BLOCK);}

    | (AT_PREFIXED (~CUSTOM_TOKEN)* CUSTOM_TOKEN )
        {  __markRule(SQLPLUS_RUNSCRIPT);}
    ;


sqlplus_exec_statement:
    ( plsql_lvalue ASSIGNMENT_EQ) => assignment_statement
        { __markRule(ASSIGNMENT_STATEMENT);}
    | ( procedure_call ( DOT procedure_call )*)
    ;

sqlplus_path:
    (DOUBLE_DOT|DOT|identifier3) ((DIVIDE|BACKSLASH) (DOUBLE_DOT|DOT|identifier3))*
    ;


create_or_replace
{Integer retVal = -1;}:
    "create"! ( "or"! "replace"! )? ("force")?
    (
        package_spec
            {  __markRule(PACKAGE_SPEC); }
        | package_body
            {  __markRule(PACKAGE_BODY);}
        | (retVal = procedure_body)
            {  __markRule(retVal); }
        | (retVal = function_body)
            {  __markRule(retVal); }
        | create_view
            {  __markRule(CREATE_VIEW);}
        | create_view_column_def
            {  __markRule(CREATE_VIEW_COLUMN_DEF);}
        | (create_table2 (SEMI!)?)
            {  __markRule(TABLE_DEF);}

        | (("global")? "temporary" (schema_name DOT!)? "table") => (create_temp_table (SEMI!)?)
            {  __markRule(CREATE_TEMP_TABLE);}

        | (create_index2 (SEMI!)?)
            {  __markRule(CREATE_INDEX);}
        | (create_trigger (SEMI!)?)
            {  __markRule(CREATE_TRIGGER);}
        | (create_directory (SEMI!)?)
            {  __markRule(CREATE_DIRECTORY);}
        | (create_db_link (SEMI!)?)
            {  __markRule(CREATE_DB_LINK);}
        | (create_sequence (SEMI!)?)
            {  __markRule(CREATE_SEQUENCE);}
        | (("public")? create_synonym (SEMI!)?)
            {  __markRule(CREATE_SYNONYM);}
        | (create_tablespace (SEMI!)?)
            {  __markRule(CREATE_TABLESPACE);}
        | (create_user (SEMI!)?)
            {  __markRule(CREATE_USER);}

        // CREATE OR REPLACE TYPE ATYPE AS OBJECT ...
        | ("type"! (schema_name DOT)? type_name
            (
              ("under" (schema_name DOT!)?  type_name_ref // type_name_ref_single - using instead of type_name_ref affects parsing time
                OPEN_PAREN! record_item (COMMA! record_item )* CLOSE_PAREN! ("not" "final")? )
                {  __markRule(OBJECT_TYPE_DEF);}
              | (
                  ("as"|"is") "object" OPEN_PAREN! record_item (COMMA! record_item )* CLOSE_PAREN! ("not" "final")?
                  {  __markRule(OBJECT_TYPE_DEF);}
                )
            ) (SEMI)?)
    ) (DIVIDE)?
    ;

/*
CREATE USER helen
    IDENTIFIED BY out_standing1
    DEFAULT TABLESPACE example
    QUOTA 10M ON example
    TEMPORARY TABLESPACE temp
    QUOTA 5M ON system
    PROFILE app_user
    PASSWORD EXPIRE;
*/
create_user:
    "user" object_name "identified" (
        ("by" password)
        | ("externally" ("as" string_literal)?)
        | ("globally" ("as" string_literal)?)
    ) (create_user_spec)*
    ;

create_user_spec:
    ("default" "tablespace" tablespace_name)
    | ("temporary" "tablespace" tablespace_name)
    | ("quota" ("unlimited"|STORAGE_SIZE) "on" tablespace_name)
    | ("profile" identifier)
    | ("password" "expire")
    | ("account" ("lock"|"unlock"))
    ;

password:
    identifier2
    ;


// TABLESPACE -----------------------------------------------------------------
create_tablespace:
    ("bigfile"|"smallfile")?
    (
        ("temporary" "tablespace" tablespace_name ("tempfile" file_specification)? (tablespace_group_clause)?)
        | ("undo" "tablespace" tablespace_name (extent_management_clause)? (tablespace_retention_clause)?)
        | ("tablespace" tablespace_name (create_tablespace_rest)+)
    )
    ;

tablespace_name:
    identifier2
    {  __markRule(TABLESPACE_NAME);}
    ;

create_tablespace_rest:
    "datafile" file_specification (COMMA file_specification)*
    | tablespace_logging_clauses
    | ("next" (STORAGE_SIZE|numeric_literal))
    | ("maxsize" (STORAGE_SIZE|numeric_literal))
    | extent_management_clause
    | tablespace_state_clause
    ;

file_specification:
    string_literal ("size" (STORAGE_SIZE|numeric_literal))? ("reuse")? (autoextend_clause)?
    ;

extent_management_clause:
    "extent" "management" (("local" ("uniform" ("size" (STORAGE_SIZE|numeric_literal)?))?)|"dictionary")
    ;

tablespace_retention_clause:
    "retention" ("guarantee"|"noguarantee")
    ;

autoextend_clause:
    "autoextend" (
        ("on" ("next" STORAGE_SIZE)? ("maxsize" (STORAGE_SIZE|numeric_literal))?)
        | "off"
    )
    ;

tablespace_group_clause:
    "tablespace" "group" identifier2
    ;

tablespace_logging_clauses:
    "logging"
    | "nologging"
    | ("no")? "force" "logging"
    ;

tablespace_state_clause:
    "online"
    | "offline" ("normal"|"temporary"|"immediate")?
    | "read" ("only"|"write")
    | "permanent"
    | "temporary"
    ;

drop_tablespace:
    "tablespace" tablespace_name
        (
            "including" "contents" ("and" "datafiles")? ("cascade" "constraints")?
        )?
    ;

datafile_tempfile_clauses:
    "add" ("datafile"|"tempfile") (file_specification)?
    | "rename" "datafile" string_literal (COMMA string_literal)* "to" string_literal (COMMA string_literal)*
    | ("datafile"|"tempfile") ("online"|"offline")
    ;

alter_tablespace:
    "tablespace" tablespace_name (
        autoextend_clause
        | ("begin"|"end") "backup"
        | "coalesce"
        | "rename" "to" tablespace_name
        | tablespace_state_clause
        | tablespace_retention_clause
        | "minimum" "extent" STORAGE_SIZE
        | tablespace_logging_clauses
        | datafile_tempfile_clauses
    )
    ;
// ----------------------------------------------------------------------------

/*
CREATE SEQUENCE T1_SEQ
  START WITH 100000
  INCREMENT BY 2
  MINVALUE 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  CYCLE
  CACHE 25
  NOORDER;
*/
create_sequence:
    "sequence"! object_name (sequence_opt)*
    ;

sequence_opt:
    ("maxvalue" numeric_literal)
    | ("minvalue" numeric_literal)
    | "cycle"
    | "nocycle"
    | ("cache" numeric_literal)
    | "nocache"
    | ("increment" "by" numeric_literal)
    | ("start" "with" numeric_literal)
    | "order"
    | "noorder"
    ;

create_synonym:
    "synonym" (schema_name DOT!)? synonym_name "for" (schema_name DOT!)? synonym_obj
    ;

synonym_name:
    identifier2
    {  __markRule(SYNONYM_NAME);}
    ;

create_db_link:
    "database" "link" object_name
    "connect" "to" identifier2 "identified" "by" DOUBLE_QUOTED_STRING
    ("using" (identifier2|string_literal))?
    ;

synonym_obj :
    identifier2
        { __markRule(SYNONYM_OBJ);}
    | TABLE_NAME_SPEC
        { __markRule(SYNONYM_OBJ_WITH_LINK);}
    ;


// -------------------------------------------------------------------
// [TRIGGER START] ----------------------------------
// -------------------------------------------------------------------

create_trigger:
    "trigger"! (schema_name DOT!)? trigger_name
    // BEFORE INSERT OR UPDATE ON ota_dev_grp_mobile_t
    // AFTER INSERT OR UPDATE OR DELETE ON ota_dev_grp_mobile_t
    // FOR EACH ROW
    (   ( ("after"|"before")
            (
                dml_trigger
                | ddl_trigger
                | db_event_trigger
            )
        )
        | instead_of_trigger
    )
    (for_each|referencing_old_new)* (trigger_when)? begin_block
    ;

db_event_trigger:
    ("startup" | "shutdown" | "servererror" | "logon" | "logoff")
    "on"! trigger_target
    {  __markRule(DB_EVNT_TRIGGER_CLAUSE);}
    ;

ddl_trigger:
    ("create" | "alter" | "drop" | "analyze" | ("associate" "statistics") | "audit" | "noaudit"
    | "comment" | "ddl" | ("diassociate" "statistics") | "grant" | "rename" | "revoke" | "truncate")
    "on"! trigger_target
    {  __markRule(DDL_TRIGGER_CLAUSE);}
    ;

dml_trigger:
    insert_update_delete ( "or"! insert_update_delete)* "on"! table_ref
    {  __markRule(DML_TRIGGER_CLAUSE);}
    ;

trigger_target:
    "schema" | "database"
    {  __markRule(TRIGGER_TARGET);}
    ;

instead_of_trigger:
    "instead"! "of"! ("delete"|"insert"|"update") "on"! table_ref  // todo -- view_name_ddl
    {  __markRule(INSTEADOF_TRIGGER);}
    ;

for_each:
    "for"! "each"! "row"!
    {  __markRule(TRIGGER_FOR_EACH);}
    ;

referencing_old_new:
    "referencing" (("old" "as" identifier2) | ("new" "as" identifier2))*
    ;
trigger_when:
    "when"! condition
    ;

insert_update_delete:
    "insert"
    | ("update" ("of" identifier2 (COMMA! identifier2)*)? )
    | "delete"
    ;

trigger_name:
    identifier2
    {  __markRule(TRIGGER_NAME);}
    ;

alter_trigger:
    "trigger"! (schema_name DOT!)? trigger_name enable_disable_clause
    ;
// -------------------------------------------------------------------
// [TRIGGER END] ----------------------------------
// -------------------------------------------------------------------


create_index2:
    ("unique"|"bitmap")? "index"! (schema_name DOT!)? index_name "on"! (schema_name DOT!)? table_ref
    OPEN_PAREN index_column_spec_list CLOSE_PAREN
    (physical_properties|table_properties)*
    ;

index_column_spec_list:
    identifier2 ("asc"|"desc")? (COMMA! identifier2 ("asc"|"desc")?)*
    ;


create_directory:
    "directory" (schema_name DOT!)? object_name ("as" string_literal)?
    ;
// -------------------------------------------------------------------
// [CREATE TABLE START] ----------------------------------
// -------------------------------------------------------------------
create_table2:
    "table"! (schema_name DOT!)? table_name_ddl (
            ( OPEN_PAREN! column_def (COMMA! (column_def|constraint))* CLOSE_PAREN!
                (nested_tab_spec)? (lob_storage_clause)? (physical_properties|table_properties)*
                )
            | ( (physical_properties|table_properties)* "as" select_expression)
        )
    ;

create_temp_table:
    ("global")? "temporary"! (schema_name DOT!)? "table"! table_name_ddl
    (OPEN_PAREN! column_def (COMMA! (column_def|constraint))* CLOSE_PAREN!)?
    ("on" "commit" ("preserve" | "delete") "rows" )
    (cache_clause)?
    ("as" select_expression)?
    ;

// NESTED TABLE ad_textdocs_ntab STORE AS textdocs_nestedtab
nested_tab_spec:
    "nested" "table" identifier "store" "as" identifier
    ("return" "as" ("locator"|"value"))?
    ;

lob_storage_clause:
    "lob" OPEN_PAREN identifier (COMMA identifier)* CLOSE_PAREN "store" "as"
    OPEN_PAREN (lob_parameters)+ CLOSE_PAREN
    ;

lob_parameters:
    ("tablespace" tablespace_name)
    | storage_spec
    | ("chunk" NUMBER)
    | (("nocache" | ("cache" ("reads")?)) (logging_clause)?)
    | "retention"
    | ("pctversion" NUMBER)
    | ("freepools" NUMBER)
    | (("enable" | "disable") "storage" "in" "row")
    ;

physical_properties:
    (
        (deferred_segment_creation)?
            (segment_attributes_clause
            | organization_spec)
    )
    | cluster_clause
    ;

deferred_segment_creation:
    "segment" "creation" ("immediate"|"deferred")
    ;

cluster_clause:
    "cluster" identifier OPEN_PAREN column_name_ddl (COMMA column_name_ddl)* CLOSE_PAREN
    ;

segment_attributes_clause:
    physical_attributes_clause
    | ("tablespace" tablespace_name)
    | "online"
    | logging_clause
    | table_compression
    | ("pctthreshold" numeric_literal)
    ;

logging_clause:
    "logging"
    | "nologging"
    | "filesystem_like_logging"
    ;

table_compression:
    ("compress" (numeric_literal | ("for" ("all"|"direct_load") "operations"))?)
    | "nocompress"
    ;

physical_attributes_clause:
    ("pctfree" numeric_literal)
    | ("pctused" numeric_literal)
    | ("initrans" numeric_literal)
    | ("maxtrans" numeric_literal)
    | (options {greedy = true;} :
        "compute" "statistics" ("parallel"|"noparallel"|identifier)?
        )
    | storage_spec
    ;

table_properties:
    table_partitioning_clause
    { __markRule(PARTITION_SPEC);}
    | cache_clause
    | parallel_clause
    | alter_table_options
    | row_movement_clause
    | monitoring_clause
    | index_org_overflow_clause
    ;

cache_clause:
    "cache"|"nocache"
    ;

monitoring_clause:
    ("monitoring" | "nomonitoring")
    { __markRule(MONITORING_CLAUSE);}
    ;

index_org_overflow_clause:
    ("including" column_name_ref)? "overflow" (physical_attributes_clause)*
    ;

table_partitioning_clause:
    range_partitions
        { __markRule(RANGE_PARTITION);}
    | hash_partitions
        { __markRule(HASH_PARTITION);}
    | local_partitioned_index // -- "create index" specific
// todo   | list_partitions
// todo   | reference_partitions
// todo   | composite_range_partitions
// todo   | composite_list_partitions
// todo  | system_partitioning
    ;

range_partitions:
    "partition" "by" "range" OPEN_PAREN! column_name_ref (COMMA! column_name_ref)* CLOSE_PAREN!
    ("interval" plsql_expression ("store" "in" OPEN_PAREN identifier2 (COMMA! identifier2)* OPEN_PAREN)?)?
    OPEN_PAREN partition_item (COMMA! partition_item)* CLOSE_PAREN
    ;

partition_item:
    "partition" (partition_name)? range_values_clause (table_partition_description)+
    ;

range_values_clause:
    "values" "less" "than" OPEN_PAREN (numeric_literal | "maxvalue") (COMMA! (numeric_literal | "maxvalue"))* CLOSE_PAREN
    ;

table_partition_description:
    segment_attributes_clause
//    | table_compression
    | "overflow"
// todo  | lob_storage_clause
    ;

hash_partitions:
    "partition" "by" "hash" OPEN_PAREN! column_name_ref (COMMA! column_name_ref)* CLOSE_PAREN!
    ((individual_hash_partitions)+ | hash_partitions_by_quantity)
    (("cache" ("parallel"|"noparallel")) | "nocache")?
    ;

individual_hash_partitions:
    OPEN_PAREN hash_partition_spec (COMMA hash_partition_spec)* CLOSE_PAREN
    ;

hash_partition_spec:
    "partition" (partition_name)? (partition_storage_clause)*
    ;

partition_name:
    identifier2
    {  __markRule(PARTITION_NAME);}
    ;

partition_storage_clause:
    ("tablespace" tablespace_name)
    | "overflow"
    | table_compression
// todo     | lob_partition_storage
    ;

hash_partitions_by_quantity:
    "partitions" numeric_literal ("store" "in" OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?
    (table_compression)? ("overflow" "store" "in" OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?
    ;

enable_disable_clause:
    "enable"|"disable"
    ;

alter_table_options:
    enable_disable_clause ("validate"|"novalidate")?
    (
        ("unique" (OPEN_PAREN! identifier2 (COMMA! identifier2) CLOSE_PAREN!))
        | ("primary" "key")
        | ("constraint" identifier2)
    )
    ;

organization_spec:
    "organization"!
        ( ("index"! ("including" identifier2)? ("pctthreshold" numeric_literal)?)
            {  __markRule(IOT_TYPE);}
        | "heap"!
            {  __markRule(HEAP_ORGINIZED);}
//        | ("external") => external_table_spec
//            { #.organization_spec = #.([EXTERNAL_TYPE, "EXTERNAL_TYPE" ], #.organization_spec);}
//        | ("external" OPEN_PAREN external_table_spec CLOSE_PAREN (reject_spec|parallel_clause)*)
        | ("external" OPEN_PAREN external_table_spec CLOSE_PAREN (reject_spec)?)
            {  __markRule(EXTERNAL_TYPE);}
        )
    ;

parallel_clause:
(
    ("parallel"
        (
            ( OPEN_PAREN
                "degree" (numeric_literal | "default")
                "instances" (numeric_literal | "default")
                CLOSE_PAREN )
            | numeric_literal
        )?
    )
    | "noparallel"
)
    {  __markRule(PARALLEL_CLAUSE);}
    ;

// REJECT LIMIT UNLIMITED
reject_spec:
    "reject" "limit" ("unlimited"| NUMBER)
    {  __markRule(REJECT_SPEC);}
    ;

storage_spec:
    "storage" OPEN_PAREN (storage_params)+ CLOSE_PAREN
    {  __markRule(STORAGE_SPEC);}
    ;

storage_params:
(
    ("initial" (STORAGE_SIZE|numeric_literal))
    | ("next" (STORAGE_SIZE|numeric_literal))
    | ("minextents" numeric_literal)
    | ("maxextents" (numeric_literal|"unlimited"))
    | ("pctincrease" numeric_literal)
    | ("freelists" numeric_literal)
    | ("freelist" "groups" numeric_literal)
    | ("optimal" (STORAGE_SIZE|"null")?)
    | ("buffer_pool" ( "keep" | "recycle" | "default"))
    | ("flash_cache" ( "keep" | "none" | "default"))
    | ("cell_flash_cache" ( "keep" | "none" | "default"))
    | "encrypt"
)
    {  __markRule(STORAGE_PARAM);}
    ;

constraint:
    ("constraint" constraint_name)? (
        pk_spec_constr
            {  __markRule(PK_SPEC);}
        | fk_spec_constr
            {  __markRule(FK_SPEC);}
        | check_condition
            {  __markRule(CHECK_CONSTRAINT);}
        | unique_constr
            {  __markRule(UNIQUE_CONSTRAINT);}
     )
    ;

pk_spec_constr:
    "primary"! "key"! OPEN_PAREN! owner_column_name_ref_list CLOSE_PAREN! (enable_disable_clause)? (using_index_clause)?
    ;

fk_spec_constr:
    "foreign"! "key"! OPEN_PAREN! owner_column_name_ref_list CLOSE_PAREN!
    "references"! (schema_name DOT)? table_ref OPEN_PAREN! column_name_ref_list CLOSE_PAREN!
    ("rely")? (enable_disable_clause)?
    ("on" ("delete"|"update") referential_actions)?
    ;

unique_constr:
    "unique" OPEN_PAREN column_name_ref (COMMA! column_name_ref)* CLOSE_PAREN (using_index_clause)?
    ;

check_condition:
    "check" condition
    ;

column_name_ref_list:
    column_name_ref (COMMA! column_name_ref)*
    {  __markRule(COLUMN_NAME_LIST);}
    ;

owner_column_name_ref_list:
    column_name_ref (COMMA! column_name_ref)*
    {  __markRule(OWNER_COLUMN_NAME_LIST);}
    ;

referential_actions:
    "cascade"|"restrict"|("no" "action")|("set" "null")|("set" "default")
    ;

constraint_name:
    identifier
    {  __markRule(CONSTRAINT_NAME);}
    ;
// -------------------------------------------------------------------
// [CREATE TABLE END] ------------------------------------------------
// -------------------------------------------------------------------


// -------------------------------------------------------------------
// [ALTER TABLE START] ------------------------------------------------
// -------------------------------------------------------------------
alter_table:
    "table"! table_ref (constraint_clause)?
    ;

constraint_clause:
    ("add" (add_syntax_1 | (OPEN_PAREN! add_syntax_2 CLOSE_PAREN!) ) )
    | ("modify" (modify_constraint_clause
                    | (OPEN_PAREN! alter_column_def (COMMA! alter_column_def)* CLOSE_PAREN!)
                    | alter_column_def)
       )
    | ("rename"
            (   ("constraint" identifier2 "to" identifier2)
                | ("to" identifier2)
                | ("column" identifier2 "to" identifier2) )
      )
    | "drop" ( ("column" identifier2) | drop_clause )
    ;

modify_constraint_clause:
    ("constraint" constraint_name ("rely")? ("disable"|"enable")? ("validate"|"novalidate")?)
    | ("primary" "key" (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?)
    | ("unique" OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)
    ;

add_syntax_1:
    alter_column_def
    | inline_out_of_line_constraint
    ;

add_syntax_2:
    (alter_column_def) => (alter_column_def (COMMA! alter_column_def)*)
    | inline_out_of_line_constraint
    ;

alter_column_def:
    column_name_ddl type_spec (column_constraint)*
    {  __markRule(A_COLUMN_DEF);}
    ;

//constraint_OLD:
//    inline_out_of_line_constraint
// todo    inline_constraint
// todo    | out_of_line_constraint
// todo    | inline_ref_constraint
// todo    | out_of_line_ref_constraint
//    ;

/*
   ADD CONSTRAINT OTA_LL1_TUI_FK FOREIGN KEY (DDE_ID)
      REFERENCES OTA_LL1_HRDATE_T (ID)
       RELY DISABLE NOVALIDATE;
*/

inline_out_of_line_constraint:
(
    ("constraint" constraint_name)? (
        not_null
        | ("unique" (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?)
        | ("primary" "key" (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?)
        | ("foreign" "key"
                (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)
                "references" (schema_name DOT)? table_ref (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)
                ("rely")? ("disable"|"enable")? ("validate"|"novalidate")? ("on" "delete" ("cascade"|("set" "null")) )?
            )
        | ("check" condition)
    )
    (using_index_clause)?
)
    {  __markRule(ALTER_TABLE_CONSTRAINT);}
    ;

drop_clause:
    ("primary" "key" ("cascade")? (("keep"|"drop") "index")? )
    | ("unique" OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN ("cascade")? (("keep"|"drop") "index")?)
    | ("constraint" constraint_name ("cascade")?)
    ;

using_index_clause:
    ("rely")? "using" "index" (
        ((schema_name DOT)? identifier2)
// todo        | (OPEN_PAREN create_index_statement CLOSE_PAREN)
        | index_properties
    )  (enable_disable_clause)?
    ;

index_properties:
    ((index_attributes)+)
    | global_partitioned_index
    | local_partitioned_index
    ;

index_attributes:
    ("tablespace" (tablespace_name|"default"))
    | physical_attributes_clause
    | logging_clause
    | "online"
    | ("sort"|"nosort")
    | "reverse"
    | ("visible"|"novisible")
    | parallel_clause
    ;

global_partitioned_index:
    "global" "partition" "by" (
        ("range" OPEN_PAREN index_column_spec_list CLOSE_PAREN OPEN_PAREN  index_partitioning_clause CLOSE_PAREN)
        | ("hash" OPEN_PAREN index_column_spec_list CLOSE_PAREN (individual_hash_partitions | hash_partitions_by_quantity))
    )
    ;

index_partitioning_clause:
    "partition" (partition_name)? "values" "less" "than"
            OPEN_PAREN numeric_literal (COMMA! numeric_literal)* CLOSE_PAREN segment_attributes_clause
    ;

local_partitioned_index:
    "local" (
        on_range_partitioned_table
// todo       | on_list_partitioned_table
// todo       | on_hash_partitioned_table
// todo       | on_comp_partitioned_table
    )?
    ;

on_range_partitioned_table:
    OPEN_PAREN local_partition_item (COMMA! local_partition_item)* CLOSE_PAREN
    ;

local_partition_item:
     "partition" (partition_name)? (segment_attributes_clause|table_compression)*
     ;

// -------------------------------------------------------------------
// [ALTER TABLE END] ------------------------------------------------
// -------------------------------------------------------------------


type_definition:
    "type"! (schema_name DOT!)? type_name
    (
      ("under" (schema_name DOT!)?  type_name_ref // type_name_ref_single - using instead of type_name_ref affects parsing time
        OPEN_PAREN! record_item (COMMA! record_item )* CLOSE_PAREN! ("not" "final")? )
        {  __markRule(OBJECT_TYPE_DEF);}
      | (
          ("as"!|"is"!)  (
            ("object"! OPEN_PAREN! record_item (COMMA! record_item )* CLOSE_PAREN! ("not" "final")?)
                {  __markRule(OBJECT_TYPE_DEF);}
            | ("table"! "of"! type_spec ("index"! "by"! type_spec)? ("not" "null")? )
                {  __markRule(TABLE_COLLECTION);}
            | ("record" OPEN_PAREN! record_item ( COMMA! record_item )* CLOSE_PAREN!)
                {  __markRule(RECORD_TYPE_DECL); }
            | ( "ref" "cursor"! ( "return"! record_name (PERCENTAGE "rowtype")? )? )
                {  __markRule(REF_CURSOR); }
            | ( "varray"! OPEN_PAREN! plsql_expression CLOSE_PAREN! "of"! type_spec ("not" "null")?)
                {  __markRule(VARRAY_COLLECTION); }
          )
      )
    ) (SEMI)?
    ;


create_view:
    "view"! view_name_ddl (OPEN_PAREN! v_column_def (COMMA! v_column_def)* CLOSE_PAREN!)?
    "as"! select_expression
    ("with" (("check" "option")|("read" "only")))? (SEMI!)?
    ;

create_view_column_def:
    "view_column_def_$internal$"! view_name_ddl OPEN_PAREN! column_def (COMMA! column_def)* CLOSE_PAREN! (SEMI!)?
    ;

v_column_def:
    (identifier2 | "timestamp" | "partition")
    {  __markRule(V_COLUMN_DEF);}
    ;

package_spec :
//    "package"! (schema_name DOT!)? o:package_name (a:"authid" {#a.setType(AUTHID);} identifier)?
    "package"! (schema_name DOT!)? o:package_name ("authid"  identifier)?
    (   ("wrapped" {
                throw new com.deepsky.lang.plsql.parser.WrappedPackageException();
//                String package_name = #o.getFirstChild().getText();
// todo                String package_name = #o.getText();
//                throw new com.deepsky.lang.plsql.parser.WrappedPackageException(package_name);
// todo                 process_wrapped_package(package_name);
             }
        )
        | (("is"! | "as"!) (serially_reusable_pragma SEMI!)?
            ( package_obj_spec_ex)* "end"! (package_name! )? SEMI!)
    )
    ;

package_obj_spec_ex:
    package_obj_spec
    | (IF_COND_CMPL condition
            THEN_COND_CMPL cond_comp_seq (ELSE_COND_CMPL cond_comp_seq)? END_COND_CMPL
      )
    { __markRule(CONDITIONAL_COMPILATION);}
    ;


cond_comp_seq:
    (error_cond_compliation)* (package_obj_spec)*
    { __markRule(COND_COMP_SEQ);}
    ;

error_cond_compliation:
    ERROR_COND_CMPL string_literal END_COND_CMPL
//    error_cond_cmpl string_literal end_cond_cmpl
    { __markRule(COND_COMP_ERROR);}
    ;

package_body :
    "package"! ("body"!)? (schema_name DOT!)? o:package_name ("is"! | "as"!)
    (serially_reusable_pragma SEMI!)?
    ( package_obj_body )*
    (package_init_section)? "end"! (package_name! )? SEMI!
//    {  __markRule(PACKAGE_BODY);}
    ;

package_init_section:
    "begin"  seq_of_statements
    {  __markRule(PACKAGE_INIT_SECTION);}
    ;

package_obj_spec
{Integer retVal = -1;}:
    subtype_declaration
    | cursor_spec
    | type_definition
    | (retVal = procedure_body)
      {  __markRule(retVal); }
    | (retVal = function_body)
      {  __markRule(retVal); }
    | pragmas
    | variable_declaration
    | exception_declaration
    ;

pragmas:
    "pragma"! (
        ("restrict_references"! OPEN_PAREN! identifier3 (COMMA! identifier3)+ CLOSE_PAREN! SEMI!)
            { __markRule(RESTRICT_REF_PRAGMA);}
        | ("interface"! OPEN_PAREN! identifier3 (COMMA! identifier3)+ CLOSE_PAREN! SEMI!)
            { __markRule(INTERFACE_PRAGMA);}
        | ("builtin"! OPEN_PAREN! string_literal (COMMA! plsql_expression)+ CLOSE_PAREN! SEMI!)
            { __markRule(BUILTIN_PRAGMA);}
        | ("fipsflag"! OPEN_PAREN! string_literal (COMMA! plsql_expression)+ CLOSE_PAREN! SEMI!)
            { __markRule(FIPSFLAG_PRAGMA);}
        | ("timestamp"! OPEN_PAREN! string_literal CLOSE_PAREN! SEMI!)
            { __markRule(TIMESTAMPG_PRAGMA);}
        | ("exception_init"! OPEN_PAREN! complex_name COMMA! plsql_expression CLOSE_PAREN! SEMI!)
            { __markRule(EXCEPTION_PRAGMA);}
    )
    ;

/*
if_cond_cmpl:
    DOLLAR "if"
    ;

then_cond_cmpl:
    DOLLAR "then"
    ;

else_cond_cmpl:
    DOLLAR "else"
    ;

end_cond_cmpl:
    DOLLAR "end"
    ;

error_cond_cmpl:
    DOLLAR "error"
    ;
*/

condition_compilation:
    IF_COND_CMPL condition THEN_COND_CMPL cond_comp_seq2 (ELSE_COND_CMPL cond_comp_seq2)? END_COND_CMPL
//    if_cond_cmpl condition then_cond_cmpl cond_comp_seq2 (else_cond_cmpl cond_comp_seq2)? end_cond_cmpl
    { __markRule(CONDITIONAL_COMPILATION);}
    ;

cond_comp_seq2:
    (error_cond_compliation)*
    ( package_obj_spec)*
    { __markRule(COND_COMP_SEQ2);}
    ;

variable_declaration :
    variable_name ("constant")?  type_spec ("not" "null")? ((ASSIGNMENT_EQ|default1) plsql_expression)?
        // todo -- workaround to enable completion for variable type
        (SEMI)?
    { __markRule(VARIABLE_DECLARATION);}
    ;
	exception catch [RecognitionException ex] {
	    throw ex;
	}

subtype_declaration :
    "subtype" (type_name|datatype) "is"
    (
        (type_spec) => type_spec
        | (table_ref PERCENTAGE! "type"! )
    )
    ("not" "null")? SEMI!
    {  __markRule(SUBTYPE_DECL); }
    ;

cursor_spec :
    "cursor" cursor_name (argument_list)? (
        ("is"! select_statement)
            { __markRule(CURSOR_DECLARATION);}
        | ("return" return_type)
    )  SEMI!
    ;


package_obj_body:
    package_obj_spec
    | condition_compilation
    ;

seq_of_statements:
    (statement_tmpl)+
    { __markRule(STATEMENT_LIST);}
    ;


statement_tmpl:
//    (statement SEMI!)
    statement
    | (START_LABEL label_name END_LABEL)
    | (IF_COND_CMPL condition THEN_COND_CMPL seq_of_statements (ELSE_COND_CMPL seq_of_statements)?  END_COND_CMPL )
//    | (if_cond_cmpl condition then_cond_cmpl seq_of_statements (else_cond_cmpl seq_of_statements)?  end_cond_cmpl )
    {  __markRule(CONDITIONAL_COMPILATION);}
    ;

statement
{boolean tag1=false;}
:
        ("begin" | "declare") => (begin_block SEMI)
        | ( "loop" | "for" | "while" ) => (loop_statement SEMI)
            { __markRule(LOOP_STATEMENT);}
        | forall_loop
            { __markRule(LOOP_STATEMENT);}
        | (if_statement SEMI)
            {  __markRule(IF_STATEMENT); }
        | ( goto_statement SEMI)
            { __markRule(GOTO_STATEMENT);}
        | ( raise_statement SEMI)
            { __markRule(RAISE_STATEMENT);}
        | ( exit_statement SEMI )
            { __markRule(EXIT_STATEMENT);}
        | ("null" SEMI)
            { __markRule(NULL_STATEMENT);}
        | ("return"! (condition)? SEMI)
            { __markRule(RETURN_STATEMENT);}
        | ( case_statement SEMI)
            { __markRule(CASE_STATEMENT);}
        | ( select_command (SEMI)?)
        | insert_command
        | (update_command (SEMI)?)
            {  __markRule(UPDATE_COMMAND); }
        | (delete_command (SEMI)?)
            {  __markRule(DELETE_COMMAND); }
        | (merge_command (SEMI)?)
            {  __markRule(MERGE_COMMAND); }
        | (grant_command (SEMI)?)
            {  __markRule(GRANT_COMMAND); }
        | (revoke_command (SEMI)?)
            {  __markRule(REVOKE_COMMAND); }
        | (rollback_statement (SEMI)?)
            {  __markRule(ROLLBACK_STATEMENT); }
        | (commit_statement (SEMI)?)
            { __markRule(COMMIT_STATEMENT);}
        | (immediate_command SEMI)
            {  __markRule(IMMEDIATE_COMMAND);}
        | (lock_table_statement SEMI)
            { __markRule(LOCK_TABLE_STATEMENT);}
        | (open_statement SEMI)
            { __markRule(OPEN_STATEMENT);}
        | (close_statement SEMI)
            { __markRule(CLOSE_STATEMENT);}
        | (fetch_statement SEMI)
            { __markRule(FETCH_STATEMENT);}
        | (set_transaction_command SEMI)
            { __markRule(SET_TRN_STATEMENT);}
        | (savepoint_statement (SEMI)?)
        | alter_command
        | ( plsql_lvalue ASSIGNMENT_EQ) => (assignment_statement  (SEMI)? )
            { __markRule(ASSIGNMENT_STATEMENT);}
// todo -- subject to revise
        | (( name_fragment_ex DOT )* exec_name_ref ~OPEN_PAREN ) => (callable_name_ref (SEMI)?)
            { __markRule(PROCEDURE_CALL);}
        | ( procedure_call ( DOT {tag1=true;} procedure_call )* (SEMI)? )
            {
                if(tag1){
                     __markRule(COLLECTION_METHOD_CALL2);
                }
            }
    ;

//l_ret_val.EXTEND;
//l_ret_val(1).amk_cols.EXTEND;
//ota_logger_pkg.info(a_log_section => pc_pkg_log_section);
/*
procedure_call_no_args:
    callable_name_ref
    { __markRule(PROCEDURE_CALL);}
    ;
*/

sql_percentage:
    "sql" PERCENTAGE (
        "found"
            { __markRule(LAST_STMT_RESULT_BOOL);}
        | "notfound"
            { __markRule(LAST_STMT_RESULT_BOOL);}
        | "rowcount"
            { __markRule(LAST_STMT_RESULT_NUM);}
        | "isopen"
            { __markRule(LAST_STMT_RESULT_BOOL);}
        | ("bulk_rowcount" OPEN_PAREN plsql_expression CLOSE_PAREN )
            { __markRule(LAST_STMT_RESULT_NUM);}
        | "bulk_exceptions" (
           (OPEN_PAREN plsql_expression CLOSE_PAREN DOT ("error_index" | "error_code"))
            | ((DOT) "count")
          )
            { __markRule(LAST_STMT_RESULT_NUM);}
     )
    ;

case_statement:
    "case"! (
        // simple case_statement
        ( plsql_expression ("when" plsql_expression "then" seq_of_statements)+ ) => plsql_expression ("when" plsql_expression "then" seq_of_statements)+
        // searched case_statement
        | ( "when" condition t:"then" seq_of_statements )+ )
    ( e:"else" seq_of_statements ) ?
    "end"! "case"!
    ;

pragma_autonomous_transaction:
    "pragma"! "autonomous_transaction" SEMI!
    { __markRule(AUTONOMOUS_TRN_PRAGMA);}
    ;

assignment_statement :
//    plsql_lvalue p:ASSIGNMENT_EQ! {#p.setType(ASSIGNMENT_OP);} condition
    plsql_lvalue ASSIGNMENT_EQ  condition
    ;

rvalue
{boolean tag1=false;}
:
    (("prior")? ( name_fragment2 DOT )* name_fragment2 ~OPEN_PAREN ) => (("prior")? ( name_fragment2 DOT)* name_fragment2)
        { __markRule(VAR_REF);} /// !!!!!! actual type needs to be resolved

    | (COLON ("new"|"old") DOT) => (COLON ("new"|"old") DOT! name_fragment)
        { __markRule(TRIGGER_COLUMN_REF);}

    | ( function_call ( DOT! {tag1=true;}((function_call) => function_call | c_record_item_ref ) )* )
     {
        if(tag1){
             __markRule(COLLECTION_METHOD_CALL);
        }
     }
    ;

c_record_item_ref:
    identifier2
    {  __markRule(C_RECORD_ITEM_REF); }
    ;

plsql_lvalue
{boolean tag1=false;}
:
    (( name_fragment DOT )* name_fragment2 ~OPEN_PAREN ) => ( name_fragment DOT! )* name_fragment2
        { __markRule(PLSQL_VAR_REF);}

    | (COLON ("new"|"old") DOT) => (COLON ("new"|"old") DOT! name_fragment)
        { __markRule(TRIGGER_COLUMN_REF);}

    | ( function_call ( DOT! {tag1=true;} ((function_call) => function_call | c_record_item_ref ) )* )
     {
        if(tag1){
             __markRule(COLLECTION_METHOD_CALL);
        }
     }
    | bind_variable
    ;

name_fragment2 :
     (identifier2 | "timestamp")
    {  __markRule(NAME_FRAGMENT);}
    ;


collection_method:
    identifier2
    {  __markRule(COLLECTION_METHOD_NAME); }
    ;

field_name :
    identifier
    {  __markRule(FIELD_NAME); }
    ;

bind_variable:
    COLON identifier2
    {  __markRule(BIND_VAR); }
    ;

goto_statement :
    g:"goto" label_name
    ;

label_name :
    identifier
    {  __markRule(LABEL_NAME); }
    ;

exit_statement:
    "exit" (label_name)? ("when" condition)?
    ;

datatype_param_info:
    "number" (COMMA "number")? EOF!
    {  __markRule(DATATYPE_PARAM_INFO); }
    ;


datatype :
    (
        "binary_integer"
        | "natural"
        | "positive"

        // note: NUMBER in parentesis is in OPTIONAL just to facilitate autocompletion
        | ("number"(OPEN_PAREN! ((NUMBER|ASTERISK) (COMMA! (MINUS)? NUMBER)?)? CLOSE_PAREN! )? )
        | ("char" (OPEN_PAREN! (NUMBER ("byte")?)? CLOSE_PAREN! )? )
        | ("long" ("raw")?)
        | "raw" (OPEN_PAREN! NUMBER CLOSE_PAREN! )?
        | "boolean"
        | "date"
        | "timestamp" (OPEN_PAREN! NUMBER CLOSE_PAREN! )?
            (("with" "local" "time" "zone") => ("with" "local" "time" "zone")
            | ("with" "time" "zone"))?

        | "interval"
            ( ( "year" (OPEN_PAREN! NUMBER CLOSE_PAREN!)? "to" "month")
            | ("day" (OPEN_PAREN! NUMBER CLOSE_PAREN!)? "to" "second" (OPEN_PAREN! NUMBER CLOSE_PAREN!)?)
            )
        | "smallint"
        | "real"
        | "numeric" (OPEN_PAREN! (NUMBER (COMMA! NUMBER)?)? CLOSE_PAREN! )?
        | "int"
        | "integer" (OPEN_PAREN! (NUMBER)? CLOSE_PAREN! )?
        | "pls_integer"
        | "double" "precision"
        | "float" ( OPEN_PAREN! (NUMBER)? CLOSE_PAREN! )?
        | "decimal"
        | "varchar" ( OPEN_PAREN! (NUMBER ("byte" | "char")?)? CLOSE_PAREN! )?
        | "varchar2" ( OPEN_PAREN! (NUMBER ("byte" | "char")?)? CLOSE_PAREN! )?
        | "nvarchar" ( OPEN_PAREN! (NUMBER ("byte" | "char")?)? CLOSE_PAREN! )?
        | "nvarchar2" ( OPEN_PAREN! (NUMBER ("byte" | "char")?)? CLOSE_PAREN! )?
        | "character" ( OPEN_PAREN! NUMBER CLOSE_PAREN! )?
        | "rowid"
        | "blob"
        | "clob"
        | "nclob"
        | "bfile"
    )
    {  __markRule(DATATYPE);}
    ;
	exception catch [RecognitionException ex] {
	    throw ex;
	}

type_spec :
    ( datatype
    | ( table_ref ((DOT column_name_ref PERCENTAGE "type") | (PERCENTAGE! "rowtype"!)) )
            => percentage_type
    | ( schema_name DOT table_ref ((DOT column_name_ref PERCENTAGE "type") | (PERCENTAGE! "rowtype"!)) )
            => percentage_type_w_schema
    | type_name_ref )
//    {  __markRule(TYPE_SPEC);}
    ;

/* ---------- docs -----------
%TYPE and %ROWTYPE are used to define variables in PL/SQL as it is defined within the database.
The %TYPE and %ROWTYPE constructs provide data independence, reduces maintenance costs, and
allows programs to adapt as the database changes to meet new business needs.

%TYPE is used to declare a field with the same type as that of a specified table's column. Example:

    DECLARE
    v_EmpName emp.ename%TYPE;
    BEGIN
    SELECT ename INTO v_EmpName FROM emp WHERE ROWNUM = 1;
    DBMS_OUTPUT.PUT_LINE('Name = ' || v_EmpName);
    END;
    /

%ROWTYPE is used to declare a record with the same types as found in the specified database table, view or cursor. Examples:

    DECLARE
    v_emp emp%ROWTYPE;
    BEGIN
    v_emp.empno := 10;
    v_emp.ename := 'XXXXXXX';
    END;
    /

    DECLARE
    v_EmpRecord emp%ROWTYPE;
    BEGIN
    SELECT * INTO v_EmpRecord FROM emp WHERE ROWNUM = 1;
    DBMS_OUTPUT.PUT_LINE('Name = ' || v_EmpRecord.ename);
    DBMS_OUTPUT.PUT_LINE('Salary = ' || v_EmpRecord.sal);
    END;
    /
 ---------- docs ----------- */
percentage_type:
    // declaration of the variable that is based on table column with the %TYPE attribute.
    table_ref (
        (DOT column_name_ref PERCENTAGE "type")
        {  __markRule(COLUMN_TYPE_REF);}
        | (PERCENTAGE! "rowtype"!)
        {  __markRule(TABLE_TYPE_REF);}
    )
    ;

percentage_type_w_schema:
    // declaration of the variable that is based on table column with the %TYPE attribute.
    schema_name DOT table_ref (
        (DOT column_name_ref PERCENTAGE "type")
        {  __markRule(COLUMN_TYPE_REF);}
        | (PERCENTAGE! "rowtype"!)
        {  __markRule(TABLE_TYPE_REF);}
    )
    ;


type_name_ref :
     name_fragment (DOT! name_fragment )*
    {  __markRule(TYPE_NAME_REF);}
    ;

type_name_ref_single :
     name_fragment
    {  __markRule(TYPE_NAME_REF);}
    ;

sequence_ref :
    identifier2
    {  __markRule(SEQUENCE_REF);}
    ;

name_fragment :
     (identifier2|"primary"|"foreign")
    {  __markRule(NAME_FRAGMENT);}
    ;

name_fragment_ex :
     (identifier3|"primary"|"foreign")
    {  __markRule(NAME_FRAGMENT);}
    ;

identifier3:
    identifier2
    | "prior"
    | "start"
    ;

identifier4:
    identifier2
    | "prior"
    | "start"
    | "create"
    |"primary"
    |"foreign"
    ;

type_name :
     identifier2
    {  __markRule(TYPE_NAME);}
    ;

parameter_spec :
    parameter_name ("in" )? ("out")? ("nocopy")? ("ref")?
    (type_spec)  (character_set)? ( (default1 | ASSIGNMENT_EQ) plsql_expression )?
    {  __markRule(PARAMETER_SPEC);}
    ;

character_set:
    "character" "set" ( "any_cs" | (identifier2 PERCENTAGE "charset") )
    {  __markRule(CHARACTER_SET);}
    ;

default1:
    "default"
    {  __markRule(DEFAULT);}
    ;

parameter_name :
    identifier2
    {  __markRule(PARAMETER_NAME);}
    ;

procedure_spec :
    procedure_declaration SEMI!
    { __markRule(PROCEDURE_SPEC);}
    ;
	exception catch [RecognitionException ex] {
	    reportError(ex);
	    throw new com.deepsky.integration.CustomRecognitionException(ex.getMessage());
	}

function_spec :
    function_declaration SEMI!
    { __markRule(FUNCTION_SPEC);}
    ;
	exception catch [RecognitionException ex] {
	    reportError(ex);
	    throw new com.deepsky.integration.CustomRecognitionException(ex.getMessage());
	}

exception_declaration :
    exception_name "exception"! SEMI!
    { __markRule(EXCEPTION_DECL);}
    ;

exception_name :
    exception_package_name (DOT! identifier)?
    ;

exception_package_name:
    identifier
    ;

serially_reusable_pragma:
    "pragma" "serially_reusable"
    { __markRule(SERIALLY_REUSABLE_PRAGMA);}
    ;

exception_pragma :
    "pragma"! "exception_init"! OPEN_PAREN! complex_name COMMA! plsql_expression CLOSE_PAREN! SEMI!
    { __markRule(EXCEPTION_PRAGMA);}
    ;

numeric_literal :
    NUMBER
    {  __markRule(NUMERIC_LITERAL);}
    ;

oracle_err_number:
        (p:PLUS | m:MINUS) ? n:NUMBER
        ;

record_item:
    record_item_name type_spec ("not" "null")? ((default1 |ASSIGNMENT_EQ) plsql_expression)?
    {  __markRule(RECORD_ITEM); }
    ;

record_item_name:
    identifier2
    {  __markRule(RECORD_ITEM_NAME); }
    ;
/*
procedure_body2  :
    procedure_declaration ("is"|"as") (
        (("language" "java" "name") => ("language" "java" "name" string_literal))
        | func_proc_statements
        {  __markRule(PROCEDURE_BODY); }
    )
    ;
*/

func_proc_statements
    : (declare_list)? plsql_block SEMI!
    {  __markRule(PLSQL_BLOCK);}
    ;

begin_block
    : ("declare" (declare_list)?)? plsql_block
    {  __markRule(PLSQL_BLOCK);}
    ;

plsql_block :
    "begin"!
    (seq_of_statements )?
    ( exception_section )?
    plsql_block_end
    ;

plsql_block_end:
    "end"! ( identifier )?
    {  __markRule(PLSQL_BLOCK_END);}
    ;

exception_section:
    "exception"! (exception_handler)+
    {  __markRule(EXCEPTION_SECTION);}
    ;

declare_list:
    (declare_spec)+
//    (declare_spec | variable_declaration | exception_declaration)+
    { __markRule(DECLARE_LIST);}
    ;


declare_spec
{Integer retVal = -1;}:
        type_definition
        | variable_declaration
        | cursor_spec
        | subtype_declaration
        | ("pragma" "autonomous_transaction") => pragma_autonomous_transaction
        | exception_pragma
        | (retVal = function_body)
            {  __markRule(retVal); }
        | (retVal = procedure_body)
            {  __markRule(retVal); }
        | exception_declaration
    ;

exception_handler :
    "when" exception_name
    ("or" exception_name )* "then"
    seq_of_statements
    ;

function_declaration :
    "function"! object_name
     (options { greedy = true; } :
        // 'argument_list' is made optional just to keep parser happy
        //     todo - annotator should take care of absence of the argument list
//        ( OPEN_PAREN! (argument_list)? CLOSE_PAREN! )?
        ( argument_list )?
    )
    "return"! return_type (character_set)? ("pipelined")? ("parallel_enable")? ("using" identifier2)? ("deterministic")?
    ;

procedure_declaration :
    "procedure"! object_name
    (options { greedy = true; } :
        // 'argument_list' is made optional just to keep parser happy
        //     todo - annotator should take care of absence of the argument list
//        ( OPEN_PAREN! (argument_list)? CLOSE_PAREN! )?
        ( argument_list )?
    )
    ;
/*
	exception catch [RecognitionException ex] {
	    throw ex;
	}
*/
/*
function_body2  :
    function_declaration ( "is"! | "as"! ) (
        (("language" "java" "name") => ("language" "java" "name" string_literal))
        | func_proc_statements
            {  __markRule(FUNCTION_BODY); }
//          | ("aggregate" "using" identifier2)
//            {  __markRule(CUSTOM_AGGR_FUNCTION); }
        )
    ;
*/

function_body returns [Integer retValue]
{ boolean tag1 = false; retValue = -1;} :
    function_declaration
        (( "is"! | "as"! ) (
//            (("language" "java" "name") => ("language" "java" "name" {tag1 = false;} string_literal ))
            ("language" "java" "name" {tag1 = false;} string_literal )
            | ( func_proc_statements {tag1 = true;})
//                {  __markRule(FUNCTION_BODY); }
                { retValue = FUNCTION_BODY; }
            )
        )? (SEMI)?
    {
        if(!tag1){
//             __markRule(FUNCTION_SPEC);
            { retValue = FUNCTION_SPEC; }
        }
    }
    ;
	exception catch [RecognitionException ex] {
	    reportError(ex);
	    throw new com.deepsky.integration.CustomRecognitionException(ex.getMessage());
	}


procedure_body returns [Integer retValue]
{ boolean tag1 = false; retValue = -1;} :
    procedure_declaration
        (("is"|"as") (
            ("language" "java" "name" {tag1 = false;} string_literal)
            | (func_proc_statements {tag1 = true;} )
//                {  __markRule(PROCEDURE_BODY); }
                { retValue = PROCEDURE_BODY; }
            )
        )? (SEMI)?
    {
        if(!tag1){
//             __markRule(PROCEDURE_SPEC);
            { retValue = PROCEDURE_SPEC; }
        }
    }
    ;
	exception catch [RecognitionException ex] {
	    reportError(ex);
	    throw new com.deepsky.integration.CustomRecognitionException(ex.getMessage());
	}


argument_list:
    OPEN_PAREN (parameter_spec ( COMMA! parameter_spec )*)? CLOSE_PAREN
    {  __markRule(ARGUMENT_LIST); }
    ;


object_name :
//    identifier3 (DOT! identifier2 )?
    (identifier3 DOT)? identifier2
    {  __markRule(OBJECT_NAME); }
    ;

return_type :
    type_spec
    {  __markRule(RETURN_TYPE); }
    ;

/*
    1. a regular function call
    2. collection initialization
*/
function_call:
    callable_name_ref call_argument_list
    {  __markRule(FUNCTION_CALL); }
    ;

procedure_call:
    callable_name_ref call_argument_list
    { __markRule(PROCEDURE_CALL);}
    ;

callable_name_ref:
    ( name_fragment_ex DOT!)* exec_name_ref
    { __markRule(CALLABLE_NAME_REF);}
    ;

exec_name_ref :
     identifier3
    {  __markRule(EXEC_NAME_REF);}
    ;

null_statement:
    "null"
    { __markRule(NULL_STATEMENT);}
    ;

raise_statement :
    "raise"! ( exception_name )?
    ;

forall_loop:
    forall_header ("save" "exceptions")?
	statement
    ;

forall_header:
    "forall" num_loop_index "in" (
        (forall_boundary DOUBLEDOT forall_boundary)
        | ("values" "of" plsql_lvalue )
        | ("indices" "of" plsql_lvalue )
    )
    { __markRule(FORALL_LOOP_SPEC);}
    ;

forall_boundary:
    numeric_literal
    | plsql_lvalue
    ;

loop_statement :
    (label_name! )?
    (   ("while" condition)
        |   ("for" (
                (numeric_loop_spec) => numeric_loop_spec
                | cursor_loop_spec
                )
            )
    )? "loop"
    seq_of_statements
    "end"! "loop"! (label_name!)?
    ;

numeric_loop_spec :
    num_loop_index "in"! ("reverse"!)? numeric_loop_spec2
/*
    plsql_expression DOUBLEDOT! plsql_expression
    { __markRule(NUMERIC_LOOP_SPEC);}
*/
    ;

num_loop_index:
    identifier2
    { __markRule(NUM_LOOP_INDEX);}
    ;

numeric_loop_spec2:
    plsql_expression DOUBLEDOT! plsql_expression
    { __markRule(NUMERIC_LOOP_SPEC);}
    ;

//
// for cus in ( select ID, UPD_CNT from XSL_CUS_CUSTOMER_T where PARENT_ID = i_id and FREEZE_DATE is null for update )
// loop
//  	  deleteCustomer( cus.ID, cus.UPD_CNT, i_deleter_user_id, aud_action_type, aud_action_source );
// end loop;

cursor_loop_spec :
    cursor_loop_index "in"! cursor_loop_spec2
    ;

cursor_loop_spec2:
    (cursor_name_ref ( call_argument_list )?)
        { __markRule(CURSOR_REF_LOOP_SPEC);}
    | select_statement
        { __markRule(CURSOR_LOOP_SPEC);}
    ;

cursor_loop_index:
    identifier2
    { __markRule(CURSOR_LOOP_INDEX);}
    ;

complex_name:
    identifier (DOT! identifier2)*
    {  __markRule(COMPLEX_NAME); }
    ;

boolean_literal :
    ("true" | "false")
    {  __markRule(BOOLEAN_LITERAL); }
    ;

index_name:
    identifier
    {  __markRule(INDEX_NAME); }
    ;

integer_expr :
    plsql_expression
    | type_spec
    ;

cursor_name :
    identifier
    {  __markRule(CURSOR_NAME); }
    ;

cursor_name_ref :
    identifier
    {  __markRule(CURSOR_NAME_REF); }
    ;

record_name:
    identifier
    ;

num_expression
{
boolean tag1=false;
boolean tag2=false;
}
:
//    num_term ((p:PLUS {#p.setType(PLUS_OP);} | m:MINUS {#m.setType(MINUS_OP);} ) num_term )*
    num_term ((PLUS {tag1=true;} | MINUS {tag2=true;} ) num_term )*
    {
        if(tag1 || tag2){
             __markRule(ARITHMETIC_EXPR);
        }
    }
    ;

num_term
{
boolean tag1=false;
boolean tag2=false;
}
:
//    num_factor (( as:ASTERISK {#as.setType(MULTIPLICATION_OP);} | dv:DIVIDE {#dv.setType(DIVIDE_OP);} ) num_factor )*
    num_factor (( as:ASTERISK {tag1=true;} | dv:DIVIDE {tag2=true;} ) num_factor )*
    {
        if(tag1 || tag2){
             __markRule(ARITHMETIC_EXPR);
        }
    }
    ;

num_factor
{ boolean tag1 = false; boolean tag2 = false;}
:
    may_be_negate_base_expr ("**" integer_expr)? (
        ("day" {tag1 = true;} (OPEN_PAREN! NUMBER CLOSE_PAREN!)? "to" "second" (OPEN_PAREN! NUMBER CLOSE_PAREN!)?)
        | ("year" {tag2 = true;} (OPEN_PAREN! NUMBER CLOSE_PAREN!)? "to" "month" (OPEN_PAREN! NUMBER CLOSE_PAREN!)?)
    )?
    {
        if(tag1){
             __markRule(INTERVAL_DAY_TO_SEC_EXPR);
        } else if(tag2){
             __markRule(INTERVAL_YEAR_TO_MONTH_EXPR);
        }
    }
    ;

may_be_negate_base_expr
{ boolean tag1 = false;}
:
    (sign! {tag1 = true;} )? may_be_at_time_zone
    {
        if(tag1){
             __markRule(ARITHMETIC_EXPR);
        }
    }
    ;

may_be_at_time_zone
{ boolean tag1 = false;}
:
    base_expression ("at"! {tag1 = true;} (("time"! "zone"! timezone_spec) | ("local")))?
    {
        if(tag1){
             __markRule(AT_TIME_ZONE_EXPR);
        }
    }
    ;

sign:
    p:PLUS
    | m:MINUS
//    p:PLUS {#p.setType(PLUS_OP);}
//    | m:MINUS {#m.setType(MINUS_OP);}
    ;

expr_list:
    plsql_expression (COMMA! plsql_expression )*
    { __markRule(EXPR_LIST);}
    ;

parentesized_exp_list:
    OPEN_PAREN! expr_list cp:CLOSE_PAREN!
    ;

condition
{boolean tag1= false;}
:
    logical_term ( "or" {tag1=true;} logical_term )*
    {
        if(tag1){
             __markRule(LOGICAL_EXPR);
        }
    }
    ;

logical_term
{boolean tag1= false;}
:
    maybe_neg_factor  ("and" {tag1=true;}  maybe_neg_factor )*
    {
        if(tag1){
             __markRule(LOGICAL_EXPR);
        }
    }
    ;

maybe_neg_factor
{boolean tag1= false;}
:
    ("not"! {tag1=true;} )? plsql_expression33
    {
        if(tag1){
             __markRule(LOGICAL_EXPR);
        }
    }
    ;

/* MEMBER OF info
Oracle Database 10g allows you to use MEMBER OF syntax to determine if a particular values
is a "member of" a nested table. Here is an example:

DECLARE
 TYPE clientele IS TABLE OF VARCHAR2 (64);
 client_list_12 clientele :=
 clientele ('Customer 1', 'Customer 2');
BEGIN
 IF 'Customer 1' MEMBER OF client_list_12
 THEN
 DBMS_OUTPUT.put_line ('Customer 1 is in the 12 list');
 END IF;

 IF 'Customer 3' NOT MEMBER OF client_list_12
 THEN
 DBMS_OUTPUT.put_line ('Customer 3 is not in the 12 list');
 END IF;
END;
/
*/

plsql_expression33
{ boolean tag1 = false;}
:
    ( "current" "of" ) => ( "current"! "of"! ) identifier
        {  __markRule(CURRENT_OF_CLAUSE); }
    | ( "exists" ) => "exists"! subquery
        {  __markRule(EXISTS_EXPR); }
    | (OPEN_PAREN! expr_list CLOSE_PAREN! (EQ | NOT_EQ | (("not" )? "in" )) OPEN_PAREN "select")
            => OPEN_PAREN! expr_list CLOSE_PAREN! (EQ | NOT_EQ | (("not" )? "in" {tag1=true;})) subquery
        {
            if(tag1){
                 __markRule(IN_CONDITION);
            } else {
                 __markRule(SUBQUERY_CONDITION);
            }
        }
    | plsql_expression (
        (relational_op) => relational_op plsql_expression
        {  __markRule(RELATION_CONDITION); }
        | (("not" )? "in" ) => ("not")? "in"! exp_set
        {  __markRule(IN_CONDITION); }
        | (( "not" )? "like" ) => ( "not" )? "like" plsql_expression ( "escape" (string_literal|identifier2) )?
        {  __markRule(LIKE_CONDITION); }
        | (( "not" )? "between") => ( "not" )? "between"! plsql_expression "and"! plsql_expression
        {  __markRule(BETWEEN_CONDITION); }
        | ("is" ( "not" )? "null" )
        {  __markRule(ISNULL_CONDITION); }
        | ( ("not")? "member" "of") =>  (("not"!)? "member"! "of"! (name_fragment DOT!)? name_fragment )
        {  __markRule(MEMBER_OF); }
        )?
    ;


plsql_expression
{ boolean tag1 = false;}
:
    num_expression (c:CONCAT {tag1=true;} num_expression )*
    {
        if(tag1){
             __markRule(ARITHMETIC_EXPR);
        }
    }
    ;

base_expression:
      ("sqlcode") => "sqlcode"
        {  __markRule(SQLCODE_SYSVAR);}
//      | ("sqlerrm") => ("sqlerrm" (OPEN_PAREN! base_expression CLOSE_PAREN!)? )
      | ("sqlerrm") => ("sqlerrm" (OPEN_PAREN! numeric_literal CLOSE_PAREN!)? )
        {  __markRule(SQLERRM_SYSVAR);}
      | ( "cast" OPEN_PAREN) => ( cast_function )
      | ( "decode" OPEN_PAREN) => ( decode_function )
      | ( "trim" OPEN_PAREN) => ( trim_function )
      | ( "count" ) => ( count_function )
      | ( "case" ) => ( case_expression )
      | ( "multiset" ) => ( multiset_operator )
      | ( "lag"  OPEN_PAREN) => ( lag_function )
      | ( "lead"  OPEN_PAREN) => ( lead_function )
      | ( ("rank"|"dense_rank") OPEN_PAREN ) => dence_rank_analytics_func
      | ( "extract" OPEN_PAREN ) => extract_date_function

        // interval examples: "INTERVAL '30' MINUTE", "INTERVAL '2-6' YEAR TO MONTH", "INTERVAL '3 12:30:06.7' DAY TO SECOND(1)"
      | ("interval"! string_literal ("second" | "minute" | "hour" | "day" | "month" | "year")
                    ("to" ("second"|"month") (OPEN_PAREN! NUMBER CLOSE_PAREN!)?)? )
        {  __markRule(INTERVAL_CONST);}
      | ("sql" PERCENTAGE ) => sql_percentage
      | ("timestamp"! string_literal)
        {  __markRule(TIMESTAMP_CONST);}
      | (( "all" | "any" )) => ( a1:"all" | a2:"any" ) subquery
      | ((cursor_name | subquery) PERCENTAGE ("rowcount"|"found"|"notfound"|"isopen") ) => ident_percentage
      | ( OPEN_PAREN "select") => subquery
        {  __markRule(SUBQUERY_EXPR);}
      | OPEN_PAREN! condition CLOSE_PAREN!
        {  __markRule(PARENTHESIZED_EXPR);}
      | string_literal
      | numeric_literal
      | boolean_literal
      | null_statement
      | (pseudo_column) => pseudo_column
      | (column_spec OPEN_PAREN! PLUS! CLOSE_PAREN!) => ( column_spec OPEN_PAREN! PLUS! CLOSE_PAREN! )
        { __markRule(COLUMN_OUTER_JOIN);}
      | ( sequence_ref DOT ("nextval" | "currval")) => sequence_expr

      | rvalue
      ;

ident_percentage:
    (cursor_name | subquery) PERCENTAGE!
        ("rowcount"
            {  __markRule(ROWCOUNT_EXRESSION);}
          |"found"
            {  __markRule(CURSOR_STATE);}
          |"notfound"
            {  __markRule(CURSOR_STATE);}
          |"isopen"
            {  __markRule(CURSOR_STATE);}
        )
    ;

// DENSE_RANK() OVER (ORDER BY MAX(SEVERITY * 1000000000 + FAULT_TS / 1000) DESC, XTL_PDC_REGISTRATION_T.MNO_NAME)
// DENSE_RANK() OVER (PARTITION BY deptno ORDER BY sal) "rank"
dence_rank_analytics_func:
/*
    ("rank"|"dense_rank")
    OPEN_PAREN CLOSE_PAREN
        "over" OPEN_PAREN
            (query_partition_clause)? order_clause
         CLOSE_PAREN
*/
    callable_name_ref2 dence_rank__arg_list
    {  __markRule(RANK_FUNCTION);}
    ;

dence_rank__arg_list:
    OPEN_PAREN CLOSE_PAREN "over"
        OPEN_PAREN (query_partition_clause)? order_clause CLOSE_PAREN
    {  __markRule(SPEC_CALL_ARGUMENT_LIST);}
    ;

lead_function:
//    "lead" lag_lead_func_arg_list
    callable_name_ref2 lag_lead_func_arg_list
    {  __markRule(LEAD_FUNCTION);}
    ;

// lag(freeze_date,1,NULL) over (PARTITION BY obj_id ORDER BY freeze_date ASC nulls last)
lag_function:
    callable_name_ref2 lag_lead_func_arg_list
    {  __markRule(LAG_FUNCTION);}
    ;

callable_name_ref2:
    exec_name_ref
    { __markRule(CALLABLE_NAME_REF);}
    ;

lag_lead_func_arg_list:
    OPEN_PAREN call_argument (COMMA call_argument)* CLOSE_PAREN
    "over" OPEN_PAREN (query_partition_clause)? order_clause CLOSE_PAREN
    {  __markRule(SPEC_CALL_ARGUMENT_LIST);}
    ;

query_partition_clause:
    "partition" "by" plsql_expression
    {  __markRule(QUERY_PARTITION_CLAUSE); }
    ;

timezone_spec:
    ( string_literal
     | complex_name
     | "sessiontimezone"
     | "dbtimezone")
    {  __markRule(TIMEZONE_SPEC); }
    ;

sequence_expr:
//    name_fragment DOT! name_fragment /// ("nextval" | "currval")
    sequence_ref DOT! ("nextval" | "currval")
    { __markRule(SEQUENCE_EXPR);}
    ;

count_function:
    "count"! OPEN_PAREN! (
        asterisk_column
        | (ident_asterisk_column) =>  ident_asterisk_column
        | ( ("distinct")? plsql_expression )
    ) CLOSE_PAREN!
    { __markRule(COUNT_FUNC);}
    ;

multiset_operator:
    "multiset" subquery
    ;

string_literal :
    (QUOTED_STR)+
    {  __markRule(STRING_LITERAL); }
    ;

/*
    EXTRACT(MINUTE FROM END_TIME_INTERVAL)
    EXTRACT(DAY FROM END_TIME_INTERVAL)
*/
extract_date_function:
     callable_name_ref2 extract_date_func_arg_list
    { __markRule(EXTRACT_DATE_FUNC);}
    ;

extract_date_func_arg_list:
    OPEN_PAREN! extract_consts "from"! call_argument CLOSE_PAREN!
    {  __markRule(SPEC_CALL_ARGUMENT_LIST);}
    ;

extract_consts:
    ("second" | "minute" | "hour" | "day" | "month" | "year" | "timezone_hour" | "timezone_minute" | "timezone_region" | "timezone_abbr")
    {  __markRule(EXTRACT_OPTIONS);}
    ;

trim_function :
    callable_name_ref2 trim_func_arg_list
    { __markRule(TRIM_FUNC);}
    ;

trim_func_arg_list:
    OPEN_PAREN ("leading"|"trailing"|"both")? call_argument ( "from" call_argument )? CLOSE_PAREN
    {  __markRule(SPEC_CALL_ARGUMENT_LIST);}
    ;

decode_function:
    callable_name_ref2 decode_function_arg_list
    { __markRule(DECODE_FUNC);}
    ;

decode_function_arg_list:
    OPEN_PAREN! ( call_argument (COMMA! call_argument)* )?  CLOSE_PAREN
    {  __markRule(SPEC_CALL_ARGUMENT_LIST);}
    ;

cast_function :
    callable_name_ref2 cast_func_arg_list
    {  __markRule(CAST_FUNC); }
    ;

cast_func_arg_list:
    OPEN_PAREN call_argument "as"! (type_name_ref|datatype) CLOSE_PAREN
    {  __markRule(SPEC_CALL_ARGUMENT_LIST);}
    ;

date_literal:
    (QUOTED_STR)+
    ;

commit_statement:
    "commit" ( "work" )?
    ;

case_expression
{ boolean tag1 = false;}
:
    ("case"! (
        // searched_case_expression
        ( "when" condition t:"then" plsql_expression )+
        // simple_case_expression
        | plsql_expression {tag1 = true;} ("when" plsql_expression "then" plsql_expression)+
     )
    ( "else" plsql_expression ) ?
    "end"!)
    {
        if(tag1){
            { __markRule(CASE_EXPRESSION_SMPL);}
        } else {
            { __markRule(CASE_EXPRESSION_SRCH);}
        }
    }
    ;


if_statement:
    "if"! condition
    "then"! seq_of_statements
    (elsif_statements)*
    (else_statements)?
    "end"! "if"!
    ;

elsif_statements:
    "elsif"! condition "then"! seq_of_statements
    {  __markRule(ELSIF_STATEMENT); }
    ;

else_statements:
    "else"! seq_of_statements
    {  __markRule(ELSE_STATEMENT); }
    ;


grant_command:
    "grant"  (
        (privilege (COMMA privilege)* "on" identifier2)
        | ("all" "privileges")
    )
    "to" (identifier2 |"public")
    ;

revoke_command:
    "revoke"  (
        (privilege (COMMA privilege)* "on" identifier2)
        | ("all" "privileges")
    )
    "from" (identifier2 |"public")
    ;


privilege:
    "select" | "insert" | "update" | "delete" | "references" | "alter" | "index" | "execute"
    ;

select_command:
    select_expression
    | subquery
    ;

select_statement:
    select_expression
    | subquery
    ;

subquery:
    OPEN_PAREN! select_command CLOSE_PAREN!
    {  __markRule(SUBQUERY); }
    ;

select_expression
{boolean tag1=false;}
:
    select_first ( (
        ( "union" ("all")? )
        | "intersect"
        | "minus"
      ) select_first {tag1=true;}) *
    {
        if(tag1){
            {  __markRule(SELECT_EXPRESSION_UNION); }
        }
    }
    ;

select_first:
    ( select_up_to_list
        ( into_clause ) ?     // the only difference
        table_reference_list_from
        ( where_condition )?
        ( connect_clause )?
        ( group_clause )?
        ( order_clause )?
        ( update_clause )?
     )
        {  __markRule(SELECT_EXPRESSION); }
   | OPEN_PAREN select_expression CLOSE_PAREN
        {  __markRule(SUBQUERY); }
    ;



/* Example of formatting markup

select_subsequent:
    select_up_to_list
        { handle_ws(_NEWLINE_WS_); } table_reference_list_from
        ( { handle_ws(_NEWLINE_WS_); } where_condition )?
        ( { handle_ws(_NEWLINE_WS_); } connect_clause )?
        ( { handle_ws(_NEWLINE_WS_); } group_clause )?
    | o:OPEN_PAREN select_subsequent c:CLOSE_PAREN
    ;


into_clause:
    ("bulk" { handle_ws(_KEEP_WS_); } "collect")? i:"into"! lvalue_list  ///plsql_exp_list
    {  __markRule(INTO_CLAUSE);}
    ;
*/


into_clause:
    ("bulk" "collect")? "into"! plsql_lvalue_list
    {  __markRule(INTO_CLAUSE);}
    ;

plsql_lvalue_list:
    plsql_lvalue (COMMA! plsql_lvalue)*
    ;

select_up_to_list:
    "select"! ( "all" | "distinct" | "unique")? displayed_column ( COMMA! displayed_column )*
    ;
	exception catch [RecognitionException ex] {
	    throw ex;
	}

displayed_column:
    asterisk_column
    | (ident_asterisk_column) =>  ident_asterisk_column
    | expr_column
    ;

asterisk_column:
    ASTERISK
    {  __markRule(ASTERISK_COLUMN); }
    ;

ident_asterisk_column:
    name_fragment DOT ASTERISK
    {  __markRule(IDENT_ASTERISK_COLUMN); }
    ;

expr_column:
    plsql_expression ( alias )?
    {  __markRule(EXPR_COLUMN); }
    ;


immediate_command:
    "execute"! "immediate"!
                (   (plsql_lvalue) => plsql_lvalue
                    |   plsql_expression
                )
        ( ("bulk" "collect")? "into" plsql_lvalue_list ) ?
        ( "using" plsql_exp_list_using ) ?
    ;


plsql_exp_list_using:
    ("in"|"out")? plsql_expression (COMMA! ("in"|"out")? plsql_expression )*
    { __markRule(PLSQL_EXPR_LIST_USING);}
    ;

/*
plsql_exp_list_using:
    ( ("in"|"out")? plsql_expression COMMA ) => ("in"|"out")? plsql_expression (COMMA! ("in"|"out")? plsql_expression )*
    { __markRule(PLSQL_EXPR_LIST_USING);}
    | ("in"|"out")? plsql_expression
    ;
*/

alter_command:
    "alter" (
        alter_system_session (SEMI)?
            { __markRule(ALTER_GENERIC);}
        | alter_table (SEMI)?
            {  __markRule(ALTER_TABLE);}
        | alter_trigger (SEMI)?
            {  __markRule(ALTER_TRIGGER);}
        | alter_tablespace (SEMI)?
            {  __markRule(ALTER_TABLESPACE);}
    )
    ;

//   ALTER DATABASE SET time_zone = 'US/Eastern';
alter_system_session:
    ( "system" | "session" | "database")
    (
        ( "flush" "shared_pool" )
        | (
            ( "set"  identifier EQ
                ( string_literal | numeric_literal | identifier | "local")
            )
            | ( "reset" identifier )
          )
         ( "sid" EQ ( string_literal | ASTERISK ) ) ?
    )
    ;

table_reference_list_from:
        "from"! selected_table // ("partition"! OPEN_PAREN! identifier2 CLOSE_PAREN!)?
        (
         ( "left" | "right" | "inner" | "outer" | "join" | "full") => ansi_spec
         | (COMMA! selected_table ("partition"! OPEN_PAREN! partition_name CLOSE_PAREN!)?)
        )*
{
     __markRule(TABLE_REFERENCE_LIST_FROM);
}
            ;

table_reference_list:
    selected_table ( COMMA! selected_table )*
    { __markRule(TABLE_REFERENCE_LIST);}
    ;

where_condition:
    "where"! condition
    {  __markRule(WHERE_CONDITION); }
    ;


call_argument_list:
    OPEN_PAREN! ( call_argument (COMMA! call_argument)* )?  CLOSE_PAREN
    {  __markRule(CALL_ARGUMENT_LIST);}
    ;

call_argument :
    (parameter_name_ref PASS_BY_NAME!)? condition
    {  __markRule(CALL_ARGUMENT);}
    ;
	exception catch [RecognitionException ex] {
	    throw ex;
	}


parameter_name_ref:
    name_fragment
    { __markRule(PARAMETER_REF);}
    ;

schema_name:
    identifier2
    { __markRule(SCHEMA_NAME);}
    ;

view_name_ddl :
    identifier
    { __markRule(VIEW_NAME_DDL);}
    ;

table_name_ddl :
    identifier2
    { __markRule(TABLE_NAME_DDL);}
    ;

table_ref :
    identifier2
    { __markRule(TABLE_REF);}
    ;

alias :
    ( "as"! )?  alias_ident
    { __markRule(ALIAS_NAME);}
    ;

alias_ident:
    (identifier2 | "timestamp")
    { __markRule(ALIAS_IDENT);}
    ;

package_name:
    identifier
    { __markRule(PACKAGE_NAME);}
    ;

column_spec:
    (name_fragment2 DOT!)? name_fragment2
    { __markRule(COLUMN_SPEC);}
    ;
	exception catch [RecognitionException ex] {
	    throw ex;
	}


column_name_ref:
    (identifier2 | "timestamp")
    { __markRule(COLUMN_NAME_REF);}
    ;

column_name_ddl:
    (identifier2 | "timestamp")
    { __markRule(COLUMN_NAME_DDL);}
    ;

/*
variable_name :
    (identifier2 | "timestamp")
    {  __markRule(VARIABLE_NAME); }
    ;
*/

pseudo_column :
        "user"
        {  __markRule(USER_CONST); }
        | "sysdate"
        {  __markRule(SYSDATE_CONST); }
        | "systimestamp"
        {  __markRule(SYSTIMESTAMP_CONST); }
        | "current_timestamp"
        {  __markRule(CURRENT_TIMESTAMP_CONST); }
        | "dbtimezone"
        {  __markRule(DBTIMEZONE); }
        | "rownum"
        {  __markRule(ROWNUM); }
        | (name_fragment2 DOT)? "rowid"
        {  __markRule(ROWID); }
        ;

selected_table:
    ("table")=> table_func ( alias )?
    {  __markRule(TABLE_FUNCTION); }
    | ("the") => the_proc ( alias )?
    | from_subquery   ///subquery ( alias )?
    | (table_alias ("partition"! OPEN_PAREN! partition_name CLOSE_PAREN!)?)
    ;


from_subquery:
    subquery ( alias )?
    {  __markRule(FROM_SUBQUERY); }
    ;

from_plain_table:
    table_spec ( alias )?
    {  __markRule(FROM_PLAIN_TABLE); }
    ;

ansi_spec :
      ( "inner"
        | (( "left" | "right"| "full") ("outer")? )
      )? "join"
      selected_table
      ("on" condition)?
      {  __markRule(ANSI_JOIN_TAB_SPEC); }
      ;


table_func:
    "table"! OPEN_PAREN!
        (
            select_statement
            | cast_function
            | (( name_fragment DOT )* name_fragment ~OPEN_PAREN ) => ( name_fragment DOT! )* name_fragment
            | function_call
         ) CLOSE_PAREN!
    ;


the_proc:
    "the" subquery
    ;


table_spec:
    ( schema_name DOT )? table_ref_ex //( AT_PREFIXED )?
    ;

table_ref_ex :
    table_ref
    | TABLE_NAME_SPEC
        { __markRule(TABLE_REF_WITH_LINK);}
    ;

table_alias:
    table_spec ( alias )?
    {  __markRule(TABLE_ALIAS); }
    ;

link_name:
    identifier
    ;

relational_op:
    ( EQ
    | LT
    | GT
    | NOT_EQ
    | LE
    | GE )
    {  __markRule(RELATION_OP); }
    ;


exp_set:
    ( OPEN_PAREN "select" ) => subquery
    | parentesized_exp_list
// todo -- is this correct?
    | plsql_expression
    ;
/*
connect_clause:
    ( "start" "with" condition )? // The start can precede the connect by
    "connect" "by"
        condition
        |  ( "connect" "by" condition ) ?
     "start" w2:"with" condition
    {  __markRule(CONNECT_CLAUSE); }
    ;
*/

connect_clause:
    connect_clause_internal (connect_clause_internal)?
    {  __markRule(CONNECT_CLAUSE); }
    ;

connect_clause_internal:
    ("start" "with" condition) // The start can precede the connect by
    | ("connect" "by" condition)
    ;
/*
group_clause:
    (
        ("group"! "by"! plsql_expression (c:COMMA! plsql_expression )*)
        | ( "having" condition )
    )+
    {  __markRule(GROUP_CLAUSE); }
    ;
*/
group_clause:
    "group"! "by"! plsql_expression (COMMA plsql_expression )* ( "having" condition )?
    {  __markRule(GROUP_CLAUSE); }
    ;

order_clause:
    "order"! "by"!
    sorted_def ( COMMA! sorted_def )*
    {  __markRule(ORDER_CLAUSE); }
    ;

sorted_def:
    plsql_expression (( a:"asc" | d:"desc" ) ("nulls" ("first" |"last"))? )?
    {  __markRule(SORTED_DEF); }
    ;

update_clause:
    "for" "update"
    ( "of" column_name_ref ( COMMA column_name_ref )* )?
    ( "nowait"|("wait" numeric_literal) )?
    {  __markRule(FOR_UPDATE_CLAUSE); }
    ;

insert_command:
    ("insert"! "into"!
        (
          (table_alias) => table_alias (column_spec_list)?
                ( ( "values"! (parentesized_exp_list | variable_ref)) | select_expression ) (returning)?
            {  __markRule(INSERT_COMMAND); }

         // To define the set of rows to be inserted into the target table of an INSERT statement
         | subquery ( "values"! (parentesized_exp_list | function_call) )
            {  __markRule(INSERT_INTO_SUBQUERY_COMMAND); }
         )
    ) (SEMI)?
    ;

column_spec_list:
    OPEN_PAREN column_spec ( COMMA! column_spec )* CLOSE_PAREN
    {  __markRule(COLUMN_SPEC_LIST); }
    ;

column_spec_list_wo_paren:
    column_spec ( COMMA! column_spec )*
    {  __markRule(COLUMN_SPEC_LIST); }
    ;

update_command:
    (   ( subquery_update ) => subquery_update
        | simple_update )
    ;

merge_command:
    "merge"! "into"! table_alias
    "using"! ( table_alias | from_subquery ) "on" condition
    when_action (when_action)?
    ("delete" "where" condition)?
    ;

when_action:
    "when" ("not")? "matched"! "then"!
    (
        ("update" "set" column_spec EQ plsql_expression ( COMMA! column_spec EQ plsql_expression )*)
        | insert_columns
    ) (where_condition)?
    {  __markRule(MERGE_WHEN_COMMAND); }
    ;

insert_columns:
    "insert"! ( column_spec_list )?
    "values"! parentesized_exp_list
    ;


simple_update:
    "update" (table_alias | (subquery (alias)?))
    "set" column_spec EQ plsql_expression ( COMMA! column_spec EQ plsql_expression )*
    ( where_condition ) ?
    ( returning )?
    {  __markRule(SIMPLE_UPDATE_COMMAND); }
    ;

returning:
//      RETURNING id INTO l_fst_ids(indx);
//      RETURNING id,upd_cnt INTO o_alertId,o_updateCtr;
    ("returning"! | "return"!) column_spec_list_wo_paren "into"! expr_list
    {  __markRule(RETURNING_CLAUSE); }
    ;


subquery_update:
    "update" (table_alias | (subquery (alias)?)) "set"  column_spec_list EQ subquery
    ( where_condition )?
    {  __markRule(SUBQUERY_UPDATE_COMMAND); }
    ;

delete_command:
    "delete"! ( "from"! )? table_alias ( where_condition )? (returning)?
    ;

set_transaction_command:
    "set" "transaction" r:"read" "only"
    ;

close_statement :
    "close" cursor_name_ref
    ;

fetch_statement:
    "fetch" cursor_name_ref ( "bulk" "collect" ) ? "into" variable_ref (COMMA! variable_ref )* ("limit" (identifier2|numeric_literal))?
    ;

variable_ref:
    ( name_fragment DOT )* name_fragment
    { __markRule(PLSQL_VAR_REF);}
    ;

lock_table_statement:
    "lock" "table" table_reference_list "in" lock_mode "mode" ( "nowait" )?
    ;

lock_mode:
        r1:"row" s1:"share"
        | r2:"row" e1:"exclusive"
        |s2:"share" u:"update"
        | s3:"share"
        | s4:"share" r3:"row" e2:"exclusive"
        | e3:"exclusive"
        ;

open_statement:
        o:"open" cursor_name_ref  (parentesized_exp_list)?
         ( f:"for" ( select_expression | plsql_expression ))?
         ( "using" ("in")? plsql_lvalue_list )?
    ;

rollback_statement:
        "rollback" ( "work" )?
        ( "to"! ( "savepoint" )? savepoint_name )?
        ( "comment"! string_literal! )?
        ;

savepoint_statement:
        "savepoint" savepoint_name
        ;

savepoint_name:
        identifier
        ;

// Direct mappings to lexer.
identifier :
    ( IDENTIFIER | DOUBLE_QUOTED_STRING )
    ;


/////////////////////////////////////////////////////////////////
/////       EXTERNAL TABLE SPECIFICATION        /////////////////
/////////////////////////////////////////////////////////////////

external_table_spec:
    "type" (
        ("oracle_loader" directory_spec (access_parameters)?)
        | ("oracle_datapump"  directory_spec (write_access_parameters)?)
    ) location
    {  __markRule(EXTERNAL_TABLE_SPEC);}
    ;

/*
external_table_spec:
    "external"! OPEN_PAREN! "type" (oracle_loader_params|oracle_datapump_params)location CLOSE_PAREN!
//    ("as" select_expression)? (reject_spec|parallel_clause)*
    (reject_spec|parallel_clause)*
    ;
*/
/*
oracle_loader_params:
    "oracle_loader" directory_spec (access_parameters)?
    ;

oracle_datapump_params:
    "oracle_datapump" directory_spec (write_access_parameters)?
    ;
*/

directory_spec:
    ("default")? "directory" identifier
    ;

write_access_parameters:
    "access" "parameters"
     OPEN_PAREN  write_access_parameters_spec CLOSE_PAREN
    ;

write_access_parameters_spec:
    ("nologfile"|("logfile" file_location_spec))?
    ("version" ("compatible"|"latest"|string_literal))?
    ("compression" ("enabled"|"disabled"))?
    ("encryption" ("enabled"|"disabled"))?
    {  __markRule(WRITE_ACCESS_PARAMS_SPEC);}
    ;

access_parameters:
    "access" "parameters" OPEN_PAREN loader_access_parameters CLOSE_PAREN
    ;

loader_access_parameters:
    (record_format_info)? (field_definitions)? (column_transforms)?
    {  __markRule(LOADER_ACCESS_PARAMS);}
    ;

record_format_info:
    "records" rec_format (rec_format_spec)*
    ;

rec_format:
    ("fixed" numeric_literal)
    | ("variable" numeric_literal)
    | ("delimited" "by" ("newline"|string_literal))
    ;

rec_format_spec:
(
    ("characterset" (string_literal | identifier) )
    | ("data" "is" ("big"|"little") "endian")
    | ("byte" "order" "mark" ("check"|"nocheck"))
    | ("string" "sizes" "in" ("bytes"|"characters"))
    | ("load" "when" condition)
    | ("nobadfile"|("badfile" file_location_spec) )
    | ("nodiscardfile"|("discardfile" file_location_spec) )
    | ("nologfile"|("logfile" file_location_spec) )
    | ( ("readsize"|"data_cache"|"skip") NUMBER)
    | ( "preprocessor" file_location_spec)
)
    {  __markRule(RECORD_FMT_SPEC);}
    ;

field_definitions:
    "fields" (delim_spec)? (trim_spec)?
        ("missing" "field" "values" "are" "null")?
        ("reject" "rows" "with" "all" "null" "fields")?
        (OPEN_PAREN field_list CLOSE_PAREN)?
    ;

column_transforms:
    "column" "transforms" OPEN_PAREN transform (COMMA transform)* CLOSE_PAREN
    ;

transform:
    identifier2 "from" (
        "null"
        | const_str
        | ("concat" OPEN_PAREN! (field_name|const_str) (COMMA! (field_name|const_str))* CLOSE_PAREN!)
        | ("lobfile"
                OPEN_PAREN! (field_name|(const_str COLON!)) (COMMA! (field_name|(const_str COLON!)))* CLOSE_PAREN!
                (lobfile_attr_list)?
          )
    )
    ;

lobfile_attr_list:
    ("from" OPEN_PAREN identifier2 (COMMA identifier2)* CLOSE_PAREN)
    | "clob"
    | "blob"
// todo --    | "characterset" EQ charcater set name
    ;

const_str:
    "constant" string_literal
    ;

// The delim_spec clause is used to find the end (and if ENCLOSED BY is specified, the start) of a field.
delim_spec:
    ("enclosed" "by" string_literal ( "and" string_literal )?)
    | ("terminated" "by" ("whitespace"| string_literal| DOUBLE_QUOTED_STRING)
        (("optionally")? "enclosed" "by" string_literal ( "and" string_literal )?)?
      )
    ;

// The trim_spec clause is used to specify that spaces should be trimmed from the beginning
// of a text field, the end of a text field, or both.
trim_spec:
    "lrtrim" | "notrim" | "ltrim" | "rtrim" | "ldrtrim"
    ;

field_list:
    field_spec (COMMA! field_spec)*
    {  __markRule(EXT_FIELD_SPEC_LIST);}
    ;

field_spec:
    identifier2 (pos_spec)? (datatype_spec)?
    {  __markRule(EXT_FIELD_SPEC);}
    ;

pos_spec:
    "position"! OPEN_PAREN! (ASTERISK)? (PLUS|MINUS)? numeric_literal COLON (PLUS)? numeric_literal CLOSE_PAREN!
    ;

datatype_spec:
    (("unsigned")? "integer" ("external")? (OPEN_PAREN NUMBER CLOSE_PAREN)? (delim_spec)?)
    | ( ("decimal"|"zoned")
        ( ("external") (OPEN_PAREN NUMBER CLOSE_PAREN)? (delim_spec)?
         | (OPEN_PAREN NUMBER CLOSE_PAREN) )?
      )
    | "oracle_date"
    | ("oracle_number" ("counted")?)
    | ("float" ("external")? (OPEN_PAREN NUMBER CLOSE_PAREN)? (delim_spec)?)
    | "double"
    | ("raw" (OPEN_PAREN NUMBER CLOSE_PAREN)?)
    | ( "char" (OPEN_PAREN NUMBER CLOSE_PAREN)? (delim_spec)? (trim_spec)? (date_format_spec)? )
    | (("varchar"| "varraw" | "varcharc" | "varrawc") (OPEN_PAREN NUMBER (COMMA NUMBER)? CLOSE_PAREN)?)
    ;


// DATE_FORMAT TIMESTAMP MASK  'YYYY-MM-DD HH24:MI:SS.FF9'
date_format_spec:
    "date_format" ("timestamp"|"date") ("with" "timezone")? "mask" (string_literal|DOUBLE_QUOTED_STRING)
    ;

location:
    "location"
        OPEN_PAREN
            (file_location_spec| string_literal ) (COMMA! (file_location_spec|string_literal))*
        CLOSE_PAREN
    {  __markRule(EXT_TABLE_LOCATION_SPEC);}
    ;

file_location_spec:
    (identifier COLON)? string_literal
    ;


///////////////////////////////////////////////////////////////////////////////

identifier2:
    ( IDENTIFIER
    | DOUBLE_QUOTED_STRING
    | PLSQL_ENV_VAR
    | "left"
    | "right"
    | "type"
    | "count"
    | "open"
    | "exec"
    | "execute"
    | "user"
//    | "date"  todo -- is it legal to have identifer DATE??
    | "dbtimezone"
    | "commit"
    | "rollback"
    | "savepoint"
    | "comment"
    | "charset"
    | "body"
    | "escape"
    | "reverse"
    | "exists"
    | "delete"
    | "trim"
    | "decode"
    | "flush"
    | "interval"
    | "transaction"
    | "session"
    | "close"
    | "read"
    | "write"
    | "only"
    | "normal"
    | "immediate"
    | "replace"
    | "sid"
    | "local"
    | "time"
    | "name"
//    | "true"
//    | "false"
//    | "default"
    | "package"
    | "ref"
    | "byte"
    | "interface"
    | "extract"
    | "next"
    | "column"
    | "col"
//    | "timestamp"
    | "found"
    | "notfound"
    | "rowcount"
    | "isopen"
    | "bulk_rowcount"
    | "bulk_exceptions"
    | "nocache"
    | "cache"
    | "compress"
    | "deterministic"
    | "degree"
    | "instances"
    | "range"
    | "parallel"
    | "noparallel"
    | "year"
    | "month"
    | "day"
    | "row"
    | "buffer_pool"
    | "system"
    | "managed"
    | "error_code"
    | "error_index"
    | "temporary"
    | "aggregate"
    | "current"
    | "sqlcode"
    | "sqlerrm"
    | "force"
    | "cascade"
    | "constraints"
    | "purge"
    | "validate"
    | "nextval"
    | "currval"
    | "rows"
//    | "foreign"
//    | "primary"
    | "records"
    | "parameters"
    | "access"
    | "newline"
    | "delimited"
    | "fixed"
    | "characterset"
    | "big"
    | "little"
    | "endian"
    | "mark"
//    | "check"   column not allowed
    | "nocheck"
    | "string"
    | "sizes"
    | "bytes"
    | "load"
    | "nobadfile"
    | "badfile"
    | "nodiscardfile"
    | "discardfile"
    | "nologfile"
    | "logfile"
    | "readsize"
    | "skip"
    | "data_cache"
    | "fields"
    | "missing"
    | "field"
    | "reject"
    | "with"
    | "lrtrim"
    | "notrim"
    | "ltrim"
    | "rtrim"
    | "ldtrim"
    | "position"
    | "enclosed"
    | "date_format"
    | "varraw"
    | "varcharc"
    | "varrawc"
    | "oracle_number"
    | "oracle_date"
    | "counted"
    | "external"
    | "zoned"
    | "unsigned"
    | "location"
    | "limit"
    | "unlimited"
    | "concat"
    | "clob"
    | "nclob"
    | "blob"
    | "bfile"
    | "lobfile"
    | "float"
    | "preprocessor"
    | "compression"
    | "enabled"
    | "disabled"
    | "encryption"
    | "encrypt"
    | "action"
    | "version"
    | "compatible"
    | "data"
    | "no"
    | "initrans"
    | "maxtrans"
    | "logging"
    | "nologging"
    | "quit"
    | "spool"
    | "def"
    | "define"
    | "novalidate"
    | "heap"
    | "freelists"
    | "freelist"
    | "organization"
    | "rely"
    | "at"
//    | "on"
    | "off"
    | "enable"
    | "disable"
    | "sql"
    | "before"
    | "after"
    | "directory"
    | "mask"
    | "terminated"
    | "whitespace"
    | "optionally"
    | "option"
    | "operations"
    | "startup"
    | "shutdown"
    | "servererror"
    | "logon"
    | "logoff"
    | "associate"
    | "statistics"
    | "audit"
    | "noaudit"
    | "ddl"
    | "diassociate"
    | "grant"
    | "rename"
    | "truncate"
    | "revoke"
    | "new"
    | "old"
    | "schema"
    | "hash"
    | "precision"
    | "key"
    | "monitoring"
    | "collect"
    | "nulls"
    | "first"
    | "last"
    | "timezone"
    | "language"
    | "java"
    | "store"
    | "nested"
    | "library"
    | "role"
    | "online"
    | "offline"
    | "compute"
    | "continue"
    | "var"
    | "variable"
    | "none"
    | "oserror"
    | "sqlerror"
    | "whenever"
    | "the"
    | "identified"
    | "link"
    | "by"
    | "noorder"
    | "maxvalue"
    | "minvalue"
    | "increment"
//    | "start" -- it is a keyword!!!
    | "cycle"
    | "nocycle"
    | "pctthreshold"
    | "including"
    | "repheader"
    | "repfooter"
    | "serveroutput"
    | "groups"
    | "wait"
    | "indices"
    | "subtype"
    | "tablespace"
//    | "partition"
    | "optimal"
    | "keep"
    | "sequence"
    | "under"
    | "final"
    | "timezone_hour"
    | "timezone_minute"
    | "timezone_region"
    | "timezone_abbr"
    | "hour"
    | "minute"
    | "second"
    | "cost"
    | "selectivity"
    | "functions"
    | "packages"
    | "types"
    | "indexes"
    | "indextypes"
    | "transforms"
    | "host"
    | "multiset"
    | "lag"
    | "lead"
    | "datafile"
    | "add"
    | "reuse"
    | "size"
    | "maxsize"
    | "bigfile"
    | "smallfile"
    | "extent"
    | "management"
    | "dictionary"
    | "uniform"
    | "retention"
    | "guarantee"
    | "noguarantee"
    | "tempfile"
    | "contents"
    | "datafiles"
    | "backup"
    | "coalesce"
    | "permanent"
    | "pctversion"
    | "freepools"
    | "chunk"
    | "reads"
    | "storage"
    | "locator"
    | "value"
    | "cluster"
    | "deferred"
    | "creation"
    | "segment"
    | "flash_cache"
    | "cell_flash_cache"
    | "cast"
    | "initial"
    | "minextents"
    | "maxextents"
    | "pctincrease"
    | "overflow"
    | "quota"
    | "profile"
    | "password"
    | "expire"
    | "account"
    | "lock"
    | "unlock"
    | "privileges"
    )
    ;


variable_name :
    ( IDENTIFIER
    | DOUBLE_QUOTED_STRING
    | "left"
    | "right"
//    | "type"
    | "count"
    | "open"
    | "exec"
    | "user"
    | "dbtimezone"
    | "execute"
    | "commit"
    | "rollback"
    | "savepoint"
    | "charset"
    | "body"
    | "escape"
    | "reverse"
    | "exists"
    | "delete"
    | "trim"
    | "decode"
    | "flush"
    | "interval"
    | "transaction"
    | "session"
    | "close"
    | "read"
    | "write"
    | "only"
    | "normal"
    | "immediate"
    | "replace"
    | "sid"
    | "local"
    | "time"
    | "name"
//    | "true"
//    | "false"
//    | "default"
    | "package"
//    | "function"
    | "ref"
    | "byte"
    | "interface"
    | "extract"
    | "next"
    | "column"
    | "col"
    | "timestamp"
    | "found"
    | "notfound"
    | "rowcount"
    | "isopen"
    | "bulk_rowcount"
    | "bulk_exceptions"
    | "nocache"
    | "cache"
//    | "compress"
    | "deterministic"
    | "degree"
    | "instances"
    | "range"
    | "parallel"
    | "noparallel"
    | "year"
    | "month"
    | "day"
    | "row"
    | "buffer_pool"
    | "system"
    | "managed"
    | "error_code"
    | "error_index"
    | "temporary"
    | "aggregate"
    | "current"
    | "sqlcode"
    | "sqlerrm"
    | "force"
    | "cascade"
    | "constraints"
    | "purge"
    | "validate"
    | "nextval"
    | "currval"
    | "rows"
    | "foreign"
    | "primary"
    | "records"
    | "parameters"
    | "access"
    | "newline"
    | "delimited"
    | "fixed"
    | "characterset"
    | "big"
    | "little"
    | "endian"
    | "mark"
//    | "check" variable not allowed
    | "nocheck"
    | "string"
    | "sizes"
    | "bytes"
    | "load"
    | "nobadfile"
    | "badfile"
    | "nodiscardfile"
    | "discardfile"
    | "nologfile"
    | "logfile"
    | "readsize"
    | "skip"
    | "data_cache"
    | "fields"
    | "missing"
    | "field"
    | "reject"
//    | "with"
    | "lrtrim"
    | "notrim"
    | "ltrim"
    | "rtrim"
    | "ldtrim"
    | "position"
    | "enclosed"
    | "date_format"
    | "varraw"
    | "varcharc"
    | "varrawc"
    | "oracle_number"
    | "oracle_date"
    | "counted"
    | "external"
    | "zoned"
    | "unsigned"
    | "location"
    | "limit"
    | "unlimited"
    | "concat"
    | "clob"
    | "nclob"
    | "blob"
    | "bfile"
    | "lobfile"
    | "float"
    | "preprocessor"
    | "compression"
    | "enabled"
    | "disabled"
    | "encryption"
    | "encrypt"
    | "action"
    | "version"
    | "compatible"
    | "data"
    | "no"
    | "initrans"
    | "maxtrans"
    | "logging"
    | "nologging"
    | "quit"
    | "spool"
    | "def"
    | "define"
    | "novalidate"
    | "heap"
    | "freelists"
    | "freelist"
    | "organization"
    | "rely"
//    | "at"
//    | "on"
    | "off"
    | "enable"
    | "disable"
//    | "sql"
    | "before"
    | "after"
    | "directory"
    | "mask"
    | "terminated"
    | "whitespace"
    | "optionally"
//    | "option"
    | "operations"
    | "startup"
    | "shutdown"
    | "servererror"
    | "logon"
    | "logoff"
    | "associate"
    | "statistics"
    | "audit"
    | "noaudit"
    | "ddl"
    | "diassociate"
//    | "grant"
    | "rename"
    | "truncate"
//    | "revoke"
    | "new"
    | "old"
    | "schema"
    | "hash"
    | "precision"
    | "key"
    | "monitoring"
    | "collect"
    | "nulls"
    | "first"
    | "last"
    | "timezone"
    | "language"
    | "java"
    | "store"
    | "nested"
    | "library"
    | "role"
    | "online"
    | "offline"
    | "compute"
    | "continue"
    | "var"
    | "variable"
    | "none"
    | "oserror"
    | "sqlerror"
    | "whenever"
    | "the"
//    | "identified"
    | "link"
//    | "by"
    | "noorder"
    | "maxvalue"
    | "minvalue"
    | "increment"
//    | "start" -- it is a keyword!!!
    | "cycle"
    | "nocycle"
    | "pctthreshold"
    | "including"
    | "repheader"
    | "repfooter"
    | "serveroutput"
    | "groups"
    | "wait"
    | "indices"
//    | "subtype"
    | "tablespace"
//    | "partition"
    | "optimal"
    | "keep"
    | "sequence"
    | "under"
    | "final"
    | "timezone_hour"
    | "timezone_minute"
    | "timezone_region"
    | "timezone_abbr"
    | "hour"
    | "minute"
    | "second"
    | "cost"
    | "selectivity"
    | "functions"
    | "packages"
    | "types"
//    | "indexes"
    | "indextypes"
    | "transforms"
    | "host"
    | "multiset"
    | "lag"
    | "lead"
    | "datafile"
    | "add"
    | "reuse"
//    | "size"
    | "maxsize"
    | "bigfile"
    | "smallfile"
    | "extent"
    | "management"
    | "dictionary"
    | "uniform"
    | "retention"
    | "guarantee"
    | "noguarantee"
    | "tempfile"
    | "contents"
    | "datafiles"
    | "backup"
    | "coalesce"
    | "permanent"
    | "pctversion"
    | "freepools"
    | "chunk"
    | "reads"
//    | "storage"
    | "locator"
    | "value"
//    | "cluster"
    | "deferred"
    | "creation"
    | "segment"
    | "flash_cache"
    | "cell_flash_cache"
//    | "cast"
    | "initial"
    | "minextents"
    | "maxextents"
    | "pctincrease"

    | "sysdate"
    | "systimestamp"
    | "rownum"
    | "rowid"
    | "quota"
    | "profile"
    | "password"
    | "expire"
    | "account"
    | "lock"
    | "unlock"
    | "privileges"
    )
    {  __markRule(VARIABLE_NAME); }
    ;

