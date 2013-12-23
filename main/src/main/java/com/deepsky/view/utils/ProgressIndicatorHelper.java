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

package com.deepsky.view.utils;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.ide.DataManager;
import com.deepsky.database.MyProgressIndicator;
import org.jetbrains.annotations.NotNull;

public class ProgressIndicatorHelper {

    String title;
    Project project;
    final boolean[] result = new boolean[1];


    public ProgressIndicatorHelper(Project project, String title) {
        this.project = project;
        this.title = title;
    }

    public boolean run(final ProgressIndicatorListener listener) {

        return ProgressManager.getInstance().runProcessWithProgressSynchronously(new Runnable() {
            public void run() {
                final com.intellij.openapi.progress.ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
                listener.updated(ProgressIndicatorListener.PROGRESS_IND_IS_ABOUT_START);
                try {
                    if (progressIndicator != null) {
                        progressIndicator.setIndeterminate(true);

                        do {
                            progressIndicator.setText(listener.getText());
                            Thread.sleep(1000);
                        } while (!(progressIndicator.isCanceled() || listener.isComplete()));

                        result[0] = listener.getResult();
                    }
                } catch (InterruptedException e) {
                    result[0] = false;
                }
                listener.updated(ProgressIndicatorListener.PROGRESS_IND_COMPLETED);
            }
        }, title, true, project);
    }


    public boolean runDeterminateProgressInd(final ProgressIndicatorListener listener) {

        DataContext dataContext = DataManager.getInstance().getDataContext();
        //Project project = LangDataKeys.PROJECT.getData(dataContext);

        return ProgressManager.getInstance().runProcessWithProgressSynchronously(new Runnable() {
            public void run() {
                final com.intellij.openapi.progress.ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
                listener.updated(ProgressIndicatorListener.PROGRESS_IND_IS_ABOUT_START);
                try {
                    double t0 = 0.;
                    if (progressIndicator != null) {
                        progressIndicator.setIndeterminate(false);

                        do {
                            progressIndicator.setText(listener.getText());
                            progressIndicator.setFraction(t0);
                            Thread.sleep(1000);
                            t0+= 0.1;
                        } while (t0 < 1.); //!(progressIndicator.isCanceled() || listener.isComplete()));

                        result[0] = listener.getResult();
                    }
                } catch (InterruptedException e) {
                    result[0] = false;
                }
                listener.updated(ProgressIndicatorListener.PROGRESS_IND_COMPLETED);
            }
        }, title, true, project);
    }

/*
    public void runBackgrounableWithProgressInd(
            @NotNull ProcessStatusReporter reporter, boolean startInBackground) {
        new BackgroundableTaskImpl(reporter, true, startInBackground).queue();
    }
*/


    public void runBackgrounableWithProgressInd(
            @NotNull MyProgressIndicator reporter, boolean startInBackground) {
        if(project != null){
            new BackgroundableTaskImpl(project, title, reporter, true, startInBackground).queue();
        } else {
            new BackgroundableTaskImpl(title, reporter, true, startInBackground).queue();
        }
    }

    public void runBackgrounableWithProgressInd(
            @NotNull ProgressIndicatorController reporter, boolean startInBackground) {
        new BackgroundableIndeterminateTaskImpl(title, reporter, true, startInBackground).queue();
    }


    public boolean getResult() {
        return result[0];
    }

    class BackgroundableTaskImpl extends BackgroundableTask {

        boolean completed = false;
        boolean startInBackground;

        MyProgressIndicator reporter;

        public BackgroundableTaskImpl(
                String title,
                @NotNull MyProgressIndicator reporter,
                boolean cancelable,
                boolean startInBackground) {
            super(project, title, cancelable);
            this.startInBackground = startInBackground;
            this.reporter = reporter;
        }

        public BackgroundableTaskImpl(
                @NotNull Project project,
                String title,
                @NotNull MyProgressIndicator reporter,
                boolean cancelable,
                boolean startInBackground) {
            super(project, title, cancelable);
            this.startInBackground = startInBackground;
            this.reporter = reporter;
        }

        public void run(@NotNull com.intellij.openapi.progress.ProgressIndicator progressIndicator) {

            if(!(reporter.getStatus() == MyProgressIndicator.ProgressStatus.INPROGRESS)){
                // all done
                return;
            }

            while (true){
                switch(reporter.getStatus()){
                    case INPROGRESS:
                        break;
                    case CANCELED:
                    case DONE_SUCCESSFUL:
                        return;
                    case FAILED:
                        final String errors = reporter.getErrorMessage();
                        ApplicationManager.getApplication().invokeLater(new Runnable(){
                            public void run() {
                                //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
                                Messages.showInfoMessage(project, errors, "Connection failed");
                            }
                        });
                        return;
                }

                if(progressIndicator.isCanceled()){
                    reporter.cancel();
                }

                try {
                    progressIndicator.setText(reporter.getCurrentStepName());

                    if(reporter.getNumberOfAllChunks() == -1){
                        progressIndicator.setIndeterminate(true);
                    } else {
                        progressIndicator.setIndeterminate(false);
                        double fr = ((double)reporter.getNumberOfDoneChunks())/reporter.getNumberOfAllChunks();
                        progressIndicator.setFraction(fr);
                    }

                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        public boolean shouldStartInBackground() {
            return startInBackground;
        }
    }



    private class BackgroundableIndeterminateTaskImpl extends BackgroundableTask {

        boolean startInBackground;
        ProgressIndicatorController reporter;

        public BackgroundableIndeterminateTaskImpl(
                String title,
                ProgressIndicatorController reporter,
                boolean cancelable,
                boolean startInBackground) {
            super(project, title, cancelable);
            this.reporter = reporter;
            this.startInBackground = startInBackground;
        }

        public void run(@NotNull com.intellij.openapi.progress.ProgressIndicator progressIndicator) {

            if(!(reporter.getStatus() == ProgressIndicatorController.INPROGRESS)){
                // all done
                return;
            }

            while (true){
                switch(reporter.getStatus()){
                    case ProgressIndicatorController.INPROGRESS:
                        break;
                    case ProgressIndicatorController.CANCELED:
                    case ProgressIndicatorController.DONE_SUCCESSFULLY:
                        return;
                    case ProgressIndicatorController.FAILED:
                        final String errors = reporter.getErrorMessage();
                        final String errorDialogTitle = reporter.errorDialogTitle(); 
                        ApplicationManager.getApplication().invokeLater(new Runnable(){
                            public void run() {
                                Messages.showInfoMessage(project, errors, errorDialogTitle);
                            }
                        });
                        return;
                }

                if(progressIndicator.isCanceled()){
                    reporter.cancel();
                }

                try {
                    progressIndicator.setText(reporter.getCurrentStepName());
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        public boolean shouldStartInBackground() {
            return startInBackground;
        }
    }

/*
 class yy extends Task.Backgroundable {
     public yy(@org.jetbrains.annotations.Nullable Project project,
               @org.jetbrains.annotations.NotNull String s) {
         super(project, s, false);

         this.asBackgroundable();

     }

     public void run(ProgressIndicator progressIndicator) {
         progressIndicator.
         //To change body of implemented methods use File | Settings | File Templates.
     }
 }
*/

}
