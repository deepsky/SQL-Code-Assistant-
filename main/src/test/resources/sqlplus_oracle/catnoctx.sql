
Rem =======================================================================
Rem script is ALWAYS run as SYS,  must set current_schema to CTXSYS before
Rem loading context
Rem =======================================================================
ALTER SESSION SET CURRENT_SCHEMA = CTXSYS;

Rem =======================================================================
Rem signal beginning of removal
Rem =======================================================================
EXECUTE dbms_registry.removing('CONTEXT');

Rem =======================================================================
Rem drop all objects
Rem =======================================================================

PROMPT dropping all ctxsys objects...
drop package driobj;

drop package dr_def;

drop package drig;

drop package driacc;

drop package dricon;

drop package dridisp;

drop package dridml;

drop package dridoc;

drop package drierr;

drop package driexp;

drop package driimp;

drop package driixs;

drop package drilist;

drop package driload;

drop package drimlx;

drop package driopt;

drop package driparse;

drop package dripref;

drop package drirec;

drop package drirep;

drop package drirepm;

drop package drirepz;

drop package drisgp;

drop package drispl;

drop package driths;

drop package drithsc;

drop package drithsd;

drop package drithsl;

drop package drithsx;

drop package driutl;

drop package drival;

drop package drixmd;

drop package drvdisp;

drop package drvddl;

drop package drvddlc;

drop package drvddlr;

drop package drvddlx;

drop package drvdml;

drop package drvdoc;

drop package drue;

drop package drvimr;

drop package driparx;

drop package drvodm;

drop package drvparx;

drop package drvtmt;

drop package drvutl;

drop package drvxmd;

drop package drvxtab;

drop package drvxtabc;

drop package drvxtabr;

drop package drvxtabx;

drop package drv0ddl;

drop package ctx_adm;

drop package ctx_ddl;

drop package ctx_doc;

drop package ctx_output;

drop package ctx_query;

drop package ctx_thes;

drop package ctx_report;

drop package ctx_ulexer;

drop package ctx_cls;

drop public synonym ctx_doc;

drop public synonym ctx_ddl;

drop public synonym ctx_output;

drop public synonym ctx_query;

drop public synonym ctx_thes;

drop public synonym ctx_report;

drop public synonym ctx_ulexer;

drop public synonym ctx_cls;

drop public synonym drvodm;

drop public synonym ctx_parameters;

drop public synonym ctx_classes;

drop public synonym ctx_objects;

drop public synonym ctx_object_attributes;

drop public synonym ctx_object_attribute_lov;

drop public synonym ctx_preferences;

drop public synonym ctx_user_preferences;

drop public synonym ctx_preference_values;

drop public synonym ctx_user_preference_values;

drop public synonym ctx_user_indexes;

drop public synonym ctx_user_index_partitions;

drop public synonym ctx_user_index_values;

drop public synonym ctx_user_index_sub_lexers;

drop public synonym ctx_user_index_sub_lexer_vals;

drop public synonym ctx_user_index_objects;

drop public synonym ctx_sqes;

drop public synonym ctx_user_sqes;

drop public synonym ctx_thesauri;

drop public synonym ctx_user_thesauri;

drop public synonym ctx_thes_phrases;

drop public synonym ctx_user_thes_phrases;

drop public synonym ctx_section_groups;

drop public synonym ctx_user_section_groups;

drop public synonym ctx_sections;

drop public synonym ctx_user_sections;

drop public synonym ctx_stoplists;

drop public synonym ctx_user_stoplists;

drop public synonym ctx_stopwords;

drop public synonym ctx_user_stopwords;

drop public synonym ctx_sub_lexers;

drop public synonym ctx_user_sub_lexers;

drop public synonym ctx_index_sets;

drop public synonym ctx_user_index_sets;

drop public synonym ctx_index_set_indexes;

drop public synonym ctx_user_index_set_indexes;

drop public synonym ctx_user_pending;

drop public synonym ctx_user_index_errors;

drop public synonym ctx_trace_values;

drop public synonym contains;

drop public synonym score;

drop public synonym context;

drop public synonym catsearch;

drop public synonym ctxcat;

drop public synonym matches;

drop public synonym match_score;

drop public synonym ctxrule;

drop public synonym ctxxpath;

drop operator xpcontains force;

