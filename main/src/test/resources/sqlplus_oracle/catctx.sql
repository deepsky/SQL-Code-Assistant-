Rem
Rem $Header: catctx.sql 07-oct-2004.15:30:58 gkaminag Exp $
Rem
Rem catctx.sql
Rem
Rem Copyright (c) 2002, 2004, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      catctx.sql
Rem
Rem    DESCRIPTION
Rem      runs as SYS
Rem  
Rem      performs an initial load of the complete component for the
Rem      current release.  It calls all of the other scripts that 
Rem      create database objects.
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    10/07/04 - val proc to sys
Rem    gkaminag    02/04/03 - ctxx location no longer needed
Rem    ehuang      01/21/03 - use default version number
Rem    ehuang      12/12/02 - add parameters
Rem    ehuang      09/27/02 - remove set statements
Rem    ehuang      07/02/02 - add ctxdef, ctxobj
Rem    ehuang      06/17/02 - ehuang_component_upgrade
Rem    ehuang      06/11/02 - Created
Rem

define pass          = "&1"
define tbs           = "&2"
define ttbs          = "&3"
define dolock        = "&4"

Rem =======================================================================
Rem CTXSYS.sql - schema creation amd granting privileges
Rem =======================================================================
@@ctxsys.sql &pass &tbs &ttbs &dolock

Rem =======================================================================
Rem script is ALWAYS run as SYS,  must set current_schema to CTXSYS before
Rem loading context
Rem =======================================================================
ALTER SESSION SET CURRENT_SCHEMA = CTXSYS;

Rem =======================================================================
Rem signal beginning of loading
Rem =======================================================================
begin
dbms_registry.loading('CONTEXT','Oracle Text', 'validate_context', 'CTXSYS');
end;
/

REM ========================================================================
REM CTX does not currently support char semantics, so this forces
REM creation to use byte semantics
REM ========================================================================

alter session set nls_length_semantics=byte;

Rem =======================================================================
Rem CTXTAB.sql - create tables, populates static tables
Rem =======================================================================
@@ctxtab.sql

Rem =======================================================================
Rem CTXVIEW.sql - create or replace public views, with grants and public 
Rem synonyms; include any fixed views
Rem =======================================================================
@@ctxview.sql

Rem =======================================================================
Rem create safe callout library
Rem =======================================================================
@@dr0lib.sql

Rem =======================================================================
Rem CTXTYP.sql - creates types specifications
Rem =======================================================================
@@ctxtyp.sql

Rem =======================================================================
Rem CTXPKH.sql - create or replace public pl/sql package specifications, 
Rem              functions, and procedures, with grants and synonyms
Rem =======================================================================
@@ctxpkh.sql

Rem =======================================================================
Rem CTXPLB.sql - create or replace public and private PL/SQL package bodies
Rem =======================================================================
@@ctxplb.sql

Rem =======================================================================
Rem CTXTYB.sql - create or replace public and private type bodies
Rem =======================================================================
@@ctxtyb.sql

Rem =======================================================================
Rem CTXITYP.sql - create or replace index type
Rem =======================================================================
@@ctxityp.sql

Rem =======================================================================
Rem CTXOBJ.sql - ctx object creation
Rem =======================================================================
@@ctxobj.sql

Rem =======================================================================
Rem CTXDEF.sql - ctx default object creation
Rem =======================================================================
@@ctxdef.sql

Rem =======================================================================
Rem CTXDBO.sql - ctx database object manifest
Rem =======================================================================
@@ctxdbo.sql

Rem =======================================================================
Rem CTXVAL.sql - install validation procedure
Rem =======================================================================
@@ctxval.sql

Rem =======================================================================
Rem signal end of loading
Rem =======================================================================
execute dbms_registry.loaded('CONTEXT');
execute sys.validate_context;

REM =======================================================================
REM must reset current_schema to SYS
REM =======================================================================
ALTER SESSION SET CURRENT_SCHEMA = SYS;

