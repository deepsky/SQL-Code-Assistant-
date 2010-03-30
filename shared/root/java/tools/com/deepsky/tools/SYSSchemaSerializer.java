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

package com.deepsky.tools;


import com.deepsky.database.ora.*;
import com.deepsky.database.ora.handlers.*;
import com.deepsky.database.DBException;
import com.deepsky.utils.StringUtils;
import com.deepsky.database.ResultSetHelper;
import com.deepsky.database.MappingHelper;
import com.deepsky.database.cache.Utils;
import com.deepsky.lang.plsql.struct.*;


import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;

import org.apache.log4j.Logger;

public class SYSSchemaSerializer implements ParseEventListener {

    static Logger log = Logger.getLogger(SYSSchemaSerializer.class);

    final String OUTPUT_DIR = "output.dir";

    final String listSysObjectsSql =
            "SELECT OWNER, OBJECT_NAME, OBJECT_TYPE, STATUS, CREATED, LAST_DDL_TIME, TIMESTAMP " +
                    "FROM ALL_OBJECTS " +
                    "WHERE OBJECT_TYPE IN ( <OBJECT_TYPES> ) " +
                    "AND OWNER = 'SYS'";

    final String listSynonymSql =
            "SELECT syns.OBJECT_NAME, syns.OBJECT_TYPE, syns.STATUS, syns.CREATED, syns.LAST_DDL_TIME, syns.TIMESTAMP, " +
                    "   t.OBJECT_TYPE AS REF_OBJECT_TYPE\n" +
                    "FROM ALL_OBJECTS syns, ALL_OBJECTS t, ALL_SYNONYMS lnk  \n" +
                    "WHERE syns.OBJECT_TYPE = 'SYNONYM' \n" +
                    "AND syns.OWNER = 'PUBLIC'\n" +
                    "AND t.OWNER = 'SYS' and t.OBJECT_TYPE in ('TABLE', 'VIEW')\n" +
                    "AND lnk.SYNONYM_NAME = syns.OBJECT_NAME AND lnk.TABLE_NAME = t.OBJECT_NAME";

    ConnectionHolder conn;
    Map<String, BaseHandler> type2handler = new HashMap<String, BaseHandler>();
    List<ItemToUpdate> queueItem2Update = Collections.synchronizedList(new ArrayList<ItemToUpdate>());
    DbUrl url;
    Date _1970;
    File dir = new File("./");

    String fileName;
    File erroredPkgPath;


    Map<DbObjectKey, String> key2text = new HashMap<DbObjectKey, String>();

    void registrySource(DbObjectKey key, String source) {
        if (source == null) {
            return;
        }
        key2text.put(key, source);
    }

    String findSource(DbObjectKey key) {
        return key2text.get(key);
    }

