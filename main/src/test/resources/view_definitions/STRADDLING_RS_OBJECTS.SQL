create or replace view STRADDLING_RS_OBJECTS
(OBJECT1, TS1, OBJECT2, TS2, REASON_ID)
as (select t.obj#, t.ts#, l.lobj#, l.ts#, 'Base table and lob object not fully contained in recovery set'
from tab$ t, lob$ l
where l.ts#!=t.ts#
  and l.obj#=t.obj#
  and bitand(t.property,64)=0)
union all
/* check iots having lobs */
select t.obj#,i.ts#,l.lobj#, l.ts#,'Base table and lob object not fully contained in pluggable set'
from  tab$ t, lob$ l, ind$ i
where bitand(t.property,64)!=0
  and l.ts#!=i.ts#
  and l.obj#=t.obj#
  and i.bo# = t.obj#
union all
/* iot and overflow segment are self contained */
select t.obj#, t.ts#, i.obj#, i.ts#, 'IOT and Overflow segment not self contained'
from   tab$ t, ind$ i
where  t.bobj# = i.bo#
  and  t.ts# !=  i.ts#
  and  bitand(t.property,512) != 0
union all
/* Are there dependencies between objects in different tablespaces that
   are enforced through constraints, also ignore constraints that are
   disabled
*/
select t.obj#,t.ts#,cdef$.obj#,t2.ts#,'constraint between tables not contained in recovery set'
from tab$ t2,cdef$, tab$ t
where cdef$.robj#=t.obj#
  and cdef$.obj#=t2.obj#
  and t.ts# != t2.ts#
  and cdef$.enabled is not null
union all
/* tables whose indexes are not in the same tablespace.
   Ignore the following:
    partitioned object - checked separately
    indexes on unsupported TSPITR objects
    indexes enforcing primary key constraints - checked separately
    join indexes - checked separately
*/
select t.obj# object1, t.ts# ts1, i.obj# object2, i.ts# ts2, 'Tables and associated indexes not fully contained in the recovery set'
from  tab$ t, ind$ i
where t.obj#=i.bo#
  and t.ts# != i.ts#
  and bitand(t.property,32)= 0
  and bitand(i.property,2 ) = 0
  and bitand(t.property, 4096) = 0
  and bitand(t.property, 131072)=0
  and bitand(i.property, 1024) = 0			/* skip join indexes */
minus  /* indexes enforcing primary key constraints */
      /* fix bug 860417 - exclude partitioned objects */
select t.obj# object1, t.ts# ts1, i.obj# object2, i.ts# ts2, 'Tables and associated indexes not fully contained in the recovery set'
from  tab$ t, ind$ i , cdef$ cf
where t.obj#=cf.obj#
  and i.obj#=cf.enabled
  and cf.type#=2
  and t.ts# != i.ts#
  and i.bo#=t.obj#
  and bitand(t.property,32)= 0
  and bitand(t.property, 4096) = 0
union all
/* Capture indexes enforcing primary key constraints, ignore internally generated snapshot/indexes */
/* Exclude iots , ALso exclude partitioned tables since they have no storage */
/* The tablespace for partitioned tables defaults to 0 and thus there will   */
/* always be a violation */
select t.obj# object1, t.ts# ts1, i.obj# object2, i.ts# ts2, 'Table and Index enforcing primary key constraint not in same tablespace'
from  tab$ t, ind$ i , cdef$ cf
where t.obj#=cf.obj#
  and i.obj#=cf.enabled
  and cf.type#=2
  and t.ts# != i.ts#
  and i.bo#=t.obj#
  and bitand(t.property,64)=0
  and bitand(t.property,32)= 0
  and bitand(t.property, 4096) = 0
minus  /* primary key constraints on internally generated snapshot tables */
/* exclude partitioned objects and unsupported objects */
select t.obj# object1, t.ts# ts1, i.obj# object2, i.ts# ts2, 'Table and Index enforcing primary key constraint not in same tablespace'
from  tab$ t, ind$ i, obj$ o, user$ u, snap$ s
where t.obj#=i.bo#
  and t.ts# != i.ts#
  and s.tname=o.name
  and s.sowner=u.name
  and o.obj#=t.obj#
  and o.owner#=u.user#
  and bitand(t.property,32)= 0
  and bitand(t.property, 4096) = 0
