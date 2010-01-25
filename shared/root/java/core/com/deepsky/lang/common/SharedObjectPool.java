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

package com.deepsky.lang.common;

import com.intellij.openapi.util.UserDataHolder;
import com.intellij.openapi.util.Key;

import java.util.Map;
import java.util.HashMap;

public class SharedObjectPool {

    static final String ORACLE_LIB_PROP = "oracle.lib";
    static final String JDBC_DRIVER_PROP = "oracle.jdbc.driver";


    static Map<String, Object> userDataMap = new HashMap<String, Object>();

    static {
        // look into the system properties
        String lib = System.getProperty(ORACLE_LIB_PROP);
        String driver = System.getProperty(JDBC_DRIVER_PROP);

        if(lib != null){
            userDataMap.put(SharedConstants.ORACLE_JDBC_JAR_PATH, lib);
        }

        if(driver != null){
            userDataMap.put(SharedConstants.ORACLE_JDBC_DRIVER, driver);
        }
       
    }

    public static synchronized Object getUserData(String key){
        return userDataMap.get(key);
    }

    public static synchronized void putUserData(String key, Object o){
        userDataMap.put(key, o);
    }

}
