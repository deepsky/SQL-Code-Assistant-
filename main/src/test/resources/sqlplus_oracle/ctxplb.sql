Rem
Rem $Header: ctxplb.sql 05-may-2005.10:46:17 gkaminag Exp $
Rem
Rem ctxplb.sql
Rem
Rem Copyright (c) 2002, 2005, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxplb.sql
Rem
Rem    DESCRIPTION
Rem      create or replace public and private PL/SQL package Bodies
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    05/05/05 - version to 10.2.0.1.0 
Rem    gkaminag    03/18/04 - version 
Rem    ehuang      10/24/03 - tracing 
Rem    gkaminag    08/18/03 - version change 
Rem    gkaminag    06/05/03 - version to 10.1.0.1.0
Rem    smuralid    06/05/03 - add drv0ddl.pkb
Rem    daliao      02/10/03 - grant ctx_cls to public
Rem    ehuang      01/21/03 - version to 10.1
Rem    smuralid    01/06/03 - 
Rem    smuralid    12/23/02 - grants on state types
Rem    daliao      12/04/02 - change driodm to drvodm
Rem    gkaminag    11/26/02 - version to 10
Rem    smuralid    12/03/02 - grant privs on drvparx
Rem    gkaminag    12/04/02 - drvutl
Rem    daliao      10/29/02 - rename drisvm.plb to driodm.plb, add drvtmt.plb
Rem    daliao      10/16/02 - add drisvm.plb
Rem    gkaminag    09/27/02 - security phase 3
Rem    gkaminag    07/19/02 - new packages
Rem    gkaminag    07/17/02 - security phase 2
Rem    ehuang      07/02/02 - move from dr0plb.sql
Rem    ehuang      06/17/02 - ehuang_component_upgrade
Rem    ehuang      06/11/02 - Created - component upgrade
Rem     gkaminag   06/03/02 - new packages for security .
Rem     gkaminag   04/09/02 - removing ctxsrv.
Rem     daliao     01/29/02 - add classification
Rem     gkaminag   01/22/02 - add ctx_version view recompile.
Rem     gkaminag   12/18/01 - dr0repor split.
Rem     gkaminag   10/02/01 - add ctx_report.
Rem     wclin      09/25/01 - handle new xpath indextype
Rem     yucheng    09/11/01 - add driddlp package
Rem     mfaisal    12/21/00 - user-defined lexer
Rem     gkaminag   01/09/01 - remove sql*plus settings
Rem     gkaminag   08/28/00 - add dr0proc.plb
Rem     ehuang     08/08/00 - split drixtab into drixtabc, drixtabr
Rem     salpha     06/26/00 - ctxrule implementation
Rem     gkaminag   02/22/00 - ctxcat implementation
Rem     gkaminag   04/09/99 -
Rem     gkaminag   04/05/99 - suspending longop project
Rem     adusange   04/04/99 - Fix integration problem 040299
Rem     gkaminag   03/09/99 - driths split
Rem     gkaminag   02/15/99 - driddl split up
Rem     cbhavsar   09/03/98 - CDM2 merge
Rem     gkaminag   08/11/98 - add output package
Rem     ehuang     06/02/98 - grant driimp to public
Rem     gkaminag   05/22/98 - gc optimization
Rem     ehuang     05/15/98 - add driimp.plb
Rem     ehuang     05/13/98 - rename
Rem     ehuang     05/11/98 - creation

REM ===================================================================
REM When adding new packages, remember to add lines to header section,
REM body section, and synonym/grant section.  
REM ===================================================================

PROMPT ================== Package Installation ==========================

REM =====================================================================
REM Package bodies
REM =====================================================================

PROMPT
PROMPT Install DR Internal package bodies
PROMPT

PROMPT ... loading driacc.plb 
@@driacc.plb
show errors
PROMPT ... loading dricon.plb
@@dricon.plb
show errors
PROMPT ... loading dridisp.plb
@@dridisp.plb
show errors
PROMPT ... loading dridml.plb
@@dridml.plb
show errors
PROMPT ... loading dridoc.plb
@@dridoc.plb
show errors
PROMPT ... loading drierr.plb
@@drierr.plb
show errors
PROMPT ... loading driexp.plb
@@driexp.plb
show errors
PROMPT ... loading driimp.plb
@@driimp.plb
show errors
PROMPT ... loading driixs.plb
@@driixs.plb
show errors
PROMPT ... loading driload.plb
@@driload.plb
show errors
PROMPT ... loading drimlx.plb
@@drimlx.plb
show errors
PROMPT ... loading driopt.plb
@@driopt.plb
show errors
PROMPT ... loading driparse.plb
@@driparse.plb
show errors
PROMPT ... loading dripref.plb
@@dripref.plb
show errors
PROMPT ... loading drirec.plb
@@drirec.plb
show errors
PROMPT ... loading drirep.plb
@@drirep.plb
show errors
PROMPT ... loading drirepm.plb
@@drirepm.plb
show errors
PROMPT ... loading drirepz.plb
@@drirepz.plb
show errors
PROMPT ... loading drisgp.plb
@@drisgp.plb
show errors
PROMPT ... loading drispl.plb
@@drispl.plb
show errors
PROMPT ... loading driths.plb
@@driths.plb
show errors
PROMPT ... loading drithsc.plb
@@drithsc.plb
show errors
PROMPT ... loading drithsd.plb
@@drithsd.plb
show errors
PROMPT ... loading drithsl.plb
@@drithsl.plb
show errors
PROMPT ... loading drithsx.plb
@@drithsx.plb
show errors
PROMPT ... loading driutl.plb 
@@driutl.plb
show errors
PROMPT ... loading drival.plb
@@drival.plb
show errors
PROMPT ... loading drixmd.plb
@@drixmd.plb
show errors

