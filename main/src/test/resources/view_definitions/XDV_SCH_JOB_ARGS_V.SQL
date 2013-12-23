CREATE OR REPLACE VIEW XDV_SCH_JOB_ARGS_V
(SCHEDULED_JOB_ID, SCHEDULED_JOB_NAME, DBMS_JOB_NAME, JOB_ID, JOB_NAME,
 ARG_POSITION, ARG_NAME, ARG_DATA_TYPE, ARG_VALUE)
AS
(
  SELECT
    sch_job.scheduled_job_id scheduled_job_id,
    sch_job.scheduled_job_name scheduled_job_name,
    sch_job.dbms_job_name dbms_job_name,
    sch_job.job_id job_id,
    sch_job.job_name job_name,
    args.arg_position arg_position,
    args.arg_name arg_name,
    args.arg_data_type arg_data_type,
    args.arg_value arg_value
  FROM
    xdv_sch_sch_job_v sch_job
    LEFT JOIN xdv_sch_job_args_t args ON
      sch_job.scheduled_job_id = args.sjb_id
)
/
