
FUNCTION is_aggr_enabled (a_job_type IN INTEGER)
RETURN BOOLEAN
IS
    l_count NUMBER;
    l_stmt  VARCHAR2(2048);
BEGIN

            l_stmt :=
 q'[SELECT index(fmn,XDV_FMN_ID_UK)   COUNT(*)
FROM XDV_PRD_FLOW_MATRIX_NODE_T fmn,
XDV_PRD_FMN2FMN_AT         f2f
WHERE fmn.ID = f2f.CFN_ID
AND fmn.ENABLED LIKE ' Y%'
AND f2f.PFN_ID = :flw
AND f2f.TRANSFER_METHOD = ' AGGREGATION ']';

    EXECUTE IMMEDIATE l_stmt INTO l_count USING xdv_generic_const_pkg.pc_jt2dmtflw(a_job_type);
    IF l_count > 0 THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;

END IS_AGGR_ENABLED;
\

select
q'[SELECT index(fmn,XDV_FMN_ID_UK)   COUNT(*)
FROM XDV_PRD_FLOW_MATRIX_NODE_T fmn,
XDV_PRD_FMN2FMN_AT         f2f
WHERE fmn.ID = f2f.CFN_ID
AND fmn.ENABLED LIKE ' Y%'
AND f2f.PFN_ID = :flw
AND f2f.TRANSFER_METHOD = ' AGGREGATION ']'
from dual
/
\
\

select 1 from dual