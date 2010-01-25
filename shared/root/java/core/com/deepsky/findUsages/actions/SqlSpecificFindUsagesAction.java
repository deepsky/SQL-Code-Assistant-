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

import com.deepsky.database.ConnectionManager;
import com.deepsky.findUsages.SqlFindUsagesHelper;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PluginKeys;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;


public class SqlSpecificFindUsagesAction extends AnAction {

    private static final Logger LOG = Logger.getInstance("#SqlSpecificFindUsagesAction");

    @Override
    public void actionPerformed(AnActionEvent action) {
        //Editor e = action.getData(LangDataKeys.EDITOR);
        //PsiElement _psi = action.getData(LangDataKeys.PSI_ELEMENT);
        Project project = action.getData(LangDataKeys.PROJECT);
        ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
        if (manager == null || !manager.isConnected()) {
            Messages.showErrorDialog(
                    "Connection is not established. Try to establish connection with a database and run Find Usages again.",
                    "Find Usages not available");
            return;
        }

        int offset = action.getData(LangDataKeys.EDITOR).getCaretModel().getOffset();
        PsiElement _psi = action.getData(LangDataKeys.PSI_FILE).findElementAt(offset);

        SqlFindUsagesHelper.runFindUsages(project, _psi);
    }


    public void update(AnActionEvent event) {
        super.update(event);
        Presentation presentation = event.getPresentation();

        PsiFile psi = event.getData(LangDataKeys.PSI_FILE);
        if (psi instanceof PlSqlFile) {
            presentation.setVisible(true);

            int offset = event.getData(LangDataKeys.EDITOR).getCaretModel().getOffset();
            PsiElement _psi = event.getData(LangDataKeys.PSI_FILE).findElementAt(offset);

            if (SqlFindUsagesHelper.canApplyFindUsagesTo(_psi) != null) {
                presentation.setEnabled(true);
            } else {
                presentation.setEnabled(false);
            }
        } else {
            presentation.setVisible(false);
        }
    }


}
