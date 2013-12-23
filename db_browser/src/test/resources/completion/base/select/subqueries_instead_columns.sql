create table emp (
    id      number,
    deptno  number,
    sal     number
);

create table emp1 (
    id1      number,
    deptno1  number,
    sal1     number
);


select ename,
            (select max(sal) from <caret>) "maxsal",
    sal,
            ((select max(sal) from emp) - sal)
                                       "difference"
from emp;