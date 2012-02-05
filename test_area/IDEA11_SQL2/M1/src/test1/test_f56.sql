select
    last_ddl_time,
    generated as ggh,
    data_object_id,
    secondary
from user_objects
ORDER BY
    TR.TABLE_NAME
    , TR1.TABLE_NAME
    , FA.CREATE_DATE
          DESC
    ,
    FA.ID


SELECT
    TABLE_NAME,
    REF_TYPE,
    INTERNAL_TABLE_NAME,
    CREATE_SOURCE,
    UPDATE_SOURCE,
    firstval AS MASK_NAME,
    MAX(CREATE_DATE) AS CREATE_DATE,
    MIN_TSPAN
FROM

    (
    SELECT
        TR.TABLE_NAME AS TABLE_NAME,
        TR1.TABLE_NAME AS REF_TYPE,
        TR1.INTERNAL_TABLE_NAME AS INTERNAL_TABLE_NAME,
        MSK.MASK_NAME,
        FA.FMN_ID
        ,
        FA.TDM_ID
        ,
        FA.AMK_ID
        ,
        FA.FCC_ID,
        FA.CREATE_DATE AS CREATE_DATE,
        FA.UPDATE_DATE,
        FA.ID AS ID,
        FA.CREATE_SOURCE AS CREATE_SOURCE,
        FA.UPDATE_SOURCE AS UPDATE_SOURCE,
        GRA.MIN_TSPAN AS MIN_TSPAN
        --first_value(MSK.MASK_NAME) over (partition by TR.TABLE_NAME order by FA.CREATE_DATE DESC ) AS firstval
        --row_number() over (partition by TR.TABLE_NAME order by FA.CREATE_DATE DESC ) seq
    FROM

        XDV_PRD_FLOW_AGGR_MASK_T FA,
        XDV_PRD_TABLE_REF_DEF_T TR,
        XDV_PRD_TABLE_REF_DEF_T TR1,
        XDV_PRD_FLOW_MATRIX_NODE_T FM,
        XDV_PRD_STAGE_REF_DEF_T STG,
        XDV_PRD_GRANULARITY_T GRA,
        XDV_PRD_AGGR_MASK_T MSK
    WHERE
        FA.FMN_ID = FM.ID
        AND FA.TDM_ID = TR1.ID
        AND FM.TRD_ID = TR.ID
        AND FM.STG_ID = STG.ID
        AND FM.GRA_ID = GRA.ID
        AND STG.STAGE_NAME = 'Mediation'
        AND GRA.MIN_TSPAN = 15 AND
        MSK.ID = FA.AMK_ID
        AND MSK.TDM_ID = TR1.ID
        AND TR1.TABLE_NAME = 'Application Type' --'Cause'
        AND TR.TABLE_NAME is not null
        AND FA.OBSOLETE_DATE is null

    GROUP BY
        s1.TABLE_NAME
        , s1.REF_TYPE
        , s1.INTERNAL_TABLE_NAME
    ORDER BY
        TR.TABLE_NAME,
        TR1.TABLE_NAME
        , FA.CREATE_DATE DESC
        , FA.ID
    ) AS s1
GROUP BY
    s1.TABLE_NAME,
    s1.REF_TYPE,
    s1.INTERNAL_TABLE_NAME,
    s1.CREATE_SOURCE,
    s1.UPDATE_SOURCE,
    s1.firstval,
    s1.MIN_TSPAN



select *
from booking_events
where
    bookingref in
        (
        select bookingref --, count(*)
        from booking_events
        where bookingref is not null
        group by bookingref having count (*) > 1
        )
order by
    bookingref,
    event_timestamp desc

SELECT *
FROM suppliers
WHERE not EXISTS
    (select *
    from orders
    where
        suppliers.supplier_id = orders.supplier_id
    );



UPDATE suppliers
SET
    supplier_name = (SELECT
    customers.name
FROM customers
WHERE customers.customer_id = suppliers.supplier_id)
    , aa = ldl
WHERE EXISTS (SELECT
    customers.name
FROM customers
WHERE customers.customer_id = suppliers.supplier_id);


DELETE FROM suppliers
WHERE
    EXISTS
        (select *
        from orders
        where
            suppliers.supplier_id = orders.supplier_id
        );


INSERT INTO suppliers
(supplier_id, supplier_name)
SELECT account_no, name
FROM suppliers
WHERE
    exists (select *
    from orders
    Where suppliers.supplier_id = orders.supplier_id);



