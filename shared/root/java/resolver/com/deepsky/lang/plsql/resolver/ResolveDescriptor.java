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

package com.deepsky.lang.plsql.resolver;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.plsql.resolver.index.ContextItem;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.struct.Type;

import java.util.Iterator;

public interface ResolveDescriptor {

    final int FUNCTION_SPEC = 1;
    final int FUNCTION_BODY = 2;
    final int PROCEDURE_SPEC = 3;
    final int PROCEDURE_BODY = 4;

    final int SEQUENCE = 5;
    final int PACKAGE_SPEC = 6;
    final int PACKAGE_BODY = 7;

    final int TABLE_COLUMN = 11;
    final int TABLE = 12;
    final int VIEW = 13;
    final int SUBQUERY_FIELD = 14;
    final int VIEW_COLUMN = 15;
    final int CURSOR_DECL = 16;
    final int SUBQUERY_ALIAS = 17;
    final int TABLE_ALIAS = 18;

    final int VARIABLE = 21;
    final int ARGUMENT = 22;

    final int RECORD_TYPE = 31;
    final int RECORD_ITEM = 32;
    final int VARRAY_TYPE = 33;
    final int COLLECTION_TYPE = 34;
    final int OBJECT_TYPE = 35;
    final int ROWTYPE = 36;
    final int REF_CURSOR = 37;
    final int COLLECTION_METHOD = 38;
    final int COLLECTION_ELEM_ACCESSOR = 39;
    final int VARRAY_ELEM_ACCESSOR = 40;
    final int VARRAY_METHOD = 41;

    final int LOOP_INDEX = 51;

    final int SYSTEM_FUNC = 61;
    final int DEFAULT = 101;

    int getTargetType();
    String getCtxPath();
    String getValue();

    /**
     * Name of the object (extracted from the context path)
     * @return - name of the object
     */
    String getName();

    /**
     * Type of the object if appliable
     * @return type or null if not appliable
     */
    Type getType();

//    String getUserName();
    DbUrl getDbUrl();

    ResolveDescriptor[] resolve(String name);
    ResolveDescriptor[] resolve(int type, String name);

    Iterator<ContextItem> iterateOverChildren(); 

}
