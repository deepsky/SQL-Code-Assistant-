CREATE OR REPLACE VIEW DBA_WARNING_SETTINGS
(OWNER, OBJECT_NAME, OBJECT_ID, OBJECT_TYPE, WARNING,
 SETTING)
AS
SELECT u.name, o.name, o.obj#,
         DECODE(o.type#,
                 7, 'PROCEDURE',
                 8, 'FUNCTION',
                 9, 'PACKAGE',
                11, 'PACKAGE BODY',
                12, 'TRIGGER',
                13, 'TYPE',
                14, 'TYPE BODY',
                    'UNDEFINED'),
         DECODE(w.warning,
                -1, 'INFORMATIONAL',
                -2, 'PERFORMANCE',
                -3, 'SEVERE',
                -4, 'ALL',
                w.warning),
         DECODE(w.setting,
                0, 'DISABLE',
                1, 'ENABLE',
                2, 'ERROR',
                   'INVALID')
    FROM sys.obj$ o, sys.user$ u,
    TABLE(dbms_warning_internal.show_warning_settings(o.obj#)) w
    WHERE o.owner# = u.user#
    AND o.linkname is null
    AND o.type# IN (7, 8, 9, 11, 12, 13, 14)
    AND w.obj_no = o.obj#
/

