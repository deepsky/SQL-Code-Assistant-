CREATE TABLE employees_demo
    ( employee_id    NUMBER(6)
    , first_name     VARCHAR2(20)
    , last_name      VARCHAR2(25)
         CONSTRAINT emp_last_name_nn_demo NOT NULL
    , email          VARCHAR2(25)
         CONSTRAINT emp_email_nn_demo     NOT NULL
    , phone_number   VARCHAR2(20)
    , hire_date      DATE  DEFAULT SYSDATE
         CONSTRAINT emp_hire_date_nn_demo  NOT NULL
    , job_id         VARCHAR2(10)
       CONSTRAINT     emp_job_nn_demo  NOT NULL
    , salary         NUMBER(8,2)
       CONSTRAINT     emp_salary_nn_demo  NOT NULL
    , commission_pct NUMBER(2,2)
    , manager_id     NUMBER(6)
    , department_id  NUMBER(4)
    , dn             VARCHAR2(300)
    , CONSTRAINT     emp_salary_min_demo
                     CHECK (salary > 0)
    , CONSTRAINT     emp_email_uk_demo
                     UNIQUE (email)
    ) ;


CREATE TABLE employees_demo
    ( employee_id    NUMBER(6)
    , first_name     VARCHAR2(20)
    , last_name      VARCHAR2(25)
         CONSTRAINT emp_last_name_nn_demo NOT NULL
    , email          VARCHAR2(25)
         CONSTRAINT emp_email_nn_demo     NOT NULL
    , phone_number   VARCHAR2(20)
    , hire_date      DATE  DEFAULT SYSDATE
         CONSTRAINT emp_hire_date_nn_demo  NOT NULL
    , job_id         VARCHAR2(10)
       CONSTRAINT     emp_job_nn_demo  NOT NULL
    , salary         NUMBER(8,2)
       CONSTRAINT     emp_salary_nn_demo  NOT NULL
    , commission_pct NUMBER(2,2)
    , manager_id     NUMBER(6)
    , department_id  NUMBER(4)
    , dn             VARCHAR2(300)
    , CONSTRAINT     emp_salary_min_demo
                     CHECK (salary > 0)
    , CONSTRAINT     emp_email_uk_demo
                     UNIQUE (email)
    )
   TABLESPACE example
   STORAGE (INITIAL     6144
            NEXT        6144
            MINEXTENTS     1
            MAXEXTENTS     5 );

CREATE GLOBAL TEMPORARY TABLE today_sales
   ON COMMIT PRESERVE ROWS
   AS SELECT * FROM orders WHERE order_date = SYSDATE;


CREATE TABLE dept_80
   PARALLEL
   AS SELECT * FROM employees
   WHERE department_id = 80;


CREATE TABLE dept_80
   AS SELECT * FROM employees
   WHERE department_id = 80;


CREATE TABLE departments_demo
    ( department_id    NUMBER(4)
    , department_name  VARCHAR2(30)
           CONSTRAINT  dept_name_nn  NOT NULL
    , manager_id   NUMBER(6)
    , location_id      NUMBER(4)
    , dn               VARCHAR2(300)
    ) ;


CREATE TABLE departments_demo
    ( department_id    NUMBER(4)   PRIMARY KEY DISABLE
    , department_name  VARCHAR2(30)
           CONSTRAINT  dept_name_nn  NOT NULL
    , manager_id       NUMBER(6) default 0 not null
    , location_id      NUMBER(4)
    , dn               VARCHAR2(300)
    ) ;



CREATE TABLE print_media
    ( product_id        NUMBER(6)
    , ad_id             NUMBER(6)
    , ad_composite      BLOB
    , ad_sourcetext     CLOB
    , ad_finaltext      CLOB
    , ad_fltextn        NCLOB
    , ad_textdocs_ntab  textdoc_tab
    , ad_photo          BLOB
    , ad_graphic        BFILE
    , ad_header         adheader_typ
    ) NESTED TABLE ad_textdocs_ntab STORE AS textdocs_nestedtab;



CREATE TABLE print_media_new
(
    product_id              NUMBER(6),
    ad_id                   NUMBER(6),
    ad_composite            BLOB,
    ad_sourcetext           CLOB,
    ad_finaltext            CLOB,
    ad_fltextn              NCLOB,
    ad_textdocs_ntab        textdoc_tab,
    ad_photo                BLOB,
    ad_graphic              BFILE,
    ad_header               adheader_typ,
    press_release           LONG
)
NESTED TABLE ad_textdocs_ntab STORE AS textdocs_nestedtab_new
LOB (
ad_sourcetext, ad_finaltext
) STORE AS (TABLESPACE example
STORAGE (
    INITIAL 6144 NEXT 6144
)
CHUNK 4000
NOCACHE LOGGING
);


CREATE TABLE countries_demo (
    country_id      CHAR(2) CONSTRAINT country_id_nn_demo NOT NULL,
    country_name    VARCHAR2(40),
    currency_name   VARCHAR2(25),
    currency_symbol VARCHAR2(3),
    region          VARCHAR2(15),
    CONSTRAINT country_c_id_pk_demo
        PRIMARY KEY (country_id)
)
ORGANIZATION INDEX
INCLUDING country_name
PCTTHRESHOLD 2
STORAGE
(
    INITIAL 4K
    NEXT 2K
    PCTINCREASE 0
    MINEXTENTS 1
    MAXEXTENTS 1
)
OVERFLOW
STORAGE
(
    INITIAL 4K
    NEXT 2K
    PCTINCREASE 0
    MINEXTENTS 1
    MAXEXTENTS 1
);


CREATE TABLE dept_external (
   deptno     NUMBER(6),
   dname      VARCHAR2(20),
   loc        VARCHAR2(25)
)
ORGANIZATION EXTERNAL
(TYPE oracle_loader
 DEFAULT DIRECTORY admin
 ACCESS PARAMETERS
 (
  RECORDS DELIMITED BY newline
  BADFILE 'ulcase1.bad'
  DISCARDFILE 'ulcase1.dis'
  LOGFILE 'ulcase1.log'
  SKIP 20
  FIELDS TERMINATED BY ","  OPTIONALLY ENCLOSED BY '"'
  (
   deptno     INTEGER EXTERNAL(6),
   dname      CHAR(20),
   loc        CHAR(25)
  )
 )
 LOCATION ('ulcase1.ctl')
)
REJECT LIMIT UNLIMITED;


select dbms_metadata.--add_months(sysdate, 3)
get_ddl( 'table', 'A345') --, 'PLN' )
from dual;



create table "pln"."a345" (
    id    number,
    "text"  varchar2(45) not null enable,
    primary key ("id") using index pctfree 10 initrans 2 maxtrans 255 compute statistics
        storage (
            initial 65536 next 1048576 minextents 1 maxextents 2147483645
            pctincrease 0 freelists 1 freelist groups 1 buffer_pool default flash_cache default cell_flash_cache default
        )
        tablespace "users" enable
) segment creation immediate
pctfree 10 pctused 40 initrans 1 maxtrans 255 nocompress logging
storage (
    initial 65536 next 1048576 minextents 1 maxextents 2147483645
    pctincrease 0 freelists 1 freelist groups 1 buffer_pool default flash_cache default cell_flash_cache default
)
tablespace "users"


select *
from emp