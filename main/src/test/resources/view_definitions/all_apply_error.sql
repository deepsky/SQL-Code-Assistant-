create or replace view "MY_SCHEMA"."ALL_APPLY_ERROR"
(APPLY_NAME, QUEUE_NAME, QUEUE_OWNER, LOCAL_TRANSACTION_ID, SOURCE_DATABASE, SOURCE_TRANSACTION_ID
, SOURCE_COMMIT_SCN, MESSAGE_NUMBER, ERROR_NUMBER, ERROR_MESSAGE, RECIPIENT_ID, RECIPIENT_NAME
, MESSAGE_COUNT, ERROR_CREATION_TIME) as
(
select e.apply_name, e.queue_name, e.queue_owner, e.local_transaction_id,
       e.source_database, e.source_transaction_id,
       e.source_commit_scn, e.message_number, e.error_number,
       e.error_message, e.recipient_id, e.recipient_name, e.message_count,
       e.error_creation_time
  from dba_apply_error e, all_users u, all_queues q
 where e.recipient_id = u.user_id
   and q.name = e.queue_name
   and q.owner = e.queue_owner
union all
select e.apply_name, e.queue_name, e.queue_owner, e.local_transaction_id,
       e.source_database, e.source_transaction_id,
       e.source_commit_scn, e.message_number, e.error_number,
       e.error_message, e.recipient_id, e.recipient_name, e.message_count,
       e.error_creation_time
  from dba_apply_error e
 where e.recipient_id NOT IN (select user_id from dba_users))