create or replace view USER_SCHEDULER_JOBS
(JOB_NAME, JOB_SUBNAME, JOB_STYLE, JOB_CREATOR, CLIENT_ID, GLOBAL_UID
, PROGRAM_OWNER, PROGRAM_NAME, JOB_TYPE, JOB_ACTION, NUMBER_OF_ARGUMENTS, SCHEDULE_OWNER
, SCHEDULE_NAME, SCHEDULE_TYPE, START_DATE, REPEAT_INTERVAL, EVENT_QUEUE_OWNER, EVENT_QUEUE_NAME
, EVENT_QUEUE_AGENT, EVENT_CONDITION, EVENT_RULE, END_DATE, JOB_CLASS, ENABLED
, AUTO_DROP, RESTARTABLE, STATE, JOB_PRIORITY, RUN_COUNT, MAX_RUNS
, FAILURE_COUNT, MAX_FAILURES, RETRY_COUNT, LAST_START_DATE, LAST_RUN_DURATION, NEXT_RUN_DATE
, SCHEDULE_LIMIT, MAX_RUN_DURATION, LOGGING_LEVEL, STOP_ON_WINDOW_CLOSE, INSTANCE_STICKINESS, RAISE_EVENTS
, SYSTEM, JOB_WEIGHT, NLS_ENV, SOURCE, DESTINATION, CREDENTIAL_OWNER
, CREDENTIAL_NAME, INSTANCE_ID, DEFERRED_DROP, COMMENTS, FLAGS) as
SELECT jo.name, jo.subname, 'REGULAR', j.creator, j.client_id, j.guid,
    DECODE(bitand(j.flags,4194304),4194304,
      substr(j.program_action,1,instr(j.program_action,'"')-1),NULL),
    DECODE(bitand(j.flags,4194304),4194304,
      substr(j.program_action,instr(j.program_action,'"')+1,
        length(j.program_action)-instr(j.program_action,'"')) ,NULL),
    DECODE(BITAND(j.flags,131072+262144+2097152+524288),
      131072, 'PLSQL_BLOCK', 262144, 'STORED_PROCEDURE',
      2097152, 'EXECUTABLE', 524288, 'CHAIN', NULL),
    DECODE(bitand(j.flags,4194304),0,j.program_action,NULL), j.number_of_args,
    DECODE(bitand(j.flags,1024+4096),0,NULL,
      substr(j.schedule_expr,1,instr(j.schedule_expr,'"')-1)),
    DECODE(bitand(j.flags,1024+4096),0,NULL,
      substr(j.schedule_expr,instr(j.schedule_expr,'"') + 1,
        length(j.schedule_expr)-instr(j.schedule_expr,'"'))),
    DECODE(BITAND(j.flags, 1+2+512+1024+2048+4096+8192+16384+134217728),
      512,'PLSQL',1024,'NAMED',2048,'CALENDAR',4096,'WINDOW',4098,'WINDOW_GROUP',
      8192,'ONCE',16384,'IMMEDIATE',134217728,'EVENT',NULL),
    j.start_date,
    DECODE(BITAND(j.flags,1024+4096+134217728), 0, j.schedule_expr, NULL),
    j.queue_owner, j.queue_name, j.queue_agent,
    DECODE(BITAND(j.flags,134217728), 0, NULL,
      DECODE(BITAND(j.flags,1024+4096), 0, j.schedule_expr, NULL)),
    j.event_rule,
    j.end_date, co.name,
    DECODE(BITAND(j.job_status,1),0,'FALSE','TRUE'),
    DECODE(BITAND(j.flags,32768),0,'TRUE','FALSE'),
    DECODE(BITAND(j.flags,65536),0,'FALSE','TRUE'),
    DECODE(BITAND(j.job_status,2+65536),2,'RUNNING',2+65536,'CHAIN_STALLED',
    DECODE(BITAND(j.job_status,1+4+8+16+32+128+8192),0,'DISABLED',1,
      (CASE WHEN j.retry_count>0 THEN 'RETRY SCHEDULED'
            WHEN (bitand(j.job_status, 1024) <> 0) THEN 'READY TO RUN'
            ELSE 'SCHEDULED' END),
      4,'COMPLETED',8,'BROKEN',16,'FAILED',
      32,'SUCCEEDED' ,128,'REMOTE',8192, 'STOPPED', NULL)),
    j.priority, j.run_count, j.max_runs, j.failure_count, j.max_failures,
    j.retry_count,
    j.last_start_date,
    (CASE WHEN j.last_end_date>j.last_start_date THEN j.last_end_date-j.last_start_date
       ELSE NULL END), j.next_run_date,
    j.schedule_limit, j.max_run_duration,
    DECODE(BITAND(j.flags,32+64+128+256),32,'OFF',64,'RUNS',128,'FAILED RUNS',
      256,'FULL',NULL),
    DECODE(BITAND(j.flags,8),0,'FALSE','TRUE'),
    DECODE(BITAND(j.flags,16),0,'FALSE','TRUE'),
    sys.dbms_scheduler.generate_event_list(j.job_status),
    DECODE(BITAND(j.flags,16777216),0,'FALSE','TRUE'),
    j.job_weight, j.nls_env,
    j.source, j.destination, j.credential_owner, j.credential_name,
    j.instance_id,
    DECODE(BITAND(j.job_status,131072),0,'FALSE','TRUE'),
    j.comments, j.flags
  FROM sys.scheduler$_job j, obj$ jo, obj$ co, v$database v
  WHERE j.obj# = jo.obj# AND
    j.class_oid = co.obj#(+) AND jo.owner# = USERENV('SCHEMAID')
  AND (j.database_role = v.database_role OR
      (j.database_role is null AND v.database_role = 'PRIMARY'))
 UNION ALL
  SELECT lo.name, lo.subname, 'LIGHTWEIGHT', l.creator, l.client_id, l.guid,
    lu.name, po.name, NULL, NULL, NULL,
    DECODE(bitand(l.flags,1024+4096),0,NULL,
      substr(l.schedule_expr,1,instr(l.schedule_expr,'"')-1)),
    DECODE(bitand(l.flags,1024+4096),0,NULL,
      substr(l.schedule_expr,instr(l.schedule_expr,'"') + 1,
        length(l.schedule_expr)-instr(l.schedule_expr,'"'))),
    DECODE(BITAND(l.flags, 1+2+512+1024+2048+4096+8192+16384+134217728),
      512,'PLSQL',1024,'NAMED',2048,'CALENDAR',4096,'WINDOW',4098,'WINDOW_GROUP',
      8192,'ONCE',16384,'IMMEDIATE',134217728,'EVENT',NULL),
    l.start_date,
    DECODE(BITAND(l.flags,1024+4096+134217728), 0, l.schedule_expr, NULL),
    l.queue_owner, l.queue_name, l.queue_agent,
    DECODE(BITAND(l.flags,134217728), 0, NULL,
      DECODE(BITAND(l.flags,1024+4096), 0, l.schedule_expr, NULL)),
    l.event_rule, l.end_date, lco.name,
    DECODE(BITAND(l.job_status,1),0,'FALSE','TRUE'),
    DECODE(BITAND(l.flags,32768),0,'TRUE','FALSE'),
    DECODE(BITAND(l.flags,65536),0,'FALSE','TRUE'),
    DECODE(BITAND(l.job_status,2+65536),2,'RUNNING',2+65536,'CHAIN_STALLED',
    DECODE(BITAND(l.job_status,1+4+8+16+32+128+8192),0,'DISABLED',1,
      (CASE WHEN l.retry_count>0 THEN 'RETRY SCHEDULED'
            WHEN (bitand(l.job_status, 1024) <> 0) THEN 'READY TO RUN'
            ELSE 'SCHEDULED' END),
      4,'COMPLETED',8,'BROKEN',16,'FAILED',
      32,'SUCCEEDED' ,128,'REMOTE',8192, 'STOPPED', NULL)),
    NULL, l.run_count, NULL, l.failure_count, NULL,
    l.retry_count, l.last_start_date,
    (CASE WHEN l.last_end_date>l.last_start_date THEN l.last_end_date-l.last_start_date
       ELSE NULL END), l.next_run_date,
    NULL, NULL,
    DECODE(BITAND(l.flags,32+64+128+256),32,'OFF',64,'RUNS',128,'FAILED RUNS',
      256,'FULL',NULL),
    DECODE(BITAND(l.flags,8),0,'FALSE','TRUE'),
    DECODE(BITAND(l.flags,16),0,'FALSE','TRUE'),
    /* BITAND(j.job_status, 16711680)/65536, */
    sys.dbms_scheduler.generate_event_list(l.job_status),
    DECODE(BITAND(l.flags,16777216),0,'FALSE','TRUE'),
    NULL, NULL, NULL, NULL, NULL, NULL, l.instance_id,
    DECODE(BITAND(l.job_status,131072),0,'FALSE','TRUE'), NULL, l.flags
  FROM scheduler$_lwjob_obj lo, user$ lu, obj$ lco,
    scheduler$_lightweight_job l, obj$ po
  WHERE ((bitand(l.flags, 8589934592) = 0 AND po.type# = 67) OR
         (bitand(l.flags, 8589934592) <> 0 AND po.type# = 66))
    AND l.obj# = lo.obj# AND l.program_oid = po.obj#
    AND lo.userid = lu.user# AND
    l.class_oid = lco.obj#(+) AND lu.user# = USERENV('SCHEMAID')