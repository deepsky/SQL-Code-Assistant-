CREATE OR REPLACE PACKAGE BODY GN_IM_MSG_INSERT_PKG
IS

  /** AQ add-on for the adapters. */
  PROCEDURE enqueue_load              (
                a_job_type           IN INTEGER,
                a_load_id            IN NUMBER,
                a_load_st_ts         IN DATE,
                a_load_end_ts        IN DATE     DEFAULT SYSDATE,
                a_sender_id          IN VARCHAR2 DEFAULT pc_sndr_unknown,
                a_message            IN VARCHAR2 DEFAULT NULL,
                a_msg_priority       IN NUMBER   DEFAULT 1) IS

     l_msg                    med_load_message;

  BEGIN

    l_msg         := med_load_message(a_sender_id, a_load_id,  a_load_st_ts, a_load_end_ts, a_message);

    xdv_logger_pkg.info(a_log_ctx => v_log_ctx, a_log_msg => 'Enqueueing message with load-id "' || a_load_id || '" of job type '|| a_job_type || ' ...');

    xdv_aq_pkg.enqueue_load(
        a_queue_name            => xdv_aq_pkg.get_queue_name(a_job_type, xdv_generic_const_pkg.pc_dflw_jp_pre_replication),
        a_load_msg              => l_msg,
        a_msg_priority          => a_msg_priority);

    xdv_logger_pkg.info(a_log_ctx => v_log_ctx, a_log_msg => 'Message with load-id "' || a_load_id || '" of job type ' || a_job_type || ' was enqueued.');

  END;


  FUNCTION GN_IM_MSG_INSERT(P_TIME_ZONE VARCHAR2 DEFAULT NULL)
  RETURN INTEGER
  IS

    l_load_id                INTEGER;
    l_load_st_dt             DATE;
    l_load_end_dt            DATE;
    RETVAL INTEGER;
    TZ VARCHAR2(50) := P_TIME_ZONE;

  BEGIN

  IF TZ IS NULL THEN TZ := DBTIMEZONE; END IF;

  SELECT
    MIN(FROM_TZ(END_TIME, TZ) AT TIME ZONE 'GMT'),
    MAX(FROM_TZ(END_TIME, TZ) AT TIME ZONE 'GMT')
  INTO l_load_st_dt, l_load_end_dt
  FROM XDV_ADS_IM_MSG_ET;

  SELECT XDV_AMM_TRN_SEQ.NEXTVAL INTO l_load_id FROM DUAL;

  INSERT /*+ APPEND */ INTO XDV_AMM_TRN_MEDMES_FT
  (ROAMER_IND, NETWORK_IND, TAT_ID, TAC_ID, TRS_ID, TRC_ID, DDE_ID, DTE_ID, END_TIME_MS, DURATION, SEND_TIME, DELIVERY_TIME, EXPIRY_TIME, TEID, USER_AGENT, IMEI, MSISDN, IMSI,
  NBR_BYTES, READ_REPLY, REPORT_ALLOWED, DELIVERY_REPORT, PRIORITY, SRV_ID, TRQ_ID, MESSAGE_ID, TRANSACTION_ID, TO_RECIPIENT, FROM_ORIGINATOR, SERVER_AGENT,
  FAC_INT_01, FAC_INT_02, FAC_INT_03, FAC_INT_04, FAC_INT_05, FAC_INT_06, FAC_INT_07, FAC_INT_08, FAC_INT_09, FAC_INT_10,
  FAC_EXT_01, FAC_EXT_02, FAC_EXT_03, FAC_EXT_04, FAC_EXT_05, FAC_EXT_06, FAC_EXT_07, FAC_EXT_08, FAC_EXT_09, FAC_EXT_10,
  INT_TRANSACTION_ID, TAC, SVN, LOAD_ID)
  SELECT /*
   -1 as ROAMER_IND,
   -1 as NETWORK_IND,
   TAT_ID AS TAT_ID,
   TAC_ID AS TAC_ID,
   -1 AS TRS_ID,
   TRC_ID AS TRC_ID, */
   EXTRACT(DAY FROM END_TIME_INTERVAL) * 86400 + EXTRACT(HOUR FROM END_TIME_INTERVAL) * 3600 AS DDE_ID,
   EXTRACT(MINUTE FROM END_TIME_INTERVAL) * 60 + FLOOR(END_TIME_SECONDS) AS DTE_ID,
   MOD(1000 * END_TIME_SECONDS, 1000) AS END_TIME_MS,
   DURATION AS DURATION,
   NULL AS SEND_TIME,
   NULL AS DELIVERY_TIME,
   NULL AS EXPIRY_TIME,
   TEID AS TEID,
   USER_AGENT AS USER_AGENT,
   IMEI AS IMEI,
   MSISDN AS MSISDN,
   IMSI AS IMSI,
   NBR_BYTES AS NBR_BYTES,
   NULL AS READ_REPLY,
   NULL as REPORT_ALLOWED,
   NULL AS DELIVERY_REPORT,
   NULL AS PRIORITY,
   SRV_ID AS SRV_ID,
   TRQ_ID AS TRQ_ID,
   MESSAGE_ID AS MESSAGE_ID,
   NULL AS TRANSACTION_ID,
   TO_RECIPIENT AS TO_RECIPIENT,
   FROM_ORIGINATOR AS FROM_ORIGINATOR,
   SERVER_AGENT AS SERVER_AGENT,
   CELL_IDENTITY    AS FAC_INT_01,
   ROUTING_AREA     AS FAC_INT_02,
   -1       AS FAC_INT_03,
   -1       AS FAC_INT_04,
   -1       AS FAC_INT_05,
   -1       AS FAC_INT_06,
   -1       AS FAC_INT_07,
   -1       AS FAC_INT_08,
   -1       AS FAC_INT_09,
   -1       AS FAC_INT_10,
   'S'||COLLECTION_SOURCE AS FAC_EXT_01,
   'G'||COLLECTION_SOURCE AS FAC_EXT_02,
   ACCESS_POINT_NAME AS FAC_EXT_03,
   -1       AS FAC_EXT_04,
   -1       AS FAC_EXT_05,
   -1       AS FAC_EXT_06,
   -1       AS FAC_EXT_07,
   -1       AS FAC_EXT_08,
   -1       AS FAC_EXT_09,
   -1       AS FAC_EXT_10,
      (EXTRACT(DAY FROM END_TIME_INTERVAL) * 86400 + EXTRACT(HOUR FROM END_TIME_INTERVAL) * 3600 + EXTRACT(MINUTE FROM END_TIME_INTERVAL) * 60 +
          END_TIME_SECONDS) * 10000000000 +
          70000 + MOD(ROWNUM, 10000)
          AS INT_TRANSACTION_ID,
   (CASE WHEN LENGTH(IMEI) >= 8 THEN SUBSTR(IMEI, 1,8) ELSE '-1' END) AS TAC,
   (CASE WHEN LENGTH(IMEI) >= 16 THEN SUBSTR(IMEI, 15, 2) ELSE '-1' END) AS SVN,
   l_load_id AS LOAD_ID
   FROM
  (
  SELECT
   TT.ID AS TAT_ID,
   CT.ID AS TAC_ID,
   CA.ID AS TRC_ID,
   ((FROM_TZ(END_TIME, TZ) AT TIME ZONE 'GMT') - C_Y1970) DAY (6) TO SECOND(0) AS END_TIME_INTERVAL,
      EXTRACT(SECOND FROM END_TIME) AS END_TIME_SECONDS,
   DURATION AS DURATION,
   TEID AS TEID,
   NVL(USER_AGENT, '-1') AS USER_AGENT,
      (CASE WHEN LENGTH(IMEI) >= 8 AND TRANSLATE(TRANSLATE(IMEI, '1''- ', '1'), ' 0123456789', ' ') IS NULL
          THEN TRANSLATE(IMEI, '1''- ', '1') ELSE '-1' END)
          AS IMEI,
      (CASE WHEN LENGTH(MSISDN) >= 8 AND TRANSLATE(TRANSLATE(MSISDN, '1''- ', '1'), ' 0123456789', ' ') IS NULL
          THEN TRANSLATE(MSISDN, '1''- ', '1') ELSE '-1' END)
          AS MSISDN,
      (CASE WHEN LENGTH(IMSI) >= 6 AND TRANSLATE(TRANSLATE(IMSI, '1''- ', '1'), ' 0123456789', ' ') IS NULL
          THEN TRANSLATE(IMSI, '1''- ', '1') ELSE '-1' END)
          AS IMSI,
   CONTENT_SIZE AS NBR_BYTES,
   CALL_ID AS MESSAGE_ID,
   TO_RECIPIENT AS TO_RECIPIENT,
   FROM_SENDER AS FROM_ORIGINATOR,
   XDV_ADS_IM_MSG_ET.SERVER_ID AS SERVER_AGENT,
   ROUTING_AREA AS ROUTING_AREA,
   CELL_IDENTITY AS CELL_IDENTITY,
   COLLECTION_SOURCE AS COLLECTION_SOURCE,
   NVL(ACCESS_POINT_NAME, -1) AS ACCESS_POINT_NAME,
   NVL(QS.ID, -1) AS TRQ_ID,
   NVL(SV.ID, -1) AS SRV_ID
  FROM
   XDV_ADS_IM_MSG_ET,
   XDV_TRN_APP_TYPE_DT TT,
   XDV_TRN_APP_CONTENT_TYPE_DT CT,
   XDV_TRN_CAUSES_DT CA,
   XDV_TRN_QOS_DT QS,
   XDV_SRV_SERVER_DT SV
  WHERE
   TT.TECHNOLOGY = 'GPRS' AND
   TT.INTERFACE = 'Gn' AND
   TT.CATEGORY = 'Application' AND
   TT.APPLICATION_TYPE = 'Instant Messaging' AND
   TT.XDR_TYPE = 'Message' AND
   TT.PROTOCOL = XDV_ADS_IM_MSG_ET.PROTOCOL AND TT.MESSAGE_TYPE = XDV_ADS_IM_MSG_ET.COMMAND AND
   TT.MESSAGE_CLASS = 'Unknown' AND  --is null AND
   TT.ORIGINATOR = 'MS' AND
   TT.AMK_ID = 10090 AND
   ( NVL(XDV_ADS_IM_MSG_ET.MESSAGE_STATUS_CODE, 0) = 0 AND CA.ID = -1 OR
     CA.TECHNOLOGY = 'GPRS' AND
     CA.CATEGORY = 'Application' AND
     CA.INTERFACE = 'Gn' AND
     CA.TYPE = 'Message' AND
     CA.CALL_RESULT = 'Success' AND
     CA.AMK_ID = 11090 AND
     CA.SEVERITY = 'Info' AND
     CA.PRIMARY_CAUSE_ID = XDV_ADS_IM_MSG_ET.MESSAGE_STATUS_CODE AND
      CA.PRIMARY_CAUSE_TYPE = 'IM-'||XDV_ADS_IM_MSG_ET.PROTOCOL AND
     CA.SECONDARY_CAUSE_ID IS NULL ) AND
   CT.AMK_ID = 13040 AND CT.TECHNOLOGY = 'GPRS' AND
   CT.CATEGORY = 'Application' AND
   CT.INTERFACE = 'Gn' AND
   XDV_ADS_IM_MSG_ET.CONTENT_TYPE LIKE CT.CONTENT_TYPE||'%'||CT.CONTENT_SUBTYPE AND
   XDV_ADS_IM_MSG_ET.RQOS(+) = QS.RQOS_HEXID AND XDV_ADS_IM_MSG_ET.NQOS = QS.NQOS_HEXID AND
   SUBSTR(REGEXP_SUBSTR(XDV_ADS_IM_MSG_ET.SERVER, '://([[:alnum:],-]+\.?){1,}'), 4) = SV.SERVER_ID(+)
  );
  RETVAL := SQL%ROWCOUNT;

  -- enqueue load-batch
  enqueue_load              (
                a_job_type      => xdv_generic_const_pkg.pc_dflw_jt_mes_trn,
                a_load_id       => l_load_id,
                a_load_st_ts    => l_load_st_dt,
                a_load_end_ts   => l_load_end_dt,
                a_sender_id     => pc_sndr_amm_tekelec,
                a_message       => 'Message with load-id ' || l_load_id || ', enqueued onto ' || xdv_generic_const_pkg.pc_aq_med_load_amm_qname);

  COMMIT;
  RETURN RETVAL;
  END GN_IM_MSG_INSERT;

END GN_IM_MSG_INSERT_PKG;
