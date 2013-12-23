Rem $Header: ctxview.sql 13-nov-2003.16:34:44 surman Exp $
Rem
Rem
Rem ctxview.sql
Rem
Rem Copyright (c) 2002, 2003, Oracle Corporation.  All rights reserved.
Rem
Rem    NAME
Rem      ctxview.sql
Rem
Rem    DESCRIPTION
Rem      create or replace public views, with grants and public
Rem      synonyms, include any fixed views.
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    surman      11/13/03 - 3242708: Remove server tables
Rem    ehuang      10/24/03 - tracing
Rem    gkaminag    09/16/03 - mdata
Rem    yucheng     07/01/03 - migrate to scheduler
Rem    yucheng     06/05/03 - add sync attributes to view
Rem    gkaminag    04/14/03 -
Rem    gkaminag    12/17/02 - procedure datatype
Rem    gkaminag    11/26/02 - increase size of version string
Rem    gkaminag    09/24/02 - security phase 3
Rem    gkaminag    07/17/02 - security phase 2
Rem    ehuang      07/02/02 - security overhaul changes
Rem    ehuang      06/17/02 - ehuang_component_upgrade
Rem    ehuang      06/11/02 - Created
Rem


REM ===================================================================
REM OBJECT DICTIONARY VIEWS
REM ===================================================================

REM -------------------------------------------------------------------
REM  dr$parameters
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_parameters

CREATE OR REPLACE VIEW ctx_parameters AS
   select par_name, par_value
     from dr$parameter;

CREATE OR REPLACE PUBLIC SYNONYM ctx_parameters for ctxsys.ctx_parameters;
GRANT select ON ctx_parameters TO public;

REM -------------------------------------------------------------------
REM  dr$class
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_classes

CREATE OR REPLACE VIEW ctx_classes AS
   select  cla_name
          ,cla_desc  cla_description
     from  dr$class
    where  cla_system = 'N';

CREATE OR REPLACE PUBLIC SYNONYM ctx_classes FOR ctxsys.ctx_classes;
GRANT select ON ctx_classes TO public;

REM -------------------------------------------------------------------
REM  dr$object
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_objects

CREATE OR REPLACE VIEW ctx_objects AS
select cla_name  obj_class,
       obj_name  obj_name,
       obj_desc  obj_description
  from dr$class,
       dr$object
 where cla_id     = obj_cla_id
   and obj_system = 'N'
   and cla_system = 'N';

CREATE OR REPLACE PUBLIC SYNONYM ctx_objects FOR ctxsys.ctx_objects;
GRANT select ON ctx_objects TO public;

REM -------------------------------------------------------------------
REM  dr$object_attribute
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_object_attributes

CREATE OR REPLACE VIEW ctx_object_attributes AS
select cla_name      oat_class,
       obj_name      oat_object,
       oat_name      oat_attribute,
       oat_desc      oat_description,
       oat_required  oat_required,
       oat_static    oat_static,
       decode(oat_datatype,'S','STRING','I','INTEGER','B','BOOLEAN',
                           'P','PROCEDURE')
                     oat_datatype,
       oat_default   oat_default,
       oat_val_min   oat_min,
       decode(oat_datatype, 'S', null, oat_val_max) oat_max,
       decode(oat_datatype, 'S', oat_val_max, null) oat_max_length
  from dr$class,
       dr$object,
       dr$object_attribute
 where cla_id     = obj_cla_id
   and obj_cla_id = oat_cla_id
   and obj_id     = oat_obj_id
   and oat_system = 'N'
   and cla_system = 'N';

CREATE OR REPLACE PUBLIC SYNONYM ctx_object_attributes FOR
  ctxsys.ctx_object_attributes;
GRANT select ON ctx_object_attributes TO public;

REM -------------------------------------------------------------------
REM  dr$object_attribute_lov
REM -------------------------------------------------------------------

PROMPT ... creating view dr$object_attribute_lov

CREATE OR REPLACE VIEW ctx_object_attribute_lov AS
select cla_name    oal_class,
       obj_name    oal_object,
       oat_name    oal_attribute,
       oal_label   oal_label,
       oal_value   oal_value,
       oal_desc    oal_description
  from dr$class,
       dr$object,
       dr$object_attribute,
       dr$object_attribute_lov
 where cla_id     = obj_cla_id
   and obj_cla_id = oat_cla_id
   and obj_id     = oat_obj_id
   and oat_id     = oal_oat_id
   and obj_system = 'N'
   and oat_system = 'N'
   and cla_system = 'N';

CREATE OR REPLACE PUBLIC SYNONYM ctx_object_attribute_lov FOR
  ctxsys.ctx_object_attribute_lov;

GRANT select ON ctx_object_attribute_lov TO public;

REM ===================================================================
REM PREFERENCE TABLES VIEWS
REM ===================================================================

REM -------------------------------------------------------------------
REM  ctx_preference
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_preference

