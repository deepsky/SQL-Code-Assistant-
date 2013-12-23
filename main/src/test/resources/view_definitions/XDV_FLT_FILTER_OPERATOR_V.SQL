CREATE OR REPLACE VIEW XDV_FLT_FILTER_OPERATOR_V
(CATEGORY, OPERATOR_ABBREV, OPERATOR_NAME, OPERAND_COUNT, OPERAND_FIRST,
 OPERATOR_SET_SUPPORT, OPERANDS_CAN_CHAIN, SQL_KEYWORD1, SQL_KEYWORD2)
AS
SELECT fopr.category,
          fopr.operator_abbrev,
          fopr.operator_name,
          fopr.operand_count,
          fopr.operand_first,
          fopr.operator_set_support,
          fopr.operands_can_chain,
          fsk1.opr_sql_keyword sql_keyword1,
          fsk2.opr_sql_keyword sql_keyword2
     FROM xdv_flt_filter_operator_t fopr
            LEFT OUTER JOIN  (SELECT for_id, opr_sql_keyword, position FROM xdv_flt_filter_opr_sql_kwrd_t WHERE position = 1) fsk1 ON fsk1.for_id = fopr.id
            LEFT OUTER JOIN  (SELECT for_id, opr_sql_keyword, position FROM xdv_flt_filter_opr_sql_kwrd_t WHERE position = 2) fsk2 ON fsk2.for_id = fopr.id
 ORDER BY fopr.id
/