union all
/* clusters whose indexes are not in the same tablespace 		*/
select c.obj# object1, c.ts# ts1, i.obj# object2, i.ts# ts2,'Tables/Clusters and associated indexes not fully contained in the recovery set'
from clu$ c, ind$ i
where c.obj#=i.bo#
  and c.ts# != i.ts#
union all
/* partitioned tables with at least two partitions in different tablespaces */
/* Exclude logical partitions - they have no storage . eg partitioned iots */
select tp1.obj#, tp1.ts#, tp.obj#, tp.ts#, ' Partitioned Objects not fully contained in the recovery set'
from tabpart$ tp,
     (select  bo#,
              min(ts#) keep (dense_rank first order by part#) ts#,
              min(file#) keep (dense_rank first order by part#) file#,
              min(block#) keep (dense_rank first order by part#) block#,
              min(obj#) keep (dense_rank first order by part#) obj#
      from     tabpart$
      where file# != 0 and block# != 0
      group by bo#) tp1
where tp1.bo# = tp.bo#
  and tp1.ts# != tp.ts#
  and tp.file# != 0
  and tp.block# != 0
union all
/* partitioned indexes that are in tablespace different than any table
   partitions. Exclude partitioned iots - no storage (check for null header)
*/
select tp1.obj#,tp1.ts#,ip.obj#,ip.ts#, '  Partitioned Objects not fully contained in the recovery set'
from indpart$ ip, ind$ i,
     (select   bo#,
               min(ts#) keep (dense_rank first order by part#) ts#,
               min(file#) keep (dense_rank first order by part#) file#,
               min(block#) keep (dense_rank first order by part#) block#,
               min(obj#) keep (dense_rank first order by part#) obj#
      from     tabpart$
      where    file# != 0 and block# != 0
      group by bo#) tp1
where tp1.bo# = i.bo#
  and ip.bo#  = i.obj#
  and tp1.ts# != ip.ts#
union all
/* partitioned table and non-partitioned index in different tablespaces */
select tp.obj#, tp.ts#, i.obj#, i.ts#, ' Partitioned Objects not fully contained in the recovery set'
from tabpart$ tp, ind$ i
where tp.ts#!=i.ts#
  and bitand(i.property,2) =0
  and tp.bo#=i.bo#
  and bitand(i.property, 1024) = 0			/* skip join indexes */
union all
/*  partitioned index and non-partitioned table in different tablespaces */
select t.obj#, t.ts#, ip.obj#, ip.ts#, ' Partitioned Objects not fully contained in the recovery set'
from indpart$ ip, tab$ t, ind$ i
where ip.ts#!=t.ts#
  and t.property=0
  and ip.bo#=i.obj#
  and i.bo#=t.obj#
  and bitand(i.property, 1024) = 0			/* skip join indexes */
union all
/* join index and parent table (or table (sub)partition) in different ts */
select v1.obj#, v1.ts#, v2.obj#, v2.ts#, 'Join Index related objects not fully contained in the recovery set'
from
 (select obj# to#, obj#, ts# from tab$
  union all
  select bo# to#, obj#, ts# from tabpart$
  union all
  select tcp.bo# to#, tsp.obj#, tsp.ts# from tabsubpart$ tsp, tabcompart$ tcp
   where tsp.pobj# = tcp.obj#
 ) v1,
 (select obj# io#, obj#, ts# from ind$
   where bitand(property, 1024) = 1024
  union all
  select ip.bo# io#, ip.obj#, ip.ts# from indpart$ ip, ind$ i
   where ip.bo# = i.obj# and bitand(i.property, 1024) = 1024
  union all
  select icp.bo# io#, isp.obj#, isp.ts#
   from indsubpart$ isp, indcompart$ icp, ind$ i
   where isp.pobj# = icp.obj# and icp.bo# = i.obj#
   and bitand(i.property, 1024) = 1024
 ) v2,
 jijoin$ j
where v2.io# = j.obj#
and (v1.to# = j.tab1obj# or v1.to# = j.tab2obj#)
and v1.ts# != v2.ts#
union all
/* Handle Composite partitions */
/* Subpartitions that are not in the same tablespace */
/* Check the tablespace of the first subpartition of partition 1
   against all tablespaces of other subpartitions for the same object */
select V1.obj#, V1.ts# , V2.obj#, V2.ts#, 'Subpartitions not fully contained in recovery set'
from
      ( select   min(tsp.obj#) keep (dense_rank first
                   order by tcp.part#, tsp.subpart#) obj#,
                 min(tsp.ts#) keep (dense_rank first
                   order by tcp.part#, tsp.subpart#) ts#,
                 tcp.bo# bo#
        from     tabcompart$ tcp, tabsubpart$ tsp
        where    tsp.pobj# = tcp.obj#
        group by tcp.bo#) V1,
      ( select tsp.obj#,ts#,tcp.bo#
        from   tabcompart$ tcp, tabsubpart$ tsp
        where  tsp.pobj# = tcp.obj#) V2
where
      V1.bo# = V2.bo#
  and V1.ts# != V2.ts#
union all
/* Make sure that composite table partitions and index composite partitions
   are in the same tablespace */
select V3.obj#,V3.ts#,V4.obj#,V4.ts#, 'Table subpartition and index subpartition not fully contained in the recovery set'
from
      ( select   min(tsp.obj#) keep (dense_rank first
                   order by tcp.part#, tsp.subpart#) obj#,
                 min(tsp.ts#) keep (dense_rank first
                   order by tcp.part#, tsp.subpart#) ts#,
                 tcp.bo# bo#
        from     tabcompart$ tcp, tabsubpart$ tsp
        where    tsp.pobj# = tcp.obj#
        group by tcp.bo#) V3,
      ( select isp.obj#,ts#,icp.bo#
         from  indcompart$ icp, indsubpart$ isp
         where isp.pobj# = icp.obj#) V4, ind$ i
where
        i.bo#  =  V3.bo#
  and   V4.bo# =  i.obj#
  and   V4.ts# != V3.ts#
union all
/* Partitions having lob fragments */
select lf.fragobj#,lf.ts#, tp.obj#,tp.ts#,'Table partition and lob fragment not in recovery set'
from   lobfrag$ lf, tabpart$ tp
where  lf.tabfragobj# = tp.obj#
  and  tp.ts# !=lf.ts#
union all
/* Subpartitions having lob fragments */
select tsp.obj#,tsp.ts#,lf.fragobj#,lf.ts#,'Table Subpartition and lob fragment not fully contained in pluggable set'
from tabsubpart$ tsp, lobfrag$ lf
where tsp.obj# = lf.tabfragobj#
  and tsp.ts# != lf.ts#
union all
/* Objects that are not supported
	tab$.property
	- 0x1000  = Primary Key based OID$ column
	- 0x20000 = AQs  to be defined
   Block REF columns with ref constraints - bug 895775
*/
/* get all non partitioned, non iot unsupported objects */
select obj#, ts#, -1, -1 , ' Object not allowed in Recovery Set'
from  tab$
where (bitand(property ,4096) != 0
   or bitand(property,131072)!=0)
  and bitand(property,64)=0
  and bitand(property,32)=0
union all
/* get iot objects that aren't supported */
select i.bo#, i.ts# , -1, -1,' Object not allowed in Pluggable Set'
from tab$ t, ind$ i
where t.obj# = i.bo#
  and bitand(t.property, 64)!=0
  and (bitand(t.property, 4096)!=0
        or bitand(t.property,131072)!=0)
union all
/* partitioned objects that aren't supported */
select tp.bo#,tp.ts#,-1,-1, ' Object not allowed in Pluggable Set'
from tabpart$ tp, tab$ t
where t.obj# = tp.bo#
  and (bitand(t.property, 4096)!=0
       or bitand(t.property,131072)!=0)
union all
/* Capture subpartitioned tables that have REFs having ref constraints */
select tcp.bo#,tsp.ts#, -1, -1, '104 Object not allowed in Pluggable Set'
from tab$ t, tabcompart$ tcp, tabsubpart$ tsp
where tsp.pobj# = tcp.obj#
  and tcp.bo# = t.obj#
  and (bitand(t.property, 4096)!=0
       or bitand(t.property,131072)!=0)
union all
/* Bug 895775 Begin  */
/*  Once this bug gets fixed we should rip this code out */
/* Capture tables having referential constraints on REF column */
/* Ignore tables that are stored as IOTs or are partitioned/subpartitioned */
select t.obj#, t.ts#, -1, -1, 'Table has REF column as OID column'
from tab$ t, col$ c
where c.obj# = t.obj#
  and bitand(c.property, 524288)!=0
  and bitand(t.property,64)=0
  and bitand(t.property,32)=0
union all
/* Capture IOTs having referential constraints on REF column */
select i.bo#, i.ts# , -1, -1, 'Table has REF column as OID column'
from ind$ i, col$ c, tab$ t
where c.obj# = i.bo#
and bitand(c.property, 524288)!=0
and bitand(t.property,64)!=0
and t.obj# = i.bo#
union all
/* Capture partitioned tables that have REFs having ref constraints */
select tp.bo#,tp.ts#,-1,-1, 'Table has REF column as OID column'
from tabpart$ tp, col$ c
where c.obj# = tp.bo#
  and bitand(c.property, 524288)!=0
union all
/* Capture subpartitioned tables that have REFs having ref constraints */
select tcp.bo#,tsp.ts#, -1, -1, 'Table has REF column as OID column'
from col$ c, tabcompart$ tcp, tabsubpart$ tsp
where tsp.pobj# = tcp.obj#
  and tcp.bo# = c.obj#
  and bitand(c.property, 524288)!=0
/* End Bug 895775 */
union all
/* Check for Snapshots */
select o2.obj#,t.ts#, -1,-1, 'Snapshots not allowed in recovery set'
from obj$ o, obj$ o2, tab$ t, user$ u, snap$ s
where t.obj#=o.obj#
  and s.tname=o.name
  and s.sowner=u.name
  and u.user#=o.owner#
  and o2.owner#=o.owner#
  and o2.name=s.vname
union all
/* Master tables without snapshot logs not supported */
select distinct(t.obj#), t.ts#, -1,-1 ,'Master table used for snapshots not allowed in recovery set'
from obj$ o,tab$ t,snap$ s,user$ u
where o.obj#=t.obj#
  and o.name=s.master
  and o.owner#=u.user#
  and s.mowner=u.name
  and t.trigflag=0
union all
/* Master tables used for replication/snapshots not allowed in recovery set
   Exclude partitioned tables, they will be checked saperately.
*/
select distinct t.obj#,t.ts#, -1, -1 ,'Master table used for snapshots/replication not allowed in recovery set'
from tab$ t
where t.trigflag!=0
  and bitand(t.property,32) = 0
union all
/* Master tables used for replication/snapshots not allowed in pluggable set
   Tablespace for partitioned tables defaults to 0, hence we need to
   do the check against tabpart$
*/
select distinct t.obj#,tp.ts#, -1, -1 ,'Master table used for snapshots/replication not allowed in pluggable set'
from tab$ t, tabpart$ tp
where t.trigflag != 0
  and t.obj#      = tp.bo#
  and bitand(t.property,32) != 0
union all
/* Capture snapshots on subpartitioned tables */
select distinct t.obj#, tsp.ts#, -1, -1, 'Master table used for snapshots/replication not allowed in pluggable set'
from tab$ t, tabcompart$ tcp, tabsubpart$ tsp
where tsp.pobj#     = tcp.obj#
  and tcp.bo#       = t.obj#
  and t.trigflag   != 0
  and bitand(t.property,32) != 0
union all
/* Check for snapshot logs */
select o.obj#,t.ts#,-1,-1, 'Snapshot logs not allowed in recovery set'
from mlog$ m, tab$ t, obj$ o
where m.log=o.name
  and o.obj#=t.obj#
/* Secondary Objects not allowed in the recovery Set */
union all
select o.obj#, t.ts#,-1,-1, 'Secondary Objects not allowed in Recovery Set'
from tab$ t, obj$ o
where o.obj#=t.obj#
  and o.flags=16
union all
/* Domain/Functional  Indexes not supported */
select i.obj#,i.ts#,-1,-1,'Domain/Functional Indexes not supported'
from ind$ i
where i.type# = 9
   or i.property = 16
union all
/****************************************************/
/*                                                  */
/* Don't allow objects owned by SYS                 */
/*                                                  */
/****************************************************/
/* Capture non-partitioned tables owned by SYS */
select o.obj#, t.ts#,-1,-1, 'Sys owned tables not allowed in Recovery Set'
from tab$ t, obj$ o
where t.obj# = o.obj#
  and bitand(t.property,32) = 0
  and o.owner# = 0
union all
/* Capture partitioned tables owned by SYS */
select o.obj#, tp.ts#,-1,-1, 'Sys owned partitions not allowed in Recovery Set'
from tabpart$ tp, obj$ o
where tp.obj# = o.obj#
  and o.owner# = 0
union all
/* Capture clusters owned by SYS */
select o.obj#, c.ts#,-1,-1, 'Sys owned clusters not allowed in Recovery Set'
from clu$ c, obj$ o
where c.obj# = o.obj#
  and o.owner# = 0
union all
/* Capture subpartitions owned by SYS */
select o.obj#, tsp.ts#,-1,-1, 'Sys owned subpartitions not allowed in Recovery Set'
from tabsubpart$ tsp, obj$ o
where tsp.obj# = o.obj#
  and o.owner# = 0
union all
/* Capture non-partitioned indexes owned by SYS */
select o.obj#, i.ts#,-1,-1, 'Sys owned indexes not allowed in Recovery Set'
from ind$ i, obj$ o
where i.obj# = o.obj#
  and o.owner# = 0
  and bitand(i.property,2) =0
union all
/* Capture partitioned indexes owned by SYS */
select o.obj#, ip.ts#,-1,-1, 'Sys owned partitioned indexes not allowed in Recovery Set'
from indpart$ ip, obj$ o
where ip.obj# = o.obj#
  and o.owner# = 0
union all
/* Capture subpartitioned indexes owned by SYS */
select o.obj#, isp.ts#,-1,-1, 'Sys owned subpartitioned indexes not allowed in Recovery Set'
from indsubpart$ isp, obj$ o
where isp.obj# = o.obj#
  and o.owner# = 0
union all
/* Capture SYS owned lobs */
select l.lobj#, l.ts#,-1,-1, 'Sys owned lobs not allowed in Recovery Set'
from lob$ l, obj$ o
where l.lobj# = o.obj#
  and o.owner# = 0
union all
/* Capture partitioned lobs */
select lf.fragobj#, lf.ts#,-1,-1, 'Sys owned lob fragments not allowed in Recovery Set'
from lobfrag$ lf, obj$ o
where lf.fragobj# = o.obj#
  and o.owner# = 0
union all
/* Make sure that for IOTs the index partitions are all self contained */
select ip1.obj#, ip1.ts#, ip2.obj#, ip2.ts# , ' IOT partitions not self contained'
from (select   bo#,
               min(ts#) keep (dense_rank first order by part#) ts#,
               min(obj#) keep (dense_rank first order by part#) obj#
      from     indpart$
      group by bo#) ip1, indpart$ ip2, ind$ i, tab$ t
where ip1.bo#= i.obj#
and ip1.ts# != ip2.ts#
and ip2.bo# = i.obj#
and i.bo# = t.obj#
and bitand(t.property,64)!=0
union all
/* Make sure that for IOTs, overflow segments and index partitions are self
contained. We can take the first overflow segment partition and run it against
all the index partitions.  This guarantees completeness since all index
partitions are checked for seperately for self containment */
select tp.obj#, tp.ts#,ip.obj#,ip.ts#, ' Overflow segment and index partition not self contained'
from   indpart$ ip, ind$ i, tab$ t,
       (select  bo#,
                min(ts#) keep (dense_rank first order by part#) ts#,
                min(obj#) keep (dense_rank first order by part#) obj#
        from     tabpart$
        group by bo#) tp
where  tp.bo# = t.obj#
  and  bitand(t.property,512)!=0
  and  t.bobj# = i.bo#
  and  ip.bo#= i.obj#
  and  ip.ts# != tp.ts#
/
