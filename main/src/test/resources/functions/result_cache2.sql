CREATE OR REPLACE FUNCTION get_test_value
RETURN NUMBER
RESULT_CACHE RELIES_ON (employees)
AS
BEGIN
    dbms_output.put_line( 'Called' );
    RETURN 0;
END;
/

FUNCTION one_employee (employee_id_in
IN employees.employee_id%TYPE)
RETURN employees%ROWTYPE
RESULT_CACHE RELIES_ON (employees)
IS
l_employee   employees%ROWTYPE;
BEGIN
    RETURN -1;
END;
/