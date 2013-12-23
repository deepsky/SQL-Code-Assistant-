CREATE OR REPLACE VIEW DEFCALL
(CALLNO, DEFERRED_TRAN_ID, SCHEMANAME, PACKAGENAME, PROCNAME,
 ARGCOUNT)
AS
SELECT step_no callno,
       enq_tid deferred_tran_id,
       substrb(sys.dbms_defer_query_utl.key_to_schema_name(
		   recipient_key,
		   enq_tid,
		   step_no), 1, 30) schemaname,
       substrb(sys.dbms_defer_query_utl.key_to_package_name(
		   recipient_key,
		   enq_tid,
		   step_no), 1, 30) packagename,
       substrb(sys.dbms_defer_query_utl.procedure_name(
		   1, --dbms_defer_query_utl.loc_normal_call,
                   chain_no,
                   user_data,
                   recipient_key), 1, 30) procname,
       chain_no argcount
  FROM system.def$_aqcall
    WHERE substrb(sys.dbms_defer_query_utl.key_to_schema_name(
		   recipient_key,
		   enq_tid,
		   step_no), 1, 30) != '** NO DATA FOUND **'
       AND substrb(sys.dbms_defer_query_utl.key_to_package_name(
		   recipient_key,
		   enq_tid,
		   step_no), 1, 30) != '** NO DATA FOUND **'
UNION ALL
SELECT step_no callno,
       enq_tid deferred_tran_id,
       substrb(sys.dbms_defer_query_utl.schema_name(
                  2, --dbms_defer_query_utl.loc_error_call,
                  chain_no,
                  user_data), 1, 30) schemaname,
       substrb(sys.dbms_defer_query_utl.package_name(
                  2, --dbms_defer_query_utl.loc_error_call,
                  chain_no,
                  user_data), 1, 30) packagename,
       substrb(sys.dbms_defer_query_utl.procedure_name(
                  2, --dbms_defer_query_utl.loc_error_call,
                  chain_no,
                  user_data,
                  0), 1, 30) procname,
       chain_no argcount
  FROM system.def$_aqerror
/

