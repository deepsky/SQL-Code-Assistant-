CREATE OR REPLACE PACKAGE BODY xdv_flow_manager_control_pkg
AS

/******************************************************************************
   NAME:       xdv_flow_manager_control_pkg
   PURPOSE:

   REVISIONS:
   Ver        Date        Author           Description
   ---------  ----------  ---------------  ------------------------------------
   1.0        04/10/2007  dvarnich         1. Created this package.

   TO DO:
   a) ...

   NOTES:
   a)

******************************************************************************/

/*=========================*/
/*======= Variables =======*/
/*=========================*/

/*=========================*/
/*==== Private Methods ====*/
/*=========================*/


--------------------------------------------------------------------
-- initialize the logger package
--------------------------------------------------------------------

  PROCEDURE start_logger (
      a_proc_name                       VARCHAR2 DEFAULT 'flow_manager_control' ) IS
  BEGIN
    xdv_logger_pkg.clearContext(v_log_ctx);

    v_log_ctx := xdv_logger_pkg.initContext2(
                   a_min_log_lvl_id  => xdv_logger_pkg.pc_ll_trace,
                   a_show_user       => xdv_logger_pkg.pc_us_or_os,
                   a_def_log_section => a_proc_name,
                   a_use_ssec_prc    => FALSE ,
                   a_show_callstack  => TRUE ,

                   a_use_logtable    => TRUE ,
                   a_use_auto_trans  => TRUE ,

                   a_use_dbms_output => FALSE ,
                   a_use_log_alert   => FALSE ,
                   a_use_log_trace   => FALSE ,
                   a_use_dbms_pipe   => FALSE ,
                   a_use_tcp_sock    => FALSE ,
                   a_use_utl_file    => FALSE ,

                   a_utl_file_name   => xdv_logger_pkg.pc_def_utl_file_name,
                   a_utl_file_dir    => xdv_logger_pkg.pc_def_utl_file_dir,

                   a_use_utl_email   => FALSE,
                   a_utl_email_user  => xdv_logger_pkg.pc_def_utl_mail_user,
                   a_utl_email_subj  => xdv_logger_pkg.pc_def_utl_mail_subj,
                   a_utl_email_recs  => pc_def_email_recs,

                   a_dbms_pipe_name  => 'LOG_PIPE',
                   a_pipe_to_log4j   => FALSE ,
                   a_dbms_output_lsize => 80  ,
                   a_dbms_output_headr => TRUE
                   );

    xdv_logger_pkg.info( a_log_ctx => v_log_ctx, a_log_msg => 'Logger initialized (auto-trans on) ... ');

  END start_logger;

  PROCEDURE stop_logger IS
  BEGIN
  	xdv_logger_pkg.info(a_log_ctx => v_log_ctx, a_log_msg => 'Logger closed.');
  	xdv_logger_pkg.clearContext(v_log_ctx);
  END stop_logger;


--------------------------------------------------------------------
-- generic procedures
--------------------------------------------------------------------

--------------------------------------------------------------------
-- schedule specific procedures
--------------------------------------------------------------------

--------------------------------------------------------------------
  FUNCTION get_sch_name (a_fmn_id INTEGER) RETURN VARCHAR2 IS
    l_fact_table_name VARCHAR2(30);
  BEGIN
    SELECT trd.internal_table_name INTO l_fact_table_name
    FROM xdv_prd_flow_matrix_node_t fmn, xdv_prd_table_ref_def_t trd
    WHERE fmn.id = a_fmn_id AND fmn.trd_id = trd.id;

	  RETURN UPPER(SUBSTR(l_fact_table_name, 5, 7) || pc_sch_name_suffix);
  END get_sch_name;

