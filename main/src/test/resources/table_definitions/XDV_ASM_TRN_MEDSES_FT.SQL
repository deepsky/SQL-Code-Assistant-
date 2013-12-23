CREATE TABLE XDV_ASM_TRN_MEDSES_FT
(
  TAT_ID               INTEGER,
  TAC_ID               INTEGER,
  TRC_ID               INTEGER,
  TRS_ID               INTEGER,
  TRQ_ID               INTEGER,
  DDE_ID               INTEGER,
  DTE_ID               INTEGER,
  SERVER_ID            VARCHAR2(256 BYTE),
  END_TIME_MS          NUMBER(3),
  DURATION             NUMBER(10),
  SETUP_DURATION       NUMBER(8),
  DATA_TRANS_DURATION  NUMBER(10),
  TEID                 INTEGER,
  USER_AGENT           VARCHAR2(256 BYTE),
  IMEI                 VARCHAR2(18 BYTE),
  ROAMER_IND           NUMBER(2),
  NETWORK_IND          VARCHAR2(32 BYTE),
  TAC                  VARCHAR2(8 BYTE),
  SVN                  VARCHAR2(2 BYTE),
  MSISDN               VARCHAR2(15 BYTE),
  IMSI                 VARCHAR2(15 BYTE),
  NBR_MESSAGES         NUMBER,
  NBR_BYTES            NUMBER,
  NBR_CLIENT_COMMANDS  NUMBER,
  NBR_BYTES_LOST       NUMBER,
  JITTER               NUMBER,
  USER_ID              VARCHAR2(32 BYTE),
  MAILBOX_NAME         VARCHAR2(128 BYTE),
  SESSION_ID           VARCHAR2(128 BYTE),
  TO_RECIPIENT         VARCHAR2(256 BYTE),
  TO_PATH              VARCHAR2(256 BYTE),
  TO_VIA_PATH          VARCHAR2(256 BYTE),
  FROM_ORIGINATOR      VARCHAR2(256 BYTE),
  FROM_PATH            VARCHAR2(256 BYTE),
  FROM_VIA_PATH        VARCHAR2(256 BYTE),
  SERVER_AGENT         VARCHAR2(128 BYTE),
  FAC_INT_01           VARCHAR2(32 BYTE),
  FAC_INT_02           VARCHAR2(32 BYTE),
  FAC_INT_03           VARCHAR2(32 BYTE),
  FAC_INT_04           VARCHAR2(32 BYTE),
  FAC_INT_05           VARCHAR2(32 BYTE),
  FAC_INT_06           VARCHAR2(32 BYTE),
  FAC_INT_07           VARCHAR2(32 BYTE),
  FAC_INT_08           VARCHAR2(32 BYTE),
  FAC_INT_09           VARCHAR2(32 BYTE),
  FAC_INT_10           VARCHAR2(32 BYTE),
  FAC_EXT_01           VARCHAR2(32 BYTE),
  FAC_EXT_02           VARCHAR2(32 BYTE),
  FAC_EXT_03           VARCHAR2(32 BYTE),
  FAC_EXT_04           VARCHAR2(32 BYTE),
  FAC_EXT_05           VARCHAR2(32 BYTE),
  FAC_EXT_06           VARCHAR2(32 BYTE),
  FAC_EXT_07           VARCHAR2(32 BYTE),
  FAC_EXT_08           VARCHAR2(32 BYTE),
  FAC_EXT_09           VARCHAR2(32 BYTE),
  FAC_EXT_10           VARCHAR2(32 BYTE),
  LOAD_ID              INTEGER,
  INT_SESSION_ID       NUMBER(24)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
NOLOGGING
PARTITION BY RANGE (DDE_ID)
(
  PARTITION XDV_ASM_TRN_20090622_2300_P VALUES LESS THAN (1245715200)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090622_2300_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0000_P VALUES LESS THAN (1245718800)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_0000_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0100_P VALUES LESS THAN (1245722400)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_0100_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0200_P VALUES LESS THAN (1245726000)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_0200_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0300_P VALUES LESS THAN (1245729600)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_0300_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0400_P VALUES LESS THAN (1245733200)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_0400_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0500_P VALUES LESS THAN (1245736800)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_0500_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0600_P VALUES LESS THAN (1245740400)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_0600_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0700_P VALUES LESS THAN (1245744000)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_0700_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0800_P VALUES LESS THAN (1245747600)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_0800_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0900_P VALUES LESS THAN (1245751200)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_0900_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1000_P VALUES LESS THAN (1245754800)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_1000_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1100_P VALUES LESS THAN (1245758400)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_1100_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1200_P VALUES LESS THAN (1245762000)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_1200_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1300_P VALUES LESS THAN (1245765600)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_1300_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1400_P VALUES LESS THAN (1245769200)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_1400_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1500_P VALUES LESS THAN (1245772800)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_1500_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1600_P VALUES LESS THAN (1245776400)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_1600_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1700_P VALUES LESS THAN (1245780000)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_1700_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1800_P VALUES LESS THAN (1245783600)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_1800_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1900_P VALUES LESS THAN (1245787200)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_1900_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_2000_P VALUES LESS THAN (1245790800)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_2000_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_2100_P VALUES LESS THAN (1245794400)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_2100_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_2200_P VALUES LESS THAN (1245798000)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_2200_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_2300_P VALUES LESS THAN (1245801600)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090623_2300_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0000_P VALUES LESS THAN (1245805200)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_0000_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0100_P VALUES LESS THAN (1245808800)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_0100_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0200_P VALUES LESS THAN (1245812400)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_0200_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0300_P VALUES LESS THAN (1245816000)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_0300_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0400_P VALUES LESS THAN (1245819600)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_0400_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0500_P VALUES LESS THAN (1245823200)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_0500_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0600_P VALUES LESS THAN (1245826800)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_0600_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0700_P VALUES LESS THAN (1245830400)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_0700_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0800_P VALUES LESS THAN (1245834000)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_0800_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0900_P VALUES LESS THAN (1245837600)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_0900_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1000_P VALUES LESS THAN (1245841200)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_1000_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1100_P VALUES LESS THAN (1245844800)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_1100_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1200_P VALUES LESS THAN (1245848400)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_1200_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1300_P VALUES LESS THAN (1245852000)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_1300_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1400_P VALUES LESS THAN (1245855600)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_1400_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1500_P VALUES LESS THAN (1245859200)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_1500_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1600_P VALUES LESS THAN (1245862800)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_1600_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1700_P VALUES LESS THAN (1245866400)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_1700_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1800_P VALUES LESS THAN (1245870000)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_1800_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1900_P VALUES LESS THAN (1245873600)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_1900_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_2000_P VALUES LESS THAN (1245877200)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_2000_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_2100_P VALUES LESS THAN (1245880800)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_2100_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_2200_P VALUES LESS THAN (1245884400)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_2200_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_2300_P VALUES LESS THAN (1245888000)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090624_2300_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090625_0000_P VALUES LESS THAN (1245891600)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090625_0000_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090625_0100_P VALUES LESS THAN (1245895200)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090625_0100_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090625_0200_P VALUES LESS THAN (1245898800)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090625_0200_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090625_0300_P VALUES LESS THAN (1245902400)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090625_0300_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090625_0400_P VALUES LESS THAN (1245906000)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090625_0400_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090625_0500_P VALUES LESS THAN (1245909600)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_20090625_0500_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_40000101_0000_P VALUES LESS THAN (MAXVALUE)
    NOLOGGING
    COMPRESS
    TABLESPACE XDV_ASM_TRN_40000101_0000_TS
    PCTFREE    0
    INITRANS   1
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                BUFFER_POOL      DEFAULT
               )
)
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;