PROMPT
PROMPT Install DR internal invoker's rights bodies
PROMPT

PROMPT ... loading drvdisp.plb
@@drvdisp.plb
show errors
PROMPT ... loading drvddl.plb 
@@drvddl.plb
show errors
PROMPT ... loading drvddlc.plb 
@@drvddlc.plb
show errors
PROMPT ... loading drvddlr.plb 
@@drvddlr.plb
show errors
PROMPT ... loading drvddlx.plb 
@@drvddlx.plb
show errors
PROMPT ... loading drvdml.plb 
@@drvdml.plb
show errors
PROMPT ... loading drvdoc.plb 
@@drvdoc.plb
show errors
PROMPT ... loading drverr.plb
@@drverr.plb
show errors
PROMPT ... loading drvimr.plb
@@drvimr.plb
show errors
PROMPT ... loading driparx.plb
@@driparx.plb
show errors
PROMPT ... loading drvodm.plb
@@drvodm.plb
show errors
PROMPT ... loading drvparx.plb
@@drvparx.plb
show errors
PROMPT ... loading drvtmt.plb
@@drvtmt.plb
show errors
PROMPT ... loading drvutl.plb
@@drvutl.plb
show errors
PROMPT ... loading drvxmd.plb
@@drvxmd.plb
show errors
PROMPT ... loading drvxtab.plb
@@drvxtab.plb
show errors
PROMPT ... loading drvxtabc.plb
@@drvxtabc.plb
show errors
PROMPT ... loading drvxtabr.plb
@@drvxtabr.plb
show errors
PROMPT ... loading drvxtabx.plb
@@drvxtabx.plb
show errors
PROMPT ... loading drv0ddl.plb
@@drv0ddl.plb
show errors


REM DRIPROC SHOULD GO AFTER ALL OTHER DRI* 

PROMPT ... loading driproc.plb
@@driproc.plb
show errors

PROMPT
PROMPT Install ConText public API bodies
PROMPT

PROMPT ... loading dr0adm.plb
@@dr0adm.plb
show errors
PROMPT ... loading dr0ddl.plb
@@dr0ddl.plb
show errors
PROMPT ... loading dr0doc.plb
@@dr0doc.plb
show errors
PROMPT ... loading dr0out.plb
@@dr0out.plb
show errors
PROMPT ... loading dr0query.plb
@@dr0query.plb
show errors
PROMPT ... loading dr0thes.plb
@@dr0thes.plb
show errors
PROMPT ... loading dr0repor.plb
@@dr0repor.plb
show errors
PROMPT ... loading dr0cls.plb
@@dr0cls.plb
show errors


REM =====================================================================
REM execute grants on public interface
REM =====================================================================

create or replace public synonym ctx_doc for ctxsys.ctx_doc;
grant execute on ctx_doc to public;

create or replace public synonym ctx_ddl for ctxsys.ctx_ddl;
grant execute on ctx_ddl to ctxapp;

create or replace public synonym ctx_output for ctxsys.ctx_output;
grant execute on ctx_output to ctxapp;

create or replace public synonym ctx_query for ctxsys.ctx_query;
grant execute on ctx_query to public;

create or replace public synonym ctx_thes for ctxsys.ctx_thes;
grant execute on ctx_thes to ctxapp;

create or replace public synonym ctx_report for ctxsys.ctx_report;
grant execute on ctx_report to public;

create or replace public synonym ctx_ulexer for ctxsys.ctx_ulexer;
grant execute on ctx_ulexer to ctxapp;

create or replace public synonym ctx_cls for ctxsys.ctx_cls;
grant execute on ctx_cls to public;

REM =====================================================================
REM execute grants on private interface
REM =====================================================================

grant execute on driload to public;
grant execute on drithsx to public;
grant execute on drithsl to ctxapp;
grant execute on driimp to public;

grant execute on drvdml to public;
grant execute on drvimr to public;
grant execute on drvxmd to public;
grant execute on drvparx to public;
grant execute on dr$session_state_t to public;
grant execute on dr$optim_state_t to public;
grant execute on dr$popindex_state_t to public;
grant execute on dr$createindex_state_t to public;

grant execute on dr$opttf_impl_t to public;
grant execute on dr$itab_t to public;
grant execute on dr$itab_set_t to public;
grant execute on dr$itab0_t to public;
grant execute on dr$itab0_set_t to public;
grant execute on drv0ddl to public;

create or replace public synonym drvodm for ctxsys.drvodm;
grant execute on drvodm to public;

PROMPT ========================================================

rem ctx_trace_values
rem this view depends on drvparx, so we have to create it here

CREATE OR REPLACE VIEW ctx_trace_values AS
select trc.trc_id, trc.trc_value
  from TABLE(ctxsys.drvparx.TraceGetTrace) trc;
grant select on ctx_trace_values to public;

rem ctx_version
rem the reason ctx_version recompile is here is because new install,
rem major version upgrade, patchset upgrade, and downgrade reload all run 
rem ctxplb.sql.

CREATE OR REPLACE VIEW ctx_version AS
select '10.2.0.1.0' ver_dict, 
substr(dri_version,1,10) ver_code from dual;

