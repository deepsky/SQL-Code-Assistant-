FUNCTION one_employee (employee_id_in
IN employees.employee_id%TYPE)
RETURN employees%ROWTYPE
IS
    l_employee   employees%ROWTYPE;
BEGIN
    SELECT *
    INTO l_employee
    FROM employees
    WHERE employee_id = employee_id_in;

    RETURN l_employee;
EXCEPTION
    WHEN NO_DATA_FOUND
    THEN
    /* Return an empty record. */
        RETURN l_employee;
END one_employee;