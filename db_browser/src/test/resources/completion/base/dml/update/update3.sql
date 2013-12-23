create table parent (
    id number,
    cnt number,
    text varchar2(23)
);

update parent
set id = 1, cnt = 4, <caret>