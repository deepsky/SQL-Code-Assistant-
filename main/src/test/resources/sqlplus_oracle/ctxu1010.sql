Rem
Rem $Header: ctxu1010.sql 07-oct-2004.11:40:47 gkaminag Exp $
Rem
Rem ctxu1010.sql
Rem
Rem Copyright (c) 2004, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxu1010.sql - <one-line expansion of the name>
Rem
Rem    DESCRIPTION
Rem      upgrade from 10.1 to 10.2
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    10/07/04 - val proc to sys 
Rem    gkaminag    03/22/04 - gkaminag_misc_040318 
Rem    gkaminag    03/18/04 - Created
Rem

Rem  =======================================================================
Rem  
Rem  ******************** changes to be made by SYS ************************
Rem
Rem  =======================================================================

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
REM 10.1 to 10.2
REM ========================================================================

@@u1001002.sql
@@t1001002.sql

REM ========================================================================
REM Post-upgrade steps
REM ========================================================================

@@ctxposup.sql

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
