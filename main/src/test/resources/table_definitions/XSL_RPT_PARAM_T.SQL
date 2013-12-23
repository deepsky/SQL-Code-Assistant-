
CREATE TABLE XSL_RPT_PARAM_T
(
  ID                     INTEGER                NOT NULL,
  OBJ_ID                 INTEGER                NOT NULL,
  CREATE_DATE            DATE,
  FREEZE_DATE            DATE,
  NAME                   VARCHAR2(256 BYTE),
  NOTES                  VARCHAR2(1024 CHAR),
  TYPE                   NUMBER                 NOT NULL,
  REPORT_OBJECT_TYPE     NUMBER                 NOT NULL,
  CUSTOMER_ID            INTEGER                NOT NULL,
  MONITOR_GROUP_ID       INTEGER                NOT NULL,
  MONITOR_ID             INTEGER                NOT NULL,
  MON_OBJECT_ID          INTEGER                NOT NULL,
  MON_OBJECT_SUBTYPE_ID  INTEGER                NOT NULL,
  CREATED_BY             VARCHAR2(256 BYTE)
)
TABLESPACE XSL_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          80K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON TABLE XSL_RPT_PARAM_T IS 'Base table for Report Definition Parameters saved to Report Directory.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.ID IS 'Unique ID for each record created in this table.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.OBJ_ID IS 'ID of the report.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.CREATE_DATE IS 'Date when the report definition was created.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.FREEZE_DATE IS 'Date when the object was deleted; the  record is preserved for historical purposes.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.NAME IS 'Name of the report definition.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.NOTES IS 'Special notes associated with the definition.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.TYPE IS 'Report type of this definition.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.CUSTOMER_ID IS 'ID of the customer associated with the definition.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.MONITOR_GROUP_ID IS 'ID of the monitor group associated with the definition.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.MONITOR_ID IS 'ID of the monitor instance associated with the definition.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.MON_OBJECT_ID IS 'ID of the monitored object associated with the definition.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.MON_OBJECT_SUBTYPE_ID IS 'ID of the subtype of the monitored object associated with the definition.';

COMMENT ON COLUMN XSL_RPT_PARAM_T.CREATED_BY IS 'ID/Name of the person who created the report definition.';


CREATE INDEX XSL_RPT_MO_IE ON XSL_RPT_PARAM_T
(MON_OBJECT_ID)
LOGGING
TABLESPACE XSL_INDEXES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX XSL_RPT_SID_IE ON XSL_RPT_PARAM_T
(CUSTOMER_ID, MONITOR_ID, MONITOR_GROUP_ID, MON_OBJECT_ID)
LOGGING
TABLESPACE XSL_INDEXES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX XSL_RPT_PAR_UK ON XSL_RPT_PARAM_T
(NAME, TYPE, CREATED_BY, FREEZE_DATE)
LOGGING
TABLESPACE XSL_INDEXES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX XSL_RPT_PARAM_PK ON XSL_RPT_PARAM_T
(ID)
LOGGING
TABLESPACE XSL_INDEXES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX XSL_RPT_OBJ_UK ON XSL_RPT_PARAM_T
(OBJ_ID, ID)
LOGGING
TABLESPACE XSL_INDEXES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX XSL_RPT_CUST_IE ON XSL_RPT_PARAM_T
(CUSTOMER_ID)
LOGGING
TABLESPACE XSL_INDEXES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX XSL_RPT_MONITOR_IE ON XSL_RPT_PARAM_T
(MONITOR_ID)
LOGGING
TABLESPACE XSL_INDEXES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX XSL_RPT_MON_GRP_IE ON XSL_RPT_PARAM_T
(MONITOR_GROUP_ID)
LOGGING
TABLESPACE XSL_INDEXES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


ALTER TABLE XSL_RPT_PARAM_T ADD (
  CONSTRAINT XSL_RPT_PARAM_PK
 PRIMARY KEY
 (ID)
    USING INDEX
    TABLESPACE XSL_INDEXES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));