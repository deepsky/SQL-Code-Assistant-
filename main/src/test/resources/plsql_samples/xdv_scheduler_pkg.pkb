--prompt Creating Package Body 'xdv_scheduler_pkg'

CREATE OR REPLACE PACKAGE BODY xdv_scheduler_pkg
AS

  TYPE id_array_t IS TABLE OF NUMBER;

--------------------------------------------------------------------
-- logger package specific methods
--------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION can_lock_resource0(
                a_sch_job_run_id IN INTEGER,
                a_res_id         IN INTEGER,
                a_lock_type_id   IN INTEGER) RETURN BOOLEAN IS
    l_shared_locks_cnt  INTEGER;
    l_shared_lock_type  INTEGER;
    l_exclsv_locks_cnt  INTEGER;
    l_exclsv_locks_type INTEGER;
    l_any_locks_cnt     INTEGER;
  BEGIN

    l_shared_lock_type := check_lock_type(pc_lock_type_shrd);
    l_exclsv_locks_type := check_lock_type(pc_lock_type_excl);

    -- first check if the resource is already locked
    SELECT COUNT(*) INTO l_shared_locks_cnt
    FROM xdv_sch_locked_res_t
    WHERE res_id = a_res_id AND
          srq_id != a_sch_job_run_id AND
          lty_id = l_shared_lock_type AND
          pending = pc_lock_status_locked;

    SELECT COUNT(*) INTO l_exclsv_locks_cnt
    FROM xdv_sch_locked_res_t
    WHERE res_id = a_res_id AND
          srq_id != a_sch_job_run_id AND
          lty_id = l_exclsv_locks_type AND
          pending = pc_lock_status_locked;

    IF (a_lock_type_id = l_exclsv_locks_type) THEN
      IF (l_shared_locks_cnt > 0 OR l_exclsv_locks_cnt > 0) THEN
        RETURN FALSE;
      END IF;
    ELSE
      IF (l_exclsv_locks_cnt > 0) THEN
        RETURN FALSE;
      END IF;
    END IF;

    -- the resource is not locked or can be shared
    -- check if there are other pending jobs with a higher priority

    IF (a_lock_type_id = l_exclsv_locks_type) THEN

      SELECT COUNT(*) INTO l_any_locks_cnt
      FROM xdv_sch_locked_res_t lock_t
      JOIN xdv_sch_job_run_queue_t job_run_queue_t ON
        job_run_queue_t.id = a_sch_job_run_id
      JOIN xdv_sch_job_run_queue_t other_job_run_queue_t ON
        other_job_run_queue_t.id = lock_t.srq_id
      WHERE
        lock_t.res_id = a_res_id AND
        lock_t.srq_id != a_sch_job_run_id AND
        lock_t.pending = pc_lock_status_pending AND
        job_run_queue_t.priority < other_job_run_queue_t.priority;

      IF (l_any_locks_cnt > 0) THEN
        RETURN FALSE;
      ELSE
        RETURN TRUE;
      END IF;

    ELSE

      SELECT COUNT(*) INTO l_exclsv_locks_cnt
      FROM xdv_sch_locked_res_t lock_t
      JOIN xdv_sch_job_run_queue_t job_run_queue_t ON
        job_run_queue_t.id = a_sch_job_run_id
      JOIN xdv_sch_job_run_queue_t other_job_run_queue_t ON
        other_job_run_queue_t.id = lock_t.srq_id
      WHERE
        lock_t.res_id = a_res_id AND
        lock_t.srq_id != a_sch_job_run_id AND
        lock_t.lty_id = l_exclsv_locks_type AND
        lock_t.pending = pc_lock_status_pending AND
        job_run_queue_t.priority < other_job_run_queue_t.priority;

      IF (l_exclsv_locks_cnt > 0) THEN
        RETURN FALSE;
      ELSE
        RETURN TRUE;
      END IF;

    END IF;
  END can_lock_resource0;
  ------------------------------------------------------------------

--------------------------------------------------------------------
  /**
  */
  PROCEDURE start_logger(a_proc_name     VARCHAR2 DEFAULT 'xdv_scheduler')
  IS
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
                   a_dbms_output_lsize => 80,
                   a_dbms_output_headr => TRUE
                   );
    xdv_logger_pkg.info(a_log_ctx => v_log_ctx, a_log_msg => 'Logger initialized ...');
  END start_logger;

--------------------------------------------------------------------
  /**
  */
  PROCEDURE stop_logger(pProcName     VARCHAR2 DEFAULT 'xdv_scheduler')
  IS
  BEGIN
    xdv_logger_pkg.info(a_log_ctx => v_log_ctx, a_log_msg => 'Logger closed.');
    xdv_logger_pkg.clearContext(v_log_ctx);
  END stop_logger;


--------------------------------------------------------------------
-- generic procedures
--------------------------------------------------------------------

--------------------------------------------------------------------
  /**
  */
  FUNCTION  build_valid_obj_name  (
              a_prefix               IN VARCHAR2,
              a_object_name          IN VARCHAR2,
              a_postfix              IN VARCHAR2) RETURN VARCHAR2 IS
    rv_vobj_name       VARCHAR2(30);
    l_prefix           VARCHAR2(64);
    l_postfix          VARCHAR2(64);
    l_object_name      VARCHAR2(64);
    l_pre_post_len     NUMBER;
  BEGIN

    l_prefix       := CASE WHEN a_prefix      IS NOT NULL THEN a_prefix      ELSE '' END;
    l_postfix      := CASE WHEN a_postfix     IS NOT NULL THEN a_postfix     ELSE '' END;
    l_object_name  := CASE WHEN a_object_name IS NOT NULL THEN a_object_name ELSE '' END;

    l_pre_post_len := NVL(LENGTH(l_prefix), 0) + NVL(LENGTH(l_postfix), 0);

    IF (l_pre_post_len > 30) THEN
      RAISE ex_invalid_schedule_name;
    END IF;

    rv_vobj_name := l_prefix || SUBSTR(UPPER(l_object_name), 1, 30 - l_pre_post_len) || l_postfix;
    RETURN rv_vobj_name;

  /*EXCEPTION
    WHEN OTHERS THEN
      -- todo : raise something
      RETURN NULL;*/
  END build_valid_obj_name;



  /** Checks a lock type. Returns internal id or NULL if no such lock type */

  FUNCTION  check_lock_type                (
                a_lock_type_name            IN VARCHAR2) RETURN NUMBER IS
    l_lty_id    INTEGER;
  BEGIN

    SELECT
      id INTO l_lty_id
    FROM
      xdv_sch_lock_type_t
    WHERE
      name = a_lock_type_name;

    RETURN l_lty_id;

    EXCEPTION
    WHEN NO_DATA_FOUND
      THEN RETURN NULL;

  END check_lock_type;


--------------------------------------------------------------------
-- job resource specific procedures
--------------------------------------------------------------------

  ------------------------------------------------------------------
  /** Creates a job resource. Returns internal id */
  FUNCTION  create_job_resource            (
                a_job_resource_name         IN VARCHAR2,
                a_job_resource_comments     IN VARCHAR2 DEFAULT NULL) RETURN INTEGER IS
    l_job_res_id    INTEGER;
  BEGIN
    l_job_res_id := check_job_resource(a_job_resource_name);

    IF (l_job_res_id >= 0) THEN
      RAISE ex_duplicate_resource_name;
    END IF;

    SELECT xdv_sch_jre_seq.NEXTVAL INTO l_job_res_id FROM DUAL;

    INSERT INTO xdv_sch_job_resource_t
      (id, name, comments)
    VALUES
      (l_job_res_id, a_job_resource_name, a_job_resource_comments);

    RETURN l_job_res_id;

  END create_job_resource;
  ------------------------------------------------------------------


  ------------------------------------------------------------------
  /** Checks a job resource. Returns internal id or NULL if no such resource */
  FUNCTION  check_job_resource             (
                a_job_resource_name         IN VARCHAR2) RETURN NUMBER IS
    l_job_res_id    INTEGER;
  BEGIN

    SELECT
      id INTO l_job_res_id
    FROM
      xdv_sch_job_resource_t
    WHERE
      name = a_job_resource_name;

    RETURN l_job_res_id;

    EXCEPTION
    WHEN NO_DATA_FOUND
      THEN RETURN NULL;

  END check_job_resource;
  ------------------------------------------------------------------

--------------------------------------------------------------------
-- job category specific procedures
--------------------------------------------------------------------

  ------------------------------------------------------------------
  /** Creates a job category. Returns internal job category id */
  FUNCTION  create_job_category            (
                a_job_category_name         IN VARCHAR2,
                a_job_category_comments     IN VARCHAR2 DEFAULT NULL) RETURN INTEGER IS
    l_job_cat_id    INTEGER;
  BEGIN
    l_job_cat_id := check_job_category(a_job_category_name);

    IF (l_job_cat_id >= 0) THEN
      RAISE ex_duplicate_job_category_name;
    END IF;

    SELECT xdv_sch_jbc_seq.NEXTVAL INTO l_job_cat_id FROM DUAL;

    INSERT INTO xdv_sch_job_categ_t
      (id, name, comments)
    VALUES
      (l_job_cat_id, a_job_category_name, a_job_category_comments);

    RETURN l_job_cat_id;

  END create_job_category;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /** Checks a job category. Returns internal job id or NULL if no such category */
  FUNCTION  check_job_category             (
                a_job_category_name                  IN VARCHAR2) RETURN NUMBER IS
    l_job_cat_id    INTEGER;
  BEGIN

    SELECT
      id INTO l_job_cat_id
    FROM
      xdv_sch_job_categ_t
    WHERE
      name = a_job_category_name;

    RETURN l_job_cat_id;

    EXCEPTION
    WHEN NO_DATA_FOUND
      THEN RETURN NULL;

  END check_job_category;
  ------------------------------------------------------------------


--------------------------------------------------------------------
-- job category specific procedures
--------------------------------------------------------------------

  ------------------------------------------------------------------
  /** Creates a schedule category. Returns internal job id */
  FUNCTION  create_sch_category            (
                a_sch_category_name         IN VARCHAR2,
                a_sch_category_comments     IN VARCHAR2 DEFAULT NULL) RETURN INTEGER IS
    l_sch_cat_id    INTEGER;
  BEGIN
    l_sch_cat_id := check_sch_category(a_sch_category_name);

    IF (l_sch_cat_id >= 0) THEN
      RAISE ex_duplicate_sch_category_name;
    END IF;

    SELECT xdv_sch_scc_seq.NEXTVAL INTO l_sch_cat_id FROM DUAL;

    INSERT INTO xdv_sch_sched_categ_t
      (id, name, comments)
    VALUES
      (l_sch_cat_id, a_sch_category_name, a_sch_category_comments);

    RETURN l_sch_cat_id;

  END create_sch_category;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /** Checks a schedule category. Returns internal schedule category id or NULL if no such category */
  FUNCTION  check_sch_category             (
                a_sch_category_name         IN VARCHAR2) RETURN NUMBER IS
    l_sch_cat_id    INTEGER;
  BEGIN

    SELECT
      id INTO l_sch_cat_id
    FROM
      xdv_sch_sched_categ_t
    WHERE
      name = a_sch_category_name;

    RETURN l_sch_cat_id;

    EXCEPTION
    WHEN NO_DATA_FOUND
      THEN RETURN NULL;

  END check_sch_category;
  ------------------------------------------------------------------


