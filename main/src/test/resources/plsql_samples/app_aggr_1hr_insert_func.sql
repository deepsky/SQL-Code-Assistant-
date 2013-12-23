CREATE OR REPLACE FUNCTION APP_AGGR_1HR_INSERT22(P_TIME_ZONE VARCHAR2 DEFAULT NULL)
  RETURN INTEGER
  IS
    l_load_id                INTEGER;
    l_load_st_dt             DATE;
    l_load_end_dt            DATE;
    l_end_ts                 TIMESTAMP;
    l_end_timeinterval_ts    INTERVAL DAY (6) TO SECOND(0);
    l_end_seconds            NUMBER(8,3);
    l_dde_id                NUMBER(8);
    l_dte_id                NUMBER;
    RETVAL INTEGER;
    TZ VARCHAR2(50) := P_TIME_ZONE;
    TZ1 VARCHAR2(50) := P_TIME_ZONE;

  BEGIN

IF TZ IS NULL
THEN
    TZ := DBTIMEZONE;
    TZ1 := DBTIMEZONE;
ELSIF TZ IS NOT NULL
THEN
    NULL;
ELSE
    TZ := NULL;
END IF;

  SELECT
    MIN(FROM_TZ(END_TIME, TZ) AT TIME ZONE 'GMT'),
    MAX(FROM_TZ(END_TIME, TZ) AT TIME ZONE 'GMT')
  INTO l_load_st_dt, l_load_end_dt
  FROM XDV_ADS_1HR_APPAGGR_ET;

  SELECT XDV_ASM_TRN_SEQ.NEXTVAL INTO l_load_id FROM DUAL;

  l_end_timeinterval_ts := ((FROM_TZ(l_load_st_dt, TZ) AT TIME ZONE 'GMT') - C_Y1970) DAY (6) TO SECOND(0);
  l_end_seconds := 0; --EXTRACT(SECOND FROM l_load_st_dt);
  l_dde_id := FLOOR(xdv_date_pkg.date_to_sec(l_load_st_dt) / xdv_generic_const_pkg.pc_dt_dde_ivl) * xdv_generic_const_pkg.pc_dt_dde_ivl;
  --l_dde_id := EXTRACT(DAY FROM l_end_timeinterval_ts) * 86400 + EXTRACT(HOUR FROM l_end_timeinterval_ts) * 3600;
  l_dte_id := EXTRACT(MINUTE FROM l_end_timeinterval_ts) * 60 + FLOOR(l_end_seconds);


  INSERT /*+ APPEND HELLO */ INTO XDV_AAM_1HR_MEDAPA_FT
  ( TAT_ID, TAC_ID, TRC_ID, TRS_ID, TRQ_ID, SERVER_ID, GSV_ID, USER_AGENT, GUA_ID, MNC, MCC, IMSI, ROAMER_IND,
    NETWORK_IND, GNW_ID, TAC, SVN, IMEI, GDV_ID, COUNTRY_CODE, MSISDN, GSB_ID, DDE_ID, DTE_ID,
    FAC_INT_01, FAC_INT_02, FAC_INT_03, FAC_INT_04, FAC_INT_05, FAC_INT_06, FAC_INT_07, FAC_INT_08, FAC_INT_09, FAC_INT_10,
    GPI_ID,
    FAC_EXT_01, FAC_EXT_02, FAC_EXT_03, FAC_EXT_04, FAC_EXT_05, FAC_EXT_06, FAC_EXT_07, FAC_EXT_08, FAC_EXT_09, FAC_EXT_10,
    GPE_ID,
    AC, AS_DURATION, AS_DATA_TRANS_DURATION, AS_NBR_MESSAGES, AS_NBR_BYTES, AS_NBR_BYTES_LOST,
    INT_APPAGGR_ID, LOAD_ID)
  SELECT
      TAT_ID    AS TAT_ID,
      -1        AS TAC_ID,
      TRC_ID    AS TRC_ID,
      TRS_ID    AS TRS_ID,
      -1        AS TRQ_ID,
      '-1'      AS SERVER_ID,
      -1        AS GSV_ID,
      -1        AS USER_AGENT,
      -1        AS GUA_ID,
      (CASE LENGTH(IMSI) WHEN 6 THEN SUBSTR(IMSI, 4,2) ELSE '-1' END) AS MNC,
      (CASE WHEN LENGTH(IMSI) >= 4 THEN SUBSTR(IMSI, 1,3) ELSE '-1' END) AS MCC,
      IMSI      AS IMSI,
        -1      AS ROAMER_IND,
      -1        AS NETWORK_IND,
      -1        AS GNW_ID,
      (CASE WHEN LENGTH(IMEI) >= 8 THEN SUBSTR(IMEI, 1,8) ELSE '-1' END) AS TAC,
      (CASE WHEN LENGTH(IMEI) >= 16 THEN SUBSTR(IMEI, 15, 2) ELSE '-1' END) AS SVN,
      IMEI      AS IMEI,
      -1        AS GDV_ID,
      -1        AS COUNTRY_CODE,
      MSISDN    AS MSISDN,
      -1        AS GSB_ID,
      EXTRACT(DAY FROM (l_dde_id - INTERVAL '30' MINUTE)) * 86400 + EXTRACT(HOUR FROM (l_dde_id)) * 3600 AS DDE_ID,
