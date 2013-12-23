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

import com.deepsky.lang.common.PlSqlFileType;
import com.intellij.openapi.fileTypes.FileNameMatcher;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;


/**
 * Helps to resolve location of the file : valid content tree or excluded one
 */
public class FSContentTracker {

    public final static int ADD_CONTENT = 1;
    public final static int REMOVE_CONTENT = 2;

    private final FileTypeManager ftManager;

    private Set<MContent> contentEntries = new HashSet<MContent>();

    public FSContentTracker() {
        ftManager = FileTypeManager.getInstance();
    }

    private void enqueueFile(VirtualFile file, int operation, LocalFileProcessor processor) {
        if (!file.isDirectory()) {
            for (FileNameMatcher m : ftManager.getAssociations(PlSqlFileType.FILE_TYPE)) {
                if (m.accept(file.getName())) {
                    processor.process(file, operation);
                    break;
                }
            }
        }
    }


    static boolean processFilesRecursively(@NotNull VirtualFile root, @NotNull Processor<VirtualFile> processor) {
        if (!processor.process(root)) return false;

        if (root.isDirectory()) {
            final LinkedList<VirtualFile[]> queue = new LinkedList<VirtualFile[]>();
            queue.add(root.getChildren());

            do {
                final VirtualFile[] files = queue.removeFirst();

                for (VirtualFile file : files) {
                    if (!processor.process(file)) {
                        continue;
                    } else if (file.isDirectory()) {
                        try {
                            VirtualFile[] children = file.getChildren();
                            queue.add(children);
                        } catch (Throwable e) {
                            // VirtualFile could be invalid, so skip processing its children
                        }
                    }
                }
            } while (!queue.isEmpty());
        }

        return true;
    }


    private static void processFilesRecursively(@NotNull MContent entry, @NotNull Processor<VirtualFile> processor) {

        for (VirtualFile sourceRoot : entry.getSourceFolders()) {
            if (sourceRoot.isDirectory()) {
                final LinkedList<VirtualFile[]> queue = new LinkedList<VirtualFile[]>();
                queue.add(sourceRoot.getChildren());

                do {
                    final VirtualFile[] files = queue.removeFirst();

                    for (VirtualFile file : files) {
                        if (!entry.isFileInExcludedContent(file)) {
                            if (file.isDirectory()) {
                                queue.add(file.getChildren());
                            } else {
                                processor.process(file);
                            }
                        }
                    }
                } while (!queue.isEmpty());
            }
        }
    }


    private boolean isSqlFile(VirtualFile file) {
        return isSqlFile(new File(file.getPath()));
    }

