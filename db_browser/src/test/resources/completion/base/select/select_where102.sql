create table airports (
    id number,
    name varchar2(20),
    location_code varchar2(20),
    type varchar2(40)
);

create table paymentevents (
    id number,
    bookingreference varchar2(20),
    transactionid varchar2(20),
    eventtype varchar2(40)
);


select substr(bt, 1, 5) as pnr, substr(bt, 6) as tr
from airports,
(
select bookingreference || transactionid as bt
from paymentevents
where <caret>
group by bookingreference || transactionid having sum(eventtype) != 3
)
order by tr desc