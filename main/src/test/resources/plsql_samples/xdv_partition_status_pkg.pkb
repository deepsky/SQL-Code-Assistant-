CREATE OR REPLACE PACKAGE BODY xdv_partition_status_pkg AS

/*=========================*/
/*======= Variables =======*/
/*=========================*/

/*=========================*/
/*==== Private Methods ====*/
/*=========================*/

  FUNCTION obj2jt(a_obj_name  IN VARCHAR2,
                  a_obj_list  IN xdv_generic_const_pkg.tva_id2name )
    RETURN NUMBER
  IS
  BEGIN
    FOR i IN a_obj_list.FIRST..a_obj_list.LAST LOOP
      IF a_obj_list(i) = a_obj_name THEN
        RETURN i;
      END IF;
    END LOOP;

    RETURN -1;
  END obj2jt;

--------------------------------------------------------------------
-- generic procedures
--------------------------------------------------------------------

--------------------------------------------------------------------
  FUNCTION get_part_status_table_name (
              a_object_name        IN VARCHAR2 ) RETURN VARCHAR2 IS
  BEGIN
    RETURN  SUBSTR(UPPER(a_object_name), 1, 11) || pc_part_status_t_suff;
  END get_part_status_table_name;

--------------------------------------------------------------------
  FUNCTION get_config_value (
              a_object_name        IN VARCHAR2,
              a_value_name         IN VARCHAR2,
              a_default_value      IN INTEGER) RETURN INTEGER IS
    l_value   INTEGER;
  BEGIN
    RETURN xdv_config_pkg.get_config_value(pc_pkg_cfg_section, a_value_name, a_object_name, a_default_value);
  END get_config_value;

--------------------------------------------------------------------
  FUNCTION get_stability_delay (
              a_object_name        IN VARCHAR2 ) RETURN INTEGER IS
  BEGIN
    RETURN  get_config_value(a_object_name, pc_stab_delay_name, pc_stab_delay_dflt);
  END get_stability_delay;

--------------------------------------------------------------------
  FUNCTION get_stability_period (
              a_object_name        IN VARCHAR2 ) RETURN INTEGER IS
  BEGIN
    RETURN  get_config_value(a_object_name, pc_stab_period_name, pc_stab_period_dflt);
  END get_stability_period;

--------------------------------------------------------------------
  FUNCTION get_delay_limit (
              a_object_name        IN VARCHAR2 ) RETURN INTEGER IS
  BEGIN
    RETURN  get_config_value(a_object_name, pc_delay_limit_name, pc_delay_limit_dflt);
  END get_delay_limit;

--------------------------------------------------------------------
  PROCEDURE send_part_status_msg(
              a_object_name        IN VARCHAR2,
              a_dde_id             IN INTEGER ) IS
    l_message                xdv_flow_status_payload_tp;
    l_status_table_name      VARCHAR2(32);
    l_enq_msg_cnt            INTEGER;
  BEGIN
    l_message := xdv_flow_status_payload_tp(
                          pc_pkg_name,
                          NULL,
                          NULL,
                          NULL,
                          NULL,
                          NULL,
                          NULL,
                          a_dde_id,
                          NULL,
                          a_dde_id,
                          NULL,
                          'Object name : ' || a_object_name || ', dde_id = ' || a_dde_id || ' partition ready message');

    -- to_do : check first if queue is up and running ... if not check for fmns on rpt enabled. log warn if queue-down but fmn enabled.
    l_enq_msg_cnt := xdv_aq_pkg.enqueue_part_status_msg(
                             a_object_name => a_object_name,
                             a_message     => l_message);

    IF (l_enq_msg_cnt > 0) THEN
      xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section, a_log_msg => 'Sent(enqueued) partition status message for object name = ' || a_object_name || ', dde_id = ' || a_dde_id);

      l_status_table_name := get_part_status_table_name(a_object_name => a_object_name);
      EXECUTE IMMEDIATE 'UPDATE ' || l_status_table_name || ' SET enqueue_time = SYSDATE WHERE dde_id = :dde_id' USING a_dde_id;
    ELSE
      xdv_logger_proxy_pkg.warn(a_log_section => pc_pkg_log_section, a_log_msg => 'Tried to enqueue, but failed (queue may not accept enqueuing at this time), partition status message for object name = ' || a_object_name || ', dde_id = ' || a_dde_id);
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      xdv_logger_proxy_pkg.error(
          a_log_section => pc_pkg_log_section,
          a_log_msg => 'Error sending partition status message for object ' || a_object_name ||
                       ', dde_id = ' || a_dde_id || ': ' || SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512));
  END send_part_status_msg;

