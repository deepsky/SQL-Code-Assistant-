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
Rem    daliao      12/12/02 - match_score support
Rem    daliao      11/13/02 - support blob column type 
Rem    gkaminag    12/04/02 - security
Rem    gkaminag    11/06/02 - make indextype creation dynamic for upgrade
Rem    ehuang      07/31/02 - operators to ityp
Rem    ehuang      07/09/02 - 
Rem    salpha      04/30/01 - force CBO to choose functional invocation
Rem    salpha      08/09/00 - add clob support for ctxrule
Rem    salpha      07/10/00 - creation
Rem


--------------------------------------------------------------
-- CREATE FUNCTIONAL IMPLEMENTATIONS for matches operator --
-------------------------------------------------------------

create or replace package ctx_matches authid current_user as
    
function  matches(
  Colval  in     varchar2, 
  Text    in     varchar2, 
  ia      in     sys.odciindexctx, 
  sctx    in out RuleIndexMethods,
  cflg    in     number
)
  return number is language C
  name "rulematches"
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

function matches(
  Colval  in     clob, 
  Text    in     varchar2, 
  ia      in     sys.odciindexctx, 
  sctx    in out RuleIndexMethods,
  cflg    in     number
)
  return number is language C
  name "rulematches"
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

function matches(
  Colval  in     blob, 
  Text    in     varchar2, 
  ia      in     sys.odciindexctx, 
  sctx    in out RuleIndexMethods,
  cflg    in     number
)
  return number is language C
  name "rulematches"
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

function matches(
  Colval  in     varchar2, 
  Text    in     clob, 
  ia      in     sys.odciindexctx, 
  sctx    in out RuleIndexMethods,
  cflg    in     number
)
  return number is language C
  name "rulematches"
  library dr$lib
  with context
  parameters(
        context,
        Colval,
        Colval INDICATOR,
        Text,
        Text INDICATOR,
	Text CHARSETID,
        ia,
        ia INDICATOR STRUCT,
        sctx,
        sctx INDICATOR STRUCT,
        cflg,
        cflg INDICATOR,
        return OCINumber
      );

function matches(
  Colval  in     clob, 
  Text    in     clob, 
  ia      in     sys.odciindexctx, 
  sctx    in out RuleIndexMethods,
  cflg    in     number
)
  return number is language C
  name "rulematches"
  library dr$lib
  with context
  parameters(
        context,
        Colval,
        Colval INDICATOR,
        Text,
        Text INDICATOR,
	Text CHARSETID,
        ia,
        ia INDICATOR STRUCT,
        sctx,
        sctx INDICATOR STRUCT,
        cflg,
        cflg INDICATOR,
        return OCINumber
      );

function matches(
  Colval  in     blob, 
  Text    in     clob, 
  ia      in     sys.odciindexctx, 
  sctx    in out RuleIndexMethods,
  cflg    in     number
)
  return number is language C
  name "rulematches"
  library dr$lib
  with context
  parameters(
        context,
        Colval,
        Colval INDICATOR,
        Text,
        Text INDICATOR,
	Text CHARSETID,
        ia,
        ia INDICATOR STRUCT,
        sctx,
        sctx INDICATOR STRUCT,
        cflg,
        cflg INDICATOR,
        return OCINumber
      );
end ctx_matches;
/

SHOW ERROR;

create  or replace operator matches binding 
  (varchar2, varchar2) return number 
     with index context, scan context RuleIndexMethods 
     compute ancillary data without column data using ctx_matches.matches,
  (clob, varchar2) return number 
     with index context, scan context RuleIndexMethods 
     compute ancillary data without column data using ctx_matches.matches,
  (blob, varchar2) return number 
     with index context, scan context RuleIndexMethods 
     compute ancillary data without column data using ctx_matches.matches,
  (varchar2, clob) return number 
     with index context, scan context RuleIndexMethods 
     compute ancillary data without column data using ctx_matches.matches,
  (clob, clob) return number 
     with index context, scan context RuleIndexMethods 
     compute ancillary data without column data using ctx_matches.matches,
  (blob, clob) return number 
     with index context, scan context RuleIndexMethods 
     compute ancillary data without column data using ctx_matches.matches
;

grant execute on matches to public;

drop public synonym matches;
create public synonym matches for ctxsys.matches;

