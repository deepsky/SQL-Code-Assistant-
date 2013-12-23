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

package com.deepsky.lang.plsql.resolver.utils;

import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.index.IndexEntriesWalkerInterruptable;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.struct.DbObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IndexTreeUtil {

    public static Set<String> getTypesInFile(IndexTree itree, String filePath) {
        final Set<String> out = new HashSet<String>();

        itree.iterateTopNodesForFile(filePath, new IndexEntriesWalkerInterruptable() {
            public boolean process(String ctxPath, String value) {

                String[] path = ctxPath.split("/");

                // validate root context
                switch (path.length) {
                    case 0:
                    case 1:
                        break;
                    default:
                        String name = "/" + path[2];
                        int ctxType = ContextPathUtil.prefix2type(name.substring(0,4));
                        String type1 = ctxType2dbType.get(ctxType);
                        if(type1 != null){
                            out.add(type1);
                        }
                        break;
                }
                return true;
            }
        });

        return out;
    }

    // todo -- duplicated in ContextPathUtil
    public static String ctxType2dbType(int ctxType){
        return ctxType2dbType.get(ctxType);
    }

    private static Map<Integer, String> ctxType2dbType = new HashMap<Integer, String>();

    static {
        ctxType2dbType.put(ContextPath.TABLE_DEF, DbObject.TABLE);
        ctxType2dbType.put(ContextPath.PACKAGE_SPEC, DbObject.PACKAGE);
        ctxType2dbType.put(ContextPath.PACKAGE_BODY, DbObject.PACKAGE);
        ctxType2dbType.put(ContextPath.VIEW_DEF, DbObject.VIEW);
        ctxType2dbType.put(ContextPath.CREATE_TRIGGER, DbObject.TRIGGER);
        ctxType2dbType.put(ContextPath.SEQUENCE, DbObject.SEQUENCE);
        ctxType2dbType.put(ContextPath.SYNONYM, DbObject.SYNONYM);
        ctxType2dbType.put(ContextPath.COLLECTION_TYPE, DbObject.TYPE);
        ctxType2dbType.put(ContextPath.RECORD_TYPE, DbObject.TYPE);
        ctxType2dbType.put(ContextPath.OBJECT_TYPE, DbObject.TYPE);
        ctxType2dbType.put(ContextPath.VARRAY_TYPE, DbObject.TYPE);
        ctxType2dbType.put(ContextPath.VARRAY_TYPE, DbObject.TYPE);

        ctxType2dbType.put(ContextPath.PROCEDURE_BODY, DbObject.PROCEDURE_BODY);
        ctxType2dbType.put(ContextPath.PROCEDURE_SPEC, DbObject.PROCEDURE);

        ctxType2dbType.put(ContextPath.FUNCTION_BODY, DbObject.FUNCTION_BODY);
        ctxType2dbType.put(ContextPath.FUNCTION_SPEC, DbObject.FUNCTION);

    }

}