--------------------------------------------------------------------
  PROCEDURE create_schedule         (
                a_sched_name         IN VARCHAR2,
                a_sched_ivl          IN VARCHAR2) IS
    l_id    INTEGER;
  BEGIN
    l_id := xdv_scheduler_pkg.create_schedule(
                  a_sch_categ       => xdv_scheduler_pkg.pc_sch_cat_other,
                  a_sch_name        => a_sched_name,
                  a_sch_interval    => a_sched_ivl,
                  a_sch_comments    => 'DD partition status schedule');

    IF (xdv_logger_pkg.isDebugEnabled(a_log_ctx => v_log_ctx) = TRUE) THEN
      xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Created schedule [' || a_sched_name || '] schedule-interval [' ||
                                         a_sched_ivl || '].');
    END IF;

  EXCEPTION
    WHEN xdv_scheduler_pkg.ex_duplicate_schedule_name THEN
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx,
                           a_log_msg => 'Unable to create schedule "'|| a_sched_name ||'": already exists.');
  END create_schedule;

------------------------------------------------------------------

  PROCEDURE drop_schedule           (
                a_sched_name         IN VARCHAR2,
                a_force_drop         IN BOOLEAN) IS
  BEGIN

    xdv_scheduler_pkg.drop_schedule(a_sched_name, a_force_drop);
    IF (xdv_logger_pkg.isDebugEnabled(a_log_ctx => v_log_ctx) = TRUE) THEN
      xdv_logger_pkg.debug( a_log_ctx => v_log_ctx, a_log_msg => 'Dropped schedule [' || a_sched_name || '].');
    END IF;

  EXCEPTION
    WHEN xdv_scheduler_pkg.ex_invalid_schedule_name        THEN
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to drop schedule: ' || a_sched_name || ' - doesn''t exist.');
    WHEN xdv_scheduler_pkg.ex_object_dependents_detected THEN
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to drop schedule: ' || a_sched_name || ' - other objects depend on it.');
  END drop_schedule;

--------------------------------------------------------------------


--------------------------------------------------------------------
-- job specific procedures
--------------------------------------------------------------------

  FUNCTION get_job_name (a_fmn_id INTEGER) RETURN VARCHAR2 IS
    l_fact_table_name VARCHAR2(30);
  BEGIN
    SELECT trd.internal_table_name INTO l_fact_table_name
    FROM xdv_prd_flow_matrix_node_t fmn, xdv_prd_table_ref_def_t trd
    WHERE fmn.id = a_fmn_id AND fmn.trd_id = trd.id;

	  RETURN UPPER(SUBSTR(l_fact_table_name, 5, 7) || pc_job_name_suffix);
  END get_job_name;

--------------------------------------------------------------------
  FUNCTION create_job (
          a_fmn_id  IN INTEGER) RETURN VARCHAR2 IS
    l_job_id          INTEGER;
    rv_job_name       VARCHAR2(64);
  BEGIN

    -- build job name
    rv_job_name := get_job_name(a_fmn_id);

    l_job_id :=
          xdv_scheduler_pkg.create_job(
              a_job_categ           => xdv_scheduler_pkg.pc_job_cat_trans,
              a_job_name            => rv_job_name,
              a_job_action          => pc_def_job_stp,
              a_job_resources       => NULL,
              a_job_comments        => 'Flow Matrix Node processing job for FMN ' || a_fmn_id);

    IF (xdv_logger_pkg.isDebugEnabled(a_log_ctx => v_log_ctx) = TRUE) THEN
      xdv_logger_pkg.debug( a_log_ctx => v_log_ctx, a_log_msg => 'Created job "' || rv_job_name || '".');
    END IF;

    RETURN rv_job_name;

  EXCEPTION
  WHEN OTHERS THEN
    xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to create job: ' || SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512));
    RETURN NULL;

  END create_job;

