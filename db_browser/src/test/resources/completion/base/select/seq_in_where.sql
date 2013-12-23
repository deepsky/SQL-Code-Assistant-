create sequence te_seq;

create table tab1 (
    id number,
    text varchar2(23)
);

select te_seq.nextval
from tab1 where <caret>