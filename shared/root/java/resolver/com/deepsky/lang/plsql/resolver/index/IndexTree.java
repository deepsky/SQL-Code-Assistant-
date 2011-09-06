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

package com.deepsky.lang.plsql.resolver.index;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;


public interface IndexTree extends BaseLookupService {

    String TIMESTAMP_ATTR = "timestamp";

    // manipulating of entries
    void addContextPath(String ctxPath, String value);

    /**
     * Update of the existing path if it exists
     * @param ctxPath
     * @param value
     * @return - true if ctx path value was updated
     */
    boolean updateCtxPathValue(String ctxPath, String value);

    boolean remove(String ctxPath);

    // access to entries
//    String getContextPathValue(String ctxPath);

    // file attributes accessors
    String getFileAttribute(String fileName, String attributeName);

    void setFileAttribute(String fileName, String attributeName, String value);
    void changeFileName(String oldFileName, String newFileName);

    boolean setContextPathAttr(String ctxPath, String attrName, String value);
    String getContextPathAttr(String ctxPath, String attrName);

    /**
     * Get Index modification counter
     *
     * @return - modification counter
     */
    long getModificationCount();

    // number of entries
    int getEntriesCount();

    // iterate thru entries : Down - Up
    void iterateThru(IndexEntriesWalker iproc);

    // iterate thru top nodes only (no deep diving)
    void iterateTopNodes(IndexEntriesWalkerInterruptable iproc);
    void iterateTopNodes(String name, IndexEntriesWalkerInterruptable iproc);

    // iterate thru top nodes only for the specified type (no deep diving)
//    void iterateTopNodes(int ctxType, IndexEntriesWalkerInterruptable iproc);
//    ContextItem[] findInRootContext(int[] types);


    /**
     * Search for the items in the root context
     * @param ctxTypes -
     * @param name - if name is null return all items withspecified type
     * @return
     */
    ContextItem[] findCtxItems(int[] ctxTypes, @Nullable String name);

    /**
     * Search for the items with specified types in the context
     * @param ctxPath - context to search
     * @param ctxTypes - to look for
     * @return
     */
    @NotNull
    ContextItem[] findCtxItems(String ctxPath, int[] ctxTypes);

    @NotNull
    ContextItem[] findCtxItems(String ctxPath, String name);

    // iterate thru top nodes only (no deep diving)
    void iterateTopNodesForFile(String fileNameEncoded, IndexEntriesWalkerInterruptable iproc);

    // iterate thru file names
    void iterateFileNames(IndexEntriesWalkerInterruptable iproc);

    // iterate thru children of the node with specified context
    void iterateOverChildren(String ctxPath, IndexEntriesWalkerInterruptable iterator);
//    void iterateOverChildren(String ctxPath, IndexEntriesWalker2 iterator);

    // iterate thru children of the node for the specified context and name
    void iterateOverChildren(String ctxPath, String name, IndexEntriesWalkerInterruptable iterator);

    boolean fileExists(String fileNameEncoded);

    // search methods
//    TreeNode[] findInContext(String ctxPath, String name);

    // search for the name in the root context
//    TreeNode[] findInRootContext(String name);

    /**
     * Search for name starting from startCtxPath and climbing Up
     *
     * @param startCtxPath - path to start climbing
     * @param name - name to look for
     * @param refType - type  of name
     * @param handler - listener
     */
    void searchContextTree_DownUp(String startCtxPath, String name, int refType, TreeNodeHandler handler);


    /**
     * Save names into the file
     *
     * @param fileName - name of the file to save to
     * @return - number of saved entries
     * @throws java.io.IOException -
     */
    int dumpNames(String fileName) throws IOException;

    int dumpNames(String fileName, IndexEntryFilter filter) throws IOException;

    /**
     * Populate cache with names from the specified file
     *
     * @param fileName - name of the file being loaded
     * @return - number of loaded names
     * @throws IOException -
     */
    int loadNames(String fileName) throws IOException;
    int loadNames(InputStream in) throws IOException;


    /**
     * Get source text locator
     *
     * @return
     */
    interface IndexEntryFilter {
        boolean accept(String ctxPath, String value);
    }

    void clear();
}
