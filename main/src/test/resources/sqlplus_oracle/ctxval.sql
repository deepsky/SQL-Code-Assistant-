Rem
Rem $Header: ctxval.sql 11-oct-2004.11:17:04 gkaminag Exp $
Rem
Rem ctxval.sql
Rem
Rem Copyright (c) 2004, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxval.sql - rreate validation procedure
Rem
Rem    DESCRIPTION
Rem      create the ctx validation procedure
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    gkaminag    10/11/04 - gkaminag_bug-3936626
Rem    gkaminag    10/07/04 - Created
Rem

ALTER SESSION SET CURRENT_SCHEMA = SYS;

create or replace procedure validate_context
is
  l_type#       binary_integer;
  l_owner#      binary_integer;
  l_ltype       varchar2(30) := 'FIRST';
  l_status      binary_integer;
  l_compile_sql varchar2(2000);
begin

  select user# into l_owner# from sys.user$ where name = 'CTXSYS';
  for c1 in (select dbo_name, dbo_type
               from ctxsys.dr$dbo
              where dbo_type != 'PUBLIC SYNONYM'
              order by dbo_type, dbo_name)
  loop
    if (c1.dbo_type != l_ltype) then
      select decode(c1.dbo_type, 'INDEX',         1,
                                 'TABLE',         2,
                                 'VIEW',          4,
                                 'SEQUENCE',      6,
                                 'PROCEDURE',     7,
                                 'FUNCTION',      8,
                                 'PACKAGE',       9,
                                 'PACKAGE BODY', 11,
                                 'TYPE',         13,
                                 'TYPE BODY',    14,
                                 'LIBRARY',      22,
                                 'INDEXTYPE',    32,
                                 'OPERATOR',     33,
                                 0) 
        into l_type#
        from dual;
      l_ltype := c1.dbo_type;
    end if;

    l_status := -1;
    for c2 in (select status from sys.obj$
                where owner# = l_owner#
                  and name = c1.dbo_name
                  and type# = l_type#)
    loop
      l_status := c2.status;
    end loop;
   
    if (l_status != 1) then
      -- 3591109: Attempt to recompile invalid objects before issuing the
      -- failure notice
      l_compile_sql :=
        case c1.dbo_type
          when 'VIEW' then 
            'alter view ctxsys.' || c1.dbo_name || ' compile'
          when 'PROCEDURE' then
            'alter procedure ctxsys.' || c1.dbo_name || ' compile'
          when 'FUNCTION' then
            'alter function ctxsys.' || c1.dbo_name || ' compile'
          when 'PACKAGE' then 
            'alter package ctxsys.' || c1.dbo_name || ' compile'
          when 'PACKAGE BODY' then
            'alter package ctxsys.' || c1.dbo_name || ' compile body'
          when 'TYPE' then 
            'alter type ctxsys.' || c1.dbo_name || ' compile'
          when 'TYPE BODY' then 
            'alter type ctxsys.' || c1.dbo_name || ' compile body'
          when 'INDEXTYPE' then 
            'alter indextype ctxsys.' || c1.dbo_name || ' compile'
          when 'OPERATOR' then 
            'alter operator ctxsys.' || c1.dbo_name || ' compile'
          else null
        end;

      if l_compile_sql is null then
        dbms_output.put_line(
          'FAILED CHECK FOR '||c1.dbo_type||' '||c1.dbo_name);
        dbms_registry.invalid('CONTEXT');
        goto endfunc;
      else
        begin
          execute immediate l_compile_sql;
        exception
          when others then
            dbms_output.put_line(
              'FAILED CHECK FOR '||c1.dbo_type||' '||c1.dbo_name);
            dbms_registry.invalid('CONTEXT');
            goto endfunc;
        end;
      end if;
    end if;
  end loop;

  dbms_registry.valid('CONTEXT');

<<endfunc>>
  null;
  
exception
  when others then
    ctxsys.drue.text_on_stack(sqlerrm, 'validate_context');
    dbms_registry.invalid('CONTEXT');
    ctxsys.drue.raise;
end validate_context;
/

grant execute on validate_context to ctxsys;

ALTER SESSION SET CURRENT_SCHEMA = CTXSYS;
