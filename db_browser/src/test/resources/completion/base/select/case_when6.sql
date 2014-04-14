create table parent (
    id      number,
    text1   varchar2(23)
);

create table child (
    id      number,
    parent_id   number,
    name   varchar2(23)
);

update child set name = 'child1'
where exists
    (select
case when substr(text1) = 4 then 1 else 2 end,
    sum(id), count (*) cnt from parent
group by case when substr(<caret>) = 4 then 1 else 2 end
)