CREATE TABLE skip_tab (
fiscal_year        NUMBER(4),
prod_no            VARCHAR2(30),
cost_per_unit      FLOAT(126),
gross_rev_per_unit FLOAT(126))
ORGANIZATION EXTERNAL (
TYPE oracle_loader
DEFAULT DIRECTORY ext
ACCESS PARAMETERS (
RECORDS DELIMITED BY NEWLINE
SKIP 1
FIELDS TERMINATED BY ' '
MISSING FIELD VALUES ARE NULL
(fiscal_year INTEGER EXTERNAL (4), prod_no CHAR(30),
cost_per_unit FLOAT EXTERNAL, gross_rev_per_unit FLOAT EXTERNAL))
LOCATION ('cost.txt'));