    void makeUpdate(List<ItemToUpdate> dbObjVsCacheObj) throws DBException, IOException {

        PrioritizedList l = new PrioritizedList(dbObjVsCacheObj, queueItem2Update);
        log.info(" Number of objects being updated: " + l.size());

        Map<DbObjectKey, DbObject> _dbObjList = new HashMap<DbObjectKey, DbObject>();

        while (l.size() > 0) {

            Mix ordered_items = l.pop2();
            BaseHandler h = type2handler.get(ordered_items.type);
            if (h == null) {
                // todo - there is no handler for the type??
            } else {
                List<DbObjectEx> dbObjects = h.load(conn, ordered_items);
                for (DbObjectEx dboEx : dbObjects) {
                    dboEx.dbo.setLastDDLTime(
                            findLastDDLTime(
                                    dbObjVsCacheObj,
                                    dboEx.owner,
                                    dboEx.dbo.getTypeName(),
                                    dboEx.dbo.getName()
                            ));
                    DbObjectKey key = new DbObjectKey(dboEx.dbo.getTypeName(), dboEx.dbo.getName());
                    _dbObjList.put(key, dboEx.dbo);

                    // save source in the temporary storage
                    // todo -- check dependencies of the DbObject
                    if(dboEx.dbo.isValid()){
                        if(dboEx.source != null)
                            registrySource(key, dboEx.source);
                    }

                    dboEx.dbo.setLocator(
                            new SysDbObjectScriptLocator(
                                dboEx.dbo.getTypeName(),
                                dboEx.dbo.getName()
                            )
                    );//"SYS"));
                }
            }
        }

        // group objects by type
        Map<String, Map<DbObjectKey, DbObject>> groupedByType = new HashMap<String, Map<DbObjectKey, DbObject>>();
        for (Map.Entry<DbObjectKey, DbObject> e : _dbObjList.entrySet()) {
            Map<DbObjectKey, DbObject> l0 = groupedByType.get(e.getKey().getType());
            if (l0 == null) {
                l0 = new HashMap<DbObjectKey, DbObject>();
                groupedByType.put(e.getKey().getType(), l0);
            }

            l0.put(e.getKey(), e.getValue());
        }

        // filter out Tables and views that do not have public synonym
//        filterOutPrivateObjects(groupedByType);

//        log.info("Number of loaded objects : " + _dbObjList.size() + ", start to serialize into the file: " + fileName);

        // serialize
        for (Map.Entry<String, Map<DbObjectKey, DbObject>> e : groupedByType.entrySet()) {
            String type = e.getKey();
            Map<DbObjectKey, DbObject> o = e.getValue();

            log.info("Serialize " + type + ", number of objects: " + o.size());
            serialize(type, o, fileName + "#" + type.toLowerCase());
        }

        VersionMarker marker = new VersionMarker();
        log.info("VersionMarker object: " + marker.getName());
        // serialize marker
        serialize(marker, fileName + "#marker");

        log.info("done.");
    }

    private void filterOutPrivateObjects(Map<String, Map<DbObjectKey, DbObject>> groupedByType) {
        // get synonym type
        Map<DbObjectKey, DbObject> synonyms = groupedByType.get("SYNONYM");
        if (synonyms == null) {
            log.warn("SYNONYM objects not found! Do you realy want to filter out non public objects?");
            return;
        }

        Map<DbObjectKey, DbObject> tables = groupedByType.get("TABLE");
        if (tables != null) {
            List<DbObjectKey> blackList = new ArrayList<DbObjectKey>();
            // mark not public tables
            for (DbObjectKey key : tables.keySet()) {
                if (!findReferencedObject(synonyms, key)) {
                    blackList.add(key);
                }
            }

            log.info(blackList.size() + " were found non public tables, about to delete them.");
            // delete non public tables
            for (DbObjectKey k : blackList) {
                tables.remove(k);
            }
        }

        Map<DbObjectKey, DbObject> views = groupedByType.get("VIEW");
        if (views != null) {
            List<DbObjectKey> blackList = new ArrayList<DbObjectKey>();
            // mark not public tables
            for (DbObjectKey key : views.keySet()) {
                if (!findReferencedObject(synonyms, key)) {
                    blackList.add(key);
                }
            }

            log.info(blackList.size() + " were found non public views, about to delete them.");
            // delete non public tables
            for (DbObjectKey k : blackList) {
                views.remove(k);
            }
        }
    }


    boolean findReferencedObject(Map<DbObjectKey, DbObject> synonyms, DbObjectKey _key) {
        for (DbObject dbo : synonyms.values()) {
            SynonymDescriptor syn = (SynonymDescriptor) dbo;

            if (syn.getReferencedType().equalsIgnoreCase(_key.getType())
                    && _key.getName().equalsIgnoreCase(syn.getReferencedTableName())) {
                return true;
            }
        }

        return false;
    }