--------------------------------------------------------------------
  PROCEDURE drop_job (
              a_fmn_id             IN INTEGER,
              a_force_drop         IN BOOLEAN
              ) IS
    l_job_name VARCHAR2(64);
  BEGIN

    l_job_name := get_job_name(a_fmn_id);

    xdv_scheduler_pkg.drop_job(l_job_name, a_force_drop);

    IF (xdv_logger_pkg.isDebugEnabled(a_log_ctx => v_log_ctx) = TRUE) THEN
      xdv_logger_pkg.debug( a_log_ctx => v_log_ctx, a_log_msg => 'Dropped job [' || l_job_name || '].');
    END IF;

  EXCEPTION
    WHEN xdv_scheduler_pkg.ex_invalid_job_name        THEN
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to drop job: ' || l_job_name || ' - doesn''t exist.');
    WHEN xdv_scheduler_pkg.ex_object_dependents_detected THEN
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to drop job: ' || l_job_name || ' - other objects depend on it.');
  END drop_job;


--------------------------------------------------------------------
-- public procedures
--------------------------------------------------------------------

--------------------------------------------------------------------
  PROCEDURE unschedule_job (
              a_fmn_id             IN INTEGER,
              a_force_stop         IN BOOLEAN DEFAULT FALSE,
              a_sched_name         IN VARCHAR2 DEFAULT pc_def_sched_name)
  IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_job_name         VARCHAR2(64);
  BEGIN
    l_job_name := get_job_name(a_fmn_id);

    BEGIN
      xdv_scheduler_pkg.unschedule_job(
                a_job_name   => l_job_name,
                a_sch_name   => a_sched_name,
                a_force_stop => a_force_stop);
    EXCEPTION
      WHEN OTHERS THEN
        xdv_logger_pkg.warn( a_log_ctx => v_log_ctx,
                             a_log_msg => 'Unable to unschedule job: "' || l_job_name ||
                                          '" with schedule "' || a_sched_name ||'" : ' ||
                                          SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512));
    END;

    drop_job (a_fmn_id => a_fmn_id, a_force_drop => a_force_stop);

    --drop_schedule(a_sched_name, a_force_stop);

    IF (xdv_logger_pkg.isInfoEnabled(a_log_ctx => v_log_ctx) = TRUE) THEN
      xdv_logger_pkg.info(
            a_log_ctx => v_log_ctx,
            a_log_msg => 'Dropped schedule [' || a_sched_name || '] and job [' || l_job_name ||
                         '], with force_stop [' || (CASE WHEN a_force_stop = TRUE THEN 'ON' ELSE 'OFF' END) || '].');
    END IF;

    COMMIT;

  END unschedule_job;

--------------------------------------------------------------------

  FUNCTION get_fmn_id(
              a_flow_type          IN VARCHAR2,
              a_stage_name         IN VARCHAR2,
              a_granularity        IN VARCHAR2) RETURN INTEGER
  IS
    l_fmn_id  INTEGER;
  BEGIN
    SELECT fmn.id INTO l_fmn_id
    FROM xdv_prd_flow_matrix_node_t fmn
         LEFT JOIN xdv_prd_flow_type_t fwt ON
              fmn.fwt_id = fwt.id
         JOIN xdv_prd_stage_ref_def_t stg ON
              fmn.stg_id = stg.id
         JOIN xdv_prd_granularity_t gra ON
              fmn.gra_id = gra.id
    WHERE fwt.context = a_flow_type AND
          stg.stage_name = a_stage_name AND
          gra.granularity = a_granularity;

    RETURN l_fmn_id;
  END get_fmn_id;

--------------------------------------------------------------------

  PROCEDURE unschedule_job (
              a_flow_type          IN VARCHAR2,
              a_stage_name         IN VARCHAR2,
              a_granularity        IN VARCHAR2,
--              a_force_stop         IN BOOLEAN DEFAULT FALSE,
              a_sched_name         IN VARCHAR2 DEFAULT pc_def_sched_name)
  IS
    l_fmn_id  INTEGER;
  BEGIN
    l_fmn_id := get_fmn_id(a_flow_type, a_stage_name, a_granularity);

    unschedule_job (
              a_fmn_id => l_fmn_id,
              a_force_stop => a_force_stop,
              a_sched_name => a_sched_name);

  END unschedule_job;

