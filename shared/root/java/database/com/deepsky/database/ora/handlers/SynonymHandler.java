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

import com.deepsky.database.cache.Cache;
import com.deepsky.database.ora.*;
import com.deepsky.database.ora.desc.*;
import com.deepsky.database.DBException;
import com.deepsky.database.ResultSetHelper;
import com.deepsky.database.MappingHelper;
import com.deepsky.utils.StringUtils;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SynonymHandler implements BaseHandler {

    static LoggerProxy log = LoggerProxy.getInstance("#SynonymHandler");

//    final String listSynonymSql =
//            "SELECT TABLE_OWNER AS OWNER, TABLE_NAME, SYNONYM_NAME " +
//            "FROM ALL_SYNONYMS " +
//            "WHERE TABLE_OWNER IN (<OWNERS>) AND SYNONYM_NAME IN (<NAMES>)";
    final String listSynonymSql =
            "SELECT \n" +
            "s.TABLE_OWNER AS OWNER, s.TABLE_NAME, s.SYNONYM_NAME, o.object_type AS TYPE\n" +
            "FROM ALL_SYNONYMS s, all_objects o\n" +
            "WHERE s.TABLE_OWNER IN (<OWNERS>) AND s.SYNONYM_NAME IN (<NAMES>) AND s.TABLE_OWNER = o.OWNER\n" +
            "and s.table_name = o.object_name\n" +
            "and o.object_type in ('TABLE', 'VIEW')";

//    final String listRefencedObjectsSql =
//            "SELECT c.OWNER, c.TABLE_NAME, c.COLUMN_NAME, c.DATA_TYPE, c.DATA_LENGTH, c.NULLABLE, c.COLUMN_ID, t.OBJECT_TYPE, lnk.SYNONYM_NAME\n" +
//            "FROM ALL_TAB_COLUMNS c, ALL_OBJECTS t, ALL_SYNONYMS lnk\n" +
//            "WHERE c.OWNER IN (<OWNERS>) AND lnk.SYNONYM_NAME IN (<NAMES>)\n" +
//            "AND c.OWNER = t.OWNER AND c.TABLE_NAME = t.OBJECT_NAME\n" +
//            "AND lnk.TABLE_NAME = c.TABLE_NAME \n" +
//            "ORDER BY c.OWNER, c.TABLE_NAME, c.COLUMN_ID";

    public String getId() {
        return DbObject.SYNONYM;
    }


    public List<DbObjectEx> load(final ConnectionHolder conn, Mix mix) throws DBException {
        String sql = listSynonymSql.replace("<NAMES>", StringUtils.convert2listStrings(mix.names));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(mix.owners));

        ResultSetHelper rsHlp = new ResultSetHelper(conn.getConnection());

        // load list of SYNONYMS
        log.info( "SYNONYMS: " + StringUtils.convert2listStrings(mix.names));

        final List<DbObjectEx> out = new ArrayList<DbObjectEx>();
        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String owner = rs.getString("OWNER");
                    String referenced_name = rs.getString("TABLE_NAME");
                    String referenced_type = rs.getString("TYPE");
                    String synonym = rs.getString("SYNONYM_NAME");

                    out.add(new DbObjectEx(owner,
                            new SynonymDescriptorImpl(synonym, referenced_name, referenced_type),
                            null)
                    );
                }
            });

            return out;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

    }