CREATE OR REPLACE VIEW ctx_preferences AS
select  u.name            pre_owner
       ,pre_name          pre_name
       ,cla_name          pre_class
       ,obj_name          pre_object
from   dr$preference
      ,dr$object
      ,dr$class
      ,sys.user$ u
where pre_obj_id = obj_id
  and pre_cla_id = obj_cla_id
  and obj_cla_id = cla_id
  and obj_system = 'N'
  and cla_system = 'N'
  and pre_owner# = u.user#
/

CREATE OR REPLACE PUBLIC SYNONYM ctx_preferences FOR ctxsys.ctx_preferences;
GRANT select ON ctx_preferences TO public;

PROMPT ... creating view ctx_user_preferences

CREATE OR REPLACE VIEW ctx_user_preferences AS
select  pre_name          pre_name
       ,cla_name          pre_class
       ,obj_name          pre_object
from   dr$preference
      ,dr$object
      ,dr$class
where pre_obj_id = obj_id
  and pre_cla_id = obj_cla_id
  and obj_cla_id = cla_id
  and obj_system = 'N'
  and cla_system = 'N'
  and pre_owner# = userenv('SCHEMAID')
/

CREATE OR REPLACE PUBLIC SYNONYM ctx_user_preferences
FOR ctxsys.ctx_user_preferences;
GRANT select ON ctx_user_preferences TO public;

REM -------------------------------------------------------------------
REM  dr$preference_value
REM -------------------------------------------------------------------

PROMPT ... creating view dr$preference_values

CREATE OR REPLACE VIEW ctx_preference_values AS
select /*+ ORDERED INDEX(dr$preference_value) */
  u.name              prv_owner
 ,pre_name            prv_preference
 ,oat_name            prv_attribute
 ,decode(oat_datatype, 'B', decode(prv_value, 1, 'YES', 'NO'),
    nvl(oal_label, prv_value)) prv_value
from
  sys.user$ u
 ,dr$preference
 ,dr$preference_value
 ,dr$object_attribute
 ,dr$object_attribute_lov
where prv_value = nvl(oal_value, prv_value)
  and oat_id = oal_oat_id (+)
  and oat_id = prv_oat_id
  and prv_pre_id = pre_id
  and pre_owner# = u.user#
/

CREATE OR REPLACE PUBLIC SYNONYM ctx_preference_values FOR
  ctxsys.ctx_preference_values;
GRANT select ON ctx_preference_values TO public;

PROMPT ... creating view ctx_user_preference_values

CREATE OR REPLACE VIEW ctx_user_preference_values AS
select /*+ ORDERED INDEX(dr$preference_value) */
  pre_name            prv_preference
 ,oat_name            prv_attribute
 ,decode(oat_datatype, 'B', decode(prv_value, 1, 'YES', 'NO'),
    nvl(oal_label, prv_value)) prv_value
from
  dr$preference
 ,dr$preference_value
 ,dr$object_attribute
 ,dr$object_attribute_lov
where oat_id = oal_oat_id (+)
  and prv_value = nvl(oal_value, prv_value)
  and pre_id = prv_pre_id
  and oat_id = prv_oat_id
  and pre_owner# = userenv('SCHEMAID')
/

CREATE OR REPLACE PUBLIC SYNONYM ctx_user_preference_values FOR
ctxsys.ctx_user_preference_values;
GRANT select ON ctx_user_preference_values TO public;

REM -------------------------------------------------------------------
REM  dr$index
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_indexes

create or replace view ctx_indexes as
select
  idx_id
 ,u.name                 idx_owner
 ,idx_name               idx_name
 ,u2.name                idx_table_owner
 ,o.name                 idx_table
 ,idx_key_name           idx_key_name
 ,idx_text_name          idx_text_name
 ,idx_docid_count        idx_docid_count
 ,idx_status             idx_status
 ,idx_language_column    idx_language_column
 ,idx_format_column      idx_format_column
 ,idx_charset_column     idx_charset_column
 ,decode(idx_type, 0, 'CONTEXT', 1, 'CTXCAT', 2, 'CTXRULE') idx_type
 ,idx_sync_type          idx_sync_type
 ,idx_sync_memory        idx_sync_memory
 ,idx_sync_para_degree   idx_sync_para_degree
 ,idx_sync_interval      idx_sync_interval
 ,idx_sync_jobname       idx_sync_jobname
 from dr$index, sys.user$ u, sys.obj$ o, sys.user$ u2
where idx_owner# = u.user#
  and idx_table_owner# = u2.user#
  and idx_table# = o.obj#
/

PROMPT ... creating view ctx_user_indexes

create or replace view ctx_user_indexes as
select
  idx_id
 ,idx_name               idx_name
 ,u.name                 idx_table_owner
 ,o.name                 idx_table
 ,idx_key_name           idx_key_name
 ,idx_text_name          idx_text_name
 ,idx_docid_count        idx_docid_count
 ,idx_status             idx_status
 ,idx_language_column    idx_language_column
 ,idx_format_column      idx_format_column
 ,idx_charset_column     idx_charset_column
 ,decode(idx_type, 0, 'CONTEXT', 1, 'CTXCAT', 2, 'CTXRULE') idx_type
 ,idx_sync_type          idx_sync_type
 ,idx_sync_memory        idx_sync_memory
 ,idx_sync_para_degree   idx_sync_para_degree
 ,idx_sync_interval      idx_sync_interval
 ,idx_sync_jobname       idx_sync_jobname
 from dr$index, sys.user$ u, sys.obj$ o