    private boolean isSqlFile(File file) {
        if (!file.isDirectory()) {
            for (FileNameMatcher m : ftManager.getAssociations(PlSqlFileType.FILE_TYPE)) {
                if (m.accept(file.getName())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check whether the file is SQL file and its location in the valid source root content
     *
     * @param virtualFile - file being checked
     * @return
     */
    public boolean isFileUnderSourceRoot(VirtualFile virtualFile) {
        if (virtualFile == null || !isSqlFile(virtualFile)) {
            return false;
        }
        for (MContent entry : contentEntries) {
            // Check against the content root first
            if (MContent.isFirstUnderSecond(virtualFile, entry.getRoot())) {
                return entry.isFileValid(virtualFile);
            }
        }
        return false;
    }


    /**
     * Check whether the file is SQL file and its location in the valid root content
     *
     * @param file - file being checked
     * @return
     */
    public boolean isFileValid(File file) {
        if (file == null || !isSqlFile(file)) {
            return false;
        }
        for (MContent entry : contentEntries) {
            // Check against the content root first
            if (MContent.isFirstUnderSecond(file, new File(entry.getRoot().getPath()))) {
                return entry.isFileInSources(file);
            }
        }
        return true;
    }

    public void iterateOverContentEntries(@NotNull final LocalFileProcessor processor) {

        for (MContent e : contentEntries) {
            processFilesRecursively(e, new Processor<VirtualFile>() {
                @Override
                public boolean process(VirtualFile virtualFile) {
                    enqueueFile(virtualFile, LocalFileProcessor.ADD_TO_INDEX, processor);
                    return true;
                }
            });
        }
    }


    public void updateContentChanges(Set<MContent> before, Set<MContent> after, final LocalFileProcessor processor) {

        identifyChanges(before, after, new MContentHandler() {
            @Override
            public void sourceRootAdded(MContent e, VirtualFile sourceRoot) {
                updateSourceRoot(FSContentTracker.ADD_CONTENT, e.getRoot(), sourceRoot, processor);
            }

            @Override
            public void sourceRootRemoved(MContent e, VirtualFile sourceRoot) {
                updateSourceRoot(FSContentTracker.REMOVE_CONTENT, e.getRoot(), sourceRoot, processor);
            }

            @Override
            public void excludeRootAdded(MContent e, VirtualFile excludeRoot) {
                updateExcludeRoot(FSContentTracker.ADD_CONTENT, e.getRoot(), excludeRoot, processor);
            }

            @Override
            public void excludeRootRemoved(MContent e, VirtualFile excludeRoot) {
                updateExcludeRoot(FSContentTracker.REMOVE_CONTENT, e.getRoot(), excludeRoot, processor);
            }
        });
    }

    public static void identifyChanges(Set<MContent> before, Set<MContent> after, MContentHandler handler) {
        // Find changes in existing content,
        // ignore changes like module added/removed
        for (MContent eBefore : before) {
            Iterator<MContent> ite = after.iterator();
            while (ite.hasNext()) {
                MContent eAfter = ite.next();
                if (eAfter.getRoot().equals(eBefore.getRoot())) {
                    // Go over source and excluded folders and find difference
                    for (VirtualFile fBefore : eBefore.getSourceFolders()) {
                        boolean found = false;
                        for (int i = 0; !found && i < eAfter.getSourceFolders().length; i++) {
                            VirtualFile fAfter = eAfter.getSourceFolders()[i];
                            found = fBefore.equals(fAfter);
                        }

                        if (!found) {
                            handler.sourceRootRemoved(eBefore, fBefore);
                        }
                    }

                    for (VirtualFile fAfter : eAfter.getSourceFolders()) {
                        boolean found = false;
                        for (int i = 0; !found && i < eBefore.getSourceFolders().length; i++) {
                            VirtualFile fBefore = eBefore.getSourceFolders()[i];
                            found = fBefore.equals(fAfter);
                        }

                        if (!found) {
                            handler.sourceRootAdded(eAfter, fAfter);
                        }
                    }

                    for (VirtualFile fBefore : eBefore.getExcludeFolders()) {
                        boolean found = false;
                        for (int i = 0; !found && i < eAfter.getExcludeFolders().length; i++) {
                            VirtualFile fAfter = eAfter.getExcludeFolders()[i];
                            found = fBefore.equals(fAfter);
                        }

                        if (!found) {
                            handler.excludeRootRemoved(eBefore, fBefore);
                        }
                    }

                    for (VirtualFile fAfter : eAfter.getExcludeFolders()) {
                        boolean found = false;
                        for (int i = 0; !found && i < eBefore.getExcludeFolders().length; i++) {
                            VirtualFile fBefore = eBefore.getExcludeFolders()[i];
                            found = fBefore.equals(fAfter);
                        }

                        if (!found) {
                            handler.excludeRootAdded(eAfter, fAfter);
                        }
                    }

                    break;
                }
            }
        }
    }


    public void updateContent(int op, Set<MContent> newOnes, @NotNull final LocalFileProcessor processor) {
        if (op == ADD_CONTENT) {
            // Add new content roots
            for (MContent e : newOnes) {
                processFilesRecursively(e, new Processor<VirtualFile>() {
                    public boolean process(VirtualFile virtualFile) {
                        // put file in the queue for indexing
                        enqueueFile(virtualFile, LocalFileProcessor.ADD_TO_INDEX, processor);
                        return true;
                    }
                });
            }

            contentEntries.addAll(newOnes);

        } else if (op == REMOVE_CONTENT) {
            // Remove content
            for (MContent e : newOnes) {
                processFilesRecursively(e, new Processor<VirtualFile>() {
                    public boolean process(VirtualFile virtualFile) {
                        // put file in the queue for indexing
                        enqueueFile(virtualFile, LocalFileProcessor.REMOVE_FROM_INDEX, processor);
                        return true;
                    }
                });
            }

            contentEntries.removeAll(newOnes);
        }
    }

    static boolean isFileInExcludedContent(VirtualFile virtualFile, VirtualFile[] excludedFolders) {
        for (VirtualFile excludedFolder : excludedFolders) {
            if (MContent.isFirstUnderSecond(virtualFile, excludedFolder)) {
                return true;
            }
        }
        return false;
    }


    public void updateSourceRoot(int op, VirtualFile contentRoot, VirtualFile rootToProcess, final LocalFileProcessor processor) {
        for (MContent c : contentEntries) {
            if (c.getRoot().equals(contentRoot)) {
                if (op == ADD_CONTENT) {
                    final VirtualFile[] excludedFolders = c.getExcludeFolders();
                    processFilesRecursively(rootToProcess, new Processor<VirtualFile>() {
                        public boolean process(VirtualFile virtualFile) {
                            if (isFileInExcludedContent(virtualFile, excludedFolders)) {
                                return false;
                            } else {
                                // put file in the queue for addition to the index
                                enqueueFile(virtualFile, LocalFileProcessor.ADD_TO_INDEX, processor);
                            }
                            return true;
                        }
                    });

                    c.addSourceFolder(rootToProcess);
                } else if (op == REMOVE_CONTENT) {
                    final VirtualFile[] excludedFolders = c.getExcludeFolders();
                    processFilesRecursively(rootToProcess, new Processor<VirtualFile>() {
                        public boolean process(VirtualFile virtualFile) {
                            if (isFileInExcludedContent(virtualFile, excludedFolders)) {
                                return false;
                            } else {
                                // put file in the queue for removal from the index
                                enqueueFile(virtualFile, LocalFileProcessor.REMOVE_FROM_INDEX, processor);
                            }
                            return true;
                        }
                    });
                    c.removeSourceFolder(rootToProcess);
                }
                break;
            }
        }
    }

    public void updateExcludeRoot(int op, VirtualFile contentRoot, VirtualFile rootToProcess, final LocalFileProcessor processor) {
        for (MContent c : contentEntries) {
            if (c.getRoot().equals(contentRoot)) {
                if (op == ADD_CONTENT) {
                    final VirtualFile[] excludedFolders = c.getExcludeFolders();
                    processFilesRecursively(rootToProcess, new Processor<VirtualFile>() {
                        public boolean process(VirtualFile virtualFile) {
                            if (isFileInExcludedContent(virtualFile, excludedFolders)) {
                                // File is already in excluded content, no need to remove
                                return false;
                            } else {
                                // put file in the queue for removal form the index
                                enqueueFile(virtualFile, LocalFileProcessor.REMOVE_FROM_INDEX, processor);
                            }
                            return true;
                        }
                    });

                    c.addExcludeFolder(rootToProcess);
                } else if (op == REMOVE_CONTENT) {
                    c.removeExcludeFolder(rootToProcess);
                    final VirtualFile[] excludedFolders = c.getExcludeFolders();
                    processFilesRecursively(rootToProcess, new Processor<VirtualFile>() {
                        public boolean process(VirtualFile virtualFile) {
                            if (isFileInExcludedContent(virtualFile, excludedFolders)) {
                                return false;
                            } else {
                                // put file in the queue for removal from the index
                                enqueueFile(virtualFile, LocalFileProcessor.ADD_TO_INDEX, processor);
                            }
                            return true;
                        }
                    });
                }
                break;
            }
        }
    }
}
