create table parent (
    id number,
    text varchar2(23)
);

create table child (
    parent_id number,
    text varchar2(23)
);

select id, <caret>
parent_id
from parent full join child on id = parent_id