where idx_owner# = userenv('SCHEMAID')
  and idx_table_owner# = u.user#
  and idx_table# = o.obj#
/

create or replace public synonym ctx_user_indexes for ctxsys.ctx_user_indexes;
grant select on ctx_user_indexes to public;

REM -------------------------------------------------------------------
REM  dr$index_partition
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_index_partitions

create or replace view ctx_index_partitions as
select
  ixp_id
 ,u1.name                ixp_index_owner
 ,idx_name               ixp_index_name
 ,ixp_name               ixp_index_partition_name
 ,u2.name                ixp_table_owner
 ,o1.name                ixp_table_name
 ,o2.subname             ixp_table_partition_name
 ,ixp_docid_count        ixp_docid_count
 ,ixp_status             ixp_status
 ,ixp_sync_type          ixp_sync_type
 ,ixp_sync_memory        ixp_sync_memory
 ,ixp_sync_para_degree   ixp_sync_para_degree
 ,ixp_sync_interval      ixp_sync_interval
 ,ixp_sync_jobname       ixp_sync_jobname
 from dr$index_partition, dr$index, sys.user$ u1, sys.user$ u2,
      sys.obj$ o1, sys.obj$ o2
where idx_owner# = u1.user#
  and idx_table_owner# = u2.user#
  and ixp_table_partition# = o2.obj#
  and idx_table# = o1.obj#
  and ixp_idx_id = idx_id
/

PROMPT ... creating view ctx_user_index_partitions

create or replace view ctx_user_index_partitions as
select
  ixp_id
 ,idx_name               ixp_index_name
 ,ixp_name               ixp_index_partition_name
 ,u2.name                ixp_table_owner
 ,o1.name                ixp_table_name
 ,o2.subname             ixp_table_partition_name
 ,ixp_docid_count        ixp_docid_count
 ,ixp_status             ixp_status
 ,ixp_sync_type          ixp_sync_type
 ,ixp_sync_memory        ixp_sync_memory
 ,ixp_sync_para_degree   ixp_sync_para_degree
 ,ixp_sync_interval      ixp_sync_interval
 ,ixp_sync_jobname       ixp_sync_jobname
 from dr$index_partition, dr$index, sys.user$ u2,
      sys.obj$ o1, sys.obj$ o2
where idx_owner# = userenv('SCHEMAID')
  and idx_table_owner# = u2.user#
  and ixp_table_partition# = o2.obj#
  and idx_table# = o1.obj#
  and ixp_idx_id = idx_id
/

create or replace public synonym ctx_user_index_partitions
for ctxsys.ctx_user_index_partitions;
grant select on ctx_user_index_partitions to public;

REM -------------------------------------------------------------------
REM  dr$index_value
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_index_values

create or replace view ctx_index_values as
select /*+ ORDERED */
       u.name    ixv_index_owner,
       idx_name  ixv_index_name,
       cla_name  ixv_class,
       obj_name  ixv_object,
       oat_name  ixv_attribute,
       decode(oat_datatype, 'B', decode(ixv_value, 1, 'YES', 'NO'),
         nvl(oal_label, ixv_value)) ixv_value
from dr$index,
     sys.user$ u,
     dr$index_value,
     dr$object_attribute,
     dr$object,
     dr$class,
     dr$object_attribute_lov
where ixv_sub_group = 0
  and ixv_value = nvl(oal_value, ixv_value)
  and oat_id = oal_oat_id (+)
  and oat_system = 'N'
  and oat_cla_id = obj_cla_id
  and oat_obj_id = obj_id
  and cla_system = 'N'
  and oat_cla_id = cla_id
  and ixv_oat_id = oat_id
  and idx_id     = ixv_idx_id
  and idx_owner# = u.user#
/

PROMPT ... creating view ctx_user_index_values

create or replace view ctx_user_index_values as
select /*+ ORDERED */
       idx_name  ixv_index_name,
       cla_name  ixv_class,
       obj_name  ixv_object,
       oat_name  ixv_attribute,
       decode(oat_datatype, 'B', decode(ixv_value, 1, 'YES', 'NO'),
         nvl(oal_label, ixv_value)) ixv_value
from dr$index,
     dr$index_value,
     dr$object_attribute,
     dr$object,
     dr$class,
     dr$object_attribute_lov
where ixv_sub_group = 0
  and ixv_value = nvl(oal_value, ixv_value)
  and oat_id = oal_oat_id (+)
  and oat_system = 'N'
  and oat_cla_id = obj_cla_id
  and oat_obj_id = obj_id
  and cla_system = 'N'
  and oat_cla_id = cla_id
  and ixv_oat_id = oat_id
  and idx_id     = ixv_idx_id
  and idx_owner# = userenv('SCHEMAID')
