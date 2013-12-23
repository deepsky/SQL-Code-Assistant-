Rem
Rem $Header: dr0itypx.sql 01-feb-2005.14:04:45 wclin Exp $
Rem
Rem dr0itypx.sql
Rem
Rem Copyright (c) 2001, 2005, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      dr0itypx.sql - <one-line expansion of the name>
Rem
Rem    DESCRIPTION
Rem      <short description of component this file declares/defines>
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
Rem    wclin       11/12/01 - associate statistics
Rem    wclin       09/25/01 - ctxxpath
Rem    wclin       09/25/01 - Created
Rem

--------------------------------------------------------------
-- CREATE FUNCTIONAL IMPLEMENTATIONS for matches operator --
-------------------------------------------------------------

create or replace package ctx_xpcontains authid current_user as
    
function xpcontains(
  Colval  in     sys.xmltype, 
  Text    in     varchar2, 
  ia      in     sys.odciindexctx, 
  sctx    in out XpathIndexMethods,
  cflg    in     number
)
  return number is language C
  name "contains"
  library dr$lib
  with context
  parameters(
        context,
        Colval,
        Colval INDICATOR,
        Text,
        Text INDICATOR,
        Text LENGTH,
        ia,
        ia INDICATOR STRUCT,
        sctx,
        sctx INDICATOR STRUCT,
        cflg,
        cflg INDICATOR,
        return OCINumber
      );

end ctx_xpcontains;
/

create  or replace operator xpcontains binding 
  (sys.xmltype, varchar2) return number 
     with index context, scan context XpathIndexMethods 
     without column data using ctx_xpcontains.xpcontains
;

grant execute on xpcontains to public;


declare
  x number;
begin
  select count(*) into x from dba_indextypes
  where owner = 'CTXSYS' and indextype_name = 'CTXXPATH';
  if (x = 0) then
    execute immediate
'create indextype ctxxpath             '||
'for xpcontains(sys.xmltype, varchar2) '||
'using XPathIndexMethods               '||
' without column data                  '||
' with array dml                       '||
' with rebuild online                  ';
  end if;
end;
/

grant execute on ctxxpath to public;

create or replace public synonym ctxxpath for ctxsys.ctxxpath;

ASSOCIATE STATISTICS WITH INDEXTYPES ctxxpath USING TextOptStats;

ASSOCIATE STATISTICS WITH PACKAGES ctx_xpcontains USING TextOptStats;

