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
import com.deepsky.database.cache.Cache;
import com.deepsky.database.cache.SourceTextProvider;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.struct.DbObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheImpl implements Cache {

    GenericCache ehcache;
    SourceTextProvider textProvider;

    Map<String, List<String>> type2name = new HashMap<String, List<String>>();
    int size = 0;

    public CacheImpl(GenericCache ehcache, SourceTextProvider textProvider) {
        this.ehcache = ehcache;
        this.textProvider = textProvider;

        // populate fastlist
        for (String key : ehcache.getKeys()) {
            // parse key
            try {
                Key k = string2key(key);
                List<String> names = type2name.get(k.type);
                if (names == null) {
                    names = new ArrayList<String>();
                    type2name.put(k.type, names);
                }

                names.add(k.name);
                size++;

            } catch (ConfigurationException e) {
            }
        }
    }

    class Key {
        String type;
        String name;

        public Key(String type, String name) {
            this.type = type;
            this.name = name;
        }
    }

    private Key string2key(String key) {
        String[] rr = key.split(":");
        if (rr.length != 2) {
            throw new ConfigurationException("Cannot parse cached values.");
        }
        return new Key(rr[0], rr[1]);
    }

    private String key2string(Key key) {
        return key.type.toUpperCase() + ":" + key.name.toUpperCase();
    }

    private String key2string(String type, String name) {
        return type.toUpperCase() + ":" + name.toUpperCase();
    }

    public DbObject get(String name, String type) {
        String key = key2string(type, name);
        return (DbObject) ehcache.get(key);
    }

    public String getTextSource(String name, String type) {
        return textProvider.getText(name, type);
    }

    @NotNull
    public List<String> getNames(String type) {
        List<String> out = type2name.get(type.toUpperCase());
        if (out == null) {
            return new ArrayList<String>();
        } else {
            return out;
        }
    }

    @NotNull
    public String[] listTypes() {
        return type2name.keySet().toArray(new String[0]);
    }

    public int size() {
        return size;
    }

    public void close() {
        ehcache.close();
    }

    @NotNull
    public List<NameTypePair> searchContentIndex(String[] types, String word) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