--------------------------------------------------------------------

  PROCEDURE schedule_job (
              a_fmn_id             IN INTEGER,
              a_force_stop         IN BOOLEAN DEFAULT FALSE,
              a_sched_ivl          IN VARCHAR2 DEFAULT pc_def_sched_ivl,
              a_sched_name         IN VARCHAR2 DEFAULT pc_def_sched_name)
  IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_job_name     VARCHAR2(64);
    l_sch_name     VARCHAR2(64);
    l_job_args     xdv_scheduler_pkg.tn_job_arguments;
    l_sch_job_id   INTEGER;
    l_alloc_adv    INTEGER;
  BEGIN

    IF(a_sched_ivl != pc_def_sched_ivl) THEN
      l_sch_name := get_sch_name(a_fmn_id);
    ELSE
      l_sch_name := a_sched_name;
    END IF;

    -- clean current job ... if any
    unschedule_job(a_fmn_id => a_fmn_id, a_force_stop => a_force_stop, a_sched_name => a_sched_name);

    -- schedule job now
    create_schedule(a_sched_name => l_sch_name, a_sched_ivl => a_sched_ivl);

    l_job_name := create_job(a_fmn_id => a_fmn_id);

    l_job_args := xdv_scheduler_pkg.tn_job_arguments();
    l_job_args.EXTEND(1);

    l_job_args(1).a_arg_name := 'a_fmn_id';
    l_job_args(1).a_arg_data_type := 'VARCHAR2';
    l_job_args(1).a_arg_value := a_fmn_id;

    l_sch_job_id :=
         xdv_scheduler_pkg.schedule_job (
                a_job_name           => l_job_name,
                a_sch_name           => l_sch_name,
                a_sch_job_name       => l_job_name,
                a_date_start         => SYSTIMESTAMP,
                a_sch_job_arguments  => l_job_args,
                a_priority_normal    => 5,
                a_priority_high      => 5,
                a_run_fail_threshold => 0,
                a_sch_job_comments   => 'Flow Matrix Node processing job for FMN ' || a_fmn_id);

    IF (xdv_logger_pkg.isInfoEnabled(a_log_ctx => v_log_ctx) = TRUE) THEN
      xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                           a_log_msg => 'Created schedule [' || a_sched_name ||
                                        '] and created/scheduled job "'||l_job_name||'".');
    END IF;

    COMMIT;

  EXCEPTION
    WHEN xdv_scheduler_pkg.ex_duplicate_job_schedule THEN
    xdv_logger_pkg.warn( a_log_ctx => v_log_ctx,
                         a_log_msg => 'Unable to schedule job: "' || l_job_name ||
                                      '" with schedule "' || a_sched_name ||
                                      '" : the job is already scheduled.');
  END schedule_job;

--------------------------------------------------------------------

  PROCEDURE schedule_job (
              a_flow_type          IN VARCHAR2,
              a_stage_name         IN VARCHAR2,
              a_granularity        IN VARCHAR2,
              a_force_stop         IN BOOLEAN DEFAULT FALSE,
              a_sched_ivl          IN VARCHAR2 DEFAULT pc_def_sched_ivl,
              a_sched_name         IN VARCHAR2 DEFAULT pc_def_sched_name)
  IS
    l_fmn_id  INTEGER;
  BEGIN
    l_fmn_id := get_fmn_id(a_flow_type, a_stage_name, a_granularity);

    schedule_job (
              a_fmn_id => l_fmn_id,
              a_force_stop => a_force_stop,
              a_sched_ivl => a_sched_ivl,
              a_sched_name => a_sched_name);
  END schedule_job;

--------------------------------------------------------------------

/*=========================*/
/*===== Pkg Main Block ====*/
/*=========================*/
--------------------------------------------------------------------
BEGIN

  start_logger();
  COMMIT;

END xdv_flow_manager_control_pkg;
