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

package com.deepsky.utils;

import com.intellij.openapi.vfs.InvalidVirtualFileAccessException;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

public class FileUtils {

    public static boolean createDir(File dir) {
        if (!dir.exists()) {
            String sep = "\\\\"; // todo -- it works on Windows platform only! File.separator;

            String[] names = dir.getPath().split(sep);
            File path = null;
            for (int i = 0; i < names.length; i++) {
                if (i == 0) {
                    path = new File(names[i]);
                } else {
                    path = new File(path, names[i]);
                }
                if (!path.exists()) {
                    // create directory
                    if (!path.mkdir()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    public static void processDirectoryTree(String startPath, FileProcessor processor) {
        File dir = new File(startPath);
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                if (f.isDirectory()) {
                    processDirectoryTree(f.toString(), processor);
                } else {
                    processor.handleEntry(dir, f);
                }
            }
        }
    }

    public static interface FileProcessor {
        void handleEntry(File parent, File child);
    }

    public static void processDirectoryTree(VirtualFile start, VirtualFileProcessor processor) {
        try {
            if (start.isValid() && start.isDirectory()) {
                for (VirtualFile f : start.getChildren()) {
                    if (f.isDirectory()) {
                        processDirectoryTree(f, processor);
                    } else {
                        processor.handleEntry(start, f);
                    }
                }
            }
        } catch(InvalidVirtualFileAccessException ignored){
            // File was changed outside
        }
    }

    public static interface VirtualFileProcessor {
        void handleEntry(VirtualFile parent, VirtualFile child);
    }

}
