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

import com.deepsky.database.DBException;
import com.deepsky.database.ora.ConnectionHolder;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.DbUrlSID;
import com.deepsky.database.ora.ItemToUpdate;
import com.deepsky.database.ora2.DbCacheManager;
import com.deepsky.database.ora2.DbObjectCache;
import com.deepsky.database.ora2.DbObjectLoader;
import com.deepsky.database.ora2.loaders.*;
import com.deepsky.lang.plsql.parser.WrappedPackageException;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.RefHolder;
import com.deepsky.lang.plsql.resolver.index.IndexEntriesWalkerInterruptable;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.index.IndexTreeBase;
import com.deepsky.lang.plsql.resolver.index.TreeNode;
import com.deepsky.lang.plsql.resolver.psibased.NamesIndexer;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.lang.plsql.sqlIndex.SysSchemaIndexProvider;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DbSchemaObjectsIndexer {

    static Logger log = Logger.getLogger(DbSchemaObjectsIndexer.class);

    // jdbc:oracle:thin:pln/pln@10.105.27.126:1521:orcl
    final static private String SCHEMA_PROP = "schema";
    final static private String OUTPUT_DIR_PROP = "output.indexDir";

    ConnectionHolder conn;
    Map<String, DbObjectLoader> type2handler = new HashMap<String, DbObjectLoader>();

    DbUrl dbUrl;
    Date _1970;

    File indexDir;
    String indexFileName;
    IndexTree itree;

    File erroredPkgPath;
    String targetUser = "SYS";

//    Writer logger;

    public DbSchemaObjectsIndexer(DbUrl url, String targetSchema, String output_dir) throws ParseException, DBException {
        this.dbUrl = url;
        this.conn = new ConnectionHolder(url);
        this.conn.getConnection();

        this.targetUser = targetSchema != null ? targetSchema : url.getUser();

        if (targetUser.equalsIgnoreCase("SYS")) {
            // SYS schema
            SysSchemaIndexProvider indexProvider = new SysSchemaIndexProvider(output_dir);
            AbstractSchema index = indexProvider.getSysUserIndex(Integer.parseInt(conn.getDatabaseVersionShort()), /*todo -- fix me*/ null);
            indexDir = new File(index.getIndexPath());
            indexFileName = index.getIndexFileName();
            itree = index.getIndexTree();
        } else {
            // user schema
            String hostPortService = dbUrl.getHostPortServiceName().replace(':', '!');
            indexDir = checkOutputDir(output_dir);
            indexFileName = (targetUser + "@" + hostPortService + ".dump").toLowerCase();
            itree = new IndexTreeBase();
        }

        // clean up output directory
        log.info("Clean up output indexDir: " + this.indexDir + " ...");
        int counter = 0;
        for (File f : this.indexDir.listFiles()) {
            if (!f.delete()) {
                log.error("Cannot delete a file in the output indexDir, the file: " + f);
                System.exit(-1);
            }
            counter++;
        }
        log.info("Done, deleted " + counter + " files");

        // clean indexes
        itree.clear();

        log.info("Database version: " + conn.getDatabaseVersionShort());
        log.info("Target schema:    " + targetUser);
//        fileName = conn.getDatabaseVersionShort() + "#serialized";
//        type2handler.put("FUNCTION", new FunctionHandler());

        type2handler.put(DbObject.TABLE, new TableLoader());
        type2handler.put(DbObject.VIEW, new ViewLoader());
        type2handler.put(DbObject.PACKAGE, new PackageSpecLoader());
        type2handler.put(DbObject.SYNONYM, new SynonymLoader());
        type2handler.put(DbObject.TYPE, new TypeLoader());

/*
        type2handler.put(DbObject.SYNONYM, new SynonymLoader());
        type2handler.put(DbObject.VIEW, new ViewLoader());
        type2handler.put(DbObject.SEQUENCE, new SequenceLoader());
        type2handler.put(DbObject.PACKAGE, new PackageSpecLoader());
        type2handler.put(DbObject.PACKAGE_BODY, new PackageBodyLoader());
        type2handler.put(DbObject.TYPE, new TypeLoader());
*/

        // todo -- how about TRIGGERS?


        _1970 = new SimpleDateFormat("yyyy").parse("1970");
        erroredPkgPath = new File("err_pkgs");
        if (!erroredPkgPath.exists()) {
            erroredPkgPath.mkdir();
        }

        long freeMem = Runtime.getRuntime().freeMemory();
        long maxMem = Runtime.getRuntime().maxMemory();
        long totalMem = Runtime.getRuntime().totalMemory();
        System.out.println("[Memory usages] free: " + freeMem + " Max: " + maxMem + " Total: " + totalMem);
    }

    public void process() throws DBException, IOException {
//        logger = new FileWriter("resolver_4.log");
//        itree = new IndexTreeBase(logger);

        DbObjectCache ocache = new DbObjectCacheImpl(itree);
        DbCacheManager dbMan = new DbCacheManager(conn, targetUser);

        String[] types = type2handler.keySet().toArray(new String[0]);

        Map<String,ItemToUpdate> lst = dbMan.createUserObjList(ocache, types);
        dbMan.makeUpdate(lst, type2handler, ocache, types);
//        dbMan.process(type2handler, ocache);

        log.info("Dump names in the file: " + indexFileName);

        // dump index
        //String dumpPublicSynFilePath = new File(indexDir, "public_syn.idx").getAbsolutePath();
        final List<String> publicSynonyms = new ArrayList<String>();
        String dumpFilePath = new File(indexDir, indexFileName).getAbsolutePath();
        int dumpedEntries = itree.dumpNames(dumpFilePath, new IndexTree.IndexEntryFilter() {
            public boolean accept(String ctxPath, String value) {
                if (ContextPath.SYNONYM == new ContextPathUtil.CtxPathParser(ctxPath).extractLastCtxType()) {
                    // filter out PUBLIC SYNONYMS
                    ContextPathUtil.SynonymAttributes attrs = ContextPathUtil.decodeSynonymValue(value);
                    if (attrs != null) {
                        if (attrs.synonymOwner.equalsIgnoreCase("PUBLIC")) {
                            log.info("... skip PUBLIC SYNONYM: " + ctxPath);
                            String synonymEntry = ctxPath + " " + value;
                            publicSynonyms.add(synonymEntry);
                            return false;
                        }
                    } else {
                        // todo -- handle exception
                    }
                }
                return true;
            }
        });

        log.info("Number of dumped entries: " + dumpedEntries);


        dumpedEntries = 0;
        // save PUBLIC synonyms
        String publicSynFilePath = new File(indexDir, "public_syn.idx").getAbsolutePath();
        Writer synonyms = new FileWriter(publicSynFilePath);
        for (String synonymEntry : publicSynonyms) {
            synonyms.write(synonymEntry);
            synonyms.write("\n");
            dumpedEntries++;
        }
        synonyms.close();
        log.info("Number of dumped PUBLIC SYSNONYMS: " + dumpedEntries);
    }


    public static void main(String[] args) throws DBException, IOException, ClassNotFoundException, ParseException {
        System.setProperty("log.type", "log4j");

        String targetSchema = System.getProperty(SCHEMA_PROP);
        String output_dir = System.getProperty(OUTPUT_DIR_PROP);

        if (args.length == 0) {
            log.error("Database URL not specified, got down");
            System.exit(-1);
        }

        DbUrl dbUrl = new DbUrlSID(args[0]);

        DbSchemaObjectsIndexer indexer = new DbSchemaObjectsIndexer(dbUrl, targetSchema, output_dir);
        indexer.process();
    }


    private boolean saveDbObjectSource(String type, String fileName, String content) {
        // filter out some object types
        if (DbObject.SYNONYM.equals(type)) {
            // skip source saving, index has enough info about the object
        } else if (DbObject.VIEW_INTERNAL_TYPE.equals(type)) {
            // skip source saving, internal stuff
        } else {
            // do saving
            try {
                StringUtils.string2file(content, new File(indexDir, fileName));
            } catch (IOException e) {
                log.error("Could not save file " + fileName + " in the directory: " + indexDir);
//                System.exit(-1);
                return false;
            }
        }
        return true;
    }

    class DbObjectCacheImpl implements DbObjectCache {

        IndexTree itree;

        public DbObjectCacheImpl(IndexTree itree) throws IOException {
            this.itree = itree;
        }

        public Date objectDDLTime(String type, String name) {
            if (DbObject.TYPE.equals(type)) {
                for (TreeNode tnode : itree.findNodeInRootContext(name)) {
                    switch (tnode.getType()) {
                        case ContextPath.COLLECTION_TYPE:
                        case ContextPath.OBJECT_TYPE:
                        case ContextPath.VARRAY_TYPE:
                        case ContextPath.RECORD_TYPE:
                            // no duplicates expected among top nodes
                            return null; //new Date(itree.getUpdateDateForEntry(tnode.getPath()));
                    }
                }
            } else {
                String ctx = dbType2ctx.get(type);
                if (ctx != null) {
                    String ctxPath = ctx + "..$" + name;
                    return null; //new Date(itree.getUpdateDateForEntry(ctxPath));
                }
            }

            return null;
        }

        public void update(String name, String type, String source, Date updateDate, boolean isValid) {

            String fileName = (type + "!" + name + ".dump").toLowerCase();
            boolean isWrapped = false;
//            if(saveDbObjectSource(type, fileName, source)){
            try {
                long ms0 = System.currentTimeMillis();
                MarkupGeneratorEx2 generator = new MarkupGeneratorEx2(fileName);
                ASTNode root = generator.parse(source);
                long ms1 = System.currentTimeMillis();

                int indexes = itree.getEntriesCount();
                NamesIndexer indexer = new NamesIndexer();
                indexer.parse(root, itree);
                int added = itree.getEntriesCount() - indexes;
                indexes = itree.getEntriesCount();

                long ms2 = System.currentTimeMillis();
                System.out.println("[Indexing], time spent (ms): " + (ms2 - ms1) + " [Parsing], time (ms): " + (ms1 - ms0) + " indexes: " + added + "(" + indexes + ") File: " + fileName);
            } catch (WrappedPackageException e) {
                System.out.println("WARN: package " + name + " is wrapped, skip saving content");
                isWrapped = true;
            } catch (Throwable e) {
                System.out.println("ERROR: " + e.getMessage() + " File: " + fileName);
            }

            long freeMem = Runtime.getRuntime().freeMemory();
            long maxMem = Runtime.getRuntime().maxMemory();
            long totalMem = Runtime.getRuntime().totalMemory();
            System.out.println("[Memory usages] free: " + freeMem + " Max: " + maxMem + " Total: " + totalMem);
//            }

            if (!isWrapped) {
                saveDbObjectSource(type, fileName, source);
            }
        }

        public void remove(String type, String name) {
            String ctxTag = dbType2ctx.get(type);
            if (ctxTag != null) {
                String ctxPath = ctxTag + "..$" + name.toLowerCase();

                itree.remove(ctxPath);
            } else if (type.equals(DbObject.TYPE)) {
                RefHolder ref = new RefHolder(ContextPath.TYPE_REF, "", name);
//                ResolveHelper rhlp = resolver.resolveReference(ref);
//                itree.remove(rhlp.getCtxPath());
            } else {
                // todo --
            }

        }

        public Date lastDDLTimeForType(String type) {
            return null;
        }

        @NotNull
        public String[] listTypes() {
            List<String> out = new ArrayList<String>();
            for (String type : dbType2ctx.keySet()) {
                out.add(type.replace('_', ' '));
            }
            return out.toArray(new String[0]);
        }

        public List<ItemToUpdate> getObjectListForType(final String type) {

            String _ctxType = dbType2ctx.get(type);
            final int ctxType = (_ctxType != null) ? ContextPathUtil.prefix2type(_ctxType) : -1;
            final List<ItemToUpdate> out = new ArrayList<ItemToUpdate>();

            itree.iterateTopNodes(new IndexEntriesWalkerInterruptable() {
                public boolean process(String ctxPath, String value) {
                    ContextPathUtil.CtxPathParser ctxParser = new ContextPathUtil.CtxPathParser(ctxPath);
                    if (ctxType != -1) {
                        if (ctxType == ctxParser.extractLastCtxType()) {
                            out.add(new ItemToUpdate(null, ctxParser.lastCtxName(), type));
                        }
                    } else {
                        // todo -- process TYPE
                    }
                    return true;
                }
            });
            return out;
        }

        public void flush() {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public int getNbrObjectForType(String type) {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }


    private static Map<String, String> dbType2ctx = new HashMap<String, String>();

    static {

        dbType2ctx.put(DbObject.TABLE, ContextPath.TABLE_DEF_PRX);
        dbType2ctx.put(DbObject.PACKAGE, ContextPath.PACKAGE_SPEC_PRX);
        dbType2ctx.put(DbObject.PACKAGE_BODY, ContextPath.PACKAGE_BODY_PRX);
        dbType2ctx.put(DbObject.VIEW, ContextPath.VIEW_DEF_PRX);
        dbType2ctx.put(DbObject.TRIGGER, ContextPath.CREATE_TRIGGER_PRX);
        dbType2ctx.put(DbObject.SEQUENCE, ContextPath.SEQUENCE_PRX);
        dbType2ctx.put(DbObject.FUNCTION, ContextPath.FUNCTION_BODY_PRX);
        dbType2ctx.put(DbObject.PROCEDURE, ContextPath.PROCEDURE_BODY_PRX);
    }


    private File checkOutputDir(String output_dir) {
        if (output_dir != null) {
            File dir = null;
            if (!new File(output_dir).exists()) {
                log.warn("Directory " + output_dir + " does not exist, trying to create ...");
                if (new File(output_dir).mkdir()) {
                    log.warn("Done successfully.");
                    dir = new File(output_dir);
                } else {
                    log.error("Cannot create directory: " + new File(output_dir));
                    System.exit(-1);
                }
            } else {
                if (!new File(output_dir).isDirectory()) {
                    log.info("Specified directory " + output_dir + " not found");
                    System.exit(-1);
                } else {
                    dir = new File(output_dir);
                }
            }
            return dir;
        } else {
            File dir = new File("sources");
            if (!dir.exists()) {
                if (dir.mkdir()) {
                    log.warn("Output directory not specified, ./sources will be used instead");
                } else {
                    log.error("Output directory not specified, unable to create ./sources directory, go down");
                    System.exit(-1);
                }
            } else {
                log.warn("Output directory not specified, ./sources will be used instead");
            }
            return dir;
        }
    }

}
