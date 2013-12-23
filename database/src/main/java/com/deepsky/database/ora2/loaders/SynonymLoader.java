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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SynonymLoader extends DbObjectLoaderAbstract {

    static LoggerProxy log = LoggerProxy.getInstance("#SynonymHandler");

    /*
        Use the CREATE SYNONYM statement to create a synonym, which is an alternative
        name for a table, view, sequence, procedure, stored function, package, materialized view,
        Java class schema object, user-defined object type, or another synonym.

        CREATE PUBLIC SYNONYM customers FOR oe.customers;

        "oe" is a schema

        'PUBLIC' will create a public synonym, accessible to all users (with the appropriate privileges.)
        Unlike Views, Synonyms do not need to be recompiled when the underlying table is redefined.

        Oracle will resolve object names in the following order:

            current user
            private synonym
            public synonym
     */


/*
    final String listSynonymSql =
            "SELECT \n" +
            "s.TABLE_OWNER AS OWNER, s.TABLE_NAME, s.SYNONYM_NAME, o.object_type AS TYPE\n" +
            "FROM ALL_SYNONYMS s, all_objects o\n" +
            "WHERE s.TABLE_OWNER IN (<OWNERS>) AND s.SYNONYM_NAME IN (<NAMES>) AND s.TABLE_OWNER = o.OWNER\n" +
            "and s.table_name = o.object_name\n" +
            "and o.object_type in ('TABLE', 'VIEW')";

    final String listSynonymSql =
            "SELECT syns.OBJECT_NAME, syns.OWNER, syns.OBJECT_TYPE, syns.STATUS, syns.CREATED, syns.LAST_DDL_TIME, syns.TIMESTAMP, " +
                    "   t.OBJECT_TYPE AS REF_OBJECT_TYPE\n" +
                    "FROM ALL_OBJECTS syns, ALL_OBJECTS t, ALL_SYNONYMS lnk  \n" +
                    "WHERE syns.OBJECT_TYPE = 'SYNONYM' \n" +
                    "AND syns.OWNER = 'PUBLIC'\n" +
                    "AND t.OBJECT_TYPE in ('TABLE', 'VIEW', 'SEQUENCE')\n" +
//                    "AND t.OWNER = 'SYS' and t.OBJECT_TYPE in ('TABLE', 'VIEW', 'SEQUENCE')\n" +
                    "AND lnk.SYNONYM_NAME = syns.OBJECT_NAME AND lnk.TABLE_NAME = t.OBJECT_NAME";
*/


    final String listSynonymSql =
            "select lnk.OWNER AS SYNONYM_OWNER, lnk.SYNONYM_NAME, lnk.TABLE_OWNER AS OBJ_OWNER, lnk.TABLE_NAME AS OBJ_NAME\n" +
            "from ALL_SYNONYMS lnk, ALL_OBJECTS t\n" +
            "WHERE\n" +
            "lnk.TABLE_NAME = t.OBJECT_NAME\n" +
            "AND lnk.TABLE_OWNER = t.OWNER\n" +
            "AND UPPER(lnk.SYNONYM_NAME) in (<NAMES>)\n" +         
            "AND t.OBJECT_TYPE in ('TABLE', 'VIEW', 'SEQUENCE', 'PACKAGE', 'PROCEDURE', 'FUNCTION')\n" +
            "AND lnk.owner in (<OWNERS>)";
//            "and (lnk.table_owner IN (<OWNERS>) or lnk.owner = 'PUBLIC')";


    public String getId() {
        return DbObject.SYNONYM;
    }

    public List<DbObjectSpec> load(final Connection conn, Mix mix) throws DBException {
        String sql = listSynonymSql.replace("<NAMES>", StringUtils.convert2listStrings(mix.names));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(mix.owners));

        ResultSetHelper rsHlp = new ResultSetHelper(conn);

        // load list of SYNONYMS
        log.info( "SYNONYMS: " + StringUtils.convert2listStrings(mix.names));

        final List<DbObjectSpec> out = new ArrayList<DbObjectSpec>();
        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String obj_owner = rs.getString("OBJ_OWNER");
                    String syn_owner = rs.getString("SYNONYM_OWNER");
                    String referenced_name = rs.getString("OBJ_NAME");
                    String synonym = rs.getString("SYNONYM_NAME");
                    boolean isPublic = "PUBLIC".equals(syn_owner);

                    out.add(new DbObjectSpec(
                                DbObject.SYNONYM,
                                synonym,
                                createScript( synonym, syn_owner, obj_owner, referenced_name)
                            )
                    );
                }
            });

            return out;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }



    // CREATE PUBLIC SYNONYM customers FOR oe.customers;
    static public  String createScript(String synonymName, String syn_owner, String objSchema, String objectName){
        StringBuilder b = new StringBuilder();
        boolean isPublic = "PUBLIC".equals(syn_owner);
        b.append("CREATE OR REPLACE").append(isPublic?" PUBLIC SYNONYM ":" SYNONYM ");
        if(!isPublic){
            b.append(syn_owner).append(".");
        }
        b.append(synonymName.toUpperCase());
        
        b.append(" FOR ").append(objSchema==null?"":objSchema.toUpperCase()+".").append(objectName.toUpperCase());
        b.append(";");
        return b.toString();
    }

}
