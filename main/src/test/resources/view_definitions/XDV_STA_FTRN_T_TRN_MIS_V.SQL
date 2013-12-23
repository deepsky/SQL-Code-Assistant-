CREATE OR REPLACE VIEW XDV_STA_FTRN_T_TRN_MIS_V
(PARTITION_START_TIME, DDE_ID, SOURCE_TRAN_COUNT, DEST_TRAN_COUNT, TRANSFER_DELTA, SRC2DEST_PERC
) AS
SELECT
            dt.date_date partition_start_time,
            dt.id dde_id,
            trn.src_cnt source_tran_count,
            trn.dst_cnt dest_tran_count,
            trn.src_cnt - trn.dst_cnt transfer_delta,
            (CASE WHEN (trn.src_cnt > 0 AND trn.dst_cnt > 0) THEN SUBSTR((trn.dst_cnt / trn.src_cnt * 100), 1, INSTR((trn.dst_cnt / trn.src_cnt * 100), '.') + 3) ELSE '--' END) src2dest_perc
FROM (
    SELECT NVL(src.dde_id, dst.dde_id) dde_id, NVL(src.cnt, 0) as src_cnt, NVL(dst.cnt, 0) as dst_cnt
    FROM (
            SELECT dde_id, count(*) cnt FROM XDV_TTM_TRN_MEDTKT_FT@xdv_med_lnk GROUP BY dde_id order by dde_id
        ) src
        FULL OUTER JOIN (SELECT dde_id, count(*) cnt FROM XDV_TTM_TRN_MEDTKT_FT GROUP BY dde_id order by dde_id) dst
        ON src.dde_id = dst.dde_id
    ) trn 
    INNER JOIN xdv_dtm_hrdate_dt dt
    ON trn.dde_id = dt.id
ORDER BY dt.id
