create table parent (
    id number,
    text varchar2(23)
);

create table child (
    parent_id number,
    text varchar2(23)
);


create or replace function test1 (a_date date)
return number
is
    l_ret NUMBER := 0;
    l_date TIMESTAMP := SYSTIMESTAMP;
begin
    update child set parent_id = 1 where text = 'abc';
    <caret>
end;