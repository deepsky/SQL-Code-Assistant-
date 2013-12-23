CREATE OR REPLACE PACKAGE BODY xdv_flow_manager_pkg
AS
/******************************************************************************
   NAME:       xdv_flow_manager_pkg
   PURPOSE:

   REVISIONS:
   Ver        Date        Author           Description
   ---------  ----------  ---------------  ------------------------------------
   1.0        04/10/2007  dvarnich         1. Created this package.

   TO DO:
   a) ...

   NOTES:
   a)

     -- $Id: xdv_flow_manager_pkg.pkb,v 1.23 2009/01/22 21:29:29 rtarczal Exp $
******************************************************************************/

/*=========================*/
/*======= Variables =======*/
/*=========================*/

/*=========================*/
/*==== Private Methods ====*/
/*=========================*/


--------------------------------------------------------------------
-- generic procedures
--------------------------------------------------------------------


  FUNCTION get_timestamp_tz_to_sec (a_ts_tz IN TIMESTAMP WITH TIME ZONE) RETURN INTEGER
  IS
  BEGIN
    RETURN xdv_date_pkg.DATE_TO_SEC(a_ts_tz AT TIME ZONE pc_gmt_tz_name);
  END;

  FUNCTION flow_interval_to_string (a_ivl IN r_flow_interval) RETURN VARCHAR2
  IS
  BEGIN
    RETURN 'a_start_dde_id = '   || a_ivl.a_start_dde_id ||
           ', a_start_dte_id = ' || a_ivl.a_start_dte_id ||
           ', a_end_dde_id = '   || a_ivl.a_end_dde_id ||
           ', a_end_dte_id = '   || a_ivl.a_end_dte_id;
  END;

  FUNCTION fmn_context_to_string (a_flow_ctx IN r_flow_context) RETURN VARCHAR2
  IS
  BEGIN
    RETURN 'Flow Context: FMN ID: [' || a_flow_ctx.a_fmn.id ||
           '], Flow Type: [' || a_flow_ctx.a_flow_type.context ||
           '], Stage: [' || a_flow_ctx.a_stage.stage_name ||
           '], Granularity: [' || a_flow_ctx.a_granularity.granularity ||
           '], Upper Bound Date sec : [' || a_flow_ctx.a_upper_bound_dt_sec ||
           '], Timezone Name: [' || a_flow_ctx.a_time_zone.tz_name ||
           '], Backscan Periods: [' || a_flow_ctx.a_back_scan_periods || ']';

  END;

  FUNCTION get_granularity_ivl_sec (a_granularity     IN xdv_prd_granularity_t%ROWTYPE) RETURN INTEGER
  IS
    l_ivl INTEGER;
  BEGIN
    l_ivl :=
      CASE a_granularity.granularity
        WHEN pc_granularity_trn THEN pc_granularity_ivl_trn
        WHEN pc_granularity_15m THEN pc_granularity_ivl_15m
        WHEN pc_granularity_1hr THEN pc_granularity_ivl_1hr
        WHEN pc_granularity_1dy THEN pc_granularity_ivl_1dy
        ELSE NULL
      END;
     RETURN l_ivl;
  END;

  FUNCTION get_sec_from_dde_dte_id(a_dde_id IN INTEGER, a_dte_id IN INTEGER) RETURN INTEGER
  IS
  BEGIN
    RETURN a_dde_id + a_dte_id;
  END;

  FUNCTION get_dde_id_from_sec(a_sec IN INTEGER) RETURN INTEGER
  IS
  BEGIN
    RETURN FLOOR(a_sec / xdv_generic_const_pkg.pc_dde_ivl) * xdv_generic_const_pkg.pc_dde_ivl;
  END;

  FUNCTION get_dte_id_from_sec(a_sec IN INTEGER) RETURN INTEGER
  IS
  BEGIN
    RETURN MOD(a_sec, xdv_generic_const_pkg.pc_dde_ivl);
  END;

  FUNCTION get_flow_max_wait_time(a_fmn        xdv_prd_flow_matrix_node_t%ROWTYPE) RETURN INTEGER
  IS
    l_sec INTEGER;
    l_granularity         xdv_prd_granularity_t%ROWTYPE;
    l_stage               xdv_prd_stage_ref_def_t%ROWTYPE;
  BEGIN
    SELECT grn.* INTO l_granularity
    FROM xdv_prd_granularity_t grn
    WHERE a_fmn.gra_id = grn.id;

    SELECT stg.* INTO l_stage
    FROM xdv_prd_stage_ref_def_t stg
    WHERE a_fmn.stg_id = stg.id;

    --get value from configuration or return defaults
    l_sec :=
      CASE l_granularity.granularity
        WHEN pc_granularity_trn THEN xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_mw_trn_name, pc_mw_trn_dflt, TRUE) * 60
        WHEN pc_granularity_15m THEN xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_mw_15m_name, pc_mw_15m_dflt, TRUE) * 60
        WHEN pc_granularity_1hr THEN xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_mw_1hr_name, pc_mw_1hr_dflt, TRUE) * 60
        WHEN pc_granularity_1dy THEN xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_mw_1dy_name, pc_mw_1dy_dflt, TRUE) * 3600
        WHEN pc_granularity_1mo THEN xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_mw_1mo_name, pc_mw_1mo_dflt, TRUE) * 3600
        ELSE NULL
      END;

    IF l_sec IS NOT NULL AND LOWER(l_stage.LAYER) = 'xdvrpt' THEN
      IF l_stage.stage_name = 'Reporting' THEN
        l_sec := l_sec + xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_rpt_dly_name, pc_rpt_dly_dflt, TRUE) * 60;
      ELSIF l_stage.stage_name = 'Reporting Staging' THEN
        l_sec := l_sec + xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_rps_dly_name, pc_rps_dly_dflt, TRUE) * 60;
      END IF;
    END IF;

    RETURN l_sec;
  END;

  FUNCTION get_flow_log_rec(a_fmn                 IN xdv_prd_flow_matrix_node_t%ROWTYPE,
                            a_flow_interval       IN r_flow_interval) RETURN xdv_agr_aggregator_log_t%ROWTYPE
  IS
    l_flow_log  xdv_agr_aggregator_log_t%ROWTYPE;
  BEGIN
    SELECT * INTO l_flow_log
    FROM xdv_agr_aggregator_log_t
    WHERE tmn_id = a_fmn.id AND
          dds_id = a_flow_interval.a_start_dde_id AND
          dts_id = a_flow_interval.a_start_dte_id AND
          dde_id = a_flow_interval.a_end_dde_id AND
          dte_id = a_flow_interval.a_end_dte_id AND
          dtz_id = a_flow_interval.a_time_zone_id;
    RETURN l_flow_log;
  END;

  FUNCTION get_or_create_flow_log_rec(a_fmn                 IN xdv_prd_flow_matrix_node_t%ROWTYPE,
                                      a_flow_interval       IN r_flow_interval) RETURN xdv_agr_aggregator_log_t%ROWTYPE
  IS
    l_flow_log  xdv_agr_aggregator_log_t%ROWTYPE;
  BEGIN
    l_flow_log := get_flow_log_rec(a_fmn, a_flow_interval);
    RETURN l_flow_log;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      --l_flow_log := xdv_agr_aggregator_log_t%ROWTYPE;
      SELECT xdv_agr_agl_seq.nextval INTO l_flow_log.id FROM dual;
      l_flow_log.tmn_id := a_fmn.id;
      l_flow_log.dds_id := a_flow_interval.a_start_dde_id;
      l_flow_log.dts_id := a_flow_interval.a_start_dte_id;
      l_flow_log.dde_id := a_flow_interval.a_end_dde_id;
      l_flow_log.dte_id := a_flow_interval.a_end_dte_id;
      l_flow_log.dtz_id := a_flow_interval.a_time_zone_id;
      l_flow_log.start_tstamp := NULL;
      l_flow_log.end_tstamp := NULL;
      l_flow_log.result := pc_flow_result_pending;
      l_flow_log.log_message := 'Created log record on ' || SYSDATE;

      INSERT INTO xdv_agr_aggregator_log_t VALUES l_flow_log;

      RETURN l_flow_log;
  END;

  PROCEDURE update_flow_log_rec(a_flow_log IN xdv_agr_aggregator_log_t%ROWTYPE)
  IS
  BEGIN
    UPDATE xdv_agr_aggregator_log_t
    SET
      start_tstamp = a_flow_log.start_tstamp,
      end_tstamp = a_flow_log.end_tstamp,
      result = a_flow_log.result,
      log_message = a_flow_log.log_message
    WHERE id = a_flow_log.id;
  END;

  FUNCTION is_flow_completed(a_fmn                 IN xdv_prd_flow_matrix_node_t%ROWTYPE,
                             a_flow_interval       IN r_flow_interval) RETURN BOOLEAN
  IS
    l_flow_log      xdv_agr_aggregator_log_t%ROWTYPE;
    l_max_wait_time INTEGER;
  BEGIN
    xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                         a_log_msg => 'Checking flow status of FMN id=[' || a_fmn.id ||
                                      '], Interval: [' || flow_interval_to_string(a_flow_interval) || '].');

    l_flow_log :=  get_flow_log_rec(a_fmn, a_flow_interval);

    IF (l_flow_log.result = pc_flow_result_complete) OR (l_flow_log.result = pc_flow_result_invalid) THEN
      xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                           a_log_msg => 'Flow job has COMPLETED: FMN id=[' || a_fmn.id ||
                                        '], Interval: [' || flow_interval_to_string(a_flow_interval) || '].');
      RETURN TRUE;
    ELSE
      xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                           a_log_msg => 'Flow job has NOT COMPLETED: FMN id=[' || a_fmn.id ||
                                        '], Interval: [' || flow_interval_to_string(a_flow_interval) || '].');
      RETURN FALSE;
    END IF;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      -- no log record means system don't have data for this period
      xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                           a_log_msg => 'Flow of job has no log record. FMN id=[' || a_fmn.id ||
                                        '], Interval: [' || flow_interval_to_string(a_flow_interval) || '].');

      l_max_wait_time := get_flow_max_wait_time(a_fmn);
      IF( xdv_date_pkg.gmtdate_sec - (a_flow_interval.a_end_dde_id + a_flow_interval.a_end_dte_id) >= l_max_wait_time ) THEN
        xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                             a_log_msg => 'Flow of job has no log record and maximum waiting time [' || l_max_wait_time ||
                                          '] has been reached. Treating as COMPLETED: FMN id=[' || a_fmn.id ||
                                          '], Interval: [' || flow_interval_to_string(a_flow_interval) || '].');
        RETURN TRUE;
      ELSE
        xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                             a_log_msg => 'Flow of job has no log record and maximum waiting time [' || l_max_wait_time ||
                                          '] has not been reached. Treating as NOT COMPLETED: FMN id=[' || a_fmn.id ||
                                          '], Interval: [' || flow_interval_to_string(a_flow_interval) || '].');
      RETURN FALSE;
      END IF;


  END;

  FUNCTION is_sub_flows_completed(a_fmn                 IN xdv_prd_flow_matrix_node_t%ROWTYPE,
                                  a_flow_interval       IN r_flow_interval) RETURN BOOLEAN
  IS
    l_stage               xdv_prd_stage_ref_def_t%ROWTYPE;
    l_granularity         xdv_prd_granularity_t%ROWTYPE;
    l_start_ivl_sec       INTEGER;
    l_end_ivl_sec         INTEGER;

    l_start_sub_ivl_sec   INTEGER;
    l_end_sub_ivl_sec     INTEGER;
    l_sub_ivl             INTEGER;
    l_sub_flow_ivl        r_flow_interval;

    l_subflow_dtz_id      INTEGER;

    l_ready               BOOLEAN := TRUE;
  BEGIN
    SELECT * INTO l_stage FROM xdv_prd_stage_ref_def_t WHERE id = a_fmn.stg_id;

    SELECT * INTO l_granularity
    FROM xdv_prd_granularity_t
    WHERE a_fmn.gra_id = id;

    --only daily and monthly flows can have non GMT time zone
    IF (l_granularity.sec_tspan >= 3600*24) THEN
      l_subflow_dtz_id := a_flow_interval.a_time_zone_id;
    ELSE
      SELECT id INTO l_subflow_dtz_id FROM xdv_dtm_timezone_dt WHERE tz_name = pc_gmt_tz_name;
    END IF;

    xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                         a_log_msg => 'Checking status of flow jobs of FMN id: [' || a_fmn.id || '], Stage: [' || l_stage.stage_name ||
                                      '] included in Interval: [' || flow_interval_to_string(a_flow_interval) || '].');

    l_sub_ivl := get_granularity_ivl_sec(l_granularity);

    l_start_ivl_sec := get_sec_from_dde_dte_id(a_flow_interval.a_start_dde_id, a_flow_interval.a_start_dte_id);
    l_start_ivl_sec := FLOOR(l_start_ivl_sec/l_sub_ivl)*l_sub_ivl;

    l_end_ivl_sec := get_sec_from_dde_dte_id(a_flow_interval.a_end_dde_id, a_flow_interval.a_end_dte_id);

    l_start_sub_ivl_sec := l_start_ivl_sec;
    l_end_sub_ivl_sec := l_start_ivl_sec + l_sub_ivl - 1;

    LOOP
      l_sub_flow_ivl.a_start_dde_id := get_dde_id_from_sec(l_start_sub_ivl_sec);
      l_sub_flow_ivl.a_start_dte_id := get_dte_id_from_sec(l_start_sub_ivl_sec);

      l_sub_flow_ivl.a_end_dde_id := get_dde_id_from_sec(l_end_sub_ivl_sec);
      l_sub_flow_ivl.a_end_dte_id := get_dte_id_from_sec(l_end_sub_ivl_sec);

      l_sub_flow_ivl.a_time_zone_id := l_subflow_dtz_id;

      l_ready := l_ready AND is_flow_completed(a_fmn, l_sub_flow_ivl);

      l_start_sub_ivl_sec := l_end_sub_ivl_sec + 1;
      l_end_sub_ivl_sec := l_start_sub_ivl_sec + l_sub_ivl - 1;

      EXIT WHEN (NOT l_ready) OR (l_end_sub_ivl_sec > l_end_ivl_sec);

    END LOOP;

    IF (l_ready) THEN
      xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                           a_log_msg => 'All flow jobs have COMPLETED: FMN id: [' || a_fmn.id || '], Stage: [' || l_stage.stage_name ||
                                        '] included in Interval: [' || flow_interval_to_string(a_flow_interval) || '].');
    ELSE
      xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                           a_log_msg => 'Some flow jobs have NOT COMPLETED: FMN id: [' || a_fmn.id || '], Stage: [' || l_stage.stage_name ||
                                        '] included in Interval: [' || flow_interval_to_string(a_flow_interval) || '].');
    END IF;
    RETURN l_ready;
  END;

  FUNCTION get_df_job_type_for_fmn(a_to_fmn        IN xdv_prd_flow_matrix_node_t%ROWTYPE) RETURN INTEGER
  IS
    l_jt  INTEGER;
  BEGIN
    l_jt :=
      CASE a_to_fmn.id
        WHEN xdv_generic_const_pkg.pc_fmn_int_asi_trn THEN xdv_generic_const_pkg.pc_dflw_jt_ses_trn
        WHEN xdv_generic_const_pkg.pc_fmn_int_ami_trn THEN xdv_generic_const_pkg.pc_dflw_jt_mes_trn
        WHEN xdv_generic_const_pkg.pc_fmn_int_sgi_trn THEN xdv_generic_const_pkg.pc_dflw_jt_sig_trn
        WHEN xdv_generic_const_pkg.pc_fmn_int_cdi_trn THEN xdv_generic_const_pkg.pc_dflw_jt_cdr_trn
        WHEN xdv_generic_const_pkg.pc_fmn_int_aai_15m THEN xdv_generic_const_pkg.pc_dflw_jt_apa_15m
        WHEN xdv_generic_const_pkg.pc_fmn_int_aai_1hr THEN xdv_generic_const_pkg.pc_dflw_jt_apa_1hr
        WHEN xdv_generic_const_pkg.pc_fmn_int_aai_1dy THEN xdv_generic_const_pkg.pc_dflw_jt_apa_1dy
        WHEN xdv_generic_const_pkg.pc_fmn_int_aai_1mo THEN xdv_generic_const_pkg.pc_dflw_jt_apa_1mo
        WHEN xdv_generic_const_pkg.pc_fmn_int_sai_15m THEN xdv_generic_const_pkg.pc_dflw_jt_sga_15m
        WHEN xdv_generic_const_pkg.pc_fmn_int_sai_1hr THEN xdv_generic_const_pkg.pc_dflw_jt_sga_1hr
        WHEN xdv_generic_const_pkg.pc_fmn_int_sai_1dy THEN xdv_generic_const_pkg.pc_dflw_jt_sga_1dy
        WHEN xdv_generic_const_pkg.pc_fmn_int_sai_1mo THEN xdv_generic_const_pkg.pc_dflw_jt_sga_1mo
        WHEN xdv_generic_const_pkg.pc_fmn_int_cai_15m THEN xdv_generic_const_pkg.pc_dflw_jt_cda_15m
        WHEN xdv_generic_const_pkg.pc_fmn_int_cai_1hr THEN xdv_generic_const_pkg.pc_dflw_jt_cda_1hr
        WHEN xdv_generic_const_pkg.pc_fmn_int_cai_1dy THEN xdv_generic_const_pkg.pc_dflw_jt_cda_1dy
        WHEN xdv_generic_const_pkg.pc_fmn_int_cai_1mo THEN xdv_generic_const_pkg.pc_dflw_jt_cda_1mo
        ELSE NULL
      END;
    RETURN l_jt;
  END;

  FUNCTION get_data_flow_status(a_from_fmn          IN xdv_prd_flow_matrix_node_t%ROWTYPE,
                                a_start_dt_sec    IN INTEGER) RETURN xdv_fst_flow_status_t%ROWTYPE
  IS
    l_df_jt INTEGER;
    l_fst   xdv_fst_flow_status_t%ROWTYPE;
  BEGIN
    l_df_jt := get_df_job_type_for_fmn(a_from_fmn);

    SELECT * INTO l_fst
    FROM xdv_fst_flow_status_t
    WHERE type = l_df_jt AND start_ts = a_start_dt_sec;

    RETURN l_fst;
  END;

  FUNCTION is_transfer_completed(a_from_fmn          IN xdv_prd_flow_matrix_node_t%ROWTYPE,
                                 a_start_dt_sec    IN INTEGER) RETURN BOOLEAN
  IS
    l_fst   xdv_fst_flow_status_t%ROWTYPE;
  BEGIN
    xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                         a_log_msg => 'Checking status of transfer job: From FMN: [' || a_from_fmn.id ||
                                        '], Start date: [' || a_start_dt_sec || '].');

    l_fst := get_data_flow_status(a_from_fmn, a_start_dt_sec);

    IF (l_fst.status = xdv_generic_const_pkg.pc_dflw_js_success) THEN
    xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                         a_log_msg => 'Transfer job has COMPLETED: From FMN: [' || a_from_fmn.id ||
                                      '], Start date: [' || a_start_dt_sec || '].');
      RETURN TRUE;
    ELSE
      xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                           a_log_msg => 'Transfer job has NOT COMPLETED: From FMN: [' || a_from_fmn.id ||
                                        '], Start date: [' || a_start_dt_sec || '].');
      RETURN FALSE;
    END IF;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      -- no log record means system don't have data for this period
      IF(xdv_date_pkg.gmtdate_sec - a_start_dt_sec > get_flow_max_wait_time(a_from_fmn)) THEN
        xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                             a_log_msg => 'Transfer job has no log records and time difference (' ||
                                          xdv_date_pkg.gmtdate_sec || '-' || a_start_dt_sec ||
                                          ' = ' || (xdv_date_pkg.gmtdate_sec - a_start_dt_sec) ||
                                          ') >= max wait time (' || get_flow_max_wait_time(a_from_fmn) ||
                                          '). Treating as COMPLETED: FMN: [' || a_from_fmn.id ||
                                          '], Start date: [' || a_start_dt_sec || '].');
        RETURN TRUE;
      ELSE
        xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                             a_log_msg => 'Transfer job has no log records and time difference (' ||
                                          xdv_date_pkg.gmtdate_sec || '-' || a_start_dt_sec ||
                                          ' = ' || (xdv_date_pkg.gmtdate_sec - a_start_dt_sec) ||
                                          ') < max wait time (' || get_flow_max_wait_time(a_from_fmn) ||
                                          '). Treating as NOT COMPLETED: FMN: [' || a_from_fmn.id ||
                                          '], Start date: [' || a_start_dt_sec || '].');
        RETURN FALSE;
      END IF;
  END;

  FUNCTION is_child_flow_completed(a_fmn IN xdv_prd_flow_matrix_node_t%ROWTYPE,
                                   a_flow_interval IN r_flow_interval) RETURN BOOLEAN
  IS
    l_start_sec INTEGER;
    l_stage     xdv_prd_stage_ref_def_t%ROWTYPE;
  BEGIN
    SELECT * INTO l_stage FROM xdv_prd_stage_ref_def_t WHERE id = a_fmn.stg_id;

    -- use different functions depending on stage of fmn
    IF (l_stage.stage_name = pc_stg_interim) THEN

      l_start_sec := a_flow_interval.a_start_dde_id + a_flow_interval.a_start_dte_id;

      --in case flow interval < partition time span, align it to partition ts
      l_start_sec := FLOOR(l_start_sec/xdv_generic_const_pkg.pc_dde_ivl) * xdv_generic_const_pkg.pc_dde_ivl;

      --TODO: do special alignment to GMT for daily/monthly

      RETURN is_transfer_completed(a_fmn, l_start_sec);

    ELSIF (l_stage.stage_name = pc_stg_rptstaging) THEN

      RETURN is_flow_completed(a_fmn, a_flow_interval);

    ELSIF  (l_stage.stage_name = pc_stg_reporting) THEN

      RETURN is_sub_flows_completed(a_fmn, a_flow_interval);

    ELSE
      xdv_logger_proxy_pkg.error(a_log_section => pc_pkg_log_section,
                            a_log_msg => 'Unsupported Stage: [' || l_stage.stage_name || '].');
      RETURN NULL;
    END IF;
  END;

  FUNCTION is_ready_to_flow(a_flow_ctx            IN OUT r_flow_context,
                            a_flow_interval       IN r_flow_interval) RETURN BOOLEAN
  IS
    l_stage               xdv_prd_stage_ref_def_t%ROWTYPE;
    l_max_wait_time       INTEGER;
    l_from_fmn            xdv_prd_flow_matrix_node_t%ROWTYPE;
    --l_stage_fmn           xdv_prd_flow_matrix_node_t%ROWTYPE;
    l_fmn2fmn             tn_fmn2fmn_array;
    --l_dep_job_type        INTEGER;
    l_ready               BOOLEAN := TRUE;
    l_force_ready         BOOLEAN := FALSE;
  BEGIN
    -- ready if (current time - from time) > max wait time (do not check dependencies)
    -- or when dependent jobs completed

    xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                         a_log_msg => 'Checking interval: [' || flow_interval_to_string(a_flow_interval) ||
                                      '] for readiness. ' || fmn_context_to_string(a_flow_ctx));

    l_max_wait_time := get_flow_max_wait_time( a_flow_ctx.a_fmn);
    IF( xdv_date_pkg.gmtdate_sec - (a_flow_interval.a_end_dde_id + a_flow_interval.a_end_dte_id) >= l_max_wait_time ) THEN
      xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                           a_log_msg => 'Maximum waiting time [' || l_max_wait_time || '] has reached for Interval: [' ||
                                        flow_interval_to_string(a_flow_interval) ||
                                        ']. ' || fmn_context_to_string(a_flow_ctx));
      l_force_ready := TRUE;
    END IF;

    -- order is important : first do (re)aggregation of data with lower granularity to staging,
    -- then from staging to reporting

    SELECT f2f.* BULK COLLECT INTO l_fmn2fmn
    FROM xdv_prd_fmn2fmn_at f2f
        JOIN xdv_prd_flow_matrix_node_t fmn ON
          fmn.id = f2f.pfn_id
        JOIN xdv_prd_granularity_t gra ON
          gra.id = fmn.gra_id
    WHERE f2f.cfn_id = a_flow_ctx.a_fmn.id AND
          LOWER(fmn.ENABLED) = xdv_generic_const_pkg.pc_yes_lower AND
          LOWER(f2f.ENABLED) = xdv_generic_const_pkg.pc_yes_lower
    ORDER BY gra.sec_tspan;

    --get status of dependent jobs
    IF (l_fmn2fmn IS NOT NULL AND l_fmn2fmn.COUNT > 0) THEN
      FOR i IN l_fmn2fmn.FIRST .. l_fmn2fmn.LAST
      LOOP
        SELECT * INTO l_from_fmn FROM xdv_prd_flow_matrix_node_t fmn WHERE l_fmn2fmn(i).pfn_id = fmn.id;
        SELECT * INTO l_stage FROM xdv_prd_stage_ref_def_t WHERE id = l_from_fmn.stg_id;

        IF (NOT l_force_ready) THEN
          xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                               a_log_msg => 'Checking status of dependent jobs of FMN: [' || l_from_fmn.id || '], Stage: [' ||
                                            l_stage.stage_name || '] for readiness. ' || fmn_context_to_string(a_flow_ctx));
          IF (NOT is_child_flow_completed(l_from_fmn, a_flow_interval)) THEN
            l_ready := FALSE;
          END IF;
        END IF;

        EXIT WHEN NOT l_ready;

        IF(l_stage.stage_name != pc_stg_interim) THEN
          -- do not transfer interim to reporting staging here: done in transport jobs
          a_flow_ctx.a_flow_jobs.EXTEND;
          a_flow_ctx.a_flow_jobs(a_flow_ctx.a_flow_jobs.LAST).a_from_fmn_id := l_from_fmn.id;
          a_flow_ctx.a_flow_jobs(a_flow_ctx.a_flow_jobs.LAST).a_to_fmn_id := a_flow_ctx.a_fmn.id;
          a_flow_ctx.a_flow_jobs(a_flow_ctx.a_flow_jobs.LAST).a_flow_interval := a_flow_interval;
        END IF;

      END LOOP;

    END IF;

    IF (l_ready) THEN
      xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                          a_log_msg => 'Interval: [' || flow_interval_to_string(a_flow_interval) ||
                                        '] is READY to flow. ' || fmn_context_to_string(a_flow_ctx));
    ELSE
      xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                           a_log_msg => 'Interval: [' || flow_interval_to_string(a_flow_interval) ||
                                          '] is NOT READY to flow. ' || fmn_context_to_string(a_flow_ctx));
    END IF;

    RETURN l_ready;

  END;

  PROCEDURE process_interval(a_flow_ctx            IN OUT r_flow_context,
                             a_flow_interval       IN r_flow_interval)
  IS
    l_flow_intervals  tn_flow_intervals;
    l_flow_ready      BOOLEAN := FALSE;
    l_src_fmn_det     xdv_query_builder_pkg.r_fmn_details;
    l_dst_fmn_det     xdv_query_builder_pkg.r_fmn_details;
    l_window          xdv_query_builder_pkg.r_window;
    l_rule_id         INTEGER;
    l_sql_select      VARCHAR2(32767);
    l_sql_insert      VARCHAR2(32767);
    l_sql             VARCHAR2(32767);
    l_nbr_rows        INTEGER;
    l_start_time      INTEGER;
  BEGIN

    a_flow_ctx.a_flow_jobs := tn_flow_jobs();

    IF ( is_ready_to_flow(a_flow_ctx, a_flow_interval) ) THEN
      xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                           a_log_msg => 'Interval: [' || flow_interval_to_string(a_flow_interval) ||
                                        '] is READY for processing. ' || fmn_context_to_string(a_flow_ctx));
      IF (a_flow_ctx.a_flow_jobs IS NOT NULL AND a_flow_ctx.a_flow_jobs.COUNT > 0) THEN
        FOR i IN a_flow_ctx.a_flow_jobs.FIRST .. a_flow_ctx.a_flow_jobs.LAST
        LOOP
         --remove the next if (used for testing)
         --IF ( a_flow_ctx.a_flow_jobs(i).a_to_fmn_id = a_flow_ctx.a_fmn.id) THEN
          BEGIN
            --a_flow_ctx.a_flow_jobs(i).a_to_fmn_id := 51050; --TODO !!!
            xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                                a_log_msg => 'EXECUTING QUERY BUILDER WITH parameters: ' ||
                                             'FROM FMN ID = [' || a_flow_ctx.a_flow_jobs(i).a_from_fmn_id ||
                                             '], TO FMN ID = [' || a_flow_ctx.a_flow_jobs(i).a_to_fmn_id ||
                                             '], INTERVAL: [' || flow_interval_to_string(a_flow_ctx.a_flow_jobs(i).a_flow_interval) || '].');
            l_src_fmn_det.fmn_id := a_flow_ctx.a_flow_jobs(i).a_from_fmn_id;
            l_dst_fmn_det.fmn_id := a_flow_ctx.a_flow_jobs(i).a_to_fmn_id;
            l_window.dds := a_flow_ctx.a_flow_jobs(i).a_flow_interval.a_start_dde_id;
            l_window.dts := a_flow_ctx.a_flow_jobs(i).a_flow_interval.a_start_dte_id;
            l_window.dde := a_flow_ctx.a_flow_jobs(i).a_flow_interval.a_end_dde_id;
            l_window.dte := a_flow_ctx.a_flow_jobs(i).a_flow_interval.a_end_dte_id;

            BEGIN
              SELECT id INTO l_rule_id
              FROM XDV_FLT_FILTER_RULE_T
              WHERE sfm_id = a_flow_ctx.a_flow_jobs(i).a_from_fmn_id AND
                    dfm_id = a_flow_ctx.a_flow_jobs(i).a_to_fmn_id;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                l_rule_id := NULL;
            END;

            xdv_logger_proxy_pkg.trace(a_log_section => pc_pkg_log_section, a_log_msg => 'Calling query builder...');

            xdv_query_builder_pkg.get_transfer_construct(
                l_src_fmn_det,
                l_dst_fmn_det,
                l_window,
                l_rule_id,
                a_flow_ctx.a_time_zone.id,
                l_sql_insert,
                l_sql_select);

            xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                                 a_log_msg => SUBSTR(
                                    'From FMN ID = [' || a_flow_ctx.a_flow_jobs(i).a_from_fmn_id  ||
                                    '], To FMN ID = [' || a_flow_ctx.a_flow_jobs(i).a_to_fmn_id ||
                                    '], sql_select : ' || l_sql_select, 1, xdv_logger_proxy_pkg.pc_max_msg_size));

            xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                                 a_log_msg => SUBSTR(
                                    'From FMN ID = [' || a_flow_ctx.a_flow_jobs(i).a_from_fmn_id  ||
                                    '], To FMN ID = [' || a_flow_ctx.a_flow_jobs(i).a_to_fmn_id ||
                                    '], sql_insert : ' || l_sql_insert, 1, xdv_logger_proxy_pkg.pc_max_msg_size));

            l_sql := l_sql_insert || ' ' || l_sql_select;

            l_start_time := DBMS_UTILITY.get_time;

            EXECUTE IMMEDIATE 'BEGIN ' || l_sql || '; :1 := SQL%ROWCOUNT; END;' USING OUT l_nbr_rows;

            xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                                 a_log_msg => 'Inserted ' || l_nbr_rows || ' row(s) in ' ||
                                              TO_CHAR((DBMS_UTILITY.get_time - l_start_time) / 100) || ' second(s).' ||
                                              ' FROM FMN ID = [' || a_flow_ctx.a_flow_jobs(i).a_from_fmn_id ||
                                              '], TO FMN ID = [' || a_flow_ctx.a_flow_jobs(i).a_to_fmn_id ||
                                              '], INTERVAL: [' || flow_interval_to_string(a_flow_ctx.a_flow_jobs(i).a_flow_interval) || '].');

            a_flow_ctx.a_row_count := l_nbr_rows;

          EXCEPTION
            WHEN OTHERS THEN
              xdv_logger_proxy_pkg.error(  a_log_section => pc_pkg_log_section,
                                     a_log_msg => 'ERROR processing FMN flow: -ERROR- ' || SUBSTR(SQLERRM,1,3192) || dbms_utility.format_error_backtrace ||
                                                  '. FROM FMN ID = [' || a_flow_ctx.a_flow_jobs(i).a_from_fmn_id ||
                                                  '], TO FMN ID = [' || a_flow_ctx.a_flow_jobs(i).a_to_fmn_id ||
                                                  '], INTERVAL: [' || flow_interval_to_string(a_flow_ctx.a_flow_jobs(i).a_flow_interval) || '].');
            RAISE;
          END;
        --END IF; --test IF
        END LOOP;
      ELSE
        xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                             a_log_msg => 'No transfer is needed to complete flow.' ||
                                          fmn_context_to_string(a_flow_ctx));
      END IF;
      a_flow_ctx.a_status := pc_flow_result_complete;
    ELSE
      xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                           a_log_msg => 'Interval: [' || flow_interval_to_string(a_flow_interval) ||
                                        '] is NOT READY for processing. ' || fmn_context_to_string(a_flow_ctx));
      a_flow_ctx.a_status := pc_flow_result_pending;
    END IF;

  END;

  FUNCTION get_back_scan_periods(a_granularity       IN xdv_prd_granularity_t%ROWTYPE) RETURN INTEGER
  IS
    l_bs  INTEGER;
  BEGIN
    --use config table or defaults
    IF (a_granularity.granularity = pc_granularity_trn) THEN
      l_bs := xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_bs_trn_name, pc_bs_trn_dflt, TRUE);
    ELSIF (a_granularity.granularity = pc_granularity_15m) THEN
      l_bs := xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_bs_15m_name, pc_bs_15m_dflt, TRUE);
    ELSIF (a_granularity.granularity = pc_granularity_1hr) THEN
      l_bs := xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_bs_1hr_name, pc_bs_1hr_dflt, TRUE);
    ELSIF (a_granularity.granularity = pc_granularity_1dy) THEN
      l_bs := xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_bs_1dy_name, pc_bs_1dy_dflt, TRUE);
    ELSIF (a_granularity.granularity = pc_granularity_1mo) THEN
      l_bs := xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_bs_1mo_name, pc_bs_1mo_dflt, TRUE);
    ELSE
      xdv_logger_proxy_pkg.error(a_log_section => pc_pkg_log_section,
                            a_log_msg => 'Unsupported granularity: [' || a_granularity.granularity || '].');
      RETURN NULL;
    END IF;
    RETURN l_bs;
  END;

  -- return last n intervals of fixed length(based on backscan)
  FUNCTION get_flow_fixed_intervals(a_granularity           IN xdv_prd_granularity_t%ROWTYPE,
                                    a_upper_bound_dt_sec    IN INTEGER,
                                    a_time_zone             IN xdv_dtm_timezone_dt%ROWTYPE,
                                    a_back_scan_periods     IN INTEGER DEFAULT NULL) RETURN tn_flow_intervals

  IS
    l_back_scan_periods INTEGER;
    l_start_sec         INTEGER;
    l_end_sec           INTEGER;
    l_start_ivl_sec     INTEGER;
    l_end_ivl_sec       INTEGER;
    l_ivl_sec           INTEGER;
    l_ivls              tn_flow_intervals;
  BEGIN

    l_ivl_sec := get_granularity_ivl_sec(a_granularity);

    IF (a_back_scan_periods IS NULL) THEN
      l_back_scan_periods := get_back_scan_periods(a_granularity);
    ELSE
      l_back_scan_periods := a_back_scan_periods;
    END IF;

    l_start_sec :=  a_upper_bound_dt_sec - l_ivl_sec * l_back_scan_periods;
    l_start_sec :=  FLOOR(l_start_sec / l_ivl_sec) * l_ivl_sec;

    l_end_sec :=  a_upper_bound_dt_sec;
    l_end_sec :=  FLOOR(l_end_sec / l_ivl_sec) * l_ivl_sec;

    l_ivls := tn_flow_intervals();

    l_start_ivl_sec := l_start_sec;
    l_end_ivl_sec := l_start_sec + l_ivl_sec - 1;

    WHILE (l_end_ivl_sec <= l_end_sec)
    LOOP
      l_ivls.EXTEND;

      l_ivls(l_ivls.LAST).a_start_dde_id := get_dde_id_from_sec(l_start_ivl_sec);
      l_ivls(l_ivls.LAST).a_start_dte_id := get_dte_id_from_sec(l_start_ivl_sec);

      l_ivls(l_ivls.LAST).a_end_dde_id := get_dde_id_from_sec(l_end_ivl_sec);
      l_ivls(l_ivls.LAST).a_end_dte_id := get_dte_id_from_sec(l_end_ivl_sec);

      l_ivls(l_ivls.LAST).a_time_zone_id := a_time_zone.id;

      l_start_ivl_sec := l_end_ivl_sec + 1;
      l_end_ivl_sec := l_start_ivl_sec + l_ivl_sec - 1;

    END LOOP;

    RETURN l_ivls;
  END;

  FUNCTION get_flow_day_intervals  (a_upper_bound_dt_sec    IN INTEGER,
                                    a_time_zone             IN xdv_dtm_timezone_dt%ROWTYPE,
                                    a_back_scan_periods     IN INTEGER DEFAULT NULL) RETURN xdv_flow_manager_pkg.tn_flow_intervals
  IS
    l_start_ts_tz       TIMESTAMP WITH TIME ZONE;
    l_end_ts_tz         TIMESTAMP WITH TIME ZONE;
    l_back_scan_days    INTEGER;
    l_start_ivl_sec     INTEGER;
    l_end_ivl_sec       INTEGER;
    l_ivls              xdv_flow_manager_pkg.tn_flow_intervals;
    l_gra               xdv_prd_granularity_t%ROWTYPE;
  BEGIN

    IF (a_back_scan_periods IS NULL) THEN
      SELECT * into l_gra from xdv_prd_granularity_t where id = 400;
      l_back_scan_days := xdv_flow_manager_pkg.get_back_scan_periods(l_gra);
    ELSE
      l_back_scan_days := a_back_scan_periods;
    END IF;

    l_start_ts_tz := FROM_TZ( TRUNC(xdv_date_pkg.SEC_TO_DATE(a_upper_bound_dt_sec) - l_back_scan_days, 'DD'), a_time_zone.tz_name) AT TIME ZONE a_time_zone.tz_name;
    l_end_ts_tz := FROM_TZ( ROUND(l_start_ts_tz + 1, 'DD'), a_time_zone.tz_name) AT TIME ZONE a_time_zone.tz_name - TO_DSINTERVAL('0 0:0:1');

    l_start_ivl_sec := get_timestamp_tz_to_sec(l_start_ts_tz);
    l_end_ivl_sec := get_timestamp_tz_to_sec(l_end_ts_tz);

    l_ivls := xdv_flow_manager_pkg.tn_flow_intervals();

    WHILE (l_end_ivl_sec < a_upper_bound_dt_sec)
    LOOP
      l_ivls.EXTEND;

      l_ivls(l_ivls.LAST).a_start_dde_id := xdv_flow_manager_pkg.get_dde_id_from_sec(l_start_ivl_sec);
      l_ivls(l_ivls.LAST).a_start_dte_id := xdv_flow_manager_pkg.get_dte_id_from_sec(l_start_ivl_sec);

      l_ivls(l_ivls.LAST).a_end_dde_id := xdv_flow_manager_pkg.get_dde_id_from_sec(l_end_ivl_sec);
      l_ivls(l_ivls.LAST).a_end_dte_id := xdv_flow_manager_pkg.get_dte_id_from_sec(l_end_ivl_sec);

      l_ivls(l_ivls.LAST).a_time_zone_id := a_time_zone.id;

      --DBMS_OUTPUT.PUT_LINE('start ts:' || l_start_ts_tz || ' sec: ' || l_start_ivl_sec);
      --DBMS_OUTPUT.PUT_LINE('end ts:' || l_end_ts_tz || ' sec: ' || l_end_ivl_sec);
      --DBMS_OUTPUT.PUT_LINE('end sec - start sec :' || (l_end_ivl_sec - l_start_ivl_sec));

      l_start_ts_tz := FROM_TZ( ROUND(l_start_ts_tz + 1, 'DD'), a_time_zone.tz_name) AT TIME ZONE a_time_zone.tz_name;
      l_end_ts_tz := FROM_TZ( ROUND(l_start_ts_tz + 1, 'DD'), a_time_zone.tz_name) AT TIME ZONE a_time_zone.tz_name - TO_DSINTERVAL('0 0:0:1');

      l_start_ivl_sec := get_timestamp_tz_to_sec(l_start_ts_tz);
      l_end_ivl_sec := get_timestamp_tz_to_sec(l_end_ts_tz);

    END LOOP;

    RETURN l_ivls;
  END;

  FUNCTION get_flow_month_intervals(a_upper_bound_dt_sec    IN INTEGER,
                                    a_time_zone             IN xdv_dtm_timezone_dt%ROWTYPE,
                                    a_back_scan_periods     IN INTEGER DEFAULT NULL) RETURN xdv_flow_manager_pkg.tn_flow_intervals
  IS
    l_start_ts_tz       TIMESTAMP WITH TIME ZONE;
    l_end_ts_tz         TIMESTAMP WITH TIME ZONE;
    l_back_scan_months    INTEGER;
    l_start_ivl_sec     INTEGER;
    l_end_ivl_sec       INTEGER;
    l_ivls              xdv_flow_manager_pkg.tn_flow_intervals;
    l_gra               xdv_prd_granularity_t%ROWTYPE;
  BEGIN

    IF (a_back_scan_periods IS NULL) THEN
      SELECT * into l_gra from xdv_prd_granularity_t where id = 500;
      l_back_scan_months := xdv_flow_manager_pkg.get_back_scan_periods(l_gra);
    ELSE
      l_back_scan_months := a_back_scan_periods;
    END IF;

    l_start_ts_tz := FROM_TZ( TRUNC(ADD_MONTHS(xdv_date_pkg.SEC_TO_DATE(a_upper_bound_dt_sec),  -l_back_scan_months), 'MON'), a_time_zone.tz_name) AT TIME ZONE a_time_zone.tz_name;
    l_end_ts_tz := FROM_TZ( ROUND(ADD_MONTHS(l_start_ts_tz, 1), 'MON'), a_time_zone.tz_name) AT TIME ZONE a_time_zone.tz_name - TO_DSINTERVAL('0 0:0:1');

    l_start_ivl_sec := get_timestamp_tz_to_sec(l_start_ts_tz);
    l_end_ivl_sec := get_timestamp_tz_to_sec(l_end_ts_tz);

    l_ivls := xdv_flow_manager_pkg.tn_flow_intervals();

    WHILE (l_end_ivl_sec < a_upper_bound_dt_sec)
    LOOP
      l_ivls.EXTEND;

      l_ivls(l_ivls.LAST).a_start_dde_id := xdv_flow_manager_pkg.get_dde_id_from_sec(l_start_ivl_sec);
      l_ivls(l_ivls.LAST).a_start_dte_id := xdv_flow_manager_pkg.get_dte_id_from_sec(l_start_ivl_sec);

      l_ivls(l_ivls.LAST).a_end_dde_id := xdv_flow_manager_pkg.get_dde_id_from_sec(l_end_ivl_sec);
      l_ivls(l_ivls.LAST).a_end_dte_id := xdv_flow_manager_pkg.get_dte_id_from_sec(l_end_ivl_sec);

      l_ivls(l_ivls.LAST).a_time_zone_id := a_time_zone.id;

      --DBMS_OUTPUT.PUT_LINE('start ts:' || l_start_ts_tz || ' sec: ' || l_start_ivl_sec);
      --DBMS_OUTPUT.PUT_LINE('end ts:' || l_end_ts_tz || ' sec: ' || l_end_ivl_sec);
      --DBMS_OUTPUT.PUT_LINE('end sec - start sec :' || (l_end_ivl_sec - l_start_ivl_sec));

      l_start_ts_tz := FROM_TZ( ROUND(ADD_MONTHS(l_start_ts_tz, 1), 'MON'), a_time_zone.tz_name) AT TIME ZONE a_time_zone.tz_name;
      l_end_ts_tz := FROM_TZ( ROUND(ADD_MONTHS(l_start_ts_tz, 1), 'MON'), a_time_zone.tz_name) AT TIME ZONE a_time_zone.tz_name - TO_DSINTERVAL('0 0:0:1');

      l_start_ivl_sec := get_timestamp_tz_to_sec(l_start_ts_tz);
      l_end_ivl_sec := get_timestamp_tz_to_sec(l_end_ts_tz);

    END LOOP;

    RETURN l_ivls;
  END;

  FUNCTION get_flow_intervals(a_granularity           IN xdv_prd_granularity_t%ROWTYPE,
                              a_upper_bound_dt_sec    IN INTEGER,
                              a_time_zone             IN xdv_dtm_timezone_dt%ROWTYPE,
                              a_back_scan_periods     IN INTEGER DEFAULT NULL) RETURN tn_flow_intervals
  IS
    l_ivls tn_flow_intervals;
  BEGIN
    l_ivls :=
      CASE a_granularity.granularity
        WHEN pc_granularity_trn THEN get_flow_fixed_intervals(a_granularity,
                                                              a_upper_bound_dt_sec,
                                                              a_time_zone,
                                                              a_back_scan_periods)
        WHEN pc_granularity_15m THEN get_flow_fixed_intervals(a_granularity,
                                                              a_upper_bound_dt_sec,
                                                              a_time_zone,
                                                              a_back_scan_periods)
        WHEN pc_granularity_1hr THEN get_flow_fixed_intervals(a_granularity,
                                                              a_upper_bound_dt_sec,
                                                              a_time_zone,
                                                              a_back_scan_periods)
        WHEN pc_granularity_1dy THEN get_flow_day_intervals  (a_upper_bound_dt_sec,
                                                              a_time_zone,
                                                              a_back_scan_periods)
        WHEN pc_granularity_1mo THEN get_flow_month_intervals(a_upper_bound_dt_sec,
                                                              a_time_zone,
                                                              a_back_scan_periods)
        ELSE NULL
      END;

    IF(l_ivls IS NULL) THEN
      xdv_logger_proxy_pkg.error(a_log_section => pc_pkg_log_section,
                            a_log_msg => 'Unsupported Granularity: ID=[' || a_granularity.id ||
                                         '], Name=[' || a_granularity.granularity || '].');
      RAISE UNSUPPORTED_GRANULARITY;
    END IF;

    RETURN l_ivls;
  END;

  PROCEDURE process_fmn_tz(a_fmn                   IN xdv_prd_flow_matrix_node_t%ROWTYPE,
                           a_upper_bound_date      IN TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP,
                           a_time_zone             IN VARCHAR2 DEFAULT pc_gmt_tz_name,
                           a_back_scan_periods     IN INTEGER DEFAULT NULL)
  IS
    l_flow_ctx        r_flow_context;
    l_flow_intervals  tn_flow_intervals;
    l_flow_log_rec    xdv_agr_aggregator_log_t%ROWTYPE;
  BEGIN
    l_flow_ctx.a_fmn := a_fmn;

    BEGIN
      SELECT * INTO l_flow_ctx.a_time_zone
      FROM xdv_dtm_timezone_dt
      WHERE UPPER(tz_name) = UPPER(TRIM(a_time_zone));
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        xdv_logger_proxy_pkg.error(a_log_section => pc_pkg_log_section,
                              a_log_msg => 'Unsupported time zone: [' || a_time_zone ||
                                           ']. FMN ID = [' || l_flow_ctx.a_fmn.id || '].');
        RAISE UNKNOWN_TIME_ZONE;
    END;

    SELECT * INTO l_flow_ctx.a_stage FROM xdv_prd_stage_ref_def_t WHERE id = a_fmn.stg_id;
    SELECT * INTO l_flow_ctx.a_flow_type FROM xdv_prd_flow_type_t WHERE id = a_fmn.fwt_id;
    SELECT * INTO l_flow_ctx.a_granularity FROM xdv_prd_granularity_t WHERE id = l_flow_ctx.a_fmn.gra_id;

    l_flow_ctx.a_upper_bound_dt_sec := xdv_date_pkg.DATE_TO_SEC(SYS_EXTRACT_UTC(a_upper_bound_date));
    l_flow_ctx.a_back_scan_periods := a_back_scan_periods;

    xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                         a_log_msg => 'Starting processing data flow. ' || fmn_context_to_string(l_flow_ctx));

    l_flow_intervals := get_flow_intervals(l_flow_ctx.a_granularity,
                                           l_flow_ctx.a_upper_bound_dt_sec,
                                           l_flow_ctx.a_time_zone,
                                           a_back_scan_periods);

    IF( l_flow_intervals IS NOT NULL AND l_flow_intervals.COUNT > 0 ) THEN
      FOR i IN l_flow_intervals.FIRST .. l_flow_intervals.LAST
      LOOP
        xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                             a_log_msg => 'Processing interval: [' || flow_interval_to_string(l_flow_intervals(i)) ||
                                          ']. ' || fmn_context_to_string(l_flow_ctx));

        l_flow_log_rec := get_or_create_flow_log_rec(a_fmn, l_flow_intervals(i));

        IF (l_flow_log_rec.result = pc_flow_result_pending) THEN
          xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                               a_log_msg => 'Processing interval: [' || flow_interval_to_string(l_flow_intervals(i)) ||
                                            '] is in pending state. ' || fmn_context_to_string(l_flow_ctx));
          BEGIN
            l_flow_log_rec.start_tstamp := sysdate;
            l_flow_ctx.a_status := l_flow_log_rec.result;
            process_interval(l_flow_ctx, l_flow_intervals(i));
            l_flow_log_rec.result := l_flow_ctx.a_status;
            if(l_flow_ctx.a_status = pc_flow_result_complete) THEN
              l_flow_log_rec.log_message := 'Successfully completed. Inserted ' ||
                                            NVL(l_flow_ctx.a_row_count, 0) || ' row(s).';
            ELSE
              l_flow_log_rec.log_message := 'Pending.';
            END IF;
          EXCEPTION
          WHEN OTHERS THEN
            xdv_logger_proxy_pkg.error(  a_log_section => pc_pkg_log_section,
                                   a_log_msg => 'ERROR processing FMN flow: -ERROR- ' || SUBSTR(SQLERRM,1,512) ||
                                                '. FMN ID = [' || l_flow_ctx.a_fmn.id ||
                                                '], INTERVAL: [' || flow_interval_to_string(l_flow_intervals(i)) || '].');
            l_flow_log_rec.result := pc_flow_result_error;
            l_flow_log_rec.log_message := SUBSTR(SQLERRM,1,256);
          END;
          l_flow_log_rec.end_tstamp := sysdate;
          update_flow_log_rec(l_flow_log_rec);
          COMMIT;
          xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                               a_log_msg => 'Finished processing interval: [' || flow_interval_to_string(l_flow_intervals(i)) ||
                                            ']. ' || fmn_context_to_string(l_flow_ctx));
        ELSE
          xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                               a_log_msg => 'Interval was already processed: [' || flow_interval_to_string(l_flow_intervals(i)) ||
                                            ']. ' || fmn_context_to_string(l_flow_ctx));
        END IF;
      END LOOP;
    END IF;

    xdv_logger_proxy_pkg.info(a_log_section => pc_pkg_log_section,
                         a_log_msg => 'Finished processing. ' || fmn_context_to_string(l_flow_ctx));

  END;

