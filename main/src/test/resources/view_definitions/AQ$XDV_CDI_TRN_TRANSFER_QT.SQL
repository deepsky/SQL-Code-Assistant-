CREATE OR REPLACE VIEW AQ$XDV_CDI_TRN_TRANSFER_QT
(QUEUE, MSG_ID, CORR_ID, MSG_PRIORITY, MSG_STATE, DELAY
, DELAY_TIMESTAMP, EXPIRATION, ENQ_TIME, ENQ_TIMESTAMP, ENQ_USER_ID, ENQ_TXN_ID
, DEQ_TIME, DEQ_TIMESTAMP, DEQ_USER_ID, DEQ_TXN_ID, RETRY_COUNT, EXCEPTION_QUEUE_OWNER
, EXCEPTION_QUEUE, USER_DATA, ORIGINAL_QUEUE_NAME, ORIGINAL_QUEUE_OWNER, EXPIRATION_REASON, SENDER_NAME
, SENDER_ADDRESS, SENDER_PROTOCOL, ORIGINAL_MSGID) AS
SELECT q_name QUEUE, msgid MSG_ID, corrid CORR_ID, priority MSG_PRIORITY, decode(state, 0,   'READY',
                                1,   'WAIT',
                                2,   'PROCESSED',
                                3,   'EXPIRED',
                                10,  'BUFFERED_EXPIRED') MSG_STATE,
    cast(FROM_TZ(delay, '-05:00')
                  at time zone sessiontimezone as date) DELAY, cast(FROM_TZ(delay, '-05:00')
               at time zone sessiontimezone as timestamp) DELAY_TIMESTAMP, expiration, cast(FROM_TZ(enq_time, '-05:00')
                  at time zone sessiontimezone as date) ENQ_TIME, cast(FROM_TZ(enq_time, '-05:00')
                  at time zone sessiontimezone as timestamp)
                  ENQ_TIMESTAMP, enq_uid ENQ_USER_ID, enq_tid ENQ_TXN_ID, cast(FROM_TZ(deq_time, '-05:00')
                  at time zone sessiontimezone as date) DEQ_TIME, cast(FROM_TZ(deq_time, '-05:00')
                  at time zone sessiontimezone as timestamp)
                  DEQ_TIMESTAMP, deq_uid DEQ_USER_ID, deq_tid DEQ_TXN_ID, retry_count,  decode (state, 0, exception_qschema,
                                  1, exception_qschema,
                                  2, exception_qschema,
                                  NULL) EXCEPTION_QUEUE_OWNER,  decode (state, 0, exception_queue,
                                  1, exception_queue,
                                  2, exception_queue,
                                  NULL) EXCEPTION_QUEUE,  user_data,  decode (state, 3,
                     decode (deq_tid, 'INVALID_TRANSACTION', NULL,
                             exception_queue), NULL)
                                ORIGINAL_QUEUE_NAME,  decode (state, 3,
                     decode (deq_tid, 'INVALID_TRANSACTION', NULL,
                             exception_qschema), NULL)
                                ORIGINAL_QUEUE_OWNER,  decode(state, 3,
                     decode(deq_time, NULL,
                       decode(deq_tid, NULL,
                       decode (expiration , NULL , 'MAX_RETRY_EXCEEDED',
                            'TIME_EXPIRATION'),
                              'INVALID_TRANSACTION', NULL,
                              'MAX_RETRY_EXCEEDED'), NULL), NULL)
                             EXPIRATION_REASON , sender_name SENDER_NAME,
                             sender_address SENDER_ADDRESS, sender_protocol SENDER_PROTOCOL,
                             dequeue_msgid ORIGINAL_MSGID
FROM "XDV_CDI_TRN_TRANSFER_QT"
WHERE
    state != 7 AND   state != 9 WITH READ ONLY