--------------------------------------------------------------------
-- job specific procedures
--------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  add_resource_to_job                     (
                a_job_id                    IN INTEGER,
                a_job_resource              IN r_job_resource) IS

    l_rec_id        INTEGER;
    l_res_id        INTEGER;
    l_lty_id        INTEGER;

  BEGIN

    l_res_id := check_job_resource(a_job_resource.a_resource_name);

    IF (l_res_id IS NULL) THEN
      RAISE ex_invalid_resource_name;
    END IF;

    l_lty_id := check_lock_type(a_job_resource.a_lock_type_name);

    IF (l_lty_id IS NULL) THEN
      RAISE ex_invalid_lock_type_name;
    END IF;

    SELECT xdv_sch_sjb_seq.NEXTVAL INTO l_rec_id FROM DUAL;

    INSERT INTO xdv_sch_job_resources_t
      (id, job_id, res_id, lty_id, comments)
    VALUES
      (l_rec_id, a_job_id, l_res_id, l_lty_id, a_job_resource.a_comments);

  END add_resource_to_job;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* Creates a job. Returns internal job id */
  FUNCTION  create_job                     (
                a_job_categ                 IN VARCHAR2,
                a_job_name                  IN VARCHAR2,
                a_job_action                IN VARCHAR2,
                a_job_resources             IN tn_job_resources DEFAULT NULL,
                a_job_comments              IN VARCHAR2 DEFAULT NULL,
                a_forced_recreate           IN BOOLEAN DEFAULT FALSE) RETURN INTEGER IS

    l_cat_id        INTEGER;
    l_job_id        INTEGER;

  BEGIN

    l_cat_id := check_job_category(a_job_categ);

    IF (l_cat_id IS NULL) THEN
        RAISE ex_invalid_job_category_name;
    END IF;

    l_job_id := check_job(a_job_name);

    IF (l_job_id IS NOT NULL) THEN
        IF (a_forced_recreate) THEN
          drop_job(a_job_name, TRUE);
        ELSE
          RAISE ex_duplicate_job_name;
        END IF;
    END IF;

    SELECT xdv_sch_sjb_seq.NEXTVAL INTO l_job_id FROM DUAL;

    INSERT INTO xdv_sch_job_t
      (id, cat_id, name, procedure_name, comments)
    VALUES
      (l_job_id, l_cat_id, a_job_name, a_job_action, a_job_comments);

    IF(a_job_resources IS NOT NULL) THEN
      FOR l_job_res_idx IN a_job_resources.FIRST .. a_job_resources.LAST LOOP
        add_resource_to_job(a_job_id => l_job_id,
                            a_job_resource => a_job_resources(l_job_res_idx));

      END LOOP;
    END IF;

    RETURN l_cat_id;

  END create_job;
  ------------------------------------------------------------------

  PROCEDURE  drop_scheduled_job (
                a_sch_job_id    INTEGER,
                a_force_stop    IN BOOLEAN DEFAULT FALSE);

  ------------------------------------------------------------------
  /* Drops a job. */
  PROCEDURE  drop_job                      (
                a_job_name                  IN VARCHAR2,
                a_forced_drop               IN BOOLEAN DEFAULT FALSE)
  IS
    l_job_id    INTEGER;
    TYPE sch_job_ids_t IS TABLE OF NUMBER;
    l_sch_job_ids sch_job_ids_t;
    l_err_msg        VARCHAR2(2000);
  BEGIN

    l_job_id := check_job(a_job_name);

    IF (l_job_id IS NULL) THEN
      RAISE ex_invalid_job_name;
    END IF;

    BEGIN
      SELECT id BULK COLLECT INTO l_sch_job_ids
      FROM xdv_sch_sch_job_t
      WHERE job_id = l_job_id;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        NULL;
    END;

    IF ( l_sch_job_ids IS NOT NULL AND l_sch_job_ids.COUNT) >0 THEN

      IF (a_forced_drop = FALSE) THEN
        xdv_logger_pkg.error(
            a_log_ctx => v_log_ctx,
            a_log_msg => 'Unable to drop job :"'|| a_job_name ||'": dependent objects exist.');
        RAISE ex_object_dependents_detected;
      END IF;

      FOR i IN l_sch_job_ids.FIRST .. l_sch_job_ids.LAST
      LOOP
        drop_scheduled_job(l_sch_job_ids(i));
      END LOOP;

    END IF;

    DELETE FROM xdv_sch_job_resources_t WHERE job_id = l_job_id;

    DELETE FROM xdv_sch_job_t WHERE id = l_job_id;

    EXCEPTION
    WHEN NO_DATA_FOUND
      THEN RAISE ex_invalid_job_name;
    -- check ORA-02292: integrity constraint (XDVINT.XDV_SCJ_SJB_FK) violated - child record found
    WHEN ex_invalid_job_name THEN
      RAISE;
    WHEN OTHERS THEN
      l_err_msg := 'Unable to drop job : ' || a_job_name || ' - other issues : ' ||
                   SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512);
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx, a_log_msg => l_err_msg);

      RAISE_APPLICATION_ERROR(pc_scheduler_application_error, l_err_msg);

  END drop_job;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* Checks a job. Returns internal job id or NULL if no such job */
  FUNCTION  check_job                      (
                a_job_name                  IN VARCHAR2) RETURN NUMBER IS
    l_job_id    INTEGER;
  BEGIN

    SELECT
      id INTO l_job_id
    FROM
      xdv_sch_job_t
    WHERE
      name = a_job_name;

    RETURN l_job_id;

    EXCEPTION
    WHEN NO_DATA_FOUND
      THEN RETURN NULL;

  END check_job;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* Returns a list of job names wich match a specified SQL style mask */
  FUNCTION  get_matched_jobs               (
                a_job_name_mask             IN VARCHAR2) RETURN tn_name_arr IS
    l_job_names   tn_name_arr := tn_name_arr();
  BEGIN

    BEGIN
      SELECT name BULK COLLECT INTO l_job_names
      FROM xdv_sch_job_t
      WHERE name like a_job_name_mask;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        NULL;
    END;

    RETURN l_job_names;
  END get_matched_jobs;
  ------------------------------------------------------------------

--------------------------------------------------------------------
-- schedule specific procedures
--------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION  get_dbms_schedule_name         (
                a_sch_id                    IN INTEGER,
                a_sch_name                  IN VARCHAR2) RETURN VARCHAR2 IS
    l_dbms_sch_name   VARCHAR2(256);
  BEGIN
    l_dbms_sch_name := 'xdv_' || a_sch_id || '_' || a_sch_name;
    IF (LENGTH(l_dbms_sch_name) > 30) THEN
      RETURN SUBSTR(l_dbms_sch_name, 1, 30);
    ELSE
      RETURN l_dbms_sch_name;
    END IF;
  END get_dbms_schedule_name;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* Creates a schedule. Returns internal schedule id */
  FUNCTION  create_schedule                (
                a_sch_categ                 IN VARCHAR2,
                a_sch_name                  IN VARCHAR2,
                a_sch_interval              IN VARCHAR2,
                a_sch_comments              IN VARCHAR2 DEFAULT NULL,
                a_forced_recreate           IN BOOLEAN DEFAULT FALSE) RETURN INTEGER IS

    l_cat_id        INTEGER;
    l_sch_id        INTEGER;
    l_err_msg       VARCHAR2(2000);
    l_dbms_sch_name VARCHAR2(65);
  BEGIN

    xdv_logger_pkg.info( a_log_ctx => v_log_ctx, a_log_msg => 'Creating schedule "'|| a_sch_name ||'".');

    l_cat_id := check_sch_category(a_sch_categ);

    IF (l_cat_id IS NULL) THEN
        RAISE ex_invalid_sch_category_name;
    END IF;

    l_sch_id := check_schedule(a_sch_name);

    IF (l_sch_id IS NOT NULL) THEN
        IF (a_forced_recreate) THEN
          drop_schedule(a_sch_name, TRUE);
        ELSE
          RAISE ex_duplicate_schedule_name;
        END IF;
    END IF;

    SELECT xdv_sch_scd_seq.NEXTVAL INTO l_sch_id FROM DUAL;

    INSERT INTO xdv_sch_sched_t
      (id, cat_id, name, sch_interval, comments)
    VALUES
      (l_sch_id, l_cat_id, a_sch_name, a_sch_interval, a_sch_comments);

    l_dbms_sch_name := get_dbms_schedule_name(l_sch_id, a_sch_name);

    xdv_logger_pkg.info( a_log_ctx => v_log_ctx, a_log_msg => 'Creating DBMS schedule "'|| l_dbms_sch_name ||'".');

    DBMS_SCHEDULER.CREATE_SCHEDULE(
           schedule_name   => l_dbms_sch_name,
           start_date      => NULL,
           repeat_interval => a_sch_interval,
           end_date        => NULL,
           comments        => CASE WHEN a_sch_comments IS NULL THEN a_sch_name || ' schedule' ELSE a_sch_comments END);

    RETURN l_sch_id;

    EXCEPTION
    WHEN ex_object_already_exists THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to create schedule "'|| a_sch_name ||'" already exists.');
      RAISE ex_duplicate_schedule_name;

    WHEN ex_invalid_sch_category_name OR ex_duplicate_schedule_name THEN

      RAISE;

    WHEN OTHERS THEN
      l_err_msg := 'Unable to create schedule: ' || a_sch_name || ' - other issues : ' ||
                   SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512);
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx, a_log_msg => l_err_msg);

      RAISE_APPLICATION_ERROR(pc_scheduler_application_error, l_err_msg);

  END create_schedule;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* Checks a schedule. Returns internal job id or NULL if no such schedule */
  FUNCTION  check_schedule                 (
                a_sch_name                IN VARCHAR2) RETURN NUMBER IS
    l_sch_id    INTEGER;
  BEGIN

    SELECT
      id INTO l_sch_id
    FROM
      xdv_sch_sched_t
    WHERE
      name = a_sch_name;

    RETURN l_sch_id;

    EXCEPTION
    WHEN NO_DATA_FOUND
      THEN RETURN NULL;

  END check_schedule;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* Drops a schedule. */
  PROCEDURE  drop_schedule                  (
                a_sch_name                  IN VARCHAR2,
                a_forced_drop               IN BOOLEAN DEFAULT FALSE) IS
    l_sch_id         INTEGER;
    TYPE sch_job_ids_t IS TABLE OF NUMBER;
    l_sch_job_ids sch_job_ids_t;
    l_dbms_sch_name  VARCHAR2(128);
    l_err_msg        VARCHAR2(2000);
  BEGIN
    l_sch_id := check_schedule(a_sch_name);
    IF(l_sch_id IS NULL) THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Unable to drop schedule: ' || a_sch_name || ' - doesn''t exist.');
      RAISE ex_invalid_schedule_name;
    END IF;

    BEGIN
      SELECT id BULK COLLECT INTO l_sch_job_ids
      FROM xdv_sch_sch_job_t
      WHERE sch_id = l_sch_id;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        NULL;
    END;

    IF (l_sch_job_ids IS NOT NULL AND l_sch_job_ids.COUNT > 0) THEN

      IF (a_forced_drop = FALSE) THEN
        xdv_logger_pkg.error(
            a_log_ctx => v_log_ctx,
            a_log_msg => 'Unable to drop schedule :"'|| a_sch_name ||'": dependent objects exist.');
        RAISE ex_object_dependents_detected;
      END IF;

      FOR i IN l_sch_job_ids.FIRST .. l_sch_job_ids.LAST
      LOOP
        drop_scheduled_job(l_sch_job_ids(i));
      END LOOP;

    END IF;

    DELETE FROM xdv_sch_sched_t WHERE id = l_sch_id;

    l_dbms_sch_name := get_dbms_schedule_name(l_sch_id, a_sch_name);
    DBMS_SCHEDULER.DROP_SCHEDULE(
          schedule_name => l_dbms_sch_name);
          -- TODO force         => a_force_stop);

    EXCEPTION
    WHEN NO_DATA_FOUND THEN

      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Unable to drop schedule: ' || a_sch_name || ' - doesn''t exist.');
      RAISE ex_invalid_schedule_name;

    WHEN ex_non_existing_object THEN

      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Unable to drop DBMS_SCHEDULER schedule: ' || l_dbms_sch_name || ' - doesn''t exist.');
      RAISE ex_invalid_schedule_name;

    WHEN ex_object_dependents_detected THEN

      l_err_msg := 'Unable to drop DBMS_SCHEDULER schedule: ' || l_dbms_sch_name || ' - other objects depend on it.';
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx, a_log_msg => l_err_msg);

      RAISE_APPLICATION_ERROR(pc_scheduler_application_error, l_err_msg);

    WHEN ex_invalid_schedule_name THEN

      RAISE;

    WHEN OTHERS THEN
      l_err_msg := 'Unable to drop schedule: ' || a_sch_name || ' - other issues : ' ||
                   SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512);
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx, a_log_msg => l_err_msg);

      RAISE_APPLICATION_ERROR(pc_scheduler_application_error, l_err_msg);

  END drop_schedule;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* Returns a list of schedule names wich match a specified SQL style mask */
  FUNCTION  get_matched_schedules           (
                a_sch_name_mask             IN VARCHAR2) RETURN tn_name_arr IS
    l_sch_names   tn_name_arr := tn_name_arr();
  BEGIN

    BEGIN
      SELECT name BULK COLLECT INTO l_sch_names
      FROM xdv_sch_sched_t
      WHERE name like a_sch_name_mask;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        NULL;
    END;

    RETURN l_sch_names;
  END get_matched_schedules;
  ------------------------------------------------------------------


