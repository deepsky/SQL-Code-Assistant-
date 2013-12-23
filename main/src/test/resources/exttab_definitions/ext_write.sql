CREATE TABLE ext_write (
tab_name, tblspname, numblocks)
ORGANIZATION EXTERNAL
(TYPE oracle_datapump
DEFAULT DIRECTORY ext
LOCATION ('table_history.exp'))
PARALLEL
AS
SELECT table_name, tablespace_name, blocks
FROM user_tables;