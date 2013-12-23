CREATE OR REPLACE VIEW "_ALL_FILE_GROUP_TABLESPACES"
(FILE_GROUP_OWNER, FILE_GROUP_NAME, VERSION_NAME, VERSION_ID, VERSION_GUID,
 TABLESPiACE_NAME)
AS
select g.file_group_owner, g.file_group_name, v.version_name, v.version_id,
       v.version_guid, ti.tablespace_name
from "_ALL_FILE_GROUPS" g, sys.fgr$_tablespace_info ti,
     sys.fgr$_file_group_versions v
where ti.version_guid = v.version_guid and v.file_group_id = g.file_group_id
/

