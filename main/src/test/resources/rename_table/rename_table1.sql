RENAME TABLE old_table TO backup_table;

RENAME TABLE old_table TO tmp_table,
             new_table TO old_table,
             tmp_table TO new_table;


RENAME TABLE current_db.tbl_name TO other_db.tbl_name;


