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
