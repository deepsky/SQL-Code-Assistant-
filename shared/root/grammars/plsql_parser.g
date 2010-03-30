header {
package com.deepsky.generated.plsql;
}


class PLSqlParser extends Parser;

options {
    importVocab = PLSql2;
    exportVocab = PLSql;
    k = 3;
    buildAST = true;
}

tokens {
    START_RULE;
    ERROR_TOKEN_A;
    PACKAGE_SPEC; PACKAGE_BODY; PACKAGE_NAME; PACKAGE_OBJ_BODY;
    RECORD_TYPE_DECL;
    SELECT_EXPRESSION; SELECT_EXPRESSION_UNION;
    PLSQL_BLOCK;
///    BEGIN_BLOCK;
    CURSOR_DECLARATION;
    VARIABLE_DECLARATION;
    PROCEDURE_BODY;
    FUNCTION_BODY;
    PARAMETER_SPEC;
    SQL_STATEMENT;
    IF_STATEMENT;
    LOOP_STATEMENT;
    STATEMENT;
//    SELECT_COMMAND;
    SELECT_LIST;
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
    PLSQL_EXPR_LIST; EXPR_LIST;
    DECLARE_LIST;
    FUNCTION_CALL;
    PACKAGE_INIT_SECTION;
    CONCAT;
    CALL_ARGUMENT_LIST;
    CALL_ARGUMENT;
    BASE_EXRESSION;
    COLUMN_SPEC;
    LOGICAL_AND;
    LOGICAL_OR;
    CASE_EXPRESSION_SMPL;
    CASE_EXPRESSION_SRCH;
    CASE_STATEMENT;
    COUNT_FUNC;
    TRIM_FUNC;
    COLUMN_SPEC_LIST;
    INSERT_COMMAND; UPDATE_COMMAND; DELETE_COMMAND; MERGE_COMMAND; MERGE_WHEN_COMMAND;
    STRING_LITERAL; NUMERIC_LITERAL; BOOLEAN_LITERAL;
    RETURN_TYPE;
    TYPE_SPEC;
    VARIABLE_NAME;
    COLUMN_OUTER_JOIN;
    LOGICAL_FACTOR;
    TABLE_ALIAS;
    CAST_EXPR;
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

    ASTERISK1;
    ROWCOUNT_EXRESSION;
    MULTIPLICATION_OP;
    DIVIDE_OP;
    PLUS_OP;
    MINUS_OP;
    CONCAT_OP;

    CURSOR_NAME;
    DATATYPE;
    EXTRACT_DATE_FUNC;
    ANSI_JOIN_TAB_SPEC;
    // DAY_TO_EXPR;
    INTERVAL_DAY_TO_SEC_EXPR;
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
    PRAGMA;
    TABLE_TYPE_REF;
    COLUMN_TYPE_REF;
    STATEMENT_ANNOT;
    ASTERISK_COLUMN;
    IDENT_ASTERISK_COLUMN;
    EXPR_COLUMN;
    TABLE_NAME; TABLE_NAME_WITH_LINK;
    FROM_SUBQUERY;
    FROM_PLAIN_TABLE;
    SCHEMA_NAME;
    TABLE_REF;
    COLUMN_NAME_REF;
    ARITHMETIC_EXPR;
    ASSIGNMENT_OP;
    DBTIMEZONE;
    SUBQUERY_CONDITION;
    RECORD_ITEM;
    EXCEPTION_DECL;
    EXCEPTION_PRAGMA;
    COMPLEX_NAME;
    RESTRICT_REF_PRAGMA;
    CHARACTER_SET;
    AUTHID;
    FIPSFLAG_PRAGMA; BUILTIN_PRAGMA; INTERFACE_PRAGMA; TIMESTAMPG_PRAGMA;
    TIMESTAMP_CONST;
    SUBTYPE_DECL;
    MEMBER_OF;

    SQLPLUS_SET; SQLPLUS_COMMAND; SQLPLUS_RUNSCRIPT;
    SQLPLUS_SHOW; SQLPLUS_DEFINE; SQLPLUS_VARIABLE; SQLPLUS_EXEC; SQLPLUS_WHENEVER;
    SQLPLUS_PROMPT; SQLPLUS_COLUMN; SQLPLUS_START;
    SQLPLUS_SERVEROUTPUT;  SQLPLUS_REPFOOTER;  SQLPLUS_REPHEADER;
    SQLPLUS_EXIT; SQLPLUS_QUIT; SQLPLUS_RUNSCRIPT; SQLPLUS_SPOOL;

    OR_LOGICAL; AND_LOGICAL; NOT_LOGICAL; LOGICAL_EXPR; RELATION_OP;
    SEQUENCE_EXPR;
    ALIAS_IDENT;
    COLUMN_NAME_DDL; COLUMN_DEF; TABLE_DEF;
    TABLE_COLLECTION; VARRAY_COLLECTION;
    REF_CURSOR;
    OBJECT_TYPE_DEF;

    AT_TIME_ZONE_EXPR;
    TIMEZONE_SPEC;

    ALTER_TABLE;
    CREATE_TEMP_TABLE;
    COMMENT; COMMENT_STR;
    CREATE_INDEX;
    INSERT_INTO_SUBQUERY_COMMAND;

    CONDITIONAL_COMPILATION;
    COND_COMP_SEQ;
    COND_COMP_SEQ2;
    COND_COMP_ERROR;
    COLUMN_NAME_LIST;

    SERIALLY_REUSABLE_PRAGMA;
    CREATE_VIEW;
    VIRTUAL_TABLE;
    ROWNUM;
    CUSTOM_AGGR_FUNCTION;
    LAST_STMT_RESULT_NUM;
    LAST_STMT_RESULT_BOOL;
    CURRENT_OF_CLAUSE;
    CURSOR_STATE;
    SQLCODE_SYSVAR;
    SQLERRM_SYSVAR;
    COLLECTION_METHOD_CALL; C_RECORD_ITEM_REF;
    CALLABLE_NAME_REF;
    TRUNCATE_TABLE;
    DROP_VIEW; DROP_TABLE; DROP_FUNCTION; DROP_PROCEDURE; DROP_PACKAGE; DROP_INDEX; DROP_SEQUENCE; DROP_TYPE;
    DROP_OPERATOR; DROP_SYNONYM; DROP_USER; DROP_ROLE; DROP_LIBRARY; DROP_DB_LINK; DROP_DIRECTORY;

    COLUMN_PK_SPEC;
    COLUMN_FK_SPEC;
    NOT_NULL_STMT;
    CONSTRAINT;
    PK_SPEC; FK_SPEC;
    UNIQUE_CONSTRAINT;
    CONSTRAINT_NAME;
    ADD_CONSTRAINT;
    ADD_PRIMARY_KEY;

    IOT_TYPE; HEAP_ORGINIZED; EXTERNAL_TYPE;

    //
    NAME_FRAGMENT;
    COLLECTION_METHOD_NAME;
    V_COLUMN_DEF;
    TABLE_NAME_DDL; VIEW_NAME; COMMENT_STR;
    INDEX_NAME; VIEW_NAME_DDL;
//    SEQUENCE_NAME;

    SQLPLUS_PROMPT;
    CREATE_TRIGGER; CREATE_DIRECTORY; CREATE_DB_LINK; CREATE_SEQUENCE;
    TRIGGER_FOR_EACH; TRIGGER_NAME; ALTER_TRIGGER;
    DB_EVNT_TRIGGER_CLAUSE;
    DDL_TRIGGER_CLAUSE;
    DML_TRIGGER_CLAUSE;
    TRIGGER_COLUMN_REF;
    INSTEADOF_TRIGGER;
    TRIGGER_TARGET;

    NUMERIC_LOOP_SPEC; FORALL_LOOP_SPEC; CURSOR_REF_LOOP_SPEC; CURSOR_IMPL_LOOP_SPEC;
//    CURSOR_LOOP_SPEC;
    LOOP_INDEX;
    RECORD_ITEM_NAME;
//    FORALL_STATEMENT;
    GOTO_STATEMENT;
    EXIT_STATEMENT;
    LABEL_NAME;
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

    public void handle_ws( int action ){
    }

    protected void process_wrapped_package(String package_name){
        // default action if the package is wrapped
        throw new com.deepsky.lang.plsql.parser.WrappedPackageException(package_name);
    }
}

