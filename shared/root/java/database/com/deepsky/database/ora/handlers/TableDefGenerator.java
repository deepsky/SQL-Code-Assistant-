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

package com.deepsky.database.ora.handlers;

import com.deepsky.database.ora2.loaders.TableLoader;
import com.deepsky.lang.plsql.struct.ColumnDescriptor;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.struct.Type;

import java.util.*;

public class TableDefGenerator implements ScriptGenerator {

    final static private String CREATE_TABLE = "CREATE TABLE";

    public String generate(DbObject dbo) {
        return generate(dbo, null, false);
    }

    public String generate(DbObject dbo, DbObjectAttributed attributs, boolean pure) {
        if (dbo instanceof TableDescriptor) {
            TableDescriptor tdesc = (TableDescriptor) dbo;

            StringBuilder bld = new StringBuilder();
            bld.append(CREATE_TABLE).append(" ").append(tdesc.getName().toUpperCase()).append(" (\n");

            List<ColumnDescriptor> pkList = new ArrayList<ColumnDescriptor>();
            List<ColumnDescriptor> fkList = new ArrayList<ColumnDescriptor>();

            String[] columns = tdesc.getColumnNames();
            for (int i = 0; i < columns.length; i++) {
                ColumnDescriptor cdesc = tdesc.getColumnDescriptor(columns[i]);
                boolean isNull = cdesc.isNullable();
                Type t = cdesc.getType(); //tdesc.getColumnType(columns[i]);
                bld.append("\t").append(columns[i]).append("\t\t").append(t.typeName());
                if (t.dataLength() > 0) {
                    bld.append("(").append(t.dataLength()).append(")");
                }

                String defaultData = cdesc.defaultData();
                if(defaultData != null){
                    bld.append(" DEFAULT ").append(defaultData.trim());
                }
                if (!isNull) {
                    bld.append(" NOT NULL");
                }

                if (cdesc.isPK()) {
                    pkList.add(cdesc);
                }

                if (cdesc.isFK()) {
                    fkList.add(cdesc);
                }

                if (i < columns.length - 1 || fkList.size() > 0 || pkList.size() > 0) {
                    bld.append(",");
                }
                bld.append("\n");
            }

            if (pkList.size() > 0) {
                // generate PK constraint
// PK spec 1
//            CONSTRAINT invoice_pk PRIMARY KEY(InvoiceNumber),
                ConstrainHelper[] hlps = groupByConstraintName(pkList, ColumnDescriptor.CONSTRAINT_TYPE.PRIMARY_KEY);
                for (int i = 0; i < hlps.length; i++) {
                    ConstrainHelper hlp = hlps[i];
                    bld.append("\tCONSTRAINT ").append(hlp.constraintName.toUpperCase()).append("\t");
                    bld.append("PRIMARY KEY (");
                    boolean nonfirst = false;
                    for (String c : hlp.columns) {
                        if (nonfirst) bld.append(",");
                        else nonfirst = true;

                        bld.append(c.toUpperCase());
                    }
                    bld.append(")");
                    if (i < hlps.length - 1 || fkList.size() > 0) {
                        bld.append(",");
                    }
                    bld.append("\n");
                }
            }

            if (fkList.size() > 0) {
                // generate FK constraint
// FK spec 1
//            CONSTRAINT fk_supplier_comp
//              FOREIGN KEY (supplier_id, supplier_name)
//              REFERENCES supplier(supplier_id, supplier_name)
// FK spec 2
//            CONSTRAINT supplier_fk
//               FOREIGN KEY(SupplierNumber)
//               REFERENCES Supplier(SupplierNumber)
//               ON UPDATE CASCADE ON DELETE RESTRICT
                ConstrainHelper[] hlps = groupByConstraintName(fkList, ColumnDescriptor.CONSTRAINT_TYPE.FOREIGN_KEY);
                for (int i = 0; i < hlps.length; i++) {
                    ConstrainHelper hlp = hlps[i];
                    bld.append("\tCONSTRAINT ").append(hlp.constraintName.toUpperCase()).append("\n");
                    bld.append("\t\tFOREIGN KEY (");
                    boolean nonfirst = false;
                    for (String c : hlp.columns) {
                        if (nonfirst) bld.append(",");
                        else nonfirst = true;

                        bld.append(c.toUpperCase());
                    }
                    bld.append(")\n");
                    String column99 = hlp.columns[0];
                    String refTable = tdesc.getColumnDescriptor(column99).getReferencedTable();
                    bld.append("\t\tREFERENCES ").append(refTable.toUpperCase()).append("(");
                    boolean nonfirst2 = false;
                    for (String c : hlp.columns) {
                        if (nonfirst2) bld.append(",");
                        else nonfirst2 = true;

                        bld.append(
                                tdesc.getColumnDescriptor(c).getReferencedColumn().toUpperCase()
                        );
                    }
                    bld.append(")\n");

                    if (i < hlps.length - 1) {
                        bld.append(",");
                    }
                    bld.append("\n");
                }
            }

            bld.append(")");
            if(attributs != null){
                bld.append("\n");
                // TABLESPACE PARAMS
/*
                TABLESPACE USERS
                PCTUSED    0
                PCTFREE    10
                INITRANS   1
                MAXTRANS   255
                NOLOGGING
*/
                String[] attrNames = new String[]{"TABLESPACE_NAME", "PCT_USED", "PCT_FREE", "INI_TRANS", "MAX_TRANS", "LOGGING"};
                String[] valueNames = new String[]{"TABLESPACE", "PCTUSED", "PCTFREE", "INITRANS", "MAXTRANS", "NOLOGGING"};
                int oldLen = bld.length();
                for(int i =0; i< attrNames.length; i++){
                    String value = attributs.getAttribute(attrNames[i]);

                    if(bld.length() != oldLen && (value != null || attrNames[i].equals("LOGGING"))){
                        bld.append("\n");
                    }

                    if(attrNames[i].equals("LOGGING")){
                        bld.append((value == null || !value.equalsIgnoreCase("YES"))? "NOLOGGING": "LOGGING");
                    } else if(value != null && value.trim().length() > 0){
                        bld.append(valueNames[i]).append("\t").append(value);
                    }
                }
            }

            if (pure) {
                bld.append("\n");
            } else {
                bld.append(";\n");
                bld.append("-- Table script is not complete and should be used for referencing only\n");
//                bld.append("-- Tablespace and storage parameters are not supported\n");
            }
            return bld.toString();
        } else {
            // todo
            return null;
        }
    }

