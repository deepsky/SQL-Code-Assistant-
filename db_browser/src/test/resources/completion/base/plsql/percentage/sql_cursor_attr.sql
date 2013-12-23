create or replace function test1 (my_ename varchar2)
return number
is
    my_empno number   := -1;
begin
    update emp set sal = sal * 1.05 where emp_name = my_ename;
    if sql%<caret> then
        insert into emp values (my_ename, 10000) returning id into my_empno;
    end if;

    return my_empno;
end;