--------------------------------------------------------------------
  PROCEDURE process_job (a_object_name IN VARCHAR2 ) IS
    l_stmt        VARCHAR2(16384);
    l_cursor_id   INTEGER;
    l_dde_id      INTEGER;
    l_update_time DATE;
    l_status      INTEGER;

    l_stability_delay       INTEGER;
    l_stability_period      INTEGER;
    l_stability_delay_limit INTEGER;
    l_trm_sdate             DATE;
    l_count                 NUMBER;
    l_xdr_type              VARCHAR2(64);
    l_partition_name        VARCHAR2(30);
    l_count_stmt            VARCHAR2(2048);
    l_span_ivl_sec          INTEGER;

    partition_not_found EXCEPTION;
    PRAGMA EXCEPTION_INIT(partition_not_found, -02149);

  BEGIN

    l_stability_delay       := get_stability_delay(a_object_name);
    l_stability_period      := get_stability_period(a_object_name);
    l_stability_delay_limit := get_delay_limit(a_object_name);

    l_stmt := 'SELECT dde_id, last_update_time FROM ' || get_part_status_table_name(a_object_name) ||
              ' WHERE enqueue_time IS NULL AND (notes IS NULL OR notes NOT LIKE ''Error:%'') AND ' ||
              '( ( (xdv_date_pkg.gmtdate - xdv_date_pkg.sec_to_date(dde_id)) * 1440 - xdv_partition_status_pkg.get_dde_id_ivl_mins() > :stability_delay AND ' ||
              '    (SYSDATE - last_update_time) * 1440 > :stability_period ) ' ||
              '  OR ( (xdv_date_pkg.gmtdate - xdv_date_pkg.sec_to_date(dde_id)) * 1440 - xdv_partition_status_pkg.get_dde_id_ivl_mins() > :stability_delay_limit) )';

    l_span_ivl_sec := xdv_partition_control_pkg.get_span_ivl_sec(xdv_generic_const_pkg.pc_jt2inttbl(obj2jt(a_object_name, xdv_generic_const_pkg.pc_jt2inttbl)));

    l_cursor_id := DBMS_SQL.OPEN_CURSOR;

    DBMS_SQL.PARSE(l_cursor_id, l_stmt, DBMS_SQL.NATIVE);

    DBMS_SQL.DEFINE_COLUMN(l_cursor_id, 1, l_dde_id);
    DBMS_SQL.DEFINE_COLUMN(l_cursor_id, 2, l_update_time);

    DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':stability_delay', l_stability_delay);
    DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':stability_period', l_stability_period);
    DBMS_SQL.BIND_VARIABLE(l_cursor_id, ':stability_delay_limit', l_stability_delay_limit);

    l_status := DBMS_SQL.EXECUTE(l_cursor_id);
    LOOP
      IF DBMS_SQL.FETCH_ROWS(l_cursor_id)>0 THEN
        DBMS_SQL.COLUMN_VALUE(l_cursor_id, 1, l_dde_id);
        DBMS_SQL.COLUMN_VALUE(l_cursor_id, 2, l_update_time);
        l_trm_sdate := sysdate;
        l_xdr_type := xdv_generic_const_pkg.pc_jt2scatagory(obj2jt(a_object_name, xdv_generic_const_pkg.pc_jt2inttbl));
        l_xdr_type := 'SENT_TO_REPORTING_' || UPPER(l_xdr_type);

        l_partition_name := xdv_tablespace_pkg.get_storage_unit_name(a_object_name, xdv_date_pkg.sec_to_date(l_dde_id - l_span_ivl_sec),'_P');
        l_count_stmt := 'SELECT COUNT(*) FROM ' || a_object_name || '  PARTITION(' || l_partition_name || ')';

        BEGIN
          EXECUTE IMMEDIATE l_count_stmt INTO l_count;
          xdv_logger_proxy_pkg.info(
              a_log_section => pc_pkg_log_section,
              a_log_msg => 'Object [' || a_object_name || '] has a partition [' || l_partition_name || '] ready for transport :' ||
                           ' dde_id = ' || l_dde_id || ', last_update_time = ' || to_char(l_update_time, 'MM/DD/YYYY HH24:MI') || ' number of records = ' || l_count);
          send_part_status_msg(a_object_name, l_dde_id);
          xdv_stats_pkg.writeStat(a_stype_name  => l_xdr_type,
                                  a_sdate_start => l_trm_sdate,
                                  a_sdate_end   => sysdate,
                                  a_stat_value  => l_count,
                                  a_auto_trans  => TRUE);
          COMMIT;

        EXCEPTION
          WHEN partition_not_found THEN
            xdv_logger_proxy_pkg.error(
              a_log_section => pc_pkg_log_section,
              a_log_msg => 'Partition does not exist error was encountered processing partition status for [' ||
                           a_object_name || '] partition [' || l_partition_name || '] - ' ||
                           SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512));
            EXECUTE IMMEDIATE 'UPDATE ' || get_part_status_table_name(a_object_name) || ' SET NOTES=:1 WHERE dde_id=:2'
              USING 'Error: Partition [' || l_partition_name || '] does not exist', l_dde_id;
            COMMIT;
          WHEN OTHERS THEN
            xdv_logger_proxy_pkg.error(
              a_log_section => pc_pkg_log_section,
              a_log_msg => 'An error was encountered processing partition status for [' ||
                           a_object_name || '] partition [' || l_partition_name || '] - ' ||
                           SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512));
            ROLLBACK;
        END;
      ELSE
        EXIT;
      END IF;
    END LOOP;

    DBMS_SQL.CLOSE_CURSOR(l_cursor_id);
  EXCEPTION
    WHEN OTHERS THEN
      xdv_logger_proxy_pkg.error(
          a_log_section => pc_pkg_log_section,
          a_log_msg => 'An error was encountered processing partition status for [' ||
                       a_object_name || '] - ' || SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512));
      IF(l_cursor_id IS NOT NULL) THEN
        DBMS_SQL.CLOSE_CURSOR(l_cursor_id);
      END IF;

      RAISE;

  END process_job;


  FUNCTION get_dde_id_ivl_mins RETURN NUMBER
  IS
  BEGIN
    RETURN xdv_generic_const_pkg.pc_dde_ivl / 60;
  END;
