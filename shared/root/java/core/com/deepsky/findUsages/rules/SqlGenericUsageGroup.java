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

package com.deepsky.findUsages.rules;

import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.openapi.actionSystem.DataSink;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.TypeSafeDataProvider;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SmartPointerManager;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.usageView.UsageInfo;
import com.intellij.usages.UsageGroup;
import com.intellij.usages.UsageView;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public abstract class SqlGenericUsageGroup<T extends NavigationItem & PsiElement>  implements UsageGroup, TypeSafeDataProvider {
    private static final Logger LOG = Logger.getInstance("#SqlGenericUsageGroup");

    private final SmartPsiElementPointer myMethodPointer;

    public SqlGenericUsageGroup(T element) {
        myMethodPointer = SmartPointerManager.getInstance(element.getProject()).createLazyPointer(element);
        update();
    }

    @NotNull
    public abstract String getMyName();

    public Icon getIcon(boolean isOpen){
        return null;
    }

    public void update() {
//        if (isValid()) {
//            myIcon = getIcon(); //(getElement());
//        }
    }


    public int hashCode() {
        return getMyName().hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof SqlGenericUsageGroup)) {
            return false;
        }
        SqlGenericUsageGroup group = (SqlGenericUsageGroup) object;
        if (isValid() && group.isValid()) {
            return getElement().getManager().areElementsEquivalent(getElement(), (PsiElement)group.getElement());
        }
        return Comparing.equal(getMyName(), ((SqlGenericUsageGroup) object).getMyName());
    }


    @NotNull
    public String getText(UsageView view) {
        return getMyName();
    }

    private T getElement() {
        return (T) myMethodPointer.getElement();
    }

    public FileStatus getFileStatus() {
        return isValid() ? getElement().getFileStatus() : null;
    }

    public boolean isValid() {
        final T body = getElement();
        return body != null && body.isValid();
    }

    public void navigate(boolean focus) throws UnsupportedOperationException {
        if (canNavigate()) {
            getElement().navigate(focus);
        }
    }

    public boolean canNavigate() {
        return isValid();
    }

    public boolean canNavigateToSource() {
        return canNavigate();
    }

    public int compareTo(UsageGroup usageGroup) {
        if (!(usageGroup instanceof SqlGenericUsageGroup)) {
            LOG.error("SqlGenericUsageGroup expected but " + usageGroup.getClass() + " found");
        }

        return getMyName().compareTo(((SqlGenericUsageGroup) usageGroup).getMyName());
    }

    public void calcData(final DataKey key, final DataSink sink) {
        if (!isValid()) return;
        if (LangDataKeys.PSI_ELEMENT == key) {
            sink.put(LangDataKeys.PSI_ELEMENT, getElement());
        }
        if (UsageView.USAGE_INFO_KEY == key) {
            T element = getElement();
            if (element != null) {
                sink.put(UsageView.USAGE_INFO_KEY, new UsageInfo(element));
            }
        }
    }

}

