-- Definition is not complete and should be used for referencing only
create or replace view XSL_DVR_PARAM_V
(ID, OBJ_ID, NAME, USER_ID, STATUS, USAGE
, JOB_ID, END_TIME, TIME_ZONE_ID, SEARCH_TEMPLATE_ID, CREATE_DATE, FREEZE_DATE
, CREATED_BY, UPDATED_BY, LAST_UPDATE_DATE, LAST_UPDATED_BY, DESCRIPTION, XML_PAYLOAD
) as
SELECT id,
       obj_id,
       name,
       user_id,
       status,
       usage,
       job_id,
       end_time,
       time_zone_id,
       (select dvSearchTemplate
          from XMLTable(
              'template/panel'
              PASSING (xml_payload)
              COLUMNS dvSearchTemplate VARCHAR2(32) PATH '@dvSearchTemplate')
        ) search_template_id,
       create_date,
       freeze_date,
       created_by,
       updated_by,
       Decode(freeze_date, NULL, Lag(freeze_date,1,NULL) over (PARTITION BY obj_id ORDER BY freeze_date ASC nulls last), freeze_date) last_update_date,
       Decode(updated_by, NULL, Lag(updated_by,1,NULL) over (PARTITION BY obj_id ORDER BY freeze_date ASC nulls last), updated_by) last_updated_by,
       description,
       xml_payload
  FROM xsl_dvr_param_t
 ORDER BY obj_id, freeze_date ASC NULLS LAST