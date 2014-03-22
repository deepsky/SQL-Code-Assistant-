create table parent (
    id number,
    text varchar2(23)
);

create table child (
    parent_id number,
    reflect_id number,
    text varchar2(23)
);

select * from parent p
where exists (select * from child
              where child.parent_id = p.<caret>
            and child.reflect_id in (select id from child c where c.text = '1'))