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

package com.deepsky.database.ora2;

import com.deepsky.database.DBException;
import com.deepsky.database.MappingHelper;
import com.deepsky.database.ResultSetHelper;
import com.deepsky.database.ora.*;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DbCacheManager {

    static LoggerProxy log = LoggerProxy.getInstance("#DbObjectManager");

    final String listObjectsSql =
            "SELECT OWNER, OBJECT_NAME, OBJECT_TYPE, STATUS, CREATED, LAST_DDL_TIME, TIMESTAMP " +
                    "FROM ALL_OBJECTS\n" +
                    "WHERE OBJECT_TYPE IN ( <OBJECT_TYPES> )\n" +
                    "AND SUBOBJECT_NAME IS NULL AND OWNER = '<USER>' " +
                    "AND NOT (GENERATED = 'Y'\n" +
                    "   OR (OBJECT_NAME LIKE 'BIN$%' AND LENGTH(OBJECT_NAME) = 30)\n" +
                    "   OR OBJECT_NAME LIKE 'SYS_YOID%')";

    final String listSynonymsSql =
            "select lnk.OWNER AS SYNONYM_OWNER, lnk.SYNONYM_NAME AS SYNONYM_NAME, t.TIMESTAMP AS TIMESTAMP, lnk.TABLE_OWNER, lnk.TABLE_NAME\n" +
                    "from ALL_SYNONYMS lnk, ALL_OBJECTS t\n" +
                    "WHERE\n" +
                    "lnk.TABLE_NAME = t.OBJECT_NAME\n" +
                    "AND lnk.TABLE_OWNER = t.OWNER\n" +
                    "AND t.OBJECT_TYPE in ('TABLE', 'VIEW', 'SEQUENCE', 'PACKAGE', 'PROCEDURE', 'FUNCTION')\n" +
//                    "AND lnk.owner in ('PUBLIC', '<USER>')";
// TODO -- private VS public synonyms
                    "AND lnk.owner in ('<USER>')";
//            "and (lnk.table_owner = '<USER>' or lnk.owner = 'PUBLIC')";


    final private String _listUserObjectsSql =
            "SELECT OBJECT_NAME, OBJECT_TYPE, STATUS, CREATED, LAST_DDL_TIME, TIMESTAMP " +
                    "FROM USER_OBJECTS a " +
                    "WHERE OBJECT_TYPE IN ( <OBJECT_TYPES> ) " +
//                    "AND GENERATED = 'N' AND OBJECT_NAME NOT LIKE 'SYS_YOID%'\n" +
                    "AND NOT (GENERATED = 'Y'\n" +
                    "   OR (OBJECT_NAME LIKE 'BIN$%' AND LENGTH(OBJECT_NAME) = 30)\n" +
                    "   OR OBJECT_NAME LIKE 'SYS_YOID%')\n" +
                    "<CHECK_RECYCLEBIN> \n" +
                    "ORDER BY OBJECT_TYPE";

    final private String _listUpdatesSql =
            "select\n" +
                    "   object_type,\n" +
                    "   to_char(MAX_TIMESTAMP, 'yyyy-mm-dd:hh24:mi:ss') AS TIMESTAMP,\n" +
                    "   NBR_ELEMENTS\n" +
                    "from (\n" +
                    "   select \n" +
                    "       object_type,\n" +
                    "       MAX(to_date(TIMESTAMP, 'yyyy-mm-dd:hh24:mi:ss')) as MAX_TIMESTAMP,\n" +
                    "       count(*) as NBR_ELEMENTS\n" +
                    "   from all_objects a\n" +
                    "       where owner IN (<OWNERS>)\n" +
                    "       and object_type in ( <OBJECT_TYPES> )\n" +
//                    "       and object_type in ('PACKAGE', 'PACKAGE BODY', 'SEQUENCE', 'TYPE', 'TABLE', 'VIEW', 'PROCEDURE', 'FUNCTION', 'TRIGGER', 'SYNONYM')\n" +
//                    "       AND GENERATED = 'N' AND OBJECT_NAME NOT LIKE 'SYS_YOID%'\n" +
                    "       AND NOT (GENERATED = 'Y'\n" +
                    "           OR (OBJECT_NAME LIKE 'BIN$%' AND LENGTH(OBJECT_NAME) = 30)\n" +
                    "           OR OBJECT_NAME LIKE 'SYS_YOID%')\n" +
                    "   <CHECK_RECYCLEBIN> \n" +
                    "   group by object_type\n" +
                    "   ) ";

    final private String recyleBinSubquery =
            "   and not exists (select * from recyclebin b where b.object_name = a.object_name\n" +
                    "       and b.type = a.object_type)";

    private String listUpdatesSql, listUserObjectsSql;


    final Object synch = new Object();
    DbObjectLoader currentLoader = null;

    ConnectionHolder connHolder;
    Connection conn;
    String user;
    boolean cancelUpdate = false;

    String databaseVersionShort;

    public DbCacheManager(ConnectionHolder connHolder, String user) throws DBException {
        this.conn = connHolder.getConnection();
        this.connHolder = connHolder;
        this.user = user.toUpperCase();

        databaseVersionShort = connHolder.getDatabaseVersionShort();

        // tune SQL query according to database version
        if ("9".equals(databaseVersionShort)) {
            listUserObjectsSql = _listUserObjectsSql.replace("<CHECK_RECYCLEBIN>", "");
            listUpdatesSql = _listUpdatesSql.replace("<CHECK_RECYCLEBIN>", "");
        } else {
            // 10 and higher
            listUserObjectsSql = _listUserObjectsSql.replace("<CHECK_RECYCLEBIN>", recyleBinSubquery);
            listUpdatesSql = _listUpdatesSql.replace("<CHECK_RECYCLEBIN>", recyleBinSubquery);
        }

    }

    public DbCacheManager(ConnectionHolder connHolder) throws DBException {
        this(connHolder, connHolder.getDbUrl().getUser());
    }

    public void cancelUpdate() {
        cancelUpdate = true;

        if(currentLoader != null){
            try {
                currentLoader.cancelLoading();
            } catch(Throwable e){
                // actually we expect NullPointerException if currentLoader has done its job
            }
        }
    }


    public String[] getTypesNeedInUpdate(final DbObjectCache _ddata, String[] typesToCheck) throws ClosedConnectionException, DBException {
        String sql = listUpdatesSql.replace("<OWNERS>", StringUtils.convert2listStrings(new String[]{user}));
        sql = sql.replace("<OBJECT_TYPES>", StringUtils.convert2listStrings(typesToCheck));
        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

        // adopt SQL query for version 9
        if ("9".equals(databaseVersionShort)) {
            sql = sql.replace("<CHECK_RECYCLEBIN>", "");
        } else {
            // 10 and higher
            sql = sql.replace("<CHECK_RECYCLEBIN>", recyleBinSubquery);
        }

        final Set<String> changed = new HashSet<String>();
        final Map<String, ValueHelper> typeLastDDLTime = new HashMap<String, ValueHelper>();

        try {
            ResultSetHelper rsHlp = new ResultSetHelper(conn);

            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String object_type = rs.getString("object_type");
                    int nbr = rs.getInt("NBR_ELEMENTS");
                    Date timestamp = null;
                    try {
                        timestamp = new Date(fmt.parse(rs.getString("TIMESTAMP")).getTime());
                        typeLastDDLTime.put(object_type, new ValueHelper(timestamp, nbr));
                    } catch (ParseException e) {
                    }
                }
            });

            // check add/updated objects
            for (Map.Entry<String, ValueHelper> e : typeLastDDLTime.entrySet()) {
                Date d = _ddata != null ? _ddata.lastDDLTimeForType(e.getKey()) : null;
                if (d == null || d.before(e.getValue().date)) {
                    // some object were updated
                    changed.add(e.getKey());
                }
            }

            // check deleted objects
            if (_ddata != null) {
                for (String type : typesToCheck) {
                    int nbr = _ddata.getNbrObjectForType(type);
                    ValueHelper v = typeLastDDLTime.get(type);
                    if(v == null && nbr>0){
                        // one or more objectes in the database were deleted
                        changed.add(type);
                    } else if(v != null &&  v.nbr != nbr){
                        // one or more objectes in the database were deleted
                        changed.add(type);
                    }
                }
            }

            if (changed.size() > 0) {

                // NOTE: check dependent objects PACKAGE -> PACKAGE BODY
                // if PACKAGE was chnages add PACKAGE BODY excplicitly
                // todo -- not correct
//                if(changed.contains(DbObject.PACKAGE)){
//                    if(!changed.contains("PACKAGE BODY")){
//                        changed.add("PACKAGE BODY");
//                    }
//                }
                String[] out = changed.toArray(new String[changed.size()]);
                // changes in the schema objects detected
                log.info("Objects needed in updating: " + StringUtils.convert2listStrings(out));
                return out;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 17008 || e.getErrorCode() == 17002) {
                throw new ClosedConnectionException();
            }
            log.warn("Error on pulling of the schema changes: " + e.getMessage());
            return new String[0];
        } catch (Error e) {
            // changes were detected
            log.warn("Error on pulling of the schema changes, ignore: " + e.getMessage());
            return new String[0];
        }

        return new String[0];
    }


    public Map<String, ItemToUpdate> createUserObjList(
            final DbObjectCache _ddata,String[] types ) throws DBException {

        // get list of objects updated since the last session
        final Map<String, ItemToUpdate> items2Update = new HashMap<String, ItemToUpdate>();
        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String sql = listObjectsSql.replace("<OBJECT_TYPES>", StringUtils.convert2listStrings(types));
        sql = sql.replace("<USER>", user);

        final int[] counters = new int[18];
        ResultSetHelper rsHlp = new ResultSetHelper(conn);

        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String type = rs.getString("OBJECT_TYPE");
                    String name = rs.getString("OBJECT_NAME");
                    String status = rs.getString("STATUS");
                    boolean isValid = "VALID".equals(status);
                    Date lastDDLTime = null;
                    try {
                        lastDDLTime = new Date(fmt.parse(rs.getString("TIMESTAMP")).getTime());
                    } catch (ParseException e) {
                        lastDDLTime = new Date(0);
                    }

                    ItemToUpdate item = null;
                    if (DbObject.PACKAGE.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.PACKAGE, name);
                        if (d == null || lastDDLTime.after(d)) { //pack.getLastDDLTime())) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[0]++;
                        } else {
                            // package definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[1]++;
                        }

                    } else if (DbObject.PACKAGE_BODY.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.PACKAGE_BODY, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
//                            counters[2]++;
                        } else {
                            // table definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
//                            counters[3]++;
                        }

                    } else if (DbObject.TABLE.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.TABLE, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[2]++;
                        } else {
                            // table definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[3]++;
                        }
                    } else if (DbObject.SEQUENCE.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.SEQUENCE, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[4]++;
                        } else {
                            // sequence definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[5]++;
                        }
                    } else if (DbObject.TYPE.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.TYPE, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[6]++;
                        } else {
                            // sequence definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[7]++;
                        }
                    } else if (DbObject.VIEW.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.VIEW, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[8]++;
                        } else {
                            // sequence definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[9]++;
                        }
                    } else if (DbObject.FUNCTION.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.FUNCTION, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[10]++;
                        } else {
                            // sequence definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[11]++;
                        }
                    } else if (DbObject.PROCEDURE.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.PROCEDURE, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[12]++;
                        } else {
                            // sequence definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[13]++;
                        }
                    } else if (DbObject.TRIGGER.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.TRIGGER, name);
                        if (d == null || lastDDLTime.after(d)) {
                            // definition is missed or obsoleted
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[14]++;
                        } else {
                            // sequence definition is up to date
                            item = new ItemToUpdate(user, name, type, d, true);
                            counters[15]++;
                        }
                    } else {
                        // todo - not supported at the moment
                    }

                    if (item != null) {
                        item.setValid(isValid);
                        items2Update.put(item.getKey(), item);
                    }
                }
            }
            );

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        // validate synonyms
        String sql2 = listSynonymsSql.replace("<USER>", user);
        ResultSetHelper rsHlp2 = new ResultSetHelper(conn);
        try {
            rsHlp2.populateFromResultSet(sql2, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String type = DbObject.SYNONYM; //rs.getString("OBJECT_TYPE");
                    String syn_owner = rs.getString("SYNONYM_OWNER");
                    String name = rs.getString("SYNONYM_NAME");
                    String table_owner = rs.getString("TABLE_OWNER");
                    Date lastDDLTime = null;
                    try {
                        lastDDLTime = new Date(fmt.parse(rs.getString("TIMESTAMP")).getTime());
                    } catch (ParseException e) {
                        lastDDLTime = new Date(0);
                    }

                    ItemToUpdate item = null;
//                    name = table_owner + "." + name;
                    if (DbObject.SYNONYM.equalsIgnoreCase(type)) {
                        Date d = _ddata.objectDDLTime(DbObject.SYNONYM, name);
                        if (d == null || lastDDLTime.after(d)) { //pack.getLastDDLTime())) {
                            // synonym is missed or obsoleted
                            item = new ItemToUpdate(syn_owner, name, type, lastDDLTime, false);
                            counters[16]++;
                        } else {
                            // synonym is up to date
                            item = new ItemToUpdate(syn_owner, name, type, d, true);
                            counters[17]++;
                        }
                    }

                    if (item != null) {
                        items2Update.put(item.getKey(), item);
                    }
                }
            });
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        log.info("[" + user + "] Up to date (packages/tables/sequences/types/views/func/proc): "
                + counters[1] + "/" + counters[3] + "/" + counters[5]
                + "/" + counters[7] + "/" + counters[9]
                + "/" + counters[11] + "/" + counters[13] + "/" + counters[15]
                + " Out of date: " + counters[0] + "/" + counters[2] + "/" + counters[4]
                + "/" + counters[6] + "/" + counters[8]
                + "/" + counters[10] + "/" + counters[12] + "/" + counters[14]
        );

        return items2Update;
    }


    public synchronized int makeUpdate(
            Map<String, ItemToUpdate> dbObjVsCacheObj,
            Map<String, DbObjectLoader> type2handler,
            DbObjectCache ecache,
            String[] types) throws DBException {

        List<ItemToUpdate> forcedItems = new ArrayList<ItemToUpdate>();
        int CHUNK_SIZE = 30;
        PrioritizedList l = new PrioritizedList(dbObjVsCacheObj.values(), forcedItems, CHUNK_SIZE);
        log.info("[" + user + "] Number of objects being updated: " + l.size());

        int counter0 = 0;
        int counter1 = 0;

        while (l.size() > 0) {

            Mix ordered_items = l.pop2();
            DbObjectLoader h = type2handler.get(ordered_items.type);
            if (h == null) {
                // todo - there is no handler for the type??
            } else {

                // [START] Progress status reporting ----------------------------------------
                String names = StringUtils.convert2listStringsWoQuotes(ordered_items.names).toLowerCase();
                names = (names.length() > 50) ? (names.substring(0, 49) + " ...") : names;
                __reportStartupProgress__("Loading " + names);
                // [END]   Progress status reporting ----------------------------------------

                __assertShutdown__();
                List<DbObjectSpec> dbObjects = null; //h.load(conn, ordered_items);
                // provide a way to cancel already started loading - loading may take a time
                try {
                    currentLoader = h;
                    dbObjects = h.load(conn, ordered_items);
                } finally {
                    currentLoader = null;
                }

                for (DbObjectSpec dboEx : dbObjects) {
                    ItemToUpdate item = findItemToUpdate(dbObjVsCacheObj, dboEx.type, dboEx.name);
                    // Update the cache
                    if(item == null){
                        // returned item may be null in case of genereted substitutor objects by loader
                        ecache.update(dboEx.name, dboEx.type, dboEx.source, null, true);
                    } else {
                        ecache.update(dboEx.name, dboEx.type, dboEx.source, item.lastDDL, item.isValid);
                    }
                    counter0++;

                    dboEx.source = null; // help to GC
                }
            }

            try {
                this.wait(10);
            } catch (InterruptedException e) {
            }
        }

        Set<ItemToUpdate> cacheObj = new HashSet<ItemToUpdate>();
        for (String type : types) {
            cacheObj.addAll(ecache.getObjectListForType(type));
        }

        log.info("[" + user + "] Update cache ...");

        //
        __assertShutdown__();

        // 2. go thru the cached objects to get list of objects being deleted
        for (ItemToUpdate item : cacheObj) {
            if (dbObjVsCacheObj.get(item.getKey()) == null){
                // object not found in the database, delete it from the cache
                ecache.remove(item.type, item.name);
                counter1++;
            }
        }

        ecache.flush();

        log.info("[" + user + "] ... done, updated/deleted " + counter0 + "/" + counter1 + " nbr objects in cache: ???");
        return counter0 + counter1;
    }

    private void __assertShutdown__() {
        if (cancelUpdate) throw new CancelUpdateException();
    }

    private void __reportStartupProgress__(String s) {
        //To change body of created methods use File | Settings | File Templates.
    }


    private ItemToUpdate findItemToUpdate(Map<String, ItemToUpdate> dbObj_vs_cacheObj, String type, String name) {
        String key = (type + "|" + name).toLowerCase();
        return dbObj_vs_cacheObj.get(key);
    }


    class ValueHelper {
        public Date date;
        public int nbr;

        public ValueHelper(Date d, int nbr) {
            this.date = d;
            this.nbr = nbr;
        }
    }


}
