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
import com.deepsky.database.ora.desc.*;
import com.deepsky.database.DBException;
import com.deepsky.database.ResultSetHelper;
import com.deepsky.database.MappingHelper;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.lang.plsql.parser.WrappedPackageException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.utils.StringUtils;

import java.util.List;
import java.util.ArrayList;
import java.io.Reader;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FunctionHandler implements BaseHandler {

    static LoggerProxy log = LoggerProxy.getInstance("#FunctionHandler");

    final String pkgsSourceListSql =
            "SELECT OWNER, NAME, TEXT " +
                    "FROM ALL_SOURCE " +
                    "WHERE OWNER IN (<OWNERS>) AND TYPE = 'FUNCTION' AND NAME IN (<NAMES>)" +
                    "ORDER BY OWNER ASC, NAME ASC, LINE ASC";


    PlSqlASTParser parser = new PlSqlASTParser();

    public String getId() {
        return DbObject.FUNCTION;
    }

    public List<DbObjectEx> load(ConnectionHolder conn, Mix mix) throws DBException {
        List<DbObjectEx> out = new ArrayList<DbObjectEx>();

        int success = 0;
        int failed = 0;
        long ms = System.currentTimeMillis();
        SourceTemp[] temps = loadFunctionSource(conn, mix.owners, mix.names);
        long ms2 = System.currentTimeMillis();
        int contentSize = 0;

        parser.syntaxErrorToStdout(false);

        log.info(
                "FUNCTION: " + StringUtils.convert2listStrings(mix.names)
                        + " load time (ms): " + (ms2 - ms)
                        + ", file size (Kb): " + ((float) contentSize / 1024));

        //SchemaDefinitionCtx ctx = new SchemaDefinitionCtx(new UserDbObjectLocator(conn.getDbUrl()));

        for (SourceTemp p : temps) {
            String content = p.bld.toString();
            contentSize += content.length();
            FunctionDescriptorImpl desc = null;
            SqlScriptLocator ctx = new DbObjectScriptLocator(conn.getDbUrl(), DbObject.FUNCTION, p.name);
            try {
                ms2 = System.currentTimeMillis();
                desc = parseFunction(ctx, content);
                long ms3 = System.currentTimeMillis();

            } catch (WrappedPackageException e) {
                log.info("Function " + p.name + ", owner: " + p.owner + " wrapped.");
                continue;
            } catch (Error e) {
                desc = new FunctionDescriptorImpl(ctx, p.name.toUpperCase(), TypeFactory.createTypeById(Type.UNKNOWN), false);
                desc.setErrored(true);
                log.warn("Could not parse the function " + p.name + ", owner: " + p.owner + ". Error: " + e.getMessage());
            } catch (Exception e) {
                desc = new FunctionDescriptorImpl(ctx, p.name.toUpperCase(), TypeFactory.createTypeById(Type.UNKNOWN), false);
                desc.setErrored(true);
                log.warn("Could not parse the function " + p.name + ", owner: " + p.owner + ". Error: " + e.getMessage());
            }

            out.add(new DbObjectEx(p.owner, desc, content));
        }

        return out;
    }

    SourceTemp[] loadFunctionSource(ConnectionHolder conn, String[] owners, String[] names) throws DBException {
        String sql = pkgsSourceListSql.replace("<NAMES>", StringUtils.convert2listStrings(names));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(owners));

        ResultSetHelper rsHlp = new ResultSetHelper(conn.getConnection());

        final List<SourceTemp> out = new ArrayList<SourceTemp>();

        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String owner = rs.getString("OWNER");
                    String pkgName = rs.getString("NAME");
                    int index = out.size() - 1;
                    if (index < 0) {
                        out.add(new SourceTemp(owner, pkgName));
                        index = 0;
                    } else if (!out.get(index).name.equalsIgnoreCase(pkgName) ||
                            !out.get(index).owner.equalsIgnoreCase(owner)) {
                        // next package come in
                        out.add(new SourceTemp(owner, pkgName));
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


    FunctionDescriptorImpl parseFunction(SqlScriptLocator ctx, String spec) throws Exception {
        Reader r = new StringReader(spec);

        parser.ignoreSyntaxErrors(true);
        parser.syntaxErrorToStdout(false);

        PlSqlElement[] elems = parser.parseStream(r);

        if (elems.length == 1) {
            if (!(elems[0] instanceof Function)) {
                throw new Exception("Function definition not found");
            } else {
                // it's ok
                Function f = (Function) elems[0];
                FunctionDescriptorImpl fd = new FunctionDescriptorImpl(ctx, f.getEName().toUpperCase(), f.getReturnType(), false);
                for (Argument a : f.getArguments()) {
                    fd.addParameter(
                            a.getType(),
                            a.getArgumentName(),
                            a.getDefaultExpr() != null
                    );
                }

                if(parser.getErrors().length > 0){
                    fd.setErrored(true);
                }

                return fd;
            }
        } else {
            throw new Exception("Function definition could not be parsed");
        }
    }


    public void setListener(ParseEventListener listener) {
    }

    class SourceTemp {
        public String owner;
        public String name;
        public StringBuilder bld;

        public SourceTemp(String owner, String name) {
            this.owner = owner;
            this.name = name;
            this.bld = new StringBuilder();
        }
    }

}
