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

package com.deepsky.lang.plsql.completion;

import com.deepsky.lang.plsql.resolver.index.ContextItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface NameProvider {

    /**
     * Search for the items in the root context
     * @param ctxTypes -
     * @param name - if name is null return all items withspecified type
     * @return
     */
//    @NotNull
//    ContextItem[] findCtxItems(int[] ctxTypes, @Nullable String name);


//    @NotNull
//    ContextItem[] findCtxItems(String ctxPath, String name);


    /**
     * Search for the items with specified types in the context
     * @param ctxPath - context to search
     * @param ctxTypes - to look for
     * @return
     */
//    @NotNull
//    ContextItem[] findCtxItems(String ctxPath, int[] ctxTypes);

    // get value of the context path
    String getContextPathValue(String ctxPath);

    @NotNull
    ContextItem[] findTopLevelItems(int[] ctxTypes, @Nullable String name);

    @NotNull
    ContextItem[] findLocalCtxItems(String ctxPath, int[] ctxTypes);
}