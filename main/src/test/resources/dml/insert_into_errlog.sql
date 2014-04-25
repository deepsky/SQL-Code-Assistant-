INSERT INTO bookingevents (
idbookingevent,
airline,
eventdatetime,
apikey,
bookingreference,
clientid,
socialtwitterid,
useragent
)
SELECT
    5000000 + id as ID,
    upper(airline) as AIRLINE,
    event_timestamp as TME,
    CASE WHEN upper(airline) = 'MH' THEN 'be16830ea89ca87be5192ce5ecba6ac1'
        WHEN upper(airline) = 'AI' THEN 'd1f663b1cff7f93709cc762e91a71dac'
        WHEN upper(airline) = 'J2' THEN '8805ae0be6ee24f5f46248517c7e7208'
        ELSE '' END as apikey,
    bookingref,
    0 as numinfants,
    '' as socialtwitterid,
    user_agent
FROM booking_events@smp
WHERE event_timestamp > to_timestamp('201301', 'YYYYMM')
LOG ERRORS INTO BOOKINGEVENTS_ERRLOG ('BOOKINGEVENTS_ERRLOG') REJECT LIMIT UNLIMITED;