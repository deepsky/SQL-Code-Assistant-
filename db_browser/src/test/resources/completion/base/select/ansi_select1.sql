create table parent (
    id number,
    text varchar2(23)
);

create table child (
    parent_id number,
    text varchar2(23)
);

select *
from parent left join child on <caret>