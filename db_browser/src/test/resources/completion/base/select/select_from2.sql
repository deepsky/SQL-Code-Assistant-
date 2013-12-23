create table parent (
    id number,
    text1 varchar2(23)
);

select
sub.<caret>
from (
select id as id1, id, text1 as text2
from parent
) sub