-------------------------------
-- CREATE ANCILLARY FUNCTION --
-- 
-- The functions support for varchar2 and clob of <Text> with one
-- C function in condition that the <Text> is not used in C function.
-------------------------------
create or replace package driscorr authid definer as
function  RuleScore(
  Colval  in     varchar2, 
  Text    in     varchar2, 
  ia      in     sys.odciindexctx, 
  sctx    in out RuleIndexMethods,
  cflg    in     number
)
  return number is language C
  name "rulematches"
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

function RuleScore(
  Colval  in     clob, 
  Text    in     varchar2, 
  ia      in     sys.odciindexctx, 
  sctx    in out RuleIndexMethods,
  cflg    in     number
)
  return number is language C
  name "rulematches"
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

function RuleScore(
  Colval  in     blob, 
  Text    in     varchar2, 
  ia      in     sys.odciindexctx, 
  sctx    in out RuleIndexMethods,
  cflg    in     number
)
  return number is language C
  name "rulematches"
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

function RuleScore(
  Colval  in     varchar2, 
  Text    in     clob, 
  ia      in     sys.odciindexctx, 
  sctx    in out RuleIndexMethods,
  cflg    in     number
)
  return number is language C
  name "rulematches"
  library dr$lib
  with context
  parameters(
        context,
        Colval,
        Colval INDICATOR,
        Text,
        Text INDICATOR,
	Text CHARSETID,
        ia,
        ia INDICATOR STRUCT,
        sctx,
        sctx INDICATOR STRUCT,
        cflg,
        cflg INDICATOR,
        return OCINumber
      );

function RuleScore(
  Colval  in     clob, 
  Text    in     clob, 
  ia      in     sys.odciindexctx, 
  sctx    in out RuleIndexMethods,
  cflg    in     number
)
  return number is language C
  name "rulematches"
  library dr$lib
  with context
  parameters(
        context,
        Colval,
        Colval INDICATOR,
        Text,
        Text INDICATOR,
	Text CHARSETID,
        ia,
        ia INDICATOR STRUCT,
        sctx,
        sctx INDICATOR STRUCT,
        cflg,
        cflg INDICATOR,
        return OCINumber
      );

function RuleScore(
  Colval  in     blob, 
  Text    in     clob, 
  ia      in     sys.odciindexctx, 
  sctx    in out RuleIndexMethods,
  cflg    in     number
)
  return number is language C
  name "rulematches"
  library dr$lib
  with context
  parameters(
        context,
        Colval,
        Colval INDICATOR,
        Text,
        Text INDICATOR,
	Text CHARSETID,
        ia,
        ia INDICATOR STRUCT,
        sctx,
        sctx INDICATOR STRUCT,
        cflg,
        cflg INDICATOR,
        return OCINumber
      );
end driscorr;
/
SHOW ERROR;

-------------------------------
-- CREATE ANCILLARY OPERATOR --
-------------------------------
---  CREATE match_score OPERATOR
create or replace operator match_score binding 
   (number) return number
     ancillary to matches(varchar2, varchar2),
		  matches(clob,     varchar2),
		  matches(blob,     varchar2),
		  matches(varchar2, clob), 
		  matches(clob,     clob),
		  matches(blob,     clob)
     without column data using driscorr.RuleScore;

grant execute on match_score to public;

drop public synonym match_score;
create public synonym match_score for ctxsys.match_score;


declare
  x number;
begin
  select count(*) into x from dba_indextypes
  where owner = 'CTXSYS' and indextype_name = 'CTXRULE';
  if (x = 0) then
    execute immediate
'create indextype ctxrule         '||
'for matches(varchar2, varchar2), '||
'    matches(clob,     varchar2), '||
'    matches(blob,     varchar2), '||
'    matches(varchar2, clob),     '||
'    matches(clob,     clob),     '||
'    matches(blob,     clob)      '||
'using RuleIndexMethods           '||
' without column data             '||
' with array dml                  '||
' with order by match_score(number) '    ||
' with rebuild online             ';
  end if;
end;
/

grant execute on ctxrule to public;

create or replace public synonym ctxrule for ctxsys.ctxrule;


rem we are telling CBO that functional invocation is extremely expensive
rem so that CBO always pick domain index scan
associate statistics with indextypes CTXRULE default cost (1,0,0);
associate statistics with packages ctx_matches default cost (1000,10000000000,0);



