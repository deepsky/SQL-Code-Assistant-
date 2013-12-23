CREATE USER sidney
IDENTIFIED BY out_standing1
DEFAULT TABLESPACE example
QUOTA 10M ON example
TEMPORARY TABLESPACE temp
QUOTA 5M ON system
PROFILE app_user
PASSWORD EXPIRE;

CREATE USER app_user1
IDENTIFIED EXTERNALLY
DEFAULT TABLESPACE example
QUOTA 5M ON example
PROFILE app_user;


CREATE USER ops$external_user
IDENTIFIED EXTERNALLY
DEFAULT TABLESPACE example
QUOTA 5M ON example
PROFILE app_user;


CREATE USER global_user
IDENTIFIED GLOBALLY AS 'CN=analyst, OU=division1, O=oracle, C=US'
DEFAULT TABLESPACE example
QUOTA 5M ON example;


create user smp_events identified by smp_events default tablespace smp;
