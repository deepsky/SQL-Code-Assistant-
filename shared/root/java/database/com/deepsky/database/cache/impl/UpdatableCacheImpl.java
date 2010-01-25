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

package com.deepsky.database.cache.impl;

import com.deepsky.cache.GenericCache;
import com.deepsky.database.cache.UpdatableCache;
import com.deepsky.database.cache.UpdatableSourceTextProvider;
import com.deepsky.database.ora.DbObjectKey;
import com.deepsky.database.ora.ItemToUpdate;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.index.BuildIndexException;
import com.deepsky.lang.plsql.struct.index.SqlTextIndexer;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class UpdatableCacheImpl implements UpdatableCache {

    GenericCache ehcache;
    UpdatableSourceTextProvider textProvider;

    // todo -- review using of the PersistableMap instead of memory map
    Map<DbObjectKey, Date> fastList = new HashMap<DbObjectKey, Date>();
    Map<String, Date> lastDDLTimeForType = new HashMap<String, Date>();

    public UpdatableCacheImpl(GenericCache ehcache, UpdatableSourceTextProvider textProvider) {
        this.ehcache = ehcache;
        this.textProvider = textProvider;

        // populate fastlist
        for (String key : ehcache.getKeys()) {
            // parse key
            try {
                Key k = string2key(key);
                if (k instanceof IndexKey) {
                    // index
                    // todo -- make sure the index is not dangling
                } else {
                    // db object
                    DbObjectKey d = new DbObjectKey(k.type, k.name);
                    DbObject dbo = (DbObject) ehcache.get(key);
                    if (dbo == null) {
                        // problem with cache storage?
                        // remove key
                        ehcache.remove(key);
                    } else {

                        Date d1 = dbo.getLastDDLTime();
                        fastList.put(d, d1);

                        Date d0 = lastDDLTimeForType.get(k.type);
                        if (d0 == null || d0.before(d1)) {
                            lastDDLTimeForType.put(k.type, d1);
                        }
                    }
                }
            } catch (ConfigurationException e) {
            }
        }

        // ----
    }

    class Key {
        String type;
        String name;

        public Key(String type, String name) {
            this.type = type;
            this.name = name;
        }
    }

    class IndexKey extends Key {
        public IndexKey(String type, String name) {
            super(type, name);
        }
    }

    private final String IDX_PREFIX = "idx@";

    private Key string2key(String key) {
        String[] rr = key.split(":");
        if (rr.length != 2) {
            throw new ConfigurationException("Cannot parse cached values.");
        }

        if (rr[0].startsWith(IDX_PREFIX)) {
            return new IndexKey(rr[0].substring(4, rr[0].length()), rr[1]);
        } else {
            return new Key(rr[0], rr[1]);
        }
    }

    private String key2string(String type, String name) {
        return type.toUpperCase() + ":" + name.toUpperCase();
    }

    private String indexkey2string(String type, String name) {
        return IDX_PREFIX + type.toUpperCase() + ":" + name.toUpperCase();
    }

    public void update(String type, String name, DbObject dbo) {
        String key = key2string(type, name);
        ehcache.update(key, dbo);

        DbObjectKey dbk = new DbObjectKey(type, name);
        fastList.put(dbk, dbo.getLastDDLTime());

        Date d = lastDDLTimeForType.get(type);
        if (d == null || d.before(dbo.getLastDDLTime())) {
            lastDDLTimeForType.put(type, dbo.getLastDDLTime());
        }
    }

    public void remove(String type, String name) {
        // remove db object
        String key = key2string(type, name);
        ehcache.remove(key);

        // remove text
        textProvider.remove(name, type);

        // remove object from fastlist
        DbObjectKey dbk = new DbObjectKey(type, name);
        fastList.remove(dbk);
    }

    public DbObject get(String name, String type) {
        String key = key2string(type, name);
        return (DbObject) ehcache.get(key);
    }

    public void updateText(String type, String name, String text) {
        try {
            String index = SqlTextIndexer.buildIndex(text);
            textProvider.put(name, type, text);

            String ikey = indexkey2string(type, name);
            ehcache.update(ikey, index);

        } catch (BuildIndexException e) {
            // todo -- ??
        }
    }

//    public void removeText(String type, String name) {
//        textProvider.remove(name, type);
//    }

    public String getTextSource(String name, String type) {
        return textProvider.getText(name, type);
    }

    @NotNull
    public List<String> getNames(String type) {
        List<String> out = new ArrayList<String>();

        // todo - time consuming task !!!
        for (DbObjectKey e : fastList.keySet()) {
            if (e.getType().equalsIgnoreCase(type)) {
                out.add(e.getName());
            }
        }
        return out;
    }

    @NotNull
    public List<ItemToUpdate> getObjectList(String schemaName, String type) {
        List<ItemToUpdate> out = new ArrayList<ItemToUpdate>();
        for (Map.Entry<DbObjectKey, Date> e : fastList.entrySet()) {
            if (e.getKey().getType().equalsIgnoreCase(type)) {
                out.add(new ItemToUpdate(
                        schemaName,
                        e.getKey().getName(),
                        e.getKey().getType(),
                        new java.sql.Date(e.getValue().getTime()))
                );
            }
        }
        return out;
    }

    public Date lastDDLTimeForType(String type) {
        return lastDDLTimeForType.get(type);
    }

    public Date objectDDLTime(String type, String name) {
        return fastList.get(new DbObjectKey(type, name));
    }

    @NotNull
    public String[] listTypes() {
        return lastDDLTimeForType.keySet().toArray(new String[0]);
    }

    public int size() {
        return fastList.size();
    }

    public void removeAll() {
        // todo --
    }

    public void flush() {
        ehcache.flush();
    }

    public void close() {
        ehcache.close();
    }

    @NotNull
    public List<NameTypePair> searchContentIndex(String[] types, String word) {
        List<NameTypePair> out = new ArrayList<NameTypePair>();
        List<String> _types = Arrays.asList(types);
        for (DbObjectKey key : fastList.keySet()) {
            if (_types.contains(key.getType())) {
                String ikey = indexkey2string(key.getType(), key.getName());
                String index = (String) ehcache.get(ikey);
                if (index != null) {
                    if (index.indexOf(word.toLowerCase()) >= 0) {
                        //
                        out.add(new NameTypePairImpl(key.getType(), key.getName()));
                    }
                }
            }
        }
        return out;
    }

    class NameTypePairImpl implements NameTypePair {

        String type;
        String name;

        public NameTypePairImpl(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }
}