--------------------------------------------------------------------
-- job scheduling specific procedures
--------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION  get_dbms_job_name         (
                a_sch_job_id                IN INTEGER,
                a_sch_job_name              IN VARCHAR2) RETURN VARCHAR2 IS
  BEGIN
    RETURN SUBSTR('xdv_' || a_sch_job_id || '_' || a_sch_job_name, 1, 30);
  END get_dbms_job_name;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  add_scheduled_job_argument     (
                a_sch_job_id                IN INTEGER,
                a_job_argument              IN r_job_argument,
                a_arg_position              IN INTEGER) IS
    l_rec_id        INTEGER;
  BEGIN

    SELECT xdv_sch_sja_seq.NEXTVAL INTO l_rec_id FROM DUAL;

    INSERT INTO xdv_sch_job_args_t
      (id, sjb_id, arg_position, arg_name,
       arg_data_type, arg_value)
    VALUES
      (l_rec_id, a_sch_job_id, a_arg_position, a_job_argument.a_arg_name,
       a_job_argument.a_arg_data_type, a_job_argument.a_arg_value);

  END add_scheduled_job_argument;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* Schedules a job. Returns internal scheduled job id */
  FUNCTION  schedule_job                   (
                a_job_name                  IN VARCHAR2,
                a_sch_name                  IN VARCHAR2,
                a_sch_job_name              IN VARCHAR2,
                a_date_start                IN DATE DEFAULT SYSDATE,
                a_date_end                  IN DATE DEFAULT NULL,
                a_sch_job_arguments         IN tn_job_arguments,
                a_priority_normal           IN INTEGER DEFAULT pc_priority_normal,
                a_priority_high             IN INTEGER DEFAULT pc_priority_normal,
                a_run_fail_threshold        IN INTEGER DEFAULT NULL,
                a_sch_job_comments          IN VARCHAR2 DEFAULT NULL,
                a_forced_recreate           IN BOOLEAN DEFAULT FALSE) RETURN INTEGER IS
    l_job_id        INTEGER;
    l_sch_id        INTEGER;
    l_sch_job_id    INTEGER;
    l_dbms_job_name VARCHAR2(30);
    l_idx           INTEGER;
    l_err_msg       VARCHAR2(2000);
  BEGIN

    l_job_id := check_job(a_job_name);

    IF (l_job_id IS NULL) THEN
      RAISE ex_invalid_job_name;
    END IF;

    l_sch_id := check_schedule(a_sch_name);

    IF (l_sch_id IS NULL) THEN
      RAISE ex_invalid_schedule_name;
    END IF;

    l_sch_job_id := check_scheduled_job(a_job_name, a_sch_name);

    IF (l_sch_job_id IS NOT NULL) THEN
        IF (a_forced_recreate) THEN
          unschedule_job(a_job_name, a_sch_name);
        ELSE
          xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                                a_log_msg => 'Unable to schedule job "'|| a_job_name ||
                                             '" with schedule "'|| a_sch_name ||' ". It'' already scheduled.');
          RAISE ex_duplicate_job_schedule;
        END IF;
    END IF;

    SELECT xdv_sch_scj_seq.NEXTVAL INTO l_sch_job_id FROM DUAL;

    INSERT INTO xdv_sch_sch_job_t
      (id, job_id, sch_id, name, date_start, date_end,
       priority_normal, priority_high, run_fail_threshold, comments)
    VALUES
      (l_sch_job_id, l_job_id, l_sch_id, a_sch_job_name, a_date_start, a_date_end,
       a_priority_normal, a_priority_high, a_run_fail_threshold, a_sch_job_comments);

    IF(a_sch_job_arguments IS NOT NULL) THEN
      l_idx := 1;
      FOR i IN a_sch_job_arguments.FIRST .. a_sch_job_arguments.LAST LOOP
        add_scheduled_job_argument(a_sch_job_id => l_sch_job_id,
                                   a_job_argument => a_sch_job_arguments(i),
                                   a_arg_position => l_idx);
      l_idx := l_idx + 1;
      END LOOP;
    END IF;

    l_dbms_job_name := get_dbms_job_name(l_sch_job_id, a_sch_job_name);

    DBMS_SCHEDULER.CREATE_JOB(
        job_name            => l_dbms_job_name,
        schedule_name       => get_dbms_schedule_name(l_sch_id, a_sch_name),
        job_type            => 'STORED_PROCEDURE',
        job_action          => 'xdv_scheduler_pkg.do_job_action',
        number_of_arguments => 1,
        --start_date          => a_date_start,
        --end_date            => a_date_end,
        job_class           => 'DEFAULT_JOB_CLASS',
        enabled             => FALSE,
        auto_drop           => TRUE,
        comments            => a_sch_job_comments);

    DBMS_SCHEDULER.SET_JOB_ANYDATA_VALUE(job_name => l_dbms_job_name,
                                         argument_position => 1,
                                         argument_value => sys.anydata.convertNumber(l_sch_job_id));

    DBMS_SCHEDULER.ENABLE(l_dbms_job_name);

    RETURN l_sch_job_id;

    EXCEPTION
    WHEN ex_object_already_exists THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Unable to create DBMS job "'|| l_dbms_job_name ||
                                         '". It''s already exists');
      RAISE ex_duplicate_job_schedule;

    WHEN ex_invalid_job_name THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Invalid job name: "' || a_job_name || '", DBMS job "'||
                                         l_dbms_job_name || '".');
      RAISE;

    WHEN ex_invalid_schedule_name THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Invalid schedule name: "' || a_sch_name || '", DBMS job "'||
                                         l_dbms_job_name || '".');
      RAISE;

    WHEN ex_duplicate_job_schedule THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Duplicate schedule job name "' || a_sch_job_name || '", DBMS job "'||
                                         l_dbms_job_name || '".');
      RAISE;

    WHEN OTHERS THEN

      l_err_msg := 'Unable to schedule: ' || a_sch_name || ' - other issues : ' ||
                   SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512);
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx, a_log_msg => l_err_msg);

      RAISE_APPLICATION_ERROR(pc_scheduler_application_error, l_err_msg);

  END schedule_job;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* Checks if a job is scheduled with a specific schedule. Returns internal id or NULL if job is not scheduled */
  FUNCTION  check_scheduled_job            (
                a_job_name                  IN VARCHAR2,
                a_sch_name                  IN VARCHAR2) RETURN NUMBER IS
    l_job_id        INTEGER;
    l_sch_id        INTEGER;
    l_sch_job_id    INTEGER;
  BEGIN

    l_job_id := check_job(a_job_name);

    IF (l_job_id IS NULL) THEN
        RAISE ex_invalid_job_name;
    END IF;

    l_sch_id := check_schedule(a_sch_name);

    IF (l_sch_id IS NULL) THEN
        RAISE ex_invalid_schedule_name;
    END IF;

    SELECT
      id INTO l_sch_job_id
    FROM
      xdv_sch_sch_job_t
    WHERE
      job_id = l_job_id AND
      sch_id = l_sch_id;

    RETURN l_sch_job_id;

    EXCEPTION
    WHEN NO_DATA_FOUND
      THEN RETURN NULL;

  END check_scheduled_job;
  ------------------------------------------------------------------

  /* Unschedules a job scheduled with a specific schedule. */
  PROCEDURE  drop_scheduled_job (
                a_sch_job_id    INTEGER,
                a_force_stop    IN BOOLEAN DEFAULT FALSE)
  IS
    l_sch_job_name  VARCHAR2(64);
    l_dbms_job_name VARCHAR2(30);
    l_err_msg       VARCHAR2(2000);
  BEGIN

    SELECT
      name INTO l_sch_job_name
    FROM
      xdv_sch_sch_job_t
    WHERE
      id = a_sch_job_id;

    l_dbms_job_name := get_dbms_job_name(a_sch_job_id, l_sch_job_name);

    BEGIN
      DBMS_SCHEDULER.DROP_JOB(job_name => l_dbms_job_name, force => a_force_stop);
    EXCEPTION
      WHEN ex_non_existing_object OR ex_non_job_object THEN

        l_err_msg := 'Unable to drop DBMS job: ' || l_dbms_job_name || ' doesn''t exist or is not a job.';
         xdv_logger_pkg.warn( a_log_ctx => v_log_ctx,
                              a_log_msg => l_err_msg);
    END;

    DELETE FROM xdv_sch_locked_res_t
    WHERE srq_id IN (SELECT id FROM xdv_sch_job_run_queue_t WHERE sjb_id = a_sch_job_id);

    DELETE FROM xdv_sch_job_run_queue_t WHERE sjb_id = a_sch_job_id;

    DELETE FROM xdv_sch_job_run_log_t WHERE sjb_id = a_sch_job_id;

    DELETE FROM xdv_sch_job_args_t WHERE sjb_id = a_sch_job_id;

    DELETE FROM xdv_sch_sch_job_t WHERE id = a_sch_job_id;

    xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                         a_log_msg => 'Scheduled Job Id = "'|| a_sch_job_id || '" has been deleted.');

    EXCEPTION
    WHEN NO_DATA_FOUND THEN

      l_err_msg := 'Invalid Scheduled Job Id = ' || a_sch_job_id || '.';
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => l_err_msg);
      RAISE ex_job_not_scheduled;


  END drop_scheduled_job;

  ------------------------------------------------------------------
  /* Unschedules a job scheduled with a specific schedule. */
  PROCEDURE  unschedule_job           (
                a_job_name                  IN VARCHAR2,
                a_sch_name                  IN VARCHAR2,
                a_force_stop                IN BOOLEAN DEFAULT FALSE) IS
    l_job_id        INTEGER;
    l_sch_id        INTEGER;
    l_sch_job_id    INTEGER;
    l_sch_job_name  VARCHAR2(64);
    l_dbms_job_name VARCHAR2(30);
    l_err_msg       VARCHAR2(2000);
  BEGIN

    l_job_id := check_job(a_job_name);

    IF (l_job_id IS NULL) THEN
        RAISE ex_invalid_job_name;
    END IF;

    l_sch_id := check_schedule(a_sch_name);

    IF (l_sch_id IS NULL) THEN
        RAISE ex_invalid_schedule_name;
    END IF;

    l_sch_job_id := check_scheduled_job(a_job_name, a_sch_name);

    IF (l_sch_job_id IS NULL) THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Job "'|| a_job_name || '" is not scheduled with schedule "'|| a_sch_name ||' ".');
      RAISE ex_job_not_scheduled;
    END IF;

    drop_scheduled_job(l_sch_job_id, a_force_stop);

    xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                         a_log_msg => 'Job "'|| a_job_name || '" has been unscheduled of schedule "'|| a_sch_name ||' ".');

    EXCEPTION
    WHEN ex_invalid_job_name THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Invalid job name "'|| a_job_name || '".');

      RAISE;

    WHEN ex_invalid_schedule_name THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Invalid schedule name "'|| a_sch_name || '".');

      RAISE;
    WHEN ex_job_not_scheduled THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Job "' || a_job_name ||
                                         '" is not scheduled with schedule "' || a_sch_name || '".');

      RAISE;

    WHEN OTHERS THEN
      xdv_logger_pkg.error(
          a_log_ctx => v_log_ctx,
          a_log_msg => 'Error unscheduling Job "'|| a_job_name || '" of schedule "'|| a_sch_name ||' ":' ||
                       ' -ERROR- ' || SUBSTR(SQLERRM,1,1024));

      RAISE;

  END unschedule_job;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* Returns a list of scheduled job names which match a specified SQL style mask */
  FUNCTION  get_matched_scheduled_jobs     (
                a_sch_job_name_mask         IN VARCHAR2) RETURN tn_name_arr IS
    l_sch_job_names   tn_name_arr := tn_name_arr();
  BEGIN

    BEGIN
      SELECT name BULK COLLECT INTO l_sch_job_names
      FROM xdv_sch_sch_job_t
      WHERE name like a_sch_job_name_mask;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        NULL;
    END;

    RETURN l_sch_job_names;
  END get_matched_scheduled_jobs;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* Temporarily disables a scheduled job. */
  PROCEDURE  disable_scheduled_job                    (
                a_job_name                  IN VARCHAR2,
                a_sch_name                  IN VARCHAR2,
                a_force                     IN BOOLEAN DEFAULT FALSE) IS
  BEGIN
    --TODO
    NULL;
  END disable_scheduled_job;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* Enables a scheduled job if temporarily disabled. */
  PROCEDURE  enable_scheduled_job                     (
                a_job_name                  IN VARCHAR2,
                a_sch_name                  IN VARCHAR2) IS
  BEGIN
    --TODO
    NULL;
  END enable_scheduled_job;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION  get_job_status                 (
                a_status_name               IN VARCHAR2) RETURN INTEGER IS
    l_status_id INTEGER;
  BEGIN
    SELECT id INTO l_status_id FROM xdv_sch_job_status_t WHERE name = a_status_name;
    RETURN l_status_id;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
       RAISE_APPLICATION_ERROR(pc_scheduler_application_error, 'Unknown status name: "' || a_status_name || '"');
  END get_job_status;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION  get_pending_job_status RETURN INTEGER IS
  BEGIN
    RETURN get_job_status(pc_job_status_name_pending);
  END get_pending_job_status;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION  get_running_job_status RETURN INTEGER IS
  BEGIN
    RETURN get_job_status(pc_job_status_name_running);
  END get_running_job_status;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION  get_failed_job_status RETURN INTEGER IS
  BEGIN
    RETURN get_job_status(pc_job_status_name_failed);
  END get_failed_job_status;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION  get_succeeded_job_status RETURN INTEGER IS
  BEGIN
    RETURN get_job_status(pc_job_status_name_succded);
  END get_succeeded_job_status;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE get_or_create_run_info (
                  a_sch_context  IN OUT NOCOPY   r_sch_context) IS
    l_run_queue_id        INTEGER;
    l_job_status          INTEGER;
    l_job_resource        xdv_sch_job_resources_t%rowtype;
    l_locked_res_id       INTEGER;
  BEGIN
    l_job_status := get_pending_job_status();

    BEGIN

      SELECT id INTO l_run_queue_id FROM xdv_sch_job_run_queue_t WHERE sjb_id = a_sch_context.a_sch_job.id;

      --run info has to be updated explicitly
      --UPDATE xdv_sch_job_run_queue_t SET tstamp = SYSDATE WHERE sjb_id = a_sch_context.a_sch_job.id;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN

        SELECT xdv_sch_srq_seq.NEXTVAL INTO l_run_queue_id FROM DUAL;

        INSERT INTO xdv_sch_job_run_queue_t
          (id, sjb_id, jst_id, priority, tstamp, run_fail_count)
        VALUES
          (l_run_queue_id, a_sch_context.a_sch_job.id, l_job_status,
           a_sch_context.a_sch_job.priority_normal, SYSDATE, 0);

        IF(a_sch_context.a_job_resources IS NOT NULL AND a_sch_context.a_job_resources.COUNT > 0) THEN
          FOR i IN a_sch_context.a_job_resources.FIRST .. a_sch_context.a_job_resources.LAST LOOP

            l_job_resource := a_sch_context.a_job_resources(i);

            SELECT xdv_sch_slr_seq.NEXTVAL INTO l_locked_res_id FROM DUAL;

            INSERT INTO xdv_sch_locked_res_t
              (id, res_id, srq_id, lty_id, pending)
            VALUES
              (l_locked_res_id, l_job_resource.res_id, l_run_queue_id,
               l_job_resource.lty_id, pc_lock_status_pending);

          END LOOP;
        END IF;

    END;

    SELECT * INTO a_sch_context.a_run_queue_entry FROM xdv_sch_job_run_queue_t WHERE id = l_run_queue_id;

    SELECT * BULK COLLECT INTO a_sch_context.a_locked_resources FROM xdv_sch_locked_res_t WHERE srq_id = l_run_queue_id;

  END;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE update_locked_resources(
                a_locked_resources  IN OUT NOCOPY tn_int_locked_resources) IS
    l_locked_res    xdv_sch_locked_res_t%rowtype;
  BEGIN

    IF(a_locked_resources IS NOT NULL AND a_locked_resources.COUNT > 0) THEN
      FOR i IN a_locked_resources.FIRST .. a_locked_resources.LAST LOOP

        l_locked_res := a_locked_resources(i);

        UPDATE xdv_sch_locked_res_t
        SET pending = l_locked_res.pending
        WHERE id = l_locked_res.id;

      END LOOP;
    END IF;

  END;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE update_run_queue_entry(
                a_run_queue_entry  IN OUT NOCOPY xdv_sch_job_run_queue_t%ROWTYPE) IS
  BEGIN

    UPDATE xdv_sch_job_run_queue_t
    SET jst_id = a_run_queue_entry.jst_id,
        priority = a_run_queue_entry.priority,
        tstamp = SYSDATE,
        run_fail_count = a_run_queue_entry.run_fail_count,
        ora_sid = a_run_queue_entry.ora_sid,
        ora_audsid = a_run_queue_entry.ora_audsid
    WHERE id = a_run_queue_entry.id;

  END;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE update_run_info(
                a_sch_context  IN OUT NOCOPY   r_sch_context) IS
  BEGIN

    update_run_queue_entry(a_sch_context.a_run_queue_entry);
    update_locked_resources(a_sch_context.a_locked_resources);

  END;
  ------------------------------------------------------------------


  ------------------------------------------------------------------
  FUNCTION  get_scheduler_lock_handle RETURN VARCHAR2 IS
    l_lock_handle   VARCHAR2(128);
  BEGIN
    DBMS_LOCK.ALLOCATE_UNIQUE(lockname => pc_scheduler_lock_name,
                              lockhandle => l_lock_handle);
    RETURN l_lock_handle;
  END;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION  get_context (
                a_sch_job_id                IN INTEGER) RETURN r_sch_context IS
    l_sch_context     r_sch_context;
  BEGIN
    xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                          a_log_msg => 'Creating scheduler context. Scheduled Job id=' || a_sch_job_id);

    SELECT * INTO l_sch_context.a_sch_job FROM xdv_sch_sch_job_t WHERE id = a_sch_job_id;

    SELECT * INTO l_sch_context.a_job FROM xdv_sch_job_t WHERE id = l_sch_context.a_sch_job.job_id;

    SELECT * BULK COLLECT INTO l_sch_context.a_job_resources FROM xdv_sch_job_resources_t WHERE job_id = l_sch_context.a_job.id;

    get_or_create_run_info(l_sch_context);

    RETURN l_sch_context;
  END;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION can_lock_resource(
                a_sch_job_run_id IN INTEGER,
                a_res_id         IN INTEGER,
                a_lock_type_id   IN INTEGER) RETURN BOOLEAN IS
    l_shared_locks_cnt  INTEGER;
    l_shared_lock_type  INTEGER;
    l_exclsv_locks_cnt  INTEGER;
    l_exclsv_locks_type INTEGER;
    l_any_locks_cnt     INTEGER;
  BEGIN

    l_shared_lock_type := check_lock_type(pc_lock_type_shrd);
    l_exclsv_locks_type := check_lock_type(pc_lock_type_excl);

    -- first check if the resource is already locked
    SELECT COUNT(*) INTO l_shared_locks_cnt
    FROM xdv_sch_locked_res_t
    WHERE res_id = a_res_id AND
          srq_id != a_sch_job_run_id AND
          lty_id = l_shared_lock_type AND
          pending = pc_lock_status_locked;

    SELECT COUNT(*) INTO l_exclsv_locks_cnt
    FROM xdv_sch_locked_res_t
    WHERE res_id = a_res_id AND
          srq_id != a_sch_job_run_id AND
          lty_id = l_exclsv_locks_type AND
          pending = pc_lock_status_locked;

    IF (a_lock_type_id = l_exclsv_locks_type) THEN
      IF (l_shared_locks_cnt > 0 OR l_exclsv_locks_cnt > 0) THEN
        RETURN FALSE;
      END IF;
    ELSE
      IF (l_exclsv_locks_cnt > 0) THEN
        RETURN FALSE;
      END IF;
    END IF;

    -- the resource is not locked or can be shared
    -- check if there are other pending jobs with a higher priority

    IF (a_lock_type_id = l_exclsv_locks_type) THEN

      SELECT COUNT(*) INTO l_any_locks_cnt
      FROM xdv_sch_locked_res_t lock_t
      JOIN xdv_sch_job_run_queue_t job_run_queue_t ON
        job_run_queue_t.id = a_sch_job_run_id
      JOIN xdv_sch_job_run_queue_t other_job_run_queue_t ON
        other_job_run_queue_t.id = lock_t.srq_id
      WHERE
        lock_t.res_id = a_res_id AND
        lock_t.srq_id != a_sch_job_run_id AND
        lock_t.pending = pc_lock_status_pending AND
        job_run_queue_t.priority < other_job_run_queue_t.priority;

      IF (l_any_locks_cnt > 0) THEN
        RETURN FALSE;
      ELSE
        RETURN TRUE;
      END IF;

    ELSE

      SELECT COUNT(*) INTO l_exclsv_locks_cnt
      FROM xdv_sch_locked_res_t lock_t
      JOIN xdv_sch_job_run_queue_t job_run_queue_t ON
        job_run_queue_t.id = a_sch_job_run_id
      JOIN xdv_sch_job_run_queue_t other_job_run_queue_t ON
        other_job_run_queue_t.id = lock_t.srq_id
      WHERE
        lock_t.res_id = a_res_id AND
        lock_t.srq_id != a_sch_job_run_id AND
        lock_t.lty_id = l_exclsv_locks_type AND
        lock_t.pending = pc_lock_status_pending AND
        job_run_queue_t.priority < other_job_run_queue_t.priority;

      IF (l_exclsv_locks_cnt > 0) THEN
        RETURN FALSE;
      ELSE
        RETURN TRUE;
      END IF;

    END IF;
  END can_lock_resource;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  lock_all_resources (
                a_sch_context  IN OUT NOCOPY   r_sch_context) IS
    l_locked_all   BOOLEAN;
    l_res_id       INTEGER;
    l_lock_type_id INTEGER;
  BEGIN
    xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                          a_log_msg => 'Trying to lock all the resources. Scheduled Job id=' || a_sch_context.a_sch_job.id);
    l_locked_all := TRUE;

    IF (a_sch_context.a_locked_resources IS NOT NULL AND a_sch_context.a_locked_resources.COUNT > 0) THEN
      FOR i IN a_sch_context.a_locked_resources.FIRST .. a_sch_context.a_locked_resources.LAST LOOP

        l_res_id := a_sch_context.a_locked_resources(i).res_id;
        l_lock_type_id := a_sch_context.a_locked_resources(i).lty_id;

        IF (a_sch_context.a_locked_resources(i).pending = pc_lock_status_pending) THEN
          IF (can_lock_resource(a_sch_context.a_run_queue_entry.id, l_res_id, l_lock_type_id)) THEN
            a_sch_context.a_locked_resources(i).pending := pc_lock_status_locked;
            xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                                 a_log_msg => 'Locked resource id=' || l_res_id ||
                                              ', lock_type_id=' || l_lock_type_id ||
                                              '. Scheduled job id=' || a_sch_context.a_sch_job.id);
          ELSE
            l_locked_all := FALSE;
            xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                                 a_log_msg => 'Unable to lock resource id=' || l_res_id ||
                                              ', lock_type_id=' || l_lock_type_id ||
                                              '. Scheduled job id=' || a_sch_context.a_sch_job.id);
          END IF;
        ELSE
            xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                                 a_log_msg => 'Resource is already locked : resource id=' || l_res_id ||
                                              ', lock_type_id=' || l_lock_type_id ||
                                              '. Scheduled job id=' || a_sch_context.a_sch_job.id);
        END IF;

      END LOOP;

      update_locked_resources(a_sch_context.a_locked_resources);

    END IF;

    IF (l_locked_all) THEN
          xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                               a_log_msg => 'Locked all resources. Scheduled job id=' || a_sch_context.a_sch_job.id);
    ELSE
          xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                               a_log_msg => 'Some or all resources can''t be locked. Scheduled job id=' || a_sch_context.a_sch_job.id);
    END IF;

    a_sch_context.a_has_all_resources := l_locked_all;

  END lock_all_resources;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  create_run_log_entry(
                a_sch_job_id    IN INTEGER,
                a_job_status_id IN INTEGER,
                a_date_start    IN DATE,
                a_date_end      IN DATE) IS
    l_run_log_id  INTEGER;
  BEGIN
    SELECT xdv_sch_srl_seq.NEXTVAL INTO l_run_log_id FROM DUAL;

    INSERT INTO xdv_sch_job_run_log_t
      (id, sjb_id, sta_id, date_start, date_end)
    VALUES
      (l_run_log_id, a_sch_job_id, a_job_status_id, a_date_start, a_date_end);
  END;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  remove_run_info (
               a_run_queue_entry_id IN INTEGER) IS
  BEGIN
    xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                          a_log_msg => 'Removing run info including run queue entry and locked resources.' ||
                                       ' run queue entry id=' || a_run_queue_entry_id);
    BEGIN
      DELETE FROM xdv_sch_locked_res_t
      WHERE srq_id = a_run_queue_entry_id;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        NULL;
    END;

    DELETE FROM xdv_sch_job_run_queue_t
    WHERE id = a_run_queue_entry_id;

  END;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  /* scheduled jobs wrapper. to be called by DBMS_SCHEDULER */
  PROCEDURE  execute_job_procedure (
                a_sch_context  IN OUT NOCOPY   r_sch_context) IS
    l_err_msg         VARCHAR2(2000);
    l_exec_stmnt      VARCHAR2(32767);
    TYPE tn_job_args  IS TABLE OF xdv_sch_job_args_t%type;
    l_job_args        tn_job_args;
    l_job_arg         xdv_sch_job_args_t%rowtype;
    l_job_arg_type    VARCHAR2(128);
  BEGIN

    xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                         a_log_msg => 'Executing Job procedure "' || a_sch_context.a_job.procedure_name ||
                                      '" of Scheduled Job id=' || a_sch_context.a_sch_job.id);


    l_exec_stmnt := 'DECLARE BEGIN ' || a_sch_context.a_job.procedure_name || '(';

    BEGIN
      SELECT * BULK COLLECT INTO l_job_args
      FROM xdv_sch_job_args_t
      WHERE sjb_id = a_sch_context.a_sch_job.id;

      IF (l_job_args IS NOT NULL AND l_job_args.COUNT >0) THEN
       FOR i IN l_job_args.FIRST .. l_job_args.LAST LOOP
        IF (i != l_job_args.FIRST) THEN
          l_exec_stmnt := l_exec_stmnt || ', ';
        END IF;

        l_job_arg := l_job_args(i);

        SELECT UPPER(l_job_arg.arg_data_type) INTO l_job_arg_type FROM DUAL;

        l_exec_stmnt := l_exec_stmnt || l_job_arg.arg_name || ' => ';

        --TODO: other types
        CASE
        WHEN UPPER(l_job_arg_type) = 'VARCHAR2' THEN
          l_exec_stmnt := l_exec_stmnt || '''' || l_job_arg.arg_value || '''';
        WHEN UPPER(l_job_arg_type) = 'CHAR2' THEN
          l_exec_stmnt := l_exec_stmnt || '''' || l_job_arg.arg_value || '''';
        WHEN l_job_arg_type = 'DATE' THEN
          l_exec_stmnt := l_exec_stmnt || '''' || l_job_arg.arg_value || '''';
        ELSE
          l_exec_stmnt := l_exec_stmnt || l_job_arg.arg_value;
        END CASE;

      END LOOP;
     END IF;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        NULL;
      WHEN OTHERS THEN
        xdv_logger_pkg.error(
            a_log_ctx => v_log_ctx,
            a_log_msg => 'Failure creating command statement: ' || SQLCODE ||
                         ' -ERROR- ' || SUBSTR(SQLERRM,1,1024));
    END;

    l_exec_stmnt := l_exec_stmnt || '); END;';

    BEGIN
      xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                           a_log_msg => 'Command to execute : "' || l_exec_stmnt || '"');

      EXECUTE IMMEDIATE l_exec_stmnt;

      xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                           a_log_msg => 'Done executing Job procedure "' || a_sch_context.a_job.procedure_name ||
                                        '" of Scheduled Job id=' || a_sch_context.a_sch_job.id);

      a_sch_context.a_run_queue_entry.jst_id := get_succeeded_job_status();
      a_sch_context.a_status_message := 'Success';
    EXCEPTION
      WHEN OTHERS THEN
        a_sch_context.a_run_queue_entry.jst_id := get_failed_job_status();
        l_err_msg := 'Error executing "' || a_sch_context.a_job.name ||
                     '" procedure of Scheduled Job id=' || a_sch_context.a_sch_job.id || ' : ' ||
                     SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512) || dbms_utility.format_error_backtrace;
        a_sch_context.a_status_message := 'Failure: ' || SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,1024);
        xdv_logger_pkg.error( a_log_ctx => v_log_ctx, a_log_msg => l_err_msg);
    END;
  END;
  ------------------------------------------------------------------

  PROCEDURE enqueue_sch_msg(
    a_queue_name         IN VARCHAR2,
    a_msg                IN xdv_sch_message_tp,
    a_msg_priority       IN NUMBER   DEFAULT 1
  );

  ------------------------------------------------------------------
  PROCEDURE  send_job_end_notification (
                a_sch_context   IN OUT NOCOPY   r_sch_context,
                a_date_start    IN DATE,
                a_date_end      IN DATE) IS
    l_sch_msg     xdv_sch_message_tp;
  BEGIN
    xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                          a_log_msg => 'Sending job end notification. Scheduled Job id=' || a_sch_context.a_sch_job.id);

    l_sch_msg := xdv_sch_message_tp(pc_message_type_job_end,
                                    a_sch_context.a_sch_job.id,
                                    a_sch_context.a_run_queue_entry.id,
                                    a_sch_context.a_run_queue_entry.jst_id,
                                    a_date_start,
                                    a_date_end,
                                    SUBSTR(a_sch_context.a_status_message, 1, 2000));

    enqueue_sch_msg(pc_message_queue_name, l_sch_msg);

    xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                          a_log_msg => 'Notification message has been sent. Scheduled Job id=' || a_sch_context.a_sch_job.id);

  END;
  ------------------------------------------------------------------

  FUNCTION is_msg_loop_running RETURN BOOLEAN;

  ------------------------------------------------------------------
  /* scheduled jobs wrapper. to be called by DBMS_SCHEDULER */
  PROCEDURE  do_job_action                 (
                a_sch_job_id                IN INTEGER) IS
    l_sch_lock_handle VARCHAR2(128);
    l_lock_ret_value  INTEGER;
    l_sch_context     r_sch_context;
    l_err_msg         VARCHAR2(2000);
    l_job_status      INTEGER;
    l_job_start_date  DATE;
    l_job_end_date    DATE;
  BEGIN
    xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                         a_log_msg => 'Starting handling of Scheduled Job id=' || a_sch_job_id);

    IF (NOT is_scheduler_enabled()) THEN
      xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                           a_log_msg => 'Scheduler is disabled. Exiting.');
      RETURN;
    END IF;

    IF (NOT is_msg_loop_running()) THEN
      xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                           a_log_msg => 'Scheduler message processing loop is not running. Exiting.');
      RETURN;
    END IF;

    l_sch_lock_handle := get_scheduler_lock_handle();

    l_lock_ret_value := DBMS_LOCK.REQUEST(lockhandle => l_sch_lock_handle,
                                          lockmode =>  DBMS_LOCK.X_MODE,
                                          timeout => DBMS_LOCK.MAXWAIT,
                                          release_on_commit => FALSE);
    IF (l_lock_ret_value != 0 AND l_lock_ret_value != 4) THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Error obtaining Scheduler lock: ' || l_lock_ret_value ||
                                         '. Scheduled Job id=' || a_sch_job_id);
      RETURN;
    END IF;

    xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                          a_log_msg => 'Scheduler lock has been acquired for pre-processing handler. Scheduled Job id=' || a_sch_job_id);

    l_sch_context := get_context(a_sch_job_id);

    IF (l_sch_context.a_run_queue_entry.jst_id = get_running_job_status()) THEN
      xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Job is already running. Scheduled Job id=' || a_sch_job_id);
      RETURN;
    END IF;

    lock_all_resources(l_sch_context);

    IF (l_sch_context.a_has_all_resources = FALSE) THEN
        l_sch_context.a_run_queue_entry.run_fail_count := l_sch_context.a_run_queue_entry.run_fail_count + 1;

        xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                             a_log_msg => 'Can''t lock some or all required resources. ' ||
                                          'run_fail_count=' || l_sch_context.a_run_queue_entry.run_fail_count ||
                                          '. Scheduled Job id=' || a_sch_job_id);

        IF (l_sch_context.a_run_queue_entry.run_fail_count = l_sch_context.a_sch_job.run_fail_threshold) THEN
          xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                               a_log_msg => 'run_fail_count=' || l_sch_context.a_run_queue_entry.run_fail_count ||
                                            ' crossed threshold=' || l_sch_context.a_sch_job.run_fail_threshold ||
                                            '. Changing job priority from ' || l_sch_context.a_run_queue_entry.priority ||
                                            ' to ' || l_sch_context.a_sch_job.priority_high ||
                                            '. Scheduled Job id=' || a_sch_job_id);

          l_sch_context.a_run_queue_entry.priority := l_sch_context.a_sch_job.priority_high;

        END IF;

        update_run_queue_entry(l_sch_context.a_run_queue_entry);

        RETURN;
    END IF;

    l_sch_context.a_run_queue_entry.jst_id := get_running_job_status();

    SELECT USERENV('SID') INTO l_sch_context.a_run_queue_entry.ora_sid FROM DUAL;

    SELECT USERENV('SESSIONID') INTO l_sch_context.a_run_queue_entry.ora_audsid FROM DUAL;

    update_run_queue_entry(l_sch_context.a_run_queue_entry);

    COMMIT;

    l_lock_ret_value := DBMS_LOCK.RELEASE(lockhandle => l_sch_lock_handle);

    xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                          a_log_msg => 'Scheduler lock has been released after pre-processing handler.' ||
                                        ' Scheduled Job id=' || a_sch_job_id);

    l_job_start_date := SYSDATE;

    execute_job_procedure(l_sch_context);

    l_job_end_date := SYSDATE;

    send_job_end_notification(l_sch_context,
                              l_job_start_date,
                              l_job_end_date);

    EXCEPTION
      WHEN OTHERS THEN
      l_err_msg := 'Error executing Scheduled Job id=' || a_sch_job_id || ' : ' ||
                   SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512);
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx, a_log_msg => l_err_msg);

      RAISE_APPLICATION_ERROR(pc_scheduler_application_error, l_err_msg);

  END do_job_action;
  ------------------------------------------------------------------


  ------------------------------------------------------------------
  PROCEDURE enqueue_sch_msg(
    a_queue_name         IN VARCHAR2,
    a_msg                IN xdv_sch_message_tp,
    a_msg_priority       IN NUMBER   DEFAULT 1
  )
  IS
    l_enq_options        DBMS_AQ.ENQUEUE_OPTIONS_T;
    l_msg_props          DBMS_AQ.MESSAGE_PROPERTIES_T;
    l_msg_handle         RAW(16);
  BEGIN
    l_enq_options.visibility := DBMS_AQ.ON_COMMIT ; -- as opposed to  DBMS_AQ.IMMEDIATE
    l_enq_options.delivery_mode := DBMS_AQ.PERSISTENT;
    /* other options that can be configured : relative_msgid, sequence_deviation, transformation */

    --l_recipients(1) := sys.aq$_agent('sgm_datapump_subs', NULL, NULL);

    l_msg_props.priority := a_msg_priority;
    l_msg_props.delay := DBMS_AQ.NO_DELAY;
    l_msg_props.expiration := DBMS_AQ.NEVER;
    --l_msg_props.recipient_list := l_recipients;
    /* other options that can be configured : correlation, recipient_list, exception_queue, delivery_mode, user_property */

    -- will throw "ORA-24033: no recipients for message" -> if there are no subscribers on the queue at the time of enqueue
    -- Messages enqueued in the same transaction into a queue that has been enabled for message grouping form a group.

    xdv_logger_pkg.info(a_log_ctx => v_log_ctx,
                        a_log_msg => 'Enqueueing message with msg_type=' || a_msg.msg_type ||
                                     ', sch_job_id=' || a_msg.sch_job_id || '" into the queue' ||
                                     a_queue_name || ' ...');

    DBMS_AQ.ENQUEUE(
      queue_name            => a_queue_name,
      enqueue_options       => l_enq_options,
      message_properties    => l_msg_props,
      payload               => a_msg,
      msgid                 => l_msg_handle);

    xdv_logger_pkg.info(a_log_ctx => v_log_ctx,
                        a_log_msg => 'Message with msg_type=' || a_msg.msg_type ||
                                     ', sch_job_id=' || a_msg.sch_job_id || '" has been enqueued into ' ||
                                     a_queue_name || ' ...');

  END enqueue_sch_msg;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION dequeue_sch_msg (
                a_queue_name    IN VARCHAR2) RETURN xdv_sch_message_tp IS
    l_dequeue_opts        DBMS_AQ.dequeue_options_t;
    l_msg_props           DBMS_AQ.message_properties_t;
    l_msgid               RAW(16);
    l_sch_msg             xdv_sch_message_tp;
  BEGIN
    --l_dequeue_opts.consumer_name := 'sgm_datapump_def_subs';
    l_dequeue_opts.dequeue_mode := DBMS_AQ.REMOVE;    -- other options are : LOCKED, BROWSE, REMOVE_NO_DATA
    l_dequeue_opts.visibility := DBMS_AQ.ON_COMMIT; -- other options are : IMMEDIATE, (would rather prefer IMMEDIATE ...)
    l_dequeue_opts.wait := DBMS_AQ.FOREVER;   -- other options are : FOREVER, NO_WAIT, number
    l_dequeue_opts.navigation := DBMS_AQ.NEXT_MESSAGE_MULTI_GROUP;
    -- other dequeue_opts : msgid , correlation, deq_condition, transformation, delivery_mode

    -- In queues that have not been enabled for message grouping, a dequeue in LOCKED or REMOVE mode locks only a single message. By contrast, a dequeue operation that seeks to dequeue a message that is part of a group locks the entire group.

    --  : ORA-25228: timeout or end-of-fetch during message dequeue from
    DBMS_AQ.DEQUEUE(
                     queue_name               => a_queue_name,
                     dequeue_options          => l_dequeue_opts,
                     message_properties       => l_msg_props,
                     payload                  => l_sch_msg,
                     msgid                    => l_msgid);

    RETURN l_sch_msg;


  EXCEPTION  WHEN ex_aq_time_out_while_dequeuing THEN

    xdv_logger_pkg.error(a_log_ctx => v_log_ctx, a_log_msg => 'Timeout while dequeueing from the ' || a_queue_name || ' queue.');

    RETURN NULL;
  END dequeue_sch_msg;

  ------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION  get_highest_prio_pending_jobs RETURN id_array_t IS
   l_sch_job_ids id_array_t;
  BEGIN

    SELECT
      sjb_id BULK COLLECT INTO l_sch_job_ids
    FROM (
      SELECT
        sjb_id
        --COUNT(res_id),
        --SUM(has_lock),
        --SUM(resource_available)
      FROM (
        SELECT
          sjb_id,
          priority,
          res_id,
          has_lock,
          CASE
            WHEN ( (SUM(excl_locked) = 0 AND  SUM(shrd_locked) = 0) OR
                    (req_lock_type = pc_lock_type_shrd AND SUM(excl_locked) = 0) ) THEN 1
            ELSE 0 END resource_available
        FROM (
          SELECT
            srq_t.sjb_id sjb_id,
            srq_t.priority priority,
            req_res_t.res_id res_id,
            req_lock_type_t.name req_lock_type,
            CASE WHEN req_res_t.pending = pc_lock_status_locked THEN 1 ELSE 0 END has_lock,
            CASE WHEN locked_lock_type_t.name = pc_lock_type_excl THEN 1 ELSE 0 END excl_locked,
            CASE WHEN locked_lock_type_t.name = pc_lock_type_shrd THEN 1 ELSE 0 END shrd_locked
          FROM xdv_sch_job_run_queue_t srq_t
          JOIN xdv_sch_job_status_t job_status_t ON
            job_status_t.id = srq_t.jst_id
          JOIN xdv_sch_locked_res_t req_res_t ON
            req_res_t.srq_id = srq_t.id
          LEFT JOIN xdv_sch_locked_res_t locked_res_t ON
            locked_res_t.res_id = req_res_t.res_id AND
            locked_res_t.id != req_res_t.id AND
            locked_res_t.pending != pc_lock_status_pending
          JOIN xdv_sch_lock_type_t req_lock_type_t ON
            req_lock_type_t.id = req_res_t.lty_id
          LEFT JOIN  xdv_sch_lock_type_t locked_lock_type_t ON
            locked_lock_type_t.id = locked_res_t.lty_id
          WHERE
            job_status_t.name = pc_job_status_name_pending
        )
        GROUP BY
          sjb_id,
          priority,
          res_id,
          has_lock,
          req_lock_type
      )
      GROUP BY
        sjb_id,
        priority
      HAVING
        COUNT(res_id) = SUM(resource_available) AND SUM(has_lock) < COUNT(res_id)
      ORDER BY
        priority DESC,
        sjb_id ASC
    );
    --WHERE
    --  ROWNUM < 2;

    RETURN l_sch_job_ids;

    EXCEPTION
      WHEN NO_DATA_FOUND
      THEN RETURN NULL;

  END get_highest_prio_pending_jobs;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  run_pending_jobs
  IS
    l_sch_lock_handle VARCHAR2(128);
    l_lock_ret_value  INTEGER;
    l_err_msg         VARCHAR2(2000);
    l_sch_context     r_sch_context;
    l_sch_job_ids     id_array_t;
    l_sch_job_id      NUMBER;
    l_sch_job_name    VARCHAR2(128);
  BEGIN

    xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                         a_log_msg => 'Searching for pending jobs to run...');

    l_sch_lock_handle := get_scheduler_lock_handle();

    l_lock_ret_value := DBMS_LOCK.REQUEST(lockhandle => l_sch_lock_handle,
                                          lockmode =>  DBMS_LOCK.X_MODE,
                                          timeout => DBMS_LOCK.MAXWAIT,
                                          release_on_commit => FALSE);
    IF (l_lock_ret_value != 0 AND l_lock_ret_value != 4) THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Error obtaining Scheduler lock: ' || l_lock_ret_value);
      RETURN;
    END IF;

    xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                          a_log_msg => 'Scheduler lock has been acquired for pending job search.');

    -- now find jobs which can be scheduled 'now'
    l_sch_job_ids := get_highest_prio_pending_jobs();

    IF(l_sch_job_ids IS NOT NULL AND l_sch_job_ids.COUNT > 0) THEN
      FOR i IN l_sch_job_ids.FIRST .. l_sch_job_ids.LAST
      LOOP
        l_sch_job_id := l_sch_job_ids(i);
        xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                              a_log_msg => 'Trying to schedule NOW Scheduled Job id=' || l_sch_job_id);

        l_sch_context := get_context(l_sch_job_id);

        lock_all_resources(l_sch_context);

        IF (l_sch_context.a_has_all_resources = FALSE) THEN
          xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                                a_log_msg => 'Scheduled Job id=' || l_sch_job_id ||
                                             '. Can''t lock some or all resources. Pending job(s) with a higher priority exist');
        ELSE
          -- schedule job now
          BEGIN
            l_sch_job_name := 'xdv_sch_now_' || l_sch_job_id;
            DBMS_SCHEDULER.CREATE_JOB(
              job_name            => l_sch_job_name,
              job_type            => 'STORED_PROCEDURE',
              job_action          => 'xdv_scheduler_pkg.do_job_action',
              repeat_interval     => 'FREQ=SECONDLY; INTERVAL=1;',
              number_of_arguments => 1,
              job_class           => 'DEFAULT_JOB_CLASS',
              enabled             => FALSE,
              auto_drop           => TRUE);

            DBMS_SCHEDULER.SET_JOB_ANYDATA_VALUE(job_name => l_sch_job_name,
                                                 argument_position => 1,
                                                 argument_value => sys.anydata.convertNumber(l_sch_job_id));
            -- auto drop after the first run
            DBMS_SCHEDULER.SET_ATTRIBUTE (name => l_sch_job_name,
                                          attribute => 'max_runs',
                                          value => 1);

            DBMS_SCHEDULER.ENABLE(l_sch_job_name);
          EXCEPTION
            WHEN ex_object_already_exists THEN
              xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                                    a_log_msg => 'Unable to create DBMS job "'|| l_sch_job_name);
          END;
        END IF;
      END LOOP;
    ELSE
      xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                            a_log_msg => 'No pending jobs to execute now');
    END IF;

    -- commit prior to releasing scheduler lock
    COMMIT;

    l_lock_ret_value := DBMS_LOCK.RELEASE(lockhandle => l_sch_lock_handle);

    xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                          a_log_msg => 'Scheduler lock has been released after running of pending jobs.');
    COMMIT;

    EXCEPTION
      WHEN OTHERS THEN
      l_err_msg := 'Error running pending jobs: ' ||
                   SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512);
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx, a_log_msg => l_err_msg);

      l_lock_ret_value := DBMS_LOCK.RELEASE(lockhandle => l_sch_lock_handle);

      xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                            a_log_msg => 'Scheduler lock has been released after running of pending jobs.');
      COMMIT;

  END run_pending_jobs;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  process_job_end_msg (
            a_sch_msg    IN xdv_sch_message_tp )
  IS
    l_err_msg         VARCHAR2(2000);
    l_sch_context     r_sch_context;
    l_sch_job_id      INTEGER;
    l_sch_job_name    VARCHAR2(128);
  BEGIN

    xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                         a_log_msg => 'Post run processing. Scheduled Job id=' || a_sch_msg.sch_job_id ||
                                      '. Job Run Queue id=' || a_sch_msg.job_run_queue_id);

    xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                          a_log_msg => 'Removing run info including run queue entry and locked resources.' ||
                                        ' Scheduled Job id=' || a_sch_msg.sch_job_id ||
                                        '. Job Run Queue id=' || a_sch_msg.job_run_queue_id);

    remove_run_info(a_sch_msg.job_run_queue_id);

    COMMIT;

    xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                          a_log_msg => 'Creating run log entry. Scheduled Job id=' || a_sch_msg.sch_job_id ||
                                        '. Job Run Queue id=' || a_sch_msg.job_run_queue_id);

    create_run_log_entry(a_sch_msg.sch_job_id,
                         a_sch_msg.job_status_id,
                         a_sch_msg.date_start,
                         a_sch_msg.date_end);

    COMMIT;

    EXCEPTION
      WHEN OTHERS THEN
      l_err_msg := 'Error post processing of Scheduled Job id=' || a_sch_msg.sch_job_id ||
                   '. Job Run Queue id=' || a_sch_msg.job_run_queue_id || ' : ' ||
                   SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512);
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx, a_log_msg => l_err_msg);

  END process_job_end_msg;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  process_stop_msg_loop_msg  (
            a_sch_msg    xdv_sch_message_tp)  IS
  BEGIN
    xdv_logger_pkg.info(
          a_log_ctx => v_log_ctx,
          a_log_msg => 'process_stop_msg_loop_msg...');
    -- TODO
  END process_stop_msg_loop_msg;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  message_process_loop  IS
    l_sch_msg        xdv_sch_message_tp;
    l_exit           BOOLEAN;
    l_running_status INTEGER;
    l_sch_lock_handle VARCHAR2(128);
    l_lock_ret_value  INTEGER;
  BEGIN

    xdv_logger_pkg.info(
          a_log_ctx => v_log_ctx,
          a_log_msg => 'Starting message_process_loop...');

    l_exit := FALSE;

    --delete runtime info: remove only locked resources and running jobs
    l_running_status := get_running_job_status();

    DELETE FROM xdv_sch_message_qt;

    DELETE FROM xdv_sch_locked_res_t locked_res
    WHERE pending = pc_lock_status_locked;

    DELETE FROM xdv_sch_locked_res_t locked_res
    WHERE EXISTS (
           SELECT id FROM xdv_sch_job_run_queue_t run_queue
           WHERE run_queue.jst_id = l_running_status AND
                 run_queue.id = locked_res.srq_id);

    DELETE FROM xdv_sch_job_run_queue_t
    WHERE jst_id = l_running_status;

    COMMIT;

    --run pending jobs prior entering loop

    run_pending_jobs();

    WHILE (l_exit = FALSE)
    LOOP

      xdv_logger_pkg.debug(
            a_log_ctx => v_log_ctx,
            a_log_msg => 'Waiting for a scheduler message...');

      l_sch_msg := dequeue_sch_msg(pc_message_queue_name);

      xdv_logger_pkg.debug(
            a_log_ctx => v_log_ctx,
            a_log_msg => 'Got message with msg_type=' || l_sch_msg.msg_type);

      BEGIN
        l_sch_lock_handle := get_scheduler_lock_handle();

        l_lock_ret_value := DBMS_LOCK.REQUEST(lockhandle => l_sch_lock_handle,
                                              lockmode =>  DBMS_LOCK.X_MODE,
                                              timeout => DBMS_LOCK.MAXWAIT,
                                              release_on_commit => FALSE);
        IF (l_lock_ret_value != 0 AND l_lock_ret_value != 4) THEN
          xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                                a_log_msg => 'Error obtaining Scheduler lock: ' || l_lock_ret_value);
          RETURN;
        END IF;

        xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                              a_log_msg => 'Scheduler lock has been acquired for message processing.');

        CASE
        WHEN l_sch_msg.msg_type = pc_message_type_job_end THEN
          process_job_end_msg(l_sch_msg);
          run_pending_jobs();
        WHEN l_sch_msg.msg_type = pc_message_type_stop_msg_loop THEN
              process_stop_msg_loop_msg(l_sch_msg);
              l_exit := TRUE;
        ELSE
          xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                                a_log_msg => 'Unknown message type : ' || l_sch_msg.msg_type);
        END CASE;

        l_lock_ret_value := DBMS_LOCK.RELEASE(lockhandle => l_sch_lock_handle);

        xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                              a_log_msg => 'Scheduler lock has been released after message processing.');

      EXCEPTION
        WHEN OTHERS THEN
          l_lock_ret_value := DBMS_LOCK.RELEASE(lockhandle => l_sch_lock_handle);

          xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                                a_log_msg => 'Scheduler lock has been released after processing of job end message.');
      END;

      COMMIT;

    END LOOP;

    xdv_logger_pkg.info(
          a_log_ctx => v_log_ctx,
          a_log_msg => 'Exitting message_process_loop...');

    EXCEPTION
      WHEN OTHERS THEN
      xdv_logger_pkg.error(
            a_log_ctx => v_log_ctx,
            a_log_msg => 'Error in message_process_loop : ' ||
                         SQLCODE || ' -ERROR- ' || SUBSTR(SQLERRM,1,512));

  END message_process_loop;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION  is_scheduler_enabled RETURN BOOLEAN IS
    l_enabled VARCHAR2(32);
  BEGIN
    SELECT enabled INTO l_enabled
    FROM user_scheduler_jobs
    WHERE UPPER(job_name) = UPPER(pc_scheduler_job_name);

    RETURN UPPER(l_enabled) = 'TRUE';
  EXCEPTION
    WHEN NO_DATA_FOUND
      THEN RETURN FALSE;
  END is_scheduler_enabled;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  FUNCTION  is_msg_loop_running RETURN BOOLEAN IS
    l_state VARCHAR2(32);
  BEGIN
    --TODO: not completely reliable, race condition possible:
    --msg loop might be in the initialization phase, which should not be treated as running
    --use global context or a table to keep msg loop status and properly initialize it
    SELECT state INTO l_state
    FROM user_scheduler_jobs
    WHERE UPPER(job_name) = UPPER(pc_scheduler_job_name);

    RETURN UPPER(l_state) = 'RUNNING';
  END is_msg_loop_running;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  enable_scheduler (
              a_forced_restart   IN BOOLEAN DEFAULT FALSE) IS
    l_is_enabled    BOOLEAN;
  BEGIN

    xdv_logger_pkg.info(
          a_log_ctx => v_log_ctx,
          a_log_msg => 'Enabling Scheduler...');

    l_is_enabled := is_scheduler_enabled();

    IF (l_is_enabled) THEN
      IF(NOT a_forced_restart) THEN
        xdv_logger_pkg.info(
          a_log_ctx => v_log_ctx,
          a_log_msg => 'Scheduler is already enabled.');
        RETURN;
      END IF;
      disable_scheduler(a_forced_disable => TRUE);
    END IF;

    DBMS_SCHEDULER.ENABLE(pc_scheduler_job_name);

    xdv_logger_pkg.info(
          a_log_ctx => v_log_ctx,
          a_log_msg => 'Scheduler has been enabled.');

  END enable_scheduler;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  disable_scheduler (
              a_forced_stop      IN BOOLEAN DEFAULT FALSE,
              a_forced_disable   IN BOOLEAN DEFAULT FALSE) IS
  BEGIN
    xdv_logger_pkg.info(
          a_log_ctx => v_log_ctx,
          a_log_msg => 'Disabling Scheduler...');

    IF (is_scheduler_enabled()) THEN
      DBMS_SCHEDULER.DISABLE(pc_scheduler_job_name, a_forced_disable);
    ELSE
        xdv_logger_pkg.info(
            a_log_ctx => v_log_ctx,
            a_log_msg => 'Scheduler Job is already disabled.');
    END IF;

    BEGIN
      DBMS_SCHEDULER.STOP_JOB(pc_scheduler_job_name, a_forced_stop);
    EXCEPTION
      WHEN ex_job_not_running THEN
        xdv_logger_pkg.info(
            a_log_ctx => v_log_ctx,
            a_log_msg => 'Scheduler Job is not running.');
    END;

    xdv_logger_pkg.info(
          a_log_ctx => v_log_ctx,
          a_log_msg => 'Scheduler has been disabled...');

  END disable_scheduler;
  ------------------------------------------------------------------

  PROCEDURE  schedule_msg_process_loop IS
  BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
            job_name             => pc_scheduler_job_name,
            job_type             => 'STORED_PROCEDURE',
            job_action           => 'xdv_scheduler_pkg.message_process_loop',
            start_date           => SYSTIMESTAMP,
            repeat_interval      => 'FREQ=MINUTELY; INTERVAL=1;',
            enabled              => TRUE,
            comments             => 'XDV Scheduler message processing job');
  END schedule_msg_process_loop;

  PROCEDURE  unschedule_msg_process_loop(
                a_force_drop      BOOLEAN DEFAULT FALSE) IS
  BEGIN
    disable_scheduler(a_forced_disable => true);

    DBMS_SCHEDULER.DROP_JOB (
            job_name             => pc_scheduler_job_name,
            force                => a_force_drop);

  END unschedule_msg_process_loop;


  PROCEDURE drop_all_schedules_and_jobs IS
    l_sch_lock_handle VARCHAR2(128);
    l_lock_ret_value  INTEGER;
  BEGIN
    l_sch_lock_handle := get_scheduler_lock_handle();

    l_lock_ret_value := DBMS_LOCK.REQUEST(lockhandle => l_sch_lock_handle,
                                          lockmode =>  DBMS_LOCK.X_MODE,
                                          timeout => DBMS_LOCK.MAXWAIT,
                                          release_on_commit => FALSE);
    IF (l_lock_ret_value != 0 AND l_lock_ret_value != 4) THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx,
                            a_log_msg => 'drop_all_schedules_and_jobs: Error obtaining Scheduler lock: ' || l_lock_ret_value);
      RAISE_APPLICATION_ERROR(pc_scheduler_application_error,
                             'drop_all_schedules_and_jobs: Error obtaining Scheduler lock.');
    END IF;

    xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                         a_log_msg => 'Deleting all scheduled jobs, jobs and schedules...');

    EXECUTE IMMEDIATE('TRUNCATE TABLE xdv_sch_job_run_log_t');
    DELETE FROM xdv_sch_locked_res_t;
    DELETE FROM xdv_sch_job_run_queue_t;
    DELETE FROM xdv_sch_job_args_t;
    DELETE FROM xdv_sch_sch_job_t;
    DELETE FROM xdv_sch_sched_t;
    DELETE FROM xdv_sch_job_resources_t;
    DELETE FROM xdv_sch_job_t;

    COMMIT;
    xdv_logger_pkg.info( a_log_ctx => v_log_ctx,
                         a_log_msg => 'Done deleting all scheduled jobs, jobs and schedules.');

    l_lock_ret_value := DBMS_LOCK.RELEASE(lockhandle => l_sch_lock_handle);

    xdv_logger_pkg.debug( a_log_ctx => v_log_ctx,
                          a_log_msg => 'drop_all_schedules_and_jobs: Scheduler lock has been released.');

  END drop_all_schedules_and_jobs;