CREATE BITMAP INDEX XDV_ASM_TRN_LID_BI ON XDV_ASM_TRN_MEDSES_FT
(LOAD_ID)
  INITRANS   2
  MAXTRANS   255
NOLOGGING
LOCAL (
  PARTITION XDV_ASM_TRN_20090622_2300_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090622_2300_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0000_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_0000_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0100_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_0100_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0200_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_0200_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0300_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_0300_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0400_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_0400_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0500_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_0500_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0600_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_0600_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0700_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_0700_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0800_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_0800_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_0900_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_0900_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1000_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_1000_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1100_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_1100_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1200_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_1200_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1300_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_1300_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1400_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_1400_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1500_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_1500_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1600_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_1600_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1700_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_1700_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1800_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_1800_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_1900_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_1900_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_2000_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_2000_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_2100_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_2100_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_2200_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_2200_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090623_2300_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090623_2300_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0000_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_0000_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0100_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_0100_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0200_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_0200_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0300_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_0300_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0400_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_0400_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0500_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_0500_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0600_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_0600_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0700_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_0700_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0800_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_0800_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_0900_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_0900_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1000_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_1000_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1100_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_1100_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1200_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_1200_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1300_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_1300_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1400_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_1400_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1500_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_1500_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1600_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_1600_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1700_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_1700_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1800_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_1800_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_1900_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_1900_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_2000_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_2000_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_2100_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_2100_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_2200_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_2200_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090624_2300_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090624_2300_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090625_0000_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090625_0000_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090625_0100_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090625_0100_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090625_0200_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090625_0200_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090625_0300_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090625_0300_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090625_0400_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090625_0400_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_20090625_0500_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_20090625_0500_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               ),
  PARTITION XDV_ASM_TRN_40000101_0000_P
    NOLOGGING
    TABLESPACE XDV_ASM_TRN_40000101_0000_TS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          80K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                BUFFER_POOL      DEFAULT
               )
)
NOPARALLEL;


