CREATE OR REPLACE FORCE VIEW xsl_alt_alert_v
(
  id,
  thr_id,
  thr_obj_id,
  thr_ivl,
  thr_type,
  thr_order,
  thr_cst,
  thr_script,
  smn_id,
  smn_name,
  smn_type,
  smn_tz,
  mon_group_id,
  mon_group_name,
  data_status,
  prev_data_status,
  svc_id,
  svc_name,
  svc_obj_type,
  svc_alias,
  severity,
  detect_time,
  detect_ts,
  real_time,
  real_ts,
  revise_time,
  revise_ts,
  short_descr,
  long_descr,
  cleared_by,
  thr_value,
  descr,
  cust_id,
  cust_full_name,
  is_suppressed,
  is_cleared,
  mtr_id,
  mtr_obj_id,
  mtr_name,
  mtr_type,
  mtr_unit,
  mtr_minint,
  mtr_maxint,
  mtr_maxfract,
  mtr_minfract,
  mtr_multiplier,
  mtr_cst,
  mtr_cst_name,
  iskpi,
  prev_severity,
  comp_sub_type,
  rc_id,
  tt_number,
  tt_status,
  tt_correlated,
  note,
  upd_cnt,
  ack_destination,
  ack_user,
  source_name,
  ack_time,
  ack_fwd_status,
  ack_fwd_flag,
  sender_id,
  arm_cust_id,
  arm_cust_name,
  MO_MARK_LABEL,
  MO_MARK_EXPIRATION
) AS
SELECT /* $Revision: 6.84 $ */
  a.id                     id,
  a.thr_id                 thr_id,
  t.obj_id                 thr_obj_id,
  t.ivl                    thr_ivl,
  t.type                   thr_type,
  t.thr_order              thr_order,
  t.cst_id                 thr_cst,
  Decode(t.execute_script, 0, '',
         Decode(t.override_default_script, 1, t.script_file,
               (SELECT script_file FROM xsl_mon_monitor_t WHERE id = t.mon_id))) AS thr_script,
  a.smn_id                 smn_id,
  smn.name                 smn_name,
  smn.type                 smn_type,
  smn.tz_id                smn_tz,
  smn.mgr_id               mon_group_id,
  g.name                   mon_group_name,
  a.data_status            data_status,
  a.prev_data_status       prev_data_status,
  a.svc_id                 svc_id,
  o.text_id                svc_name,
  o.obj_type               svc_obj_type,
  o.alias                  svc_alias,
  a.severity               severity,
  a.detect_time            detect_time,
  xsl_date_pkg.date_to_millis(a.detect_time) detect_ts,
  a.real_time              real_time,
  xsl_date_pkg.date_to_millis(a.real_time) real_ts,
  a.revise_time            revise_time,
  xsl_date_pkg.date_to_millis(a.revise_time) revise_ts,
  ''                       short_descr,
  ''                       long_descr,
  a.cleared_by             cleared_by,
  a.thr_value              thr_value,
  a.descr                  descr,
  o.cust_id                cust_id,
  Decode(o.cust_id, NULL, '', xsl_customer_pkg.get_cust_path(o.cust_id)) cust_full_name,
  a.is_suppressed          is_suppressed,
  Decode(a.cleared_by, NULL, 0, 1 ) is_cleared,
  t.mtr_id                 mtr_id,
  m.obj_id                 mtr_obj_id,
  m.name                   mtr_name,
  m.type_mask              mtr_type,
  m.units                  mtr_unit,
  m.format_min_int_digits  mtr_minint,
  m.format_max_int_digits  mtr_maxint,
  m.format_max_frac_digits mtr_maxfract,
  m.format_min_frac_digits mtr_minfract,
  m.format_multiplier      mtr_multiplier,
  m.cst_id                 mtr_cst,
  typ.name                 mtr_cst_name,
  m.is_kpi                 iskpi,
  a.prev_severity          prev_severity,
  o.obj_type               comp_sub_type,
  (SELECT id FROM xsl_arm_alarm_t arm
    WHERE arm.nwo_id = svc_id AND ROWNUM=1
  )                        rc_id,
  a.tt_number              tt_number,
  a.tt_status              tt_status,
  a.tt_correlated          tt_correlated,
  a.note                   note,
  a.upd_cnt                upd_cnt,
  ''                       ack_destination,
  a.ack_user               ack_user,
  ''                       source_name,
  a.ack_time               ack_time,
  (CASE WHEN ack_user IS NOT NULL AND ack_time IS NOT NULL THEN 0
        ELSE -1 END)       ack_fwd_status,
  0                        ack_fwd_flag,
  ''                       sender_id,
  0                        arm_cust_id,
  ''                       arm_cust_name,
  refs.TYPE_NAME           MO_MARK_LABEL,
  o.MO_MARK_EXPIRATION     MO_MARK_EXPIRATION
