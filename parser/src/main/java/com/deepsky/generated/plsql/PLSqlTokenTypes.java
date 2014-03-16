// $ANTLR 2.7.5 (20050128): "plsql_parser.g" -> "PLSqlParser.java"$

package com.deepsky.generated.plsql;

public interface PLSqlTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int C_MARKER = 4;
	int BAD_ML_COMMENT = 5;
	int BAD_CHARACTER = 6;
	int BAD_CHAR_LITERAL = 7;
	int BAD_NUMBER_LITERAL = 8;
	int STORAGE_SIZE = 9;
	int LT = 10;
	int LE = 11;
	int GE = 12;
	int NOT_EQ = 13;
	int CUSTOM_TOKEN = 14;
	int COLON_NEW = 15;
	int COLON_OLD = 16;
	int TABLE_NAME_SPEC = 17;
	int QUOTED_STR_START = 18;
	int QUOTED_STR_END = 19;
	int BAD_QUOTED_STRING = 20;
	int PLSQL_ENV_VAR = 21;
	int ANY_CHARACTER = 22;
	int IDENTIFIER = 23;
	int QUOTED_STR = 24;
	int DOUBLE_QUOTED_STRING = 25;
	int AT_PREFIXED = 26;
	int SEMI = 27;
	int COLON = 28;
	int DOT = 29;
	int COMMA = 30;
	int ASTERISK = 31;
	int OPEN_PAREN = 32;
	int CLOSE_PAREN = 33;
	int PLUS = 34;
	int MINUS = 35;
	int DIVIDE = 36;
	int BACKSLASH = 37;
	int EQ = 38;
	int PERCENTAGE = 39;
	int DOUBLEDOT = 40;
	int CONCAT = 41;
	int START_LABEL = 42;
	int DOLLAR = 43;
	int END_LABEL = 44;
	int ASSIGNMENT_EQ = 45;
	int PASS_BY_NAME = 46;
	int VERTBAR = 47;
	int GT = 48;
	int NUMBER = 49;
	int N = 50;
	int WS = 51;
	int LF = 52;
	int SL_COMMENT = 53;
	int ML_COMMENT = 54;
	int IF_COND_CMPL = 55;
	int THEN_COND_CMPL = 56;
	int ELSE_COND_CMPL = 57;
	int END_COND_CMPL = 58;
	int ERROR_COND_CMPL = 59;
	int START_RULE = 60;
	int ERROR_TOKEN_A = 61;
	int PACKAGE_SPEC = 62;
	int PACKAGE_BODY = 63;
	int PACKAGE_NAME = 64;
	int PACKAGE_OBJ_BODY = 65;
	int RECORD_TYPE_DECL = 66;
	int SELECT_EXPRESSION = 67;
	int SELECT_EXPRESSION_UNION = 68;
	int PLSQL_BLOCK = 69;
	int PLSQL_BLOCK_END = 70;
	int CURSOR_DECLARATION = 71;
	int VARIABLE_DECLARATION = 72;
	int PROCEDURE_BODY = 73;
	int FUNCTION_BODY = 74;
	int PARAMETER_SPEC = 75;
	int IF_STATEMENT = 76;
	int LOOP_STATEMENT = 77;
	int STATEMENT = 78;
	int TABLE_REFERENCE_LIST = 79;
	int TABLE_REFERENCE_LIST_FROM = 80;
	int WHERE_CONDITION = 81;
	int SUBQUERY = 82;
	int SQL_IDENTIFIER = 83;
	int FUNCTION = 84;
	int GROUP_FUNCTION = 85;
	int USER_FUNCTION = 86;
	int MULTIPLY = 87;
	int ARGUMENT = 88;
	int ARGUMENT_LIST = 89;
	int FIELD_NAME = 90;
	int PLSQL_EXPR_LIST_USING = 91;
	int EXPR_LIST = 92;
	int DECLARE_LIST = 93;
	int FUNCTION_CALL = 94;
	int PACKAGE_INIT_SECTION = 95;
	int CALL_ARGUMENT_LIST = 96;
	int SPEC_CALL_ARGUMENT_LIST = 97;
	int QUERY_PARTITION_CLAUSE = 98;
	int EXTRACT_OPTIONS = 99;
	int CALL_ARGUMENT = 100;
	int BASE_EXRESSION = 101;
	int COLUMN_SPEC = 102;
	int LOGICAL_AND = 103;
	int LOGICAL_OR = 104;
	int CASE_EXPRESSION_SMPL = 105;
	int CASE_EXPRESSION_SRCH = 106;
	int CASE_STATEMENT = 107;
	int COUNT_FUNC = 108;
	int SQLPLUS_ANONYM_PLSQL_BLOCK = 109;
	int RANK_FUNCTION = 110;
	int LEAD_FUNCTION = 111;
	int LAG_FUNCTION = 112;
	int TRIM_FUNC = 113;
	int DECODE_FUNC = 114;
	int COLUMN_SPEC_LIST = 115;
	int INSERT_COMMAND = 116;
	int UPDATE_COMMAND = 117;
	int DELETE_COMMAND = 118;
	int MERGE_COMMAND = 119;
	int MERGE_WHEN_COMMAND = 120;
	int STRING_LITERAL = 121;
	int NUMERIC_LITERAL = 122;
	int BOOLEAN_LITERAL = 123;
	int RETURN_TYPE = 124;
	int TYPE_SPEC = 125;
	int VARIABLE_NAME = 126;
	int COLUMN_OUTER_JOIN = 127;
	int LOGICAL_FACTOR = 128;
	int TABLE_ALIAS = 129;
	int CAST_FUNC = 130;
	int VAR_REF = 131;
	int PLSQL_VAR_REF = 132;
	int PARAMETER_REF = 133;
	int EXCEPTION_SECTION = 134;
	int EXCEPTION_HANDLER = 135;
	int SELECTED_TABLE = 136;
	int CREATE_PROCEDURE = 137;
	int CREATE_FUNCTION = 138;
	int COMMIT_STATEMENT = 139;
	int ROLLBACK_STATEMENT = 140;
	int NULL_STATEMENT = 141;
	int ASSIGNMENT_STATEMENT = 142;
	int PROCEDURE_CALL = 143;
	int RETURN_STATEMENT = 144;
	int LOCK_TABLE_STATEMENT = 145;
	int OPEN_STATEMENT = 146;
	int FETCH_STATEMENT = 147;
	int SET_TRN_STATEMENT = 148;
	int CLOSE_STATEMENT = 149;
	int OBJECT_NAME = 150;
	int PARAMETER_NAME = 151;
	int DEFAULT = 152;
	int THEN_STATEMENT = 153;
	int ELSIF_STATEMENT = 154;
	int ELSE_STATEMENT = 155;
	int STATEMENT_LIST = 156;
	int RELATION_CONDITION = 157;
	int ISNULL_CONDITION = 158;
	int LIKE_CONDITION = 159;
	int BETWEEN_CONDITION = 160;
	int IN_CONDITION = 161;
	int PARENTHESIZED_EXPR = 162;
	int ORDER_CLAUSE = 163;
	int CONNECT_CLAUSE = 164;
	int GROUP_CLAUSE = 165;
	int INTO_CLAUSE = 166;
	int FOR_UPDATE_CLAUSE = 167;
	int PARTITION_NAME = 168;
	int ASTERISK1 = 169;
	int ROWCOUNT_EXRESSION = 170;
	int MULTIPLICATION_OP = 171;
	int DIVIDE_OP = 172;
	int PLUS_OP = 173;
	int MINUS_OP = 174;
	int CONCAT_OP = 175;
	int CURSOR_NAME = 176;
	int CURSOR_NAME_REF = 177;
	int DATATYPE = 178;
	int EXTRACT_DATE_FUNC = 179;
	int ANSI_JOIN_TAB_SPEC = 180;
	int ANSI_JOIN_TAB_CONDITION = 181;
	int INTERVAL_DAY_TO_SEC_EXPR = 182;
	int INTERVAL_YEAR_TO_MONTH_EXPR = 183;
	int INTERVAL_CONST = 184;
	int USER_CONST = 185;
	int SYSDATE_CONST = 186;
	int SYSTIMESTAMP_CONST = 187;
	int CURRENT_TIMESTAMP_CONST = 188;
	int ALIAS_NAME = 189;
	int EXISTS_EXPR = 190;
	int SUBQUERY_EXPR = 191;
	int TYPE_NAME_REF = 192;
	int TYPE_NAME = 193;
	int SORTED_DEF = 194;
	int RAISE_STATEMENT = 195;
	int PACKAGE_OBJ_SPEC = 196;
	int PROCEDURE_SPEC = 197;
	int SIMPLE_UPDATE_COMMAND = 198;
	int SUBQUERY_UPDATE_COMMAND = 199;
	int IMMEDIATE_COMMAND = 200;
	int FUNCTION_SPEC = 201;
	int NEGATE_FACTOR = 202;
	int TABLE_TYPE_REF = 203;
	int COLUMN_TYPE_REF = 204;
	int STATEMENT_ANNOT = 205;
	int ASTERISK_COLUMN = 206;
	int IDENT_ASTERISK_COLUMN = 207;
	int EXPR_COLUMN = 208;
	int TABLE_REF = 209;
	int TABLE_REF_WITH_LINK = 210;
	int FROM_SUBQUERY = 211;
	int FROM_PLAIN_TABLE = 212;
	int SCHEMA_NAME = 213;
	int COLUMN_NAME_REF = 214;
	int ARITHMETIC_EXPR = 215;
	int ASSIGNMENT_OP = 216;
	int DBTIMEZONE = 217;
	int SUBQUERY_CONDITION = 218;
	int RECORD_ITEM = 219;
	int EXCEPTION_DECL = 220;
	int COMPLEX_NAME = 221;
	int CHARACTER_SET = 222;
	int AUTHID = 223;
	int RESTRICT_REF_PRAGMA = 224;
	int AUTONOMOUS_TRN_PRAGMA = 225;
	int EXCEPTION_PRAGMA = 226;
	int FIPSFLAG_PRAGMA = 227;
	int BUILTIN_PRAGMA = 228;
	int INTERFACE_PRAGMA = 229;
	int TIMESTAMPG_PRAGMA = 230;
	int SERIALLY_REUSABLE_PRAGMA = 231;
	int TIMESTAMP_CONST = 232;
	int SUBTYPE_DECL = 233;
	int MEMBER_OF = 234;
	int SQLPLUS_SET = 235;
	int SQLPLUS_SHOW = 236;
	int SQLPLUS_DEFINE = 237;
	int SQLPLUS_VARIABLE = 238;
	int SQLPLUS_EXEC = 239;
	int SQLPLUS_WHENEVER = 240;
	int SQLPLUS_PROMPT = 241;
	int SQLPLUS_COLUMN = 242;
	int SQLPLUS_START = 243;
	int SQLPLUS_SERVEROUTPUT = 244;
	int SQLPLUS_REPFOOTER = 245;
	int SQLPLUS_REPHEADER = 246;
	int SQLPLUS_EXIT = 247;
	int SQLPLUS_QUIT = 248;
	int SQLPLUS_RUNSCRIPT = 249;
	int SQLPLUS_SPOOL = 250;
	int CONSUMED_TILL_EOL = 251;
	int OR_LOGICAL = 252;
	int AND_LOGICAL = 253;
	int NOT_LOGICAL = 254;
	int LOGICAL_EXPR = 255;
	int RELATION_OP = 256;
	int SEQUENCE_EXPR = 257;
	int SEQUENCE_REF = 258;
	int ALIAS_IDENT = 259;
	int COLUMN_NAME_DDL = 260;
	int COLUMN_DEF = 261;
	int TABLE_DEF = 262;
	int A_COLUMN_DEF = 263;
	int TABLE_COLLECTION = 264;
	int VARRAY_COLLECTION = 265;
	int REF_CURSOR = 266;
	int OBJECT_TYPE_DEF = 267;
	int AT_TIME_ZONE_EXPR = 268;
	int TIMEZONE_SPEC = 269;
	int GRANT_COMMAND = 270;
	int REVOKE_COMMAND = 271;
	int ALTER_TABLE = 272;
	int ALTER_GENERIC = 273;
	int ALTER_TABLE_CONSTRAINT = 274;
	int CREATE_TEMP_TABLE = 275;
	int COMMENT_STMT = 276;
	int COMMENT_STR = 277;
	int CREATE_INDEX = 278;
	int INSERT_INTO_SUBQUERY_COMMAND = 279;
	int CONDITIONAL_COMPILATION = 280;
	int COND_COMP_SEQ = 281;
	int COND_COMP_SEQ2 = 282;
	int COND_COMP_ERROR = 283;
	int COLUMN_NAME_LIST = 284;
	int OWNER_COLUMN_NAME_LIST = 285;
	int CREATE_VIEW = 286;
	int CREATE_MATERIALIZED_VIEW = 287;
	int CREATE_MATERIALIZED_VIEW_LOG = 288;
	int DATATYPE_PARAM_INFO = 289;
	int CREATE_VIEW_COLUMN_DEF = 290;
	int TABLE_FUNCTION = 291;
	int ROWNUM = 292;
	int ROWID = 293;
	int CUSTOM_AGGR_FUNCTION = 294;
	int LAST_STMT_RESULT_NUM = 295;
	int LAST_STMT_RESULT_BOOL = 296;
	int SQL_CURSOR_FAKE_ATTR = 297;
	int CURRENT_OF_CLAUSE = 298;
	int CURSOR_STATE = 299;
	int SQLCODE_SYSVAR = 300;
	int SQLERRM_SYSVAR = 301;
	int COLLECTION_METHOD_CALL = 302;
	int COLLECTION_METHOD_CALL2 = 303;
	int C_RECORD_ITEM_REF = 304;
	int CALLABLE_NAME_REF = 305;
	int BUILTIN_FUNC_NAME = 306;
	int TRUNCATE_TABLE = 307;
	int DROP_VIEW = 308;
	int DROP_TABLE = 309;
	int DROP_FUNCTION = 310;
	int DROP_PROCEDURE = 311;
	int DROP_PACKAGE = 312;
	int DROP_INDEX = 313;
	int DROP_SEQUENCE = 314;
	int DROP_TYPE = 315;
	int DROP_OPERATOR = 316;
	int DROP_SYNONYM = 317;
	int DROP_USER = 318;
	int DROP_ROLE = 319;
	int DROP_LIBRARY = 320;
	int DROP_DB_LINK = 321;
	int DROP_DIRECTORY = 322;
	int DROP_TRIGGER = 323;
	int CREATE_SYNONYM = 324;
	int SYNONYM_NAME = 325;
	int SYNONYM_OBJ = 326;
	int SYNONYM_OBJ_WITH_LINK = 327;
	int COLUMN_PK_SPEC = 328;
	int COLUMN_FK_SPEC = 329;
	int NOT_NULL_STMT = 330;
	int COLUMN_CHECK_CONSTRAINT = 331;
	int COLUMN_NOT_NULL_CONSTRAINT = 332;
	int PK_SPEC = 333;
	int FK_SPEC = 334;
	int UNIQUE_CONSTRAINT = 335;
	int CHECK_CONSTRAINT = 336;
	int CONSTRAINT_NAME = 337;
	int ADD_CONSTRAINT = 338;
	int ADD_PRIMARY_KEY = 339;
	int ALTER_COLUMN_SPEC = 340;
	int IOT_TYPE = 341;
	int HEAP_ORGINIZED = 342;
	int EXTERNAL_TYPE = 343;
	int EXTERNAL_TABLE_SPEC = 344;
	int WRITE_ACCESS_PARAMS_SPEC = 345;
	int LOADER_ACCESS_PARAMS = 346;
	int EXT_FIELD_SPEC_LIST = 347;
	int EXT_FIELD_SPEC = 348;
	int RECORD_FMT_SPEC = 349;
	int EXT_TABLE_LOCATION_SPEC = 350;
	int STORAGE_PARAM = 351;
	int STORAGE_SPEC = 352;
	int PARALLEL_CLAUSE = 353;
	int REJECT_SPEC = 354;
	int NAME_FRAGMENT = 355;
	int EXEC_NAME_REF = 356;
	int COLLECTION_METHOD_NAME = 357;
	int V_COLUMN_DEF = 358;
	int TABLE_NAME_DDL = 359;
	int VIEW_NAME = 360;
	int INDEX_NAME = 361;
	int VIEW_NAME_DDL = 362;
	int SEQUENCE_NAME = 363;
	int USER_NAME = 364;
	int USER_ROLE = 365;
	int SYSTEM_PRIVILEGE = 366;
	int CREATE_TRIGGER = 367;
	int CREATE_DIRECTORY = 368;
	int CREATE_DB_LINK = 369;
	int CREATE_SEQUENCE = 370;
	int TRIGGER_FOR_EACH = 371;
	int TRIGGER_NAME = 372;
	int ALTER_TRIGGER = 373;
	int DB_EVNT_TRIGGER_CLAUSE = 374;
	int DDL_TRIGGER_CLAUSE = 375;
	int DML_TRIGGER_CLAUSE = 376;
	int TRIGGER_COLUMN_REF = 377;
	int INSTEADOF_TRIGGER = 378;
	int TRIGGER_TARGET = 379;
	int FORALL_LOOP_SPEC = 380;
	int CURSOR_REF_LOOP_SPEC = 381;
	int CURSOR_LOOP_SPEC = 382;
	int CURSOR_LOOP_INDEX = 383;
	int NUM_LOOP_INDEX = 384;
	int NUMERIC_LOOP_SPEC = 385;
	int RECORD_ITEM_NAME = 386;
	int GOTO_STATEMENT = 387;
	int EXIT_STATEMENT = 388;
	int LABEL_NAME = 389;
	int PARTITION_SPEC = 390;
	int RANGE_PARTITION = 391;
	int HASH_PARTITION = 392;
	int MONITORING_CLAUSE = 393;
	int CREATE_TABLESPACE = 394;
	int DROP_TABLESPACE = 395;
	int TABLESPACE_NAME = 396;
	int ALTER_TABLESPACE = 397;
	int CREATE_USER = 398;
	int CREATE_TYPE = 399;
	int BIND_VAR = 400;
	int RETURNING_CLAUSE = 401;
	int ALTER_INDEX = 402;
	int RENAME_TABLE = 403;
	int TABLE_REF_NOT_RESOLVED = 404;
	int VIEW_NAME_REF = 405;
	int TABLE_NAME_REF = 406;
	int GENERIC_REF = 407;
	int CALL_NOT_RESOLVED = 408;
	int BUILT_IT_FUNCTION_CALL = 409;
	int UDF_CALL = 410;
	int UDP_CALL = 411;
	int LITERAL_package = 412;
	int LITERAL_body = 413;
	int LITERAL_alter = 414;
	int LITERAL_drop = 415;
	int LITERAL_table = 416;
	int LITERAL_purge = 417;
	int LITERAL_view = 418;
	int LITERAL_cascade = 419;
	int LITERAL_constraints = 420;
	int LITERAL_function = 421;
	int LITERAL_procedure = 422;
	int LITERAL_index = 423;
	int LITERAL_force = 424;
	int LITERAL_sequence = 425;
	int LITERAL_type = 426;
	int LITERAL_validate = 427;
	int LITERAL_public = 428;
	int LITERAL_synonym = 429;
	int LITERAL_operator = 430;
	int LITERAL_user = 431;
	int LITERAL_role = 432;
	int LITERAL_directory = 433;
	int LITERAL_library = 434;
	int LITERAL_database = 435;
	int LITERAL_link = 436;
	int LITERAL_trigger = 437;
	int LITERAL_associate = 438;
	int LITERAL_statistics = 439;
	int LITERAL_with = 440;
	int LITERAL_column = 441;
	int LITERAL_functions = 442;
	int LITERAL_packages = 443;
	int LITERAL_types = 444;
	int LITERAL_indexes = 445;
	int LITERAL_indextypes = 446;
	int LITERAL_system = 447;
	int LITERAL_managed = 448;
	int LITERAL_storage = 449;
	int LITERAL_default = 450;
	int LITERAL_cost = 451;
	int LITERAL_selectivity = 452;
	int LITERAL_using = 453;
	int LITERAL_null = 454;
	int LITERAL_truncate = 455;
	int LITERAL_comment = 456;
	int LITERAL_on = 457;
	int LITERAL_is = 458;
	int LITERAL_not = 459;
	int LITERAL_disable = 460;
	int LITERAL_enable = 461;
	int LITERAL_row = 462;
	int LITERAL_movement = 463;
	int LITERAL_constraint = 464;
	int LITERAL_primary = 465;
	int LITERAL_key = 466;
	int LITERAL_references = 467;
	int LITERAL_rely = 468;
	int LITERAL_check = 469;
	int LITERAL_unique = 470;
	int LITERAL_sysdate = 471;
	int LITERAL_systimestamp = 472;
	int LITERAL_set = 473;
	int LITERAL_long = 474;
	int LITERAL_show = 475;
	int LITERAL_var = 476;
	int LITERAL_variable = 477;
	int LITERAL_col = 478;
	int LITERAL_exec = 479;
	int LITERAL_execute = 480;
	int LITERAL_whenever = 481;
	int LITERAL_sqlerror = 482;
	int LITERAL_oserror = 483;
	int LITERAL_exit = 484;
	int LITERAL_continue = 485;
	int LITERAL_commit = 486;
	int LITERAL_rollback = 487;
	int LITERAL_none = 488;
	int LITERAL_def = 489;
	int LITERAL_define = 490;
	int LITERAL_prompt = 491;
	int LITERAL_rem = 492;
	int LITERAL_host = 493;
	int LITERAL_quit = 494;
	int LITERAL_spool = 495;
	int LITERAL_sta = 496;
	int LITERAL_start = 497;
	int LITERAL_repfooter = 498;
	int LITERAL_off = 499;
	int LITERAL_repheader = 500;
	int LITERAL_serveroutput = 501;
	int LITERAL_begin = 502;
	int LITERAL_declare = 503;
	int DOUBLE_DOT = 504;
	int LITERAL_rename = 505;
	int LITERAL_create = 506;
	int LITERAL_or = 507;
	int LITERAL_replace = 508;
	int LITERAL_materialized = 509;
	int LITERAL_log = 510;
	int LITERAL_global = 511;
	int LITERAL_temporary = 512;
	int LITERAL_under = 513;
	int LITERAL_final = 514;
	int LITERAL_as = 515;
	int LITERAL_object = 516;
	int LITERAL_identified = 517;
	int LITERAL_by = 518;
	int LITERAL_externally = 519;
	int LITERAL_globally = 520;
	int LITERAL_tablespace = 521;
	int LITERAL_quota = 522;
	int LITERAL_unlimited = 523;
	int LITERAL_profile = 524;
	int LITERAL_password = 525;
	int LITERAL_expire = 526;
	int LITERAL_account = 527;
	int LITERAL_lock = 528;
	int LITERAL_unlock = 529;
	int LITERAL_bigfile = 530;
	int LITERAL_smallfile = 531;
	int LITERAL_tempfile = 532;
	int LITERAL_undo = 533;
	int LITERAL_datafile = 534;
	int LITERAL_next = 535;
	int LITERAL_maxsize = 536;
	int LITERAL_size = 537;
	int LITERAL_reuse = 538;
	int LITERAL_extent = 539;
	int LITERAL_management = 540;
	int LITERAL_local = 541;
	int LITERAL_uniform = 542;
	int LITERAL_dictionary = 543;
	int LITERAL_retention = 544;
	int LITERAL_guarantee = 545;
	int LITERAL_noguarantee = 546;
	int LITERAL_autoextend = 547;
	int LITERAL_group = 548;
	int LITERAL_logging = 549;
	int LITERAL_nologging = 550;
	int LITERAL_no = 551;
	int LITERAL_online = 552;
	int LITERAL_offline = 553;
	int LITERAL_normal = 554;
	int LITERAL_immediate = 555;
	int LITERAL_read = 556;
	int LITERAL_only = 557;
	int LITERAL_write = 558;
	int LITERAL_permanent = 559;
	int LITERAL_including = 560;
	int LITERAL_contents = 561;
	int LITERAL_and = 562;
	int LITERAL_datafiles = 563;
	int LITERAL_add = 564;
	int LITERAL_to = 565;
	int LITERAL_end = 566;
	int LITERAL_backup = 567;
	int LITERAL_coalesce = 568;
	int LITERAL_minimum = 569;
	int LITERAL_maxvalue = 570;
	int LITERAL_minvalue = 571;
	int LITERAL_cycle = 572;
	int LITERAL_nocycle = 573;
	int LITERAL_cache = 574;
	int LITERAL_nocache = 575;
	int LITERAL_increment = 576;
	int LITERAL_order = 577;
	int LITERAL_noorder = 578;
	int LITERAL_for = 579;
	int LITERAL_connect = 580;
	int LITERAL_after = 581;
	int LITERAL_before = 582;
	int LITERAL_startup = 583;
	int LITERAL_shutdown = 584;
	int LITERAL_servererror = 585;
	int LITERAL_logon = 586;
	int LITERAL_logoff = 587;
	int LITERAL_analyze = 588;
	int LITERAL_audit = 589;
	int LITERAL_noaudit = 590;
	int LITERAL_ddl = 591;
	int LITERAL_diassociate = 592;
	int LITERAL_grant = 593;
	int LITERAL_revoke = 594;
	int LITERAL_schema = 595;
	int LITERAL_instead = 596;
	int LITERAL_of = 597;
	int LITERAL_delete = 598;
	int LITERAL_insert = 599;
	int LITERAL_update = 600;
	int LITERAL_each = 601;
	int LITERAL_referencing = 602;
	int LITERAL_old = 603;
	int LITERAL_new = 604;
	int LITERAL_when = 605;
	int LITERAL_bitmap = 606;
	int LITERAL_asc = 607;
	int LITERAL_desc = 608;
	int LITERAL_preserve = 609;
	int LITERAL_rows = 610;
	int LITERAL_nested = 611;
	int LITERAL_store = 612;
	int LITERAL_return = 613;
	int LITERAL_locator = 614;
	int LITERAL_value = 615;
	int LITERAL_lob = 616;
	int LITERAL_chunk = 617;
	int LITERAL_reads = 618;
	int LITERAL_pctversion = 619;
	int LITERAL_freepools = 620;
	int LITERAL_in = 621;
	int LITERAL_segment = 622;
	int LITERAL_creation = 623;
	int LITERAL_deferred = 624;
	int LITERAL_cluster = 625;
	int LITERAL_pctthreshold = 626;
	int LITERAL_filesystem_like_logging = 627;
	int LITERAL_compress = 628;
	int LITERAL_all = 629;
	int LITERAL_direct_load = 630;
	int LITERAL_operations = 631;
	int LITERAL_nocompress = 632;
	int LITERAL_pctfree = 633;
	int LITERAL_pctused = 634;
	int LITERAL_initrans = 635;
	int LITERAL_maxtrans = 636;
	int LITERAL_compute = 637;
	int LITERAL_parallel = 638;
	int LITERAL_noparallel = 639;
	int LITERAL_monitoring = 640;
	int LITERAL_nomonitoring = 641;
	int LITERAL_overflow = 642;
	int LITERAL_partition = 643;
	int LITERAL_range = 644;
	int LITERAL_interval = 645;
	int LITERAL_values = 646;
	int LITERAL_less = 647;
	int LITERAL_than = 648;
	int LITERAL_hash = 649;
	int LITERAL_partitions = 650;
	int LITERAL_novalidate = 651;
	int LITERAL_organization = 652;
	int LITERAL_heap = 653;
	int LITERAL_external = 654;
	int LITERAL_degree = 655;
	int LITERAL_instances = 656;
	int LITERAL_reject = 657;
	int LITERAL_limit = 658;
	int LITERAL_initial = 659;
	int LITERAL_minextents = 660;
	int LITERAL_maxextents = 661;
	int LITERAL_pctincrease = 662;
	int LITERAL_freelists = 663;
	int LITERAL_freelist = 664;
	int LITERAL_groups = 665;
	int LITERAL_optimal = 666;
	int LITERAL_buffer_pool = 667;
	int LITERAL_keep = 668;
	int LITERAL_recycle = 669;
	int LITERAL_flash_cache = 670;
	int LITERAL_cell_flash_cache = 671;
	int LITERAL_encrypt = 672;
	int LITERAL_foreign = 673;
	int LITERAL_restrict = 674;
	int LITERAL_action = 675;
	int LITERAL_modify = 676;
	int LITERAL_sort = 677;
	int LITERAL_nosort = 678;
	int LITERAL_reverse = 679;
	int LITERAL_visible = 680;
	int LITERAL_novisible = 681;
	int LITERAL_record = 682;
	int LITERAL_ref = 683;
	int LITERAL_cursor = 684;
	int LITERAL_rowtype = 685;
	int LITERAL_varray = 686;
	int LITERAL_option = 687;
	int LITERAL_prebuilt = 688;
	int LITERAL_without = 689;
	int LITERAL_reduced = 690;
	int LITERAL_precision = 691;
	int LITERAL_excluding = 692;
	int LITERAL_rowid = 693;
	int LITERAL_query = 694;
	int LITERAL_rewrite = 695;
	int LITERAL_never = 696;
	int LITERAL_refresh = 697;
	int LITERAL_build = 698;
	int LITERAL_fast = 699;
	int LITERAL_complete = 700;
	int LITERAL_demand = 701;
	// "view_column_def_$internal$" = 702
	int LITERAL_timestamp = 703;
	int LITERAL_authid = 704;
	int LITERAL_wrapped = 705;
	int LITERAL_pragma = 706;
	int LITERAL_restrict_references = 707;
	int LITERAL_interface = 708;
	int LITERAL_builtin = 709;
	int LITERAL_fipsflag = 710;
	int LITERAL_exception_init = 711;
	int LITERAL_constant = 712;
	int LITERAL_subtype = 713;
	int LITERAL_loop = 714;
	int LITERAL_while = 715;
	int LITERAL_sql = 716;
	int LITERAL_found = 717;
	int LITERAL_notfound = 718;
	int LITERAL_rowcount = 719;
	int LITERAL_isopen = 720;
	int LITERAL_bulk_rowcount = 721;
	int LITERAL_bulk_exceptions = 722;
	int LITERAL_error_index = 723;
	int LITERAL_error_code = 724;
	int LITERAL_count = 725;
	int LITERAL_case = 726;
	int LITERAL_then = 727;
	int LITERAL_else = 728;
	int LITERAL_autonomous_transaction = 729;
	int LITERAL_prior = 730;
	int LITERAL_goto = 731;
	int LITERAL_number = 732;
	int LITERAL_binary_integer = 733;
	int LITERAL_natural = 734;
	int LITERAL_positive = 735;
	int LITERAL_char = 736;
	int LITERAL_byte = 737;
	int LITERAL_raw = 738;
	int LITERAL_boolean = 739;
	int LITERAL_date = 740;
	int LITERAL_time = 741;
	int LITERAL_zone = 742;
	int LITERAL_year = 743;
	int LITERAL_month = 744;
	int LITERAL_day = 745;
	int LITERAL_second = 746;
	int LITERAL_smallint = 747;
	int LITERAL_real = 748;
	int LITERAL_numeric = 749;
	int LITERAL_int = 750;
	int LITERAL_integer = 751;
	int LITERAL_pls_integer = 752;
	int LITERAL_double = 753;
	int LITERAL_float = 754;
	int LITERAL_decimal = 755;
	int LITERAL_varchar = 756;
	// "varchar2" = 757
	int LITERAL_nvarchar = 758;
	// "nvarchar2" = 759
	int LITERAL_character = 760;
	int LITERAL_blob = 761;
	int LITERAL_clob = 762;
	int LITERAL_nclob = 763;
	int LITERAL_bfile = 764;
	int LITERAL_out = 765;
	int LITERAL_nocopy = 766;
	int LITERAL_any_cs = 767;
	int LITERAL_charset = 768;
	int LITERAL_exception = 769;
	int LITERAL_serially_reusable = 770;
	int LITERAL_pipelined = 771;
	int LITERAL_parallel_enable = 772;
	int LITERAL_deterministic = 773;
	int LITERAL_result_cache = 774;
	int LITERAL_relies_on = 775;
	int LITERAL_language = 776;
	int LITERAL_java = 777;
	int LITERAL_name = 778;
	int LITERAL_raise = 779;
	int LITERAL_save = 780;
	int LITERAL_exceptions = 781;
	int LITERAL_forall = 782;
	int LITERAL_indices = 783;
	int LITERAL_true = 784;
	int LITERAL_false = 785;
	// "**" = 786
	int LITERAL_at = 787;
	int LITERAL_current = 788;
	int LITERAL_exists = 789;
	int LITERAL_select = 790;
	int LITERAL_like = 791;
	int LITERAL_escape = 792;
	int LITERAL_between = 793;
	int LITERAL_member = 794;
	int LITERAL_sqlcode = 795;
	int LITERAL_sqlerrm = 796;
	int LITERAL_cast = 797;
	int LITERAL_decode = 798;
	int LITERAL_trim = 799;
	int LITERAL_multiset = 800;
	int LITERAL_lag = 801;
	int LITERAL_lead = 802;
	int LITERAL_rank = 803;
	int LITERAL_dense_rank = 804;
	int LITERAL_extract = 805;
	int LITERAL_minute = 806;
	int LITERAL_hour = 807;
	int LITERAL_any = 808;
	int LITERAL_nextval = 809;
	int LITERAL_currval = 810;
	int LITERAL_over = 811;
	int LITERAL_sessiontimezone = 812;
	int LITERAL_dbtimezone = 813;
	int LITERAL_distinct = 814;
	int LITERAL_from = 815;
	int LITERAL_timezone_hour = 816;
	int LITERAL_timezone_minute = 817;
	int LITERAL_timezone_region = 818;
	int LITERAL_timezone_abbr = 819;
	int LITERAL_leading = 820;
	int LITERAL_trailing = 821;
	int LITERAL_both = 822;
	int LITERAL_work = 823;
	int LITERAL_if = 824;
	int LITERAL_elsif = 825;
	int LITERAL_privileges = 826;
	int LITERAL_hierarchy = 827;
	int LITERAL_debug = 828;
	int LITERAL_admin = 829;
	int LITERAL_resource = 830;
	int LITERAL_indextype = 831;
	int LITERAL_session = 832;
	int LITERAL_become = 833;
	int LITERAL_union = 834;
	int LITERAL_intersect = 835;
	int LITERAL_minus = 836;
	int LITERAL_bulk = 837;
	int LITERAL_collect = 838;
	int LITERAL_into = 839;
	int LITERAL_flush = 840;
	int LITERAL_shared_pool = 841;
	int LITERAL_reset = 842;
	int LITERAL_sid = 843;
	int LITERAL_rebuild = 844;
	int LITERAL_unusable = 845;
	int LITERAL_left = 846;
	int LITERAL_right = 847;
	int LITERAL_inner = 848;
	int LITERAL_full = 849;
	int LITERAL_where = 850;
	int LITERAL_current_timestamp = 851;
	int LITERAL_rownum = 852;
	int LITERAL_the = 853;
	int LITERAL_outer = 854;
	int LITERAL_join = 855;
	int LITERAL_having = 856;
	int LITERAL_nulls = 857;
	int LITERAL_first = 858;
	int LITERAL_last = 859;
	int LITERAL_nowait = 860;
	int LITERAL_wait = 861;
	int LITERAL_merge = 862;
	int LITERAL_matched = 863;
	int LITERAL_returning = 864;
	int LITERAL_transaction = 865;
	int LITERAL_close = 866;
	int LITERAL_fetch = 867;
	int LITERAL_mode = 868;
	int LITERAL_share = 869;
	int LITERAL_exclusive = 870;
	int LITERAL_open = 871;
	int LITERAL_savepoint = 872;
	int LITERAL_oracle_loader = 873;
	int LITERAL_oracle_datapump = 874;
	int LITERAL_access = 875;
	int LITERAL_parameters = 876;
	int LITERAL_nologfile = 877;
	int LITERAL_logfile = 878;
	int LITERAL_version = 879;
	int LITERAL_compatible = 880;
	int LITERAL_latest = 881;
	int LITERAL_compression = 882;
	int LITERAL_enabled = 883;
	int LITERAL_disabled = 884;
	int LITERAL_encryption = 885;
	int LITERAL_records = 886;
	int LITERAL_fixed = 887;
	int LITERAL_delimited = 888;
	int LITERAL_newline = 889;
	int LITERAL_characterset = 890;
	int LITERAL_data = 891;
	int LITERAL_big = 892;
	int LITERAL_little = 893;
	int LITERAL_endian = 894;
	int LITERAL_mark = 895;
	int LITERAL_nocheck = 896;
	int LITERAL_string = 897;
	int LITERAL_sizes = 898;
	int LITERAL_bytes = 899;
	int LITERAL_characters = 900;
	int LITERAL_load = 901;
	int LITERAL_nobadfile = 902;
	int LITERAL_badfile = 903;
	int LITERAL_nodiscardfile = 904;
	int LITERAL_discardfile = 905;
	int LITERAL_readsize = 906;
	int LITERAL_data_cache = 907;
	int LITERAL_skip = 908;
	int LITERAL_preprocessor = 909;
	int LITERAL_fields = 910;
	int LITERAL_missing = 911;
	int LITERAL_field = 912;
	int LITERAL_are = 913;
	int LITERAL_transforms = 914;
	int LITERAL_concat = 915;
	int LITERAL_lobfile = 916;
	int LITERAL_enclosed = 917;
	int LITERAL_terminated = 918;
	int LITERAL_whitespace = 919;
	int LITERAL_optionally = 920;
	int LITERAL_lrtrim = 921;
	int LITERAL_notrim = 922;
	int LITERAL_ltrim = 923;
	int LITERAL_rtrim = 924;
	int LITERAL_ldrtrim = 925;
	int LITERAL_position = 926;
	int LITERAL_unsigned = 927;
	int LITERAL_zoned = 928;
	int LITERAL_oracle_date = 929;
	int LITERAL_oracle_number = 930;
	int LITERAL_counted = 931;
	int LITERAL_varraw = 932;
	int LITERAL_varcharc = 933;
	int LITERAL_varrawc = 934;
	int LITERAL_date_format = 935;
	int LITERAL_timezone = 936;
	int LITERAL_mask = 937;
	int LITERAL_location = 938;
	int LITERAL_aggregate = 939;
	int LITERAL_ldtrim = 940;
}
