
---------------------------------------------------------------------------------

--	   This SQL file builds the stored procedure and object types ,LOBS and samples 
--   	   neccessary for running samples mentioned in the OO4O help files

--  Modification history:
--  11-10-98 		Mahadevan L        Created
--  11-11-98 		Mahadevan L        Schema for Address and Person types are added
--  11-11-98 		Mahadevan L        Schema for EnamList Varray and department table are added
--  11-11-98 		Mahadevan L        Schema for Coureses Nested Table  are added
--  11-21-98 		Mahadevan L        Schema for Part LOB Table  are added
--  01-27-98		Luo H		   Schema for oramd_address type are added		

---------------------------------------------------------------------------------


---------------------------------------------------------------------------------
--  	Create procedure for Employee package
---------------------------------------------------------------------------------

create or replace package Employee as
 type NUMARRAY is table of NUMBER  index by BINARY_INTEGER;   --Define EMPNOS array
 type VCHAR2ARRAY is table of VARCHAR2(10)  index by BINARY_INTEGER;   --Define ENAMES array
 cursor c1 is  select *   from emp;
 type empCur is ref cursor return c1%rowtype; 
 procedure GetEmpData(indeptno IN NUMBER,EmpCursor in out empCur );
 PROCEDURE GetEmpNamesInArray (ArraySize IN INTEGER, inEmpnos IN NUMARRAY, outEmpNames OUT VCHAR2ARRAY);
 PROCEDURE GetEmpName (inEmpno IN NUMBER, outEmpName OUT VARCHAR2);
 PROCEDURE GetEmpDate (inEmpno IN NUMBER, outEmpDate OUT DATE);
 FUNCTION GetEmpSal (inEmpno IN NUMBER)  RETURN NUMBER;

end Employee;
/


create or replace package body Employee as

PROCEDURE GetEmpNamesInArray (ArraySize IN INTEGER, inEmpnos IN NUMARRAY, outEmpNames OUT VCHAR2ARRAY) is
--ArrIndex INTEGER;
BEGIN 
FOR I in 1..ArraySize loop   
	SELECT ENAME into outEmpNames(I) from EMP WHERE EMPNO = inEmpNos(I);
END LOOP; 
END;


PROCEDURE GetEmpName (inEmpno IN NUMBER, outEmpName OUT VARCHAR2) is 
BEGIN 
 SELECT ENAME into outEmpName from EMP WHERE EMPNO = inEmpNo;
END;

PROCEDURE GetEmpDate (inEmpno IN NUMBER, outEmpDate OUT DATE) is 
BEGIN 
 SELECT HIREDATE into outEmpDate from EMP WHERE EMPNO = inEmpNo;
END;

FUNCTION GetEmpSal (inEmpno IN NUMBER)
RETURN NUMBER is 
  outEmpsal NUMBER(7,2);
BEGIN 
  SELECT SAL into outEmpsal from EMP WHERE EMPNO = inEmpno;
  RETURN (outEmpsal);
END;

procedure GetEmpData(indeptno IN NUMBER,EmpCursor in out empcur) 
is 
begin 
 
                open EmpCursor for 
                        select *   from emp where deptno= indeptno;

        
end GetEmpData; 

end Employee;
/

---show errors
----------------------------- End of Employee package ------------------------

-----------------------------------------------------------------------------
---  	 	Create Schema for Person,Address types and customer tables
-----------------------------------------------------------------------------

drop type address force; 

create type address as object (
	street VARCHAR2(200),
	city varchar2(20),
	state CHAR(2),
	zip varchar2(10));
/

drop type person  force;
drop table person_tab;

create type person as object ( 
	name varchar2(20),
	age number, 
	addr address);
/



create table  person_tab of person;

insert into person_tab values('nasser',40, address('Wine Blvd','Plesonton', 'CA','94065'));

insert into person_tab values('Maha', 25, address('Continentals way','Belmont','CA', '94002'));

insert into person_tab values('chris',30, address('First Street','San Francisco','CA' ,'94123'));

insert into person_tab values('Gopal',28, address('Ardenwood Blvd','Fremont','CA', '11111'));

insert into person_tab values('Ashish',27, address('Mary Ave','San Jose','CA', '22222'));

drop type customerobj  force;

drop table customers;

create type customerobj as object (
	account number, 
	aperson REF person);
/

create table customers of customerobj;

insert into customers values(10, null);                              
						                              
update customers set aperson = (select ref(p) from person_tab p where p.name = 'nasser') where account = 10;

insert into customers values(20, null);                              
						                              
update customers set aperson = (select ref(p) from person_tab p where p.name = 'Maha') where account = 20;

insert into customers values(30, null);                              
						                              
update customers set aperson = (select ref(p) from person_tab p where p.name = 'chris') where account = 30;

insert into customers values(40, null);                              
						                              
