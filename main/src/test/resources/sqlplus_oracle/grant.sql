DECLARE
  row integer := 0;
  user varchar2(256);
BEGIN
user := '&1';
dbms_java.grant_policy_permission('PUBLIC', 'SYS',
  'java.lang.RuntimePermission', 'loadLibrary.*', row);
dbms_java.grant_permission(user, 'SYS:java.lang.RuntimePermission',
'loadLibrary.orawcom10', null);
dbms_java.disable_permission(row);
dbms_java.delete_permission(row);
EXCEPTION
WHEN OTHERS THEN
  IF row > 0 THEN
    dbms_java.disable_permission(row);
    dbms_java.delete_permission(row);
  END IF;
  RAISE;
END;
/

