create table parent (
    id number,
    text varchar2(23)
);

create view parent_v
as select * from parent;

create view parent_v2 (id, text)
as select * from parent;


select *
from (
select id as id1, id, text1 as text2
from <caret>
) sub
