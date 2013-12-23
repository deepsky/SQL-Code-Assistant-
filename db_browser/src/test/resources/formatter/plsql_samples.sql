--Example 5-51 Updating a Row Using a Record

DECLARE
    dept_info       departments%ROWTYPE;
BEGIN
    -- department_id, department_name, and location_id are the table columns
    -- The record picks up these names from the %ROWTYPE.
    dept_info.department_id   := 300;
    dept_info.department_name := 'Personnel';
    dept_info.location_id     := 1700;
    -- The fields of a %ROWTYPE can completely replace the table columns
    -- The row will have values for the filled-in columns, and null
    -- for any other columns
    UPDATE departments SET ROW = dept_info WHERE department_id = 300;
END;
/


DECLARE
    TYPE EmpRec IS RECORD ( last_name employees.last_name%TYPE,
                            salary    employees.salary%TYPE );
    emp_info        EmpRec;
    emp_id          NUMBER := 100;
BEGIN
    UPDATE employees SET salary = salary * 1.1 WHERE employee_id = emp_id
    RETURNING last_name, salary INTO emp_info;
    DBMS_OUTPUT.PUT_LINE('Just gave a raise to ' || emp_info.last_name ||
        ', who now makes ' || emp_info.salary);
    ROLLBACK;
END;
/


DECLARE
    TYPE EmployeeSet        IS TABLE OF employees%ROWTYPE;
    underpaid       EmployeeSet; -- Holds set of rows from EMPLOYEES table.
    CURSOR c1 IS SELECT first_name, last_name
                 FROM employees;
    TYPE NameSet    IS TABLE OF c1%ROWTYPE;
    some_names      NameSet; -- Holds set of partial rows from EMPLOYEES table.
BEGIN
    -- With one query, we bring all the relevant data into the collection of records.
    SELECT * BULK COLLECT INTO underpaid
    FROM employees
    WHERE salary < 5000
    ORDER BY salary DESC;
    -- Now we can process the data by examining the collection, or passing it to
    -- a separate procedure, instead of writing a loop to FETCH each row.
    DBMS_OUTPUT.PUT_LINE(underpaid.COUNT || ' people make less than 5000.');
    FOR i IN underpaid.FIRST .. underpaid.LAST
    LOOP
        DBMS_OUTPUT.PUT_LINE(underpaid(i).last_name || ' makes ' ||
            underpaid(i).salary);
    END LOOP;
    -- We can also bring in just some of the table columns.
    -- Here we get the first and last names of 10 arbitrary employees.
    SELECT first_name, last_name BULK COLLECT INTO some_names
    FROM employees
    WHERE ROWNUM < 11;
    FOR i IN some_names.FIRST .. some_names.LAST
    LOOP
        DBMS_OUTPUT.PUT_LINE('Employee = ' || some_names(i).first_name || ' ' || some_names(i).last_name);
    END LOOP;
END;
/


CREATE TABLE employees_temp
AS SELECT *
   FROM employees;

DECLARE
    CURSOR c1 IS SELECT employee_id, salary
                 FROM employees_temp
                 WHERE salary > 2000 AND ROWNUM <= 10; -- 10 arbitrary rows
    CURSOR c2 IS SELECT *
                 FROM
                     (SELECT employee_id, salary
                      FROM employees_temp
                      WHERE salary > 2000
                      ORDER BY salary DESC)
                 WHERE ROWNUM < 5; -- first 5 rows, in sorted order
BEGIN
    -- Each row gets assigned a different number
    UPDATE employees_temp SET employee_id = ROWNUM;
END;
/


CREATE TABLE dept_temp
AS SELECT *
   FROM departments;

DECLARE
    dept_no NUMBER(4) := 270;
BEGIN
    DELETE FROM dept_temp WHERE department_id = dept_no;
    IF SQL%FOUND THEN -- delete succeeded
        INSERT INTO dept_temp VALUES (270, 'Personnel', 200, 1700);
    END IF;
END;
/


DECLARE
    my_emp_id       NUMBER(6); -- variable for employee_id
    my_job_id       VARCHAR2(10); -- variable for job_id
    my_sal          NUMBER(8, 2); -- variable for salary
    CURSOR c1 IS SELECT employee_id, job_id, salary
                 FROM employees
                 WHERE salary > 2000;
    my_dept departments%ROWTYPE; -- variable for departments row
CURSOR c2 RETURN departments%ROWTYPE IS
SELECT * FROM departments WHERE department_id = 110;
begin
    NULL;
