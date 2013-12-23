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

package com.deepsky.findUsages.scopes;

import com.deepsky.database.ora.DbUrl;
import com.intellij.ide.util.scopeChooser.ScopeDescriptor;
import com.intellij.ide.util.scopeChooser.ScopeDescriptorProvider;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import org.jetbrains.annotations.NotNull;

public class DbScopeDescriptorProvider implements ScopeDescriptorProvider {
    @NotNull
    public ScopeDescriptor[] getScopeDescriptors(Project project) {
        return new ScopeDescriptor[]{
            new DbScopeDescriptor(new DbSearchScope())
        };
    }

    public static class DbScopeDescriptor extends ScopeDescriptor {
        public DbScopeDescriptor(SearchScope scope) {
            super(scope);
        }
    }


    public static class DbSearchScope extends GlobalSearchScope {
        private DbUrl dbUrl;

        @Override
        public boolean contains(VirtualFile file) {
            return false;
        }

        @Override
        public int compare(VirtualFile file1, VirtualFile file2) {
            return 0;
        }

        @Override
        public boolean isSearchInModuleContent(@NotNull Module aModule) {
            return false;
        }

        @Override
        public boolean isSearchInLibraries() {
            return false;
        }

//        @NotNull
//        public SearchScope intersectWith(@NotNull SearchScope scope2) {
//            return this;
//        }
//
//        @NotNull
//        public SearchScope union(@NotNull SearchScope scope) {
//            return this;
//        }

        public String getDisplayName() {
          return "Database schema";
        }

        public DbUrl getDbUrl() {
            return dbUrl;
        }
    }
}