/

CREATE OR REPLACE PUBLIC SYNONYM ctx_user_index_values FOR
  ctxsys.ctx_user_index_values;
GRANT select ON ctx_user_index_values to public;

REM -------------------------------------------------------------------
REM  index sub lexer views
REM -------------------------------------------------------------------

PROMPT ... creating function dri_sublxv_lang

CREATE or replace function dri_sublxv_lang(value in varchar2)
return varchar2
is
begin
  return null;
end dri_sublxv_lang;
/

PROMPT ... creating view ctx_index_sub_lexers

CREATE or replace view ctx_index_sub_lexers as
select /*+ ORDERED */
       u.name    isl_index_owner,
       idx_name  isl_index_name,
       substr(dri_sublxv_lang(ixv_value),1,30) isl_language,
       substr(substr(ixv_value,instr(ixv_value,':',-1)+1),1,30) isl_alt_value,
       substr(substr(ixv_value,instr(ixv_value,':')+1,
                     instr(ixv_value, ':', -1) - instr(ixv_value,':') - 1),
              1,30) isl_object
from dr$index,
     sys.user$ u,
     dr$index_value
where ixv_oat_id = 60601
  and idx_id     = ixv_idx_id
  and idx_owner# = u.user#
/

PROMPT ... creating view ctx_user_index_sub_lexers

CREATE or replace view ctx_user_index_sub_lexers as
select /*+ ORDERED */
       idx_name  isl_index_name,
       substr(dri_sublxv_lang(ixv_value),1,30) isl_language,
       substr(substr(ixv_value,instr(ixv_value,':',-1)+1),1,30) isl_alt_value,
       substr(substr(ixv_value,instr(ixv_value,':')+1,
                     instr(ixv_value, ':', -1) - instr(ixv_value,':') - 1),
              1,30) isl_object
from dr$index,
     dr$index_value
where ixv_oat_id = 60601
  and idx_id     = ixv_idx_id
  and idx_owner# = userenv('SCHEMAID')
/

CREATE OR REPLACE PUBLIC SYNONYM ctx_user_index_sub_lexers FOR
  ctxsys.ctx_user_index_sub_lexers;

GRANT select ON ctx_user_index_sub_lexers to public;

PROMPT ... creaing view ctx_index_sub_lexer_values

create or replace view ctx_index_sub_lexer_values as
select /*+ ORDERED */
       u.name    isv_index_owner,
       idx_name  isv_index_name,
       substr(dri_sublxv_lang(iv2.ixv_value),1,30) isv_language,
       obj_name  isv_object,
       oat_name  isv_attribute,
       decode(oat_datatype, 'B', decode(iv1.ixv_value, 1, 'YES', 'NO'),
         nvl(oal_label, iv1.ixv_value)) isv_value
from dr$index,
     sys.user$ u,
     dr$index_value iv1,
     dr$index_value iv2,
     dr$object_attribute,
     dr$object,
     dr$object_attribute_lov
where iv1.ixv_value = nvl(oal_value, iv1.ixv_value)
  and oat_id = oal_oat_id (+)
  and oat_system = 'N'
  and oat_cla_id = obj_cla_id
  and oat_obj_id = obj_id
  and iv1.ixv_sub_oat_id = oat_id
  and iv2.ixv_oat_id = 60601
  and iv1.ixv_sub_group = iv2.ixv_sub_group
  and iv1.ixv_idx_id = iv2.ixv_idx_id
  and iv1.ixv_oat_id = 60602
  and idx_id     = iv1.ixv_idx_id
  and idx_owner# = u.user#
/

PROMPT ... creating view ctx_user_index_sub_lexer_vals

create or replace view ctx_user_index_sub_lexer_vals as
select /*+ ORDERED */
       idx_name  isv_index_name,
       substr(dri_sublxv_lang(iv2.ixv_value),1,30) isv_language,
       obj_name  isv_object,
       oat_name  isv_attribute,
       decode(oat_datatype, 'B', decode(iv1.ixv_value, 1, 'YES', 'NO'),
         nvl(oal_label, iv1.ixv_value)) isv_value
from dr$index,
     dr$index_value iv1,
     dr$index_value iv2,
     dr$object_attribute,
     dr$object,
     dr$object_attribute_lov
where iv1.ixv_value = nvl(oal_value, iv1.ixv_value)
  and oat_id = oal_oat_id (+)
  and oat_system = 'N'
  and oat_cla_id = obj_cla_id
  and oat_obj_id = obj_id
  and iv1.ixv_sub_oat_id = oat_id
  and iv2.ixv_oat_id = 60601
  and iv1.ixv_sub_group = iv2.ixv_sub_group
  and iv1.ixv_idx_id = iv2.ixv_idx_id
  and iv1.ixv_oat_id = 60602
  and idx_id     = iv1.ixv_idx_id
  and idx_owner# = userenv('SCHEMAID')
