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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.struct.ColumnDescriptor;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class TableDefGenerator implements ScriptGenerator {

    public String generate(DbObject dbo) {
        return generate(dbo, false);
    }
    public String generate(DbObject dbo, boolean pure) {
        if(dbo instanceof TableDescriptor){
            TableDescriptor tdesc = (TableDescriptor) dbo;

            StringBuilder bld = new StringBuilder();
            bld.append("CREATE TABLE ").append(tdesc.getName().toUpperCase()).append(" (\n");

            List<ColumnDescriptor> pkList = new ArrayList<ColumnDescriptor>();
            List<ColumnDescriptor> fkList = new ArrayList<ColumnDescriptor>();

            String[] columns = tdesc.getColumnNames();
            for(int i =0; i<columns.length; i++){
                ColumnDescriptor cdesc = tdesc.getColumnDescriptor(columns[i]);
                boolean isNull = cdesc.isNullable();
                Type t = cdesc.getType(); //tdesc.getColumnType(columns[i]);
                bld.append("\t").append(columns[i]).append("\t\t").append(t.typeName());
                if(t.dataLength() > 0){
                    bld.append("(").append(t.dataLength()).append(")");
                }

                if(!isNull){
                    bld.append("\tNOT NULL");
                }

                if(cdesc.isPK()){
                    pkList.add(cdesc);
                }

                if(cdesc.isFK()){
                    fkList.add(cdesc);
                }

                if(i < columns.length-1 || fkList.size() > 0 || pkList.size() > 0){
                    bld.append(",");
                }
                bld.append("\n");
            }

            if(pkList.size() > 0){
                // generate PK constraint
// PK spec 1
//            CONSTRAINT invoice_pk PRIMARY KEY(InvoiceNumber),
                ConstrainHelper[] hlps = groupByConstraintName(pkList, ColumnDescriptor.CONSTRAINT_TYPE.PRIMARY_KEY);
                for(int i =0; i<hlps.length; i++){
                    ConstrainHelper hlp = hlps[i];
                    bld.append("\tCONSTRAINT ").append(hlp.constraintName.toUpperCase()).append("\t");
                    bld.append("PRIMARY KEY (");
                    boolean nonfirst = false;
                    for(String c: hlp.columns){
                        if(nonfirst)    bld.append(",");
                        else    nonfirst = true;

                        bld.append(c.toUpperCase());
                    }
                    bld.append(")");
                    if(i < hlps.length-1 || fkList.size() > 0 ){
                        bld.append(",");
                    }
                    bld.append("\n");
                }
            }

            if(fkList.size() > 0){
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
                for(int i =0; i<hlps.length; i++){
                    ConstrainHelper hlp = hlps[i];
                    bld.append("\tCONSTRAINT ").append(hlp.constraintName.toUpperCase()).append("\n");
                    bld.append("\t\tFOREIGN KEY (");
                    boolean nonfirst = false;
                    for(String c: hlp.columns){
                        if(nonfirst)    bld.append(",");
                        else    nonfirst = true;

                        bld.append(c.toUpperCase());
                    }
                    bld.append(")\n");
                    String column99 = hlp.columns[0];
                    String refTable = tdesc.getColumnDescriptor(column99).getReferencedTable();
                    bld.append("\t\tREFERENCES ").append(refTable.toUpperCase()).append("(");
                    boolean nonfirst2 = false;
                    for(String c: hlp.columns){
                        if(nonfirst2)    bld.append(",");
                        else    nonfirst2 = true;

                        bld.append(
                            tdesc.getColumnDescriptor(c).getReferencedColumn().toUpperCase()
                                );
                    }
                    bld.append(")\n");

                    if(i < hlps.length-1){
                        bld.append(",");
                    }
                    bld.append("\n");
                }
            }

            if(pure){
                bld.append(")\n");
            } else {
                bld.append(");\n");
                bld.append("-- Table script is not complete and should be used for referencing only\n");
                bld.append("-- Tablespace and storage parameters are not supported\n");
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
        for(ColumnDescriptor cdesc: columns){
            List<String> t = mapper.get(cdesc.getContsraintName(type));
            if(t == null){
                t = new ArrayList<String>();
                mapper.put(cdesc.getContsraintName(type), t);
            }
            t.add(cdesc.getName());
        }

        ConstrainHelper[] hlps = new ConstrainHelper[mapper.size()];
        int i=0;
        for(Map.Entry<String, List<String>> e: mapper.entrySet()){
            hlps[i] = new ConstrainHelper(e.getKey(), e.getValue());
            i++;
        }

        return hlps;
    }

    public String generate(TableHandler.DbObjectAttributed attributed) {
        if( attributed.dbo.getType() == TableDescriptor.EXTERNAL){
            return generateExtTable(attributed);
        } else {
            return generate(attributed.dbo, false);
        }
    }

    private String generateExtTable(TableHandler.DbObjectAttributed attributed) {
        String header = generate(attributed.dbo, true);

        String mon = attributed.getAttribute("MONITORING");
        String monitoring = (mon != null && mon.equalsIgnoreCase("NO"))? "NOMONITORING": "MONITORING";
        String parallel = "NOPARALLEL";
        if(attributed.getAttribute("DEGREE").equals("1") && attributed.getAttribute("INSTANCES").equals("1")){
            // NOPARALLEL
        } else {
            parallel = "PARALLEL ( DEGREE " + attributed.getAttribute("DEGREE") + " INSTANCES " + attributed.getAttribute("INSTANCES") + " )";
        }

        String tail = "ORGANIZATION EXTERNAL\n" +
                "  (  TYPE " +  attributed.getAttribute("TYPE_NAME") + "\n" +
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

        public ConstrainHelper(String constraintName, String[] columns){
            this.constraintName = constraintName;
            this.columns = columns;
        }

        public ConstrainHelper(String constraintName, List<String> columns){
            this.constraintName = constraintName;
            this.columns = columns.toArray(new String[columns.size()]);
        }
    }
}
