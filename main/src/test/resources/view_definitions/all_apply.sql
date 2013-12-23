create or replace view "MY_SCHEMA".ALL_APPLY
(APPLY_NAME, QUEUE_NAME, QUEUE_OWNER, APPLY_CAPTURED, RULE_SET_NAME, RULE_SET_OWNER
, APPLY_USER, APPLY_DATABASE_LINK, APPLY_TAG, DDL_HANDLER, PRECOMMIT_HANDLER, MESSAGE_HANDLER
, STATUS, MAX_APPLIED_MESSAGE_NUMBER, NEGATIVE_RULE_SET_NAME, NEGATIVE_RULE_SET_OWNER, STATUS_CHANGE_TIME, ERROR_NUMBER
, ERROR_MESSAGE, MESSAGE_DELIVERY_MODE) as
select a."APPLY_NAME",a."QUEUE_NAME",a."QUEUE_OWNER",a."APPLY_CAPTURED",a."RULE_SET_NAME",a."RULE_SET_OWNER",a."APPLY_USER",a."APPLY_DATABASE_LINK",a."APPLY_TAG",a."DDL_HANDLER",a."PRECOMMIT_HANDLER",a."MESSAGE_HANDLER",a."STATUS",a."MAX_APPLIED_MESSAGE_NUMBER",a."NEGATIVE_RULE_SET_NAME",a."NEGATIVE_RULE_SET_OWNER",a."STATUS_CHANGE_TIME",a."ERROR_NUMBER",a."ERROR_MESSAGE",a."MESSAGE_DELIVERY_MODE"
  from dba_apply a, all_queues q
 where a.queue_name = q.name
   and a.queue_owner = q.owner
   and ((a.rule_set_owner is null and a.rule_set_name is null) or
        ((a.rule_set_owner, a.rule_set_name) in
          (select r.rule_set_owner, r.rule_set_name
             from all_rule_sets r)))
   and ((a.negative_rule_set_owner is null and
         a.negative_rule_set_name is null) or
        ((a.negative_rule_set_owner, a.negative_rule_set_name) in
          (select r.rule_set_owner, r.rule_set_name
             from all_rule_sets r)))
