create table parent (
    id number,
    text varchar2(23)
);

create view parent_v
as select * from parent;

create view parent_v2 (id, text)
as select * from parent;


select *
from <caret>

