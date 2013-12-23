Rem
Rem $Header: ctxsys.sql 02-aug-2004.11:10:36 gkaminag Exp $
Rem
Rem ctxsys.sql
Rem
Rem Copyright (c) 2002, 2004, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxsys.sql <password> <tablespc> <temptblspc> <LOCK|NOLOCK>
Rem
Rem    DESCRIPTION
Rem      schema creation and granting privileges (schema name CTXSYS,
Rem      grant EXECUTE on dbms_registry, alter user lock account
Rem      expire password
Rem
Rem    NOTES
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    08/02/04 - deprecation of connect 
Rem    ekwan       10/03/03 - Bug 3161706: cannot get column type for nested 
Rem    surman      09/04/03 - 3101316: Update duc$ for drop user cascade 
Rem    gkaminag    08/19/03 - grant ctxsys permissions on ctxapp role 
Rem    ekwan       07/01/03 - Bug 2999760: grant dba_tab_cols
Rem    gkaminag    01/07/03 - dba_indextypes
Rem    gkaminag    12/03/02 - make ctxsys a normal user
Rem    ehuang      09/09/02 - grant more sys tables
Rem    gkaminag    08/22/02 - 
Rem    ehuang      07/08/02 - migrate from dr* scripts
Rem    ehuang      06/17/02 - ehuang_component_upgrade
Rem    ehuang      06/11/02 - Created
Rem

define pass   = &1
define tbs    = &2
define ttbs   = &3
define dolock = &4

prompt ...creating user CTXSYS
create user ctxsys
identified by &pass default tablespace &tbs temporary tablespace &ttbs;

grant create session, alter session, create view, create synonym, resource 
to CTXSYS;

REM
REM Needed for ctx_servers
REM

grant select on sys.v_$session to ctxsys with grant option;

grant create public synonym to ctxsys;
grant drop public synonym to ctxsys;

grant select on SYS.ARGUMENT$ to ctxsys with grant option;
grant select on SYS.DBA_COLL_TYPES to ctxsys;
grant select on SYS.DBA_CONSTRAINTS to ctxsys;
grant select on SYS.DBA_CONS_COLUMNS to ctxsys with grant option;
grant select on SYS.DBA_DB_LINKS to ctxsys with grant option;
grant select on SYS.DBA_INDEXTYPES to ctxsys with grant option;
grant select on SYS.DBA_JOBS to ctxsys;
grant select on SYS.DBA_JOBS_RUNNING to ctxsys;
grant select on SYS.DBA_OBJECTS to ctxsys with grant option;
grant select on SYS.DBA_OBJECTS to ctxsys;
grant select on SYS.DBA_ROLES to ctxsys with grant option;
grant select on SYS.DBA_ROLE_PRIVS to ctxsys with grant option;
grant select on SYS.DBA_SYNONYMS to ctxsys with grant option;
grant select on SYS.DBA_SYS_PRIVS to ctxsys;
grant select on SYS.DBA_TABLES to ctxsys;
grant select on SYS.DBA_TAB_COLUMNS to ctxsys;
grant select on SYS.DBA_TAB_COLS to ctxsys;
grant select on SYS.DBA_TAB_PARTITIONS to ctxsys;
grant select on SYS.DBA_TAB_PRIVS to ctxsys with grant option;
grant select on SYS.DBA_TYPE_ATTRS to ctxsys with grant option;
grant select on SYS.DBA_USERS to ctxsys;
grant select on SYS.GV_$PARAMETER to ctxsys;
grant select on SYS.HIST_HEAD$ to ctxsys;
grant select on SYS.COL$ to ctxsys with grant option;
grant select on SYS.COLTYPE$ to ctxsys;
grant select on SYS.IND$ to ctxsys with grant option;
grant select on SYS.INDPART$ to ctxsys with grant option;
grant select on SYS.LOB$ to ctxsys with grant option;
grant select on SYS.LOBFRAG$ to ctxsys with grant option;
grant select on SYS.OBJ$ to ctxsys with grant option;
grant select on SYS.PARTOBJ$ to ctxsys with grant option;
grant select on SYS.SYN$ to ctxsys with grant option;
grant select on SYS.SYSAUTH$ to ctxsys with grant option;
grant select on SYS.TAB$ to ctxsys with grant option;
grant select on SYS.TABPART$ to ctxsys with grant option;
grant select on SYS.TS$ to ctxsys with grant option;
grant select on SYS.USER$ to ctxsys with grant option;
grant select on SYS.USER$ to ctxsys with grant option;
grant select on SYS.VIEW$ to ctxsys with grant option;
grant select on SYS.V_$PARAMETER to ctxsys with grant option;
grant select on SYS.V_$RESOURCE to ctxsys with grant option;
grant select on dba_types to ctxsys with grant option;
grant select on SYS.V_$THREAD to ctxsys with grant option;
grant select on SYS.CDEF$ to ctxsys with grant option;
grant select on SYS.CON$ to ctxsys with grant option;
grant select on SYS.CCOL$ to ctxsys with grant option;
grant select on SYS.ICOL$ to ctxsys with grant option;

grant execute on dbms_pipe to ctxsys;
grant execute on dbms_lock to ctxsys;
grant execute on dbms_registry to ctxsys;

promp ...creating role CTXAPP
create role CTXAPP;
grant ctxapp to ctxsys with admin option;

begin
  if ('&dolock' = 'LOCK') then
    execute immediate
      'alter user ctxsys password expire account lock';
  end if;
end;
/

REM Support DROP USER CASCADE
DELETE FROM sys.duc$
  WHERE owner = 'CTXSYS'
    AND pack = 'CTX_ADM'
    AND proc = 'DROP_USER_OBJECTS'
    AND operation# = 1;

INSERT INTO sys.duc$ (owner, pack, proc, operation#, seq, com)
  VALUES ('CTXSYS', 'CTX_ADM', 'DROP_USER_OBJECTS', 1, 1,
          'Drops any Text objects for this user');

COMMIT;
