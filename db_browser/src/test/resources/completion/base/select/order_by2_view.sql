create view parent_v as
select
    id,
    text1
from parent

select id, count(*) cnt from parent_v
order by id, <caret>