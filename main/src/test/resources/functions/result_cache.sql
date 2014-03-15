CREATE OR REPLACE FUNCTION get_test_value
RETURN NUMBER
RESULT_CACHE
AS
BEGIN
    dbms_output.put_line( 'Called' );
    RETURN 0;
END;