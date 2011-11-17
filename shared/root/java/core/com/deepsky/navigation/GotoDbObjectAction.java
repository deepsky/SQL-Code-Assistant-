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

package com.deepsky.navigation;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.view.schema_pane.DbBrowserToolWindow;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.ide.actions.GotoActionBase;
import com.intellij.ide.util.gotoByName.ChooseByNamePopup;
import com.intellij.ide.util.gotoByName.ChooseByNamePopupComponent;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

public class GotoDbObjectAction extends GotoActionBase implements DumbAware {

    @Override
    public void gotoActionPerformed(AnActionEvent e) {
        FeatureUsageTracker.getInstance().triggerFeatureUsed("navigation.popup.file");
        final Project project = e.getData(PlatformDataKeys.PROJECT);
        final GotoDbObjectModel gotoFileModel = new GotoDbObjectModel(project);
        final ChooseByNamePopup popup = ChooseByNamePopup.createPopup(project, gotoFileModel, getPsiContext(e));
        popup.invoke(new ChooseByNamePopupComponent.Callback() {
            public void onClose() {
                if (GotoDbObjectAction.class.equals(myInAction)) myInAction = null;
            }

            public void elementChosen(Object element) {
                if (element == null) return;
                final PsiElement psiElement =
                        (element instanceof NavigationItemEx)?
                                ((NavigationItemEx)element).loadPhisicalElement():
                                (PsiElement) element;

                if(psiElement == null){
                    // todo -- FIX ME the case when the requested object is in excluded folder (?)
                    return;
                }
                final PsiFile file = psiElement.getContainingFile();
                final int offset = psiElement.getTextOffset();
                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    public void run() {
                        // If object being searched for is hosted in the database,
                        // open Database Browser and point it out
                        if(file instanceof PlSqlFile && psiElement instanceof PlSqlElement){
                            if(!((PlSqlFile)file).getDbUrl().equals(IndexManager.FS_URL)){
                                DbBrowserToolWindow dbBrowser = project.getComponent(DbBrowserToolWindow.class);
                                dbBrowser.selectElementInBrowser((PlSqlElement) psiElement);
                                return;
                            }
                        }
                        final OpenFileDescriptor descriptor = new OpenFileDescriptor(project, file.getVirtualFile(), offset);
                        if (descriptor.canNavigate()) {
                            descriptor.navigate(true);
                        }
                    }
                }, ModalityState.NON_MODAL);
            }
        }, ModalityState.current(), true);
    }

}
