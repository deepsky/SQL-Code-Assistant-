create table tab1 (
    id number,
    text varchar2(23)
);


select *
from tab1
where event_d between to_timestamp('0214', 'MMDD') and
        /*
        Just a multiline comment
        */
    sys<caret>

