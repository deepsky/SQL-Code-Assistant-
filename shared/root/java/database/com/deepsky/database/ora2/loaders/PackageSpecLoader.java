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
import com.deepsky.database.ora2.DbObjectLoader;
import com.deepsky.database.ora2.DbObjectSpec;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageSpecLoader extends DbObjectLoaderAbstract {

    static LoggerProxy log = LoggerProxy.getInstance("#PackageSpecLoader");

    final String pkgsSourceListSql =
            "SELECT s.OWNER, s.NAME, s.TEXT, o.status\n" +
                    "FROM ALL_SOURCE s, ALL_OBJECTS o\n" +
                    "WHERE s.OWNER IN (<OWNERS>) AND s.TYPE = 'PACKAGE' AND s.NAME IN (<NAMES>)\n" +
                    "and s.OWNER = o.OWNER and o.OBJECT_NAME = s.NAME and s.TYPE = o.OBJECT_TYPE\n" +
                    "ORDER BY s.OWNER ASC, s.NAME ASC, s.LINE ASC";
    

    public String getId() {
        return DbObject.PACKAGE;
    }

    public List<DbObjectSpec> load(Connection conn, Mix mix) throws DBException {

        long ms = System.currentTimeMillis();
        SourceTemp[] temps = loadPackageSpecs(conn, mix.owners, mix.names);
        long ms2 = System.currentTimeMillis();
        int contentSize = 0;

        final List<DbObjectSpec> out = new ArrayList<DbObjectSpec>();

        for(SourceTemp s: temps){
            String text = s.bld.toString();
            out.add(new DbObjectSpec(
                                DbObject.PACKAGE,
                                s.name,
                                text
                            ));
            contentSize += text.length();
        }

        log.info(
                "PACKAGE: " + StringUtils.convert2listStrings(mix.names)
                        + " load time (ms): " + (ms2 - ms)
                        + ", file size (Kb): " + ((float) contentSize / 1024));
        return out;
    }


    private SourceTemp[] loadPackageSpecs(Connection conn, String[] owners, String[] packageNames) throws DBException {
        String sql = pkgsSourceListSql.replace("<NAMES>", StringUtils.convert2listStrings(packageNames));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(owners));

        ResultSetHelper rsHlp = new ResultSetHelper(conn);

        final List<SourceTemp> out = new ArrayList<SourceTemp>();

        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String owner = rs.getString("OWNER");
                    String pkgName = rs.getString("NAME");
                    String status = rs.getString("STATUS");
                    int index = out.size() - 1;
                    if (index < 0) {
                        out.add(new SourceTemp(owner, pkgName, "valid".equalsIgnoreCase(status)));
                        index = 0;
                    } else if (!out.get(index).name.equalsIgnoreCase(pkgName) ||
                            !out.get(index).owner.equalsIgnoreCase(owner)) {
                        // next package come in
                        out.add(new SourceTemp(owner, pkgName, "valid".equalsIgnoreCase(status)));
                        index++;
                    } else {

                    }

                    StringBuilder bldr = out.get(index).bld;
                    String line = rs.getString("TEXT");
                    bldr.append(line);
                }
            });

            return out.toArray(new SourceTemp[out.size()]);
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }


    class SourceTemp {
        public String owner;
        public String name;
        public StringBuilder bld;
        boolean isValid;

        public SourceTemp(String owner, String name, boolean isValid) {
            this.owner = owner;
            this.name = name;
            this.bld = new StringBuilder();
            this.isValid = isValid;
        }
    }

}
