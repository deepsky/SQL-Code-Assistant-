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

package com.deepsky.navigation.impl;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.indexMan.IndexBulkChangeListener;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.factory.PlSqlElementLocator;
import com.deepsky.lang.plsql.resolver.index.ContextItem;
import com.deepsky.lang.plsql.resolver.index.IndexEntriesWalkerInterruptable;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.resolver.utils.IndexTreeUtil;
import com.deepsky.lang.plsql.sqlIndex.*;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.navigation.NameLookupService;
import com.deepsky.navigation.NavigationItemEx;
import com.deepsky.navigation.NavigationItemProvider;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.psi.PsiElement;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;


public class NameLookupServiceImpl implements NameLookupService {

    private static final LoggerProxy log = LoggerProxy.getInstance("#NameLookupService");

    final int SYNTETIC_TYPE = 12345;

    Project project;
    IndexManager manager;

    // format key: <full_DbURL>|<DbObject.<TYPE_NAME>>
    // format value for all type except PACKAGE: <name0>.<name1>. ... <nameN>
    // format value for PACKAGE: <object_name0>..<object_name1>.. ... |<package_name0>. ...
    Map<String, String> key2names = new HashMap<String, String>();

    private int updateFS_Counter = 0;
    //private final Set<DbUrl> pendedRequest = new HashSet<DbUrl>();
    IndexManagerListener listener = new IndexManagerListenerImpl();

    public NameLookupServiceImpl(Project project) {
        this.project = project;
        manager = PluginKeys.SQL_INDEX_MAN.getData(project);
        manager.addListener(listener);

        MessageBus bus1 = project.getMessageBus();
        // listen for index updates
        IndexBulkChangeListener ll = new IndexBulkChangeListener() {
            public void handleUpdate(final DbUrl source, final String[] types) {
//                long ms = System.currentTimeMillis();
                AbstractSchema schema = manager.getIndex(source, 0);
                if (schema == null) {
                    deleteCache(source);
                } else {
                    if(!source.equals(IndexManager.FS_URL)){
                        // update database schema request
                        updateCache(schema, types);
                    } else {
                        // postpone updating of the FileSystem schema
                        updateFS_Counter++;
                    }
                }

//                ms = System.currentTimeMillis() - ms;
//                log.info("Index built: " + ms);
            }
        };

        bus1.connect().subscribe(IndexBulkChangeListener.TOPIC, ll);

        // initial cache populating
        for (SqlDomainIndex i : manager.getIndexes()) {
            AbstractSchema[] schemas = i.getIndexes();
            for (AbstractSchema schema : schemas) {
                updateCache(schema, null);
            }
        }
    }

    private synchronized void updateCache(DbUrl dbUrl, String[] _types) {

    }

    private synchronized void deleteCache(DbUrl dbUrl) {
        // remove names for the index
        String[] keysBeingRemoved = findKeysMatchTo(dbUrl);
        for (String key : keysBeingRemoved) {
            key2names.remove(key);
        }
    }

