Rem
Rem $Header: ctxu1020.sql 21-feb-2005.11:45:15 gkaminag Exp $
Rem
Rem ctxu1020.sql
Rem
Rem Copyright (c) 2005, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxu1020.sql - <one-line expansion of the name>
Rem
Rem    DESCRIPTION
Rem      upgrade from 10.2.0.0 to latest version
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    02/21/05 - gkaminag_test_050217
Rem    gkaminag    02/17/05 - Created
Rem

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
REM ******************* Begin SYS changes **********************************
REM
REM ========================================================================

ALTER SESSION SET CURRENT_SCHEMA = SYS;
@@t1002000.sql
ALTER SESSION SET CURRENT_SCHEMA = CTXSYS;

REM ========================================================================
REM 
REM ******************* End SYS changes ************************************
REM
REM ========================================================================

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
REM 10.2 to next version
REM ========================================================================

@@u1002000.sql
@@t1002000.sql

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

