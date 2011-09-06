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
import java.util.*;


/**
 * Helps to resolve location of the file : valid content tree or excluded one
 */
public class FSTrackHelper {

    private final FileTypeManager ftManager = FileTypeManager.getInstance();
    private Set<TrivialContentEntry> contentEntries = new HashSet<TrivialContentEntry>();

    private LocalFileProcessor processor;

    private void enqueueFile(VirtualFile file, int operation){
        if (!file.isDirectory()) {
            for (FileNameMatcher m : ftManager.getAssociations(PlSqlFileType.FILE_TYPE)) {
                if (m.accept(file.getName())) {
                    processor.process(file, operation);

/*
                    if(operation == LocalFileProcessor.REMOVE_FROM_INDEX){
                        System.out.println("File being deleted from index: " + file);
                    // todo -- implement me
                    } else if(operation == LocalFileProcessor.ADD_TO_INDEX){
                        System.out.println("File being add to index: " + file);
                    // todo -- implement me
                    }
*/
                }
            }
        }
    }


    private static boolean processFilesRecursively(@NotNull VirtualFile root, @NotNull Processor<VirtualFile> processor) {
      if (!processor.process(root)) return false;

      if (root.isDirectory()) {
        final LinkedList<VirtualFile[]> queue = new LinkedList<VirtualFile[]>();

        queue.add(root.getChildren());

        do {
          final VirtualFile[] files = queue.removeFirst();

          for (VirtualFile file : files) {
            if (!processor.process(file)){
                continue;
            } else if (file.isDirectory()) {
              queue.add(file.getChildren());
            }
          }
        } while (!queue.isEmpty());
      }

      return true;
    }


