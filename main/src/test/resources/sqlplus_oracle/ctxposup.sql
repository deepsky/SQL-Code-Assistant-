Rem
Rem ctxposup.sql
Rem
Rem Copyright (c) 2002, 2004, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxposup.sql - <one-line expansion of the name>
Rem
Rem    DESCRIPTION
Rem      This script contains common post-upgrade steps.  Developers
Rem      should keep this up-to-date so that it is compatible with
Rem      the latest versions of everything.
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    10/07/04 - inst val proc 
Rem    ehuang      12/16/02 - 
Rem    ehuang      08/02/02 - ehuang_component_upgrade_2
Rem    ehuang      07/30/02 - Created
Rem


REM ========================================================================
REM drop dummy operator and index implementation type
REM ========================================================================

drop operator dummyop;
drop package ctx_dummyop;
drop type DummyIndexMethods;

REM ========================================================================
REM drop temporary versions of ctx_ddl routines
REM ========================================================================

drop procedure dr$temp_crepref;
drop procedure dr$temp_cresg;

REM ========================================================================
REM Recompile shared library 
REM ========================================================================

PROMPT STARTING DR0ULIB.SQL
@@dr0ulib.sql

REM ========================================================================
REM Attempt to recompile any invalid views
REM ========================================================================

declare
begin
  for c1 in (select object_name 
             from dba_objects 
             where status = 'INVALID'
               and owner = 'CTXSYS'
               and object_type = 'VIEW')
  loop
    begin
      execute immediate 
        'alter view '||c1.object_name||' compile';
    exception
       when others then null;
    end;
  end loop;
end;
/

REM ========================================================================
REM Recompile Packages
REM ========================================================================

REM Assumption is that the INDEXTYPE HEADERS are VALID at this point

PROMPT STARTING CTXPKH.SQL
@@ctxpkh.sql

PROMPT STARTING CTXPLB.SQL
@@ctxplb.sql

REM ========================================================================
REM Recompile Type Bodies
REM ========================================================================

@@ctxtyb.sql

REM ========================================================================
REM populate database object manifest
REM ========================================================================

@@ctxdbo.sql

REM ========================================================================
REM Attempt to recompile any leftover errant objects
REM ========================================================================

declare
  cbody varchar2(10);
  otype varchar2(30);
begin
  for c1 in (select object_type, object_name 
             from dba_objects 
             where status = 'INVALID'
               and owner = 'CTXSYS'
             order by object_type) 
  loop
    otype := c1.object_type;
    cbody := null;

    if (c1.object_type = 'PACKAGE BODY') then
      otype := 'PACKAGE';
      cbody := 'BODY';
    elsif (c1.object_type = 'TYPE BODY') then
      otype := 'TYPE';
      cbody := 'BODY';
    elsif (c1.object_type = 'VIEW') then
      cbody := 'BODY';
    end if;
    
    begin
      execute immediate 
        'alter '||otype||' '||c1.object_name||' compile '||cbody;
    exception
       when others then null;
    end;
  end loop;
end;
/

REM this is not a mistake -- we want to run this twice due to possible
REM cyclical dependencies

/

REM ========================================================================
REM Install latest version of validation procedure
REM ========================================================================
@@ctxval.sql


REM END OF CTXPOSUP
