create table child (
    parent_id number,
    text varchar2(23)
);

create table emp (
    id      number,
    deptno  number,
    sal     number
);

insert into emp (id, deptno, <caret>)