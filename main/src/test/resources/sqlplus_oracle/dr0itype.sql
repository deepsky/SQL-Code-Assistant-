Rem
Rem $Header: dr0itype.sql 01-feb-2005.13:58:37 wclin Exp $
Rem
Rem dr0type.pkb
Rem
Rem Copyright (c) 1997, 2005, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      dr0itype.sql - create Index TYPE
Rem
Rem    DESCRIPTION
Rem      EIX framework interfaces body definitions
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    wclin       02/01/05 - remove grant exec to public for impl packages 
Rem    gkaminag    12/04/02 - invoker's rights
Rem    gkaminag    11/06/02 - make indextype creation dynamic for upg scripts
Rem    gkaminag    09/24/02 - security phase 3
Rem    ehuang      08/26/02 - move textoptstats body to itype
Rem    ehuang      07/31/02 - operators to itype
Rem    ehuang      07/09/02 - 
Rem    gkaminag    06/17/02 - 
Rem    gkaminag    06/14/02 - syncrn to invoker's rights
Rem    ehuang      09/29/01 - add uritype bindings.
Rem    wclin       03/09/01 - mv TextOptStats type body
Rem    gkaminag    09/19/00 - more xml support
Rem    gkaminag    08/14/00 - partition support
Rem    yucheng     07/25/00 - enable range partition
Rem    ehuang      06/20/00 - xmltype support
Rem    gkaminag    06/26/00 - latest 8.1.7 upgrade changes
Rem    salpha      03/09/00 - Add structured parameter to catsearch
Rem    gkaminag    02/22/00 - add ctxcat index
Rem    gkaminag    07/13/99 - new dr0itype.sql
Rem

--------------------------------------------------------------
-- CREATE FUNCTIONAL IMPLEMENTATIONS for contains operator --
-------------------------------------------------------------
-- Since two functions cannot be overloaded if their formal 
-- parameters differ only in datatype and the different datatypes 
-- are the same family, there is only one function per datatype 
-- family. 
-- There are 4 datatype families here used by ConText, each is 
-- represented by 1 of its family members: 
--   Number family { number } is represented  by number. 
--   Character family {char, varchar2, varchar, long, long raw, 
--                     nchar, nvarchar2, raw, rowid} is represented 
--                    by varchar2.
--   Date/time family { date } is represented by date.
--   LOB family {BFILE, BLOB, CLOB, NCLOB} is represented by BLOB.
-- Where {} is a list of family members.
-- CTX 8.1 will no longer support date and number families.

create or replace package ctx_contains authid current_user as
    function Textcontains(Colval in varchar2, 
                             Text in varchar2, ia sys.odciindexctx, 
                             sctx IN OUT TextIndexMethods,
                             cflg number /*, env sys.ODCIEnv*/)
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
/*
        env,
        env  INDICATOR STRUCT,
*/
        return OCINumber
      );
    function Textcontains(Colval in clob, 
                                Text in varchar2, ia sys.odciindexctx, 
                                sctx IN OUT TextIndexMethods,
                                cflg number /*, env sys.ODCIEnv */)
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
/*
        env,
        env  INDICATOR STRUCT,
*/
        return OCINumber
      );
    function Textcontains(Colval in blob, 
                                Text in varchar2, ia sys.odciindexctx, 
                                sctx IN OUT TextIndexMethods,
                                cflg number /*, env sys.ODCIEnv*/)
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
/*
        env,
        env  INDICATOR STRUCT,
*/
        return OCINumber
      );
    function Textcontains(Colval in bfile, 
                                Text in varchar2, ia sys.odciindexctx, 
                                sctx IN OUT TextIndexMethods,
                                cflg number /*, env sys.ODCIEnv */)
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
/*
        env,
        env  INDICATOR STRUCT,
*/
        return OCINumber
      );

    function Textcontains(Colval in sys.xmltype,
                                Text in varchar2, ia sys.odciindexctx, 
                                sctx IN OUT TextIndexMethods,
                                cflg number /*, env sys.ODCIEnv*/)
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
/*
        env,
        env  INDICATOR STRUCT,
*/
        return OCINumber
      );

    function Textcontains(Colval in sys.uritype,
                                Text in varchar2, ia sys.odciindexctx, 
                                sctx IN OUT TextIndexMethods,
                                cflg number /*, env sys.ODCIEnv*/)
      return number is language C
      name "contains"
      library dr$lib
      with context
      parameters(
        context,
        Colval,
        Colval INDICATOR STRUCT,
        Text,
        Text INDICATOR,
        Text LENGTH,
        ia,
        ia INDICATOR STRUCT,
        sctx,
        sctx INDICATOR STRUCT,
        cflg,
        cflg INDICATOR,
/*
        env,
        env  INDICATOR STRUCT,
*/
        return OCINumber
      );

end ctx_contains;
/

--------------------------------------
-- CREATE CONTAINS PRIMARY OPERATOR --
--------------------------------------
---  CREATE TEXT OPERATOR
create or replace operator contains binding 
  (varchar2, varchar2) return number 
     with index context, scan context TextIndexMethods 
     compute ancillary data without column data using ctx_contains.Textcontains
