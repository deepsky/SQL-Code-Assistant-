create view parent_v
as select
    id,
    text1,
    date1
from parent;

select to_char
           (
           date1,
           'YYYY') year1, count(*) cnt from parent_v
group by to_char(date1, 'YYYY')
order by <caret> desc