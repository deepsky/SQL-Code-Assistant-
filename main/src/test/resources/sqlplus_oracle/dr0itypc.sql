Rem
Rem dr0itypc.sql
Rem
Rem Copyright (c) 2000, 2005, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      dr0itypc.sql - create Index TYPe Ctxcat
Rem
Rem    DESCRIPTION
Rem      EIX framework interfaces body definitions
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    wclin       02/01/05 - remove grant exec to public for impl packages 
Rem    gkaminag    12/04/02 - security
Rem    gkaminag    11/06/02 - make indextype creation dynamic for upgrade
Rem    ehuang      07/31/02 - operators to itype
Rem    ehuang      07/09/02 - 
Rem    gkaminag    06/26/00 - creation
Rem


--------------------------------------------------------------
-- CREATE FUNCTIONAL IMPLEMENTATIONS for ctxcat operator --
-------------------------------------------------------------

create or replace package ctx_catsearch authid current_user as

function catsearch(
  Colval  in     varchar2, 
  Text    in     varchar2, 
  condcls in     varchar2,
  ia      in     sys.odciindexctx, 
  sctx    in out CatIndexMethods,
  cflg    in     number
)
  return number is language C
  name "catsearch"
  library dr$lib
  with context
  parameters(
        context,
        Colval,
        Colval INDICATOR,
        Text,
        Text INDICATOR,
        Text LENGTH,
        condcls,
        condcls INDICATOR,
        condcls LENGTH,
        ia,
        ia INDICATOR STRUCT,
        sctx,
        sctx INDICATOR STRUCT,
        cflg,
        cflg INDICATOR,
        return OCINumber
      );

function catsearch(
  Colval  in     clob, 
  Text    in     varchar2, 
  condcls in     varchar2,
  ia      in     sys.odciindexctx, 
  sctx    in out CatIndexMethods,
  cflg    in     number
)
  return number is language C
  name "catsearch"
  library dr$lib
  with context
  parameters(
        context,
        Colval,
        Colval INDICATOR,
        Text,
        Text INDICATOR,
        Text LENGTH,
        condcls,
        condcls INDICATOR,
        condcls LENGTH,
        ia,
        ia INDICATOR STRUCT,
        sctx,
        sctx INDICATOR STRUCT,
        cflg,
        cflg INDICATOR,
        return OCINumber
      );
end ctx_catsearch;
/

create  operator catsearch binding 
  (varchar2, varchar2, varchar2) return number 
     with index context, scan context CatIndexMethods 
     without column data using ctx_catsearch.catsearch,
  (clob, varchar2, varchar2) return number 
     with index context, scan context CatIndexMethods 
     without column data using ctx_catsearch.catsearch
;

grant execute on catsearch to public;

drop public synonym catsearch;
create public synonym catsearch for ctxsys.catsearch;


declare
  x number;
begin
  select count(*) into x from dba_indextypes
  where owner = 'CTXSYS' and indextype_name = 'CTXCAT';
  if (x = 0) then
    execute immediate
'create indextype ctxcat                       '||
'for catsearch(varchar2, varchar2, varchar2),  '||
'    catsearch(clob,     varchar2, varchar2)   '||
'using CatIndexMethods                         '||
' without column data                          '||
' with array dml                               '||
' with rebuild online                          ';
  end if;
end;
/

select *
from dba_indextypes

grant execute on ctxcat to public;

create or replace public synonym ctxcat for ctxsys.ctxcat;

