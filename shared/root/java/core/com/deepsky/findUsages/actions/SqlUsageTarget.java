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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

import com.deepsky.findUsages.SQLFindItemPresentation;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.usages.UsageTarget;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SqlUsageTarget implements UsageTarget {


    public void findUsages() {
    }

    public void findUsagesInEditor(@NotNull FileEditor fileEditor) {
    }

    public void highlightUsages(PsiFile file, Editor editor, boolean clearHighlights) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isValid() {
        return true;
    }

    public boolean isReadOnly() {
        return true;
    }

    @Nullable
    public VirtualFile[] getFiles() {
        return new VirtualFile[0];
    }

    public void update() {

    }

    @Nullable
    public String getName() {
        return "Hello";
    }

    @Nullable
    public ItemPresentation getPresentation() {
        return new SQLFindItemPresentation(presentableText);
    }

    public FileStatus getFileStatus() {
        return FileStatus.MODIFIED;
    }

    public void navigate(boolean b) {
    }

    public boolean canNavigate() {
        return true;
    }

    public boolean canNavigateToSource() {
        return false;
    }

    String presentableText = "Just Text";
    public void setPresentableText(String presentableText){
        this.presentableText = presentableText;
    }
}
