create table child (
    id number,
    parent_id number,
    text varchar2(23)
);

create table parent (
    id number,
    text varchar2(23)
);

comment on column parent.<caret>