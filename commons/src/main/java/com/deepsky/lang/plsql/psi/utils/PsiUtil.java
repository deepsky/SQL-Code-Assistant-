package com.deepsky.lang.plsql.psi.utils;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: sky
 * Date: 12/9/12
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class PsiUtil {
    public static boolean moveToOffset(@NotNull PsiFile plSqlFile, int offset) {
        Project project = plSqlFile.getProject();

        final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        OpenFileDescriptor desc = new OpenFileDescriptor(project, plSqlFile.getVirtualFile(), offset);
        return fileEditorManager.openTextEditor(desc, true) != null;
    }

}
