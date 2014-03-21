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
from left b <caret>
