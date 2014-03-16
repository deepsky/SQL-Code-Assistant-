create table parent (
    id number,
    text varchar2(23)
);

create table child (
    parent_id number,
    text varchar2(23)
);

select <caret>
from parent p inner join child on p.id = parent_id