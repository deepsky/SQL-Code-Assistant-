/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.database.ora2.loaders;

import com.deepsky.database.DBException;
import com.deepsky.database.MappingHelper;
import com.deepsky.database.ResultSetHelper;
import com.deepsky.database.ora.*;
import com.deepsky.database.ora.desc.OraTableDescriptor;
import com.deepsky.database.ora.handlers.DbObjectAttributed;
import com.deepsky.database.ora.handlers.TableDefGenerator;
import com.deepsky.database.ora2.DbObjectSpec;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TableLoader extends DbObjectLoaderAbstract {
    static LoggerProxy log = LoggerProxy.getInstance("#TableHandler");

    final public static String PARTITION_SPEC = "partion_spec";
    final public static String PARTITION_MAPPING =
            "partitioning_type.column_name.partition_name.high_value.partition_position.tablespace_name.pct_free.ini_trans.max_trans";


    final static String TEMP = "temp";
    final static String PARTITIONED = "part";
    final static String EXTERNAL = "ext";
    final static String REGULAR = "reg";

    final String listTableSql =
//            "SELECT OWNER, TABLE_NAME, COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE, COLUMN_ID " +
//                    "FROM ALL_TAB_COLUMNS " +
//                    "WHERE OWNER IN (<OWNERS>) AND TABLE_NAME = ? " +
//                    "ORDER BY OWNER, TABLE_NAME, COLUMN_ID";
            "SELECT OWNER, TABLE_NAME, COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE, COLUMN_ID, " +
                    "DEFAULT_LENGTH, DATA_DEFAULT\n" +
                    "FROM ALL_TAB_COLUMNS\n" +
                    "WHERE OWNER IN (<OWNERS>) AND TABLE_NAME IN (<NAMES>)\n" +
                    "ORDER BY OWNER, TABLE_NAME, COLUMN_ID";

    final String externalTableAttribs =
            "select loc.DIRECTORY_NAME, loc.LOCATION, loc.table_name,\n" +
                    "tab.type_name, tab.reject_limit, tab.DEFAULT_DIRECTORY_NAME, tab.ACCESS_PARAMETERS\n" +
                    "from user_external_locations loc,\n" +
                    "user_external_tables tab\n" +
                    "where loc.TABLE_NAME = tab.TABLE_NAME\n" +
                    "and tab.TABLE_NAME in (<NAMES>)";

    final String partitionSpecification =
            "select t.partitioning_type, p.table_name, c.column_name, p.partition_name, p.high_value, p.partition_position,\n" +
                    "p.tablespace_name, p.pct_free, p.ini_trans, p.max_trans\n" +
                    "from all_tab_partitions p, all_part_key_columns c, all_part_tables t \n" +
                    "where t.table_name in (<NAMES>)\n" +
                    "and p.table_name = c.NAME\n" +
                    "and t.table_name = c.NAME";

    final String tableExAttribs =
            "select\n" +
                    "   tname as table_name, table_type, constraint_type, i1.constraint_name as constraint_name, " +
                    "   r_constraint_name, i1.column_name as column_name,\n" +
                    "   cc1.column_name as parent_column_name, cc1.table_name as parent_table_name,\n" +
                    "   i1.DEGREE as DEGREE, i1.INSTANCES as INSTANCES, i1.MONITORING as MONITORING,\n" +
                    "   i1.tablespace_name, i1.pct_used, i1.pct_free, i1.ini_trans, i1.max_trans, i1.logging\n" +
                    "from\n" +
                    "    (select tname, table_type, c.constraint_type, c.constraint_name, c.r_constraint_name, cc.column_name,\n" +
                    "       DEGREE, INSTANCES, MONITORING, tablespace_name, pct_used, pct_free, ini_trans, max_trans, logging\n" +
                    "    from\n" +
                    "        ( select u.table_name tname,\n" +
                    "            case when e.table_name is null\n" +
                    "            then (case when u.temporary = 'Y' then 'temp' else (case when u.partitioned = 'YES' then 'part' else 'reg' end) end)\n" +
                    "            else 'ext' end as table_type,\n" +
                    "            u.DEGREE as DEGREE,\n" +
                    "            u.INSTANCES as INSTANCES,\n" +
                    "            u.MONITORING as MONITORING,\n" +
                    "            u.tablespace_name as tablespace_name,\n" +
                    "            u.pct_used as pct_used,\n" +
                    "            u.pct_free as pct_free,\n" +
                    "            u.ini_trans as ini_trans,\n" +
                    "            u.max_trans as max_trans,\n" +
                    "            u.logging as logging\n" +
                    "         from all_tables u\n" +
                    "            left join all_external_tables e\n" +
                    "            on u.table_name = e.table_name\n" +
                    "         where\n" +
                    "            u.table_name in (<NAMES>)\n" +
                    "        and u.owner IN (<OWNERS>)\n" +
                    "    ) i0\n" +
                    "    left join all_constraints c\n" +
                    "        on (c.table_name = tname and c.constraint_type in ('P', 'R'))\n" +
                    "    left join all_cons_columns cc\n" +
                    "        on (c.table_name = cc.table_name and c.owner = cc.owner and cc.OWNER IN (<OWNERS>) and cc.constraint_name = c.constraint_name)\n" +
                    ") i1\n" +
                    "left join all_cons_columns cc1\n" +
                    "    on (cc1.owner IN (<OWNERS>) and cc1.constraint_name = i1.r_constraint_name)";

/*  todo - the query which fails on Oracle 9i
                    "select\n" +
                    "   tname as table_name, table_type, constraint_type, i1.constraint_name as constraint_name, " +
                    "   r_constraint_name, i1.column_name as column_name,\n" +
                    "   cc1.column_name as parent_column_name, cc1.table_name as parent_table_name\n" +
                    "from\n" +
                    "    (select tname, table_type, c.constraint_type, c.constraint_name, c.r_constraint_name, cc.column_name\n" +
                    "    from\n" +
                    "        ( select u.table_name tname,\n" +
                    "            case when e.table_name is null\n" +
                    "            then (case when u.temporary = 'Y' then 'temp' else (case when u.partitioned = 'YES' then 'part' else 'reg' end) end)\n" +
                    "            else 'ext' end as table_type\n" +
                    "         from all_tables u\n" +
                    "            left join all_external_tables e\n" +
                    "            on u.table_name = e.table_name\n" +
                    "         where\n" +
                    "            u.table_name in (<NAMES>)\n" +
                    "        and u.owner IN (<OWNERS>)\n" +
                    "    ) i0\n" +
                    "    left join all_constraints c\n" +
                    "        on (c.table_name = tname and c.constraint_type in ('P', 'R') and c.OWNER IN (<OWNERS>))\n" +
                    "    left join all_cons_columns cc\n" +
                    "        on (c.table_name = cc.table_name and c.owner = cc.owner and cc.constraint_name = c.constraint_name)\n" +
                    ") i1\n" +
                    "left join all_cons_columns cc1\n" +
                    "    on (cc1.owner IN (<OWNERS>) and cc1.constraint_name = i1.r_constraint_name)";

 */


    public String getId() {
        return "TABLE";
    }


    public List<DbObjectSpec> load(final Connection conn, Mix mix) throws DBException {
        String sql = listTableSql.replace("<NAMES>", StringUtils.convert2listStrings(mix.names));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(mix.owners));
        ResultSetHelper rsHlp = new ResultSetHelper(conn);

        log.info( "TABLES: " + StringUtils.convert2listStrings(mix.names));

        long ms = System.currentTimeMillis();
        // request common tables' parameters
        final Map<String, DbObjectAttributed> tables = new HashMap<String, DbObjectAttributed>();
        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String owner = rs.getString("OWNER");
                    String name = rs.getString("TABLE_NAME");
                    String column = rs.getString("COLUMN_NAME");
                    String data_type = rs.getString("DATA_TYPE");
                    int data_len = rs.getInt("DATA_LENGTH");
                    String nullable = rs.getString("NULLABLE");
                    long columnId = rs.getLong("COLUMN_ID");
                    long defaultLen = rs.getLong("DEFAULT_LENGTH");
                    String dataDefault = rs.getString("DATA_DEFAULT");

                    DbObjectAttributed dbo = tables.get(name);
                    if (dbo == null) {
                        dbo = new DbObjectAttributed(
                                owner,
                                new OraTableDescriptor(
                                        null, //new DbObjectScriptLocator(null, DbObject.TABLE, name),
                                        name)
                        );
                        tables.put(name, dbo);
                    }
                    OraTableDescriptor tab = (OraTableDescriptor) dbo.dbo;
                    tab.addColumn(column, TypeFactory.createTypeByName(data_type, data_len), "Y".equals(nullable), (int) columnId, dataDefault);
                }
            });

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        long ms2 = System.currentTimeMillis();

        final Set<String> partitionedTables = new HashSet<String>();

        // request table type, list pk/fk columns, list of  referenced tables and columns
        String names = StringUtils.convert2listStrings(mix.names);
        String sql2 = tableExAttribs.replace("<NAMES>", names);
        sql2 = sql2.replace("<OWNERS>", StringUtils.convert2listStrings(mix.owners));
        try {
            rsHlp.populateFromResultSet(sql2, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String tab_name = rs.getString("TABLE_NAME");
                    String column = rs.getString("COLUMN_NAME");
                    String table_type = rs.getString("TABLE_TYPE");
                    String constraint_type = rs.getString("CONSTRAINT_TYPE");
                    String parent_tab_name = rs.getString("PARENT_TABLE_NAME");
                    String parent_column = rs.getString("PARENT_COLUMN_NAME");

                    __assertCancel__();

                    // todo --
                    String constraint_name = rs.getString("CONSTRAINT_NAME");
                    String degree = rs.getString("DEGREE");
                    String instances = rs.getString("INSTANCES");
                    String monitoring = rs.getString("MONITORING");
                    // tablespace_name, pct_used, pct_free, ini_trans, max_trans, logging

                    DbObjectAttributed dbo = tables.get(tab_name);
                    if (dbo == null) {
                        // todo - looks strange, move to the next record
                        return;
                    }
                    dbo.putAttribute("TABLESPACE_NAME", rs.getString("TABLESPACE_NAME"));
                    dbo.putAttribute("PCT_FREE", rs.getString("PCT_FREE"));
                    dbo.putAttribute("PCT_USED", rs.getString("PCT_USED"));
                    dbo.putAttribute("INI_TRANS", rs.getString("INI_TRANS"));
                    dbo.putAttribute("MAX_TRANS", rs.getString("MAX_TRANS"));
                    dbo.putAttribute("LOGGING", rs.getString("LOGGING"));

                    dbo.putAttribute("DEGREE", degree.trim());
                    dbo.putAttribute("INSTANCES", instances.trim());
                    dbo.putAttribute("MONITORING", monitoring.trim());

                    OraTableDescriptor tab = (OraTableDescriptor) dbo.dbo;
                    if ("R".equalsIgnoreCase(constraint_type)) {
                        // foregn key found
                        tab.setFKAttribute(column, parent_tab_name, parent_column);
                        tab.setType(mapStringTabType2Int(table_type));
                        tab.setConstraintNameFK(column, constraint_name);
                    } else if ("P".equalsIgnoreCase(constraint_type)) {
                        // primary key found
                        tab.setPKAttribute(column);
                        tab.setType(mapStringTabType2Int(table_type));
                        tab.setConstraintNamePK(column, constraint_name);
                    } else {
                        // update table type
                        tab.setType(mapStringTabType2Int(table_type));
                    }

                    if (tab.getType() == TableDescriptor.PARTITIONED) {
                        partitionedTables.add(tab_name);
                    }
                }
            });

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        long ms3 = System.currentTimeMillis();
        // [START] Find additional attributes for EXT tables
        List<String> extTables = groupByTableType(TableDescriptor.EXTERNAL, tables);
        if (extTables.size() > 0) {
            String sql3 = externalTableAttribs.replace("<NAMES>", StringUtils.convert2listStrings(extTables));
            try {
                rsHlp.populateFromResultSet(sql3, new MappingHelper() {
                    public void processRow(ResultSet rs) throws SQLException {
                        String tab_name = rs.getString("TABLE_NAME");
                        DbObjectAttributed dbo = tables.get(tab_name);
                        if (dbo == null) {
                            // todo - looks strange, move to the next record
                            return;
                        }

                        __assertCancel__();

                        dbo.putAttribute("LOCATION", rs.getString("LOCATION").trim());
                        dbo.putAttribute("DIRECTORY_NAME", rs.getString("DIRECTORY_NAME").trim());
                        dbo.putAttribute("TYPE_NAME", rs.getString("TYPE_NAME").trim());
                        dbo.putAttribute("REJECT_LIMIT", rs.getString("REJECT_LIMIT").trim());
                        dbo.putAttribute("DEFAULT_DIRECTORY_NAME", rs.getString("DEFAULT_DIRECTORY_NAME").trim());
                        dbo.putAttribute("ACCESS_PARAMETERS", rs.getString("ACCESS_PARAMETERS").trim());
                    }
                });

            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
        //  [END] Find additional attributes for EXT tables

        long ms4 = System.currentTimeMillis();
        // [START] Find additional attributes for PARTITIONED tables
        if (partitionedTables.size() > 0) {
            String sql3 = partitionSpecification.replace("<NAMES>", StringUtils.convert2listStrings(partitionedTables.toArray(new String[0])));
            try {
                rsHlp.populateFromResultSet(sql3, new MappingHelper() {
                    public void processRow(ResultSet rs) throws SQLException {
                        String tab_name = rs.getString("TABLE_NAME");
                        DbObjectAttributed dbo = tables.get(tab_name);
                        if (dbo == null) {
                            // todo - looks strange, move to the next record
                            return;
                        }

                        __assertCancel__();

                        String num = dbo.getAttribute("PartitionNumber");
                        if (num == null) {
                            num = "0";
                        }
                        int nbr = Integer.parseInt(num) + 1;
                        dbo.putAttribute("PartitionNumber", Integer.toString(nbr));

                        StringBuilder sb = new StringBuilder();
                        String[] columns = PARTITION_MAPPING.split("\\.");
                        for (String column : columns) {
                            if (sb.length() > 0) {
                                sb.append(":");
                            }
                            sb.append(rs.getString(column));
                        }

                        dbo.putAttribute(PARTITION_SPEC + ":" + nbr, sb.toString());
                    }
                });

            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
        // [END] Find additional attributes for PARTITIONED tables

        long ms5 = System.currentTimeMillis();

        List<DbObjectSpec> out = new ArrayList<DbObjectSpec>();
        for (String name : tables.keySet()) {
            try {
                DbObjectAttributed ex = tables.get(name);
                // generate "CREATE TABLE" script
                DbObjectSpec dboEx = new DbObjectSpec(
                        DbObject.TABLE, name,
                        new TableDefGenerator().generate(ex)
                );
                out.add(dboEx);
            } catch (Throwable e) {
                log.warn("Cannot build a table script for: " + name);
            }
        }

        long ms6 = System.currentTimeMillis();

        log.info("Phase 1: " + (ms2 - ms) + " Phase 2: " + (ms3 - ms2) + " Phase 3: " + (ms4 - ms3) + " Phase 4: " + (ms5 - ms4) + " Phase 5: " + (ms6 - ms5));
        return out;
    }


    List<String> groupByTableType(int tabType, Map<String, DbObjectAttributed> tables) {
        List<String> out = new ArrayList<String>();
        for (DbObjectAttributed attr : tables.values()) {
            TableDescriptor tdesc = attr.dbo;
            if (tdesc.getType() == tabType) {
                out.add(tdesc.getName());
            }
        }

        return out;
    }

    int mapStringTabType2Int(String type) {
        if (TEMP.equalsIgnoreCase(type)) {
            return TableDescriptor.TEMPORARY;
        } else if (PARTITIONED.equalsIgnoreCase(type)) {
            return TableDescriptor.PARTITIONED;
        } else if (EXTERNAL.equalsIgnoreCase(type)) {
            return TableDescriptor.EXTERNAL;
        } else {
            return TableDescriptor.REGULAR;
        }
    }


    public void setListener(ParseEventListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
