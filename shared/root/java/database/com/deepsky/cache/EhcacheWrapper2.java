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

package com.deepsky.cache;

import com.deepsky.database.ora.VersionMarker;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.bootstrap.BootstrapCacheLoader;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EhcacheWrapper2 {

    static LoggerProxy log = LoggerProxy.getInstance(EhcacheWrapper2.class.getClass().getName());

    final private static String PREFIX_LIST = "PREFIX_LIST";

    private String storeDir;
    private String fileName;

    private BootstrapCacheLoader bootstrapCacheLoader;
    private int maxElementsInMemory = 50;
    CacheManager manager;
    Cache c1;

    private final Object sync = new Object();
    List<GenericCacheImpl> cacheList = new ArrayList<GenericCacheImpl>();

    Map<String, String> longname2prefix = new HashMap<String, String>();

    public void init() {

        System.setProperty("ehcache.disk.store.dir", storeDir);

        InputStream fis = this.getClass().getClassLoader().getResourceAsStream("resources/ehcache.xml");
        if (fis == null) {
            throw new ConfigurationException("Could not locate ehcache.xml file");
        }

        try {
            c1 = new Cache(fileName,
                    maxElementsInMemory,
                    MemoryStoreEvictionPolicy.LRU /* memoryStoreEvictionPolicy */,
                    true /* overflowToDisk */,
                    storeDir /* diskStorePath */,
                    false /* eternal */,
                    0 /* timeToLiveSeconds */,
                    0 /* timeToIdleSeconds */,
                    true /* diskPersistent */,
                    240 /* diskExpiryThreadIntervalSeconds */,
                    null /* registeredEventListeners */,
                    bootstrapCacheLoader /* bootstrapCacheLoader */);

            manager = CacheManager.create(fis);
            manager.addCache(c1);

            Element element = c1.get(PREFIX_LIST);
            if (element == null) {
                c1.put(new Element(PREFIX_LIST, longname2prefix));
            } else {
                longname2prefix = (Map<String, String>) element.getValue();
            }

        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                // looks strange
            }
        }
    }

    public void shutdown() {
        manager.shutdown();
    }

    public String getStoreDir() {
        return storeDir;
    }

    public void setStoreDir(String storeDir) {
        if (storeDir == null || !new File(storeDir).exists()) {
            throw new ConfigurationException("Store directory not accessible: " + storeDir);
        }

        this.storeDir = storeDir;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getMaxElementsInMemory() {
        return maxElementsInMemory;
    }

    public void setMaxElementsInMemory(int maxElementsInMemory) {
        this.maxElementsInMemory = maxElementsInMemory;
    }

    public synchronized GenericCache getCache(String longname) {
        String prefix = longname2prefix.get(longname.toUpperCase());
        if (prefix == null) {
            // create entry
            prefix = generatePrefix(longname.toUpperCase());
            longname2prefix.put(longname.toUpperCase(), prefix);
            c1.put(new Element(PREFIX_LIST, longname2prefix));
            c1.flush();
        }

        // todo -- redandunt GenericCacheImpl object may be created  
        GenericCacheImpl gcache = new GenericCacheImpl(prefix);
        cacheList.add(gcache);
        return gcache;
    }

    public synchronized void removeCache(String longname) {
        String prefix = longname2prefix.get(longname.toUpperCase());
        if (prefix == null) {
            GenericCacheImpl gcache = new GenericCacheImpl(prefix);
            gcache.removeAll();

            gcache = null; // small helpo GC

            // remove from entry set
            longname2prefix.remove(longname.toUpperCase());
            c1.put(new Element(PREFIX_LIST, longname2prefix));
            c1.flush();
        }
    }

    public String[] getCacheNames() {
        return longname2prefix.keySet().toArray(new String[0]);
    }

    private String generatePrefix(String longname) {
        List<Integer> numbers = new ArrayList<Integer>();
        for (String hex : longname2prefix.values()) {
            int num = Integer.parseInt(hex, 16);
            numbers.add(num);
        }

        int out = 0;
        Collections.sort(numbers);
        for (Integer o : numbers) {
            if (out == o) {
                out++;
            } else {
                // number found
                break;
            }
        }
        String hex = Integer.toString(out, 16);
        return hex.length() == 1 ? "0" + hex : hex;
    }

    public void flush() {
        c1.flush();
    }

    public int size() {
        return c1.getSize();
    }

    public void setBootstrapCacheLoader(BootstrapCacheLoader bootstrapCacheLoader) {
        this.bootstrapCacheLoader = bootstrapCacheLoader;
    }

    public void removeAll() {
        c1.removeAll();
    }

    public String getCacheIdFor(String name) {
        return longname2prefix.get(name.toUpperCase());
    }


    class GenericCacheImpl implements GenericCache {

        String prefix;

        public GenericCacheImpl(String prefix) {
            this.prefix = prefix;
        }

        public Object get(String name) {
            String key = prefix + name;
            Element element = c1.get(key);
            if (element == null) {
                return null;
            } else {
                return element.getValue();
            }
        }

        public List<String> getKeys() {
            List<String> out = new ArrayList<String>();
            for (Object o : c1.getKeys()) {
                String key = (String) o;
                if (key.startsWith(prefix)) {
                    out.add(key.substring(prefix.length(), key.length()));
                }
            }
            return out;
        }

        public void update(String name, Object dbo) {
            String key = prefix + name;
            c1.put(new Element(key, dbo));
        }

        public void remove(String name) {
            String key = prefix + name;
            c1.remove(key);
        }

        public void removeAll() {
            for (Object _key : c1.getKeys()) {
                String key = (String) _key;
                if (key.startsWith(prefix)) {
                    c1.remove(_key);
                }
            }
            c1.flush();
        }

        public void flush() {
            c1.flush();
        }

        public void close() {
            synchronized (sync) {
                cacheList.remove(this);
                c1.flush();
            }
        }
    }

}
