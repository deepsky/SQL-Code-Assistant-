select last_ddl_time, generated, data_object_id
from user_objects

/*
create table aa378 (
id NUMBER,
name CHAR(34)
)
*/


create table f56 (
id number,
name VARCHAR2(23),
text NVARCHAR2(33)
);

create table f562 (
id number,
name VARCHAR2(23),
text NVARCHAR2(33)
)

select text, id
from f56


select department_id, e.*, commission_pct
from employees e


create view f56_view as
select * from f56;