/*
  ------------------------------------------------------------------
  PROCEDURE  register_db_startup_trigger IS
  BEGIN
    EXECUTE IMMEDIATE
      'CREATE OR REPLACE TRIGGER xdv_sch_db_start_trigger AFTER STARTUP ON DATABASE
        BEGIN
          xdv_scheduler_pkg.database_startup_hook();
        END;';
  END register_db_startup_trigger;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  deregister_db_startup_trigger IS
  BEGIN
    EXECUTE IMMEDIATE
      'DROP TRIGGER xdv_sch_db_start_trigger';
  END deregister_db_startup_trigger;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE database_startup_hook IS
  BEGIN
    --delete runtime info left from the previous session
    DELETE FROM xdv_sch_locked_res_t;
    DELETE FROM xdv_sch_job_run_queue_t;
  END database_startup_hook;
  ------------------------------------------------------------------
*/

  ------------------------------------------------------------------
  PROCEDURE  test_action (
            arg_1 VARCHAR2,
            arg_2 INTEGER,
            arg_3 NUMBER) IS
  BEGIN
      xdv_logger_pkg.warn(
          a_log_ctx => v_log_ctx,
          a_log_msg => 'test_job is being running with the following parameters: ' ||
                       'arg_1="' || arg_1 || '", arg_2=' || arg_2 || ', arg_3=' || arg_3);
  END test_action;
  ------------------------------------------------------------------

  ------------------------------------------------------------------
  PROCEDURE  test_sleep_action (
            sleep_time_sec INTEGER) IS
  BEGIN
    DBMS_LOCK.SLEEP(sleep_time_sec);
  END test_sleep_action;
  ------------------------------------------------------------------


  --------------------------------------------------------------------
  FUNCTION build_job_name           (
              a_object_name          IN VARCHAR2,
              a_job_kind             IN VARCHAR2) RETURN VARCHAR2 IS
    rv_job_name VARCHAR2(64);
  BEGIN

    -- build job name (first identify job kind)
    rv_job_name:=
      CASE a_job_kind
        WHEN pc_job_cat_hdr_purge THEN pc_job_cat_hdr_purge
        WHEN pc_job_cat_hdr_alloc THEN pc_job_cat_hdr_alloc
        WHEN pc_job_cat_hdr_dtflw THEN pc_job_cat_hdr_dtflw
        WHEN pc_job_cat_hdr_parsr THEN pc_job_cat_hdr_parsr
        WHEN pc_job_cat_hdr_trans THEN pc_job_cat_hdr_trans
        WHEN pc_job_cat_hdr_dpump THEN pc_job_cat_hdr_dpump
        WHEN pc_job_cat_hdr_aggrg THEN pc_job_cat_hdr_aggrg
        ELSE NULL
      END;

    IF (rv_job_name IS NOT NULL) THEN
      rv_job_name := rv_job_name || SUBSTR(UPPER(a_object_name), 1, 30 - LENGTH(rv_job_name));
    ELSE
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx,
                           a_log_msg => 'While trying to build a job_name using object_name and job_kind, I could not identify the job_kind [' ||
                                         a_job_kind || ']. Please investigate!');
    END IF;

    RETURN rv_job_name;

  END build_job_name;


  FUNCTION  build_schedule_name     (
              a_object_name          IN VARCHAR2,
              a_sched_kind           IN VARCHAR2) RETURN VARCHAR2 IS
    rv_sched_name  VARCHAR2(64);
  BEGIN
    -- build job name (first identify job kind)
    -- build job name (first identify job kind)
    rv_sched_name :=
      CASE a_sched_kind
        WHEN pc_sch_cat_hdr_purge THEN pc_sch_cat_hdr_purge
        WHEN pc_sch_cat_hdr_alloc THEN pc_sch_cat_hdr_alloc
        WHEN pc_sch_cat_hdr_dtflw THEN pc_sch_cat_hdr_dtflw
        WHEN pc_sch_cat_hdr_parsr THEN pc_sch_cat_hdr_parsr
        WHEN pc_sch_cat_hdr_trans THEN pc_sch_cat_hdr_trans
        WHEN pc_sch_cat_hdr_dpump THEN pc_sch_cat_hdr_dpump
        WHEN pc_sch_cat_hdr_aggrg THEN pc_sch_cat_hdr_aggrg
        ELSE NULL
      END;

    IF (rv_sched_name IS NOT NULL) THEN
      rv_sched_name := rv_sched_name || SUBSTR(UPPER(a_object_name), 1, 30 - LENGTH(rv_sched_name));
    ELSE
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx,
                           a_log_msg => 'While trying to build a schedule_name, I could not identify the schedule kind [' ||
                                        a_sched_kind || ']. Please investigate!');
    END IF;

    RETURN rv_sched_name;
  END build_schedule_name;

  FUNCTION  build_scheduled_job_name            (
                a_job_name                  IN VARCHAR2,
                a_sched_name                IN VARCHAR2) RETURN VARCHAR2 IS
    rv_sched_name  VARCHAR2(64);
  BEGIN
    rv_sched_name := SUBSTR(UPPER(a_job_name || '_' || a_sched_name), 1, 64);
    RETURN rv_sched_name;
  END build_scheduled_job_name;

