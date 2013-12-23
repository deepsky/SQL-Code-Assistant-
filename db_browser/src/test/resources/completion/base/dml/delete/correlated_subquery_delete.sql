create table emp (
    id      number,
    deptno  number,
    sal     number
);


delete from emp a
where
    a.sal = (select max(sal) from emp b
             where a.<caret> = b.deptno);