/

CREATE OR REPLACE PUBLIC SYNONYM ctx_user_index_sub_lexer_vals FOR
  ctxsys.ctx_user_index_sub_lexer_vals;

GRANT select ON ctx_user_index_sub_lexer_vals to public;

REM -------------------------------------------------------------------
REM  dr$index_object
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_index_objects

create or replace view ctx_index_objects as
select u.name    ixo_index_owner,
       idx_name  ixo_index_name,
       cla_name  ixo_class,
       obj_name  ixo_object
from dr$index, dr$class, dr$object, dr$index_object, sys.user$ u
where cla_system = 'N'
  and ixo_cla_id = cla_id
  and ixo_cla_id = obj_cla_id
  and ixo_obj_id = obj_id
  and ixo_idx_id = idx_id
  and idx_owner# = u.user#
/

PROMPT ... creating view ctx_user_index_objects

create or replace view ctx_user_index_objects as
select idx_name  ixo_index_name,
       cla_name  ixo_class,
       obj_name  ixo_object
from dr$index, dr$class, dr$object, dr$index_object
where cla_system = 'N'
  and ixo_cla_id = cla_id
  and ixo_cla_id = obj_cla_id
  and ixo_obj_id = obj_id
  and ixo_idx_id = idx_id
  and idx_owner# = userenv('SCHEMAID')
/

CREATE OR REPLACE PUBLIC SYNONYM ctx_user_index_objects FOR
  ctxsys.ctx_user_index_objects;

GRANT select ON ctx_user_index_objects to public;

REM -------------------------------------------------------------------
REM  dr$sqe -
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_sqes

create or replace view ctx_sqes as
select u.name     sqe_owner,
       sqe_name,
       sqe_query
  from dr$sqe sqe, sys.user$ u
 where sqe_owner# = u.user#
/

create or replace public synonym ctx_sqes for ctxsys.ctx_sqes;
grant select on ctx_sqes to public;

PROMPT ... creating view ctx_user_sqes

create or replace view ctx_user_sqes as
select u.name     sqe_owner,
       sqe_name,
       sqe_query
  from dr$sqe sqe, sys.user$ u
 where sqe_owner# = u.user#
   and u.user# = userenv('SCHEMAID')
/

create or replace public synonym ctx_user_sqes for ctxsys.ctx_user_sqes;
grant select on ctx_user_sqes to public;

REM -------------------------------------------------------------------
REM  dr$ths -
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_thesauri

create or replace view ctx_thesauri as
select u.name ths_owner,
       ths_name
  from dr$ths, sys.user$ u
 where ths_owner# = u.user#
/

create or replace public synonym CTX_THESAURI for CTXSYS.CTX_THESAURI;
grant select on CTX_THESAURI to PUBLIC;

PROMPT  ... creating view ctx_user_thesauri

create or replace view ctx_user_thesauri as
select ths_name
  from dr$ths
 where ths_owner# = userenv('SCHEMAID')
/

create or replace public synonym CTX_USER_THESAURI
for CTXSYS.CTX_USER_THESAURI;
grant select on CTX_USER_THESAURI to PUBLIC;

REM -------------------------------------------------------------------
REM  dr$ths_phrase -
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_thes_phrases

create or replace view ctx_thes_phrases as
   select ths_name     thp_thesaurus,
          thp_phrase   thp_phrase,
          thp_qualify  thp_qualifier,
          thp_note     thp_scope_note
     from dr$ths, dr$ths_phrase
    where thp_thsid = ths_id
    order by thp_thsid, thp_phrase, thp_qualify
/

create or replace public synonym CTX_THES_PHRASES for CTXSYS.CTX_THES_PHRASES;
grant select on CTX_THES_PHRASES to PUBLIC;

PROMPT ... creating ctx_user_thes_phrases

create or replace view ctx_user_thes_phrases as
   select ths_name     thp_thesaurus,
          thp_phrase   thp_phrase,
          thp_qualify  thp_qualifier,
          thp_note     thp_scope_note
     from dr$ths_phrase, dr$ths
    where thp_thsid = ths_id
      and ths_owner# = userenv('SCHEMAID')
    order by thp_thsid, thp_phrase, thp_qualify
/

create or replace public synonym CTX_USER_THES_PHRASES
for CTXSYS.CTX_USER_THES_PHRASES;
grant select on CTX_USER_THES_PHRASES to PUBLIC;

REM -------------------------------------------------------------------
REM  dr$section_group -
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_section_groups

create or replace view ctx_section_groups as
select
   u.name      sgp_owner,
   sgp_name,
   obj_name    sgp_type
from dr$section_group, dr$object, dr$class, sys.user$ u
where sgp_obj_id = obj_id
  and obj_system = 'N'
  and obj_cla_id = cla_id
  and cla_name = 'SECTION_GROUP'
  and sgp_owner# = u.user#
/

