create table parent (
    id      number,
    text    varchar2(23)
);

create table child (
    parent_id       number,
    text1            varchar2(23)
);

select *
from parent p,
    (
    select * from child
    order by <caret>
    )