/*==============================================================*/
/* Table: XDV_TKT_CAUSE_DT                                      */
/*==============================================================*/
CREATE TABLE XDV_TKT_CAUSE_DT  (
   ID                   INTEGER                         NOT NULL,
   AMK_ID               INTEGER,
   CAUSE_TYPE           VARCHAR2(40 BYTE),
   CAUSE_CODE           VARCHAR2(64 BYTE),
   CAUSE_DESC           VARCHAR2(256 BYTE),
   CREATE_DATE          DATE,
   UPDATE_DATE          DATE,
   OBSOLETE_DATE        DATE,
   CREATE_SOURCE        VARCHAR2(32 BYTE),
   UPDATE_SOURCE        VARCHAR2(32 BYTE),
   LOAD_SEQ_NBR         INTEGER
)
ORGANIZATION HEAP
    PCTFREE 5
    PCTUSED 95
    STORAGE (BUFFER_POOL KEEP)
    TABLESPACE XDV_SDT_TS
     LOGGING
     COMPRESS
DISABLE ROW MOVEMENT
CACHE
MONITORING
 NOPARALLEL;