end;
/


DECLARE
    CURSOR c1 IS SELECT
                     employee_id,
                     last_name,
                     job_id,
                     salary
                 FROM employees
                 WHERE salary > 2000;
BEGIN
    OPEN C1;
end;
/


DECLARE
    v_jobid         employees.job_id%TYPE; -- variable for job_id
    v_lastname      employees.last_name%TYPE; -- variable for last_name
    CURSOR c1 IS SELECT last_name, job_id
                 FROM employees
                 WHERE REGEXP_LIKE(job_id, 'S[HT]_CLERK');
    v_employees     employees%ROWTYPE; -- record variable for row
    CURSOR c2 is SELECT *
                 FROM employees
                 WHERE REGEXP_LIKE(job_id, '[ACADFIMKSA]_M[ANGR]');
BEGIN
    OPEN c1; -- open the cursor before fetching
    LOOP
        FETCH c1 INTO v_lastname, v_jobid; -- fetches 2 columns into variables
        EXIT WHEN c1%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(RPAD(v_lastname, 25, ' ') || v_jobid);
    END LOOP;
    CLOSE c1;
    DBMS_OUTPUT.PUT_LINE('-------------------------------------');
    OPEN c2;
    LOOP
        FETCH c2 INTO v_employees; -- fetches entire row into the v_employees record
        EXIT WHEN c2%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(RPAD(v_employees.last_name, 25, ' ') ||
            v_employees.job_id);
    END LOOP;
    CLOSE c2;
END;
/


DECLARE
    my_sal  employees.salary%TYPE;
    my_job  employees.job_id%TYPE;
    factor  INTEGER := 2;
    CURSOR c1 IS
        SELECT factor * salary
        FROM employees
        WHERE job_id = my_job;
BEGIN
    OPEN c1; -- factor initially equals 2
    LOOP
        FETCH c1 INTO my_sal;
        EXIT WHEN c1%NOTFOUND;
        factor := factor + 1; -- does not affect FETCH
    END LOOP;
    CLOSe c1;
END;
/

DECLARE
    CURSOR c1 IS SELECT last_name
                 FROM employees
                 ORDER BY last_name;
    name1   employees.last_name%TYPE;
    name2   employees.last_name%TYPE;
    name3   employees.last_name%TYPE;
BEGIN
    OPEN c1;
    FETCH c1 INTO name1; -- this fetches first row
    FETCH c1 INTO name2; -- this fetches second row
    FETCH c1 INTO name3; -- this fetches third row
    CLOSE c1;
END; /


DECLARE
    TYPE IdsTab     IS TABLE OF employees.employee_id%TYPE;
    TYPE NameTab    IS TABLE OF employees.last_name%TYPE;
    ids     IdsTab;
    names   NameTab;
    CURSOR c1 IS
        SELECT employee_id, last_name
        FROM employees
        WHERE job_id = 'ST_CLERK';
BEGIN
    OPEN c1;
    FETCH c1 BULK COLLECT INTO ids, names;
    CLOsE c1;
    -- Here is where you process the elements in the collections
    FOR i IN ids.FIRST .. ids.LAST
    LOOP
        IF ids(i) > 140 THEN
            DBMS_OUTPUT.PUT_LINE(ids(i));
        END IF;
    END LOOP;
    FOR i IN names.FIRST .. names.LAST
    LOOP
        IF names(i) LIKE '%Ma%' THEN
            DBMS_OUTPUT.PUT_LINE(names(i));
        END IF;
    END LOOP;
END;
/


DECLARE
    CURSOR c1 IS SELECT last_name, salary
                 FROM
                     employees
                 WHERE ROWNUM < 11;
    my_ename        employees.last_name%TYPE;
    my_salary       employees.salary%TYPE;
BEGIN
    OPEN c1;
    LOOP
        FETCH c1 INTO my_ename, my_salary;
        IF c1%FOUND THEN -- fetch succeeded
            DBMS_OUTPUT.PUT_LINE('Name = ' || my_ename || ', salary = ' || my_salary);
        ELSE -- fetch failed, so exit loop
            EXIT; END IF;
    END LOOP;
END; /


CREATE OR REPLACE PROCEDURE raise_emp_salary (column_value NUMBER,
                                              emp_column   VARCHAR2, amount NUMBER)
IS
    v_column        VARCHAR2(30);
    sql_stmt        VARCHAR2(200);