    private ConstrainHelper[] groupByConstraintName(
            List<ColumnDescriptor> columns, ColumnDescriptor.CONSTRAINT_TYPE type) {

        Map<String, List<String>> mapper = new HashMap<String, List<String>>();
        for (ColumnDescriptor cdesc : columns) {
            List<String> t = mapper.get(cdesc.getContsraintName(type));
            if (t == null) {
                t = new ArrayList<String>();
                mapper.put(cdesc.getContsraintName(type), t);
            }
            t.add(cdesc.getName());
        }

        ConstrainHelper[] hlps = new ConstrainHelper[mapper.size()];
        int i = 0;
        for (Map.Entry<String, List<String>> e : mapper.entrySet()) {
            hlps[i] = new ConstrainHelper(e.getKey(), e.getValue());
            i++;
        }

        return hlps;
    }

    public String generate(DbObjectAttributed attributed) {
        if (attributed.dbo.getType() == TableDescriptor.EXTERNAL) {
            return generateExtTable(attributed);
        } else if (attributed.dbo.getType() == TableDescriptor.TEMPORARY) {
            return generateTempTable(attributed);
        } else if (attributed.dbo.getType() == TableDescriptor.PARTITIONED) {
            return generatePartitionedTable(attributed);
        } else {
            return generate(attributed.dbo, attributed,  false);
        }
    }

    // "partitioning_type.column_name.partition_name.high_value.partition_position.tablespace_name.pct_free.ini_trans.max_trans";
    private String generatePartitionedTable(DbObjectAttributed attributed) {
        String[] columns = TableLoader.PARTITION_MAPPING.split("\\.");
        boolean errorOnParse = false;
        String partitionType = "";
        String columnName = "";

        List<PartitionSpecHelper> pos2part = new ArrayList<PartitionSpecHelper>();

        for (Map.Entry<String, String> attr : attributed.attributes.entrySet()) {
            if (attr.getKey().startsWith(TableLoader.PARTITION_SPEC + ":")) {
                String[] values = attr.getValue().split("\\:");
                if (values.length != columns.length) {
                    errorOnParse = true;
                    break;
                }

                partitionType = values[0];
                columnName = values[1];
                pos2part.add(new PartitionSpecHelper(values[2], values[3], values[4], values[5], values[6], values[7], values[8]));
            }
        }

        String parts = "";
        if (!errorOnParse) {
            // sort partiotion specs according to partition position
            Collections.sort(pos2part, new Comparator<PartitionSpecHelper>(){
                public int compare(PartitionSpecHelper o1, PartitionSpecHelper o2) {
                    return o1.partition_position.compareTo(o2.partition_position);
                }
            });
            if (partitionType.equals("RANGE")) {
                parts = buildRangePartitionScript(columnName, pos2part);
            } else if (partitionType.equals("HASH")) {
                parts = buildHashPartitionScript(columnName, pos2part);
            } else {
                // todo -- implement rest of partition types 
            }
        } else {
            // todo -- handle partition mapping error
            int h =0;
        }
        return generate(attributed.dbo, attributed, true) + parts + ";\n";
    }

    /*
            PARTITION BY HASH (DDE_ID )
            (PARTITION
                DDV_UAM_1HR_40000101_0000_P
                TABLESPACE DDV_UAM_1HR_40000101_0000_TS)
            (PARTITION ....
    */
    private String buildHashPartitionScript(String columnName, List<PartitionSpecHelper> parts) {
        String out = "PARTITION BY HASH (" + columnName.toUpperCase() + ")\n";
        StringBuilder sb = new StringBuilder();
        for (PartitionSpecHelper part : parts) {
            if(sb.length() > 0){
                sb.append("\n");
            }
            sb.append("(PARTITION\n").append("\t").append(part.partition_name).append("\n");
            sb.append("\t").append("TABLESPACE ").append(part.tablespace_name).append(")");
        }

        return out + sb.toString();
    }

