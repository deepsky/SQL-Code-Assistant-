create table parent (
    id number,
    text1 varchar2(23),
    date1 date
);

select to_char
           (
           date1,
    'YYYY') year1, count(*) cnt from parent
group by to_char(date1, 'YYYY')
order by <caret> desc