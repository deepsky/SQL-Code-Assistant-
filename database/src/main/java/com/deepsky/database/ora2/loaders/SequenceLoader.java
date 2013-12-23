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
import com.deepsky.database.ora.Mix;
import com.deepsky.database.ora2.DbObjectSpec;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SequenceLoader extends DbObjectLoaderAbstract {

    static LoggerProxy log = LoggerProxy.getInstance("#SequenceHandler");

    final String listSeqSql =
            "select \n"+
            "SEQUENCE_OWNER, sequence_name, min_value, max_value, increment_by, cycle_flag, order_flag, cache_size, last_number\n"+
            "from ALL_SEQUENCES\n" +
            "WHERE SEQUENCE_OWNER IN (<OWNERS>) AND SEQUENCE_NAME IN (<NAMES>)\n";


    public String getId() {
        return DbObject.SEQUENCE;
    }

    public List<DbObjectSpec> load(Connection conn, Mix mix) throws DBException {
        String sql = listSeqSql.replace("<NAMES>", StringUtils.convert2listStrings(mix.names));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(mix.owners));

        // load list of SEQUENCES
        log.info( "SEQUENCES: " + StringUtils.convert2listStrings(mix.names));
        ResultSetHelper rsHlp = new ResultSetHelper(conn);

        final List<DbObjectSpec> out = new ArrayList<DbObjectSpec>();
        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String owner = rs.getString("sequence_owner");
                    String name = rs.getString("SEQUENCE_NAME");
                    BigDecimal maxValue = rs.getBigDecimal("max_value");
                    BigDecimal minValue = rs.getBigDecimal("min_value");
                    int incrementBy = rs.getInt("increment_by");
                    int cacheSize = rs.getInt("cache_size");

                    // no sence, because definition gonna be cached
                    //BigDecimal lastNumber = rs.getBigDecimal("last_number");

                    String cycled = rs.getString("cycle_flag");
                    String ordered = rs.getString("order_flag");

//                    Date lastDDLTime = null;
//                    try {
//                        lastDDLTime = new Date(fmt.parse(rs.getString("TIMESTAMP")).getTime());
//                    } catch (ParseException e) {
//                    }

//                    DbObjectEx dbo = seqs.get(name);
//                    if (dbo == null) {
//                        dbo = new DbObjectEx(owner, new SequenceDescriptorImpl(name), null);
//                        seqs.put(name, dbo);
//                    }
//                    SequenceDescriptorImpl seq = (SequenceDescriptorImpl) dbo.dbo;
//                    seq.setLastDDLTime(lastDDLTime);

                    String script = createScript(
                            name,
                            maxValue.toString(),
                            minValue.toString(),
                            incrementBy,
                            cycled.equals("Y"),
                            ordered.equals("Y"),
                            cacheSize);

                    out.add(new DbObjectSpec(
                                DbObject.SEQUENCE,
                                name,
                                script
                            )
                    );

                }
            });

//            List<DbObjectEx> out = new ArrayList<DbObjectEx>();
//            for (DbObjectEx ex : seqs.values()) {
//                out.add(ex);
//            }

            return out;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }




/*
CREATE SEQUENCE OTARES.OTA_TRN_SEQ
  START WITH 868170
  INCREMENT BY 2
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  CYCLE
  CACHE 200
  NOORDER;
*/

    private String createScript(String sequenceName, String maxVal, String minVal, int incrementBy, boolean isCycled, boolean isOrdered, int cacheSize){
        return "CREATE SEQUENCE " + sequenceName.toUpperCase() +
                "\n\tINCREMENT BY " + incrementBy +
                "\n\tMAXVALUE " + maxVal +
                "\n\tMINVALUE " + minVal +
                "\n\t" + ((isCycled)? "CYCLE": "NOCYCLE") +
                "\n\t" + ((cacheSize==0)? "NOCACHE": ("CACHE " + cacheSize)) +
                "\n\t" + ((isOrdered)? "ORDER": "NOORDER") + ";\n";
    }
    
}
