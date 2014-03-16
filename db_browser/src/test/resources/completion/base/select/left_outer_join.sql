CREATE TABLE suppliers
( supplier_id number(10) not null,
  supplier_name varchar2(50) not null,
  contact_name varchar2(50),
    CONSTRAINT suppliers_pk PRIMARY KEY (supplier_id)
);

CREATE TABLE orders
( supplier_id number(10) not null,
  order_name varchar2(50) not null,
  order_date date
);

CREATE TABLE department
( id number(10) not null,
  deptno varchar2(50) not null
);


CREATE TABLE employee
( id number(5),
  empno number,
  name char(20),
  lastname char(20),
  dept char(10),
  age number(2),
  salary number(10),
  location char(10),
  mgrno number,
  workdept number
);

SELECT E.EMPNO, E.LASTNAME, M.EMPNO, M.LASTNAME
FROM EMPLOYEE E LEFT OUTER JOIN
DEPARTMENT INNER JOIN EMPLOYEE M
ON MGRNO = M.EMPNO
AND E.WORKDEPT = DEPTNO AND DEPARTMENT.<caret>