/*
    public List<DbObjectEx> load(final ConnectionHolder conn, Mix mix) throws DBException {
        String sql = listSynonymSql.replace("<NAMES>", StringUtils.convert2listStrings(mix.names));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(mix.owners));

        ResultSetHelper rsHlp = new ResultSetHelper(conn.getConnection());

        // load list of SYNONYMS
        log.info( "SYNONYMS: " + StringUtils.convert2listStrings(mix.names));

//        final List<DbObjectEx> out = new ArrayList<DbObjectEx>();
        final List<SynonymDesc> out = new ArrayList<SynonymDesc>();
        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String owner = rs.getString("OWNER");
                    String name = rs.getString("TABLE_NAME");
                    String synonym = rs.getString("SYNONYM_NAME");

                    out.add(new SynonymDesc(owner, name, synonym));
                }
            });

//            return out;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        // fragment the list of synonym names and load descriptions of referenced objcets
        List<DbObjectEx> out2 = new ArrayList<DbObjectEx>();
        List<String> synonyms = new ArrayList<String>();
        String last_owner = null;
        for(SynonymDesc syn: out){
            if(last_owner == null){
                last_owner = syn.owner;
            }

            if(syn.owner.equalsIgnoreCase(last_owner)){
                synonyms.add(syn.synonymName);
            } else {
                List<DbObjectEx> _out2 = loadDefinitionsForSynonyms(conn, last_owner, synonyms);
                out2.addAll(_out2);

                last_owner = syn.owner;
                synonyms.clear();
                synonyms.add(syn.synonymName);
            }

            if(synonyms.size() >= 20){
                List<DbObjectEx> _out2 = loadDefinitionsForSynonyms(conn, last_owner, synonyms);
                out2.addAll(_out2);
                synonyms.clear();
            }
        }

        if(synonyms.size() > 0){
            List<DbObjectEx> _out2 = loadDefinitionsForSynonyms(conn, last_owner, synonyms);
            out2.addAll(_out2);
            synonyms.clear();
        }

         return out2;
    }

    private List<DbObjectEx> loadDefinitionsForSynonyms(final ConnectionHolder conn, String owner, List<String> synonyms) throws DBException {
        String sql2= listRefencedObjectsSql.replace("<NAMES>", StringUtils.convert2listStrings(synonyms));
        sql2 = sql2.replace("<OWNERS>", "'" + owner + "'");

        ResultSetHelper rsHlp = new ResultSetHelper(conn.getConnection());

        final Map<String, DbObjectEx> tables = new HashMap<String, DbObjectEx>();
        try {
            rsHlp.populateFromResultSet(sql2, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String owner = rs.getString("OWNER");
                    String name = rs.getString("TABLE_NAME");
                    String column = rs.getString("COLUMN_NAME");
                    String data_type = rs.getString("DATA_TYPE");
                    int data_len = rs.getInt("DATA_LENGTH");
                    String nullable = rs.getString("NULLABLE");
                    String synonymName = rs.getString("SYNONYM_NAME");
                    long columnId = rs.getLong("COLUMN_ID");
                    String object_type = rs.getString("OBJECT_TYPE");

                    DbObjectEx dbo = tables.get(name);
                    if (dbo == null) {
                        if("VIEW".equalsIgnoreCase(object_type)){
                            dbo = new DbObjectEx(owner, new SynonymOfViewDesc(new UserDbObjectLocator(conn.getDbUrl()), synonymName, name));
                            tables.put(name, dbo);
                        } else if("TABLE".equalsIgnoreCase(object_type)){
                            dbo = new DbObjectEx(owner, new SynonymOfTableDesc(new UserDbObjectLocator(conn.getDbUrl()), synonymName, name));
                            tables.put(name, dbo);
                        } else {
                            // todo - looks strange!
                            return;
                        }
                    }

                    if(dbo.dbo instanceof SynonymOfViewDesc){
                        ((SynonymOfViewDesc)dbo.dbo).addColumn(column,
                            TypeFactory.createTypeByName(data_type, data_len), "Y".equals(nullable), (int) columnId);
                    } else {
                        ((SynonymOfTableDesc)dbo.dbo).addColumn(column,
                            TypeFactory.createTypeByName(data_type, data_len), "Y".equals(nullable), (int) columnId);
                    }
                }
            });

            List<DbObjectEx> out2 = new ArrayList<DbObjectEx>();
            for (DbObjectEx ex : tables.values()) {
                out2.add(ex);
            }

            return out2;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }



    class SynonymDesc {
        String owner;
        String referencedName;
        String synonymName;

        public SynonymDesc(String owner, String referencedName, String synonymName){
            this.owner = owner;
            this.referencedName = referencedName;
            this.synonymName = synonymName;
        }
    }
*/

    public void setListener(ParseEventListener listener) {
    }

    public boolean finalUpdate(DbObjectEx dex, Cache cache) {
        return false;
    }
}
