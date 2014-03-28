create table parent (
    id      number,
    text1   varchar2(23)
);

select
case when substr(text1) = 4 then 1 else 2 end,
    sum(id), count (*) cnt from parent
group by case when substr(<caret>) = 4 then 1 else 2 end
order by 1