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

package com.deepsky.database.fs;

import com.intellij.openapi.vfs.DeprecatedVirtualFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.impl.local.LocalFileSystemImpl;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.util.LocalTimeCounter;
import com.intellij.testFramework.LightVirtualFile;
import com.deepsky.lang.common.PlSqlSupportLoader;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.Charset;

import junit.framework.Assert;

public class DBSpecificVirtualFile extends LightVirtualFile { //VirtualFile {

    File file;
    String name;
    FileType fileType = PlSqlSupportLoader.PLSQL;

    public DBSpecificVirtualFile(String name, String content){
        super(name);
        this.file = file;
        this.name = name;
//        myModStamp = LocalTimeCounter.currentTime();

        setCharset(Charset.defaultCharset()); //(LanguageFileType)fileType).extractCharsetFromFileContent(null, this, text.toString()));
    }

    @NotNull
    @NonNls
    public String getName() {
        return name + ".sql";
    }

//    @NotNull
//    public VirtualFileSystem getFileSystem() {
//        return ourFileSystem;
//    }

//    public String getPath() {
//        return "/" + getName();
//    }
//
//    public long getLength() {
//        try {
//            return contentsToByteArray().length;
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//            Assert.assertTrue(false);
//            return 0;
//        }
//    }
//
//    public long getModificationStamp() {
//      return myModStamp;
//    }

    public byte[] contentsToByteArray() throws IOException {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            in = new FileInputStream(file);
            byte[] temp = new byte[1000];
            int size;
            while ((size = in.read(temp)) > 0) {
                out.write(temp, 0, size);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return out.toByteArray();
    }

//    @NotNull
//    public String getUrl() {
//        return VirtualFileManager.constructUrl(getFileSystem().getProtocol(), getPath());
//    }

    @NotNull
    public FileType getFileType(){
        return PlSqlSupportLoader.PLSQL;
    }

    public OutputStream getOutputStream(Object requestor, long newModificationStamp, long newTimeStamp) throws IOException {
//        return new ByteArrayOutputStream() {
//          public void close() {
//            myModStamp = newModificationStamp;
//            myContent = toString();
//          }
//        };
        return null;
    }

    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }


//    public boolean isWritable() {
//        return false;
//    }
//
//    public boolean isDirectory() {
//        return false;
//    }
//
//    public boolean isValid() {
//        return true;
//    }
//
//    @Nullable
//    public VirtualFile getParent() {
//        return null;
//    }
//
//    public VirtualFile[] getChildren() {
//        return new VirtualFile[0];
//    }
//
//    public long getTimeStamp() {
//        return myModStamp;
//    }
//
//    public void refresh(boolean asynchronous, boolean recursive, Runnable postRunnable) {
//        // todo
//    }

    private static final MyVirtualFileSystem ourFileSystem = new MyVirtualFileSystem();


    /////////////////////
    private static class MyVirtualFileSystem extends DeprecatedVirtualFileSystem {
        @NonNls
        private static final String PROTOCOL = "mock";

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
        public VirtualFile refreshAndFindFileByPath(String path) {
            return null;
        }

        public void deleteFile(Object requestor, VirtualFile vFile) throws IOException {
        }

        public void moveFile(Object requestor, VirtualFile vFile, VirtualFile newParent) throws IOException {
        }

        public VirtualFile copyFile(Object requestor, VirtualFile vFile, VirtualFile newParent, final String copyName) throws IOException {
            throw new IOException("Cannot copy files");
        }

        public void renameFile(Object requestor, VirtualFile vFile, String newName) throws IOException {
        }

        public VirtualFile createChildFile(Object requestor, VirtualFile vDir, String fileName) throws IOException {
            throw new IOException("Cannot create files");
        }

        public VirtualFile createChildDirectory(Object requestor, VirtualFile vDir, String dirName) throws IOException {
            throw new IOException("Cannot create directories");
        }
    }


}
