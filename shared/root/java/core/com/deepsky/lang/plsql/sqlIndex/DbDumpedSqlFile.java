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

    String objectName;
    String fileName;
    String path;
    long timestamp;
    protected CharSequence content;

    File f;

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


    private static final MyVirtualFileSystem ourFileSystem = new MyVirtualFileSystem();

    /**
     * Redefine VirtualFileSystem to avoid undesired adopting of slash symbol
     * according to current file system done by default implementation
     * @return
     */
    @NotNull
    public VirtualFileSystem getFileSystem() {
        return ourFileSystem;
    }


    private static class MyVirtualFileSystem extends DeprecatedVirtualFileSystem {
        @NonNls
        private static final String PROTOCOL = "mock";

        @NotNull
        public String getProtocol() {
            return PROTOCOL;
        }

        @Nullable
        public VirtualFile findFileByPath(@NotNull String path) {
            return null;
        }

        public void refresh(boolean asynchronous) {
        }

        @Nullable
        public VirtualFile refreshAndFindFileByPath(@NotNull String path) {
            return null;
        }

        public void deleteFile(Object requestor, @NotNull VirtualFile vFile) throws IOException {
        }

        public void moveFile(Object requestor, @NotNull VirtualFile vFile, @NotNull VirtualFile newParent) throws IOException {
        }

        public VirtualFile copyFile(Object requestor, @NotNull VirtualFile vFile, @NotNull VirtualFile newParent, @NotNull final String copyName) throws IOException {
            throw new IOException("Cannot copy files");
        }

        public void renameFile(Object requestor, @NotNull VirtualFile vFile, @NotNull String newName) throws IOException {
        }

        public VirtualFile createChildFile(Object requestor, @NotNull VirtualFile vDir, @NotNull String fileName) throws IOException {
            throw new IOException("Cannot create files");
        }

        @NotNull
        public VirtualFile createChildDirectory(Object requestor, @NotNull VirtualFile vDir, @NotNull String dirName) throws IOException {
            throw new IOException("Cannot create directories");
        }

        public String extractPresentableUrl(@NotNull String path) {
            return path;
        }

    }

}