    void serialize(String type, Map<DbObjectKey, DbObject> o, String filename) throws IOException {
        // serialize objects
        OutputStream out = new FileOutputStream(new File(dir, filename));
        ObjectOutputStream ostream = new ObjectOutputStream(out);
        ostream.writeObject(o);
        ostream.flush();
        ostream.close();
        out.close();

        // save sources
        String folder = conn.getDatabaseVersionShort();
        File srcDir = new File(dir, folder);
        if (!srcDir.exists()) {
            if (!srcDir.mkdir()) {
                log.error("Could not create source dircetory: " + srcDir.toString());
                System.exit(-1);
            }
        }
        int sources = 0;
        for (DbObjectKey key : o.keySet()) {
            String src = findSource(key);
            if (src != null) {
                String fname = Utils.encryptFileName(key.getType(), key.getName()); //key.getType() +"!" +key.getName() + ".dump";
                StringUtils.string2file(src, new File(srcDir, fname.toLowerCase()));
                sources++;
            }
        }
        log.info("Type " + type + ", number of saved sources: " + sources);
    }


    void serialize(Serializable o, String filename) throws IOException {
        // serialize objects
        OutputStream out = new FileOutputStream(new File(dir, filename));
        ObjectOutputStream ostream = new ObjectOutputStream(out);
        ostream.writeObject(o);
        ostream.flush();
        ostream.close();
        out.close();
    }

    Date findLastDDLTime(List<ItemToUpdate> items, String owner, String type, String name) {
        for (ItemToUpdate item : items) {
            if (item.owner.equalsIgnoreCase(owner) && item.type.equalsIgnoreCase(type) && item.name.equalsIgnoreCase(name)) {
                return item.lastDDL;
            }
        }

        return (Date) _1970.clone();
    }

