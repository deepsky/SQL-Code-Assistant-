Rem
Rem $Header: ctxdbmig.sql 07-oct-2004.11:40:45 gkaminag Exp $
Rem
Rem ctxdbmig.sql
Rem
Rem Copyright (c) 2002, 2004, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxdbmig.sql
Rem
Rem    DESCRIPTION
Rem      runs as SYS
Rem
Rem      performs the upgrade of context from all prior releases
Rem      supported for upgrade (806, 817, 901 and 920 for 10i).
Rem      It first runs a 'u' script to upgrade the tables and types
Rem      and then load in the new package specifications, views and 
Rem      package and type bodies..etc.
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    10/07/04 - val proc to sys 
Rem    gkaminag    03/18/04 - version 
Rem    gkaminag    02/13/03 - avoid calling upgraded if nothing has been done
Rem    mfaisal     01/15/03 - restore schema
Rem    ehuang      12/12/02 - add parameters
Rem    gkaminag    11/26/02 - add call to check_server_instance
Rem    ehuang      11/11/02 - 
Rem    ehuang      09/23/02 - add missing quote
Rem    ehuang      07/09/02 - 
Rem    ehuang      06/17/02 - ehuang_component_upgrade
Rem    ehuang      06/11/02 - Created
Rem

Rem ensure that we are in an expected state

WHENEVER SQLERROR EXIT;
EXECUTE dbms_registry.check_server_instance;
WHENEVER SQLERROR CONTINUE;

Rem ===========================================
Rem set current schema
Rem ===========================================
ALTER SESSION SET CURRENT_SCHEMA = CTXSYS;

Rem ===========================================
Rem setup component script filname variable
Rem ===========================================
COLUMN :SCRIPT_NAME NEW_VALUE comp_file NOPRINT
Variable script_name varchar2(50)

Rem ===========================================
Rem select upgrade script to run
Rem ===========================================
Begin

If (substr(dbms_registry.version('CONTEXT'), 1, 5) = '8.1.7') then

   :script_name := '@ctxu817.sql';

elsif (substr(dbms_registry.version('CONTEXT'), 1, 5) = '9.0.1') then

   :script_name := '@ctxu901.sql';

elsif (substr(dbms_registry.version('CONTEXT'), 1, 5) = '9.2.0') then

   :script_name := '@ctxu920.sql';

elsif (substr(dbms_registry.version('CONTEXT'), 1, 6) = '10.1.0') then

   :script_name := '@ctxu1010.sql';

else

   :script_name := '?/rdbms/admin/nothing.sql';

end if;

end;
/


Rem set to upgrading
begin
  if (upper(:script_name) not like '%NOTHING%') then
    dbms_registry.upgrading('CONTEXT','Oracle Text','validate_context',
                            'CTXSYS');
  end if;
end;
/

select :script_name from dual;

@&comp_file

Rem ====================================================
Rem reset schema just in case the last script changed it
Rem ====================================================
ALTER SESSION SET CURRENT_SCHEMA = CTXSYS;

begin
  if (upper(:script_name) not like '%NOTHING%') then
    dbms_registry.upgraded('CONTEXT');
    sys.validate_context;
  end if;
end;
/

Rem ===========================================
Rem reset current schema
Rem ===========================================
ALTER SESSION SET CURRENT_SCHEMA = SYS;


