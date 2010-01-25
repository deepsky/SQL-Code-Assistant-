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

package com.deepsky.database.ora;

import com.deepsky.database.cache.Cache;
import com.deepsky.lang.plsql.castor.mapping.SystemFunctionLoader;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.database.*;
import com.intellij.util.containers.HashMap;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.*;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class OraObjectCache3 implements ObjectCache, CacheManagerListener {

    List<StateListener> lnrs = new ArrayList<StateListener>();
    CacheManager cacheManager;

    Map<String, List<SystemFunctionDescriptor>> sysFuncs;
    Map<String, Cache> caches = new HashMap<String, Cache>();

    boolean isReady = false;

    public OraObjectCache3(CacheManager cacheManager){

        this.cacheManager = cacheManager;
        // todo -- memory leak possible, listener is not disposed!
        cacheManager.addListener(this);

        // 1. load definitions of the system functions
        InputStream istream = this.getClass().getClassLoader().getResourceAsStream("resources/sysfunc_list.xml");
        if (istream != null) {
            Reader r = new InputStreamReader(istream);
            SystemFunctionDescriptor[] lst = SystemFunctionLoader.load(r);

            sysFuncs = new HashMap<String, List<SystemFunctionDescriptor>>();
            for (SystemFunctionDescriptor fdesc : lst) {
                List<SystemFunctionDescriptor> _lst = sysFuncs.get(fdesc.getName());
                if (_lst == null) {
                    _lst = new ArrayList<SystemFunctionDescriptor>();
                    sysFuncs.put(fdesc.getName(), _lst);
                }
                _lst.add(fdesc);
            }
        }

        // 2. laod definition of DUAL table
        // todo -
    }

    public void close(){
        cacheManager.removeListener(this);        
    }

    public void addStateListener(StateListener l) {
        lnrs.add(l);
    }

    public boolean isReady() {
        return isReady;
    }

    // lightweight query methods
    @NotNull
    public String[] getNameListForType(String user, int type) {
        if (isReady) {
            List<String> out = new ArrayList<String>();
            Cache c = caches.get(user);
            if(c != null){
                if ((type & ObjectCache.TABLE) != 0) {
                    out.addAll(c.getNames(DbObject.TABLE));
                }

                if ((type & ObjectCache.VIEW) != 0) {
                    out.addAll(c.getNames(DbObject.VIEW));
                }

                if ((type & ObjectCache.PACKAGE) != 0) {
                    out.addAll(c.getNames(DbObject.PACKAGE));
                }

                if ((type & ObjectCache.PACKAGE_BODY) != 0) {
                    out.addAll(c.getNames(DbObject.PACKAGE_BODY));
                }

                if ((type & ObjectCache.SEQUENCE) != 0) {
                    out.addAll(c.getNames(DbObject.SEQUENCE));
                }

                if ((type & ObjectCache.USER_DEFINED_TYPE) != 0) {
                    out.addAll(c.getNames(DbObject.TYPE));
                }
            }
            return out.toArray(new String[out.size()]);
        }
        return new String[0];
    }

    @NotNull
    public String[] findByNamePrefix2(String schema, int objType, String text) {
        List<String> dbo = new ArrayList<String>();

        if ((objType & ObjectCache.EMBEDDED_FUNCTION) != 0) {
            for(List<SystemFunctionDescriptor> f: sysFuncs.values()){
                if (f.get(0).getName().toUpperCase().startsWith(text.toUpperCase())) {
                    // todo - full signature should be added
                    dbo.add(f.get(0).getName());
                }
            }
        }
        if (!isReady) {
            return dbo.toArray(new String[dbo.size()]);
        }

        Cache cache = caches.get(schema.toUpperCase());
        if (cache != null) {
            if ((objType & ObjectCache.TABLE) != 0) {
                for (String name : cache.getNames(DbObject.TABLE)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(name);
                    }
                }
            }

            if ((objType & ObjectCache.VIEW) != 0) {
                for (String name : cache.getNames(DbObject.VIEW)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(name);
                    }
                }
            }

            if ((objType & ObjectCache.USER_FUNCTION) != 0) {
                for (String name : cache.getNames(DbObject.FUNCTION)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(name);
                    }
                }
            }

            if ((objType & ObjectCache.USER_PROCEDURE) != 0) {
                for (String name : cache.getNames(DbObject.PROCEDURE)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(name);
                    }
                }
            }

            if ((objType & ObjectCache.PACKAGE) != 0) {
                for (String name : cache.getNames(DbObject.PACKAGE)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(name);
                    }
                }
            }

            if ((objType & ObjectCache.PACKAGE_BODY) != 0) {
                for (String name : cache.getNames(DbObject.PACKAGE_BODY)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(name);
                    }
                }
            }
        }

        return dbo.toArray(new String[dbo.size()]);
    }


    @NotNull
    public String[] findByNamePrefix2(int type, String prefix) {

        List<String> out = new ArrayList<String>();
        if ((type & ObjectCache.EMBEDDED_FUNCTION) != 0) {
            for(List<SystemFunctionDescriptor> f: sysFuncs.values()){
                if (f.get(0).getName().toUpperCase().startsWith(prefix.toUpperCase())) {
                    // todo - full signature should be added
                    out.add(f.get(0).getName());
                }
            }
        }

        if (isReady) {
            if ((type & ObjectCache.TABLE) != 0) {
                for (Cache cache : caches.values()) {
                    for (String name : cache.getNames(DbObject.TABLE)) {
                        if (name.startsWith(prefix.toUpperCase())) {
                            out.add(name);
                        }
                    }
                }
            }

            if ((type & ObjectCache.VIEW) != 0) {
                for (Cache cache : caches.values()) {
                    for (String name : cache.getNames(DbObject.VIEW)) {
                        if (name.startsWith(prefix.toUpperCase())) {
                            out.add(name);
                        }
                    }
                }
            }

            if ((type & ObjectCache.USER_FUNCTION) != 0) {
                for (Cache cache : caches.values()) {
                    for (String name : cache.getNames(DbObject.FUNCTION)) {
                        if (name.startsWith(prefix.toUpperCase())) {
                            out.add(name);
                        }
                    }
                }
            }

            if ((type & ObjectCache.USER_PROCEDURE) != 0) {
                for (Cache cache : caches.values()) {
                    for (String name : cache.getNames(DbObject.PROCEDURE)) {
                        if (name.startsWith(prefix.toUpperCase())) {
                            out.add(name);
                        }
                    }
                }
            }
        }

        return out.toArray(new String[out.size()]);
    }

    void notifyLsnrs(StateListener.StateEvent state) {
        for (StateListener l : lnrs) {
            l.stateUpdated(state);
        }
    }

    public void refreshDbObject(int objType, String schema, String name) {
        if (isReady) {
            cacheManager.enqueueUpdate(objType, schema, name);
        }
    }

    public String getCurrentUser() {
        return cacheManager.getCurrentUser();
    }

