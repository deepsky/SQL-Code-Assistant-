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

package com.deepsky.lang.plsql.sqlIndex;


import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.Language;

import java.io.File;
import java.io.IOException;

// todo -- subject to remove
@Deprecated
public abstract class FSSqlFile extends SqlFile {

    String fullPath;
    protected CharSequence content;

    public FSSqlFile(DbUrl dbUrl, final String fullPath, final Language language, final CharSequence text){
        super(dbUrl, fullPath, language, null);
        this.fullPath = fullPath;
    }

    public String getEncodedFilePathCtx(){
        return ContextPathUtil.encodeFilePathCtx(getPath());
    }

    public String getPath() {
        return fullPath;
    }

    public String getPresentableName() {
        return new File(fullPath).getName();
    }

    public CharSequence getContent(){
        try {
            if(content == null){
               content = StringUtils.file2string(new File(fullPath));
            }
            return content; 
        } catch (IOException e) {
            return "";
        }
    }

}