create or replace public synonym CTX_SECTION_GROUPS
for CTXSYS.CTX_SECTION_GROUPS;
grant select on CTX_SECTION_GROUPS to PUBLIC;

PROMPT ... creating view ctx_user_section_groups

create or replace view ctx_user_section_groups as
select
   sgp_name,
   obj_name    sgp_type
from dr$section_group, dr$object, dr$class
where sgp_obj_id = obj_id
  and obj_system = 'N'
  and obj_cla_id = cla_id
  and cla_name = 'SECTION_GROUP'
  and sgp_owner# = userenv('SCHEMAID')
/

create or replace public synonym CTX_USER_SECTION_GROUPS for
   CTXSYS.CTX_USER_SECTION_GROUPS;

grant select on CTX_USER_SECTION_GROUPS to PUBLIC;

REM -------------------------------------------------------------------
REM  dr$section -
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_sections

create or replace view ctx_sections as
select
   u.name            sec_owner,
   sgp_name          sec_section_group,
   decode(sec_type, 1, 'ZONE', 2, 'FIELD', 3, 'SPECIAL', 4, 'STOP',
                    5, 'ATTR', 7, 'MDATA', null)
                     sec_type,
   sec_id            sec_id,
   decode(sec_type, 4, null, sec_name)
                     sec_name,
   sec_tag           sec_tag,
   sec_visible       sec_visible
from dr$section sec, dr$section_group sgp, sys.user$ u
where sgp.sgp_id = sec.sec_sgp_id
  and sgp_owner# = u.user#
/

create or replace public synonym CTX_SECTIONS for CTXSYS.CTX_SECTIONS;
grant select on CTX_SECTIONS to PUBLIC;

PROMPT ... creating view ctx_user_sections

create or replace view ctx_user_sections as
select
   sgp_name          sec_section_group,
   decode(sec_type, 1, 'ZONE', 2, 'FIELD', 3, 'SPECIAL', 4, 'STOP',
                    5, 'ATTR', 7, 'MDATA', null)
                     sec_type,
   sec_id            sec_id,
   decode(sec_type, 4, null, sec_name)
                     sec_name,
   sec_tag           sec_tag,
   sec_visible       sec_visible
from dr$section sec, dr$section_group sgp
where sgp.sgp_id = sec.sec_sgp_id
  and sgp_owner# = userenv('SCHEMAID')
/

create or replace public synonym CTX_USER_SECTIONS
for CTXSYS.CTX_USER_SECTIONS;
grant select on CTX_USER_SECTIONS to PUBLIC;

REM -------------------------------------------------------------------
REM  dr$stoplist -
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_stoplists

create or replace view ctx_stoplists as
select
  u.name             spl_owner,
  spl_name           spl_name,
  spl_count          spl_count,
  obj_name           spl_type
from dr$stoplist, sys.user$ u, dr$object, dr$class
where spl_owner# = u.user#
  and spl_type = obj_id
  and obj_cla_id = cla_id
  and cla_name = 'STOPLIST'
/

create or replace public synonym CTX_STOPLISTS for CTXSYS.CTX_STOPLISTS;
grant select on CTX_STOPLISTS to PUBLIC;

PROMPT ... creating view ctx_user_stoplists

create or replace view ctx_user_stoplists as
select spl_name           spl_name,
       spl_count          spl_count,
       obj_name           spl_type
from   dr$stoplist, dr$object, dr$class
where  spl_owner# = userenv('SCHEMAID')
  and  spl_type = obj_id
  and  obj_cla_id = cla_id
  and  cla_name = 'STOPLIST'
/

create or replace public synonym CTX_USER_STOPLISTS
for CTXSYS.CTX_USER_STOPLISTS;
grant select on CTX_USER_STOPLISTS to PUBLIC;

REM -------------------------------------------------------------------
REM  dr$stopword -
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_stopwords

create or replace view ctx_stopwords as
select
  u.name         spw_owner,
  spl_name       spw_stoplist,
  decode(spw_type, 1, 'STOP_CLASS', 2, 'STOP_WORD', 3, 'STOP_THEME', null)
                 spw_type,
  spw_word       spw_word,
  decode(spw_language, 'ALL', null, spw_language)   spw_language
from dr$stoplist, dr$stopword, sys.user$ u
where spl_id = spw_spl_id
  and spl_owner# = u.user#
/

create or replace public synonym ctx_stopwords for CTXSYS.CTX_STOPWORDS;
grant select on CTX_STOPWORDS to PUBLIC;

PROMPT ... creating view ctx_user_stopwords

create or replace view CTX_USER_STOPWORDS as
select
  spl_name       spw_stoplist,
  decode(spw_type, 1, 'STOP_CLASS', 2, 'STOP_WORD', 3, 'STOP_THEME', null)
                 spw_type,
  spw_word       spw_word,
  decode(spw_language, 'ALL', null, spw_language)   spw_language
from dr$stoplist, dr$stopword
where spl_id = spw_spl_id
  and spl_owner# = userenv('SCHEMAID')