UPDATE
    (
    SELECT
        a.COL1
    FROM
        TABLE1 a,
        TABLE2 b
    WHERE
        a.FK = b.PK
        AND b.COL2 IN ('SET OF VALUES')
    ) update_tbl
SET
    update_tbl.COL1 = 'VALUE'



-- Create a table with all the right IDs, but messed-up names
CREATE TABLE employee_temp
AS SELECT employee_id, UPPER(first_name) first_name,
    TRANSLATE(last_name, 'aeiou', '12345') last_name
FROM employees;

BEGIN
    -- Display the first 5 names to show they're messed up
    FOR person IN (SELECT *
    FROM employee_temp
    WHERE ROWNUM < 6)
    LOOP
        DBMS_OUTPUT.PUT_LINE(person.first_name || ' ' || person.last_name);
    END LOOP;
    UPDATE employee_temp SET (first_name, last_name) =
        (SELECT first_name, last_name
        FROM employees
        WHERE employee_id = employee_temp.employee_id);
    DBMS_OUTPUT.PUT_LINE('*** Updated ' || SQL % ROWCOUNT || ' rows. ***');
    -- Display the first 5 names to show they've been fixed up
    FOR person IN (SELECT *
    FROM employee_temp
    WHERE ROWNUM < 6)
    LOOP
        DBMS_OUTPUT.PUT_LINE(person.first_name || ' ' || person.last_name);
    END LOOP;
END;
/


DECLARE
    my_emp_id       NUMBER(6);
    my_job_id       VARCHAR2(10);
    my_sal          NUMBER(8, 2); CURSOR c1 IS SELECT employee_id, job_id, salary
FROM employees
FOR UPDATE;
BEGIN
    OPEN c1;
    LOOP

        FETCH c1 INTO my_emp_id, my_job_id, my_sal;

        IF my_job_id = 'SA_REP' THEN

            UPDATE employees
            SET salary = salary * 1.02
            WHERE
                CURRENT OF c1;
        END IF;

        EXIT WHEN c1 % NOTFOUND;
    END LOOP;
END;
/


DECLARE

CURSOR c1 IS
SELECT
    last_name, job_id, rowid
FROM employees;
    my_lastname     employees.last_name%TYPE;
    my_jobid        employees.job_id%TYPE;
    my_rowid        UROWID;
BEGIN
    OPEN c1;
    LOOP

        FETCH c1 INTO my_lastname, my_jobid, my_rowid;
        EXIT WHEN c1 % NOTFOUND;
        UPDATE employees SET salary = salary * 1.02
        WHERE rowid = my_rowid;
        -- this mimics WHERE CURRENT OF c1
        COMMIT;
    END LOOP;
    CLOSE c1;
END;
/


create or replace package e
as
end;



CREATE OR REPLACE PACKAGE
emp_actions
AS -- package specification
    FUNCTION raise_salary (emp_id NUMBER, sal_raise NUMBER) RETURN NUMBER;
END emp_actions
    ;
/

CREATE OR REPLACE PACKAGE BODY emp_actions
AS -- package body
    -- code for function raise_salary
    FUNCTION raise_salary (
        emp_id    NUMBER,
        sal_raise NUMBER
    ) RETURN NUMBER
    IS
        PRAGMA AUTONOMOUS_TRANSACTION;
        new_sal NUMBER(8, 2);
    BEGIN
        UPDATE employees SET salary = salary + sal_raise WHERE employee_id = emp_id
        ;
        COMMIT;
        SELECT salary INTO new_sal
        FROM employees
        WHERE employee_id = emp_id;

        RETURN new_sal;
    END raise_salary
        ;

END emp_actions;
/


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

/*



create table f562 (

    id              number primary key,
    name            VARCHAR2(23),
    text            NVARCHAR2(33),
    INT_D2S_COL     INTERVAL DAY(2) TO SECOND(6),
    INT_Y2M_COL     INTERVAL YEAR(2) TO MONTH,
    TS_COL          TIMESTAMP(6),
    TSWTZ_COL       TIMESTAMP(6) WITH TIME ZONE,
    TSWLTZ_COL      TIMESTAMP(6)

    WITH LOCAL

    TIME
    ZONE references ddd ( oeoe )

)

select text, id,
    substr(content, id)
from f56



select department_id, e.*, commission_pct
from employees e


create view f56_view as select *
from f56;
*/
