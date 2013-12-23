CREATE TABLE all_objects_big_compressed_xt
    ORGANIZATION EXTERNAL
    (
       TYPE ORACLE_DATAPUMP
       DEFAULT DIRECTORY xt_dir
       ACCESS PARAMETERS (COMPRESSION ENABLED)
       LOCATION ( 'all_objects_big_compressed_xt.dmp' )
    )
    AS
      SELECT  object_id
      FROM   all_objects
      ,     (SELECT ROWNUM AS r FROM dual CONNECT BY ROWNUM <= 10)
      WHERE  object_name NOT LIKE 'ALL_OBJECTS%XT';