FROM
  xsl_alt_alert_t   a,
  xsl_smn_svc_mon_t smn,
  xsl_thr_threshold_t t,
  xsl_nwo_nwobj_t   o,
  xsl_mtr_metric_t m,
  xsl_mgr_mon_group_t g,
  xsl_cst_comp_sub_types_t typ,
  (SELECT SYSDATE+1 maxdate FROM dual) d,
  XSL_REF_REFERENCE_T refs
WHERE a.smn_id = smn.id
  AND a.thr_id = t.id
  AND a.svc_id = o.id(+)
  AND t.mtr_id = m.obj_id
  AND a.detect_time BETWEEN m.create_date AND NVL(m.freeze_date - 0.00001574074074, d.maxdate)
  AND smn.mgr_id = g.id(+)
  AND typ.id = m.cst_id
  AND o.mo_mark_id=refs.id(+)
  AND ( refs.TYPE_CLASS is null OR refs.TYPE_CLASS='&def_ref_mo_mark_tag')
/

CREATE OR REPLACE FORCE VIEW xsl_alt_alert_opt_fld_v AS
SELECT a.*,
       '' opt_fld_1_12,
       '' opt_fld_13_25
FROM xsl_alt_alert_v a
/

CREATE OR REPLACE VIEW XSL_SG_ALERTS_BY_FQN_V (ID,FQN,OBJ_TYPE,alert_id,SEVERITY)
  AS SELECT A.id ,A.name,A.OBJ_TYPE,B.id,B.SEVERITY FROM xsl_top_nwobj_v A, xsl_alt_alert_t B
  WHERE A.id=B.svc_id and (B.cleared_by IS NULL)
/

CREATE OR REPLACE VIEW XSL_SG_CRITICAL_ALERTS_V (ID,type,CRITICAL)
  AS SELECT A.svc_id,B.type,count(A.SEVERITY) FROM xsl_thr_threshold_t B, xsl_alt_alert_t A
  WHERE (A.cleared_by IS NULL) and A.SEVERITY=1 and A.thr_id = B.id GROUP BY A.svc_id,B.type
/

CREATE OR REPLACE VIEW XSL_SG_MAJOR_ALERTS_V (ID,type,MAJOR)
  AS SELECT A.svc_id,B.type,count(A.SEVERITY) FROM xsl_thr_threshold_t B, xsl_alt_alert_t A
  WHERE (A.cleared_by IS NULL) and A.SEVERITY=2 and A.thr_id = B.id GROUP BY A.svc_id,B.type
/

CREATE OR REPLACE VIEW XSL_SG_MINOR_ALERTS_V (ID,type,MINOR)
  AS SELECT A.svc_id,B.type,count(A.SEVERITY) FROM xsl_thr_threshold_t B, xsl_alt_alert_t A
  WHERE (A.cleared_by IS NULL) and A.SEVERITY=4 and A.thr_id = B.id GROUP BY A.svc_id,B.type
/

CREATE OR REPLACE VIEW XSL_SG_WARNING_ALERTS_V (ID,type,WARNING)
  AS SELECT A.svc_id,B.type,count(A.SEVERITY) FROM xsl_thr_threshold_t B, xsl_alt_alert_t A
  WHERE (A.cleared_by IS NULL) and A.SEVERITY=8 and A.thr_id = B.id GROUP BY A.svc_id,B.type
/

CREATE OR REPLACE VIEW XSL_SG_ALERT_INFO_V (ID,FQN,TYPE,CRITICAL,MAJOR,MINOR,WARNING)
  AS SELECT F.ID ,F.name, xsl_datascope_pkg.CHECK_TYPES(A.type,B.type,C.type,D.type) ,sum(A.CRITICAL),sum(B.MAJOR),sum(C.MINOR),sum(D.WARNING) FROM XSL_SG_CRITICAL_ALERTS_V A,
              XSL_SG_MAJOR_ALERTS_V B,XSL_SG_MINOR_ALERTS_V C,XSL_SG_WARNING_ALERTS_V D,xsl_top_nwobj_v F
  WHERE F.id=A.ID(+) and F.id=B.id(+) and F.id=C.id(+) and F.id=D.id(+) and (A.type=B.type OR A.type IS NULL OR B.type IS NULL)
    and (C.type=B.type OR C.type IS NULL OR B.type IS NULL) and (C.type=D.type OR C.type IS NULL OR D.type IS NULL)
    GROUP BY F.ID ,F.name, xsl_datascope_pkg.CHECK_TYPES(A.type,B.type,C.type,D.type)
/


