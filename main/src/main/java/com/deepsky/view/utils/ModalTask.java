package com.deepsky.view.utils;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModalTask extends Task.Modal {
    public ModalTask(@Nullable Project project, @NotNull String title, boolean canBeCancelled) {
        super(project, title, canBeCancelled);
    }

    public void run(@NotNull ProgressIndicator progressIndicator) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