--------------------------------------------------------------------
  /**
    Procedure checks for a particular schedule and returns
    the number of jobs that use this schedule.

    In case schedule does not exist this will return -1 else
    depending on jobs allocated to this scheduler could be : 0,1, ...

  FUNCTION  check_schedule          (
              a_sched_name           IN VARCHAR2) RETURN NUMBER IS
    l_schedule_name   VARCHAR2(30);
    l_schedule_type   VARCHAR2(8);
    l_start_date      TIMESTAMP(6) WITH TIME ZONE;
    l_repeat_interval VARCHAR2(4000);
    l_sel_stmt        VARCHAR2(4000);
    rv_sched_cnt      INTEGER;
  BEGIN

    -- check if SCHEDULE already exists -> if YES then do nothing (if not forced) else drop/recreate
    l_sel_stmt :=               ' SELECT COUNT(*) ';
    l_sel_stmt := l_sel_stmt || '   FROM USER_SCHEDULER_SCHEDULES sched ';
    l_sel_stmt := l_sel_stmt || '  WHERE sched.schedule_name = :sched_name';

    EXECUTE IMMEDIATE l_sel_stmt INTO rv_sched_cnt USING IN a_sched_name;

    IF (rv_sched_cnt > 0) THEN

      l_sel_stmt :=               ' SELECT sched.schedule_name, sched.schedule_type, sched.start_date, sched.repeat_interval ';
      l_sel_stmt := l_sel_stmt || '   FROM USER_SCHEDULER_SCHEDULES sched ';
      l_sel_stmt := l_sel_stmt || '  WHERE sched.schedule_name = :sched_name';

      EXECUTE IMMEDIATE l_sel_stmt INTO l_schedule_name, l_schedule_type, l_start_date, l_repeat_interval USING IN a_sched_name;

    ELSE
      rv_sched_cnt := -1;
    END IF;

    RETURN rv_sched_cnt;

  END check_schedule;
  */
