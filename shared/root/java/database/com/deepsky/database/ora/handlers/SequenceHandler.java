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
import com.deepsky.database.ora.desc.SequenceDescriptorImpl;
import com.deepsky.utils.StringUtils;
import com.deepsky.database.ResultSetHelper;
import com.deepsky.database.DBException;
import com.deepsky.database.MappingHelper;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class SequenceHandler implements BaseHandler {

    final String listSeqSql =
            "SELECT OWNER, OBJECT_NAME, TIMESTAMP " +
                    "FROM ALL_OBJECTS " +
                    "WHERE OWNER IN (<OWNERS>) AND OBJECT_NAME IN (<NAMES>) AND OBJECT_TYPE = 'SEQUENCE' " +
                    "ORDER BY OWNER ASC";

    ParseEventListener listener;

    public String getId() {
        return "SEQUENCE";
    }

    public List<DbObjectEx> load(ConnectionHolder conn, Mix mix) throws DBException {
        String sql = listSeqSql.replace("<NAMES>", StringUtils.convert2listStrings(mix.names));
        sql = sql.replace("<OWNERS>", StringUtils.convert2listStrings(mix.owners));

        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        final Map<String, DbObjectEx> seqs = new HashMap<String, DbObjectEx>();
        ResultSetHelper rsHlp = new ResultSetHelper(conn.getConnection());

        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String owner = rs.getString("OWNER");
                    String name = rs.getString("OBJECT_NAME");
                    Date lastDDLTime = null;
                    try {
                        lastDDLTime = new Date(fmt.parse(rs.getString("TIMESTAMP")).getTime());
                    } catch (ParseException e) {
                    }

                    DbObjectEx dbo = seqs.get(name);
                    if (dbo == null) {
                        dbo = new DbObjectEx(owner, new SequenceDescriptorImpl(name), null);
                        seqs.put(name, dbo);
                    }
                    SequenceDescriptorImpl seq = (SequenceDescriptorImpl) dbo.dbo;
                    seq.setLastDDLTime(lastDDLTime);
                }
            });

            List<DbObjectEx> out = new ArrayList<DbObjectEx>();
            for (DbObjectEx ex : seqs.values()) {
                out.add(ex);
            }

            return out;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }


    public void setListener(ParseEventListener listener) {
        this.listener = listener;
    }

    public boolean finalUpdate(DbObjectEx dex, Cache cache) {
        return false;
    }
}
