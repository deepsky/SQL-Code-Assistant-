PACKAGE xdv_agr_dt_bloat_pkg AS

    -----------------------------------------------------------------------------
    -- Type definitions
    -----------------------------------------------------------------------------
    TYPE ntt_cols_list  IS TABLE OF XDV_PRD_COLUMN_REF_DEF_T.COLUMN_NAME%TYPE;

    TYPE amk_cols_rc IS RECORD (
        amk_id    XDV_PRD_AGGR_MASK_T.ID%TYPE,
        amk_cols  ntt_cols_list);

    TYPE amk_lvl_rc IS RECORD (
        amk_id    XDV_PRD_AGGR_MASK_T.ID%TYPE,
        amk_lvl   XDV_PRD_AGGR_MASK_ELEMENT_T.POSITION%TYPE);

    TYPE va_amk_list IS VARRAY(10) OF amk_cols_rc;
    TYPE va_col_list IS VARRAY(10) OF VARCHAR2(32);

    -----------------------------------------------------------------------------
    -- Global constants and variables
    -----------------------------------------------------------------------------

    pc_pkg_name             CONSTANT VARCHAR2(64)           := 'xdv_agr_dt_bloat_pkg';
    pc_pkg_log_section      CONSTANT VARCHAR2(64)           := 'xdv_agr_dt_bloat';
    pc_pkg_cfg_section      CONSTANT VARCHAR2(64)           := 'agr_dt_bloat';

    pc_eoln  CONSTANT VARCHAR2(3) := chr(10);
    -- pc_eoln  CONSTANT VARCHAR2(3) := chr(10);

    pc_blt_cols  CONSTANT va_col_list := va_col_list(
        'MSK_EL_01', 'MSK_EL_02', 'MSK_EL_03', 'MSK_EL_04', 'MSK_EL_05',
        'MSK_EL_06', 'MSK_EL_07', 'MSK_EL_08', 'MSK_EL_09', 'MSK_EL_10');
    pc_attr_cols CONSTANT va_col_list := va_col_list(
        'ATTR_01', 'ATTR_02', 'ATTR_03', 'ATTR_04', 'ATTR_05',
        'ATTR_06', 'ATTR_07', 'ATTR_08', 'ATTR_09', 'ATTR_10');

    pc_mos_attr CONSTANT va_col_list := va_col_list('SOFTWARE');
    pc_sub_attr CONSTANT va_col_list := va_col_list('COUNTRY', 'PLAN_TYPE');
    pc_snt_attr CONSTANT va_col_list := va_col_list('MCCMNC','MCC_DESC','MNC_DESC', 'ROAMER_IND', 'ROAMER_DESC', 'NETWORK_IND', 'PLAN_TYPE');
    pc_pth_attr CONSTANT va_col_list := va_col_list('FACILITY_01_NAME', 'FACILITY_02_NAME', 'FACILITY_03_NAME', 'FACILITY_04_NAME', 'FACILITY_05_NAME', 'FACILITY_06_NAME', 'FACILITY_07_NAME', 'FACILITY_08_NAME', 'FACILITY_09_NAME', 'FACILITY_10_NAME' );

    pc_int_list_asb  ntt_cols_list := ntt_cols_list('COUNTRY_CODE_INT','MSISDN_INT');
    pc_int_list_anw  ntt_cols_list := ntt_cols_list('IMSI_INT');

    pc_amk_list_adv  va_amk_list := NULL;
    pc_amk_list_aua  va_amk_list := NULL;
    pc_amk_list_asb  va_amk_list := NULL;
    pc_amk_list_anw  va_amk_list := NULL;
    pc_amk_list_asv  va_amk_list := NULL;
    pc_amk_list_api  va_amk_list := NULL;
    pc_amk_list_ape  va_amk_list := NULL;

    pc_amk_cols_adv  VARCHAR2(256);
    pc_amk_cols_aua  VARCHAR2(256);
    pc_amk_cols_asb  VARCHAR2(256);
    pc_amk_cols_anw  VARCHAR2(256);
    pc_amk_cols_asv  VARCHAR2(256);
    pc_amk_cols_api  VARCHAR2(256);
    pc_amk_cols_ape  VARCHAR2(256);

    pc_amk_lvl_adv   NUMBER := -1;
    pc_amk_lvl_aua   NUMBER := -1;
    pc_amk_lvl_asb   NUMBER := -1;
    pc_amk_lvl_anw   NUMBER := -1;
    pc_amk_lvl_asv   NUMBER := -1;
    pc_amk_lvl_api   NUMBER := -1;
    pc_amk_lvl_ape   NUMBER := -1;

    -----------------------------------------------------------------------------
    -- Exceptions
    -----------------------------------------------------------------------------


    -----------------------------------------------------------------------------
    -- Fuctional and procedures
    -----------------------------------------------------------------------------
    PROCEDURE init(a_dim_tbl          IN VARCHAR2);

    FUNCTION get_amk_ids(a_job_type   IN INTEGER,
                         a_dim_tbl    IN VARCHAR2)
    RETURN DBMS_SQL.NUMBER_TABLE;

    FUNCTION get_amk_lvlnid(a_job_type  IN INTEGER,
                            a_dim_tbl   IN VARCHAR2,
                            a_fcc_name  IN VARCHAR2 DEFAULT NULL)
    RETURN amk_lvl_rc;

    FUNCTION get_amk_cols(a_dim_tbl     IN VARCHAR2)
    RETURN va_amk_list;

    FUNCTION get_max_agr_lvl(a_dim_tbl     IN VARCHAR2)
    RETURN NUMBER;

    FUNCTION update_mos(a_mos_tbl   IN VARCHAR2,
                        a_lsn       IN INTEGER)
    RETURN NUMBER;

    FUNCTION update_nwk(a_dim_tbl   IN VARCHAR2,
                        a_lsn       IN INTEGER)
    RETURN NUMBER;

    FUNCTION update_sub(a_dim_tbl   IN VARCHAR2,
                        a_lsn       IN INTEGER)
    RETURN NUMBER;

    PROCEDURE bloat_dim_adv(a_msg  IN xdv_flow_status_payload_tp);
    PROCEDURE bloat_dim_asb(a_msg  IN xdv_flow_status_payload_tp);
    PROCEDURE bloat_dim_anw(a_msg  IN xdv_flow_status_payload_tp);
    PROCEDURE bloat_dim_api(a_msg  IN xdv_flow_status_payload_tp);
    PROCEDURE bloat_dim_ape(a_msg  IN xdv_flow_status_payload_tp);

    PROCEDURE bloat_dim_apt(a_msg     IN xdv_flow_status_payload_tp,
                            a_dim_tbl IN VARCHAR2 );

    FUNCTION is_aggr_enabled(a_job_type     IN INTEGER)
    RETURN BOOLEAN;

    -----------------------------------------------------------------------------
    -- test
    -----------------------------------------------------------------------------
    PROCEDURE test;

    FUNCTION pop_blt_tt(a_job_type  IN INTEGER,
                        a_lsn       IN INTEGER,
                        a_amK_lvl   IN INTEGER,
                        a_dim_tbl   IN VARCHAR2,
                        a_amk_list  IN va_amk_list,
                        a_attr_list IN va_col_list DEFAULT NULL)
    RETURN NUMBER;

    PROCEDURE bloat_agr_mos(a_msg  IN xdv_flow_status_payload_tp);

END xdv_agr_dt_bloat_pkg;