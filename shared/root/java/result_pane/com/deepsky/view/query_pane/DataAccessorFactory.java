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

package com.deepsky.view.query_pane;

import com.deepsky.database.ora.types.LONGRAWType;
import com.deepsky.database.ora.types.RAWType;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.view.query_pane.converters.*;
import com.intellij.openapi.project.Project;
import oracle.sql.*;
import org.jetbrains.annotations.NotNull;

public class DataAccessorFactory {

/*
    public static DataAccessor create(final Project project, Object value) {
        if (value instanceof RAWType) {
            return new DataAccessor<RAWType>((RAWType) value, new RAWType_Convertor());
        } else if (value instanceof LONGRAWType) {
            return new DataAccessor<LONGRAWType>((LONGRAWType) value, new LONGRAWType_Convertor());
        } else if (value instanceof String) {
            return new DataAccessor<String>((String) value, new StringType_Convertor());
        } else if(value instanceof TIMESTAMP){
            return new DataAccessor<TIMESTAMP>((TIMESTAMP) value, PluginKeys.TS_CONVERTOR.getData(project));
        } else if(value instanceof TIMESTAMPTZ){
            return new DataAccessor<TIMESTAMPTZ>((TIMESTAMPTZ) value, PluginKeys.TSTZ_CONVERTOR.getData(project));
        } else if(value instanceof TIMESTAMPLTZ){
            return new DataAccessor<TIMESTAMPLTZ>((TIMESTAMPLTZ) value, PluginKeys.TSLTZ_CONVERTOR.getData(project));
        } else {
            return null;
        }
    }
*/


    public static DataAccessor createReadOnly(final Project project, @NotNull Class columnClazz, Object value) {
        if(columnClazz.isAssignableFrom(RAWType.class)){
            return new DataAccessor<RAWType>((RAWType) value, new RAWType_Convertor());
        } else if(columnClazz.isAssignableFrom(LONGRAWType.class)){
            return new DataAccessor<LONGRAWType>((LONGRAWType) value, new LONGRAWType_Convertor());
        } else if(columnClazz.isAssignableFrom(TIMESTAMP.class)){
            return new DataAccessor<TIMESTAMP>((TIMESTAMP) value, PluginKeys.TS_CONVERTOR.getData(project));
        } else if(columnClazz.isAssignableFrom(TIMESTAMPTZ.class)){
            return new DataAccessor<TIMESTAMPTZ>((TIMESTAMPTZ) value, PluginKeys.TSTZ_CONVERTOR.getData(project));
        } else if(columnClazz.isAssignableFrom(TIMESTAMPLTZ.class)){
            return new DataAccessor<TIMESTAMPLTZ>((TIMESTAMPLTZ) value, PluginKeys.TSLTZ_CONVERTOR.getData(project));
        } else if(columnClazz.isAssignableFrom(String.class)){
            return new DataAccessor<String>((String) value, new StringType_Convertor());
        } else if(columnClazz.isAssignableFrom(BLOB.class)){
            return new DataAccessor<BLOB>((BLOB) value, new BLOB_Convertor());
        } else if(columnClazz.isAssignableFrom(CLOB.class)){
            return new DataAccessor<CLOB>((CLOB) value, new CLOB_Convertor());
        } else {
            return null;
        }
    }
}
