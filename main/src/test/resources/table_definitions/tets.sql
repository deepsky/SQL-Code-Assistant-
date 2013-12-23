select partition_name, high_value, partition_position,
tablespace_name, pct_free, ini_trans, max_trans
-- storage params not
from all_tab_partitions--ll_ind_partitions--part_tables --user_part_tables
where table_name = 'XDV_ASM_TRN_MEDSES_FT' --'XDV_ASM_TRN_MEDSES_FT'

select owner, column_name, column_position
from all_part_key_columns
where name = 'XDV_ASM_TRN_MEDSES_FT'



select *
from all_tab_partitions--ll_ind_partitions--part_tables --user_part_tables
where TABLE_OWNER = 'XDVDWR' --table_name = 'XDV_AMS_STGMES_T' --'XDV_ASM_TRN_MEDSES_FT'

select *
from user_PART_TABLES--tables

select *
from all_tab_columns--ll_subpart_key_columns--partcol$--all_tab_columns--subpart_key_columns--tab_subpartitions
where table_name = 'XDV_ASM_TRN_MEDSES_FT'

select owner, column_name, column_position
from all_part_key_columns
where name = 'XDV_ASM_TRN_MEDSES_FT'


select t.partitioning_type,
p.table_name, c.column_name, p.partition_name, p.high_value, p.partition_position,
p.tablespace_name, p.pct_free, p.ini_trans, p.max_trans
-- storage params not
from all_tab_partitions p, all_part_key_columns c
, all_part_tables t
where --p.table_name = 'XDV_ASS_TRN_STGSES_T' -- 'XDV_ASM_TRN_MEDSES_FT' --'XDV_ASM_TRN_MEDSES_FT'
p.table_name = c.NAME
and t.TABLE_NAME = c.NAME

select t.partitioning_type, p.table_name, c.column_name, p.partition_name, p.high_value, p.partition_position,
p.tablespace_name, p.pct_free, p.ini_trans, p.max_trans
from all_tab_partitions p, all_part_key_columns c, all_part_tables t
where t.table_name in (<NAMES>)
and p.table_name = c.NAME
and t.table_name = c.NAME


select *
from all_part_tables
