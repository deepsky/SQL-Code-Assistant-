DECLARE
dept_rec departments%rowtype;  -- declare record variable
TYPE DeptCurTyp IS REF CURSOR RETURN dept_rec%<caret>;
    dept_cv DeptCurTyp;  -- declare cursor variable
BEGIN
    NULL;
END;