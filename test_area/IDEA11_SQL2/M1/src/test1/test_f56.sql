select last_ddl_time, generated, data_object_id
from user_objects


/*
create table aa378 (
id NUMBER,
id89 NUMBER,

name CHAR(34)
)

create table f56 (
id number,
name VARCHAR2(23),
text NVARCHAR2(33),
content varchar2(8)
);
*/




create table f562 (
    id          number,
    name        VARCHAR2(23),
    text        NVARCHAR2(33),
    INT_D2S_COL INTERVAL DAY(2) TO SECOND(6),
    INT_Y2M_COL INTERVAL YEAR(2) TO MONTH,
    TS_COL      TIMESTAMP(6),
    TSWTZ_COL   TIMESTAMP(6) WITH TIME ZONE,
    TSWLTZ_COL  TIMESTAMP(6) WITH LOCAL TIME ZONE
)

select text, id,
substr (content, id)
from f56



select department_id, e.*, commission_pct
from employees e


create view f56_view as
select * from f56;