--------------------------------------------------------------------
  /**
    Wrapper procedure for drop_schedule action.
    Will forcefully (or not) drop a schedule

  PROCEDURE drop_schedule           (
                a_sched_name         IN VARCHAR2,
                a_force_stop         IN BOOLEAN) IS
  BEGIN

    DBMS_SCHEDULER.DROP_SCHEDULE(
          schedule_name => a_sched_name,
          force         => a_force_stop);

    IF (xdv_logger_pkg.isDebugEnabled(a_log_ctx => v_log_ctx) = TRUE) THEN
      xdv_logger_pkg.debug( a_log_ctx => v_log_ctx, a_log_msg => (CASE WHEN a_force_stop = TRUE THEN 'Force d' ELSE 'D' END)  || 'ropped schedule [' || a_sched_name || '].');
    END IF;

  EXCEPTION
    WHEN ex_non_existing_object        THEN
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to drop schedule: ' || a_sched_name || ' - doesn''t exist.');
    WHEN ex_object_dependents_detected THEN
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to drop schedule: ' || a_sched_name || ' - other objects depend on it.');
    WHEN OTHERS THEN
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to drop schedule: ' || a_sched_name || ' - other issues.');
  END drop_schedule;
  */

--------------------------------------------------------------------
  /**
    Wrapper procedure for create_schedule action.
    Will forcefully (or not) drop a schedule

  FUNCTION   create_schedule         (
                a_sched_name         IN VARCHAR2,
                a_start_date         IN TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP,
                a_sched_rep_interval IN VARCHAR2,
                a_sched_comments     IN VARCHAR2 DEFAULT NULL,
                a_forced_recreate    IN BOOLEAN) RETURN BOOLEAN IS
    l_sel_stmt        VARCHAR2(4000);
    l_schedule_name   VARCHAR2(30);
    l_schedule_type   VARCHAR2(8);
    l_start_date      TIMESTAMP(6) WITH TIME ZONE;
    l_repeat_interval VARCHAR2(4000);
    l_sched_cnt       NUMBER;
    rv_create_sched   BOOLEAN;
  BEGIN

    rv_create_sched := FALSE;

    IF (a_sched_rep_interval IS NOT NULL AND LENGTH(a_sched_rep_interval) > 0) THEN

      -- check if SCHEDULE already exists -> if YES then do nothing (if not forced) else drop/recreate
      l_sel_stmt :=               ' SELECT COUNT(*) ';
      l_sel_stmt := l_sel_stmt || '   FROM USER_SCHEDULER_SCHEDULES sched ';
      l_sel_stmt := l_sel_stmt || '  WHERE sched.schedule_name = :sched_name';

      EXECUTE IMMEDIATE l_sel_stmt INTO l_sched_cnt USING IN a_sched_name;

      IF (l_sched_cnt > 0) THEN

        l_sel_stmt :=               ' SELECT sched.schedule_name, sched.schedule_type, sched.start_date, sched.repeat_interval ';
        l_sel_stmt := l_sel_stmt || '   FROM USER_SCHEDULER_SCHEDULES sched ';
        l_sel_stmt := l_sel_stmt || '  WHERE sched.schedule_name = :sched_name';

        EXECUTE IMMEDIATE l_sel_stmt INTO l_schedule_name, l_schedule_type, l_start_date, l_repeat_interval USING IN a_sched_name;

        -- @TODO : do extra testing with --l_schedule_type, l_start_date, l_repeat_interval-- IF/WHEN needed
        IF (l_repeat_interval NOT LIKE a_sched_rep_interval) THEN
          xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'While creating schedule [' || a_sched_name || '] found out that there is already an existing schedule with this name and has a different interval [' || a_sched_rep_interval || '] than the existing one [' || l_repeat_interval || '].');
        END IF;

        IF (a_forced_recreate = TRUE) THEN
          rv_create_sched := TRUE;

          -- wipe out the existing one
          drop_schedule(a_sched_name => l_schedule_name, a_force_stop => FALSE);
        END IF;
      ELSE
        rv_create_sched := TRUE;
      END IF;

      -- IF (a_forced_recreate)
      IF (rv_create_sched = TRUE) THEN
        DBMS_SCHEDULER.CREATE_SCHEDULE(
               schedule_name   => a_sched_name,
               start_date      => a_start_date,
               repeat_interval => a_sched_rep_interval,
               end_date        => NULL,
               comments        => CASE WHEN a_sched_comments IS NULL THEN a_sched_name || ' schedule' ELSE a_sched_comments END);

        IF (xdv_logger_pkg.isDebugEnabled(a_log_ctx => v_log_ctx) = TRUE) THEN
          xdv_logger_pkg.debug( a_log_ctx => v_log_ctx, a_log_msg => 'Created schedule [' || a_sched_name || '] with start timestamp [' || TO_CHAR(a_start_date, pc_dt_mid_endian_sec) || '] and schedule-interval [' || a_sched_rep_interval || '].');
        END IF;
      END IF;

    ELSE
      xdv_logger_pkg.debug( a_log_ctx => v_log_ctx, a_log_msg => 'While creating schedule [' || a_sched_name || '] found out that there is no schedule-interval defined! Please define one prior to calling this procedure.');
    END IF;

    RETURN rv_create_sched;

  EXCEPTION
    WHEN ex_object_already_exists THEN
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to create schedule "'|| a_sched_name ||'" already exists.');
      RETURN FALSE;
  END create_schedule;
  */