--------------------------------------------------------------------
-- schedule specific procedures
--------------------------------------------------------------------

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

    IF (xdv_logger_proxy_pkg.isDebugEnabled(a_log_section => pc_pkg_log_section) = TRUE) THEN
      xdv_logger_proxy_pkg.debug( a_log_section => pc_pkg_log_section,
                            a_log_msg => 'Created schedule [' || a_sched_name || '] schedule-interval [' ||
                                         a_sched_ivl || '].');
    END IF;

  EXCEPTION
    WHEN xdv_generic_exceptions_pkg.ex_duplicate_schedule_name THEN
      xdv_logger_proxy_pkg.warn( a_log_section => pc_pkg_log_section,
                           a_log_msg => 'Unable to create schedule "'|| a_sched_name ||'": already exists.');
  END create_schedule;

------------------------------------------------------------------

  PROCEDURE drop_schedule           (
                a_sched_name         IN VARCHAR2,
                a_force_drop         IN BOOLEAN) IS
  BEGIN

    xdv_scheduler_pkg.drop_schedule(a_sched_name, a_force_drop);
    IF (xdv_logger_proxy_pkg.isDebugEnabled(a_log_section => pc_pkg_log_section) = TRUE) THEN
      xdv_logger_proxy_pkg.debug( a_log_section => pc_pkg_log_section, a_log_msg => 'Dropped schedule [' || a_sched_name || '].');
    END IF;

  EXCEPTION
    WHEN xdv_generic_exceptions_pkg.ex_invalid_schedule_name        THEN
      xdv_logger_proxy_pkg.warn( a_log_section => pc_pkg_log_section, a_log_msg => 'Unable to drop schedule: ' || a_sched_name || ' - doesn''t exist.');
    WHEN xdv_generic_exceptions_pkg.ex_object_dependents_detected THEN
      xdv_logger_proxy_pkg.warn( a_log_section => pc_pkg_log_section, a_log_msg => 'Unable to drop schedule: ' || a_sched_name || ' - other objects depend on it.');
  END drop_schedule;