/

create or replace public synonym CTX_USER_STOPWORDS
for CTXSYS.CTX_USER_STOPWORDS;
grant select on CTX_USER_STOPWORDS to PUBLIC;

REM -------------------------------------------------------------------
REM  dr$sub_lexer -
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_sub_lexers

CREATE OR REPLACE VIEW ctx_sub_lexers AS
select  u1.name           slx_owner
       ,p1.pre_name       slx_name
       ,slx_language      slx_language
       ,slx_alt_value     slx_alt_value
       ,u2.name           slx_sub_owner
       ,p2.pre_name       slx_sub_name
from   dr$sub_lexer
      ,dr$preference p1
      ,dr$preference p2
      ,sys.user$ u1
      ,sys.user$ u2
where p2.pre_owner# = u2.user#
  and p1.pre_owner# = u1.user#
  and slx_sub_pre_id = p2.pre_id
  and slx_pre_id = p1.pre_id
/

create or replace public synonym CTX_SUB_LEXERS for CTXSYS.CTX_SUB_LEXERS;
grant select on CTX_SUB_LEXERS to PUBLIC;

PROMPT ... creating view ctx_user_sub_lexers

CREATE OR REPLACE VIEW ctx_user_sub_lexers AS
select  p1.pre_name       slx_name
       ,slx_language      slx_language
       ,slx_alt_value     slx_alt_value
       ,u2.name           slx_sub_owner
       ,p2.pre_name       slx_sub_name
from   dr$sub_lexer
      ,dr$preference p1
      ,dr$preference p2
      ,sys.user$ u2
where p2.pre_owner# = u2.user#
  and p1.pre_owner# = userenv('SCHEMAID')
  and slx_sub_pre_id = p2.pre_id
  and slx_pre_id = p1.pre_id
/

create or replace public synonym CTX_USER_SUB_LEXERS
for CTXSYS.CTX_USER_SUB_LEXERS;
grant select on CTX_USER_SUB_LEXERS to PUBLIC;

REM -------------------------------------------------------------------
REM  dr$index_set -
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_index_sets

create or replace view ctx_index_sets as
select
   u.name      ixs_owner,
   ixs_name
from dr$index_set, sys.user$ u
where ixs_owner# = u.user#
/

create or replace public synonym CTX_INDEX_SETS for CTXSYS.CTX_INDEX_SETS;
grant select on CTX_INDEX_SETS to PUBLIC;

PROMPT ... creating view ctx_user_index_sets

create or replace view ctx_user_index_sets as
select
   ixs_name
from dr$index_set
where ixs_owner# = userenv('SCHEMAID')
/

create or replace public synonym CTX_USER_INDEX_SETS for
   CTXSYS.CTX_USER_INDEX_SETS;
grant select on CTX_USER_INDEX_SETS to PUBLIC;

REM -------------------------------------------------------------------
REM  dr$index_set_index -
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_index_set_indexes

create or replace view ctx_index_set_indexes as
select
   u.name            ixx_index_set_owner,
   ixs_name          ixx_index_set_name,
   ixx_collist,
   ixx_storage
from dr$index_set_index, dr$index_set, sys.user$ u
where ixs_owner# = u.user#
  and ixx_ixs_id = ixs_id;
/

create or replace public synonym CTX_INDEX_SET_INDEXES
for CTXSYS.CTX_INDEX_SET_INDEXES;
grant select on CTX_INDEX_SET_INDEXES to PUBLIC;

PROMPT ... creating view ctx_user_index_set_indexes

create or replace view ctx_user_index_set_indexes as
select
   ixs_name          ixx_index_set_name,
   ixx_collist,
   ixx_storage
from dr$index_set_index, dr$index_set
where ixx_ixs_id = ixs_id
  and ixs_owner# = userenv('SCHEMAID')
/

create or replace public synonym CTX_USER_INDEX_SET_INDEXES for
   CTXSYS.CTX_USER_INDEX_SET_INDEXES;
grant select on CTX_USER_INDEX_SET_INDEXES to PUBLIC;

REM ===================================================================
REM ADMINISTRATION TABLES
REM ===================================================================

REM -------------------------------------------------------------------
REM  dr$pending
REM -------------------------------------------------------------------

PROMPT ... creating view drv$pending

CREATE OR REPLACE VIEW drv$pending as
select * from dr$pending
 where pnd_cid = SYS_CONTEXT('DR$APPCTX','IDXID')
with check option;

grant select, insert on drv$pending to public;

PROMPT ... creating view ctx_pending

CREATE OR REPLACE VIEW ctx_pending AS
select /*+ ORDERED USE_NL(i p) */
       u.name      pnd_index_owner,
       idx_name    pnd_index_name,
       ixp_name    pnd_partition_name,
       pnd_rowid,
       pnd_timestamp
  from dr$pending, dr$index i, dr$index_partition p, sys.user$ u
 where idx_owner# = u.user#
   and idx_id = ixp_idx_id
   and pnd_pid = ixp_id
   and pnd_pid != 0
   and pnd_cid = idx_id
