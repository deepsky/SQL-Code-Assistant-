create table export_empl_info
  organization external
  ( type oracle_datapump
    default directory xtern_data_dir
    location ('empl_info_rpt.dmp')
 ) as select * from empl_info;