BEGIN
    -- determine if a valid column name has been given as input
    SELECT COLUMN_NAME INTO v_column
    FROM USER_TAB_COLS
    WHERE TABLE_NAME = 'EMPLOYEES' AND COLUMN_NAME = emp_column;
    sql_stmt := 'UPDATE employees SET salary = salary + :1 WHERE '
                || v_column || ' = :2';
    EXECUTE IMMEDIATE sql_stmt USING amount, column_value;
    IF SQL%ROWCOUNT > 0 THEN
        DBMS_OUTPUT.PUT_LINE('Salaries have been updated for: ' || emp_column
            || ' = ' || column_value);
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Invalid Column: ' || emp_column);
END raise_emp_salary;
/

DECLARE
    plsql_block     VARCHAR2(500);
BEGIN
    -- note the semi-colons (;) inside the quotes '...'
    plsql_block := 'BEGIN raise_emp_salary(:cvalue, :cname, :amt); END;';
    EXECUTE IMMEDIATE plsql_block USING 110, 'DEPARTMENT_ID', 10;
    EXECUTE IMMEDIATE 'BEGIN raise_emp_salary(:cvalue, :cname, :amt); END;'
        USING 112, 'EMPLOYEE_ID', 10;
END;
/

DECLARE
    sql_stmt        VARCHAR2(200);
    v_column        VARCHAR2(30) := 'DEPARTMENT_ID';
    dept_id         NUMBER(4)    := 46;
    dept_name       VARCHAR2(30) := 'Special Projects';
    mgr_id          NUMBER(6)    := 200;
    loc_id          NUMBER(4)    := 1700;
BEGIN
    -- note that there is no semi-colon (;) inside the quotes '...'
    EXECUTE IMMEDIATE 'CREATE TABLE bonus (id NUMBER, amt NUMBER)';
    sql_stmt := 'INSERT INTO departments VALUES (:1, :2, :3, :4)';
    EXECUTE IMMEDIATE sql_stmt USING dept_id, dept_name, mgr_id, loc_id;
    EXECUTE IMMEDIATE 'DELETE FROM departments WHERE ' || v_column || ' = :num'
        USING dept_id;
    EXECUTE IMMEDIATE 'ALTER SESSION SET SQL_TRACE TRUE';
    EXECUTE IMMEDIATE 'DROP TABLE bonus';
END;
/


CREATE TABLE employees_temp
AS SELECT *
   FROM employees;

CREATE OR REPLACE PROCEDURE delete_rows (
    table_name IN VARCHAR2,
    condition  IN VARCHAR2 DEFAULT NULL)
AS
    where_clause    VARCHAR2(100) := ' WHERE ' || condition;
    v_table         VARCHAR2(30);
BEGIN
    -- first make sure that the table actually exists; if not, raise an exception
    SELECT OBJECT_NAME INTO v_table
    FROM USER_OBJECTS
    WHERE OBJECT_NAME = UPPER(table_name) AND OBJECT_TYPE = 'TABLE';
    IF condition IS NULL THEN
        where_clause := NULL; END IF;
    EXECUTE IMMEDIATE 'DELETE FROM ' || v_table || where_clause;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Invalid table: ' || table_name);
END;
/

BEGIN
    delete_rows('employees_temp', 'employee_id = 111');
END;
/


CREATE PROCEDURE fire_employee (emp_id NUMBER)
AS
BEGIN
    EXECUTE IMMEDIATE
        'DELETE FROM employees WHERE employee_id = ' || TO_CHAR(emp_id);
END;
/

/*
    You can improve performance by using a bind variable, which allows Oracle to reuse
    the same cursor for different values of emp_id:
*/

CREATE PROCEDURE fire_employee (emp_id NUMBER)
AS
BEGIN
    EXECUTE IMMEDIATE
        'DELETE FROM employees WHERE employee_id = :id' USING emp_id;
END;
/


CREATE PROCEDURE calc_stats (w NUMBER, x NUMBER, y NUMBER, z NUMBER)
IS
BEGIN
    DBMS_OUTPUT.PUT_LINE(w + x + y + z);
END;
/

DECLARE
    a               NUMBER := 4;
    b               NUMBER := 7;
    plsql_block     VARCHAR2(100);
BEGIN
    plsql_block := 'BEGIN calc_stats(:x, :x, :y, :x); END;';
    EXECUTE IMMEDIATE plsql_block USING a, b;
END;
/


DECLARE
    TYPE cursor_ref IS REF CURSOR;
    c1      cursor_ref;
    TYPE emp_tab    IS TABLE OF employees%ROWTYPE;
    rec_tab         emp_tab;
    rows_fetched    NUMBER;
