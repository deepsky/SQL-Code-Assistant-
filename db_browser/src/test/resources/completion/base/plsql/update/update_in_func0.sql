create table parent (
    id number,
    text varchar2(23)
);

create table child (
    parent_id number,
    text varchar2(23)
);


create or replace function test1
return number
is
    l_ret NUMBER := 0;
begin
    update <caret>
end;