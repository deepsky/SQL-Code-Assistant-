CREATE OR REPLACE FUNCTION CDR_INSERT(P_TIME_ZONE VARCHAR2 DEFAULT NULL)
  RETURN INTEGER
  IS
    l_load_id                INTEGER;
    l_load_st_dt             DATE;
    l_load_end_dt            DATE;
    RETVAL              INTEGER;
    TZ VARCHAR2(50) := P_TIME_ZONE;

  BEGIN

  IF TZ IS NULL THEN TZ := DBTIMEZONE; END IF;

  SELECT
    MIN(FROM_TZ(END_TIME, TZ) AT TIME ZONE 'GMT'),
    MAX(FROM_TZ(END_TIME, TZ) AT TIME ZONE 'GMT')
  INTO l_load_st_dt, l_load_end_dt
  FROM XDV_ADS_CDR_TRN_ET;

  SELECT XDV_ASM_TRN_SEQ.NEXTVAL INTO l_load_id FROM DUAL;

  INSERT /*+ APPEND */ INTO XDV_CDM_TRN_MEDCDR_FT
  ( TAT_ID, TAC_ID, TRC_ID, TRS_ID, TRQ_ID, SRV_ID, MSISDN_MO, MSISDN_MT,
    IMSI_MO, NETWORK_IND_MO, SUB_TYPE_MO, ROAMER_IND_MO,
    IMSI_MT, NETWORK_IND_MT, ROAMER_IND_MT, IMEI, TAC, SVN, DDE_ID, DTE_ID, DURATION,
    NBR_BYTES_UL, NBR_BYTES_DL, NBR_BYTES_LOST, OTHER_SUB_NBR, CHG_ID, PLN_ID,
    CALL_CHARGE, PREV_BALANCE, CUR_BALANCE, COUNTER, HANDOVERS_2G_3G, HANDOVERS_3G_2G,
    PRIORITY, RECEIPT, TRANSACTION_ID,
    FAC_INT_01, FAC_INT_02, FAC_INT_03, FAC_INT_04, FAC_INT_05, FAC_INT_06, FAC_INT_07, FAC_INT_08, FAC_INT_09, FAC_INT_10,
    FAC_EXT_01, FAC_EXT_02, FAC_EXT_03, FAC_EXT_04, FAC_EXT_05, FAC_EXT_06, FAC_EXT_07, FAC_EXT_08, FAC_EXT_09, FAC_EXT_10,
    INT_CDR_ID, LOAD_ID)
  SELECT
      TAT_ID    AS TAT_ID,
      TAC_ID    AS TAC_ID,
      TRC_ID    AS TRC_ID,
      TRS_ID    AS TRS_ID,
      -1        AS TRQ_ID,
      -1        AS SRV_ID,
      MSISDN_MO    AS MSISDN_MO,
      MSISDN_MT    AS MSISDN_MT,
      IMSI_MO      AS IMSI_MO,
      -1        AS NETWORK_IND_MO,
      -1        AS SUB_TYPE_MO,
      (CASE WHEN ROAMER = 'no' THEN 1 ELSE (CASE WHEN ROAMER = 'yes' THEN 2 ELSE 3 END) END) AS ROAMER_IND_MO,
      IMSI_MT   AS IMSI_MT,
      -1        AS NETWORK_IND_MT,
      3         AS ROAMER_IND_MT,
      IMEI      AS IMEI,
      (CASE WHEN LENGTH(IMEI) >= 8 THEN SUBSTR(IMEI, 1,8) ELSE '-1' END) AS TAC,
      (CASE WHEN LENGTH(IMEI) >= 16 THEN SUBSTR(IMEI, 15, 2) ELSE '-1' END) AS SVN,
      EXTRACT(DAY FROM END_TIME_INTERVAL) * 86400 + EXTRACT(HOUR FROM END_TIME_INTERVAL) * 3600 AS DDE_ID,
      EXTRACT(MINUTE FROM END_TIME_INTERVAL) * 60 + FLOOR(END_TIME_SECONDS) AS DTE_ID,
      DURATION AS DURATION,
      NULL      AS NBR_BYTES_UL,
      NBR_BYTES AS NBR_BYTES_DL,
      NULL      AS NBR_BYTES_LOST,
      NULL      AS OTHER_SUB_NBR,
      CHG_ID    AS CHG_ID,
      PLN_ID    AS PLN_ID,
      CHARGE     AS CALL_CHARGE,
      NEW_BALANCE - CHARGE  AS PREV_BALANCE,
      NEW_BALANCE AS CUR_BALANCE,
      0     AS COUNTER,
      -1    AS HANDOVERS_2G_3G,
      -1    AS HANDOVERS_3G_2G,
      NULL  AS PRIORITY,
      NULL  AS RECEIPT,
      TRANSACTION_ID AS TRANSACTION_ID,
      START_CELL     AS FAC_INT_01,
      -1             AS FAC_INT_02,
      -1                AS FAC_INT_03,
      BSC                AS FAC_INT_04,
      MSC                AS FAC_INT_05,
      -1                AS FAC_INT_06,
      -1                AS FAC_INT_07,
      -1                AS FAC_INT_08,
      -1                AS FAC_INT_09,
      -1                AS FAC_INT_10,
      -1                AS FAC_EXT_01,
      -1                AS FAC_EXT_02,
      -1                AS FAC_EXT_03,
      SMSC              AS FAC_EXT_04,
      -1                AS FAC_EXT_05,
      -1                AS FAC_EXT_06,
      -1                AS FAC_EXT_07,
      -1                AS FAC_EXT_08,
      -1                AS FAC_EXT_09,
      -1                AS FAC_EXT_10,
      (EXTRACT(DAY FROM END_TIME_INTERVAL) * 86400 + EXTRACT(HOUR FROM END_TIME_INTERVAL) * 3600 + EXTRACT(MINUTE FROM END_TIME_INTERVAL) * 60 +
          END_TIME_SECONDS) * 10000000000 +
          50000 + MOD(ROWNUM, 10000)
          AS INT_CDR_ID,
      l_load_id AS LOAD_ID
 FROM (
   SELECT
      TT.ID AS TAT_ID,
      CA.ID AS TRC_ID,
      AC.ID AS TAC_ID,
      SS.ID AS TRS_ID,
      CH.ID AS CHG_ID,
      PL.ID AS PLN_ID,
      ((FROM_TZ(END_TIME, TZ) AT TIME ZONE 'GMT') - C_Y1970) DAY (6) TO SECOND(0) AS END_TIME_INTERVAL,
      EXTRACT(SECOND FROM END_TIME) AS END_TIME_SECONDS,
      DURATION AS DURATION,
      ET.IMSI_MO AS IMSI_MO,
      ET.IMSI_MT AS IMSI_MT,
      ET.MSISDN_MO AS MSISDN_MO,
      ET.MSISDN_MT AS MSISDN_MT,
      ET.IMEI AS IMEI,
      ET.NEW_BALANCE AS NEW_BALANCE,
      ET.CHARGE AS CHARGE,
      ET.NBR_BYTES AS NBR_BYTES,
      ET.START_CELL AS START_CELL,
      ET.BSC AS BSC,
      ET.MSC AS MSC,
      ET.SMSC AS SMSC,
      ET.ROAMER AS ROAMER,
      ET.TRANSACTION_ID AS TRANSACTION_ID
  FROM
      XDV_ADS_CDR_TRN_ET ET,
      XDV_TRN_CAUSES_DT CA,
      XDV_TRN_APP_TYPE_DT TT,
      XDV_TRN_APP_CONTENT_TYPE_DT AC,
      XDV_TRN_STATUS_DT SS,
      XDV_TRN_CDR_PLAN_DT PL,
      XDV_TRN_CDR_CHARGE_DT CH
  WHERE  
      TT.TECHNOLOGY = 'GSM' AND
      TT.INTERFACE = 'SMS' AND
      TT.CATEGORY = 'CDR' AND
      TT.APPLICATION_TYPE = 'SMS' AND
      TT.XDR_TYPE = 'CDR' AND
      TT.PROTOCOL = 'SMS' AND
      TT.PROTOCOL_VERSION = '1' AND
      UPPER(TT.MESSAGE_TYPE) LIKE  UPPER('%' || ET.MSG_TYPE) AND
      UPPER(TT.MESSAGE_CLASS) LIKE  UPPER('%' || ET.MSG_TYPE) AND
      TT.ORIGINATOR = 'MS' AND
      AC.AMK_ID = 0 AND
      AC.TECHNOLOGY = 'GSM' AND
      AC.INTERFACE = 'SMS' AND
      AC.CATEGORY = 'CDR' AND
      UPPER(AC.CONTENT_TYPE) LIKE  UPPER(ET.CONTENT_TYPE || '%') AND
      SS.AMK_ID = 0 AND
      SS.TECHNOLOGY = 'GSM' AND
      SS.INTERFACE = 'SMS' AND
      SS.CATEGORY = 'CDR' AND
      SS.STATUS_CODE like '%' || ET.STATUS AND
      PL.AMK_ID = 0 AND
      PL.TECHNOLOGY = 'GSM' AND
      PL.INTERFACE = 'SMS' AND
      PL.CATEGORY = 'CDR' AND
      PL.PLAN = ET.PLAN_TYPE AND
      CA.AMK_ID = Y1.XDV_DEV_GRP_MOBILE_STATION_DT.AMK_ID AND
      CA.TECHNOLOGY = 'GSM'  AND
      CA.INTERFACE = 'SMS' AND
      CA.CATEGORY = 'CDR' AND
      CA.TYPE = 'Minor' AND
      CA.SEVERITY = 'Failure' AND
      CA.PRIMARY_CAUSE_TYPE = 'SMS' AND
      ( CA.CALL_RESULT = 'Success' OR
      CA.CALL_RESULT = 'Failure' AND CA.PRIMARY_CAUSE_DESC LIKE '%' || ET.CAUSE || '%' ) AND
      CH.AMK_ID = 0 AND
      CH.TECHNOLOGY = 'GSM' AND
      CH.INTERFACE = 'SMS' AND
      CH.CATEGORY = 'CDR' AND
      CH.BILLING_TYPE = ET.BILLING_TYPE AND
      CH.PAID_TYPE = ET.PAID_TYPE AND
      CH.CHARGE_IND = ET.CHARGE_INDICATOR
  );

  RETVAL := SQL%ROWCOUNT;

  -- enqueue load-batch

  enqueue_load              (
                a_job_type      => xdv_generic_const_pkg.pc_dflw_jt_cdr_trn,
                a_load_id       => l_load_id,
                a_load_st_ts    => l_load_st_dt,
                a_load_end_ts   => l_load_end_dt,
                a_sender_id     => pc_sndr_asm_tekelec,
                a_message       => 'Message with load-id ' || l_load_id || ', enqueued onto ' || xdv_generic_const_pkg.pc_aq_med_load_asm_qname);

  COMMIT;
  RETURN RETVAL;
END CDR_INSERT;
