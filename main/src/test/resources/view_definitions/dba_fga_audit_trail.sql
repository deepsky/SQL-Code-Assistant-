create or replace view DBA_FGA_AUDIT_TRAIL
(SESSION_ID, TIMESTAMP, DB_USER, OS_USER, USERHOST, CLIENT_ID
, ECONTEXT_ID, EXT_NAME, OBJECT_SCHEMA, OBJECT_NAME, POLICY_NAME, SCN
, SQL_TEXT, SQL_BIND, COMMENT$TEXT, STATEMENT_TYPE, EXTENDED_TIMESTAMP, PROXY_SESSIONID
, GLOBAL_UID, INSTANCE_NUMBER, OS_PROCESS, TRANSACTIONID, STATEMENTID, ENTRYID
, OBJ_EDITION_NAME) as
select
      sessionid,
      CAST (
        (FROM_TZ(ntimestamp#,'00:00') AT LOCAL) AS DATE
      ),
      dbuid, osuid, oshst, clientid, auditid, extid,
      obj$schema, obj$name, policyname, scn, to_nchar(substr(lsqltext,1,2000)),
      to_nchar(substr(lsqlbind,1,2000)), comment$text,
      DECODE(stmt_type,
              1, 'SELECT', 2, 'INSERT', 4, 'UPDATE', 8, 'DELETE', 'INVALID'),
      FROM_TZ(ntimestamp#,'00:00') AT LOCAL,
      proxy$sid, user$guid, instance#, process#,
      xid, statement, entryid, obj$edition
from sys.fga_log$