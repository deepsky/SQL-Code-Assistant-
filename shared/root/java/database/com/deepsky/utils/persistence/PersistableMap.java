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

package com.deepsky.utils.persistence;

import com.deepsky.database.ora.DbObjectKey;
import com.deepsky.lang.plsql.ConfigurationException;

import java.util.*;
import java.io.File;

public class PersistableMap {

    File saveDir;
    Map<DbObjectKey, Value> key2date = new HashMap<DbObjectKey, Value>();

    PersistableList2 o;
    PersistenceManager m;

    public PersistableMap(File saveDir, String baseLogName){
        this.saveDir = saveDir;
        m = new PersistenceManager();
        m.setTargetDir(saveDir);
        m.setBaseLogName(baseLogName);
        m.setLimit(5000);
        try {
            m.init();
        } catch (Exception e) {
            throw new ConfigurationException("");
        }
//        m.cleanupState();

        o = new PersistableList2();
        o.setManager(m);
        o.init();

        int idx = 0;
        for(Object i: o){
            if(i != null){
                Item item = (Item) i;
                key2date.put(item.key, new Value(item.date, idx));
            }
            idx++;
        }
    }


    public PersistableMap(File saveDir){
        this.saveDir = saveDir;
        m = new PersistenceManager();
        m.setTargetDir(saveDir);
        m.setBaseLogName(null);
        m.setLimit(5000);
        try {
            m.init();
        } catch (Exception e) {
            throw new ConfigurationException("");
        }
//        m.cleanupState();

        o = new PersistableList2();
        o.setManager(m);
        o.init();

        int idx = 0;
        for(Object i: o){
            Item item = (Item) i;
            key2date.put(item.key, new Value(item.date, idx));
            idx++;
        }
    }

    public int size(){
        return key2date.size();        
    }

    public void clear(){
        key2date.clear();
        o.clear();
    }

    public Date get(DbObjectKey key){
        Value v = key2date.get(key);
        return v!= null? v.date: null;
    }

    public Date remove(DbObjectKey key){
        Value v = key2date.remove(key);
        if(v != null){
            Date d = v.date;
            o.set(v.index, null);
            return d;
        } else {
            return null;
        }
    }

    public void put(DbObjectKey key, Date date){
        Value v = key2date.put(key, new Value(date, 1));

        // persist item
        if(v != null){
            o.set(v.index, new Item(key, date));
        } else {
            // search for an empty slot
            int h=0;
            for(Object o1: o){
                if(o1 == null){
                    o.set(h, new Item(key, date));
                    key2date.get(key).index = h;
                    return;
                }
                h++;
            }

            // slot not found, put a new item at the end
            int size = o.size();
            o.add(new Item(key, date));
            key2date.get(key).index = size;
        }
    }

    public void startBatch() {
        o.startBatch();
    }

    public void completeBatch() {
        o.completeBatch();
    }


    public static class Entry {
        DbObjectKey k;
        Date v;
        public Entry(DbObjectKey k, Date v){
            this.k = k;
            this.v = v;
        }
        public DbObjectKey key(){
            return k;
        }

        public Date value(){
            return v;
        }
    }

    public Set<Entry> entrySet() {
        Set out = new HashSet<Entry>();
        for(Map.Entry<DbObjectKey,Value> e: key2date.entrySet()){
            out.add(new Entry(e.getKey(), e.getValue().date));
        }
        return out;
    }

    class PersistableList2 extends PersistableList<Item> {
        protected String valueToString(Item value) {
            return value.toString();
        }
        protected Item stringToValue(String value) {
            return Item.fromString(value);
        }
    }

    class Value {
        public Date date;
        public int index;

        public Value(Date date, int index) {
            this.date = date;
            this.index = index;
        }
    }

    static class Item {
        public DbObjectKey key;
        public Date date;

        public Item(DbObjectKey key, Date date){
            this.key = key;
            this.date = date;
        }

        public String toString(){
            return key.getId() + ":" + date.getTime();
        }

        static public Item fromString(String v){
            String[] parts = v.split(":");

            if(parts.length != 3){
                throw new ConfigurationException("Could not parse key/date value");
            }

            return new Item(new DbObjectKey(parts[0], parts[1]), new Date(Long.parseLong(parts[2])));
        }
    }
}