ALTER TABLE XDV_ASM_TRN_MEDSES_FT ADD (
  CONSTRAINT XDV_ASM_TRN_TAC_FK
 FOREIGN KEY (TAC_ID)
 REFERENCES XDV_TRN_APP_CONTENT_TYPE_DT (ID) DISABLE);

ALTER TABLE XDV_ASM_TRN_MEDSES_FT ADD (
  CONSTRAINT XDV_ASM_TRN_TRQ_FK
 FOREIGN KEY (TRQ_ID)
 REFERENCES XDV_TRN_QOS_DT (ID) DISABLE);

ALTER TABLE XDV_ASM_TRN_MEDSES_FT ADD (
  CONSTRAINT XDV_ASM_TRN_TRC_FK
 FOREIGN KEY (TRC_ID)
 REFERENCES XDV_TRN_CAUSES_DT (ID) DISABLE);

ALTER TABLE XDV_ASM_TRN_MEDSES_FT ADD (
  CONSTRAINT XDV_ASM_TRN_DDE_FK
 FOREIGN KEY (DDE_ID)
 REFERENCES XDV_DTM_HRDATE_DT (ID) DISABLE);

ALTER TABLE XDV_ASM_TRN_MEDSES_FT ADD (
  CONSTRAINT XDV_ASM_TRN_DTE_FK
 FOREIGN KEY (DTE_ID)
 REFERENCES XDV_DTM_HRTIME_DT (ID) DISABLE);

ALTER TABLE XDV_ASM_TRN_MEDSES_FT ADD (
  CONSTRAINT XDV_ASM_TRN_TRS_FK
 FOREIGN KEY (TRS_ID)
 REFERENCES XDV_TRN_STATUS_DT (ID) DISABLE);

ALTER TABLE XDV_ASM_TRN_MEDSES_FT ADD (
  CONSTRAINT XDV_ASM_TRN_TAT_FK
 FOREIGN KEY (TAT_ID)
 REFERENCES XDV_TRN_APP_TYPE_DT (ID) DISABLE);

ALTER TABLE XDV_ASM_TRN_MEDSES_FT MODIFY CONSTRAINT XDV_ASM_TRN_TAC_FK RELY;
ALTER TABLE XDV_ASM_TRN_MEDSES_FT MODIFY CONSTRAINT XDV_ASM_TRN_TRQ_FK RELY;
ALTER TABLE XDV_ASM_TRN_MEDSES_FT MODIFY CONSTRAINT XDV_ASM_TRN_TRC_FK RELY;
ALTER TABLE XDV_ASM_TRN_MEDSES_FT MODIFY CONSTRAINT XDV_ASM_TRN_DDE_FK RELY;
ALTER TABLE XDV_ASM_TRN_MEDSES_FT MODIFY CONSTRAINT XDV_ASM_TRN_DTE_FK RELY;
ALTER TABLE XDV_ASM_TRN_MEDSES_FT MODIFY CONSTRAINT XDV_ASM_TRN_TRS_FK RELY;
ALTER TABLE XDV_ASM_TRN_MEDSES_FT MODIFY CONSTRAINT XDV_ASM_TRN_TAT_FK RELY;