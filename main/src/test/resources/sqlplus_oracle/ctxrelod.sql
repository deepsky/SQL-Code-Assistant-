Rem
Rem $Header: ctxrelod.sql 07-oct-2004.11:40:47 gkaminag Exp $
Rem
Rem ctxrelod.sql
Rem
Rem Copyright (c) 2002, 2004, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxrelod.sql
Rem
Rem    DESCRIPTION
Rem      reload views, packages and JAVA classes after a downgrade.
Rem      The dictionary objects are reset to the old release by the
Rem      ctxe***.sql script, this reload script processes the "old"
Rem      scripts to reload the "old" version using the "old" server.
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    10/07/04 - val proc to sys 
Rem    gkaminag    01/07/03 - recompile views
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
dbms_registry.loading('CONTEXT','Oracle Text', 'validate_context','CTXSYS');
end;
/

@@ctxpkh.sql
@@ctxplb.sql
@@ctxtyb.sql
@@ctxview.sql
@@ctxdbo.sql
@@ctxval.sql

EXECUTE dbms_registry.loaded('CONTEXT');

EXECUTE sys.validate_context;

ALTER SESSION SET CURRENT_SCHEMA = SYS;

