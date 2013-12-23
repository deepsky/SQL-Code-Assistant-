Rem
Rem $Header: ctxe920.sql 18-feb-2005.15:01:38 gkaminag Exp $
Rem
Rem ctxe920.sql
Rem
Rem Copyright (c) 2002, 2005, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxe920.sql
Rem
Rem    DESCRIPTION
Rem      downgrade from 10i to 9.2.0
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    02/18/05 - downgrade
Rem    gkaminag    11/03/04 - flip order of downgrade scripts
Rem    gkaminag    03/18/04 - version 
Rem    surman      09/04/03 - 3101316: Update duc$ for drop user cascade 
Rem    gkaminag    02/06/03 - fix errors
Rem    gkaminag    01/07/03 - security privs
Rem    ehuang      09/23/02 - fix quote
Rem    ehuang      08/01/02 - 
Rem    ehuang      06/17/02 - ehuang_component_upgrade
Rem    ehuang      06/11/02 - Created
Rem


REM ===========================================================
REM regrant revoked privileges
REM ===========================================================

grant DBA, ALL PRIVILEGES to CTXSYS;

REM ===========================================================
REM Delete from duc$ (support for DROP USER CASCADE)
REM ===========================================================

DELETE FROM sys.duc$
  WHERE owner = 'CTXSYS'
    AND pack = 'CTX_ADM'
    AND proc = 'DROP_USER_OBJECTS'
    AND operation# = 1;

COMMIT;

REM ===========================================================
REM set schema, registry
REM ===========================================================

ALTER SESSION SET CURRENT_SCHEMA = CTXSYS;

EXECUTE dbms_registry.downgrading('CONTEXT');

REM run downgrade scripts

@@d1002000.sql
@@d1001002.sql
@@d0902000.sql

REM ========================================================================
REM Registry to downgraded state
REM ========================================================================

EXECUTE dbms_registry.downgraded('CONTEXT','9.2.0.1.0');

REM ========================================================================
REM reset schema to SYS
REM ========================================================================     
ALTER SESSION SET CURRENT_SCHEMA = SYS;