//    public Cache getCacheCurrentUser() {
//        return caches.get(
//                cacheManager.getCurrentUser()
//        );
//    }

    public Cache getCache(String user) {
        return caches.get(user.toUpperCase());
    }

    public void preloadObject(int objType, String schema, String name) {
        // todo - must be fixed ASAP
        if (!isReady) {
            return;
        }

        if ((objType & ObjectCache.TABLE) != 0) {
            cacheManager.preloadTable(schema, name);
        } else if ((objType & ObjectCache.PACKAGE) != 0) {
            cacheManager.preloadPackage(schema, name);
        }
        // todo - need more types
    }

    void addWithCheck(List<DbObject> l, DbObject dbo) {
        if (dbo != null) {
            l.add(dbo);
        }
    }

    @NotNull
    public List<SystemFunctionDescriptor> findSystemFunction(String name) {
        List<SystemFunctionDescriptor> lst = sysFuncs.get(name.toUpperCase());
        if (lst != null) {
            return lst;
        } else {
            return new ArrayList<SystemFunctionDescriptor>();
        }
    }

    @NotNull
    public DbObject[] findByNameForType(int objType, String name) {

        if (!isReady) {
            return new DbObject[0];
        }

        List<DbObject> out = new ArrayList<DbObject>();

        // todo - it is supposed the first element in collection is an user schema, the second - SYS
        for (Cache cache : caches.values()) {
            if ((objType & ObjectCache.TABLE) != 0) {
                TableDescriptor td = (TableDescriptor) cache.get(name, DbObject.TABLE);
                addWithCheck(out, td);
            }

            if ((objType & ObjectCache.VIEW) != 0) {
                ViewDescriptor td = (ViewDescriptor) cache.get(name, DbObject.VIEW);
                addWithCheck(out, td);
            }

            if ((objType & ObjectCache.PACKAGE) != 0) {
                PackageDescriptor pdesc = (PackageDescriptor) cache.get(name, DbObject.PACKAGE);
                addWithCheck(out, pdesc);
            }

            if ((objType & ObjectCache.PACKAGE_BODY) != 0) {
                DbObject pdesc = cache.get(name, DbObject.PACKAGE_BODY);
                addWithCheck(out, pdesc);
            }

            if ((objType & ObjectCache.SEQUENCE) != 0) {
                SequenceDescriptor desc = (SequenceDescriptor) cache.get(name, DbObject.SEQUENCE);
                addWithCheck(out, desc);
            }

            if ((objType & ObjectCache.USER_DEFINED_TYPE) != 0) {
                UserDefinedTypeDescriptor desc = (UserDefinedTypeDescriptor) cache.get(name, DbObject.TYPE);
                addWithCheck(out, desc);
            }

            // check for synonym name
            if (out.size() == 0 && (objType & (ObjectCache.TABLE | ObjectCache.VIEW)) != 0) {
                DbObject syn = null;
                if ((syn = cache.get(name, DbObject.SYNONYM)) != null) {
                    SynonymDescriptor synDesc = (SynonymDescriptor) syn;
                    DbObject referenced;
                    if ((referenced = cache.get(synDesc.getReferencedTableName(), DbObject.VIEW)) != null) {
                        ViewDescriptor viewDesc = (ViewDescriptor) referenced;
                        InvocationHandler handler = new ViewInvocationHandler(name, viewDesc);
                        DbObject f = (DbObject) Proxy.newProxyInstance(DbObject.class.getClassLoader(),
                                new Class[]{SynonymDescriptor.class, ViewDescriptor.class},
                                handler);
                        addWithCheck(out, f);
                    } else if ((referenced = cache.get(synDesc.getReferencedTableName(), DbObject.TABLE)) != null) {
                        TableDescriptor viewDesc = (TableDescriptor) referenced;
                        InvocationHandler handler = new TableInvocationHandler(name, viewDesc);

                        DbObject f = (DbObject) Proxy.newProxyInstance(DbObject.class.getClassLoader(),
                                new Class[]{SynonymDescriptor.class, TableDescriptor.class},
                                handler);
                        addWithCheck(out, f);
                    }
                }
            }

            if (out.size() > 0) {
                // do not search SYS schema if specified target was found
                break;
            }
        }

        if ((objType & ObjectCache.EMBEDDED_FUNCTION) != 0) {
            List<SystemFunctionDescriptor> func = findSystemFunction(name);
            out.addAll(func);
        }

        return out.toArray(new DbObject[out.size()]);
    }

    @NotNull
    public DbObject[] findByNameForType(String schema, int objType, String name) {
        List<DbObject> out = new ArrayList<DbObject>();

        Cache cache = caches.get(schema.toUpperCase());
        if (cache != null) {
            if ((objType & ObjectCache.TABLE) != 0) {
                TableDescriptor td = (TableDescriptor) cache.get(name, DbObject.TABLE);
                addWithCheck(out, td);
            }

            if ((objType & ObjectCache.PACKAGE) != 0) {
                PackageDescriptor pdesc = (PackageDescriptor) cache.get(name, DbObject.PACKAGE);
                addWithCheck(out, pdesc);
            }

            if ((objType & ObjectCache.PACKAGE_BODY) != 0) {
                DbObject pdesc = cache.get(name, DbObject.PACKAGE_BODY);
                addWithCheck(out, pdesc);
            }

            if ((objType & ObjectCache.SEQUENCE) != 0) {
                SequenceDescriptor desc = (SequenceDescriptor) cache.get(name, DbObject.SEQUENCE);
                addWithCheck(out, desc);
            }

            if ((objType & ObjectCache.USER_DEFINED_TYPE) != 0) {
                UserDefinedTypeDescriptor desc = (UserDefinedTypeDescriptor) cache.get(name, DbObject.TYPE);
                addWithCheck(out, desc);
            }

            // check for synonym name
            if (out.size() == 0 && (objType & (ObjectCache.TABLE | ObjectCache.VIEW)) != 0) {
                DbObject syn = null;
                if ((syn = cache.get(name, DbObject.SYNONYM)) != null) {
                    SynonymDescriptor synDesc = (SynonymDescriptor) syn;
                    DbObject referenced;
                    if ((referenced = cache.get(synDesc.getReferencedTableName(), DbObject.VIEW)) != null) {
                        ViewDescriptor viewDesc = (ViewDescriptor) referenced;
                        InvocationHandler handler = new ViewInvocationHandler(name, viewDesc);
                        DbObject f = (DbObject) Proxy.newProxyInstance(DbObject.class.getClassLoader(),
                                new Class[]{SynonymDescriptor.class, ViewDescriptor.class},
                                handler);
                        addWithCheck(out, f);
                    } else if ((referenced = cache.get(synDesc.getReferencedTableName(), DbObject.TABLE)) != null) {
                        TableDescriptor viewDesc = (TableDescriptor) referenced;
                        InvocationHandler handler = new TableInvocationHandler(name, viewDesc);

                        DbObject f = (DbObject) Proxy.newProxyInstance(DbObject.class.getClassLoader(),
                                new Class[]{SynonymDescriptor.class, TableDescriptor.class},
                                handler);
                        addWithCheck(out, f);
                    }
                }
            }
        }

        if ((objType & ObjectCache.EMBEDDED_FUNCTION) != 0) {
            List<SystemFunctionDescriptor> func = findSystemFunction(name);
            out.addAll(func);
        }

        return out.toArray(new DbObject[out.size()]);
    }