    private synchronized void updateCache(@NotNull AbstractSchema aschema, String[] _types) {
        //SqlDomainIndex sqlIndex = manager.getIndex(dbUrl);
//        if (aschema != null) {
        final List types = _types == null ? null : Arrays.asList(_types);
        final Map<String, StringBuilder> type2names = new HashMap<String, StringBuilder>();
        final Set<String> pkgCtxPaths = new HashSet<String>();

        // loop thru top nodes to collect top names
        IndexTree itree = aschema.getIndexTree();
        itree.iterateTopNodes(new IndexEntriesWalkerInterruptable() {
            public boolean process(String ctxPath, String value) {
                ContextPathUtil.CtxPathParser parser = new ContextPathUtil.CtxPathParser(ctxPath);
                int ctxType = parser.extractLastCtxType();
                if (ctxType == ContextPath.SYSTEM_FUNC) {
                    // skip system functions
                    return true;
                } else if (ctxType == ContextPath.SYNONYM) {
                    //  put in the cache only private synonyms
                    ContextPathUtil.SynonymAttributes r = ContextPathUtil.decodeSynonymValue(value);
                    if (r == null || "public".equalsIgnoreCase(r.synonymOwner)) {
                        return true;
                    }
                } else if (ctxType == ContextPath.PACKAGE_SPEC) {
                    pkgCtxPaths.add(ctxPath);
                    // package scope names will be collected later
                    return true;
                } else if (ctxType == ContextPath.PACKAGE_BODY) {
                    pkgCtxPaths.add(ctxPath);
                    // package scope names will be collected later
                    return true;
                }

                String type = IndexTreeUtil.ctxType2dbType(ctxType);
                if (type != null && (types == null || types.contains(type))) {
                    StringBuilder sb = type2names.get(type);
                    if (sb == null) {
                        sb = new StringBuilder();
                        type2names.put(type, sb);
                    }
                    String name = parser.lastCtxName();
                    sb.append(name).append(".");
                }

                return true;
            }
        });

        // loop over packages to collect package scope function/procedure names
        final Map<String, Set<String>> pkgName2children = new HashMap<String, Set<String>>();
        for (String pkgCtxPath : pkgCtxPaths) {
            itree.iterateOverChildren(pkgCtxPath, new IndexEntriesWalkerInterruptable() {
                public boolean process(String ctxPath, String value) {
                    ContextPathUtil.CtxPathParser parser = new ContextPathUtil.CtxPathParser(ctxPath);
                    int ctxType = parser.extractLastCtxType();
                    switch (ctxType) {
                        case ContextPath.FUNCTION_BODY:
                        case ContextPath.FUNCTION_SPEC:
                        case ContextPath.PROCEDURE_BODY:
                        case ContextPath.PROCEDURE_SPEC:
                            String pkgName = new ContextPathUtil.CtxPathParser(parser.getParentCtx()).lastCtxName();
                            Set<String> children = pkgName2children.get(pkgName);
                            if (children == null) {
                                children = new HashSet<String>();
                                pkgName2children.put(pkgName, children);
                            }

                            children.add(parser.lastCtxName());
                            break;
                    }
                    return true;
                }
            });
        }

        // process package scope objects
        StringBuilder sb1 = new StringBuilder();
        for (Map.Entry<String, Set<String>> e : pkgName2children.entrySet()) {
            for (String name : e.getValue()) {
                sb1.append(name).append("..");
            }
            sb1.append("|").append(e.getKey()).append(".");
        }

        DbUrl dbUrl = aschema.getDbUrl();
        String key1 = buildKey(dbUrl, DbObject.PACKAGE);
        key2names.put(key1, sb1.toString());

        // populate names cache
        for (Map.Entry<String, StringBuilder> e : type2names.entrySet()) {
            String key = buildKey(dbUrl, e.getKey());
            key2names.put(key, e.getValue().toString());
        }

/*
        } else {
            // remove names for the index
            String[] keysBeingRemoved = findKeysMatchTo(dbUrl);
            for (String key : keysBeingRemoved) {
                key2names.remove(key);
            }
        }
*/
    }

    public String[] getNames() {
        if (updateFS_Counter > 0) {
            AbstractSchema schema = manager.getIndex(IndexManager.FS_URL, 0);
            if (schema != null) {
                updateCache(schema, null);
            }
            updateFS_Counter = 0;
        }

        Set<String> out = new HashSet<String>();
        for (String names : key2names.values()) {
            String[] pp = names.split("[\\.\\|]+");
            out.addAll(Arrays.asList(pp));
        }


        return out.toArray(new String[out.size()]);
    }

    public NavigationItem[] findItem(final String name) {

        List<NavigationItem> out = new ArrayList<NavigationItem>();
        String target = name.toLowerCase() + ".";
        for (Map.Entry<String, String> e : key2names.entrySet()) {
            String index = e.getValue();
            int pos = index.indexOf(target);
            if (pos == -1) {
                continue;
            }

            DbUrl dbUrl = extractDbUrlFromKey(e.getKey());
            SqlDomainIndex dindex = manager.getIndex(DbUID.getDbUID(dbUrl));
            if (dindex != null && dindex.getSimpleIndex(dbUrl.getUser()) != null) {
                int type = extractTypeFromKey(e.getKey());
                IndexTree itree = dindex.getSimpleIndex(dbUrl.getUser()).getIndexTree();

                List<ContextItem> itemList = new ArrayList<ContextItem>();
                do {
                    ContextItem[] ctxItem = parsePackageIndex(name, index, type, pos, itree);
                    itemList.addAll(Arrays.asList(ctxItem));
                    pos = index.indexOf(target, pos + 1);
                } while (pos != -1);

                // remove duplicates, like function spec in the package spec and function body in package body
                Map<String, Object[]> presentationS2stuff = new HashMap<String, Object[]>();
                for (ContextItem item : itemList) {
                    ItemPresentation itemP = NavigationItemProvider
                            .create(item.getCtxPath(), item.getValue());
                    if (itemP != null) {
                        presentationS2stuff.put(itemP.getPresentableText(), new Object[]{itemP, item});
                    }
                }

                // create NavigationItem proxy for found objects
                for (Object[] item_ctx : presentationS2stuff.values()) {
                    ItemPresentation itemP = (ItemPresentation) item_ctx[0];
                    ContextItem item = (ContextItem) item_ctx[1];
                    InvocationHandler interceptor = new NavigationItemProxy(dbUrl, name, item.getCtxPath(), itemP);
                    NavigationItem proxy = (NavigationItem) Proxy.newProxyInstance(
                            this.getClass().getClassLoader(),
                            new Class[]{PsiElement.class, NavigationItemEx.class},
                            interceptor);

                    out.add(proxy);
                }
            } else {
                // index seems to be removed?
                // todo - handle index deleting
            }
        }

        return out.toArray(new NavigationItem[out.size()]);
    }


