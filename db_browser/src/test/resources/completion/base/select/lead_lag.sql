create table emp (
    id number,
    text varchar2(23),
    hiredate timestamp,
    sal number  ,
    dept varchar2(20)

);


SELECT hiredate,
LEAD(sal, 1, 0) OVER (PARTITION BY dept ORDER BY <caret> DESC NULLS LAST) NEXT_LOWER_SAL,
    LAG(sal, 1, 0) OVER (PARTITION BY dept ORDER BY sal DESC NULLS LAST) PREV_HIGHER_SAL
FROM emp
