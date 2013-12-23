Rem
Rem ctxpreup.sql
Rem
Rem Copyright (c) 2002, Oracle Corporation.  All rights reserved.
Rem
Rem    NAME
Rem      ctxpreup.sql - <one-line expansion of the name>
Rem
Rem    DESCRIPTION
Rem      This script contains common pre-upgrade steps.  Developers
Rem      should keep this up-to-date so that it is compatible with
Rem      the latest versions of everything.  But, because it runs
Rem      before any data dictionary changes, be careful that it is
Rem      also compatible with the lowest supported starting version!
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    ehuang      08/02/02 - ehuang_component_upgrade_2
Rem    ehuang      07/30/02 - Created
Rem

REM ========================================================================
REM temporary versions of ctx_ddl routines
REM ========================================================================

create or replace procedure dr$temp_crepref(
  p_pre_name in varchar2,
  p_obj_name in varchar2
) is
 l_owner# number;
 l_pre_id number;
 l_obj_id number;
 l_cla_id number;
begin
 select user# into l_owner# from sys.user$ where name = 'CTXSYS';
 select dr_id_seq.nextval into l_pre_id from dual;
 select obj_id, obj_cla_id into l_obj_id, l_cla_id from dr$object
  where obj_name = p_obj_name;
 insert into dr$preference
   (pre_id, pre_name, pre_owner#, pre_obj_id, pre_cla_id, pre_valid)
    values
   (l_pre_id, p_pre_name, l_owner#, l_obj_id, l_cla_id, 'Y');

 commit;
end;
/
create or replace procedure dr$temp_cresg(
  p_pre_name in varchar2,
  p_obj_name in varchar2
) is
 l_owner# number;
 l_pre_id number;
 l_obj_id number;
 l_cla_id number;
begin
 select user# into l_owner# from sys.user$ where name = 'CTXSYS';
 select dr_id_seq.nextval into l_pre_id from dual;
 select obj_id into l_obj_id from dr$object where obj_name = p_obj_name;
 insert into dr$section_group(sgp_id, sgp_owner#, sgp_name, sgp_obj_id)
 values                      (l_pre_id, l_owner#, p_pre_name, l_obj_id);
 commit;
end;
/

REM ========================================================================
REM dummy index implementation type in case t scripts need it
REM ========================================================================

create or replace type DummyIndexMethods authid definer as object
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
                           ridlist sys.odciridlist, env sys.ODCIEnv)
            return number,
   static function ODCIIndexUpdate(ia sys.odciindexinfo,
                           ridlist sys.odciridlist, env sys.ODCIEnv)
            return number,
   static function ODCIIndexStart(sctx in out DummyIndexMethods,
                          ia sys.odciindexinfo,
                          op sys.odcipredinfo,
                          qi sys.odciqueryinfo,
                          strt number, stop number, valarg varchar2,
                          env SYS.ODCIEnv)
            return number,
   member function ODCIIndexFetch(nrows number,
                          rids OUT sys.odciridlist, env SYS.ODCIEnv)
            return number,
   member function ODCIIndexClose(env sys.ODCIEnv)
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

PROMPT Create dummy implementation type body ...
PROMPT

create or replace type body DummyIndexMethods is

/* ----------------------- ODCIGetInterfaces ------------------------------ */

static function ODCIGetInterfaces(
  ifclist out    sys.ODCIObjectList
) return number
is
begin
  ifclist := sys.ODCIObjectList(sys.ODCIObject('SYS','ODCIINDEX2'));
  return sys.ODCIConst.Success;
end ODCIGetInterfaces;

/* ----------------------- ODCIIndexCreate -------------------------------- */

static function ODCIIndexCreate(
  ia      in     sys.odciindexinfo,
  parms   in     varchar2,
  env     in     sys.ODCIEnv
) return number
is
begin
  return sys.ODCIConst.Success;
end ODCIIndexCreate;

/* ----------------------- ODCIIndexAlter --------------------------------- */


static function ODCIIndexAlter(
  ia      in     sys.odciindexinfo,
  parms   in out varchar2,
  altopt  in     number,
  env     in     sys.ODCIEnv
) return number
is
begin
  return sys.ODCIConst.Success;
end ODCIIndexAlter;

/* ----------------------- ODCIIndexTruncate ------------------------------ */

static function ODCIIndexTruncate(
  ia      in     sys.odciindexinfo,
  env     in     sys.ODCIEnv
) return number
is
begin
  return sys.odciconst.success;
end ODCIIndexTruncate;

/* ----------------------- ODCIIndexDrop ---------------------------------- */

static function ODCIIndexDrop(
  ia      in     sys.odciindexinfo,
  env     in     sys.ODCIEnv
) return number
is
begin
  return sys.odciconst.success;
end ODCIIndexDrop;

/* ----------------------- ODCIIndexInsert --------------------------- */

static function ODCIIndexInsert(
  ia sys.odciindexinfo,
  ridlist sys.odciridlist, env sys.odcienv) return number
is
begin
  return sys.odciconst.success;
end ODCIIndexInsert;

/* ----------------------- ODCIIndexDelete --------------------------- */

static function ODCIIndexDelete(
  ia sys.odciindexinfo,
  ridlist sys.odciridlist, env sys.ODCIEnv) return number
is
begin
  return sys.odciconst.success;
end ODCIIndexDelete;

/* ----------------------- ODCIIndexUpdate --------------------------- */

static function ODCIIndexUpdate(
  ia sys.odciindexinfo,
  ridlist sys.odciridlist, env sys.ODCIEnv) return number
is
begin
  return sys.odciconst.success;
end ODCIIndexUpdate;

/* ----------------------- ODCIIndexStart --------------------------- */

static function ODCIIndexStart(
  sctx in out DummyIndexMethods,
  ia sys.odciindexinfo,
  op sys.odcipredinfo,
  qi sys.odciqueryinfo,
  strt number,
  stop number,
  valarg varchar2,
  env SYS.ODCIEnv) return number
is
begin
  return sys.odciconst.success;
end ODCIIndexStart;

/* ----------------------- ODCIIndexFetch --------------------------- */

member function ODCIIndexFetch(
  nrows number,
  rids OUT sys.odciridlist,
  env SYS.ODCIEnv) return number
is
begin
  return sys.odciconst.success;
end ODCIIndexFetch;

/* ----------------------- ODCIIndexClose --------------------------- */

member function ODCIIndexClose(
  env sys.ODCIEnv) return number
is
begin
  return sys.odciconst.success;
end ODCIIndexClose;

/* ----------------------- ODCIIndexGetMetaData --------------------------- */

static function ODCIIndexGetMetaData(
  ia        in  sys.odciindexinfo,
  version   in  varchar2,
  new_block out PLS_INTEGER,
  env       in  sys.ODCIEnv
) return varchar2
is
begin
  return null;
end ODCIIndexGetMetaData;

/* ------------------- ODCIIndexUtilGetTableNames -------------------------- */

static function ODCIIndexUtilGetTableNames(
  ia        IN  sys.odciindexinfo,
  read_only IN  PLS_INTEGER,
  version   IN  varchar2,
  context   OUT PLS_INTEGER)
return boolean
is
begin
  return null;
end ODCIIndexUtilGetTableNames;


/* ------------------- ODCIIndexUtilCleanup -------------------------- */

static procedure ODCIIndexUtilCleanup(
 context IN PLS_INTEGER)
is
begin
  null;
end ODCIIndexUtilCleanup;

/* ------------------- ODCIIndexSplitPartition -------------------------- */

static function ODCIIndexSplitPartition(
  ia         IN SYS.ODCIIndexInfo,
  part_name1 IN SYS.ODCIPartInfo,
  part_name2 IN SYS.ODCIPartInfo,
  parms      IN varchar2,
  env        IN SYS.ODCIEnv
) return number
is
begin
  return sys.odciconst.success;
end ODCIIndexSplitPartition;

/* ------------------- ODCIIndexMergePartition -------------------------- */

static function ODCIIndexMergePartition(
  ia         IN SYS.ODCIIndexInfo,
  part_name1 IN SYS.ODCIPartInfo,
  part_name2 IN SYS.ODCIPartInfo,
  parms      IN varchar2,
  env        IN SYS.ODCIEnv
) return number
is
begin
  return sys.odciconst.success;
end ODCIIndexMergePartition;

/* ------------------- ODCIIndexExchangePartition -------------------------- */

static function ODCIIndexExchangePartition(
  ia  IN SYS.ODCIIndexInfo,
  ia1 IN SYS.ODCIIndexInfo,
  env IN SYS.ODCIEnv
) return number
is
begin
  return sys.odciconst.success;
end ODCIIndexExchangePartition;
end;
/
show errors


REM ========================================================================
REM dummy operator in case t scripts need it
REM ========================================================================

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

PROMPT Create dummy operator ...
PROMPT

create or replace operator dummyop binding
  (varchar2, varchar2) return number
     with index context, scan context DummyIndexMethods
without column data using ctx_dummyop.dummyop;

grant execute on dummyop to public;