BEGIN
    OPEN c1 FOR 'SELECT * FROM employees';
    FETCH c1 BULK COLLECT INTO rec_tab;
    rows_fetched := c1%ROWCOUNT;
    DBMS_OUTPUT.PUT_LINE('Number of employees fetched: ' || TO_CHAR(rows_fetched));
END;
/


DECLARE
    TYPE EmpCurTyp IS REF CURSOR;
    emp_cv          EmpCurTyp;
    emp_rec         employees%ROWTYPE;
    sql_stmt        VARCHAR2(200);
    v_job           VARCHAR2(10) := 'ST_CLERK';
BEGIN
    sql_stmt := 'SELECT * FROM employees WHERE job_id = :j';
    OPEN emp_cv FOR sql_stmt USING v_job;
    LOOP
        FETCH emp_cv INTO emp_rec;
        EXIT WHEN emp_cv%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Name: ' || emp_rec.last_name || ' Job Id: ' ||
            emp_rec.job_id);
    END LOOP;
    CLOSE emp_cv;
END;
/


CREATE TABLE employees_temp
AS SELECT employee_id, first_name, last_name
   FROM employees;

CREATE TABLE employees_temp2
AS SELECT employee_id, first_name, last_name
   FROM employees;

DECLARE
    seq_value       NUMBER;
BEGIN
    -- Display initial value of NEXTVAL
    -- This is invalid: seq_value := employees_seq.NEXTVAL;
    SELECT employees_seq.NEXTVAL INTO seq_value
    FROM dual;
    DBMS_OUTPUT.PUT_LINE('Initial sequence value: ' || TO_CHAR(seq_value));
    -- The NEXTVAL value is the same no matter what table you select from
    -- You usually use NEXTVAL to create unique numbers when inserting data.
    INSERT INTO employees_temp VALUES (employees_seq.NEXTVAL, 'Lynette', 'Smith');
    -- If you need to store the same value somewhere else, you use CURRVAL
    INSERT INTO employees_temp2 VALUES (employees_seq.CURRVAL, 'Morgan', 'Smith');
    -- Because NEXTVAL values might be referenced by different users and
    -- applications, and some NEXTVAL values might not be stored in the
    -- database, there might be gaps in the sequence
    -- The following uses the stored value of the CURRVAL in seq_value to specify
    -- the record to delete because CURRVAL (or NEXTVAL) cannot used in a WHERE clause
    -- This is invalid: WHERE employee_id = employees_seq.CURRVAL;
    SELECT employees_seq.CURRVAL INTO seq_value
    FROM dual;
    DELETE FROM employees_temp2 WHERE employee_id = seq_value;
    -- The following udpates the employee_id with NEXTVAL for the specified record
    UPDATE employees_temp SET employee_id = employees_seq.NEXTVAL
    WHERE first_name = 'Lynette' AND last_name = 'Smith';
    -- Display end value of CURRVAL
    SELECT employees_seq.CURRVAL INTO seq_value
    FROM dual;
    DBMS_OUTPUT.PUT_LINE('Ending sequence value: ' || TO_CHAR(seq_value));
END;
/


DECLARE
    my_emp_id       NUMBER(6);
    my_job_id       VARCHAR2(10);
    my_sal          NUMBER(8, 2);
    CURSOR c1 IS SELECT employee_id, job_id, salary
                 FROM employees
                 FOR UPDATE;
BEGIN
    OPEN c1;
    LOOP
        FETCH c1 INTO my_emp_id, my_job_id, my_sal;
        IF my_job_id = 'SA_REP' THEN
            UPDATE employees SET salary = salary * 1.02 WHERE CURRENT OF c1;
        END IF;
        EXIT WHEN c1%NOTFOUND;
    END LOOP;
END;
/


DECLARE
    CURSOR c1 IS SELECT last_name, job_id, rowid FROM employees;
    my_lastname     employees.last_name%TYPE;
    my_jobid        employees.job_id%TYPE;
    my_rowid        UROWID;
BEGIN
    OPEN c1;
    LOOP
        FETCH c1 INTO my_lastname, my_jobid, my_rowid;
        EXIT WHEN c1%NOTFOUND;
        UPDATE employees SET salary = salary * 1.02 WHERE rowid = my_rowid;
        -- this mimics WHERE CURRENT OF c1
        COMMIT;
    END LOOP;
    CLOSE c1;
END;
/


