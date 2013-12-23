CREATE TABLE "HR"."EMPLOYEES"
(	"EMPLOYEE_ID" NUMBER(6,0),
     "FIRST_NAME" VARCHAR2(20),
     "LAST_NAME" VARCHAR2(25) CONSTRAINT "EMP_LAST_NAME_NN" NOT NULL ENABLE,
     "EMAIL" VARCHAR2(25) CONSTRAINT "EMP_EMAIL_NN" NOT NULL ENABLE,
     "PHONE_NUMBER" VARCHAR2(20),
     "HIRE_DATE" DATE CONSTRAINT "EMP_HIRE_DATE_NN" NOT NULL ENABLE,
     "JOB_ID" VARCHAR2(10) CONSTRAINT "EMP_JOB_NN" NOT NULL ENABLE,
     "SALARY" NUMBER(8,2),
     "COMMISSION_PCT" NUMBER(2,2),
     "MANAGER_ID" NUMBER(6,0),
     "DEPARTMENT_ID" NUMBER(4,0),
    CONSTRAINT "EMP_SALARY_MIN" CHECK (salary > 0) --ENABLE
);


CREATE TABLE employees_temp
AS SELECT employee_id, first_name, last_name
   FROM employees;

CREATE TABLE emp_audit (
    emp_audit_id    NUMBER(6),
    up_date         DATE,
    new_sal         NUMBER(8, 2),
    old_sal         NUMBER(8, 2)
);


create table parent (
    id      number,
    text    varchar2(20),
    content varchar2(40)
);

create table child (
    parent_id       number,
    attributes      varchar2(40),
    constraint xdv_aai_15m_trc_fk
        foreign key (parent_id)
        references parent (id, <caret>)
);