    /*
            PARTITION BY RANGE (DDE_ID )
            (PARTITION
                DDV_UAM_1HR_40000101_0000_P VALUES LESS THAN (MAXVALUE)
                TABLESPACE DDV_UAM_1HR_40000101_0000_TS
                PCTFREE    0
                INITRANS   1
                MAXTRANS   255,
            PARTITION ....)

    */
    private String buildRangePartitionScript(String columnName, List<PartitionSpecHelper> parts) {
        String out = "PARTITION BY RANGE (" + columnName.toUpperCase() + ")\n";
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        int oldLen = sb.length();
        for (PartitionSpecHelper part : parts) {
            if(sb.length() > oldLen){
                sb.append(",\n");
            }
            sb.append("PARTITION\n").append("\t").append(part.partition_name);
            sb.append(" VALUES LESS THAN (").append(part.high_value).append(")\n");
            sb.append("\t").append("TABLESPACE ").append(part.tablespace_name).append("\n");
            sb.append("\t").append("PCTFREE\t").append(part.pct_free).append("\n");
            sb.append("\t").append("INITRANS\t").append(part.ini_trans).append("\n");
            sb.append("\t").append("MAXTRANS\t").append(part.max_trans);
        }
        sb.append(")");

        return out + sb.toString();
    }


    class PartitionSpecHelper {
        public String partition_name;
        public String high_value;
        public String partition_position;
        public String tablespace_name;
        public String pct_free;
        public String ini_trans;
        public String max_trans;

        public PartitionSpecHelper(
                String partition_name, String high_value, String partition_position,
                String tablespace_name, String pct_free, String ini_trans, String max_trans) {
            this.partition_name = partition_name;
            this.partition_position = partition_position;
            this.high_value = high_value;
            this.tablespace_name = tablespace_name;
            this.pct_free = pct_free;
            this.ini_trans = ini_trans;
            this.max_trans = max_trans;
        }

    }

    private String generateTempTable(DbObjectAttributed attributed) {
        StringBuilder bld = new StringBuilder();

        String base = generate(attributed.dbo, null, true);
        bld.append(base.replace(CREATE_TABLE, "CREATE GLOBAL TEMPORARY TABLE").trim());
        // todo -- actually, just a stub
        bld.append(" ON COMMIT PRESERVE ROWS;\n");
        bld.append("-- Table script is not complete and should be used for referencing only\n");
//        bld.append("-- Tablespace and storage parameters are not supported\n");
        return bld.toString();
    }

    private String generateExtTable(DbObjectAttributed attributed) {
        String header = generate(attributed.dbo, null, true);

        String mon = attributed.getAttribute("MONITORING");
        String monitoring = (mon != null && mon.equalsIgnoreCase("NO")) ? "NOMONITORING" : "MONITORING";
        String parallel = "NOPARALLEL";
        if (attributed.getAttribute("DEGREE").equals("1") && attributed.getAttribute("INSTANCES").equals("1")) {
            // NOPARALLEL
        } else {
            parallel = "PARALLEL ( DEGREE " + attributed.getAttribute("DEGREE") + " INSTANCES " + attributed.getAttribute("INSTANCES") + " )";
        }

        String tail = "ORGANIZATION EXTERNAL\n" +
                "  (  TYPE " + attributed.getAttribute("TYPE_NAME") + "\n" +
                "     DEFAULT DIRECTORY " + attributed.getAttribute("DEFAULT_DIRECTORY_NAME") + "\n" +
                "ACCESS PARAMETERS ( " + attributed.getAttribute("ACCESS_PARAMETERS") + "    )\n" +
                "     LOCATION (" + attributed.getAttribute("DIRECTORY_NAME") + ":'" + attributed.getAttribute("LOCATION") + "')\n" +
                "  )\n" +
                "REJECT LIMIT " + attributed.getAttribute("REJECT_LIMIT") + "\n" +
                parallel + "\n" +
                monitoring + ";";

//        attributed.getAttribute("DEGREE");
//        attributed.getAttribute("INSTANCES");
//        attributed.getAttribute("MONITORING");
//
//        attributed.getAttribute("LOCATION");
//        attributed.getAttribute("DIRECTORY_NAME");
//        attributed.getAttribute("TYPE_NAME");
//        attributed.getAttribute("REJECT_LIMIT");
//        attributed.getAttribute("DEFAULT_DIRECTORY_NAME");
//        attributed.getAttribute("ACCESS_PARAMETERS");
        return header + tail;
    }


    class ConstrainHelper {
        public String constraintName;
        public String[] columns;

        public ConstrainHelper(String constraintName, String[] columns) {
            this.constraintName = constraintName;
            this.columns = columns;
        }

        public ConstrainHelper(String constraintName, List<String> columns) {
            this.constraintName = constraintName;
            this.columns = columns.toArray(new String[columns.size()]);
        }
    }
}