update customers set aperson = (select ref(p) from person_tab p where p.name = 'Gopal') where account = 40;
                              
insert into customers values(50, null);                              
						                              
update customers set aperson = (select ref(p) from person_tab p where p.name = 'Ashish') where account = 50;


--------------------   End of Person,Address types and customer tables --------------------------


-------------------------------------------------------------------------------------------------
----		       Create Schema for EnamList Varray and department table 
-------------------------------------------------------------------------------------------------

DROP TYPE ENAMELIST FORCE;

DROP TABLE department;

CREATE TYPE ENAMELIST  AS VARRAY(20) OF VARCHAR2(30);
/

CREATE TABLE department (
dept_id NUMBER(2),
name VARCHAR2(15),
ENAMES ENAMELIST);


INSERT INTO department
      VALUES(10, 'ACCOUNTING',
      ENAMELIST('KING','CLARK','MILLER') );

INSERT INTO department
      VALUES(20, 'RESEARCH',
      ENAMELIST('JONES','SCOTT','ADAMS','SMITH','FORD') );

INSERT INTO department
      VALUES(30, 'SALES',
      ENAMELIST('BLAKE','MARTIN','ALLEN','TURNER','JAMES') );

commit;

----------------------- End of EnamList Varray and department table  ----------------------------


-------------------------------------------------------------------------------------------------
----		       Create Schema for Courses Nested tables and Division table 
-------------------------------------------------------------------------------------------------

DROP TABLE DIVISION;

DROP TYPE COURSELIST FORCE;

DROP TYPE COURSE FORCE;

CREATE TYPE Course AS OBJECT (
    course_no NUMBER(4), 
    title     VARCHAR2(35),
    credits    NUMBER(1));
/

CREATE TYPE CourseList AS TABLE OF Course;
/

CREATE TABLE division (
    name     VARCHAR2(20),
    director VARCHAR2(20),
    office   VARCHAR2(20),
    courses  CourseList) 
    NESTED TABLE courses STORE AS courses_tab;


INSERT INTO division
      VALUES('Psychology', 'Irene Friedman', 'Fulton Hall 133',
         CourseList(Course(1000, 'General Psychology', 5),
                    Course(2100, 'Experimental Psychology', 4),
                    Course(2200, 'Psychological Tests', 3),
                    Course(2250, 'Behavior Modification', 4),
                    Course(3540, 'Groups and Organizations', 3),
                    Course(3552, 'Human Factors in the Workplace', 4),
                    Course(4210, 'Theories of Learning', 4),
                    Course(4320, 'Cognitive Processes', 4),
                    Course(4410, 'Abnormal Psychology', 4)));
INSERT INTO division
      VALUES('History', 'John Whalen', 'Applegate Hall 142',
         CourseList(Course(1011, 'History of Europe I', 4),
                    Course(1012, 'History of Europe II', 4),
                    Course(1202, 'American History', 5),
                    Course(2130, 'The Renaissance', 3),
                    Course(2132, 'The Reformation', 3),
                    Course(3105, 'History of Ancient Greece', 4),
                    Course(3321, 'Early Japan', 4),
                    Course(3601, 'Latin America Since 1825', 4),
                    Course(3702, 'Medieval Islamic History', 4)));
INSERT INTO division
      VALUES('English', 'Lynn Saunders', 'Breakstone Hall 205',
         CourseList(Course(1002, 'Expository Writing', 3),
                    Course(2020, 'Film and Literature', 4),
                    Course(2418, 'Modern Science Fiction', 3),
                    Course(2810, 'Discursive Writing', 4),
                    Course(3010, 'Modern English Grammar', 3),
                    Course(3720, 'Introduction to Shakespeare', 4),
                    Course(3760, 'Modern Drama', 4),
                    Course(3822, 'The Short Story', 4),
                    Course(3870, 'The American Novel', 5)));

commit;

----------------------- End of  Courses Nested tables and Division table  -----------

---------------------------------------------------------------------------------
--	   create schema for part table for LOB data
---------------------------------------------------------------------------------


drop table part;
 
CREATE TABLE part( 
    part_id NUMBER,
    part_name VARCHAR2(20),
    part_image BLOB,  
    part_desc  CLOB,  
    part_collateral BFILE
);
 
 
insert into part values (1,'ORACLE NETWORK',EMPTY_BLOB(),EMPTY_CLOB(),NULL);
insert into part values (2,'ORACLE SERVER', EMPTY_BLOB(),EMPTY_CLOB(),NULL);
 
--------------	   End of part table

drop type ORAMD_ADDRESS ;

CREATE TYPE ORAMD_ADDRESS AS OBJECT ( no NUMBER,

                                      street VARCHAR(60),

                                      state CHAR(2),

                                      zip CHAR(10),

MEMBER PROCEDURE ChangeStreetName(newstreet IN VARCHAR2)

);
/