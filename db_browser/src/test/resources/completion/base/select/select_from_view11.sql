create table parent (
    id number,
    text varchar2(23)
);

create view parent_v (id, text)
as select * from parent;

select <caret>
from parent_v

