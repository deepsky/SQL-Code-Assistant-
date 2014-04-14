create table tab1 (
    id number,
    text varchar2(23)
);


select b.bookref, b.flight, b.depairport, b.arrairport, b.depdate_text, b.given_name, b.surname, t.ticket
from tab1  b, airdemand t
where
    b.bookref = t.bookref
    and b.given_name = t.given_name
    and b.surname = t.surname
order by  b.bookref
    --order by server, event_text desc
/*
Just amultiline comment
*/
<caret>