// dirty hack, should be fixed ASAP ----------------
no_one_should_call_me:
    identifier
    { #no_one_should_call_me = #([ERROR_TOKEN_A, "ERROR_TOKEN_A" ], #no_one_should_call_me);}
    ;
/*
start_rule:
    (
        (create_or_replace (DIVIDE!)?)
        | package_spec
            { #start_rule =
                #([PACKAGE_SPEC, "PACKAGE_SPEC" ],
                #start_rule); }
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

start_rule_inner:
        (create_or_replace (DIVIDE!)?)
        | package_spec
            { #start_rule_inner = #([PACKAGE_SPEC, "PACKAGE_SPEC" ], #start_rule_inner); }
        | (function_body (DIVIDE!)?)
        | (procedure_body (DIVIDE!)?)
        | (sql_statement (SEMI!)?)
        | ("alter") => (alter_command (SEMI!)?)
        | (comment (SEMI!)?)
        | (type_definition (SEMI!)?)
        | ( drop_command (SEMI!)?)
        | ( truncate_command (SEMI!)?)
        | sqlplus_command_internal
    ;


drop_command:
    "drop"! (
        ("table"! table_name_ddl ("purge")?)
            { #drop_command = #([DROP_TABLE, "DROP_TABLE" ], #drop_command);}
        | ("view" table_name_ddl ("cascade" "constraints")?)
            { #drop_command = #([DROP_VIEW, "DROP_VIEW" ], #drop_command);}
        | ("function" callable_name_ref)
            { #drop_command = #([DROP_FUNCTION, "DROP_FUNCTION" ], #drop_command);}
        | ("procedure" callable_name_ref)
            { #drop_command = #([DROP_PROCEDURE, "DROP_PROCEDURE" ], #drop_command);}
        | ("package" ("body")? (schema_name DOT!)? package_name )  // todo - "package_name" should be replaced with "package_name_ref"
            { #drop_command = #([DROP_PACKAGE, "DROP_PACKAGE" ], #drop_command);}
        | ("index" (schema_name DOT!)? index_name ("force")? )
            { #drop_command = #([DROP_INDEX, "DROP_INDEX" ], #drop_command);}
        | ("sequence" object_name )
            { #drop_command = #([DROP_SEQUENCE, "DROP_SEQUENCE" ], #drop_command);}
// todo - "column_name_ref" should be replaced with "type_name_ref"
        | ("type" ("body")? object_name ("force"|"validate")? )
            { #drop_command = #([DROP_TYPE, "DROP_TYPE" ], #drop_command);}
        | ( ("public")? "synonym" object_name  )
            { #drop_command = #([DROP_SYNONYM, "DROP_SYNONYM" ], #drop_command);}
        | ("operator"  object_name  ("force")? )
            { #drop_command = #([DROP_OPERATOR, "DROP_OPERATOR" ], #drop_command);}
        | ("user"  object_name ("cascade")? )
            { #drop_command = #([DROP_USER, "DROP_USER" ], #drop_command);}
        | ("role"  object_name )
            { #drop_command = #([DROP_ROLE, "DROP_ROLE" ], #drop_command);}
        | ("directory"  object_name )
            { #drop_command = #([DROP_DIRECTORY, "DROP_DIRECTORY" ], #drop_command);}
        | ("library"  object_name )
            { #drop_command = #([DROP_LIBRARY, "DROP_LIBRARY" ], #drop_command);}
        | ("database" "link" object_name)
            { #drop_command = #([DROP_DB_LINK, "DROP_DB_LINK" ], #drop_command);}
     )
    ;

truncate_command:
    "truncate"! "table"! table_name_ddl
    { #truncate_command = #([TRUNCATE_TABLE, "TRUNCATE_TABLE" ], #truncate_command);}
    ;

comment:
    "comment"! "on"! (
        ("table"! table_name_ddl "is"! comment_string)
        | ("column"! table_name_ddl DOT column_name_ddl "is"! comment_string)
     )
    { #comment = #([COMMENT, "COMMENT" ], #comment);}
    ;

comment_string:
    string_literal
    { #comment_string = #([COMMENT_STR, "COMMENT_STR" ], #comment_string);}
    ;

column_def:
//    column_name_ddl datatype (not_null|("default" ("sysdate"|numeric_literal)))?
    column_name_ddl type_spec ("default" ("sysdate"|numeric_literal|string_literal))? (not_null)?
    (pk_spec|fk_spec|column_constraint)?
    { #column_def = #([COLUMN_DEF, "COLUMN_DEF" ], #column_def);}
    ;

not_null:
     ("not")? "null"
    { #not_null = #([NOT_NULL_STMT, "NOT_NULL_STMT" ], #not_null);}
    ;

row_movement_clause:
    ("disable"|"enable") "row" "movement"
    ;

pk_spec:
    "primary" "key" ("disable"|"enable")?
    { #pk_spec = #([COLUMN_PK_SPEC, "COLUMN_PK_SPEC" ], #pk_spec);}
    ;

fk_spec:
    "references"! table_name_ddl OPEN_PAREN! column_name_ddl CLOSE_PAREN!
    { #fk_spec = #([COLUMN_FK_SPEC, "COLUMN_FK_SPEC" ], #fk_spec);}
    ;

column_constraint:
    "constraint" identifier (
        not_null
// todo        | ("unique" (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?)
        | ("primary" "key" OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN ("disable"|"enable")? )
// todo        | ("check" condition )
    )
    ;

sqlplus_command_internal:
    (sqlplus_command)+
    { #sqlplus_command_internal = #([SQLPLUS_COMMAND, "SQLPLUS_COMMAND" ], #sqlplus_command_internal);}
    ;

sqlplus_command:
    ("set" (identifier2|"long")? (identifier2|numeric_literal|string_literal)*   ( SEMI! )*)
        { #sqlplus_command = #([SQLPLUS_SET, "SQLPLUS_SET" ], #sqlplus_command);}
    | ("show"  identifier ( base_expression ) *  ( SEMI! )*)
        { #sqlplus_command = #([SQLPLUS_SHOW, "SQLPLUS_SHOW" ], #sqlplus_command);}
    | (("var"|"variable")  identifier2 datatype  ( SEMI! )*)
        { #sqlplus_command = #([SQLPLUS_VARIABLE, "SQLPLUS_VARIABLE" ], #sqlplus_command);}
    | (("col"|"column")  identifier2 (identifier2)*  ( SEMI! )*)
        { #sqlplus_command = #([SQLPLUS_COLUMN, "SQLPLUS_COLUMN" ], #sqlplus_command);}
    | (("exec"|"execute")  sqlplus_exec_statement ( SEMI! )*)
        { #sqlplus_command = #([SQLPLUS_EXEC, "SQLPLUS_EXEC" ], #sqlplus_command);}
    | ("whenever" ("sqlerror"| "oserror") ("exit"|"continue"|"commit"|"rollback"|"none") (SEMI!)* )
        { #sqlplus_command = #([SQLPLUS_WHENEVER, "SQLPLUS_WHENEVER" ], #sqlplus_command);}
    | (("def"|"define") identifier2 (EQ (plsql_expression | DOUBLE_QUOTED_STRING | STORAGE_SIZE))? )
        { #sqlplus_command = #([SQLPLUS_DEFINE, "SQLPLUS_DEFINE" ], #sqlplus_command);}
    | ("prompt" (~CUSTOM_TOKEN)* CUSTOM_TOKEN)
        { #sqlplus_command = #([SQLPLUS_PROMPT, "SQLPLUS_PROMPT" ], #sqlplus_command);}
    | "rem" (~CUSTOM_TOKEN)* CUSTOM_TOKEN
        { #sqlplus_command = #([SQLPLUS_PROMPT, "SQLPLUS_PROMPT" ], #sqlplus_command);}
    | "exit"  (identifier2|numeric_literal)?
        { #sqlplus_command = #([SQLPLUS_EXIT, "SQLPLUS_EXIT" ], #sqlplus_command);}
    | ("quit" (SEMI!)?)
        { #sqlplus_command = #([SQLPLUS_QUIT, "SQLPLUS_QUIT" ], #sqlplus_command);}
    | "spool" ( "off"|(identifier4 (DOT! identifier4)*) )
        { #sqlplus_command = #([SQLPLUS_SPOOL, "SQLPLUS_SPOOL" ], #sqlplus_command);}
    | ( ("sta"|"start") sqlplus_path )    // identifier4 (DOT! identifier4)* )
        { #sqlplus_command = #([SQLPLUS_START, "SQLPLUS_START" ], #sqlplus_command);}
    | ( "repfooter" ("off"|"on"|("is" "null")) (SEMI!)?)
        { #sqlplus_command = #([SQLPLUS_REPFOOTER, "SQLPLUS_REPFOOTER" ], #sqlplus_command);}
    | ( "repheader" ("off"|"on"|("is" "null")) (SEMI!)?)
        { #sqlplus_command = #([SQLPLUS_REPHEADER, "SQLPLUS_REPHEADER" ], #sqlplus_command);}
    | ( "serveroutput" ("off"|"on") (SEMI!)?)
        { #sqlplus_command = #([SQLPLUS_SERVEROUTPUT, "SQLPLUS_SERVEROUTPUT" ], #sqlplus_command);}

    | ("begin"|"declare") => (begin_block (SEMI!)? (DIVIDE!)? )
    | (AT_PREFIXED (~CUSTOM_TOKEN)* CUSTOM_TOKEN )
        { #sqlplus_command = #([SQLPLUS_RUNSCRIPT, "SQLPLUS_RUNSCRIPT" ], #sqlplus_command);}
    ;


sqlplus_exec_statement:
    ( plsql_lvalue ASSIGNMENT_EQ) => assignment_statement
    | ( procedure_call ( DOT procedure_call )*)
    ;

sqlplus_path:
    (DOUBLE_DOT|DOT|identifier3) ((DIVIDE|BACKSLASH) (DOUBLE_DOT|DOT|identifier3))*
    ;


create_or_replace:
    "create"! ( "or"! "replace"! )? ("force")?
    (
        package_spec
            { #create_or_replace = #([PACKAGE_SPEC, "PACKAGE_SPEC" ], #create_or_replace); }
        | package_body
        | procedure_body
            { #create_or_replace = #([CREATE_PROCEDURE, "CREATE_PROCEDURE" ], #create_or_replace);}
        | function_body
            { #create_or_replace = #([CREATE_FUNCTION, "CREATE_FUNCTION" ], #create_or_replace);}
        | create_view
            { #create_or_replace = #([CREATE_VIEW, "CREATE_VIEW" ], #create_or_replace);}
        | (type_definition (SEMI!)?)
        | (create_table2 (SEMI!)?)
            { #create_or_replace = #([TABLE_DEF, "TABLE_DEF" ], #create_or_replace);}
        | (create_temp_table2 (SEMI!)?)
            { #create_or_replace = #([CREATE_TEMP_TABLE, "CREATE_TEMP_TABLE" ], #create_or_replace);}
        | (create_index2 (SEMI!)?)
            { #create_or_replace = #([CREATE_INDEX, "CREATE_INDEX" ], #create_or_replace);}
        | (create_trigger (SEMI!)?)
            { #create_or_replace = #([CREATE_TRIGGER, "CREATE_TRIGGER" ], #create_or_replace);}
        | (create_directory (SEMI!)?)
            { #create_or_replace = #([CREATE_DIRECTORY, "CREATE_DIRECTORY" ], #create_or_replace);}
        | (create_db_link (SEMI!)?)
            { #create_or_replace = #([CREATE_DB_LINK, "CREATE_DB_LINK" ], #create_or_replace);}
        | (create_sequence (SEMI!)?)
            { #create_or_replace = #([CREATE_SEQUENCE, "CREATE_SEQUENCE" ], #create_or_replace);}
    )
    ;

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


create_db_link:
    "database" "link" object_name
    "connect" "to" identifier2 "identified" "by" DOUBLE_QUOTED_STRING
    ("using" (identifier2|string_literal))?
    ;

// -------------------------------------------------------------------
// [TRIGGER START] ----------------------------------
// -------------------------------------------------------------------

create_trigger:
    "trigger"! trigger_name
    // BEFORE INSERT OR UPDATE ON xdv_dev_grp_mobile_station_it
    // AFTER INSERT OR UPDATE OR DELETE ON xdv_dev_grp_mobile_station_it
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
    { #db_event_trigger = #([DB_EVNT_TRIGGER_CLAUSE, "DB_EVNT_TRIGGER_CLAUSE" ], #db_event_trigger);}
    ;

ddl_trigger:
    ("create" | "alter" | "drop" | "analyze" | ("associate" "statistics") | "audit" | "noaudit"
    | "comment" | "ddl" | ("diassociate" "statistics") | "grant" | "rename" | "revoke" | "truncate")
    "on"! trigger_target
    { #ddl_trigger = #([DDL_TRIGGER_CLAUSE, "DDL_TRIGGER_CLAUSE" ], #ddl_trigger);}
    ;

dml_trigger:
    insert_update_delete ( "or"! insert_update_delete)* "on"! table_name_ddl
    { #dml_trigger = #([DML_TRIGGER_CLAUSE, "DML_TRIGGER_CLAUSE" ], #dml_trigger);}
    ;

trigger_target:
    "schema" | "database"
    { #trigger_target = #([TRIGGER_TARGET, "TRIGGER_TARGET" ], #trigger_target);}
    ;

instead_of_trigger:
    "instead"! "of"! ("delete"|"insert"|"update") "on"! view_name_ddl
    { #instead_of_trigger = #([INSTEADOF_TRIGGER, "INSTEADOF_TRIGGER" ], #instead_of_trigger);}
    ;

for_each:
    "for"! "each"! "row"!
    { #for_each = #([TRIGGER_FOR_EACH, "TRIGGER_FOR_EACH" ], #for_each);}
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
    { #trigger_name = #([TRIGGER_NAME, "TRIGGER_NAME" ], #trigger_name);}
    ;

alter_trigger:
    "alter"! "trigger"! trigger_name enable_disable_clause
    { #alter_trigger = #([ALTER_TRIGGER, "ALTER_TRIGGER" ], #alter_trigger);}
    ;
// -------------------------------------------------------------------
// [TRIGGER END] ----------------------------------
// -------------------------------------------------------------------


create_index2:
    ("unique"|"bitmap")? "index"! index_name "on"! table_name_ddl
    OPEN_PAREN index_column_spec_list CLOSE_PAREN
    (physical_properties|table_properties)*
    ;

index_column_spec_list:
    identifier2 ("asc"|"desc")? (COMMA! identifier2 ("asc"|"desc")?)*
    ;


create_directory:
    "directory" object_name ("as" string_literal)?
    ;
// -------------------------------------------------------------------
// [CREATE TABLE START] ----------------------------------
// -------------------------------------------------------------------

create_table2:
    "table"! (schema_name DOT!)? table_name_ddl
    (OPEN_PAREN! column_def (COMMA! column_def)* (COMMA! constaraint)* CLOSE_PAREN!)?
    (organization_spec)? (physical_properties|table_properties)*
    ("as" select_expression)?
    ;

create_temp_table2:
    ("global")? "temporary"! "table"! table_name_ddl
    (OPEN_PAREN! column_def (COMMA! column_def)* (COMMA! constaraint)* CLOSE_PAREN!)?
    ("on" "commit" ("preserve" | "delete") "rows" )
    (cache_clause)?
    ("as" select_expression)?
    ;

physical_properties:
    segment_attributes_clause
//    | organization_spec
//    | cluster_clause 
    ;

segment_attributes_clause:
    physical_attributes_clause
    | ("tablespace" identifier2)
    | "online"
    | ("compute" "statistics" ("parallel"|"noparallel"|identifier2)? )
    | logging_clause
    | table_compression
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
    | storage_spec
    ;

table_properties:
    table_partitioning_clause
    | cache_clause
    | parallel_clause
    | alter_table_options
    | row_movement_clause
    | monitoring_clause
    ;

cache_clause:
    "cache"|"nocache"
    ;

monitoring_clause:
    "monitoring" | "nomonitoring"
    ;

table_partitioning_clause:
    range_partitions
    | hash_partitions
    | local_partitioned_index // -- "create index" specific
// todo   | list_partitions
// todo   | reference_partitions
// todo   | composite_range_partitions
// todo   | composite_list_partitions
// todo  | system_partitioning
    ;

range_partitions:
    "partition" "by" "range" OPEN_PAREN! identifier2 (COMMA! identifier2)* CLOSE_PAREN!
    ("interval" plsql_expression ("store" "in" OPEN_PAREN identifier2 (COMMA! identifier2)* OPEN_PAREN)?)?
    OPEN_PAREN partition_item (COMMA! partition_item)* CLOSE_PAREN
    ;

partition_item:
    "partition" (identifier2)? range_values_clause (table_partition_description)+
    ;

range_values_clause:
    "values" "less" "than" OPEN_PAREN (numeric_literal | "maxvalue") (COMMA! (numeric_literal | "maxvalue"))* CLOSE_PAREN
    ;

table_partition_description:
    segment_attributes_clause
    | table_compression
    | "overflow"
// todo  | lob_storage_clause
    ;

hash_partitions:
    "partition" "by" "hash" OPEN_PAREN! identifier2 (COMMA! identifier2)* CLOSE_PAREN!
    (individual_hash_partitions | hash_partitions_by_quantity)
    ;

individual_hash_partitions:
    OPEN_PAREN "partition" (identifier2)? (partition_storage_clause)* (COMMA! "partition" (identifier2)? (partition_storage_clause)* )* CLOSE_PAREN
    ;

partition_storage_clause:
    ("tablespace" identifier2)
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
            { #organization_spec = #([IOT_TYPE, "IOT_TYPE" ], #organization_spec);}
        | "heap"!
            { #organization_spec = #([HEAP_ORGINIZED, "HEAP_ORGINIZED" ], #organization_spec);}
        | ("external") => external_table_spec
            { #organization_spec = #([EXTERNAL_TYPE, "EXTERNAL_TYPE" ], #organization_spec);}
        )
    ;

parallel_clause:
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
    ;

// REJECT LIMIT UNLIMITED
reject_spec:
    "reject" "limit" ("unlimited"| NUMBER)
    ;

storage_spec:
    "storage" OPEN_PAREN (storage_params)+ CLOSE_PAREN
    ;

storage_params:
    ("initial" (STORAGE_SIZE|numeric_literal))
    | ("next" (STORAGE_SIZE|numeric_literal))
    | ("minextents" numeric_literal)
    | ("maxextents" (numeric_literal|"unlimited"))
    | ("pctincrease" numeric_literal)
    | ("freelists" numeric_literal)
    | ("freelist" "groups" numeric_literal)
    | ("optimal" (STORAGE_SIZE|"null")?)
    | ("buffer_pool" ( "keep" | "recycle" | "default"))
    | "encrypt"
    ;

/*
partition_spec:
    ( ("partition" "by" "range" OPEN_PAREN identifier2 CLOSE_PAREN)
     /// | "local"
    )
    OPEN_PAREN partition_item (COMMA partition_item)* CLOSE_PAREN
    ;

tablespace_params:
    ("tablespace" identifier)
    | ("pctused" numeric_literal)
    | ("pctfree" numeric_literal)
    | ("initrans" numeric_literal)
    | ("maxtrans" numeric_literal)
    | storage_spec
    ;

*/

constaraint:
    "constraint"! constraint_name (
        pk_spec_constr
        | fk_spec_constr
        | ("check" condition)
        | unique_contsr
     )
    { #constaraint = #([CONSTRAINT, "CONSTRAINT" ], #constaraint);}
    ;

pk_spec_constr:
    "primary"! "key"! OPEN_PAREN! column_name_ddl_list CLOSE_PAREN!
    { #pk_spec_constr = #([PK_SPEC, "PK_SPEC" ], #pk_spec_constr);}
    ;

fk_spec_constr:
    "foreign"! "key"! OPEN_PAREN! column_name_ddl_list CLOSE_PAREN!
    "references"! table_name_ddl OPEN_PAREN! column_name_ddl_list CLOSE_PAREN!
    ("disable")? ("on" "update" referential_actions)? ("on" "delete" referential_actions)?
    { #fk_spec_constr = #([FK_SPEC, "FK_SPEC" ], #fk_spec_constr);}
    ;

unique_contsr:
    "unique" OPEN_PAREN column_name_ddl (COMMA! column_name_ddl)* CLOSE_PAREN
    { #unique_contsr = #([UNIQUE_CONSTRAINT, "UNIQUE_CONSTRAINT" ], #unique_contsr);}
    ;

column_name_ddl_list:
    column_name_ddl (COMMA! column_name_ddl)*
    { #column_name_ddl_list = #([COLUMN_NAME_LIST, "COLUMN_NAME_LIST" ], #column_name_ddl_list);}
    ;

referential_actions:
    "cascade"|"restrict"|("no" "action")|("set" "null")|("set" "default")
    ;

constraint_name:
    identifier
    { #constraint_name = #([CONSTRAINT_NAME, "CONSTRAINT_NAME" ], #constraint_name);}
    ;
// -------------------------------------------------------------------
// [CREATE TABLE END] ------------------------------------------------
// -------------------------------------------------------------------


// -------------------------------------------------------------------
// [ALTER TABLE START] ------------------------------------------------
// -------------------------------------------------------------------
alter_table:
    "alter"! "table"! table_name_ddl (constraint_clause)? (alter_table_options)?
    { #alter_table = #([ALTER_TABLE, "ALTER_TABLE" ], #alter_table);}
    ;

constraint_clause:
    ("add" (add_syntax_1 | (OPEN_PAREN! add_syntax_2 CLOSE_PAREN!) ) )
    | ("modify" (modify_constraint_clause | (OPEN_PAREN! modify_syntax_2 CLOSE_PAREN!) | modify_syntax_1) )
    | ("rename"
            (   ("constraint" identifier2 "to" identifier2)
                | ("to" identifier2)
                | ("column" identifier2 "to" identifier2) )  
      )
    | "drop" ( ("column" identifier2) | drop_clause )
    ;

modify_constraint_clause:
    ("constraint" identifier ("rely")? ("disable"|"enable")? ("validate"|"novalidate")?)
    | ("primary" "key" (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?)
    | ("unique" OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)
    ;

add_syntax_1:
    column_def_alter
    | inline_out_of_line_constraint
    ;

add_syntax_2:
    (column_def_alter) => (column_def_alter (COMMA! column_def_alter)*)
    | inline_out_of_line_constraint
    ;

modify_syntax_1:
    column_def_alter
    ;

modify_syntax_2:
    column_def_alter (COMMA! column_def_alter)*
    ;

column_def_alter:
    column_name_ddl (datatype)? ( not_null|("default" ("sysdate"|numeric_literal)) )? (pk_spec|fk_spec|column_constraint)?
    ;

constraint:
    inline_out_of_line_constraint
// todo    inline_constraint
// todo    | out_of_line_constraint
// todo    | inline_ref_constraint
// todo    | out_of_line_ref_constraint
    ;
/*
   ADD CONSTRAINT XDV_TAM_1HR_DDE_FK FOREIGN KEY (DDE_ID)
      REFERENCES XDV_DTM_HRDATE_DT (ID)
       RELY DISABLE NOVALIDATE;
*/

inline_out_of_line_constraint:
    ("constraint" identifier)? (
        not_null
        | ("unique" (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?)
        | ("primary" "key" (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)?) // ("rely" "using" "index" identifier2)? ("enable")? )
        | ("foreign" "key"
                (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)
                "references" identifier2 (OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN)
                ("rely")? ("disable"|"enable")? ("validate"|"novalidate")? ("on" "delete" ("cascade"|("set" "null")) )?
            )
        | ("check" condition)
    )
    (using_index_clause)?
    ;

drop_clause:
    ("primary" "key" ("cascade")? (("keep"|"drop") "index")? )
    | ("unique" OPEN_PAREN identifier2 (COMMA! identifier2)* CLOSE_PAREN ("cascade")? (("keep"|"drop") "index")?)
    | ("constraint" identifier ("cascade")?)
    ;

enable_disable_clause2:
    using_index_clause ("cascade")? (("keep"|"drop") "index")?
    ;

using_index_clause:
    ("rely")? "using" "index" (
        ((schema_name DOT)? identifier2)
// todo        | (OPEN_PAREN create_index_statement CLOSE_PAREN)
        | index_properties
    )  ("enable")?
    ;

index_properties:
    ((index_attributes)+)
    | global_partitioned_index
    | local_partitioned_index
    ;

index_attributes:
    ("tablespace" (identifier2|"default"))
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
    "partition" (identifier2)? "values" "less" "than"
            OPEN_PAREN numeric_literal (COMMA! numeric_literal)* CLOSE_PAREN segment_attributes_clause 
    ;

local_partitioned_index:
    "local" (
        on_range_partitioned_table
//        | on_list_partitioned_table
//        | on_hash_partitioned_table
//        | on_comp_partitioned_table
    )?
    ;

on_range_partitioned_table:
    OPEN_PAREN local_partition_item (COMMA! local_partition_item)* CLOSE_PAREN
    ;

local_partition_item:
     "partition" (identifier2)? (segment_attributes_clause|table_compression)*
     ;

// -------------------------------------------------------------------
// [ALTER TABLE END] ------------------------------------------------
// -------------------------------------------------------------------


type_definition:
    "type"! type_name ("as"!|"is"!)
    (
        ("object"! OPEN_PAREN! record_item (COMMA! record_item )* CLOSE_PAREN!)
            { #type_definition = #([OBJECT_TYPE_DEF, "OBJECT_TYPE_DEF" ], #type_definition);}
        | ("table"! "of"! type_spec ("index"! "by"! type_spec)? ("not" "null")? )
            { #type_definition = #([TABLE_COLLECTION, "TABLE_COLLECTION" ], #type_definition);}
        | ("record" OPEN_PAREN! record_item ( COMMA! record_item )* CLOSE_PAREN!)
            { #type_definition = #([RECORD_TYPE_DECL, "RECORD_TYPE_DECL" ], #type_definition); }
        | ( "ref" "cursor"! ( "return"! record_name (PERCENTAGE "rowtype")? )? )
            { #type_definition = #([REF_CURSOR, "REF_CURSOR" ], #type_definition); }
        | ( "varray"! OPEN_PAREN! plsql_expression CLOSE_PAREN! "of"! type_spec ("not" "null")?)
            { #type_definition = #([VARRAY_COLLECTION, "VARRAY_COLLECTION" ], #type_definition); }
    )
    ;


create_view:
    "view"! view_name_ddl (OPEN_PAREN! v_column_def (COMMA! v_column_def)* CLOSE_PAREN!)?
    "as"! select_expression  ("with" (("check" "option")|("read" "only")))? (SEMI!)?
    ;

v_column_def:
    name_fragment
    { #v_column_def = #([V_COLUMN_DEF, "V_COLUMN_DEF" ], #v_column_def);}
    ;

package_spec :
    "package"! (schema_name DOT!)? o:package_name (a:"authid" {#a.setType(AUTHID);} identifier)?
    (   ("wrapped" {
//                String package_name = #o.getFirstChild().getText();
                String package_name = #o.getText();
//                throw new com.deepsky.lang.plsql.parser.WrappedPackageException(package_name);
                process_wrapped_package(package_name);
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
//    | (if_cond_cmpl condition
//            then_cond_cmpl cond_comp_seq (else_cond_cmpl cond_comp_seq)? end_cond_cmpl
      )
    {#package_obj_spec_ex = #([CONDITIONAL_COMPILATION, "CONDITIONAL_COMPILATION" ], #package_obj_spec_ex);}
    ;


cond_comp_seq:
    (error_cond_compliation)* (package_obj_spec)*
    {#cond_comp_seq = #([COND_COMP_SEQ, "COND_COMP_SEQ" ], #cond_comp_seq);}
    ;

error_cond_compliation:
    ERROR_COND_CMPL string_literal END_COND_CMPL
//    error_cond_cmpl string_literal end_cond_cmpl
    {#error_cond_compliation = #([COND_COMP_ERROR, "COND_COMP_ERROR" ], #error_cond_compliation);}
    ;

package_body :
    "package"! ("body"!)? (schema_name DOT!)? o:package_name ("is"! | "as"!)
    (serially_reusable_pragma SEMI!)?
    ( package_obj_body )*
    (package_init_section)? "end"! (package_name! )? SEMI!
    { #package_body = #([PACKAGE_BODY, "PACKAGE_BODY" ], #package_body);}
    ;

package_init_section:
    "begin"  seq_of_statements
    { #package_init_section = #([PACKAGE_INIT_SECTION, "PACKAGE_INIT_SECTION" ], #package_init_section);}
    ;

package_obj_spec:
    variable_declaration
    | subtype_declaration
    | cursor_declaration
    | cursor_spec
    | (type_definition SEMI!)
//    | plsql_table_declaration
    | procedure_spec
    | function_spec
    | exception_declaration
    | exception_pragma
    | restrict_ref_pragma
    | interface_pragma
    | builtin_pragma
    | fipsflag_pragma
    | timestamp_pragma
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
    {#condition_compilation = #([CONDITIONAL_COMPILATION, "CONDITIONAL_COMPILATION" ], #condition_compilation);}
    ;

cond_comp_seq2:
    (error_cond_compliation)*
    ((function_declaration ("is"|"as")) => function_body
     | (procedure_declaration ("is"|"as")) => procedure_body
     | package_obj_spec)*
    {#cond_comp_seq2 = #([COND_COMP_SEQ2, "COND_COMP_SEQ2" ], #cond_comp_seq2);}
    ;

variable_declaration :
    variable_name ("constant")?  type_spec ("not" "null")? ((ASSIGNMENT_EQ|default1) plsql_expression)? SEMI!
    {#variable_declaration = #([VARIABLE_DECLARATION, "VARIABLE_DECLARATION" ], #variable_declaration);}
    ;

subtype_declaration :
    "subtype" (type_name|datatype) "is"
    (
        (type_spec) => type_spec
        | (table_name PERCENTAGE! "type"! )
    )
    ("not" "null")? SEMI!
    { #subtype_declaration = #([SUBTYPE_DECL, "SUBTYPE_DECL"], #subtype_declaration); }
    ;

cursor_declaration :
    "cursor" cursor_name (OPEN_PAREN! argument_list CLOSE_PAREN!)?
     "is"! select_command SEMI!
    {#cursor_declaration = #([CURSOR_DECLARATION, "CURSOR_DECLARATION" ], #cursor_declaration);}
    ;

package_obj_body:
    (function_declaration ("is"|"as")) => function_body
    | (procedure_declaration ("is"|"as")) => procedure_body
    | package_obj_spec
    | condition_compilation
    ;

seq_of_statements:
    (statement_tmpl)+
//    ( (statement SEMI!) | (START_LABEL label_name END_LABEL) )
//        ( (statement SEMI!) | (START_LABEL label_name END_LABEL))*
    {#seq_of_statements = #([STATEMENT_LIST, "STATEMENT_LIST" ], #seq_of_statements);}
    ;


statement_tmpl:
    (statement SEMI!)
    | (START_LABEL label_name END_LABEL)
    | (IF_COND_CMPL condition THEN_COND_CMPL seq_of_statements (ELSE_COND_CMPL seq_of_statements)?  END_COND_CMPL )
//    | (if_cond_cmpl condition then_cond_cmpl seq_of_statements (else_cond_cmpl seq_of_statements)?  end_cond_cmpl )
    { #statement_tmpl = #([CONDITIONAL_COMPILATION, "CONDITIONAL_COMPILATION" ], #statement_tmpl);}
    ;

statement :
        ("begin" | "declare") => begin_block   //// (("declare" declare_list)? plsql_block) /// begin_block   ///plsql_block
        | ( "loop" | "for" | "while" ) => loop_statement
          {#statement = #([LOOP_STATEMENT, "LOOP_STATEMENT" ], #statement);}
        | ( "forall" ) => forall_loop
          {#statement = #([LOOP_STATEMENT, "LOOP_STATEMENT" ], #statement);}
        | ("if" ) => if_statement
        | ( "goto" ) => goto_statement
          {#statement = #([GOTO_STATEMENT, "GOTO_STATEMENT" ], #statement);}
        | ( "raise" )=> raise_statement
          {#statement = #([RAISE_STATEMENT, "RAISE_STATEMENT" ], #statement);}
        | ( "exit" ) => exit_statement
          {#statement = #([EXIT_STATEMENT, "EXIT_STATEMENT" ], #statement);}
        | null_statement
        | ( "return" ) => return_statement
          {#statement = #([RETURN_STATEMENT, "RETURN_STATEMENT" ], #statement);}
        | ( "case" ) => case_statement
          {#statement = #([CASE_STATEMENT, "CASE_STATEMENT" ], #statement);}
        | ( "select" | "update" | "insert" | "delete" |                 /// "with" | ("alter" ("system" | "session") ) |
            "grant" | "commit" |                                // | "lock" | "execute" | "open" | "fetch" | "close" "set" |
            "rollback" | "merge") => sql_statement
        | ("execute") => immediate_command
        | ("lock") => lock_table_statement
        | ("open") => open_statement
        | ("close") => close_statement
        | ("fetch") => fetch_statement
        | set_transaction_command
        | savepoint_statement
        | alter_command
        | ( plsql_lvalue ASSIGNMENT_EQ) => assignment_statement
        | ( procedure_call ( DOT procedure_call )*)
    ;


sql_percentage:
    "sql" PERCENTAGE (
        "found"
            {#sql_percentage = #([LAST_STMT_RESULT_BOOL, "LAST_STMT_RESULT_BOOL"], #sql_percentage);}
        | "notfound"
            {#sql_percentage = #([LAST_STMT_RESULT_BOOL, "LAST_STMT_RESULT_BOOL"], #sql_percentage);}
        | "rowcount"
            {#sql_percentage = #([LAST_STMT_RESULT_NUM, "LAST_STMT_RESULT_NUM"], #sql_percentage);}
        | "isopen"
            {#sql_percentage = #([LAST_STMT_RESULT_BOOL, "LAST_STMT_RESULT_BOOL"], #sql_percentage);}
        | ("bulk_rowcount" OPEN_PAREN plsql_expression CLOSE_PAREN )
            {#sql_percentage = #([LAST_STMT_RESULT_NUM, "LAST_STMT_RESULT_NUM"], #sql_percentage);}
        | "bulk_exceptions" (
           (OPEN_PAREN plsql_expression CLOSE_PAREN DOT ("error_index" | "error_code"))
            | ((DOT) "count")
          )
            {#sql_percentage = #([LAST_STMT_RESULT_NUM, "LAST_STMT_RESULT_NUM"], #sql_percentage);}
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
    {#case_statement = #([CASE_STATEMENT, "CASE_STATEMENT"], #case_statement);}
    ;


declare_spec:
        (variable_declaration
        | subtype_declaration
        | cursor_declaration
        | exception_declaration
        | exception_pragma
        | (type_definition SEMI!)
        | pragma
//        | plsql_table_declaration
        |( (function_declaration) => function_body
           | function_spec )
        |( (procedure_declaration) => procedure_body
           | procedure_spec)
        )
        ;

pragma:
    "pragma"! "autonomous_transaction" SEMI!
    {#pragma = #([PRAGMA, "PRAGMA" ], #pragma);}
    ;

assignment_statement :
    plsql_lvalue p:ASSIGNMENT_EQ! {#p.setType(ASSIGNMENT_OP);} condition
    {#assignment_statement =   #([ASSIGNMENT_STATEMENT, "ASSIGNMENT_STATEMENT" ], #assignment_statement);}
    ;

rvalue :
    (("prior")? ( name_fragment DOT )* name_fragment ~OPEN_PAREN ) => (("prior"!)? ( name_fragment DOT! )* name_fragment)
        {#rvalue = #([VAR_REF, "VAR_REF" ], #rvalue);} /// !!!!!! actual type needs to be resolved

//    | (COLON_NEW|COLON_OLD) => (COLON_NEW|COLON_OLD) DOT! name_fragment
    | (COLON ("new"|"old") DOT) => (COLON ("new"|"old") DOT! name_fragment)
        {#rvalue = #([TRIGGER_COLUMN_REF, "TRIGGER_COLUMN_REF" ], #rvalue);}

    | ( function_call ( d:DOT! ((function_call) => function_call | c_record_item_ref ) )* )
     {
        if(#d != null){
            #rvalue = #([COLLECTION_METHOD_CALL, "COLLECTION_METHOD_CALL" ], #rvalue);
        }
     }
    ;

c_record_item_ref:
    identifier2
    { #c_record_item_ref = #([C_RECORD_ITEM_REF, "C_RECORD_ITEM_REF" ], #c_record_item_ref); }
    ;

plsql_lvalue :
    (( name_fragment DOT )* name_fragment ~OPEN_PAREN ) => ( name_fragment DOT! )* name_fragment
        {#plsql_lvalue = #([PLSQL_VAR_REF, "PLSQL_VAR_REF" ], #plsql_lvalue);}
//    | (COLON_NEW|COLON_OLD) => (COLON_NEW|COLON_OLD) DOT! name_fragment
    | (COLON ("new"|"old") DOT) => (COLON ("new"|"old") DOT! name_fragment)
        {#plsql_lvalue = #([TRIGGER_COLUMN_REF, "TRIGGER_COLUMN_REF" ], #plsql_lvalue);}

    | ( function_call ( d:DOT! ((function_call) => function_call | c_record_item_ref ) )* )
     {
        if(#d != null){
            #plsql_lvalue = #([COLLECTION_METHOD_CALL, "COLLECTION_METHOD_CALL" ], #plsql_lvalue);
        }
     }
    | host_variable
    ;

collection_method:
    identifier2
    { #collection_method = #([COLLECTION_METHOD_NAME, "COLLECTION_METHOD_NAME" ], #collection_method); }
    ;

field_name :
    identifier
    { #field_name = #([FIELD_NAME, "FIELD_NAME" ], #field_name); }
    ;

host_variable:
    COLON identifier2
    ;

goto_statement :
    g:"goto" label_name
    ;

label_name :
    identifier
    { #label_name = #([LABEL_NAME, "LABEL_NAME" ], #label_name); }
    ;

exit_statement:
    e:"exit" (label_name)? (w:"when" condition)?
    ;

datatype :
    (
        "binary_integer"
        | "natural"
        | "positive"
        | ("number"(OPEN_PAREN! (NUMBER|ASTERISK) (COMMA! NUMBER)? CLOSE_PAREN! )? )
        | ("char" (OPEN_PAREN! NUMBER ("byte")? CLOSE_PAREN! )? )
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
        | "numeric" (OPEN_PAREN! NUMBER (COMMA! NUMBER)? CLOSE_PAREN! )?
        | "int"
        | "integer" (OPEN_PAREN! NUMBER CLOSE_PAREN! )?
        | "pls_integer"
        | "double" "precision"
        | "float" ( OPEN_PAREN! NUMBER CLOSE_PAREN! )?
        | "decimal"
        | "varchar" ( OPEN_PAREN! NUMBER ("byte" | "char")? CLOSE_PAREN! )?
        | "varchar2" ( OPEN_PAREN! NUMBER ("byte" | "char")? CLOSE_PAREN! )?
        | "nvarchar" ( OPEN_PAREN! NUMBER ("byte" | "char")? CLOSE_PAREN! )?
        | "nvarchar2" ( OPEN_PAREN! NUMBER ("byte" | "char")? CLOSE_PAREN! )?
        | "character" ( OPEN_PAREN! NUMBER CLOSE_PAREN! )?
        | "mlslabel"
        | "blob"
        | "clob"
    )
    { #datatype = #([DATATYPE, "DATATYPE" ], #datatype);}
    ;
	exception catch [RecognitionException ex] {
	    throw ex;
	}

type_spec :
    ( datatype
    | (percentage_type) => percentage_type
    | type_name_ref )
//    { #type_spec = #([TYPE_SPEC, "TYPE_SPEC" ], #type_spec);}
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
    table_name DOT! column_name_ref PERCENTAGE! "type"! /// => table_name DOT! column_name_ref PERCENTAGE! "type"!
    { #percentage_type = #([COLUMN_TYPE_REF, "COLUMN_TYPE_REF" ], #percentage_type);}

    | table_name PERCENTAGE! "rowtype"!
    { #percentage_type = #([TABLE_TYPE_REF, "TABLE_TYPE_REF" ], #percentage_type);}
    ;

type_name_ref :
     name_fragment (DOT! name_fragment )*
    { #type_name_ref = #([TYPE_NAME_REF, "TYPE_NAME_REF" ], #type_name_ref);}
    ;

name_fragment :
     identifier2
    { #name_fragment = #([NAME_FRAGMENT, "NAME_FRAGMENT" ], #name_fragment);}
    ;

name_fragment_ex :
     identifier3
    { #name_fragment_ex = #([NAME_FRAGMENT, "NAME_FRAGMENT" ], #name_fragment_ex);}
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
    ;

type_name :
     identifier2
    { #type_name = #([TYPE_NAME, "TYPE_NAME" ], #type_name);}
    ;

parameter_spec :
    parameter_name ("in" )? ("out")? ("nocopy")? ("ref")?
    (type_spec)  (character_set)? ( (default1 | ASSIGNMENT_EQ) plsql_expression )?
    { #parameter_spec = #([PARAMETER_SPEC, "PARAMETER_SPEC" ], #parameter_spec);}
    ;

character_set:
    "character" "set" ( "any_cs" | (identifier2 PERCENTAGE "charset") )
    { #character_set = #([CHARACTER_SET, "CHARACTER_SET" ], #character_set);}
    ;

default1:
    "default"
    { #default1 = #([DEFAULT, "DEFAULT" ], #default1);}
    ;

parameter_name :
    identifier2
    { #parameter_name = #([PARAMETER_NAME, "PARAMETER_NAME" ], #parameter_name);}
    ;

cursor_spec :
        c:"cursor" cursor_name
        (o:OPEN_PAREN!
(options {
    greedy = true;
} :
      parameter_spec
        (COMMA parameter_spec)* ) CLOSE_PAREN!)?
        "return" return_type SEMI!
        ;

procedure_spec :
    procedure_declaration SEMI!
    {#procedure_spec = #([PROCEDURE_SPEC, "PROCEDURE_SPEC" ], #procedure_spec);}
    ;

function_spec :
    function_declaration SEMI!
    {#function_spec = #([FUNCTION_SPEC, "FUNCTION_SPEC" ], #function_spec);}
    ;

exception_declaration :
    exception_name "exception"! SEMI!
    {#exception_declaration = #([EXCEPTION_DECL, "EXCEPTION_DECL" ], #exception_declaration);}
    ;

exception_name :
    exception_package_name (DOT! identifier)?
    ;

exception_package_name:
    identifier
    ;

serially_reusable_pragma:
    "pragma" "serially_reusable"
    {#serially_reusable_pragma = #([SERIALLY_REUSABLE_PRAGMA, "SERIALLY_REUSABLE_PRAGMA" ], #serially_reusable_pragma);}
    ;

exception_pragma :
    "pragma"! "exception_init"! OPEN_PAREN! complex_name COMMA! plsql_expression CLOSE_PAREN! SEMI!
    {#exception_pragma = #([EXCEPTION_PRAGMA, "EXCEPTION_PRAGMA" ], #exception_pragma);}
    ;

restrict_ref_pragma :
    "pragma"! "restrict_references"! OPEN_PAREN! identifier3 (COMMA! identifier3)+ CLOSE_PAREN! SEMI!
    {#restrict_ref_pragma = #([RESTRICT_REF_PRAGMA, "RESTRICT_REF_PRAGMA" ], #restrict_ref_pragma);}
    ;

interface_pragma:
    "pragma"! "interface"! OPEN_PAREN! identifier3 (COMMA! identifier3)+ CLOSE_PAREN! SEMI!
    {#interface_pragma = #([INTERFACE_PRAGMA, "INTERFACE_PRAGMA" ], #interface_pragma);}
    ;

builtin_pragma:
    "pragma"! "builtin"! OPEN_PAREN! string_literal (COMMA! plsql_expression)+ CLOSE_PAREN! SEMI!
    {#builtin_pragma = #([BUILTIN_PRAGMA, "BUILTIN_PRAGMA" ], #builtin_pragma);}
    ;

fipsflag_pragma:
    "pragma"! "fipsflag"! OPEN_PAREN! string_literal (COMMA! plsql_expression)+ CLOSE_PAREN! SEMI!
    {#fipsflag_pragma = #([FIPSFLAG_PRAGMA, "FIPSFLAG_PRAGMA" ], #fipsflag_pragma);}
    ;

timestamp_pragma:
    "pragma"! "timestamp"! OPEN_PAREN! string_literal CLOSE_PAREN! SEMI!
    {#timestamp_pragma = #([TIMESTAMPG_PRAGMA, "TIMESTAMPG_PRAGMA" ], #timestamp_pragma);}
    ;

numeric_literal :
    NUMBER
    { #numeric_literal = #([NUMERIC_LITERAL, "NUMERIC_LITERAL" ], #numeric_literal);}
    ;

oracle_err_number:
        (p:PLUS | m:MINUS) ? n:NUMBER
        ;

record_item:
    record_item_name type_spec ("not" "null")? ((default1 |p:ASSIGNMENT_EQ {#p.setType(ASSIGNMENT_OP);}) plsql_expression)?
    { #record_item = #([RECORD_ITEM, "RECORD_ITEM" ], #record_item); }
    ;

record_item_name:
    identifier2
    { #record_item_name = #([RECORD_ITEM_NAME, "RECORD_ITEM_NAME" ], #record_item_name); }
    ;

procedure_declaration :
    "procedure"! object_name
    (options { greedy = true; } :
        ( OPEN_PAREN! argument_list CLOSE_PAREN! )?  ("as" "language" "java" "name" string_literal)?
    )
    ;

procedure_body  :
    procedure_declaration
    ( i:"is"! | a:"as"! )
    func_proc_statements
    { #procedure_body = #([PROCEDURE_BODY, "PROCEDURE_BODY" ], #procedure_body); }
    ;

func_proc_statements
    : (declare_list)? plsql_block SEMI!
    { #func_proc_statements = #([PLSQL_BLOCK, "PLSQL_BLOCK" ], #func_proc_statements);}
    ;

begin_block
    : ("declare" (declare_list)?)? plsql_block
    { #begin_block = #([PLSQL_BLOCK, "PLSQL_BLOCK" ], #begin_block);}
//    { #.begin_block = #.([BEGIN_BLOCK, "BEGIN_BLOCK" ], #.begin_block); }
    ;

plsql_block :
    "begin"!
    (seq_of_statements )?
    ( exception_section )?
    "end"! ( identifier2 ) ?
//    { #.plsql_block = #.([PLSQL_BLOCK, "PLSQL_BLOCK" ], #.plsql_block);}
    ;

exception_section:
    "exception"! (exception_handler)+
    { #exception_section = #([EXCEPTION_SECTION, "EXCEPTION_SECTION" ], #exception_section);}
    ;

declare_list:
    (declare_spec)+
    {#declare_list = #([DECLARE_LIST, "DECLARE_LIST" ], #declare_list);}
    ;

exception_handler :
    w:"when" exception_name
    (o:"or" exception_name )* t:"then"
    seq_of_statements
    ;

function_declaration :
    "function"! object_name
     (options { greedy = true; } :
        ( OPEN_PAREN! argument_list CLOSE_PAREN!
        )?
    )
    "return"! return_type (character_set)? ("pipelined")? ("parallel_enable")? ("using" identifier2)? ("deterministic")?
    ("as" "language" "java" "name" string_literal)?
    ;

function_body  :
    function_declaration
        ( (( "is"! | "as"! ) func_proc_statements)
            { #function_body = #([FUNCTION_BODY, "FUNCTION_BODY" ], #function_body); }
          | ("aggregate" "using" identifier2)
            { #function_body = #([CUSTOM_AGGR_FUNCTION, "CUSTOM_AGGR_FUNCTION" ], #function_body); }
        )
    ;

argument_list:
    // argument ( COMMA! argument )*
    parameter_spec ( COMMA! parameter_spec )*
    { #argument_list = #([ARGUMENT_LIST, "ARGUMENT_LIST" ], #argument_list); }
    ;


object_name :
    identifier3 (DOT! identifier2 )?
    { #object_name = #([OBJECT_NAME, "OBJECT_NAME" ], #object_name); }
    ;

return_type :
    type_spec
    { #return_type = #([RETURN_TYPE, "RETURN_TYPE" ], #return_type); }
    ;

/*
    1. a regular function call
    2. collection initialization
*/
function_call:
    callable_name_ref OPEN_PAREN! (call_argument_list )?  CLOSE_PAREN!
    { #function_call = #([FUNCTION_CALL, "FUNCTION_CALL" ], #function_call ); }
    ;

procedure_call:
    callable_name_ref (OPEN_PAREN! (call_argument_list)? CLOSE_PAREN!)?
    {#procedure_call = #([PROCEDURE_CALL, "PROCEDURE_CALL" ], #procedure_call);}
    ;

callable_name_ref:
//    ( name_prefix DOT! )* exec_name_ref
//    name_fragment ( DOT! name_fragment )*
    name_fragment_ex ( DOT! name_fragment_ex )*
    {#callable_name_ref = #([CALLABLE_NAME_REF,"CALLABLE_NAME_REF" ], #callable_name_ref);}
    ;

variable_name :
    identifier2
    { #variable_name = #([VARIABLE_NAME, "VARIABLE_NAME" ], #variable_name); }
    ;

null_statement:
    "null"
    {#null_statement = #([NULL_STATEMENT, "NULL_STATEMENT" ], #null_statement);}
    ;

raise_statement :
    "raise"! ( exception_name )?
    ;

return_statement :
    "return"! (condition)? // ( plsql_expression)?
    ;

forall_loop:
    forall_header ("save" "exceptions")?
	statement
    ;

forall_header:
    "forall" loop_index "in" (
        (forall_boundary DOUBLEDOT forall_boundary)
        | ("values" "of" plsql_lvalue )
        | ("indices" "of" plsql_lvalue )
    )
    {#forall_header = #([FORALL_LOOP_SPEC, "FORALL_LOOP_SPEC" ], #forall_header);}
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
    loop_index "in"! ("reverse"!)? plsql_expression DOUBLEDOT! plsql_expression
    {#numeric_loop_spec = #([NUMERIC_LOOP_SPEC, "NUMERIC_LOOP_SPEC" ], #numeric_loop_spec);}
    ;


//coll_for:
//    loop_index "in"! ("reverse")? complex_name DOUBLEDOT! complex_name
//    ;
//
// for cus in ( select ID, UPD_CNT from XSL_CUS_CUSTOMER_T where PARENT_ID = i_id and FREEZE_DATE is null for update )
// loop
//  	  deleteCustomer( cus.ID, cus.UPD_CNT, i_deleter_user_id, aud_action_type, aud_action_source );
// end loop;

cursor_loop_spec :
    loop_index "in"! (
        ( cursor_name (OPEN_PAREN! call_argument_list CLOSE_PAREN!)?)
//        ( cursor_name (parentesized_plsql_exp_list )?)
            {#cursor_loop_spec = #([CURSOR_REF_LOOP_SPEC, "CURSOR_REF_LOOP_SPEC" ], #cursor_loop_spec);}
        | (OPEN_PAREN! select_expression CLOSE_PAREN!)
            {#cursor_loop_spec = #([CURSOR_IMPL_LOOP_SPEC, "CURSOR_IMPL_LOOP_SPEC" ], #cursor_loop_spec);}
    )
    ;

loop_index:
    identifier2
    {#loop_index = #([LOOP_INDEX, "LOOP_INDEX" ], #loop_index);}
    ;

complex_name:
//    (identifier2 DOT!)* identifier2
    identifier (DOT! identifier2)*
    { #complex_name = #([COMPLEX_NAME, "COMPLEX_NAME"], #complex_name); }
    ;

boolean_literal :
    ("true" | "false")
    { #boolean_literal = #([BOOLEAN_LITERAL, "BOOLEAN_LITERAL"], #boolean_literal); }
    ;

index_name:
    identifier
    { #index_name = #([INDEX_NAME, "INDEX_NAME"], #index_name); }
    ;

integer_expr :
    plsql_expression
    | type_spec
    ;

cursor_name :
    identifier
    { #cursor_name = #([CURSOR_NAME, "CURSOR_NAME"], #cursor_name); }
    ;

record_name:
    identifier
    ;

num_expression:
    num_term ((p:PLUS {#p.setType(PLUS_OP);} | m:MINUS {#m.setType(MINUS_OP);} ) num_term )*
    {
        if(#p != null || #m != null){
            #num_expression = #([ARITHMETIC_EXPR, "ARITHMETIC_EXPR" ], #num_expression);
        }
    }
    ;

num_term:
    num_factor (( as:ASTERISK {#as.setType(MULTIPLICATION_OP);} | dv:DIVIDE {#dv.setType(DIVIDE_OP);} ) num_factor )*
    {
        if(#as != null || #dv != null){
            #num_term = #([ARITHMETIC_EXPR, "ARITHMETIC_EXPR" ], #num_term);
        }
    }
    ;

num_factor:
    may_be_negate_base_expr ("**" integer_expr)?
        (d:"day" (OPEN_PAREN! NUMBER CLOSE_PAREN!)? "to" "second" (OPEN_PAREN! NUMBER CLOSE_PAREN!)?)?
    {

        if(#d != null){
            #num_factor = #([INTERVAL_DAY_TO_SEC_EXPR, "INTERVAL_DAY_TO_SEC_EXPR" ], #num_factor);
        }
    }
    ;

may_be_negate_base_expr:
    (s1:sign!)? may_be_at_time_zone
    {
        if(#s1 != null){
            #may_be_negate_base_expr = #([ARITHMETIC_EXPR, "ARITHMETIC_EXPR" ], #may_be_negate_base_expr);
        }
    }
    ;

may_be_at_time_zone:
    base_expression (at:"at"! "time"! "zone"! timezone_spec)?
    {
        if(#at != null){
            #may_be_at_time_zone = #([AT_TIME_ZONE_EXPR, "AT_TIME_ZONE_EXPR" ], #may_be_at_time_zone);
        }
    }
    ;

sign:
    p:PLUS {#p.setType(PLUS_OP);}
    | m:MINUS {#m.setType(MINUS_OP);}
    ;

plsql_expr_list:
    plsql_expression (c:COMMA! plsql_expression )*
    {#plsql_expr_list = #([EXPR_LIST, "EXPR_LIST" ], #plsql_expr_list);}
/*
    {
        if(#c != null)
            {#.plsql_expr_list = #.([PLSQL_EXPR_LIST, "PLSQL_EXPR_LIST" ], #.plsql_expr_list);}
    }
*/
    ;

parentesized_plsql_exp_list:
    OPEN_PAREN! plsql_expr_list cp:CLOSE_PAREN!
//    { #.parentesized_plsql_exp_list = #.([PAREN_EXPR_LIST, "PAREN_EXPR_LIST" ], #.parentesized_plsql_exp_list); }
    ;

condition:
    logical_term ( p:"or" {#p.setType(OR_LOGICAL);} logical_term )*
    {
        if(#p != null){
            #condition = #([LOGICAL_EXPR, "LOGICAL_EXPR" ], #condition);
        }
    }
    ;

logical_term:
    maybe_neg_factor  (p:"and" {#p.setType(AND_LOGICAL);} maybe_neg_factor )*
    {
        if(#p != null){
            #logical_term = #([LOGICAL_EXPR, "LOGICAL_EXPR" ], #logical_term);
        }
    }
    ;

maybe_neg_factor:
    (p:"not"! {#p.setType(NOT_LOGICAL);})? plsql_expression33
    {
        if(#p != null){
            #maybe_neg_factor = #([LOGICAL_EXPR, "LOGICAL_EXPR" ], #maybe_neg_factor);
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

plsql_expression33:
    ( "current" "of" ) => ( "current"! "of"! ) identifier
        { #plsql_expression33 = #([CURRENT_OF_CLAUSE, "CURRENT_OF_CLAUSE"], #plsql_expression33); }
    | ( "exists" ) => "exists"! subquery
        { #plsql_expression33 = #([EXISTS_EXPR, "EXISTS_EXPR"], #plsql_expression33); }
    | plsql_expression (
        (relational_op) => relational_op plsql_expression
        { #plsql_expression33 = #([RELATION_CONDITION, "RELATION_CONDITION"], #plsql_expression33); }
        | (("not" )? "in" ) => ("not")? "in"! exp_set
        { #plsql_expression33 = #([IN_CONDITION, "IN_CONDITION"], #plsql_expression33); }
        | (( "not" )? "like" ) => ( "not" )? "like" plsql_expression ( "escape" (string_literal|identifier2) )?
        { #plsql_expression33 = #([LIKE_CONDITION, "LIKE_CONDITION"], #plsql_expression33); }
        | (( "not" )? "between") => ( "not" )? "between"! plsql_expression "and"! plsql_expression
        { #plsql_expression33 = #([BETWEEN_CONDITION, "BETWEEN_CONDITION"], #plsql_expression33); }
        | ("is" ( "not" )? "null" )
        { #plsql_expression33 = #([ISNULL_CONDITION, "ISNULL_CONDITION"], #plsql_expression33); }
        | ( ("not")? "member" "of") =>  (("not"!)? "member"! "of"! (name_fragment DOT!)? name_fragment )
        { #plsql_expression33 = #([MEMBER_OF, "MEMBER_OF"], #plsql_expression33); }
        )?
    | (OPEN_PAREN! plsql_expr_list CLOSE_PAREN! (EQ | NOT_EQ) subquery) => OPEN_PAREN! plsql_expr_list CLOSE_PAREN! (EQ | NOT_EQ) subquery
        { #plsql_expression33 = #([SUBQUERY_CONDITION, "SUBQUERY_CONDITION"], #plsql_expression33); }
    ;


plsql_expression:
    num_expression (c:CONCAT {#c.setType(CONCAT_OP);} num_expression )*
    {
        if(#c != null ){
            #plsql_expression = #([ARITHMETIC_EXPR, "ARITHMETIC_EXPR" ], #plsql_expression);
        }
    }
    ;

base_expression:
      ( "cast" ) => ( cast_proc )
      | ("sqlcode") => "sqlcode"
        { #base_expression = #([SQLCODE_SYSVAR, "SQLCODE_SYSVAR" ], #base_expression);}
      | ("sqlerrm") => ("sqlerrm" (OPEN_PAREN! base_expression CLOSE_PAREN!)? )
        { #base_expression = #([SQLERRM_SYSVAR, "SQLERRM_SYSVAR" ], #base_expression);}
      | ( "trim" ) => ( trim_function )
      | ( "count" ) => ( count_function )
      | ( "case" ) => ( case_expression )
      | ( ("rank"|"dense_rank") OPEN_PAREN ) => dence_rank_analytics_func
      | ("sql" PERCENTAGE ) => sql_percentage
      | ( extract_date_function ) => extract_date_function
        // interval examples: "INTERVAL '30' MINUTE", "INTERVAL '2-6' YEAR TO MONTH", "INTERVAL '3 12:30:06.7' DAY TO SECOND(1)"
      | ("interval"! string_literal ("second" | "minute" | "hour" | "day" | "month" | "year")
                    ("to" ("second"|"month") (OPEN_PAREN! NUMBER CLOSE_PAREN!)?)? )
        { #base_expression = #([INTERVAL_CONST, "INTERVAL_CONST" ], #base_expression);}
      | ("timestamp"! string_literal)
        { #base_expression = #([TIMESTAMP_CONST, "TIMESTAMP_CONST" ], #base_expression);}
      | (( "all" | "any" )) => ( a1:"all" | a2:"any" ) subquery
      | ((cursor_name | subquery) PERCENTAGE ("rowcount"|"found"|"notfound"|"isopen") ) => ident_percentage
      | ( OPEN_PAREN "select") => subquery
        { #base_expression = #([SUBQUERY_EXPR, "SUBQUERY_EXPR" ], #base_expression);}
      | OPEN_PAREN! condition CLOSE_PAREN!
        { #base_expression = #([PARENTHESIZED_EXPR, "PARENTHESIZED_EXPR" ], #base_expression);}
      | string_literal
//      | date_literal
      | numeric_literal
      | boolean_literal
      | null_statement
      | pseudo_column
      | (column_spec OPEN_PAREN! PLUS! CLOSE_PAREN!) => ( column_spec OPEN_PAREN! PLUS! CLOSE_PAREN! )
        {#base_expression = #([COLUMN_OUTER_JOIN, "COLUMN_OUTER_JOIN" ], #base_expression);}
      | ( identifier DOT ("nextval" | "currval")) => sequence_expr

      | rvalue
      ;

ident_percentage:
    (cursor_name | subquery) PERCENTAGE!
        ("rowcount"
            { #ident_percentage = #([ROWCOUNT_EXRESSION, "ROWCOUNT_EXRESSION" ], #ident_percentage);}
          |"found"
            { #ident_percentage = #([CURSOR_STATE, "CURSOR_STATE" ], #ident_percentage);}
          |"notfound"
            { #ident_percentage = #([CURSOR_STATE, "CURSOR_STATE" ], #ident_percentage);}
          |"isopen"
            { #ident_percentage = #([CURSOR_STATE, "CURSOR_STATE" ], #ident_percentage);}
        )
    ;

dence_rank_analytics_func:
    // DENSE_RANK() OVER (ORDER BY MAX(SEVERITY * 1000000000 + FAULT_TS / 1000) DESC, XTL_PDC_REGISTRATION_T.MNO_NAME)
    // DENSE_RANK() OVER (PARTITION BY deptno ORDER BY sal) "rank"
    ("rank"|"dense_rank") OPEN_PAREN CLOSE_PAREN "over" OPEN_PAREN (
            order_clause
            | ("partition" "by" plsql_expression)
        ) CLOSE_PAREN
    ;

timezone_spec:
    ( string_literal
     | complex_name // identifier (DOT identifier)*
     | "sessiontimezone"
     | "dbtimezone")
    { #timezone_spec = #([TIMEZONE_SPEC, "TIMEZONE_SPEC"], #timezone_spec); }
    ;

sequence_expr:
    name_fragment DOT! name_fragment /// ("nextval" | "currval")
    {#sequence_expr = #([SEQUENCE_EXPR, "SEQUENCE_EXPR" ], #sequence_expr);}
    ;

count_function:
    "count"! OPEN_PAREN! (
        asterisk_column
        | (ident_asterisk_column) =>  ident_asterisk_column
        | ( ("distinct")? plsql_expression )
    ) CLOSE_PAREN!
//    ASTERISK | ( ("distinct")? plsql_expression )) CLOSE_PAREN!
    {#count_function = #([COUNT_FUNC, "COUNT_FUNC" ], #count_function);}
    ;

string_literal :
    (QUOTED_STR)+
    { #string_literal = #([STRING_LITERAL, "STRING_LITERAL"], #string_literal); }
    ;

extract_date_function:
     "extract"! OPEN_PAREN! extract_consts "from"! plsql_expression CLOSE_PAREN!
    {#extract_date_function = #([EXTRACT_DATE_FUNC, "EXTRACT_DATE_FUNC" ], #extract_date_function);}
    ;

/*
extract_date_function_name:
    "extract"
    {.#.extract_date_function_name = #.([FUNCTION,_NAME, "FUNCTION,_NAME" ], #.extract_date_function_name);}
    ;
*/
extract_consts:
    "second" | "minute" | "hour" | "day" | "month" | "year" | "timezone_hour" | "timezone_minute"
    ;

date_literal:
    (QUOTED_STR)+
    ;

commit_statement:
    "commit" ( "work" )?
    {#commit_statement=#([COMMIT_STATEMENT, "COMMIT_STATEMENT"], #commit_statement);}
    ;

case_expression:
    ("case"! (
        // searched_case_expression
        ( "when" condition t:"then" plsql_expression )+
        // simple_case_expression
        | smpl:plsql_expression ("when" plsql_expression "then" plsql_expression)+
     )
    ( "else" plsql_expression ) ?
    "end"!)
    {
        if(#smpl != null){
            {#case_expression=#([CASE_EXPRESSION_SMPL, "CASE_EXPRESSION_SMPL"], #case_expression);}
        } else {
            {#case_expression=#([CASE_EXPRESSION_SRCH, "CASE_EXPRESSION_SRCH"], #case_expression);}
        }
    }
    ;


if_statement:
    "if"! condition
    "then"! seq_of_statements
    (elsif_statements)*
    (else_statements)?
    "end"! "if"!
    { #if_statement = #([IF_STATEMENT, "IF_STATEMENT" ], #if_statement); }
    ;

elsif_statements:
    "elsif"! condition "then"! seq_of_statements
    { #elsif_statements = #([ELSIF_STATEMENT, "ELSIF_STATEMENT" ], #elsif_statements); }
    ;

else_statements:
    "else"! seq_of_statements
    { #else_statements = #([ELSE_STATEMENT, "ELSE_STATEMENT" ], #else_statements); }
    ;

sql_statement:
        select_command
        | insert_command
        | update_command
        | delete_command
        | merge_command

        | commit_statement
        | rollback_statement
    ;


select_command:
    ( select_expression | subquery )
//    { #.select_command = #.([SELECT_COMMAND, "SELECT_COMMAND" ], #.select_command); }
    ;

subquery:
    o:OPEN_PAREN! select_command cp:CLOSE_PAREN!
    { #subquery = #([SUBQUERY, "SUBQUERY" ], #subquery); }
    ;

select_expression :
    select_first ( (
        ( "union" ("all")? )
        | "intersect"
        | "minus"
      ) sub:select_first ) *
    {
        if(#sub != null){
            { #select_expression = #([SELECT_EXPRESSION_UNION, "SELECT_EXPRESSION_UNION" ], #select_expression); }
        }
    }
    ;


/*
 * There are ambiguity issues here; it seems to be legal to put any number
 * of brackets round a select statement, but then the thing reports an
 * unexpected token when the MINUS turns up.
 */

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
     { #select_first = #([SELECT_EXPRESSION, "SELECT_EXPRESSION" ], #select_first); }
    | o:OPEN_PAREN select_first c:CLOSE_PAREN
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
    { #into_clause = #([INTO_CLAUSE, "INTO_CLAUSE" ], #into_clause);}
    ;
*/


into_clause:
    ("bulk" "collect")? i:"into"! plsql_lvalue_list  ///plsql_exp_list
    { #into_clause = #([INTO_CLAUSE, "INTO_CLAUSE" ], #into_clause);}
    ;

plsql_lvalue_list:
    plsql_lvalue (COMMA! plsql_lvalue)*
    ;

select_up_to_list:
//(options { greedy = true; } :
/*
    (w:"with" alias  a1:"as" subquery
         (c:COMMA alias a2:"as" subquery )*
    )?
*/
    "select"! ( "all" | "distinct" | "unique")?
    select_list
//)
    ;


select_list:
(options { greedy = true; } :
        displayed_column ( COMMA! displayed_column )*
    )
    ;

displayed_column:
    asterisk_column
    | (ident_asterisk_column) =>  ident_asterisk_column
    | expr_column
    ;

asterisk_column:
    ASTERISK
    { #asterisk_column = #([ASTERISK_COLUMN, "ASTERISK_COLUMN" ], #asterisk_column ); }
    ;

ident_asterisk_column:
//    table_ref DOT ASTERISK
    name_fragment DOT ASTERISK 
    { #ident_asterisk_column = #([IDENT_ASTERISK_COLUMN, "IDENT_ASTERISK_COLUMN" ], #ident_asterisk_column ); }
    ;

expr_column:
    plsql_expression ( alias )?
    { #expr_column = #([EXPR_COLUMN, "EXPR_COLUMN" ], #expr_column ); }
    ;


immediate_command:
    "execute"! "immediate"!
                (   (plsql_lvalue) => plsql_lvalue
                    |   plsql_expression
                )
        ( ("bulk" "collect")? "into" plsql_lvalue_list ) ?
//        ( ("bulk" "collect")? "into" plsql_expr_list ) ?
        ( "using" plsql_exp_list_using ) ?
    { #immediate_command = #([IMMEDIATE_COMMAND, "IMMEDIATE_COMMAND" ], #immediate_command);}
    ;


plsql_exp_list_using:
    ( ("in"|"out")? plsql_expression COMMA ) => ("in"|"out")? plsql_expression (COMMA! ("in"|"out")? plsql_expression )*
    {#plsql_exp_list_using = #([PLSQL_EXPR_LIST, "PLSQL_EXPR_LIST" ], #plsql_exp_list_using);}
    | ("in"|"out")? plsql_expression
    ;

alter_command:
    alter_system_session
    | alter_table
    | alter_trigger
    ;

alter_system_session:
    "alter" ( "system" | "session" )
    (
        ( "flush" "shared_pool" )
        | (
            ( "set"  identifier EQ
                ( string_literal | numeric_literal | identifier )
            )
            | ( "reset" identifier )
          )
         ( "sid" EQ ( string_literal | ASTERISK ) ) ?
    )
    ;


table_reference_list_from:
        "from"! selected_table ("partition"! OPEN_PAREN! identifier2 CLOSE_PAREN!)?
        (
         ( "left" | "right" | "inner" | "outer" | "join") => ansi_spec
         | (COMMA! selected_table ("partition"! OPEN_PAREN! identifier2 CLOSE_PAREN!)?)
        )*
{
    #table_reference_list_from = #([TABLE_REFERENCE_LIST_FROM,"TABLE_REFERENCE_LIST_FROM" ], #table_reference_list_from);
}
            ;

table_reference_list:
    selected_table ( COMMA! selected_table )*
    {#table_reference_list = #([TABLE_REFERENCE_LIST, "TABLE_REFERENCE_LIST" ], #table_reference_list);}
    ;

where_condition:
    "where"! condition
    { #where_condition = #([WHERE_CONDITION, "WHERE_CONDITION" ], #where_condition); }
    ;


call_argument_list:
    call_argument (COMMA! call_argument)*
    { #call_argument_list = #([CALL_ARGUMENT_LIST, "CALL_ARGUMENT_LIST" ], #call_argument_list);}
    ;

call_argument :
    (parameter_name_ref PASS_BY_NAME!)? condition   ///plsql_expression
    { #call_argument = #([CALL_ARGUMENT, "CALL_ARGUMENT" ], #call_argument);}
    ;

parameter_name_ref:
    name_fragment
    {#parameter_name_ref = #([PARAMETER_REF, "PARAMETER_REF" ], #parameter_name_ref);}
    ;

schema_name:
    identifier2
    {#schema_name = #([SCHEMA_NAME, "SCHEMA_NAME" ], #schema_name);}
    ;

view_name_ddl :
    identifier
    {#view_name_ddl = #([VIEW_NAME_DDL, "VIEW_NAME_DDL" ], #view_name_ddl);}
    ;

table_name_ddl :
    identifier2
    {#table_name_ddl = #([TABLE_NAME_DDL, "TABLE_NAME_DDL" ], #table_name_ddl);}
    ;

table_name :
    identifier2
    {#table_name = #([TABLE_NAME, "TABLE_NAME" ], #table_name);}
    ;

table_ref :
    identifier
    {#table_ref = #([TABLE_REF, "TABLE_REF" ], #table_ref);}
    ;

alias :
    ( "as"! )?  alias_ident
    {#alias = #([ALIAS_NAME,"ALIAS_NAME" ], #alias);}
    ;

alias_ident:
    identifier2
    {#alias_ident = #([ALIAS_IDENT,"ALIAS_IDENT" ], #alias_ident);}
    ;

package_name:
    identifier
    {#package_name = #([PACKAGE_NAME, "PACKAGE_NAME" ], #package_name);}
    ;

column_spec:
    (name_fragment DOT!)? name_fragment
    {#column_spec = #([COLUMN_SPEC,"COLUMN_SPEC" ], #column_spec);}
    ;


column_name_ref:
    identifier2
    {#column_name_ref = #([COLUMN_NAME_REF,"COLUMN_NAME_REF" ], #column_name_ref);}
    ;

column_name_ddl:
    identifier2
    {#column_name_ddl = #([COLUMN_NAME_DDL,"COLUMN_NAME_DDL" ], #column_name_ddl);}
    ;

trim_function :
    "trim"! OPEN_PAREN! ( "leading" | "trailing" | "both") ?
    plsql_expression ( "from" plsql_expression )? CLOSE_PAREN!
    {#trim_function = #([TRIM_FUNC,"TRIM_FUNC" ], #trim_function);}
    ;


pseudo_column :
        "user"
        { #pseudo_column = #([USER_CONST, "USER_CONST"], #pseudo_column); }
        | "sysdate"
        { #pseudo_column = #([SYSDATE_CONST, "SYSDATE_CONST"], #pseudo_column); }
        | "systimestamp"
        { #pseudo_column = #([SYSTIMESTAMP_CONST, "SYSTIMESTAMP_CONST"], #pseudo_column); }
        | "current_timestamp"
        { #pseudo_column = #([CURRENT_TIMESTAMP_CONST, "CURRENT_TIMESTAMP_CONST"], #pseudo_column); }
        | "dbtimezone"
        { #pseudo_column = #([DBTIMEZONE, "DBTIMEZONE"], #pseudo_column); }
        | "rownum"
        { #pseudo_column = #([ROWNUM, "ROWNUM"], #pseudo_column); }
        ;

selected_table:
    ("table")=> row_proc ( alias )?
    { #selected_table = #([VIRTUAL_TABLE, "VIRTUAL_TABLE"], #selected_table); }
    | ("the") => the_proc ( alias )?
    | from_subquery   ///subquery ( alias )?
    | table_alias
    ;

from_subquery:
    subquery ( alias )?
    { #from_subquery = #([FROM_SUBQUERY, "FROM_SUBQUERY"], #from_subquery); }
    ;

from_plain_table:
    table_spec ( alias )?
    { #from_plain_table = #([FROM_PLAIN_TABLE, "FROM_PLAIN_TABLE"], #from_plain_table); }
    ;

ansi_spec :
      ( "inner"
        | (( "left" | "right") ("outer")? )
      )? "join"
      selected_table
      "on"
      condition
      { #ansi_spec = #([ANSI_JOIN_TAB_SPEC, "ANSI_JOIN_TAB_SPEC"], #ansi_spec); }
      ;


row_proc:
    "table"! OPEN_PAREN!  ( select_command | cast_proc | function_call | identifier ) CLOSE_PAREN!
    ;


the_proc:
    "the" subquery
    ;

cast_proc :
    "cast"! OPEN_PAREN! plsql_expression "as"! (type_name_ref|datatype) CLOSE_PAREN!
    { #cast_proc = #([CAST_EXPR, "CAST_EXPR"], #cast_proc); }
    ;

table_spec:
//    ( schema_name DOT )? table_name ( AT_SIGN link_name )?
    ( schema_name DOT )? table_name2 //( AT_PREFIXED )?
    ;

table_name2 :
    identifier2
        {#table_name2 = #([TABLE_NAME, "TABLE_NAME" ], #table_name2);}
    | TABLE_NAME_SPEC
        {#table_name2 = #([TABLE_NAME_WITH_LINK, "TABLE_NAME_WITH_LINK" ], #table_name2);}
    ;

table_alias:
    table_spec ( alias )?
    { #table_alias = #([TABLE_ALIAS, "TABLE_ALIAS"], #table_alias); }
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
    { #relational_op = #([RELATION_OP, "RELATION_OP"], #relational_op); }
    ;


exp_set:
    ( OPEN_PAREN "select" ) => subquery
    | parentesized_plsql_exp_list
    | plsql_expression
    ;
/*
connect_clause:
    ( "start" "with" condition )? // The start can precede the connect by
    "connect" "by"
        condition
        |  ( "connect" "by" condition ) ?
     "start" w2:"with" condition
    { #connect_clause = #([CONNECT_CLAUSE, "CONNECT_CLAUSE" ], #connect_clause); }
    ;
*/

connect_clause:
    connect_clause_internal (connect_clause_internal)?
    { #connect_clause = #([CONNECT_CLAUSE, "CONNECT_CLAUSE" ], #connect_clause); }
    ;

connect_clause_internal:
    ("start" "with" condition) // The start can precede the connect by
    | ("connect" "by" condition)
    ;

group_clause:
    (
        ("group"! "by"! plsql_expression (c:COMMA! plsql_expression )*)
        | ( "having" condition )
    )+
    { #group_clause = #([GROUP_CLAUSE, "GROUP_CLAUSE" ], #group_clause); }
    ;
/*
groupby_expr_list:
    plsql_expression (c:COMMA! plsql_expression )*
    {
        if(#c != null)
            {#.groupby_expr_list = #.([GROUPBY_EXPR_LIST, "GROUPBY_EXPR_LIST" ], #.groupby_expr_list);}
    }
    ;
*/

order_clause:
    "order"! "by"!
    sorted_def ( COMMA! sorted_def )*
    { #order_clause = #([ORDER_CLAUSE, "ORDER_CLAUSE" ], #order_clause); }
    ;

sorted_def:
    plsql_expression (( a:"asc" | d:"desc" ) ("nulls" ("first" |"last"))? )?
    { #sorted_def = #([SORTED_DEF, "SORTED_DEF" ], #sorted_def); }
    ;

update_clause:
    "for" "update"
    ( "of" column_name_ref ( COMMA column_name_ref )* )?
    ( "nowait"|("wait" numeric_literal) )?
    { #update_clause = #([FOR_UPDATE_CLAUSE, "FOR_UPDATE_CLAUSE" ], #update_clause); }
    ;

insert_command:
    "insert"! "into"!
        (
          (table_alias) => table_alias ( OPEN_PAREN! column_spec_list CLOSE_PAREN! )?
                ( ( "values"! (parentesized_plsql_exp_list | variable_ref)) | select_expression ) (returning)?
            { #insert_command = #([INSERT_COMMAND, "INSERT_COMMAND" ], #insert_command); }

         // To define the set of rows to be inserted into the target table of an INSERT statement
         | subquery ( "values"! (parentesized_plsql_exp_list | function_call) )
            { #insert_command = #([INSERT_INTO_SUBQUERY_COMMAND, "INSERT_INTO_SUBQUERY_COMMAND" ], #insert_command); }
         )
    ;

column_spec_list:
    column_spec ( COMMA! column_spec )*
    { #column_spec_list = #([COLUMN_SPEC_LIST, "COLUMN_SPEC_LIST" ], #column_spec_list); }
    ;

update_command:
    (   ( subquery_update ) => subquery_update
        | simple_update )
    { #update_command = #([UPDATE_COMMAND, "UPDATE_COMMAND" ], #update_command); }
    ;

merge_command:
    "merge"! "into"! table_alias
    "using"! ( table_alias| (subquery ( alias )?) ) "on" condition
    when_action (when_action)?
    ("delete" "where" condition)?
    { #merge_command = #([MERGE_COMMAND, "MERGE_COMMAND" ], #merge_command); }
    ;

when_action:
    "when" ("not")? "matched"! "then"!
    (
        ("update" "set" column_spec EQ plsql_expression ( COMMA! column_spec EQ plsql_expression )*)
        | insert_columns
    ) (where_condition)?
    { #when_action = #([MERGE_WHEN_COMMAND, "MERGE_WHEN_COMMAND" ], #when_action); }
    ;

insert_columns:
    "insert"! ( OPEN_PAREN! column_spec_list CLOSE_PAREN! )?
    "values"! parentesized_plsql_exp_list
    ;


simple_update:
    "update"! table_alias
    "set" column_spec EQ plsql_expression ( COMMA! column_spec EQ plsql_expression )*
    ( where_condition ) ?
    ( returning )?
    { #simple_update = #([SIMPLE_UPDATE_COMMAND, "SIMPLE_UPDATE_COMMAND" ], #simple_update); }
    ;


returning:
//      RETURNING id INTO l_fst_ids(indx);
//      RETURNING id,upd_cnt INTO o_alertId,o_updateCtr;
    ("returning"! | "return"!) identifier2 (COMMA identifier2)* "into"! plsql_expression (COMMA plsql_expression)*
    ;


subquery_update:
    "update"! table_alias
    "set"
    OPEN_PAREN! column_spec_list CLOSE_PAREN! EQ subquery
    ( where_condition )?
    { #subquery_update = #([SUBQUERY_UPDATE_COMMAND, "SUBQUERY_UPDATE_COMMAND" ], #subquery_update); }
    ;

delete_command:
    "delete"! ( "from"! )? table_alias ( where_condition )? (returning)?
    { #delete_command = #([DELETE_COMMAND, "DELETE_COMMAND" ], #delete_command); }
    ;

set_transaction_command:
        "set" "transaction" r:"read" "only"
        ;

close_statement :
      "close" cursor_name
      ;

fetch_statement:
    "fetch" cursor_name ( "bulk" "collect" ) ? "into" variable_ref (COMMA! variable_ref )*
    ;

variable_ref:
    ( name_fragment DOT )* name_fragment
    {#variable_ref = #([PLSQL_VAR_REF, "PLSQL_VAR_REF" ], #variable_ref);}
    ;

lock_table_statement:
        l:"lock" t:"table" table_reference_list
        i:"in" lock_mode m:"mode" ( n:"nowait" )?
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
        o:"open" cursor_name  (parentesized_plsql_exp_list)?
         ( f:"for" ( select_expression | plsql_expression ))?
//         ( "using" plsql_expr_list ) ?
         ( "using" "in" plsql_lvalue_list )?
        ;

rollback_statement:
        "rollback" ( "work" )?
        ( "to"! ( "savepoint" )? savepoint_name )?
        ( "comment"! string_literal! )?
        { #rollback_statement = #([ROLLBACK_STATEMENT, "ROLLBACK_STATEMENT" ], #rollback_statement); }
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
    | "execute"
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
    | "flush"
    | "interval"
    | "transaction"
    | "session"
    | "close"
    | "read"
    | "immediate"
    | "replace"
    | "sid"
    | "local"
    | "time"
    | "name"
//    | "true"
//    | "false"
    | "default"
    | "package"
//    | "function"
    | "ref"
    | "byte"
    | "interface"
//    | "from_tz"
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
    | "check"
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
    | "blob"
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
    | "on"
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
    | "library"
    | "role"
    | "online"
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
    )
    ;


/////////////////////////////////////////////////////////////////
/////       EXTERNAL TABLE SPECIFICATION        /////////////////
/////////////////////////////////////////////////////////////////
 
external_table_spec:
    "external"! OPEN_PAREN! "type" (oracle_loader_params|oracle_datapump_params)location CLOSE_PAREN!
//    ("as" select_expression)? (reject_spec|parallel_clause)*
    (reject_spec|parallel_clause)*
    ;

oracle_loader_params:
    "oracle_loader" directory_spec (access_parameters)?
    ;

oracle_datapump_params:
    "oracle_datapump" directory_spec (write_access_parameters)?
    ;

directory_spec:
    ("default")? "directory" identifier
    ;

write_access_parameters:
    "access" "parameters"
        OPEN_PAREN!
            ("nologfile"|("logfile" file_location_spec))?
            ("version" ("compatible"|"latest"|string_literal))?
            ("compression" ("enabled"|"disabled"))?
            ("encryption" ("enabled"|"disabled"))?
        CLOSE_PAREN!
    ;

access_parameters:
    "access" "parameters"
        OPEN_PAREN!
             (record_format_info)? (field_definitions)? (column_transforms)? 
        CLOSE_PAREN!
    ;

record_format_info:
    "records" rec_format (rec_format_tail)*
    ;
    
rec_format:
    ("fixed" numeric_literal)
    | ("variable" numeric_literal)
    | ("delimited" "by" ("newline"|string_literal))
    ;

rec_format_tail:
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
    ;

field_definitions:
    "fields" (delim_spec)? (trim_spec)?
        ("missing" "field" "values" "are" "null")?
        ("reject" "rows" "with" "all" "null" "fields")?
        (field_list)?
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
    OPEN_PAREN! field_spec (COMMA! field_spec)* CLOSE_PAREN!
    ;

field_spec:
    identifier2 (pos_spec)? (datatype_spec)?
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
    ;

file_location_spec:
    (identifier COLON)? string_literal
    ;