    private ContextItem[] parsePackageIndex(final String name, final String index, int indexType, int pos, IndexTree itree) {
        switch (indexType) {
            case ContextPath.PACKAGE_BODY:
            case ContextPath.PACKAGE_SPEC: {
                int offset = name.length() + 1;
                // name may be not a package name but name of function or procedure (package scope)
                if (index.length() > pos + offset && index.charAt(pos + offset) == '.') {
                    // package scope object, find package name

                    // having SET collection is supposed to effectiflly remove duplicates
                    final Set<ContextItem> out = new HashSet<ContextItem>();
                    int start = index.indexOf('|', pos + offset + 1); //StringUtils.searchInReverse(index, idx-2, '.');
                    if (start != -1) {
                        // package name found
                        int end = index.indexOf('.', start);
                        String pkgName = index.substring(start + 1, end); //(start+1, idx-1);

                        // todo -- it is not quite correct, func/proc can be duplicated
                        //          if it is defined with different argument names

                        // collect names from the package specification
                        ContextItem[] pkgCtxItem = itree.findCtxItems(new int[]{ContextPath.PACKAGE_SPEC}, pkgName);
                        if (pkgCtxItem.length > 0) {
                            itree.iterateOverChildren(pkgCtxItem[0].getCtxPath(), name, new IndexEntriesWalkerInterruptable() {
                                public boolean process(final String ctxPath, final String value) {
//                                    ContextPathUtil.CtxPathParser parser = new ContextPathUtil.CtxPathParser(ctxPath);
//                                    if (parser.lastCtxName().equals(name)) {
                                    out.add(new ContextItemImpl(name, ctxPath, value));
//                                    }
                                    return true;
                                }
                            });
                        }

                        // collect names from the package body (names added on previose)
                        pkgCtxItem = itree.findCtxItems(new int[]{ContextPath.PACKAGE_BODY}, pkgName);
                        if (pkgCtxItem.length > 0) {
                            itree.iterateOverChildren(pkgCtxItem[0].getCtxPath(), name, new IndexEntriesWalkerInterruptable() {
                                public boolean process(final String ctxPath, final String value) {
//                                    ContextPathUtil.CtxPathParser parser = new ContextPathUtil.CtxPathParser(ctxPath);
//                                    if (parser.lastCtxName().equals(name)) {
                                    out.add(new ContextItemImpl(name, ctxPath, value));
//                                    }
                                    return true;
                                }
                            });
                        }
                    }

                    return out.toArray(new ContextItem[out.size()]);
                } else {
                    // package specification name
                }
                break;
            }
            case SYNTETIC_TYPE: {
                final ContextItem[] item = new ContextItem[1];
                itree.iterateTopNodes(name, new IndexEntriesWalkerInterruptable() {
                    public boolean process(String ctxPath, String value) {
                        ContextPathUtil.CtxPathParser parser = new ContextPathUtil.CtxPathParser(ctxPath);
                        switch (parser.extractLastCtxType()) {
                            case ContextPath.COLLECTION_TYPE:
                            case ContextPath.RECORD_TYPE:
                            case ContextPath.VARRAY_TYPE:
                            case ContextPath.OBJECT_TYPE:
                                item[0] = new ContextItemImpl(name, ctxPath, value);
                                return false;
                        }
                        return true;
                    }
                });
                return item[0] == null ? new ContextItem[0] : item;
            }
        }

        // top node name
        return itree.findCtxItems(new int[]{indexType}, name);
    }

    private class ContextItemImpl implements ContextItem {
        String name;
        String ctxPath;
        String value;