CREATE OR REPLACE PACKAGE emp_actions
AS
    -- package specification
    FUNCTION raise_salary (emp_id NUMBER, sal_raise NUMBER) RETURN NUMBER;
END emp_actions;
/

CREATE OR REPLACE PACKAGE BODY emp_actions
AS
    -- package body
    -- code for function raise_salary
    FUNCTION raise_salary (emp_id NUMBER, sal_raise NUMBER) RETURN NUMBER
    IS
        PRAGMA AUTONOMOUS_TRANSACTION;
        new_sal NUMBER(8, 2);
    BEGIN
        UPDATE employees SET salary = salary + sal_raise WHERE employee_id = emp_id;
        COMMIT;
        SELECT salary INTO new_sal
        FROM employees
        WHERE employee_id = emp_id;
        RETURN new_sal;
    END raise_salary;
END emp_actions;
/


CREATE TABLE emp_audit (
    emp_audit_id    NUMBER(6),
    up_date         DATE,
    new_sal         NUMBER(8, 2),
    old_sal         NUMBER(8, 2)
);

CREATE OR REPLACE TRIGGER audit_sal
AFTER UPDATE OF salary ON employees FOR EACH ROW
DECLARE
    PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
    -- bind variables are used here for values
    INSERT INTO emp_audit VALUES (:old.employee_id, SYSDATE,
    :new.salary, :old.salary);
    COMMIT;
END;
/

CREATE TABLE emp_audit (
    emp_audit_id    NUMBER(6),
    up_date         DATE,
    new_sal         NUMBER(8, 2),
    old_sal         NUMBER(8, 2)
);

-- create an autonomous trigger that inserts into the audit table before
-- each update of salary in the employees table
CREATE OR REPLACE TRIGGER audit_sal
BEFORE UPDATE OF salary ON employees FOR EACH ROW
DECLARE
    PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
    INSERT INTO emp_audit VALUES (:old.employee_id, SYSDATE,
    :new.salary, :old.salary);
    COMMIT;
END;
/
-- update the salary of an employee, and then commit the insert
UPDATE employees SET salary = salary * 1.05 WHERE employee_id = 115;

COMMIT;

-- update another salary, then roll back the update
UPDATE employees SET salary = salary * 1.05 WHERE employee_id = 116;

ROLLBACK;

-- show that both committed and rolled-back updates add rows to audit table
SELECT *
FROM emp_audit
WHERE emp_audit_id = 115 OR emp_audit_id = 116;

--Unlike regular triggers, autonomous triggers can execute DDL statements
--using native dynamic SQL, discussed in Chapter 7, "Performing SQL Operations
--with Native Dynamic SQL". In the following example, trigger drop_temp_table drops a temporary
--database table after a row is inserted in table emp_audit.

CREATE TABLE emp_audit (
    emp_audit_id    NUMBER(6),
    up_date         DATE,
    new_sal         NUMBER(8, 2),
    old_sal         NUMBER(8, 2)
);

CREATE TABLE temp_audit (
    emp_audit_id    NUMBER(6),
    up_date         DATE
);

CREATE OR REPLACE TRIGGER drop_temp_table
AFTER INSERT ON emp_audit
DECLARE
    PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE temp_audit';
    COMMIT;
END;
/


-- create the debug table
CREATE TABLE debug_output (
    msg     VARCHAR2(200)
);

-- create the package spec
CREATE PACKAGE debugging
AS
    FUNCTION log_msg (msg VARCHAR2) RETURN VARCHAR2;
    PRAGMA RESTRICT_REFERENCES ( log_msg, WNDS, RNDS );
END debugging;
/
-- create the package body
CREATE PACKAGE BODY debugging
AS
    FUNCTION log_msg (msg VARCHAR2) RETURN VARCHAR2
    IS
        PRAGMA AUTONOMOUS_TRANSACTION;
    BEGIN
        -- the following insert does not violate the constraint
        -- WNDS because this is an autonomous routine
        INSERT INTO debug_output VALUES (msg);
        COMMIT;
        RETURN msg;
    END;
END debugging;
/
-- call the packaged function from a query
DECLARE
    my_emp_id       NUMBER(6);
    my_last_name    VARCHAR2(25);
    my_count        NUMBER;
BEGIN
    my_emp_id := 120;
    SELECT debugging.log_msg(last_name) INTO my_last_name
    FROM employees
    WHERE employee_id = my_emp_id;
    -- even if you roll back in this scope, the insert into 'debug_output' remains
    -- committed because it is part of an autonomous transaction
    ROLLBACK;
END;
/

