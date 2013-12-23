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

package com.deepsky.lang.integration;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ExcludeFolder;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


class MContent {

    private final Logger log = Logger.getInstance("#MContent");

    public static final int SOURCE_ROOT = 1;
    public static final int EXCLUDE_ROOT = 2;


    private VirtualFile root;
    private Set<VirtualFile> excludeFolders = new HashSet<VirtualFile>();
    private Set<VirtualFile> sourceFolders = new HashSet<VirtualFile>();

    /**
     * Some restrictions on sources and excludes to keep files in consistent state:
     * <p/>
     * 1. Source roots can not be intersected between each other
     * 2. Excluded roots can not be intersected  between each other
     * 3. There should NO be source roots under an excluded root
     * 4. There may be excluded roots under a source root
     *
     * @param root
     */
    public MContent(@NotNull VirtualFile root) {
        this.root = root;
    }

    VirtualFile getRoot() {
        return root;
    }

    VirtualFile[] getExcludeFolders() {
        return excludeFolders.toArray(new VirtualFile[excludeFolders.size()]);
    }

    /**
     * File is valid if it is under root content,
     * under one of source root and not under excluded root
     *
     * @param file
     * @return
     */
    public boolean isFileValid(VirtualFile file) {
        if (isFirstUnderSecond(file, root)) {
            // Make sure the file being added are not under some of excluded folder
            for (VirtualFile f : excludeFolders) {
                if (isFirstUnderSecond(file, f)) {
                    // File is under exclude folder, skip adding it as source root
                    return false;
                }
            }

            for (VirtualFile f : sourceFolders) {
                if (isFirstUnderSecond(file, f)) {
                    // File is under source folder
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isFileInSources(File file) {
        if (isFirstUnderSecond(file, new File(root.getPath()))) {
            // Make sure the file being added are not under some of excluded folder
            for (VirtualFile f : excludeFolders) {
                if (isFirstUnderSecond(file, new File(f.getPath()))) {
                    // File is under exclude folder, skip adding it as source root
                    return false;
                }
            }

            for (VirtualFile f : sourceFolders) {
                if (isFirstUnderSecond(file, new File(f.getPath()))) {
                    // File is under source folder
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean isFirstUnderSecond(@NotNull VirtualFile first, @NotNull VirtualFile second) {
        try {
            Path p1 = Paths.get(first.getPath()).normalize();
            Path p2 = Paths.get(second.getPath()).normalize();
            return p1.startsWith(p2);
        } catch (Throwable e) {
            System.err.println("MContent issue: " + e.getMessage());
            return false;
        }
    }

    public static boolean isFirstUnderSecond(@NotNull File first, @NotNull File second) {
        try {
            Path p1 = Paths.get(first.getAbsolutePath()).normalize();
            Path p2 = Paths.get(second.getAbsolutePath()).normalize();
            return p1.startsWith(p2);
        } catch (Throwable e) {
            System.err.println("MContent issue: " + e.getMessage());
            return false;
        }
    }

    public void addExcludeFolder(@NotNull VirtualFile file) {
        if (isFirstUnderSecond(file, root)) {
            addFile(file, excludeFolders);
        }
    }

    public void addSourceFolder(VirtualFile file) {
        if (isFirstUnderSecond(file, root)) {
            // Make sure the file being added are not under some of excluded folder
            for (VirtualFile f : excludeFolders) {
                if (isFirstUnderSecond(file, f)) {
                    // File is under exclude folder, skip adding it as source root
                    return;
                }
            }
            addFile(file, sourceFolders);
        }
    }

    /**
     * Verify source folders
     */
    public void init() {
        Iterator<VirtualFile> ite = sourceFolders.iterator();
        while (ite.hasNext()) {
            VirtualFile f = ite.next();
            for (VirtualFile fE : excludeFolders) {
                if (isFirstUnderSecond(f, fE)) {
                    // File is under exclude folder, skip adding it as source root
                    ite.remove();
                }
            }
        }
    }

    /**
     * Add file and make sure directories are not intersected
     *
     * @param file
     * @param folders
     */
    private void addFile(VirtualFile file, Set<VirtualFile> folders) {
        Iterator<VirtualFile> ite = folders.iterator();
        while (ite.hasNext()) {
            VirtualFile f = ite.next();
            if (isFirstUnderSecond(f, file)) {
                // more generic path come, remove current
                ite.remove();
                folders.add(file);
                return;
            } else if (isFirstUnderSecond(file, f)) {
                // more generic already exists, skip adding new file
                return;
            }
        }
        folders.add(file);
    }

    public void print() {
        log.info("Root: " + root.getPath());
        for (VirtualFile f : excludeFolders) {
            log.info("Excluded folder: " + f.getPath());
        }

        for (VirtualFile f : excludeFolders) {
            log.info("Source folder: " + f.getPath());
        }
    }

    VirtualFile[] getSourceFolders() {
        return sourceFolders.toArray(new VirtualFile[sourceFolders.size()]);
    }

    public boolean isFileInExcludedContent(@NotNull VirtualFile file) {
        for (VirtualFile excludedFolder : excludeFolders) {
            if (file.getPath().startsWith(excludedFolder.getPath())) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object o) {
        return o instanceof MContent && ((MContent) o).getRoot().equals(getRoot());
    }

    public int hashCode() {
        return getRoot().getPath().hashCode();
    }


    public static Set<MContent> convert(Module[] modules) {
        Set<MContent> out = new HashSet<MContent>();
        for (Module module : modules) {
            ContentEntry[] entries = ModuleRootManager.getInstance(module).getContentEntries();
            for (ContentEntry contentEntry : entries) {
                VirtualFile contentRoot = contentEntry.getFile();
                if (contentRoot != null) {
                    MContent trivial = new MContent(contentRoot);
                    for (ExcludeFolder e : contentEntry.getExcludeFolders()) {
                        if (e.getFile() != null) {
                            trivial.addExcludeFolder(e.getFile());
                        }
                    }

                    for (SourceFolder e : contentEntry.getSourceFolders()) {
                        if (e.getFile() != null) {
                            trivial.addSourceFolder(e.getFile());
                        }
                    }

                    trivial.init();
                    out.add(trivial);
                }
            }
        }

        return out;
    }

    public boolean removeSourceFolder(VirtualFile file) {
        return sourceFolders.remove(file);
    }

    public boolean removeExcludeFolder(VirtualFile file) {
        return excludeFolders.remove(file);
    }
}