CREATE OR REPLACE VIEW XSL_SG_IDS_WITH_ALERTS_V (ID,SMN_NAME,SVC_ID,SVC_NAME, SEVERITY, CUST_FULL_NAME,
IS_CLEARED,THR_TYPE, COMP_SUB_TYPE) AS SELECT
  a.id,smn.name,a.svc_id,s.name,a.severity,decode(s.cust_id, null, '', xsl_customer_pkg.GET_CUST_PATH(s.CUST_ID)),a.cleared_by,
  t.TYPE,s.OBJ_TYPE FROM xsl_alt_alert_t a,xsl_smn_svc_mon_t smn,xsl_thr_threshold_t t,xsl_top_nwobj_v s
  WHERE a.smn_id = smn.id(+) and a.thr_id = t.id and a.SVC_ID = s.ID(+) and (a.cleared_by IS NULL)
/

CREATE OR REPLACE VIEW XSL_SG_FQN_V (SMN_NAME,SVC_ID,SVC_NAME,CUST_FULL_NAME, COMP_SUB_TYPE) AS SELECT
  t.name,s.id,s.name,decode(s.cust_id, null, '', xsl_customer_pkg.GET_CUST_PATH(s.CUST_ID)),s.OBJ_TYPE
  FROM (select a.name,b.svc_id,b.smn_id from xsl_smn_svc_mon_t a,xsl_ssv_smn_svc_assoc_t b where a.id=b.smn_id and b.freeze_date is NULL ) t,xsl_top_nwobj_v s
  WHERE s.id = t.svc_id(+)
/

/*
Creating XSL_ALM_ALERT_ENCODED_V
--class Severity
        --CRITICAL = 1;
        --MAJOR    = 2;
        --MINOR    = 4;
        --WARNING  = 8;
        --INTERMINTATE = 16;
        --CLEAR    = 0;
    --class ThresholdType
      --DCI                   = 0;
      --THRESHOLD_CROSSING    = 1;
      --PERSISTENCE_THRESHOLD = 2;
      --TRENDING_THRESHOLD    = 4;
      --OVERTIME_THRESHOLD    = 8;
*/

CREATE OR REPLACE VIEW
  XSL_ALM_ALERT_ENCODED_V AS
SELECT /*+ INDEX(XSL_ALT_SVC_IE) */
  ALT.SVC_ID                   ID,
  'AL'||
    decode(ALT.SEVERITY	,1,'CR1',2,'MJ2',4,'MN3',8,'WR4',16,'IN5',0,'CL6','NN7')||
    decode(THR.TYPE,0,'DC',1,'TC',2,'TP',4,'TR',8,'OT','NN')	MASK,
    COUNT(*)									 COUNTER
FROM
  XSL_ALT_ALERT_T						ALT,
  XSL_THR_THRESHOLD_T				THR
WHERE
  THR.ID = ALT.THR_ID  AND
  ALT.CLEARED_BY IS NULL
GROUP BY
  ALT.SVC_ID,
  'AL'||
    decode(ALT.SEVERITY	,1,'CR1',2,'MJ2',4,'MN3',8,'WR4',16,'IN5',0,'CL6','NN7')||
    decode(THR.TYPE,0,'DC',1,'TC',2,'TP',4,'TR',8,'OT','NN')
UNION ALL
SELECT
  ARM.NWO_ID                   ID,
  'AM'||
      decode(ARM.SEVERITY	,1,'CR1',2,'MJ2',4,'MN3',8,'WR4',16,'IN5',0,'CL6','NN7')||
     'NN'								   MASK,
  COUNT(*)									 COUNTER
FROM
  XSL_ARM_ALARM_T  ARM
WHERE
  ARM.CLEARED_BY IS NULL
GROUP BY
  ARM.NWO_ID,
  'AM'||
        decode(ARM.SEVERITY	,1,'CR1',2,'MJ2',4,'MN3',8,'WR4',16,'IN5',0,'CL6','NN7')||
         'NN'
/




/*
Creating XSL_ALA_ALERT_ENCODED_AGGR_V
*/
CREATE OR REPLACE VIEW
  XSL_ALA_ALERT_ENCODED_AGGR_V AS
SELECT
  ALM.ID                          ID,
  toAggregateAlertMask(ALM.MASK||'='||ALM.COUNTER)  MASK_AGGR
FROM
  XSL_ALM_ALERT_ENCODED_V ALM
GROUP BY
  ALM.ID
/

/*
Creating XSL_ALM_ALERT_MON_AGGR_V
*/
CREATE OR REPLACE VIEW
  XSL_AMA_ALERT_MON_AGGR_V AS
SELECT
  A.SVC_ID ID,
  toAggregateAlertMonitor(smn.NAME)  MON_AGGR,
  toAggregateAlertMonitor(mon_grp.NAME)  MON_GRP_AGGR
FROM
    xsl_alt_alert_t a,
    xsl_smn_svc_mon_t smn,
    XSL_MGR_MON_GROUP_T mon_grp
where
    a.smn_id = smn.id(+) AND
    smn.mgr_id = mon_grp.id(+) AND
    a.CLEARED_BY IS NULL
