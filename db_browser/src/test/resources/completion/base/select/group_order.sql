create table parent (
    id number,
    text1 varchar2(23),
    date1 date
);

select to_char(date1, 'YYYY'), count(*) cnt from parent
group by to_char(date1, 'YYYY'), text1
order by <caret> desc