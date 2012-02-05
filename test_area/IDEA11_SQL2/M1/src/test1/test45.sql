create table y
(
    d BLOB,
    dd INTEGER
);

CREATE TABLE dept_80
PARALLEL
AS SELECT * FROM employees
WHERE department_id = 80;





/*
select object_type
from user_objects

select
    parsing_time, l.id
from load_ref_stats l,
load_time_stats t
where l.id = t.id

insert into kip (id) values (3);

select extract (SECOND FROM sysdate)
from kip

alter table load_time_stats add nm


PROCEDURE put_into_recalc_queue
(
p_meas_ivl INTEGER,
p_ts NUMBER,
p_nod_id INTEGER,
p_tz_id INTEGER,
p_coll_ivl INTEGER,
p_mtr_id INTEGER
)
AS
    lv_recalc_ivl   INTEGER;
    lv_source_tz_id INTEGER;
BEGIN
    IF p_meas_ivl < xsl_interval_pkg.ivl_day THEN
        lv_recalc_ivl := xsl_interval_pkg.ivl_day;
        lv_source_tz_id := NULL;
    ELSIF p_meas_ivl = xsl_interval_pkg.ivl_day THEN
        lv_recalc_ivl := xsl_interval_pkg.ivl_month;
        lv_source_tz_id := p_tz_id;
    ELSE
        RETURN;
    END IF;
    INSERT INTO xsl_rdp_rollup_queue_t
    (enq_time, target_ivl, ts, nod_id, tz_id, source_ivl, coll_ivl, mtr_id, target_ts)
    SELECT
        SYSDATE,
        lv_recalc_ivl,
        p_ts,
        p_nod_id,
        tz.id,
        p_meas_ivl,
        p_coll_ivl,
        p_mtr_id,
        tz.t
    FROM
        (SELECT id, xsl_interval_pkg.tstrunc(p_ts, lv_recalc_ivl, java_id) t
        FROM xsl_tzm_timezone_t
        WHERE is_active = 1
        AND (id = lv_source_tz_id OR lv_source_tz_id IS NULL)
        ) tz
    WHERE NOT EXISTS (SELECT 1
    FROM xsl_rdp_rollup_queue_t
    WHERE target_ivl = lv_recalc_ivl
    AND source_ivl = p_meas_ivl
    AND tz_id = tz.id
    AND target_ts = tz.t
    AND nod_id = p_nod_id
    AND mtr_id = p_mtr_id
    AND coll_ivl = p_coll_ivl
    );
    COMMIT;
END put_into_recalc_queue;
/

*/

SELECT
    SYSDATE,
    lv_recalc_ivl,
    p_ts,
    p_nod_id,
    tz.id,
    p_meas_ivl,
    p_coll_ivl,
    p_mtr_id,
    tz.t
FROM werttz
WHERE
    TR.TABLE_NAME = p1.REF_TYPE AND

    MSK.MASK_NAME = p1.MASK_NAME
    AND MSK.TDM_ID = TR.ID
    AND CR.TRD_ID = TR.ID AND
    AE.MSK_ID = MSK.ID
    AND AE.CRD_ID = CR.ID