    List<ItemToUpdate> createUserObjList(String _sql, String[] types) throws DBException {
        // get list of objects updated since the last session
        final List<ItemToUpdate> items2Update = new ArrayList<ItemToUpdate>();
        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String sql = _sql.replace("<OBJECT_TYPES>", StringUtils.convert2listStrings(types));

        final String user = "SYS";
        final int[] counters = new int[12];
        ResultSetHelper rsHlp = new ResultSetHelper(conn.getConnection());

        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String type = rs.getString("OBJECT_TYPE");
                    String name = rs.getString("OBJECT_NAME");
                    Date lastDDLTime = null;
                    try {
                        lastDDLTime = new Date(fmt.parse(rs.getString("TIMESTAMP")).getTime());
                    } catch (ParseException e) {
                    }

                    ItemToUpdate item = null;
                    if ("PACKAGE".equalsIgnoreCase(type)) {
                        item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                        counters[0]++;
                    } else if ("TABLE".equalsIgnoreCase(type)) {
                        item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                        counters[2]++;
                    } else if ("SEQUENCE".equalsIgnoreCase(type)) {
                        item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                        counters[4]++;
                    } else if ("TYPE".equalsIgnoreCase(type)) {
                        item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                        counters[6]++;
                    } else if ("VIEW".equalsIgnoreCase(type)) {
                        if (name.startsWith("KU$_") || name.startsWith("EXU")) {
                            // todo - skip some views
                            item = null;
                        } else {
                            item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                            counters[8]++;
                        }
                    } else if ("FUNCTION".equalsIgnoreCase(type)) {
                        item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                        counters[10]++;
                    } else {
                        // todo - not supported at the moment
                    }

                    if (item != null) {
                        items2Update.add(item);
                    }
                }
            }
            );

            log.info(
                    "[" + user + "]  (packages/tables/sequences/types/view/func): "
                            + counters[0] + "/" + counters[2] + "/" + counters[4] + "/" + counters[6]
                            + "/" + counters[8] + "/" + counters[10]);
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        return items2Update;
    }

    List<ItemToUpdate> createSynonymList() throws DBException {
        // get list of objects updated since the last session
        final List<ItemToUpdate> items2Update = new ArrayList<ItemToUpdate>();
        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String sql = listSynonymSql;

        final String user = "SYS";
        final int[] counters = new int[11];
        ResultSetHelper rsHlp = new ResultSetHelper(conn.getConnection());

        try {
            rsHlp.populateFromResultSet(sql, new MappingHelper() {
                public void processRow(ResultSet rs) throws SQLException {
                    String type = rs.getString("OBJECT_TYPE");
                    String name = rs.getString("OBJECT_NAME");
                    Date lastDDLTime = null;
                    try {
                        lastDDLTime = new Date(fmt.parse(rs.getString("TIMESTAMP")).getTime());
                    } catch (ParseException e) {
                    }

                    ItemToUpdate item = null;
                    if ("SYNONYM".equalsIgnoreCase(type)) {
                        item = new ItemToUpdate(user, name, type, lastDDLTime, false);
                        counters[10]++;
                    } else {
                        // todo - not supported at the moment
                    }

                    if (item != null) {
                        items2Update.add(item);
                    }
                }
            }
            );

            log.info("[" + user + "]  synonyms: " + counters[10]);
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        return items2Update;
    }


    public void process() throws DBException, IOException {
        String[] types = new String[]{"TABLE", "PACKAGE", "VIEW", "FUNCTION"};
        List<ItemToUpdate> lst = createUserObjList(listSysObjectsSql, types);

        List<ItemToUpdate> synonyms = createSynonymList();

        lst.addAll(synonyms);
        makeUpdate(lst);
    }

    public SYSSchemaSerializer(DbUrl url) throws ParseException, DBException {
        this.url = url;
        this.conn = new ConnectionHolder(url);
        this.conn.getConnection();

        // validate output directory
        String output_dir = System.getProperty(OUTPUT_DIR);
        if (output_dir != null) {
            if (!new File(output_dir).exists()) {
                log.info("Directory " + output_dir + " does not exist.");
            } else {
                if (!new File(output_dir).isDirectory()) {
                    log.info("Specified directory " + output_dir + " not found");
                } else {
                    this.dir = new File(output_dir);
                }
            }
        }

        log.info("Database version: " + conn.getDatabaseVersionShort());
        fileName = conn.getDatabaseVersionShort() + "#serialized";

        type2handler.put("FUNCTION", new FunctionHandler());
        type2handler.put("TABLE", new TableHandler());
        type2handler.put("VIEW", new ViewHandler());
        type2handler.put("SYNONYM", new SynonymHandler());
        type2handler.put("SEQUENCE", new SequenceHandler());

        BaseHandler h = new TypeHandler();
        h.setListener(this);
        type2handler.put("TYPE", h);

        BaseHandler h2 = new PackageSpecHandler();
        h2.setListener(this);
        type2handler.put("PACKAGE", h2);

        _1970 = new SimpleDateFormat("yyyy").parse("1970");
        erroredPkgPath = new File("err_pkgs");
        if (!erroredPkgPath.exists()) {
            erroredPkgPath.mkdir();
        }
    }


    public void handleEvent(int event, int status, ParseEvent evObject) {
        if (event == ParseEventListener.EVNT_PARSING_RESULT && status == ParseEventListener.STATUS_FAILED) {
            if (evObject.type.equalsIgnoreCase("PACKAGE")) {
                String pkgName = evObject.name + ".pks";

                try {
                    StringUtils.string2file(evObject.content, new File(erroredPkgPath, pkgName.toLowerCase()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws DBException, IOException, ClassNotFoundException, ParseException {
        System.setProperty("log.type", "log4j");

        if (args.length == 0) {
            log.error("Database URL not specified, got down");
        }

        DbUrl dbUrl = new DbUrl(args[0]);

        SYSSchemaSerializer ser = new SYSSchemaSerializer(dbUrl);
        ser.process();
    }


    public Map<DbObjectKey, DbObject> load(String version) throws IOException, ClassNotFoundException {

        InputStream in = this.getClass().getClassLoader().getResourceAsStream(version + "#serialized");
        if (in == null) {
            return new HashMap<DbObjectKey, DbObject>();
        } else {
            ObjectInputStream ostream = new ObjectInputStream(in);
            Map<DbObjectKey, DbObject> res = (Map<DbObjectKey, DbObject>) ostream.readObject();
            log.info("Number of loaded objects: " + res.size());
            return res;
        }
    }

}
