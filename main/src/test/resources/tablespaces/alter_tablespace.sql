ALTER TABLESPACE tbs_01
    BEGIN BACKUP;

ALTER TABLESPACE tbs_01
   END BACKUP;

--Take the tablespace offline using an ALTER TABLESPACE statement with the OFFLINE clause:

ALTER TABLESPACE tbs_02 OFFLINE NORMAL;

--Rename the datafile using an ALTER TABLESPACE statement with the RENAME DATAFILE clause:

ALTER TABLESPACE tbs_02
  RENAME DATAFILE 'diskb:tbs_f5.dat'
  TO              'diska:tbs_f5.dat';

--Bring the tablespace back online using an ALTER TABLESPACE statement with the ONLINE clause:

ALTER TABLESPACE tbs_02 ONLINE;

--The following statement adds a datafile to the tablespace. When more space is needed, new 10-kilobytes extents will be added up to a maximum of 100 kilobytes:

ALTER TABLESPACE tbs_03
    ADD DATAFILE 'tbs_f04.dbf'
    SIZE 100K
    AUTOEXTEND ON
    NEXT 10K
    MAXSIZE 100K;

-- Add an Oracle-managed datafile to the omf_ts1 tablespace
-- The new datafile is 100M and is autoextensible with unlimited maximum size:

ALTER TABLESPACE omf_ts1 ADD DATAFILE;

--The following example changes the default logging attribute of a tablespace to NOLOGGING:

ALTER TABLESPACE tbs_03 NOLOGGING;

--The following statement changes the allocation of every extent of tbs_03 to a multiple of 128K:

ALTER TABLESPACE tbs_03 MINIMUM EXTENT 128K;


--The following statement changes the undo data retention for tablespace undots1 to normal undo data behavior:

ALTER TABLESPACE undots1 RETENTION NOGUARANTEE;

--The following statement changes the undo data retention for tablespace undots1 to behavior that preserves unexpired undo data:

ALTER TABLESPACE undots1 RETENTION GUARANTEE;