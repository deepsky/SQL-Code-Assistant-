    SELECT
      agg.dde_id AS dde_id,
      agg.ivl AS ivl,
      agg.cnt AS cnt,
      agg.rnum
    FROM
      ( SELECT
            t.*, rownum AS rnum
        FROM
        (   SELECT
                dde_id,
                CASE  WHEN dte_id/60 < 15 THEN 15 WHEN dte_id/60 < 30 THEN 30 WHEN dte_id/60 < 45 THEN 45 ELSE 60 END AS ivl,
                COUNT(*) AS cnt
            FROM
                XDV_STG_AMACS_AGR_15M_T
            GROUP BY dde_id, CASE WHEN dte_id/60 < 15 THEN 15 WHEN dte_id/60 < 30 THEN 30 WHEN dte_id/60 < 45 THEN 45 ELSE 60 END
            ORDER BY dde_id desc, ivl desc
        ) T
      ) AGG
    WHERE
      NVL((SELECT max(dde_id + ivl*60) FROM XDV_LST_AMACS_AGR_15M_T), 0) < agg.dde_id+agg.ivl*60 AND
      rnum > 1
    ORDER BY dde_id asc, ivl asc;


select *
from dual