--------------------------------------------------------------------
  /**
     An overloaded function of the previous wrapper that will automatically look up
     the right schedule-pattern based on the name of the schedule.

  FUNCTION   create_schedule         (
                a_sched_name         IN VARCHAR2,
                a_start_date         IN TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP,
                a_forced_recreate    IN BOOLEAN) RETURN BOOLEAN IS
    l_sched_rep_ivl  VARCHAR2(256);
    rv_status        BOOLEAN;
  BEGIN

    l_sched_rep_ivl := NULL;

    -- identify schedule and apply correct schedule-interval
    l_sched_rep_ivl:=
      CASE
        WHEN a_sched_name LIKE pc_def_sch_pur_delt THEN pc_def_si_pur_delt
        WHEN a_sched_name LIKE pc_def_sch_pur_part THEN pc_def_si_pur_part
        WHEN a_sched_name LIKE pc_def_sch_alo_part THEN pc_def_si_alo_part
        ELSE NULL
      END;

    IF (l_sched_rep_ivl IS NULL) THEN
      xdv_logger_pkg.error( a_log_ctx => v_log_ctx, a_log_msg => 'While creating schedule [' || a_sched_name || '] found out that there is no predefined schedule-interval! Please define one (in the source code) prior to calling this procedure.');
    END IF;

    rv_status := create_schedule(
                a_sched_name         => a_sched_name,
                a_start_date         => a_start_date,
                a_sched_rep_interval => l_sched_rep_ivl,
                a_sched_comments     => NULL,
                a_forced_recreate    => a_forced_recreate);

    RETURN rv_status;

  END create_schedule;
  */
--------------------------------------------------------------------
-- job specific procedures
--------------------------------------------------------------------

