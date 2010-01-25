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

import com.deepsky.database.ora.*;

import com.deepsky.database.ora.desc.PackageBodyDescriptorImpl;
import com.deepsky.database.DBException;
import com.deepsky.database.ResultSetHelper;
import com.deepsky.database.MappingHelper;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.struct.PackageDescriptor;
import com.deepsky.lang.plsql.struct.SqlScriptLocator;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.PackageBodyDescriptor;
import com.deepsky.lang.plsql.parser.WrappedPackageException;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.PackageBody;
import com.deepsky.utils.StringUtils;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.Reader;
import java.io.StringReader;

public class PackageBodyHandler implements BaseHandler {

    static LoggerProxy log = LoggerProxy.getInstance("#PackageBodyHandler");

    final String pkgsSourceListSql =
            "SELECT s.OWNER, s.NAME, s.TEXT, o.status, s.LINE as LINE\n" +
                    "FROM ALL_SOURCE s, ALL_OBJECTS o\n" +
                    "WHERE s.OWNER IN (<OWNERS>) AND s.TYPE = 'PACKAGE BODY' AND s.NAME IN (<NAMES>)\n" +
                    "and s.OWNER = o.OWNER and o.OBJECT_NAME = s.NAME and s.TYPE = o.OBJECT_TYPE\n" +
                    "ORDER BY s.OWNER ASC, s.NAME ASC, s.LINE ASC";
/*
            "SELECT OWNER, NAME, TEXT " +
                    "FROM ALL_SOURCE " +
                    "WHERE OWNER IN (<OWNERS>) AND TYPE = 'PACKAGE' AND NAME IN (<NAMES>)" +
                    "ORDER BY OWNER ASC, NAME ASC, LINE ASC";
*/


    PlSqlASTParser parser = new PlSqlASTParser();
    ParseEventListener listener;

    public String getId() {
        return DbObject.PACKAGE_BODY;
    }

    public List<DbObjectEx> load(ConnectionHolder conn, Mix mix) throws DBException {

        List<DbObjectEx> out = new ArrayList<DbObjectEx>();

        int success = 0;
        int failed = 0;
        long ms = System.currentTimeMillis();
        SourceTemp[] temps = loadPackageBodies(conn, mix.owners, mix.names);
        long ms2 = System.currentTimeMillis();
        int contentSize = 0;

        parser.syntaxErrorToStdout(false);

        log.info(
                "PACKAGE BODY: " + StringUtils.convert2listStrings(mix.names)
                        + " load time (ms): " + (ms2 - ms)
                        + ", file size (Kb): " + ((float) contentSize / 1024));

        for (SourceTemp p : temps) {
            String content = p.bld.toString();
            contentSize += content.length();
            PackageDescriptor desc = null;

            SqlScriptLocator ctx = new PackageBodyScriptLocator(conn.getDbUrl(), p.name);

            try {
                ms2 = System.currentTimeMillis();
                desc = parsePackageBody(conn.getDbUrl(), content);
                desc.setValid(p.isValid);
                long ms3 = System.currentTimeMillis();

                parsingCompleted(
                        ParseEventListener.EVNT_PARSING_RESULT,
                        ParseEventListener.STATUS_SUCCESSFUL,
                        new ParseEventListener.ParseEvent(p.name, content, getId())
                );
                success++;
            } catch (WrappedPackageException e) {
                log.info("Package body " + p.name + ", owner: " + p.owner + " wrapped.");
                desc = new PackageBodyDescriptorImpl(ctx, p.name);
                desc.setWrapped(true);
//                desc.setPackageSpecSource("-- Package wrapped");
            } catch (Error e) {
                log.warn("Could not parse the package body " + p.name + ", owner: " + p.owner);
                desc = new PackageBodyDescriptorImpl(ctx, p.name);
                desc.setErrored(true);
//                desc.setPackageSpecSource(content);
                parsingCompleted(
                        ParseEventListener.EVNT_PARSING_RESULT,
                        ParseEventListener.STATUS_FAILED,
                        new ParseEventListener.ParseEvent(p.name, content, getId())
                );
            } catch (Exception e) {
                desc = new PackageBodyDescriptorImpl(ctx, p.name);
                desc.setErrored(true);
//                desc.setPackageSpecSource("");
                parsingCompleted(
                        ParseEventListener.EVNT_PARSING_RESULT,
                        ParseEventListener.STATUS_FAILED,
                        new ParseEventListener.ParseEvent(p.name, content, getId())
                );
            }
            out.add(new DbObjectEx(p.owner, desc, content));
        }

        return out;
    }

    private void parsingCompleted(int evnt, int status, ParseEventListener.ParseEvent evObject) {
        if(listener != null){
            listener.handleEvent(evnt, status, evObject);
        }
    }

    public void setListener(ParseEventListener listener) {
        this.listener = listener;
    }


    private SourceTemp[] loadPackageBodies(ConnectionHolder conn, String[] owners, String[] packageNames) throws DBException {
        String sql = pkgsSourceListSql.replace("<NAMES>", StringUtils.convert2listStrings(packageNames));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(owners));

        ResultSetHelper rsHlp = new ResultSetHelper(conn.getConnection());

        final List<SourceTemp> out = new ArrayList<SourceTemp>();

        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String owner = rs.getString("OWNER");
                    String pkgName = rs.getString("NAME");
                    String status = rs.getString("STATUS");
                    int lineNbr = rs.getInt("LINE");
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
                    if(lineNbr == 1){
                        if( line.toUpperCase().startsWith("PACKAGE")){
                            // prefix the line with "CREATE OR REPLACE"
                            line = "CREATE OR REPLACE " + line; 
                        }
                    }
                    bldr.append(line);
                }
            });

            return out.toArray(new SourceTemp[out.size()]);
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    PackageBodyDescriptor parsePackageBody(DbUrl url, String spec) throws Exception {
        Reader r = new StringReader(spec);
        PlSqlElement[] elems = parser.parseStream(r);

        if (elems.length == 1) {
            if (!(elems[0] instanceof PackageBody)) {
                throw new Exception("Package body not found");
            } else {
                ; // it's ok
            }
        } else {
            throw new Exception("Package body could not be parsed");
        }

        PackageBody pspec = (PackageBody) elems[0];
        PackageBodyDescriptor pkgDesc = pspec.describe();
        pkgDesc.setLocator(new PackageBodyScriptLocator(url, pkgDesc.getName()));

        return pkgDesc;
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
