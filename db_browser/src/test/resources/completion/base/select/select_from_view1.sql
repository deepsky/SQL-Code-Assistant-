create table parent (
    id number,
    text varchar2(23)
);

create view parent_v
as select * from parent;

select <caret>
from parent_v

