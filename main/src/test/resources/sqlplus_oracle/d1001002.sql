Rem
Rem $Header: d1001002.sql 22-may-2005.17:34:24 mfaisal Exp $
Rem
Rem d1001002.sql
Rem
Rem Copyright (c) 2004, 2005, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      d1001002.sql - <one-line expansion of the name>
Rem
Rem    DESCRIPTION
Rem      downgrade CTXSYS 10.2 to 10.1
Rem
Rem    NOTES
Rem      IMPORTANT: AFTER DOWNGRADE, IT WILL BE CLOSE TO 10.1.0.2
Rem        IF YOU PREVIOUSLY HAD SOME OTHER PATCHSET VERSION
Rem        YOU MUST RE-UPGRADE
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    mfaisal     05/22/05 - bug 4385898 
Rem    wclin       03/07/05 - grant back exec priv on ODCIStats Impl. type 
Rem    wclin       01/28/05 - grant back exec priv on ODCIIndex Impl types 
Rem    daliao      01/26/05 - bug 4144867
Rem    gkaminag    11/03/04 - bug 3986658 
Rem    gkaminag    09/16/04 - part field style attribute of mail filter 
Rem    tokawa      07/27/04 - add KOREAN_LEXER
Rem    mfaisal     08/04/04 - keyview html export release 8.0.0 
Rem    tokawa      03/29/04 - mixed-case for zh/ja/world lexers.
Rem    gkaminag    03/22/04 - gkaminag_misc_040318 
Rem    gkaminag    03/18/04 - Created
Rem

REM ========================================================================
REM MIXED CASE FOR JAPANESE LEXERS
REM ========================================================================

delete from dr$object_attribute where oat_id = 60207;
delete from dr$object_attribute where oat_id = 60807;
commit;

REM ========================================================================
REM MIXED CASE FOR CHINESE LEXERS
REM ========================================================================

delete from dr$object_attribute where oat_id = 60407;
delete from dr$object_attribute where oat_id = 60507;
commit;

REM ========================================================================
REM MIXED CASE FOR WORLD LEXER
REM ========================================================================

delete from dr$object_attribute where oat_id = 61107;
commit;

REM ========================================================================
REM ADD KOREAN_LEXER
REM ========================================================================

update dr$object_attribute set oat_system = 'N'
    where oat_cla_id = 6 and oat_obj_id = 3;
update dr$object set obj_system = 'N'
    where obj_cla_id = 6 and obj_id = 3;

commit;

REM ========================================================================
REM PART_FIELD_STYLE of MAIL_FILTER
REM ========================================================================

delete from dr$object_attribute_lov where oal_oat_id = 40704;
delete from dr$object_attribute where oat_id = 40704;
commit;

REM ========================================================================
REM AUTO_FILTER_TIMEOUT and AUTO_FILTER_OUTPUT_FORMATTING attributes for
REM MAIL_FILTER
REM ========================================================================

update dr$index_value
  set ixv_oat_id = 40702
  where ixv_oat_id = 40705;
update dr$index_value
  set ixv_oat_id = 40703
  where ixv_oat_id = 40706;

delete from dr$object_attribute where oat_id = 40705;
delete from dr$object_attribute where oat_id = 40706;
commit;

REM ========================================================================
REM DEFAULT_FILTER_FILE AND DEFAULT_FILTER_BINARY SYSTEM PARAMETERS
REM ========================================================================

update dr$parameter 
set par_value = 'CTXSYS.INSO_FILTER'
where par_name = 'DEFAULT_FILTER_BINARY' and par_value = 'CTXSYS.AUTO_FILTER';

update dr$parameter
set par_value = 'CTXSYS.INSO_FILTER'
where par_name = 'DEFAULT_FILTER_FILE' and par_value = 'CTXSYS.AUTO_FILTER';

commit;

REM ========================================================================
REM CTXSYS.AUTO_FILTER DEFAULT PREFERENCE
REM ========================================================================

delete from dr$preference where pre_cla_id = 4 and pre_obj_id = 8;
commit;

REM ========================================================================
REM AUTO_FILTER
REM ========================================================================

update dr$index_value
  set ixv_oat_id = 40502
  where ixv_oat_id = 40802;
update dr$index_value
  set ixv_oat_id = 40503
  where ixv_oat_id = 40803;
update dr$index_value
  set ixv_oat_id = 40504
  where ixv_oat_id = 40804;

update dr$index_object
  set ixo_obj_id = 5
  where ixo_cla_id = 4 and ixo_obj_id = 8;

delete from dr$object_attribute_lov where oal_oat_id = 40803;
delete from dr$object_attribute where oat_cla_id = 4 and oat_obj_id = 8;
delete from dr$object where obj_cla_id = 4 and obj_id = 8;
commit;

REM ========================================================================
REM odm_util package
REM ========================================================================

drop public synonym odm_util;   
drop package odm_util_dummy;

REM ========================================================================
REM Drop new validate_context procedure
REM ========================================================================

ALTER SESSION SET CURRENT_SCHEMA = SYS;
drop procedure validate_context;
ALTER SESSION SET CURRENT_SCHEMA = CTXSYS;

REM ========================================================================
REM Grant back execute priv. on TextIndexMethods, CatIndexMethods,
REM RuleIndexMethods, and XpathIndexMethods  to public
REM ========================================================================

grant execute on TextIndexMethods to public;
alter type TextIndexMethods compile;

grant execute on CatIndexMethods to public;
alter type CatIndexMethods compile;

grant execute on RuleIndexMethods to public;
alter type RuleIndexMethods compile;

grant execute on XpathIndexMethods to public;
alter type XpathIndexMethods compile;

grant execute on ctx_contains to public;
alter package ctx_contains compile;

grant execute on driscore to public;
alter package driscore compile;

grant execute on ctx_catsearch to public;
alter package ctx_catsearch compile;

grant execute on ctx_matches  to public;
alter package ctx_matches  compile;

grant execute on driscorr to public;
alter package driscorr compile;

grant execute on ctx_xpcontains to public;
alter package ctx_xpcontains  compile;

grant execute on TextOptStats to public;
alter type TextOptStats  compile;