drop package ctx_xpcontains ;

drop operator match_score force;

drop package driscorr ;

drop operator matches force;

drop package ctx_matches ;

drop operator catsearch force;

drop package ctx_catsearch ;

drop procedure syncrn ;

drop operator score force;

drop package driscore ;

drop operator contains force;

drop package ctx_contains ;

drop type xpathindexmethods force;

drop type ruleindexmethods force;

drop type catindexmethods force;

drop type textoptstats force;

drop type textindexmethods force;

drop view ctx_trace_values ;

drop view ctx_version ;

drop function dri_version ;

drop view ctx_user_index_errors ;

drop view ctx_index_errors ;

drop view drv$unindexed2 ;

drop view drv$unindexed ;

drop view drv$delete2 ;

drop view drv$delete ;

drop view drv$online_pending ;

drop view drv$waiting ;

drop view ctx_user_pending ;

drop view ctx_pending ;

drop view drv$pending ;

drop view ctx_user_index_set_indexes ;

drop view ctx_index_set_indexes ;

drop view ctx_user_index_sets ;

drop view ctx_index_sets ;

drop view ctx_user_sub_lexers ;

drop view ctx_sub_lexers ;

drop view ctx_user_stopwords ;

drop view ctx_stopwords ;

drop view ctx_user_stoplists ;

drop view ctx_stoplists ;

drop view ctx_user_sections ;

drop view ctx_sections ;

drop view ctx_user_section_groups ;

drop view ctx_section_groups ;

drop view ctx_user_thes_phrases ;

drop view ctx_thes_phrases ;

drop view ctx_user_thesauri ;

drop view ctx_thesauri ;

drop view ctx_user_sqes ;

drop view ctx_sqes ;

drop view ctx_user_index_objects ;

drop view ctx_index_objects ;

drop view ctx_user_index_sub_lexer_vals ;

drop view ctx_index_sub_lexer_values ;

drop view ctx_user_index_sub_lexers ;

drop view ctx_index_sub_lexers ;

drop function dri_sublxv_lang ;

drop view ctx_user_index_values ;

drop view ctx_index_values ;

drop view ctx_user_index_partitions ;

drop view ctx_index_partitions ;

drop view ctx_user_indexes ;

drop view ctx_indexes ;

drop view ctx_user_preference_values ;

drop view ctx_preference_values ;

drop view ctx_user_preferences ;

drop view ctx_preferences ;

drop view ctx_object_attribute_lov ;

drop view ctx_object_attributes ;

drop view ctx_objects ;

drop view ctx_classes ;

drop view ctx_parameters ;

drop table dr$nvtab ;

drop type ctx_feedback_type ;

drop type ctx_feedback_item_type ;

drop sequence ths_seq ;

drop sequence mesg_id_seq ;

drop sequence dr_id_seq ;

drop table dr$dbo ;

drop table dr$number_sequence ;

drop table dr$stats ;

drop table dr$parallel ;

drop table dr$index_error ;

drop table dr$unindexed ;

drop table dr$delete ;

drop table dr$online_pending ;

drop table dr$waiting ;

drop table dr$pending ;

drop table dr$index_set_index ;

drop table dr$index_set ;

drop table dr$sub_lexer ;

drop table dr$stopword ;

drop table dr$stoplist ;

drop table dr$section ;

drop table dr$section_group ;

drop type type force;

drop type type force;

drop table dr$ths_bt ;

drop table dr$ths_fphrase ;

drop table dr$ths_phrase ;

drop table dr$ths ;

drop table dr$sqe ;

drop table dr$index_object ;

drop table dr$policy_tab ;

drop table dr$index_value ;

drop table dr$index_partition ;

drop table table ;

drop table dr$index ;

drop table dr$preference_value ;

drop table dr$preference ;

drop table dr$object_attribute_lov ;

drop table dr$object_attribute ;

drop table dr$object ;

drop table dr$class ;

drop table dr$parameter ;


Rem =======================================================================
Rem signal end of removal
Rem =======================================================================
EXECUTE dbms_registry.removed('CONTEXT');

Rem =======================================================================
Rem now drop ctxsys itself
Rem =======================================================================
ALTER SESSION SET CURRENT_SCHEMA = SYS;

PROMPT dropping user ctxsys...

drop role CTXAPP;
drop user ctxsys cascade;