--------------------------------------------------------------------


--------------------------------------------------------------------
-- job specific procedures
--------------------------------------------------------------------

--------------------------------------------------------------------
  /*
    old-school since 2009/07 :)
    should use xdv_scheduler_pkg.get_job_name_by_table

    this is what gets generated now

       pstat on table              old pstat job name                new pstat job name
       XDV_AMI_TRN_INTMES_FT    -> XDV_100088_AMI_TRN_INTMES_FT_P -> XDJ_PUR_AMI_TRN_INTMES_F0088
  */
  FUNCTION get_job_name_old (a_object_name VARCHAR2) RETURN VARCHAR2 IS
  BEGIN
	  RETURN UPPER(SUBSTR(a_object_name, 5, 7) || pc_job_name_body);
  END get_job_name_old;

--------------------------------------------------------------------
  FUNCTION create_job (
          a_object_name         IN VARCHAR2) RETURN VARCHAR2 IS
    l_job_id          INTEGER;
    rv_job_name       VARCHAR2(64);
  BEGIN

    -- build job name
    rv_job_name := xdv_scheduler_pkg.get_job_name_by_table(
                          a_table_name    => a_object_name,
                          a_job_categ_hdr => xdv_scheduler_pkg.pc_job_cat_hdr_pstat);

    l_job_id :=
          xdv_scheduler_pkg.create_job(
              a_job_categ           => xdv_scheduler_pkg.pc_job_cat_trans_facts,
              a_job_name            => rv_job_name,
              a_job_action          => pc_def_job_stp,
              a_job_resources       => NULL,
              a_job_comments        => 'XDV partition status job for ' || a_object_name);

    IF (xdv_logger_proxy_pkg.isDebugEnabled(a_log_section => pc_pkg_log_section) = TRUE) THEN
      xdv_logger_proxy_pkg.debug( a_log_section => pc_pkg_log_section, a_log_msg => 'Created job "' || rv_job_name || '".');
    END IF;

    RETURN rv_job_name;

  EXCEPTION
  WHEN OTHERS THEN
    xdv_logger_proxy_pkg.warn( a_log_section => pc_pkg_log_section, a_log_msg => 'Unable to create job: ' || SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512));
    RETURN NULL;

  END create_job;

--------------------------------------------------------------------
  PROCEDURE drop_job (
              a_object_name        IN VARCHAR2,
              a_force_drop         IN BOOLEAN) IS
    l_job_name VARCHAR2(64);
  BEGIN

    l_job_name := xdv_scheduler_pkg.get_job_name_by_table(
                          a_table_name    => a_object_name,
                          a_job_categ_hdr => xdv_scheduler_pkg.pc_job_cat_hdr_pstat);

    xdv_scheduler_pkg.drop_job(l_job_name, a_force_drop);

    IF (xdv_logger_proxy_pkg.isDebugEnabled(a_log_section => pc_pkg_log_section) = TRUE) THEN
      xdv_logger_proxy_pkg.debug( a_log_section => pc_pkg_log_section, a_log_msg => 'Dropped job ['||l_job_name||'].');
    END IF;

  EXCEPTION
    WHEN xdv_generic_exceptions_pkg.ex_invalid_job_name        THEN
      xdv_logger_proxy_pkg.warn( a_log_section => pc_pkg_log_section, a_log_msg => 'Unable to drop job: ' || l_job_name || ' - doesn''t exist.');
    WHEN xdv_generic_exceptions_pkg.ex_object_dependents_detected THEN
      xdv_logger_proxy_pkg.warn( a_log_section => pc_pkg_log_section, a_log_msg => 'Unable to drop job: ' || l_job_name || ' - other objects depend on it.');
  END drop_job;


--------------------------------------------------------------------
-- public procedures
--------------------------------------------------------------------

