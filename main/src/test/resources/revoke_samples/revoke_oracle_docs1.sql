-- Revoking a System Privilege from a User
REVOKE DROP ANY TABLE
    FROM hr, oe;

-- Revoking a Role from a User
REVOKE dw_manager
    FROM sh;


-- Revoking a System Privilege from a Role
REVOKE CREATE TABLESPACE
   FROM dw_manager;


-- Revoking a Role from a Role
REVOKE dw_user

  FROM dw_manager;


-- Revoking an Object Privilege from a User:
-- Example You can grant DELETE, INSERT, SELECT, and UPDATE privileges on the table orders to the user hr
-- with the following statement:
GRANT ALL
   ON orders TO hr;

--To revoke the DELETE privilege on orders from hr, issue the following statement:
REVOKE DELETE
   ON orders FROM hr;


-- Revoking Object Privileges from PUBLIC:
-- Example You can grant SELECT and UPDATE privileges on the view emp_details_view to all users by
-- granting the privileges to the role PUBLIC:
GRANT SELECT, UPDATE
    ON emp_details_view TO public;

--The following statement revokes UPDATE privilege on emp_details_view from all users:
REVOKE UPDATE
    ON emp_details_view FROM public;

-- Revoking an Object Privilege on a Sequence from a User: Example You can grant the user oe the SELECT privilege
-- on the departments_seq sequence in the schema hr with the following statement:
GRANT SELECT
    ON hr.departments_seq TO oe;

-- To revoke the SELECT privilege on departments_seq from oe, issue the following statement:
REVOKE SELECT
    ON hr.departments_seq FROM oe;


--Revoking an Object Privilege with CASCADE CONSTRAINTS: Example You can grant to oe the privileges REFERENCES
-- and UPDATE on the employees table in the schema hr with the following statement:
GRANT REFERENCES, UPDATE
    ON hr.employees TO oe;

--The user oe can exercise the REFERENCES privilege to define a constraint
-- in his or her own dependent table that refers to the employees table in the schema hr:
CREATE TABLE dependent
(dependno   NUMBER,
 dependname VARCHAR2(10),
 employee   NUMBER
    CONSTRAINT in_emp REFERENCES hr.employees(employee_id) );

--You can revoke the REFERENCES privilege on hr.employees from oe by issuing
-- the following statement that contains the CASCADE CONSTRAINTS clause:
REVOKE REFERENCES
    ON hr.employees
    FROM oe
    CASCADE CONSTRAINTS;


-- Revoking an Object Privilege on a Directory from a User:
-- Example You can revoke the READ object privilege on directory bfile_dir from hr by issuing the following statement:

REVOKE READ ON DIRECTORY bfile_dir FROM hr;
