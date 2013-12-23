CREATE OR REPLACE VIEW DBA_WORKLOAD_CAPTURES
(ID, NAME, DBID, DBNAME, DBVERSION,
 PARALLEL, DIRECTORY, STATUS, START_TIME, END_TIME,
 DURATION_SECS, START_SCN, END_SCN, DEFAULT_ACTION, FILTERS_USED,
 CAPTURE_SIZE, DBTIME, DBTIME_TOTAL, USER_CALLS, USER_CALLS_TOTAL,
 USER_CALLS_UNREPLAYABLE, TRANSACTIONS, TRANSACTIONS_TOTAL, CONNECTS, CONNECTS_TOTAL,
 ERRORS, AWR_DBID, AWR_BEGIN_SNAP, AWR_END_SNAP, AWR_EXPORTED,
 ERROR_CODE, ERROR_MESSAGE, DIR_PATH, DIR_PATH_SHARED, LAST_PROCESSED_VERSION)
AS
select
 r.id, r.name
 , r.dbid, r.dbname, r.dbversion
 , (case when rs.parallel > 0 then 'YES' else 'NO' end)
 , r.directory
 , r.status
 , r.start_time, r.end_time
 , round((r.end_time - r.start_time) * 86400)
 , r.start_scn, r.end_scn
 , r.default_action, nvl(f.cnt,0)
 , rs.capture_size
 , rs.dbtime, greatest(rs.dbtime_total, rs.dbtime)
 , rs.user_calls, greatest(rs.user_calls_total, rs.user_calls)
 , rs.user_calls_empty
 , rs.txns, greatest(rs.txns_total, rs.txns)
 , rs.connects, greatest(rs.connects_total, rs.connects)
 , rs.errors
 , r.awr_dbid, r.awr_begin_snap, r.awr_end_snap
 , decode(r.awr_exported, 1, 'YES', 0, 'NO', 'NOT POSSIBLE')
 , r.error_code, r.error_msg
 , r.dir_path
 , r.dir_path_shared
 , r.last_prep_version
from
 wrr$_captures r
 , (select wrr_id, count(*) as cnt
    from   wrr$_filters
    where  filter_type = 'CAPTURE'
    group  by wrr_id) f
 , (select id,
           sum(decode(parallel,'YES',1,0)) as parallel,
           sum(capture_size) as capture_size,
           sum(dbtime) as dbtime,
           sum(dbtime_tend - dbtime_tstart) as dbtime_total,
           sum(user_calls) as user_calls,
           sum(user_calls_tend - user_calls_tstart) as user_calls_total,
           sum(user_calls_empty) as user_calls_empty,
           sum(txns) as txns,
           sum(txns_tend - txns_tstart) as txns_total,
           sum(connects) as connects,
           sum(connects_tend - connects_tstart) as connects_total,
           sum(errors) as errors
    from   wrr$_capture_stats
    group by id) rs
where r.id = f.wrr_id(+)
  and r.id = rs.id(+)
/

