DECLARE
    dept_rec departments%<caret>;  -- declare record variable
    TYPE DeptCurTyp IS REF CURSOR RETURN dept_rec%TYPE;
    dept_cv DeptCurTyp;  -- declare cursor variable
BEGIN
    NULL;
END;