create or replace view DBA_THRESHOLDS
(METRICS_NAME, WARNING_OPERATOR, WARNING_VALUE, CRITICAL_OPERATOR, CRITICAL_VALUE, OBSERVATION_PERIOD
, CONSECUTIVE_OCCURRENCES, INSTANCE_NAME, OBJECT_TYPE, OBJECT_NAME, STATUS) as
select m.name AS metrics_name,
            decode(a.warning_operator, 0, 'GT',
                                       1, 'EQ',
                                       2, 'LT',
                                       3, 'LE',
                                       4, 'GE',
                                       5, 'CONTAINS',
                                       6, 'NE',
                                       7, 'DO NOT CHECK',
                                          'NONE') AS warning_operator,
            a.warning_value AS warning_value,
            decode(a.critical_operator, 0, 'GT',
                                        1, 'EQ',
                                        2, 'LT',
                                        3, 'LE',
                                        4, 'GE',
                                        5, 'CONTAINS',
                                        6, 'NE',
                                        7, 'DO_NOT_CHECK',
                                           'NONE') AS critical_operator,
            a.critical_value AS critical_value,
            a.observation_period AS observation_period,
            a.consecutive_occurrences AS consecutive_occurrences,
            decode(a.instance_name, ' ', null,
                                       instance_name) AS instance_name,
            o.typnam_keltosd AS object_type,
            a.object_name AS object_name,
            decode(a.flags, 1, 'VALID',
                            0, 'INVALID') AS status
  FROM table(dbms_server_alert.view_thresholds) a,
       X$KEWMDSM m,
       X$KELTOSD o
  WHERE a.object_type != 2
    AND m.metricid(+) = a.metrics_id
    AND a.object_type = o.typid_keltosd
  UNION
     select m.name AS metrics_name,
            decode(a.warning_operator, 0, 'GT',
                                       1, 'EQ',
                                       2, 'LT',
                                       3, 'LE',
                                       4, 'GE',
                                       5, 'CONTAINS',
                                       6, 'NE',
                                       7, 'DO_NOT_CHECK',
                                          'NONE') AS warning_operator,
            a.warning_value AS warning_value,
            decode(a.critical_operator, 0, 'GT',
                                        1, 'EQ',
                                        2, 'LT',
                                        3, 'LE',
                                        4, 'GE',
                                        5, 'CONTAINS',
                                        6, 'NE',
                                        7, 'DO NOT CHECK',
                                           'NONE') AS critical_operator,
            a.critical_value AS critical_value,
            a.observation_period AS observation_period,
            a.consecutive_occurrences AS consecutive_occurrences,
            decode(a.instance_name, ' ', null,
                                       instance_name) AS instance_name,
            o.typnam_keltosd AS object_type,
            f.name AS object_name,
            decode(a.flags, 1, 'VALID',
                            0, 'INVALID') AS status
  FROM table(dbms_server_alert.view_thresholds) a,
       X$KEWMDSM m, sys.v$dbfile f, X$KELTOSD o
  WHERE a.object_type = 2
    AND m.metricid = a.metrics_id
    AND a.object_id = f.file#
    AND a.object_type = o.typid_keltosd
    