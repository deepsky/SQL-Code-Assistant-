FUNCTION INSERT_TEST_FUNC2
  RETURN INTEGER
  IS

    l_load_id                INTEGER;
    l_load_st_dt             DATE;
    l_load_end_dt            DATE;
    RETVAL INTEGER;
    TZ VARCHAR2(50) := P_TIME_ZONE;

  BEGIN

    INSERT /*+ APPEND */ INTO TEST_TABLE_1
    (TSPAN, MASK_ID, COLUMN_NAME, COLUMN_ID)
    SELECT
          p1.MIN_TSPAN AS TSPAN,
          MSK.ID AS MASK_ID,
          MSK.MASK_NAME AS MASK_NAME,
          CR.COLUMN_NAME AS COLUMN_NAME,
          CR.COLUMN_ID AS COLUMN_ID
    FROM (
            SELECT
                TABLE_NAME,
                REF_TYPE,
                INTERNAL_TABLE_NAME,
                CREATE_SOURCE,
                UPDATE_SOURCE,
                firstval AS MASK_NAME,
                MAX(CREATE_DATE) AS CREATE_DATE,
                MIN_TSPAN
            FROM (
                SELECT
                    TR.TABLE_NAME AS TABLE_NAME,
                    TR1.TABLE_NAME AS REF_TYPE,
                    TR1.INTERNAL_TABLE_NAME AS INTERNAL_TABLE_NAME,
                    MSK.MASK_NAME,
                    FA.FMN_ID,
                    FA.TDM_ID,
                    FA.AMK_ID,
                    FA.FCC_ID,
                    FA.CREATE_DATE AS CREATE_DATE,
                    FA.UPDATE_DATE,
                    FA.ID AS ID,
                    FA.CREATE_SOURCE AS CREATE_SOURCE,
                    FA.UPDATE_SOURCE AS UPDATE_SOURCE,
                    GRA.MIN_TSPAN AS MIN_TSPAN
                    --first_value(MSK.MASK_NAME) over (partition by TR.TABLE_NAME order by FA.CREATE_DATE DESC ) AS firstval
                    --row_number() over (partition by TR.TABLE_NAME order by FA.CREATE_DATE DESC ) seq
                FROM
                    XDV_PRD_FLOW_AGGR_MASK_T FA,
                    XDV_PRD_TABLE_REF_DEF_T TR,
                    XDV_PRD_TABLE_REF_DEF_T TR1,
                    XDV_PRD_FLOW_MATRIX_NODE_T FM,
                    XDV_PRD_STAGE_REF_DEF_T STG,
                    XDV_PRD_GRANULARITY_T GRA,
                    XDV_PRD_AGGR_MASK_T MSK
                WHERE
                    FA.FMN_ID = FM.ID
                    AND FA.TDM_ID = TR1.ID
                    AND FM.TRD_ID = TR.ID
                    AND FM.STG_ID = STG.ID
                    AND FM.GRA_ID = GRA.ID
                    AND STG.STAGE_NAME = 'Mediation'
                    AND GRA.MIN_TSPAN = 60
                    AND MSK.ID = FA.AMK_ID
                    AND MSK.TDM_ID = TR1.ID
                    AND MSK.DATE_IN BETWEEN to_date('YYYY', '1234') AND to_date ('YYYY', '1235')
                    AND TR1.TABLE_NAME = 'Application Type' --'Cause'
                    AND TR.TABLE_NAME in ('TABLE_A', 'TABLE_B')
                    AND FA.OBSOLETE_DATE is null
                    AND EXISTS (SELECT * FROM TABLE_NESTED WHERE UU = 0)
                    ORDER BY TR.TABLE_NAME , TR1.TABLE_NAME, FA.CREATE_DATE DESC, FA.ID
                ) AS s1
                GROUP BY
                    s1.TABLE_NAME,
                    s1.REF_TYPE,
                    s1.INTERNAL_TABLE_NAME,
                    s1.CREATE_SOURCE,
                    s1.UPDATE_SOURCE,
                    s1.firstval,
                    s1.MIN_TSPAN
            ) p1,
            XDV_PRD_AGGR_MASK_T MSK,
            XDV_PRD_TABLE_REF_DEF_T TR,
            XDV_PRD_AGGR_MASK_ELEMENT_T AE,
            XDV_PRD_COLUMN_REF_DEF_T CR
    WHERE
            TR.TABLE_NAME = p1.REF_TYPE
            AND MSK.MASK_NAME = p1.MASK_NAME
            AND MSK.TDM_ID = TR.ID
            AND CR.TRD_ID = TR.ID
            AND AE.MSK_ID = MSK.ID
            AND AE.CRD_ID = CR.ID
            AND NOT EXISTS (SELECT * FROM TABLE_NESTED WHERE UU = 0)
    ORDER BY CR.COLUMN_ID;


  BEGIN
    NULL;
  END;
  RETURN RETVAL;
END INSERT_TEST_FUNC2;
