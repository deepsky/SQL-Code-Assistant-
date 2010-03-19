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

package com.deepsky.findUsages.actions;

import com.deepsky.findUsages.scopes.DbScopeDescriptorProvider;
import com.intellij.find.FindSettings;
import com.intellij.find.findUsages.FindUsagesOptions;
import com.intellij.ide.util.scopeChooser.ScopeDescriptor;
import com.intellij.ide.util.scopeChooser.ScopeDescriptorProvider;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class FindUsagesOptionsAdopted extends FindUsagesOptions {

    public FindUsagesOptionsAdopted(@NotNull Project project, @Nullable DataContext dataContext) {
        this(project, dataContext, false);    
    }

    public FindUsagesOptionsAdopted(@NotNull Project project, @Nullable DataContext dataContext, boolean preselectDatabaseScope) {
        super(project, dataContext);
        if (preselectDatabaseScope) {
            for (ScopeDescriptorProvider provider : Extensions.getExtensions(ScopeDescriptorProvider.EP_NAME)) {
                for (ScopeDescriptor scopeDescriptor : provider.getScopeDescriptors(project)) {
                    if (scopeDescriptor instanceof DbScopeDescriptorProvider.DbScopeDescriptor) {
                        searchScope = scopeDescriptor.getScope();
                        return;
                    }
                }
            }
        } else {
            String defaultScopeName = FindSettings.getInstance().getDefaultScopeName();
            if (!searchScope.getDisplayName().equals(defaultScopeName)) {
                // search among external specified
                for (ScopeDescriptorProvider provider : Extensions.getExtensions(ScopeDescriptorProvider.EP_NAME)) {
                    for (ScopeDescriptor scopeDescriptor : provider.getScopeDescriptors(project)) {
                        if (defaultScopeName.equals(scopeDescriptor.getDisplay())) {
                            // specified Search Scope found!
                            // todo - dirty workaround! we are not interesed in any scope except of SQL specific
                            if (scopeDescriptor instanceof DbScopeDescriptorProvider.DbScopeDescriptor) {
                                searchScope = scopeDescriptor.getScope();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

}
