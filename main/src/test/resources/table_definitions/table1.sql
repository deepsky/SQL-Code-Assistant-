create table table1 (
id number primary key
)

CREATE TABLE "TFL_CPS_COMM_PARAMS_T" (
       "ID"                 VARCHAR2(256) NOT NULL,
       "HOST"               VARCHAR2(256) NULL,
       "PORT"               INTEGER NOT NULL
                                   CONSTRAINT RANGE_0_65535_19
                                          CHECK ("PORT" BETWEEN 0 AND 65535),
       "TIMEOUT"            INTEGER NOT NULL
                                   CONSTRAINT RANGE_0_65535_20
                                          CHECK ("TIMEOUT" BETWEEN 0 AND 65535),
       "RETRIES"            INTEGER NOT NULL
                                   CONSTRAINT RANGE_0_65535_21
                                          CHECK ("RETRIES" BETWEEN 0 AND 65535),
       "COMMUNITY"          VARCHAR2(256) NULL,
       "VERSION"            VARCHAR2(128) NOT NULL,
       "MAX_SIM_REQS_NUM"   INTEGER NOT NULL
                                   CONSTRAINT RANGE_0_1000_7
                                          CHECK ("MAX_SIM_REQS_NUM" BETWEEN 1 AND 1000)
);

select WM_CONCAT(email),
commission_pct,
job_id
from employees

SELECT *
FROM user_tablespaces



