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
import com.intellij.lang.Language;
import com.intellij.openapi.vfs.DeprecatedVirtualFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.testFramework.LightVirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;


public abstract class SqlFile extends LightVirtualFile {

    protected DbUrl dbUrl;

    public SqlFile(DbUrl dbUrl, final String name, final Language language, final CharSequence text) {
        super(name, language, text);
        this.dbUrl = dbUrl;
    }

    public abstract AbstractSchema getSimpleIndex();

    public DbUrl getDbUrl() {
        return dbUrl;
    }

    public abstract String getEncodedFilePathCtx();

    @NotNull
    public String getUrl(){
        return getFileSystem().getProtocol() + "://" + getDbUrl() + "#" + getEncodedFilePathCtx();
    }

    /**
     * Redefine VirtualFileSystem to avoid undesired adopting of slash symbol
     * according to current file system done by default implementation
     */
    protected static class VeryRestrictedVirtualFileSystem extends DeprecatedVirtualFileSystem {
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
