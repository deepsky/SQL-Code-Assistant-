CREATE OR REPLACE VIEW XDV_PRD_LOG_V
(LOG_DATE, LOG_LEVEL, LOG_USER, LOG_SECTION, LOG_CALLSTACK,
 LOG_MESSAGE)
AS
SELECT  '['|| TO_CHAR(LDATE, 'YYYY-Mon-DD, HH24:MI:SS')||']' AS log_date,
        '['|| ltype      || '-' || xdv_prd_log_level_t.lname ||']' AS log_level,
        '['|| luser      ||']' AS log_user,
        '['|| lsection   ||']' AS log_section,
        '['|| lcallstack ||']' AS log_callstack,
        '['|| lmessage   ||']' AS log_message
FROM     xdv_prd_log_t, xdv_prd_log_level_t
WHERE    xdv_prd_log_t.lvl_id = xdv_prd_log_level_t.id
ORDER BY xdv_prd_log_t.id
/