--------------------------------------------------------------------
  /**
     Wrapper procedure for scheduler.drop_job.
     Contains prechecks for existence of jobs to drop .
     Also may automatically drop empty schedules if configured so.

  PROCEDURE drop_job (
              a_job_name           IN VARCHAR2,
              a_force_stop         IN BOOLEAN,
              a_drop_sched         IN BOOLEAN DEFAULT TRUE) IS
    l_sel_stmt       VARCHAR2(4000);
    l_rem_job_count  NUMBER;
    l_sch_job_count  NUMBER;
    l_qrd_sched_name VARCHAR2(4000);
  BEGIN

    -- check if job exists together with schedule
    l_sel_stmt :=               ' SELECT COUNT(*) ';
    l_sel_stmt := l_sel_stmt || '   FROM USER_SCHEDULER_JOBS scjbs ';
    l_sel_stmt := l_sel_stmt || '  WHERE scjbs.job_name LIKE :job_name';

    EXECUTE IMMEDIATE l_sel_stmt INTO l_sch_job_count USING IN a_job_name;

    IF (l_sch_job_count > 0) THEN

      -- find out to which schedule this job belongs to
      l_sel_stmt :=               ' SELECT scjbs.schedule_name ';
      l_sel_stmt := l_sel_stmt || '   FROM USER_SCHEDULER_JOBS scjbs ';
      l_sel_stmt := l_sel_stmt || '  WHERE scjbs.job_name LIKE :job_name';

      EXECUTE IMMEDIATE l_sel_stmt INTO l_qrd_sched_name USING IN a_job_name;

      -- check if scheduler has more jobs ... if not than drop scheduler as well
      --SELECT scjbs.JOB_NAME, scjbs.JOB_TYPE, scjbs.JOB_ACTION
      l_sel_stmt :=               ' SELECT COUNT(*) ';
      l_sel_stmt := l_sel_stmt || '   FROM USER_SCHEDULER_JOBS scjbs ';
      l_sel_stmt := l_sel_stmt || '  WHERE scjbs.SCHEDULE_NAME LIKE :sched_name';

      EXECUTE IMMEDIATE l_sel_stmt INTO l_rem_job_count USING IN l_qrd_sched_name;

      DBMS_SCHEDULER.DROP_JOB(job_name => a_job_name, force => a_force_stop);

      IF ((l_rem_job_count = 1) AND (a_drop_sched = TRUE)) THEN
        drop_schedule(a_sched_name => l_qrd_sched_name, a_force_stop => FALSE);
      END IF;

      IF (xdv_logger_pkg.isDebugEnabled(a_log_ctx => v_log_ctx) = TRUE) THEN
        IF ((l_rem_job_count = 1) AND (a_drop_sched = TRUE)) THEN
          l_sel_stmt := 'together with the schedule ['  || l_qrd_sched_name || '] to which job belongs to, since there are no more jobs using this schedule.';
        ELSE
          l_sel_stmt := 'But kept the schedule ['       || l_qrd_sched_name || '] to which job belongs to, as there are ' || (l_rem_job_count - 1) || ' more jobs using this schedule or schedule-drop was not requested';
        END IF;

        xdv_logger_pkg.debug( a_log_ctx => v_log_ctx, a_log_msg => 'Dropped job [' || a_job_name || ']. ' || l_sel_stmt);
      END IF;

    ELSE
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to drop job: ' || a_job_name || ' doesn''t exist or is not a job; as I could not find it in USER_SCHEDULER_JOBS table.');
    END IF;

  EXCEPTION
  WHEN ex_non_existing_object OR ex_non_job_object THEN
    -- these exceptions might never been thrown since there is a COUNT prior to the drop-calls
    xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to drop job: ' || a_job_name || ' doesn''t exist or is not a job.');
  END drop_job;


--*/------------------------------------------------------------------
  /**
     Overloaded procedure for other drop_job,
     Takes hassle away by building job name from object_name and job_kind

  PROCEDURE drop_job                (
              a_object_name          IN VARCHAR2,
              a_job_kind             IN VARCHAR2,
              a_force_stop           IN BOOLEAN,
              a_drop_sched           IN BOOLEAN DEFAULT TRUE) IS
    l_job_name       VARCHAR2(30);
  BEGIN
    l_job_name := build_job_name (
              a_object_name => a_object_name,
              a_job_kind    => a_job_kind);

    drop_job(
        a_job_name   => l_job_name,
        a_force_stop => a_force_stop,
        a_drop_sched => a_drop_sched);

  END drop_job;
*/

--------------------------------------------------------------------
  /**
     Schedule must be created already and name must be valid.

     Limitation : currently defaults to 'DEFAULT_JOB_CLASS'. If needed extend on demand!

  PROCEDURE  create_job              (
              a_sched_name           IN VARCHAR2,
              a_job_name             IN VARCHAR2,
              a_job_type             IN VARCHAR2,
              a_abs_stproc_name      IN VARCHAR2,
              a_job_args             IN tn_job_args,
              a_job_comments         IN VARCHAR2) IS
    l_sel_stmt        VARCHAR2(4000);
    l_arg_cnt         NUMBER;
    l_sched_cnt       NUMBER;
    l_idx             NUMBER;
  BEGIN

    -- check for schedule name validity
    IF (a_sched_name IS NULL OR LENGTH(a_sched_name) = 0 OR LENGTH(a_sched_name) > 30) THEN
      RAISE ex_invalid_schedule_name;
    ELSE

      l_sel_stmt :=               ' SELECT COUNT(*) ';
      l_sel_stmt := l_sel_stmt || '   FROM USER_SCHEDULER_SCHEDULES sched ';
      l_sel_stmt := l_sel_stmt || '  WHERE sched.schedule_name = :sched_name';

      EXECUTE IMMEDIATE l_sel_stmt INTO l_sched_cnt USING IN a_sched_name;

      IF (l_sched_cnt IS NULL OR l_sched_cnt = 0) THEN
        RAISE ex_non_existing_schedule;
      END IF;

    END IF;

    -- check for job name validity
    IF (a_job_name IS NULL OR LENGTH(a_job_name) = 0 OR LENGTH(a_job_name) > 30) THEN
      RAISE ex_invalid_job_name;
    END IF;

    -- check for job type validity (can be only one of four values defined in the spec of this package)
    IF (NOT(   a_job_type LIKE pc_job_type_pls
            OR a_job_type LIKE pc_job_type_stp
            OR a_job_type LIKE pc_job_type_exe
            OR a_job_type LIKE pc_job_type_chn)) THEN
      RAISE ex_invalid_job_type;
    END IF;

    -- create the job (disabled)
    DBMS_SCHEDULER.CREATE_JOB(
        job_name            => a_job_name,
        schedule_name       => a_sched_name,
        job_type            => a_job_type,
        job_action          => a_abs_stproc_name,
        number_of_arguments => a_job_args.LAST,
        job_class           => 'DEFAULT_JOB_CLASS',
        enabled             => FALSE,
        auto_drop           => TRUE,
        comments            => a_job_comments);

    l_idx := 1;
    FOR i IN a_job_args.FIRST..a_job_args.LAST
    LOOP
      DBMS_SCHEDULER.SET_JOB_ANYDATA_VALUE(a_job_name, l_idx, a_job_args(i));
      l_idx := l_idx + 1;
    END LOOP;

    DBMS_SCHEDULER.ENABLE(a_job_name);

    IF (xdv_logger_pkg.isDebugEnabled(a_log_ctx => v_log_ctx) = TRUE) THEN
      xdv_logger_pkg.debug( a_log_ctx => v_log_ctx, a_log_msg => 'Created and scheduled job [' || a_job_name || '] to use schedule [' || a_sched_name || '], job-action being [' || a_abs_stproc_name || '] with ' || a_job_args.LAST || ' arguments.');
    END IF;

  EXCEPTION
  WHEN ex_object_already_exists THEN
    xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to create job: ' || a_job_name || ' already exists.');

  END create_job;
*/

--------------------------------------------------------------------
  /**
    Wrapper procedure for (program, job, chain, window, or window group)-disable
    As this procedure is too small and insignifficant .. will not pop EXCEPTION but return a status-boolean if things went wrong

  FUNCTION  disable_job             (
              a_job_name             IN VARCHAR2,
              a_force                IN BOOLEAN) RETURN BOOLEAN IS
    l_sel_stmt        VARCHAR2(4000);
    l_sch_job_count   NUMBER;
    rv_status         BOOLEAN;
  BEGIN

    rv_status := TRUE;

    -- check if job exists together with schedule
    l_sel_stmt :=               ' SELECT COUNT(*) ';
    l_sel_stmt := l_sel_stmt || '   FROM USER_SCHEDULER_JOBS scjbs ';
    l_sel_stmt := l_sel_stmt || '  WHERE scjbs.job_name LIKE :job_name';

    EXECUTE IMMEDIATE l_sel_stmt INTO l_sch_job_count USING IN a_job_name;

    IF (l_sch_job_count > 0) THEN
      DBMS_SCHEDULER.DISABLE ( name => a_job_name, force => a_force);
    ELSE
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to disable job: ' || a_job_name || ' as it does not exist.');
      rv_status := FALSE;
    END IF;

    RETURN rv_status;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN FALSE;
  END disable_job;

  */
--------------------------------------------------------------------
  /**
    Wrapper procedure for (program, job, chain, window, or window group)-enable
    As this procedure is too small and insignifficant .. will not pop EXCEPTION but return a status-boolean if things went wrong

  FUNCTION  enable_job              (
              a_job_name             IN VARCHAR2) RETURN BOOLEAN IS
    l_sel_stmt        VARCHAR2(4000);
    l_sch_job_count   NUMBER;
    rv_status         BOOLEAN;
  BEGIN

    rv_status := TRUE;

    -- check if job exists together with schedule
    l_sel_stmt :=               ' SELECT COUNT(*) ';
    l_sel_stmt := l_sel_stmt || '   FROM USER_SCHEDULER_JOBS scjbs ';
    l_sel_stmt := l_sel_stmt || '  WHERE scjbs.job_name LIKE :job_name';

    EXECUTE IMMEDIATE l_sel_stmt INTO l_sch_job_count USING IN a_job_name;

    IF (l_sch_job_count > 0) THEN
      DBMS_SCHEDULER.ENABLE ( name => a_job_name);
    ELSE
      xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to enable job: ' || a_job_name || ' as it does not exist.');
      rv_status := FALSE;
    END IF;

    RETURN rv_status;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN FALSE;
  END enable_job;

  */

--------------------------------------------------------------------
  /**
    Will be implemented on demand!!!

    It is supposed to unload a job from a schedule and reload it to some other (existing) schedule.

  FUNCTION  reschedule_job          (
              a_job_name             IN VARCHAR2,
              a_sched_name           IN VARCHAR2) RETURN BOOLEAN IS
  BEGIN
    NULL;

    -- check for job and new schedule

    -- save job params

    -- drop job

    -- recreate job within new schedule

  END reschedule_job;

  */

--------------------------------------------------------------------
-- Sanity and status checks
--------------------------------------------------------------------

  FUNCTION  transfer_job_logs       (
              a_job_name             IN VARCHAR2,
              a_start_ts             IN DATE) RETURN BOOLEAN IS
  BEGIN

   RETURN FALSE;
  END transfer_job_logs;


  PROCEDURE purge_sched_job_logs    (
              a_job_name             IN VARCHAR2,
              a_end_ts               IN DATE) IS
  BEGIN
   NULL;
  END purge_sched_job_logs;


--------------------------------------------------------------------
  /**
    Will be implemented on demand!!!

    It is supposed to analyze all jobs that run for a specific date (day) and report on overlaps
        sort of a dumb advisor on where things overlap so that one could decide how to better spread
        certain jobs
  */
  FUNCTION  analyze_daily_overlaps  (
              a_date                 IN DATE) RETURN BOOLEAN IS
  BEGIN
    RETURN FALSE;
  END analyze_daily_overlaps;



--PARTITION CONTROL


--------------------------------------------------------------------
/*
--------------------------------------------------------------------
  FUNCTION create_job (
    a_sched_name                     IN VARCHAR2,
      a_object_type                    IN VARCHAR2,
      a_object_name                    IN VARCHAR2,
      a_directory                        IN VARCHAR2,
      a_span_ivl                        IN INTEGER) RETURN VARCHAR2 IS
      rv_job_name    VARCHAR2(64);
  BEGIN

    -- build job name
    rv_job_name := xdv_tablespace_pkg.get_relative_unit_name(UPPER(a_object_name),pc_job_name_body);

    -- create the job (disabled) as STORED_PROCEDURE with arguments and send in "process" (stp) as job
      dbms_scheduler.create_job(
        job_name            => rv_job_name,
        schedule_name       => a_sched_name,
        job_type            => pc_job_type_stp,
          job_action          => pc_def_job_stp,
        number_of_arguments => 4,
        job_class           => 'DEFAULT_JOB_CLASS',
          enabled             => FALSE,
        auto_drop           => TRUE,
        comments            => 'DD partition allocation job for ' || LOWER(a_object_type) || ' ' || a_object_name);

      dbms_scheduler.set_job_argument_value(rv_job_name, 1, a_object_type);
      dbms_scheduler.set_job_argument_value(rv_job_name, 2, a_object_name);
      dbms_scheduler.set_job_argument_value(rv_job_name, 3, a_directory);
      dbms_scheduler.set_job_argument_value(rv_job_name, 4, TO_CHAR(a_span_ivl));

      dbms_scheduler.enable(rv_job_name);

    IF (xdv_logger_pkg.isDebugEnabled(a_log_ctx => v_log_ctx) = TRUE) THEN
      xdv_logger_pkg.debug( a_log_ctx => v_log_ctx, a_log_msg => 'Created and scheduled job "' || rv_job_name || '".');
    END IF;

    RETURN rv_job_name;

  EXCEPTION
  WHEN ex_object_already_exists THEN
    xdv_logger_pkg.warn( a_log_ctx => v_log_ctx, a_log_msg => 'Unable to create job: ' || rv_job_name || ' already exists.');
    RETURN NULL;

  END create_job;

*/



--------------------------------------------------------------------


/*=========================*/
/*===== Pkg Main Block ====*/
/*=========================*/
--------------------------------------------------------------------
BEGIN

  start_logger;

END xdv_scheduler_pkg;
/

show errors
