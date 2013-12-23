create table emp (
    id      number,
    deptno  number,
    sal     number
);


/*
update emp a
set sal = (select avg(caret)
           from emp b
           where
               a.deptno = b.deptno)
where sal <
                (select avg(sal) from emp c
                 where a.deptno = c.deptno);
*/

update emp a
set sal = (select avg(<caret>)
           from emp b
           where
               a.deptno = b.deptno)
where sal <
                (select avg(sal) from emp c
                 where a.deptno = c.deptno);