//    public List<FunctionDescriptor> getSystemFunction(String name) {
//        List<SystemFunctionDescriptor> lst = sysFuncs.get(name.toUpperCase());
//        if (lst != null) {
//            return lst;
//        } else {
//            return new ArrayList<FunctionDescriptor>();
//        }
//    }

    int findByNameForType = 0;
    int findByNamePrefix = 0;


    @NotNull
    public DbObject[] findByNamePrefix(int objType, String text) {
        List<DbObject> dbo = new ArrayList<DbObject>();

        if ((objType & ObjectCache.EMBEDDED_FUNCTION) != 0) {
            for(List<SystemFunctionDescriptor> f: sysFuncs.values()){
                if (f.get(0).getName().toUpperCase().startsWith(text.toUpperCase())) {
                    // todo - full signature should be added
                    dbo.add(f.get(0));
                }
            }
        }
        if (!isReady) {
            return dbo.toArray(new DbObject[dbo.size()]);
        }

        if ((objType & ObjectCache.TABLE) != 0) {
            for (Cache cache : caches.values()) {
                for (String name : cache.getNames(DbObject.TABLE)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(cache.get(name, DbObject.TABLE));
                    }
                }
            }
        }

        if ((objType & ObjectCache.VIEW) != 0) {
            for (Cache cache : caches.values()) {
                for (String name : cache.getNames(DbObject.VIEW)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(cache.get(name, DbObject.VIEW));
                    }
                }
            }
        }

        if ((objType & ObjectCache.USER_FUNCTION) != 0) {
            for (Cache cache : caches.values()) {
                for (String name : cache.getNames(DbObject.FUNCTION)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(cache.get(name, DbObject.FUNCTION));
                    }
                }
            }
        }

        if ((objType & ObjectCache.USER_PROCEDURE) != 0) {
            for (Cache cache : caches.values()) {
                for (String name : cache.getNames(DbObject.PROCEDURE)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(cache.get(name, DbObject.PROCEDURE));
                    }
                }
            }
        }

        return dbo.toArray(new DbObject[dbo.size()]);
    }


    @NotNull
    public DbObject[] findByNamePrefix(String schema, int objType, String text) {
        List<DbObject> dbo = new ArrayList<DbObject>();

        if ((objType & ObjectCache.EMBEDDED_FUNCTION) != 0) {
            for(List<SystemFunctionDescriptor> f: sysFuncs.values()){
                if (f.get(0).getName().toUpperCase().startsWith(text.toUpperCase())) {
                    // todo - full signature should be added
                    dbo.add(f.get(0));
                }
            }
        }
        if (!isReady) {
            return dbo.toArray(new DbObject[dbo.size()]);
        }

        Cache cache = caches.get(schema.toUpperCase());
        if (cache != null) {
            if ((objType & ObjectCache.TABLE) != 0) {
                for (String name : cache.getNames(DbObject.TABLE)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(cache.get(name, DbObject.TABLE));
                    }
                }
            }

            if ((objType & ObjectCache.VIEW) != 0) {
                for (String name : cache.getNames(DbObject.VIEW)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(cache.get(name, DbObject.VIEW));
                    }
                }
            }

            if ((objType & ObjectCache.USER_FUNCTION) != 0) {
                for (String name : cache.getNames(DbObject.FUNCTION)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(cache.get(name, DbObject.FUNCTION));
                    }
                }
            }

            if ((objType & ObjectCache.USER_PROCEDURE) != 0) {
                for (String name : cache.getNames(DbObject.PROCEDURE)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(cache.get(name, DbObject.PROCEDURE));
                    }
                }
            }

            if ((objType & ObjectCache.PACKAGE) != 0) {
                for (String name : cache.getNames(DbObject.PACKAGE)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(cache.get(name, DbObject.PACKAGE));
                    }
                }
            }

            if ((objType & ObjectCache.PACKAGE_BODY) != 0) {
                for (String name : cache.getNames(DbObject.PACKAGE_BODY)) {
                    if (name.startsWith(text.toUpperCase())) {
                        dbo.add(cache.get(name, DbObject.PACKAGE_BODY));
                    }
                }
            }
        }

        return dbo.toArray(new DbObject[dbo.size()]);
    }

    public void handleUpdate(int state) {
        if (CacheManagerListener.CACHE_UPDATED == state) {
            caches = cacheManager.getCaches();
            isReady = true;
        } else if (CacheManagerListener.STOPPED == state) {
            isReady = false;
        }
    }

    class TableInvocationHandler implements InvocationHandler {
        TableDescriptor tab;
        String synonym;

        public TableInvocationHandler(String synonym, TableDescriptor tab) {
            this.tab = tab;
            this.synonym = synonym;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("getName")) {
                return synonym;
            } else if (method.getName().equals("getReferencedTableName")) {
                return tab.getName();
            } else {
                return method.invoke(tab, args);
            }
        }
    }

    class ViewInvocationHandler implements InvocationHandler {
        ViewDescriptor view;
        String synonym;

        public ViewInvocationHandler(String synonym, ViewDescriptor view) {
            this.view = view;
            this.synonym = synonym;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("getName")) {
                return synonym;
            } else if (method.getName().equals("getReferencedTableName")) {
                return view.getName();
            } else {
                return method.invoke(view, args);
            }
        }
    }

//    public boolean isConnected() {
//        return conn != null && conn.isConnected();
//    }

//    public void setRefreshPeriod(int refreshPeriod) {
//        this.refreshPeriod = refreshPeriod;
//    }


}

