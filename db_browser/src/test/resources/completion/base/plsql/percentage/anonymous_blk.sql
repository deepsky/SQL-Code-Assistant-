DECLARE
    TYPE NumList IS TABLE OF NUMBER;
    depts NumList := NumList(10, 20, 50);
BEGIN
    FORALL j IN depts.FIRST..depts.LAST
    UPDATE emp SET sal = sal * 1.10 WHERE deptno = depts(j);
    IF SQL%<caret>
END;