,
  (clob, varchar2) return number 
     with index context, scan context TextIndexMethods 
     compute ancillary data without column data using ctx_contains.Textcontains
,
  (blob, varchar2) return number 
     with index context, scan context TextIndexMethods 
     compute ancillary data without column data using ctx_contains.Textcontains
,
  (bfile, varchar2) return number 
     with index context, scan context TextIndexMethods 
     compute ancillary data without column data using ctx_contains.Textcontains
,
  (sys.xmltype, varchar2) return number 
     with index context, scan context TextIndexMethods 
     compute ancillary data without column data using ctx_contains.Textcontains
,
  (sys.uritype, varchar2) return number 
     with index context, scan context TextIndexMethods 
     compute ancillary data without column data using ctx_contains.Textcontains
;

grant execute on contains to public;

drop public synonym contains;
create public synonym contains for ctxsys.contains;

-------------------------------
-- CREATE ANCILLARY FUNCTION --
-------------------------------
create or replace package driscore authid current_user as
    function TextScore(Colval in varchar2, 
                             Text in varchar2, ia sys.odciindexctx, 
                             sctx IN OUT TextIndexMethods,
                             cflg number /*, env sys.ODCIEnv */)
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
/*
        env,
        env  INDICATOR STRUCT,
*/
        return OCINumber
      );
    function TextScore(Colval in clob, 
                                Text in varchar2, ia sys.odciindexctx, 
                                sctx IN OUT TextIndexMethods,
                                cflg number /*, env sys.ODCIEnv */)
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
/*
        env,
        env  INDICATOR STRUCT,
*/
        return OCINumber
      );
    function TextScore(Colval in blob, 
                                Text in varchar2, ia sys.odciindexctx, 
                                sctx IN OUT TextIndexMethods,
                                cflg number /*, env sys.ODCIEnv */)
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
/*
        env,
        env  INDICATOR STRUCT,
*/
        return OCINumber
      );
    function TextScore(Colval in bfile, 
                                Text in varchar2, ia sys.odciindexctx, 
                                sctx IN OUT TextIndexMethods,
                                cflg number /*, env sys.ODCIEnv */)
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
/*
        env,
        env  INDICATOR STRUCT,
*/
        return OCINumber
      );
    function TextScore(Colval in sys.xmltype, 
                                Text in varchar2, ia sys.odciindexctx, 
                                sctx IN OUT TextIndexMethods,
                                cflg number /*, env sys.ODCIEnv */)
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
/*
        env,
        env  INDICATOR STRUCT,
*/
        return OCINumber
      );
    function TextScore(Colval in sys.uritype, 
                                Text in varchar2, ia sys.odciindexctx, 
                                sctx IN OUT TextIndexMethods,
                                cflg number /*, env sys.ODCIEnv */)
      return number is language C
      name "contains"
      library dr$lib
      with context
      parameters(
        context,
        Colval,
        Colval INDICATOR STRUCT,
        Text,
        Text INDICATOR,
        Text LENGTH,
        ia,
        ia INDICATOR STRUCT,
        sctx,
        sctx INDICATOR STRUCT,
        cflg,
        cflg INDICATOR,
/*
        env,
        env  INDICATOR STRUCT,
*/
        return OCINumber
      );
end driscore;
/

-------------------------------
-- CREATE ANCILLARY OPERATOR --
-------------------------------
---  CREATE Score OPERATOR
create or replace operator score binding 
   (number) return number
     ancillary to contains(varchar2, varchar2),
                  contains(clob, varchar2), 
                  contains(blob, varchar2), 
                  contains(bfile, varchar2),
                  contains(sys.xmltype, varchar2),
                  contains(sys.uritype, varchar2)
     without column data using driscore.TextScore;

grant execute on score to public;

drop public synonym score;
create public synonym score for ctxsys.score;

declare
  x number;
begin
  select count(*) into x from dba_indextypes
  where owner = 'CTXSYS' and indextype_name = 'CONTEXT';
  if (x = 0) then
    execute immediate
'create indextype ConText                           '||
'for contains(varchar2, varchar2),                  '||
'    contains(clob, varchar2),                      '||
'    contains(blob, varchar2),                      '||
'    contains(bfile, varchar2),                     '||
'    contains(sys.xmltype, varchar2),               '||
'    contains(sys.uritype, varchar2)                '||
'using TextIndexMethods without column data         '||
'                       with array dml              '||
'                       with order by score(number) '||
'                       with rebuild online         '||
'                       with local range partition  ';
  end if;
end;
/

grant execute on ConText to public;

create or replace public synonym context for ctxsys.context;

ASSOCIATE STATISTICS WITH INDEXTYPES ConText USING TextOptStats;

ASSOCIATE STATISTICS WITH PACKAGES ctx_contains USING TextOptStats;

create or replace procedure syncrn (
  ownid IN binary_integer,
  oname IN varchar2,
  idxid IN binary_integer,
  ixpid IN binary_integer,
  rtabnm IN varchar2
)
  authid definer
  as external
  name "comt_cb"
  library dr$lib
  with context
  parameters(
    context,
    ownid  ub4,
    oname  OCISTRING,
    idxid  ub4,
    ixpid  ub4,
    rtabnm OCISTRING
);
/
