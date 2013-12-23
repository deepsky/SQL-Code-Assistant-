Rem
Rem $Header: ctxu920.sql 07-oct-2004.11:40:48 gkaminag Exp $
Rem
Rem ctxu920.sql
Rem
Rem Copyright (c) 2002, 2004, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxu920.sql 
Rem
Rem    DESCRIPTION
Rem      component upgrade from 9.2.0 to 10i
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    10/07/04 - val proc to sys 
Rem    gkaminag    03/18/04 - version 
Rem    gkaminag    08/22/03 - drop unneeded ctxcat update procedures 
Rem    ehuang      01/21/03 - use default version number
Rem    ehuang      12/12/02 - add parameters
Rem    gkaminag    10/29/02 - security upgrade
Rem    ehuang      07/29/02 - component upgrade 
Rem    ehuang      07/02/02 - move from u1000000.sql
Rem    ehuang      06/17/02 - ehuang_component_upgrade
Rem    ehuang      06/11/02 - Created - component upgrade
Rem    ehuang      05/21/02 - add mail_filter_config_file parametr.
Rem    ehuang      04/27/02 - ehuang_mail_filter_020420
Rem    ehuang      04/20/02 - Created
Rem

Rem  =======================================================================
Rem  
Rem  ******************** changes to be made by SYS ************************
Rem
Rem  =======================================================================

@@s0902000.sql
@@s1001002.sql

Rem  =======================================================================
Rem  
Rem  ***********************   end of SYS changes  *************************
Rem
Rem  =======================================================================

REM ========================================================================
REM set schema, Registry to upgrading state
REM ========================================================================

ALTER SESSION SET CURRENT_SCHEMA = CTXSYS;

begin
dbms_registry.upgrading('CONTEXT','Oracle Text','validate_context','CTXSYS');
end;
/

REM ========================================================================
REM 
REM ******************* Begin CTXSYS schema changes ************************
REM
REM ========================================================================

REM ========================================================================
REM Pre-upgrade steps
REM ========================================================================

@@ctxpreup.sql

REM ========================================================================
REM 9.2.0 to 10.1.0
REM ========================================================================

@@u0902000.sql
@@t0902000.sql

REM ========================================================================
REM 10.1 to 10.2
REM ========================================================================

@@u1001002.sql
@@t1001002.sql

REM ========================================================================
REM Post-upgrade steps
REM ========================================================================

@@ctxposup.sql

REM ========================================================================
REM reconstitute ctxcat triggers (packages should be valid)
REM ========================================================================

ALTER SESSION SET CURRENT_SCHEMA = SYS;

begin
  for c1 in (select u.name idx_owner, idx_name, idx_id
               from ctxsys.dr$index, sys.user$ u
              where idx_owner# = u.user#
                and idx_type = 1)
  loop
    begin
    execute immediate 'drop procedure '||
       'ctxsys.dr$'||ltrim(to_char(c1.idx_id))||'$u';
    exception when others then null; end;
    ctxsys.drvxtabc.recreate_trigger(c1.idx_owner, c1.idx_name, c1.idx_id);
  end loop;
end;
/

ALTER SESSION SET CURRENT_SCHEMA = CTXSYS;

REM ========================================================================
REM
REM ****************  End CTXSYS schema change *****************************
REM
REM ========================================================================

REM ========================================================================
REM Registry to upgraded state, reset schema
REM ========================================================================

begin
  dbms_registry.loaded('CONTEXT');
  dbms_registry.valid('CONTEXT');
end;
/

ALTER SESSION SET CURRENT_SCHEMA = SYS;
