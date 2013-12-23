create table parent (
    id number,
    text1 varchar2(23)
);

select id, count(*) from parent
order by <caret>