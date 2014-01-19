create table tab1 (
    id number,
    text varchar2(23)
);

select * from tab1
where abc < 34 or event_time between sysdate-10 and sysdate -2 and <caret> = 1