create table parent (
    id      number,
    text1    varchar2(23)
);

create table child (
    parent_id       number,
    text            varchar2(23)
);

select *
from
    (select *
     from
         (
         select * from child
         )
    ) s2,
    (select *
     from
         (
         select * from parent
         )
    ) s3
where
    <caret>
