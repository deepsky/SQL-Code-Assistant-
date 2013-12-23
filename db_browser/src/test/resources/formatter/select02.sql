    SELECT
      1300  AS TAT_ID,
      -2    AS TAC_ID,
      CASE WHEN
            TRIM(TERMINATING_REASON) IS NULL
            OR TERMINATING_REASON = '00000'
            OR TERMINATING_REASON = '01667'
            OR TERMINATING_REASON = '03735'
      THEN 17001 ELSE 17000 END AS TRC_ID,
--      NVL(TO_NUMBER(TRIM(LTRIM(TERMINATING_REASON, '0'))), -1)  AS TRC_ID,
      -2    AS TRS_ID,
      -2    AS TRQ_ID,
      l_dde_id  AS DDE_ID,
      l_dte_id AS DTE_ID,
      3150 AS DTZ_ID,
      -1    AS SERVER_ID,
      -2    AS GSV_ID,
      -1    AS USER_AGENT,
      -2    AS GUA_ID,
      -1    AS MNC,
      -1    AS MCC,
      -1    AS IMSI,
      -1    AS ROAMER_IND,
      -1    AS NETWORK_IND,
      -2    AS GNW_ID,
      -1    AS TAC,
      -1    AS SVN,
      -1    AS IMEI,
      -2    AS GDV_ID,
      -1    AS COUNTRY_CODE,
      -1    AS MSISDN,
      -2    AS GSB_ID,
      -1    AS FAC_INT_01,
      -1    AS FAC_INT_02,
      -1    AS FAC_INT_03,
      -1    AS FAC_INT_04,
      -1    AS FAC_INT_05,
      NVL(MSC_ID, -1)    AS FAC_INT_06,
      -1    AS FAC_INT_07,
      -1    AS FAC_INT_08,
      -1    AS FAC_INT_09,
      -1    AS FAC_INT_10,
      -2    AS GPI_ID,
      -1    AS FAC_EXT_01,
      -1    AS FAC_EXT_02,
      -1    AS FAC_EXT_03,
      -1    AS FAC_EXT_04,
      -1    AS FAC_EXT_05,
      -1    AS FAC_EXT_06,
      -1    AS FAC_EXT_07,
      -1    AS FAC_EXT_08,
      -1    AS FAC_EXT_09,
      -1    AS FAC_EXT_10,
      -2    AS GPE_ID,
      COUNT(*)  AS AC,
      NULL  AS AS_DURATION,
      NULL  AS AS_DATA_TRANS_DURATION,
      NULL  AS AS_NBR_MESSAGES,
      NULL  AS AS_NBR_BYTES,
      NULL  AS AS_NBR_BYTES_LOST,
      NULL  AS INT_APPAGGR_ID,
      l_load_id AS LOAD_ID
    FROM
        XDV_STG_AMACS_AGR_15M_T EXT
    WHERE
        (EXT.DDE_ID + EXT.DTE_ID) BETWEEN (l_dde_id + l_dte_id - 15*60) AND (l_dde_id + l_dte_id)
    GROUP BY
        CASE WHEN
            TRIM(TERMINATING_REASON) IS NULL
            OR TERMINATING_REASON = '00000'
            OR TERMINATING_REASON = '01667'
            OR TERMINATING_REASON = '03735'
        THEN 17001 ELSE 17000 END,
        NVL(MSC_ID, -1)
