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

import com.deepsky.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DataAccessor<E> {

    private E data;
    private ValueConvertor<E> convertor;

    public DataAccessor(@Nullable E data, @NotNull ValueConvertor<E> convertor){
        this.data = data;
        this.convertor = convertor;
    }

    protected DataAccessor(){
    }

/*
    public DataAccessor(@NotNull ValueConvertor<E> convertor){
        this.convertor = convertor;
    }
*/

    public long size() throws SQLException{
        return convertor.size(data);
    }

    public String convertToString() throws SQLException{
        return convertor.valueToString(data);
    }

    public void loadFromString(String text) throws ConversionException {
        data = convertor.stringToValue(text);
    }

    public void saveValueTo(File file) throws IOException{
        try {
            // todo -- not correct! FIX ME
            String value = convertor.valueToString(data);
            StringUtils.string2file(value == null? "": value, file);
        } catch (SQLException e) {
            throw new IOException(e.getMessage());
        }
    }

/*
    public void setValue(@Nullable E data){
        this.data = data;
    }

*/

    public E getValue(){
        return data;
    }

    public boolean isReadOnly(){
        return true;
    }
}
