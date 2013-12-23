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

import com.deepsky.findUsages.wordProc.FileProcessor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface WordIndexManager {

    /**
     * Index file
     * @param fullPath - path of the file being indexed
     */
    void updateIndexForFile(String fullPath);

    /**
     * Index the file but use 'text' as a content of the file.
     * Call of the method may mean that indexing was requested for an uncommitted document
     * @param filePath - path of the file being indexed
     * @param text - content being indexed
     */
    void updateIndexForFile(String filePath, String text);

    void updateIndexForFiles(File[] fullPaths);

    void deleteIndexForFile(String fullPath);

    boolean rebuildIndex(String widxName);


    /**
     * Search widx files in the specified directory for occurence of the 'text',
     * call iterator.processFile for each file containing the 'text'.
     *
     * @param project - ancor
     * @param text     - target words
     * @param iterator - listener found files
     * @return - and array of widx file names needed in rebuilding (more then 30% entries are duplicates )
     */
    String[] scanDir(
            @NotNull Project project,
            @NotNull String[] text,
            @NotNull FileProcessor iterator);

}
