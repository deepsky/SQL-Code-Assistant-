create table parent (
    id number,
    text varchar2(23)
);

update parent
set id = 1
where
    <caret>