--------------------------------------------------------------------
  PROCEDURE unschedule_job (
              a_object_name        IN VARCHAR2,
              a_force_stop         IN BOOLEAN DEFAULT FALSE,
              a_sched_name         IN VARCHAR2 DEFAULT pc_def_sched_name)
  IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_job_name         VARCHAR2(64);
  BEGIN
    l_job_name := xdv_scheduler_pkg.get_job_name_by_table(
                          a_table_name    => a_object_name,
                          a_job_categ_hdr => xdv_scheduler_pkg.pc_job_cat_hdr_pstat);

    BEGIN
      xdv_scheduler_pkg.unschedule_job(
                a_job_name   => l_job_name,
                a_sch_name   => a_sched_name,
                a_force_stop => a_force_stop);
    EXCEPTION
      WHEN OTHERS THEN
        xdv_logger_proxy_pkg.warn( a_log_section => pc_pkg_log_section,
                             a_log_msg => 'Unable to unschedule job: "' || l_job_name ||
                                          '" with schedule "' || a_sched_name ||'" : ' ||
                                          SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512));
    END;

    drop_job (a_object_name => a_object_name, a_force_drop => a_force_stop);

    --drop_schedule(a_sched_name, a_force_stop);

    IF (xdv_logger_proxy_pkg.isInfoEnabled(a_log_section => pc_pkg_log_section) = TRUE) THEN
      xdv_logger_proxy_pkg.info(
            a_log_section => pc_pkg_log_section,
            a_log_msg => 'Dropped schedule [' || a_sched_name || '] and job [' || a_object_name ||
                         '], with force_stop [' || (CASE WHEN a_force_stop = TRUE THEN 'ON' ELSE 'OFF' END) || '].');
    END IF;

    COMMIT;

  END unschedule_job;

--------------------------------------------------------------------

  PROCEDURE schedule_job (
              a_object_name        IN VARCHAR2,
              a_force_stop         IN BOOLEAN DEFAULT FALSE,
              a_sched_ivl          IN VARCHAR2 DEFAULT pc_def_sched_ivl,
              a_sched_name         IN VARCHAR2 DEFAULT pc_def_sched_name)
  IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_job_name     VARCHAR2(64);
    l_job_args     xdv_scheduler_pkg.tn_job_arguments;
    l_sch_job_id   INTEGER;
    l_alloc_adv    INTEGER;
  BEGIN

    -- clean current job ... if any
    unschedule_job(a_object_name => a_object_name, a_force_stop => a_force_stop, a_sched_name => a_sched_name);

    -- schedule job now
    create_schedule(a_sched_name => a_sched_name, a_sched_ivl => a_sched_ivl);

    l_job_name := create_job(a_object_name => a_object_name);

    l_job_args := xdv_scheduler_pkg.tn_job_arguments();
    l_job_args.EXTEND(1);

    l_job_args(1).a_arg_name := 'a_object_name';
    l_job_args(1).a_arg_data_type := 'VARCHAR2';
    l_job_args(1).a_arg_value := a_object_name;

    l_sch_job_id :=
         xdv_scheduler_pkg.schedule_job (
                a_job_name           => l_job_name,
                a_sch_name           => a_sched_name,
                a_sch_job_name       => l_job_name,
                a_date_start         => SYSTIMESTAMP,
                a_sch_job_arguments  => l_job_args,
                a_priority_normal    => 5,
                a_priority_high      => 5,
                a_run_fail_threshold => 0,
                a_sch_job_comments   => 'DD partition status job for ' || a_object_name);

    IF (xdv_logger_proxy_pkg.isInfoEnabled(a_log_section => pc_pkg_log_section) = TRUE) THEN
      xdv_logger_proxy_pkg.info( a_log_section => pc_pkg_log_section,
                           a_log_msg => 'Created schedule [' || a_sched_name ||
                                        '] and created/scheduled job "'||l_job_name||'".');
    END IF;

    COMMIT;

  EXCEPTION
    WHEN xdv_generic_exceptions_pkg.ex_duplicate_job_schedule THEN
    xdv_logger_proxy_pkg.warn( a_log_section => pc_pkg_log_section,
                         a_log_msg => 'Unable to schedule job: "' || l_job_name ||
                                      '" with schedule "' || a_sched_name ||
                                      '" : the job is already scheduled.');
  END schedule_job;

--------------------------------------------------------------------

/*=========================*/
/*===== Pkg Main Block ====*/
/*=========================*/
--------------------------------------------------------------------

END xdv_partition_status_pkg;