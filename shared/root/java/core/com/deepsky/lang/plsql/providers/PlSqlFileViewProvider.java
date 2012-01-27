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

package com.deepsky.lang.plsql.providers;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlLanguage;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.integration.PlSqlFileChangeTracker;
import com.deepsky.lang.integration.PlSqlFileChangeTrackerAbstract;
import com.intellij.lang.Language;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.SingleRootFileViewProvider;
import com.intellij.testFramework.LightVirtualFile;
import org.jetbrains.annotations.NotNull;

public class PlSqlFileViewProvider extends SingleRootFileViewProvider{

    private PlSqlFileChangeTrackerAbstract tracker;

    public PlSqlFileViewProvider(@NotNull PsiManager manager, @NotNull VirtualFile virtualFile, boolean physical) {
        super(manager, virtualFile, physical);
        this.tracker = PluginKeys.PLSQLFILE_CHANGE_TRACKER.getData(manager.getProject());
    }

    protected PlSqlFileViewProvider(@NotNull PsiManager manager, @NotNull VirtualFile virtualFile, boolean physical, @NotNull Language language) {
        super(manager, virtualFile, physical, language);
        this.tracker = PluginKeys.PLSQLFILE_CHANGE_TRACKER.getData(manager.getProject());
    }

    public void contentsSynchronized() {
        super.contentsSynchronized();
        PsiFile file = getCachedPsi(null);
        if(file instanceof PlSqlFile){
            tracker.indexPlSqlFile((PlSqlFile) file);
        } else {
            // Psi was not built? skip indexing
        }
    }

    @NotNull
    public SingleRootFileViewProvider createCopy(final VirtualFile copy) {
        return new PlSqlFileViewProvider(getManager(), copy, false, Language.findInstance(PlSqlLanguage.class));
    }

    @NotNull
    public SingleRootFileViewProvider createCopy(final LightVirtualFile copy) {
        return new PlSqlFileViewProvider(getManager(), copy, false, Language.findInstance(PlSqlLanguage.class));
    }

}