    private boolean isSqlFile(VirtualFile file){
        if (!file.isDirectory()) {
            for (FileNameMatcher m : ftManager.getAssociations(PlSqlFileType.FILE_TYPE)) {
                if (m.accept(file.getName())) {
                    return true;
                }
            }
        }

        return false;
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
     * Check whether the file is SQL file and its location in the valid root content
     * @param virtualFile - file being checked
     * @return
     */
    public boolean isFileValid(VirtualFile virtualFile) {
        if(virtualFile == null || !isSqlFile(virtualFile)){
            return false;
        }
        for(TrivialContentEntry entry: contentEntries){
            for(VirtualFile dir: entry.getExcludeFolders()){
                if(virtualFile.getUrl().startsWith(dir.getUrl())){
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Check whether the file is SQL file and its location in the valid root content
     * @param file - file being checked
     * @return
     */
    public boolean isFileValid(File file) {
        if(file == null || !isSqlFile(file)){
            return false;
        }
        for(TrivialContentEntry entry: contentEntries){
            for(VirtualFile dir: entry.getExcludeFolders()){
                if(file.toURI().toString().startsWith(dir.getUrl())){
                    return false;
                }
            }
        }
        return true;
    }


    public void process(Set<TrivialContentEntry> newOnes, @NotNull LocalFileProcessor processor){
        this.processor = processor;

        List<List> pairs = new ArrayList<List>();
        // 1. Find all intersected roots (old vs new ones)
        for(TrivialContentEntry old: contentEntries){
            for(TrivialContentEntry newOne: newOnes){
                if(intersectRoots(old, newOne) != 0){
                    // intersection detected (content roots may be equal as a special case of intersection)
                    List pair = new ArrayList();
                    pair.add(old);
                    pair.add(newOne);
                    pairs.add(pair);
                }
            }
        }

        // 2. Find added/deleted roots
        // Roots which leaves in the contentEntries are being deleted
        // Roots which leaves in the newOnes are being added
        for(List pair: pairs){
            contentEntries.remove(pair.get(0));
            newOnes.remove(pair.get(1));
        }

        // 3. print out result -------------------------------------
        // ------------------------------------------------------------

        // 4. apply modifications
        // 4.1 Process removing old content roots
        for (TrivialContentEntry e : contentEntries) {
            VirtualFile contentRoot = e.getRoot();
            final VirtualFile[] excludedFolders = e.getExcludeFolders();
            processFilesRecursively(contentRoot, new Processor<VirtualFile>() {
                public boolean process(VirtualFile virtualFile) {
                    if (isFileInExcludedContent(virtualFile, excludedFolders)) {
                        return false;
                    } else {
                        // put file in the queue for removing from index
                        enqueueFile(virtualFile, LocalFileProcessor.REMOVE_FROM_INDEX);
                    }
                    return true;
                }
            });
        }
        // 4.2 Process adding new content roots
        for (TrivialContentEntry e : newOnes) {
            VirtualFile contentRoot = e.getRoot();
            final VirtualFile[] excludedFolders = e.getExcludeFolders();
            processFilesRecursively(contentRoot, new Processor<VirtualFile>() {
                public boolean process(VirtualFile virtualFile) {
                    if(isFileInExcludedContent(virtualFile, excludedFolders)){
                        return false;
                    } else {
                        // put file in the queue for indexing
                        enqueueFile(virtualFile, LocalFileProcessor.ADD_TO_INDEX);
                    }
                    return true;
                }
            });
        }

        // 4.3 Process intersected roots
        for(List pair: pairs){
            TrivialContentEntry old = (TrivialContentEntry) pair.get(0);
            TrivialContentEntry newOne = (TrivialContentEntry) pair.get(1);
            if(old.getRoot().getUrl().equals(newOne.getRoot().getUrl())){
                // root equals, check excluded folders
                processExcludedFolders(old.getRoot(), old.getExcludeFolders(), newOne.getExcludeFolders());
            } else if(intersectRoots(old, newOne) == 1){
                //  newOne content root is an ancestor of old one
                VirtualFile contentRoot = newOne.getRoot();
                final VirtualFile[] excludedFolders = newOne.getExcludeFolders();
                processFilesRecursively(contentRoot, new Processor<VirtualFile>() {
                    public boolean process(VirtualFile virtualFile) {
                        if(isFileInExcludedContent(virtualFile, excludedFolders)){
                            // put file in the queue for removing from index
                            enqueueFile(virtualFile,LocalFileProcessor.REMOVE_FROM_INDEX);
                        } else {
                            // put file in the queue for indexing
                            enqueueFile(virtualFile,LocalFileProcessor.ADD_TO_INDEX);
                        }
                        return true;
                    }
                });
            } else {
                // (-1) old content root is an ancestor of a new one
                VirtualFile oldContentRoot = old.getRoot();
                final VirtualFile newContentRoot = newOne.getRoot();
                final VirtualFile[] newExcludedFolders = newOne.getExcludeFolders();
                processFilesRecursively(oldContentRoot, new Processor<VirtualFile>() {
                    public boolean process(VirtualFile virtualFile) {
                        if(!virtualFile.getUrl().startsWith(newContentRoot.getUrl())){
                            // file is out side of new content root, remove form the index
                            enqueueFile(virtualFile,LocalFileProcessor.REMOVE_FROM_INDEX);
                        } else if(isFileInExcludedContent(virtualFile, newExcludedFolders)){
                            // put file in the queue for removing from index
                            enqueueFile(virtualFile,LocalFileProcessor.REMOVE_FROM_INDEX);
                        } else {
                            // put file in the queue for indexing
                            enqueueFile(virtualFile,LocalFileProcessor.ADD_TO_INDEX);
                        }
                        return true;
                    }
                });
            }
        }


        // 5. save results
        contentEntries.clear();
        contentEntries.addAll(newOnes);
        for(List pair: pairs){
            contentEntries.add((TrivialContentEntry) pair.get(1));
        }
    }


    private void processExcludedFolders(VirtualFile contentRoot,
                                        final VirtualFile[] oldExcludeFolders,
                                        final VirtualFile[] newExcludeFolders) {

        if(!equals(oldExcludeFolders, newExcludeFolders)){
            processFilesRecursively(contentRoot, new Processor<VirtualFile>() {
                public boolean process(VirtualFile virtualFile) {
                    boolean excludedNow = isFileInExcludedContent(virtualFile, newExcludeFolders);
                    boolean excludedBefore = isFileInExcludedContent(virtualFile, oldExcludeFolders);
                    if( excludedNow && excludedBefore || (!excludedNow && !excludedBefore)){
                        // skip file processing
                    } else if( excludedNow ){
                        // put file in the queue for removing from index
                        enqueueFile(virtualFile,LocalFileProcessor.REMOVE_FROM_INDEX);
                    } else {
                        // excludedNow == false && excludedBefore == true
                        enqueueFile(virtualFile,LocalFileProcessor.ADD_TO_INDEX);
                    }
                    return true;
                }
            });
        }
    }

    private boolean equals(VirtualFile[] oldExcludeFolders, VirtualFile[] newExcludeFolders){
        if(oldExcludeFolders.length != newExcludeFolders.length){
            return false;
        }
        Set<VirtualFile> oldest = new HashSet<VirtualFile>();
        oldest.addAll(Arrays.asList(oldExcludeFolders));
        for(VirtualFile f: newExcludeFolders){
            if(!oldest.contains(f)){
                return false;
            }
        }
        return true;
    }

    private boolean isFileInExcludedContent(VirtualFile virtualFile, VirtualFile[] excludedFolders) {
        for(VirtualFile excludedFolder: excludedFolders){
            if(virtualFile.getUrl().startsWith(excludedFolder.getUrl())){
                return true;
            }
        }
        return false;
    }

    /**
     * Check for intersection between two content roots
     * @param first - content root
     * @param second - content root
     * @return -    0 if contents have no intersections,
     *              1 if the second content root is an ancestor of the first one and -1 otherwise
     */
    private int intersectRoots(TrivialContentEntry first, TrivialContentEntry second){
        if(first.getRoot().getUrl().startsWith(second.getRoot().getUrl())){
            // First content root is a descendant of the second one
            return -1;
        } else if(second.getRoot().getUrl().startsWith(first.getRoot().getUrl())){
            // First content root is an ancestor of the second one
            return 1;
        } else {
            // no intersection detected
            return 0;
        }
    }

}