UNION ALL
select /*+ ORDERED USE_NL(i) */
       u.name      pnd_index_owner,
       idx_name    pnd_index_name,
       null        pnd_partition_name,
       pnd_rowid,
       pnd_timestamp
  from dr$pending, dr$index i, sys.user$ u
 where idx_owner# = u.user#
   and pnd_pid = 0
   and pnd_cid = idx_id
/

PROMPT ... creating view ctx_user_pending

CREATE OR REPLACE VIEW ctx_user_pending AS
select /*+ ORDERED USE_NL(i p)*/
       idx_name  pnd_index_name,
       ixp_name  pnd_partition_name,
       pnd_rowid,
       pnd_timestamp
  from dr$pending, dr$index i, dr$index_partition p
 where idx_id = ixp_idx_id
   and pnd_pid = ixp_id
   and pnd_cid != 0
   and pnd_cid = idx_id
   and idx_owner# = userenv('SCHEMAID')
UNION ALL
select /*+ ORDERED USE_NL(i) */
       idx_name  pnd_index_name,
       null      pnd_partition_name,
       pnd_rowid,
       pnd_timestamp
  from dr$pending, dr$index i
 where pnd_pid = 0
   and pnd_cid = idx_id
   and idx_owner# = userenv('SCHEMAID')
/

create or replace public synonym CTX_USER_PENDING for CTXSYS.CTX_USER_PENDING;
grant select on CTX_USER_PENDING to PUBLIC;

REM -------------------------------------------------------------------
REM  dr$waiting
REM -------------------------------------------------------------------

PROMPT ... creating view drv$waiting

CREATE OR REPLACE VIEW drv$waiting as
select * from dr$waiting
 where wtg_cid = SYS_CONTEXT('DR$APPCTX','IDXID')
with check option;

grant select, insert on drv$waiting to public;

REM -------------------------------------------------------------------
REM  drv$online_pending
REM -------------------------------------------------------------------

PROMPT ... creating view drv$online_pending

CREATE OR REPLACE VIEW drv$online_pending as
select * from dr$online_pending
where onl_cid = SYS_CONTEXT('DR$APPCTX','IDXID')
with check option;

grant select, insert on drv$online_pending to public;

REM -------------------------------------------------------------------
REM  drv$delete
REM -------------------------------------------------------------------

PROMPT ... creating view drv$delete

CREATE OR REPLACE VIEW drv$delete as
select * from dr$delete;

grant select on drv$delete to public;

CREATE OR REPLACE VIEW drv$delete2 as
select * from dr$delete
where del_idx_id = SYS_CONTEXT('DR$APPCTX','IDXID')
with check option;

grant insert on drv$delete2 to public;

REM -------------------------------------------------------------------
REM  drv$unindexed
REM -------------------------------------------------------------------

PROMPT ... creating view drv$unindexed

CREATE OR REPLACE VIEW drv$unindexed as
select * from dr$unindexed;

grant select on drv$unindexed to public;

CREATE OR REPLACE VIEW drv$unindexed2 as
select * from dr$unindexed
where unx_idx_id = SYS_CONTEXT('DR$APPCTX','IDXID')
with check option;

grant insert, delete on drv$unindexed2 to public;

REM -------------------------------------------------------------------
REM  dr$index_error
REM -------------------------------------------------------------------

PROMPT ... creating view ctx_index_errors

CREATE OR REPLACE VIEW ctx_index_errors AS
select u.name         err_index_owner,
       idx_name       err_index_name,
       err_timestamp,
       err_textkey,
       err_text
  from dr$index_error, dr$index, sys.user$ u
 where idx_id = err_idx_id
   and idx_owner# = u.user#
/

PROMPT ... creating view ctx_user_index_errors

CREATE OR REPLACE VIEW ctx_user_index_errors AS
select idx_name       err_index_name,
       err_timestamp,
       err_textkey,
       err_text
  from dr$index_error, dr$index
 where idx_id = err_idx_id
   and idx_owner# = userenv('SCHEMAID')
/

create or replace public synonym ctx_user_index_errors for
ctxsys.ctx_user_index_errors;
grant select, delete on ctx_user_index_errors to public;

REM -------------------------------------------------------------------
REM  ctx_version
REM -------------------------------------------------------------------

create or replace function dri_version return varchar2
is begin return '0.0.0.0.0'; end;
/

PROMPT ... creating view ctx_version

CREATE OR REPLACE VIEW ctx_version AS
select '0.0.0.0.0' ver_dict,
substr(dri_version,1,10) ver_code from dual;


REM -------------------------------------------------------------------
REM  ctx_trace_values
REM -------------------------------------------------------------------
REM this is a dummy until the types and packages we need get created

create or replace view ctx_trace_values as
select 0 trc_id, 0 trc_value from dual where 1 = 2;
create or replace public synonym ctx_trace_values for
ctxsys.ctx_trace_values;
grant select on ctx_trace_values to public;