--      l_dde_id AS DDE_ID,
      l_dte_id AS DTE_ID,
      CELL_IDENTITY     AS FAC_INT_01,
      ROUTING_AREA      AS FAC_INT_02,
      -1                AS FAC_INT_03,
      -1                AS FAC_INT_04,
      -1                AS FAC_INT_05,
      -1                AS FAC_INT_06,
      -1                AS FAC_INT_07,
      -1                AS FAC_INT_08,
      -1                AS FAC_INT_09,
      -1                AS FAC_INT_10,
      -1        AS GPI_ID,
      'S'||COLLECTION_SOURCE    AS FAC_EXT_01,
      'G'||COLLECTION_SOURCE    AS FAC_EXT_02,
      ACCESS_POINT_NAME         AS FAC_EXT_03,
      -1                AS FAC_EXT_04,
      -1                AS FAC_EXT_05,
      -1                AS FAC_EXT_06,
      -1                AS FAC_EXT_07,
      -1                AS FAC_EXT_08,
      -1                AS FAC_EXT_09,
      -1                AS FAC_EXT_10,
      -1    AS GPE_ID,
      AS_COUNTER    AS AC,
      AS_DURATION   AS AS_DURATION,
      AS_DURATION   AS AS_DATA_TRANS_DURATION,
      AS_NBR_MESSAGES   AS AS_NBR_MESSAGES,
      AS_NBR_BYTES  AS AS_NBR_BYTES,
      0             AS AS_NBR_BYTES_LOST,
      CAST (INT_SESSION_ID AS INTEGER) AS INT_SESSION_ID,
      (EXTRACT(DAY FROM l_end_timeinterval_ts) * 86400 + EXTRACT(HOUR FROM l_end_timeinterval_ts) * 3600
          + EXTRACT(MINUTE FROM l_end_timeinterval_ts) * 60 +
          l_end_seconds) * 10000000000 +
          50000 + MOD(ROWNUM, 10000)
          AS INT_APPAGGR_ID,
      l_load_id AS LOAD_ID
   FROM
  (
  SELECT
    TAT_ID,
    TRC_ID,
    TRS_ID,
    IMSI,
    MSISDN,
    IMEI,
    CELL_IDENTITY,
    ROUTING_AREA,
    COLLECTION_SOURCE,
    ACCESS_POINT_NAME,
    SUM(NBR_BYTES) AS AS_NBR_BYTES,
    SUM(NBR_MESSAGES) AS AS_NBR_MESSAGES,
    SUM(DURATION) AS AS_DURATION,
    COUNT(*) AS AS_COUNTER
FROM (
  SELECT
      TT.ID AS TAT_ID,
      CA.ID AS TRC_ID,
      NVL((SELECT CA.ID FROM XDV_TRN_CAUSES_DT CA
        WHERE CA.TECHNOLOGY = 'GPRS' AND CA.INTERFACE = 'Gn' AND CA.CATEGORY = 'Application' AND
        (XDV_ADS_MMS_MSG_ET.MMS_TYPE IN ('m-notification-ind', 'm-notifyresp-ind') AND CA.PRIMARY_CAUSE_TYPE = 'MMS-Notification' OR
        XDV_ADS_MMS_MSG_ET.MMS_TYPE IN ('m-send-req', 'm-send-conf') AND CA.PRIMARY_CAUSE_TYPE = 'MMS-Send') AND
        UPPER(CA.PRIMARY_CAUSE_DESC) = UPPER(XDV_ADS_MMS_MSG_ET.MMS_STATUS)),
        -1) AS TRC_ID,
      (CASE WHEN LENGTH(IMEI) >= 8 AND TRANSLATE(TRANSLATE(IMEI, '1''- ', '1'), ' 0123456789', ' ') IS NULL
          THEN TRANSLATE(IMEI, '1''- ', '1') ELSE '-1' END)
          AS IMEI,
      (CASE WHEN LENGTH(MSISDN) >= 8 AND TRANSLATE(TRANSLATE(MSISDN, '1''- ', '1'), ' 0123456789', ' ') IS NULL
          THEN TRANSLATE(MSISDN, '1''- ', '1') ELSE '-1' END)
          AS MSISDN,
      (CASE WHEN LENGTH(IMSI) >= 6 AND TRANSLATE(TRANSLATE(IMSI, '1''- ', '1'), ' 0123456789', ' ') IS NULL
          THEN TRANSLATE(IMSI, '1''- ', '1') ELSE '-1' END)
          AS IMSI,
      CELL_IDENTITY AS CELL_IDENTITY,
      ROUTING_AREA AS ROUTING_AREA,
      COLLECTION_SOURCE AS COLLECTION_SOURCE,
      NVL(ACCESS_POINT_NAME,-1) AS ACCESS_POINT_NAME,
      NBR_BYTES AS NBR_BYTES,
      DURATION AS DURATION,
      NBR_MESSAGES AS NBR_MESSAGES
  FROM
      XDV_ADS_1HR_APPAGGR_ET ET,
      XDV_TRN_CAUSES_DT CA,
      XDV_TRN_APP_TYPE_DT TT,
      XDV_TRN_STATUS_DT SS
  WHERE
      TT.TECHNOLOGY = 'GPRS' AND
      TT.INTERFACE = 'Gn' AND
      TT.CATEGORY = 'Application' AND
      TT.APPLICATION_TYPE = 'Instant Messaging' AND
      TT.XDR_TYPE = 'Session' AND
      TT.PROTOCOL = ET.PROTOCOL AND
      TT.MESSAGE_TYPE is null AND
      TT.MESSAGE_CLASS is null AND
      TT.ORIGINATOR is null AND
      TT.TYPE(+) = 'SIP' AND
      NVL(TT.MESSAGE_TYPE, '-') = NVL(ET.SESSION_TYPE, '-') AND
      SS.TECHNOLOGY = 'GPRS' AND SS.AMK_ID = 20050 AND
      SS.INTERFACE = 'Gn' AND
      SS.CATEGORY = 'Application' AND
      SS.STATUS_CODE = ET.STATUS_CODE AND
      (ET.STATUS_CODE = 'OK' AND CA.ID = -1 OR ET.STATUS_CODE <> 'OK' AND
      CA.TECHNOLOGY = 'GPRS' AND CA.INTERFACE = 'Gn' AND CA.CATEGORY = 'Application' AND CA.SECONDARY_CAUSE_ID IS NULL AND
      CA.PRIMARY_CAUSE_TYPE = 'IM-'|| ET.PROTOCOL AND CA.AMK_ID = 11080 AND
      (ET.PROTOCOL = 'SIP' AND CA.PRIMARY_CAUSE_ID = ET.CREATION_STATUS OR
      ET.PROTOCOL = 'XMPP' AND CA.PRIMARY_CAUSE_DESC = ET.CREATION_TEXT))
  )
  GROUP BY
    TAT_ID,
    TRC_ID,
    TRS_ID,
    IMSI,
    MSISDN,
    IMEI,
    CELL_IDENTITY,
    ROUTING_AREA,
    COLLECTION_SOURCE,
    ACCESS_POINT_NAME
  );
  
  RETVAL := SQL%ROWCOUNT;

        IF RETVAL > 0
        THEN
          XDV_ADAPTER_PKG.enqueue_load              (
                      a_job_type      => xdv_generic_const_pkg.pc_dflw_jt_cda_15m,
                      a_load_id       => l_load_id,
                      a_load_st_ts    => l_load_st_dt,
                      a_load_end_ts   => l_load_end_dt,
                      a_sender_id     => XDV_ADAPTER_PKG.pc_sndr_asm_tekelec,
                      a_message       => 'Message with load-id ' || l_load_id || ', enqueued onto ' || xdv_generic_const_pkg.pc_aq_med_load_asm_qname);
        END IF;

  COMMIT;
  RETURN RETVAL;
END APP_AGGR_1HR_INSERT22;
