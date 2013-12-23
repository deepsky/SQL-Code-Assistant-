create table t2
 ( a INTEGER,
   b VARCHAR2(10),
   constraint a_pk primary key (a)
 )
 organization index
 pctfree 10
 tablespace tb1
 pctthreshold 50
 overflow
 pctfree 10
 tablespace tb2
