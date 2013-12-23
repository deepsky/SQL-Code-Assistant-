Rem
Rem Copyright (c) 2001, 2005, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      d0902000.sql - downgrade from 10i to 9.2.0.2
Rem
Rem    DESCRIPTION
Rem      <short description of component this file declares/defines>
Rem
Rem    NOTES
Rem      this takes a valid 10i ctxsys and tries to get it as close as
Rem      possible to 9.2.0.1.
Rem
Rem      IMPORTANT: AFTER DOWNGRADE, IT WILL BE CLOSE TO 9.2.0.1!!!!
Rem        IF YOU PREVIOUSLY HAD 9.2.0.2 OR SOME OTHER PATCHSET VERSION
Rem        YOU MUST RE-UPGRADE
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    surman      05/11/05 - 4294153: Add deletes for world lexer and mdata 
Rem    daliao      04/08/05 - lrg 1843895
Rem    gkaminag    11/03/04 - bug 3986658 
Rem    gkaminag    10/07/04 - 
Rem    gkaminag    08/02/04 - changes from rae 
Rem    mfaisal     12/17/03 - fast inso filter 
Rem    surman      11/13/03 - 3242708: Remove server tables 
Rem    yucheng     11/10/03 - reverse view changs for sync 
Rem    ehuang      10/30/03 - tracing 
Rem    smuralid    10/28/03 - drop optimize-rebuild types on downgrade 
Rem    gshank      10/03/03 - New German spelling 
Rem    surman      08/13/03 - 2695369: Add ALTER INDEXTYPE 
Rem    daliao      08/04/03 - lrg_1551717: drop object for ODM integration
Rem    gkaminag    04/15/03 - missed objects
Rem    wclin       03/03/03 - reverse dr$stats changes
Rem    gkaminag    02/24/03 - new operator
Rem    daliao      02/24/03 - ctxrule classifier object
Rem    druthven    02/12/03 - bug 2782584: drop OVERRIDE_BASE_LETTER
Rem    gkaminag    02/06/03 - fix errors
Rem    tokawa      01/31/03 - Japanese wordlist
Rem    daliao      02/04/03 - drop clustering
Rem    gkaminag    01/24/03 - classification changes
Rem    gkaminag    01/07/03 - downgrade type does not need new dml methods
Rem    gkaminag    12/17/02 - 
Rem    ehuang      12/16/02 - 
Rem    smuralid    12/17/02 - parallel optimize downgrade
Rem    smuralid    12/23/02 - PX logging desupport
Rem    gkaminag    10/29/02 - 
Rem    gkaminag    10/28/02 - security downgrade
Rem    ehuang      08/02/02 - ehuang_component_upgrade_2
Rem    ehuang      08/01/02 - creation

REM ========================================================================
REM reversing t0902000.sql
REM ========================================================================

Rem create a 9.2.0 version dummy indextype and operator