GROUP BY
    A.SVC_ID
/


/*
Creating XSL_ASM_SUR_FOR_MAP_V
*/
CREATE OR REPLACE VIEW
  XSL_ASM_SUR_FOR_MAP_V AS
SELECT
  NWO.ID											                    ID,
  CST.COMPONENT_TYPE					                    COMP_TYPE,
  NWO.TEXT_ID									                    NAME,
  NWO.ALIAS										                    ALIAS,
  NWO.OBJ_TYPE								                    SUBT_ID,
  CST.NAME										                    SUBT_NAME,
  NWO.CUST_ID									                    CUST_ID,
  CUS.NAME										                    CUST_NAME,
  NWO.SVC_SM_ASSOC_NUM				                    MON_COUNT,
  NWO.LOC_A										                    LOCATION_A,
  NWO.LOC_Z										                    LOCATION_Z,
  NVL(LCL.LONGITUDE,0.0)			                    LONGITUDE_A,
  NVL(LCL.LATITUDE,0.0)				                    LATITUDE_A,
  NULL												                    LONGITUDE_Z,
  NULL												                    LATITUDE_Z,
  NWO.STATUS									                    STATUS,
  ALM.MASK_AGGR								                    MASK,
  to_number(nvl(substr(ALM.MASK_AGGR,1,1),'0'))   SEVERITY,
        ALS_MON.MON_AGGR                                MONITORS,
        ALS_MON.MON_GRP_AGGR	MONGRPS
FROM
    XSL_ALA_ALERT_ENCODED_AGGR_V		    ALM,
  XSL_CST_COMP_SUB_TYPES_T	            CST,
  XSL_CUS_CUSTOMER_T			            CUS,
  XSL_LCL_LOCATION_CLLI_T		            LCL,
  XSL_AMA_ALERT_MON_AGGR_V                ALS_MON,
  XSL_NWO_NWOBJ_T				            NWO
WHERE
  NWO.ID = ALM.ID				    AND
  NWO.LOC_A = LCL.CLLI(+)		AND
  CUS.ID = NWO.CUST_ID			AND
  CST.ID = NWO.OBJ_TYPE			AND
        NWO.ID=ALS_MON.ID(+)			  AND
  NWO.LOC_Z is NULL					AND
  NWO.LOC_A is not NULL
UNION ALL
SELECT
  NWO.ID										                      ID,
  CST.COMPONENT_TYPE				                      COMPONENT_TYPE,
  NWO.TEXT_ID								                      NAME,
  NWO.ALIAS									                      ALIAS,
  NWO.OBJ_TYPE							                      SUBTYPE_ID,
  CST.NAME									                      SUBTYPE_NAME,
  NWO.CUST_ID								                      CUSTOMER_ID,
  CUS.NAME									                      CUSTOMER_NAME,
  NWO.SVC_SM_ASSOC_NUM			                      MONITOR_COUNT,
  NWO.LOC_A									                      LOCATION_A,
  NWO.LOC_Z									                      LOCATION_Z,
  NVL(LCL_A.LONGITUDE,0.0)	                      LONGITUDE_A,
  NVL(LCL_A.LATITUDE,0.0)                         LATITUDE_A,
  NVL(LCL_Z.LONGITUDE,NVL(LCL_A.LONGITUDE,0.0))		LONGITUDE_Z,
  NVL(LCL_Z.LATITUDE,NVL(LCL_A.LATITUDE,0.0))			LATITUDE_Z,
  NWO.STATUS								                      STATUS,
  ALM.MASK_AGGR							                      MASK,
  to_number(nvl(substr(ALM.MASK_AGGR,1,1),'0'))   SEVERITY,
  ALS_MON.MON_AGGR                                MONITORS,
        ALS_MON.MON_GRP_AGGR	MONGRPS
FROM
  XSL_ALA_ALERT_ENCODED_AGGR_V		ALM,
  XSL_CST_COMP_SUB_TYPES_T	      CST,
  XSL_CUS_CUSTOMER_T				      CUS,
  XSL_LCL_LOCATION_CLLI_T		      LCL_A,
  XSL_LCL_LOCATION_CLLI_T		      LCL_Z,
  XSL_AMA_ALERT_MON_AGGR_V        ALS_MON,
  XSL_NWO_NWOBJ_T						      NWO
WHERE
  NWO.ID = ALM.ID						AND
  NWO.LOC_A = LCL_A.CLLI(+) AND
  NWO.LOC_Z = LCL_Z.CLLI(+) AND
  CUS.ID = NWO.CUST_ID			AND
  CST.ID = NWO.OBJ_TYPE			AND
        NWO.ID=ALS_MON.ID(+)			  AND
  NWO.LOC_Z is not NULL			AND
  NWO.LOC_A is not NULL
/





