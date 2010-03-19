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

package com.deepsky.database;

import com.deepsky.lang.common.PluginKeys;
import com.intellij.openapi.diagnostic.Logger;

public class ObjectCacheFactory {

    //private static final Logger log = Logger.getInstance("#ObjectCacheFactory");

    //final static String OBJECT_CACHE_CLASS = "object.cache.class";
    //static ObjectCache instance;

    

/*
    public static synchronized ObjectCache getObjectCache() {
        // todo - dirty workaround !!!
        return PluginKeys.OBJECT_CACHE.getData();
//        return ConnectionManagerImpl.getInstance().cache;

//        if (instance != null) {
//            return instance;
//        } else {
//            String cacheClazz = System.getProperty(OBJECT_CACHE_CLASS);
//            if (cacheClazz != null) {
//                // try to instantuate of the cache
//                try {
//                    Class cache = Class.forName(cacheClazz);
//                    Object o = cache.newInstance();
//
//                    instance = (ObjectCache) o;
//                    return instance;
//                } catch (ClassNotFoundException e) {
//                    log.info("[ERROR] Exception on Object Cache creating: " + e.getMessage());
//                } catch (IllegalAccessException e) {
//                    log.info("[ERROR] Exception on Object Cache creating: " + e.getMessage());
//                } catch (InstantiationException e) {
//                    log.info("[ERROR] Exception on Object Cache creating: " + e.getMessage());
//                } catch (ClassCastException e) {
//                    log.info("[ERROR] Exception on Object Cache creating: " + e.getMessage());
//                }
//                log.info("[WARN] Default implementation of Object Cache will be used");
//            }
//
//            // use default cache
//            instance = null; //new InMemoryObjectCache();
//            return instance;
//        }
    }
*/

}
