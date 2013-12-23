create table parent (
    id number,
    text1 varchar2(23)
);

select id, count(*) cnt from parent
order by 1, <caret>