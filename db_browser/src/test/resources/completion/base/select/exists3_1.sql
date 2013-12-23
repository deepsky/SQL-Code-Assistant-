create table parent (
    id number,
    text varchar2(23)
);

create table child (
    parent_id number,
    text varchar2(23)
);

select * from child p
where exists (select * from child where child.parent_id = p.<caret>)