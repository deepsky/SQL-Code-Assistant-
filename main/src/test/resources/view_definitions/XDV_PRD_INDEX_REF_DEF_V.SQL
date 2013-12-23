CREATE OR REPLACE VIEW XDV_PRD_INDEX_REF_DEF_V
(INDEX_ID, INDEX_NAME, INTERNAL_TABLE_NAME, INDEX_TYPE, UNIQUENESS,
 BUFFER_POOL, TABLESPACE_NAME, POSITION, SORT_ORDER)
AS
select
        ird.id as index_id,
        ird.index_name,
        trd.internal_table_name,
        ird.index_type,
        ird.uniqueness,
        ird.buffer_pool,
        ird.tablespace_name,
        i2c.position,
        i2c.sort_order
   from
        xdv_prd_table_ref_def_t trd,
        xdv_prd_index_ref_def_t ird,
        xdv_prd_column_ref_def_t crd,
        xdv_prd_indcol_at i2c
  where i2c.crd_id = crd.id
    and i2c.ird_id = ird.id
    and crd.trd_id = trd.id
  order by trd.internal_table_name, crd.column_id
/