        public ContextItemImpl(String name, String ctxPath, String value) {
            this.name = name;
            this.ctxPath = ctxPath;
            this.value = value;
        }

        public String getCtxPath() {
            return ctxPath;
        }

        public String getValue() {
            return value;
        }

        public int hashCode() {
            return (name + value).hashCode();
        }

        public boolean equals(Object e) {
            return e instanceof ContextItemImpl
                    && (name + value).equals(((ContextItemImpl) e).name + ((ContextItemImpl) e).value);
        }
    }


    private class IndexManagerListenerImpl implements IndexManagerListener {
        public void indexAdded(final DbUrl dbUrl) {
            ApplicationManager.getApplication().invokeLater(new Runnable() {
                public void run() {

                    AbstractSchema schema = manager.getIndex(dbUrl, 0);
                    if (schema == null) {
                        deleteCache(dbUrl);
                    } else {
                        updateCache(schema, null);
                    }
                }
            });
        }

        public void indexRemoved(final DbUrl dbUrl) {
            ApplicationManager.getApplication().invokeLater(new Runnable() {
                public void run() {

                    AbstractSchema schema = manager.getIndex(dbUrl, 0);
                    if (schema == null) {
                        deleteCache(dbUrl);
                    } else {
                        updateCache(schema, null);
                    }
                }
            });
        }
    }


    // service methods
    private DbUrl extractDbUrlFromKey(String key) {
        return DbUrl.parse(key.split("\\|")[0]);
    }

    private int extractTypeFromKey(String key) {
        String t = key.split("\\|")[1];
        int type = ContextPathUtil.dbType2ctxType(t.toUpperCase());
        if (type != -1) {
            return type;
        } else if ("TYPE".equalsIgnoreCase(t)) {
            // a trick to work around mismatch DbObject.TYPE and ContextPath types 
            return SYNTETIC_TYPE;
        } else {
            return -1;
        }
    }

    private String buildKey(DbUrl dbUrl, String type) {
        return (dbUrl.getFullUrl() + "|" + type).toLowerCase();
    }

    private String[] findKeysMatchTo(DbUrl dbUrl) {
        String url = dbUrl.getUserHostPortServiceName().toLowerCase();
        List<String> findings = new ArrayList<String>();
        for (String key : key2names.keySet()) {
            if (key.startsWith(url + "|")) {
                findings.add(key);
            }
        }

        return findings.toArray(new String[findings.size()]);
    }


    class NavigationItemProxy implements NavigationItemEx, InvocationHandler {

        String name;
        String ctxPath;
        ItemPresentation itemP;
        DbUrl dbUrl;

        public NavigationItemProxy(DbUrl dbUrl, String name, String ctxPath, ItemPresentation itemP) {
            this.dbUrl = dbUrl;
            this.name = name;
            this.ctxPath = ctxPath;
            this.itemP = itemP;
        }

        public String getName() {
            return name;
        }

        public ItemPresentation getPresentation() {
            return itemP;
        }

        public FileStatus getFileStatus() {
            return FileStatus.NOT_CHANGED;
        }

        public void navigate(boolean requestFocus) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public boolean canNavigate() {
            return false;
        }

        public boolean canNavigateToSource() {
            return false;
        }

        public String getSchemaAlias() {
            if (dbUrl.equals(IndexManager.FS_URL)) {
                // source from local FS
                return project.getName();
            }
            return dbUrl.getAlias();
        }

        public PsiElement loadPhisicalElement() {
            return PlSqlElementLocator.locatePsiElement(project, dbUrl, ctxPath);
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class clazz = method.getDeclaringClass();

            if (clazz.getName().equals(NavigationItem.class.getName())) {
                return method.invoke(this, args);
            } else if (clazz.getName().equals(NavigationItemEx.class.getName())) {
                return method.invoke(this, args);
            } else if (clazz.getName().equals(Object.class.getName())) {
                return method.invoke(this, args);
            }
            if (method.getName().equals("getContainingFile")) {
                return null;
            } else if (method.getName().equals("getContext")) {
                return null;
            } else if (method.getName().equals("isValid")) {
                return true;
            } else if (method.getName().equals("getProject")) {
                return project;
            } else if (method.getName().equals("isWritable")) {
                return true;
            } else if (method.getName().equals("isPhysical")) {
                return false; //true;
            } else if (method.getName().equals("getIcon")) {
                return itemP.getIcon(true);
            }

            return null;
        }
    }
}
