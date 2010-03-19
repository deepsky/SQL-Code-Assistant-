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

package com.deepsky.lang.validation;

import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.psi.ColumnSpec;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.InsertStatement;
import com.deepsky.database.ObjectCacheFactory;
import com.deepsky.database.ObjectCache;
import com.intellij.openapi.util.TextRange;
import com.intellij.lang.annotation.AnnotationHolder;

public class StatementValidator {

    public static void validate(AnnotationHolder myHolder, InsertStatement node){
        String table = node.getIntoTable().getTableName();
         DbObject[] objs = PluginKeys.OBJECT_CACHE.getData(node.getProject())//ObjectCacheFactory.getObjectCache()
                 .findByNameForType(
                    ObjectCache.TABLE | ObjectCache.VIEW, table
                 );

        if(objs.length != 0){
            // 1. validate column names
            ColumnSpec[] columns = node.getColumnList();
            Type[] columnSpecTypes = new Type[columns.length];
            int i =0;
            for(ColumnSpec column: columns){
                try {
                    // we are not interested in the actual type
                    columnSpecTypes[i] = column.getColumnType();
                } catch (ValidationException e) {
                    myHolder.createErrorAnnotation(column, "Table " + table + " has no column " + column.getColumnNameRef());
                }
                i++;
            }

            // 2. validate number of columns in specification against number of values
            // identify type of INSERT
            Expression[] exprs = node.getValues();
            if(exprs != null){
                // INSERT FROM VALUES
                if(columnSpecTypes.length != exprs.length){
                    int startOffset = columns[0].getTextOffset();
                    int endOffset = columns[columns.length-1].getTextRange().getEndOffset();
                    myHolder.createErrorAnnotation(
                            new TextRange(startOffset, endOffset),
                            "Number of specified columns differ from number of values.");
                } else {
                    // validate column Spec types against actual types and number of values
                    // todo --
                }
            } else {
                // INSERT FROM SUBQUERY
                SelectStatement select = node.getSubquery();
            }
        } else {
            // table not found, skip column validation
        }
    }
}
