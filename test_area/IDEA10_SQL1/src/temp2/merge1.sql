-- Service Impact Index metric attribute creation
prompt Schema change from 5/26/2006

MERGE INTO xsl_oav_obj_attr_value_t a
USING (
	SELECT m.obj_id
	FROM xsl_mtr_metric_t m
	GROUP BY m.obj_id
) x
ON (a.atr_id = 504 AND a.obj_id = x.obj_id)
WHEN MATCHED THEN
	UPDATE SET a.c_value = (
		SELECT d.val_name FROM xsl_atd_attr_domain_t d
		WHERE d.atr_id = 504 AND d.val_id = a.n_value
	)
WHEN NOT MATCHED THEN
	INSERT (a.obj_id,a.aux_id,a.atr_id,a.n_value,a.c_value)
	VALUES (x.obj_id,0,504,0,'None');

COMMIT;

create table a123 (
id number
)

select *
from d
