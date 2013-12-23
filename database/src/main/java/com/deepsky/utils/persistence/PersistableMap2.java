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

package com.deepsky.utils.persistence;

import com.deepsky.lang.plsql.ConfigurationException;


import java.io.File;
import java.util.*;

public class PersistableMap2 {

    File saveDir;
    Map<String, Value> key2date = new HashMap<String, Value>();

    PersistableList2 o;
    PersistenceManager m;

    public PersistableMap2(File saveDir, String baseLogName){
        this.saveDir = saveDir;
        m = new PersistenceManager();
        m.setTargetDir(saveDir);
        m.setBaseLogName(baseLogName);
        m.setLimit(5000);
        try {
            m.init();
        } catch (Exception e) {
            throw new ConfigurationException("", e);
        }
//        m.cleanupState();

        o = new PersistableList2();
        o.setManager(m);
        o.init();

        int idx = 0;
        for(Object i: o){
            if(i != null){
                Item item = (Item) i;
                key2date.put(item.key,new Value(item.data, idx));
            }
            idx++;
        }
    }


    public PersistableMap2(File saveDir){
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
            key2date.put(item.key, new Value(item.data, idx));
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

    public String get(String key){
        Value v = key2date.get(key);
        return v!= null? v.data: null;
    }

    public String remove(String key){
        Value v = key2date.remove(key);
        if(v != null){
            String d = v.data;
            o.set(v.index, null);
            return d;
        } else {
            return null;
        }
    }

    public void put(String key, String data){
        Value v = key2date.put(key, new Value(data, 1));

        // persist item
        if(v != null){
            o.set(v.index, new Item(key, data));
        } else {
            // search for an empty slot
            int h=0;
            for(Object o1: o){
                if(o1 == null){
                    o.set(h, new Item(key, data));
                    key2date.get(key).index = h;
                    return;
                }
                h++;
            }

            // slot not found, put a new item at the end
            int size = o.size();
            o.add(new Item(key, data));
            key2date.get(key).index = size;
        }
    }

    public Collection<String> values(){
        List<String> out = new ArrayList<String>();
        for(Value e: key2date.values()){
            out.add(e.data);
        }

        return out;
    }

    public void startBatch() {
        o.startBatch();
    }

    public void completeBatch() {
        o.completeBatch();
    }


    public static class Entry {
        String k;
        String v;
        public Entry(String k, String v){
            this.k = k;
            this.v = v;
        }
        public String key(){
            return k;
        }

        public String value(){
            return v;
        }
    }

    public Set<Entry> entrySet() {
        Set out = new HashSet<Entry>();
        for(Map.Entry<String,Value> e: key2date.entrySet()){
            out.add(new Entry(e.getKey(), e.getValue().data));
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
        public String data;
        public int index;

        public Value(String data, int index) {
            this.data =data;
            this.index = index;
        }
    }

    static class Item {
        public String key;
        public String data;

        public Item(String key, String date){
            this.key = key;
            this.data = date;
        }

        final static String MARKER = "<-$$$->";
        public String toString(){
/*
            StringBuilder out = new StringBuilder();
            for(int i=0; i<key.length(); i++){
                if(key.charAt(i) == ':'){
                    out.append(':');
                }
                out.append(key.charAt(i));
            }

            out.append(':');

            for(int i=0; i<data.length(); i++){
                if(data.charAt(i) == ':'){
                    out.append(':');
                }
                out.append(data.charAt(i));
            }

            return out.toString(); //key + ":" + data;
*/
            return key + MARKER + data;
        }

        static public Item fromString(String v){

            int end = v.indexOf(MARKER);
/*
            StringBuilder out = new StringBuilder();
            String key = "";
            String value = "";
            char last = '-';
            for(int i=0; i<v.length(); i++){
                if(last == ':'){
                    if(v.charAt(i) == ':'){
                        // skip extension
                        last = '-'; //v.charAt(i);
                    } else {
                        // separator found !!!
                        key = out.substring(0, out.length()-1);

                        // restart accum
                        out = new StringBuilder();
                        last = v.charAt(i);
                        out.append(last);
                    }
                } else {
                    out.append(v.charAt(i));
                    last = v.charAt(i);
                }
            }

            value = out.toString();
*/
            String key = v.substring(0, end);
            String value = v.substring(end + MARKER.length());
            if(key.length() == 0){
                throw new ConfigurationException("Could not parse key/date value");
            }

            return new Item(key, value);
        }



    }
}
