Rem
Rem ctxe101.sql
Rem
Rem Copyright (c) 2004, 2005, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxe101.sql - <one-line expansion of the name>
Rem
Rem    DESCRIPTION
Rem      downgrade from 10.2 to 10.1
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    02/18/05 - backout any patchset changes
Rem    gkaminag    08/03/04 - deprecate connect
Rem    gkaminag    05/12/04 - gkaminag_upgrade_040512
Rem    gkaminag    05/12/04 - gkaminag_upgrade_040512
Rem    gkaminag    03/22/04 - gkaminag_misc_040318 
Rem    gkaminag    03/18/04 - Created
Rem


REM ===========================================================
REM regrant connect role
REM ===========================================================

revoke create session, alter session, create view, create synonym from CTXSYS;
grant CONNECT to CTXSYS;

REM ===========================================================
REM set schema, registry
REM ===========================================================

ALTER SESSION SET CURRENT_SCHEMA = CTXSYS;

EXECUTE dbms_registry.downgrading('CONTEXT');

REM run downgrade scripts
@@d1002000.sql
@@d1001002.sql

REM ========================================================================
REM Registry to downgraded state
REM ========================================================================

EXECUTE dbms_registry.downgraded('CONTEXT','10.1.0');

REM ========================================================================
REM reset schema to SYS
REM ========================================================================     
ALTER SESSION SET CURRENT_SCHEMA = SYS;

