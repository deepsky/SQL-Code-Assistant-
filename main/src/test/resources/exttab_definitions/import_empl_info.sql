create table import_empl_info
  ( empl_id varchar2(3),
    last_name varchar2(50),
    first_name varchar2(50),
    ssn varchar2(9),
    birth_dt date
  )
  organization external
  ( type oracle_datapump
    default directory xtern_data_dir
    location ('empl_info_rpt.dmp')
  ) ;
