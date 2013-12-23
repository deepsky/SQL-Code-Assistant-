package com.deepsky.lang.common.tnsnames;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class TNSFileBase extends PsiFileBase {

    protected TNSFileBase(@NotNull FileViewProvider viewProvider, @NotNull Language language) {
        super(viewProvider, language);
    }

    @NotNull
    public FileType getFileType() {
        return null;
    }
}
