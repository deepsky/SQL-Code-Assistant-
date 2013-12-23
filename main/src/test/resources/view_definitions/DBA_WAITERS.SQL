CREATE OR REPLACE VIEW DBA_WAITERS
(WAITING_SESSION, HOLDING_SESSION, LOCK_TYPE, MODE_HELD, MODE_REQUESTED,
 LOCK_ID1, LOCK_ID2)
AS
select /*+ordered */ w.sid
      ,s.ksusenum
      ,decode(r.ksqrsidt,
                'MR', 'Media Recovery',
                'RT', 'Redo Thread',
                'UN', 'User Name',
                'TX', 'Transaction',
                'TM', 'DML',
                'UL', 'PL/SQL User Lock',
                'DX', 'Distributed Xaction',
                'CF', 'Control File',
                'IS', 'Instance State',
                'FS', 'File Set',
                'IR', 'Instance Recovery',
                'ST', 'Disk Space Transaction',
                'TS', 'Temp Segment',
                'IV', 'Library Cache Invalidation',
                'LS', 'Log Start or Switch',
                'RW', 'Row Wait',
                'SQ', 'Sequence Number',
                'TE', 'Extend Table',
                'TT', 'Temp Table',
                r.ksqrsidt)
      ,decode(l.lmode,
                0, 'None',           /* Mon Lock equivalent */
                1, 'Null',           /* N */
                2, 'Row-S (SS)',     /* L */
                3, 'Row-X (SX)',     /* R */
                4, 'Share',          /* S */
                5, 'S/Row-X (SSX)',  /* C */
                6, 'Exclusive',      /* X */
                l.lmode)
      ,decode(bitand(w.p1,65535),
                0, 'None',           /* Mon Lock equivalent */
                1, 'Null',           /* N */
                2, 'Row-S (SS)',     /* L */
                3, 'Row-X (SX)',     /* R */
                4, 'Share',          /* S */
                5, 'S/Row-X (SSX)',  /* C */
                6, 'Exclusive',      /* X */
                to_char(bitand(w.p1,65535)))
      ,r.ksqrsid1, r.ksqrsid2
  from v$session_wait w, x$ksqrs r, v$_lock l, x$ksuse s
 where w.wait_Time = 0
   and w.event like 'enq:%'
   and r.ksqrsid1 = w.p2
   and r.ksqrsid2 = w.p3
   and r.ksqrsidt = chr(bitand(p1,-16777216)/16777215)||
                   chr(bitand(p1,16711680)/65535)
   and l.block = 1
   and l.saddr = s.addr
   and l.raddr = r.addr
   and s.inst_id = userenv('Instance')
/

