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

import com.deepsky.database.DBException;
import com.deepsky.database.ResultSetHelper;
import com.deepsky.database.MappingHelper;
import com.deepsky.database.cache.Cache;
import com.deepsky.utils.StringUtils;
import com.deepsky.database.ora.*;
import com.deepsky.database.ora.desc.TableCollectionDescriptorImpl;
import com.deepsky.database.ora.desc.ObjectTypeDescriptorImpl;
import com.deepsky.database.ora.desc.VarrayCollectionDescriptorImpl;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.parser.WrappedPackageException;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.lang.plsql.psi.*;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.Reader;
import java.io.StringReader;

public class TypeHandler implements BaseHandler {

    static LoggerProxy log = LoggerProxy.getInstance("#TypeHandler");

    final String typeSourceListSql =
            "SELECT OWNER, NAME, TEXT " +
                    "FROM ALL_SOURCE " +
                    "WHERE OWNER IN (<OWNERS>) AND TYPE = 'TYPE' AND NAME IN (<NAMES>)" +
                    "ORDER BY OWNER ASC, NAME ASC, LINE ASC";

    PlSqlASTParser parser = new PlSqlASTParser();
    ParseEventListener listener;

    SourceTemp[] loadTypeDefSource(ConnectionHolder conn, String[] owners, String[] typeNames) throws DBException {
        String sql = typeSourceListSql.replace("<NAMES>", StringUtils.convert2listStrings(typeNames));
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

    public String getId() {
        return "TYPE";
    }

    public List<DbObjectEx> load(ConnectionHolder conn, Mix mix) throws DBException {

        List<DbObjectEx> out = new ArrayList<DbObjectEx>();

        SourceTemp[] temps = loadTypeDefSource(conn, mix.owners, mix.names);
        int failed = 0;
        log.info("TYPE: " + StringUtils.convert2listStrings(mix.names));
        for (SourceTemp p : temps) {
            String content = p.bld.toString();
            try {
                UserDefinedTypeDescriptor desc = parseTypeDefinition(content, conn.getDbUrl());
                out.add(new DbObjectEx(p.owner, desc, content));
                parsingCompleted(
                        ParseEventListener.EVNT_PARSING_RESULT,
                        ParseEventListener.STATUS_SUCCESSFUL,
                        new ParseEventListener.ParseEvent(p.name, content, getId())
                );
            } catch (WrappedPackageException e) {
                failed++;
            } catch (Error e) {
                failed++;
                parsingCompleted(
                        ParseEventListener.EVNT_PARSING_RESULT,
                        ParseEventListener.STATUS_FAILED,
                        new ParseEventListener.ParseEvent(p.name, content, getId())
                );
                log.info("[Error] Could not parse TYPE: " + p.name);
            } catch (Exception e) {
                failed++;
                parsingCompleted(
                        ParseEventListener.EVNT_PARSING_RESULT,
                        ParseEventListener.STATUS_FAILED,
                        new ParseEventListener.ParseEvent(p.name, content, getId())
                );
                log.info("[Error] Could not parse TYPE: " + p.name);
            }
        }
        return out;
    }

    UserDefinedTypeDescriptor parseTypeDefinition(String src, DbUrl url) throws Exception {
        Reader r = new StringReader(src);
        PlSqlElement[] elems = parser.parseStream(r);

//        SchemaDefinitionCtx ctx = new SchemaDefinitionCtx(new UserDbObjectLocator(url));

        if (elems.length == 1) {
            if (elems[0] instanceof ObjectTypeDecl) {
                ObjectTypeDecl odecl = (ObjectTypeDecl) elems[0];
                SqlScriptLocator url2 = new DbObjectScriptLocator(url, DbObject.TYPE, odecl.getDeclName());
                ObjectTypeDescriptorImpl rtype = new ObjectTypeDescriptorImpl(//ctx,
                        url2, odecl.getDeclName().toUpperCase());
                for(RecordTypeItem item: odecl.getItems()){
                    rtype.addRecordItem(item.getRecordItemName(), item.getType());
                    // TODO - requires resolving of the real type in case of %TYPE and %ROWTYPE
                    // ...
                }

                return rtype;
            } else if (elems[0] instanceof TableCollectionDecl){
                TableCollectionDecl tdecl = (TableCollectionDecl) elems[0];
                SqlScriptLocator url2 = new DbObjectScriptLocator(url, DbObject.TYPE, tdecl.getDeclName());
                TableCollectionDescriptorImpl tcoll = new TableCollectionDescriptorImpl(//ctx,
                        url2, tdecl.getDeclName().toUpperCase(), tdecl.getBaseType());

                return tcoll;
            } else if (elems[0] instanceof VarrayCollectionDecl){
                VarrayCollectionDecl tdecl = (VarrayCollectionDecl)elems[0];
                SqlScriptLocator url2 = new DbObjectScriptLocator(url, DbObject.TYPE, tdecl.getDeclName());
                VarrayCollectionDescriptor tcoll = new VarrayCollectionDescriptorImpl(
                        url2, tdecl.getDeclName().toUpperCase(), tdecl.getBaseType());

                return tcoll;
            } else {
                throw new Exception("Type specification not supported: " + elems[0].getClass().getSimpleName());
            }
        } else {
            throw new Exception("Type specification could not be parsed");
        }
    }

    private void parsingCompleted(int evnt, int status, ParseEventListener.ParseEvent evObject) {
        if(listener != null){
            listener.handleEvent(evnt, status, evObject);
        }
    }

    public void setListener(ParseEventListener listener) {
        this.listener = listener;
    }

    public boolean finalUpdate(DbObjectEx dex, Cache cache) {
        return false;
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
