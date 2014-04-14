create table left (
    id number,
    name varchar2(20),
    text1 varchar2(20)
);

create table right (
    id number,
    name varchar2(20),
    text2 varchar2(20)
);




select *
from left l, right r
where
    l.<caret>
    l.name = r.text
    and l.name <> r.text2
