Rem
Rem $Header: ctxu901.sql 07-oct-2004.11:40:48 gkaminag Exp $
Rem
Rem ctxu901.sql
Rem
Rem Copyright (c) 2002, 2004, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxu901.sql
Rem
Rem    DESCRIPTION
Rem      component upgrade from 9.0.1 to 9.2.0
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    10/07/04 - val proc to sys 
Rem    gkaminag    03/31/04 - 
Rem    gkaminag    03/18/04 - 
Rem    ehuang      01/21/03 - use default version number
Rem    ehuang      01/24/03 - 
Rem    ehuang      12/12/02 - add parameters
Rem    ehuang      07/29/02 - component upgrade 
Rem    ehuang      07/02/02 - move from s/u0902000.sql
Rem    ehuang      06/17/02 - ehuang_component_upgrade
Rem    ehuang      06/12/02 - Created
Rem

Rem  =======================================================================
Rem  
Rem  ******************** changes to be made by SYS ************************
Rem
Rem  =======================================================================

@@s0900010.sql
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
REM 9.0.1 to 9.2.0
REM ========================================================================

@@u0900010.sql
@@t0900010.sql

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
REM special case; default policy oracontains
REM ========================================================================

PROMPT creating default policy for ora:contains
begin
  CTX_DDL.create_policy('CTXSYS.DEFAULT_POLICY_ORACONTAINS',
    filter        => 'CTXSYS.NULL_FILTER',
    section_group => 'CTXSYS.NULL_SECTION_GROUP',
    lexer         => 'CTXSYS.DEFAULT_LEXER',
    stoplist      => 'CTXSYS.DEFAULT_STOPLIST',
    wordlist      => 'CTXSYS.DEFAULT_WORDLIST'
);
end;
/

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
