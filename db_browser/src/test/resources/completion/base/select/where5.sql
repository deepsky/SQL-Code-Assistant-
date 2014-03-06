create table tab1 (
    id number,
    text varchar2(23)
);

select * from tab1
where abc < 34 or <caret> like '%TEST'