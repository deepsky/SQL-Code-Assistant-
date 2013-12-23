create table parent (
    id number,
    text varchar2(23)
);

create table child (
    parent_id number,
    text varchar2(23)
);

select s1.*
from
    child s1
where
    <caret>
