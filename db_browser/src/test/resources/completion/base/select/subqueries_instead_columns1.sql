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
            (select max(<caret>) from emp1) "maxsal",
    sal,
            ((select max(sal) from emp) - sal)
                                       "difference"
from emp e;

/*
select ename,
            (select max(deptno) from emp1) "maxsal",
    sal,
            ((select max(sal) from emp) - sal)
                                            "difference"
from emp e1;*/
