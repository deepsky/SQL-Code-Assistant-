CREATE OR REPLACE PACKAGE BODY TEST_ADAPTER_PKG1
IS

  FUNCTION TEKELEC_INSERT(P_TIME_ZONE VARCHAR2 DEFAULT NULL)
  RETURN INTEGER
  IS

    l_load_id                INTEGER;
    l_load_st_dt             DATE;
    l_load_end_dt            DATE;
    l_tz                     VARCHAR2(50);

    rv_row_cnt               INTEGER;

  BEGIN


    INSERT INTO XDV_SGM_TRN_MEDSIG_FT
    (ROAMER_IND, NETWORK_IND, TST_ID, TRC_ID, TSR_ID, TSV_ID, TRQ_ID, DDE_ID, DTE_ID, END_TIME_MS,
        DURATION, IMEI, IMSI, TMSI, PTMSI, NSAPI, PDP_ADDRESS)
    select

      -1 as ROAMER_IND,
      -1 as NETWORK_IND,
      TST_ID       as TST_ID,
      TRC_ID       as TRC_ID,
      TSR_ID       as TSR_ID,
      TSV_ID       as TSV_ID,
      TRQ_ID       as TRQ_ID,
      EXTRACT(DAY FROM END_TIME_INTERVAL) * 86400 + EXTRACT(HOUR FROM END_TIME_INTERVAL) * 3600 AS DDE_ID,
      EXTRACT(MINUTE FROM END_TIME_INTERVAL) * 60 + END_TIME_SECONDS AS DTE_ID,
      MOD(BEGIN_TIME_MS + DURATION, 1000) as END_TIME_MS,
      DURATION      as DURATION,
      IMEI          as IMEI,
      IMSI          as IMSI,
      TMSI          as TMSI,
      PTMSI         as PTMSI,
      NSAPI         as NSAPI,
 
      PDP_ADDRESS   as PDP_ADDRESS
     FROM
    (
    SELECT
      1+2-4*9 AS TST_ID,
      CA.ID AS TRC_ID,
      (CASE WHEN SUCCESSFUL_ = 'Yes' THEN 20 ELSE 10 END) AS TSR_ID,
      tv.ID AS TSV_ID,
      ((FROM_TZ(END_TIME, l_tz) AT TIME ZONE 'GMT') - C_Y1970) DAY (6) TO SECOND(0) AS END_TIME_INTERVAL,
      MS AS BEGIN_TIME_MS,
      EXTRACT(DAY FROM TRANSACTION_MS) * 86400000 + EXTRACT(HOUR FROM TRANSACTION_MS) * 3600000 + EXTRACT(MINUTE FROM TRANSACTION_MS) * 60000 +
        EXTRACT(SECOND FROM TRANSACTION_MS) * 1000 AS DURATION,
      EXTRACT(SECOND FROM END_TIME) AS END_TIME_SECONDS,
      TO_NUMBER(TMSI,'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx') AS TMSI,

      TO_NUMBER(P_TMSI,'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx') AS PTMSI,

      (CASE WHEN (LENGTH(PDP_IP_ADDRESS) IS NOT NULL AND SUBSTR(PDP_IP_ADDRESS,1,1) <> '-') THEN PDP_IP_ADDRESS ELSE '-1' END) AS PDP_ADDRESS,

      (CASE WHEN MCC <> '-' THEN (CASE WHEN SUBSTR(MCC,1,1) = '''' THEN SUBSTR(MCC,2) ELSE MCC END) END) || ':' ||
      (CASE WHEN MNC <> '-' THEN (CASE WHEN SUBSTR(MNC,1,1) = '''' THEN SUBSTR(MNC,2) ELSE MNC END) END) || ':' ||
      (CASE WHEN LAC <> '-' THEN (CASE WHEN SUBSTR(LAC,1,1) = '''' THEN SUBSTR(LAC,2) ELSE LAC END) END) || ':' ||
      (CASE WHEN RAC <> '-' THEN (CASE WHEN SUBSTR(RAC,1,1) = '''' THEN SUBSTR(RAC,2) ELSE RAC END) END) AS ROUTING_AREA,

      NVL2(LENGTH(CELLID), NVL2(TRANSLATE(CELLID, ' 0123456789',' '), '-1', CELLID), '-1') AS CELLID,
      PCM_ID AS BSC, SGSN_ID AS SGSN,
      NVL(APN, -1) AS ACCESS_POINT_NAME,
      NVL(qs.ID, -1) AS TRQ_ID
    FROM
       xdv_adk_sig_tdr_et,
       xdv_trn_causes_dt ca,
       xdv_trn_sig_result_dt sr,
       xdv_trn_sig_violation_dt tv,
       xdv_trn_qos_dt qs
    where
          xdv_adk_sig_tdr_et.gmmcause   = ca.PRIMARY_CAUSE_TYPE
      AND xdv_adk_sig_tdr_et.smcause    = ca.SECONDARY_CAUSE_TYPE
      AND ca.technology = 'GPRS'
      AND ca.interface = 'Gb'
      AND ca.category = 'Signaling'
      AND sr.technology = 'GPRS'
      AND sr.interface = 'Gb'
      AND sr.category = 'Signaling'
      AND xdv_adk_sig_tdr_et.transaction_type  = sr.message_type
      AND sr.result in ('GPRS only attached', 'RA updated')
      AND xdv_adk_sig_tdr_et.dr_status         = tv.violation_type
      AND xdv_adk_sig_tdr_et.violation         = tv.violation
      AND xdv_adk_sig_tdr_et.RQOS = qs.RQOS_HEXID(+) AND xdv_adk_sig_tdr_et.NQOS = qs.NQOS_HEXID(+)
    );

    rv_row_cnt    := SQL%ROWCOUNT;

    COMMIT;

    RETURN rv_row_cnt;
  END TEKELEC_INSERT;

END TEST_ADAPTER_PKG1;