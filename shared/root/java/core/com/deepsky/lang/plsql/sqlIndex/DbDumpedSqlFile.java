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
import com.deepsky.lang.common.PlSqlFileType;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.utils.StringUtils;
import com.intellij.openapi.vfs.DeprecatedVirtualFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public abstract class DbDumpedSqlFile extends SqlFile {

    private String objectName;
    private String fileName;
    private String path;
    private long timestamp;
    protected CharSequence content;

    private File f;

    public DbDumpedSqlFile(DbUrl dbUrl, String objectName, final String fileName,
                           String path, final CharSequence text, long timestamp, File f) {
        super(dbUrl, objectName + ".sql", PlSqlFileType.FILE_TYPE.getLanguage(), null);
        this.objectName = objectName;
        this.fileName = fileName;
        this.path = path;
        this.timestamp = timestamp;
        this.f = f;
        this.content = text;
    }

    public String getPath() {
        return path;
    }

    public String getPresentableName() {
        return objectName;
    }

    public long getModificationStamp() {
        return timestamp;
    }

    @Override
    public String getEncodedFilePathCtx() {
        return ContextPathUtil.encodeFilePathCtx(fileName);

    }

    @NotNull
    public String getName() {
        return objectName + ".sql";
    }

    public String getOriginFileName() {
        return fileName;
    }

    public CharSequence getContent() {
        try {
            if (content == null) {
                if (!f.exists()) {
                    // script not found?? is this possible?
                    content = "";
                } else {
                    content = StringUtils.file2string(f);
                }
            }
            return content;
        } catch (IOException e) {
            return "";
        }
    }


    private static final VeryRestrictedVirtualFileSystem ourFileSystem = new VeryRestrictedVirtualFileSystem();

    @NotNull
    public VirtualFileSystem getFileSystem() {
        return ourFileSystem;
    }



}