--------------------------------------------------------------------

  PROCEDURE process_fmn_tz(a_fmn_id                IN INTEGER,
                           a_upper_bound_date      IN TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP,
                           a_time_zone             IN VARCHAR2 DEFAULT pc_gmt_tz_name,
                           a_back_scan_periods     IN INTEGER DEFAULT NULL)
  IS
    l_fmn xdv_prd_flow_matrix_node_t%ROWTYPE;
  BEGIN
    SELECT * INTO l_fmn FROM xdv_prd_flow_matrix_node_t WHERE id = a_fmn_id;

    process_fmn_tz(a_fmn => l_fmn,
                   a_upper_bound_date => a_upper_bound_date,
                   a_time_zone => a_time_zone,
                   a_back_scan_periods => a_back_scan_periods);
  END;

--------------------------------------------------------------------

  PROCEDURE process_fmn_all_tz(a_fmn                   IN xdv_prd_flow_matrix_node_t%ROWTYPE,
                               a_upper_bound_date      IN TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP,
                               a_back_scan_periods     IN INTEGER DEFAULT NULL)
  IS
    l_granularity xdv_prd_granularity_t%ROWTYPE;
    l_tz_config   VARCHAR2(256);
    l_tz          VARCHAR2(265);
    l_cur_pos     INTEGER := 1;
    l_last_pos    INTEGER := 1;
  BEGIN
    SELECT * INTO l_granularity FROM xdv_prd_granularity_t WHERE id = a_fmn.gra_id;

    IF (l_granularity.sec_tspan >= 3600*24) THEN
      l_tz_config := TRIM(xdv_config_pkg.get_config_value(pc_pkg_cfg_section, pc_time_zone_list, NULL, TRUE));

      IF(l_tz_config IS NOT NULL) THEN
        LOOP
          SELECT INSTR(l_tz_config, ',', l_last_pos) INTO l_cur_pos FROM dual;
          IF(l_cur_pos = 0) THEN
            SELECT SUBSTR(l_tz_config, l_last_pos) INTO l_tz FROM dual;
          ELSE
            SELECT SUBSTR(l_tz_config, l_last_pos, l_cur_pos - l_last_pos) INTO l_tz FROM dual;
          END IF;

          process_fmn_tz(a_fmn => a_fmn,
                         a_upper_bound_date => a_upper_bound_date,
                         a_time_zone => l_tz,
                         a_back_scan_periods => a_back_scan_periods);

          EXIT WHEN l_cur_pos = 0;

          l_last_pos := l_cur_pos + 1;
          l_cur_pos := l_cur_pos + 1;
        END LOOP;
      ELSE
        process_fmn_tz(a_fmn => a_fmn,
                       a_upper_bound_date => a_upper_bound_date,
                       a_time_zone => pc_gmt_tz_name,
                       a_back_scan_periods => a_back_scan_periods);
      END IF;
    ELSE
        process_fmn_tz(a_fmn => a_fmn,
                       a_upper_bound_date => a_upper_bound_date,
                       a_time_zone => pc_gmt_tz_name,
                       a_back_scan_periods => a_back_scan_periods);
    END IF;
  END;


--------------------------------------------------------------------

  PROCEDURE process_fmn_all_tz(a_fmn_id                IN INTEGER,
                               a_upper_bound_date      IN TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP,
                               a_back_scan_periods     IN INTEGER DEFAULT NULL)
  IS
    l_fmn xdv_prd_flow_matrix_node_t%ROWTYPE;
  BEGIN
    SELECT * INTO l_fmn FROM xdv_prd_flow_matrix_node_t WHERE id = a_fmn_id;

    process_fmn_all_tz(a_fmn => l_fmn,
                       a_upper_bound_date => a_upper_bound_date,
                       a_back_scan_periods => a_back_scan_periods);
  END;

--------------------------------------------------------------------
/*=========================*/
/*===== Pkg Main Block ====*/
/*=========================*/
--------------------------------------------------------------------

END xdv_flow_manager_pkg;
/
