create table parent (
    id number,
    text varchar2(23)
);

create table child (
    parent_id number,
    text varchar2(23)
);

select *
from
    (select * from child) s2
where
    s2.<caret>
