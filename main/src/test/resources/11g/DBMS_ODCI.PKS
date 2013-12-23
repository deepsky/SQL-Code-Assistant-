PACKAGE DBMS_ODCI AS
-- Overview
-- This is a generic package that can be used by all the extensibility
-- projects. This package is owned by SYS and is granted to PUBLIC

-- This function is used to help users estimate the number of CPU cycles
-- that have been used in a certain time interval.
  FUNCTION estimate_cpu_units(elapsed_time IN NUMBER)
    RETURN NUMBER;

-- This procedure is used to maintain secobj$ during upgrade
  PROCEDURE upgrade_secobj(objlist ODCISecObjTable);

  PROCEDURE SaveRefCursor(rc IN SYS_REFCURSOR, curnum OUT NUMBER)
  IS LANGUAGE C
  LIBRARY odci_extopt_lib
  NAME "SaveRefCursor"
  WITH CONTEXT
  PARAMETERS
  (
    CONTEXT,
    rc,
    curnum OCINumber,
    curnum INDICATOR
  );

  PROCEDURE RestoreRefCursor(rc OUT SYS_REFCURSOR, curnum IN NUMBER)
  IS LANGUAGE C
  LIBRARY odci_extopt_lib
  NAME "RestoreRefCursor"
  WITH CONTEXT
  PARAMETERS
  (
    CONTEXT,
    rc,
    curnum OCINumber,
    curnum INDICATOR
  );

  PROCEDURE GetMetadata(ia IN SYS.ODCIIndexInfo, exp_version IN VARCHAR2,
     idx_version IN NUMBER default 1, stmt_string OUT VARCHAR2,
     new_block OUT NUMBER);

  PROCEDURE GetTableNames(ia IN SYS.ODCIIndexInfo, read_only IN NUMBER,
    exp_version IN VARCHAR2, idx_version IN NUMBER, stmt_string OUT VARCHAR2,
    gtn_context OUT NUMBER, status OUT NUMBER);

  PROCEDURE Cleanup(ia IN SYS.ODCIIndexInfo, gtn_context IN NUMBER);

END DBMS_ODCI;