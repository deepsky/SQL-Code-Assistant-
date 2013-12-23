select count(*)
from user_tables ul,
    all_tables a
where ul.table_name = a.table_name



--stdout



SELECT
nbrBookingsPurchased
FROM
(
SELECT
1 AS nbrBookingsPurchased
FROM
booking_events b1
WHERE
b1.event_timestamp BETWEEN ? AND ? AND
b1.airline = ?
)
)

SELECT
rownum AS rnum,
valueBookingsPurchased,
valueBookingsHeld,
nbrBookingsHeld,
nbrBookingsPurchased
FROM
(
SELECT
NVL(sum(CASE WHEN success = 1 AND booknowpaylater=0 THEN default_currency_amount ELSE 0 END),0) AS valueBookingsPurchased,
NVL(sum(CASE WHEN success = 1 AND booknowpaylater=1 THEN default_currency_amount ELSE 0 END),0) AS valueBookingsHeld,
NVL(sum(CASE WHEN success = 1 AND booknowpaylater=1 THEN 1 ELSE 0 END),0) AS nbrBookingsHeld,
NVL(sum(CASE WHEN success = 1 AND booknowpaylater=0 THEN 1 ELSE 0 END),0) AS nbrBookingsPurchased
FROM
booking_events b1
WHERE
b1.event_timestamp BETWEEN ? AND ? AND
b1.airline = ?
)
)



SELECT
rownum AS rnum,
route,
allCount,
successCount,
failedCount
FROM
(
SELECT
depairportcode || '-' || arrairportcode AS route,
count(*) AS allCount,
sum(CASE WHEN success = 1 THEN 1 ELSE 0 END) AS successCount,
sum(CASE WHEN success = 0 THEN 1 ELSE 0 END) AS failedCount
FROM
checkin_archive b1
WHERE
b1.event_timestamp BETWEEN ? AND ? AND
b1.airline = ?

GROUP BY depairportcode || '-' || arrairportcode
)
)



SELECT
rownum AS rnum,
route,
allCount,
successCount,
failedCount
FROM
(
SELECT
depairportcode || '-' || arrairportcode AS route,
count(*) AS allCount,
sum(CASE WHEN success = 1 THEN 1 ELSE 0 END) AS successCount,
sum(CASE WHEN success = 0 THEN 1 ELSE 0 END) AS failedCount
FROM
checkin_archive b1
WHERE
b1.event_timestamp BETWEEN ? AND ? AND
b1.airline = ?

GROUP BY depairportcode || '-' || arrairportcode
)
)

SELECT
rownum AS rnum,
route,
successCount
FROM
(
SELECT
origin || '-' || destination AS route,
count(*) AS successCount
FROM
booking_events b1
WHERE
b1.success = ? AND
b1.event_timestamp BETWEEN ? AND ? AND
b1.airline = ?

GROUP BY origin || '-' || destination
)
)

SELECT
rownum AS rnum,
route,
successCount
FROM
(
SELECT
origin || '-' || destination AS route,
count(*) AS successCount
FROM
booking_events b1
WHERE
b1.success = ? AND
b1.event_timestamp BETWEEN ? AND ? AND
b1.airline = ?

GROUP BY origin || '-' || destination
)
)

























































