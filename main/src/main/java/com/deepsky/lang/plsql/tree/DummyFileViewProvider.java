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

package com.deepsky.lang.plsql.tree;

import com.intellij.lang.Language;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.testFramework.LightVirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class DummyFileViewProvider implements FileViewProvider {
    @NotNull
    public PsiManager getManager() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Document getDocument() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public CharSequence getContents() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public VirtualFile getVirtualFile() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public Language getBaseLanguage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public Set<Language> getLanguages() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiFile getPsi(@NotNull Language target) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public List<PsiFile> getAllFiles() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isEventSystemEnabled() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isPhysical() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long getModificationStamp() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean supportsIncrementalReparse(Language rootLanguage) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void rootChanged(PsiFile psiFile) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void beforeContentsSynchronized() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void contentsSynchronized() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public FileViewProvider clone() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement findElementAt(int offset) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiReference findReferenceAt(int offset) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement findElementAt(int offset, Language language) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement findElementAt(int offset, Class<? extends Language> lang) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiReference findReferenceAt(int offsetInElement, @NotNull Language language) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public FileViewProvider createCopy(VirtualFile copy) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public PsiFile getStubBindingRoot() {
        return null;
    }

    public boolean isLockedByPsiOperations() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public FileViewProvider createCopy(LightVirtualFile copy) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> T getUserData(@NotNull Key<T> key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> void putUserData(@NotNull Key<T> key, @Nullable T value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
