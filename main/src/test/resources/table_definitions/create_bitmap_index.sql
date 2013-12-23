CREATE BITMAP INDEX XDV_MMS_TATET_EXTID_BI ON XDV_SINGTEL_MMS_APP_TYPE_DT (EXT_ID ASC) NOPARALLEL;

CREATE BITMAP INDEX XDV_MMS_TATET_ET_BI ON XDV_SINGTEL_MMS_APP_TYPE_DT (EVENT_TYPE ASC) NOPARALLEL;

-- just on case
drop directory "XDV_EXT_CONFIG_DIR" ;


create or replace directory foo_dir as '/tmp';


create or replace directory foo_dir;