create or replace type DummyIndexMethods authid definer as object
(
   key          RAW(4),
   objid        RAW(4),
   tmpobjid     RAW(4),
   flag         RAW(4),

   static function ODCIGetInterfaces(ifclist OUT sys.ODCIObjectList)
            return number,
   static function ODCIIndexCreate(ia sys.odciindexinfo, parms varchar2,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexAlter(ia sys.odciindexinfo,
                          parms in out varchar2,
                          altopt number, env sys.ODCIEnv)
            return number,
   static function ODCIIndexTruncate(ia sys.odciindexinfo,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexDrop(ia sys.odciindexinfo,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexStart(sctx in out DummyIndexMethods,
                          ia sys.odciindexinfo,
                          op sys.odcipredinfo,
                          qi sys.odciqueryinfo,
                          strt number, stop number, valarg varchar2,
                          env SYS.ODCIEnv)
            return number,
   static function ODCIIndexFetch(nrows number,
                          rids OUT sys.odciridlist, env SYS.ODCIEnv)
            return number,
   static function ODCIIndexClose(env sys.ODCIEnv) 
            return number,
   static function ODCIIndexGetMetaData(ia        IN  sys.odciindexinfo, 
                                        version   IN  varchar2,
                                        new_block OUT PLS_INTEGER,
                                        env       IN  sys.ODCIEnv)
            return varchar2,
   static function ODCIIndexUtilGetTableNames(ia        IN  sys.odciindexinfo,
                                              read_only IN  PLS_INTEGER,
                                              version   IN  varchar2,
                                              context   OUT PLS_INTEGER)
            return boolean,
   static procedure ODCIIndexUtilCleanup(context IN PLS_INTEGER),
   static function ODCIIndexSplitPartition(ia         IN SYS.ODCIIndexInfo,
                                           part_name1 IN SYS.ODCIPartInfo,
                                           part_name2 IN SYS.ODCIPartInfo,
                                           parms      IN varchar2,
                                           env        IN SYS.ODCIEnv)
            return number,
   static function ODCIIndexMergePartition(ia         IN SYS.ODCIIndexInfo,
                                           part_name1 IN SYS.ODCIPartInfo,
                                           part_name2 IN SYS.ODCIPartInfo,
                                           parms      IN varchar2,
                                           env        IN SYS.ODCIEnv)
            return number, 
   static function ODCIIndexExchangePartition(ia  IN SYS.ODCIIndexInfo,
                                              ia1 IN SYS.ODCIIndexInfo,
                                              env IN SYS.ODCIEnv)
            return number
);
/

rem 9.2.0 version dummy type body

create or replace type body DummyIndexMethods is
static function ODCIGetInterfaces(ifclist out    sys.ODCIObjectList) 
return number is begin
  ifclist := sys.ODCIObjectList(sys.ODCIObject('SYS','ODCIINDEX2')); 
  return sys.ODCIConst.Success; 
end ODCIGetInterfaces; 
static function ODCIIndexCreate(ia in sys.odciindexinfo, parms in varchar2,
env in sys.ODCIEnv) return number 
is begin return sys.ODCIConst.Success; end ODCIIndexCreate;
static function ODCIIndexAlter(ia in sys.odciindexinfo, parms in out varchar2,
altopt in number, env in sys.ODCIEnv) return number 
is begin  return sys.ODCIConst.Success; end ODCIIndexAlter;
static function ODCIIndexStart(sctx in out DummyIndexMethods,
ia sys.odciindexinfo, op sys.odcipredinfo, qi sys.odciqueryinfo,
strt number, stop number, valarg varchar2, env SYS.ODCIEnv) return number
is begin return sys.ODCIConst.Success; end ODCIIndexStart;
static function ODCIIndexFetch(nrows number, rids OUT sys.odciridlist, 
env SYS.ODCIEnv) return number is begin return sys.ODCIConst.Success; end 
ODCIIndexFetch;
static function ODCIIndexClose(env sys.ODCIEnv) return number is begin 
return sys.ODCIConst.Success; end ODCIIndexClose;
static function ODCIIndexTruncate(ia in sys.odciindexinfo, env in sys.ODCIEnv
) return number is begin  return sys.odciconst.success; end ODCIIndexTruncate;
static function ODCIIndexDrop(ia in sys.odciindexinfo, env in sys.ODCIEnv
) return number is begin  return sys.odciconst.success; end ODCIIndexDrop;
static function ODCIIndexGetMetaData(ia in sys.odciindexinfo, 
version in varchar2, new_block out PLS_INTEGER, env in sys.ODCIEnv
) return varchar2 is begin  return null; end ODCIIndexGetMetaData;
static function ODCIIndexUtilGetTableNames(ia IN sys.odciindexinfo,
read_only IN PLS_INTEGER, version IN varchar2, context OUT PLS_INTEGER)
return boolean is begin  return null;  end ODCIIndexUtilGetTableNames;
static procedure ODCIIndexUtilCleanup(context IN PLS_INTEGER)
is begin  null; end ODCIIndexUtilCleanup;
static function ODCIIndexSplitPartition(ia IN SYS.ODCIIndexInfo, 
part_name1 IN SYS.ODCIPartInfo, part_name2 IN SYS.ODCIPartInfo,
parms IN varchar2, env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexSplitPartition;
static function ODCIIndexMergePartition(ia IN SYS.ODCIIndexInfo,
part_name1 IN SYS.ODCIPartInfo, part_name2 IN SYS.ODCIPartInfo,
parms IN varchar2, env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexMergePartition;
static function ODCIIndexExchangePartition(ia IN SYS.ODCIIndexInfo,
ia1 IN SYS.ODCIIndexInfo,env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexExchangePartition;
end;
/

rem dummy operator

create or replace package ctx_dummyop authid definer as
    function dummyop(Colval in varchar2, 
                             Text in varchar2, ia sys.odciindexctx, 
                             sctx IN OUT DummyIndexMethods,
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
end ctx_dummyop;
/

create or replace operator dummyop binding 
  (varchar2, varchar2) return number 
     with index context, scan context DummyIndexMethods
without column data using ctx_dummyop.dummyop;

grant execute on dummyop to public;

REM ========================================================================
REM recreate CONTEXT indextype
REM ========================================================================

PROMPT Remove existing indextype operator bindings ...
PROMPT

alter indextype context add dummyop(varchar2, varchar2);
alter indextype context drop contains(varchar2, varchar2);
alter indextype context drop contains(clob, varchar2);
alter indextype context drop contains(blob, varchar2);
alter indextype context drop contains(bfile, varchar2);
alter indextype context drop contains(sys.xmltype, varchar2);
alter indextype context drop contains(sys.uritype, varchar2);

PROMPT Drop SCORE and CONTAINS operators ...
PROMPT

drop operator score FORCE;
drop operator contains FORCE;
drop package ctx_contains;
DISASSOCIATE STATISTICS FROM INDEXTYPES CONTEXT FORCE;

PROMPT Shift indextype implementation to dummy implementation type ...
PROMPT

alter indextype context using DummyIndexMethods;

PROMPT Create old 9.2.0 version of TextIndexMethods ...
REM    (this is copied directly from dr0type.pkh)

drop type TextIndexMethods;
create or replace type TextIndexMethods authid definer as object
(
   key          RAW(4),
   objid        RAW(4),
   tmpobjid     RAW(4),
   flag         RAW(4),
 
   static function ODCIGetInterfaces(ifclist OUT sys.ODCIObjectList)
            return number,
   static function ODCIIndexCreate(ia sys.odciindexinfo, parms varchar2,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexAlter(ia sys.odciindexinfo,
                          parms in out varchar2,
                          altopt number, env sys.ODCIEnv)
            return number,
   static function ODCIIndexTruncate(ia sys.odciindexinfo,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexDrop(ia sys.odciindexinfo,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexInsert(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.odcienv)
            return number is language C
            name "insert"
            library dr$lib
            with context
            parameters(
               context,
               ia,
               ia INDICATOR STRUCT,
               ridlist,
               ridlist INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexDelete(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.ODCIEnv)
            return number is language C
            name "delete"
            library dr$lib
            with context
            parameters(
               context,
               ia,
               ia INDICATOR STRUCT,
               ridlist,
               ridlist INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexUpdate(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.ODCIEnv) 
            return number is language C
            name "update"
            library dr$lib
            with context
            parameters(
               context,
               ia,
               ia INDICATOR STRUCT,
               ridlist,
               ridlist INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexStart(sctx in out TextIndexMethods,
                          ia sys.odciindexinfo,
                          op sys.odcipredinfo,
                          qi sys.odciqueryinfo,
                          strt number, stop number, valarg varchar2,
                          env SYS.ODCIEnv)
            return number is language C
            name "start"
            library dr$lib
            with context
            parameters(
               context,
               sctx,
               sctx INDICATOR STRUCT,
               ia,
               ia INDICATOR STRUCT,
               op,
               op INDICATOR STRUCT,
               qi,
               qi INDICATOR STRUCT,
               strt,
               strt INDICATOR,
               stop,
               stop INDICATOR,
               valarg, 
               valarg INDICATOR,
               valarg LENGTH,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   member function ODCIIndexFetch(nrows number,
                          rids OUT sys.odciridlist, env SYS.ODCIEnv)
            return number is language C
            name "fetch"
            library dr$lib
            with context
            parameters(
               context,
               self,
               self INDICATOR STRUCT,
               nrows,
               nrows INDICATOR,
               rids,
               rids INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   member function ODCIIndexClose(env sys.ODCIEnv) 
            return number is language C
            name "close"
            library dr$lib
            with context
            parameters(
               context,
               self,
               self INDICATOR STRUCT,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexGetMetaData(ia        IN  sys.odciindexinfo, 
                                        version   IN  varchar2,
                                        new_block OUT PLS_INTEGER,
                                        env       IN  sys.ODCIEnv)
            return varchar2,
   static function ODCIIndexUtilGetTableNames(ia        IN  sys.odciindexinfo,
                                              read_only IN  PLS_INTEGER,
                                              version   IN  varchar2,
                                              context   OUT PLS_INTEGER)
            return boolean,
   static procedure ODCIIndexUtilCleanup(context IN PLS_INTEGER),
   static function ODCIIndexSplitPartition(ia         IN SYS.ODCIIndexInfo,
                                           part_name1 IN SYS.ODCIPartInfo,
                                           part_name2 IN SYS.ODCIPartInfo,
                                           parms      IN varchar2,
                                           env        IN SYS.ODCIEnv)
            return number,
   static function ODCIIndexMergePartition(ia         IN SYS.ODCIIndexInfo,
                                           part_name1 IN SYS.ODCIPartInfo,
                                           part_name2 IN SYS.ODCIPartInfo,
                                           parms      IN varchar2,
                                           env        IN SYS.ODCIEnv)
            return number, 
   static function ODCIIndexExchangePartition(ia  IN SYS.ODCIIndexInfo,
                                              ia1 IN SYS.ODCIIndexInfo,
                                              env IN SYS.ODCIEnv)
            return number
);
/

grant execute on TextIndexMethods to public;


rem create dummy type body

create or replace type body TextIndexMethods is
static function ODCIGetInterfaces(ifclist out    sys.ODCIObjectList) 
return number is begin
  ifclist := sys.ODCIObjectList(sys.ODCIObject('SYS','ODCIINDEX2')); 
  return sys.ODCIConst.Success; 
end ODCIGetInterfaces; 
static function ODCIIndexCreate(ia in sys.odciindexinfo, parms in varchar2,
env in sys.ODCIEnv) return number 
is begin return sys.ODCIConst.Success; end ODCIIndexCreate;
static function ODCIIndexAlter(ia in sys.odciindexinfo, parms in out varchar2,
altopt in number, env in sys.ODCIEnv) return number 
is begin  return sys.ODCIConst.Success; end ODCIIndexAlter;
static function ODCIIndexTruncate(ia in sys.odciindexinfo, env in sys.ODCIEnv
) return number is begin  return sys.odciconst.success; end ODCIIndexTruncate;
static function ODCIIndexDrop(ia in sys.odciindexinfo, env in sys.ODCIEnv
) return number is begin  return sys.odciconst.success; end ODCIIndexDrop;
static function ODCIIndexGetMetaData(ia in sys.odciindexinfo, 
version in varchar2, new_block out PLS_INTEGER, env in sys.ODCIEnv
) return varchar2 is begin  return null; end ODCIIndexGetMetaData;
static function ODCIIndexUtilGetTableNames(ia IN sys.odciindexinfo,
read_only IN PLS_INTEGER, version IN varchar2, context OUT PLS_INTEGER)
return boolean is begin  return null;  end ODCIIndexUtilGetTableNames;
static procedure ODCIIndexUtilCleanup(context IN PLS_INTEGER)
is begin  null; end ODCIIndexUtilCleanup;
static function ODCIIndexSplitPartition(ia IN SYS.ODCIIndexInfo, 
part_name1 IN SYS.ODCIPartInfo, part_name2 IN SYS.ODCIPartInfo,
parms IN varchar2, env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexSplitPartition;
static function ODCIIndexMergePartition(ia IN SYS.ODCIIndexInfo,
part_name1 IN SYS.ODCIPartInfo, part_name2 IN SYS.ODCIPartInfo,
parms IN varchar2, env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexMergePartition;
static function ODCIIndexExchangePartition(ia IN SYS.ODCIIndexInfo,
ia1 IN SYS.ODCIIndexInfo,env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexExchangePartition;
end;
/

PROMPT Shift indextype implementation to TextIndexMethods ...
PROMPT

alter indextype context using TextIndexMethods;

REM recreate 9.2.0 version contains and score operators
REM following copied directly from dr0type.pkh

create or replace package ctx_contains authid definer as
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

grant execute on ctx_contains to public;

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
create or replace package driscore authid definer as
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

grant execute on driscore to public;

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

----------------------------------------------
-- CREATE EIX OPTIMIZER IMPLEMENTATION TYPE --
----------------------------------------------
create or replace type TextOptStats authid definer as object
(
   stats_ctx RAW(4),
   static function ODCIGetInterfaces(ifclist OUT sys.ODCIObjectList)  
       return number,
   static function ODCIStatsCollect(idx sys.ODCIIndexInfo, 
                                    options sys.ODCIStatsOptions,
                                    statistics OUT RAW,
                                    env sys.ODCIEnv)
            return number is language C
            name "st_coll"
            library dr$lib
            with context
            parameters(
               context,
               idx, 
               idx INDICATOR STRUCT,
               options,
               options INDICATOR STRUCT,
               statistics,
               statistics INDICATOR,
               statistics LENGTH,
               env,
               env     INDICATOR STRUCT,
               return OCINumber
             ),
  
   static function ODCIStatsDelete(idx sys.ODCIIndexInfo, statistics OUT RAW,
                                   env sys.ODCIEnv)
            return number is language C
            name "st_del"
            library dr$lib
            with context
            parameters(
               context,
               idx,
               idx INDICATOR STRUCT,
               statistics,
               statistics INDICATOR,
               statistics LENGTH,
               env,
               env     INDICATOR STRUCT,
               return OCINumber
            ),

   static function ODCIStatsSelectivity(pred sys.ODCIPredInfo, 
                                        sel  OUT NUMBER,
                                        args sys.ODCIArgDescList,
                                        strt NUMBER, 
                                        stop NUMBER, 
                                        colval varchar2,
                                        valarg varchar2,
                                        env  sys.ODCIEnv)
            return number is language C
            name "st_sel"
            library dr$lib
            with context
            parameters(
               context,
               pred,
               pred INDICATOR STRUCT,
               sel, 
               sel INDICATOR,
               args,
               args INDICATOR,
               strt,
               strt INDICATOR,
               stop,
               stop INDICATOR,
               colval, 
               colval INDICATOR,
               valarg, 
               valarg INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
             ),
  
   static function ODCIStatsFunctionCost(func sys.ODCIFuncinfo, 
                                         cost IN OUT sys.ODCICost,
                                         args sys.ODCIArgDescList,
                                         colval varchar2,
                                         valarg varchar2,
                                         env  sys.ODCIEnv)
            return number is language C
            name "st_fcost"
            library dr$lib
            with context
            parameters(
               context,
               func,
               func INDICATOR STRUCT,
               cost,
               cost INDICATOR STRUCT,
               args,
               args INDICATOR,
               colval, 
               colval INDICATOR,
               valarg, 
               valarg INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
             ),

   static function ODCIStatsIndexCost(idx sys.ODCIIndexInfo, 
                                      sel NUMBER,
                                      cost IN OUT sys.ODCICost,
                                      qi sys.ODCIQueryInfo,
                                      pred sys.ODCIPredInfo,
                                      args sys.ODCIArgDescList, 
                                      strt NUMBER,
                                      stop NUMBER,
                                      valarg varchar2,
                                      env  sys.ODCIEnv)
            return number is language C
            name "st_icost"
            library dr$lib
            with context
            parameters(
               context,
               idx,
               idx INDICATOR STRUCT,
               sel,
               sel INDICATOR,
               cost,
               cost INDICATOR STRUCT,
               qi,
               qi INDICATOR STRUCT,
               pred,
               pred INDICATOR STRUCT,
               args,
               args INDICATOR,
               strt,
               strt INDICATOR,
               stop,
               stop INDICATOR,
               valarg, 
               valarg INDICATOR,
               env,
               env    INDICATOR STRUCT,
               return OCINumber
             ),
  
  pragma restrict_references(ODCIStatsSelectivity, WNDS, WNPS), 
  pragma restrict_references(ODCIStatsFunctionCost, WNDS, WNPS), 
  pragma restrict_references(ODCIStatsIndexCost, WNDS, WNPS) 
);
/

grant execute on TextOptStats to public;

ASSOCIATE STATISTICS WITH INDEXTYPES ConText USING TextOptStats;

ASSOCIATE STATISTICS WITH PACKAGES ctx_contains USING TextOptStats;

PROMPT Rebind operators and remove the dummyop
PROMPT

alter indextype context add contains(varchar2, varchar2);
alter indextype context add contains(clob, varchar2);
alter indextype context add contains(blob, varchar2);
alter indextype context add contains(bfile, varchar2);
alter indextype context add contains(sys.xmltype, varchar2);
alter indextype context add contains(sys.uritype, varchar2);
alter indextype context drop dummyop(varchar2, varchar2);

-- Added for bug 2695369
alter indextype context using textindexmethods
  with order by score(number);

REM ========================================================================
REM recreate CTXCAT indextype
REM ========================================================================

PROMPT Remove existing indextype operator bindings ...
PROMPT

alter indextype ctxcat add dummyop(varchar2, varchar2);
alter indextype ctxcat drop catsearch(varchar2, varchar2, varchar2);
alter indextype ctxcat drop catsearch(clob, varchar2, varchar2);

PROMPT Drop CATSEARCH operators ...
PROMPT

drop operator catsearch FORCE;
drop package ctx_catsearch;

PROMPT Shift indextype implementation to dummy implementation type ...
PROMPT

alter indextype ctxcat using DummyIndexMethods;

PROMPT Create old 9.2.0 version of TextIndexMethods ...
REM    (this is copied directly from dr0typec.pkh)

drop type CatIndexMethods;
create type CatIndexMethods authid definer as object
(
   key          RAW(4),
   objid        RAW(4),
   tmpobjid     RAW(4),

   static function ODCIGetInterfaces(ifclist OUT sys.ODCIObjectList)
            return number,
   static function ODCIIndexCreate(ia sys.odciindexinfo, parms varchar2,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexAlter(ia sys.odciindexinfo,
                          parms in out varchar2,
                          altopt number, env sys.ODCIEnv)
            return number,
   static function ODCIIndexTruncate(ia sys.odciindexinfo,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexDrop(ia sys.odciindexinfo,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexInsert(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.odcienv) 
             return number,
   static function ODCIIndexDelete(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.odcienv)
            return number,
   static function ODCIIndexUpdate(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.odcienv)
            return number,
   static function ODCIIndexStart(sctx in out CatIndexMethods,
                          ia sys.odciindexinfo,
                          op sys.odcipredinfo,
                          qi sys.odciqueryinfo,
                          strt number, stop number, valarg varchar2, 
                          valarg2 varchar2, env sys.odcienv)
            return number is language C
        name "catstart"
        library dr$lib
        with context
        parameters(
               context,
               sctx,
               sctx INDICATOR STRUCT,
               ia,
               ia INDICATOR STRUCT,
               op,
               op INDICATOR STRUCT,
               qi,
               qi INDICATOR STRUCT,
               strt,
               strt INDICATOR,
               stop,
               stop INDICATOR,
               valarg, 
               valarg INDICATOR,
               valarg LENGTH,          
               valarg2, 
               valarg2 INDICATOR,
               valarg2 LENGTH,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   member function ODCIIndexFetch(nrows number,
                          rids OUT sys.odciridlist, env SYS.ODCIEnv)
            return number is language C
            name "catfetch"
            library dr$lib
            with context
            parameters(
               context,
               self,
               self INDICATOR STRUCT,
               nrows,
               nrows INDICATOR,
               rids,
               rids INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   member function ODCIIndexClose(env sys.ODCIEnv)
            return number is language C
            name "catclose"
            library dr$lib
            with context
            parameters(
               context,
               self,
               self INDICATOR STRUCT,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexGetMetaData(ia        IN  sys.odciindexinfo, 
                                        version   IN  varchar2,
                                        new_block OUT PLS_INTEGER,
                                        env       IN  sys.ODCIEnv)
            return varchar2,
   static function ODCIIndexUtilGetTableNames(ia        IN  sys.odciindexinfo,
                                              read_only IN  PLS_INTEGER,
                                              version   IN  varchar2,
                                              context   OUT PLS_INTEGER)
            return boolean,
   static procedure ODCIIndexUtilCleanup(context IN PLS_INTEGER),
   static function ODCIIndexSplitPartition(ia         IN SYS.ODCIIndexInfo,
                                           part_name1 IN SYS.ODCIPartInfo,
                                           part_name2 IN SYS.ODCIPartInfo,
                                           parms      IN varchar2,
                                           env        IN SYS.ODCIEnv)
            return number,
   static function ODCIIndexMergePartition(ia         IN SYS.ODCIIndexInfo,
                                           part_name1 IN SYS.ODCIPartInfo,
                                           part_name2 IN SYS.ODCIPartInfo,
                                           parms      IN varchar2,
                                           env        IN SYS.ODCIEnv)
            return number, 
   static function ODCIIndexExchangePartition(ia  IN SYS.ODCIIndexInfo,
                                              ia1 IN SYS.ODCIIndexInfo,
                                              env IN SYS.ODCIEnv)
            return number
);
/

grant execute on CatIndexMethods to public;

rem create dummy type body

create or replace type body CatIndexMethods is
static function ODCIGetInterfaces(ifclist out    sys.ODCIObjectList) 
return number is begin
  ifclist := sys.ODCIObjectList(sys.ODCIObject('SYS','ODCIINDEX2')); 
  return sys.ODCIConst.Success; 
end ODCIGetInterfaces; 
static function ODCIIndexCreate(ia in sys.odciindexinfo, parms in varchar2,
env in sys.ODCIEnv) return number 
is begin return sys.ODCIConst.Success; end ODCIIndexCreate;
static function ODCIIndexAlter(ia in sys.odciindexinfo, parms in out varchar2,
altopt in number, env in sys.ODCIEnv) return number 
is begin  return sys.ODCIConst.Success; end ODCIIndexAlter;
static function ODCIIndexTruncate(ia in sys.odciindexinfo, env in sys.ODCIEnv
) return number is begin  return sys.odciconst.success; end ODCIIndexTruncate;
static function ODCIIndexDrop(ia in sys.odciindexinfo, env in sys.ODCIEnv
) return number is begin  return sys.odciconst.success; end ODCIIndexDrop;
static function ODCIIndexInsert(ia in sys.odciindexinfo, ridlist in 
sys.odciridlist, env in sys.ODCIEnv) return number is begin
return sys.odciconst.success; end ODCIIndexInsert;
static function ODCIIndexDelete(ia in sys.odciindexinfo, ridlist in 
sys.odciridlist, env in sys.ODCIEnv) return number is begin
return sys.odciconst.success; end ODCIIndexDelete;
static function ODCIIndexUpdate(ia in sys.odciindexinfo, ridlist in 
sys.odciridlist, env in sys.ODCIEnv) return number is begin
return sys.odciconst.success; end ODCIIndexUpdate;
static function ODCIIndexGetMetaData(ia in sys.odciindexinfo, 
version in varchar2, new_block out PLS_INTEGER, env in sys.ODCIEnv
) return varchar2 is begin  return null; end ODCIIndexGetMetaData;
static function ODCIIndexUtilGetTableNames(ia IN sys.odciindexinfo,
read_only IN PLS_INTEGER, version IN varchar2, context OUT PLS_INTEGER)
return boolean is begin  return null;  end ODCIIndexUtilGetTableNames;
static procedure ODCIIndexUtilCleanup(context IN PLS_INTEGER)
is begin  null; end ODCIIndexUtilCleanup;
static function ODCIIndexSplitPartition(ia IN SYS.ODCIIndexInfo, 
part_name1 IN SYS.ODCIPartInfo, part_name2 IN SYS.ODCIPartInfo,
parms IN varchar2, env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexSplitPartition;
static function ODCIIndexMergePartition(ia IN SYS.ODCIIndexInfo,
part_name1 IN SYS.ODCIPartInfo, part_name2 IN SYS.ODCIPartInfo,
parms IN varchar2, env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexMergePartition;
static function ODCIIndexExchangePartition(ia IN SYS.ODCIIndexInfo,
ia1 IN SYS.ODCIIndexInfo,env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexExchangePartition;
end;
/

PROMPT Shift indextype implementation to CatIndexMethods ...
PROMPT

alter indextype ctxcat using CatIndexMethods;

REM recreate 9.2.0 version catsearch operators
REM following copied directly from dr0typec.pkh

create or replace package ctx_catsearch authid definer as
    
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

grant execute on ctx_catsearch to public;

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

PROMPT Rebind operators and remove the dummyop
PROMPT

alter indextype ctxcat add catsearch(varchar2, varchar2, varchar2);
alter indextype ctxcat add catsearch(clob, varchar2, varchar2);
alter indextype ctxcat drop dummyop(varchar2, varchar2);


REM ========================================================================
REM recreate CTXRULE indextype
REM ========================================================================

PROMPT Remove existing indextype operator bindings ...
PROMPT

alter indextype ctxrule add dummyop(varchar2, varchar2);
alter indextype ctxrule drop matches(varchar2, varchar2);
alter indextype ctxrule drop matches(clob, varchar2);
alter indextype ctxrule drop matches(blob, varchar2);
alter indextype ctxrule drop matches(varchar2, clob);
alter indextype ctxrule drop matches(clob, clob);
alter indextype ctxrule drop matches(blob, clob);

PROMPT Drop MATCH_SCORE AND MATCHES operators ...
PROMPT

drop operator match_score FORCE;
drop operator matches FORCE;
drop package ctx_matches;
drop package driscorr;

PROMPT Shift indextype implementation to dummy implementation type ...
PROMPT

alter indextype ctxrule using DummyIndexMethods;

PROMPT Create old 9.2.0 version of RuleIndexMethods ...
REM    (this is copied directly from dr0typer.pkh)

drop type RuleIndexMethods;
create or replace type RuleIndexMethods authid definer as object
(
   key          RAW(4),
   objid        RAW(4),
   tmpobjid     RAW(4),

   static function ODCIGetInterfaces(ifclist OUT sys.ODCIObjectList)
            return number,
   static function ODCIIndexCreate(ia sys.odciindexinfo, parms varchar2,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexAlter(ia sys.odciindexinfo,
                          parms in out varchar2,
                          altopt number, env sys.ODCIEnv)
            return number,
   static function ODCIIndexTruncate(ia sys.odciindexinfo,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexDrop(ia sys.odciindexinfo,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexInsert(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.ODCIEnv)
            return number is language C
            name "insert"
            library dr$lib
            with context
            parameters(
               context,
               ia,
               ia INDICATOR STRUCT,
               ridlist,
               ridlist INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexDelete(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.ODCIEnv)
            return number is language C
            name "delete"
            library dr$lib
            with context
            parameters(
               context,
               ia,
               ia INDICATOR STRUCT,
               ridlist,
               ridlist INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexUpdate(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.ODCIEnv) 
            return number is language C
            name "update"
            library dr$lib
            with context
            parameters(
               context,
               ia,
               ia INDICATOR STRUCT,
               ridlist,
               ridlist INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexStart(sctx in out RuleIndexMethods,
                          ia sys.odciindexinfo,
                          op sys.odcipredinfo,
                          qi sys.odciqueryinfo,
                          strt number, stop number, valarg varchar2,
                          env SYS.ODCIEnv)
            return number is language C
        name "rulestart"
        library dr$lib
        with context
        parameters(
               context,
               sctx,
               sctx INDICATOR STRUCT,
               ia,
               ia INDICATOR STRUCT,
               op,
               op INDICATOR STRUCT,
               qi,
               qi INDICATOR STRUCT,
               strt,
               strt INDICATOR,
               stop,
               stop INDICATOR,
               valarg, 
               valarg INDICATOR,
               valarg LENGTH,          
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),   
        static function ODCIIndexStart(sctx in out RuleIndexMethods,
                          ia sys.odciindexinfo,
                          op sys.odcipredinfo,
                          qi sys.odciqueryinfo,
                          strt number, stop number, valarg clob,
                          env SYS.ODCIEnv)
            return number is language C
        name "rulecstart"
        library dr$lib
        with context
        parameters(
               context,
               sctx,
               sctx INDICATOR STRUCT,
               ia,
               ia INDICATOR STRUCT,
               op,
               op INDICATOR STRUCT,
               qi,
               qi INDICATOR STRUCT,
               strt,
               strt INDICATOR,
               stop,
               stop INDICATOR,
               valarg, 
               valarg INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   member function ODCIIndexFetch(nrows number,
                          rids OUT sys.odciridlist, env SYS.ODCIEnv)
            return number is language C
            name "rulefetch"
            library dr$lib
            with context
            parameters(
               context,
               self,
               self INDICATOR STRUCT,
               nrows,
               nrows INDICATOR,
               rids,
               rids INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   member function ODCIIndexClose(env sys.ODCIEnv)
            return number is language C
            name "ruleclose"
            library dr$lib
            with context
            parameters(
               context,
               self,
               self INDICATOR STRUCT,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexGetMetaData(ia        IN  sys.odciindexinfo, 
                                        version   IN  varchar2,
                                        new_block OUT PLS_INTEGER,
                                        env       IN  sys.ODCIEnv)
            return varchar2,
   static function ODCIIndexUtilGetTableNames(ia        IN  sys.odciindexinfo,
                                              read_only IN  PLS_INTEGER,
                                              version   IN  varchar2,
                                              context   OUT PLS_INTEGER)
            return boolean,
   static procedure ODCIIndexUtilCleanup(context IN PLS_INTEGER),
   static function ODCIIndexSplitPartition(ia         IN SYS.ODCIIndexInfo,
                                           part_name1 IN SYS.ODCIPartInfo,
                                           part_name2 IN SYS.ODCIPartInfo,
                                           parms      IN varchar2,
                                           env        IN SYS.ODCIEnv)
            return number,
   static function ODCIIndexMergePartition(ia         IN SYS.ODCIIndexInfo,
                                           part_name1 IN SYS.ODCIPartInfo,
                                           part_name2 IN SYS.ODCIPartInfo,
                                           parms      IN varchar2,
                                           env        IN SYS.ODCIEnv)
            return number, 
   static function ODCIIndexExchangePartition(ia  IN SYS.ODCIIndexInfo,
                                              ia1 IN SYS.ODCIIndexInfo,
                                              env IN SYS.ODCIEnv)
            return number
);
/

grant execute on RuleIndexMethods to public;

rem create dummy type body

create or replace type body RuleIndexMethods is
static function ODCIGetInterfaces(ifclist out    sys.ODCIObjectList) 
return number is begin
  ifclist := sys.ODCIObjectList(sys.ODCIObject('SYS','ODCIINDEX2')); 
  return sys.ODCIConst.Success; 
end ODCIGetInterfaces; 
static function ODCIIndexCreate(ia in sys.odciindexinfo, parms in varchar2,
env in sys.ODCIEnv) return number 
is begin return sys.ODCIConst.Success; end ODCIIndexCreate;
static function ODCIIndexAlter(ia in sys.odciindexinfo, parms in out varchar2,
altopt in number, env in sys.ODCIEnv) return number 
is begin  return sys.ODCIConst.Success; end ODCIIndexAlter;
static function ODCIIndexTruncate(ia in sys.odciindexinfo, env in sys.ODCIEnv
) return number is begin  return sys.odciconst.success; end ODCIIndexTruncate;
static function ODCIIndexDrop(ia in sys.odciindexinfo, env in sys.ODCIEnv
) return number is begin  return sys.odciconst.success; end ODCIIndexDrop;
static function ODCIIndexGetMetaData(ia in sys.odciindexinfo, 
version in varchar2, new_block out PLS_INTEGER, env in sys.ODCIEnv
) return varchar2 is begin  return null; end ODCIIndexGetMetaData;
static function ODCIIndexUtilGetTableNames(ia IN sys.odciindexinfo,
read_only IN PLS_INTEGER, version IN varchar2, context OUT PLS_INTEGER)
return boolean is begin  return null;  end ODCIIndexUtilGetTableNames;
static procedure ODCIIndexUtilCleanup(context IN PLS_INTEGER)
is begin  null; end ODCIIndexUtilCleanup;
static function ODCIIndexSplitPartition(ia IN SYS.ODCIIndexInfo, 
part_name1 IN SYS.ODCIPartInfo, part_name2 IN SYS.ODCIPartInfo,
parms IN varchar2, env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexSplitPartition;
static function ODCIIndexMergePartition(ia IN SYS.ODCIIndexInfo,
part_name1 IN SYS.ODCIPartInfo, part_name2 IN SYS.ODCIPartInfo,
parms IN varchar2, env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexMergePartition;
static function ODCIIndexExchangePartition(ia IN SYS.ODCIIndexInfo,
ia1 IN SYS.ODCIIndexInfo,env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexExchangePartition;
end;
/

PROMPT Shift indextype implementation to RuleIndexMethods ...
PROMPT

alter indextype ctxrule using RuleIndexMethods;

REM recreate 9.2.0 version matches operators
REM following copied directly from dr0typer.pkh

create or replace package ctx_matches authid definer as
    
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

grant execute on ctx_matches to public;

create  or replace operator matches binding 
  (varchar2, varchar2) return number 
     with index context, scan context RuleIndexMethods 
     without column data using ctx_matches.matches,
  (clob, varchar2) return number 
     with index context, scan context RuleIndexMethods 
     without column data using ctx_matches.matches,
  (varchar2, clob) return number 
     with index context, scan context RuleIndexMethods 
     without column data using ctx_matches.matches,
  (clob, clob) return number 
     with index context, scan context RuleIndexMethods 
     without column data using ctx_matches.matches
;

grant execute on matches to public;

drop public synonym matches;
create public synonym matches for ctxsys.matches;

PROMPT Rebind operators and remove the dummyop
PROMPT

alter indextype ctxrule add matches(varchar2, varchar2);
alter indextype ctxrule add matches(clob, varchar2);
alter indextype ctxrule add matches(varchar2, clob);
alter indextype ctxrule add matches(clob, clob);
alter indextype ctxrule drop dummyop(varchar2, varchar2);

REM ========================================================================
REM recreate CTXXPATH indextype
REM ========================================================================

PROMPT Remove existing indextype operator bindings ...
PROMPT
alter indextype ctxxpath add dummyop(varchar2, varchar2);
alter indextype ctxxpath drop xpcontains(sys.xmltype, varchar2);

PROMPT Drop CATSEARCH operators ...
PROMPT

drop operator xpcontains FORCE;
drop package ctx_xpcontains;

PROMPT Shift indextype implementation to dummy implementation type ...
PROMPT

alter indextype ctxxpath using DummyIndexMethods;

PROMPT Create new 9.0 version of XpathIndexMethods ...
PROMPT

drop type XpathIndexMethods;

PROMPT Create old 9.2.0 version of XpathIndexMethods ...
REM    (this is copied directly from dr0typex.pkh)

create or replace type XPathIndexMethods authid definer as object
(
   key          RAW(4),
   objid        RAW(4),
   tmpobjid     RAW(4),

   static function ODCIGetInterfaces(ifclist OUT sys.ODCIObjectList)
            return number,
   static function ODCIIndexCreate(ia sys.odciindexinfo, parms varchar2,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexAlter(ia sys.odciindexinfo,
                          parms in out varchar2,
                          altopt number, env sys.ODCIEnv)
            return number,
   static function ODCIIndexTruncate(ia sys.odciindexinfo,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexDrop(ia sys.odciindexinfo,
            env sys.ODCIEnv)
            return number,
   static function ODCIIndexInsert(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.ODCIEnv)
            return number is language C
            name "insert"
            library dr$lib
            with context
            parameters(
               context,
               ia,
               ia INDICATOR STRUCT,
               ridlist,
               ridlist INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexDelete(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.ODCIEnv)
            return number is language C
            name "delete"
            library dr$lib
            with context
            parameters(
               context,
               ia,
               ia INDICATOR STRUCT,
               ridlist,
               ridlist INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexUpdate(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.ODCIEnv) 
            return number is language C
            name "update"
            library dr$lib
            with context
            parameters(
               context,
               ia,
               ia INDICATOR STRUCT,
               ridlist,
               ridlist INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexStart(sctx in out XpathIndexMethods,
                          ia sys.odciindexinfo,
                          op sys.odcipredinfo,
                          qi sys.odciqueryinfo,
                          strt number, stop number, valarg varchar2,
                          env SYS.ODCIEnv)
            return number is language C
        name "start"
        library dr$lib
        with context
        parameters(
               context,
               sctx,
               sctx INDICATOR STRUCT,
               ia,
               ia INDICATOR STRUCT,
               op,
               op INDICATOR STRUCT,
               qi,
               qi INDICATOR STRUCT,
               strt,
               strt INDICATOR,
               stop,
               stop INDICATOR,
               valarg, 
               valarg INDICATOR,
               valarg LENGTH,          
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),   
   member function ODCIIndexFetch(nrows number,
                          rids OUT sys.odciridlist, env SYS.ODCIEnv)
            return number is language C
            name "fetch"
            library dr$lib
            with context
            parameters(
               context,
               self,
               self INDICATOR STRUCT,
               nrows,
               nrows INDICATOR,
               rids,
               rids INDICATOR,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   member function ODCIIndexClose(env sys.ODCIEnv)
            return number is language C
            name "close"
            library dr$lib
            with context
            parameters(
               context,
               self,
               self INDICATOR STRUCT,
               env,
               env INDICATOR STRUCT,
               return OCINumber
            ),
   static function ODCIIndexGetMetaData(ia        IN  sys.odciindexinfo, 
                                        version   IN  varchar2,
                                        new_block OUT PLS_INTEGER,
                                        env       IN  sys.ODCIEnv)
            return varchar2,
   static function ODCIIndexUtilGetTableNames(ia        IN  sys.odciindexinfo,
                                              read_only IN  PLS_INTEGER,
                                              version   IN  varchar2,
                                              context   OUT PLS_INTEGER)
            return boolean,
   static procedure ODCIIndexUtilCleanup(context IN PLS_INTEGER),
   static function ODCIIndexSplitPartition(ia         IN SYS.ODCIIndexInfo,
                                           part_name1 IN SYS.ODCIPartInfo,
                                           part_name2 IN SYS.ODCIPartInfo,
                                           parms      IN varchar2,
                                           env        IN SYS.ODCIEnv)
            return number,
   static function ODCIIndexMergePartition(ia         IN SYS.ODCIIndexInfo,
                                           part_name1 IN SYS.ODCIPartInfo,
                                           part_name2 IN SYS.ODCIPartInfo,
                                           parms      IN varchar2,
                                           env        IN SYS.ODCIEnv)
            return number, 
   static function ODCIIndexExchangePartition(ia  IN SYS.ODCIIndexInfo,
                                              ia1 IN SYS.ODCIIndexInfo,
                                              env IN SYS.ODCIEnv)
            return number
);
/

grant execute on XpathIndexMethods to public;

rem create dummy type body

create or replace type body XPathIndexMethods is
static function ODCIGetInterfaces(ifclist out    sys.ODCIObjectList) 
return number is begin
  ifclist := sys.ODCIObjectList(sys.ODCIObject('SYS','ODCIINDEX2')); 
  return sys.ODCIConst.Success; 
end ODCIGetInterfaces; 
static function ODCIIndexCreate(ia in sys.odciindexinfo, parms in varchar2,
env in sys.ODCIEnv) return number 
is begin return sys.ODCIConst.Success; end ODCIIndexCreate;
static function ODCIIndexAlter(ia in sys.odciindexinfo, parms in out varchar2,
altopt in number, env in sys.ODCIEnv) return number 
is begin  return sys.ODCIConst.Success; end ODCIIndexAlter;
static function ODCIIndexTruncate(ia in sys.odciindexinfo, env in sys.ODCIEnv
) return number is begin  return sys.odciconst.success; end ODCIIndexTruncate;
static function ODCIIndexDrop(ia in sys.odciindexinfo, env in sys.ODCIEnv
) return number is begin  return sys.odciconst.success; end ODCIIndexDrop;
static function ODCIIndexGetMetaData(ia in sys.odciindexinfo, 
version in varchar2, new_block out PLS_INTEGER, env in sys.ODCIEnv
) return varchar2 is begin  return null; end ODCIIndexGetMetaData;
static function ODCIIndexUtilGetTableNames(ia IN sys.odciindexinfo,
read_only IN PLS_INTEGER, version IN varchar2, context OUT PLS_INTEGER)
return boolean is begin  return null;  end ODCIIndexUtilGetTableNames;
static procedure ODCIIndexUtilCleanup(context IN PLS_INTEGER)
is begin  null; end ODCIIndexUtilCleanup;
static function ODCIIndexSplitPartition(ia IN SYS.ODCIIndexInfo, 
part_name1 IN SYS.ODCIPartInfo, part_name2 IN SYS.ODCIPartInfo,
parms IN varchar2, env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexSplitPartition;
static function ODCIIndexMergePartition(ia IN SYS.ODCIIndexInfo,
part_name1 IN SYS.ODCIPartInfo, part_name2 IN SYS.ODCIPartInfo,
parms IN varchar2, env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexMergePartition;
static function ODCIIndexExchangePartition(ia IN SYS.ODCIIndexInfo,
ia1 IN SYS.ODCIIndexInfo,env IN SYS.ODCIEnv) return number
is begin  return sys.odciconst.success; end ODCIIndexExchangePartition;
end;
/

PROMPT Shift indextype implementation to XPathIndexMethods ...
PROMPT

alter indextype ctxxpath using XPathIndexMethods;

REM recreate 9.2.0 version xpcontains operators
REM following copied directly from dr0typex.pkh

create or replace package ctx_xpcontains authid definer as
    
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

grant execute on ctx_xpcontains to public;

create  or replace operator xpcontains binding 
  (sys.xmltype, varchar2) return number 
     with index context, scan context XpathIndexMethods 
     without column data using ctx_xpcontains.xpcontains
;

grant execute on xpcontains to public;

PROMPT Re-bind operators to indextype  ...
PROMPT

alter indextype ctxxpath add xpcontains(sys.xmltype, varchar2);
alter indextype ctxxpath drop dummyop(varchar2, varchar2);

alter package ctx_xpcontains compile;
alter operator xpcontains compile;
alter indextype ctxxpath compile;

REM ========================================================================
REM drop the dummy operator/type
REM ========================================================================

drop operator dummyop;
drop package ctx_dummyop;
drop type DummyIndexMethods;

REM ========================================================================
REM reversing u0902000.sql
REM ========================================================================

REM ========================================================================
REM classification changes
REM ========================================================================

rem remove clustering class

delete from dr$object_attribute where oat_cla_id = 99;
delete from dr$object where obj_cla_id = 99;
delete from dr$class where cla_id = 99;
delete from dr$preference where pre_cla_id = 99;
delete from dr$parameter where par_name = 'DEFAULT_CLUSTERING';
commit;

rem remove SVM classifier

delete from dr$object_attribute where oat_cla_id = 12 and oat_obj_id = 2;
delete from dr$object where obj_cla_id = 12 and obj_id = 2;
commit;

rem remove prune_level
delete from dr$object_attribute where oat_id = 120107;
commit;

rem bug 2782584: remove OVERRIDE_BASE_LETTER
delete from dr$object_attribute where oat_id = 60121;
commit;

rem change classifier class id back to 99
update dr$class set cla_id = 99 where cla_id = 12;
update dr$object set obj_cla_id = 99 where obj_cla_id = 12;
update dr$object_attribute set oat_cla_id = 99, oat_id = oat_id + 870000
where oat_cla_id = 12;
update dr$preference set pre_cla_id = 99 where pre_cla_id = 12;
update dr$preference_value set prv_oat_id = prv_oat_id + 870000
 where prv_pre_id in (select pre_id from dr$preference 
                       where pre_cla_id = 99);

rem remove classifier object with class id 12 from index table  
delete from dr$index_object where ixo_cla_id=12;
delete from dr$index_value where ixv_oat_id>120000 and ixv_oat_id<129999;

commit;

REM ========================================================================
REM MULTI_COLUMN_DATASTORE
REM ========================================================================

delete from dr$object_attribute where oat_id = 10702;
delete from dr$object_attribute where oat_id = 10703;
delete from dr$object_attribute_lov where oal_oat_id = 10703;
commit;

REM ========================================================================
REM JAPANESE LEXER DELIMITER CHARACTERS
REM ========================================================================

delete from dr$object_attribute where oat_id = 60209;
delete from dr$object_attribute_lov where oal_oat_id = 60209;
delete from dr$object_attribute where oat_id = 60809;
delete from dr$object_attribute_lov where oal_oat_id = 60809;
commit;

REM ========================================================================
REM JAPANESE WORDLIST
REM ========================================================================

delete from dr$object_attribute_lov where oal_oat_id = 70101;
delete from dr$object_attribute_lov where oal_oat_id = 70102;
commit;

REM ========================================================================
REM 4294153: Unicode world lexer
REM ========================================================================

delete from dr$object where obj_cla_id = 6 and obj_id = 11;

REM ========================================================================
REM NEW GERMAN SPELLING
REM ========================================================================

delete from dr$object_attribute where oat_id = 60122;
commit;

REM ========================================================================
REM SECURITY
REM ========================================================================

drop view drv$pending;
drop view drv$online_pending;
drop view drv$unindexed2;
drop view drv$unindexed;
drop view drv$delete2;
drop view drv$delete;
drop view drv$online_pending;
drop view drv$waiting;
drop view drv$pending;

REM ========================================================================
REM MAIL_FILTER
REM ========================================================================

delete from dr$object where obj_id = 7 and obj_cla_id = 4;
delete from dr$object_attribute where oat_id = 40701;
delete from dr$object_attribute where oat_id = 40702;
delete from dr$object_attribute where oat_id = 40703;
delete from dr$parameter where par_name = 'MAIL_FILTER_CONFIG_FILE';
delete from dr$preference where pre_cla_id = 4 and pre_obj_id = 7;
commit;

REM ========================================================================
REM INSO_FILTER
REM ========================================================================

delete from dr$object_attribute where oat_id = 40504;
commit;

REM ========================================================================
REM DR$DBO
REM ========================================================================

drop table dr$dbo;

REM ========================================================================
REM PARALLEL OPTIMIZE
REM ========================================================================

drop table dr$number_sequence;

REM ========================================================================
REM 4294153: MDATA
REM ========================================================================

delete from dr$object_attribute where oat_id IN (50207, 50307, 50507, 50607);

REM ========================================================================
REM PROCEDURE type in attributes
REM ========================================================================

update dr$object_attribute set oat_datatype = 'S'
where oat_id in (10501, 40601, 61001, 61003);
update dr$preference_value set prv_value = substr(prv_value,8)
 where prv_oat_id in (10501, 40601, 61001, 61003);
update dr$index_value set ixv_value = substr(ixv_value,8)
 where ixv_oat_id in (10501, 40601, 61001, 61003);
commit;

REM ========================================================================
REM DRIPARX
REM ========================================================================

drop table dr$nvtab;

REM ========================================================================
REM CTX_TRACE_VALUES
REM ========================================================================

drop view ctx_trace_values;

REM ========================================================================
REM CTX_VERSION
REM ========================================================================

create or replace function dri_version return varchar2
is begin return '0.0.0.0.0'; end;
/

CREATE OR REPLACE VIEW ctx_version AS
select '9.2.0.0.0' ver_dict, 
substr(dri_version,1,10) ver_code from dual;


REM ========================================================================
REM Extensible Optimizer
REM ========================================================================
DROP TABLE dr$stats;
ALTER TABLE dr$stats_pre10i RENAME TO dr$stats;
ALTER TABLE dr$part_stats_pre10i RENAME TO dr$part_stats;

ALTER TABLE dr$stats ADD PRIMARY KEY(idx_id);
ALTER TABLE dr$part_stats ADD PRIMARY KEY(idx_id, ixp_id);

REM =======================================================================
REM  CTX_INDEXES, CTX_USER_INDEXES, CTX_INDEX_PARTITIONS,
REM  CTX_USER_INDEX_PARTITIONS
REM =======================================================================

create or replace view ctx_indexes as
select
  idx_id
 ,u.name                 idx_owner
 ,idx_name               idx_name
 ,u2.name                idx_table_owner
 ,o.name                 idx_table
 ,idx_key_name           idx_key_name
 ,idx_text_name          idx_text_name
 ,idx_docid_count        idx_docid_count
 ,idx_status             idx_status
 ,idx_language_column    idx_language_column
 ,idx_format_column      idx_format_column
 ,idx_charset_column     idx_charset_column
 ,decode(idx_type, 0, 'CONTEXT', 1, 'CTXCAT', 2, 'CTXRULE') idx_type
 from dr$index, sys.user$ u, sys.obj$ o, sys.user$ u2
where idx_owner# = u.user#
  and idx_table_owner# = u2.user#
  and idx_table# = o.obj#
;

create or replace view ctx_user_indexes as
select
  idx_id
 ,idx_name               idx_name
 ,u.name                 idx_table_owner
 ,o.name                 idx_table
 ,idx_key_name           idx_key_name
 ,idx_text_name          idx_text_name
 ,idx_docid_count        idx_docid_count
 ,idx_status             idx_status
 ,idx_language_column    idx_language_column
 ,idx_format_column      idx_format_column
 ,idx_charset_column     idx_charset_column
 ,decode(idx_type, 0, 'CONTEXT', 1, 'CTXCAT', 2, 'CTXRULE') idx_type
 from dr$index, sys.user$ u, sys.obj$ o
where idx_owner# = userenv('SCHEMAID')
  and idx_table_owner# = u.user#
  and idx_table# = o.obj#
;

create or replace view ctx_index_partitions as
select
  ixp_id
 ,u1.name                ixp_index_owner
 ,idx_name               ixp_index_name
 ,ixp_name               ixp_index_partition_name
 ,u2.name                ixp_table_owner
 ,o1.name                ixp_table_name
 ,o2.subname             ixp_table_partition_name
 ,ixp_docid_count        ixp_docid_count
 ,ixp_status             ixp_status
 from dr$index_partition, dr$index, sys.user$ u1, sys.user$ u2,
      sys.obj$ o1, sys.obj$ o2
where idx_owner# = u1.user#
  and idx_table_owner# = u2.user#
  and ixp_table_partition# = o2.obj#
  and idx_table# = o1.obj#
  and ixp_idx_id = idx_id
;

create or replace view ctx_user_index_partitions as
select
  ixp_id
 ,idx_name               ixp_index_name
 ,ixp_name               ixp_index_partition_name
 ,u2.name                ixp_table_owner
 ,o1.name                ixp_table_name
 ,o2.subname             ixp_table_partition_name
 ,ixp_docid_count        ixp_docid_count
 ,ixp_status             ixp_status
 from dr$index_partition, dr$index, sys.user$ u2,
      sys.obj$ o1, sys.obj$ o2
where idx_owner# = userenv('SCHEMAID')
  and idx_table_owner# = u2.user#
  and ixp_table_partition# = o2.obj#
  and idx_table# = o1.obj#
  and ixp_idx_id = idx_id
;
REM ========================================================================
REM  server table and view
REM ========================================================================

create table dr$server(
   ser_name        VARCHAR2(60),
   ser_status      VARCHAR2(8)
     CHECK ( ser_status IN ('IDLE', 'RUN', 'EXIT', '')),
   ser_admmbx      VARCHAR2(60),
   ser_oobmbx      VARCHAR2(60),
   ser_session     NUMBER,
   ser_audsid      NUMBER,
   ser_dbid        NUMBER,
   ser_procid      VARCHAR2(10),
   ser_person_mask VARCHAR(30),
   ser_started_at  DATE,
   ser_idle_time   NUMBER,
   ser_db_instance VARCHAR2(10),
   ser_machine     VARCHAR2(64),
   ser_idle_since  DATE,
 CONSTRAINT dr_server_sid  PRIMARY KEY (ser_audsid)
)
;

create or replace view ctx_servers as
select ser_name, ser_status, ser_admmbx, ser_oobmbx, ser_session,
       ser_audsid, ser_dbid, ser_procid, ser_person_mask, ser_started_at,
       ser_idle_time+round(nvl(sysdate-ser_idle_since,0)*86400) ser_idle_time,
       ser_db_instance, ser_machine  
  from dr$server
 where exists (select null from v$session where audsid = ser_audsid)
;

REM ========================================================================
REM generic downgrade steps
REM ========================================================================

REM ========================================================================
REM re-create dr$libx, using null path.  up to 9.2 to run dr0ulib.sql
REM ========================================================================

create or replace library dr$libx as 'RUN_DR0ULIB';
/

REM ========================================================================
REM now drop all packages
REM ========================================================================

drop package CTX_ADM;
drop package CTX_CLS;
drop package CTX_DDL;
drop package CTX_DOC;
drop package CTX_OUTPUT;
drop package CTX_QUERY;
drop package CTX_REPORT;
drop package CTX_THES;
drop package CTX_ULEXER;
drop package DRIACC;
drop package DRICON;
drop package DRIDDLC;
drop package DRIDDLR;
drop package DRIDDLX;
drop type drParaMethod;
drop function parallelpopuindex;
drop procedure dri_move_ctxsys;
drop procedure ctx_validate;
drop package DRIDISP;
drop package DRIDML;
drop package DRIDOC;
drop package DRIERR;
drop package DRIEXP;
drop package DRIG;
drop package DRIIMP;
drop package DRIIXS;
drop package DRILIST;
drop package DRILOAD;
drop package DRIMLX;
drop package DRIOBJ;
drop package DRIOPT;
drop package DRIPARSE;
drop package DRIPREF;
drop package DRIREC;
drop package DRIREP;
drop package DRIREPM;
drop package DRIREPS;
drop package DRIREPT;
drop package DRIREPZ;
drop package DRISGP;
drop package DRISPL;
drop package DRITHS;
drop package DRITHSC;
drop package DRITHSD;
drop package DRITHSL;
drop package DRITHSX;
drop package DRIUTL;
drop package DRIVAL;
drop package DRIXMD;
drop package DRIXTABC;
drop package DRIXTABR;
drop package DRIXTABX;
drop package DRUE;
drop package DRVDDL;
drop package DRVDDLC;
drop package DRVDDLR;
drop package DRVDDLX;
drop package DRVDISP;
drop package DRVDML;
drop package DRVDOC;
drop package DRVIMR;
drop package DRVODM;
drop package DRVTMT;
drop package DRVUTL;
drop package DRVXMD;
drop package DRVXTAB;
drop package DRVXTABC;
drop package DRVXTABR;
drop package DRVXTABX;
drop package DR_DEF;
drop package DRIPARX;
drop package DRVPARX;
drop package DRV0DDL;

rem drop objects used for integration with ODM

drop public synonym dmp_sys;
drop package DMP_SYS_DUMMY;

drop public synonym dm_svm_apply;
drop function dm_svm_apply_dummy;
drop type dmsvmaos;
drop type dmsvmao;

drop public synonym dm_svm_build;
drop function dm_svm_build_dummy;
drop type dmsvmbos;
drop type dmsvmbo;

drop package dm_svm_cur;

drop public synonym dbms_data_mining;
drop package dbms_data_mining_dummy;

drop type dm_clusters;
drop type dm_cluster;
drop type dm_rule;
drop type dm_predicates;
drop type dm_predicate;
drop type dm_histograms;
drop type dm_histogram_bin;

drop public synonym dm_centroids;
drop type dm_centroids_dummy;
drop type dm_centroid_dummy;
drop type dm_children;
drop type dm_child;

drop public synonym dm_cl_build;
drop function dm_cl_build_dummy;
drop type dmclbos;
drop type dmclbo;
drop package dm_cl_cur;

REM ========================================================================
REM now drop all types
REM ========================================================================

drop type dr$optim_state_t;
drop type dr$popindex_state_t;
drop type dr$createindex_state_t;

drop type dr$opttf_impl_t;
drop type dr$itab0_set_t;
drop type dr$itab0_t;
drop type dr$itab_set_t;
drop type dr$itab_t;
