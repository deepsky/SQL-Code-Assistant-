Rem
Rem $Header: ctxpatch.sql 28-feb-2005.15:18:17 gkaminag Exp $
Rem
Rem ctxpatch.sql
Rem
Rem Copyright (c) 2002, 2005, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxpatch.sql 
Rem
Rem    DESCRIPTION
Rem      this patch script is used to apply bug fixes. It is run in
Rem      the context of catpatch.sql, after the RDBMS catalog.sql
Rem      and catproc.sql is run with a special EVEN set which causes
Rem      CREATE OR REPLACE statements to only recompile objects if
Rem      the new source is different from the source stored in the 
Rem      database.  Tables, types and public interfaces should not
Rem      be changed by the patch scripts.
Rem 
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    02/28/05 - typo
Rem    gkaminag    02/17/05 - 
Rem    gkaminag    10/07/04 - val proc to sys 
Rem    ehuang      12/16/02 - 
Rem    gkaminag    11/26/02 - add call to check_server_instance
Rem    ehuang      07/09/02 - 
Rem    ehuang      06/17/02 - ehuang_component_upgrade
Rem    ehuang      06/11/02 - Created
Rem

Rem ensure that we are in an expected state

WHENEVER SQLERROR EXIT;
EXECUTE dbms_registry.check_server_instance;
WHENEVER SQLERROR CONTINUE;

ALTER SESSION SET CURRENT_SCHEMA = CTXSYS;

begin
dbms_registry.loading('CONTEXT','Oracle Text', 'validate_context', 'CTXSYS');
end;
/

Rem do any needed upgrades, then recompile packages, etc.
@@ctxu1020.sql

EXECUTE dbms_registry.loaded('CONTEXT');

EXECUTE sys.validate_context;

ALTER SESSION SET CURRENT_SCHEMA = SYS;

