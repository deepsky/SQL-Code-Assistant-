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

package com.deepsky.database;

import com.deepsky.database.cache.Cache;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.SystemFunctionDescriptor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ObjectCache {

    final int TABLE = 1;
    final int VIEW = 2;
    final int USER_PROCEDURE = 4;
    final int USER_FUNCTION = 8;
    final int EMBEDDED_FUNCTION = 16;
    final int SEQUENCE = 32; // sequence name
    final int PACKAGE = 64;  // package spec
    final int PACKAGE_BODY = 1024;  // package body

    // user defined type, example:
    //      TYPE custr_sales_rtype IS RECORD (customer_id NUMBER(5), customer_name customer.name%TYPE, total_sales NUMBER (15,2));
    final int USER_DEFINED_TYPE = 128;
//    final int RECORD_TYPE = 128;
//    final int COLLECTION_TYPE = 256;
    final int VARIABLE = 512;       // definition scope: package


    @NotNull
    List<SystemFunctionDescriptor> findSystemFunction(String name);

    @NotNull
    DbObject[] findByNameForType(int objType, String name);

    @NotNull
    DbObject[] findByNameForType(String schema, int objType, String name);


    @NotNull
    DbObject[] findByNamePrefix(int objType, String prefix);
    @NotNull
    DbObject[] findByNamePrefix(String schema, int objType, String prefix);

    // lightweight query methods
    @NotNull
    String[] getNameListForType(String user, int type);

    @NotNull
    String[] findByNamePrefix2(int type, String prefix);

    @NotNull
    String[] findByNamePrefix2(String schema, int objType, String text);


    void refreshDbObject(int objType, String schema, String name);

    String getCurrentUser();
    Cache getCache(String user);

    /**
     * Start preloading object asynchronously
     * @param objType -
     * @param schema -
     * @param name -
     */
    void preloadObject(int objType, String schema, String name);

    void addStateListener(StateListener l);

    /**
     * Cache is up to date and ready for use
     * @return
     */
    boolean isReady();

    void close();

}
